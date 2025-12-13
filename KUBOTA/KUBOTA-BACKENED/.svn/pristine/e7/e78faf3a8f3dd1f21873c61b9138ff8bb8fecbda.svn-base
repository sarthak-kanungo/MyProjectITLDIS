package com.i4o.dms.kubota.spares.reports;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.mrc.repository.MrcRepository;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.dto.StockAdjustmentSearchDto;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.dto.StockAdjustmentSearchResult;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.repository.SparePurchaseOrderRepository;
import com.i4o.dms.kubota.spares.reports.dto.BackOrderPartsReportsDto;
import com.i4o.dms.kubota.spares.reports.dto.ClosingStockReportDto;
import com.i4o.dms.kubota.spares.reports.dto.InventoryMovementDto;
import com.i4o.dms.kubota.spares.reports.dto.ItemMovementDto;
import com.i4o.dms.kubota.spares.reports.dto.NonMovingPartsStockReportDto;
import com.i4o.dms.kubota.spares.reports.dto.ReportSearchDto;
import com.i4o.dms.kubota.spares.stock.repository.SpareStockStoreBinDetailRepository;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.ExcelCellGenerator;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/spare/reports")
@Slf4j
public class SpareReportsController {
    @Autowired
    private SparePurchaseOrderRepository purchaseOrderRepo;
    @Autowired
    private SpareStockStoreBinDetailRepository stockRepo;
    @Autowired
    private UserAuthentication userAuthentication;
    @Autowired
    private MrcRepository mrcRepo;
    
 	public JasperPrint pdfGeneratorReport(String receiptNumber, HttpServletRequest request, String jaspername, HashMap<String, Object> jasperParameter)
 	{
 		String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/"+jaspername);
 		JasperPrint jasperPrint = null;
 		Connection connection = null;
 		try {
 			connection = purchaseOrderRepo.getConnection();
 			
 			if (connection != null) {
 				jasperPrint = JasperFillManager.fillReport(filePath, jasperParameter, connection);
 			}
 		} catch (Exception e) {
 			jasperPrint = null;
 			e.printStackTrace();
 		}
 		finally {
 			try {
 				if (connection != null)
 					connection.close();
 			} catch (Exception e) {
 				jasperPrint = null;
 				e.printStackTrace();
 			}
 		}
 		return jasperPrint;
 	}

    
    public void printReport(JasperPrint jasperPrint,String printStatus,OutputStream outputStream) throws Exception{

	    JRPdfExporter exporter = new JRPdfExporter();
	   
	    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	   
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
	    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		if(printStatus!=null && printStatus.equals("true")){
	          configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
	          configuration.setPdfJavaScript("this.print();");
		}
        exporter.setConfiguration(configuration);
		exporter.exportReport();
	}
    
    @PostMapping("/searchClosingStockReport")
    public ResponseEntity<?> seachStockAdjDetails(@RequestBody ReportSearchDto searchDto){
		ApiResponse apiResponse = new ApiResponse();
		List<ClosingStockReportDto> result = stockRepo.getClosingStockReport(searchDto.getStockOnDate(),
    			searchDto.getProduct(),
    			searchDto.getModel(),
    			searchDto.getSubModel(),
    			searchDto.getSeries(),
    			searchDto.getVariant(),
    			searchDto.getDealerId(),
    			searchDto.getBranchId(),
    			searchDto.getState(),
    			searchDto.getOrgHierId(),
    			searchDto.getPage(),
    			searchDto.getSize(),
    			userAuthentication.getUsername()
    			);
		Long count = 0l;
		if(result!=null && result.size()>0){
			count = result.get(0).getTotalCount();
		}
		apiResponse.setResult(result);
		apiResponse.setCount(count);
	    apiResponse.setMessage("Fetch Closing Stock Details");
	    apiResponse.setStatus(HttpStatus.OK.value());
	    return ResponseEntity.ok(apiResponse);
	} 
    

