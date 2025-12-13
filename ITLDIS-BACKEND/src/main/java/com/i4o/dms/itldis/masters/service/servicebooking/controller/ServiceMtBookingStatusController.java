package com.i4o.dms.itldis.masters.service.servicebooking.controller;

import com.i4o.dms.itldis.masters.service.jobcard.domain.ServiceMtServiceTypeInfo;
import com.i4o.dms.itldis.masters.service.jobcard.repository.ServiceMtServiceTypeInfoRepo;
import com.i4o.dms.itldis.masters.service.servicebooking.repository.ServiceMtBookingStatusRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/bookingstatus")
public class ServiceMtBookingStatusController {

    @Autowired
    private ServiceMtBookingStatusRepo serviceMtBookingStatusRepo;

    @GetMapping("/dropDownBookingStatus")
    public ResponseEntity<?> dropDownBookingStatus()
    {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("drop down booking status");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(serviceMtBookingStatusRepo.dropDownBookingStatus());
        return ResponseEntity.ok(apiResponse);
    }
}


