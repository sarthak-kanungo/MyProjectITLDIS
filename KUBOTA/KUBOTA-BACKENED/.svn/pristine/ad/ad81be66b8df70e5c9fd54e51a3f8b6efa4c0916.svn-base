package com.i4o.dms.kubota.masters.usermanagement.dealerusers.controller;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerDesignationMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.DealerDesignationSearchDto;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.DealerDesignationSearchResponse;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerDesignationRepo;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/dealerDesignationMaster")
public class DealerDesignationMasterController {

    @Autowired
    private DealerDesignationRepo dealerDesignationRepo;

    @GetMapping("/departmentForDealerDesignation")
    public ResponseEntity<?>departmentForDealerDesignation()
    {
        ApiResponse<List<Map<String, Object>>> apiResponse=new ApiResponse<>();
        List<Map<String,Object>> dfdd =dealerDesignationRepo.getdepartmentForDealerDesignation();
        apiResponse.setMessage("Department For Dealer Designation get Succesfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dfdd);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/saveDealerDesignation")
    public ResponseEntity<?> addDealerDesignation(@RequestBody DealerDesignationMaster dealerDesignationMaster) {
        ApiResponse apiResponse = new ApiResponse();
        try {
              DealerDesignationMaster dealerDesignationMaster1 = dealerDesignationRepo.save(dealerDesignationMaster);
              //dealerDesignationRepo.save(dealerDesignationMaster1);
              apiResponse.setMessage("Dealer Designation Master Added successfully");
              apiResponse.setStatus(HttpStatus.OK.value());
          }
          catch(DataIntegrityViolationException e)
          {
              apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
              apiResponse.setMessage("Dealer Designation can't save because of duplicate records");
          }
          catch (Exception e)
          {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Dealer Designation can't saved");
          }
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/changeActiveStatus")
    public ResponseEntity<?> changeActiveStatus(@RequestParam("id") Long id)
    {
        DealerDesignationMaster designationMaster=dealerDesignationRepo.getOne(id);
        designationMaster.setActiveStatus(designationMaster.getActiveStatus().equals('Y') ?  'N' : 'Y');
        DealerDesignationMaster status=dealerDesignationRepo.save(designationMaster);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Status updated successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(status);
        return ResponseEntity.ok(apiResponse);
    }

   @PostMapping(value = "/searchDealerDesignationForSearchPage")
    public ResponseEntity<?> searchDealerDesignation(@RequestBody DealerDesignationSearchDto dealerDesignationSearchDto)
   {
       ApiResponse<List<DealerDesignationSearchResponse>> apiResponse=new ApiResponse<>();
       apiResponse.setMessage("dealer designation master data get successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       List<DealerDesignationSearchResponse> result=dealerDesignationRepo.searchDealerDesignation(dealerDesignationSearchDto.getDesignation(),dealerDesignationSearchDto.getDepartment(),dealerDesignationSearchDto.getPage()
    	       ,dealerDesignationSearchDto.getSize());

//       apiResponse.setCount(dealerDesignationRepo.searchDealerDesignationCount(dealerDesignationSearchDto.getDesignation(),dealerDesignationSearchDto.getPage(),
//               dealerDesignationSearchDto.getSize()));
       Long count=0L;
       if(result!=null && result.size()>0) {
      	 count=result.get(0).getTotalRecords();
       }
       apiResponse.setCount(count);
       apiResponse.setResult(result);

       return  ResponseEntity.ok(apiResponse);
   }

   @GetMapping(value = "/getDesignationForDealer")
    public ResponseEntity<?>designationAuto(@RequestParam("designation") String designation)
   {
       ApiResponse<List<Map<String, Object>>> apiResponse=new ApiResponse<>();
       List<Map<String,Object>> designationAuto=dealerDesignationRepo.getDesignationForDealer(designation);
       apiResponse.setMessage("Designation get successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(designationAuto);
       return ResponseEntity.ok(apiResponse);
   }
   
   @GetMapping(value = "/toCheckDesignationCode")
   public ResponseEntity<?>checkDesignationCode(@RequestParam("designationCode") String designationCode){
      ApiResponse<Object> apiResponse=new ApiResponse<>();
      String checCode=dealerDesignationRepo.toCheckDesignationCode(designationCode);
      apiResponse.setMessage("Designation Code get successfully");
      apiResponse.setStatus(HttpStatus.OK.value());
      apiResponse.setResult(checCode);
      return ResponseEntity.ok(apiResponse);
  }
  
   @GetMapping(value = "/toCheckDesignationName")
   public ResponseEntity<?>checkDesignationName(@RequestParam("designationName") String designationName){
      ApiResponse<Object> apiResponse=new ApiResponse<>();
      String checkName=dealerDesignationRepo.toCheckDesignationName(designationName);
      System.out.println("checCode--"+checkName);
      apiResponse.setMessage("Designation name get successfully");
      apiResponse.setStatus(HttpStatus.OK.value());
      apiResponse.setResult(checkName);
      return ResponseEntity.ok(apiResponse);
  }
   
   @GetMapping(value = "/viewDesignationDetails")
   public ResponseEntity<?>viewDesignation(@RequestParam("designationId") String designationId){
      ApiResponse<Object> apiResponse=new ApiResponse<>();
      List<Map<String,Object>> designationDetails=dealerDesignationRepo.viewDesignationDetails(designationId);
      Map<String, Object> deptName = dealerDesignationRepo.viewDeptForDesignationDetails(designationId);
      designationDetails.add(1, deptName);
      apiResponse.setMessage("Designation details get successfully");
      apiResponse.setStatus(HttpStatus.OK.value());
      apiResponse.setResult(designationDetails);
      return ResponseEntity.ok(apiResponse);
  }
   
   @GetMapping(value = "/updateDesignationDetails")
   public ResponseEntity<?>updateDesignation(@RequestParam("departmentId") Long departmentId,
											 @RequestParam("designationcode") String designationcode,
											 @RequestParam("designation") String designation,
											 @RequestParam("id") Long id){
      ApiResponse<Object> apiResponse=new ApiResponse<>();
      Integer response=dealerDesignationRepo.updateDesignationDetails(departmentId, designationcode,designation, id);
      apiResponse.setMessage("Designation details updated successfully");
      apiResponse.setStatus(HttpStatus.OK.value());
      apiResponse.setResult(response);
      return ResponseEntity.ok(apiResponse);
  }
   

}





