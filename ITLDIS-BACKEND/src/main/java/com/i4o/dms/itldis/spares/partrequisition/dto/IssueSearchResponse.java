package com.i4o.dms.itldis.spares.partrequisition.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","partIssueNo","requisitionPurpose","requestedBy","requisitionNo","requisitionDate","jobCardNo",
        "jobCardDate","issueDate","partsIssuedBy","issueTo"})
public interface IssueSearchResponse {
    Long getId();
    String getPartIssueNo();
    String getRequisitionPurpose();
    String getRequestedBy();
    String getRequisitionNo();
    String getRequisitionDate();
    String getJobCardNo();
    String getJobCardDate();
    String getIssueDate();
    String getPartsIssuedBy();
    String getIssueTo();
    @JsonIgnore
    Long getTotalCount();
}
