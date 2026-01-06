package com.modernapp.services.controller;

import com.modernapp.services.dto.ServiceDoneLapseDTO;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/services/reports")
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceReportController {

    @GetMapping("/done-lapse")
    public List<ServiceDoneLapseDTO> getDoneLapseReport(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String dealerCode) {
        
        List<ServiceDoneLapseDTO> list = new ArrayList<>();
        
        // Generate Mock Data
        for(int i=1; i<=25; i++) {
             ServiceDoneLapseDTO dto = new ServiceDoneLapseDTO();
             dto.setVinNo("TRCTR000" + String.format("%03d", i));
             dto.setModelCode("MOD-" + (500 + i));
             dto.setModelCodeDesc("Heavy Duty Tractor " + type(i));
             dto.setJobTypeDesc(i % 3 == 0 ? "Paid Service" : (i % 3 == 1 ? "1st Free Service" : "2nd Free Service"));
             
             if (status != null && status.equalsIgnoreCase("NOT DONE")) {
                 dto.setJobCardNo("-");
                 dto.setJobCardDate("-");
                 dto.setHmr("-");
             } else {
                 dto.setJobCardNo("JC/2026/" + String.format("%04d", 1000 + i));
                 dto.setJobCardDate(String.format("%02d/01/2026", (i % 28) + 1));
                 dto.setHmr(String.valueOf(10 * i));
             }
             
             dto.setDealerCode("DLR001");
             dto.setDealerName("Modern Agri Sales");
             dto.setLocationName("Rural District");
             list.add(dto);
        }
        
        return list;
    }
    
    private String type(int i) {
        return i % 2 == 0 ? "XKS" : "ZYT";
    }
}
