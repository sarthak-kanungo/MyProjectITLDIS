package com.i4o.dms.kubota.masters.usermanagement.kubotausers.controller;

import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.BranchDepotMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.BranchDepotMasterRepository;
import com.i4o.dms.kubota.security.service.UserAuthentication;
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
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/depot")
public class BranchDepotMasterController {

    @Autowired
    private BranchDepotMasterRepository branchDepotMasterRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @PostMapping(value = "/saveBranchDepot")
    public ResponseEntity<?> saveBranchDepot(@Valid @RequestBody BranchDepotMaster branchDepotMaster) {
        ApiResponse apiResponse = new ApiResponse();
        try
        {
            branchDepotMasterRepository.save(branchDepotMaster);
            apiResponse.setMessage("Branch depot successfully saved.");
            apiResponse.setStatus(HttpStatus.OK.value());
        }
        catch (DataIntegrityViolationException e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Branch Depot Master can't saved because of duplicate records.");
        }
        catch (Exception e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Branch Depot Master can't saved.");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getFollowUpType(@RequestParam(required = false) String poId) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Depot get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        if(poId==null || poId.equals("") || poId.equals("null"))
        	poId = null;
        apiResponse.setResult(branchDepotMasterRepository.getAllDepot(userAuthentication.getDealerId(),poId==null?null:Long.valueOf(poId)));
        return ResponseEntity.ok(apiResponse);
    }

//    @GetMapping("/getActiveKaiBranch")
//    public ResponseEntity<?> getActiveKaiBranch() {
//        ApiResponse apiResponse = new ApiResponse();
//        List<Map<String,Object>> branch=branchDepotMasterRepository.getAllActiveBranchName();
//         apiResponse.setMessage("Kai Branch get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(branch);
//        return ResponseEntity.ok(apiResponse);
//    }

    @GetMapping("/branchCodeAuto")
    public ResponseEntity<?> branchCodeAuto(@RequestParam("branchCode") String branchCode)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> code=branchDepotMasterRepository.findByBranchCodeContaining(branchCode);
        apiResponse.setMessage("branch code get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(code);
        return ResponseEntity.ok(apiResponse);
    }

@Deprecated
@GetMapping("/depotDropdown")
    public ResponseEntity<?> depotDropdown()
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> depot=branchDepotMasterRepository.getDepotDropdown();
        apiResponse.setMessage("depot get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(depot);
        return ResponseEntity.ok(apiResponse);
    }

}
