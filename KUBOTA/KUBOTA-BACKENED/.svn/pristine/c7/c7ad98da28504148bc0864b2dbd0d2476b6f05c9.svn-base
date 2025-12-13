package com.i4o.dms.kubota.masters.service.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.masters.service.repository.CheckpointRepository;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api")
public class ChecklistController
{
    @Autowired
    private CheckpointRepository checkpointRepository;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/getAllAggregateWithCheckPoints")
    public ResponseEntity<?> getAllAggregateWithCheckPoints(@RequestParam String transType, @RequestParam String chassis,
    		@RequestParam(value="model",required=false) String model)
    {
    	System.out.println("transType,chassis,model--->"+transType+","+chassis+","+model);
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> code = checkpointRepository.getAllCheckpoint(transType,chassis,model);
        System.out.println("getAllAggregateWithCheckPoints--->"+code);
        apiResponse.setMessage("Checkpoint get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(code);
        return ResponseEntity.ok(apiResponse);
    }

}
