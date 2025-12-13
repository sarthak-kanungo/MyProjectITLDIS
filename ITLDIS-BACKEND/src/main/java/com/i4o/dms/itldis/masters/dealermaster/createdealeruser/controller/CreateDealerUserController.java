package com.i4o.dms.itldis.masters.dealermaster.createdealeruser.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.i4o.dms.itldis.masters.dealermaster.createdealeruser.domain.DealerRoleFunction;
import com.i4o.dms.itldis.masters.dealermaster.createdealeruser.dto.DealerSaveDataDto;
import com.i4o.dms.itldis.masters.dealermaster.createdealeruser.dto.DealerSearchDto;
import com.i4o.dms.itldis.masters.dealermaster.createdealeruser.dto.DealerSearchResponse;
import com.i4o.dms.itldis.masters.dealermaster.createdealeruser.repository.CreateDealerUserRepo;
import com.i4o.dms.itldis.masters.usermanagement.user.domain.LoginUser;
import com.i4o.dms.itldis.masters.usermanagement.user.domain.UserFunctionMapping;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.KubotaUserRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.UserFunctionalityMappingRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping(value = "/api/createUser")


public class CreateDealerUserController {
	
	@Autowired
	private CreateDealerUserRepo createDealerUserRepo;
	
	@Autowired
	 private UserAuthentication userAuthentication;
	
	 @Autowired
	 private KubotaUserRepository kubotaUserRepository;
	 
	 @Autowired
	 private UserFunctionalityMappingRepository funcationRepo;
	
