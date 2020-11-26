package org.carly.core.companymanagement.service;

import org.bson.types.ObjectId;
import org.carly.api.rest.request.CompanyFactoryMatchRequest;
import org.carly.api.rest.request.CompanyMatchingRequest;
import org.carly.api.rest.response.CompanyMatchResponse;
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

@Service
public class CompanyMatchingService {

    private final CompanyMatchRepository companyMatchRepository;
    private final CompanyFindService companyFindService;
    private final LoggedUserProvider loggedUserProvider;
    private final TimeService timeService;
    private final CompanyMatchMapper companyMatchMapper;

    public CompanyMatchingService(CompanyMatchRepository companyMatchRepository,
                                  CompanyFindService companyFindService,
                                  LoggedUserProvider loggedUserProvider,
                                  TimeService timeService,
                                  CompanyMatchMapper companyMatchMapper) {
        this.companyMatchRepository = companyMatchRepository;
        this.companyFindService = companyFindService;
        this.loggedUserProvider = loggedUserProvider;
        this.timeService = timeService;
        this.companyMatchMapper = companyMatchMapper;
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
                loggedUserProvider.provideUserDetail().getEmail()
        );

        companyMatchRepository.save(companyMatch);

        return ResponseEntity.ok(companyMatchMapper.mapDomainToResponse(companyMatch));
    }

    public ResponseEntity<CompanyMatchResponse> addResponseForRequest(CompanyFactoryMatchRequest matchResponse) {
        CompanyMatch companyMatch = companyMatchRepository.findById(new ObjectId(matchResponse.getCompanyMatchId()))
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getDescription()));
        companyMatch.setStatus(matchResponse.getStatusResponse());
        companyMatch.setModifiedBy(loggedUserProvider.provideUserDetail().getEmail());
        companyMatch.setModifiedDate(timeService.getLocalDateTime());
        companyMatchRepository.save(companyMatch);
        return ResponseEntity.ok(companyMatchMapper.mapDomainToResponse(companyMatch));
    }

    public ResponseEntity<CompanyMatchResponse> declineRequestMatching(String matchRequestId) {
        CompanyMatch companyMatch = companyMatchRepository.findById(new ObjectId(matchRequestId))
                .stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getDescription()));
        if (companyMatch.getStatus() == CompanyMatchStatus.PENDING) {
            companyMatch.setStatus(CompanyMatchStatus.DECLINED);
            companyMatchRepository.save(companyMatch);
            return ResponseEntity.ok(new CompanyMatchResponse(companyMatch.getCompanyName(), companyMatch.getFactoryName(), companyMatch.getStatus()));
        }
        return ResponseEntity.badRequest().body(new CompanyMatchResponse(companyMatch.getCompanyName(), companyMatch.getFactoryName(), companyMatch.getStatus()));
    }
}
