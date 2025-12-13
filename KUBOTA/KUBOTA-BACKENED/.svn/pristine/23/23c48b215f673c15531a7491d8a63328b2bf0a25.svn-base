package com.i4o.dms.kubota.salesandpresales.sales.blockmachinesforsale.controller;

import java.util.ArrayList;
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

import com.i4o.dms.kubota.masters.dbentities.machineTransferStatus;
import com.i4o.dms.kubota.masters.products.domain.MachineMaster;
import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.kubota.salesandpresales.sales.blockmachinesforsale.dto.BlockMachineForSaleSearchResponse;
import com.i4o.dms.kubota.salesandpresales.sales.blockmachinesforsale.dto.BlockMacinesForSaleSearchRequest;
import com.i4o.dms.kubota.salesandpresales.sales.blockmachinesforsale.dto.ChangeStatusByIdsRequest;
import com.i4o.dms.kubota.salesandpresales.sales.blockmachinesforsale.repository.BlockMachinesForSaleRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(
		allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, 
		methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("/api/blockMachinesForSale")
public class BlockMachinesForSaleController {
	
	@Autowired
	BlockMachinesForSaleRepo blockMachinesForSaleRepo;
	
	@PostMapping("/blockMachineForSaleSearch")
	public ResponseEntity<?> blockMachineForSaleSearch(@RequestBody BlockMacinesForSaleSearchRequest request){
		
		List<BlockMachineForSaleSearchResponse> response = 
				blockMachinesForSaleRepo.getBlockMachinesForSaleSearch(
						request.getProduct(), request.getSeries(), request.getModel(), request.getSubModel(), 
						request.getVariant(), request.getPage(), request.getSize());
        
        Long count = 0L;
		if (response != null && response.size() > 0) {
			count = response.get(0).getCount();
		}
		
		ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(response);
        apiResponse.setCount(count);       
        apiResponse.setMessage("Search Block Machines for Sale Successful");
        apiResponse.setStatus(HttpStatus.OK.value());
        
        return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value = "/changeActiveStatus")
    public ResponseEntity<?> changeActiveStatus(@RequestParam("id") Long id)
    {
		MachineMaster machineMaster = blockMachinesForSaleRepo.getOne(id);
		
		machineMaster.setActiveStatus(machineMaster.getActiveStatus().equals("Y") ?  "N" : "Y");
		MachineMaster master = blockMachinesForSaleRepo.save(machineMaster);
		
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(master);
        apiResponse.setMessage("Status Updated Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        
        return ResponseEntity.ok(apiResponse);
    }
	
	@PostMapping(value = "/changeActiveStatusByIds")
    public ResponseEntity<?> changeActiveStatus(@RequestBody ChangeStatusByIdsRequest request)
    {
		List<MachineMaster> machineMasterList = new ArrayList<>();
		for (Long id : request.getBlockIds()) {
			MachineMaster machineMaster = blockMachinesForSaleRepo.getOne(id);
			
			machineMaster.setActiveStatus(machineMaster.getActiveStatus().equals("Y") ?  "N" : "Y");
			MachineMaster master = blockMachinesForSaleRepo.save(machineMaster);
			
			machineMasterList.add(master);
		}
		
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(machineMasterList);
        apiResponse.setMessage("Status Updated Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        
        return ResponseEntity.ok(apiResponse);
    }
}
