package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.domain.MarketingActivityClaim;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "SM_activity_proposal_head")
public class ActivityHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "head name can`t be blank")
    @NotNull
    @Column(length = 255)
    private String headName;

    @NotNull
    @Column(length = 15)
    private Double amount=0.0;

//    private Double gstPercent=0.0;
//
//
//    private Double gstAmount=0.0;

    
//    private Double approvedAmount=0.0;
    
//    private Double actualClaimAmount=0.0;

    private Boolean isDelete=false;
    
    private String remark;

//    private String headImage;
//
//    @Transient
//    private MultipartFile image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "marketing_activity_proposal_id")
    @JsonBackReference
    private MarketingActivityProposal marketingActivityProposal;

}
