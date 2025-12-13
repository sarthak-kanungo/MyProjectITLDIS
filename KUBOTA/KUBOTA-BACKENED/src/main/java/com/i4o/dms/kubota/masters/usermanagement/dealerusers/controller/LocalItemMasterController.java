package com.i4o.dms.kubota.masters.usermanagement.dealerusers.controller;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.LocalItemMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.LocalItemMasterSearchDto;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.LocalItemMasterRepo;
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
@RequestMapping(value = "/api/localItemMaster")
public class LocalItemMasterController {

    @Autowired
    private LocalItemMasterRepo localItemMasterRepo;

    @PostMapping
    public ResponseEntity<?> addBranchMaster(@Valid @RequestBody LocalItemMaster localItemMaster)
    {
        ApiResponse apiResponse = new ApiResponse();
        try {
            LocalItemMaster localItemMaster1 = localItemMasterRepo.save(localItemMaster);
            localItemMaster1.setDmsItemNo(NumberGenerator.generateBranchCode(localItemMaster1.getId()));
            localItemMasterRepo.save(localItemMaster1);
            apiResponse.setMessage("Local Item Master Added successfully");
            apiResponse.setStatus(HttpStatus.OK.value());
        }
        catch(DataIntegrityViolationException e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Local Item Master can't saved because of duplicate records.");
        }
        catch (Exception e)
        {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("local Item Master can't saved.");
        }
        return ResponseEntity.ok(apiResponse);
    }

   @GetMapping("/igstDropdown")
    public ResponseEntity<?> igstDropdown()
   {
       ApiResponse apiResponse=new ApiResponse();
       List<Map<String,Object>> igst=localItemMasterRepo.getIgstList();
       apiResponse.setMessage("IGST get successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(igst);
       return ResponseEntity.ok(apiResponse);
   }

//   @GetMapping("/getSgstAndCgst")
//    public ResponseEntity<?> getSgstAndCgst(@RequestParam("igst") Double igst)
//   {
//       ApiResponse apiResponse=new ApiResponse();
//       List<Map<String,Object>> data=localItemMasterRepo.getSgstAndCgst(igst);
//       apiResponse.setMessage("SGST and CGS value get successfully");
//       apiResponse.setStatus(HttpStatus.OK.value());
//       apiResponse.setResult(data);
//       return ResponseEntity.ok(apiResponse);
//   }

    @PostMapping("/searchLocalItemMaster")
    public ResponseEntity<?> searchLocalItemMaster(@RequestBody LocalItemMasterSearchDto localItemMasterSearchDto){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("data get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(localItemMasterRepo.searchItemMaster(localItemMasterSearchDto.getDmsItemNo(),
                localItemMasterSearchDto.getVendorItemNo(),localItemMasterSearchDto.getDealerVendorCode(),
                localItemMasterSearchDto.getPage(),localItemMasterSearchDto.getSize()));

        apiResponse.setCount(localItemMasterRepo.searchItemMasterCount(localItemMasterSearchDto.getDmsItemNo(),
                localItemMasterSearchDto.getVendorItemNo(),localItemMasterSearchDto.getDealerVendorCode(),
                localItemMasterSearchDto.getPage(),localItemMasterSearchDto.getSize()));

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/dmsItemNoAuto")
    public ResponseEntity<?> dmsItemNoAuto(@RequestParam("dmsItemNo") String dmsItemNo)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> itemNo=localItemMasterRepo.findByDmsItemNoContaining(dmsItemNo);
        apiResponse.setMessage("Dms Item No get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(itemNo);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/vendorItemNoAuto")
    public ResponseEntity<?> vendorItemNoAuto(@RequestParam("vendorItemNo") String vendorItemNo)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> vendorNo=localItemMasterRepo.findByVendorItemNoContaining(vendorItemNo);
        apiResponse.setMessage("vendor Item No get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(vendorNo);
        return  ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/dealerVendorCodeAuto")
    public ResponseEntity<?> dealerVendorCodeAuto(@RequestParam("vendorDealerCode") String vendorDealerCode)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> vendorCode=localItemMasterRepo.findByDealerVendorCodeContaining(vendorDealerCode);
        apiResponse.setMessage("vendor Dealer Code get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(vendorCode);
        return  ResponseEntity.ok(apiResponse);
    }


}