	@GetMapping("/getDealer")
	public ResponseEntity<?> newDealer(@RequestParam("dealer") String dealer) {
		ApiResponse apiResponse = new ApiResponse();
		String userName = userAuthentication.getUsername();
		apiResponse.setResult(createDealerUserRepo.getNewDealer(dealer,userName));
		apiResponse.setMessage("New Dealer get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getEmployeeCodeForNewDealer")
	public ResponseEntity<?> employeeCodeForNewDealer(@RequestParam("employeeCode") String employeeCode,@RequestParam("dealerId") int dealerId) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setResult(createDealerUserRepo.getReportingEmployee(employeeCode,dealerId));
		apiResponse.setMessage("Employee Code for New Dealer get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getDealerRole")
	public ResponseEntity<?> dealerRole(@RequestParam("dealerId") Long dealerId,@RequestParam("hoOrDealer") String hoOrDealer) {
		ApiResponse apiResponse = new ApiResponse();
		if(userAuthentication.getDealerId()==null) {
			hoOrDealer="Y";
		}
		apiResponse.setResult(createDealerUserRepo.getDealerRole(dealerId, hoOrDealer));
		apiResponse.setMessage("Employee Code for New Dealer get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	
	
	@PostMapping(value = "/submitCreateNewUserForm")
    public ResponseEntity<?> saveDealer(@RequestBody DealerSaveDataDto saveData) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		System.out.println("vinay--"+userAuthentication.getLoginId());
		try {
			LoginUser loginUser = new LoginUser();
			Long checkForHo = userAuthentication.getDealerId();
			loginUser.setKubotaEmployeeId(null);
			loginUser.setDealerEmployeeId(saveData.getLoginUser().getDealerEmployeeId());
			if(checkForHo==null) {
				loginUser.setUserTypeId(2L);
				loginUser.setUserName(saveData.getLoginUser().getUserName());
				loginUser.setPassword(saveData.getLoginUser().getPassword());
				loginUser.setCreatedby(userAuthentication.getLoginId());
				loginUser.setIsactive('Y');
				LoginUser user = kubotaUserRepository.save(loginUser);
				if(user.getId()!=null) {
					saveData.getUserRoleManu().forEach(role ->{
						role.setLoginUserId(user.getId());
						role.setCreatedBy(userAuthentication.getLoginId());
						role.setCreatedDate(new Date());
						funcationRepo.save(role);
					});
					apiResponse.setMessage("New Dealer created successfuly");
					apiResponse.setStatus(HttpStatus.OK.value());
				}
			}
			else {
				apiResponse.setMessage("User Master can't  created");
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("User Master can't updated");
		}
		
		return ResponseEntity.ok(apiResponse);
	}
	
	
	@PostMapping("/getSearchDealer")
	public ResponseEntity<?> searchDealer(@RequestBody DealerSearchDto dealerSearchDto) {
		ApiResponse<List<DealerSearchResponse>> apiResponse = new ApiResponse();
		apiResponse.setMessage("data get successfully ");
		apiResponse.setStatus(HttpStatus.OK.value());
		Long dealerId = userAuthentication.getDealerId();
		List<DealerSearchResponse> result = null;
//		if (dealerId==null) {
//			String applicablefor="kubota";
//			result = (createDealerUserRepo.getSearchDealer(dealerSearchDto.getEmployeeCode(),
//					dealerSearchDto.getEmployeeName(),applicablefor, dealerSearchDto.getPage(),
//					dealerSearchDto.getSize()));
//		}
//		else {
//			String applicablefor="Dealer";
//			result = (createDealerUserRepo.getSearchDealer(dealerSearchDto.getEmployeeCode(),
//					dealerSearchDto.getEmployeeName(),applicablefor, dealerSearchDto.getPage(),
//					dealerSearchDto.getSize()));
//		}
		String applicablefor="Dealer";
		result = (createDealerUserRepo.getSearchDealer(userAuthentication.getUsername(),dealerSearchDto.getEmployeeCode(),
				dealerSearchDto.getEmployeeName(),applicablefor, dealerSearchDto.getPage(),
				dealerSearchDto.getSize()));
		

		apiResponse.setResult(result);
		Long count=0L;
		if (result!= null && result.size()>0) {
			count = result.get(0).getCount();
		}
		apiResponse.setCount(count);
		return ResponseEntity.ok(apiResponse);
	}
	
	
	@GetMapping("/getDuplicateUserId")
	public ResponseEntity<?> checkDuplicateUserName(@RequestParam("userName") String userName) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setResult(createDealerUserRepo.tofindDuplicateUserName(userName));
		apiResponse.setMessage("Duplicate Checking  successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getDealerDetailsForView")
	public ResponseEntity<?> dealerDetailsForView(@RequestParam("empId") String userName) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setResult(createDealerUserRepo.getDealerDetailsForView(userName));
		apiResponse.setMessage("Featch data successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	@PostMapping(value = "/updateDealerUser")
    public ResponseEntity<?> updateDealerUser(@RequestBody DealerSaveDataDto saveData) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			LoginUser loginUserForUpdate = kubotaUserRepository.getOne(saveData.getLoginUser().getId());
			loginUserForUpdate.setUserTypeId(2L);
			loginUserForUpdate.setUserName(saveData.getLoginUser().getUserName());
			loginUserForUpdate.setPassword(saveData.getLoginUser().getPassword());
			loginUserForUpdate.setIsactive(saveData.getLoginUser().getIsactive());
			loginUserForUpdate.setModifieddate(new Date());
			loginUserForUpdate.setModifiedby(userAuthentication.getLoginId());
			LoginUser updatedUser = kubotaUserRepository.save(loginUserForUpdate);
			if(updatedUser.getId()!=null) {
				saveData.getUserRoleManu().forEach(role ->{
					
					UserFunctionMapping m1 = funcationRepo.findByLoginUserIdAndRoleId(saveData.getLoginUser().getId(), role.getRoleId());
					if(m1!=null){
						role.setLastModifiedBy(userAuthentication.getLoginId());
						role.setLastModifiedDate(new Date());
						role.setId(m1.getId());
						funcationRepo.save(role);
					}else{
						if(role.getIsactive().equals('Y')){
							role.setCreatedDate(new Date());
							role.setCreatedBy(userAuthentication.getLoginId());
							role.setLoginUserId(saveData.getLoginUser().getId());
							funcationRepo.save(role);
						}
					}
					
					//createDealerUserRepo.roleManueId(role.getIsactive(),saveData.getLoginUser().getId(), role.getRoleId());
				});
			}
			apiResponse.setMessage("User Master updated successfully");
			apiResponse.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("User Master can't updated");
		}
		return ResponseEntity.ok(apiResponse);
	}

}
