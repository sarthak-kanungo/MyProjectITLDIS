package com.modernapp.services.dto;

import java.util.List;

public class ChecklistGroupDto {
    private String contentId;
    private String contentDesc;
    private List<ChecklistItemDto> items;
    private String status;
    private String actionTaken;
    private String observations;

    public ChecklistGroupDto() {}

    public ChecklistGroupDto(String contentId, String contentDesc, List<ChecklistItemDto> items) {
        this.contentId = contentId;
        this.contentDesc = contentDesc;
        this.items = items;
    }

    public String getContentId() { return contentId; }
    public void setContentId(String contentId) { this.contentId = contentId; }

    public String getContentDesc() { return contentDesc; }
    public void setContentDesc(String contentDesc) { this.contentDesc = contentDesc; }

    public List<ChecklistItemDto> getItems() { return items; }
    public void setItems(List<ChecklistItemDto> items) { this.items = items; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getActionTaken() { return actionTaken; }
    public void setActionTaken(String actionTaken) { this.actionTaken = actionTaken; }

    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
}
