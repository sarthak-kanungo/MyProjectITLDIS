package com.i4o.dms.kubota.salesandpresales.branchtansfer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
@JsonIgnoreProperties(value = {"createdBy","createdDate","lastModifiedBy","lastModifiedDate"},allowSetters = true)
public class BranchTransferIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestNo;
    private String branchTransferIssueNo;
    private String issuingBranch;
    private String issuedBy;
    private String issuedToBranch;
    private String itemNo;
    private String itemDescription;
    private String requestQty;
    private String issueQty;
    private String pendingQty;
    private String mrp;
    private String value;
    private String totalValue;
}
