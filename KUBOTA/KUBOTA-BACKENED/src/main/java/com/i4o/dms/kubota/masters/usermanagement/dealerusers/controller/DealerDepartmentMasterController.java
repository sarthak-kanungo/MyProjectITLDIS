package com.i4o.dms.kubota.masters.usermanagement.dealerusers.controller;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerDepartmentMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.DealerDepartmentDto;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.DealerDepartmentSearchResponse;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerDepartmentRepo;
import com.i4o.dms.kubota.utils.ApiResponse;
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
@RequestMapping(value = "/api/dealerDepartmentMaster")
public class DealerDepartmentMasterController {

    @Autowired
    private DealerDepartmentRepo dealerDepartmentRepo;

    @PostMapping("/saveDealerDepartment")
    public ResponseEntity<?> addDealerDepartment(@Valid @RequestBody DealerDepartmentMaster dealerDepartmentMaster){
        ApiResponse<DealerDepartmentMaster> apiResponse = new ApiResponse<>();
        String checkCode=dealerDepartmentRepo.toCheckDepartmentCode(dealerDepartmentMaster.getDepartmentCode());
        String checkName=dealerDepartmentRepo.toCheckDepartmentName(dealerDepartmentMaster.getDepartmentName());
        System.out.println("dealerDepartmentMaster--"+dealerDepartmentMaster.getDepartmentCode());
        System.out.println(checkCode+ "		"+checkName);
        try {
//        	if(checkCode.toLowerCase().equalsIgnoreCase( dealerDepartmentMaster.getDepartmentCode())&& checkName.toLowerCase().equalsIgnoreCase(dealerDepartmentMaster.getDepartmentName())) {
//        		apiResponse.setMessage("Record Already Exist");
//                apiResponse.setStatus(HttpStatus.OK.value());
//                System.out.println("save not");
//        	}else {
            	apiResponse.setResult(dealerDepartmentRepo.save(dealerDepartmentMaster));
                apiResponse.setMessage("Dealer Department Master Added");
                apiResponse.setStatus(HttpStatus.OK.value());
                //}
        	System.out.println("save");
        }
        catch(DataIntegrityViolationException e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Dealer Department Master can't saved because of duplicate Records");
        }
        catch(Exception e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Dealer Department Master can't saved");
        }
        return ResponseEntity.ok(apiResponse);

    }
//
//    @GetMapping("/departmentNameDropdown")
//    public ResponseEntity<?> departmentNameDropdown()
//    {
//        ApiResponse apiResponse=new ApiResponse();
//        List<Map<String,Object>> department=dealerDepartmentRepo.getDepartmentName();
//        apiResponse.setMessage("department Name get successfully");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(department);
//        return ResponseEntity.ok(apiResponse);
//    }
//
//     @GetMapping("/departmentNameAuto")
//     public ResponseEntity<?> departmentNameAuto(@RequestParam("departmentName") String departmentName)
//     {
//         ApiResponse apiResponse=new ApiResponse();
//         List<Map<String,Object>> name =dealerDepartmentRepo.findByDepartmentNameContaining(departmentName);
//         apiResponse.setMessage("department name get successfully");
//         apiResponse.setStatus(HttpStatus.OK.value());
//         apiResponse.setResult(name);
//         return ResponseEntity.ok(apiResponse);
//     }
//    
    @GetMapping("/departmentCodeAndName")
    public ResponseEntity<?> dealerDepartmentCodeAndName(@RequestParam("deptCodeAndName") String deptCodeAndName)
    {
        ApiResponse<List<Map<String, Object>>> apiResponse=new ApiResponse<>();
        List<Map<String,Object>> codeNname =dealerDepartmentRepo.departmentCodeAndName(deptCodeAndName);
        apiResponse.setMessage("Department Code & Name get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(codeNname);
        return ResponseEntity.ok(apiResponse);
    }

     @PostMapping("/getDeptDetailsForSearchPage")
     public ResponseEntity<?> searchDealerDepartment(@RequestBody DealerDepartmentDto dealerDepartmentDto)
     {
         ApiResponse<List<DealerDepartmentSearchResponse>> apiResponse=new ApiResponse<>();
         apiResponse.setMessage("data get successfully ");
         apiResponse.setStatus(HttpStatus.OK.value());
         List<DealerDepartmentSearchResponse> result = dealerDepartmentRepo.searchDepartment(dealerDepartmentDto.getDepartmentCode(),dealerDepartmentDto.getDepartmentName(),dealerDepartmentDto.getPage(),dealerDepartmentDto.getSize());
        

        // apiResponse.setCount(dealerDepartmentRepo.searchDealerDepartmentCount(dealerDepartmentDto.getDepartmentName(),dealerDepartmentDto.getPage(),dealerDepartmentDto.getSize()));
         
         Long count=0L;
         if(result!=null && result.size()>0) {
        	 count=result.get(0).getTotalRecords();
         }
         apiResponse.setCount(count);
         apiResponse.setResult(result);

         return ResponseEntity.ok(apiResponse);
     }

    @GetMapping(value = "/changeActiveStatus")
    public ResponseEntity<?> changeActiveStatus(@RequestParam("id") Long id)
    {
        DealerDepartmentMaster departmentMaster=dealerDepartmentRepo.getOne(id);
        departmentMaster.setActiveStatus(departmentMaster.getActiveStatus().equals('Y') ?  'N' : 'Y');
        DealerDepartmentMaster master=dealerDepartmentRepo.save(departmentMaster);
        ApiResponse<DealerDepartmentMaster> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Status updated successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(master);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "/toCheckDepartmentCode")
    public ResponseEntity<?>checkDepartmentCode(@RequestParam("departmentCode") String departmentCode){
       ApiResponse<Object> apiResponse=new ApiResponse<>();
       String checkCode=dealerDepartmentRepo.toCheckDepartmentCode(departmentCode);
       apiResponse.setMessage("Department Code Code get successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(checkCode);
       return ResponseEntity.ok(apiResponse);
   }
    
    @GetMapping(value = "/toCheckDepartmentName")
    public ResponseEntity<?>checkDepartmentName(@RequestParam("departmentName") String departmentName){
       ApiResponse<Object> apiResponse=new ApiResponse<>();
       String checkName=dealerDepartmentRepo.toCheckDepartmentName(departmentName);
       apiResponse.setMessage("Department Name Code get successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(checkName);
       return ResponseEntity.ok(apiResponse);
   }
    
    @GetMapping(value = "/viewDepartmentDetails")
    public ResponseEntity<?>viewDesignation(@RequestParam("departmentId") String departmentId){
       ApiResponse<Object> apiResponse=new ApiResponse<>();
        Map<String, Object> departmentDetails = dealerDepartmentRepo.viewDepartmentDetails(departmentId);
       apiResponse.setMessage("Department details get successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(departmentDetails);
       return ResponseEntity.ok(apiResponse);
   }
    
    @GetMapping(value = "/updateDepartmentDetails")
    public ResponseEntity<?>updateDepartment(@RequestParam("departmentCode") String departmentCode,
 											 @RequestParam("departmentname") String departmentname,
 											 @RequestParam("id") Long id){
       ApiResponse<Object> apiResponse=new ApiResponse<>();
       Integer response=dealerDepartmentRepo.updateDepartmentDetails(departmentCode,departmentname, id);
       apiResponse.setMessage("Department details updated successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(response);
       return ResponseEntity.ok(apiResponse);
   }

}
