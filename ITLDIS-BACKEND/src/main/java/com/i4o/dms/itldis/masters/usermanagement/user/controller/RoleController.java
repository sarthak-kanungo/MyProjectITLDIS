package com.i4o.dms.itldis.masters.usermanagement.user.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.i4o.dms.itldis.masters.usermanagement.user.domain.FunctionalityMaster;
import com.i4o.dms.itldis.masters.usermanagement.user.domain.RoleFunctionalityMapping;
import com.i4o.dms.itldis.masters.usermanagement.user.domain.RoleMaster;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.AssignedFunctionRoleDto;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.HierarchyService;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.MainTab;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.RoleFunctionDto;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.RoleSearchDto;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.RoleSearchResponse;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.RoleUnit;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.TreeNode;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.FunctionalityRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.RoleFunctionalityMappingRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.RoleRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private FunctionalityRepository functionalityRepository;

	@Autowired
	private RoleFunctionalityMappingRepository mappingRepository;

	@Autowired
	private UserAuthentication userAuthentication;

	@PostMapping
	public ResponseEntity<?> addRole(@RequestBody RoleFunctionDto roleFunctionDto) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			roleFunctionDto.getRoleMaster().setRoleCode("Role-" + System.currentTimeMillis());
			roleFunctionDto.getRoleMaster().setCreatedBy(userAuthentication.getLoginId());
			roleFunctionDto.getRoleMaster().setCreatedDate(new Date());
			RoleMaster roleMaster = roleRepository.save(roleFunctionDto.getRoleMaster());
			
			List<RoleFunctionalityMapping> mappings = new ArrayList<>();
			
			roleFunctionDto.getFunctionalityMasters().forEach(functionID -> {
				RoleFunctionalityMapping functionality = new RoleFunctionalityMapping();
				FunctionalityMaster fun = new FunctionalityMaster();
				fun.setId(functionID);
				functionality.setFunctionalityMaster(fun);
				functionality.setRoleMaster(roleMaster);
				mappings.add(functionality);
			});
			
