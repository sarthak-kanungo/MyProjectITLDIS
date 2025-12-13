package com.i4o.dms.kubota.spares.purchase.grn.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SpareLocalPartMaster;
import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.BinLocationMaster;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.domain.SparePurchaseOrder;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="SP_PART_GRN_ITEM")
public class SparePartGrnItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accPacOrderNumber;

   /* @ManyToOne
    @JoinColumn(name = "spare_local_part_id", referencedColumnName = "dmsItemNumber")
    private SpareLocalPartMaster spareLocalPartMaster;*/

    @ManyToOne
    @JoinColumn(name = "item_no", referencedColumnName = "itemNo")
    private SparePartMaster sparePartMaster;

    private String hsnCode;

    private Double unitPrice;

    private Double spmgst;

    private Double spegst;

    private Double spmrp;

    @Min(1)
    private Integer invoiceQty;

    private Double assessableValue;

    private Double discount;

    ///tax
    private Double cgstPercent;

    private Double cgstAmount;

    private Double sgstPercent;

    private Double sgstAmount;

    private Double igstPercent;

    private Double igstAmount;
    
    private Double tcsAmount;
    
    private Double tcsPercent;
    
    //total received
    //@Min(1)
    private Integer totalReceivedQty;

   // @DecimalMin(value = "1.0")
    private Double totalReceivedValue;

    //received short
    private Integer receivedShortQty;

    private Double receivedShortValue;

    //received damaged
    private Integer receivedDamageQty;

    private Double receivedDamageValue;

    //nett received
   // @Min(1)
    private Integer netReceivedQty;

  //  @DecimalMin(value = "1.0")
    private Double netReceivedValue;

    @ManyToOne
    @JoinColumn(name = "spare_grn_id")
    @JsonBackReference
    private SparePartGrn sparePartGrn;

    @ManyToOne
    @JoinColumn(name = "bin_location_id")
    private BinLocationMaster binLocation;
    
    @ManyToOne
    @JoinColumn(name = "spare_purchase_order_id")
    private SparePurchaseOrder sparePurchaseOrder;

}

//SELECT iItem.item_no, SUM(ISNULL(gItem.net_received_qty,0)) netRecvQty, SUM(ISNULL(iItem.invoice_qty,0)) invQty,
//CASE WHEN SUM(iItem.invoice_qty) >= SUM(gItem.net_received_qty) then 'GRN Done'
//WHEN SUM(ISNULL(gItem.net_received_qty,0)) = 0 then 'GRN Pending'
//ELSE 'Partial GRN' END
//FROM accpac_spare_part_invoice_item iItem left join spare_part_grn_item gItem
//    ON iItem.item_no = gItem.item_no
//GROUP BY iItem.item_no