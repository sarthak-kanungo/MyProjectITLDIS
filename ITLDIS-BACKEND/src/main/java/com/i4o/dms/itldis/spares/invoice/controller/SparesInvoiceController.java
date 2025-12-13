package com.i4o.dms.itldis.spares.invoice.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.invoice.domain.SparesInvoice;
import com.i4o.dms.itldis.spares.invoice.dto.InvoiceCancellationDto;
import com.i4o.dms.itldis.spares.invoice.dto.ResponseSearchSparesInvoice;
import com.i4o.dms.itldis.spares.invoice.dto.SalesOrderDto;
import com.i4o.dms.itldis.spares.invoice.dto.SpareInvoiceSearch;
import com.i4o.dms.itldis.spares.invoice.dto.SpareInvoiceViewDto;
import com.i4o.dms.itldis.spares.invoice.repository.SparesInvoiceRepo;
import com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto.ResponseSearchPurchaseOrder;
import com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto.SearchSparePurchaseOrder;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RequestMapping(value = "/api/spares/invoice")
public class SparesInvoiceController {

    @Autowired
    private SparesInvoiceRepo sparesInvoiceRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @GetMapping(value = "/getReferenceDocument/{id}")
    public ResponseEntity<?> getSaleOrderDetails(@PathVariable Long id) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Sale Order Details");
        apiResponse.setStatus(HttpStatus.OK.value());

        List<Map<String, Object>> saleOrderInfo = sparesInvoiceRepo.getSaleOrderInfo(id, userAuthentication.getBranchId());
        if(saleOrderInfo!=null && saleOrderInfo.size()>0){
        	SalesOrderDto dto = new SalesOrderDto();
        	dto.setInvoiceDetails(saleOrderInfo.get(0));
        	dto.setItemDetails(saleOrderInfo);
        	apiResponse.setResult(dto);
        }
        
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/documentAutoComplete")
    public ResponseEntity<?> getSaleOrderAutoComplete(@RequestParam String saleOrderNo,
                                                      @RequestParam String documentType) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Sale Order Autocomplete");
        apiResponse.setStatus(HttpStatus.OK.value());

