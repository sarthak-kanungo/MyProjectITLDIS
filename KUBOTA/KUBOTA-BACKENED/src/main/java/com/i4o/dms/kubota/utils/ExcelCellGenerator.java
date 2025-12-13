package com.i4o.dms.kubota.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.i4o.dms.kubota.crm.crmmodule.report.dto.CustomerSatisfactionScoreDto;
import com.i4o.dms.kubota.crm.crmmodule.report.dto.MonthlyFailureCodeSummaryReportDto;
import com.i4o.dms.kubota.crm.crmmodule.report.dto.TollFreeReportDto;
import com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.response.QAReportResponse;
import com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.response.SurveySummaryReportDissatisfiedResponse;
import com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.response.SurveySummaryReportResponse;
import com.i4o.dms.kubota.crm.crmmodule.surveysummaryreport.response.SurveySummaryReportWithQuationariesResponse;
import com.i4o.dms.kubota.crm.crmmodule.tollFreeCall.dto.TollFreeCallSearchResponseDto;
import com.i4o.dms.kubota.salesandpresales.enquiry.dto.EnquirySearchResponseDto;
import com.i4o.dms.kubota.salesandpresales.marketIntelligence.dto.MarketIntelligenceSearchResponse;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.dto.ResponsePoDto;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.dto.SalesPoExcelResponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.ActivityClaimStatusReportResponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.ActivityEnquiryReportResponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.ActivityPropoalStatusReportResponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.ActivityReportStatusReportReponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.EnquiryReportSearchResponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.MachineMasterReportResponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.MarketingClaimReportResponse;
import com.i4o.dms.kubota.salesandpresales.reports.dto.TransactionResponse;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.SearchDeliveryChallanResponse;
import com.i4o.dms.kubota.service.claim.dto.ServiceClaimDetailExcelReport;
import com.i4o.dms.kubota.service.claim.dto.ServiceClaimSummeryExcelReport;
import com.i4o.dms.kubota.service.jobcard.dto.JobcardDetailedExcelResponseDto;
import com.i4o.dms.kubota.service.jobcard.dto.JobcardSearchResponseDto;
import com.i4o.dms.kubota.service.report.model.CustomerMachineMasterReportResponse;
import com.i4o.dms.kubota.service.report.model.FillRatioItemReportResponseDto;
import com.i4o.dms.kubota.service.report.model.FillRatioReportResponseDto;
import com.i4o.dms.kubota.service.report.model.InstallationMonitoringBoardResponseDto;
import com.i4o.dms.kubota.service.report.model.ServiceMonitoringBoardResponseDto;
import com.i4o.dms.kubota.spares.inventorymanagement.btbt.dto.SpareBinTransferSearchResponseDto;
import com.i4o.dms.kubota.spares.inventorymanagement.currentstock.dto.CurrentStockResponse;
import com.i4o.dms.kubota.spares.inventorymanagement.machinestock.domain.MachineStockSearchResponse;
import com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.dto.AuctionPartsListResDto;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.dto.StockAdjustmentSearchResult;
import com.i4o.dms.kubota.spares.invoice.dto.ResponseSearchSparesInvoice;
import com.i4o.dms.kubota.spares.purchase.grn.dto.GrnSearchResponseDto;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.ResponseSearchPurchaseOrder;
import com.i4o.dms.kubota.spares.purchase.transitreport.dto.SearchResponsedto;
import com.i4o.dms.kubota.spares.reports.dto.BackOrderPartsReportsDto;
import com.i4o.dms.kubota.spares.reports.dto.ClosingStockReportDto;
import com.i4o.dms.kubota.spares.reports.dto.InventoryMovementDto;
import com.i4o.dms.kubota.spares.reports.dto.ItemMovementDto;
import com.i4o.dms.kubota.spares.reports.dto.NonMovingPartsStockReportDto;
import com.i4o.dms.kubota.spares.salesorder.dto.SpareSaleOrderResponseDto;
import com.i4o.dms.kubota.training.trainingProgramReport.dto.TrainingProgramReportSearchResponse;
import com.i4o.dms.kubota.warranty.logsheet.dto.LogsheetDetailsExcelResponse;
import com.i4o.dms.kubota.warranty.logsheet.dto.LogsheetResponseDto;
import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrDetailsExcelResponse;
import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrSummaryExcelResponse;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.WarrantyWcrExcelSummaryResponse;

public class ExcelCellGenerator {
	public static ByteArrayInputStream generateSalesPreSalesTransactionReport(
			List<TransactionResponse> salesPreSalesTransactionCounts) throws IOException {
		String[] columns = { "Dealer Code", "Dealer Name", "Purchase Order", "Channel Finance Indent",
				"Goods Receipt Note", "Machine Transfer Request", "Enquiry", "SpareQuotation", "Payment Receipt",
				"Allotment / De-Allotment", "Delivery Challan", "Invoice", "Marketing Activity Proposal",
				"Marketing Activity Report", "Marketing Activity Claim", "MRC Count", "PDI Count", "PDC Count",
				"PSC Count", "Spares PO", "Spares GRN", "Spares Quotation", "Spares Customer Order", "Spares Pick List",
				"Spares Invoice", "Job Card", "PCR", "Warranty Claim", "Spares Issue", "Installation" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Transaction Report");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (TransactionResponse salesPreSalesTransactionCount : salesPreSalesTransactionCounts) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(salesPreSalesTransactionCount.getDealerCode());
				row.createCell(1).setCellValue(salesPreSalesTransactionCount.getDealerName());
				row.createCell(2).setCellValue(salesPreSalesTransactionCount.getPoCount());
				row.createCell(3).setCellValue(salesPreSalesTransactionCount.getChannelFinanceIntentCount());
				row.createCell(4).setCellValue(salesPreSalesTransactionCount.getGrnCount());
				row.createCell(5).setCellValue(salesPreSalesTransactionCount.getMachineTransferCount());
				row.createCell(6).setCellValue(salesPreSalesTransactionCount.getEnquiryCount());
				row.createCell(7).setCellValue(salesPreSalesTransactionCount.getQuotationCount());
				row.createCell(8).setCellValue(salesPreSalesTransactionCount.getPaymentReceiptCount());
				row.createCell(9).setCellValue(salesPreSalesTransactionCount.getAllotmentCount());
				row.createCell(10).setCellValue(salesPreSalesTransactionCount.getDeliveryChallanCount());
				row.createCell(11).setCellValue(salesPreSalesTransactionCount.getSalesInvoiceCount());
				row.createCell(12).setCellValue(salesPreSalesTransactionCount.getProposalCount());
				row.createCell(13).setCellValue(salesPreSalesTransactionCount.getReportCount());
				row.createCell(14).setCellValue(salesPreSalesTransactionCount.getClaimCount());

				row.createCell(15).setCellValue(salesPreSalesTransactionCount.getMrcCount());
				row.createCell(16).setCellValue(salesPreSalesTransactionCount.getPdiCount());
				row.createCell(17).setCellValue(salesPreSalesTransactionCount.getPdcCount());
				row.createCell(18).setCellValue(salesPreSalesTransactionCount.getPscCount());

				row.createCell(19).setCellValue(salesPreSalesTransactionCount.getSparesPO());
				row.createCell(20).setCellValue(salesPreSalesTransactionCount.getSparesGRN());
				row.createCell(21).setCellValue(salesPreSalesTransactionCount.getSparesQuotation());
				row.createCell(22).setCellValue(salesPreSalesTransactionCount.getSparesCustomerOrder());
				row.createCell(23).setCellValue(salesPreSalesTransactionCount.getSparesPickList());
				row.createCell(24).setCellValue(salesPreSalesTransactionCount.getSparesInvoice());
				row.createCell(25).setCellValue(salesPreSalesTransactionCount.getJobCard());
				row.createCell(26).setCellValue(salesPreSalesTransactionCount.getPCR());
				row.createCell(27).setCellValue(salesPreSalesTransactionCount.getWarrantyClaim());
				row.createCell(28).setCellValue(salesPreSalesTransactionCount.getSparesIssue());
				row.createCell(29).setCellValue(salesPreSalesTransactionCount.getInstallation());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}

	}