//			roleFunctionDto.getFunctionalityMasters().forEach(functionalityMaster -> {
//				RoleFunctionalityMapping mapping = mappingRepository
//						.findByRoleAndFunctionalityMapping(roleMaster.getId(), functionalityMaster.getId());
//				if (mapping != null && !functionalityMaster.getAccessibleFlag())
//					mappingRepository.delete(mapping);
//
//				else if (functionalityMaster.getAccessibleFlag() && mapping == null) {
//					RoleFunctionalityMapping roleFunctionalityMapping = new RoleFunctionalityMapping();
////				roleFunctionalityMapping.setAssignedBy(userAuthentication.getKubotaEmployeeId());
//					roleFunctionalityMapping.setFunctionalityMaster(functionalityMaster);
//					roleFunctionalityMapping.setRoleMaster(roleMaster);
//					mappings.add(roleFunctionalityMapping);
//				}
//			});
			
			/*for (RoleFunctionalityMapping mappingData : mappings) {
				if (mappingData == null) {
					mappings.remove(mappingData);
				}
			}*/
			mappingRepository.saveAll(mappings);
			apiResponse.setMessage("Role assigned to functionality");
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setResult("Role assigned to functionality");
		} catch (DataIntegrityViolationException e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Role assigned to functionality can't saved because of duplicate records");
		} catch (Exception e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Role assigned to functionality can't saved");
		}
		return ResponseEntity.ok(apiResponse);
	}

	private Collection<TreeNode<RoleUnit>>  generateMenuTree(String applicableTo){
		
		List<RoleUnit> roles = new LinkedList<>();
		
		List<MainTab> tabs = roleRepository.getTabModuleByApplicableUser(applicableTo);
		if(tabs!=null){
			tabs.forEach(mainTab -> {
				
				roles.add(new RoleUnit(mainTab.getId(),mainTab.getFunctionality(),null));
				System.out.println("mainTab.getId() : "+mainTab.getId());
				List<Map<String, Object>> hieLevel = roleRepository.getAssignedRole(applicableTo, mainTab.getId());
				if(hieLevel!=null){
					for (Map<String, Object> assignedRoleVal : hieLevel) {
						if(assignedRoleVal.get("modulid")!=null){
							roles.add(new RoleUnit(((BigInteger)assignedRoleVal.get("modulid")).longValue(),(String)assignedRoleVal.get("module"),mainTab.getId()));
						
							if(assignedRoleVal.get("menuid")!=null){
								roles.add(new RoleUnit(((BigInteger)assignedRoleVal.get("menuid")).longValue(),(String)assignedRoleVal.get("functionality"),((BigInteger)assignedRoleVal.get("modulid")).longValue()));
							}
						}
					}
				}
			});
		}
		HierarchyService<RoleUnit, Long> service = new HierarchyService<>(roles);
		
		Collection<TreeNode<RoleUnit>> trees = service.getRoots().stream()
                .map(service::getTree)
                .collect(Collectors.toSet());
		
		return trees;
	}
	
	@GetMapping("/getAssignedFunctionalityToRole")
	public ResponseEntity<?> getAssignedFunctionalityToRole(@RequestParam String applicableTo,
			@RequestParam(required = false) String roleName) {
		
		Collection<TreeNode<RoleUnit>> trees = this.generateMenuTree(applicableTo);
		
		Gson gson = new Gson();
		String jsonRoles = gson.toJson(trees);
		System.out.println("Vikash : "+jsonRoles);
		
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setResult(trees);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setMessage("get assigned functionality to role");
		return ResponseEntity.ok(apiResponse);
	}

	private void callMyself(String applicableTo, Map<String, Object> assignedRoleVal) {
		List<Map<String, Object>> hieLevelRecur = roleRepository.getAssignedRole(applicableTo,(Long) assignedRoleVal.get("menuid"));

		if (hieLevelRecur == null) {
			return;
		} else {

			callMyself(applicableTo,assignedRoleVal);
		}		
	}

	@GetMapping("/getMainModulesFunctionalityForRole")
	public ResponseEntity<?> getMainModulesFunctionalityForRole(@RequestParam String applicableTo) {
		List<MainTab> tabs = roleRepository.getTabModuleByApplicableUser(applicableTo);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setMessage("get main modules for role");
		apiResponse.setResult(tabs);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/getSubModulesFunctionalityForRole")
	public ResponseEntity<?> getSubModulesFunctionalityForRole(@RequestParam String applicableTo) {
		/*
		 * List<AssignedFunctionRoleDto> roleDtos = new ArrayList<>();
		 * AssignedFunctionRoleDto assignedFunctionRoleDto = new
		 * AssignedFunctionRoleDto(); assignedFunctionRoleDto.setId(mainModuleId);
		 * //assignedFunctionRoleDto.setTabName(mainTab.getFunctionality());
		 * assignedFunctionRoleDto.setFunctionalityMasters(roleRepository.
		 * getAssignedRole(applicableTo, mainModuleId, roleName));
		 * roleDtos.add(assignedFunctionRoleDto);
		 */

		List<Map<String, Object>> tabs = roleRepository.getAssignedRole(applicableTo, null);
		List<Map<String, Object>> withoutDupes = tabs.stream().distinct().collect(Collectors.toList());
//		List<AssignedFunctionRoleDto> roleDtos = new ArrayList<>();
//		tabs.forEach(mainTab -> {
//			AssignedFunctionRoleDto assignedFunctionRoleDto = new AssignedFunctionRoleDto();
//			assignedFunctionRoleDto.setId(mainTab.getId());
//			assignedFunctionRoleDto.setFunctionality(mainTab.getFunctionality());
//			assignedFunctionRoleDto.setAssignedFlag(mainTab.getAssignedFlag());
//			assignedFunctionRoleDto
//					.setPermissions(roleRepository.getPermissionsByFunctionalityForKubotaUser(mainTab.getId()));
//			roleDtos.add(assignedFunctionRoleDto);
//		});
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setMessage("get main modules for role");
		apiResponse.setResult(roleRepository.getAssignedRole(applicableTo, null));
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/getRoles")
	public ResponseEntity<?> getRoles(@RequestParam String applicableTo) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setMessage("get applicable roles");
		apiResponse.setResult(roleRepository.getApplicableRole(applicableTo));
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/getAssignRoleFunctionToUser")
	public ResponseEntity<?> getAssignRoleFunctionToUser(@RequestParam(required = false) Long userId,
			@RequestParam Long roleId) {
		List<MainTab> tabs = roleRepository.assignRoleFunctionToUser(userId, roleId);
		List<AssignedFunctionRoleDto> roleDtos = new ArrayList<>();
		tabs.forEach(mainTab -> {
			AssignedFunctionRoleDto assignedFunctionRoleDto = new AssignedFunctionRoleDto();
			assignedFunctionRoleDto.setId(mainTab.getId());
			assignedFunctionRoleDto.setFunctionality(mainTab.getFunctionality());
			assignedFunctionRoleDto.setAssignedFlag(mainTab.getAssignedFlag());
			assignedFunctionRoleDto
					.setPermissions(roleRepository.getPermissionsByFunctionalityForKubotaUser(mainTab.getId()));
			roleDtos.add(assignedFunctionRoleDto);
		});
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setMessage("get applicable functions and roles");
		apiResponse.setResult(roleDtos);
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("/getApplicable")
	public ResponseEntity<?> getApplicable() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setMessage("get applicable");
		apiResponse.setResult(roleRepository.getApplicable());
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/roleCodeAuto")
	public ResponseEntity<?> roleCodeAuto(@RequestParam("roleCode") String roleCode, @RequestParam(value="type",required=false) String type) {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> code = roleRepository.findByRoleCodeContaining(roleCode, type);
		apiResponse.setMessage("Role Code get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(code);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/roleNameAuto")
	public ResponseEntity<?> roleNameAuto(@RequestParam("roleName") String roleName) {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> name = roleRepository.findByRoleNameContaining(roleName);
		apiResponse.setMessage("Role Name get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(name);
		return ResponseEntity.ok(apiResponse);
	}

	@PostMapping("/searchRole")
	public ResponseEntity<?> searchRole(@RequestBody RoleSearchDto roleSearchDto) {
		System.out.println("roleSearchDto--" + roleSearchDto);
		ApiResponse apiResponse = new ApiResponse();

		List<RoleSearchResponse> roleSearch = roleRepository.searchRole(roleSearchDto.getRoleCode(),
				roleSearchDto.getRoleName(), roleSearchDto.getIsActive(), roleSearchDto.getApplicableFor(),
				roleSearchDto.getPage(), roleSearchDto.getSize());
		Long count = 0l;
		if (roleSearch != null && roleSearch.size() > 0) {
			count = roleSearch.get(0).getTotalRecords();
		}

		apiResponse.setResult(roleSearch);
		apiResponse.setMessage("get Role");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setCount(count);

		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/searchRole/{id}")
	public ResponseEntity<?> searchRoleById(@PathVariable Long id) {
		ApiResponse apiResponse = new ApiResponse();
		RoleMaster rolemaster = roleRepository.findById(id).get();
		List<RoleFunctionalityMapping> mapping = mappingRepository.findByRoleMapping(id);

		/*List<MainTab> tabs = roleRepository.getTabModuleByApplicableUser(mapping.get(0).getRoleMaster().getApplicableTo());
		List<AssignedFunctionRoleDto> roleDtos = new ArrayList<>();
		tabs.forEach(mainTab -> {
			List<Map<String, Object>> assignedRoleList = new ArrayList<Map<String, Object>>();
			AssignedFunctionRoleDto assignedFunctionRoleDto = new AssignedFunctionRoleDto();
			assignedFunctionRoleDto.setId(mainTab.getId());
			assignedFunctionRoleDto.setTabName(mainTab.getFunctionality());

			List<Map<String, Object>> assignedRole = roleRepository
					.getAssignedRole(mapping.get(0).getRoleMaster().getApplicableTo(), mainTab.getId());
			mapping.forEach(mappings -> {
				for (Map<String, Object> assignedRoleVal : assignedRole) {
					if (assignedRoleVal.get("functionality").equals(mappings.getFunctionalityMaster().getMenuname())) {
						assert (assignedRoleVal.replace("accessibleFlag", false, true) == false);
						assignedRoleList.add(assignedRoleVal);
						break;
					}
				}
			});
			assignedFunctionRoleDto.setFunctionalityMasters(assignedRoleList);
			roleDtos.add(assignedFunctionRoleDto);

		});*/
		
		Collection<TreeNode<RoleUnit>> trees = this.generateMenuTree(rolemaster.getApplicableTo());
		
		HashMap<String, Object> hash_map = new HashMap<>();
		hash_map.put("roleMaster", rolemaster);
		hash_map.put("role", mapping);
		hash_map.put("functionality", trees);

		apiResponse.setMessage("get Role by id");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(hash_map);
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping(value = "/changeActiveStatus")
	public ResponseEntity<?> changeActiveStatus(@RequestParam("id") Long id) {
		RoleMaster roleMaster = roleRepository.getOne(id);
		roleMaster.setActiveStatus(roleMaster.getActiveStatus().equals('Y') ? 'N' : 'Y');
		RoleMaster master = roleRepository.save(roleMaster);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Status updated successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(master);
		return ResponseEntity.ok(apiResponse);
	}

	@PostMapping(value = "/updateRoleMaster")
	public ResponseEntity<?> updateRoleMaster(@RequestBody RoleFunctionDto roleFunctionDto) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			RoleMaster roleMaster = roleRepository.findById(roleFunctionDto.getRoleMaster().getId()).get();
			roleMaster.setRoleName(roleFunctionDto.getRoleMaster().getRoleName());
			roleMaster.setLastModifiedBy(userAuthentication.getLoginId());
			roleMaster.setLastModifiedDate(new Date());
			roleMaster.setActiveStatus(roleFunctionDto.getRoleMaster().getActiveStatus());
			roleRepository.saveAndFlush(roleMaster);
			
			List<RoleFunctionalityMapping> mappings = new ArrayList<>();
			
			mappingRepository.deleteByRoleId(roleMaster.getId());
			
			roleFunctionDto.getFunctionalityMasters().forEach(functionID -> {
				RoleFunctionalityMapping functionality = new RoleFunctionalityMapping();
				FunctionalityMaster fun = new FunctionalityMaster();
				fun.setId(functionID);
				functionality.setFunctionalityMaster(fun);
				functionality.setRoleMaster(roleMaster);
				mappings.add(functionality);
			});
			
//			roleFunctionDto.getFunctionalityMasters().forEach(functionalityMaster -> {
////				RoleFunctionalityMapping mapping = mappingRepository
////						.findByRoleAndFunctionalityMapping(roleMaster.getId(), functionalityMaster.getId());
//////				if (mapping != null && !functionalityMaster.getAccessibleFlag()) {
////					mappingRepository.delete(mapping);
//////				}
//
//				if (functionalityMaster.getAccessibleFlag()) {
//					RoleFunctionalityMapping roleFunctionalityMapping = new RoleFunctionalityMapping();
//					roleFunctionalityMapping.setFunctionalityMaster(functionalityMaster);
//					roleFunctionalityMapping.setRoleMaster(roleMaster);
//					mappings.add(roleFunctionalityMapping);
//				}
//			});
			mappingRepository.saveAll(mappings);
			apiResponse.setMessage("Role assigned to functionality");
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setResult("Role assigned to functionality");
		} catch (DataIntegrityViolationException e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Role assigned to functionality can't saved because of duplicate records");
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Role assigned to functionality can't saved");
		}
		return ResponseEntity.ok(apiResponse);
	}

}
