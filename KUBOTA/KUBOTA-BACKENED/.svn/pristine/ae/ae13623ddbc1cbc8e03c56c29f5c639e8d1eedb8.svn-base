package com.i4o.dms.kubota.masters.usermanagement.kubotausers.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.KubotaEmployeeMasterOrgHier;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto.EmployeeOrgHierDto;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto.EmployeeOrgHierStatusUpdateDto;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto.EmployeeSearchDto;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto.EmployeeSearchResponse;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.KubotaEmployeeMasterOrgHierRepository;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("/api/kubotaemployee")
public class KubotaEmployeeController {

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private KubotaEmployeeMasterOrgHierRepository orgHierRepository;

//    @PostMapping(value = "/add")
//    public ResponseEntity<?> addKubotaEmployee(@Valid  @RequestBody KubotaEmployeeMaster kubotaEmployeeMaster) {
//        ApiResponse apiResponse = new ApiResponse();
//     try {
//         KubotaEmployeeMaster kubotaEmployeeMaster1 = kubotaEmployeeRepository.getOne(userAuthentication.getKubotaEmployeeId());
//         kubotaEmployeeMaster.setCreatedBy(kubotaEmployeeMaster1);
//         kubotaEmployeeMaster.setLastModifiedBy(kubotaEmployeeMaster1);
//         KubotaEmployeeMaster employeeMaster = kubotaEmployeeRepository.save(kubotaEmployeeMaster);
//       //  kubotaEmployeeRepository.save(employeeMaster);
//         apiResponse.setMessage("kubota Employee Added");
//         apiResponse.setStatus(HttpStatus.OK.value());
//     }
//     catch (DataIntegrityViolationException e)
//     {
//         apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//         apiResponse.setMessage("Kubota Employee Master can't saved because of duplicate records");
//     }
//     catch (Exception e)
//     {
//         apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//         apiResponse.setMessage("Kubota Employee Master can't saved");
//     }
//        return ResponseEntity.ok(apiResponse);
//    }






