package com.i4o.dms.kubota.masters.usermanagement.kubotausers.controller;

import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.TransporterMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto.TransporterSearchDto;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.TransporterMasterRepository;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("/api/transporter")
public class TransporterMasterController {

    @Autowired
    private TransporterMasterRepository transporterMasterRepository;

    @PostMapping
    public ResponseEntity<?> addTransporter(@Valid @RequestBody TransporterMaster transporterMaster) {
        ApiResponse apiResponse = new ApiResponse();
        try
        {
            TransporterMaster transporterMaster1 = transporterMasterRepository.save(transporterMaster);
            transporterMaster1.setCode(NumberGenerator.generateTrasnportCode(transporterMaster1.getId()));
            transporterMasterRepository.save(transporterMaster1);
            apiResponse.setMessage("Transporter Added successfully");
            apiResponse.setStatus(HttpStatus.OK.value());
        }
        catch(DataIntegrityViolationException e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Transporter Master can't saved Because of duplicate records");
        }
        catch(Exception e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Transporter Master can't saved");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/titleDropdown")
    public ResponseEntity<?> titleDropdown()
    {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> title =transporterMasterRepository.title();
        apiResponse.setMessage("Title Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(title);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/transporterNameAuto")
    public ResponseEntity<?>transporterNameAuto(@RequestParam("transporterName") String transporterName)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> name=transporterMasterRepository.findByTransporterNameContaining(transporterName);
        apiResponse.setMessage("transporter Name get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(name);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/transporterCodeAuto")
    public ResponseEntity<?>transporterCodeAuto(@RequestParam("code") String code)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> transporterCode=transporterMasterRepository.findByCodeContaining(code);
        apiResponse.setMessage("Transporter Code get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(transporterCode);
        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping("/searchTransporter")
    public ResponseEntity<?> searchTransporter(@RequestBody TransporterSearchDto transporterSearchDto){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Transporter");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(transporterMasterRepository.searchTransporter(transporterSearchDto.getCode(),
                transporterSearchDto.getTransporterName(),transporterSearchDto.getPage(),transporterSearchDto.getSize()));

        apiResponse.setCount(transporterMasterRepository.searchTransporterCount(transporterSearchDto.getCode(),
                transporterSearchDto.getTransporterName(),transporterSearchDto.getPage(),transporterSearchDto.getSize()));

        return ResponseEntity.ok(apiResponse);

    }

}
