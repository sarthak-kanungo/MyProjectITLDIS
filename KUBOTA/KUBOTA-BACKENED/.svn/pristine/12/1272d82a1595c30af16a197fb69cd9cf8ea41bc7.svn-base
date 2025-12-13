package com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.controller;

import static com.i4o.dms.kubota.configurations.Constants.COUNT;
import static com.i4o.dms.kubota.utils.Constants.MESSAGE;
import static com.i4o.dms.kubota.utils.Constants.RESULT;
import static com.i4o.dms.kubota.utils.Constants.STATUS;
import static com.i4o.dms.kubota.utils.Constants.SUCCESS;
import static com.i4o.dms.kubota.utils.Constants.FAIL;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import com.i4o.dms.kubota.masters.dealermaster.customermaster.repository.CustomerMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.salesandpresales.grn.repository.MachineInventoryRepository;
import com.i4o.dms.kubota.salesandpresales.sales.allotment.repository.MachineAllotmentRepository;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.domain.DeliveryChallan;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.ApproveDcDto;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.DcCancelRequestDto;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.DcSearchRequestDto;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.DeliveryChallanEditResponse;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.DeliveryChallanEditResponseDto;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.MachineAllotmentDto;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.MachineDetailsResponse;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.SearchByEnquiryNumber;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.SearchByRequestNumber;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.SearchDeliveryChallanCancelApproval;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto.SearchDeliveryChallanResponse;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.repository.DeliveryChallanRepositroy;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.repository.InsuranceCompanyMasterRepository;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.repository.SalesDcApprovalRepository;
import com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.service.DeliveryChallanService;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.inventorymanagement.btbt.dto.SpareBinTransferSearchDto;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.ExcelCellGenerator;


@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/deliveryChallan")
public class DeliveryChallanController {

    @Autowired
    private DeliveryChallanRepositroy deliveryChallanRepositroy;
    
    @Autowired
    private SalesDcApprovalRepository salesDcApprovalRepository;

    @Autowired
    private InsuranceCompanyMasterRepository insuranceCompanyMasterRepository;

    @Autowired
    private CustomerMasterRepo customerMasterRepo;

    @Autowired
    private DeliveryChallanService deliveryChallanService;

    private Logger logger = LoggerFactory.getLogger(DeliveryChallanController.class);

    @Autowired
    private MachineInventoryRepository machineInventoryRepository;

