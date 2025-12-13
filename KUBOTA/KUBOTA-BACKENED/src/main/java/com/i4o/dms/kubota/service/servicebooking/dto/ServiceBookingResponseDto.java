package com.i4o.dms.kubota.service.servicebooking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"edit","id","bookingNo","bookingDate","status","reasonForCancellation","chassisNo","mechanicName","customerName","engineNo","model","dateOfInstallation","hours","sourceOfBooking","serviceCategory","placeOfService","serviceType","appointmentDate","appointmentTime","remarks","address","mobileNo"})
@JsonIgnoreProperties({"recordCount"})
public interface ServiceBookingResponseDto {
    Long getId();
    String getBookingNo();
    String getBookingDate();
    String getStatus();
    String getReasonForCancellation();
    String getChassisNo();
    String getEdit();
    String getMechanicName();
    String getCustomerName();
    String getEngineNo();
    String getModel();
    @JsonFormat(pattern = "dd-MM-yyyy")
    String getDateOfInstallation();
    String getHours();
    String getSourceOfBooking();
    String getServiceCategory();
    String getPlaceOfService();
    String getServiceType();
    @JsonFormat(pattern = "dd-MM-yyyy")
    String getAppointmentDate();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    String getAppointmentTime();
    String getRemarks();
    String getAddress();
    String getMobileNo();
    Long getRecordCount();




}
