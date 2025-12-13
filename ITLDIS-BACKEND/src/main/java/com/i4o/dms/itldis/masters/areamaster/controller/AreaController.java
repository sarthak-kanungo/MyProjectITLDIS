package com.i4o.dms.itldis.masters.areamaster.controller;

import com.i4o.dms.itldis.masters.areamaster.dto.AreaMaster;
import com.i4o.dms.itldis.masters.areamaster.model.*;
import com.i4o.dms.itldis.masters.areamaster.repository.*;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/master/areamaster")
public class AreaController {
    @Autowired
    private ModelRepo modelRepo;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private TehsilRepository tehsilRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PinCodeRepository pinCodeRepository;

    @Autowired
    private PostOfficeRepository postOfficeRepository;

    @Autowired
    private UserAuthentication userAuthentication;
    
    @GetMapping("/getPinCode")
    public ResponseEntity<?> getPinCode(Integer pinCode) {
        ApiResponse apiResponse = new ApiResponse();
        List pin = modelRepo.getPinCode(pinCode);
        apiResponse.setMessage("get pin Code");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(pin);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getByPinCode")
    public ResponseEntity<?> getByPinCode(@RequestParam Long pinCode,
                                          @RequestParam Long cityId) {
        ApiResponse apiResponse = new ApiResponse();
        Map<String, Object> pin = modelRepo.getByPinCode(cityId, pinCode);
        apiResponse.setMessage("get complete address by pin code");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(pin);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getPostOffice")
    public ResponseEntity<?> getPostOffice(Integer pinCode) {
        ApiResponse apiResponse = new ApiResponse();
        List postOffice = modelRepo.getPostOffice(pinCode);
        apiResponse.setMessage("get post office based on pin code");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(postOffice);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getAllTehsil")
    public ResponseEntity<?> getAllTehsil(@RequestParam String textValue) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> tehsil = modelRepo.getAllTehsil(userAuthentication.getDealerId(), textValue);
        apiResponse.setMessage("get all tehsil ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(tehsil);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getByLocality")
    public ResponseEntity<?> getByLocality(@RequestParam("postOffice") String postOffice) {
        ApiResponse apiResponse = new ApiResponse();
        Map<String, Object> locality = modelRepo.getByLocality(postOffice);
        apiResponse.setMessage("get complete address by postOffice");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(locality);

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/pinCodeAuto")
    public ResponseEntity<?> pinCodeAuto(@RequestParam("pinCode") String pinCode) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> pin = modelRepo.findByPinCodeContaining(pinCode);
        apiResponse.setMessage("Pin Code get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(pin);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/localityDropdown")
    public ResponseEntity<?> localityDropdown() {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> variant = modelRepo.getByPostOffice();
        apiResponse.setMessage("Data Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(variant);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/addAreaMaster")
    public ResponseEntity<?> addAreaMaster(@RequestBody List<AreaMaster> areaMasters) {
        areaMasters.forEach(areaMaster -> {
            State state = stateRepository.findByState(areaMaster.getState());
            if (state == null) {
                state = new State();
                state.setState(areaMaster.getState());
                state = stateRepository.save(state);
            }
            District district = districtRepository.findByDistrict(areaMaster.getDistrict());
            if (district == null) {
                district = new District();
                district.setDistrict(areaMaster.getDistrict());
                district.setState(state);
                district = districtRepository.save(district);
            }
            Tehsil tehsil = tehsilRepository.findByTehsil(areaMaster.getTehsil());
            if (tehsil == null) {
                tehsil = new Tehsil();
                tehsil.setTehsil(areaMaster.getTehsil());
                tehsil.setDistrict(district);
                tehsil = tehsilRepository.save(tehsil);
            }
            City city = cityRepository.findByCity(areaMaster.getCity());
            if (city == null) {
                city = new City();
                city.setCity(areaMaster.getCity());
                city.setTehsil(tehsil);
                city = cityRepository.save(city);
            }
            PinCode pinCode = pinCodeRepository.findByPinCode(areaMaster.getPincode());
            if (pinCode == null) {
                pinCode = new PinCode();
                pinCode.setPinCode(areaMaster.getPincode());
                pinCode.setCity(city);
                pinCode = pinCodeRepository.save(pinCode);
            }
            PostOffices postOffice = postOfficeRepository.findByPostOffice(areaMaster.getPostOffice());
            if (postOffice == null) {
                postOffice = new PostOffices();
                postOffice.setPinCode(pinCode);
                postOffice.setPostOffice(areaMaster.getPostOffice());
                postOfficeRepository.save(postOffice);
            }
        });

        return ResponseEntity.ok("");
    }


    @GetMapping("/getCountry")
    public ResponseEntity<?> getCountry() {
        ApiResponse apiResponse = new ApiResponse();
        Map<String, Object> country = modelRepo.getCountry();
        apiResponse.setMessage("Country List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(country);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getStateAutocomplete")
    public ResponseEntity<?> getStateAutocomplete(@RequestParam Long countryId,
                                                  @RequestParam String stateName) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> state = modelRepo.getStateAutoComplete(countryId,stateName);
        apiResponse.setMessage("State List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(state);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getDistrictAutocomplete")
    public ResponseEntity<?> getDistrictAutocomplete(@RequestParam Long stateId,@RequestParam String districtName) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> districtAutoComplete = modelRepo.getDistrictAutoComplete(stateId,districtName);
        apiResponse.setMessage("District List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(districtAutoComplete);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getTehsilAutocomplete")
    public ResponseEntity<?> getTehsilAutocomplete(@RequestParam Long districtId,@RequestParam String tehsilName) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> tehsilAutoComplete = modelRepo.getTehsilAutoComplete(districtId,tehsilName);
        apiResponse.setMessage("Tehsil List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(tehsilAutoComplete);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getCityAutocomplete")
    public ResponseEntity<?> getCityAutocomplete(@RequestParam Long tehsilId,@RequestParam String cityName) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> cityAutoComplete = modelRepo.getCityAutoComplete(tehsilId,cityName);
        apiResponse.setMessage("City List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(cityAutoComplete);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getPinCodeAutocomplete")
    public ResponseEntity<?> getPinCodeAutocomplete(@RequestParam Long cityId,@RequestParam String pincode) {
    	System.out.println("pincode--->"+pincode+"===="+"cityId--->"+cityId);
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> pinCodeAutoComplete = modelRepo.getPinCodeAutoComplete(pincode,cityId);
        System.out.println("pinCodeAutoComplete--->"+pinCodeAutoComplete);
        apiResponse.setMessage("Pin Code List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(pinCodeAutoComplete);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getSparesPinCodeAutocomplete")
    public ResponseEntity<?> getSparesPinCodeAutocomplete(@RequestParam Long cityId,@RequestParam String pincode) {
    	System.out.println("pincode--->"+pincode+"===="+"cityId--->"+cityId);
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> pinCodeAutoComplete = modelRepo.getSparesPinCodeAutoComplete(cityId, pincode);
        
        apiResponse.setMessage("Pin Code List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(pinCodeAutoComplete);
        return ResponseEntity.ok(apiResponse);
    }

  @GetMapping("/getPostOfficeAutocomplete")
    public ResponseEntity<?> getPostOfficeAutocomplete(@RequestParam Long pinCodeId,@RequestParam String postOffice) {
        ApiResponse apiResponse = new ApiResponse();
        System.out.println("post_office");
        List<Map<String, Object>> pinCodeAutoComplete = modelRepo.getPostOfficeAutoComplete(pinCodeId,postOffice);
        apiResponse.setMessage("Post Office List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(pinCodeAutoComplete);
        return ResponseEntity.ok(apiResponse);
    }


}
