package com.i4o.dms.itldis.warranty.kaiinspectionsheet.controller;

import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartRequisitionItem;
import com.i4o.dms.itldis.spares.partrequisition.repository.SparePartRequisitionItemRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.warranty.deliverychallan.repository.WarrantyDeliveryChallanRepo;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.domain.KaiInspectionSheet;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.dto.KaiInspectionSheetResponseDto;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.dto.KaiInspectionSheetSearchDto;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.dto.KaiInspectionsheetDto;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.dto.WcrDCForInspetionSheetDto;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.repository.KaiInspectionSheetRepo;

import com.i4o.dms.itldis.warranty.kaiinspectionsheet.service.KaiInspectionSheetService;
import com.i4o.dms.itldis.warranty.warrantyclaimrequest.dto.WarrantyWcrView;
import com.i4o.dms.itldis.warranty.warrantyclaimrequest.repository.WarrantyWcrRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RequestMapping(value = "/api/warranty/kaiinspectionsheet")
public class KaiInspectionSheetController {

    @Autowired
    private WarrantyWcrRepo warrantyWcrRepo;

    @Autowired
    private WarrantyDeliveryChallanRepo warrantyDeliveryChallanRepo;

    @Autowired
    private KaiInspectionSheetRepo kaiInspectionSheetRepo;

    @Autowired
    private SparePartRequisitionItemRepo sparePartRequisitionItemRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private KaiInspectionSheetService kaiInspectionSheetService;

    @GetMapping("/WcrDcForKaiInspectionSheet")
    public ResponseEntity<?> WcrDcForKaiInspectionSheet(@RequestParam String wcrNo) {
        ApiResponse apiResponse = new ApiResponse();
        KaiInspectionsheetDto kaiInspectionsheetDto=new KaiInspectionsheetDto();
        kaiInspectionsheetDto.setWcrDcDetail(kaiInspectionSheetRepo.getWcrDcDetails(wcrNo,userAuthentication.getDealerId()));
        kaiInspectionsheetDto.setWarrantyPart(kaiInspectionSheetRepo.getJobCardPartWarrantyInfo(wcrNo,userAuthentication.getDealerId()));
        kaiInspectionsheetDto.setLabourCharge(kaiInspectionSheetRepo.getLabourChargeInfo(wcrNo,userAuthentication.getDealerId()));
        kaiInspectionsheetDto.setOutSideLabourCharge(kaiInspectionSheetRepo.getOutsideChargeInfo(wcrNo,userAuthentication.getDealerId()));
        
        apiResponse.setResult(kaiInspectionsheetDto);
        apiResponse.setMessage("get wcr and dc detail for kai inspection sheet ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/saveKaiInspectionSheet")
    public ResponseEntity<?> saveKaiInspectionSheet(@RequestPart KaiInspectionSheet kaiInspectionSheet, @RequestPart List<MultipartFile> multipartFileList) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse =kaiInspectionSheetService.saveKaiInspectionSheet(kaiInspectionSheet,multipartFileList);
        return ResponseEntity.ok(apiResponse);
    }



//    @PostMapping("/saveKaiInspectionSheetTest")
//    public ResponseEntity<?> saveKaiInspectionSheetTest(@RequestBody KaiInspectionSheet kaiInspectionSheet) {
//        ApiResponse apiResponse = new ApiResponse();
//
//        List<SparePartRequisitionItem> sparePartRequisitionItems=new ArrayList<>();
//
//        kaiInspectionSheet.getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
//            SparePartRequisitionItem sparePartRequisitionItem1=sparePartRequisitionItemRepo.getOne(sparePartRequisitionItem.getId());
//            sparePartRequisitionItem1.setKaiInspectionSheet(sparePartRequisitionItem.getKaiInspectionSheet());
//            sparePartRequisitionItem1.setKeyPartNumber(sparePartRequisitionItem.getKeyPartNumber());
//            sparePartRequisitionItem1.setClaimable(sparePartRequisitionItem.getClaimable());
//            sparePartRequisitionItems.add(sparePartRequisitionItem1);
//
//        });
//        kaiInspectionSheet.setSparePartRequisitionItem(sparePartRequisitionItems);
//        KaiInspectionSheet kaiInspectionSheet1= kaiInspectionSheetRepo.save(kaiInspectionSheet);
//        kaiInspectionSheet1.getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
//            SparePartRequisitionItem sparePartRequisitionItem1=sparePartRequisitionItemRepo.getOne(sparePartRequisitionItem.getId());
//            sparePartRequisitionItem1.setKaiInspectionSheet(kaiInspectionSheet1);
//            sparePartRequisitionItemRepo.save(sparePartRequisitionItem1);
//
//        });
//
//
//        apiResponse.setMessage("save kai inspection sheet");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return ResponseEntity.ok(apiResponse);
//    }


