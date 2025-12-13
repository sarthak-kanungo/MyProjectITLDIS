package com.i4o.dms.kubota.salesandpresales.enquiry.controller;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.Enquiry;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.EnquiryTransferHistory;
import com.i4o.dms.kubota.salesandpresales.enquiry.dto.EnquiryToTransferDto;
import com.i4o.dms.kubota.salesandpresales.enquiry.dto.TransferEnquiryDto;
import com.i4o.dms.kubota.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.kubota.salesandpresales.enquiry.repository.EnquiryTransferHistoryRepo;

import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/salesandpresales/enquiry")
public class EnquiryTransferHistoryController {

    @Autowired
    private EnquiryTransferHistoryRepo enquiryTransferHistoryRepo;
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;
    @Autowired
    private EnquiryRepo enquiryRepo;
    @Autowired
    private UserAuthentication userAuthentication;

    @PostMapping("addTransfer")
    public ResponseEntity<?> addTransfer(@RequestBody TransferEnquiryDto transferEnquiryDto) {
        ApiResponse apiResponse = new ApiResponse();
        EnquiryTransferHistory enquiryTransferHistory = new EnquiryTransferHistory();
        DealerEmployeeMaster transferBy = dealerEmployeeMasterRepo.getOne(transferEnquiryDto.getTransferByEmployeeId().getId());
        DealerEmployeeMaster transferTo = dealerEmployeeMasterRepo.getOne(transferEnquiryDto.getTransferToEmployeeId().getId());
        for (Long id:transferEnquiryDto.getEnquiryId())
        {
            Enquiry enquiry=enquiryRepo.findById(id).get();
            System.out.print(id+"==================");
            enquiry.setTransferTo(transferTo);
            enquiry.setSalesPerson(transferTo);
            enquiryRepo.save(enquiry);
        }
        enquiryTransferHistory.setTransferBy(transferBy);
        enquiryTransferHistory.setTransferTo(transferTo);
        enquiryTransferHistory.setTransferDate(new Date());

        apiResponse.setMessage("enquiry transfer successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryTransferHistoryRepo.save(enquiryTransferHistory));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("getEnquiryToTransfer")
    public ResponseEntity<?> getEnquiryToTransfer(@RequestBody  EnquiryToTransferDto enquiryToTransferDto) {
    	//System.out.println("autoClose----->"+userAuthentication.getDealerEmployeeId()+"==="+enquiryToTransferDto.getSalesPerson()+"==="+enquiryToTransferDto.getEnquiryNumber()+"==="+enquiryToTransferDto.getTaluka()+"==="+enquiryToTransferDto.getEnquiryType()+"==="+enquiryToTransferDto.getAutoClose());
    	System.out.println("enquiryToTransferDto----->"+enquiryToTransferDto);
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("get enquiry to transfer");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryTransferHistoryRepo.getEnquiryToTransfer(userAuthentication.getDealerEmployeeId(),enquiryToTransferDto.getSalesPerson(),enquiryToTransferDto.getEnquiryNumber(),enquiryToTransferDto.getTaluka(),enquiryToTransferDto.getEnquiryType(),enquiryToTransferDto.getAutoClose()));
        return ResponseEntity.ok(apiResponse);

    }
    @GetMapping("getSalesPersonNameInTransfer")
    public ResponseEntity<?> getSalesPersonNameInTransfer(@RequestParam  String id) {
        ApiResponse apiResponse = new ApiResponse();
       apiResponse.setResult(enquiryTransferHistoryRepo.getSalesPersonName(userAuthentication.getDealerEmployeeId(),id));
        apiResponse.setMessage("get sales person");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }


}
