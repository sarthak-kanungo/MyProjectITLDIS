package com.i4o.dms.kubota.masters.spares.dbentities.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "SP_MT_PURCHASE_ORDER_ORDER_TYPE")
public class SparesMtPurchaseOrderOrderType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,unique = true)
    private String orderType;

}
