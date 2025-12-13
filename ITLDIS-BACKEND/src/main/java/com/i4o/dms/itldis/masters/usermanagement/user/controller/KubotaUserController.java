package com.i4o.dms.itldis.masters.usermanagement.user.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.i4o.dms.itldis.common.model.MailEntity;
import com.i4o.dms.itldis.common.repo.MailRepo;
import com.i4o.dms.itldis.common.service.SendMailService;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.AddLoginUserDto;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.DynamicMenuResponse;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.KubotaUserSearchDto;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.KubotaUserSearchResponse;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.LoginDto;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.ResetPasswordModel;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.KubotaUserRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.UserFunctionalityMappingRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.service.LoginUserService;
import com.i4o.dms.itldis.security.jwt.JwtProvider;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.security.service.UserTokenService;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.EncryptionDecryptionUtil;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("/api/kubotauser")
public class KubotaUserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Autowired
    SendMailService sendMailService;
    
    @Autowired
    MailRepo mailRepo;
    
    @Autowired
    private KubotaUserRepository kubotaUserRepository;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private UserFunctionalityMappingRepository userFunctionalityMappingRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private UserTokenService userTokenService;	//Suraj-06/08/2024.

    @PostMapping
    public ResponseEntity<?> addKubotaUser(@Valid @RequestBody AddLoginUserDto loginUser) {
    	System.out.println();
    	System.out.println("addKubotaUser---->"+loginUser);
    	System.out.println();
        ApiResponse apiResponse = new ApiResponse();
    	try {
            apiResponse.setMessage("kubota User Added");
            apiResponse.setStatus(HttpStatus.OK.value());
            loginUserService.saveUserFunctions(loginUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/update")
    public ResponseEntity<?> updateKubotaUser(@Valid @RequestBody AddLoginUserDto loginUser) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("kubota User Added");
        apiResponse.setStatus(HttpStatus.OK.value());
        loginUserService.updateUserFunctions(loginUser);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
    	try {
    		ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
    		
    		System.out.println(EncryptionDecryptionUtil.encrypt(loginDto.getUsername())
            		+ " : " +EncryptionDecryptionUtil.encrypt(loginDto.getPassword()));
    		
            loginDto.setUsername(EncryptionDecryptionUtil.decrypt(loginDto.getUsername()));
            loginDto.setPassword(EncryptionDecryptionUtil.decrypt(loginDto.getPassword()));

//            if(userTokenService.isUserLoggedIn(loginDto.getUsername())) {
//            	userTokenService.logoutUser(loginDto.getUsername());
//            }
            
            Optional<Map<String, Object>> loginUser = loginDto.getAppLogin()
                    ? kubotaUserRepository.userAppLogin(loginDto.getUsername(), loginDto.getPassword())
                    : kubotaUserRepository.userLogin(loginDto.getUsername(), loginDto.getPassword());
            
            if (!loginUser.get().isEmpty()) {
            	Map<String, Object> map = loginUser.get();
            	if(map.get("msg").toString().equalsIgnoreCase("Done")){
            		//String authToken = jwtProvider.generateToken(loginUserService.getAuthenticatedUser(loginDto.getUsername()));
            		
            		/**
            		 * @author suraj.gaur
            		 * @impleNote implemented on 04-10-2024 for setting security related restrictions. Requested by client in VAPT points.
            		 */
            		String encryptedToken = jwtProvider.generateEncryptedToken(loginUserService.getAuthenticatedUser(loginDto.getUsername()));

            		Map<String, Object> resDto = new HashMap<>();
    				if(loginUser.isPresent()) {
    					Map<String, Object> logUser = loginUser.get();
    					resDto.put("departmentId", logUser.get("departmentId"));
    					resDto.put("name", logUser.get("name"));
    					resDto.put("msg", logUser.get("msg"));
    					resDto.put("userType", logUser.get("userType"));
    					resDto.put("departmentName", logUser.get("departmentName"));
    					resDto.put("designation", logUser.get("designation"));
    					resDto.put("dealerCode", logUser.get("dealerCode"));
    					resDto.put("id", EncryptionDecryptionUtil.encrypt(String.valueOf(logUser.get("id"))));
    					resDto.put("username", EncryptionDecryptionUtil.encrypt((String)logUser.get("username")));
    					resDto.put("mobileNo", EncryptionDecryptionUtil.encrypt((String)logUser.get("mobileNo")));
    					resDto.put("emailId", EncryptionDecryptionUtil.encrypt((String)logUser.get("emailId")));
    					resDto.put("loginUserId", EncryptionDecryptionUtil.encrypt(String.valueOf(logUser.get("loginUserId"))));
    				}
            		
            		userTokenService.recordToken(loginDto.getUsername(), encryptedToken);	//suraj.gaur_06/08/2024.
            		
    	            apiResponse.setToken(encryptedToken);
    	            apiResponse.setResult(resDto);
    	            apiResponse.setMessage("User login successfully");
    	            apiResponse.setStatus(HttpStatus.OK.value());
    	            return ResponseEntity.ok(apiResponse);
            	}else{
            		apiResponse.setMessage(map.get("msg").toString());
            	}
            }else{
            	apiResponse.setMessage("Invalid Credentials");
            }
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(apiResponse);
		} catch (Exception e) {
			logger.info("/login Exception: " + e.getMessage());
			logger.error("An error occurred in /login:", e);
			
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiResponse);
		}
        
    }

    @GetMapping("/getFunctionalityMappedToUser/{userId}")
    public ResponseEntity<?> getFunctionalityMappedToUser(@PathVariable Long userId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get functionality mapped to user");
        logger.info("username : " + userAuthentication.getUsername());
        logger.info("getDealerEmployeeId : " + userAuthentication.getDealerEmployeeId());
        logger.info("getDealerId : " + userAuthentication.getDealerId());
        logger.info("getKubotaEmployeeId : " + userAuthentication.getKubotaEmployeeId());
        logger.info("getManagementAccess :" + userAuthentication.getManagementAccess());
        List<DynamicMenuResponse> responses = new ArrayList<>();
        userFunctionalityMappingRepository.getFirstLevelFunctionality(userId).forEach(mappedFunctionsResponse -> {
            DynamicMenuResponse dynamicMenuResponse = new DynamicMenuResponse();
            dynamicMenuResponse.setFunctionality(mappedFunctionsResponse.getFunctionality());
            dynamicMenuResponse.setId(mappedFunctionsResponse.getId());
            dynamicMenuResponse.setParentId(mappedFunctionsResponse.getParentId());
            dynamicMenuResponse.setRouterLink(mappedFunctionsResponse.getRouterLink());
            dynamicMenuResponse.setSequenceNo(mappedFunctionsResponse.getSequenceNo());
            userFunctionalityMappingRepository
                    .getFunctionalityByParentIdAndUserId(mappedFunctionsResponse.getId(), userId).forEach(mappedFunctionsResponse1 -> {
                DynamicMenuResponse dynamicMenuResponse1 = new DynamicMenuResponse();
                dynamicMenuResponse1.setFunctionality(mappedFunctionsResponse1.getFunctionality());
                dynamicMenuResponse1.setId(mappedFunctionsResponse1.getId());
                dynamicMenuResponse1.setParentId(mappedFunctionsResponse1.getParentId());
                dynamicMenuResponse1.setRouterLink(mappedFunctionsResponse1.getRouterLink());
                dynamicMenuResponse1.setSequenceNo(mappedFunctionsResponse1.getSequenceNo());
                userFunctionalityMappingRepository
                        .getFunctionalityByParentIdAndUserId(mappedFunctionsResponse1.getId(), userId).forEach(mappedFunctionsResponse2 -> {
                    DynamicMenuResponse dynamicMenuResponse2 = new DynamicMenuResponse();
                    dynamicMenuResponse2.setFunctionality(mappedFunctionsResponse2.getFunctionality());
                    dynamicMenuResponse2.setId(mappedFunctionsResponse2.getId());
                    dynamicMenuResponse2.setParentId(mappedFunctionsResponse2.getParentId());
                    dynamicMenuResponse2.setRouterLink(mappedFunctionsResponse2.getRouterLink());
                    dynamicMenuResponse2.setSequenceNo(mappedFunctionsResponse2.getSequenceNo());
                    dynamicMenuResponse1.getChildren().add(dynamicMenuResponse2);
                });
                dynamicMenuResponse.getChildren().add(dynamicMenuResponse1);
            });
            responses.add(dynamicMenuResponse);
        });
        apiResponse.setResult(responses);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/checkRouterAccessibility")
    public ResponseEntity<?> checkRouterAccessibility(@RequestParam Long userId, @RequestParam String routerLink) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        try {
        	//Suraj-START-06-08-2024
            Long result = userFunctionalityMappingRepository.checkUserRouterAccessibility(userId, routerLink);
            if(result == 0) {
            	apiResponse.setResult(false);
            	apiResponse.setMessage("User Access Denied!");
            }
            else {
            	apiResponse.setResult(true);
            	apiResponse.setMessage("Good to Go!");
            }
            //Suraj-END-06-08-2024
        } catch (Exception e) {
            apiResponse.setResult(false);
            apiResponse.setMessage("User Access Denied!");
        }
        
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchKubotaUsers")
    public ResponseEntity<?> searchKubotaUsers(@RequestBody KubotaUserSearchDto kubotaUserSearchDto){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Kubota Users");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<KubotaUserSearchResponse> searchResult = kubotaUserRepository.searchKubotaUser(userAuthentication.getUsername(),kubotaUserSearchDto.getEmployeeCode(),
                kubotaUserSearchDto.getEmployeeName(),"kubota", kubotaUserSearchDto.getPage(),kubotaUserSearchDto.getSize()); 
        apiResponse.setResult(searchResult);        
        apiResponse.setCount(searchResult.get(0).getCount());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/assignRoleDropdown")
    public ResponseEntity<?> assignRoleDropdown() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Role");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(kubotaUserRepository.assignRole(0, ' '));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/assignFunctions")
    public ResponseEntity<?> assignFunctions(@RequestParam String loginUserId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Role");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(kubotaUserRepository.assignRole(Integer.parseInt(loginUserId), ' '));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/loginIdStatusDropdown")
    public ResponseEntity<?> loginIdStatusDropdown() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get login Id status");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(kubotaUserRepository.getLoginIdStatus());
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/employeeCodeDropdown")
    public ResponseEntity<?> employeeCodeDropdown(@RequestParam String employeeId,@RequestParam String forCreate) {
    	System.out.println("employeeCodeDropdown----->"+employeeId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Role");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(kubotaUserRepository.getEmployeeCodeList(employeeId,forCreate));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/viewKubotaUserDetails")
    public ResponseEntity<?> viewKubotaUserDetails(@RequestParam String userName) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Role");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(kubotaUserRepository.findByUserName(userName).get());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordModel model){
	   ApiResponse apiResponse = new ApiResponse();
       apiResponse.setMessage("Reset Password");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(kubotaUserRepository.resetPassword(model.getOldPassword(), model.getNewPassword(), model.getCount(),userAuthentication.getUsername()));
       return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String username) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Forgot Password");
        apiResponse.setStatus(HttpStatus.OK.value());
        Map<String, Object> map = kubotaUserRepository.forgotPassword(username);
        if(map.get("id")!=null){
        	MailEntity mailEntity = mailRepo.findById(((BigDecimal)map.get("id")).longValue()).get();
        	Boolean send = sendMailService.sendMail(mailEntity, null);
        	 if (send) {
             	mailEntity.setMailstatus("DONE");
             	mailEntity.setMailsentdate(new Date());
             	mailRepo.save(mailEntity);
			}
        }
        apiResponse.setResult(map.get("msg"));
        return ResponseEntity.ok(apiResponse);
    }

}
