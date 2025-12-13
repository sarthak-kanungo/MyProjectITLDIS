package com.i4o.dms.kubota.masters.kaicommonmaster.manageorghierarchy.controller;

import java.util.Date;

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

import com.i4o.dms.kubota.masters.kaicommonmaster.assignorghierarchytodealer.domain.AssignOrgLevelHierarchyMaster;
import com.i4o.dms.kubota.masters.kaicommonmaster.manageorghierarchy.repository.OrganizationHierarchyRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping("/api/master/kaicommonmaster/manageOrgHierarchy")
public class OragnizationHierarchyController {

	@Autowired
	private OrganizationHierarchyRepo organizationHierarchyRepo;
	@Autowired
	private UserAuthentication userAuthentication;

	@GetMapping("/validateCode")
	public ResponseEntity<?> validateCode(@RequestParam String code) {
		ApiResponse apiResponse = new ApiResponse();
		AssignOrgLevelHierarchyMaster org = organizationHierarchyRepo.findByHierarchyCode(code);
		if(org!=null){
			apiResponse.setMessage("Exist");
		}else {
			apiResponse.setMessage("Valid");
		}
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addHierarchy(@RequestBody AssignOrgLevelHierarchyMaster orgLevel) {
		ApiResponse apiResponse = new ApiResponse();
		if(orgLevel.getOrgHierarchyId()!=null){
			AssignOrgLevelHierarchyMaster orgLevelDB = organizationHierarchyRepo.findById(orgLevel.getOrgHierarchyId()).get();
			
			orgLevelDB.setHierarchyDesc(orgLevel.getHierarchyDesc());
			orgLevelDB.setParentOrgHierarchyId(orgLevel.getParentOrgHierarchyId());
			orgLevelDB.setIsactive(orgLevel.getIsactive());
			orgLevelDB.setModifiedby(userAuthentication.getLoginId());
			orgLevelDB.setModifieddate(new Date());
			organizationHierarchyRepo.save(orgLevelDB);
			apiResponse.setMessage("Organization Hierarchy updated successfully");
			
		}else{
			orgLevel.setCreatedby(userAuthentication.getLoginId());
			orgLevel.setCreateddate(new Date());
			organizationHierarchyRepo.save(orgLevel);
			apiResponse.setMessage("Organization Hierarchy added successfully");
		}		
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
}
