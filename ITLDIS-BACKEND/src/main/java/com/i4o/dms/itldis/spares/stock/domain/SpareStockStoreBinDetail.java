package com.i4o.dms.itldis.spares.stock.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="SP_STOCK_STORE_BIN_DTL")
public class SpareStockStoreBinDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false)
	private Long branchId;
	
	@Column(updatable=false)
	private String itemNo;
	
	@Column(updatable=false)
	private Long storeId;
	
	@Column(updatable=false)
	private Long binLocationId;
	
	@Column(updatable=false)
	private String transactionType;
	
	@Column(updatable=false)
	private Date transactionDate = new Date();
	
	@Column(updatable=false)
	private Long refTransHdrId;
	
	private Integer outQty;
	
	private Integer inQty;
	
	private Integer usedQty;
	
	private Integer avlQty;
	
	private Double unitPrice;
	
	private Double spegst;
	
	private Double spmgst;
	
	private Double spmrp;
	
	@Column(updatable=false)
	private Date createddate = new Date();
	
	@Column(updatable=false)
	private Long createdby;
	
	private Date modifieddate;
	
	private Long modifiedby;
}
