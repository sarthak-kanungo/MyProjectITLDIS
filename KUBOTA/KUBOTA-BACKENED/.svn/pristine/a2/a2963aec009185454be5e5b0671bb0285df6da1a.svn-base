package com.i4o.dms.kubota.masters.usermanagement.dealerusers.controller;


import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerBranchAndSubDealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.PartyMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.DealerBranchAndSubDealerSearchDto;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerBranchAndSubDealerRepo;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.NumberGenerator;
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
@RequestMapping(value = "/api/dealerBranchAndSubDealerMaster")
public class DealerBranchAndSubDealerMasterController {

    @Autowired
    private DealerBranchAndSubDealerRepo dealerBranchAndSubDealerRepo;

    @PostMapping
    public ResponseEntity<?> addBranchMaster(@Valid @RequestBody DealerBranchAndSubDealerMaster dealerBranchAndSubDealerMaster) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            DealerBranchAndSubDealerMaster dealerBranchAndSubDealerMaster1 = dealerBranchAndSubDealerRepo.save(dealerBranchAndSubDealerMaster);
            dealerBranchAndSubDealerMaster1.setBranchCode(NumberGenerator.generateBranchCode(dealerBranchAndSubDealerMaster1.getId()));
            dealerBranchAndSubDealerRepo.save(dealerBranchAndSubDealerMaster1);
            apiResponse.setMessage("Branch Master Added successfully");
            apiResponse.setStatus(HttpStatus.OK.value());
        }
        catch(DataIntegrityViolationException e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Dealer Branch can't saved because of duplicate records.");
        }
        catch(Exception e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Dealer Branch can't saved.");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/branchCategoryDropdown")
    public ResponseEntity<?> branchCategoryDropdown()
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> branch=dealerBranchAndSubDealerRepo.branchCategoryDropdown();
        apiResponse.setMessage("get Category");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(branch);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/subDealerCodeAuto")
    public ResponseEntity<?> subDealerCodeAuto(@RequestParam("branchCode") String branchCode)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> code=dealerBranchAndSubDealerRepo.findBySubDealerCodeContaining(branchCode);
        apiResponse.setMessage("Sub Dealer Code get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(code);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/subDealerNameAuto")
    public ResponseEntity<?> subDealerNameAuto(@RequestParam("subDealerName") String subDealerName)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> name=dealerBranchAndSubDealerRepo.findBySubDealerNameContaining(subDealerName);
        apiResponse.setMessage("sub Dealer Name get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(name);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/categoryAuto")
    public ResponseEntity<?> categoryAuto(@RequestParam("category") String category)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> categoryAuto=dealerBranchAndSubDealerRepo.findByCategoryContaining(category);
        apiResponse.setMessage("category get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(categoryAuto);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchDealerBranchMaster")
    public ResponseEntity<?> searchDealerBranchMaster(@RequestBody DealerBranchAndSubDealerSearchDto dealerBranchAndSubDealerSearchDto){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("data get successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerBranchAndSubDealerRepo.searchBranch(dealerBranchAndSubDealerSearchDto.getBranchCode(),
                dealerBranchAndSubDealerSearchDto.getSubDealerName(),dealerBranchAndSubDealerSearchDto.getCategory(),
                dealerBranchAndSubDealerSearchDto.getPage(),dealerBranchAndSubDealerSearchDto.getSize()));

        apiResponse.setCount(dealerBranchAndSubDealerRepo.searchBranchCount(dealerBranchAndSubDealerSearchDto.getBranchCode(),
                dealerBranchAndSubDealerSearchDto.getSubDealerName(),dealerBranchAndSubDealerSearchDto.getCategory(),
                dealerBranchAndSubDealerSearchDto.getPage(),dealerBranchAndSubDealerSearchDto.getSize()));

    return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/branchNameList")
    public ResponseEntity<?> branchNameList()
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> branch=dealerBranchAndSubDealerRepo.getBranchName();
        apiResponse.setMessage("branch Name get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(branch);
        return ResponseEntity.ok(apiResponse);
    }

}
