package com.i4o.dms.itldis.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.controller;


import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.domain.ActivityTypeBudgetMaster;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.dto.ActivityBudgetMasterSearchDto;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.repository.ActivityTypeBudgetMasterRepo;
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
@RequestMapping("/api/salesAndPreSales/activityTypeBudgetMaster")
public class ActivityTypeBudgetMasterController {

    @Autowired
    private ActivityTypeBudgetMasterRepo activityTypeBudgetMasterRepo;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @PostMapping(value = "/saveActivityTypeBudgetMaster")
    public ResponseEntity<?> saveActivityTypeBudgetMaster(@RequestBody ActivityTypeBudgetMaster activityTypeBudgetMaster)
    {
        KubotaEmployeeMaster kubotaEmployeeMaster = kubotaEmployeeRepository.getOne(userAuthentication.getKubotaEmployeeId());
        activityTypeBudgetMaster.setCreatedBy(kubotaEmployeeMaster);
        activityTypeBudgetMaster.setLastModifiedBy(kubotaEmployeeMaster);

        ApiResponse apiResponse = new ApiResponse();
            activityTypeBudgetMasterRepo.save(activityTypeBudgetMaster);
        apiResponse.setMessage("activity type budget master successfully saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
//
//    @GetMapping(value = "/getMaximumLimitByActivityType")
//    public ResponseEntity<?> getMaximumLimitByActivityType(@RequestParam(required = false) String activityType) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("maximum limit get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(activityTypeBudgetMasterRepo.getMaximumLimitByActivityType(activityType));
//        return ResponseEntity.ok(apiResponse);
//    }

    @GetMapping(value = "/getActivityTypeByPurpose")
    public ResponseEntity<?> getActivityTypeByPurpose(@RequestParam(required = false) String purpose) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("activity type get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityTypeBudgetMasterRepo.getActivityTypeByPurpose(purpose));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getActivityTypes")
    public ResponseEntity<?> getActivityTypes() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("activity type get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityTypeBudgetMasterRepo.getAllActivityType());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/budgetTypeDropdown")
    public  ResponseEntity<?> budgetTypeDropdown()
    {

        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Budget Type Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityTypeBudgetMasterRepo.budgetType());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/maximumLimitAuto")
    public ResponseEntity<?>maximumLimitAuto(@RequestParam("maximumLimit") Double maximumLimit)
    {
        ApiResponse<List<Map<String,Object>>> apiResponse=new ApiResponse<>();
        List<Map<String,Object>> maxLimit=activityTypeBudgetMasterRepo.findByMaximumLimitContaining(maximumLimit);
        apiResponse.setMessage("Maximum limit  get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(maxLimit);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/maximumDayMonthAuto")
    public ResponseEntity<?>maximumDayMonthAuto(@RequestParam("maximumDayMonth") Integer maximumDayMonth)
    {
        ApiResponse<List<Map<String,Object>>> apiResponse=new ApiResponse<>();
        List<Map<String,Object>> dayMonth=activityTypeBudgetMasterRepo.findByMaximumDayMonthContaining(maximumDayMonth);
        apiResponse.setMessage("Maximum day month get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dayMonth);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/kaiShareAuto")
    public ResponseEntity<?>kaiShareAuto(@RequestParam("kaiShare") Double kaiShare)
    {
        ApiResponse<List<Map<String,Object>>> apiResponse=new ApiResponse<>();
        List<Map<String,Object>> kai=activityTypeBudgetMasterRepo.findByKaiShareContaining(kaiShare);
        apiResponse.setMessage("KAI Share get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(kai);
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/searchActivityBudgetMaster")
    public ResponseEntity<?> searchActivityBudgetMaster(@RequestBody ActivityBudgetMasterSearchDto activityBudgetMasterSearchDto){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get activity budget Master");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityTypeBudgetMasterRepo.searchActivityBudget(activityBudgetMasterSearchDto.getActivityType(),
                activityBudgetMasterSearchDto.getBudgetType(),
                activityBudgetMasterSearchDto.getMaximumLimit(),
                activityBudgetMasterSearchDto.getMaximumDayMonth(),
                activityBudgetMasterSearchDto.getKaiShare(),
                activityBudgetMasterSearchDto.getPage(),
                activityBudgetMasterSearchDto.getSize()));

        apiResponse.setCount(activityTypeBudgetMasterRepo.searchActivityBudgetCount(activityBudgetMasterSearchDto.getActivityType(),
                activityBudgetMasterSearchDto.getBudgetType(),
                activityBudgetMasterSearchDto.getMaximumLimit(),
                activityBudgetMasterSearchDto.getMaximumDayMonth(),
                activityBudgetMasterSearchDto.getKaiShare(),
                activityBudgetMasterSearchDto.getPage(),
                activityBudgetMasterSearchDto.getSize()));
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/changeActiveStatus")
    public ResponseEntity<?> changeActiveStatus(@RequestParam Long budgetId){
        ApiResponse apiResponse = new ApiResponse();
        ActivityTypeBudgetMaster budgetMaster = activityTypeBudgetMasterRepo.getOne(budgetId);
        budgetMaster.setActiveStatus(budgetMaster.getActiveStatus().equals('Y')?'N':'Y');
        ActivityTypeBudgetMaster updated = activityTypeBudgetMasterRepo.save(budgetMaster);
        apiResponse.setMessage("STATUS UPDATED SUCCESSFULLY");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(updated);
        return ResponseEntity.ok(apiResponse);

    }


    @GetMapping("/getMarketingHeadsDropDown")
    public  ResponseEntity<?> getMarketingHeadsDropDown() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Budget Type Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityTypeBudgetMasterRepo.getHeadsForMarketingActivity());
        return ResponseEntity.ok(apiResponse);
    }
}
