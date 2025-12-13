package com.i4o.dms.kubota.masters.kdm.csb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.masters.products.domain.MachineMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"}, allowSetters = true)
public class Csb {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String csbNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date csbDate=new Date();

   // private Boolean draftFlag=false;

    @ManyToOne
    @JoinColumn(name = "machine_master_id")
    private MachineMaster machineMaster;

    @ManyToOne
    @JoinColumn(name="dealer_id")
    private DealerMaster dealerMaster;

    @ManyToOne
    @JoinColumn(name="created_by")
    private DealerEmployeeMaster dealerEmployeeMaster;

}
