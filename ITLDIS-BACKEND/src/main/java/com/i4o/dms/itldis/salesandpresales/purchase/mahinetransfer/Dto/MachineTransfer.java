package com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;

import java.util.Date;
import java.util.List;


public interface MachineTransfer {
    Long getId();
    String getRequestNumber();
    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getRequestDate();
    String getStatus();
    DealerMaster getCoDealer();
    DealerMaster getDealerMaster();
    interface  DealerMaster{
        String getDealerCode();
        String getDealerName();
        String getGstNo();
    }

    List<DealerMachineTransferDetail> getDealerMachineTransferDetails();


    interface  DealerMachineTransferDetail {
        MachineMaster getMachineMaster();
        Integer getQuantity();

        interface  MachineMaster{
            String getItemNo();
            String getProduct();
            String getModel();
            String getVariant();
            String getSeries();
            String getItemDescription();
        }
    }
    Integer getTotalQty();
}
