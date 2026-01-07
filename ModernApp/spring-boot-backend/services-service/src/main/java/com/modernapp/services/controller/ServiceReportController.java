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
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        java.time.LocalDate start = null;
        java.time.LocalDate end = null;
        
        try {
            if (fromDate != null && !fromDate.isEmpty()) start = java.time.LocalDate.parse(fromDate, formatter);
            if (toDate != null && !toDate.isEmpty()) end = java.time.LocalDate.parse(toDate, formatter);
        } catch (Exception e) {
            // Ignore parse errors for mock
        }

        // Generate Mock Data
        for(int i=1; i<=25; i++) {
             ServiceDoneLapseDTO dto = new ServiceDoneLapseDTO();
             dto.setVinNo("TRCTR000" + String.format("%03d", i));
             dto.setModelCode("MOD-" + (500 + i));
             dto.setModelCodeDesc("Heavy Duty Tractor " + type(i));
             dto.setJobTypeDesc(i % 3 == 0 ? "Paid Service" : (i % 3 == 1 ? "1st Free Service" : "2nd Free Service"));
             
             String itemStatus = "DONE"; 
             // Simple mock logic for status
             if (i % 5 == 0) itemStatus = "PENDING";
             else if (i % 4 == 0) itemStatus = "NOT DONE";

             if (status != null && !status.isEmpty() && !status.equalsIgnoreCase("ALL") && !itemStatus.equalsIgnoreCase(status)) {
                 continue; // Filter by status
             }

             if (itemStatus.equals("NOT DONE")) {
                 dto.setJobCardNo("-");
                 dto.setJobCardDate("-");
                 dto.setHmr("-");
             } else {
                 dto.setJobCardNo("JC/2026/" + String.format("%04d", 1000 + i));
                 // Distribute dates across Jan 2026
                 String dateStr = String.format("%02d/01/2026", (i % 28) + 1);
                 dto.setJobCardDate(dateStr);
                 dto.setHmr(String.valueOf(10 * i));
                 
                 // Filter by Date Range
                 if (start != null && end != null) {
                     try {
                         java.time.LocalDate rowDate = java.time.LocalDate.parse(dateStr, formatter);
                         if (rowDate.isBefore(start) || rowDate.isAfter(end)) {
                             continue;
                         }
                     } catch (Exception e) {}
                 }
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

    @GetMapping("/done-lapse/export")
    public org.springframework.http.ResponseEntity<byte[]> exportDoneLapseReport(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String dealerCode) {

        // Logic to generate HTML data to be interpreted as Excel (Legacy behavior)
        List<ServiceDoneLapseDTO> data = getDoneLapseReport(fromDate, toDate, status, dealerCode);
        
        StringBuilder html = new StringBuilder();
        html.append("<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"></head><body>");
        html.append("<table border='0' cellspacing='0' cellpadding='4' width='95%' align='center'>");

        // Title Row (Merged)
        // Mimicking: Service Due Date / Lapse Report for Pradeep Kumar [Punjab] [3031] From 07/01/2026 To 07/01/2026
        String dealerInfo = "Modern Agri Sales [Rural District] [DLR001]"; // Mocked for now to match data
        String title = "Service Due Date / Lapse Report for " + dealerInfo + " From " + (fromDate != null ? fromDate : "") + " To " + (toDate != null ? toDate : "");
        
        html.append("<tr>");
        html.append("<td colspan='7' align='center' style='font-family:Calibri; font-size:13.5pt; font-weight:bold;'>").append(title).append("</td>");
        html.append("</tr>");

        // Header Row
        // Empty Row (Row 2)
        html.append("<tr><td colspan='7'></td></tr>");

        // Header Row (Row 3)
        html.append("<tr>");
        html.append("<td bgcolor='#f2f2f2' align='center' style='font-weight:bold; border: 1px solid #cccccc;'>S No.</td>");
        html.append("<td bgcolor='#f2f2f2' align='left' style='font-weight:bold; border: 1px solid #cccccc;'>Chassis No.</td>");
        html.append("<td bgcolor='#f2f2f2' align='left' style='font-weight:bold; border: 1px solid #cccccc;'>Model Code</td>");
        html.append("<td bgcolor='#f2f2f2' align='left' style='font-weight:bold; border: 1px solid #cccccc;'>Model Code Desc.</td>");
        html.append("<td bgcolor='#f2f2f2' align='left' style='font-weight:bold; border: 1px solid #cccccc;'>Job Type</td>");
        html.append("<td bgcolor='#f2f2f2' align='left' style='font-weight:bold; border: 1px solid #cccccc;'>Job Card No.</td>");
        html.append("<td bgcolor='#f2f2f2' align='left' style='font-weight:bold; border: 1px solid #cccccc;'>HMR</td>");
        html.append("</tr>");
        
        int sno = 1;
        for (ServiceDoneLapseDTO dto : data) {
            html.append("<tr>");
            html.append("<td align='center' style='border: 1px solid #cccccc;'>").append(sno++).append("</td>");
            html.append("<td align='left' style='border: 1px solid #cccccc;'>").append(dto.getVinNo()).append("</td>");
            html.append("<td align='left' style='border: 1px solid #cccccc;'>").append(dto.getModelCode()).append("</td>");
            html.append("<td align='left' style='border: 1px solid #cccccc;'>").append(dto.getModelCodeDesc()).append("</td>");
            html.append("<td align='left' style='border: 1px solid #cccccc;'>").append(dto.getJobTypeDesc()).append("</td>");
            html.append("<td align='left' style='border: 1px solid #cccccc;'>").append(dto.getJobCardNo() + " [" + dto.getJobCardDate() + "]").append("</td>");
            html.append("<td align='left' style='border: 1px solid #cccccc;'>").append(dto.getHmr()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table></body></html>");

        byte[] content = html.toString().getBytes();

        return org.springframework.http.ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ServiceDoneLapseReport.xls\"")
                .header(org.springframework.http.HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel")
                .body(content);
    }
}
