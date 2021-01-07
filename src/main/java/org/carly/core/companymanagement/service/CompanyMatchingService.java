package org.carly.core.companymanagement.service;

import org.bson.types.ObjectId;
import org.carly.api.rest.request.CompanyFactoryMatchRequest;
import org.carly.api.rest.request.CompanyMatchingRequest;
import org.carly.api.rest.response.CompanyFactoriesMatched;
import org.carly.api.rest.response.CompanyMatchResponse;
import org.carly.api.rest.response.CompanyResponse;
import org.carly.api.rest.response.ErrorResponse;
import org.carly.core.companymanagement.mapper.CompanyMapper;
import org.carly.core.companymanagement.mapper.CompanyMatchMapper;
import org.carly.core.companymanagement.model.CompanyMatch;
import org.carly.core.companymanagement.model.CompanyMatchStatus;
import org.carly.core.companymanagement.repository.CompanyMatchRepository;
import org.carly.core.security.service.LoggedUserProvider;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.shared.exception.ErrorCode;
import org.carly.core.shared.utils.time.TimeService;
import org.carly.core.usermanagement.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyMatchingService {

    private final CompanyMatchRepository companyMatchRepository;
    private final CompanyFindService companyFindService;
    private final LoggedUserProvider loggedUserProvider;
    private final TimeService timeService;
    private final CompanyMatchMapper companyMatchMapper;
    private final CompanyMapper companyMapper;

    public CompanyMatchingService(CompanyMatchRepository companyMatchRepository,
                                  CompanyFindService companyFindService,
                                  LoggedUserProvider loggedUserProvider,
                                  TimeService timeService,
                                  CompanyMatchMapper companyMatchMapper,
                                  CompanyMapper companyMapper) {
        this.companyMatchRepository = companyMatchRepository;
        this.companyFindService = companyFindService;
        this.loggedUserProvider = loggedUserProvider;
        this.timeService = timeService;
        this.companyMatchMapper = companyMatchMapper;
        this.companyMapper = companyMapper;
    }

    public ResponseEntity<CompanyMatchResponse> createMatchingRequest(CompanyMatchingRequest matchingRequest) {
        User company = companyFindService.getCompanyById(new ObjectId(matchingRequest.getCompanyId()));
        User factory = companyFindService.getCompanyById(new ObjectId(matchingRequest.getFactoryId()));

        CompanyMatch companyMatch = new CompanyMatch(
                company.getCompany().getName(),
                company.getId(),
                factory.getCompany().getName(),
                factory.getId(),
                CompanyMatchStatus.PENDING,
                timeService.getLocalDateTime(),
                loggedUserProvider.loggedUser().getEmail()
        );

        companyMatchRepository.save(companyMatch);

        return ResponseEntity.ok(companyMatchMapper.mapDomainToResponse(companyMatch));
    }

    public ResponseEntity<CompanyMatchResponse> addResponseForRequest(CompanyFactoryMatchRequest matchResponse) {
        CompanyMatch companyMatch = companyMatchRepository.findById(new ObjectId(matchResponse.getCompanyMatchId()))
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getDescription()));
        companyMatch.setStatus(matchResponse.getStatusResponse());
        companyMatch.setModifiedBy(loggedUserProvider.loggedUser().getEmail());
        companyMatch.setModifiedDate(timeService.getLocalDateTime());
        companyMatchRepository.save(companyMatch);
        return ResponseEntity.ok(companyMatchMapper.mapDomainToResponse(companyMatch));
    }

    public ResponseEntity<CompanyMatchResponse> declineRequestMatching(String matchRequestId) {
        CompanyMatch companyMatch = companyMatchRepository.findById(new ObjectId(matchRequestId))
                .stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getDescription()));
        if (companyMatch.getStatus() == CompanyMatchStatus.PENDING || companyMatch.getStatus() == CompanyMatchStatus.ACCEPTED) {
            companyMatch.setStatus(CompanyMatchStatus.DECLINED);
            companyMatchRepository.save(companyMatch);
            return ResponseEntity.ok(new CompanyMatchResponse(companyMatch.getId().toHexString(), companyMatch.getCompanyName(), companyMatch.getFactoryId().toHexString(), companyMatch.getFactoryName(), companyMatch.getStatus()));
        }
        return ResponseEntity.badRequest().body(new CompanyMatchResponse(companyMatch.getId().toHexString(), companyMatch.getCompanyName(), companyMatch.getFactoryId().toHexString(), companyMatch.getFactoryName(), companyMatch.getStatus()));
    }

    public List<CompanyMatch> findAllMatchedFactoriesByCompanyId(String companyId) {
        return companyMatchRepository.findAllByCompanyIdAndStatus(new ObjectId(companyId), CompanyMatchStatus.ACCEPTED);
    }

    public ResponseEntity<?> getAllMatchedFactoriesByCompanyId(String companyId) {
        List<CompanyMatch> matchedList = companyMatchRepository.findAllByCompanyIdAndStatus(new ObjectId(companyId), CompanyMatchStatus.ACCEPTED);
        if (matchedList != null && !matchedList.isEmpty()) {
            List<CompanyMatchResponse> companyMatches = matchedList.stream().map(companyMatchMapper::mapDomainToResponse).collect(Collectors.toList());
            return ResponseEntity.ok(new CompanyFactoriesMatched(companyMatches));
        }
        return ResponseEntity.ok(new ErrorResponse("Cannot find any matched companies"));
    }

    public ResponseEntity<?> findAllNonMatchedFactories(String companyIdd) {
        final ObjectId companyId = new ObjectId(companyIdd);
        List<User> factories = companyFindService.findAllFactoriesCompanies();
        List<User> matchedUsers = new ArrayList<>();
        for (User factoryId : factories) {
            final CompanyMatch matched = companyMatchRepository.findByCompanyIdAndFactoryId(companyId, factoryId.getId()).stream().findFirst().orElse(null);
            if (matched != null) {
                final User user = factories.stream()
                        .filter(f -> f.getId().equals(matched.getFactoryId())).findFirst().orElse(null);
                matchedUsers.add(user);
            }
        }
        factories.removeIf(matchedUsers::contains);
        final List<CompanyResponse> companyResponses = factories.stream().map(companyMapper::simplifyRestObject).collect(Collectors.toList());
        return ResponseEntity.ok(companyResponses);
    }
}
