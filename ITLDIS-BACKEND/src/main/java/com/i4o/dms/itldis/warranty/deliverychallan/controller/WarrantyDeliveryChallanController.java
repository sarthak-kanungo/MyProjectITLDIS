package com.i4o.dms.itldis.warranty.deliverychallan.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.jobcard.repository.ServiceJobCardRepo;
import com.i4o.dms.itldis.spares.partrequisition.repository.SparePartRequisitionItemRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.warranty.deliverychallan.domain.WarrantyDeliveryChallan;
import com.i4o.dms.itldis.warranty.deliverychallan.dto.WarrantyDcResponseDto;
import com.i4o.dms.itldis.warranty.deliverychallan.dto.WarrantyDcSearchDcSearchDto;
import com.i4o.dms.itldis.warranty.deliverychallan.dto.WarrantyDeliveryChallanDto;
import com.i4o.dms.itldis.warranty.deliverychallan.dto.WarrantyDeliveryChallanViewDto;
import com.i4o.dms.itldis.warranty.deliverychallan.repository.WarrantyDeliveryChallanRepo;
import com.i4o.dms.itldis.warranty.warrantyclaimrequest.domain.WarrantyWcr;
import com.i4o.dms.itldis.warranty.warrantyclaimrequest.repository.WarrantyWcrRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RequestMapping(value = "/api/warranty/deliverychallan")
public class WarrantyDeliveryChallanController {


    @Autowired
    private WarrantyDeliveryChallanRepo warrantyDeliveryChallanRepo;

    @Autowired
    private SparePartRequisitionItemRepo sparePartRequisitionItemRepo;

    @Autowired
    private WarrantyWcrRepo warrantyWcrRepo;

    @Autowired
    private ServiceJobCardRepo serviceJobCardRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @GetMapping("/getClaimPartInDc")
    public ResponseEntity<?> getClaimPartInDc(@RequestParam String ids) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyDeliveryChallanRepo.getClaimPartInDc(ids));
        apiResponse.setMessage(" get claim part details");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/saveDeliveryChallan")
    public ResponseEntity<?> saveDeliveryChallan(@RequestBody WarrantyDeliveryChallan warrantyDeliveryChallan) {
        ApiResponse apiResponse = new ApiResponse();

            for (WarrantyWcr warrantyWcr : warrantyDeliveryChallan.getWarrantyWcr()) {
                String wcrNo = warrantyDeliveryChallanRepo.getDeliveryChallanCount(warrantyWcr.getId());
                if (wcrNo != null) {
                    apiResponse.setMessage("Delivery Challan already created for WCR : " + wcrNo);
                    apiResponse.setStatus(HttpStatus.OK.value());
                    return ResponseEntity.ok(apiResponse);
                }
            }
        warrantyDeliveryChallan.setBranchId(userAuthentication.getBranchId());
        warrantyDeliveryChallan.setCreatedBy(userAuthentication.getLoginId());
        warrantyDeliveryChallan.setStatus(warrantyDeliveryChallan.getDraftFlag()?"Draft":"Submitted");
        warrantyDeliveryChallan.setDcDate(new Date());
        WarrantyDeliveryChallan warrantyDeliveryChallan1=  warrantyDeliveryChallanRepo.save(warrantyDeliveryChallan);

        apiResponse.setMessage("Delivery Challan created successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/deliveryChallanSearch")
    public ResponseEntity<?> deliveryChallanSearch(@RequestBody WarrantyDcSearchDcSearchDto dcSearchDto) {
        ApiResponse apiResponse = new ApiResponse();
        
        List<WarrantyDcResponseDto> list = warrantyDeliveryChallanRepo.deliveryChallanSearch(userAuthentication.getManagementAccess(),userAuthentication.getKubotaEmployeeId(),userAuthentication.getDealerId(),userAuthentication.getDealerEmployeeId(),dcSearchDto.getDcNo(),dcSearchDto.getWcrNo(),dcSearchDto.getDcFromDate(),dcSearchDto.getDcToDate(),dcSearchDto.getMachineModel(),dcSearchDto.getWcrFromDate(),dcSearchDto.getWcrToDate(),dcSearchDto.getPage(),dcSearchDto.getSize(),0L,userAuthentication.getUsername(),'N');
        apiResponse.setResult(list);
        Long count=0L;
        if(list!=null && !list.isEmpty()){
        	count = list.get(0).getTotalCount();
        }
        apiResponse.setCount(count);
        apiResponse.setMessage("warranty dc search");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/viewWarrantyDeliveryChallan")
    public ResponseEntity<?> viewWarrantyDeliveryChallan(@RequestParam String dcNo) {
        ApiResponse apiResponse = new ApiResponse();
        WarrantyDeliveryChallanDto warrantyDeliveryChallanDto=new WarrantyDeliveryChallanDto();
        WarrantyDeliveryChallanViewDto warrantyDeliveryChallanViewDto=warrantyDeliveryChallanRepo.findByDcNo(dcNo);
        warrantyDeliveryChallanDto.setDealerMaster(warrantyDeliveryChallanRepo.getDealerDetails(warrantyDeliveryChallanViewDto.getBranchId()));
        warrantyDeliveryChallanDto.setWarrantyDeliveryChallanViewDto(warrantyDeliveryChallanViewDto);
        warrantyDeliveryChallanDto.setWarrantyPart(warrantyDeliveryChallanRepo.getJobCardPartWarrantyInfo(dcNo,userAuthentication.getDealerId()));
        apiResponse.setResult(warrantyDeliveryChallanDto);
        apiResponse.setMessage("view warranty delivery challan ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/updateWarrantyDeliveryChallan")
    public ResponseEntity<?> updateWarrantyDeliveryChallan(@RequestBody WarrantyDeliveryChallan warrantyDeliveryChallan) {
        ApiResponse apiResponse = new ApiResponse();
        WarrantyDeliveryChallan warrantyDeliveryChallan1=warrantyDeliveryChallanRepo.getOne(warrantyDeliveryChallan.getId());
        warrantyDeliveryChallan1.setTransporterName(warrantyDeliveryChallan.getTransporterName());
        warrantyDeliveryChallan1.setDispatchDate(new Date());
        warrantyDeliveryChallan1.setDocketNumber(warrantyDeliveryChallan.getDocketNumber());
        warrantyDeliveryChallan1.setNumberOfBox(warrantyDeliveryChallan.getNumberOfBox());
        warrantyDeliveryChallan1.setLastModifiedDate(new Date());
        warrantyDeliveryChallan1.setLastModifiedBy(userAuthentication.getLoginId());
        warrantyDeliveryChallanRepo.save(warrantyDeliveryChallan1);
        apiResponse.setMessage("Transport details updated successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


}
