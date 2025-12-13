package com.i4o.dms.itldis.warranty.reports;

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

import com.i4o.dms.itldis.warranty.pcr.repository.WarrantyPcrRepo;
import com.itextpdf.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/warranty/reports")
public class WarrantyReportController {

	@Autowired
	WarrantyPcrRepo warrantyPcrRepo;
	
	@GetMapping("/printPCR")
    public void printPCR(@RequestParam String pcrNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("PCRNo", pcrNo);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(pcrNo, request, "ProductConcernReport.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + pcrNo + ".pdf");
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
    
	@GetMapping("/printGoodwill")
    public void printGoodwill(@RequestParam String goodwillNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("GoodwillReqNo", goodwillNo);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(goodwillNo, request, "GoodWillRequestPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + goodwillNo + ".pdf");
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
    
	@GetMapping("/printWCR")
    public void printWCR(@RequestParam String wcrNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("WCRNo", wcrNo);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(wcrNo, request, "WarrantyClaimRequestPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + wcrNo + ".pdf");
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
	
	@GetMapping("/printWpdc")
    public void warrantyPartDeliveryChallan (@RequestParam String wpdcNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("DeliveryChallanNo", wpdcNo);
		
    	JasperPrint jasperPrint = pdfGeneratorReport(wpdcNo, request, "WarrantyPartsDeliveryChallanPrint.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + wpdcNo + ".pdf");
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
	
	@GetMapping("/printKAIInspectSheet")
    public void kaiInspectionSheet  (@RequestParam String inspectionNo,@RequestParam String printStatus, HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	OutputStream outputStream = null;
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("inspectionNo", inspectionNo);
    	JasperPrint jasperPrint = pdfGeneratorReport(inspectionNo, request, "WarrantyKISMain.jasper", jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + inspectionNo + ".pdf");
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
			connection = warrantyPcrRepo.getConnection();
			
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
	    
}
