package com.i4o.dms.kubota.salesandpresales.sales.allotment.controller;


import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.salesandpresales.sales.allotment.domain.MachineAllotment;
import com.i4o.dms.kubota.salesandpresales.sales.allotment.domain.MachineAllotmentDetail;
import com.i4o.dms.kubota.salesandpresales.sales.allotment.dto.AllotmentSearchDto;
import com.i4o.dms.kubota.salesandpresales.sales.allotment.dto.AllotmentSearchResponseDto;
import com.i4o.dms.kubota.salesandpresales.sales.allotment.dto.DeAllotmentDto;
import com.i4o.dms.kubota.salesandpresales.sales.allotment.dto.EnquiryCustomerDetails;
import com.i4o.dms.kubota.salesandpresales.sales.allotment.repository.MachineAllotmentDetailRepository;
import com.i4o.dms.kubota.salesandpresales.sales.allotment.repository.MachineAllotmentRepository;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@ResponseBody
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/sales/machineAllotment")
public class MachineAllotmentController {

    @Autowired
    private MachineAllotmentRepository machineAllotmentRepository;

    @Autowired
    private MachineAllotmentDetailRepository machineAllotmentDetailRepository;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @GetMapping("searchByAllotmentNumber")
    public ResponseEntity<?> searchByAllotmentNumber(@RequestParam String allotmentNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("search machine Allotment number");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.searchByAllotmentNumber(allotmentNumber, userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

   /* @GetMapping("searchByEnquiryNumber")
    public ResponseEntity<?> searchByEnquiryNumber(@RequestParam String enquiryNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("search machine enquiry number");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.searchByEnquiryNumber(enquiryNumber, userAuthentication.getDealerEmployeeId()));
        return ResponseEntity.ok(apiResponse);
    }*/

    @GetMapping("searchByEngineNumber")
    public ResponseEntity<?> searchByEngineNumber(@RequestParam String engineNumber, @RequestParam Long userId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("search machine Engine number");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.searchByEngineNumber(engineNumber, userId));
        return ResponseEntity.ok(apiResponse);
    }

   /* @GetMapping("/searchByEnquiryNumberForAllotment")
    public ResponseEntity<?> searchByEnquiryNumberForAllotment(@RequestParam String searchString) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("search enquiry by search string ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.searchByEnquiryNumberMobileNameTehsil(searchString, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }*/

    @GetMapping("/getEnquiryMachineDetailByEnquiryId")
    public ResponseEntity<?> getEnquiryMachineDetailByEnquiryId(@RequestParam Long enquiryId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("getEnquiryMachineDetailByEnquiryNumber");
        apiResponse.setStatus(HttpStatus.OK.value());
        EnquiryCustomerDetails enquiryCustomerDetails = new EnquiryCustomerDetails();
        
        enquiryCustomerDetails.setCustomerDetail(machineAllotmentRepository.getCustomerByEnquiry(enquiryId, userAuthentication.getBranchId()));
        
        enquiryCustomerDetails.setMachineDetails(machineAllotmentRepository.getMachineDetailsFromEnquiry(enquiryId, userAuthentication.getBranchId()));
        apiResponse.setResult(enquiryCustomerDetails);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchMachineChassisNumber")
    public ResponseEntity<?> searchChassisNumber(@RequestParam String chassisNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchMachineByChassisNumber");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.searchMachineChassisNumber(chassisNumber, userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);

    }

    //rename url. This is in use in creation screen.
   /* @GetMapping("/searchImplementsMachineChassisNumber")
    public ResponseEntity<?> searchImplementsMachineChassisNumber(@RequestParam String chassisNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchMachineByChassisNumber");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.searchImplementsMachineChassisNumber(chassisNumber, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);

    }*/

    @GetMapping("/searchMachineByChassisNumber")
    public ResponseEntity<?> searchMachineByChassisNumber(@RequestParam String chassisNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchMachineByChassisNumber");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.getMachineByChassisNumber(chassisNumber, userAuthentication.getBranchId()));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<?> allotMachine(@RequestBody MachineAllotment machineAllotment) {
        ApiResponse apiResponse = new ApiResponse();
        machineAllotment.setCreatedBy(userAuthentication.getLoginId());
        machineAllotment.setCreatedDate(new Date());
        machineAllotment.setBranchId(userAuthentication.getBranchId());
        machineAllotment.setDealerEmployeeId(userAuthentication.getDealerEmployeeId());
        machineAllotment.setAllotmentDate(new Date());
        apiResponse.setMessage("allot machine");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.save(machineAllotment));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchMachineAllotment")
    public ResponseEntity<?> searchAllotment(@RequestBody AllotmentSearchDto allotmentSearchDto) {
        ApiResponse apiResponse = new ApiResponse();
        
        List<AllotmentSearchResponseDto> result = machineAllotmentRepository.searchMachineAllotment(allotmentSearchDto.getAllotmentNumber(),
                allotmentSearchDto.getEnquiryNumber(), allotmentSearchDto.getFromDate(), allotmentSearchDto.getToDate(),
                allotmentSearchDto.getProduct(), allotmentSearchDto.getSeries(), allotmentSearchDto.getModel(),
                allotmentSearchDto.getSubModel(), allotmentSearchDto.getVariant(), allotmentSearchDto.getItemNo(),
                allotmentSearchDto.getChassisNo(), allotmentSearchDto.getEngineNo(),userAuthentication.getDealerId(),
                userAuthentication.getDealerEmployeeId(),userAuthentication.getManagementAccess(),userAuthentication.getKubotaEmployeeId(),
                allotmentSearchDto.getPage(), allotmentSearchDto.getSize(),
                userAuthentication.getUsername(),'N',allotmentSearchDto.getOrgHierId());
        
        apiResponse.setMessage("search  machine");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getAllotmentById/{allotmentId}")
    public ResponseEntity<?> getAllotmentById(@PathVariable Long allotmentId) {
        ApiResponse apiResponse = new ApiResponse();
        EnquiryCustomerDetails enquiryCustomerDetails = new EnquiryCustomerDetails();
        enquiryCustomerDetails.setCustomerDetail(machineAllotmentRepository.getAllotmentById(allotmentId));
        enquiryCustomerDetails.setMachineDetails(machineAllotmentRepository.getAllotmentDetailsById(allotmentId));
        apiResponse.setResult(enquiryCustomerDetails);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("get allotment by id");
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/deAllotMainMachine")
    public ResponseEntity<?> deAllotMainMachine(@RequestBody DeAllotmentDto dto) {
        ApiResponse apiResponse = new ApiResponse();

        MachineAllotment machineAllotment = machineAllotmentRepository.getOne(dto.getAllotmentId());
        Date deAllotmentDate = new Date();
        machineAllotment.setLastModifiedBy(userAuthentication.getLoginId());
        machineAllotment.setLastModifiedDate(deAllotmentDate);
        /*machineAllotment.getMachineAllotmentDetails().forEach(machineAllotmentDetail -> {
            machineAllotmentDetail.setDeAllotmentDate(deAllotmentDate);
            machineAllotmentDetail.setDeAllotFlag(true);
            machineAllotmentDetail.setDeAllocatedById(userAuthentication.getDealerEmployeeId());
        });*/
        machineAllotment.setDeAllocatedById(userAuthentication.getDealerEmployeeId());
        machineAllotment.setDeAllotFlag(true);
        machineAllotment.setDeAllotmentDate(new Date());
        machineAllotment.setDeAllotReason(dto.getReason());
        //machineAllotmentDetailRepository.saveAll(machineAllotment.getMachineAllotmentDetails());
        machineAllotmentRepository.save(machineAllotment);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("machine de allocated successfully");
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/deAllotMachineImplements")
    public ResponseEntity<?> deAllotMachineImplements(@RequestBody DeAllotmentDto deAllotmentDto) {
        ApiResponse apiResponse = new ApiResponse();
        MachineAllotmentDetail allotmentDetail = machineAllotmentDetailRepository.getOne(deAllotmentDto.getMachineDetailId());
        //allotmentDetail.setDeAllocatedById(userAuthentication.getDealerEmployeeId());
        //allotmentDetail.setDeAllotFlag(true);
        //allotmentDetail.setDeAllotmentDate(new Date());
        machineAllotmentDetailRepository.save(allotmentDetail);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("addAllotment")
    public ResponseEntity<?> addAllotment(@RequestBody MachineAllotment machineAllotment) {
        ApiResponse apiResponse = new ApiResponse();
        machineAllotment.setBranchId(userAuthentication.getBranchId());
        apiResponse.setMessage("get enquiry in app");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.save(machineAllotment));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getEnquiryInAllotment")
    public ResponseEntity<?> getEnquiryInAllotment(@RequestParam(required = true) String enquiryNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get enquiry in app");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.getEnquiryInAllotment(enquiryNumber));

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getChassisNumbers")
    public ResponseEntity<?> getChassisNumbers(@RequestParam(required = true) String itemNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get enquiry in app");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.getMachineByChassisNumber(itemNumber, userAuthentication.getBranchId()));
        return ResponseEntity.ok(apiResponse);


    }

    /*@Deprecated
    @PostMapping("deAllotImplements")
    public ResponseEntity<?> deAllotImplements(@RequestBody DeAllotmentDto deAllotmentDto) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get enquiry in app");
        apiResponse.setStatus(HttpStatus.OK.value());
        MachineAllotment machineAllotment = machineAllotmentRepository.getOne(deAllotmentDto.getAllotmentId());


        if (deAllotmentDto.mainMachine) {
            machineAllotment.setDeAllotmentDate(new Date());
            machineAllotment.setDeAllotReason(deAllotmentDto.getReason());
            for (MachineAllotmentDetail machineAllotmentDetail : machineAllotment.getMachineAllotmentDetails()) {
               // machineAllotmentDetail.setDeAllotReason(deAllotmentDto.getReason());
                machineAllotmentDetail.setDeAllotmentDate(new Date());
                machineAllotmentDetailRepository.save(machineAllotmentDetail);
            }
            machineAllotmentRepository.save(machineAllotment);
        } else {
            for (Long id : deAllotmentDto.getIds()) {
                MachineAllotmentDetail machineAllotmentDetail = machineAllotmentDetailRepository.getOne(id);
                machineAllotmentDetail.setMachineAllotment(machineAllotment);
                machineAllotmentDetail.setDeAllotmentDate(new Date());
              //  machineAllotmentDetail.setDeAllotReason(deAllotmentDto.getReason());
                machineAllotmentDetailRepository.save(machineAllotmentDetail);
            }
        }
        return ResponseEntity.ok(apiResponse);
    }*/
    @GetMapping("getAllotmentByAllotmentNumber")
    public ResponseEntity<?> getAllotmentByAllotmentNumber(@RequestParam(required = true) String allotmentNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get machineAllotment number");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.findByAllotmentNumber(allotmentNumber));

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getCustomer")
    public ResponseEntity<?> getEnquiryInfoByMobileNumber(@RequestParam(required = true) String mobileNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get machineAllotment number");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.getCustomerByMobileNumber(mobileNumber));

        return ResponseEntity.ok(apiResponse);
    }

    /*@GetMapping("autoCompleteImplementItemNo")
    public ResponseEntity<?> autoCompleteItemNo(@RequestParam String itemNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("auto complete implement item no ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.autoCompleteItemNo(itemNo,userAuthentication.getDealerId()));

        return ResponseEntity.ok(apiResponse);
    }*/


    @GetMapping("autoCompleteChassisNo")
    public ResponseEntity<?> autoCompleteChassisNo(@RequestParam Long machineMasterId,@RequestParam String chassisNo) {
    	System.out.println("chessis");
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("auto complete implement item no ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineAllotmentRepository.autoCompleteChassisNo(machineMasterId,chassisNo,
        		userAuthentication.getBranchId()));

        return ResponseEntity.ok(apiResponse);
    }


}
