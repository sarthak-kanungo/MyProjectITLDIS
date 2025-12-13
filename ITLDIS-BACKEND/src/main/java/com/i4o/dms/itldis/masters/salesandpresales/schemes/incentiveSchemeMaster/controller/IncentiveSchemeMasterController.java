package com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.domain.IncentiveSchemeMaster;
import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.domain.IncentiveSchemeMasterAttachment;
import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.domain.IncentiveSchemeMasterDetail;
import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.dto.IncentiveSchemeMasterSearchDto;
import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.dto.IncentiveSchemeSearchRequest;
import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.dto.IsmViewReponseDto;
import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.repository.IncentiveSchemeMasterRepo;
import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.repository.IsmAttachmentRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@RequestMapping("api/salesandpresales/incentiveSchemeMaster")
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class IncentiveSchemeMasterController {

    @Autowired
    private IncentiveSchemeMasterRepo incentiveSchemeRepo;
    
    @Autowired
	private UserAuthentication userAuthentication;
    
    @Autowired
    private DealerMasterRepo dealerMasterRepo;
    
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;
    
    @Autowired
    private StorageService storageService;
    
    @Autowired
    private IsmAttachmentRepo attachmentRepo;
    
    @Transactional
	@PostMapping("/saveIncentiveSchemeMaster")
    public ResponseEntity<?> saveIncentiveSchemeMaster(
    		@RequestPart(value = "incentiveSchemeMaster") IncentiveSchemeMaster incentiveSchemeMaster,
    		@RequestPart(value = "multipartFile", required = false) MultipartFile multipartFile)
    {
        ApiResponse<IncentiveSchemeMaster> apiResponse = new ApiResponse<>();
        
        incentiveSchemeMaster.setCreatedBy(userAuthentication.getLoginId());
        incentiveSchemeMaster.setCreatedDate(new Date());
        incentiveSchemeMaster.getIncentiveSchemeDetails().forEach(scheme -> {
        	DealerMaster dealer = dealerMasterRepo.findByDealerCode(scheme.getDealerCode());
        	if(dealer == null) {
        		scheme.setErrorMsg("Dealer code not found");
        		scheme.setDealerCode(null);
        	} else {
        		scheme.setDealer(dealer);
        		if(incentiveSchemeMaster.getSchemeType().equalsIgnoreCase("DSE") 
        				|| incentiveSchemeMaster.getSchemeType().equalsIgnoreCase("TM/BM/SM") 
        				|| incentiveSchemeMaster.getSchemeType().equalsIgnoreCase("GM"))
        		{
        			if(!StringUtils.isBlank(scheme.getDealerEmployeeCode()) 
        					&& !scheme.getDealerEmployeeCode().equalsIgnoreCase("ALL")){
    	        		DealerEmployeeMaster employee = dealerEmployeeMasterRepo.findByEmployeeCodeAndDealerId(scheme.getDealerEmployeeCode(), dealer.getId());
    	        		if(employee == null){
    	            		scheme.setErrorMsg("Employee code not found or not mapped to dealer");
    	            	}else{
    	            		scheme.setDealerEmployee(employee);
    	            	}
            		}
        		}
        	}
        });
        
        List<IncentiveSchemeMasterDetail> l = incentiveSchemeMaster.getIncentiveSchemeDetails().stream().filter(scheme -> scheme.getErrorMsg()!=null).collect(Collectors.toList());
        if(l != null && l.size() > 0){
        	apiResponse.setResult(incentiveSchemeMaster);
	        apiResponse.setMessage("Invalid Details");
	        apiResponse.setStatus(HttpStatus.OK.value());
	        return ResponseEntity.ok(apiResponse);
        }
        
        if(!CollectionUtils.isEmpty(incentiveSchemeMaster.getRegions())){
        	String regions = incentiveSchemeMaster.getRegions().stream().collect(Collectors.joining(","));
        	incentiveSchemeMaster.setSRegion(regions);
        }
        if(!CollectionUtils.isEmpty(incentiveSchemeMaster.getZones())){
        	String zones = incentiveSchemeMaster.getZones().stream().collect(Collectors.joining(","));
        	incentiveSchemeMaster.setSZone(zones);
        }
        if(!CollectionUtils.isEmpty(incentiveSchemeMaster.getProduct())){
        	String products = incentiveSchemeMaster.getProduct().stream().collect(Collectors.joining(","));
        	incentiveSchemeMaster.setSProduct(products);
        }
        if(!CollectionUtils.isEmpty(incentiveSchemeMaster.getSeries())){
        	String series = incentiveSchemeMaster.getSeries().stream().collect(Collectors.joining(","));
        	incentiveSchemeMaster.setSSeries(series);
        }
        if(!CollectionUtils.isEmpty(incentiveSchemeMaster.getModel())){
        	String models = incentiveSchemeMaster.getModel().stream().collect(Collectors.joining(","));
        	incentiveSchemeMaster.setSModel(models);
        }
        if(!CollectionUtils.isEmpty(incentiveSchemeMaster.getSubmodels())){
        	String subModels = incentiveSchemeMaster.getSubmodels().stream().collect(Collectors.joining(","));
        	incentiveSchemeMaster.setSSubModel(subModels);
        }
        if(!CollectionUtils.isEmpty(incentiveSchemeMaster.getVariant())){
        	String variants = incentiveSchemeMaster.getVariant().stream().collect(Collectors.joining(","));
        	incentiveSchemeMaster.setSVariant(variants);
        }
        if(!CollectionUtils.isEmpty(incentiveSchemeMaster.getItemNo())){
        	String items = incentiveSchemeMaster.getItemNo().stream().collect(Collectors.joining(","));
        	incentiveSchemeMaster.setSItem(items);
        }
        
        incentiveSchemeRepo.save(incentiveSchemeMaster);
        
        if(multipartFile != null && !multipartFile.isEmpty()) {
        	IncentiveSchemeMasterAttachment schemeAttachment = new IncentiveSchemeMasterAttachment();
        	
        	String fileName = multipartFile.getOriginalFilename();
        	String photoName = "sa_ism_" + System.currentTimeMillis() + "_" + fileName;
            
            storageService.store(multipartFile, photoName);	//Storing the file to the location
            
            schemeAttachment.setFileName(photoName);
            schemeAttachment.setFileType(multipartFile.getContentType());
            schemeAttachment.setIncentiveSchemeId(incentiveSchemeMaster.getId());
            
            attachmentRepo.save(schemeAttachment);
        }
        
        apiResponse.setMessage("Scheme Master Saved Successfully!");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/schemeTypeDropdown")
    public ResponseEntity<?> schemeTypeDropdown() {
        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<>();
        
        apiResponse.setMessage("Scheme Type Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(incentiveSchemeRepo.schemeType());
        
        return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author suraj.gaur
     * @return
     */
    @GetMapping("/getAllZones")
    public ResponseEntity<?> getAllZones() {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        
        List<Map<String, Object>> zones = incentiveSchemeRepo.getAllZones();
        
        if(zones.isEmpty() || zones == null){
        	apiResponse.setMessage("Zones are not found!");
            apiResponse.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(apiResponse);	
        }
        
        apiResponse.setMessage("Get Zones Success!");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(zones);
        
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getRegionsByZoneIds")
    public ResponseEntity<?> getRegions(@RequestParam("zoneIds") String zoneIds) {
        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<>();
        
        List<Map<String,Object>> subModel = incentiveSchemeRepo.getRegions(userAuthentication.getLoginId(), zoneIds);
        
        if(subModel.isEmpty() || subModel == null){
        	apiResponse.setMessage("Regions are not found!");
            apiResponse.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(apiResponse);	
        }
        
        apiResponse.setMessage("get regions");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(subModel);
        
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchIncentiveScheme")
    public ResponseEntity<?> searchIncentiveScheme(@RequestBody IncentiveSchemeSearchRequest request){
        ApiResponse<List<IncentiveSchemeMasterSearchDto>> apiResponse = new ApiResponse<>();
        
        List<IncentiveSchemeMasterSearchDto> result = incentiveSchemeRepo.searchIncentiveSchemes(userAuthentication.getUsername(), 
        		request.getSchemeFromDate(),
        		request.getSchemeToDate(),
        		request.getSchemeno(),
        		request.getSchemeType(),
        		request.getStatus(),
        		request.getPage(),
        		request.getSize());
        
        Long count = result.get(0).getRecordCount();
        
        if(result.isEmpty() || result == null){
        	apiResponse.setMessage("Incentive Scheme Search not ound!");
            apiResponse.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(apiResponse);	
        }
        
        apiResponse.setCount(count);
        apiResponse.setMessage("get incentive schemes list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        
        return ResponseEntity.ok(apiResponse);    	
    }
    
    /**
     * @author suraj.gaur
     * @param schemeNo
     * @return ResponseEntity<?>
     * @apiNote New View API with setters in interface DTO. 
     */
    @GetMapping("/viewIncentiveSchemeMaster")
    public ResponseEntity<?> viewIncentiveSchemeMaster(@RequestParam String schemeNo){
        ApiResponse<IsmViewReponseDto> apiResponse = new ApiResponse<>();
        
        IsmViewReponseDto schemeMaster = incentiveSchemeRepo.findBySchemeNumber(schemeNo);
        IncentiveSchemeMasterAttachment attachment = attachmentRepo.findByIncentiveSchemeId(schemeMaster.getId());
        
        schemeMaster.setSchemeAttachment(attachment);
        
        apiResponse.setMessage("Get Incentive Scheme Details Success!");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(schemeMaster);
        
        return ResponseEntity.ok(apiResponse);    	
    }
    
    @GetMapping("/getIncentiveSchemesNo")
    public ResponseEntity<?> getSchemeNo(@RequestParam("searchValue")String searchValue){
    	ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
    	
    	List<Map<String, Object>> result = incentiveSchemeRepo.getSchemeNo(searchValue, userAuthentication.getUsername());
    	
    	if(result.isEmpty() || result == null){
        	apiResponse.setMessage("Incentive scheme no. not found!");
            apiResponse.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(apiResponse);	
        }
    	
    	apiResponse.setMessage("get incentive schemes");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        
        return ResponseEntity.ok(apiResponse);
    }
}

