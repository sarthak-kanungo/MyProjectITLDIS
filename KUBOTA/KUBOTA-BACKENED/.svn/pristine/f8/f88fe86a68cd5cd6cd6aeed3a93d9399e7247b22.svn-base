package com.i4o.dms.kubota.spares.reports;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.repository.SparePurchaseOrderRepository;
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
@RequestMapping("/api/service/reports")
@Slf4j
public class ServiceReportsController {


    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private SparePurchaseOrderRepository purchaseOrderRepo;
    
    @GetMapping("/printJobcard")
    public void printJobcard(@RequestParam String jobCardNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("JobCardNo", jobCardNo);
    	
		JasperPrint jasperPrint = pdfGeneratorReport(jobCardNo, request, "JobCardPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + jobCardNo + ".pdf");
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
    
    @GetMapping("/printJobcard_blank")
    public void printJobcard_blank(HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	if(userAuthentication.getBranchId() != null) {
    		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
        	jasperParameter.put("BranchId",userAuthentication.getBranchId()+"");
        	
        	JasperPrint jasperPrint = pdfGeneratorReport("", request, "JobCardOpenTemplate.jasper", jasperParameter);
        	try {
        		response.setContentType("application/pdf");
        		response.setHeader("Content-Disposition", "inline; filename=Jobcard_blank.pdf");
                response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        		outputStream = response.getOutputStream();
        		printReport(jasperPrint, "true", outputStream);
    			
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
    	else {
    		System.out.println("Login with Dealer User!");
    	}
    	
    }
    
    @GetMapping("/printDealerInvoice")
    public void printDealerInvoice(@RequestParam Integer id,
    		@RequestParam String invoiceNo,
    		@RequestParam String printStatus,
    		HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		
    	JasperPrint jasperPrint = null;
    	
		jasperParameter.put("InvoiceId", id);
		jasperPrint = pdfGeneratorReport(invoiceNo, request, "DealerClaimInvoice.jasper", jasperParameter);
    	
    	
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + invoiceNo + ".pdf");
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
			System.out.println("Message Error: " + e.getMessage());
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
}
