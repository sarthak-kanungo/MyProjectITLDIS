package com.i4o.dms.itldis.service.activityproposal.dto;

import java.util.Date;
import java.util.List;

public interface ServiceActivityProposalViewResponse {

    Long getId();

    Date getCreatedDate();

    String getActivityNumber();

    String getStatus();

    String getLocation();

    Date getFromDate();

    Date getToDate();

    Integer getNoOfDays();

    Double getProposedBudget();

    Double getMaxAllowedBudget();

    Integer getTargetedNumbers();

    Double getApprovedAmount();
    
    String getKaiRemark();
    
    String setKaiRemark(String kaiRemark);

    String getRemarks();
    
    ServiceMtActivityType getServiceMtActivityType();

    interface ServiceMtActivityType {
        Long getId();

        String getActivityType();
    }

    List<ServiceActivityProposalHeads> getServiceActivityProposalHeads();

    interface ServiceActivityProposalHeads {
        Long getId();

        String getHead();

        Double getAmount();
    }

    List<ServiceActivityProposalSubActivity> getServiceActivityProposalSubActivity();

    interface ServiceActivityProposalSubActivity {

        Long getId();

        String getSubActivity();

        Double getAmount();
    }

    DealerMaster getDealerMaster();

    interface DealerMaster {
        Long getId();
        String getDealerName();
    }

    List<ProductMaster> getTargetedProducts();

interface ProductMaster {
    Long getId();

    String getProduct();

}
    Boolean getDraftFlag();

}
