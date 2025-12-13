package com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i4o.dms.kubota.masters.products.domain.MachineMaster;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="SA_DELIVERY_ITEM_DETAIL")
public class DcAccessoriesDetail {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "delivery_challan_id")
    @JsonBackReference
    private DeliveryChallan deliveryChallan;

    @ManyToOne
    @JoinColumn(name = "machine_master_id")
    private MachineMaster machineMaster;

    private Long quantity;

}
