package com.i4o.dms.kubota.masters.usermanagement.dealerusers.controller;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.DealerMasterSearchDto;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/dealerMaster")
public class DealerMasterController {

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserAuthentication userAuthentication;

    @PostMapping(value = "/saveDealerMaster", consumes = {"multipart/form-data"})
    public ResponseEntity<?> saveDealerMaster(@ModelAttribute("dealerMaster") DealerMaster dealerMaster) {
        //Logo
        MultipartFile logoImage = dealerMaster.getLogoDocument();
        String photoName = logoImage.getOriginalFilename();
        String name = "logo" + System.currentTimeMillis() + "_" + photoName;
        storageService.store(logoImage, name);
        dealerMaster.setLogo(photoName);
        ApiResponse apiResponse = new ApiResponse();

        try
        {
            dealerMasterRepo.save(dealerMaster);
            apiResponse.setMessage("Dealer Master successfully saved.");
            apiResponse.setStatus(HttpStatus.OK.value());
        }
        catch (DataIntegrityViolationException e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Dealer Master can't saved because of duplicate records.");
        }
        catch (Exception e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Dealer master can't saved");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/dealerNameAuto")
    public ResponseEntity<?> dealerNameAuto(@RequestParam("dealerName") String dealerName)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> name=dealerMasterRepo.findByDealerNameContaining(dealerName);
        apiResponse.setMessage("Dealer Name get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(name);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/dealerCodeAuto")
    public ResponseEntity<?> dealerCodeAuto(@RequestParam("dealerCode") String dealerCode)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> code=dealerMasterRepo.findByDealerCodeContaining(dealerCode);
        apiResponse.setMessage("Dealer Code Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(code);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/allocatedTerritoryDropdown")
    public ResponseEntity<?> allocatedTerritoryDropdown()
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> code=dealerMasterRepo.getAllocatedTerritory();
        apiResponse.setMessage("allocated Territory Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(code);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/areaLevelDropdown")
    public ResponseEntity<?> areaLevelDropdown()
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> code=dealerMasterRepo.getAreaLevel();
        apiResponse.setMessage("Area Level Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(code);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getStatusForSearch")
    public ResponseEntity<?> getStatusForSearch()
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> code=dealerMasterRepo.getStatus();
        apiResponse.setMessage("Status Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(code);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/dealerStarRatingDropdown")
    public ResponseEntity<?> dealerStarRatingDropdown()
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> star=dealerMasterRepo.getAStarRating();
        apiResponse.setMessage("dealer Star Rating Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(star);
        return ResponseEntity.ok(apiResponse);
    }



    @GetMapping("/getDealerRegionInfo")
    public ResponseEntity<?> getDealerRegionInfo(@RequestParam(required=false) String showAll)
    {
        ApiResponse apiResponse=new ApiResponse();
        
        Boolean allDist = (showAll!=null && showAll.equals("Y"))?true:false;
        List<Map<String,Object>> regionInfo=dealerMasterRepo.getDealerRegionInfo(userAuthentication.getUsername(), allDist);
        apiResponse.setMessage("get dealer region info");

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(regionInfo);
        return ResponseEntity.ok(apiResponse);
    }


    //Stored Procedure Not Available
    @GetMapping("/autoCompleteTehsilCityPincode")
    public ResponseEntity<?> autoCompleteTehsilCityPincode(@RequestParam Long districtId,@RequestParam String code)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> data=dealerMasterRepo.autoCompleteTehsilCityPincode(code,districtId);
        apiResponse.setMessage("auto complete tehsil city pincode ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(data);
        return ResponseEntity.ok(apiResponse);
    }

    //Stored Procedure Not Available
    @GetMapping("/getPincodeDetail")
    public ResponseEntity<?> getPincodeDetail(@RequestParam Long cityId, @RequestParam Long pincodeId)
    {
        ApiResponse apiResponse=new ApiResponse();
        Map<String,Object> data=dealerMasterRepo.getPincodeDetailByPincodeId(cityId, pincodeId);
        apiResponse.setMessage("get pin code detail by pincode id  ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(data);
        return ResponseEntity.ok(apiResponse);
    }



    @PostMapping(value = "/searchDealer")
    public ResponseEntity<?>searchDealer(@RequestBody DealerMasterSearchDto dealerMasterSearchDto){

        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Dealer Master Search List");
        apiResponse.setResult( dealerMasterRepo.searchDealerMaster(
                dealerMasterSearchDto.getZone(),
                dealerMasterSearchDto.getRegion(),
                dealerMasterSearchDto.getArea(),
                dealerMasterSearchDto.getTerritoryLevel(),
                dealerMasterSearchDto.getDealerCode(),
                dealerMasterSearchDto.getDealerName(),
                dealerMasterSearchDto.getActiveStatus(),
                dealerMasterSearchDto.getSubsidyDealer(),
                dealerMasterSearchDto.getPage(),
                dealerMasterSearchDto.getSize()));

        apiResponse.setCount( dealerMasterRepo.searchDealerMasterCount(
                dealerMasterSearchDto.getZone(),
                dealerMasterSearchDto.getRegion(),
                dealerMasterSearchDto.getArea(),
                dealerMasterSearchDto.getTerritoryLevel(),
                dealerMasterSearchDto.getDealerCode(),
                dealerMasterSearchDto.getDealerName(),
                dealerMasterSearchDto.getActiveStatus(),
                dealerMasterSearchDto.getSubsidyDealer(),
                dealerMasterSearchDto.getPage(),
                dealerMasterSearchDto.getSize()));
        return ResponseEntity.ok(apiResponse);
    }


}
