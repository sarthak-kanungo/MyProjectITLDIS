package com.i4o.dms.itldis.service.servicebooking.controller;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineInventory;
import com.i4o.dms.itldis.salesandpresales.grn.repository.MachineInventoryRepository;
import com.i4o.dms.itldis.salesandpresales.sales.paymentReceipt.dto.PaymentReceiptList;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.servicebooking.domain.ServiceBooking;
import com.i4o.dms.itldis.service.servicebooking.dto.ServiceBookingResponseDto;
import com.i4o.dms.itldis.service.servicebooking.dto.ServiceBookingSearchDto;
import com.i4o.dms.itldis.service.servicebooking.repository.ServiceBookingRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RequestMapping(value = "/api/service/servicebooking")
public class ServiceBookingController {


    @Autowired
    private MachineInventoryRepository machineInventoryRepository;
    @Autowired
    private ServiceBookingRepo serviceBookingRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    
    @SuppressWarnings("unchecked")
	@PostMapping("/saveServiceBooking")
    public ResponseEntity<?> saveServiceBooking(@RequestBody ServiceBooking serviceBooking) {
        ApiResponse apiResponse = new ApiResponse();
        serviceBooking.setBranchId(userAuthentication.getBranchId());
        serviceBooking.setBookingDate(new Date());
        serviceBooking.setStatus(serviceBooking.getDraftFlag()==true ? "Draft":"Open");
		if(serviceBooking.getId()!=null)
		{
			if(serviceBooking.getCancelBookingFlag()!=null && serviceBooking.getCancelBookingFlag()){
	           serviceBooking.setStatus("Cancelled");
	           serviceBooking.setReasonForCancellation(serviceBooking.getReasonForCancellation());
	           serviceBooking.setCancelBookingFlag(true);
		     }
			 serviceBooking.setLastModifiedBy(userAuthentication.getLoginId());
			 serviceBooking.setLastModifiedOn(new Date());
		}
        serviceBooking.setCreatedBy(userAuthentication.getLoginId());
        serviceBooking.setCreatedOn(new Date());
        apiResponse.setResult(serviceBookingRepo.save(serviceBooking));
        apiResponse.setMessage("Service booking submitted successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/dropDownSourceOfBooking")
    public ResponseEntity<?> getChassisNumber() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("drop down source of booking");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(serviceBookingRepo.dropDownSourceOfBooking());
        return ResponseEntity.ok(apiResponse);


    }

