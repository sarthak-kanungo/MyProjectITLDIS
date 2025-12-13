package com.i4o.dms.kubota.salesandpresales.purchase.machinedescripancycomplaint.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface MachineDescripancyComplaintViewDto {
    Long getComplaintId();

    String getComplaintNumber();

    String getcomplaintStatus();

    Boolean getdraftMode();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getcomplaintDate();

    List<MachineDescripancyComplaintDetail> getMachineDescripancyComplaintDetails();

    interface MachineDescripancyComplaintDetail {

        Long getId();

        String getItemNo();

        String getItemDescription();

        String getRemarks();

        String getType();

        Integer getApprovedQuantity();

        Integer getQuantity();

        MachineDescripancyComplaint getMachineDescripancyComplaint();

        interface MachineDescripancyComplaint {
            Long getComplaintId();

        }

    }

    GrnMachineDetails getGrnMachineDetails();

    interface GrnMachineDetails {
        Long getId();

        String getChassisNo();

        String getEngineNo();

        MachineGrn getMachineGrn();
        interface MachineGrn{
            String getDmsGrnNumber();
            @JsonFormat(pattern = "yyyy-MM-dd")
            Date getGrnDate();
            String getTransporterVehicleNumber();
            AccPacInvoice getAccPacInvoice();
            interface AccPacInvoice{
                @JsonFormat(pattern = "dd-MM-yyyy")
                Date getInvoiceDate();
                String getLrNo();
            }
            Transporter getTransporter();
            interface Transporter{
                String getTransporterName();
            }
        }
    }

    DealerEmployeeMaster getDealerEmployeeMaster();

    interface DealerEmployeeMaster {
        Long getId();

    }

    List<Map<String, Object>> getApprovalDetails();
    
    void setApprovalDetails(List<Map<String, Object>> approvalDetails);
}
