package com.i4o.dms.kubota.masters.dbentities.salespresales.sales.deliverychallan;


import com.i4o.dms.kubota.masters.products.domain.ModelMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="SA_DELIVERY_MST_CHECKLIST")
public class DcDeliverableChecklistMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deliverableChecklist;

    private Character activeStatus = 'Y';

    @ManyToOne
    @JoinColumn(name = "model_master_id")
    private ModelMaster modelMaster;


}
