package com.modernapp.services.config;

import com.modernapp.services.model.DealerVsLocationCode;
import com.modernapp.services.model.VehicleDetails;
import com.modernapp.services.model.PdiDetails;
import com.modernapp.services.repository.DealerVsLocationCodeRepository;
import com.modernapp.services.repository.VehicleDetailsRepository;
import com.modernapp.services.repository.PdiDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private DealerVsLocationCodeRepository dealerRepository;

    @Autowired
    private VehicleDetailsRepository vehicleRepository;
    
    @Autowired
    private PdiDetailsRepository pdiDetailsRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        // Only initialize if data doesn't exist
        if (dealerRepository.count() == 0) {
            System.out.println("Initializing dealer data...");
            initializeDealers();
        }
        if (vehicleRepository.count() == 0) {
            System.out.println("Initializing vehicle data...");
            initializeVehicles();
        }
        if (pdiDetailsRepository.count() == 0) {
            System.out.println("Initializing PDI details data...");
            initializePdiDetails();
        }
        System.out.println("Data initialization complete. Dealers: " + dealerRepository.count() + 
                          ", Vehicles: " + vehicleRepository.count() + 
                          ", PDI Details: " + pdiDetailsRepository.count());
    }

    private void initializeDealers() {
        dealerRepository.save(new DealerVsLocationCode("DLR001", "LOC001", "ABC Motors", "Mumbai"));
        dealerRepository.save(new DealerVsLocationCode("DLR002", "LOC002", "XYZ Automobiles", "Delhi"));
        dealerRepository.save(new DealerVsLocationCode("DLR003", "LOC003", "PQR Vehicles", "Bangalore"));
        dealerRepository.save(new DealerVsLocationCode("DLR004", "LOC004", "DEF Motors", "Chennai"));
        dealerRepository.save(new DealerVsLocationCode("DLR005", "LOC005", "GHI Automobiles", "Pune"));
    }

    private void initializeVehicles() {
        // Create vehicles with pending PDI status
        createVehicle("VIN001", "DLR001", "CHASSIS001", "Tractor", "MODEL001", LocalDate.now().minusDays(5));
        createVehicle("VIN002", "DLR001", "CHASSIS002", "Tractor", "MODEL002", LocalDate.now().minusDays(10));
        createVehicle("VIN003", "DLR002", "CHASSIS003", "Harvester", "MODEL003", LocalDate.now().minusDays(15));
        createVehicle("VIN004", "DLR002", "CHASSIS004", "Tractor", "MODEL001", LocalDate.now().minusDays(20));
        createVehicle("VIN005", "DLR003", "CHASSIS005", "Harvester", "MODEL004", LocalDate.now().minusDays(25));
        createVehicle("VIN006", "DLR003", "CHASSIS006", "Tractor", "MODEL002", LocalDate.now().minusDays(30));
        createVehicle("VIN007", "DLR004", "CHASSIS007", "Tractor", "MODEL001", LocalDate.now().minusDays(35));
        createVehicle("VIN008", "DLR004", "CHASSIS008", "Harvester", "MODEL003", LocalDate.now().minusDays(40));
        createVehicle("VIN009", "DLR005", "CHASSIS009", "Tractor", "MODEL002", LocalDate.now().minusDays(45));
        createVehicle("VIN010", "DLR005", "CHASSIS010", "Harvester", "MODEL004", LocalDate.now().minusDays(50));
        createVehicle("VIN011", "DLR001", "CHASSIS011", "Tractor", "MODEL001", LocalDate.now().minusDays(3));
        createVehicle("VIN012", "DLR002", "CHASSIS012", "Tractor", "MODEL002", LocalDate.now().minusDays(7));
        createVehicle("VIN013", "DLR003", "CHASSIS013", "Harvester", "MODEL003", LocalDate.now().minusDays(12));
        createVehicle("VIN014", "DLR004", "CHASSIS014", "Tractor", "MODEL001", LocalDate.now().minusDays(18));
        createVehicle("VIN015", "DLR005", "CHASSIS015", "Harvester", "MODEL004", LocalDate.now().minusDays(22));
    }

    private void createVehicle(String vinid, String dealerCode, String vinNo, 
                              String modelFamily, String modelCode, LocalDate pdiPendingDate) {
        VehicleDetails vehicle = new VehicleDetails();
        vehicle.setVinid(vinid);
        vehicle.setDealerCode(dealerCode);
        vehicle.setVinNo(vinNo);
        vehicle.setModelFamily(modelFamily);
        vehicle.setModelCode(modelCode);
        vehicle.setPdiStatus('N');
        vehicle.setPdiPendingDate(pdiPendingDate);
        vehicle.setCustomerName("Customer " + vinid);
        vehicle.setEngineNo("ENG" + vinid.substring(3));
        vehicle.setRegNo("REG" + vinid.substring(3));
        vehicleRepository.save(vehicle);
    }

    private void initializePdiDetails() {
        // Create 50 vehicles with completed PDI status (pdiStatus='Y') for testing View PDI
        String[] modelFamilies = {"TRACTOR", "HARVESTER", "IMPLEMENT", "ROTAVATOR"};
        String[] modelCodes = {"MODEL001", "MODEL002", "MODEL003", "MODEL004"};
        String[] dealers = {"DLR001", "DLR002", "DLR003", "DLR004", "DLR005"};

        for (int i = 1; i <= 50; i++) {
            String vinid = String.format("VIN%03d", 100 + i);
            String binNo = String.format("CHASSIS%03d", 100 + i);
            String dealerCode = dealers[(i - 1) % dealers.length];
            String modelFamily = modelFamilies[(i - 1) % modelFamilies.length];
            String modelCode = modelCodes[(i - 1) % modelCodes.length];
            
            // Varied dates
            LocalDate pdiDate = LocalDate.now().minusDays(i % 365); 
            
            // Special case for INS records (e.g., every 5th record)
            boolean isInstallation = (i % 5 == 0);
            
            createCompletedPdiVehicle(vinid, dealerCode, binNo, modelFamily, modelCode, pdiDate, isInstallation);
        }
    }

    private void createCompletedPdiVehicle(String vinid, String dealerCode, String vinNo, 
                                           String modelFamily, String modelCode, LocalDate pdiDate, boolean isInstallation) {
        // Create vehicle with completed PDI status
        VehicleDetails vehicle = new VehicleDetails();
        vehicle.setVinid(vinid);
        vehicle.setDealerCode(dealerCode);
        vehicle.setVinNo(vinNo);
        vehicle.setModelFamily(modelFamily);
        vehicle.setModelCode(modelCode);
        vehicle.setPdiStatus('Y'); // Completed PDI
        vehicle.setPdiPendingDate(null);
        vehicle.setCustomerName("Customer " + vinid);
        vehicle.setEngineNo("ENG" + vinid.substring(3));
        vehicle.setRegNo("REG" + vinid.substring(3));
        vehicleRepository.save(vehicle);

        // Create corresponding PDI Details record
        PdiDetails pdiDetails = new PdiDetails();
        // Use INS prefix if isInstallation is true
        String prefix = isInstallation ? "INS" : "PDI";
        pdiDetails.setPdiNo(prefix + "/2025/" + (100 + Integer.parseInt(vinid.substring(3))));
        
        pdiDetails.setDealercode(dealerCode);
        pdiDetails.setVinNo(vinNo);
        pdiDetails.setPdiDate(pdiDate);
        pdiDetails.setRefPDINo(""); // Empty for regular PDI
        pdiDetails.setEngineNo("ENG" + vinid.substring(3));
        pdiDetails.setItlInvoiceNo("INV" + vinid.substring(3));
        pdiDetails.setInvoiceDate(pdiDate.minusDays(1));
        pdiDetails.setCreatedBy("SYSTEM");
        pdiDetails.setCreatedOn(java.time.LocalDateTime.now());
        pdiDetailsRepository.save(pdiDetails);
    }
}

