package com.i4o.dms.kubota.service.servicebooking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface ServiceBookingViewDto {

     Long getId();
     String getBookingNo();
     @JsonFormat(pattern = "dd-MM-yyyy")
    String getBookingDate();
    String getStatus();
    Boolean getDraftFlag();
    String getMobileNumber();
    String getPlaceOfService();
    Boolean getCancelBookingFlag();
    String getReasonForCancellation();
    String getChassisNo();
    @JsonProperty("name")
    String getMechanicName();
    String getCustomerName();
    Long getMachineInventoryId();
    Long getMechanicId();
    Long getCustomerId();
    Long getSourceOfBookingId();
    Long getPlaceOfServiceId();
    Long getServiceTypeId();
    String getServiceType();
    String getModel();
    Long getModelId();
    String getEngineNo();
    String getDateOfInstallation();
    Double getHours();
    String getSourceOfBooking();
    @JsonProperty("category")
    String getServiceCategory();
    @JsonFormat(pattern = "dd-MM-yyyy")
    String getAppointmentDate();
    String getAppointmentTime();
    Double getCurrentHour();
    Double getTotalHour();
    Double getPreviousHour();
    Long getServiceCategoryId();
    String getRemarks();
    String getAddress();
    String getPreviousServiceType();
    @JsonFormat(pattern = "dd-MM-yyy")
    String getPreviousServiceDate();
    Double getPreviousServiceHour();
    String getNextDueServiceType();
    Long getActivityTypeId();
    Long getActivityNoId();
    String getActivityType();
    String getActivityNumber();


}
