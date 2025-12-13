package com.i4o.dms.itldis.masters.usermanagement.kubotausers.controller;

import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KaiDesignationMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.dto.DepartmentSearchDto;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.dto.DesignationSearchDto;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.dto.DesignationSearchResponse;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.DesignationRepository;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RequestMapping("/api/designation")
@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
public class DesignationController {
    @Autowired
    private DesignationRepository designationRepository;

    @PostMapping
    public ResponseEntity<?> addDesignation(@Valid @RequestBody KaiDesignationMaster kaiDesignationMaster){
    	System.out.println("new designation 1 --->"+kaiDesignationMaster.getDesignation()+kaiDesignationMaster.getDepartmentId());
    	
        ApiResponse apiResponse = new ApiResponse();
        try
        {
            apiResponse.setMessage("Designation Added");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(designationRepository.save(kaiDesignationMaster));
        }
        catch (DataIntegrityViolationException e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Designation Master can't saved because of duplicate Records.");
        }
        catch (Exception e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Designation Master can't saved");
        }
        return ResponseEntity.ok(apiResponse);
    }

//    @GetMapping("/getDepartmentName")
//    public ResponseEntity<?> getHierarchy(){
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("get Designation Hierarchy");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(designationRepository.getDepartmentName());
//        return ResponseEntity.ok(apiResponse);
//    }

    @GetMapping("/searchByDesignation")
    public ResponseEntity<?> searchByDesignation(@RequestParam String designation){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get designations");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(designationRepository.searchByDesignation(designation));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchDesignation")
    public ResponseEntity<?> searchDesignation(@RequestBody DesignationSearchDto designationSearchDto){
    	
    	System.out.println("searchValue---->"+ designationSearchDto.getDesignation()+
                designationSearchDto.getDepartmentName()+designationSearchDto.getPage()+designationSearchDto.getSize());
    	
    	List<DesignationSearchResponse> response = designationRepository.searchDesignation(designationSearchDto.getDesignation(),
                designationSearchDto.getDepartmentName(),designationSearchDto.getPage(),designationSearchDto.getSize());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get designations");
        apiResponse.setStatus(HttpStatus.OK.value());
        
        apiResponse.setResult(response);
        Long count=0l;
        if(response!=null && response.size()>0)
        	count = response.get(0).getTotalRecords();
        apiResponse.setCount(count);
//        apiResponse.setCount(designationRepository.searchDesignationCount(designationSearchDto.getDesignation(),
//                designationSearchDto.getDepartmentName(),designationSearchDto.getPage(),designationSearchDto.getSize()));

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/changeActiveStatus")
    public ResponseEntity<?> changeActiveStatus(@RequestParam("id") Long id)
    {
        KaiDesignationMaster kaiDesignationMaster=designationRepository.getOne(id);
        kaiDesignationMaster.setActiveStatus(kaiDesignationMaster.getActiveStatus().equals('Y') ?  'N' : 'Y');
        KaiDesignationMaster master=designationRepository.save(kaiDesignationMaster);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Status updated successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(master);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "/tocheckKaiDesignation")
    public ResponseEntity<?>checkKaiDesignation(@RequestParam("designation") String designation){
       ApiResponse<Object> apiResponse=new ApiResponse<>();
       String designationValue=designationRepository.toCheckKaiDesignation(designation);
       apiResponse.setMessage("Designation get successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(designationValue);
       return ResponseEntity.ok(apiResponse);
   }
    
    @GetMapping(value = "/tocheckKaiDesignationCode")
    public ResponseEntity<?>checkKaiDesignationCode(@RequestParam("designationCode") String designationCode){
       ApiResponse<Object> apiResponse=new ApiResponse<>();
       String checCode=designationRepository.tocheckKaiDesignationCode(designationCode);
       apiResponse.setMessage("Kai Designation Code get successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(checCode);
       return ResponseEntity.ok(apiResponse);
   }

}
