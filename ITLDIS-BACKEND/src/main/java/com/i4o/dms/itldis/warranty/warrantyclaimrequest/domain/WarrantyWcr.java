package com.i4o.dms.itldis.warranty.warrantyclaimrequest.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import com.i4o.dms.itldis.warranty.deliverychallan.domain.WarrantyDeliveryChallan;
import com.i4o.dms.itldis.warranty.goodwill.domain.WarrantyGoodwill;
import com.i4o.dms.itldis.warranty.pcr.domain.WarrantyPcr;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="WA_WCR")
public class WarrantyWcr {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 21)
    private String wcrNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    private Date wcrDate;

    private String wcrStatus = "Submitted";

    private String wcrType;

    @Column(columnDefinition = "boolean default false")
    private Boolean draftFlag;

    @ManyToOne
    @JoinColumn(name = "warranty_pcr_id")
    private WarrantyPcr warrantyPcr;
    

    @ManyToOne
    @JoinColumn(name = "warranty_gwl_id")
    private WarrantyGoodwill warrantyGoodwill;
    

    private String kaiRemarks;

    private String inspectionRemarks;

    @Column(updatable=false)
    private Long createdBy;
    @Column(updatable=false)
    private Date createdOn;
    
    private Long modifiedBy;
    
    private Date modifiedOn;

    @Column(updatable=false)
    private Long branchId;

    @ManyToOne
    @JoinColumn(name = "inspection_by")
    private KubotaEmployeeMaster inspectionBy;

    private Date inspectionDate;

    @ManyToMany(mappedBy = "warrantyWcr")
    private List<WarrantyDeliveryChallan> warrantyDeliveryChallans = new ArrayList<>();
}
