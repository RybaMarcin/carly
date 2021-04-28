package org.carly.api.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.carly.core.companymanagement.model.MatchStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CompanyMatchResponse {
    private String matchId;
    private String companyName;
    private String factoryId;
    private String factoryName;
    private MatchStatus status;
    private LocalDateTime createDate;
}
