package com.i4o.dms.kubota.masters.products.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"},
        allowSetters = true)
@Table
public class MachineMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String itemNo;

    @Column(length = 500)
    private String itemDescription;

    @Column(length = 50)
    private String productGroup;

    @Column(length = 50)
    private String product;

    @Column(length = 50)
    private String series;

    @Column(length = 50)
    private String model;

    @Column(length = 50)
    private String subModel;

    @Column(length = 50)
    private String variant;

    @Column(length = 1)
    private Character purchase;

    @Column(length = 1)
    private Character saleableDealer;

    @Column(length = 1)
    private Character isSfg;

    @Column(length = 1)
    private Character isFg;

    @Column(length = 1)
    private Character isAssemblyReq;

    @Column(length = 50)
    private String sfgPartNo;

    @Column(length = 50)
    private String isPalletReq;

    @Column(length = 50)
    private String palletItemNo;

    @Column(length = 50)
    private Integer noOfItemInPallet;

    @Column(length = 50)
    private String vendorCode;

    @Column(length = 50)
    private String hsnCode;

    @Column(length = 25)
    private Float bcdPercent;

    @Column(length = 25)
    private Float swsPercent;

    @Column(length = 25,name = "igst_percent")
   // @JsonProperty("iGst")
    private Float igstPercent;
    @Column(length = 25,name = "cgst_percent")
 //   @JsonProperty("cGst")
    private Float cgstPercent;
    @Column(length = 25,name = "sgst_percent")
 //   @JsonProperty("sGst")
    private Float sgstPercent;
    @Column(length = 50)
    private String portOfLoading;
    private String uom;
    private Integer moq;
    private Long etdInDays;
    private Long etaInDays;
    @Column(length = 1)
    private Character chassisNoReq;
    @Column(length = 1)
    private Character engineNoReq;
    @Column(length = 50)
    private String engineModel;
    @Column(length = 50)
    private String batteryNo;
    @Column(length = 50)
    private String fipNo;
    @Column(length = 50)
    private String crawlerNoLeft;
    @Column(length = 50)
    private String crawlerNoRight;
    @Column(length = 50)
    private String hydraulicPumpNo;
    @Column(length = 50)
    private String starterNO;
    @Column(length = 50)
    private String alternatorNo;
    @Column(length = 50)
    private String frontTyreLeft;
    @Column(length = 50)
    private String frontTyreRight;
    @Column(length = 50)
    private String rearTyreLeft;
    @Column(length = 50)
    private String rearTyreRight;
    @Column(length = 50)
    private String frontRimLeft;
    @Column(length = 50)
    private String frontRimRight;
    @Column(length = 50)
    private String frontTyreMake;
    private String rearRimLeft;
    private String rearRimRight;
    @Column(length = 50)
    private String rearTyreMake;
    @Column(length = 50)
    private String radiatorNo;
    @Column(length = 50)
    private String transmissionNo;
    @Column(length = 50)
    private String hstNo;
    private String swingMotorNo;
    @Column(length = 50)
    private String leadTime;
    @Column(length = 24)
    private String color;
    private Double spaceOccupied;
    @Column(length = 50)
    private Character rtoRegistration;
    @ManyToOne
    private KubotaEmployeeMaster updatedBy;
    @ManyToOne
    private KubotaEmployeeMaster lastModifiedBy;
    private Date lastModifiedDate;
    private Date lastUpdateDate;
    @Column(length = 15)
    private Double unitCost;

    @Column(length = 1, columnDefinition = "varchar(1) default 'Y'")
    private String activeStatus;

}