    @GetMapping("/autoCompleteChassisNo")
    public ResponseEntity<?> getAutoCompleteChassisNo(@RequestParam String chassisNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("drop down source of booking");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(serviceBookingRepo.autoCompleteChassisNo(chassisNo,userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/serviceBookingSearch")
    public ResponseEntity<?> saveServiceBooking(@RequestBody ServiceBookingSearchDto serviceBookingSearchDto) {
        ApiResponse apiResponse = new ApiResponse();

        List<ServiceBookingResponseDto> listOfServiceBooking=serviceBookingRepo.serviceBookingSearch(userAuthentication.getManagementAccess(),
                userAuthentication.getDealerId(),userAuthentication.getKubotaEmployeeId(),userAuthentication.getDealerEmployeeId()
                , serviceBookingSearchDto.getBookingNo(),
                serviceBookingSearchDto.getStatus(),serviceBookingSearchDto.getChassisNo(),serviceBookingSearchDto.getBookingFromDate(),
                serviceBookingSearchDto.getBookingToDate(),serviceBookingSearchDto.getMechanicName(),
                serviceBookingSearchDto.getEngineNo(),serviceBookingSearchDto.getMachineSubModel(),
                serviceBookingSearchDto.getSourceOfBooking(),serviceBookingSearchDto.getServiceCategory(),
                serviceBookingSearchDto.getServiceType(),
                serviceBookingSearchDto.getPlaceOfService(),serviceBookingSearchDto.getActivityType(),
                serviceBookingSearchDto.getActivityNo(),serviceBookingSearchDto.getAppointmentFromDate(),
                serviceBookingSearchDto.getAppointmentToDate(),serviceBookingSearchDto.getPage(),serviceBookingSearchDto.getSize(),
                userAuthentication.getUsername(),
        		'N',0l);

        for(ServiceBookingResponseDto p:listOfServiceBooking) {
        	System.out.println(p.getBookingDate()+"==="+p.getBookingNo());
        }
        
        Long count = 0L;
        if(listOfServiceBooking!=null && listOfServiceBooking.size()>0){
        	count = listOfServiceBooking.get(0).getRecordCount();
        }
        
        apiResponse.setMessage("service booking search");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setCount(count);
        apiResponse.setResult(listOfServiceBooking);

//        Long count=serviceBookingRepo.serviceBookingSearchCount(userAuthentication.getManagementAccess(),
//                userAuthentication.getDealerId(),userAuthentication.getKubotaEmployeeId(),userAuthentication.getDealerEmployeeId()
//                , serviceBookingSearchDto.getBookingNo(),
//                serviceBookingSearchDto.getStatus(),serviceBookingSearchDto.getChassisNo(),serviceBookingSearchDto.getBookingFromDate(),
//                serviceBookingSearchDto.getBookingToDate(),serviceBookingSearchDto.getMechanicName(),
//                serviceBookingSearchDto.getEngineNo(),serviceBookingSearchDto.getMachineSubModel(),
//                serviceBookingSearchDto.getSourceOfBooking(),serviceBookingSearchDto.getServiceCategory(),
//                serviceBookingSearchDto.getServiceType(),
//                serviceBookingSearchDto.getPlaceOfService(),serviceBookingSearchDto.getActivityType(),
//                serviceBookingSearchDto.getActivityNo(),serviceBookingSearchDto.getAppointmentFromDate(),
//                serviceBookingSearchDto.getAppointmentToDate(),serviceBookingSearchDto.getPage(),serviceBookingSearchDto.getSize());
//              apiResponse.setCount(count);

        return ResponseEntity.ok(apiResponse);



    }

    @GetMapping("/autoCompleteBookingNo")
    public ResponseEntity<?> autoCompleteBookingNo(@RequestParam String bookingNo,@RequestParam Boolean jobCardFlag ) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("auto complete booking no");
        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(serviceBookingRepo.autoCompleteBookingNo(bookingNo,userAuthentication.getDealerId(),jobCardFlag));
        apiResponse.setResult(serviceBookingRepo.autoCompleteBookingNo(bookingNo,userAuthentication.getBranchId(),jobCardFlag,
        		userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);


    }

    @GetMapping("/autoCompleteEngineNumber")
    public ResponseEntity<?> autoCompleteEngineNumber(@RequestParam String engineNo ) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("auto complete booking no");
        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(serviceBookingRepo.autoCompleteEngineNumber(engineNo,userAuthentication.getDealerId()));
        apiResponse.setResult(serviceBookingRepo.autoCompleteEngineNumber(engineNo,userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);


    }

    @GetMapping("/viewServiceBookingById/{id}")
    public ResponseEntity<?> autoCompleteEngineNumber(@PathVariable Long id ) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("view service booking by id ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(serviceBookingRepo.viewServiceBookingById(id));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/cancelServiceBooking")
    public ResponseEntity<?> cancelServiceBooking(@RequestParam Long id,@RequestParam String reason) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("cancel service booking");
        ServiceBooking  serviceBooking=serviceBookingRepo.findById(id).get();
        if(!serviceBooking.getStatus().equals("Cancelled")){
           serviceBooking.setStatus("Cancelled");
           serviceBooking.setLastModifiedBy(userAuthentication.getLoginId());
           serviceBooking.setLastModifiedOn(new Date());
           serviceBooking.setReasonForCancellation(reason);
           serviceBooking.setCancelBookingFlag(true);
           serviceBookingRepo.save(serviceBooking);
           apiResponse.setResult("Service booking cancel");
           apiResponse.setMessage("service booking cancelled successfully");
        }else{
            apiResponse.setResult("service booking cancel");
            apiResponse.setMessage("service booking already cancelled successfully");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }



}
