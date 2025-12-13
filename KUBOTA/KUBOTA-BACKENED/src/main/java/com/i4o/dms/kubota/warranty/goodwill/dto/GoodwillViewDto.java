package com.i4o.dms.kubota.warranty.goodwill.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.kubota.warranty.goodwill.domain.WarrantyGoodwillPhoto;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcr;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcrImplements;
import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrViewDto;

import java.util.Date;
import java.util.List;

public interface GoodwillViewDto {

    Long getId();
    Long getBranchId();
    String getGoodwillNo();
    String getStatus();
    String getGoodwillType();
    Date getGoodwillDate();
    String getGoodwillReason();
    String getKaiRemark();
    String getDealerRemark();
    Double getBudgetConsumed();
    String  getWcrNo();
    void setWcrNo(String wcrNo);
    WarrantyPcr getWarrantyPcr();
    List<WarrantyGoodwillPhoto> getWarrantyGoodwillPhoto();

    Boolean getCreateWcr();
    void setCreateWcr(Boolean createWcr);
    
    String getMobileNumber();
    void setMobileNumber(String mobileNumber);
    String getEmailId();
    void setEmailId(String emailId);
    String getDealerCode();
    void setDealerCode(String dealerCode);
    
    interface  WarrantyPcr{

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
        String getSoldToDealer();
        
        String getServiceDealer();
        
        String getServiceDealerAddress();


       ServiceJobCard getServiceJobCard();
        interface  ServiceJobCard{

            Long getId();
            /*WarrantyPcrViewDto.ServiceJobCard.DealerMaster getDealerMaster();
            interface  DealerMaster{
                Long getId();
                @JsonProperty("serviceDealer")
                String getDealerName();
                @JsonProperty("serviceDealerAddress")
                String getShowroomAddress1();
            }*/


            String getJobCardNo();
            String getStatus();
            Double getTotalHour();
            @JsonFormat(pattern = "yyyy-MM-dd")
            Date getJobCardDate();
            String getRegistrationNumber();
            String getCustomerConcern();
            @JsonProperty("dateOfFailure")
            @JsonFormat(pattern = "yyyy-MM-dd")
            Date getTaskDate();
            Boolean getInvoicedFlag();
            String getMechanicObservation();
            WarrantyPcrViewDto.ServiceJobCard.MachineInventory getMachineInventory();
            interface  MachineInventory{
                Long getId();
                String  getChassisNo();
                String getRegistrationNumber();
                @JsonProperty("dateOfInstallation")
                @JsonFormat(pattern = "yyyy-MM-dd")
                Date getInstallationDate();
                String getEngineNo();
                WarrantyPcrViewDto.ServiceJobCard.MachineInventory.MachineMaster getMachineMaster();
                interface MachineMaster{
                    Long getId();
                    String getModel();
                }
               /* WarrantyPcrViewDto.ServiceJobCard.MachineInventory.DealerMaster getDealerMaster();
                interface  DealerMaster{
                    Long getId();
                    @JsonProperty("soldToDealer")
                    String getDealerName();

                }*/
            }

            WarrantyPcrViewDto.ServiceJobCard.CustomerMaster getCustomerMaster();
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

    }

}