	public static ByteArrayInputStream spareOrderExcelReport(List<ResponseSearchPurchaseOrder> list)
			throws IOException {
		String[] Columns = { "Purchase Order Number", "Order Type", "Purchase Order Date", "Dealer Code", "Dealer Name",
				"Supplier Type", "Transfer Mode", "Transporter", "Freight Borne By", "Total Base Amount",
				"Total Po Amount", "Purchase Order Status" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("PurchaseOrder");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (ResponseSearchPurchaseOrder responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getPurchaseOrderNumber());
				row.createCell(1).setCellValue(responseSearchDtos.getOrderType());
				row.createCell(2).setCellValue(responseSearchDtos.getPurchaseOrderDate());
				row.createCell(3).setCellValue(responseSearchDtos.getDealerCode());
				row.createCell(4).setCellValue(responseSearchDtos.getDealerName());
				row.createCell(5).setCellValue(responseSearchDtos.getSupplierType());
				row.createCell(6).setCellValue(responseSearchDtos.getTransferMode());
				row.createCell(7).setCellValue(responseSearchDtos.getTransporter());
				row.createCell(8).setCellValue(responseSearchDtos.getFreightBorneBy());
				row.createCell(9).setCellValue(responseSearchDtos.getTotalBaseAmount());
				row.createCell(10).setCellValue(responseSearchDtos.getTotalPoAmount());
				row.createCell(11).setCellValue(responseSearchDtos.getPurchaseOrderStatus());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream spareOrderDetailReport(List<ResponseSearchPurchaseOrder> list)
			throws IOException {
		String[] Columns = { "PO Date", "Purchase Order Number", "Order Type", "Purchase order Status", "Dealer Code",
				"Dealer Name", "Supplier Type", "Transport Mode", "Transporter", "Part Number", "Part Desc", "Qty",
				"NDP Price", "GST", "Total" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("PurchaseOrder");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (ResponseSearchPurchaseOrder responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getPurchaseOrderDate());
				row.createCell(1).setCellValue(responseSearchDtos.getPurchaseOrderNumber());
				row.createCell(2).setCellValue(responseSearchDtos.getOrderType());

				row.createCell(3).setCellValue(responseSearchDtos.getPurchaseOrderStatus());
				row.createCell(4).setCellValue(responseSearchDtos.getDealerCode());
				row.createCell(5).setCellValue(responseSearchDtos.getDealerName());
				row.createCell(6).setCellValue(responseSearchDtos.getSupplierType());
				row.createCell(7).setCellValue(responseSearchDtos.getTransferMode());
				row.createCell(8).setCellValue(responseSearchDtos.getTransporter());

				row.createCell(9).setCellValue(responseSearchDtos.getItemNo());
				row.createCell(10).setCellValue(responseSearchDtos.getItemDesc());
				row.createCell(11).setCellValue(responseSearchDtos.getQuantity());
				row.createCell(12).setCellValue(responseSearchDtos.getNdp().doubleValue());
				row.createCell(13).setCellValue(responseSearchDtos.getGst().doubleValue());
				row.createCell(14).setCellValue(responseSearchDtos.getTotalAmount().doubleValue());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream spareGrnDetailReport(List<GrnSearchResponseDto> list) throws IOException {
		String[] Columns = { "GRN Date", "GRN No", "GRN Type", "GRN Status", "Invoice No", "Invoice Date",
				"Supplier Name", "Supplier Type", "Supplier GST No", "Mode of Transport", "Supplier Address1",
				"Supplier Address2", "Supplier Address3", "Supplier State", "Transporter", "Part Number",
				"Part Description", "Qty", "Grn Done By", "Branch Code", "Stores ( Spares Counter/Warehouse /FOC)",
				"Basic Amount", "Gst Amount", "Total Amount", "MRP Price (Per unit)" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("PartsGRN");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (GrnSearchResponseDto responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getGrnDate());
				row.createCell(1).setCellValue(responseSearchDtos.getGrnNumber());

				row.createCell(2).setCellValue(responseSearchDtos.getGrnType());
				row.createCell(3).setCellValue(responseSearchDtos.getGrnStatus());

				row.createCell(4).setCellValue(responseSearchDtos.getInvoiceNumber());
				row.createCell(5).setCellValue(responseSearchDtos.getInvoiceDate());

				row.createCell(6).setCellValue(responseSearchDtos.getSupplierName());
				row.createCell(7).setCellValue(responseSearchDtos.getSupplierType());
				row.createCell(8).setCellValue(responseSearchDtos.getSupplierGstNo());
				row.createCell(9).setCellValue(responseSearchDtos.getModeOfTransport());

				row.createCell(10).setCellValue(responseSearchDtos.getSupplierAddress1());
				row.createCell(11).setCellValue(responseSearchDtos.getSupplierAddress2());
				row.createCell(12).setCellValue(responseSearchDtos.getSupplierAddress3());
				row.createCell(13).setCellValue(responseSearchDtos.getSupplierState());
				row.createCell(14).setCellValue(responseSearchDtos.getTransporter());

				row.createCell(15).setCellValue(responseSearchDtos.getItemNo());
				row.createCell(16).setCellValue(responseSearchDtos.getItemDesc());
				row.createCell(17).setCellValue(responseSearchDtos.getQuantity());

				row.createCell(18).setCellValue(responseSearchDtos.getGrnDoneBy());
				row.createCell(19).setCellValue(responseSearchDtos.getBranchCode());
				row.createCell(20).setCellValue(responseSearchDtos.getStores());

				row.createCell(21).setCellValue(responseSearchDtos.getBasic().doubleValue());
				row.createCell(22).setCellValue(responseSearchDtos.getGst().doubleValue());
				row.createCell(23).setCellValue(responseSearchDtos.getTotalAmount().doubleValue());
				row.createCell(24).setCellValue(responseSearchDtos.getNdp().doubleValue());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream sparePartSalesReport(List<ResponseSearchSparesInvoice> list) throws IOException {
		String[] Columns = { "Invoice Date", "Invoice Number", "Customer Name", "Customer Code", "Customer Type",
				"Customer Contact No.", "State", "District", "Sales Type", "Item No", "Item Description", "Unit Price",
				"MRP Price", "Qty", "Amount", "Discount %", "Discount amount", "Net Amount", "CGST %", "CGST amount",
				"SGST %", "SGST Amount", "IGST %", "IGST Amount", "Total", "Supplied part on NDP/MRP (for Co dealer)",
				"Machine Serial Number", "Engine Serial No" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("PartsGRN");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (ResponseSearchSparesInvoice responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getInvoiceDate());
				row.createCell(1).setCellValue(responseSearchDtos.getSalesInvoiceNumber());
				row.createCell(2).setCellValue(responseSearchDtos.getCustomerName());
				row.createCell(3).setCellValue(responseSearchDtos.getCustomerCode());
				row.createCell(4).setCellValue(responseSearchDtos.getCustomerType());
				row.createCell(5).setCellValue(responseSearchDtos.getContactNumber());
				row.createCell(6).setCellValue(responseSearchDtos.getState());
				row.createCell(7).setCellValue(responseSearchDtos.getDistrict());
				row.createCell(8).setCellValue(responseSearchDtos.getSalesType());
				row.createCell(9).setCellValue(responseSearchDtos.getItemNo());
				row.createCell(10).setCellValue(responseSearchDtos.getItemDesc());

				row.createCell(11).setCellValue(responseSearchDtos.getUnitPrice().doubleValue());
				row.createCell(12).setCellValue(responseSearchDtos.getMrp().doubleValue());
				row.createCell(13).setCellValue(responseSearchDtos.getQuantity());
				row.createCell(14).setCellValue(responseSearchDtos.getAmount().doubleValue());
				
				//row.createCell(15).setCellValue(responseSearchDtos.getDiscountPerc().doubleValue());
				row.createCell(15).setCellValue(responseSearchDtos.getDiscountPerc() == null ? 0.00 : responseSearchDtos.getDiscountPerc().doubleValue());	//Suraj--08/12/2022
				
				row.createCell(16).setCellValue(responseSearchDtos.getDiscountAmount().doubleValue());
				row.createCell(17).setCellValue(responseSearchDtos.getNetAmount().doubleValue());
				row.createCell(18).setCellValue(responseSearchDtos.getCgstPerc() == null ? 0.00 : responseSearchDtos.getCgstPerc().doubleValue());	//Suraj--08/12/2022
				row.createCell(19).setCellValue(responseSearchDtos.getCgstAmount().doubleValue());
				row.createCell(20).setCellValue(responseSearchDtos.getSgstPerc() == null ? 0.00 : responseSearchDtos.getSgstPerc().doubleValue());	//Suraj--08/12/2022
				row.createCell(21).setCellValue(responseSearchDtos.getSgstAmount().doubleValue());
				
				//row.createCell(22).setCellValue(responseSearchDtos.getIgstPerc().doubleValue());
				row.createCell(22).setCellValue(responseSearchDtos.getIgstPerc() == null ? 0.00 : responseSearchDtos.getIgstPerc().doubleValue());	//Suraj--08/12/2022
				
				row.createCell(23).setCellValue(responseSearchDtos.getIgstAmount().doubleValue());
				row.createCell(24).setCellValue(responseSearchDtos.getTotalAmount().doubleValue());
				row.createCell(25).setCellValue(responseSearchDtos.getSuppliedPart());
				row.createCell(26).setCellValue(responseSearchDtos.getMachineSrNo());
				row.createCell(27).setCellValue(responseSearchDtos.getEngineNo());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream closingStockReport(List<ClosingStockReportDto> list) throws IOException {
		String[] Columns = { "Dealer Name", "Date", "Item Number", "Item Description", "Avaialble Stock /Qty", "Store",
				"Bin Location", "NDP Price", "MRP Price", "Total" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("ClosingStockReport");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (ClosingStockReportDto responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getDealerName());
				row.createCell(1).setCellValue(responseSearchDtos.getStockOnDate());

				row.createCell(2).setCellValue(responseSearchDtos.getItemNumber());
				row.createCell(3).setCellValue(responseSearchDtos.getItemDescription());

				row.createCell(4).setCellValue(responseSearchDtos.getAvailableStock());
				row.createCell(5).setCellValue(responseSearchDtos.getStore());

				row.createCell(6).setCellValue(responseSearchDtos.getBinLocation());
				row.createCell(7).setCellValue(responseSearchDtos.getNdpPrice().doubleValue());
				row.createCell(8).setCellValue(responseSearchDtos.getMrpPrice().doubleValue());
				row.createCell(9).setCellValue(responseSearchDtos.getTotal().doubleValue());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream itemMovementReports(List<ItemMovementDto> list) throws IOException {
		String[] Columns = { "From Date", "To Date", "Dealer Name", "Dealer Code", "KAI Invoice Number", "Item Number",
				"Item Descrition", "Purchase Quantity", "Ref.GRN No.", "Sale Qty",
				"ref.Documet/Dealer Sales Invoice No.", "Price/NDP", "Opening Balance", "Closing Balance" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("ItemMovementReport");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (ItemMovementDto responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getFromDate());
				row.createCell(1).setCellValue(responseSearchDtos.getToDate());
				row.createCell(2).setCellValue(responseSearchDtos.getDealerName());
				row.createCell(3).setCellValue(responseSearchDtos.getDealerCode());

				row.createCell(4).setCellValue(responseSearchDtos.getKaiInvoiceNumber());

				row.createCell(5).setCellValue(responseSearchDtos.getItemNumber());
				row.createCell(6).setCellValue(responseSearchDtos.getItemDescription());

				row.createCell(7).setCellValue(responseSearchDtos.getPurchaseQuantity());
				row.createCell(8).setCellValue(responseSearchDtos.getRefGrnNo());
				row.createCell(9).setCellValue(responseSearchDtos.getSaleQty());
				row.createCell(10).setCellValue(responseSearchDtos.getSalesInvoiceNo());
				row.createCell(11).setCellValue(responseSearchDtos.getSaleQty());
				row.createCell(12).setCellValue(responseSearchDtos.getNdp().doubleValue());
				row.createCell(13).setCellValue(responseSearchDtos.getOpeningBalance().doubleValue());
				row.createCell(14).setCellValue(responseSearchDtos.getClosingBalance().doubleValue());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream inventoryMovementReports(List<InventoryMovementDto> list) throws IOException {
		String[] Columns = { "From Date", "To Date", "Dealer Name", "Dealer Code", "KAI Invoice Number", "Item Number",
				"Item Descrition", "Purchase Quantity", "Ref.GRN No.", "Sale Qty",
				"ref.Documet/Dealer Sales Invoice No.", "Price/NDP", "Opening Balance", "Closing Balance" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("InventoryMovementReport");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (InventoryMovementDto responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getFromDate());
				row.createCell(1).setCellValue(responseSearchDtos.getToDate());
				row.createCell(2).setCellValue(responseSearchDtos.getDealerName());
				row.createCell(3).setCellValue(responseSearchDtos.getDealerCode());

				row.createCell(4).setCellValue(responseSearchDtos.getKaiInvoiceNumber());

				row.createCell(5).setCellValue(responseSearchDtos.getItemNumber());
				row.createCell(6).setCellValue(responseSearchDtos.getItemDescription());

				row.createCell(7).setCellValue(responseSearchDtos.getPurchaseQuantity());
				row.createCell(8).setCellValue(responseSearchDtos.getRefGrnNo());
				row.createCell(9).setCellValue(responseSearchDtos.getSaleQty());
				row.createCell(10).setCellValue(responseSearchDtos.getSalesInvoiceNo());
				row.createCell(11).setCellValue(responseSearchDtos.getSaleQty());
				row.createCell(12).setCellValue(responseSearchDtos.getNdp().doubleValue());
				row.createCell(13).setCellValue(responseSearchDtos.getOpeningBalance().doubleValue());
				row.createCell(14).setCellValue(responseSearchDtos.getClosingBalance().doubleValue());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream tollFreeReports(List<TollFreeReportDto> list) throws IOException {
		String[] Columns = { "Toll Free Call Number", "Model", "Machine Serial Number", "Date of delivery",
				"Dealer Name", "Dept.", "Description", "Customer Name", "Customer Number", "Enquiry Saved On", "State",
				"Action taken by Dealer/TM", "FRT", "FRT Delay Reasons", "Action Taken shared by Dealer/TM",
				"ART Delay reasons", "Pending Days", "ART", "Customer Type", "Enquiry" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("TollFreeReport");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (TollFreeReportDto responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getTollFreeCallNumber());
				row.createCell(1).setCellValue(responseSearchDtos.getModel());
				row.createCell(2).setCellValue(responseSearchDtos.getMachineSerialNumber());
				row.createCell(3).setCellValue(responseSearchDtos.getDateOfDelivery());

				row.createCell(4).setCellValue(responseSearchDtos.getDealerName());

				row.createCell(5).setCellValue(responseSearchDtos.getDepartment());
				row.createCell(6).setCellValue(responseSearchDtos.getDescription());

				row.createCell(7).setCellValue(responseSearchDtos.getCustomerName());
				row.createCell(8).setCellValue(responseSearchDtos.getCustomerNumber());
				row.createCell(9).setCellValue(responseSearchDtos.getEnquirySavedOn());
				row.createCell(10).setCellValue(responseSearchDtos.getState());
				row.createCell(11).setCellValue(responseSearchDtos.getActionTakenByDealer());
				row.createCell(12).setCellValue(responseSearchDtos.getFrt());
				row.createCell(13).setCellValue(responseSearchDtos.getFrtDelayReasons());
				row.createCell(14).setCellValue(responseSearchDtos.getActionTakenSharedByDealer());
				row.createCell(15).setCellValue(responseSearchDtos.getArtDelayReasons());
				row.createCell(16).setCellValue(responseSearchDtos.getPendingDays());
				row.createCell(17).setCellValue(responseSearchDtos.getArt());
				row.createCell(18).setCellValue(responseSearchDtos.getCustomerType());
				row.createCell(19).setCellValue(responseSearchDtos.getEnquiry());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream backOrderPartsReports(List<BackOrderPartsReportsDto> list) throws IOException {
		String[] Columns = { "Dealer Name", "PO Number", "PO Date", "Sale Order Number", "Sales Order Date",
				"Item Number", "Item Description", "PO Order Qty", "KAI Supplied Qty", "Pending Back order Qty",
				"NDP Price", "Total" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("BackOrderPartsReport");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (BackOrderPartsReportsDto responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getDealerName());

				row.createCell(1).setCellValue(responseSearchDtos.getPoNumber());
				row.createCell(2).setCellValue(responseSearchDtos.getPoDate());
				row.createCell(3).setCellValue(responseSearchDtos.getSaleOrderNumber());
				row.createCell(4).setCellValue(responseSearchDtos.getSalesOrderDate());

				row.createCell(5).setCellValue(responseSearchDtos.getItemNumber());
				row.createCell(6).setCellValue(responseSearchDtos.getItemDescription());

				row.createCell(7).setCellValue(responseSearchDtos.getPoOrderQty());
				row.createCell(8).setCellValue(responseSearchDtos.getKaiSuppliedQty());
				row.createCell(9).setCellValue(responseSearchDtos.getPendingBackorderQty());
				row.createCell(10).setCellValue(responseSearchDtos.getNdpPrice().doubleValue());
				row.createCell(11).setCellValue(responseSearchDtos.getTotal().doubleValue());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream nonMovingPartsStockReport(List<NonMovingPartsStockReportDto> list)
			throws IOException {
		String[] Columns = { "Dealer Name", "Item Number", "Item Description", "Qty", "Location", "Ageing 1 Year",
				"Ageing 2 Years", "Ageing 3 Years", "Ageing More than 3 Years", "NDP Price", "MRP Price", "Total" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("NonMovingPartsStockReport");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (NonMovingPartsStockReportDto responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getDealerName());
				row.createCell(1).setCellValue(responseSearchDtos.getItemNumber());

				row.createCell(2).setCellValue(responseSearchDtos.getItemDescription());
				row.createCell(3).setCellValue(responseSearchDtos.getQty());

				row.createCell(4).setCellValue(responseSearchDtos.getLocation());
				row.createCell(5).setCellValue(responseSearchDtos.getAgeingYear1());

				row.createCell(6).setCellValue(responseSearchDtos.getAgeingYear2());
				row.createCell(7).setCellValue(responseSearchDtos.getAgeingYear3());
				row.createCell(8).setCellValue(responseSearchDtos.getAgeingYearMoreThan3());
				row.createCell(9).setCellValue(responseSearchDtos.getNdpPrice().doubleValue());
				row.createCell(10).setCellValue(responseSearchDtos.getMrpPrice().doubleValue());
				row.createCell(11).setCellValue(responseSearchDtos.getTotal().doubleValue());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream adjustmentReport(List<StockAdjustmentSearchResult> list) throws IOException {
		String[] Columns = { "Adjustment Date", "Adjustment No", "Adjustment Type", "Item Number", "Item Description",
				"Store", "Bin Location", "Qty Adjusted", "MRP", "Reason" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("StockAdjustmentReport");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (StockAdjustmentSearchResult responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getAdjustmentDate());
				row.createCell(1).setCellValue(responseSearchDtos.getAdjustmentNo());

				row.createCell(2).setCellValue(responseSearchDtos.getAdjustmentType());
				row.createCell(3).setCellValue(responseSearchDtos.getItemNo());

				row.createCell(4).setCellValue(responseSearchDtos.getItemDesc());
				row.createCell(5).setCellValue(responseSearchDtos.getStore());

				row.createCell(6).setCellValue(responseSearchDtos.getBin());
				row.createCell(7).setCellValue(responseSearchDtos.getQuantity());
				row.createCell(8).setCellValue(responseSearchDtos.getMrp().doubleValue());
				row.createCell(9).setCellValue(responseSearchDtos.getReason());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	// public static ByteArrayInputStream
	// branchTransferReport(List<StockAdjustmentSearchResult> list) throws
	// IOException {
	// String[] Columns = {"Date", "Document Number", "Dealer Name", "Dealer
	// Branch Name", "Branch Code", "Parts Number", "Parts Description", "Qty",
	// "MRP Amount", "NDP", "Amount"};
	//
	// try (
	// Workbook workbook = new XSSFWorkbook();
	// ByteArrayOutputStream out = new ByteArrayOutputStream();
	// ) {
	// CreationHelper creationHelper = workbook.getCreationHelper();
	//
	// Sheet sheet = workbook.createSheet("StockAdjustmentReport");
	//
	// Font headerFont = workbook.createFont();
	// headerFont.setBold(true);
	//
	// CellStyle headerCellStyle = workbook.createCellStyle();
	// headerCellStyle.setFont(headerFont);
	//
	// Row headerRow = sheet.createRow(0);
	//
	// for (int col = 0; col < Columns.length; col++) {
	// Cell cell = headerRow.createCell(col);
	// cell.setCellValue(Columns[col]);
	// cell.setCellStyle(headerCellStyle);
	// }
	//
	// int rowIdx = 1;
	// for (StockAdjustmentSearchResult responseSearchDtos : list) {
	// Row row = sheet.createRow(rowIdx++);
	//
	// row.createCell(0).setCellValue(responseSearchDtos.getAdjustmentDate());
	// row.createCell(1).setCellValue(responseSearchDtos.getAdjustmentNo());
	//
	// row.createCell(2).setCellValue(responseSearchDtos.getAdjustmentType());
	// row.createCell(3).setCellValue(responseSearchDtos.getItemNo());
	//
	// row.createCell(4).setCellValue(responseSearchDtos.getItemDesc());
	// row.createCell(5).setCellValue(responseSearchDtos.getStore());
	//
	// row.createCell(6).setCellValue(responseSearchDtos.getBin());
	// row.createCell(7).setCellValue(responseSearchDtos.getQuantity());
	// row.createCell(8).setCellValue(responseSearchDtos.getMrp().doubleValue());
	// row.createCell(9).setCellValue(responseSearchDtos.getReason());
	// }
	//
	// workbook.write(out);
	// return new ByteArrayInputStream(out.toByteArray());
	// }
	// }

	public static ByteArrayInputStream enquiryExcelReport(List<EnquirySearchResponseDto> responceSearchDto)
			throws IOException {
		String[] Columns = { "Enquiry Status", "Enquiry Type", "Enquiry Number", "Enquiry Date", "Sales Person",
				"App Enquiry Flag", "Validation Date", "Source", "Current FollowUp Date", "Next FollowUp Date",
				"Tentative Purchase Date", "Prospect Code", "Prospect Type", "Company Name", "First Name",
				"Middle Name", "Last Name", "Father Name", "Mobile Number", "Telephone Number", "WhatsApp Number",
				"Email Id", "Address1", "Address2", "Address3", "Pincode", "Post Office", "Tehsil", "District", "City",
				"State", "Country", "Item Number", "Item Description", "Product", "Model", "SubModel", "Variant",
				"Cash Loan", "Financier", "Subsidy", "Exchange Required", "Generation Activity Number",
				"Conversion Activity Number", "Remarks", "Asset Value", "Dealer Code", "Dealer Name" }; // Suraj--31/10/2022
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("Enquiry");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (EnquirySearchResponseDto responseSearchDtos : responceSearchDto) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getEnquiryStatus());
				row.createCell(1).setCellValue(responseSearchDtos.getEnquiryType());
				row.createCell(2).setCellValue(responseSearchDtos.getEnquiryNumber());
				row.createCell(3).setCellValue(responseSearchDtos.getEnquiryDate());
				row.createCell(4).setCellValue(responseSearchDtos.getSalesPerson());
				row.createCell(5).setCellValue(responseSearchDtos.getAppEnquiryFlag());
				row.createCell(6).setCellValue(responseSearchDtos.getValidationDate());
				row.createCell(7).setCellValue(responseSearchDtos.getSource());
				row.createCell(8).setCellValue(responseSearchDtos.getCurrentFollowUpDate());
				row.createCell(9).setCellValue(responseSearchDtos.getNextFollowUpDate());
				row.createCell(10).setCellValue(responseSearchDtos.getTentativePurchaseDate());
				row.createCell(11).setCellValue(responseSearchDtos.getProspectCode());
				row.createCell(12).setCellValue(responseSearchDtos.getProspectType());
				row.createCell(13).setCellValue(responseSearchDtos.getCompanyName());
				row.createCell(14).setCellValue(responseSearchDtos.getFirstName());
				row.createCell(15).setCellValue(responseSearchDtos.getMiddleName());
				row.createCell(16).setCellValue(responseSearchDtos.getLastName());
				row.createCell(17).setCellValue(responseSearchDtos.getFatherName());
				row.createCell(18).setCellValue(responseSearchDtos.getMobileNumber());
				row.createCell(19).setCellValue(responseSearchDtos.getTelephoneNumber());
				row.createCell(20).setCellValue(responseSearchDtos.getWhatsAppNumber());
				row.createCell(21).setCellValue(responseSearchDtos.getEmailId());
				row.createCell(22).setCellValue(responseSearchDtos.getAddress1());
				row.createCell(23).setCellValue(responseSearchDtos.getAddress2());
				row.createCell(24).setCellValue(responseSearchDtos.getAddress3());
				row.createCell(25).setCellValue(responseSearchDtos.getPinCode());
				row.createCell(26).setCellValue(responseSearchDtos.getPostOffice());
				row.createCell(27).setCellValue(responseSearchDtos.getTehsil());
				row.createCell(28).setCellValue(responseSearchDtos.getDistrict());
				row.createCell(29).setCellValue(responseSearchDtos.getCity());
				row.createCell(30).setCellValue(responseSearchDtos.getState());
				row.createCell(31).setCellValue(responseSearchDtos.getCountry());
				row.createCell(32).setCellValue(responseSearchDtos.getItemNumber());
				row.createCell(33).setCellValue(responseSearchDtos.getItemDescription());
				row.createCell(34).setCellValue(responseSearchDtos.getProduct());
				row.createCell(35).setCellValue(responseSearchDtos.getModel());
				row.createCell(36).setCellValue(responseSearchDtos.getSubModel());
				row.createCell(37).setCellValue(responseSearchDtos.getVariant());
				row.createCell(38).setCellValue(responseSearchDtos.getCashLoan());
				row.createCell(39).setCellValue(responseSearchDtos.getFinancier());
				row.createCell(40).setCellValue(responseSearchDtos.getSubsidy());
				row.createCell(41).setCellValue(responseSearchDtos.getExchangeRequired());
				row.createCell(42).setCellValue(responseSearchDtos.getGenerationActivityNumber());
				row.createCell(43).setCellValue(responseSearchDtos.getConversionActivityNumber());
				row.createCell(44).setCellValue(responseSearchDtos.getRemarks());
				row.createCell(45).setCellValue(responseSearchDtos.getAssetValue());
				row.createCell(46).setCellValue(responseSearchDtos.getDealerCode()); // Suraj--31/10/2022
				row.createCell(47).setCellValue(responseSearchDtos.getDealerName()); // Suraj--31/10/2022
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream transitDetaitsExcelReport(List<SearchResponsedto> responceSearchDto)
			throws IOException {
		String[] Columns = { "Invoice No", "Invoice Date", "Invoice Amount", "Transport Mode", "Transporter", "LR No",
				"Expected Delivery Date", "No Of Boxes", "Status" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("Transit");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (SearchResponsedto responseSearchDtos : responceSearchDto) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(responseSearchDtos.getInvoiceNo());
				row.createCell(1).setCellValue(responseSearchDtos.getInvoiceDate());
				row.createCell(2).setCellValue(responseSearchDtos.getInvoiceAmount());
				row.createCell(3).setCellValue(responseSearchDtos.getTransportMode());
				row.createCell(4).setCellValue(responseSearchDtos.getTransporter());
				row.createCell(5).setCellValue(responseSearchDtos.getLrNo());
				row.createCell(6).setCellValue(responseSearchDtos.getExpectedDeliveryDate());
				row.createCell(7).setCellValue(responseSearchDtos.getNoOfBoxes());
				row.createCell(8).setCellValue(responseSearchDtos.getStatus());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream customerSalseOrderExcelReport(
			List<SpareSaleOrderResponseDto> responseSearchExcel) throws IOException {
		String[] Columns = { "salesOrderNumber", "salesOrderDate", "quotationNumber", "mobileNumber", "customerName",
				"customerType", "customerAddress1", "customerAddress2", "totalBasicValue", "totalDiscountValue",
				"totalSalesOrderAmount", "totalTaxAmount" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("CustomerSalseOrder");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (SpareSaleOrderResponseDto responseSearchExcel1 : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(responseSearchExcel1.getSalesOrderNumber());
				row.createCell(1).setCellValue(responseSearchExcel1.getSalesOrderDate());
				row.createCell(2).setCellValue(responseSearchExcel1.getQuotationNumber());
				row.createCell(3).setCellValue(responseSearchExcel1.getMobileNumber());
				row.createCell(4).setCellValue(responseSearchExcel1.getCustomerName());
				row.createCell(5).setCellValue(responseSearchExcel1.getCustomerType());
				row.createCell(6).setCellValue(responseSearchExcel1.getCustomerAddress1());
				row.createCell(7).setCellValue(responseSearchExcel1.getCustomerAddress2());
				row.createCell(8).setCellValue(responseSearchExcel1.getTotalBasicValue());
				row.createCell(9).setCellValue(responseSearchExcel1.getTotalDiscountValue());
				row.createCell(10).setCellValue(responseSearchExcel1.getTotalSalesOrderAmount());
				row.createCell(11).setCellValue(responseSearchExcel1.getTotalTaxAmount());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream bintobinTransferExcelReport(
			List<SpareBinTransferSearchResponseDto> responseSearchExcel) throws IOException {
		String[] Columns = { "Transfer Number", "Transfer Date", "Item No", "Item Description", "From Store",
				"From Location", "MRP", "Transfer Qty", "To Store", "To Location" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("BinToBinTransfer");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (SpareBinTransferSearchResponseDto responseSearchExcel1 : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(responseSearchExcel1.getTransferNumber());
				row.createCell(1).setCellValue(responseSearchExcel1.getTransferDate());
				row.createCell(2).setCellValue(responseSearchExcel1.getItemNo());
				row.createCell(3).setCellValue(responseSearchExcel1.getItemDescription());
				row.createCell(4).setCellValue(responseSearchExcel1.getFromStore());
				row.createCell(5).setCellValue(responseSearchExcel1.getFromLocation());
				row.createCell(6).setCellValue(responseSearchExcel1.getMrp());
				row.createCell(7).setCellValue(responseSearchExcel1.getTransferQty());
				row.createCell(8).setCellValue(responseSearchExcel1.getToStore());
				row.createCell(9).setCellValue(responseSearchExcel1.getToLocation());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream currentStockExcelReport(List<CurrentStockResponse> responseSearchExcel)
			throws IOException {
		String[] Columns = { "Item No", "Item Description", "Bin Location", "Store Name", "SPMRP", "Avalilable Stock" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("BinToBinTransfer");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (CurrentStockResponse responseSearchExcel1 : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(responseSearchExcel1.getItemNo());
				row.createCell(1).setCellValue(responseSearchExcel1.getItemDescription());
				row.createCell(2).setCellValue(responseSearchExcel1.getBinLocation());
				row.createCell(3).setCellValue(responseSearchExcel1.getStoreName());
				row.createCell(4).setCellValue(responseSearchExcel1.getSpmrp());
				row.createCell(5).setCellValue(responseSearchExcel1.getAvailableStock());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream dcExcelReport(List<SearchDeliveryChallanResponse> responseSearchExcel)
			throws IOException {
		String[] Columns = { "Delivery Challan Number", "Delivery Challan Date", "Delivery Challan Type", "DC Status",
				"Enquiry Number", "Enquiry Date", "Enquiry Type", "Dealer Name", "Dealer Code", "Dealer State",
				"DSE Employee Code", "DSE Name", "Cash/Loan", "Financier", "Item Number", "Item Description",
				"Quantity", "Chassis Number", "Engine Number", "Color", "Remark", "Customer Type", "Company Name",
				"Customer Code", "Customer Name", "Invoice Customer Name", "Mobile Number", "Address1", "Address2",
				"Address3", "Pin Code", "Post Office", "Village", " Tehsil", "District", "City", "State",
				"GSTIN Number", "PAN Number", "Insurance Company Code", "Insurance Company Name", "Insurance Address1",
				"Insurance Address2", "Insurance Address3", "Insurance PinCode", "Insurance Locality",
				"Insurance Tehsil", "Insurance City", "Insurance State", "Insurance Country",
				"Insurance TelephoneNumber", "Insurance Email", "Policy Start Date", "Policy Exipry Date",
				"Request Number", "Transfer To Dealer Name", "Transfer To Dealer Code", "Created Date",
				"KAI Invoice Date", "KAI Invoice Number", "Gate Pass Number", "Gate Pass Date", 
				"Bank Name", "bank Account No", "ifs Code", "Pan No"};	//Suraj-26/12/2022

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Delivery_Challan");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (SearchDeliveryChallanResponse responseSearchExcel1 : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(responseSearchExcel1.getDeliveryChallanNumber());
				row.createCell(1).setCellValue(responseSearchExcel1.getDeliveryChallanDate());
				row.createCell(2).setCellValue(responseSearchExcel1.getDeliveryChallantype());
				row.createCell(3).setCellValue(responseSearchExcel1.getdcStatus());
				row.createCell(4).setCellValue(responseSearchExcel1.getEnquiryNumber());
				row.createCell(5).setCellValue(responseSearchExcel1.getEnquiryDate());
				row.createCell(6).setCellValue(responseSearchExcel1.getEnquiryType());
				row.createCell(7).setCellValue(responseSearchExcel1.getDealerName());
				row.createCell(8).setCellValue(responseSearchExcel1.getDealerCode());
				row.createCell(9).setCellValue(responseSearchExcel1.getDealerState());
				row.createCell(10).setCellValue(responseSearchExcel1.getdSE_EmployeeCode());
				row.createCell(11).setCellValue(responseSearchExcel1.getdSEName());
				row.createCell(12).setCellValue(responseSearchExcel1.getCashLoan());
				row.createCell(13).setCellValue(responseSearchExcel1.getFinancier());
				row.createCell(14).setCellValue(responseSearchExcel1.getItemNumber());
				row.createCell(15).setCellValue(responseSearchExcel1.getItemDescription());
				row.createCell(16).setCellValue(responseSearchExcel1.getQuantity());
				row.createCell(17).setCellValue(responseSearchExcel1.getChassisNumber());
				row.createCell(18).setCellValue(responseSearchExcel1.getEngineNumber());
				row.createCell(19).setCellValue(responseSearchExcel1.getColor());
				row.createCell(20).setCellValue(responseSearchExcel1.getRemark());
				row.createCell(21).setCellValue(responseSearchExcel1.getCustomerType());
				row.createCell(22).setCellValue(responseSearchExcel1.getCompanyName());
				row.createCell(23).setCellValue(responseSearchExcel1.getCustomerCode());
				row.createCell(24).setCellValue(responseSearchExcel1.getCustomerName());
				row.createCell(25).setCellValue(responseSearchExcel1.getInvoiceCustomerName());
				row.createCell(26).setCellValue(responseSearchExcel1.getMobileNumber());
				row.createCell(27).setCellValue(responseSearchExcel1.getAddress1());
				row.createCell(28).setCellValue(responseSearchExcel1.getAddress2());
				row.createCell(29).setCellValue(responseSearchExcel1.getAddress2());
				row.createCell(30).setCellValue(responseSearchExcel1.getPinCode());
				row.createCell(31).setCellValue(responseSearchExcel1.getPostOffice());
				row.createCell(32).setCellValue(responseSearchExcel1.getVillage());
				row.createCell(33).setCellValue(responseSearchExcel1.getTehsil());
				row.createCell(34).setCellValue(responseSearchExcel1.getDistrict());
				row.createCell(35).setCellValue(responseSearchExcel1.getCity());
				row.createCell(36).setCellValue(responseSearchExcel1.getState());
				row.createCell(37).setCellValue(responseSearchExcel1.getGstinNumber());
				row.createCell(38).setCellValue(responseSearchExcel1.getPanNumber());
				row.createCell(39).setCellValue(responseSearchExcel1.getInsuranceCompanyCode());
				row.createCell(40).setCellValue(responseSearchExcel1.getInsuranceCompanyName());
				row.createCell(41).setCellValue(responseSearchExcel1.getInsAddress1());
				row.createCell(42).setCellValue(responseSearchExcel1.getInsAddress2());
				row.createCell(43).setCellValue(responseSearchExcel1.getInsAddress3());
				row.createCell(44).setCellValue(responseSearchExcel1.getInsPinCode());
				row.createCell(45).setCellValue(responseSearchExcel1.getInsLocality());
				row.createCell(46).setCellValue(responseSearchExcel1.getInsTehsil());
				row.createCell(47).setCellValue(responseSearchExcel1.getInsCity());
				row.createCell(48).setCellValue(responseSearchExcel1.getInsState());
				row.createCell(49).setCellValue(responseSearchExcel1.getInsCountry());
				row.createCell(50).setCellValue(responseSearchExcel1.getInsTelephoneNumber());
				row.createCell(51).setCellValue(responseSearchExcel1.getInsEmail());
				row.createCell(52).setCellValue(responseSearchExcel1.getPolicyStartDate());

				row.createCell(53).setCellValue(responseSearchExcel1.getPolicyExipryDate());
				row.createCell(54).setCellValue(responseSearchExcel1.getRequestNumber());
				row.createCell(55).setCellValue(responseSearchExcel1.getTransferToDealerName());

				row.createCell(56).setCellValue(responseSearchExcel1.getTransferToDealerCode());
				row.createCell(57).setCellValue(responseSearchExcel1.getCreatedDate());
				row.createCell(58).setCellValue(responseSearchExcel1.getkaiInvoiceDate());
				row.createCell(59).setCellValue(responseSearchExcel1.getkaiInvoiceNumber());
				row.createCell(60).setCellValue(responseSearchExcel1.getGetPassNumber());
				row.createCell(61).setCellValue(responseSearchExcel1.getGatePassDate());
				row.createCell(62).setCellValue(responseSearchExcel1.getBankName());	//Suraj-26/12/2022
				row.createCell(63).setCellValue(responseSearchExcel1.getBankAccountNo());	//Suraj-26/12/2022
				row.createCell(64).setCellValue(responseSearchExcel1.getIfsCode());	//Suraj-26/12/2022
				row.createCell(65).setCellValue(responseSearchExcel1.getPanNo());	//Suraj-26/12/2022
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream marketIntelligenceReport(
			List<MarketIntelligenceSearchResponse> responseSearchExcel) throws IOException {
		String[] Columns = { "User Name", "Dealer Code", "Dealer Name", "Date", "Product", "Competitor",
				"Feedback Category", "Feedback" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("BinToBinTransfer");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (MarketIntelligenceSearchResponse responseSearchExcel1 : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(responseSearchExcel1.getUserName());
				row.createCell(1).setCellValue(responseSearchExcel1.getDealerCode());
				row.createCell(2).setCellValue(responseSearchExcel1.getDealerName());
				row.createCell(3).setCellValue(responseSearchExcel1.getDate());
				row.createCell(4).setCellValue(responseSearchExcel1.getProduct());
				row.createCell(5).setCellValue(responseSearchExcel1.getCompetitor());
				row.createCell(6).setCellValue(responseSearchExcel1.getFeedbackCategory());
				row.createCell(7).setCellValue(responseSearchExcel1.getFeedback());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream trainingProgramExcelReport(List<TrainingProgramReportSearchResponse> tpr)
			throws IOException {
		String[] Columns = { "Program Number", "Program Date", "Training Location Desc", "Training Module Desc",
				"Training Type Desc", "Location", "Start Date", "End Date", "Remarks", "Nomination Number",
				"Nomination Date", "Dealer Name", "Employee Name", "Attended Status", "Nomination Status",
				"Training Date", "Attendance" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("TrainingProgramReport");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (TrainingProgramReportSearchResponse tprExcel : tpr) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(tprExcel.getProgramNumber());
				row.createCell(1).setCellValue(tprExcel.getProgramDate());
				row.createCell(2).setCellValue(tprExcel.getTrainingLocDesc());
				row.createCell(3).setCellValue(tprExcel.getTrainingModuleDesc());
				row.createCell(4).setCellValue(tprExcel.getTrainingTypeDesc());
				row.createCell(5).setCellValue(tprExcel.getLocation());
				row.createCell(6).setCellValue(tprExcel.getStartDate());
				row.createCell(7).setCellValue(tprExcel.getEndDate());
				row.createCell(8).setCellValue(tprExcel.getRemarks());
				row.createCell(9).setCellValue(tprExcel.getNomination_Number());
				row.createCell(10).setCellValue(tprExcel.getNominationDate());
				row.createCell(11).setCellValue(tprExcel.getDealerName());
				row.createCell(12).setCellValue(tprExcel.getEmployeeName());
				row.createCell(13).setCellValue(tprExcel.getAttendedStatus());
				row.createCell(14).setCellValue(tprExcel.getNominationStatus());
				row.createCell(15).setCellValue(tprExcel.getTrainingDate());
				row.createCell(16).setCellValue(tprExcel.getAttendance());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream jobCardExcelReport(List<JobcardSearchResponseDto> jcr,
			List<JobcardDetailedExcelResponseDto> resultExcel, String isFor) throws IOException {
		String[] Columns = { "Dealer Name", "Job Card Number", "Model", "Chassis Number", "Engine Number",
				"Current Hours", "Meter Change Hours", "Total Hours", "Service Category", "Job Card Date",
				"Job Card Closed Date ", "Machine In Date", "Machine In Time", "Status", "PCR Number", "PCR Status",
				"Mechanic Name", "Reason For Delay", "Created By", "Creator Name" };

		String[] detailedColumns = { "Zone", "State", "ASM/RSM Name", "TM Name", "Sold Dealer Code", "Sold Dealer Name",
				"Service Dealer Code", "Service Dealer Name", "Job Card Number", "Product", "Model", "Sub Model",
				"Chassis No", "Engine No", "Current Hours", "Meter Change Hours", "Total Hours", "Customer Name",
				"Mobile Number", "Alternate Number", "Customer Full Address", "Service Category", "Service Type",
				"DC Date", "Date of Failure", "Job card date", "Job Card Closed Date", "Machine In Date",
				"Machine In Time", "Machine Out Date", "Machine Out Date Time", "Total Hours taken", "Place of Service",
				"Customer Concern", "Mechanic Observation", "Final Action Taken", "Parts Sale", "Warranty Claim Amount",
				"Labour Charge", "Outside Labour Charge", "Total Amount", "Status", "PCR Number", "PCR Status",
				"Mechanic Name", "Reason For Delay", "Created By", "Creator Name" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			if (isFor.equals("VIEW") || isFor.equals("EXCEL")) {
				Sheet sheet = workbook.createSheet("JC Summery Report");
				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);
				Row headerRow = sheet.createRow(0);
				for (int col = 0; col < Columns.length; col++) {
					Cell cell = headerRow.createCell(col);
					cell.setCellValue(Columns[col]);
					cell.setCellStyle(headerCellStyle);
				}
				int rowIdx = 1;
				for (JobcardSearchResponseDto job : jcr) {
					Row row = sheet.createRow(rowIdx++);
					row.createCell(0).setCellValue(job.getDealerName());
					row.createCell(1).setCellValue(job.getJobCardNo());
					row.createCell(2).setCellValue(job.getModel());
					row.createCell(3).setCellValue(job.getChassisNo());
					row.createCell(4).setCellValue(job.getEngineNo());
					if (job.getCurrentHour() != null) {
						row.createCell(5).setCellValue(job.getCurrentHour());
					}
					if (job.getMeterChangeHours() != null) {
						row.createCell(6).setCellValue(job.getMeterChangeHours());
					}
					if (job.getMeterTotalHours() != null) {
						row.createCell(7).setCellValue(job.getMeterTotalHours());
					}
					if (job.getServiceCategory() != null) {
						row.createCell(8).setCellValue(job.getServiceCategory());
					}
					row.createCell(9).setCellValue(job.getJobCardDate());
					if (job.getCloseDate() != null) {
						row.createCell(10).setCellValue(job.getCloseDate());
					}
					row.createCell(11).setCellValue(job.getTaskDate());
					row.createCell(12).setCellValue(job.getTaskTime());
					row.createCell(13).setCellValue(job.getStatus());
					row.createCell(14).setCellValue(job.getPcrNumber());
					row.createCell(15).setCellValue(job.getPcrStatus());
					row.createCell(16).setCellValue(job.getMechanicName());
					row.createCell(17).setCellValue(job.getReasonForDelay());
					row.createCell(18).setCellValue(job.getCreatedBy());
					
					row.createCell(19).setCellValue(job.getCreatorName());	//Suraj--30/11/2022
				}
			} else if (isFor.equals("ALLEXCEL")) {
				Sheet sheet = workbook.createSheet("JC Detail Report");
				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);
				Row headerRow = sheet.createRow(0);
				for (int col = 0; col < detailedColumns.length; col++) {
					Cell cell = headerRow.createCell(col);
					cell.setCellValue(detailedColumns[col]);
					cell.setCellStyle(headerCellStyle);
				}
				int rowIdx = 1;
				for (JobcardDetailedExcelResponseDto job : resultExcel) {
					Row row = sheet.createRow(rowIdx++);
					row.createCell(0).setCellValue(job.getZone());
					row.createCell(1).setCellValue(job.getState());
					row.createCell(2).setCellValue(job.getAsmName());
					row.createCell(3).setCellValue(job.getTmName());
					row.createCell(4).setCellValue(job.getSoldDealerCold());
					row.createCell(5).setCellValue(job.getSoldDealerName());
					row.createCell(6).setCellValue(job.getServiceDealerCode());
					row.createCell(7).setCellValue(job.getServiceDealerName());
					row.createCell(8).setCellValue(job.getJobCardNo());
					row.createCell(9).setCellValue(job.getProduct());
					row.createCell(10).setCellValue(job.getModel());
					row.createCell(11).setCellValue(job.getSubModel());
					row.createCell(12).setCellValue(job.getChassisNo());
					row.createCell(13).setCellValue(job.getEngineNo());
					if (job.getCurrentHour() != null) {
						row.createCell(14).setCellValue(job.getCurrentHour());
					}
					if (job.getMeterChangeHours() != null) {
						row.createCell(15).setCellValue(job.getMeterChangeHours());
					}
					if (job.getMeterTotalHours() != null) {
						row.createCell(16).setCellValue(job.getMeterTotalHours());
					}
					row.createCell(17).setCellValue(job.getCustomerName());
					row.createCell(18).setCellValue(job.getMobileNumber());
					row.createCell(19).setCellValue(job.getAlternate_mobile_no());
					row.createCell(20).setCellValue(job.getAddress1());
					row.createCell(21).setCellValue(job.getServiceCategory2());
					row.createCell(22).setCellValue(job.getServiceType());
					row.createCell(23).setCellValue(job.getDeliveryDate());
					row.createCell(24).setCellValue(job.getFailureDate());
					row.createCell(25).setCellValue(job.getJobDate());
					row.createCell(26).setCellValue(job.getCloseDate());
					row.createCell(27).setCellValue(job.getTaskDate());
					row.createCell(28).setCellValue(job.getTaskTime());
					row.createCell(29).setCellValue(job.getTaskEndDate());
					row.createCell(30).setCellValue(job.getTaskEndTime());
					row.createCell(31).setCellValue(job.getTotalHours());
					row.createCell(32).setCellValue(job.getPlaceOfService());
					row.createCell(33).setCellValue(job.getCustomerConcern());
					row.createCell(34).setCellValue(job.getMechanicObservation());
					row.createCell(35).setCellValue(job.getFinalActionTaken());
					row.createCell(36).setCellValue(job.getPartAmt());
					row.createCell(37).setCellValue(job.getWarrentyAmount());
					row.createCell(38).setCellValue(job.getLabourAmt());
					row.createCell(39).setCellValue(job.getOutsideAmt());
					row.createCell(40).setCellValue(job.getJobCardAmount());
					row.createCell(41).setCellValue(job.getStatus());
					row.createCell(42).setCellValue(job.getPcrNumber());
					row.createCell(43).setCellValue(job.getPcrStatus());
					row.createCell(44).setCellValue(job.getMechanicName());
					row.createCell(45).setCellValue(job.getReasonForDelay());
					row.createCell(46).setCellValue(job.getCreatedBy());
					
					row.createCell(47).setCellValue(job.getCreatorName());	//Suraj--30/11/2022
				}
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream machineStockExcelReport(List<MachineStockSearchResponse> msr, Long branchId)
			throws IOException {
		String[] Columns_kai = { "Zone", "Region", "Area", "Dealer Code", "Dealer Name", "Dealer Location", "Product",
				"Series", "Model", "Sub Model", "Variant", "Item No", "Item Description", "Chassis No", "Engine No",
				"Last Transactin Type", "Last Transactin No", "Last Transactin Date", "Dealer State", // Suraj--06/11/2022 (Dealer State)
				"Invoice No", "Invoice Date"};	//Suraj--02/01/2023}
		String[] Columns_dealer = { "Product", "Series", "Model", "Sub Model", "Variant", "Item No", "Item Description",
				"Chassis No", "Engine No", "Last Transactin Type", "Last Transactin No", "Last Transactin Date", 
				"Invoice No", "Invoice Date"};	//Suraj--02/01/2023
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			// CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("MSReport");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);

			if (branchId == null) {
				for (int col = 0; col < Columns_kai.length; col++) {
					Cell cell = headerRow.createCell(col);
					cell.setCellValue(Columns_kai[col]);
					cell.setCellStyle(headerCellStyle);
				}
				int rowIdx = 1;
				for (MachineStockSearchResponse ms : msr) {
					Row row = sheet.createRow(rowIdx++);
					row.createCell(0).setCellValue(ms.getZone());
					row.createCell(1).setCellValue(ms.getRegion());
					row.createCell(2).setCellValue(ms.getArea());
					row.createCell(3).setCellValue(ms.getDealerCode());
					row.createCell(4).setCellValue(ms.getDealerName());
					row.createCell(5).setCellValue(ms.getDealerLocation());
					row.createCell(6).setCellValue(ms.getProduct());
					row.createCell(7).setCellValue(ms.getSeries());
					row.createCell(8).setCellValue(ms.getModel());
					row.createCell(9).setCellValue(ms.getSubModel());
					row.createCell(10).setCellValue(ms.getVariant());
					row.createCell(11).setCellValue(ms.getItemNo());
					row.createCell(12).setCellValue(ms.getItemDescription());
					row.createCell(13).setCellValue(ms.getChassisNo());
					row.createCell(14).setCellValue(ms.getEngineNo());
					row.createCell(15).setCellValue(ms.getLastTransactinType());
					row.createCell(16).setCellValue(ms.getLastTransactinNo());
					row.createCell(17).setCellValue(ms.getLastTransactinDate());
					row.createCell(18).setCellValue(ms.getDealerState());	// Suraj--06/11/2022
					row.createCell(19).setCellValue(ms.getInvoiceNo());	// Suraj--02/01/2023
					row.createCell(20).setCellValue(ms.getInvoiceDate());	// Suraj--02/01/2023
				}
			} else {
				for (int col = 0; col < Columns_dealer.length; col++) {
					Cell cell = headerRow.createCell(col);
					cell.setCellValue(Columns_dealer[col]);
					cell.setCellStyle(headerCellStyle);
				}
				int rowIdx = 1;
				for (MachineStockSearchResponse ms : msr) {
					Row row = sheet.createRow(rowIdx++);

					row.createCell(0).setCellValue(ms.getProduct());
					row.createCell(1).setCellValue(ms.getSeries());
					row.createCell(2).setCellValue(ms.getModel());
					row.createCell(3).setCellValue(ms.getSubModel());
					row.createCell(4).setCellValue(ms.getVariant());
					row.createCell(5).setCellValue(ms.getItemNo());
					row.createCell(6).setCellValue(ms.getItemDescription());
					row.createCell(7).setCellValue(ms.getChassisNo());
					row.createCell(8).setCellValue(ms.getEngineNo());
					row.createCell(9).setCellValue(ms.getLastTransactinType());
					row.createCell(10).setCellValue(ms.getLastTransactinNo());
					row.createCell(11).setCellValue(ms.getLastTransactinDate());
					row.createCell(12).setCellValue(ms.getInvoiceNo());	// Suraj--02/01/2023
					row.createCell(13).setCellValue(ms.getInvoiceDate());	// Suraj--02/01/2023
				}
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream warrantyPcrExcelReport(List<WarrantyPcrSummaryExcelResponse> summary,
			List<WarrantyPcrDetailsExcelResponse> details) throws IOException {
		String[] SummaryColumns = { "Job Card Number", "Job Card Date", "Sold Dealer Code", "Sold Dealer Name",
				"Service Dealer Code", "Service Dealer Name", "Service Dealer City", "Service Dealer State",
				"PCR Number", "PCR Date", "PCR Status", "PCR Type", "Product", "Model", "Sub Model", "Chassis number",
				"Engine Number", "Date of Installation", "Date of Failure", "Hours", "Crop", "Crop Condition",
				"Field Condition", "Soil type", "Failure Type OE", "Failure Type Repeat Failure",
				"Customer Name & Address", "Customer Concern", "Mechanic Observation", "Dealer Observation",
				"Action Taken", "Dealer Remarks", "KAI Remarks", "Free Service Date1", "Free Service Hours1",
				"Free Service Date2", "Free Service Hours2", "Free Service Date3", "Free Service Hours3",
				"Free Service Date4", "Free Service Hours4", "Free Service Date5", "Free Service Hours5",
				"Free Service Date6", "Free Service Hours6", "Free Service Date7", "Free Service Hours7",
				"Free Service Date8", "Free Service Hours8", "Free Service Date9", "Free Service Hours9",
				"Free_ Service Date10", "Free Service Hours10", "1st Approved By", "Designation", "Approved Date1",
				"2nd Approved By", "Designation", "Approved Date2", "Approval Remakrs", "Claim Amount", "Approved Amount"};

		String[] DetailsColumns = { "PCR Number", "Type", "Code", "Description", "Category / Code / FRS Hours",
				"Serial Number/Complaint Desc", "Gear Ratio", "Engine RPM", "% Of Usage / Claimed Amt",
				"Usage during failure / Approved Amount" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Pcr Summary Report");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < SummaryColumns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(SummaryColumns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (WarrantyPcrSummaryExcelResponse responseSearchExcel1 : summary) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(responseSearchExcel1.getJobCardNo());
				row.createCell(1).setCellValue(responseSearchExcel1.getJobCardDate());
				row.createCell(2).setCellValue(responseSearchExcel1.getSoldDealerCode());
				row.createCell(3).setCellValue(responseSearchExcel1.getSoldDealerName());
				row.createCell(4).setCellValue(responseSearchExcel1.getServiceDealerCode());
				row.createCell(5).setCellValue(responseSearchExcel1.getServiceDealerName());
				row.createCell(6).setCellValue(responseSearchExcel1.getServiceDealerCity());
				row.createCell(7).setCellValue(responseSearchExcel1.getServiceDealerState());
				row.createCell(8).setCellValue(responseSearchExcel1.getPCRNo());
				row.createCell(9).setCellValue(responseSearchExcel1.getPCRDate());
				row.createCell(10).setCellValue(responseSearchExcel1.getStatus());
				row.createCell(11).setCellValue(responseSearchExcel1.getPcrType());
				row.createCell(12).setCellValue(responseSearchExcel1.getProduct());
				row.createCell(13).setCellValue(responseSearchExcel1.getModel());
				row.createCell(14).setCellValue(responseSearchExcel1.getsubModel());
				row.createCell(15).setCellValue(responseSearchExcel1.getChassisNo());
				row.createCell(16).setCellValue(responseSearchExcel1.getEngineNo());
				row.createCell(17).setCellValue(responseSearchExcel1.getDateOfInstallation());
				row.createCell(18).setCellValue(responseSearchExcel1.getDateOfFailure());
				row.createCell(19).setCellValue(responseSearchExcel1.getHours());
				row.createCell(20).setCellValue(responseSearchExcel1.getCrop());
				row.createCell(21).setCellValue(responseSearchExcel1.getCropCondition());
				row.createCell(22).setCellValue(responseSearchExcel1.getFieldCondition());
				row.createCell(23).setCellValue(responseSearchExcel1.getSoilType());
				row.createCell(24).setCellValue(responseSearchExcel1.getFailureTypeOE());
				row.createCell(25).setCellValue(responseSearchExcel1.getFailureTypeRepeatFailure());
				row.createCell(26).setCellValue(responseSearchExcel1.getCustomernameAddress());
				row.createCell(27).setCellValue(responseSearchExcel1.getCustomerConcern());
				row.createCell(28).setCellValue(responseSearchExcel1.getMechanicObservation());
				row.createCell(29).setCellValue(responseSearchExcel1.getDealerObservation());
				row.createCell(30).setCellValue(responseSearchExcel1.getActionTaken());
				row.createCell(31).setCellValue(responseSearchExcel1.getDealerRemarks());
				row.createCell(32).setCellValue(responseSearchExcel1.getKAIRemarks());
				row.createCell(33).setCellValue(responseSearchExcel1.getFreeServiceDate1());
				row.createCell(34).setCellValue(responseSearchExcel1.getFreeServiceHours1());
				row.createCell(35).setCellValue(responseSearchExcel1.getFreeServiceDate2());
				row.createCell(36).setCellValue(responseSearchExcel1.getFreeServiceHours2());
				row.createCell(37).setCellValue(responseSearchExcel1.getFreeServiceDate3());
				row.createCell(38).setCellValue(responseSearchExcel1.getFreeServiceHours3());
				row.createCell(39).setCellValue(responseSearchExcel1.getFreeServiceDate4());
				row.createCell(40).setCellValue(responseSearchExcel1.getFreeServiceHours4());
				row.createCell(41).setCellValue(responseSearchExcel1.getFreeServiceDate5());
				row.createCell(42).setCellValue(responseSearchExcel1.getFreeServiceHours5());
				row.createCell(43).setCellValue(responseSearchExcel1.getFreeServiceDate6());
				row.createCell(44).setCellValue(responseSearchExcel1.getFreeServiceHours6());
				row.createCell(45).setCellValue(responseSearchExcel1.getFreeServiceDate7());
				row.createCell(46).setCellValue(responseSearchExcel1.getFreeServiceHours7());
				row.createCell(47).setCellValue(responseSearchExcel1.getFreeServiceDate8());
				row.createCell(48).setCellValue(responseSearchExcel1.getFreeServiceHours8());
				row.createCell(49).setCellValue(responseSearchExcel1.getFreeServiceDate9());
				row.createCell(50).setCellValue(responseSearchExcel1.getFreeServiceHours9());
				row.createCell(51).setCellValue(responseSearchExcel1.getFreeServiceDate10());
				row.createCell(52).setCellValue(responseSearchExcel1.getFreeServiceHours10());
				row.createCell(53).setCellValue(responseSearchExcel1.getApprovedBy1());
				row.createCell(54).setCellValue(responseSearchExcel1.getDesignation1());
				row.createCell(55).setCellValue(responseSearchExcel1.getApprovedDate1());
				row.createCell(56).setCellValue(responseSearchExcel1.getApprovedBy2());
				row.createCell(57).setCellValue(responseSearchExcel1.getDesignation2());
				row.createCell(58).setCellValue(responseSearchExcel1.getApprovedDate2());
				row.createCell(59).setCellValue(responseSearchExcel1.getApprovedReamarks());
				row.createCell(60).setCellValue(responseSearchExcel1.getClaimAmount());	//Suraj-21-03-2023
				row.createCell(61).setCellValue(responseSearchExcel1.getApprovedAmount());	//Suraj-21-03-2023
			}

			Sheet sheetD = workbook.createSheet("Pcr Details Report");
			Font headerFontD = workbook.createFont();
			headerFontD.setBold(true);
			CellStyle headerCellStyleD = workbook.createCellStyle();
			headerCellStyleD.setFont(headerFont);
			Row headerRowD = sheetD.createRow(0);
			for (int col = 0; col < DetailsColumns.length; col++) {
				Cell cell = headerRowD.createCell(col);
				cell.setCellValue(DetailsColumns[col]);
				cell.setCellStyle(headerCellStyleD);
			}
			int rowIdxD = 1;
			for (WarrantyPcrDetailsExcelResponse responseDetails : details) {
				Row row = sheetD.createRow(rowIdxD++);
				row.createCell(0).setCellValue(responseDetails.getPCRNumber());
				row.createCell(1).setCellValue(responseDetails.getType());
				row.createCell(2).setCellValue(responseDetails.getCode());
				row.createCell(3).setCellValue(responseDetails.getDescription());
				row.createCell(4).setCellValue(responseDetails.getCategoryCodeFRSHours());
				row.createCell(5).setCellValue(responseDetails.getSerialNumberComplaintDescription());
				row.createCell(6).setCellValue(responseDetails.getGear_ratio());
				row.createCell(7).setCellValue(responseDetails.getEngine_rpm());
				row.createCell(8).setCellValue(responseDetails.getClaimQuantity());
				row.createCell(9).setCellValue(responseDetails.getApprovedAmount());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream wcrExcelReport(List<WarrantyWcrExcelSummaryResponse> summary,
			List<WarrantyPcrDetailsExcelResponse> details) throws IOException {
		String[] SummaryColumns = { "WCR Number", "WCR Date", "WCR Status", "Dealer Code", "Dealer Name", "Dealer City",
				"Dealer Mobile No", "PCR Number", "PCR Date", "Job Card Number", "Job Card Date", "Product Type",
				"Model", "Sub Model", "Chassis Number", "Engine Number", "Type Of Claim", "Hours", "Date Of Repair",
				"Date of Installation", "Date of Failure", "Delivery Challan Number", "Delivery Challan Date",
				"Transporter Name", "Dispatch Date", "No of Boxes", "Docket Number" };

		String[] DetailsColumns = { "WCR Number", "Type", "Code", "Description", "Category / Code / FRS Hours",
				"Serial Number/Complaint Desc", "Gear Ratio/Claim Qty", "Engine RPM/Approved Qty",
				"% Of Usage / Claimed Amt", "Usage during failure / Approved Amount" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Summary");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < SummaryColumns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(SummaryColumns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (WarrantyWcrExcelSummaryResponse responseSearchExcel1 : summary) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(responseSearchExcel1.getWcrNo());
				row.createCell(1).setCellValue(responseSearchExcel1.getWcrDate());
				row.createCell(2).setCellValue(responseSearchExcel1.getStatus());
				row.createCell(3).setCellValue(responseSearchExcel1.getDealerCode());
				row.createCell(4).setCellValue(responseSearchExcel1.getDealerName());
				row.createCell(5).setCellValue(responseSearchExcel1.getDealerCity());
				row.createCell(6).setCellValue(responseSearchExcel1.getDealerMobileNo());
				row.createCell(7).setCellValue(responseSearchExcel1.getPcrNo());
				row.createCell(8).setCellValue(responseSearchExcel1.getPcrDate());
				row.createCell(9).setCellValue(responseSearchExcel1.getJobCardNo());
				row.createCell(10).setCellValue(responseSearchExcel1.getJobCardDate());
				row.createCell(11).setCellValue(responseSearchExcel1.getProductType());
				row.createCell(12).setCellValue(responseSearchExcel1.getModel());
				row.createCell(13).setCellValue(responseSearchExcel1.getSubModel());
				row.createCell(14).setCellValue(responseSearchExcel1.getChassisNo());
				row.createCell(15).setCellValue(responseSearchExcel1.getEngineNo());
				row.createCell(16).setCellValue(responseSearchExcel1.getTypeOfClaim());
				row.createCell(17).setCellValue(responseSearchExcel1.getHour());
				row.createCell(18).setCellValue(responseSearchExcel1.getDateOfRepair());
				row.createCell(19).setCellValue(responseSearchExcel1.getInstallationDate());
				row.createCell(20).setCellValue(responseSearchExcel1.getDateOfFailure());
				row.createCell(21).setCellValue(responseSearchExcel1.getDeliveryChallanNumber());
				row.createCell(22).setCellValue(responseSearchExcel1.getDeliveryChallanDate());
				row.createCell(23).setCellValue(responseSearchExcel1.getTransporterName());
				row.createCell(24).setCellValue(responseSearchExcel1.getDispatchDate());
				row.createCell(25).setCellValue(responseSearchExcel1.getNoOfBoxes());
				row.createCell(26).setCellValue(responseSearchExcel1.getDocketNumber());
			}

			Sheet sheetD = workbook.createSheet("Details");
			Font headerFontD = workbook.createFont();
			headerFontD.setBold(true);
			CellStyle headerCellStyleD = workbook.createCellStyle();
			headerCellStyleD.setFont(headerFont);
			Row headerRowD = sheetD.createRow(0);
			for (int col = 0; col < DetailsColumns.length; col++) {
				Cell cell = headerRowD.createCell(col);
				cell.setCellValue(DetailsColumns[col]);
				cell.setCellStyle(headerCellStyleD);
			}
			int rowIdxD = 1;
			for (WarrantyPcrDetailsExcelResponse responseDetails : details) {
				Row row = sheetD.createRow(rowIdxD++);
				row.createCell(0).setCellValue(responseDetails.getWCRNumber());
				row.createCell(1).setCellValue(responseDetails.getType());
				row.createCell(2).setCellValue(responseDetails.getCode());
				row.createCell(3).setCellValue(responseDetails.getDescription());
				row.createCell(4).setCellValue(responseDetails.getCategoryCodeFRSHours());
				row.createCell(5).setCellValue(responseDetails.getSerialNumberComplaintDescription());
				row.createCell(6).setCellValue(responseDetails.getGear_ratio());
				row.createCell(7).setCellValue(responseDetails.getEngine_rpm());
				row.createCell(8).setCellValue(responseDetails.getClaimQuantity());
				row.createCell(9).setCellValue(responseDetails.getApprovedAmount());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream imbExcelReport(List<InstallationMonitoringBoardResponseDto> records)
			throws IOException {
		String[] Columns = { "Zone", "Area / State", "RSM/ASM Name", "TM Name", "Sold Dealer Code",
				"Sold Dealership Name", "Product Group", "Product", "Model", "Sub Model", "Chassis No", "Engine No",
				"Delivery Challan Date", "Delivery Installation Due Date", "Delivery Installation Date",
				"Delivery Installation Number", "Delivery Installation Status", "Field InstallationDue Date",
				"Field Installation Date", "Field Installation Number", "Field Installation Status", "Customer Name",
				"Mobile Number", "Alternate Mobile No", "Customer Full Address" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("INSTALLATION MONITORING BOARD - MAINTENANCE [IMB]");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (InstallationMonitoringBoardResponseDto job : records) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(job.getZone());
				row.createCell(1).setCellValue(job.getArea());
				row.createCell(2).setCellValue(job.getAreaManger());
				row.createCell(3).setCellValue(job.getTerritoryManager());
				row.createCell(4).setCellValue(job.getSoldDealerCode());
				row.createCell(5).setCellValue(job.getSoldDealershipName());
				row.createCell(6).setCellValue(job.getProductGroup());
				row.createCell(7).setCellValue(job.getProduct());
				row.createCell(8).setCellValue(job.getModel());
				row.createCell(9).setCellValue(job.getSubModel());
				row.createCell(10).setCellValue(job.getChassisNo());
				row.createCell(11).setCellValue(job.getEngineNo());
				row.createCell(12).setCellValue(job.getDeliveryChallanDate());
				row.createCell(13).setCellValue(job.getDeliveryInstallationDueDate());
				row.createCell(14).setCellValue(job.getDeliveryInstallationDate());
				row.createCell(15).setCellValue(job.getDeliveryInstallationNumber());
				row.createCell(16).setCellValue(job.getDeliveryInstallationStatus());
				row.createCell(17).setCellValue(job.getFieldInstallationDueDate());
				row.createCell(18).setCellValue(job.getFieldInstallationDate());
				row.createCell(19).setCellValue(job.getFieldInstallationNumber());
				row.createCell(20).setCellValue(job.getFieldInstallationStatus());
				row.createCell(21).setCellValue(job.getCustomerName());
				row.createCell(22).setCellValue(job.getMobileNumber());
				row.createCell(23).setCellValue(job.getAlternateNo());
				row.createCell(24).setCellValue(job.getFullCustAddress());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream smbExcelReport(List<ServiceMonitoringBoardResponseDto> records)
			throws IOException {
		String[] Columns = { "Zone", "Area / State", "RSM/ASM Name", "TM Name", "Sold Dealer Code",
				"Sold Dealership Name", "Product Group", "Product", "Model", "Sub Model", "Chassis No", "Engine No",
				"Customer Name", "Mobile Number", "Alternate Mobile No", "Customer Full Address",
				"Previous Service Type", "Previous Job Card Date ( Machine Out Date )", "Previous Hours",
				"Current Service Due Type", "Current Service Due Date", "Service Booking Date", "Booking Number",
				"Service Completed Date" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("SERVICE MONITORING BOARD - MAINTENANCE [IMB]");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (ServiceMonitoringBoardResponseDto job : records) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(job.getZone());
				row.createCell(1).setCellValue(job.getArea());
				row.createCell(2).setCellValue(job.getAreaManger());
				row.createCell(3).setCellValue(job.getTerritoryManager());
				row.createCell(4).setCellValue(job.getSoldDealerCode());
				row.createCell(5).setCellValue(job.getSoldDealershipName());
				row.createCell(6).setCellValue(job.getProductGroup());
				row.createCell(7).setCellValue(job.getProduct());
				row.createCell(8).setCellValue(job.getModel());
				row.createCell(9).setCellValue(job.getSubModel());
				row.createCell(10).setCellValue(job.getChassisNo());
				row.createCell(11).setCellValue(job.getEngineNo());
				row.createCell(12).setCellValue(job.getCustomerName());
				row.createCell(13).setCellValue(job.getMobileNumber());
				row.createCell(14).setCellValue(job.getAlternateNo());
				row.createCell(15).setCellValue(job.getFullCustAddress());
				row.createCell(16).setCellValue(job.getPreviuosServiceType());
				row.createCell(17).setCellValue(job.getPreviousJobCardDate());
				row.createCell(18).setCellValue(job.getPreviousHours());
				row.createCell(19).setCellValue(job.getCusrrentServiceDueType());
				row.createCell(20).setCellValue(job.getCurrentServiceDueDate());
				row.createCell(21).setCellValue(job.getServiceBookingDate());
				row.createCell(22).setCellValue(job.getBookingNumber());
				row.createCell(23).setCellValue(job.getServiceCompleteDate());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream fillRatioExcelReport(List<FillRatioReportResponseDto> records,
			List<FillRatioItemReportResponseDto> itemrecords) throws IOException {
		String[] Columns = { "Dealer Name", "Doc Type", "Doc Number", "Doc Date", "Line Items",
				"Line Items Issued/ Sold", "Fully Completed", "Partially Completed", "Fill Rate Full",
				"Fill Rate Partial", "Remarks" };
		String[] Columns2 = { "Doc Type", "Doc Number", "Item Number", "Total Qty", "Issue/Sold Qty", "Back Qty" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Fill_Ratio_Report");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			Integer totalLineItems = 0;
			Integer totalIssueItems = 0;
			Integer totalFullCompleted = 0;
			Integer totalPartialComplete = 0;
			for (FillRatioReportResponseDto job : records) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(job.getDealerName());
				row.createCell(1).setCellValue(job.getDocType());
				row.createCell(2).setCellValue(job.getDocNumber());
				row.createCell(3).setCellValue(job.getDocDate());
				row.createCell(4).setCellValue(job.getRequesitionLineItemsCount());
				row.createCell(5).setCellValue(job.getIssuedLintItemsCount());
				row.createCell(6).setCellValue(job.getFullyCompleted());
				row.createCell(7).setCellValue(job.getPartiallyCompleted());
				row.createCell(8).setCellValue(job.getFillRateFull());
				row.createCell(9).setCellValue(job.getFillRatePartial());
				row.createCell(10).setCellValue(job.getRemarks());

				totalLineItems = totalLineItems + job.getRequesitionLineItemsCount();
				totalIssueItems = totalIssueItems + job.getIssuedLintItemsCount();
				totalFullCompleted = totalFullCompleted + job.getFullyCompleted();
				totalPartialComplete = totalPartialComplete + job.getPartiallyCompleted();
			}
			Row row = sheet.createRow(rowIdx++);
			row.createCell(0).setCellValue("");
			row.createCell(1).setCellValue("");
			row.createCell(2).setCellValue("");
			row.createCell(3).setCellValue("Total");
			row.createCell(4).setCellValue(totalLineItems);
			row.createCell(5).setCellValue(totalIssueItems);
			row.createCell(6).setCellValue(totalFullCompleted);
			row.createCell(7).setCellValue(totalPartialComplete);
			if (totalLineItems > 0) {
				row.createCell(8).setCellValue((totalFullCompleted * 100) / totalLineItems);
				row.createCell(9).setCellValue((totalIssueItems * 100) / totalLineItems);
			} else {
				row.createCell(8).setCellValue(0);
				row.createCell(9).setCellValue(0);
			}
			row.createCell(10).setCellValue("");

			Sheet sheet2 = workbook.createSheet("Item_Details");
			Row headerRow2 = sheet2.createRow(0);
			rowIdx = 1;
			for (int col = 0; col < Columns2.length; col++) {
				Cell cell = headerRow2.createCell(col);
				cell.setCellValue(Columns2[col]);
				cell.setCellStyle(headerCellStyle);
			}
			for (FillRatioItemReportResponseDto job : itemrecords) {
				Row row2 = sheet2.createRow(rowIdx++);
				row2.createCell(0).setCellValue(job.getDocType());
				row2.createCell(1).setCellValue(job.getDocNumber());
				row2.createCell(2).setCellValue(job.getItemNumber());
				row2.createCell(3).setCellValue(job.getTotalQty());
				row2.createCell(4).setCellValue(job.getIssueSoldQty());
				row2.createCell(5).setCellValue(job.getBackQty());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream salesPoExcelReport(List<SalesPoExcelResponse> responseSearchExcel)
			throws IOException {
		String[] Columns = { "Zone", "Area", "Dealer Code", "Dealer Name", "Dealer Category", "State", "PO Number",
				"PO Date", "Depot", "Model", "Item No", "Quantity", "Item Description", "PO Type" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Sales Po");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (SalesPoExcelResponse ex : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getZone());
				row.createCell(1).setCellValue(ex.getArea());
				row.createCell(2).setCellValue(ex.getDealerCode());
				row.createCell(3).setCellValue(ex.getDealerName());
				row.createCell(4).setCellValue(ex.getDealerCategory());
				row.createCell(5).setCellValue(ex.getDealerState());
				row.createCell(6).setCellValue(ex.getPoNumber());
				row.createCell(7).setCellValue(ex.getPoDate());
				row.createCell(8).setCellValue(ex.getDepot());
				row.createCell(9).setCellValue(ex.getModel());
				row.createCell(10).setCellValue(ex.getItemNo());
				row.createCell(11).setCellValue(ex.getQuantity());
				row.createCell(12).setCellValue(ex.getItemDescription());
				row.createCell(13).setCellValue(ex.getPoType());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream surveyDetailsExcelReportDisatisfied(
			List<SurveySummaryReportDissatisfiedResponse> surveyExcel) throws IOException {
		String[] Columns = { "Survey No", "Product Group", "Product", "Model", "Sub Model", "Chassis No", "Engine No",
				"Customer Name", "Mobile Number", "Alternate Mobile No", "Customer State", "Dealer Code", "Dealer Name",
				"Dealer State", "Survey Type", "Date Of Installation", "DC Date", "Survey Date", "Survey Due Date",
				"Survey Comments", "Survey Status", "Satisfication Level", "Survey Done By", "Survey Done Date",
				"Action taken by", "Isssue Closed By", "Issue Closed On", "Department", "TM Name", "RSM Name", "Zone" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Survey Details");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (SurveySummaryReportDissatisfiedResponse ex : surveyExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getSurveyNumber());
				row.createCell(1).setCellValue(ex.getProductGroup());
				row.createCell(2).setCellValue(ex.getProduct());
				row.createCell(3).setCellValue(ex.getModel());
				row.createCell(4).setCellValue(ex.getSubModel());
				row.createCell(5).setCellValue(ex.getChassisNo());
				row.createCell(6).setCellValue(ex.getEngineNo());
				row.createCell(7).setCellValue(ex.getCustomerName());
				row.createCell(8).setCellValue(ex.getCustomerNumber());
				row.createCell(9).setCellValue(ex.getCustomerAlternetNumber());
				row.createCell(10).setCellValue(ex.getState());
				row.createCell(11).setCellValue(ex.getDealerCode());
				row.createCell(12).setCellValue(ex.getDealerName());
				row.createCell(13).setCellValue(ex.getDealerState());
				row.createCell(14).setCellValue(ex.getSurveyType());
				row.createCell(15).setCellValue(ex.getDateOfInstallation());
				row.createCell(16).setCellValue(ex.getDcDate());
				row.createCell(17).setCellValue(ex.getSurveyDate());
				row.createCell(18).setCellValue(ex.getSurveyDueDate());
				row.createCell(19).setCellValue(ex.getSurveyComments());
				row.createCell(20).setCellValue(ex.getSurveyStatus());
				row.createCell(21).setCellValue(ex.getCustomerSatisfied());
				row.createCell(22).setCellValue(ex.getSurveyDoneBy());
				row.createCell(23).setCellValue(ex.getSurveyDoneDate());
				row.createCell(24).setCellValue(ex.getActionTakenBy());
				row.createCell(25).setCellValue(ex.getIssueClosedBy());
				row.createCell(26).setCellValue(ex.getIssueClosedOn());
				row.createCell(27).setCellValue(ex.getDepartment());
				row.createCell(28).setCellValue(ex.getTmName());
				row.createCell(29).setCellValue(ex.getRsmName());
				row.createCell(30).setCellValue(ex.getzone());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream surveyDetailsExcelReportWith(
			List<SurveySummaryReportWithQuationariesResponse> surveyExcel) throws IOException {
		String[] Columns = { "Survey No", "Product Group", "Product", "Model", "Sub Model", "Chassis No", "Engine No",
				"Customer Name", "Mobile Number", "Alternate Mobile No", "Customer State", "Dealer Code", "Dealer Name",
				"Dealer State", "Survey Type", "Date Of Installation", "DC Date", "Survey Date", "Survey Due Date",
				"Survey Comments", "Question", "Main Answer", "Sub Answer", "Survey Status", "Satisfication Level",
				"Survey Done By", "Survey Done Date", "Isssue Closed By", "Issue Closed On" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Survey Details");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (SurveySummaryReportWithQuationariesResponse ex : surveyExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getSurveyNumber());
				row.createCell(1).setCellValue(ex.getProductGroup());
				row.createCell(2).setCellValue(ex.getProduct());
				row.createCell(3).setCellValue(ex.getModel());
				row.createCell(4).setCellValue(ex.getSubModel());
				row.createCell(5).setCellValue(ex.getChassisNo());
				row.createCell(6).setCellValue(ex.getEngineNo());
				row.createCell(7).setCellValue(ex.getCustomerName());
				row.createCell(8).setCellValue(ex.getCustomerNumber());
				row.createCell(9).setCellValue(ex.getCustomerAlternetNumber());
				row.createCell(10).setCellValue(ex.getState());
				row.createCell(11).setCellValue(ex.getDealerCode());
				row.createCell(12).setCellValue(ex.getDealerName());
				row.createCell(13).setCellValue(ex.getDealerState());
				row.createCell(14).setCellValue(ex.getSurveyType());
				row.createCell(15).setCellValue(ex.getDateOfInstallation());
				row.createCell(16).setCellValue(ex.getDcDate());
				row.createCell(17).setCellValue(ex.getSurveyDate());
				row.createCell(18).setCellValue(ex.getSurveyDueDate());
				row.createCell(19).setCellValue(ex.getSurveyComments());
				row.createCell(20).setCellValue(ex.getQuestion());
				row.createCell(21).setCellValue(ex.getMainAnswer());
				row.createCell(22).setCellValue(ex.getSubAnswer());
				row.createCell(23).setCellValue(ex.getSurveyStatus());
				row.createCell(24).setCellValue(ex.getCustomerSatisfied());
				row.createCell(25).setCellValue(ex.getSurveyDoneBy());
				row.createCell(26).setCellValue(ex.getSurveyDoneDate());
				row.createCell(27).setCellValue(ex.getIssueClosedBy());
				row.createCell(28).setCellValue(ex.getIssueClosedOn());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream surveyDetailsExcelReport(List<SurveySummaryReportResponse> surveyExcel)
			throws IOException {
		String[] Columns = { "Survey No", "Product Group", "Product", "Model", "Sub Model", "Chassis No", "Engine No",
				"Customer Name", "Mobile Number", "Alternate Mobile No", "Customer State", "Dealer Code", "Dealer Name",
				"Dealer State", "Survey Type", "Date Of Installation", "DC Date", "Survey Date", "Survey Due Date",
				"Survey Comments", "Survey Status", "Satisfication Level", "Survey Done By", "Survey Done Date",
				"Isssue Closed By", "Issue Closed On" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Survey Details");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (SurveySummaryReportResponse ex : surveyExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getSurveyNumber());
				row.createCell(1).setCellValue(ex.getProductGroup());
				row.createCell(2).setCellValue(ex.getProduct());
				row.createCell(3).setCellValue(ex.getModel());
				row.createCell(4).setCellValue(ex.getSubModel());
				row.createCell(5).setCellValue(ex.getChassisNo());
				row.createCell(6).setCellValue(ex.getEngineNo());
				row.createCell(7).setCellValue(ex.getCustomerName());
				row.createCell(8).setCellValue(ex.getCustomerNumber());
				row.createCell(9).setCellValue(ex.getCustomerAlternetNumber());
				row.createCell(10).setCellValue(ex.getState());
				row.createCell(11).setCellValue(ex.getDealerCode());
				row.createCell(12).setCellValue(ex.getDealerName());
				row.createCell(13).setCellValue(ex.getDealerState());
				row.createCell(14).setCellValue(ex.getSurveyType());
				row.createCell(15).setCellValue(ex.getDateOfInstallation());
				row.createCell(16).setCellValue(ex.getDcDate());
				row.createCell(17).setCellValue(ex.getSurveyDate());
				row.createCell(18).setCellValue(ex.getSurveyDueDate());
				row.createCell(19).setCellValue(ex.getSurveyComments());
				row.createCell(20).setCellValue(ex.getSurveyStatus());
				row.createCell(21).setCellValue(ex.getCustomerSatisfied());
				row.createCell(22).setCellValue(ex.getSurveyDoneBy());
				row.createCell(23).setCellValue(ex.getSurveyDoneDate());
				row.createCell(24).setCellValue(ex.getIssueClosedBy());
				row.createCell(25).setCellValue(ex.getIssueClosedOn());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream surveyQaExcelReport(List<QAReportResponse> qaExcel) throws IOException {
		String[] Columns = { "Product Group", "Product", "Model", "Question", "Answer", "Sub Answer",
				"No Of Times answered", "% Of Answer" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Survey Summary QA Report");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (QAReportResponse ex : qaExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getProductGroup());
				row.createCell(1).setCellValue(ex.getProduct());
				row.createCell(2).setCellValue(ex.getProduct());
				row.createCell(3).setCellValue(ex.getModel());
				row.createCell(4).setCellValue(ex.getQuestion());
				row.createCell(5).setCellValue(ex.getAnswer());
				row.createCell(6).setCellValue(ex.getSubAnswer());
				row.createCell(7).setCellValue(ex.getNoOfTimeAnswered());
				row.createCell(8).setCellValue(ex.getPercentageOfAnswer());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream machineMasterReport(List<MachineMasterReportResponse> mmrExcel)
			throws IOException {
		String[] Columns = { "Product Group", "Product", "Series", "Model", "Sub Model", "Varient", "Item Code",
				"Description" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("MM_Report");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (MachineMasterReportResponse ex : mmrExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getProductGroup());
				row.createCell(1).setCellValue(ex.getProduct());
				row.createCell(2).setCellValue(ex.getProduct());
				row.createCell(3).setCellValue(ex.getSeries());
				row.createCell(4).setCellValue(ex.getModel());
				row.createCell(5).setCellValue(ex.getSubModel());
				row.createCell(6).setCellValue(ex.getVariant());
				row.createCell(7).setCellValue(ex.getItemCode());
				row.createCell(8).setCellValue(ex.getDescription());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	// Suraj-07/11/2022
	public static ByteArrayInputStream serviceClaimExcelReport(List<ServiceClaimSummeryExcelReport> claimSummery,
			List<ServiceClaimDetailExcelReport> claimDetail) throws IOException {
		String[] summeryColumn = { "Dealer Code", "Dealer Name", "Dealer State", "Claim No", "Claim Date",
				"Claim Status", "From Date", "To Date", "Claim Amount", "Bonus Amount", "Total Claim Amount",
				"Total ApprovedAmount", "Rsn Number", "Rsn Date", "Last Approved By" };

		String[] detailColumn = { "Claim No", "Document No", "Document Date", "Customer Name", "Customer Code",
				"Chassis No", "Model", "Type of Service", "Date of Installation", "Amount", "Bonus Amount",
				"Approved Amount", "Document Type", "Remark" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			// -------------------------------------Summary
			// View-----------------------------------
			Sheet sheet = workbook.createSheet("Claim Header Report");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < summeryColumn.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(summeryColumn[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;

			for (ServiceClaimSummeryExcelReport claimSummeryExcelReport : claimSummery) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(claimSummeryExcelReport.getDealerCode());
				row.createCell(1).setCellValue(claimSummeryExcelReport.getDealerName());
				row.createCell(2).setCellValue(claimSummeryExcelReport.getDealerState());
				row.createCell(3).setCellValue(claimSummeryExcelReport.getClaimNo());
				row.createCell(4).setCellValue(claimSummeryExcelReport.getClaimDate());
				row.createCell(5).setCellValue(claimSummeryExcelReport.getClaimStatus());
				row.createCell(6).setCellValue(claimSummeryExcelReport.getFromDate());
				row.createCell(7).setCellValue(claimSummeryExcelReport.getToDate());
				row.createCell(8).setCellValue(claimSummeryExcelReport.getClaimAmount());
				row.createCell(9).setCellValue(claimSummeryExcelReport.getBonusAmount());
				row.createCell(10).setCellValue(claimSummeryExcelReport.getTotalClaimAmount());
				row.createCell(11).setCellValue(claimSummeryExcelReport.getTotalApprovedAmount());
				row.createCell(12).setCellValue(claimSummeryExcelReport.getRsnNumber());
				row.createCell(13).setCellValue(claimSummeryExcelReport.getRsnDate());
				row.createCell(14).setCellValue(claimSummeryExcelReport.getLastApprovedBy());
			}

			// -------------------------------------Details
			// View-----------------------------------
			Sheet sheetD = workbook.createSheet("Claim Detail Report");
			Font headerFontD = workbook.createFont();
			headerFontD.setBold(true);
			CellStyle headerCellStyleD = workbook.createCellStyle();
			headerCellStyleD.setFont(headerFont);
			Row headerRowD = sheetD.createRow(0);
			for (int col = 0; col < detailColumn.length; col++) {
				Cell cell = headerRowD.createCell(col);
				cell.setCellValue(detailColumn[col]);
				cell.setCellStyle(headerCellStyleD);
			}
			int rowIdxD = 1;
			for (ServiceClaimDetailExcelReport claimDetailExcelReport : claimDetail) {
				Row row = sheetD.createRow(rowIdxD++);
				row.createCell(0).setCellValue(claimDetailExcelReport.getClaimNo());
				row.createCell(1).setCellValue(claimDetailExcelReport.getDocumentNo());
				row.createCell(2).setCellValue(claimDetailExcelReport.getDocumentDate());
				row.createCell(3).setCellValue(claimDetailExcelReport.getCustomerName());
				row.createCell(4).setCellValue(claimDetailExcelReport.getCustomerCode());
				row.createCell(5).setCellValue(claimDetailExcelReport.getChassisNo());
				row.createCell(6).setCellValue(claimDetailExcelReport.getModel());
				row.createCell(7).setCellValue(claimDetailExcelReport.getTypeOfService());
				row.createCell(8).setCellValue(claimDetailExcelReport.getDateofInstallation());
				row.createCell(9).setCellValue(claimDetailExcelReport.getAmount());
				row.createCell(10).setCellValue(claimDetailExcelReport.getBonusAmount());
				row.createCell(11).setCellValue(claimDetailExcelReport.getApprovedAmount());
				row.createCell(12).setCellValue(claimDetailExcelReport.getDocumentType());
				row.createCell(13).setCellValue(claimDetailExcelReport.getRemark());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	// Suraj--09/11/2022
	public static ByteArrayInputStream salesPoExcelReport1(List<ResponsePoDto> responseSearchExcel) throws IOException {
		String[] Columns = { "PO Number", "Last Approved By", "PO Date", "PO Type", "Dealer Code", "Dealer Name",
				"Dealer State", "Depot", "Total Quantity", "PO Status", "Total Credit Limit", "Available Limit" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Sales Po");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (ResponsePoDto ex : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getPoNumber());
				row.createCell(1).setCellValue(ex.getLastApprovedBy());
				row.createCell(2).setCellValue(ex.getPoDate());
				row.createCell(3).setCellValue(ex.getPoType());
				row.createCell(4).setCellValue(ex.getDealerCode());
				row.createCell(5).setCellValue(ex.getDealerName());
				row.createCell(6).setCellValue(ex.getDealerState());
				row.createCell(7).setCellValue(ex.getDepot());
				row.createCell(8).setCellValue(ex.getTotalQuantity());
				row.createCell(9).setCellValue(ex.getPoStatus());
				row.createCell(10).setCellValue(ex.getTotalCreditLimit());
				row.createCell(11).setCellValue(ex.getAvailableLimit());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	public static ByteArrayInputStream marketingClaimReport(List<MarketingClaimReportResponse> responseSearchExcel) throws IOException {
		String[] Columns = { "Claim Number",	
				"Claim Status",	
				"Claim Date",	
				"Activity Number",	
				"Activity Status",	
				"Activity Creation Date",
				"Activity Type",	
				"NoOfDays",
				"From Activity Date",	
				"To Activity Date",	
				"Actual From Activity Date",	
				"Actual To Activity Date",	
				"Location",	
				"Hot Enquiries",	
				"Warm Enquiries",
				"Cold Enquiries",	
				"Cost Per Enquiry",	
				"Cost Per Hot Enquiry",	
				"Total Bookings",	
				"Cost Per Booking",	
				"Total Retails",	
				"Cost Per Retail",	
				"Activity Effectiveness",
				"Approved Amount",	
				"Actual Claim Amount",	
				"Last Approved By",
				"Dealer Code",
				"Dealer Name",	
				"Dealer State" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Marketing Claim");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (MarketingClaimReportResponse ex : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getClaimNumber());
				row.createCell(1).setCellValue(ex.getClaimStatus());
				row.createCell(2).setCellValue(ex.getClaimDate());
				row.createCell(3).setCellValue(ex.getActivityNumber());
				row.createCell(4).setCellValue(ex.getActivityStatus());
				row.createCell(5).setCellValue(ex.getActivityCreationDate());
				row.createCell(6).setCellValue(ex.getActivityType());
				row.createCell(7).setCellValue(ex.getNoOfDays());
				row.createCell(8).setCellValue(ex.getFromActivityDate());
				row.createCell(9).setCellValue(ex.getToActivityDate());
				row.createCell(10).setCellValue(ex.getActualFromActivityDate());
				row.createCell(11).setCellValue(ex.getActualToActivityDate());
				row.createCell(12).setCellValue(ex.getLocation());
				row.createCell(13).setCellValue(ex.getHotEnquiries());
				row.createCell(14).setCellValue(ex.getWarmEnquiries());
				row.createCell(15).setCellValue(ex.getColdEnquiries());
				row.createCell(16).setCellValue(ex.getCostPerEnquiry());
				row.createCell(17).setCellValue(ex.getCostPerHotEnquiry().doubleValue());
				row.createCell(18).setCellValue(ex.getTotalBookings());
				row.createCell(19).setCellValue(ex.getCostPerBooking().doubleValue());
				row.createCell(20).setCellValue(ex.getTotalRetails());
				row.createCell(21).setCellValue(ex.getCostPerRetail().doubleValue());
				row.createCell(22).setCellValue(ex.getActivityEffectiveness());
				row.createCell(23).setCellValue(ex.getApprovedAmount().doubleValue());
				row.createCell(24).setCellValue(ex.getActualClaimAmount().doubleValue());
				row.createCell(25).setCellValue(ex.getLastApprovedBy());
				row.createCell(26).setCellValue(ex.getDealerCode());
				row.createCell(27).setCellValue(ex.getDealerName());
				row.createCell(28).setCellValue(ex.getDealerState());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	public static ByteArrayInputStream activityProposalStatusReport(List<ActivityPropoalStatusReportResponse> responseSearchExcel) throws IOException {
		String[] Columns = { "state",
				"dealerCode",
				"dealerName",	
				"pendingProposalLastMonth",	
				"proposalReceived",	
				"proposalRejected",	
				"proposalHold",	
				"proposalApproved",
				"pendingForApproval" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Activity Proposal Status");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (ActivityPropoalStatusReportResponse ex : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getState());
				row.createCell(1).setCellValue(ex.getDealerCode());
				row.createCell(2).setCellValue(ex.getDealerName());
				row.createCell(3).setCellValue(ex.getPendingProposalLastMonth());
				row.createCell(4).setCellValue(ex.getProposalReceived());
				row.createCell(5).setCellValue(ex.getProposalRejected());
				row.createCell(6).setCellValue(ex.getProposalHold());
				row.createCell(7).setCellValue(ex.getProposalApproved());
				row.createCell(8).setCellValue(ex.getPendingForApproval());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	public static ByteArrayInputStream activityClaimStatusReport(List<ActivityClaimStatusReportResponse> responseSearchExcel) throws IOException {
		String[] Columns = { "State",
				"Dealer Code",
				"Dealer Name",	
				"Claim Uploaded",	
				"Claim Approved",	
				"Claim Rejected",	
				"Pending For Approval",	
				"Agening < 30",
				"Ageing 31 To 45",	
				"dhApproval",
				"Completed",	
				"Pending" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Activity Claim Status");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (ActivityClaimStatusReportResponse ex : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getState());
				row.createCell(1).setCellValue(ex.getDealerCode());
				row.createCell(2).setCellValue(ex.getDealerName());
				row.createCell(3).setCellValue(ex.getClaimUploaded());
				row.createCell(4).setCellValue(ex.getClaimApproved());
				row.createCell(5).setCellValue(ex.getClaimRejected());
				row.createCell(6).setCellValue(ex.getPendingForApproval());
				row.createCell(7).setCellValue(ex.getAgeingLessThan30());
				row.createCell(8).setCellValue(ex.getAgeingLessThan31To45());
				row.createCell(9).setCellValue(ex.getDhApproval());
				row.createCell(10).setCellValue(ex.getCompleted());
				row.createCell(11).setCellValue(ex.getPending());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream activityReportStatusReport(List<ActivityReportStatusReportReponse> responseSearchExcel) throws IOException {
		String[] Columns = { "State",
				"Dealer Code",
				"Dealer Name",	
				"Approved Proposal Till Date",	
				"Report Uploded Till Date",	
				"Report Pending",	
				"Contact Received",	
				"HP Received",
				"Booking Received",	
				"Retail Done",
				"Deferred",	
				"Lost",	
				"Open Contact",	
				"Open Hp" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Activity Report Status");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (ActivityReportStatusReportReponse ex : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getState());
				row.createCell(1).setCellValue(ex.getDealerCode());
				row.createCell(2).setCellValue(ex.getDealerName());
				row.createCell(3).setCellValue(ex.getApprovedProposalTillDate());
				row.createCell(4).setCellValue(ex.getReportUplodedTillDate());
				row.createCell(5).setCellValue(ex.getReportPending());
				row.createCell(6).setCellValue(ex.getContactReceived());
				row.createCell(7).setCellValue(ex.getHpReceived());
				row.createCell(8).setCellValue(ex.getBookingReceived());
				row.createCell(9).setCellValue(ex.getRetailDone());
				row.createCell(10).setCellValue(ex.getDeferred());
				row.createCell(11).setCellValue(ex.getLost());
				row.createCell(12).setCellValue(ex.getOpenContact());
				row.createCell(13).setCellValue(ex.getOpenHp());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream acivityEnquiryReport(List<ActivityEnquiryReportResponse> responseSearchExcel) throws IOException {
		String[] Columns = { "Zone",	
				"Area",	
				"DealerCode",	
				"DealerName",	
				"DealerState",	
				"ActivityNumber",	
				"AcitivityStatus",
				"ApprovedAmount",	
				"ApprovedMonth",	
				"TotalEnquires",	
				"HotEnquires",	
				"WarmEnquires",	
				"ColdEnquires",	
				"CostperEnquiry",	
				"CostPerHotEnquiry",	
				"TotalBookings",	
				"CostPerBooking",	
				"TotalRetails",	
				"CostPerRetail" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Activity Enquiry");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (ActivityEnquiryReportResponse ex : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getZone());
				row.createCell(1).setCellValue(ex.getArea());
				row.createCell(2).setCellValue(ex.getDealerCode());
				row.createCell(3).setCellValue(ex.getDealerName());
				row.createCell(4).setCellValue(ex.getDealerState());
				row.createCell(5).setCellValue(ex.getActivityNumber());
				row.createCell(6).setCellValue(ex.getAcitivityStatus());
				row.createCell(7).setCellValue(ex.getApprovedAmount().doubleValue());
				row.createCell(8).setCellValue(ex.getApprovedMonth());
				row.createCell(9).setCellValue(ex.getTotalEnquires());
				row.createCell(10).setCellValue(ex.getHotEnquires());
				row.createCell(11).setCellValue(ex.getWarmEnquires());
				row.createCell(12).setCellValue(ex.getColdEnquires());
				row.createCell(13).setCellValue(ex.getCostperEnquiry().doubleValue());
				row.createCell(14).setCellValue(ex.getCostPerHotEnquiry().doubleValue());
				row.createCell(15).setCellValue(ex.getTotalBookings());
				row.createCell(16).setCellValue(ex.getCostPerBooking().doubleValue());
				row.createCell(17).setCellValue(ex.getTotalRetails());
				row.createCell(18).setCellValue(ex.getCostPerRetail().doubleValue());
				
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	public static ByteArrayInputStream enquiryReport(List<EnquiryReportSearchResponse> responseSearchExcel) throws IOException {
		String[] Columns = { "Zone",	
				"Area",	
				"DealerCode",	
				"DealerName",	
				"EnquiryNumber",	
				"EnquiryDate",	
				"ValidationDate",
				"GenActivityType",
				"SalesPersonName",	
				"EnquiryType",
				"EnquiryStatus",	
				"NextFupDate",	
				"TentativeDateOfPurchase",	
				"Exchange",	
				"ExchangeAssetDetails",	
				"CustomerName",	
				"FatherName",	
				"MobileNo",	
				"Address",
				"City",
				"District",	
				"Pincode",	
				"State",	
				"ChassisNo",	
				"EngineNo",	
				"Model",	
				"SubModel",	
				"Variant",	
				"AssetValue",
				"FinanceType",		
				"FinancierName",	
				"Finance", 
				"LoginDate",	
				"EstimatedLoanAmount",
				"FinanceStatus",	
				"Subsidy",	
				"MarginAmount",	
				"TotalAmountRecd" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Enquiry Report");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (EnquiryReportSearchResponse ex : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getZone());
				row.createCell(1).setCellValue(ex.getArea());
				row.createCell(2).setCellValue(ex.getDealerCode());
				row.createCell(3).setCellValue(ex.getDealerName());
				row.createCell(4).setCellValue(ex.getEnquiryNumber());
				row.createCell(5).setCellValue(ex.getEnquiryDate());
				row.createCell(6).setCellValue(ex.getValidationDate());
				row.createCell(7).setCellValue(ex.getGenActivityType());
				row.createCell(8).setCellValue(ex.getSalesPersonName());
				row.createCell(9).setCellValue(ex.getEnquiryType());
				row.createCell(10).setCellValue(ex.getEnquiryStatus());
				row.createCell(11).setCellValue(ex.getNextFupDate());
				row.createCell(12).setCellValue(ex.getTentativeDateOfPurchase());
				row.createCell(13).setCellValue(ex.getExchange());
				row.createCell(14).setCellValue(ex.getExchangeAssetDetails());
				row.createCell(15).setCellValue(ex.getCustomerName());
				row.createCell(16).setCellValue(ex.getFatherName());
				row.createCell(17).setCellValue(ex.getMobileNo());
				row.createCell(18).setCellValue(ex.getAddress());
				row.createCell(19).setCellValue(ex.getCity());
				row.createCell(20).setCellValue(ex.getDistrict());
				row.createCell(21).setCellValue(ex.getPincode());
				row.createCell(22).setCellValue(ex.getState());
				row.createCell(23).setCellValue(ex.getChassisNo());
				row.createCell(24).setCellValue(ex.getEngineNo());
				row.createCell(25).setCellValue(ex.getModel());
				row.createCell(26).setCellValue(ex.getSubModel());
				row.createCell(27).setCellValue(ex.getVariant());
				row.createCell(28).setCellValue(ex.getAssetValue());
				row.createCell(29).setCellValue(ex.getFinanceType());
				row.createCell(30).setCellValue(ex.getFinancierName());
				row.createCell(31).setCellValue(ex.getFinance());
				row.createCell(32).setCellValue(ex.getLoginDate());
				row.createCell(33).setCellValue(ex.getEstimatedLoanAmount());
				row.createCell(34).setCellValue(ex.getFinanceStatus());
				row.createCell(35).setCellValue(ex.getSubsidy());
				row.createCell(36).setCellValue(ex.getMarginAmount());
				row.createCell(37).setCellValue(ex.getTotalAmountRecd());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	public static ByteArrayInputStream customerMachineMasterExcelReport(List<CustomerMachineMasterReportResponse> responseSearchExcel) throws IOException {
		String[] Columns = { "Dealer Code",
				"Dealer_Name",
				"Model_Code",
				"Status",
				"Machine_Serial_Number",
				"Engine_Number",
				"KAI_Invoice_Date",
				"Customer Name",
				"Address",
				"City",
				"Taluka",
				"District",
				"Customer Mobile Number",
				"CSB Book Number",
				"PDI_Date",
				"Delivery Challan No",		//Suraj--09/12/2022
				"Delivery Challan Date",		//Suraj--09/12/2022
				"Delivery Installation_Date",
				"Field Installation Date",
				"Warranty_End_Date",
				"Free_service1_Date",
				"Free_service1_Hour",
				"Service1_By",
				"Free_service2_Date",
				"Free_service2_Hour",
				"Service2_By",
				"Free_service3_Date",
				"Free_service3_Hour",
				"Service3_By",
				"Free_service4_Date",
				"Free_service4_Hour",
				"Service4_By",
				"Free_service5_Date",
				"Free_service5_Hour",
				"Service5_By",
				"Free_service6_Date",
				"Free_service6_Hour",
				"Service6_By",
				"Free_service7_Date",
				"Free_service7_Hour",
				"Service7_By",
				"Free_service8_Date",
				"Free_service8_Hour",
				"Service8_By",
				"Free_service9_Date",
				"Free_service9_Hour",
				"Service9_By",
				"Free_service10_Date",
				"Free_service10_Hour",
				"Service10_By"
 };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Customer Machine Report");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (CustomerMachineMasterReportResponse ex : responseSearchExcel) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(ex.getDealerCode());
				row.createCell(1).setCellValue(ex.getDealerName());
				row.createCell(2).setCellValue(ex.getModelCode());
				row.createCell(3).setCellValue(ex.getStatus());
				row.createCell(4).setCellValue(ex.getMachineSerialNumber());
				row.createCell(5).setCellValue(ex.getEngineNumber());
				row.createCell(6).setCellValue(ex.getKaiInvoiceDate());
				row.createCell(7).setCellValue(ex.getCustomerName());
				row.createCell(8).setCellValue(ex.getAddress());
				row.createCell(9).setCellValue(ex.getCity());
				row.createCell(10).setCellValue(ex.getTaluka());
				row.createCell(11).setCellValue(ex.getDistrict());
				row.createCell(12).setCellValue(ex.getCustomerMobileNumber());
				row.createCell(13).setCellValue(ex.getCsbBookNumber());
				row.createCell(14).setCellValue(ex.getPdiDate());
				row.createCell(15).setCellValue(ex.getDcNumber());		//Suraj--09/12/2022
				row.createCell(16).setCellValue(ex.getDcDate());		//Suraj--09/12/2022
				row.createCell(17).setCellValue(ex.getDeliveryInstallationDate());
				row.createCell(18).setCellValue(ex.getFieldInstallationDate());
				row.createCell(19).setCellValue(ex.getWarrantyEndDate());
				row.createCell(20).setCellValue(ex.getFreeservice1Date());
				row.createCell(21).setCellValue(ex.getFreeservice1Hour());
				row.createCell(22).setCellValue(ex.getService1By());
				row.createCell(23).setCellValue(ex.getFreeservice2Date());
				row.createCell(24).setCellValue(ex.getFreeservice2Hour());
				row.createCell(25).setCellValue(ex.getService2By());
				row.createCell(26).setCellValue(ex.getFreeservice3Date());
				row.createCell(27).setCellValue(ex.getFreeservice3Hour());
				row.createCell(28).setCellValue(ex.getService3By());
				row.createCell(29).setCellValue(ex.getFreeservice4Date());
				row.createCell(30).setCellValue(ex.getFreeservice4Hour());
				row.createCell(31).setCellValue(ex.getService4By());
				row.createCell(32).setCellValue(ex.getFreeservice5Date());
				row.createCell(33).setCellValue(ex.getFreeservice5Hour());
				row.createCell(34).setCellValue(ex.getService5By());
				row.createCell(35).setCellValue(ex.getFreeservice6Date());
				row.createCell(36).setCellValue(ex.getFreeservice6Hour());
				row.createCell(37).setCellValue(ex.getService6By());
				row.createCell(38).setCellValue(ex.getFreeservice7Date());
				row.createCell(39).setCellValue(ex.getFreeservice7Hour());
				row.createCell(40).setCellValue(ex.getService7By());
				row.createCell(41).setCellValue(ex.getFreeservice8Date());
				row.createCell(42).setCellValue(ex.getFreeservice8Hour());
				row.createCell(43).setCellValue(ex.getService8By());
				row.createCell(44).setCellValue(ex.getFreeservice9Date());
				row.createCell(45).setCellValue(ex.getFreeservice9Hour());
				row.createCell(46).setCellValue(ex.getService9By());
				row.createCell(47).setCellValue(ex.getFreeservice10Date());
				row.createCell(48).setCellValue(ex.getFreeservice10Hour());
				row.createCell(49).setCellValue(ex.getService10By());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	
	
	public static ByteArrayInputStream mfcsExcelReport(List<MonthlyFailureCodeSummaryReportDto> records)
			throws IOException {
		String[] columns = { "failureCode", "description", "model", "year",
	            "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec",
	            "rejected", "grandTotal", "averageYear", "total",
	            "averageTotalPerc", "occurrencePercentage", "score", "severityScore"};

	    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
	        CreationHelper creationHelper = workbook.getCreationHelper();
	        Sheet sheet = workbook.createSheet("Report Data");
	        
	        Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	        
	        Row headerRow = sheet.createRow(0);
	        for (int col = 0; col < columns.length; col++) {
	            Cell cell = headerRow.createCell(col);
	            cell.setCellValue(columns[col]);
	            cell.setCellStyle(headerCellStyle);
	        }
	        
	        int rowIdx = 1;
	        for (MonthlyFailureCodeSummaryReportDto data : records) {
	            Row row = sheet.createRow(rowIdx++);
	            row.createCell(0).setCellValue(data.getFailureCode());
	            row.createCell(1).setCellValue(data.getComplaintDescription());
	            row.createCell(2).setCellValue(data.getModel());
	            row.createCell(3).setCellValue(data.getYear());
	            row.createCell(4).setCellValue(data.getJan());
	            row.createCell(5).setCellValue(data.getFeb());
	            row.createCell(6).setCellValue(data.getMar());
	            row.createCell(7).setCellValue(data.getApr());
	            row.createCell(8).setCellValue(data.getMay());
	            row.createCell(9).setCellValue(data.getJun());
	            row.createCell(10).setCellValue(data.getJul());
	            row.createCell(11).setCellValue(data.getAug());
	            row.createCell(12).setCellValue(data.getSep());
	            row.createCell(13).setCellValue(data.getOct());
	            row.createCell(14).setCellValue(data.getNov());
	            row.createCell(15).setCellValue(data.getDec());
	            row.createCell(16).setCellValue(data.getRejected());
	            row.createCell(17).setCellValue(data.getGrandTotal());
	            row.createCell(18).setCellValue(data.getAverageYear());
//	            row.createCell(19).setCellValue(data.getTotal());
	            row.createCell(19).setCellValue(data.getAverageTotalPerc());
	            row.createCell(20).setCellValue(data.getOccurrencePercentage());
	            row.createCell(21).setCellValue(data.getScore());
	            row.createCell(22).setCellValue(data.getSeverityScore());
//	            row.createCell(24).setCellValue(data.getRecordsCount());;
//	            row.createCell(25).setCellValue(data.getRpn());
//	            row.createCell(26).setCellValue(data.getPriority());
	        }
	        
	        workbook.write(out);
	        return new ByteArrayInputStream(out.toByteArray());
	    }
	}
	
	public static ByteArrayInputStream logsheetExcelReport(List<LogsheetResponseDto> logsheetSummary) throws IOException {
		String[] SummaryColumns = {"Logsheet Number", "Logsheet Date", "Logsheet Type", "Status", "Job Card Number",
			"Job Card Date", "Installation Date", "Customer Name", "Address", "Date of Failure", "Hours", "Model",
			"Registration Number", "Chassis Number", "Service Dealer", "Service Dealer City", "Sold To Dealer", 
			"Crop", "Crop Condition", "Failure Type", "Soil Type", "Field Condition", "Action", "Total Count"};

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Logsheet Summary Report");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < SummaryColumns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(SummaryColumns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (LogsheetResponseDto responseSearchExcel1 : logsheetSummary) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(responseSearchExcel1.getLogsheetNo());
				row.createCell(1).setCellValue(responseSearchExcel1.getLogsheetDate());
				row.createCell(2).setCellValue(responseSearchExcel1.getLogsheetType());
				row.createCell(3).setCellValue(responseSearchExcel1.getStatus());
				row.createCell(4).setCellValue(responseSearchExcel1.getJobCardNo());
				row.createCell(5).setCellValue(responseSearchExcel1.getJobCardDate());
				row.createCell(6).setCellValue(responseSearchExcel1.getInstallationDate());
				row.createCell(7).setCellValue(responseSearchExcel1.getCustomerName());
				row.createCell(8).setCellValue(responseSearchExcel1.getAddress());
				row.createCell(9).setCellValue(responseSearchExcel1.getDateOfFailure());
				row.createCell(10).setCellValue(responseSearchExcel1.getHours());
				row.createCell(11).setCellValue(responseSearchExcel1.getModel());
				row.createCell(12).setCellValue(responseSearchExcel1.getRegistrationNumber());
				row.createCell(13).setCellValue(responseSearchExcel1.getChassisNo());
				row.createCell(14).setCellValue(responseSearchExcel1.getServiceDealer());
				row.createCell(15).setCellValue(responseSearchExcel1.getServiceDealerCity());
				row.createCell(16).setCellValue(responseSearchExcel1.getSoldToDealer());
				row.createCell(17).setCellValue(responseSearchExcel1.getCrop());
				row.createCell(18).setCellValue(responseSearchExcel1.getCropCondition());
				row.createCell(19).setCellValue(responseSearchExcel1.getFailureType());
				row.createCell(20).setCellValue(responseSearchExcel1.getSoilType());
				row.createCell(21).setCellValue(responseSearchExcel1.getFieldCondition());
				row.createCell(22).setCellValue(responseSearchExcel1.getAction());
				row.createCell(23).setCellValue(responseSearchExcel1.getTotalCount());
			}

			Sheet sheetD = workbook.createSheet("Logsheet Details Report");
			Font headerFontD = workbook.createFont();
			headerFontD.setBold(true);
			CellStyle headerCellStyleD = workbook.createCellStyle();
			headerCellStyleD.setFont(headerFont);
			Row headerRowD = sheetD.createRow(0);
			String[] DetailsColumns = {"Logsheet Number", "Activity Date", "Activity", "Remarks"};

			for (int col = 0; col < DetailsColumns.length; col++) {
				Cell cell = headerRowD.createCell(col);
				cell.setCellValue(DetailsColumns[col]);
				cell.setCellStyle(headerCellStyleD);
			}
			int rowIdxD = 1;
//			for (LogsheetDetailsExcelResponse responseDetails : logsheetDetails) {
//				Row row = sheetD.createRow(rowIdxD++);
//				row.createCell(0).setCellValue(responseDetails.getLogsheetNo());
//				row.createCell(1).setCellValue(responseDetails.getActivityDate());
//				row.createCell(2).setCellValue(responseDetails.getActivity());
//				row.createCell(3).setCellValue(responseDetails.getRemarks());
//			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	
	public static ByteArrayInputStream nonMovInvSearchForHoReportExcel(List<AuctionPartsListResDto> list)
			throws IOException {
		String[] Columns = {"dealerCode","dealerName","partId","currStockId","itemNumber","itemDescription","aging","avlQty", "isOEMPart",
				"isOilSupplierPart","isLocalPart","spegst","spmgst","spmrp","localPurchasePrice","alternatePartNo",
				"igstPercent","uom","hsCode8Digit","isAuction" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("NonMovInvSearchForHo");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (AuctionPartsListResDto responseSearchDtos : list) {
				Row row = sheet.createRow(rowIdx++);


				row.createCell(0).setCellValue(responseSearchDtos.getDealerCode());
				row.createCell(1).setCellValue(responseSearchDtos.getDealerName());
				row.createCell(2).setCellValue(responseSearchDtos.getPartId());
				row.createCell(3).setCellValue(responseSearchDtos.getCurrStockId());
				row.createCell(4).setCellValue(responseSearchDtos.getItemNumber());
				row.createCell(5).setCellValue(responseSearchDtos.getItemDescription());
				row.createCell(6).setCellValue(responseSearchDtos.getAging());
				row.createCell(7).setCellValue(responseSearchDtos.getAvlQty());
				row.createCell(8).setCellValue(responseSearchDtos.getIsOEMPart());
				row.createCell(9).setCellValue(responseSearchDtos.getIsOilSupplierPart());
				row.createCell(10).setCellValue(responseSearchDtos.getIsLocalPart());
				row.createCell(11).setCellValue(responseSearchDtos.getSpegst());
				row.createCell(12).setCellValue(responseSearchDtos.getSpmgst());
				row.createCell(13).setCellValue(responseSearchDtos.getSpmrp());
				row.createCell(14).setCellValue(responseSearchDtos.getLocalPurchasePrice());
				row.createCell(15).setCellValue(responseSearchDtos.getAlternatePartNo());
				row.createCell(16).setCellValue(responseSearchDtos.getIgstPercent());
				row.createCell(17).setCellValue(responseSearchDtos.getUom());
				row.createCell(18).setCellValue(responseSearchDtos.getHsCode8Digit());
				row.createCell(19).setCellValue(responseSearchDtos.getIsAuction());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param records
	 * @return
	 * @throws IOException
	 */
	public static ByteArrayInputStream cssExcelReport(List<Map<String, Object>> records)
			throws IOException {
		String[] columns = {"Dealer Code","Dealer Name","Dealer State","Total Due","Total Survey Done",
				"Total Satisifed", "Cs Score", "RNR or Switched-Off", "Incorrect Data"};

	    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
	        CreationHelper creationHelper = workbook.getCreationHelper();
	        Sheet sheet = workbook.createSheet("Report Data");
	        
	        Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	        
	        Row headerRow = sheet.createRow(0);
	        for (int col = 0; col < columns.length; col++) {
	            Cell cell = headerRow.createCell(col);
	            cell.setCellValue(columns[col]);
	            cell.setCellStyle(headerCellStyle);
	        }
	        
	        int rowIdx = 1;
	        for (Map<String, Object> data : records) {
	            Row row = sheet.createRow(rowIdx++);
	            
	            row.createCell(0).setCellValue((String)data.get("dealerCode"));
	            row.createCell(1).setCellValue((String)data.get("dealerName"));
	            row.createCell(2).setCellValue((String)data.get("dealerState"));
	            row.createCell(3).setCellValue((Integer)data.get("totalDue"));
	            row.createCell(4).setCellValue((Integer)data.get("totalSurveyDone"));
	            row.createCell(5).setCellValue((Integer)data.get("totalSatisfied"));
	            row.createCell(6).setCellValue((Integer)data.get("csScore"));
	            row.createCell(7).setCellValue((Integer)data.get("rnrOrSwitchedOff"));
	            row.createCell(8).setCellValue((Integer)data.get("incorrectData"));	           
	        }
	        
	        workbook.write(out);
	        return new ByteArrayInputStream(out.toByteArray());
	    }
	}
	/**
	 * @author suraj.gaur
	 * @param records
	 * @return
	 * @throws IOException
	 */
	public static ByteArrayInputStream cssExcelStateReport(List<Map<String, Object>> records)
			throws IOException {
		String[] columns = {"Dealer State","Total Due","Total Survey Done", "Total Satisifed", 
				"Cs Score", "RNR or Switched-Off", "Incorrect Data"};

	    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
	        Sheet sheet = workbook.createSheet("Report Data");
	        
	        Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	        
	        Row headerRow = sheet.createRow(0);
	        for (int col = 0; col < columns.length; col++) {
	            Cell cell = headerRow.createCell(col);
	            cell.setCellValue(columns[col]);
	            cell.setCellStyle(headerCellStyle);
	        }
	        
	        int rowIdx = 1;
	        for (Map<String, Object> data : records) {
	            Row row = sheet.createRow(rowIdx++);
	            
	            row.createCell(0).setCellValue((String)data.get("dealerState"));
	            row.createCell(1).setCellValue((Integer)data.get("totalDue"));
	            row.createCell(2).setCellValue((Integer)data.get("totalSurveyDone"));
	            row.createCell(3).setCellValue((Integer)data.get("totalSatisfied"));
	            row.createCell(4).setCellValue((Integer)data.get("csScore"));
	            row.createCell(5).setCellValue((Integer)data.get("rnrOrSwitchedOff"));
	            row.createCell(6).setCellValue((Integer)data.get("incorrectData"));	            
	        }
	        
	        workbook.write(out);
	        return new ByteArrayInputStream(out.toByteArray());
	    }
	}
}
