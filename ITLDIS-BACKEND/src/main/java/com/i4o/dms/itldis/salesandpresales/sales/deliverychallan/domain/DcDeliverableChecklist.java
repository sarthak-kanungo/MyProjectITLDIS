package com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i4o.dms.itldis.masters.dbentities.salespresales.sales.deliverychallan.DcDeliverableChecklistMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="SA_DELIVERY_CHECKLIST")
public class DcDeliverableChecklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "delivery_challan_id")
    @JsonBackReference
    private DeliveryChallan deliveryChallan;

    @ManyToOne
    @JoinColumn(name = "dc_deliverable_checklist_master_id")
    private DcDeliverableChecklistMaster dcDeliverableChecklistMaster;

    private Boolean isDelivered=true;

}
