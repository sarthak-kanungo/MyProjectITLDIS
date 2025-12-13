package com.i4o.dms.itldis.masters.usermanagement.kubotausers.controller;

import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaDepartmentMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.dto.DepartmentSearchDto;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.dto.DepartmentSearchResponse;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.DepartmentRepository;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(
        allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("/api/department")
public class DepartmentMasterController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public ResponseEntity<?> addDepartment(@Valid @RequestBody KubotaDepartmentMaster departmentMaster){
		ApiResponse apiResponse = new ApiResponse();
        try {
        	if (departmentRepository.findByDepartmentCodeAndDepartmentName(departmentMaster.getDepartmentCode(), departmentMaster.getDepartmentName()) != null) {
        		System.out.println("-----------------> Duplicate Exists");
    			throw new DataIntegrityViolationException("Department Master can't saved because of duplicate Records ");
    		} else {
    			System.out.println("-----------------> New Created");
    			KubotaDepartmentMaster kubotaDepartmentMaster = new KubotaDepartmentMaster();
    			kubotaDepartmentMaster.setDepartmentCode(departmentMaster.getDepartmentCode());
    			kubotaDepartmentMaster.setDepartmentName(departmentMaster.getDepartmentName());
    			apiResponse.setMessage("Department Added");
                apiResponse.setStatus(HttpStatus.OK.value());
               // apiResponse.setResult(departmentRepository.save(kubotaDepartmentMaster));
    		}
        }
        catch (DataIntegrityViolationException e)
        {
        	System.out.println("-----------------> DataIntegrityViolationException Error");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Department Master can't be saved");
        }
        catch (Exception e)
        {
        	System.out.println("-----------------> Exception Error");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Department Master can't saved");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @Deprecated
    @GetMapping("/getDepartment")
    public ResponseEntity<?> getDepartment(@RequestParam String applicableTo){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get departments");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(departmentRepository.getDepartmentByApplicableTo(applicableTo));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchByDepartmentCode")
    public ResponseEntity<?> searchByDepartmentCode(@RequestParam String departmentCode){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get department codes");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(departmentRepository.searchByDepartmentCode(departmentCode));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchDepartment")
    public ResponseEntity<?> searchDepartment(@RequestBody DepartmentSearchDto departmentSearchDto){
    	
    	List<DepartmentSearchResponse> response = departmentRepository.searchDepartment(departmentSearchDto.getDepartmentCode(),
                departmentSearchDto.getDepartmentName(),departmentSearchDto.getLinkedToDealer(),
                departmentSearchDto.getDealerCode(),departmentSearchDto.getPage(),departmentSearchDto.getSize());
    	
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get data Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(response);
        apiResponse.setCount(response.get(0).getTotalRecords());
        System.out.println("searchDepartment------>"+response.get(0).getTotalRecords());
//        apiResponse.setCount(departmentRepository.searchDepartmentCount(departmentSearchDto.getDepartmentCode(),
//                departmentSearchDto.getDepartmentName(),departmentSearchDto.getLinkedToDealer(),
//                departmentSearchDto.getDealerCode()));
        
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/departmentNameDropdown")
    public  ResponseEntity<?> departmentNameDropdown()
    {

        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> name=departmentRepository.departmentName();
        apiResponse.setMessage("Department Name Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(name);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/dealerCodeDropdown")
    public  ResponseEntity<?> dealerCodeDropdown()
    {

        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> code=departmentRepository.dealerCodeDropdown();
        apiResponse.setMessage("Dealer Code Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(code);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/linkedToDealerDropdown")
    public  ResponseEntity<?> linkedToDealerDropdown()
    {

        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> linkedToDealer=departmentRepository.linkedToDealer();
        apiResponse.setMessage("Linked To Dealer Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(linkedToDealer);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/changeActiveStatus")
    public ResponseEntity<?> changeActiveStatus(@RequestParam("id") Long id)
    {
        KubotaDepartmentMaster departmentMaster=departmentRepository.getOne(id);
        departmentMaster.setActiveStatus(departmentMaster.getActiveStatus().equals('Y') ?  'N' : 'Y');
        KubotaDepartmentMaster master=departmentRepository.save(departmentMaster);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Status updated successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(master);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/kaiDepartmentList")
    public  ResponseEntity<?> kaiDepartmentList()
    {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> list=departmentRepository.getdepartment();
        apiResponse.setMessage("Department List Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(list);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "/tocheckKaiDepartmentCode")
    public ResponseEntity<?>checkKaiDepartmentCode(@RequestParam("departmentCode") String departmentCode){
       ApiResponse<Object> apiResponse=new ApiResponse<>();
       String checkCode=departmentRepository.toCheckKaiDepartmentCode(departmentCode);
       apiResponse.setMessage("KAI Department Code get successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(checkCode);
       return ResponseEntity.ok(apiResponse);
   }
    
    @GetMapping(value = "/tocheckKaiDepartmentName")
    public ResponseEntity<?>checkKaiDepartmentName(@RequestParam("departmentName") String departmentName){
       ApiResponse<Object> apiResponse=new ApiResponse<>();
       String checkName=departmentRepository.toCheckKaiDepartmentName(departmentName);
       apiResponse.setMessage("Department Name Code get successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(checkName);
       return ResponseEntity.ok(apiResponse);
   }
    

}