    @Autowired
    private MachineAllotmentRepository machineAllotmentRepository;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @PostMapping(value = "/addDeliveryChallan")
    public ResponseEntity<?> addDeliveryChallan(@Valid @RequestBody DeliveryChallan deliveryChallan) {
        Map<String, Object> map = new HashMap<>();
        deliveryChallan.setCreatedBy(userAuthentication.getLoginId());
        deliveryChallan.setBranchId(userAuthentication.getBranchId());
        deliveryChallan.setDeliveryChallanDate(new Date());
        deliveryChallan.setCreatedDate(new Date());
        deliveryChallan = deliveryChallanService.saveDc(deliveryChallan);
        if(deliveryChallan.getMessage()==null){
	        map.put(STATUS, SUCCESS);
	        map.put(MESSAGE, "DC Saved");
	        map.put(RESULT, deliveryChallan.getDeliveryChallanNumber());
        }else{
        	map.put(STATUS, FAIL);
	        map.put(MESSAGE, deliveryChallan.getMessage());
        }
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getDeliveryChallanType")
    public ResponseEntity<?> getDcType() {
        Map<String, Object> map = new HashMap<>();

        map.put(MESSAGE, "Dc Type List ");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, deliveryChallanRepositroy.getDcTypeList());
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getInsuranceCodeAutoComplete")
    public ResponseEntity<?> getInsuranceCodeAutoComplete(
            @RequestParam("companyCode") String companyCode) {
        Map<String, Object> map = new HashMap<>();

        map.put(MESSAGE, "Insurance Code List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, insuranceCompanyMasterRepository.getCompanyCodeAutoComplete(companyCode));
        return ResponseEntity.ok(map);
    }

    /**
     * Get Enquiry Number Auto Search Which Enquiry Number have No Dc Created
     * @param enquiryNumber
     * @return
     */

    @GetMapping(value = "/getEnquiryNumberAutoComplete")
    public ResponseEntity<?> getEnquiryNumberAutoComplete(@RequestParam("enquiryNumber") String enquiryNumber,
            @RequestParam(value = "dcType") String dcType) {
        Map<String, Object> map = new HashMap<>();
        map.put(MESSAGE, "Enquiry Number auto List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, deliveryChallanRepositroy.getEnquiryNumberAutoComplete(enquiryNumber, dcType, userAuthentication.getBranchId()));
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getInsuranceDetailsByCode")
    public ResponseEntity<?> getInsuranceDetailsByCode(
            @RequestParam("companyCode") String companyCode) {
        Map<String, Object> map = new HashMap<>();

        map.put(MESSAGE, "Insurance Details ");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, insuranceCompanyMasterRepository.findByCompanyCode(companyCode));
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getDcCancellationType")
    public ResponseEntity<?> getDcCancellationType() {
        Map<String, Object> map = new HashMap<>();

        map.put(MESSAGE, "Dc Cancellation Type List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, deliveryChallanRepositroy.getDcCancellationTypeList());
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getDcCancellationReason")
    public ResponseEntity<?> getDcCancellationReason() {
        Map<String, Object> map = new HashMap<>();

        map.put(MESSAGE, "Dc Cancellation Reason List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, deliveryChallanRepositroy.getDcCancellationReasonList());
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getDcCancellationOtherReason")
    public ResponseEntity<?> getDcCancellationOtherReason() {
        Map<String, Object> map = new HashMap<>();

        map.put(MESSAGE, "Dc Cancellation Other Reason List");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, deliveryChallanRepositroy.getDcCancellationOtherReasonList());
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getMachineDetailsByEnquiryNumber")
    public ResponseEntity<?> getMachineDetailsByEnquiryNumber(
            @RequestParam("enquiryNumber") String enquiryNumber) {
        Map<String, Object> map = new HashMap<>();

        SearchByEnquiryNumber searchByEnquiryNumber = new SearchByEnquiryNumber();

        searchByEnquiryNumber.setEnquiryProspectDetailsResponse(
                deliveryChallanRepositroy.getEnquiryProspectDetails(enquiryNumber,userAuthentication.getBranchId()));
        searchByEnquiryNumber.setMachineDetailsResponse(
                deliveryChallanRepositroy.machineDetails(enquiryNumber,userAuthentication.getBranchId()));
        searchByEnquiryNumber.setDeliverableChecklistResponse(
                deliveryChallanRepositroy.getDeliverableChecklist(enquiryNumber,userAuthentication.getBranchId()));

        map.put(MESSAGE, "Machine Details ");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, searchByEnquiryNumber);
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getDcById/{id}")
    public ResponseEntity<?> getDcById(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();

        DeliveryChallanEditResponseDto deliveryChallanEditResponseDto = new DeliveryChallanEditResponseDto();
        DeliveryChallanEditResponse deliveryChallan =
                deliveryChallanRepositroy.findByDeliveryChallanId(id);
        deliveryChallanEditResponseDto.setDeliveryChallanEditResponse(deliveryChallan);
        
        List<Map<String,Object>> approvalHier = salesDcApprovalRepository.getApprovalHierDetails(id, userAuthentication.getUsername());
        deliveryChallanEditResponseDto.setApprovalHier(approvalHier);
		/*
		 * deliveryChallanEditResponseDto.setAllotmentResponse(deliveryChallanRepositroy
		 * .getAllotmentDetailsByDcId(id)); if (deliveryChallan.getDcMachineDetail() !=
		 * null) { deliveryChallan.getDcMachineDetail().removeIf(
		 * DeliveryChallanEditResponse.DcMachineDetail::getDeleteFlag ); }
		 */
        map.put(MESSAGE, "Dc Details By Id");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, deliveryChallanEditResponseDto);
        return ResponseEntity.ok(map);

    }

    /*  @GetMapping(value = "/getDcByeId/{id}")
      public ResponseEntity<?> getDcByeId(@PathVariable Long id) {
          Map<String, Object> map = new HashMap<>();

          DeliveryChallan deliveryChallan =
                  deliveryChallanRepositroy.getOne(id);

          map.put(MESSAGE, "Dc  Detailss...... ");
          map.put(STATUS, SUCCESS);
          map.put(RESULT, deliveryChallan);
          return ResponseEntity.ok(map);

      }
  */

    @GetMapping(value = "/getItemDetailsByItemNumber")
    public ResponseEntity<?> getItemDetailsByItemNumber(@RequestParam String itemNumber) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> itemNo =
                deliveryChallanRepositroy.getItemDetailsByItemNumber(itemNumber, userAuthentication.getBranchId());
        map.put(MESSAGE, "Item Details ");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, itemNo);
        return ResponseEntity.ok(map);

    }

    @GetMapping(value = "/getDeliveryChallanNumberAutoComplete")
    public ResponseEntity<?> getDeliveryChallanAutoComplete(@RequestParam String dcNumber) {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> dcNo =
                deliveryChallanRepositroy.getDeleiveryChallanNumberAutocomplete(dcNumber, userAuthentication.getUsername());
        map.put(MESSAGE, "DC Number Auto list");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, dcNo);
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getDcChassisNumberAutoComplete")
    public ResponseEntity<?> getDcChassisNumberAutoComplete(@RequestParam String chassisNo) {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> chassisNumber =
                deliveryChallanRepositroy.getDcChassiNumberAutocomplete(chassisNo, userAuthentication.getUsername());
        map.put(MESSAGE, "Chassis Number Auto list");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, chassisNumber);
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getDcCustomerNameAutoComplete")
    public ResponseEntity<?> getDcCustomerNameAutoComplete(@RequestParam String customerName) {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> customerNm =
                deliveryChallanRepositroy.getDcCustomerNameAutoComplete(customerName, userAuthentication.getUsername());
        map.put(MESSAGE, "DC Customer Name Auto list");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, customerNm);
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getDcCustomerMobileNumberAutoComplete")
    public ResponseEntity<?> getDcCustomerMobileNumberAutoComplete(
            @RequestParam String customerMobileNumber
    ) {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> customerMobNo =
                deliveryChallanRepositroy.getDcCustomerMobileNumberAutoComplete(customerMobileNumber, userAuthentication.getUsername());
        map.put(MESSAGE, "DC Customer Mobile Number Auto list");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, customerMobNo);
        return ResponseEntity.ok(map);
    }

    /**
     * Enquiry Number Auto Search For Search Form
     *
     * @param enquiryNumber
     * @return
     */
   /* @GetMapping(value = "/getDcEnquiryNumberAutoComplete")
    public ResponseEntity<?> getDcEnquiryNumberAutoComplete(@RequestParam String enquiryNumber) {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> enNo =
                deliveryChallanRepositroy.getDcEnquiryNumberAutoComplete(enquiryNumber, userAuthentication.getBranchId());
        map.put(MESSAGE, "DC Enquiry Number Auto list");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, enNo);
        return ResponseEntity.ok(map);
    }*/

    @GetMapping(value = "/getDcStatusDropDown")
    public ResponseEntity<?> getDcStatusDropDown() {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> status =
                deliveryChallanRepositroy.getDcStatus();
        map.put(MESSAGE, "Dc Status list");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, status);
        return ResponseEntity.ok(map);

    }

    @GetMapping(value = "/getDcEngineNumberAutoComplete")
    public ResponseEntity<?> getDcEngineNumberAutoComplete(@RequestParam String engineNumber) {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> status =
                deliveryChallanRepositroy.getDcEngineNumberAutoComplete(engineNumber, userAuthentication.getUsername());
        map.put(MESSAGE, "Engine Number list");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, status);
        return ResponseEntity.ok(map);
    }

    @GetMapping(value="dcListForApproval")
    public ResponseEntity<?> getDCListForApproval(@RequestParam Integer page,
            @RequestParam Integer size){
    	Map<String, Object> map = new HashMap<>();
    	List<SearchDeliveryChallanCancelApproval> result = salesDcApprovalRepository.getDCListForApproval(userAuthentication.getUsername(), 'N', 0l, page, size);
    	Long searchCount = 0l;
        if(result!=null && result.size()>0){
        	searchCount = result.get(0).getRecordCount();
        }
        map.put(MESSAGE, "Search Dc list");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, result);
        map.put(COUNT, searchCount);
        return ResponseEntity.ok(map);
    }

    @PostMapping(value = "/dcSearch")
    public ResponseEntity<?> getDcSearch(@RequestBody DcSearchRequestDto dcSearchRequestDto) {
        Map<String, Object> map = new HashMap<>();
        Long dealerId;
        if(userAuthentication.getDealerId()==null) {
        	dealerId = dcSearchRequestDto.getDealerId();
        }else {
        	dealerId = userAuthentication.getDealerId();
        }

        List<SearchDeliveryChallanResponse> search = deliveryChallanRepositroy.searchDc(
                userAuthentication.getManagementAccess(),
                dealerId,
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getDealerEmployeeId(),
                dcSearchRequestDto.getDeliveryChallanNumber(),
                dcSearchRequestDto.getChassisNumber(), dcSearchRequestDto.getCustomerName(),
                dcSearchRequestDto.getCustomerMobileNumber(), dcSearchRequestDto.getDcFromDate(),
                dcSearchRequestDto.getDcToDate(), dcSearchRequestDto.getEnquiryNumber(),
                dcSearchRequestDto.getEnquiryType(), dcSearchRequestDto.getDcStatus(),
                dcSearchRequestDto.getProduct(), dcSearchRequestDto.getSeries(), dcSearchRequestDto.getModel(),
                dcSearchRequestDto.getSubModel(), dcSearchRequestDto.getVarient(),
                dcSearchRequestDto.getItemNumber(), dcSearchRequestDto.getEngineNumber(),
                dcSearchRequestDto.getPage(), dcSearchRequestDto.getSize(),
                userAuthentication.getUsername(), 'N', dcSearchRequestDto.getHierId()
        );

        Long searchCount = 0l;
        if(search!=null && search.size()>0){
        	searchCount = search.get(0).getRecordCount();
        }
        map.put(MESSAGE, "Search Dc list");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, search);
        map.put(COUNT, searchCount);
        return ResponseEntity.ok(map);
    }

