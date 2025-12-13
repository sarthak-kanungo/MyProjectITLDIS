package com.i4o.dms.itldis.service.activityproposal.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "SV_ACTIVITY_PROPOSAL_HEADS")
public class ServiceActivityProposalHeads
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String head;

    private Double amount;

    private Double actualClaimAmount;

    private String headImage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_activity_proposal_id")
    @JsonBackReference("a")
    private ServiceActivityProposal serviceActivityProposal;
    
    @Column(length = 100)
    private String vendorName;	//Suraj--11-01-2024
    
    @Column(length = 100)
    private String billNo;	//Suraj--11-01-2024
    
    private Date billDate;	//Suraj--11-01-2024

    @Transient
    private String billDateTran;	//Suraj--11-01-2024
    
    @Transient
    private MultipartFile image;

    @Override
    public String toString() {
        return "ServiceActivityProposalHeads{" +
                "id=" + id +
                ", head='" + head + '\'' +
                ", amount=" + amount +
                ", actualClaimAmount=" + actualClaimAmount +
                ", headImage='" + headImage + '\'' +
                ", image=" + image +
                ", serviceActivityProposal=" + serviceActivityProposal +
                '}';
    }
}
