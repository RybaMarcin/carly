package org.carly.core.companymanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "company-match")
public class CompanyMatch {

    @Id
    private ObjectId id;
    private String companyName;
    private ObjectId companyId;
    private String factoryName;
    private ObjectId factoryId;
    private MatchStatus status;
    private LocalDateTime createDate;
    private String createBy;
    private LocalDateTime modifiedDate;
    private String modifiedBy;

    public CompanyMatch(String companyName, ObjectId companyId, String factoryName, ObjectId factoryId, MatchStatus status, LocalDateTime createDate, String createBy) {
        this.companyName = companyName;
        this.companyId = companyId;
        this.factoryName = factoryName;
        this.factoryId = factoryId;
        this.status = status;
        this.createDate = createDate;
        this.createBy = createBy;
    }
}
