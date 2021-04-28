package org.carly.core.companymanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.CompanyMatchingRequest;
import org.carly.api.rest.response.*;
import org.carly.core.companymanagement.mapper.CompanyMapper;
import org.carly.core.companymanagement.mapper.CompanyMatchMapper;
import org.carly.core.companymanagement.model.CompanyMatch;
import org.carly.core.companymanagement.model.MatchStatus;
import org.carly.core.companymanagement.repository.CompanyMatchRepository;
import org.carly.core.security.service.LoggedUserProvider;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.shared.exception.ErrorCode;
import org.carly.core.shared.utils.time.TimeService;
import org.carly.core.usermanagement.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    public ResponseEntity<?> findAllMatchedAndPendingContractsByCompanyId(String companyId) {
        List<CompanyMatch> matchedList = new ArrayList<>();
        List<CompanyMatch> pendingList = findAllPendingFactoriesByCompanyId(companyId);
        List<CompanyMatch> acceptedList = findAllMatchedFactoriesByCompanyId(companyId);

        if (!pendingList.isEmpty()) {
            matchedList.addAll(pendingList);
        }

        if (!acceptedList.isEmpty()) {
            matchedList.addAll(acceptedList);
        }

        if (matchedList.isEmpty()) {
            log.info("Couldn't find any pending or accepted matching for company with id: {}", companyId);
        }

        List<CompanyMatchResponse> companyMatches = matchedList.stream().map(companyMatchMapper::mapDomainToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(new CompanyFactoriesMatched(companyMatches));
    }

    public ResponseEntity<?> findAllFactoriesToMatchByCompanyId(String id) {
        final ObjectId companyId = new ObjectId(id);
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
        final List<CompanyResponse> companyResponses = factories.stream()
                                                                .map(companyMapper::simplifyRestObject)
                                                                .collect(Collectors.toList());
        return ResponseEntity.ok(companyResponses);
    }

    public ResponseEntity<SuccessResponse> createMatchingRequest(CompanyMatchingRequest matchingRequest) {
        User company = companyFindService.getCompanyById(new ObjectId(matchingRequest.getCompanyId()));
        User factory = companyFindService.getCompanyById(new ObjectId(matchingRequest.getFactoryId()));

        CompanyMatch companyMatch = new CompanyMatch(
                company.getCompany().getName(),
                company.getId(),
                factory.getCompany().getName(),
                factory.getId(),
                MatchStatus.PENDING,
                timeService.getLocalDateTime(),
                loggedUserProvider.loggedUser().getEmail()
        );

        companyMatchRepository.save(companyMatch);

        return ResponseEntity.ok(new SuccessResponse(String.format("Request matching between %s and %s created!",
                                                                    company.getCompany().getName(),
                                                                    factory.getCompany().getName())));
    }

    public ResponseEntity<?> cancelMatching(MatchResponse matchResponse) {
        CompanyMatch companyMatch = companyMatchRepository.findById(new ObjectId(matchResponse.getMatchId()))
                                                          .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getDescription()));
        companyMatchRepository.delete(companyMatch);
        return ResponseEntity.ok(new SuccessResponse(String.format("Matching between %s and %s canceled!",
                                                                    companyMatch.getCompanyName(),
                                                                    companyMatch.getFactoryName())));
    }

    public ResponseEntity<?> confirmMatchingWithCompany(MatchResponse matchResponse) {
        CompanyMatch companyMatch = companyMatchRepository.findById(new ObjectId(matchResponse.getMatchId()))
                                                          .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getDescription()));
        companyMatch.setStatus(MatchStatus.ACCEPTED);
        companyMatch.setModifiedBy(loggedUserProvider.loggedUser().getEmail());
        companyMatch.setModifiedDate(timeService.getLocalDateTime());
        companyMatchRepository.save(companyMatch);
        return ResponseEntity.ok(new SuccessResponse(String.format("Matching between %s and %s successfully created!",
                                                                    companyMatch.getCompanyName(),
                                                                    companyMatch.getFactoryName())));
    }

    public List<CompanyMatch> findAllPendingFactoriesByCompanyId(String companyId) {
        return findAllMatchingByCompanyIdAndStatus(companyId, MatchStatus.PENDING);
    }

    public List<CompanyMatch> findAllMatchedFactoriesByCompanyId(String companyId) {
        return findAllMatchingByCompanyIdAndStatus(companyId, MatchStatus.ACCEPTED);
    }

    public List<CompanyMatch> findAllMatchingByCompanyIdAndStatus(String companyId, MatchStatus status) {
        List<CompanyMatch> matchedList = companyMatchRepository.findAllByCompanyIdAndStatus(new ObjectId(companyId), status);
        if (matchedList != null && !matchedList.isEmpty()) {
            log.info("Found ({}) {} matching for company with id: {}", matchedList.size(), status.name(), companyId);
            return matchedList;
        }
        log.info("Couldn't find any {} matching for company with id: {}", status.name(), companyId);
        return Collections.emptyList();
    }

    public ResponseEntity<?> findAllAcceptedMatching(String factoryId) {
        return findAllByMatchingByFactoryIdAndStatus(factoryId, MatchStatus.ACCEPTED);
    }

    public ResponseEntity<?> findAllPendingContracts(String factoryId) {
        return findAllByMatchingByFactoryIdAndStatus(factoryId, MatchStatus.PENDING);
    }

    public ResponseEntity<?> findAllByMatchingByFactoryIdAndStatus(String factoryId, MatchStatus status) {
        List<CompanyMatch> allMatching = companyMatchRepository.findAllByFactoryIdAndStatus(new ObjectId(factoryId), status);
        if (allMatching != null && !allMatching.isEmpty()) {
            log.info("Found ({}) {} matching.", allMatching.size(), status.name());
            List<CompanyMatchResponse> allPendingContractsResponse = allMatching.stream()
                                                                                .map(companyMatchMapper::mapDomainToResponse)
                                                                                .collect(Collectors.toList());
            return ResponseEntity.ok().body(new CompanyFactoriesMatched(allPendingContractsResponse));
        }
        return ResponseEntity.ok().body(new CompanyFactoriesMatched(Collections.emptyList()));
    }

    public List<ObjectId> findMatchedFactoryIds(String companyId) {
        List<CompanyMatch> matchedList = companyMatchRepository.findAllByCompanyIdAndStatus(new ObjectId(companyId), MatchStatus.ACCEPTED);
        return matchedList.stream().map(CompanyMatch::getFactoryId).collect(Collectors.toList());
    }

    public CompanyMatch findMatchByCompanyIdAndFactoryId(ObjectId companyId, ObjectId factoryId) {
        return companyMatchRepository.findByCompanyIdAndFactoryId(companyId, factoryId).orElse(null);
    }
}
