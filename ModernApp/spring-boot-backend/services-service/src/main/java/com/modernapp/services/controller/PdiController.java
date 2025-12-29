package com.modernapp.services.controller;

import com.modernapp.services.dto.PendingChassisDto;
import com.modernapp.services.dto.PendingPdiResponse;
import com.modernapp.services.dto.PdiDetailDto;
import com.modernapp.services.dto.PdiDetailResponse;
import com.modernapp.services.dto.PdiDetailViewDto;
import com.modernapp.services.service.PdiService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/services/pdi")
@CrossOrigin(origins = "*")
public class PdiController {

    @Autowired
    private PdiService pdiService;

    /**
     * Health check endpoint to verify the service is running
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("PDI Service is running");
    }

    /**
     * GET /api/services/pdi/pending-chassis
     * Get paginated list of pending PDI chassis
     * 
     * Query parameters:
     * - chassisNo (optional): Filter by chassis number (partial match)
     * - dealerCode (optional): Filter by dealer code (use "ALL" for all dealers)
     * - page (optional, default: 0): Page number (0-indexed)
     * - size (optional, default: 15): Page size
     */
    @GetMapping("/pending-chassis")
    public ResponseEntity<PendingPdiResponse> getPendingChassisList(
            @RequestParam(required = false) String chassisNo,
            @RequestParam(required = false) String dealerCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        
        try {
            PendingPdiResponse response = pdiService.getPendingChassisList(
                chassisNo, dealerCode, page, size);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the error for debugging
            System.err.println("Error in getPendingChassisList: " + e.getMessage());
            e.printStackTrace();
            
            // Return empty response on error
            PendingPdiResponse errorResponse = new PendingPdiResponse();
            errorResponse.setContent(List.of());
            errorResponse.setTotalElements(0);
            errorResponse.setTotalPages(0);
            errorResponse.setCurrentPage(page);
            errorResponse.setSize(size);
            return ResponseEntity.ok(errorResponse); // Return 200 OK with empty data instead of 500
        }
    }