        List<Map<String, Object>> saleOrderInfo = sparesInvoiceRepo.
                getSaleOrderAutoComplete(documentType, saleOrderNo,
                        userAuthentication.getBranchId());
        apiResponse.setResult(saleOrderInfo);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/saveInvoice")
    @Transactional
    public ResponseEntity<?> saveInvoice(@RequestBody SparesInvoice sparesInvoice) {
        ApiResponse apiResponse = new ApiResponse();
        sparesInvoice.setCreatedBy(userAuthentication.getLoginId());
        sparesInvoice.setCreatedDate(new Date());
        sparesInvoice.setBranchId(userAuthentication.getBranchId());
        sparesInvoice.setInvoiceDate(new Date());
        
        SparesInvoice sparesInvoice1 = sparesInvoiceRepo.save(sparesInvoice);
        if(sparesInvoice.getSparesSalesOrderId()!=null){
        	sparesInvoiceRepo.spareInvoiceSave(sparesInvoice1.getId(), userAuthentication.getBranchId());
        }else if(sparesInvoice.getServiceJobcardId()!=null){
        	sparesInvoiceRepo.updateInvoiceFlagJobCard(1,"Billed",  sparesInvoice1.getId(), userAuthentication.getBranchId(), userAuthentication.getLoginId());
        }else if(sparesInvoice.getWarrantyWcrId()!=null){
        	sparesInvoiceRepo.updateInvoiceFlagWCR("Billed",userAuthentication.getLoginId(), sparesInvoice1.getId());
        }
        apiResponse.setResult("Invoice saved Successfully..");
        
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/searchSpareInvoice")
    public ResponseEntity<?> searchSpareInvoice(@RequestBody SpareInvoiceSearch spareInvoiceSearch) {
        ApiResponse<List<?>> apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Spare Invoice list");
        List<ResponseSearchSparesInvoice> result = sparesInvoiceRepo.searchSaleInvoice(spareInvoiceSearch.getSalesInvoiceId(), spareInvoiceSearch.getCustomerCode(),
                spareInvoiceSearch.getCustomerName(), spareInvoiceSearch.getReferenceDocument(), spareInvoiceSearch.getCustomerType(),
                spareInvoiceSearch.getSalesType(), spareInvoiceSearch.getModeOfTransport(), spareInvoiceSearch.getTransporter(), spareInvoiceSearch.getFromDate(),
                spareInvoiceSearch.getToDate(), userAuthentication.getDealerEmployeeId(), userAuthentication.getDealerId(),
                spareInvoiceSearch.getPage(), spareInvoiceSearch.getSize(),userAuthentication.getUsername(),'N',0L,"View", spareInvoiceSearch.getWcrNo(), spareInvoiceSearch.getJobCardNumber());
        apiResponse.setResult(result);
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getSpareInvoiceById/{id}")
    public ResponseEntity<?> searchSpareInvoice(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Spare Invoice Details");
        SpareInvoiceViewDto spareInvoiceViewDto = new SpareInvoiceViewDto();
        spareInvoiceViewDto.setInvoiceDetails(sparesInvoiceRepo.getSparesInvoiceViewHeaderData(id, userAuthentication.getBranchId()));
        spareInvoiceViewDto.setItemDetails(sparesInvoiceRepo.getSparesInvoiceViewPartDetails(id, userAuthentication.getBranchId()));
        //spareInvoiceViewDto.setSalesOrder(sparesInvoiceRepo.getSparesInvoiceViewSalesOrderDetails(id, userAuthentication.getBranchId()));
        
        spareInvoiceViewDto.setLabourDetails(sparesInvoiceRepo.getSparesInvoiceViewLabourDetails(id, userAuthentication.getBranchId()));
        spareInvoiceViewDto.setOutsideChargeDetails(sparesInvoiceRepo.getSparesInvoiceViewOutsideChargeDetails(id, userAuthentication.getBranchId()));
       
        apiResponse.setResult(spareInvoiceViewDto);
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping(value="cancelInvoice")
    public ResponseEntity<?> cancelInvoice(@RequestBody InvoiceCancellationDto dto) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        sparesInvoiceRepo.cancelInvoice(dto.getId(), userAuthentication.getLoginId(), dto.getRemark());
        if(dto.getReferenceType().equals("Sale Order")){
        	sparesInvoiceRepo.spareInvoiceSave(dto.getId(), userAuthentication.getBranchId());
        }else if(dto.getReferenceType().equals("Job Card")){
        	sparesInvoiceRepo.updateInvoiceFlagJobCard(0,"Close", dto.getId(), userAuthentication.getBranchId(), userAuthentication.getLoginId());
        }/*else if(dto.getReferenceType().equals("WCR")){
        	sparesInvoiceRepo.updateInvoiceFlagWCR("Approved",userAuthentication.getLoginId(), dto.getId());
        }*/
        apiResponse.setMessage("Invoice Cancelled successfuly");
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "/getSparesInvoiceNumberAutocomplete")
    public ResponseEntity<?> getSparesInvoiceNumberAutocomplete(@RequestParam String invoiceNumber) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Invoice Number list");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<Map<String, Object>> list = sparesInvoiceRepo.getSpareInvoiceNumberAutocomplete(
                invoiceNumber, userAuthentication.getUsername());
        apiResponse.setResult(list);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getJobCardDetails")
    public ResponseEntity<?> getJobCardDeatils(@RequestParam Long jobCardId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Job Card Details");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<Map<String, Object>> jobCardDetails =
                sparesInvoiceRepo.getSpareInvoiceJobCardDetails(jobCardId, userAuthentication.getBranchId());
        if(jobCardDetails!=null && jobCardDetails.size()>0){
        	if(jobCardDetails.get(0).get("partIssue")!=null){
        		apiResponse.setMessage(jobCardDetails.get(0).get("partIssue").toString());
        	}else{
		        List<Map<String, Object>> labourDetails = jobCardDetails.stream().filter(f -> f.get("listCategory").equals("LABOUR")).collect(Collectors.toList());
		        List<Map<String, Object>> partDetails = jobCardDetails.stream().filter(f -> f.get("listCategory").equals("PART")).collect(Collectors.toList());
		        List<Map<String, Object>> outsideChargeDetails = jobCardDetails.stream().filter(f -> f.get("listCategory").equals("OUTSIDE_CHARGES")).collect(Collectors.toList());
		        
		        Map map = new HashMap<>();
		        map.put("itemDetails", partDetails);
		        map.put("outsideChargeDetails", outsideChargeDetails);
		        map.put("labourDetails", labourDetails);
		        map.put("invoiceDetails", jobCardDetails.get(0));
		        /*invoiceDetails: InvoiceDetails;
		        itemDetails: ItemDetail[];
		        labourDetails?: LabourDetail[];
		        outsideChargeDetails?: OutsideChargeDetail[];
		        salesOrder?:IdMaster*/
		        
		        apiResponse.setResult(map);
        	}
        }		
        

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "/getWcrDetails")
    public ResponseEntity<?> getWcrDetails(@RequestParam Long wcrId, @RequestParam String claimType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("WCR Details");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<Map<String, Object>> jobCardDetails =
                sparesInvoiceRepo.getSpareInvoiceWcrDetails(wcrId, claimType, userAuthentication.getBranchId());
        if(jobCardDetails!=null && jobCardDetails.size()>0){
        	if(jobCardDetails.get(0).get("partIssue")!=null){
        		apiResponse.setMessage(jobCardDetails.get(0).get("partIssue").toString());
        	}else{
		        List<Map<String, Object>> labourDetails = jobCardDetails.stream().filter(f -> f.get("listCategory").equals("LABOUR")).collect(Collectors.toList());
		        List<Map<String, Object>> partDetails = jobCardDetails.stream().filter(f -> f.get("listCategory").equals("PART")).collect(Collectors.toList());
		        List<Map<String, Object>> outsideChargeDetails = jobCardDetails.stream().filter(f -> f.get("listCategory").equals("OUTSIDE_CHARGES")).collect(Collectors.toList());
		        
		        Map map = new HashMap<>();
		        map.put("itemDetails", partDetails);
		        map.put("outsideChargeDetails", outsideChargeDetails);
		        map.put("labourDetails", labourDetails);
		        map.put("invoiceDetails", jobCardDetails.get(0));
		        
		        apiResponse.setResult(map);
        	}
        }		
        

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/downloadPartSalesReport")
    public ResponseEntity<InputStreamResource> downloadPartSalesReport(@RequestBody SpareInvoiceSearch spareInvoiceSearch,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = Integer.MAX_VALUE-1;
    	
    	List<ResponseSearchSparesInvoice> list = sparesInvoiceRepo.searchSaleInvoice(spareInvoiceSearch.getSalesInvoiceId(), spareInvoiceSearch.getCustomerCode(),
                spareInvoiceSearch.getCustomerName(), spareInvoiceSearch.getReferenceDocument(), spareInvoiceSearch.getCustomerType(),
                spareInvoiceSearch.getSalesType(), spareInvoiceSearch.getModeOfTransport(), spareInvoiceSearch.getTransporter(), spareInvoiceSearch.getFromDate(),
                spareInvoiceSearch.getToDate(), userAuthentication.getDealerEmployeeId(), userAuthentication.getDealerId(),
                spareInvoiceSearch.getPage(), size,userAuthentication.getUsername(),'N',0L,"Details", spareInvoiceSearch.getWcrNo(), spareInvoiceSearch.getJobCardNumber());
        
    	ByteArrayInputStream in = ExcelCellGenerator.sparePartSalesReport(list);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "SparePartSalesReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
}