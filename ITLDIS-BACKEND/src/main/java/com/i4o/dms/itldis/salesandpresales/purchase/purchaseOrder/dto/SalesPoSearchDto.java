package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesPoSearchDto {
	
	 public String poNumber;
     public String poType;
     public String depot;
     public String itemNo;
     public String fromDate;
     public String toDate;
     public String poStatus;
     public String product;
     public String series;
     public String model;
     public String subModel;
     public String variant;
     public Integer page;
     public Integer size;
     public Long hierId;
     public Long dealerId;

}