    /**
     * **********************
     * Dc For Dealer Transfer
     * ***********************
     */

    @GetMapping(value = "/getDcRequestNumberAutoComplete")
    public ResponseEntity<?> getDcRequestNumberAutoComplete(@RequestParam String requestNumber) {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> requestNo =
                deliveryChallanRepositroy.getDcRequestNumberAutoComplete(requestNumber, userAuthentication.getBranchId());
        map.put(MESSAGE, "Request Number list");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, requestNo);
        return ResponseEntity.ok(map);

    }


    @GetMapping(value = "/getMachineAndDealerDetailsByRequestNumber")
    public ResponseEntity<?> getMachineAndDealerDetailsByRequestNumber(@RequestParam String requestNumber) {
        Map<String, Object> map = new HashMap<>();
        SearchByRequestNumber searchByRequestNumber = new SearchByRequestNumber();
        List<MachineDetailsResponse> machineDetailsResponseArrayList = deliveryChallanRepositroy.getMachineDetailsByRequestNumber(requestNumber, userAuthentication.getBranchId());
        searchByRequestNumber.setMachineDetailsResponse(machineDetailsResponseArrayList);
        searchByRequestNumber.setDealerDetails(deliveryChallanRepositroy.getDealerDetailsByRequestNumber(requestNumber));
        searchByRequestNumber.setAllotmentDetails(deliveryChallanRepositroy.getAllotmentDetailsByRequestNumber(requestNumber, userAuthentication.getBranchId()));

        map.put(MESSAGE, "Machine,Dealer and Allotment Details");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, searchByRequestNumber);
        return ResponseEntity.ok(map);
    }

    /**
     * Dc Cancel
     * ****************
     */

    @PostMapping(value = "/dcCancel")
    public ResponseEntity<?> dcCancel(@RequestBody DcCancelRequestDto dcCancelRequestDto) {
        Map<String, Object> map = new HashMap<>();
        deliveryChallanService.cancelDc(dcCancelRequestDto);

        map.put(MESSAGE, "Dc Cancelled");
        map.put(STATUS, SUCCESS);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/approveDc")
    public ResponseEntity<?> approveDc(@RequestBody ApproveDcDto approveDcDto) {
        ApiResponse apiResponse = new ApiResponse();
        String message = salesDcApprovalRepository.approveCancelledDC(approveDcDto.getDcId(), 
        		userAuthentication.getKubotaEmployeeId(),
        		approveDcDto.getRemarks(),
        		userAuthentication.getUsername(),
        		approveDcDto.getApprovalFlag());
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(message);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getAccessoriesAutocomplete")
    public ResponseEntity<?> getItemNumberAutoComplete(@RequestParam String itemNumber) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> itemNo =
                deliveryChallanRepositroy.getItemNumberAutoComplete(itemNumber);
        map.put(MESSAGE, "Item No Details ");
        map.put(STATUS, SUCCESS);
        map.put(RESULT, itemNo);
        return ResponseEntity.ok(map);

    }


    @GetMapping(value = "/allotmentNumberAutoComplete")
    public ResponseEntity<?> allotmentNumberAutoComplete(@RequestParam String allotmentNumber) {

        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(deliveryChallanRepositroy.allotmentAutocomplete(
                allotmentNumber, userAuthentication.getBranchId()));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Allotment Number auto complete");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/allotmentDetails")
    public ResponseEntity<?> allotmentNumberAutoComplete(@RequestParam Long allotmentId) {

        ApiResponse<MachineAllotmentDto> apiResponse = new ApiResponse<>();
        MachineAllotmentDto machineAllotmentDto=new MachineAllotmentDto();

        machineAllotmentDto.setEnquiryProspectDetailsResponse(deliveryChallanRepositroy.allotmentCustomerDetails(allotmentId,userAuthentication.getBranchId()));
        machineAllotmentDto.setMachineAllotmentDetails(deliveryChallanRepositroy.allotmentMachineDetails(allotmentId,userAuthentication.getBranchId()));

        apiResponse.setResult(machineAllotmentDto);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Allotment Number auto complete");
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/dcDownloadExcelReport")
	public ResponseEntity<InputStreamResource> downloadBBExcelReport(@RequestBody DcSearchRequestDto searchDto,HttpServletResponse response) throws IOException{
//    	Integer size = Integer.MAX_VALUE-1;
    	  Long dealerId;
          if(userAuthentication.getDealerId()==null) {
          	dealerId = searchDto.getDealerId();
          }else {
          	dealerId = userAuthentication.getDealerId();
          }
    	 List<SearchDeliveryChallanResponse> result = deliveryChallanRepositroy.searchDc(
                 userAuthentication.getManagementAccess(),
                 dealerId,
                 userAuthentication.getKubotaEmployeeId(),
                 userAuthentication.getDealerEmployeeId(),
                 searchDto.getDeliveryChallanNumber(),
                 searchDto.getChassisNumber(), searchDto.getCustomerName(),
                 searchDto.getCustomerMobileNumber(), searchDto.getDcFromDate(),
                 searchDto.getDcToDate(), searchDto.getEnquiryNumber(),
                 searchDto.getEnquiryType(), searchDto.getDcStatus(),
                 searchDto.getProduct(), searchDto.getSeries(), searchDto.getModel(),
                 searchDto.getSubModel(), searchDto.getVarient(),
                 searchDto.getItemNumber(), searchDto.getEngineNumber(),
                 searchDto.getPage(), searchDto.getSize(),
                 //size,
                 userAuthentication.getUsername(), 'N', searchDto.getHierId()
         );
    	 ByteArrayInputStream byteInputStream = ExcelCellGenerator.dcExcelReport(result);
         response.setContentType("application/vnd.ms-excel");
         HttpHeaders headers = new HttpHeaders();
         String filename = "Delevery_Challan"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
         headers.add("Content-Disposition", "attachment ; filename = "+filename);
         headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
         return ResponseEntity.ok().headers(headers).body(new InputStreamResource(byteInputStream));
    }


}
