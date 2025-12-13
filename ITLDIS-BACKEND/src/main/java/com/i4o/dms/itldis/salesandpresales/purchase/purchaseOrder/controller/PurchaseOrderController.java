package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.controller;

import static com.i4o.dms.itldis.configurations.Constants.DRAFT;
import static com.i4o.dms.itldis.configurations.Constants.UNDER_KAI_APPROVAL;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.PurchaseOrder;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.SalesPoApproval;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.PoApproval;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.PurchaseOrderResponseDto;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.ResponsePoDto;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.SalesPoExcelResponse;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.SalesPoSearchDto;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.repository.PurchaseOrderRepo;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.repository.SalesPoApprovalRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.service.PurchaseOrderService;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;
import com.i4o.dms.itldis.warranty.pcr.dto.WarrantyPcrSearchDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/purchaseOrder")
@Slf4j
public class PurchaseOrderController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private PurchaseOrderRepo purchaseOrderRepo;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;
    
    @Autowired
    private SalesPoApprovalRepository salesPoApprovalRepository;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private StorageService storageService;

//    @PostMapping(value = "/savePurchaseOrder")
//    public ResponseEntity<?> savePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
//        ApiResponse apiResponse = new ApiResponse();
//        purchaseOrder.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
//        purchaseOrder.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
//        purchaseOrder.setLastModifiedBy(purchaseOrder.getDealerEmployeeMaster());
//        purchaseOrder.setPoStatus(purchaseOrder.isDraftMode() ? DRAFT : UNDER_KAI_APPROVAL);
//        purchaseOrderRepo.save(purchaseOrder);
//        apiResponse.setMessage("Purchase Order saved successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setId(purchaseOrder.getId());
//        return ResponseEntity.ok(apiResponse);
//    }

    @PostMapping(value = "/savePurchaseOrder", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> savePurchaseOrder(@RequestPart(value = "purchaseOrder") PurchaseOrder purchaseOrder,
                                                @RequestPart(value = "chequeLeaf", required = false) MultipartFile chequeLeaf,
                                                @RequestPart(value = "coveringLetter", required = false) MultipartFile coveringLetter,
                                                @RequestPart(value = "creditApplication", required = false) MultipartFile creditApplication,
                                                @RequestPart(value = "chequeCopy", required = false) MultipartFile chequeCopy) {

        ApiResponse apiResponse = new ApiResponse();
        purchaseOrder.setPoDate(new Date());
        purchaseOrder.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        purchaseOrder.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        purchaseOrder.setLastModifiedBy(userAuthentication.getLoginId());
        purchaseOrder.setPoStatus(purchaseOrder.isDraftMode() ? DRAFT : UNDER_KAI_APPROVAL);

        if (chequeLeaf != null ) {
            String cheque = chequeLeaf.getOriginalFilename();
            String chequeAttachment = "sales_purchase_order" +"_"+ System.currentTimeMillis() + "_" + cheque;
            storageService.store(chequeLeaf, chequeAttachment);
            purchaseOrder.setChequeLeaf(chequeAttachment);
        }
        if (coveringLetter != null) {
            String covering = coveringLetter.getOriginalFilename();
            String coveringAttachment = "sales_purchase_order" +"_"+ System.currentTimeMillis() + "_" + covering;
            storageService.store(coveringLetter, coveringAttachment);
            purchaseOrder.setCoveringLetter(coveringAttachment);
        }
        if (creditApplication != null) {
            String credit = creditApplication.getOriginalFilename();
            String creditAttachment = "sales_purchase_order" +"_"+ System.currentTimeMillis() + "_" + credit;
            storageService.store(creditApplication, creditAttachment);
            purchaseOrder.setCreditApplication(creditAttachment);
        }
        //Suraj-10-07-2023--Start
        if (chequeCopy != null) {
            String chequeCpy = chequeCopy.getOriginalFilename();
            String chequeCopyAttachment = "sales_purchase_order" +"_"+ System.currentTimeMillis() + "_" + chequeCpy;
            storageService.store(chequeCopy, chequeCopyAttachment);
            purchaseOrder.setChequeCopy(chequeCopyAttachment);
        }
        //Suraj-10-07-2023--End
        purchaseOrder.setCreatedBy(userAuthentication.getLoginId());
        purchaseOrderRepo.save(purchaseOrder);
        if(!purchaseOrder.isDraftMode()){
        	List<SalesPoApproval> approvalList = new ArrayList<>();
            salesPoApprovalRepository.getApprovalHierarchyLevel(userAuthentication.getDealerId(), purchaseOrder.getId()).forEach(map -> {
                SalesPoApproval approval = new SalesPoApproval();
                approval.setPurchaseOrderId(purchaseOrder.getId());
                approval.setApproverLevelSeq((Integer)map.get("approver_level_seq"));
                approval.setDesignationLevelId((BigInteger)map.get("designation_level_id"));
                approval.setGrpSeqNo((Integer)map.get("grp_seq_no"));
                approval.setIsfinalapprovalstatus((Character)map.get("isFinalApprovalStatus"));
                approval.setApprovalStatus((String)map.get("approvalStatus"));
                approval.setRejectedFlag('N');
                approvalList.add(approval);
            });
            salesPoApprovalRepository.saveAll(approvalList);
        }
        apiResponse.setMessage("Purchase Order saved successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setId(purchaseOrder.getId());
        return ResponseEntity.ok(apiResponse);
    }

    
    @SuppressWarnings("unchecked")
	@GetMapping(value = "/searchBy")
    public ResponseEntity<?> searchBy(@RequestParam(required = false) String poNumber,
                                      @RequestParam(required = false) String poType,
                                      @RequestParam(required = false) String depot,
                                      @RequestParam(required = false) String itemNo,
                                      @RequestParam(required = false) String fromDate,
                                      @RequestParam(required = false) String toDate,
                                      @RequestParam(required = false) String poStatus,
                                      @RequestParam(required = false) String product,
                                      @RequestParam(required = false) String series,
                                      @RequestParam(required = false) String model,
                                      @RequestParam(required = false) String subModel,
                                      @RequestParam(required = false) String variant,
                                      @RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer size,
                                      @RequestParam(required = false) Long hierId,
                                      @RequestParam(required = false) Long dealerId) {

    	
        ApiResponse apiResponse = new ApiResponse();
        if(userAuthentication.getDealerId()!=null)
        	dealerId = userAuthentication.getDealerId();
        
        List<ResponsePoDto> results = purchaseOrderRepo
                .searchBy(poNumber, poType, depot, itemNo, fromDate, toDate, poStatus, product, series, model,
                        subModel, variant, dealerId, userAuthentication.getKubotaEmployeeId(),
                        userAuthentication.getDealerEmployeeId(), userAuthentication.getManagementAccess(), page, size, userAuthentication.getUsername(),'N',hierId);
                
        apiResponse.setMessage("purchase order get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(results);
        Long count = 0L;
        if(results!=null && results.size()>0){
        	count = results.get(0).getTotalCount();
        }
        apiResponse.setCount(count);
       /* apiResponse.setCount(purchaseOrderRepo.searchCount(
                poNumber, poType, depot, itemNo, fromDate, toDate, poStatus, product, series, model,
                subModel, variant, userAuthentication.getDealerId(), userAuthentication.getKubotaEmpl
                oyeeId(),
                userAuthentication.getDealerEmployeeId(), userAuthentication.getManagementAccess()));*/
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/searchPoNumber")
    public ResponseEntity<?> searchPoNumber(@RequestParam(required = false) String poNumber) {

        ///Long dealerId=1L; //Static value for fetching data and will be changed after token implementation
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("po number get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(purchaseOrderRepo.searchPoNumber(poNumber, userAuthentication.getDealerId(), userAuthentication.getUsername(), 'N'));
        return ResponseEntity.ok(apiResponse);
    }

//    @GetMapping(value = "/getpurchaseorderbyid/{id}")
//    public ResponseEntity<?> getpurchaseorderbyid(@PathVariable Long id) {
//        PurchaseOrder purchaseOrder = purchaseOrderRepo.findById(id).get();
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("po get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(purchaseOrderService.getPurchaseOrderById(purchaseOrder,userAuthentication.getKubotaEmployeeId()));
//        return ResponseEntity.ok(apiResponse);
//    }

    @GetMapping(value = "/getpurchaseorder/{id}")
    public ResponseEntity<?> getpurchaseorder(@PathVariable Long id) {
        PurchaseOrderResponseDto purchaseOrder = purchaseOrderRepo.findByPoNumber(purchaseOrderRepo.findById(id).get().getPoNumber());
        for(PurchaseOrderResponseDto.PurchaseOrderMachineDetails purchaseMachine:purchaseOrder.getMachineDetails())
        {
        	if(purchaseMachine.getDiscountAmount()==null)
        	{
        		purchaseMachine.setDiscountAmount(BigDecimal.ZERO);
        	}
        	if(purchaseMachine.getDiscountPercentage()==null)
        	{
        		purchaseMachine.setDiscountPercentage(BigDecimal.ZERO);
        	}
        }
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Data fetched successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        // apiResponse.setResult(purchaseOrder);
        apiResponse.setResult(purchaseOrderService.getPurchaseOrderById(purchaseOrder, userAuthentication.getKubotaEmployeeId()));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/approvePurchaseOrder")
    public ResponseEntity<?> approvePurchaseOrder(@RequestBody PoApproval poApproval) {
        ApiResponse apiResponse = new ApiResponse();
        poApproval.setUserId(userAuthentication.getKubotaEmployeeId());
        String success = purchaseOrderService.poApproval(poApproval, userAuthentication.getUsername());
        if(success!=null && !success.equalsIgnoreCase("FAIL")){
        	apiResponse.setMessage(poApproval.getApprovalFlag().equalsIgnoreCase("APPROVE") ? "Po Approved successfully" : 
        		poApproval.getApprovalFlag().equalsIgnoreCase("REJECT") ? "Po rejected" : "PO on hold");
        }else{
        	apiResponse.setMessage("APPROVAL/REJECTION/HOLD FAILED");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getPoDiscountType")
    public ResponseEntity<?> getPoDiscountType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("getPoDiscountType.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(purchaseOrderRepo.getPoDiscountType());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSalesPoApprovalDetails")
    public ResponseEntity<?> getSalesPoApprovalDetails(@RequestParam Long poId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Sales Po Approval Details.");
        apiResponse.setStatus(HttpStatus.OK.value());
        log.info("userAuthentication.getKubotaEmployeeId():" + userAuthentication.getKubotaEmployeeId());
        if (userAuthentication.getKubotaEmployeeId() != null)
            apiResponse.setResult(purchaseOrderRepo.getSalesPoApprovalDetails(poId, userAuthentication.getKubotaEmployeeId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getChannelFinanceAvailable")
    public ResponseEntity<?> getChannelFinanceAvailable(@RequestParam String dealerCode) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("channel finance available");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(purchaseOrderRepo.getChannelFinanceAvailable(dealerCode));
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping(value = "/tcsPercList")
    public ResponseEntity<?> tcsPercList(){
    	ApiResponse apiResponse = new ApiResponse();        
        List<Map<String, Object>> tcsList = purchaseOrderRepo.getTcsList();
        apiResponse.setResult(tcsList);
        apiResponse.setMessage("TCS Perc List");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/downloadPoExcelReport")
    public ResponseEntity<InputStreamResource> poExcelReport(@RequestBody SalesPoSearchDto poExcel, HttpServletResponse response) throws IOException{

    	/*List<SalesPoExcelResponse> results = purchaseOrderRepo.poReport(poExcel.poNumber, poExcel.poType, poExcel.depot, poExcel.itemNo, poExcel.fromDate,
        		poExcel.getToDate(), poExcel.getPoStatus(), poExcel.getProduct(), poExcel.getSeries(), poExcel.getModel(),poExcel.getSubModel(),
        		poExcel.getVariant(), poExcel.getDealerId(), userAuthentication.getKubotaEmployeeId(),userAuthentication.getDealerEmployeeId(),
        		userAuthentication.getManagementAccess(),userAuthentication.getUsername(),'N',poExcel.getHierId());*/
    	
    	//Suraj--09/11/202
    	if(userAuthentication.getDealerId()!=null)
    		poExcel.setDealerId(userAuthentication.getDealerId());
        List<ResponsePoDto> results = purchaseOrderRepo
                .searchBy(poExcel.getPoNumber(), poExcel.getPoType(), poExcel.getDepot(), poExcel.getItemNo(), 
                		poExcel.getFromDate(), poExcel.getToDate(), poExcel.getPoStatus(), poExcel.getProduct(), 
                		poExcel.getSeries(), poExcel.getModel(), poExcel.getSubModel(), poExcel.getVariant(), 
                		poExcel.getDealerId(), userAuthentication.getKubotaEmployeeId(), userAuthentication.getDealerEmployeeId(), 
                		userAuthentication.getManagementAccess(), poExcel.getPage(), poExcel.getSize(), 
                		userAuthentication.getUsername(),'N',poExcel.getHierId());
    	
    	ByteArrayInputStream in = ExcelCellGenerator.salesPoExcelReport1(results);
        //Suraj--09/11/202
    	
    	//ByteArrayInputStream in = ExcelCellGenerator.salesPoExcelReport(results);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "Sales_PO_Report_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    	
    }
    
    /**
     * @author suraj.gaur
     * @apiNote This api is for getting TCS value if deaer is allowed to take TCS.
     */
    @GetMapping("/getDealerTcsValue")
    public ResponseEntity<?> getDealerTcsValue(){
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Type of Delivery Get Successfully.");
        apiResponse.setResult(purchaseOrderRepo.getDealerTcsValue(userAuthentication.getDealerId()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author suraj.gaur
     * @apiNote This api is for getting type of delivery
     */
//    @GetMapping("/getTypeOfDelivery")
//    public ResponseEntity<?> getTypeOfDelivery(){
//    	ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("Type of Delivery Get Successfully.");
//        apiResponse.setResult(purchaseOrderRepo.getTypeOfDelivery());
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return ResponseEntity.ok(apiResponse);
//    }
    
    @GetMapping(value = "/getPoPendingForApproval")
    public ResponseEntity<?> getPoPendingForApproval(){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		ApiResponse<?> apiResponse = purchaseOrderService.getPoPendingForApproval();
    		apiResponse.setMessage("Get PO Pending For Approval Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			return ResponseEntity.ok(apiResponse);
			
    	}catch(Exception e){
    		ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getPoPendingForApproval Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
    	}
    }
    
 
    
    @PostMapping("/purchaseOrderGroupApproval")
    public ResponseEntity<?> purchaseOrderGroupApproval(@Valid @RequestBody List<PoApproval> poApprovalList){
    	try {
	    	ApiResponse<?> apiResponse = purchaseOrderService.purchaseOrderGroupApproval(poApprovalList, userAuthentication.getUsername());
	        apiResponse.setMessage("Group PO have been Approved");
	        apiResponse.setStatus(HttpStatus.OK.value());
	        return ResponseEntity.ok(apiResponse);
    	}catch(Exception e){
    		ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/purchaseOrderGroupApproval Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
    	}
    }
}

//{ "id": 108,  "poType": "CASH",   "depot": "Chennai", "pendingOrder":12,  "totalQuantity": 1,   "totalCreditLimit": 12000000,   "channelFinanceAvailable": 23232,   "orderUnderProcess":123.00,   "availableLimit": 4633520.2,   "os31To60Days": 0,   "os61To90Days": 0,   "os0To30Days": 103120.06,   "netOs": 7572719.92,   "paymentPending": "0",   "os90Days": 0,   "totalOs": 7572719.92,   "currentOs": 7469599.86,   "exposureAmount": "59370124172.80",   "branchCode": null,   "draftMode": false,   "machineDetails": [   {      "id":135,   "machineMaster": {         "id": 27       },       "itemDescription": "Kubota Tractor B2420",       "variant": "STD",       "colour": "Red",       "quantity": 1,       "unitPrice": 7000,       "amount": 7000,       "isSelected": false,       "igst": 12     }   ],   "basicAmount": 7000,   "totalGstAmount": 840,   "totalAmount": 7840,   "dealerEmployeeMaster": {     "id": 61   } }