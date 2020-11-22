package org.carly.core.companymanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collation = "companyMatcher")
public class CompanyMatch {

    private String companyName;
    private ObjectId companyId;
    private String factoryName;
    private ObjectId factoryId;
    private CompanyMatchStatus status;
    private LocalDateTime createDate;
    private String createBy;
    private LocalDateTime modifiedDate;
    private String modifiedBy;

    public CompanyMatch(String companyName, ObjectId companyId, String factoryName, ObjectId factoryId, CompanyMatchStatus status, LocalDateTime createDate, String createBy) {
        this.companyName = companyName;
        this.companyId = companyId;
        this.factoryName = factoryName;
        this.factoryId = factoryId;
        this.status = status;
        this.createDate = createDate;
        this.createBy = createBy;
    }
}
