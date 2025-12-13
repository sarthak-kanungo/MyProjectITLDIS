package com.i4o.dms.kubota.masters.usermanagement.user.controller;

import com.i4o.dms.kubota.masters.usermanagement.user.domain.FunctionalityMaster;
import com.i4o.dms.kubota.masters.usermanagement.user.domain.FunctionalityPermissionMapping;
import com.i4o.dms.kubota.masters.usermanagement.user.dto.FunctionPermissionDto;
import com.i4o.dms.kubota.masters.usermanagement.user.repository.FunctionalityPermissionMappingRepository;
import com.i4o.dms.kubota.masters.usermanagement.user.repository.FunctionalityRepository;
import com.i4o.dms.kubota.masters.usermanagement.user.repository.PermissionRepository;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("/api/functionality")
public class FunctionalityController {

    @Autowired
    private FunctionalityRepository functionalityRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private FunctionalityPermissionMappingRepository functionalityPermissionMappingRepository;

    @PostMapping
    public ResponseEntity<?> addFunctions(@RequestBody List<FunctionalityMaster> functionalityMasters) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Functionality uploaded successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(functionalityRepository.saveAll(functionalityMasters));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/addFunctionsPermission")
    public ResponseEntity<?> addFunctionsPermission(@RequestBody List<FunctionPermissionDto> permissionDtos) {
        List<FunctionalityPermissionMapping> functionalityPermissionMappings = new ArrayList<>();
        permissionDtos.forEach(functionPermissionDto -> {
            FunctionalityPermissionMapping permissionMapping = new FunctionalityPermissionMapping();
            permissionMapping.setApplicableToDealer(functionPermissionDto.getApplicableToDealer());
            permissionMapping.setApplicableToKubota(functionPermissionDto.getApplicableToKubota());
            permissionMapping.setRouterLink(functionPermissionDto.getRouterLink());
            permissionMapping.setPermission(permissionRepository.getOne(functionPermissionDto.getPermissionId()));
            permissionMapping.setSeqNo(functionPermissionDto.getSeqNo());
            permissionMapping.setFunctionalityMaster(functionalityRepository.getOne(functionPermissionDto.getFunctionalityId()));
            functionalityPermissionMappings.add(permissionMapping);
        });
        functionalityPermissionMappingRepository.saveAll(functionalityPermissionMappings);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Functionality uploaded successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
}

