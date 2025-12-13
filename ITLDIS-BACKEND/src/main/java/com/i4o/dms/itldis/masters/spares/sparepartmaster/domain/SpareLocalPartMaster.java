package com.i4o.dms.itldis.masters.spares.sparepartmaster.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "SP_LOCAL_PART_MASTER")
public class SpareLocalPartMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String dmsItemNumber;

    @Column(length = 30)
    private String vendorItemNumber;

    @Column(length = 250)
    private String itemDescription;

    @Column(length = 30)
    private String category;

    @Column(length = 30)
    private String dealerVendorCode;

    private Integer hsCode;

    private Double igstPercent;

    private Double cgstPercent;

    private Double sgstPercent;

    private Double purchasePrice;

    private Character activeStatus;

    private Integer moq;

}