    @ApiOperation("")
    @PostMapping(value = "/add")
    public ResponseEntity<?> addEnquirySource(@Valid @RequestBody KubotaEmployeeMaster kubotaEmployeeMaster) {
        KubotaEmployeeMaster kubotaEmployeeMaster1 = kubotaEmployeeRepository.getOne(userAuthentication.getKubotaEmployeeId());
       // kubotaEmployeeMaster.setCreatedBy(kubotaEmployeeMaster1);
        //kubotaEmployeeMaster.setLastModifiedBy(kubotaEmployeeMaster1);
        
        ApiResponse apiResponse = new ApiResponse<>();
        KubotaEmployeeMaster kubotaEmployeeMaster2 = kubotaEmployeeRepository.save(kubotaEmployeeMaster);
        apiResponse.setMessage("Employee Saved Successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(kubotaEmployeeMaster2);
        return ResponseEntity.ok(apiResponse);
    }

    //customise object and write procedure
    @GetMapping("/getKubotaEmployeeById")
    public ResponseEntity<?> getKubotaEmployee(@RequestParam Long empId){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("kubota Employee ");
        apiResponse.setResult(kubotaEmployeeRepository.getOne(empId));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/designationLevel")
    public ResponseEntity<?> designationLevel(){
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> designation=kubotaEmployeeRepository.designationLevel();
        apiResponse.setMessage("get designation");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(designation);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/designationLevelAuto")
    public ResponseEntity<?>designationLevelAuto(@RequestParam("desiganationLevel") String desiganationLevel, @RequestParam("departmentid") String departmentid)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> designation=kubotaEmployeeRepository.findByDesiganationLevelContaining(desiganationLevel, Integer.parseInt(departmentid));
        apiResponse.setMessage("Desiganation Level get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(designation);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/employeeCodeAuto")
    public ResponseEntity<?>employeeCodeAuto(@RequestParam("employeeCode") String employeeCode)
    {
        ApiResponse apiResponse=new ApiResponse();
       List<Map<String,Object>> code=kubotaEmployeeRepository.findByEmployeeCodeContaining(employeeCode);
        apiResponse.setMessage("Employee Code get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(code);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/employeeNameAuto")
    public ResponseEntity<?>employeeNameAuto(@RequestParam("employeeName") String employeeName)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> name=kubotaEmployeeRepository.findByEmployeeNameContaining(employeeName);
        apiResponse.setMessage("Employee Name get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(name);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getEmployeeNameByEmployeeCode")
    public ResponseEntity<?> getEmployeeNameByEmployeeCode(@RequestParam String employeeCode){
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> empCode=kubotaEmployeeRepository.findByEmployeeCode(employeeCode);
        apiResponse.setMessage("Employee Name get successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(empCode);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getReportingToHierarchy")
    public ResponseEntity<?> getReportingToHierarchy(@RequestParam String employeeCode){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Employee Name get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(kubotaEmployeeRepository.reportingToHierarchy(employeeCode));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getZonalManagers")
    public ResponseEntity<?> getZonalManagers(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Zonal Managers Fetched successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(kubotaEmployeeRepository.findZonalManagers(4L,true));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getRegionalManagers")
    public ResponseEntity<?> getRegionalManagers(@RequestParam Long zonalManagerId){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Regional Managers Fetched successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(kubotaEmployeeRepository.findRegionalManagers(4L,true,5L));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchEmployee")
    public ResponseEntity<?> searchEmployee(@RequestBody EmployeeSearchDto employeeSearchDto){
    	List<EmployeeSearchResponse> response = kubotaEmployeeRepository.searchEmployee
                (employeeSearchDto.getEmployeeCode(),
                        employeeSearchDto.getEmployeeName(),
                        employeeSearchDto.getPage(),
                        employeeSearchDto.getSize());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get employee");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(response);
        apiResponse.setCount(response.get(0).getTotalRecords());
        System.out.println("count--->"+response.get(0).getTotalRecords());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/changeActiveStatus")
    public ResponseEntity<?> changeActiveStatus(@RequestParam("id") Long id)
    {
        KubotaEmployeeMaster employeeMaster=kubotaEmployeeRepository.getOne(id);
        employeeMaster.setActiveStatus(employeeMaster.getActiveStatus().equals('Y') ?  'N' : 'Y');
        KubotaEmployeeMaster master=kubotaEmployeeRepository.save(employeeMaster);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Status updated successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(master);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getEmployeeDetailsByEmployeeCode")
    public ResponseEntity<?> getEmployeeDetailsByEmployeeCode(@RequestParam String employeeCode){
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> code=kubotaEmployeeRepository.findDetailsByEmployeeCode(employeeCode);
        apiResponse.setMessage("Employee Details get successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(code);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getEmployeeCodeByDepartmentName")
    public ResponseEntity<?> getEmployeeCodeByDepartmentName(@RequestParam String departmentName){
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> name=kubotaEmployeeRepository.findEmployeeCodeByDepartmentName(departmentName);
        apiResponse.setMessage("Employee Code get successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(name);
        return ResponseEntity.ok(apiResponse);
    }

   //for dealer master
    @GetMapping("/getDetailsByEmployeeCode")
    public ResponseEntity<?> getDetailsByEmployeeCode(@RequestParam String employeeCode){
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> empCode=kubotaEmployeeRepository.findEmployeeDetailsByEmployeeCode(employeeCode);
        apiResponse.setMessage("Employee Name and Designation get successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(empCode);
        return ResponseEntity.ok(apiResponse);
    }



    @GetMapping("/getReportingEmployeeNames")
    public ResponseEntity<?> getReportingEmployeeNames(@RequestParam Long id){
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> empCode=kubotaEmployeeRepository.findReportingEmployeeNames(id);
        apiResponse.setMessage("Employee Name get successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(empCode);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getEmployeeByEmployeeCode")
    public ResponseEntity<?> getEmployeeByEmployeeCode(@RequestParam String employeeCode){
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> empCode=kubotaEmployeeRepository.getEmployeeByEmployeeCode(employeeCode);
        apiResponse.setMessage("Employee Name and Designation get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(empCode);
        return ResponseEntity.ok(apiResponse);
    }
    
    @ApiOperation("")
    @PostMapping(value = "/update")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody KubotaEmployeeMaster kubotaEmployeeMaster) {
    	System.out.println("KubotaEmployeeMaster update---->"+kubotaEmployeeMaster.getEmployeeCode()
    												  +kubotaEmployeeMaster.getEmployeeName()
    												  +kubotaEmployeeMaster.getActiveStatus()
    												  +kubotaEmployeeMaster.getContactNo()
    												  +kubotaEmployeeMaster.getEmailId()
    												  +kubotaEmployeeMaster.getHoDepartmentId()
    												  +kubotaEmployeeMaster.getHoDesignationId()
    												  +kubotaEmployeeMaster.getHoDesignationLevelId()
    												  +kubotaEmployeeMaster.getReportingUserId()
    												  +kubotaEmployeeMaster.getManagementAccess());
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Employee Updated Successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(kubotaEmployeeRepository.updateEmployee(kubotaEmployeeMaster.getEmployeeCode(), kubotaEmployeeMaster.getEmployeeName(), 
        		kubotaEmployeeMaster.getActiveStatus(), kubotaEmployeeMaster.getContactNo(), kubotaEmployeeMaster.getEmailId(), 
        		kubotaEmployeeMaster.getHoDepartmentId(), kubotaEmployeeMaster.getHoDesignationId(), kubotaEmployeeMaster.getHoDesignationLevelId(), 
        		kubotaEmployeeMaster.getReportingUserId(), kubotaEmployeeMaster.getManagementAccess()));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getempMasterdepartmentForOrgHier")
    public ResponseEntity<?> empMasterdepartmentForOrgHier(){
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        String applicableTo="dealer";
        List<Map<String,Object>> empCode=kubotaEmployeeRepository.getempMasterdepartmentForOrgHier(applicableTo);
        apiResponse.setMessage("Employee Departments get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(empCode);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getLevelForOrgHier")
    public ResponseEntity<?> levelForOrgHier(@RequestParam String deptCode){
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        List<Map<String,Object>> empCode=kubotaEmployeeRepository.getLevelForOrgHier(deptCode);
        apiResponse.setMessage("Employee Level get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(empCode);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getLevelDetailsForOrgHier")
    public ResponseEntity<?> zoneForOrgHier(@RequestParam Long levelId,
    										@RequestParam Long orgHierId){
    	String userCode = userAuthentication.getUsername();
		String includeInactive="N";
    	ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        List<Map<String,Object>> empCode=kubotaEmployeeRepository.getLevelDetailsForOrgHier(userCode,levelId,orgHierId,includeInactive);
        apiResponse.setMessage("Level details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(empCode);
        return ResponseEntity.ok(apiResponse);
    }
    
    @PutMapping("/updateHoOrgHierStatus")
    public ResponseEntity<?> updateHoOrgHierStatus(@RequestBody EmployeeOrgHierStatusUpdateDto dto){
    	ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
    	
    	KubotaEmployeeMasterOrgHier hier = orgHierRepository.findById(dto.getId()).get();
    	hier.setLastModifiedBy(userAuthentication.getLoginId());
    	hier.setLastModifiedDate(new Date());
    	hier.setIsactive(dto.getStatus()?"Y":"N");
    	
    	orgHierRepository.save(hier);
    	
        apiResponse.setMessage("Employee Org Hier Status updated successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/assigneDeptInOrgHier")
    public ResponseEntity<?> assigneDeptInOrgHier(@RequestBody EmployeeOrgHierDto empOrgHierDto) {
    	ApiResponse apiResponse= new  ApiResponse<>();
    	Long id = empOrgHierDto.getHoEmployeeId();
    	KubotaEmployeeMasterOrgHier orgHier = new KubotaEmployeeMasterOrgHier();
    	empOrgHierDto.getSaveOrgHier().forEach(item->{
        	Long UsrID_vs_FieldRoleID = orgHierRepository.checkForHoUserStatus(id,item.getOrgHierarchyId());
        	if(UsrID_vs_FieldRoleID!=null){
            	KubotaEmployeeMasterOrgHier hier = orgHierRepository.findById(UsrID_vs_FieldRoleID).get();
            	hier.setLastModifiedBy(userAuthentication.getLoginId());
            	hier.setLastModifiedDate(new Date());
            	hier.setIsactive("Y");
            	orgHierRepository.save(hier);
        	}else{
	        	orgHier.setHoUsrId(id);
	    		orgHier.setOrgHierarchyId(item.getOrgHierarchyId());
	    		orgHier.setCreatedBy(userAuthentication.getLoginId());
	    		orgHier.setCreatedDate(new Date());
	    		orgHier.setIsactive("Y");
	    		orgHierRepository.save(orgHier);
        	}
    	});
		apiResponse.setMessage("Saved Organization Hierarchy  Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getOrgHierDetailsForHoEmp")
    public ResponseEntity<?> orgHierDetailsForHoEmp(@RequestParam Long hoEmpCode){
    	ApiResponse apiResponse= new  ApiResponse<>();
    	List<Map<String, Object>> hoEmpDetaisls = orgHierRepository.getOrgHierDetailsForHoEmp(hoEmpCode);
    	apiResponse.setStatus(HttpStatus.OK.value());
    	apiResponse.setResult(hoEmpDetaisls);
    	apiResponse.setMessage("Employee Organization Hierarchy get successfully");
        return ResponseEntity.ok(apiResponse);
    }
    
    
}


