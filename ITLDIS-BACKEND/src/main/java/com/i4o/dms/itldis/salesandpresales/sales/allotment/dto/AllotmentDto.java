package com.i4o.dms.itldis.salesandpresales.sales.allotment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.itldis.salesandpresales.sales.allotment.domain.MachineAllotmentDetail;

import java.util.Date;
import java.util.List;

public interface AllotmentDto {
    String getAllotmentNumber();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getCreatedDate();

    Enquiry getEnquiry();

    interface Enquiry {
        String getEnquiryNumber();

        String getFirstName();

        String getMobileNumber();

        String getAddress1();

        String getPinCode();

        String getPostOffice();

        String getCity();

        String getTehsil();

        String getModel();

        String getItemNo();

        String getItemDescription();
    }

    MachineInventory getMachineInventory();

    interface MachineInventory {
        String getChassisNo();

        String getEngineNo();
        String getColor();

        MachineGrn getMachineGrn();
        interface MachineGrn {
            String getGrnNumber();

            AccPacInvoice getAccPacInvoice();

            interface AccPacInvoice {
                String getInvoiceNumber();

                @JsonFormat(pattern = "dd-MM-yyyy")
                Date getInvoiceDate();
            }

        }
    }

    List<MachineAllotmentDetail> getAllotmentImplement();
    interface AllotmentImplements {
        AllotmentDto.MachineInventory getMachineInventory();

        interface MachineInventory {
            String getChassisNo();

            String getEngineNo();
            String getColor();

            AllotmentDto.MachineInventory.MachineGrn getMachineGrn();
            interface MachineGrn {
                String getGrnNumber();

                AllotmentDto.MachineInventory.MachineGrn.AccPacInvoice getAccPacInvoice();

                interface AccPacInvoice {
                    String getInvoiceNumber();

                    @JsonFormat(pattern = "dd-MM-yyyy")
                    Date getInvoiceDate();
                }

            }
        }

    }
}



















