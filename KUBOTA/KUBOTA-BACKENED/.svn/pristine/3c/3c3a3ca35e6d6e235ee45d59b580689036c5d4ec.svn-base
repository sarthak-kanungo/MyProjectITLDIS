package com.i4o.dms.kubota.warranty.deliverychallan.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.spares.partrequisition.domain.SparePartRequisitionItem;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.domain.WarrantyWcr;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Entity
@Getter
@Setter
@Table(name="wa_delivery_challan")
public class WarrantyDeliveryChallan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 21)
    private String dcNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    private Date dcDate;

    private String status;

    @Column(columnDefinition = "boolean default false")
    private Boolean draftFlag;
    @Column(updatable=false)
    private Long branchId;
    
    private String transporterName;

    private String  docketNumber;

    private Integer numberOfBox;

    private Date lastModifiedDate;

    private Long lastModifiedBy;

    @Column(updatable=false)
    private Long createdBy;
    @Column(updatable=false)
    private Date createdDate = new Date();
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dispatchDate;

    @ManyToMany()
    @JoinTable(name = "wa_dc_wcr_mapping",
            joinColumns = @JoinColumn(name = "delevery_challan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "wcr_id", referencedColumnName = "id"))
    private List<WarrantyWcr> warrantyWcr;

}
