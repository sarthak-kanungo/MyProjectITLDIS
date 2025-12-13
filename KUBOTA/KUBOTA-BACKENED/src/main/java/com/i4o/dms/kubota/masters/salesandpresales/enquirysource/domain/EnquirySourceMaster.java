package com.i4o.dms.kubota.masters.salesandpresales.enquirysource.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i4o.dms.kubota.masters.dbentities.activity.source.domain.SourcePurpose;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy","lastModifiedDate"}, allowSetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "SA_MST_ENQUIRY_SOURCE")
public class EnquirySourceMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String sourceCode;

    @Column(length = 60, nullable = false)
    @NotBlank(message = "source name should not be blank")
    private String sourceName;//activityType

    @ManyToOne
    @JoinColumn(name = "source_purpose_id")
    private SourcePurpose sourcePurpose;

    @Column(length = 6)
    @NotBlank(message = "activeStatus should not be blank")
    private String activeStatus = "Y";

    @Column(length = 6)
    private Integer seqNo = 0;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date createdDate = new Date();

    @ManyToOne
    @JoinColumn(name = "created_by")
    private KubotaEmployeeMaster createdBy;

    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    private KubotaEmployeeMaster lastModifiedBy;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date lastModifiedDate = new Date();

}
