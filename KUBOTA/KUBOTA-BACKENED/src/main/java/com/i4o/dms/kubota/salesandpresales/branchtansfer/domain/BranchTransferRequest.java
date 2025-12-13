package com.i4o.dms.kubota.salesandpresales.branchtansfer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@Entity
@JsonIgnoreProperties(value = {"createdBy","createdDate","lastModifiedBy","lastModifiedDate"},allowSetters = true)
public class BranchTransferRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestFromBranch;
    private String requestToBranch;
    private String requestNo;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date requestDate;
    private String requestBy;
    private String itemNo;
    private String itemDescription;
    private String requestQty;
}

//userMaster Mapping And DealerMaster Mapping