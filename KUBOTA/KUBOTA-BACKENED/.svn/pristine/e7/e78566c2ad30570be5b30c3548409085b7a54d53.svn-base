package com.i4o.dms.kubota.salesandpresales.sales.paymentReceipt.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.repository.CustomerMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.kubota.salesandpresales.sales.paymentReceipt.Repository.PaymentReceiptRepo;
import com.i4o.dms.kubota.salesandpresales.sales.paymentReceipt.domain.PaymentReceipt;
import com.i4o.dms.kubota.salesandpresales.sales.paymentReceipt.dto.PaymentReceiptList;
import com.i4o.dms.kubota.salesandpresales.sales.paymentReceipt.dto.SparePaymentReceiptResposne;
import com.i4o.dms.kubota.salesandpresales.sales.paymentReceipt.dto.SparePaymentReceiptSearchDto;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@ResponseBody
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/sales/paymentReceipt")
public class PaymentReceiptController {

    @Autowired
    private PaymentReceiptRepo paymentReceiptRepo;

    @Autowired
    private EnquiryRepo enquiryRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private CustomerMasterRepo customerMasterRepo;

    private Logger logger = LoggerFactory.getLogger(PaymentReceiptController.class);

    @PostMapping("addPayment")
    public ResponseEntity<?> addPayment(@RequestBody PaymentReceipt paymentReceipt) {
        ApiResponse apiResponse = new ApiResponse();

        paymentReceipt.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        //paymentReceipt.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        paymentReceipt.setLastModifiedBy(userAuthentication.getLoginId());
        paymentReceipt.setLastModifiedDate(new Date());
        paymentReceipt.setBranchId(userAuthentication.getBranchId());
        if (paymentReceipt.getEnquiry() != null) {
            if (paymentReceiptRepo.checkEnquiryInPaymentReceipt(paymentReceipt.getEnquiry().getId(), paymentReceipt.getReceiptType()) == 0) {
                paymentReceipt.setEnquiry(enquiryRepo.findById(paymentReceipt.getEnquiry().getId()).get());
                paymentReceiptRepo.save(paymentReceipt);
                apiResponse.setMessage("add payment receipt");
                apiResponse.setStatus(HttpStatus.OK.value());
            } else {
                apiResponse.setMessage("can not create payment receipt ");
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setResult("payment receipt for " + paymentReceipt.getReceiptType() + " already created");
            }
        }
        Long id = 0L;
        if (paymentReceipt.getCustomerMaster() != null) {
        	id=paymentReceipt.getCustomerMaster().getId();
        }
        else if (paymentReceipt.getPartyMaster() != null) {
        	id=paymentReceipt.getPartyMaster().getId();
        }
        else if (paymentReceipt.getCoDealer() != null) {
        	id=paymentReceipt.getCoDealer().getId();
        }
        if (paymentReceipt.getCustomerMaster() != null || paymentReceipt.getPartyMaster() != null || paymentReceipt.getCoDealer() != null) {
        	Optional<CustomerMaster> customerMaster =
                    customerMasterRepo.findById(id);
            if (customerMaster.isPresent()) {
                paymentReceipt.setCustomerMaster(customerMaster.get());
            } else {
                paymentReceipt.setCustomerMaster(null);
            }

            paymentReceiptRepo.save(paymentReceipt);
            apiResponse.setMessage("payment receipt Saved....!");
            apiResponse.setStatus(HttpStatus.OK.value());
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getBank")
    public ResponseEntity<?> getBank() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get bank");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getBank());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getCardType")
    public ResponseEntity<?> getCardType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get card type");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getCardType());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getCheckDdBank")
    public ResponseEntity<?> getCheckDdBank() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get get check dd bank");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getCheckDdBank());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getReceiptMode")
    public ResponseEntity<?> getReceiptMode() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get receipt mode");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getReceiptMode());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getReceiptType")
    public ResponseEntity<?> getReceiptType(@RequestParam String enquiryNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get receipt type");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getReceiptType(enquiryNumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getAllReceiptType")
    public ResponseEntity<?> getAllReceiptType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get all receipt type");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getReceiptAllType());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getSparesReceiptType")
    public ResponseEntity<?> getSparesReceiptType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Spare Receipt type");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.sparesPaymentReceiptType());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getMarginAmount")
    public ResponseEntity<?> getMarginAmount(String enquiryNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get margin amount");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getMarginAmount(enquiryNo));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getSubsidyAmount")
    public ResponseEntity<?> getSubsidyAmount(String enquiryNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get subsidy amount");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getSubsidyAmount(enquiryNo));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getLoanAmount")
    public ResponseEntity<?> getLoanAmount(String enquiryNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get subsidy amount");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getLoanAmount(enquiryNo));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getExchangeAmount")
    public ResponseEntity<?> getExchangeAmount(String enquiryNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get exchange amount");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getExchangeAmount(enquiryNo));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getEnquiryPayment")
    public ResponseEntity<?> getEnquiryPayment(String enquiryNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get exchange amount");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getEnquiryOnPayment(enquiryNo));
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getPaymentReceiptList")
    public ResponseEntity<?> getPaymentReceiptList(
            //@RequestParam(required = false) Long userId,
            @RequestParam(required = false) String receiptNo,
            @RequestParam(required = false) String receiptMode,
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String customerMobileNo,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String series,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String subModel,
            @RequestParam(required = false) String variant,
            @RequestParam(required = false) String itemNo,
            @RequestParam(required = false) String receiptType,
            @RequestParam Integer page,
            @RequestParam Integer size) {
        ApiResponse apiResponse = new ApiResponse();
        System.out.println(receiptNo+":"+receiptMode+":"+customerName+":"+customerMobileNo+":"+fromDate+":"+toDate+":"+product+":"+series+":"+model+":"+subModel+":"+variant+":"+itemNo+":"+receiptType);
        List<PaymentReceiptList> paymentReceiptList = paymentReceiptRepo.getPaymentReceiptList(userAuthentication.getManagementAccess(), userAuthentication.getDealerId(), userAuthentication.getKubotaEmployeeId(), userAuthentication.getDealerEmployeeId(), 
        		userAuthentication.getBranchId(),
        		receiptNo, receiptMode, customerName, customerMobileNo, fromDate, toDate, product, series, model, subModel, variant, itemNo,
        		receiptType,
        		userAuthentication.getUsername(),
        		'N',
        		page, size);
        
        for(PaymentReceiptList p:paymentReceiptList) {
        	System.out.println(p.getReceiptNumber()+"==="+p.getBankName());
        }
        
        
        Long count = 0L;
        //paymentReceiptRepo.getPaymentReceiptListCount(userAuthentication.getManagementAccess(), userAuthentication.getDealerId(), userAuthentication.getKubotaEmployeeId(), userAuthentication.getDealerEmployeeId(), receiptNo, receiptMode, customerName, customerMobileNo, fromDate, toDate, product, series, model, subModel, variant, itemNo, page, size);
        if(paymentReceiptList!=null && paymentReceiptList.size()>0){
        	count = paymentReceiptList.get(0).getTotalCount();
        }
        apiResponse.setMessage("get payment receipt List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setCount(count);
        apiResponse.setResult(paymentReceiptList);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("searchByReceiptNumber")
    public ResponseEntity<?> getReceiptNumber(@RequestParam String receiptNumber,
                                              @RequestParam(required = false) String documentType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get receipt number");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.getReceiptNumber(receiptNumber, documentType, userAuthentication.getUsername(),
        		userAuthentication.getBranchId(),'N'));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getPaymentReceiptByReceiptNumber")
    public ResponseEntity<?> getPaymentReceiptById(@RequestParam String receiptNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Payment Receipt By receipt number");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.findByReceiptNo(receiptNo));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("searchByCustomerName")
    public ResponseEntity<?> searchByCustomerName(@RequestParam String customerName,
                                                  @RequestParam(required = false) String documentType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get customer name");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.searchCustomerName(customerName, documentType));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("searchByMobileNumber")
    public ResponseEntity<?> searchByMobileNumber(@RequestParam String mobileNumber,
                                                  @RequestParam(required = false) String documentType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get mobile Number");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.searchMobileNumber(mobileNumber, documentType));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("searchPaymentReceiptForSpare")
    public ResponseEntity<?> searchPaymentReceiptForSpare(@RequestBody SparePaymentReceiptSearchDto searchDto) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Spare Payment Receipt Search ");
        apiResponse.setStatus(HttpStatus.OK.value());        
        List<SparePaymentReceiptResposne> sparesPaymentReceiptSearchList=paymentReceiptRepo.sparesPaymentReceiptSearch(searchDto.getReceiptNumber(),
                searchDto.getReceiptMode(),
                searchDto.getCustomerName(),
                searchDto.getCustomerContactNumber(),
                searchDto.getFromDate(),
                searchDto.getToDate(),
                userAuthentication.getManagementAccess(),
                userAuthentication.getDealerId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getDealerEmployeeId(),
                searchDto.getPage(),
                searchDto.getSize(),
                userAuthentication.getUsername(),'N');
        
        Long count = 0L;
        if(sparesPaymentReceiptSearchList!=null && sparesPaymentReceiptSearchList.size()>0){
        	count = sparesPaymentReceiptSearchList.get(0).getTotalCount();
        }
        apiResponse.setResult(sparesPaymentReceiptSearchList);
        apiResponse.setCount(count);
//        apiResponse.setCount(paymentReceiptRepo.sparesPaymentReceiptSearchCount(searchDto.getReceiptNumber(),
//                searchDto.getReceiptMode(),
//                searchDto.getCustomerName(),
//                searchDto.getCustomerContactNumber(),
//                searchDto.getFromDate(),
//                searchDto.getToDate(),
//                userAuthentication.getManagementAccess(),
//                userAuthentication.getDealerId(),
//                userAuthentication.getKubotaEmployeeId(),
//                userAuthentication.getDealerEmployeeId(),
//                searchDto.getPage(),
//                searchDto.getSize()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getSparePaymentReceiptById/{id}")
    public ResponseEntity<?> getSparePaymentReceiptById(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Payment Receipt View Details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(paymentReceiptRepo.sparePaymentReceiptView(id, userAuthentication.getBranchId()));
        return ResponseEntity.ok(apiResponse);
    }

   
}
