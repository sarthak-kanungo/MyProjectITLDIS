package com.i4o.dms.kubota.feedback.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class FeedbackForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String moduleName;
    private String transactions;
    private String operation;
    private String fieldName;
    private String comment;
    private String mobileNumber;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdDate=new Date();
    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;
}
