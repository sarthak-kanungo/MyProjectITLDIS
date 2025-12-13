package com.i4o.dms.itldis.spares.purchase.binningslip.controller;

import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.purchase.binningslip.dto.BinningGrnResponse;
import com.i4o.dms.itldis.spares.purchase.grn.repository.SparePartGrnRepository;
import com.i4o.dms.itldis.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/spares/binningslip")
@Slf4j
public class BinningSlipController {

    @Autowired
    private SparePartGrnRepository sparePartGrnRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @GetMapping("/searchSparesGrnNo")
    public ResponseEntity<?> searchSparesGrnNo(@RequestParam String grnNo){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchSparesGrnNo");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartGrnRepository.searchSparesGrnNo(grnNo,userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getBinningSlipFromGrn")
    public ResponseEntity<?> getBinningSlipFromGrn(@RequestParam Long grnId){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchSparesGrnNo");
        apiResponse.setStatus(HttpStatus.OK.value());
        BinningGrnResponse response = new BinningGrnResponse();
        response.setSparesGrn(sparePartGrnRepository.getBinningSlipFromGrn(grnId,userAuthentication.getDealerId()));
        response.setSparesGrnItems(sparePartGrnRepository.getBinningSlipItemDetailsFromGrn(grnId,userAuthentication.getDealerId()));
        apiResponse.setResult(response);
        return ResponseEntity.ok(apiResponse);
    }

    //save binning slip
}
