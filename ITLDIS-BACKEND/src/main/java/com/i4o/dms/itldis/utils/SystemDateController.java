package com.i4o.dms.itldis.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("/api")
public class SystemDateController {
    @GetMapping("/getDate")
    public ResponseEntity<?> getDate() {
        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String strDate = simpleDateFormat.format(date);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get date");
        apiResponse.setStatus(HttpStatus.OK.value());
        //apiResponse.setResult(strDate);
        apiResponse.setResult(date);	//Suraj-28/11/2022
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSystemGeneratedDate")
    public ResponseEntity<?> getSystemGeneratedDate() {
        Date date = new Date();
//        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String strDate = simpleDateFormat.format(date);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get date");
        apiResponse.setStatus(HttpStatus.OK.value());
        //apiResponse.setResult(strDate);
        apiResponse.setResult(date);	//Suraj-28/11/2022
        return ResponseEntity.ok(apiResponse);
    }

}
