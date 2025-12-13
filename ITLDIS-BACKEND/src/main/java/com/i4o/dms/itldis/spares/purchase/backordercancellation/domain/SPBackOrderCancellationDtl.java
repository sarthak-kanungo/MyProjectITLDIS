package com.i4o.dms.itldis.spares.purchase.backordercancellation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.domain.SparePurchaseOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
@Entity
@Table(name = "SP_BACK_ORDER_CANCELLATION_DTL")
public class SPBackOrderCancellationDtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "boc_id", referencedColumnName = "id")
	private SPBackOrderCancellation orderCancellation;

	@ManyToOne
    @JoinColumn(name = "spare_part_master_id", referencedColumnName = "id")
    private SparePartMaster sparePartMaster;
	
	@ManyToOne
	@JoinColumn(name = "dms_po_id", referencedColumnName = "id")
	private SparePurchaseOrder purchaseOrder;
	
	private String accpacPoNo;

	private String status;
	
	private Integer pendingOrderQty;
	
	private Integer cancelQty;
	
	private Integer kaiAcceptedQty;
}
