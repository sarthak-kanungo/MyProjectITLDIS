package com.i4o.dms.kubota.salesandpresales.enquiry.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.exception.DataException;

import java.util.Date;


public interface PendingValidationDto {

   public String getEnquiryNumber();

   public  String getEnquiryDate();
    public String  getNextFollowUpDate();
   public   String getModel();
   public String getRemarks();
   public String getFirstName();
   public String getMobileNumber();

}
