package com.i4o.dms.kubota.warranty.pcr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcrImplements;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcrPhotos;

import java.util.Date;
import java.util.List;

public interface WarrantyPcrViewDto {


    Long getId();
    String getPcrNo();
    String getStatus();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getPcrDate();
    String getCrop();
    String getCropCondition();
    String getFailureType();
    String getSoilType();
    String getFieldCondition();
    @JsonProperty("repeatFailure")
    Boolean getRepeatFailureFlag();
    String getKaiRemarks();
    String getDealerRemarks();
    @JsonProperty("noOfTimes")
    Integer getFailureCount();
    String getDealerObservation();
    String getActionTaken();
    Boolean getGoodwillFlag();
    Boolean getAllowVideoUpload();
    String  getWcrNo();
    void setWcrNo(String wcrNo);
    String getSoldToDealer();
    String getDelayReason();
    String getServiceDealer();
    String getSpecialApvRemarks();
    String getServiceDealerAddress();
    
    Boolean getCreateWcr();
    void setCreateWcr(Boolean createWcr);
    
    ServiceJobCard getServiceJobCard();
    interface  ServiceJobCard{

        Long getId();
        String getJobCardNo();
        String getStatus();
        Double getTotalHour();
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date getJobCardDate();
        String getRegistrationNumber();
        String getCustomerConcern();
        @JsonProperty("dateOfFailure")
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date getDateOfFailure();
        String getMechanicObservation();
        Boolean getInvoicedFlag();
        MachineInventory getMachineInventory();
        interface  MachineInventory{
            Long getVinId();
            String  getChassisNo();
            String getRegistrationNumber();
            @JsonProperty("dateOfInstallation")
            @JsonFormat(pattern = "yyyy-MM-dd")
            Date getDeliveryDate();
            //Date getInstallationDate();
            
            String getEngineNo();
            MachineMaster getMachineMaster();
            interface MachineMaster{
                Long getId();
                String getModel();
            }
        }

        CustomerMaster getCustomerMaster();
        interface  CustomerMaster{
            Long getId();
            @JsonProperty("mobileNo")
            String getMobileNumber();
            @JsonProperty("customerName")
            String getFirstName();
            String getLastName();
            @JsonProperty("address")
            String getAddress1();

        }

    }

    List<WarrantyPcrImplements> getWarrantyPcrImplements();

    List<WarrantyPcrPhotos> getWarrantyPcrPhotos();



}
