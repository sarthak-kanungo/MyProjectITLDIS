package com.i4o.dms.kubota.masters.spares.dbentities.controller;

import com.i4o.dms.kubota.masters.spares.dbentities.repository.SparesMtDocumentTypeRepo;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/spares/dbEntities")
public class SparesMtDocumentTypeController {

    @Autowired
    private SparesMtDocumentTypeRepo sparesMtDocumentTypeRepo;

    @GetMapping(value = "/getReferenceDocumentTypes")
    public ResponseEntity<?> getDocumentTypeForDropdown() {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Reference document types");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<Map<String, Object>> documentTypes =
                sparesMtDocumentTypeRepo.getReferenceDocumentTypes();
        apiResponse.setResult(documentTypes);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getSalesTypeDropdown")
    public ResponseEntity<?> getSalesTypeDropdown() {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Sales Type Dropdown List");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<Map<String, Object>> documentTypes =
                sparesMtDocumentTypeRepo.getSpareSalesTypeTypes();
        apiResponse.setResult(documentTypes);
        return ResponseEntity.ok(apiResponse);
    }
}
