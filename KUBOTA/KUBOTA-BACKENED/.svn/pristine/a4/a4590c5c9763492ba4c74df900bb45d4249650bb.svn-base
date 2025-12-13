package com.i4o.dms.kubota.masters.spares.sparepartmaster.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@ToString
@Table(name="SP_PART_MASTER")
public class SparePartMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String itemNo;

    @Column(length = 100)
    private String itemDescription;

    @Column(length = 50)
    private String alternatePartNo;

    @Column(length = 50)
    private String supplier;

    @Column(length = 15)
    private Character openForSale;

    private String canSaleOrCanNotProcure;

    @Column(length = 15)
    private Character activeStatus;

    @Column(length = 50)
    private String uom;

    @Column(length = 15)
    private Double spmgst;

    @Column(length = 15)
    private Double spegst;

    private Double spmrp;

    @Column(length = 15)
    private Double height;

    @Column(length = 15)
    private Double length;

    @Column(length = 15)
    private Double width;

    @Column(length = 15)
    private Integer moq;

    /*private String hsCode8Digit;

    private String hsCode4Digit;*/

    private Double bcdPercent;

    private Double swsPercent;

    private Double igstPercent;

    private Double cgstPercent;

    private Double sgstPercent;

}
