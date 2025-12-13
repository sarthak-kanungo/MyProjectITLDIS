package com.i4o.dms.kubota.warranty.logsheet.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.kubota.service.jobcard.domain.LabourJobCharges;
import com.i4o.dms.kubota.warranty.logsheet.domain.WarrantyLogsheetPhotos;
import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;

public interface WarrantyLogsheetViewDto {

	Long getId();

    List<WarrantyLogsheetPhotos> getWarrantyLogsheetPhotosList();

    List<LogsheetFailurePartInfo> getLogsheetFailurePartInfo();
    interface LogsheetFailurePartInfo {
        Long getId();

//        below code is hidden by Suraj
//        SparePartMaster getSparePartMaster();
//        interface SparePartMaster {
//            Long getId();
//            String getItemDescription();
//            String getItemNo();
//        }
        
        String getSparePartMasterId();	//Suraj--20-02-2023
        
        void setSparePartMaster(SparePartMaster sparePartMaster);	//Suraj--20-02-2023
        SparePartMaster getSparePartMaster();	//Suraj--20-02-2023

        Long getQuantity();
        
        String getFailureDescription();	//Suraj--01/11/2022
        
        String getFailureCode();	//Suraj--01/11/2022
        
        Boolean getKeyPartNumber(); 	//Suraj--26/12/2022

    }

    List<LogsheetImplement> getLogsheetImplements();
    interface LogsheetImplement {
        Long getId();

        String getImplementCategory();

        String getImplement();

        String getImplementMake();

        String getGearRation();

        Integer getEngineRpm();

        Double getPercentOfUsage();

        Boolean usageFailure();
    }
    String getLogsheetNo();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getLogsheetDate();
    String getLogsheetType();
    String getStatus();
    String getHours();
    String getCrop();
    String getNoOfTimes();
    Boolean getRepeatFailure();
    String getCropCondition();
    String getFailureType();
    String getSoilType();
    String getFieldCondition();
    String  getProbableCause();
    String getResultOfConfirmation();
    String getTentativeAction();
    String getRemarks();
    
    String getQaRemarks();	//Suraj 11-10-2022
    String getServiceRemarks();	//Suraj 11-10-2022
        

    @JsonProperty("failureDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getDateOfFailure();
    
    MachineVinMaster getMachineInventory();
    
    interface MachineVinMaster{
    	Long getVinId();
        String getChassisNo();
        String getRegistrationNumber();
        String getEngineNo();
    	MachineMaster getMachineMaster();
        interface MachineMaster {
            Long getId();
            String getModel();
        }	
    }
        
    CustomerMaster getCustomerMaster();
    interface  CustomerMaster{
     Long getId();
     String getFirstName();
     String getLastName();
     String getMobileNumber();
     String getAddress1();
    }
    ServiceJobCard getServiceJobCard();
    interface ServiceJobCard {

        List<LabourJobCharges> getLabourCharge();
        Long getId();

        Long getBranchId();
        
        String getJobCardNo();
        String getSoldDealer();

        String getPcrNo();
        void setPcrNo(String pcrNo);
        
        void setServiceDealer(String serviceDealer);
        String getServiceDealer();

        void setServiceDealerCity(String serviceDealerCity);
        String getServiceDealerCity();
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date getInstallationDate();
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date getJobCardDate();

        String getRegistrationNumber();
        
        CustomerMaster getCustomerMaster();
        interface CustomerMaster {
            String getFirstName();

            String getLastName();

            String getId();

            String getAddress1();

            String getMobileNumber();

        }
        MachineInventory getMachineInventory();
        interface MachineInventory {
            Long getVinId();
            String getChassisNo();
            String getRegistrationNumber();
            String getEngineNo();
        	MachineMaster getMachineMaster();
            interface MachineMaster {
                Long getId();
                String getModel();
            }	
        }
        List<SparePartRequisitionItem> getSparePartRequisitionItem();
        interface SparePartRequisitionItem {

            Long getId();

            SparePartMaster getSparePartMaster();
            interface SparePartMaster {
                Long getId();
                String getItemDescription();
                String getItemNo();
            }

            Integer getReqQuantity();

        }
    }


}
