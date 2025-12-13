package com.i4o.dms.kubota.masters.usermanagement.kubotausers.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.DesignationLevelMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto.DesignationLevelSearchDto;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.DesignationLevelRepository;

import com.i4o.dms.kubota.utils.ApiResponse;

@RequestMapping("/api/designationlevel")
@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
public class DesignationLevelController {

	@Autowired
    private DesignationLevelRepository designationLevelRepository;

    @PostMapping
    public ResponseEntity<?> addDesignationLevel(@Valid @RequestBody DesignationLevelMaster designationLevelMaster){
    	System.out.println("designationLevelMaster---->"+designationLevelMaster.getDesignationlevel()+"<===>"+designationLevelMaster.getDepartmentId());
        ApiResponse apiResponse = new ApiResponse();
        try
        {
            apiResponse.setMessage("Designation Level Added");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(designationLevelRepository.save(designationLevelMaster));
        }
        catch (DataIntegrityViolationException e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Designation Level can't saved because of duplicate Records.");
        }
        catch (Exception e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Designation Level Master can't saved");
        }
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/searchByDesignationLevel")
    public ResponseEntity<?> searchByDesignation(@RequestParam String designationlevel){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get designations");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(designationLevelRepository.searchByDesignationLevel(designationlevel));
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/searchDesignationLevel")
    public ResponseEntity<?> searchDesignation(@RequestBody DesignationLevelSearchDto designationLevelSearchDto){
    	
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get designations");
        apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(designationLevelRepository.searchDesignationLevel(designationLevelSearchDto.getDesignationLevel(),
    		   designationLevelSearchDto.getDepartmentName(),designationLevelSearchDto.getPage(),designationLevelSearchDto.getSize()));

        apiResponse.setCount(designationLevelRepository.searchDesignationLevelCount(designationLevelSearchDto.getDesignationLevel(),
        		designationLevelSearchDto.getDepartmentName(),designationLevelSearchDto.getPage(),designationLevelSearchDto.getSize()));

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "/changeActiveStatus")
    public ResponseEntity<?> changeActiveStatus(@RequestParam("id") Long id)
    {
    	DesignationLevelMaster designationLevelMaster=designationLevelRepository.getOne(id);
    	designationLevelMaster.setActiveStatus(designationLevelMaster.getActiveStatus().equals('Y') ?  'N' : 'Y');
    	DesignationLevelMaster master=designationLevelRepository.save(designationLevelMaster);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Status updated successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(master);
        return ResponseEntity.ok(apiResponse);
    }

}
