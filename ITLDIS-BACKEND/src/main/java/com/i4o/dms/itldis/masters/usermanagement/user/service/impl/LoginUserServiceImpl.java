package com.i4o.dms.itldis.masters.usermanagement.user.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.itldis.masters.dealermaster.createdealeruser.domain.DealerRoleFunction;
import com.i4o.dms.itldis.masters.dealermaster.createdealeruser.repository.CreateDealerUserRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.domain.LoginUser;
import com.i4o.dms.itldis.masters.usermanagement.user.domain.UserFunctionMapping;
import com.i4o.dms.itldis.masters.usermanagement.user.dto.AddLoginUserDto;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.FunctionalityPermissionMappingRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.FunctionalityRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.KubotaUserRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.UserFunctionalityMappingRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.UserFunctionalityPermissionMappingRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.service.LoginUserService;
import com.i4o.dms.itldis.security.model.AuthenticatedUser;
import com.i4o.dms.itldis.security.service.UserAuthentication;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private KubotaUserRepository kubotaUserRepository;
    
    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeRepository;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;
    
    @Autowired
    private FunctionalityRepository functionalityRepository;

    @Autowired
    private UserFunctionalityMappingRepository userFunctionalityMappingRepository;

    @Autowired
    private FunctionalityPermissionMappingRepository functionalityPermissionMappingRepository;

    @Autowired
    private UserFunctionalityPermissionMappingRepository userFunctionalityPermissionMappingRepository;  

    @Autowired
    private CreateDealerUserRepo createDealerUserRepo;
    
    @Autowired
	 private UserFunctionalityMappingRepository funcationRepo;

    @Override
    @Transactional
    public void saveUserFunctions(AddLoginUserDto loginUserDto) {
    	
    	//LoginUser loginUser = kubotaUserRepository.findByUserName(loginUserDto.getLoginUser().getUserName()).get();
    	
    	//if (loginUser == null) {
    	loginUserDto.getLoginUser().setCreatedby(userAuthentication.getLoginId());
    	loginUserDto.getLoginUser().setCreateddate(new Date());
    	loginUserDto.getLoginUser().setUserTypeId(1L);
    	loginUserDto.getLoginUser().setIsactive('Y');
    	LoginUser loginUser = kubotaUserRepository.save(loginUserDto.getLoginUser());
		//} 
        for(UserFunctionMapping mapping : loginUserDto.getMapping()) {
        	DealerRoleFunction userFunctionMapping = new DealerRoleFunction();
        	userFunctionMapping.setLoginUserId(loginUser.getId());
        	userFunctionMapping.setIsactive(mapping.getIsactive());
        	userFunctionMapping.setRoleId(mapping.getRoleId());
        	createDealerUserRepo.save(userFunctionMapping);
        }
    }

    @Override
    @Transactional
    public void updateUserFunctions(AddLoginUserDto loginUserDto) {
    	
    	LoginUser loginUser = kubotaUserRepository.findById(loginUserDto.getLoginUser().getId()).get();
    	
    	loginUser.setPassword(loginUserDto.getLoginUser().getPassword());
    	loginUser.setLoginIdStatus(loginUserDto.getLoginUser().getLoginIdStatus());
    	loginUser.setModifiedby(userAuthentication.getLoginId());
    	loginUser.setModifieddate(new Date());
    	kubotaUserRepository.save(loginUser);
    	
    	//List<DealerRoleFunction> dealerRoleFunction = createDealerUserRepo.findByLoginUserId(loginUser.getId());
    	
//    	if (!loginUserDto.getMapping().isEmpty()) {
//    		for(DealerRoleFunction deleteMapping: loginUserDto.getMapping()) {			
//        		for(DealerRoleFunction functions: dealerRoleFunction) {
//        			if (deleteMapping.getRoleId() == functions.getRoleId()) {
//        				createDealerUserRepo.deleteById(functions.getId());
//    				}    			
//        		}    		
//        	}
//		}
    	
    	if(loginUser.getId()!=null) {
    		loginUserDto.getMapping().forEach(role ->{
				
				UserFunctionMapping m1 = funcationRepo.findByLoginUserIdAndRoleId(role.getLoginUserId(), role.getRoleId());
				if(m1!=null){
					role.setLastModifiedBy(userAuthentication.getLoginId());
					role.setLastModifiedDate(new Date());
					role.setId(m1.getId());
					funcationRepo.save(role);
				}else{
					if(role.getIsactive().equals('Y')){
						role.setCreatedDate(new Date());
						role.setCreatedBy(userAuthentication.getLoginId());
						role.setLoginUserId(role.getLoginUserId());
						funcationRepo.save(role);
					}
				}
				
				//createDealerUserRepo.roleManueId(role.getIsactive(),saveData.getLoginUser().getId(), role.getRoleId());
			});
		}
    	
    }
    
    @Override
    public AuthenticatedUser getAuthenticatedUser(String username) {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        LoginUser loginUser = kubotaUserRepository.findByUserName(username).get();
        Map<String,Object> map = kubotaUserRepository.getUserProfileDtls(username);
        if(map!=null){
        	authenticatedUser.setMobileNo((String)map.get("MobileNo"));
        	authenticatedUser.setEmailId((String)map.get("EmailId"));
        }
        //LoginUser loginUser = user.get();
       if (loginUser.getDealerEmployeeId()!=null){
            authenticatedUser.setBranchId(dealerEmployeeRepository.getBranchId(loginUser.getDealerEmployeeId()));
            authenticatedUser.setDealerEmployeeId(loginUser.getDealerEmployeeId());
            authenticatedUser.setDealerId(dealerEmployeeRepository.findById(loginUser.getDealerEmployeeId()).get().getDealerId());
            authenticatedUser.setManagementAccess(false);
        }else {
            authenticatedUser.setKubotaEmployeeId(loginUser.getKubotaEmployeeId());
            authenticatedUser.setManagementAccess(kubotaEmployeeRepository.findById(loginUser.getKubotaEmployeeId()).get().getManagementAccess());
        }
        authenticatedUser.setPassword(loginUser.getPassword().toString());
        authenticatedUser.setUsername(loginUser.getUserName());
        authenticatedUser.setLoginIdStatus(loginUser.getLoginIdStatus());
        authenticatedUser.setId(loginUser.getId());
        return authenticatedUser;
    }
}