    /**
     * GET /api/services/pdi/pending-chassis/export
     * Export pending PDI chassis list to Excel
     * 
     * Query parameters:
     * - chassisNo (optional): Filter by chassis number
     * - dealerCode (optional): Filter by dealer code
     */
    @GetMapping("/pending-chassis/export")
    public ResponseEntity<byte[]> exportPendingChassisList(
            @RequestParam(required = false) String chassisNo,
            @RequestParam(required = false) String dealerCode) {
        
        try {
            List<PendingChassisDto> chassisList = pdiService.getAllPendingChassisForExport(
                chassisNo, dealerCode);
            
            // Create Excel workbook
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Pending PDI Chassis");
            
            // Create header style
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"S.No", "Chassis No", "Model Family", "Model Code", 
                               "Dealer Code", "Dealer Name", "Location", "Pending Days"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Create data rows
            int rowNum = 1;
            int sno = 1;
            for (PendingChassisDto chassis : chassisList) {
                Row row = sheet.createRow(rowNum++);
                
                row.createCell(0).setCellValue(sno++);
                row.createCell(1).setCellValue(chassis.getVinNo() != null ? chassis.getVinNo() : "");
                row.createCell(2).setCellValue(chassis.getModelFamily() != null ? chassis.getModelFamily() : "");
                row.createCell(3).setCellValue(chassis.getModelCode() != null ? chassis.getModelCode() : "");
                row.createCell(4).setCellValue(chassis.getDealerCode() != null ? chassis.getDealerCode() : "");
                row.createCell(5).setCellValue(chassis.getDealerName() != null ? chassis.getDealerName() : "");
                row.createCell(6).setCellValue(chassis.getLocationName() != null ? chassis.getLocationName() : "");
                row.createCell(7).setCellValue(chassis.getPdiPendingDays() != null ? chassis.getPdiPendingDays() : 0);
            }
            
            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            // Convert to byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            
            byte[] excelBytes = outputStream.toByteArray();
            
            // Set response headers
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            responseHeaders.setContentDispositionFormData("attachment", 
                "pending_pdi_chassis_" + System.currentTimeMillis() + ".xlsx");
            
            return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(excelBytes);
                
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * GET /api/services/pdi/details
     * Get paginated list of PDI details (completed PDI records)
     * 
     * Query parameters:
     * - chassisNo (optional): Filter by chassis number (partial match)
     * - dealerCode (optional): Filter by dealer code (use "ALL" for all dealers)
     * - fromDate (optional): Filter by PDI done date from (format: dd/MM/yyyy)
     * - toDate (optional): Filter by PDI done date to (format: dd/MM/yyyy)
     * - status (optional): Filter by status (ok, Atleast One NotOk, or all)
     * - useDateRange (optional, default: false): Whether to use date range filter
     * - page (optional, default: 0): Page number (0-indexed)
     * - size (optional, default: 15): Page size
     */
    @GetMapping("/details")
    public ResponseEntity<PdiDetailResponse> getPdiDetailsList(
            @RequestParam(required = false) String chassisNo,
            @RequestParam(required = false) String dealerCode,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "false") boolean useDateRange,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        
        try {
            PdiDetailResponse response = pdiService.getPdiDetailsList(
                chassisNo, dealerCode, fromDate, toDate, status, useDateRange, page, size);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Error in getPdiDetailsList: " + e.getMessage());
            e.printStackTrace();
            
            PdiDetailResponse errorResponse = new PdiDetailResponse();
            errorResponse.setContent(List.of());
            errorResponse.setTotalElements(0);
            errorResponse.setTotalPages(0);
            errorResponse.setCurrentPage(page);
            errorResponse.setSize(size);
            return ResponseEntity.ok(errorResponse);
        }
    }

    /**
     * GET /api/services/pdi/details/export
     * Export PDI details list to Excel
     */
    @GetMapping("/details/export")
    public ResponseEntity<byte[]> exportPdiDetailsList(
            @RequestParam(required = false) String chassisNo,
            @RequestParam(required = false) String dealerCode,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "false") boolean useDateRange) {
        
        try {
            List<PdiDetailDto> pdiList = pdiService.getAllPdiDetailsForExport(
                chassisNo, dealerCode, fromDate, toDate, status, useDateRange);
            
            // Create Excel workbook
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("PDI Details");
            
            // Create header style
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"S.No", "Chassis No", "PDI No", "PDI Done Date", 
                               "Model Family", "Dealer Code", "Dealer Name", "Location"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Create data rows
            int rowNum = 1;
            int sno = 1;
            for (PdiDetailDto pdi : pdiList) {
                Row row = sheet.createRow(rowNum++);
                
                row.createCell(0).setCellValue(sno++);
                row.createCell(1).setCellValue(pdi.getVinNo() != null ? pdi.getVinNo() : "");
                row.createCell(2).setCellValue(pdi.getPdiNo() != null ? pdi.getPdiNo() : "");
                row.createCell(3).setCellValue(pdi.getPdiDate() != null ? pdi.getPdiDate() : "");
                row.createCell(4).setCellValue(pdi.getModelFamily() != null ? pdi.getModelFamily() : "");
                row.createCell(5).setCellValue(pdi.getDealerCode() != null ? pdi.getDealerCode() : "");
                row.createCell(6).setCellValue(pdi.getDealerName() != null ? pdi.getDealerName() : "");
                row.createCell(7).setCellValue(pdi.getLocationName() != null ? pdi.getLocationName() : "");
            }
            
            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            // Convert to byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            
            byte[] excelBytes = outputStream.toByteArray();
            
            // Set response headers
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            responseHeaders.setContentDispositionFormData("attachment", 
                "pdi_details_" + System.currentTimeMillis() + ".xlsx");
            
            return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(excelBytes);
                
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * GET /api/services/pdi/details/view
     * Get detailed PDI information for viewing
     * 
     * Query parameters:
     * - vinNo: Vehicle identification number (chassis number)
     * - pdiNo: PDI number
     */
    @GetMapping("/details/view")
    public ResponseEntity<PdiDetailViewDto> getPdiDetailView(
            @RequestParam String vinNo,
            @RequestParam String pdiNo) {
        
        try {
            PdiDetailViewDto dto = pdiService.getPdiDetailView(vinNo, pdiNo);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            System.err.println("Error in getPdiDetailView: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

