package com.modernapp.services.dto;

public class ChecklistItemDto {
    private String subContentId;
    private String subContentDesc;
    private String textBoxOption;
    private String actionTaken;
    private String observations;

    public ChecklistItemDto() {}

    public ChecklistItemDto(String subContentId, String subContentDesc, String textBoxOption, String actionTaken, String observations) {
        this.subContentId = subContentId;
        this.subContentDesc = subContentDesc;
        this.textBoxOption = textBoxOption;
        this.actionTaken = actionTaken;
        this.observations = observations;
    }

    public String getSubContentId() { return subContentId; }
    public void setSubContentId(String subContentId) { this.subContentId = subContentId; }

    public String getSubContentDesc() { return subContentDesc; }
    public void setSubContentDesc(String subContentDesc) { this.subContentDesc = subContentDesc; }

    public String getTextBoxOption() { return textBoxOption; }
    public void setTextBoxOption(String textBoxOption) { this.textBoxOption = textBoxOption; }

    public String getActionTaken() { return actionTaken; }
    public void setActionTaken(String actionTaken) { this.actionTaken = actionTaken; }

    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
}
