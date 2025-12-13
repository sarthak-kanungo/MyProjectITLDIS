package com.i4o.dms.itldis.accpac.controller;

import com.i4o.dms.itldis.accpac.domain.AccPacDealerMaster;
import com.i4o.dms.itldis.accpac.repository.AccPacDealerMasterRepository;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "api/accpac")
public class AccPacController {

    @Autowired
    private AccPacDealerMasterRepository dealerMasterRepository;

    @PostMapping(value = "/uploadDealerMasterExcel")
    public ResponseEntity<?> uploadDealerMasterExcel(@RequestParam MultipartFile multipartFile) throws IOException {
        ApiResponse apiResponse = new ApiResponse();
        InputStream in = multipartFile.getInputStream();

        List<AccPacDealerMaster> accPacDealerMasters = Poiji.fromExcel(
                in,
                PoijiExcelType.XLS,
                AccPacDealerMaster.class,
                PoijiOptions.PoijiOptionsBuilder
                        .settings()
                        .headerStart(0)
                        .build()
        );

        dealerMasterRepository.saveAll(accPacDealerMasters);
        apiResponse.setMessage("Dealer Master Uploaded Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

     @GetMapping("/dealerCodeAuto")
     public ResponseEntity<?> dealerCodeAuto(@RequestParam("dealerCode") String dealerCode)
     {
         ApiResponse apiResponse=new ApiResponse();
         List<Map<String,Object>> code =dealerMasterRepository.findByDealerCodeContaining(dealerCode);
         apiResponse.setMessage("dealer code get successfully");
         apiResponse.setStatus(HttpStatus.OK.value());
         apiResponse.setResult(code);
         return ResponseEntity.ok(apiResponse);
     }

     @GetMapping("/getDetailsByDealerCode")
     public ResponseEntity<?> getDetailsByDealerCode(@RequestParam("dealerCode") String dealerCode)
     {
         ApiResponse apiResponse=new ApiResponse();
         List<Map<String,Object>> code=dealerMasterRepository.getDetailsByDealerCode(dealerCode);
         apiResponse.setMessage("Details Get Successfully");
         apiResponse.setStatus(HttpStatus.OK.value());
         apiResponse.setResult(code);
         return ResponseEntity.ok(apiResponse);
     }

    @GetMapping("/dealerTypeList")
    public ResponseEntity<?> dealerTypeList()
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> type=dealerMasterRepository.getDealerType();
        apiResponse.setMessage("Dealer Type Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(type);
        return ResponseEntity.ok(apiResponse);
    }

}