    @PostMapping("/searchKaiInspectionSheet")
    public ResponseEntity<?> searchKaiInspectionSheet(@RequestBody KaiInspectionSheetSearchDto searchDto) {
        ApiResponse apiResponse = new ApiResponse();
        List<KaiInspectionSheetResponseDto> kaiInspectionSheetResponseDtoList=
                kaiInspectionSheetRepo.kaiInspectionSheetSearch(searchDto.getInspectionNo(), searchDto.getFromDate(), searchDto.getToDate(), searchDto.getPage(), searchDto.getSize(), userAuthentication.getUsername(),searchDto.getWcrNo());

        Long count = 0L;
        if(kaiInspectionSheetResponseDtoList!=null && !kaiInspectionSheetResponseDtoList.isEmpty()){
        	count= kaiInspectionSheetResponseDtoList.get(0).getTotalCount();
        }
        apiResponse.setResult(kaiInspectionSheetResponseDtoList);
        apiResponse.setCount(count);
        apiResponse.setMessage("search kai inspection search");

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/viewKaiInspectionSheet")
    public ResponseEntity<?> searchKaiInspectionSheet(@RequestParam String inspectionNo,@RequestParam String wcrNo) {
        ApiResponse apiResponse = new ApiResponse();
        KaiInspectionsheetDto kaiInspectionsheetDto=new KaiInspectionsheetDto();
        kaiInspectionsheetDto.setKaiInspectionSheetView(kaiInspectionSheetRepo.findByInspectionNo(inspectionNo));
        kaiInspectionsheetDto.setWcrDcDetail(kaiInspectionSheetRepo.getWcrDcDetails(wcrNo,userAuthentication.getDealerId()));
        kaiInspectionsheetDto.setWarrantyPart(kaiInspectionSheetRepo.getJobCardPartWarrantyInfo(wcrNo,userAuthentication.getDealerId()));
        kaiInspectionsheetDto.setLabourCharge(kaiInspectionSheetRepo.getLabourChargeInfo(wcrNo,userAuthentication.getDealerId()));
        kaiInspectionsheetDto.setOutSideLabourCharge(kaiInspectionSheetRepo.getOutsideChargeInfo(wcrNo,userAuthentication.getDealerId()));
        apiResponse.setResult(kaiInspectionsheetDto);

        apiResponse.setMessage("view kai inspection by inspection no");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }



//    @GetMapping("/viewKaiInspectionSheet")
//    public ResponseEntity<?> searchKaiInspectionSheet(@RequestParam String inspectionNo,@RequestParam String wcrNo) {
//        ApiResponse apiResponse = new ApiResponse();
//        KaiInspectionsheetDto kaiInspectionsheetDto=new KaiInspectionsheetDto();
//        kaiInspectionsheetDto.setKaiInspectionSheetView(kaiInspectionSheetRepo.findByInspectionNo(inspectionNo));
//        kaiInspectionsheetDto.setWcrDcDetail(kaiInspectionSheetRepo.getWcrDcDetails(wcrNo,userAuthentication.getDealerId()));
//        kaiInspectionsheetDto.setWarrantyPart(kaiInspectionSheetRepo.getJobCardPartWarrantyInfo(wcrNo,userAuthentication.getDealerId()));
//        apiResponse.setResult(kaiInspectionsheetDto);
//
//        apiResponse.setMessage("view kai inspection by inspection no");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return ResponseEntity.ok(apiResponse);
//    }


    @GetMapping("/dropDownTypeOfUse")
    public ResponseEntity<?> dropDownTypeOfUse() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(kaiInspectionSheetRepo.dropDownTypeOfUse());
        apiResponse.setMessage("drop down type of use");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/dropDownFailureUnit")
    public ResponseEntity<?> dropDownFailureUnit() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(kaiInspectionSheetRepo.dropDownFailureUnit());
        apiResponse.setMessage("drop down failure type");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/dropDownFailureMode")
    public ResponseEntity<?> dropDownFailureMode() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(kaiInspectionSheetRepo.dropDownFailureMode());
        apiResponse.setMessage("drop down failure mode");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/autoCompleteInspectionNo")
    public ResponseEntity<?> autoCompleteInspectionNo(@RequestParam String inspectionNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(kaiInspectionSheetRepo.autoCompleteInspectionNo(inspectionNo, userAuthentication.getUsername()));
        apiResponse.setMessage("drop down failure mode");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/autoCompleteWcrNo")
    public ResponseEntity<?> autoCompleteWcrNo(@RequestParam String wcrNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(kaiInspectionSheetRepo.autoCompleteWcrNo(wcrNo, userAuthentication.getUsername()));
        apiResponse.setMessage("drop down failure mode");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
}
