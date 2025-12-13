package com.i4o.dms.itldis.salesandpresales.reports;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.common.service.JasperPrintService;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.repository.PurchaseOrderRepo;
import com.i4o.dms.itldis.salesandpresales.reports.dto.PrintMachineInventoryReportModel;
import com.i4o.dms.itldis.salesandpresales.reports.dto.TransactionResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;
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
@RequestMapping("/api/salesandpresales/reports")
@Slf4j
public class ReportsController {
    @Autowired
    private PurchaseOrderRepo purchaseOrderRepo;

	@Autowired
	JasperPrintService jasperPrintService;
    
    @GetMapping("/getSalesPreSalesReport")
    public ResponseEntity<?> getSalesPreSalesReport(
                                                    HttpServletResponse response) throws IOException {
        List<TransactionResponse> transactionCounts = purchaseOrderRepo.getAllTransactionalCount();
        log.info("count:"+transactionCounts.size());
        log.info("transactionCounts:"+transactionCounts);
        response.setContentType("application/vnd.ms-excel");
        ByteArrayInputStream in = ExcelCellGenerator.generateSalesPreSalesTransactionReport(transactionCounts);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment ; filename = DMS-Sales-PreSales-Transaction-Report.xlsx");

        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
    
    @GetMapping("/printPurchaseOrder")
    public void printPurchaseOrder(@RequestParam String ponumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("PurchaseOrderNo", ponumber);
    	
		JasperPrint jasperPrint = pdfGeneratorReport(ponumber, request, "PurchaseOrderPrint.jasper", jasperParameter);
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
    public void getQuotationPrint(@RequestParam String quotationNumber,@RequestParam String printStatus,@RequestParam String gstOrWithoutgst, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("QuotationNumber", quotationNumber);
		JasperPrint jasperPrint=null;
		if(gstOrWithoutgst.equalsIgnoreCase("withgst")) {
			jasperPrint = pdfGeneratorReport(quotationNumber, request,"QuotationReport.jasper", jasperParameter);
			}
		else if(gstOrWithoutgst.equalsIgnoreCase("withoutgst")) {
			jasperPrint = pdfGeneratorReport(quotationNumber, request,"SalesQuotationPrintWithoutGST.jasper", jasperParameter);
		}
		
    	
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
		
    	JasperPrint jasperPrint = pdfGeneratorReport(grnNumber, request, "GRNPrint.jasper", jasperParameter);
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
    
    @GetMapping("/getEnquiryPrint")
    public void getEnquiryPrint(@RequestParam String enquiryNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("EnquiryNumber", enquiryNumber);
    	JasperPrint jasperPrint = pdfGeneratorReport(enquiryNumber, request, "EnquiryPdfPrint.jasper",jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + enquiryNumber + ".pdf");
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
    @GetMapping("/getPaymentReceiptPrint")
    public void getPaymentReceiptPrint(@RequestParam String receiptNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("PaymentReceiptNumber", receiptNumber);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(receiptNumber, request, "PaymentReceipt.jasper", jasperParameter);
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
    
    @GetMapping("/getActivityProposalPrint")
    public void getActivityProposalPrint(@RequestParam String activityNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("ActivityNumber", activityNumber);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(activityNumber, request, "MarketingActivityProposal.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + activityNumber + ".pdf");
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
    
    @GetMapping("/getActivityReportPrint")
    public void getActivityReportPrint(@RequestParam String activityReportNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("ActivityNumber", activityReportNumber);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(activityReportNumber, request, "MarketingActivityReport.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + activityReportNumber + ".pdf");
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
    
    @GetMapping("/getChanneFinlIndentPrint")
    public void getChanneFinlIndentPrint(@RequestParam String indentNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("IndentNo", indentNumber);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(indentNumber, request, "ChannelFinanceIndentPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + indentNumber + ".pdf");
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
    
    @GetMapping("/getInvoicePrint")
    public void getInvoicePrint(@RequestParam String invoiceNumber,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("InvoiceNo", invoiceNumber);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(invoiceNumber, request, "InvoicePrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + invoiceNumber + ".pdf");
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
    
    @GetMapping("/getDeliveryChallanPrint")
    public void getDeliveryChallanPrint(@RequestParam String dcNumber,@RequestParam String printStatus, @RequestParam String gatePassNo,HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("DCNumber", dcNumber);
		jasperParameter.put("GatePassNo", gatePassNo);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(dcNumber, request, "DeliveryChallanPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + dcNumber + ".pdf");
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
    
    @GetMapping("/getGatePassPrint")
    public void getGatePassPrint(@RequestParam String gatePassNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("GatePassNo", gatePassNo);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(gatePassNo, request, "gatePassPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + gatePassNo + ".pdf");
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
    
    /**
	 * @author suraj.gaur
	 * @param fromDate
	 * @param toDate
	 * @apiNote Api for generating report PDF for a particular BT Receipt no.
	 */
	@PostMapping("/printMachineInventoryReport")
    public void printMachineInventoryReport(@RequestBody PrintMachineInventoryReportModel inventoryReportModel,
    		HttpServletRequest request, HttpServletResponse response)
    {
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/");
    	
    	OutputStream outputStream = null;
    	response.setContentType("application/vnd. ms-exce");
		response.setHeader("Content-Disposition", "inline; filename=" 
				+ "Machine-Inv-Report" + "-" + ThreadLocalRandom.current().nextInt(1000) + "-" 
				+ System.currentTimeMillis() + ".xlsx");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		
    	try {
    		outputStream = response.getOutputStream();
    		
    		String jasperfile = filePath + "MachineInventoryReport.jasper";
        	
        	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
    		jasperParameter.put("fromDate", inventoryReportModel.getFromDate());
    		jasperParameter.put("toDate", inventoryReportModel.getToDate());
        	
    		JasperPrint jasperPrint = jasperPrintService.getJasperPrint(jasperfile, jasperParameter);
        	
    		jasperPrintService.exportToXlsx(jasperPrint, new String[] {"Machine_Inventory"}, outputStream);
		} 
    	catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(outputStream!=null){
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					System.err.println("There is an Error flushing and closing the outStream object");
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
            }
		}
    }
    
}
