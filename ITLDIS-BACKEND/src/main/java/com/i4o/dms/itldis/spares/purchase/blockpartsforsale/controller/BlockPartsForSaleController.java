package com.i4o.dms.itldis.spares.purchase.blockpartsforsale.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaDepartmentMaster;
import com.i4o.dms.itldis.spares.purchase.blockpartsforsale.dto.BlockPartsForSaleSearchRequest;
import com.i4o.dms.itldis.spares.purchase.blockpartsforsale.dto.BlockPartsForSaleSearchRespose;
import com.i4o.dms.itldis.spares.purchase.blockpartsforsale.repository.BlockPartsForSaleRepo;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(
        allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("/api/blockPartsForSale")
public class BlockPartsForSaleController {
	
	@Autowired
	BlockPartsForSaleRepo blockPartsForSaleRepo;
	
	@GetMapping("/getPartsNoByPartNo")
    public ResponseEntity<?> getPartsNoByPartNo(@RequestParam String partNoStr)
	{
        ApiResponse apiResponse = new ApiResponse();
        
        apiResponse.setResult(blockPartsForSaleRepo.getPartsNoByPartNo(partNoStr));
        
        apiResponse.setMessage("Get Part No Successful");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
	
	@PostMapping("/getBlockPartForSaleSearch")
    public ResponseEntity<?> getBlockPartForSaleSearch(@RequestBody BlockPartsForSaleSearchRequest request)
	{
        List<BlockPartsForSaleSearchRespose> response = blockPartsForSaleRepo.blockPartsforSaleSearch(
        		request.getPartNo(), request.getStatus(), request.getPage(), request.getSize());
        
        Long count = 0L;
		if (response != null && response.size() > 0) {
			count = response.get(0).getCount();
		}
		
		ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(response);
        apiResponse.setCount(count);       
        apiResponse.setMessage("Search Block Parts for Sale Successful");
        apiResponse.setStatus(HttpStatus.OK.value());
        
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping(value = "/changeActiveStatus")
    public ResponseEntity<?> changeActiveStatus(@RequestParam("id") Long id)
    {
		SparePartMaster sparePartMaster = blockPartsForSaleRepo.getOne(id);
		
		sparePartMaster.setActiveStatus(sparePartMaster.getActiveStatus().equals('Y') ?  'N' : 'Y');
		SparePartMaster master = blockPartsForSaleRepo.save(sparePartMaster);
		
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(master);
        apiResponse.setMessage("Status Updated Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        
        return ResponseEntity.ok(apiResponse);
    }
	
	@GetMapping(value = "/getPartDetailsByPartNo")
    public ResponseEntity<?> getPartDetailsByPartNo(@RequestParam String partNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Get Item details Successful");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(blockPartsForSaleRepo.partDetailsByPartNo(partNo));
        return ResponseEntity.ok(apiResponse);
    }
}
