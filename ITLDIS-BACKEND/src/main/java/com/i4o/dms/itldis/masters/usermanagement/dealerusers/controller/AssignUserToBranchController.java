package com.i4o.dms.itldis.masters.usermanagement.dealerusers.controller;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.AssignUserToBranch;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.AssignUserToBranchMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.AssignUserToBranchSearchDto;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.AssignUserToBranchSearchResponse;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.DealerEmployeeSearchDto;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.AssignUserToBranchRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/assignUserToBranchMaster")
public class AssignUserToBranchController {

    @Autowired
    private AssignUserToBranchRepo assignUserToBranchRepo;
    
    @Autowired
	 private UserAuthentication userAuthentication;

    @PostMapping
    public ResponseEntity<?> addAssignUserToBranch(@Valid @RequestBody AssignUserToBranchMaster  assignUserToBranchMaster)
    {
        ApiResponse apiResponse=new ApiResponse();
        try {
            AssignUserToBranchMaster assignUserToBranchMaster1 = assignUserToBranchRepo.save(assignUserToBranchMaster);
            apiResponse.setMessage("Assign User To Branch Saved Successfully");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(assignUserToBranchMaster1);
        }
        catch(DataIntegrityViolationException e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Assign User To Branch can't saved because of duplicate records.");
        }
        catch (Exception e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Assign User To Branch can't saved.");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/userIdAuto")
    public ResponseEntity<?> userIdAuto(@RequestParam("userId") Long userId)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> user=assignUserToBranchRepo.findByUserIdContaining(userId);
        apiResponse.setMessage("user id get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(user);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getDealerToAssignBranch")
    public ResponseEntity<?> dealerToAssignBranch(@RequestParam("dealervalue") String dealervalue){
        ApiResponse<List<Map<String, Object>>> apiResponse=new ApiResponse<>();
        String userName = userAuthentication.getUsername();
        List<Map<String,Object>> dealerToAssignBranch =assignUserToBranchRepo.getDealerToAssignBranch(dealervalue,userName);
        apiResponse.setMessage("Dealer details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerToAssignBranch);
        return ResponseEntity.ok(apiResponse);
    }
    
	@GetMapping("/getUserIdToAssignBranch")
	public ResponseEntity<?> userIdToAssignBranch(@RequestParam("userIdVal") String userIdVal,@RequestParam("dealerId") Long dealerId, @RequestParam("isFor") String isFor) {
		ApiResponse <List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		 List<Map<String,Object>> userToAssignBranch =assignUserToBranchRepo.getUserIdToAssignBranch(userIdVal,dealerId,isFor);
		apiResponse.setMessage("user Id get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(userToAssignBranch);
		return ResponseEntity.ok(apiResponse);
	}
    
	@GetMapping("/getBranchToAssignUser")
	public ResponseEntity<?> branchToAssignUser(@RequestParam("dealerId") Long dealerId) {
		System.out.println("dealerId-----"+dealerId);
		ApiResponse <List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		 List<Map<String,Object>> userToAssignBranch =assignUserToBranchRepo.getBranchToAssignUser(dealerId);
		apiResponse.setMessage("Branch details get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(userToAssignBranch);
		return ResponseEntity.ok(apiResponse);
	}
	
    @PostMapping(value = "/submitBranchToUser")
    public ResponseEntity<?> submitBranchToUser(@RequestBody AssignUserToBranch assignUserToBranch) {
        ApiResponse apiResponse = new ApiResponse();
        Long id = assignUserToBranch.getId();
        Long tfddeid = assignUserToBranchRepo.tofindDuplicateDealerEmployeeIdForAutb(id);
        try {
	//        	if (tfddeid==id) {
	//   		 apiResponse.setMessage("Branch already Assigned");
	//		}
	//   	else {
	           AssignUserToBranchMaster autbm = new  AssignUserToBranchMaster();
	           assignUserToBranch.getUserToBranch().forEach(item -> {
	           	autbm.setDealerEmployeeId(item.getDealerEmployeeId());
	           	autbm.setBranchId(item.getBranchId());
	           	autbm.setIsactive(item.getIsactive());
	           	autbm.setCreatedBy(1L);
	               assignUserToBranchRepo.save(autbm);
	           	apiResponse.setMessage("Assign User To Branch Saved Successfully");
	            apiResponse.setStatus(HttpStatus.OK.value());
	           });
	   	//}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Branch  can't Assign  to User");
		}

        return ResponseEntity.ok(apiResponse);
    }
    
    
    @PostMapping("/searchAssignUserToBranch")
	public ResponseEntity<?> searchAssignBranchUserTo(@RequestBody  AssignUserToBranchSearchDto assignBranchSearchDto) {
    	ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("data get successfully ");
		apiResponse.setStatus(HttpStatus.OK.value());
		List<AssignUserToBranchSearchResponse> result = assignUserToBranchRepo.searchAssignUserToBranch(assignBranchSearchDto.getDealership(),
				assignBranchSearchDto.getUserId(), assignBranchSearchDto.getPage(), assignBranchSearchDto.getSize());
		apiResponse.setResult(result);
		Long count=0L;
		if (result!= null && result.size()>0) {
			count = result.get(0).getCount();
		}
		apiResponse.setCount(count);
		return ResponseEntity.ok(apiResponse);
		}
    
	@GetMapping("/getDealerIdAndName")
	public ResponseEntity<?>dealerIdAndName(@RequestParam("empId") Long empId) {
		ApiResponse <List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		 List<Map<String,Object>> userToAssignBranch =assignUserToBranchRepo.getDealerIdAndName(empId);
		apiResponse.setMessage("Dealer details get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(userToAssignBranch);
		return ResponseEntity.ok(apiResponse);
	}
	@PostMapping(value="/updateUserBranchAssignment")
	public ResponseEntity<?> updateUserBranchAssignment(@RequestBody AssignUserToBranch assign) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			AssignUserToBranchMaster autbUpdate = assignUserToBranchRepo.getOne(assign.getId());
			assign.getUserToBranch().forEach(item -> {
				autbUpdate.setDealerEmployeeId(item.getDealerEmployeeId());
				autbUpdate.setBranchId(item.getBranchId());
				autbUpdate.setIsactive(item.getIsactive());
				autbUpdate.setLastModifiedDate(new Date());
				assignUserToBranchRepo.save(autbUpdate);
			});
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setMessage("Assigned Branch updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Assigned Branch  can't updated");
		}
		return ResponseEntity.ok(apiResponse);
	}



}
