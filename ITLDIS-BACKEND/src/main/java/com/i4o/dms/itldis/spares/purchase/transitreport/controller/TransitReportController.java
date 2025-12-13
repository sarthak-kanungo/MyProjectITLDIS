package com.i4o.dms.itldis.spares.purchase.transitreport.controller;

import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.purchase.grn.repository.SparePartGrnRepository;
import com.i4o.dms.itldis.spares.purchase.transitreport.dto.SearchResponsedto;
import com.i4o.dms.itldis.spares.purchase.transitreport.dto.TransitReportSearchDto;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/spares/transitreport")
@Slf4j
public class TransitReportController {
    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private SparePartGrnRepository sparePartGrnRepository;

    @PostMapping("/getTransitReport")
    public ResponseEntity<?> getTransitReport(@RequestBody TransitReportSearchDto transitReportSearchDto){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("transit report details");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<SearchResponsedto> result = sparePartGrnRepository.getTransitReportDetails(transitReportSearchDto.getGrnStatus(),
                transitReportSearchDto.getFromInvoiceDate(),transitReportSearchDto.getToInvoiceDate(),
                userAuthentication.getDealerId(),
                transitReportSearchDto.getPage(),transitReportSearchDto.getSize(),userAuthentication.getUsername(),
                'N', 
                0L );
        apiResponse.setResult(result);
        Long count=0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/downloadTransitReportExcel")
    ResponseEntity<InputStreamResource> downloadTransitReportExcel(@RequestParam(required = true) Long userId,
    		@RequestParam(required= false) String grnStatus,
    		@RequestParam(required= false) String fromInvoiceDate,
    		@RequestParam(required= false) String toInvoiceDate,
    		@RequestParam Integer page,
            @RequestParam Integer size,
            HttpServletResponse response) throws IOException{
    	System.out.println("transit_excel");
    	
    	List<SearchResponsedto> responceSearchDto= sparePartGrnRepository.getTransitReportDetails(grnStatus, fromInvoiceDate, toInvoiceDate, userAuthentication.getDealerId(), page, size, userAuthentication.getUsername(), 'N', 0L);
    	ByteArrayInputStream in= ExcelCellGenerator.transitDetaitsExcelReport(responceSearchDto);
    	
    	 response.setContentType("application/vnd.ms-excel");

         HttpHeaders headers = new HttpHeaders();
         String filename = "TransitReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
         headers.add("Content-Disposition", "attachment ; filename = "+filename);
         headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


         return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
         }
    
    @GetMapping(value = "/getTransitReportItems")
    public ResponseEntity<?>viewDesignation(@RequestParam("invoiceNo") String invoiceNo){
       ApiResponse<Object> apiResponse=new ApiResponse<>();
       List<Map<String, Object>>  itemsDetails = sparePartGrnRepository.getTransitReportItems(invoiceNo);
       apiResponse.setMessage("Transit Items details get successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(itemsDetails);
       return ResponseEntity.ok(apiResponse);
   }
    
    
    
}