    @PostMapping("/downloadClosingStockReport")
    public ResponseEntity<InputStreamResource> downloadClosingStockReport(@RequestBody ReportSearchDto searchDto,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = Integer.MAX_VALUE-1;

    	List<ClosingStockReportDto> result = stockRepo.getClosingStockReport(
    			searchDto.getStockOnDate(),
    			searchDto.getProduct(),
    			searchDto.getModel(),
    			searchDto.getSubModel(),
    			searchDto.getSeries(),
    			searchDto.getVariant(),
    			searchDto.getDealerId(),
    			searchDto.getBranchId(),
    			searchDto.getState(),
    			searchDto.getOrgHierId(),
    			searchDto.getPage(),
    			size,
    			userAuthentication.getUsername());
		
    	ByteArrayInputStream in = ExcelCellGenerator.closingStockReport(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "ClosingStockReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
    
    @PostMapping("/searchNonMovingPartsStockReport")
    public ResponseEntity<?> searchNonMovingPartsStockReport(@RequestBody ReportSearchDto searchDto){
		ApiResponse apiResponse = new ApiResponse();
		List<NonMovingPartsStockReportDto> result = stockRepo.getNonMovingPartsStockReport(searchDto.getProduct(),
    			searchDto.getModel(),
    			searchDto.getSubModel(),
    			searchDto.getSeries(),
    			searchDto.getVariant(),
    			searchDto.getDealerId(),
    			searchDto.getBranchId(),
    			searchDto.getState(),
    			searchDto.getOrgHierId(),
    			searchDto.getPage(),
    			searchDto.getSize(),
    			userAuthentication.getUsername());
		Long count = 0l;
		if(result!=null && result.size()>0){
			count = result.get(0).getTotalCount();
		}
		apiResponse.setResult(result);
		apiResponse.setCount(count);
	    apiResponse.setMessage("Fetch Non Moving Parts Stock Details");
	    apiResponse.setStatus(HttpStatus.OK.value());
	    return ResponseEntity.ok(apiResponse);
	} 

    @PostMapping("/downloadNonMovingPartsStockReport")
    public ResponseEntity<InputStreamResource> downloadNonMovingPartsStockReport(@RequestBody ReportSearchDto searchDto,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = Integer.MAX_VALUE-1;

    	List<NonMovingPartsStockReportDto> result = stockRepo.getNonMovingPartsStockReport(searchDto.getProduct(),
    			searchDto.getModel(),
    			searchDto.getSubModel(),
    			searchDto.getSeries(),
    			searchDto.getVariant(),
    			searchDto.getDealerId(),
    			searchDto.getBranchId(),
    			searchDto.getState(),
    			searchDto.getOrgHierId(),
    			searchDto.getPage(),
    			size,
    			userAuthentication.getUsername());
		
    	ByteArrayInputStream in = ExcelCellGenerator.nonMovingPartsStockReport(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "NonMovingPartsStockReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
    
    @PostMapping("/searchBackOrderPartsReport")
    public ResponseEntity<?> searchBackOrderPartsReport(@RequestBody ReportSearchDto searchDto){
		ApiResponse apiResponse = new ApiResponse();
		List<BackOrderPartsReportsDto> result = stockRepo.getBackOrderPartsReport(
				searchDto.getFromDate(),
    			searchDto.getToDate(),
				searchDto.getProduct(),
    			searchDto.getModel(),
    			searchDto.getSubModel(),
    			searchDto.getSeries(),
    			searchDto.getVariant(),
    			searchDto.getDealerId(),
    			searchDto.getBranchId(),
    			searchDto.getState(),
    			searchDto.getOrgHierId(),
    			searchDto.getPage(),
    			searchDto.getSize(),
    			userAuthentication.getUsername());
		Long count = 0l;
		if(result!=null && result.size()>0){
			count = result.get(0).getTotalCount();
		}
		apiResponse.setResult(result);
		apiResponse.setCount(count);
	    apiResponse.setMessage("Fetch Back Order Parts Details");
	    apiResponse.setStatus(HttpStatus.OK.value());
	    return ResponseEntity.ok(apiResponse);
	} 

    @PostMapping("/downloadBackOrderPartsReport")
    public ResponseEntity<InputStreamResource> downloadBackOrderPartsReport(@RequestBody ReportSearchDto searchDto,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = Integer.MAX_VALUE-1;

    	List<BackOrderPartsReportsDto> result = stockRepo.getBackOrderPartsReport(
    			searchDto.getFromDate(),
    			searchDto.getToDate(),
    			searchDto.getProduct(),
    			searchDto.getModel(),
    			searchDto.getSubModel(),
    			searchDto.getSeries(),
    			searchDto.getVariant(),
    			searchDto.getDealerId(),
    			searchDto.getBranchId(),
    			searchDto.getState(),
    			searchDto.getOrgHierId(),
    			searchDto.getPage(),
    			size,
    			userAuthentication.getUsername());
		
    	ByteArrayInputStream in = ExcelCellGenerator.backOrderPartsReports(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "BackOrderPartskReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
    @PostMapping("/searchitemMovementReport")
    public ResponseEntity<?> searchItemMovementReport(@RequestBody ReportSearchDto searchDto){
		ApiResponse apiResponse = new ApiResponse();
		List<ItemMovementDto> result = stockRepo.getItemMovementReport(
				searchDto.getFromDate(),
    			searchDto.getToDate(),
				searchDto.getProduct(),
    			searchDto.getModel(),
    			searchDto.getSubModel(),
    			searchDto.getSeries(),
    			searchDto.getVariant(),
    			searchDto.getPartNumber(),
    			searchDto.getDealerId(),
    			searchDto.getBranchId(),
    			searchDto.getState(),
    			searchDto.getOrgHierId(),
    			searchDto.getPage(),
    			searchDto.getSize(),
    			userAuthentication.getUsername());
		Long count = 0l;
		if(result!=null && result.size()>0){
			count = result.get(0).getTotalCount();
		}
		apiResponse.setResult(result);
		apiResponse.setCount(count);
	    apiResponse.setMessage("Fetch Item Movement Details");
	    apiResponse.setStatus(HttpStatus.OK.value());
	    return ResponseEntity.ok(apiResponse);
	} 

    @PostMapping("/downloaditemMovementReport")
    public ResponseEntity<InputStreamResource> downloadItemMovementReport(@RequestBody ReportSearchDto searchDto,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = Integer.MAX_VALUE-1;

    	List<ItemMovementDto> result = stockRepo.getItemMovementReport(
    			searchDto.getFromDate(),
    			searchDto.getToDate(),
    			searchDto.getProduct(),
    			searchDto.getModel(),
    			searchDto.getSubModel(),
    			searchDto.getSeries(),
    			searchDto.getVariant(),
    			searchDto.getPartNumber(),
    			searchDto.getDealerId(),
    			searchDto.getBranchId(),
    			searchDto.getState(),
    			searchDto.getOrgHierId(),
    			searchDto.getPage(),
    			size,
    			userAuthentication.getUsername());
		
    	ByteArrayInputStream in = ExcelCellGenerator.itemMovementReports(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "ItemMovementReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
    @PostMapping("/searchInventoryMovementReport")
    public ResponseEntity<?> searchInventoryMovementReport(@RequestBody ReportSearchDto searchDto){
		ApiResponse apiResponse = new ApiResponse();
		List<InventoryMovementDto> result = stockRepo.getInventoryMovementReport(
				searchDto.getFromDate(),
    			searchDto.getToDate(),
				searchDto.getProduct(),
    			searchDto.getModel(),
    			searchDto.getSubModel(),
    			searchDto.getSeries(),
    			searchDto.getVariant(),
    			searchDto.getPartNumber(),
    			searchDto.getDealerId(),
    			searchDto.getBranchId(),
    			searchDto.getState(),
    			searchDto.getOrgHierId(),
    			searchDto.getPage(),
    			searchDto.getSize(),
    			userAuthentication.getUsername());
		Long count = 0l;
		if(result!=null && result.size()>0){
			count = result.get(0).getTotalCount();
		}
		apiResponse.setResult(result);
		apiResponse.setCount(count);
	    apiResponse.setMessage("Fetch Inventory Movement Details");
	    apiResponse.setStatus(HttpStatus.OK.value());
	    return ResponseEntity.ok(apiResponse);
	} 

    @PostMapping("/downloadInventoryMovementReport")
    public ResponseEntity<InputStreamResource> downloadInventoryMovementReport(@RequestBody ReportSearchDto searchDto,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = Integer.MAX_VALUE-1;

    	List<InventoryMovementDto> result = stockRepo.getInventoryMovementReport(
    			searchDto.getFromDate(),
    			searchDto.getToDate(),
    			searchDto.getProduct(),
    			searchDto.getModel(),
    			searchDto.getSubModel(),
    			searchDto.getSeries(),
    			searchDto.getVariant(),
    			searchDto.getPartNumber(),
    			searchDto.getDealerId(),
    			searchDto.getBranchId(),
    			searchDto.getState(),
    			searchDto.getOrgHierId(),
    			searchDto.getPage(),
    			size,
    			userAuthentication.getUsername());
		
    	ByteArrayInputStream in = ExcelCellGenerator.inventoryMovementReports(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "InventoryMovementReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
    @GetMapping("/printBinToBin")
    public void printBinToBin(@RequestParam String transferNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("TransferNumber", transferNo);
    	
		JasperPrint jasperPrint = pdfGeneratorReport(transferNo, request, "SparesBinToBinTransferPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + transferNo + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
    @GetMapping("/printPartIssue")
    public void printPartIssue(@RequestParam String partIssueNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("PartIssueNo", partIssueNo);
    	
		JasperPrint jasperPrint = pdfGeneratorReport(partIssueNo, request, "SparesIssuePrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + partIssueNo + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
    @GetMapping("/printPurchaseOrder")
    public void printPurchaseOrder(@RequestParam String ponumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("PurchaseOrderNo", ponumber);
    	
		JasperPrint jasperPrint = pdfGeneratorReport(ponumber, request, "SparePurchaseOrderPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + ponumber + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
    @GetMapping("/getQuotationPrint")
    public void getQuotationPrint(@RequestParam String quotationNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("QuotationNumber", quotationNumber);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(quotationNumber, request,"SparesQuotationPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + quotationNumber + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
    @GetMapping("/getGrnPrint")
    public void getGrnPrint(@RequestParam String grnNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("GRNNumber", grnNumber);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(grnNumber, request, "SparesGRNPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + grnNumber + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
    
    @GetMapping("/printSalesOrder")
    public void printSalesOrder(@RequestParam String soNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("CustomerOrderNo", soNumber);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(soNumber, request, "SparesCustomerOrderPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + soNumber + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
    
    @GetMapping("/printPaymentReceipt")
    public void printPaymentReceipt(@RequestParam String receiptNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("PaymentReceiptNo", receiptNumber);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(receiptNumber, request, "SparesPaymentReceipt.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + receiptNumber + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
    
    @GetMapping("/printSalesInvoice")
    public void printSalesInvoice(@RequestParam(required=false) String invoiceNo,
    		@RequestParam(required=false) String jobCardNo,
    		@RequestParam String printStatus,
    		@RequestParam(required=false) String type, 
    		HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		
		
    	JasperPrint jasperPrint = null;
    	String doc = "";
    	if(type!=null && type.equals("Job Card")){
    		doc = jobCardNo;
    		jasperParameter.put("JobCardNo", jobCardNo);
    		jasperPrint = pdfGeneratorReport(jobCardNo, request, "JobCardPreInvoicePrint.jasper", jasperParameter);
    	}else if(type!=null && type.equals("WCR")){
    		doc = invoiceNo;
    		jasperParameter.put("InvoiceNo", invoiceNo);
    		jasperPrint = pdfGeneratorReport(invoiceNo, request, "WarrantyClaimInvoicePrintMain.jasper", jasperParameter);
    	}else{
    		doc = invoiceNo;
    		jasperParameter.put("InvoiceNo", invoiceNo);
    		jasperPrint = pdfGeneratorReport(invoiceNo, request, "SparesSalesInvoicePrint.jasper", jasperParameter);
    	}
    	
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + doc + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
    
    @GetMapping("/printBinningSlip")
    public void printBinningSlip(@RequestParam String grnNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("GRNNumber", grnNumber);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(grnNumber, request, "SparesBinningSlipPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + grnNumber + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
    
    @GetMapping("/printPicklList")
    public void printPicklList(@RequestParam String picklistNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("pickListNo", picklistNumber);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(picklistNumber, request, "PickListPrintMain.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + picklistNumber + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
    
    
    @GetMapping("/printPreSalesServiceMrc")
    public void printPreSalesServiceMrc(@RequestParam String mrcNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("MRCNo", mrcNo);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(mrcNo, request, "MRCHeader.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + mrcNo + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }   
    
    @GetMapping("/printFormF22")
    public void printFormF22(@RequestParam String mrcNo,
    		@RequestParam String formType,
    		@RequestParam String requestType,
    		@RequestParam String printStatus, 
    		HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("ChassisNo", mrcNo);
		
    	JasperPrint jasperPrint = null;
    	if(userAuthentication.getDealerId()!=null){
    		if(formType.equals("FormF"))
    			mrcRepo.updateFormFDownloadStatus(mrcNo);
    		else
    			mrcRepo.updateForm22DownloadStatus(mrcNo);
		}
    	if(formType.equals("FormF"))
    		jasperPrint = pdfGeneratorReport(mrcNo, request, "formF.jasper", jasperParameter);
    	else
    		jasperPrint = pdfGeneratorReport(mrcNo, request, "form22.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename="
    				+ formType + "_"
    				+ mrcNo + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
    
    
    @GetMapping("/printPreSalesServicePdi")
    public void printPreSalesServicePdi(@RequestParam String pdiNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("PDINo", pdiNo);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(pdiNo, request, "PdiHeader.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + pdiNo + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
 
    
    @GetMapping("/printPreSalesServicePdc")
    public void printPreSalesServicePdc(@RequestParam String pdcNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("PDCNo", pdcNo);
    	JasperPrint jasperPrint = pdfGeneratorReport(pdcNo, request, "PdcHeader.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + pdcNo + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
 
    @GetMapping("/printPreSalesServicePsc")
    public void printPreSalesServicePsc(@RequestParam String pscNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("PSCNo", pscNo);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(pscNo, request, "PscHeader.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + pscNo + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		printReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				outputStream.flush();
				outputStream.close();
            }
		}
    }
 
 

}

