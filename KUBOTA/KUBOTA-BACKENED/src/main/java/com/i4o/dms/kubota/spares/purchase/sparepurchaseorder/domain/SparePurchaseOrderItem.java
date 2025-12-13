package com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SpareLocalPartMaster;
import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "SP_PURCHASE_ORDER_ITEM")
public class SparePurchaseOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 1,message = "Minimum quantity should be 1")
    private Integer quantity;

    @Transient
    private Integer moq;
    
    @NotNull(message = "Unit Price is mandatory")
    private Double unitPrice = 0.0;

    @NotNull(message = "Base Amount is mandatory")
    private Double baseAmount;

    @NotNull(message = "Gst Percent is mandatory")
    private Double gstPercent;

    @NotNull(message = "Gst Value is mandatory")
    private Double gstValue = 0.0;

    @NotNull(message = "Total amount is mandatory")
    private Double totalAmount;

    private Double tcsPercent;
    
    private Double tcsValue;
    
    private Long backOrderAtKai;

    private Long transitAtKai;

    private String status;

    @ManyToOne
    @JoinColumn(name = "spare_purchase_order_id")
    @JsonBackReference
    @NotNull(message = "spare purchase order is mandatory")
    private SparePurchaseOrder sparePurchaseOrder;

    @ManyToOne
    @JoinColumn(name = "spare_part_id", referencedColumnName = "itemNo")
    private SparePartMaster sparePartMaster;

    /*@ManyToOne
    @JoinColumn(name = "spare_local_part_id", referencedColumnName = "dmsItemNumber")
    private SpareLocalPartMaster spareLocalPartMaster;*/

    private Boolean deleteFlag;

    private Boolean grnDoneFlag = false;

}
