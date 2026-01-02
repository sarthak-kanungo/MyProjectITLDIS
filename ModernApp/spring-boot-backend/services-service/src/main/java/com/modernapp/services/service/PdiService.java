package com.modernapp.services.service;

import com.modernapp.services.dto.PendingChassisDto;
import com.modernapp.services.dto.PendingPdiResponse;
import com.modernapp.services.dto.PdiDetailDto;
import com.modernapp.services.dto.PdiDetailResponse;
import com.modernapp.services.dto.PdiDetailViewDto;
import com.modernapp.services.model.VehicleDetails;
import com.modernapp.services.model.DealerVsLocationCode;
import com.modernapp.services.model.PdiDetails;
import com.modernapp.services.repository.VehicleDetailsRepository;
import com.modernapp.services.repository.DealerVsLocationCodeRepository;
import com.modernapp.services.repository.PdiDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class PdiService {

    @Autowired
    private VehicleDetailsRepository vehicleDetailsRepository;
    
    @Autowired
    private DealerVsLocationCodeRepository dealerRepository;
    
    @Autowired
    private PdiDetailsRepository pdiDetailsRepository;

    /**
     * Get pending PDI chassis list with pagination
     * This method queries the Vehicledetails and Dealervslocationcode tables
     * to get vehicles with pdiStatus='N' (not completed)
     */
    public PendingPdiResponse getPendingChassisList(String chassisNo, String dealerCode, 
                                                     int page, int size) {
        try {
            // Build query filters
            List<VehicleDetails> allPendingVehicles;
            
            if (chassisNo != null && !chassisNo.trim().isEmpty() && 
                dealerCode != null && !dealerCode.trim().isEmpty() && !dealerCode.equalsIgnoreCase("ALL")) {
                // Filter by both chassis and dealer
                allPendingVehicles = vehicleDetailsRepository.findByPdiStatus('N')
                    .stream()
                    .filter(v -> v.getVinNo() != null && v.getVinNo().toUpperCase().contains(chassisNo.trim().toUpperCase()))
                    .filter(v -> v.getDealerCode() != null && v.getDealerCode().equals(dealerCode.trim()))
                    .collect(Collectors.toList());
            } else if (chassisNo != null && !chassisNo.trim().isEmpty()) {
                // Filter by chassis only
                allPendingVehicles = vehicleDetailsRepository.findByPdiStatus('N')
                    .stream()
                    .filter(v -> v.getVinNo() != null && v.getVinNo().toUpperCase().contains(chassisNo.trim().toUpperCase()))
                    .collect(Collectors.toList());
            } else if (dealerCode != null && !dealerCode.trim().isEmpty() && !dealerCode.equalsIgnoreCase("ALL")) {
                // Filter by dealer only
                allPendingVehicles = vehicleDetailsRepository.findByDealerCode(dealerCode.trim())
                    .stream()
                    .filter(v -> v.getPdiStatus() != null && v.getPdiStatus().equals('N'))
                    .collect(Collectors.toList());
            } else {
                // No filters
                allPendingVehicles = vehicleDetailsRepository.findByPdiStatus('N');
            }

            // Get all dealers for lookup
            Map<String, DealerVsLocationCode> dealerMap = dealerRepository.findAll()
                .stream()
                .collect(Collectors.toMap(DealerVsLocationCode::getDealerCode, d -> d));

            // Sort by pdiPendingDate
            allPendingVehicles.sort((v1, v2) -> {
                if (v1.getPdiPendingDate() == null && v2.getPdiPendingDate() == null) return 0;
                if (v1.getPdiPendingDate() == null) return 1;
                if (v2.getPdiPendingDate() == null) return -1;
                return v1.getPdiPendingDate().compareTo(v2.getPdiPendingDate());
            });

            // Calculate pagination
            long totalElements = allPendingVehicles.size();
            int totalPages = totalElements > 0 ? (int) Math.ceil((double) totalElements / size) : 0;
            int start = page * size;
            int end = Math.min(start + size, allPendingVehicles.size());

            // Get paginated results (handle empty list case)
            List<VehicleDetails> paginatedVehicles;
            if (start >= allPendingVehicles.size() || start < 0) {
                paginatedVehicles = new ArrayList<>();
            } else {
                paginatedVehicles = allPendingVehicles.subList(start, end);
            }

            // Convert to DTOs
            List<PendingChassisDto> chassisList = new ArrayList<>();
            for (VehicleDetails vehicle : paginatedVehicles) {
                DealerVsLocationCode dealer = dealerMap.get(vehicle.getDealerCode());
                
                String dealerName = dealer != null ? dealer.getDealerName() : "";
                String locationName = dealer != null ? dealer.getLocation() : "";
                String locationCode = dealer != null ? dealer.getLocationCode() : "";

                // Calculate pending days
                Integer pdiPendingDays = null;
                if (vehicle.getPdiPendingDate() != null) {
                    pdiPendingDays = (int) ChronoUnit.DAYS.between(vehicle.getPdiPendingDate(), LocalDate.now());
                }

                PendingChassisDto dto = new PendingChassisDto(
                    vehicle.getVinNo(),
                    vehicle.getModelFamily(),
                    vehicle.getModelCode(),
                    vehicle.getDealerCode(),
                    dealerName,
                    locationName,
                    locationCode,
                    pdiPendingDays,
                    vehicle.getPdiPendingDate()
                );
                chassisList.add(dto);
            }

            return new PendingPdiResponse(chassisList, totalElements, totalPages, page, size);

        } catch (Exception e) {
            System.err.println("Error querying pending PDI chassis: " + e.getMessage());
            e.printStackTrace();
            // Return empty response instead of throwing exception
            PendingPdiResponse emptyResponse = new PendingPdiResponse();
            emptyResponse.setContent(new ArrayList<>());
            emptyResponse.setTotalElements(0);
            emptyResponse.setTotalPages(0);
            emptyResponse.setCurrentPage(page);
            emptyResponse.setSize(size);
            return emptyResponse;
        }
    }

    /**
     * Get all pending chassis for export (no pagination)
     */
    public List<PendingChassisDto> getAllPendingChassisForExport(String chassisNo, String dealerCode) {
        try {
            // Build query filters (same logic as getPendingChassisList but without pagination)
            List<VehicleDetails> allPendingVehicles;
            
            if (chassisNo != null && !chassisNo.trim().isEmpty() && 
                dealerCode != null && !dealerCode.trim().isEmpty() && !dealerCode.equalsIgnoreCase("ALL")) {
                allPendingVehicles = vehicleDetailsRepository.findByPdiStatus('N')
                    .stream()
                    .filter(v -> v.getVinNo() != null && v.getVinNo().toUpperCase().contains(chassisNo.trim().toUpperCase()))
                    .filter(v -> v.getDealerCode() != null && v.getDealerCode().equals(dealerCode.trim()))
                    .collect(Collectors.toList());
            } else if (chassisNo != null && !chassisNo.trim().isEmpty()) {
                allPendingVehicles = vehicleDetailsRepository.findByPdiStatus('N')
                    .stream()
                    .filter(v -> v.getVinNo() != null && v.getVinNo().toUpperCase().contains(chassisNo.trim().toUpperCase()))
                    .collect(Collectors.toList());
            } else if (dealerCode != null && !dealerCode.trim().isEmpty() && !dealerCode.equalsIgnoreCase("ALL")) {
                allPendingVehicles = vehicleDetailsRepository.findByDealerCode(dealerCode.trim())
                    .stream()
                    .filter(v -> v.getPdiStatus() != null && v.getPdiStatus().equals('N'))
                    .collect(Collectors.toList());
            } else {
                allPendingVehicles = vehicleDetailsRepository.findByPdiStatus('N');
            }

            // Get all dealers for lookup
            Map<String, DealerVsLocationCode> dealerMap = dealerRepository.findAll()
                .stream()
                .collect(Collectors.toMap(DealerVsLocationCode::getDealerCode, d -> d));

            // Sort by pdiPendingDate
            allPendingVehicles.sort((v1, v2) -> {
                if (v1.getPdiPendingDate() == null && v2.getPdiPendingDate() == null) return 0;
                if (v1.getPdiPendingDate() == null) return 1;
                if (v2.getPdiPendingDate() == null) return -1;
                return v1.getPdiPendingDate().compareTo(v2.getPdiPendingDate());
            });

            // Convert to DTOs
            List<PendingChassisDto> chassisList = new ArrayList<>();
            for (VehicleDetails vehicle : allPendingVehicles) {
                DealerVsLocationCode dealer = dealerMap.get(vehicle.getDealerCode());
                
                String dealerName = dealer != null ? dealer.getDealerName() : "";
                String locationName = dealer != null ? dealer.getLocation() : "";
                String locationCode = dealer != null ? dealer.getLocationCode() : "";

                Integer pdiPendingDays = null;
                if (vehicle.getPdiPendingDate() != null) {
                    pdiPendingDays = (int) ChronoUnit.DAYS.between(vehicle.getPdiPendingDate(), LocalDate.now());
                }

                PendingChassisDto dto = new PendingChassisDto(
                    vehicle.getVinNo(),
                    vehicle.getModelFamily(),
                    vehicle.getModelCode(),
                    vehicle.getDealerCode(),
                    dealerName,
                    locationName,
                    locationCode,
                    pdiPendingDays,
                    vehicle.getPdiPendingDate()
                );
                chassisList.add(dto);
            }

            return chassisList;

        } catch (Exception e) {
            System.err.println("Error querying pending PDI chassis for export: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Get PDI details list with pagination
     * This method queries completed PDI records (pdiStatus='Y')
     * Joins PdiDetails, Vehicledetails, and Dealervslocationcode tables
     */
    public PdiDetailResponse getPdiDetailsList(String chassisNo, String dealerCode, 
                                                String fromDate, String toDate, 
                                                String status, Boolean useDateRange,
                                                int page, int size) {
        try {
            // Get all completed PDI records (vehicles with pdiStatus='Y')
            List<VehicleDetails> completedVehicles = vehicleDetailsRepository.findAll()
                .stream()
                .filter(v -> v.getPdiStatus() != null && v.getPdiStatus().equals('Y'))
                .collect(Collectors.toList());

            // Get all PDI details
            List<PdiDetails> allPdiDetails = pdiDetailsRepository.findAll();
            
            // Get all dealers for lookup
            Map<String, DealerVsLocationCode> dealerMap = dealerRepository.findAll()
                .stream()
                .collect(Collectors.toMap(DealerVsLocationCode::getDealerCode, d -> d));

            // Build result list by joining data
            List<PdiDetailDto> allPdiDetailList = new ArrayList<>();
            for (PdiDetails pdi : allPdiDetails) {
                // Find matching vehicle
                VehicleDetails vehicle = completedVehicles.stream()
                    .filter(v -> v.getVinNo() != null && v.getVinNo().equals(pdi.getVinNo()))
                    .filter(v -> v.getDealerCode() != null && v.getDealerCode().equals(pdi.getDealercode()))
                    .findFirst()
                    .orElse(null);

                if (vehicle == null) continue;

                DealerVsLocationCode dealer = dealerMap.get(pdi.getDealercode());
                
                String dealerName = dealer != null ? dealer.getDealerName() : "";
                String locationName = dealer != null ? dealer.getLocation() : "";
                String locationCode = dealer != null ? dealer.getLocationCode() : "";

                // Format PDI date
                String pdiDateStr = "";
                if (pdi.getPdiDate() != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    pdiDateStr = pdi.getPdiDate().format(formatter);
                }

                PdiDetailDto dto = new PdiDetailDto(
                    pdi.getVinNo(),
                    pdi.getPdiNo(),
                    pdiDateStr,
                    vehicle.getModelFamily(),
                    vehicle.getModelCode(),
                    pdi.getDealercode(),
                    dealerName,
                    locationName,
                    locationCode,
                    pdi.getRefPDINo()
                );
                allPdiDetailList.add(dto);
            }

            // Apply filters
            List<PdiDetailDto> filteredList = allPdiDetailList.stream()
                .filter(dto -> {
                    // Filter by chassis number
                    if (chassisNo != null && !chassisNo.trim().isEmpty()) {
                        if (dto.getVinNo() == null || 
                            !dto.getVinNo().toUpperCase().contains(chassisNo.trim().toUpperCase())) {
                            return false;
                        }
                    }
                    
                    // Filter by dealer code
                    if (dealerCode != null && !dealerCode.trim().isEmpty() && !dealerCode.equalsIgnoreCase("ALL")) {
                        if (!dealerCode.trim().equals(dto.getDealerCode())) {
                            return false;
                        }
                    }
                    
                    // Filter by date range
                    if (useDateRange != null && useDateRange && fromDate != null && toDate != null) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate pdiDate = dto.getPdiDate() != null && !dto.getPdiDate().isEmpty() ?
                                LocalDate.parse(dto.getPdiDate(), formatter) : null;
                            LocalDate from = LocalDate.parse(fromDate, formatter);
                            LocalDate to = LocalDate.parse(toDate, formatter);
                            
                            if (pdiDate == null) {
                                return false;
                            }
                            // Include dates that are on or between from and to
                            if (pdiDate.isBefore(from) || pdiDate.isAfter(to)) {
                                return false;
                            }
                        } catch (Exception e) {
                            // If date parsing fails, skip date filter
                            System.err.println("Error parsing date in filter: " + e.getMessage());
                        }
                    }
                    
                    // Note: Status filter (OK, Atleast One NotOk) requires PdiChecklist table
                    // For now, we'll skip status filtering
                    
                    return true;
                })
                .collect(Collectors.toList());

            // Sort by PDI date descending
            filteredList.sort((d1, d2) -> {
                try {
                    if (d1.getPdiDate() == null || d1.getPdiDate().isEmpty()) return 1;
                    if (d2.getPdiDate() == null || d2.getPdiDate().isEmpty()) return -1;
                    LocalDate date1 = LocalDate.parse(d1.getPdiDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalDate date2 = LocalDate.parse(d2.getPdiDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    return date2.compareTo(date1); // Descending order
                } catch (Exception e) {
                    return 0;
                }
            });

            // Calculate pagination
            long totalElements = filteredList.size();
            int totalPages = totalElements > 0 ? (int) Math.ceil((double) totalElements / size) : 0;
            int start = page * size;
            int end = Math.min(start + size, filteredList.size());

            // Get paginated results
            List<PdiDetailDto> paginatedList;
            if (start >= filteredList.size() || start < 0) {
                paginatedList = new ArrayList<>();
            } else {
                paginatedList = filteredList.subList(start, end);
            }

            PdiDetailResponse response = new PdiDetailResponse();
            response.setContent(paginatedList);
            response.setTotalElements(totalElements);
            response.setTotalPages(totalPages);
            response.setCurrentPage(page);
            response.setSize(size);
            return response;

        } catch (Exception e) {
            System.err.println("Error querying PDI details: " + e.getMessage());
            e.printStackTrace();
            PdiDetailResponse errorResponse = new PdiDetailResponse();
            errorResponse.setContent(new ArrayList<>());
            errorResponse.setTotalElements(0);
            errorResponse.setTotalPages(0);
            errorResponse.setCurrentPage(page);
            errorResponse.setSize(size);
            return errorResponse;
        }
    }

    /**
     * Get all PDI details for export (no pagination)
     */
    public List<PdiDetailDto> getAllPdiDetailsForExport(String chassisNo, String dealerCode,
                                                         String fromDate, String toDate,
                                                         String status, Boolean useDateRange) {
        try {
            // Use the same logic as getPdiDetailsList but without pagination
            // Get all completed PDI records
            List<VehicleDetails> completedVehicles = vehicleDetailsRepository.findAll()
                .stream()
                .filter(v -> v.getPdiStatus() != null && v.getPdiStatus().equals('Y'))
                .collect(Collectors.toList());

            List<PdiDetails> allPdiDetails = pdiDetailsRepository.findAll();
            
            Map<String, DealerVsLocationCode> dealerMap = dealerRepository.findAll()
                .stream()
                .collect(Collectors.toMap(DealerVsLocationCode::getDealerCode, d -> d));

            List<PdiDetailDto> allPdiDetailList = new ArrayList<>();
            for (PdiDetails pdi : allPdiDetails) {
                VehicleDetails vehicle = completedVehicles.stream()
                    .filter(v -> v.getVinNo() != null && v.getVinNo().equals(pdi.getVinNo()))
                    .filter(v -> v.getDealerCode() != null && v.getDealerCode().equals(pdi.getDealercode()))
                    .findFirst()
                    .orElse(null);

                if (vehicle == null) continue;

                DealerVsLocationCode dealer = dealerMap.get(pdi.getDealercode());
                
                String dealerName = dealer != null ? dealer.getDealerName() : "";
                String locationName = dealer != null ? dealer.getLocation() : "";
                String locationCode = dealer != null ? dealer.getLocationCode() : "";

                String pdiDateStr = "";
                if (pdi.getPdiDate() != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    pdiDateStr = pdi.getPdiDate().format(formatter);
                }

                PdiDetailDto dto = new PdiDetailDto(
                    pdi.getVinNo(),
                    pdi.getPdiNo(),
                    pdiDateStr,
                    vehicle.getModelFamily(),
                    vehicle.getModelCode(),
                    pdi.getDealercode(),
                    dealerName,
                    locationName,
                    locationCode,
                    pdi.getRefPDINo()
                );
                allPdiDetailList.add(dto);
            }

            // Apply filters (same as getPdiDetailsList)
            List<PdiDetailDto> filteredList = allPdiDetailList.stream()
                .filter(dto -> {
                    if (chassisNo != null && !chassisNo.trim().isEmpty()) {
                        if (dto.getVinNo() == null || 
                            !dto.getVinNo().toUpperCase().contains(chassisNo.trim().toUpperCase())) {
                            return false;
                        }
                    }
                    
                    if (dealerCode != null && !dealerCode.trim().isEmpty() && !dealerCode.equalsIgnoreCase("ALL")) {
                        if (!dealerCode.trim().equals(dto.getDealerCode())) {
                            return false;
                        }
                    }
                    
                    if (useDateRange != null && useDateRange && fromDate != null && toDate != null) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate pdiDate = dto.getPdiDate() != null && !dto.getPdiDate().isEmpty() ?
                                LocalDate.parse(dto.getPdiDate(), formatter) : null;
                            LocalDate from = LocalDate.parse(fromDate, formatter);
                            LocalDate to = LocalDate.parse(toDate, formatter);
                            
                            if (pdiDate == null) {
                                return false;
                            }
                            // Include dates that are on or between from and to
                            if (pdiDate.isBefore(from) || pdiDate.isAfter(to)) {
                                return false;
                            }
                        } catch (Exception e) {
                            // Skip date filter on error
                            System.err.println("Error parsing date in export filter: " + e.getMessage());
                        }
                    }
                    
                    return true;
                })
                .collect(Collectors.toList());

            // Sort by PDI date descending
            filteredList.sort((d1, d2) -> {
                try {
                    if (d1.getPdiDate() == null || d1.getPdiDate().isEmpty()) return 1;
                    if (d2.getPdiDate() == null || d2.getPdiDate().isEmpty()) return -1;
                    LocalDate date1 = LocalDate.parse(d1.getPdiDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalDate date2 = LocalDate.parse(d2.getPdiDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    return date2.compareTo(date1);
                } catch (Exception e) {
                    return 0;
                }
            });

            return filteredList;
            
        } catch (Exception e) {
            System.err.println("Error querying PDI details for export: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Get single PDI detail data for viewing
     * This method retrieves complete PDI information including vehicle details
     */
    public PdiDetailViewDto getPdiDetailView(String vinNo, String pdiNo) {
        try {
            // Find the PDI details record
            List<PdiDetails> pdiList = pdiDetailsRepository.findAll()
                .stream()
                .filter(p -> p.getPdiNo() != null && p.getPdiNo().equals(pdiNo))
                .filter(p -> p.getVinNo() != null && p.getVinNo().equals(vinNo))
                .collect(Collectors.toList());

            if (pdiList.isEmpty()) {
                return null;
            }

            PdiDetails pdi = pdiList.get(0);

            // Find the vehicle
            List<VehicleDetails> vehicles = vehicleDetailsRepository.findAll()
                .stream()
                .filter(v -> v.getVinNo() != null && v.getVinNo().equals(vinNo))
                .filter(v -> v.getDealerCode() != null && v.getDealerCode().equals(pdi.getDealercode()))
                .filter(v -> v.getPdiStatus() != null && v.getPdiStatus().equals('Y'))
                .collect(Collectors.toList());

            if (vehicles.isEmpty()) {
                return null;
            }

            VehicleDetails vehicle = vehicles.get(0);

            // Find the dealer
            DealerVsLocationCode dealer = dealerRepository.findByDealerCode(pdi.getDealercode()).orElse(null);

            // Build the DTO
            PdiDetailViewDto dto = new PdiDetailViewDto();
            dto.setVinNo(vehicle.getVinNo());
            dto.setEngineNo(vehicle.getEngineNo() != null ? vehicle.getEngineNo() : pdi.getEngineNo());
            dto.setModelFamily(vehicle.getModelFamily());
            dto.setModelCode(vehicle.getModelCode());
            dto.setModelFamilyDesc(""); // Not available in current schema
            dto.setModelCodeDesc(""); // Not available in current schema
            dto.setPdiNo(pdi.getPdiNo());
            
            // Format dates
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            if (pdi.getInvoiceDate() != null) {
                dto.setInvoiceDate(pdi.getInvoiceDate().format(formatter));
            } else {
                dto.setInvoiceDate("-");
            }
            
            if (pdi.getPdiDate() != null) {
                dto.setPdiDate(pdi.getPdiDate().format(formatter));
            } else {
                dto.setPdiDate("-");
            }
            
            dto.setInvoiceNo(pdi.getItlInvoiceNo() != null ? pdi.getItlInvoiceNo() : "-");
            dto.setDealerName(dealer != null ? dealer.getDealerName() : "");
            dto.setDealerCode(pdi.getDealercode());
            dto.setLocationName(dealer != null ? dealer.getLocation() : "");
            dto.setLocationCode(dealer != null ? dealer.getLocationCode() : "");
            dto.setRemarks(pdi.getRemarks() != null ? pdi.getRemarks() : "-");
            dto.setPdiDoneBy(pdi.getCreatedBy() != null ? pdi.getCreatedBy() : "-");
            dto.setTractorReceivedDate("-"); // Not available in current schema
            dto.setJobCardNo(""); // Not available in current schema
            dto.setCreateJobCard(true); // Enabled for demo workflow
            dto.setJobCardNo("JC-" + dto.getVinNo().substring(Math.max(0, dto.getVinNo().length() - 5)));

            // Mock Data for Travel Card Parts (Replica of legacy functionality capability)
            List<com.modernapp.services.dto.TravelCardPartDto> parts = new ArrayList<>();
            parts.add(new com.modernapp.services.dto.TravelCardPartDto(1, "Battery", "Exide", "BAT-8822"));
            parts.add(new com.modernapp.services.dto.TravelCardPartDto(2, "Starter Motor", "Bosch", "SM-1102"));
            parts.add(new com.modernapp.services.dto.TravelCardPartDto(3, "Alternator", "Lucas", "ALT-5541"));
            dto.setTravelCardParts(parts);

            // Mock Data for Checklist (Replica of legacy functionality capability)
            List<com.modernapp.services.dto.ChecklistGroupDto> checklist = new ArrayList<>();
            
            List<com.modernapp.services.dto.ChecklistItemDto> electricalItems = new ArrayList<>();
            electricalItems.add(new com.modernapp.services.dto.ChecklistItemDto("101", "Check Battery Condition", "OK", "", "Voltage 12.5V"));
            electricalItems.add(new com.modernapp.services.dto.ChecklistItemDto("102", "Check Lights & Indicators", "OK", "", "All working"));
            checklist.add(new com.modernapp.services.dto.ChecklistGroupDto("100", "Electrical System", electricalItems));

            List<com.modernapp.services.dto.ChecklistItemDto> engineItems = new ArrayList<>();
            engineItems.add(new com.modernapp.services.dto.ChecklistItemDto("201", "Check Oil Level", "OK", "", "Level Correct"));
            engineItems.add(new com.modernapp.services.dto.ChecklistItemDto("202", "Check Coolant Level", "OK", "", "Top up done"));
             checklist.add(new com.modernapp.services.dto.ChecklistGroupDto("200", "Engine System", engineItems));

            dto.setChecklist(checklist);

            return dto;

        } catch (Exception e) {
            System.err.println("Error getting PDI detail view: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

