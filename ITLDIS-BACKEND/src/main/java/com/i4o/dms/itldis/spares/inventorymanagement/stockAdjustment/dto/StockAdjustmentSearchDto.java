package com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.dto;

public class StockAdjustmentSearchDto {

	private String adjustmentFromDate;
	private String adjustmentToDate;
	private String stockAdjustmentNo;
	private Integer size;
	private Integer page;
	
	//-------------Added 23/09/2022
	private String adjustmentStatus;
	private Long hierId;
	private Long dealerId;
	//-------------
	
	public String getAdjustmentFromDate() {
		return adjustmentFromDate;
	}
	public void setAdjustmentFromDate(String adjustmentFromDate) {
		this.adjustmentFromDate = adjustmentFromDate;
	}
	public String getAdjustmentToDate() {
		return adjustmentToDate;
	}
	public void setAdjustmentToDate(String adjustmentToDate) {
		this.adjustmentToDate = adjustmentToDate;
	}
	public String getStockAdjustmentNo() {
		return stockAdjustmentNo;
	}
	public void setStockAdjustmentNo(String stockAdjustmentNo) {
		this.stockAdjustmentNo = stockAdjustmentNo;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getAdjustmentStatus() {
		return adjustmentStatus;
	}
	public void setAdjustmentStatus(String adjustmentStatus) {
		this.adjustmentStatus = adjustmentStatus;
	}
	public Long getHierId() {
		return hierId;
	}
	public void setHierId(Long hierId) {
		this.hierId = hierId;
	}
	public Long getDealerId() {
		return dealerId;
	}
	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}
	
}
