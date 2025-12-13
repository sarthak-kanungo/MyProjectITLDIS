package com.i4o.dms.kubota.service.activityproposal.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "SV_ACTIVITY_PROPOSAL_SUB_ACTIVITY")
public class ServiceActivityProposalSubActivity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Sub Activity can't be blank")
    private String subActivity;

    private Double amount;

    private Double actualClaimAmount;

    private String subActivityImage;
    
    @Column(length = 100)
    private String vendorName;	//Suraj--11-01-2024
    
    @Column(length = 100)
    private String billNo;	//Suraj--11-01-2024
    
    private Date billDate;	//Suraj--11-01-2024

    @Transient
    private String billDateTran;	//Suraj--11-01-2024

    @Transient
    private MultipartFile image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_proposal_id")
    @JsonBackReference("b")
    private ServiceActivityProposal serviceActivityProposal;

//    @ManyToOne
//    @JoinColumn(name = "service_mt_sub_activity_id")
//    private ServiceMtSubActivityType serviceMtSubActivityType;


    @Override
    public String toString() {
        return "ServiceActivityProposalSubActivity{" +
                "id=" + id +
                ", subActivity='" + subActivity + '\'' +
                ", amount=" + amount +
                ", actualClaimAmount=" + actualClaimAmount +
                ", subActivityImage='" + subActivityImage + '\'' +
                ", image=" + image +
                ", serviceActivityProposal=" + serviceActivityProposal +
                '}';
    }
}
