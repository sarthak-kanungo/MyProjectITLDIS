package com.i4o.dms.itldis.masters.kaicommonmaster.assignorghierarchytodealer.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.i4o.dms.itldis.masters.kaicommonmaster.assignorghierarchytodealer.domain.AssignOrgHierarchyToDealerMap;
import com.i4o.dms.itldis.masters.kaicommonmaster.assignorghierarchytodealer.dto.AssignOrgHierarchyToDealerDto;
import com.i4o.dms.itldis.masters.kaicommonmaster.assignorghierarchytodealer.dto.AssignOrgHierarchyToDealerResponse;
import com.i4o.dms.itldis.masters.kaicommonmaster.assignorghierarchytodealer.repository.AssignOrgHierarchyToDealerRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.PartyMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.PartySearchDto;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.PartySearchResponse;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaDepartmentMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.DepartmentRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.domain.FunctionalityPermissionMapping;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.FunctionPermissionDto;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.NumberGenerator;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping("/api/master/kaicommonmaster/assignOrgHierarchyToDealer")

public class AssignOrgHierarchyToDealerController {

	@Autowired
	private AssignOrgHierarchyToDealerRepo assignOrgHierarchyToDealerRepo;

	@Autowired
	private UserAuthentication userAuthentication;

	@GetMapping("/getLevelByDepartment")
	public ResponseEntity<?> getLevelByDepartment(@RequestParam("departmentCode") String departmentCode) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get levels");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(assignOrgHierarchyToDealerRepo.getLevelByDepartment(departmentCode));
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/getHierarchyByLevel")
	public ResponseEntity<?> getHierarchyByLevel(@RequestParam("levelId") String LEVELID,
			@RequestParam("orgHierarchyId") String ORGHIERID) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get hierarchy");
		apiResponse.setStatus(HttpStatus.OK.value());
//		System.out.println(LEVELID+".."+ORGHIERID+".."+userAuthentication.getUsername());
		apiResponse.setResult(assignOrgHierarchyToDealerRepo.getHierarchyByLevel(userAuthentication.getUsername(),
				LEVELID, ORGHIERID, "N"));
		return ResponseEntity.ok(apiResponse);
	}

	@PostMapping("/addAssignOrgHierarchyToDealer")
	public ResponseEntity<?> addAssignOrgHierarchyToDealer(
			@Valid @RequestBody List<AssignOrgHierarchyToDealerMap> assignOrgHierarchyToDealerMap) {
		ApiResponse apiResponse = new ApiResponse();
		try {

			List<AssignOrgHierarchyToDealerMap> assignOrgHierarchyToDealerMappings = new ArrayList<>();
			assignOrgHierarchyToDealerMap.forEach(assignOrgHierarchyToDealerMapObject -> {
				AssignOrgHierarchyToDealerMap assignOrgHierarchyToDealerMapping = new AssignOrgHierarchyToDealerMap();
				assignOrgHierarchyToDealerMapping
						.setDealerHoDepartmentId(assignOrgHierarchyToDealerMapObject.getDealerHoDepartmentId());
				assignOrgHierarchyToDealerMapping
						.setOrgHierarchyId(assignOrgHierarchyToDealerMapObject.getOrgHierarchyId());
				assignOrgHierarchyToDealerMapping.setCreatedBy(userAuthentication.getLoginId());
				assignOrgHierarchyToDealerMapping.setCreatedDate(new Date());
				assignOrgHierarchyToDealerMappings.add(assignOrgHierarchyToDealerMapping);
				Integer countDelete=assignOrgHierarchyToDealerRepo.
						deleteByDealerAssignOrgHier(assignOrgHierarchyToDealerMapObject.getDealerHoDepartmentId().getDealerId());
				
			});

			assignOrgHierarchyToDealerRepo.saveAll(assignOrgHierarchyToDealerMappings);
			apiResponse.setMessage("Assign Org Hierarchy To Dealer Added successfully");
			apiResponse.setStatus(HttpStatus.OK.value());
		} catch (DataIntegrityViolationException e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Assign Org Hierarchy To Dealer can't saved because of duplicate records");
		} catch (Exception e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Assign Org Hierarchy To Dealer can't saved");
		}
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/searchAllAssignOrgHierarchyToDealer")
	public ResponseEntity<?> searchAllAssignOrgHierarchyToDealer(
			@RequestBody AssignOrgHierarchyToDealerDto assignOrgHierarchyToDealerDto) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("data get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		Integer dealer=null;
		Integer department=null;
		if(assignOrgHierarchyToDealerDto.getDealerId()!=null) {
			dealer=assignOrgHierarchyToDealerDto.getDealerId().intValue();
		}
		if(assignOrgHierarchyToDealerDto.getDepartmentId()!=null)
		{
			department=assignOrgHierarchyToDealerDto.getDepartmentId().intValue();
		}
		List<AssignOrgHierarchyToDealerResponse> assignOrgHierToDealerResponse = assignOrgHierarchyToDealerRepo
				.searchAllAssignOrgHierarchyToDealer(dealer,department,
						assignOrgHierarchyToDealerDto.getPage(), assignOrgHierarchyToDealerDto.getSize());
		Long count = 0l;
		if (assignOrgHierToDealerResponse != null && assignOrgHierToDealerResponse.size() > 0) {
			count = assignOrgHierToDealerResponse.get(0).getCount();
		}

		apiResponse.setResult(assignOrgHierToDealerResponse);
		apiResponse.setCount(count);
		return ResponseEntity.ok(apiResponse);
	}

}