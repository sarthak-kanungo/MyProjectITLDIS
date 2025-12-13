package com.i4o.dms.itldis.salesandpresales.sales.invoice.controller;

import static com.i4o.dms.itldis.utils.Constants.MESSAGE;
import static com.i4o.dms.itldis.utils.Constants.RESULT;
import static com.i4o.dms.itldis.utils.Constants.STATUS;
import static com.i4o.dms.itldis.utils.Constants.SUCCESS;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.i4o.dms.itldis.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto.SalesInvoiceDto;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.repository.DeliveryChallanRepositroy;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.domain.SalesInvoice;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.dto.CancelInvoiceDto;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.dto.DcAndCustomerDetailsDto;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.dto.SearchInvoiceApprovalResponse;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.dto.SearchInvoiceDto;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.dto.SearchInvoiceResponse;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.repository.InvoiceCancellationRepository;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.repository.InvoiceRepository;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.repository.SalesInvoiceCancelApprovalRepository;
import com.i4o.dms.itldis.salesandpresales.sales.invoice.service.SalesInvoiceService;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DeliveryChallanRepositroy deliveryChallanRepositroy;

    @Autowired
    private SalesInvoiceCancelApprovalRepository salesInvoiceCancelApprovalRepository;

    @Autowired
    InvoiceCancellationRepository invoiceCancellationRepository;
    
    @Autowired
    private SalesInvoiceService salesInvoiceService;
    
    @Autowired
    private EnquiryRepo enquiryRepository;

    @GetMapping(value = "/getBankName")
    public ResponseEntity<?> getBankName(@RequestParam("bankName")String bankName) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> bankMaster = invoiceRepository.getBankName(bankName);
        map.put(MESSAGE, "Bank Name List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, bankMaster);
        return ResponseEntity.ok(map);
    }


    @GetMapping(value = "/getInvoiceType")
    public ResponseEntity<?> getInvoiceType() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> invoiceType = invoiceRepository.getInvoiceType();
        map.put(MESSAGE, "Invoice Type List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, invoiceType);
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getInvoiceStatus")
    public ResponseEntity<?> getInvoiceStatus() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(" get invoice status");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(invoiceRepository.getInvoiceStatus());
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/getGstValue")
    public ResponseEntity<?> getgstDropDown() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> gstValue = invoiceRepository.getGstValue();
        map.put(MESSAGE, "Gst values  List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, gstValue);
        return ResponseEntity.ok(map);
    }


    @GetMapping(value = "/getCustomerCodeAutoComplete")
    public ResponseEntity<?> getCustomerCodeAutoComplete(@RequestParam("customerCode") String customerCode,
    		@RequestParam(value="invoiceType", required=false)String invoiceType) {
        Map<String, Object> map = new HashMap<>();
        Set<Map<String, Object>> customerCodeAutoComplete = invoiceRepository.getCustomerCodeAutoComplete(customerCode,
                userAuthentication.getBranchId(),
                invoiceType);
        map.put(MESSAGE, "Customer Auto List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, customerCodeAutoComplete);
        return ResponseEntity.ok(map);
    }



    @GetMapping(value = "/getDcAndCustomerDetailsByCustomerCode")
    public ResponseEntity<?> getDcDetailsByCustomerCode(@RequestParam("customerCode") String customerCode) {
        Map<String, Object> map = new HashMap<>();

        DcAndCustomerDetailsDto dcAndCustomerDetailsDto=new DcAndCustomerDetailsDto();

        dcAndCustomerDetailsDto.setCustomerDetails(invoiceRepository.getCustomerDetailsByCustomerCode(customerCode,userAuthentication.getBranchId()));
        dcAndCustomerDetailsDto.setDcDetails(invoiceRepository.getDcDetailsByCustomerCode(customerCode, userAuthentication.getBranchId()));

        map.put(MESSAGE, "Dc and Customer Details List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, dcAndCustomerDetailsDto);
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getMaterialDetailsByDcId")
    public ResponseEntity<?> getMaterialDetailsByDcId(@RequestParam("dcId") String dcId) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> materialDetails  = invoiceRepository.getMaterialDetailsByDcId(dcId);
        map.put(MESSAGE, "Dc Details List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, materialDetails);
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getInvoiceCancellationType")
    public ResponseEntity<?> getInvoiceCancellationType() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> getInvoiceCancellationType  = invoiceRepository.getInvoiceCancellationType();
        map.put(MESSAGE, " Invoice Cancellation Type List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, getInvoiceCancellationType);
        return ResponseEntity.ok(map);
    }


    @GetMapping(value = "/getInvoiceCancellationReason")
    public ResponseEntity<?> getInvoiceCancellationReason() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> getInvoiceCancellationReason  =
                invoiceRepository.getInvoiceCancellationReason();
        map.put(MESSAGE, " Invoice Cancellation Reason List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, getInvoiceCancellationReason);
        return ResponseEntity.ok(map);
    }


    @GetMapping(value = "/getInvoiceCancellationOtherReason")
    public ResponseEntity<?> getInvoiceCancellationOtherReason() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> getInvoiceCancellationOtherReason  =
                invoiceRepository.getInvoiceCancellationOtherReason();
        map.put(MESSAGE, " Invoice Cancellation other Reason List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, getInvoiceCancellationOtherReason);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/saveSalesInvoice")
    public ResponseEntity<?> saveInvoice(@RequestBody SalesInvoice salesInvoice){
        salesInvoice.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        salesInvoice.setBranchId(userAuthentication.getBranchId());
        salesInvoice.setCreatedBy(userAuthentication.getLoginId());
        salesInvoice.setInvoiceDate(new Date());
        salesInvoice.setCreatedDate(new Date());
        
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("save invoice");
        apiResponse.setStatus(HttpStatus.OK.value());
        SalesInvoice invoice = invoiceRepository.save(salesInvoice);
        if(salesInvoice.getInvoiceType().equalsIgnoreCase("Dealer Transfer Invoice")){
        	invoiceRepository.callCsbTransfer(salesInvoice.getId());
        }
        if(invoice!=null && invoice.getId()!=null){
        	try{
        		invoiceRepository.updateDcStatus(invoice.getId());
        	}catch(Exception ex){
        		ex.printStackTrace();
        	}
        }
        apiResponse.setResult(salesInvoice.getInvoiceNumber());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchInvoice")
    public ResponseEntity<?> searchInvoice(@RequestBody SearchInvoiceDto searchInvoiceDto){
        ApiResponse apiResponse = new ApiResponse();
        
        List<SearchInvoiceResponse> result = invoiceRepository.searchInvoice(searchInvoiceDto.getInvoiceNumber(),searchInvoiceDto.getChassisNo(),searchInvoiceDto.getCustomerName(),
                searchInvoiceDto.getMobileNo(),searchInvoiceDto.getFromDate(),searchInvoiceDto.getToDate(),searchInvoiceDto.getEnquiryNumber(),searchInvoiceDto.getEnquiryType(),
                searchInvoiceDto.getInvoiceStatus(),searchInvoiceDto.getInvoiceType(),searchInvoiceDto.getProduct(),searchInvoiceDto.getModel(),
                searchInvoiceDto.getSeries(),searchInvoiceDto.getVariant(),searchInvoiceDto.getItemNo(),searchInvoiceDto.getEngineNo(),
                userAuthentication.getDealerId(),userAuthentication.getDealerEmployeeId(),userAuthentication.getKubotaEmployeeId(),userAuthentication.getManagementAccess(),
                searchInvoiceDto.getPage(),searchInvoiceDto.getSize(),userAuthentication.getUsername(), 'N', 0l);
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        apiResponse.setMessage("search Invoice");
        apiResponse.setResult(result);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("searchInvoiceApproval")
    public ResponseEntity<?> searchInvoiceApproval( @RequestParam(name="Page")Integer page, @RequestParam(name="Size")Integer size){
        ApiResponse apiResponse = new ApiResponse();
        
        List<SearchInvoiceApprovalResponse> result = invoiceRepository.searchInvoiceApproval(userAuthentication.getUsername(), 'N', 0l, page, size);
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        apiResponse.setMessage("search Invoice");
        apiResponse.setResult(result);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getInvoiceById/{invoiceId}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long invoiceId){
        ApiResponse apiResponse = new ApiResponse();
        SalesInvoiceDto invoiceDto = new SalesInvoiceDto();
        invoiceDto.setInvoice(invoiceRepository.getSalesInvoiceById(invoiceId));
        invoiceDto.setDeliveryChallanDetails(invoiceRepository.getDeliveryChallanDetailsByInvoiceId(invoiceId));
        invoiceDto.setMaterialDetails(invoiceRepository.getMaterialsDetailsByInvoiceId(invoiceId));
        invoiceDto.setCustomerDetails(invoiceRepository.getCustomerDetailsByInvoiceId(invoiceId));
        invoiceDto.setInsuranceCompanyDetails(invoiceRepository.getInsuranceCompanyDetailsByInvoiceId(invoiceId));
        invoiceDto.setApprovalHier(salesInvoiceCancelApprovalRepository.getApprovalHierarchy(invoiceId, userAuthentication.getUsername()));
        invoiceDto.setInvoiceCancelRequest(invoiceCancellationRepository.findByInvoiceId(invoiceId));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Invoice");
        apiResponse.setResult(invoiceDto);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/cancelInvoice")
    public ResponseEntity<?> cancelInvoice(@RequestBody CancelInvoiceDto invoiceDto){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(salesInvoiceService.cancelInvoice(invoiceDto));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(" cancel Invoice");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/approveInvoice")
    public ResponseEntity<?> approveInvoice(@RequestParam(name="invoiceId") Long invoiceId,
    		@RequestParam(name="remark") String remark,
    		@RequestParam(name="flag") String flag){
        ApiResponse apiResponse = new ApiResponse();
        String message = salesInvoiceCancelApprovalRepository.approveCancelInvoice(invoiceId,userAuthentication.getKubotaEmployeeId(),
        		remark,userAuthentication.getUsername(),flag);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(message);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/searchByInvoiceNumber")
    public ResponseEntity<?> searchByInvoiceNumber(@RequestParam String invoiceNumber){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(" search Invoice by no");
        apiResponse.setResult(invoiceRepository.searchInvoiceByInvoiceNumber(invoiceNumber,userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchByChassisNumber")
    public ResponseEntity<?> searchByChassisNumber(@RequestParam String chassisNumber){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(" search Invoice by no");
        apiResponse.setResult(invoiceRepository.searchInvoiceByChassisNumber(chassisNumber,userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchByCustomerName")
    public ResponseEntity<?> searchByCustomerName(@RequestParam String customerName){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(" search Invoice by no");
        apiResponse.setResult(invoiceRepository.searchInvoiceByCustomerName(customerName,userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchByMobileNumber")
    public ResponseEntity<?> searchByMobileNumber(@RequestParam String mobileNumber){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(" search Invoice by no");
        apiResponse.setResult(invoiceRepository.searchInvoiceByMobileNumber(mobileNumber,userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchByEnquiryNumber")
    public ResponseEntity<?> searchByEnquiryNumber(@RequestParam String enquiryNumber){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(" search Invoice by no");
        apiResponse.setResult(enquiryRepository.getEnquiryNumberName(enquiryNumber,userAuthentication.getManagementAccess(),
        		userAuthentication.getDealerId(),
        		userAuthentication.getBranchId(),
        		userAuthentication.getKubotaEmployeeId(),
        		userAuthentication.getDealerEmployeeId(),
        		userAuthentication.getUsername(),
        		"INVOICE_SEARCH"));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchByEngineNumber")
    public ResponseEntity<?> searchByEngineNumber(@RequestParam String engineNumber){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(" search Invoice by no");
        apiResponse.setResult(invoiceRepository.searchInvoiceByEngineNumber(engineNumber,userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

}

