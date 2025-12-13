package com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcrImplements;

import java.util.Date;
import java.util.List;

public interface PcrWarrantyClaimDto {


    List<WarrantyPcrImplements> getWarrantyPcrImplements();

    Long getId();
    String getPcrNo();
    Long getBranchId();
    String getStatus();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getPcrDate();
    String getFailureType();
    @JsonProperty("repeatFailure")
    Boolean getRepeatFailureFlag();
    String getKaiRemarks();
    String getDealerRemarks();
    @JsonProperty("noOfTimes")
    Integer getFailureCount();
    String getDealerObservation();
    String getActionTaken();

    
    
    String getServiceDealer();
    
    String getServiceDealerAddress();
    
    String getSoldToDealer();
    
    String getMobileNumber();
    void setMobileNumber(String mobileNumber);
    String getEmailId();
    void setEmailId(String emailId);
    String getDealerCode();
    void setDealerCode(String dealerCode);
   /* DealerMaster getDealerMaster();
    interface  DealerMaster{
        Long getId();
        @JsonProperty("serviceDealer")
        String getDealerName();
        @JsonProperty("serviceDealerAddress")
        String getShowroomAddress1();
        @JsonProperty("dealerCode")
        String getDealerCode();
        @JsonProperty("mobileNumber")
        String getMobileNo();
        @JsonProperty("emailId")
        String getEmailId();
    }*/

  ServiceJobCard getServiceJobCard();
    interface  ServiceJobCard{
        Long getId();
        String getJobCardNo();
        Double getTotalHour();
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date getJobCardDate();
        @JsonProperty("dateOfRepair")
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date getClosedDate();
        String getCustomerConcern();
        Boolean getInvoicedFlag();
        @JsonProperty("dateOfFailure")
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date getTaskDate();

        MachineInventory getMachineInventory();
        interface  MachineInventory{
            Long getVinId();
            String  getChassisNo();
            String getEngineNo();
            @JsonFormat(pattern = "yyyy-MM-dd")
            Date getInstallationDate();
            MachineMaster getMachineMaster();
            interface MachineMaster{
                Long getId();
                String getModel();
            }
          /*DealerMaster getDealerMaster();
            interface  DealerMaster{
                Long getId();
                @JsonProperty("soldToDealer")
                String getDealerName();

            }*/
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

}
