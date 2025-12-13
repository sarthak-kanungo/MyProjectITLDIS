/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Other.ActionFormBean;

import java.util.ArrayList;


/**
 *
 * @author manish.kishore
 */
public class ECNFormBean extends org.apache.struts.action.ActionForm {

    private ArrayList<String> modelList;
    private String modelNo;
    private String changeType;
    private String status;
    private String effectiveDate;
    private String effectiveTSN;
    private String oldPartNo;
    private String newPartNo;
    private String option;
    private String message;
    private ArrayList existList;
    private ArrayList<UtilityOptionBean> affectedModelList;
    private int counter;
    private String ecnNo;
    private String[] affectedModels;
    private String[] attachedlist;

    /**
     * @return the modelNo
     */
    /**
     * @return the changeType
     */
    public String getChangeType() {
        return changeType;
    }

    /**
     * @param changeType the changeType to set
     */
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the effectiveDate
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @param effectiveDate the effectiveDate to set
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @return the effectiveTSN
     */
    public String getEffectiveTSN() {
        return effectiveTSN;
    }

    /**
     * @param effectiveTSN the effectiveTSN to set
     */
    public void setEffectiveTSN(String effectiveTSN) {
        this.effectiveTSN = effectiveTSN;
    }

    /**
     * @return the oldPartNo
     */
    public String getOldPartNo() {
        return oldPartNo;
    }

    /**
     * @param oldPartNo the oldPartNo to set
     */
    public void setOldPartNo(String oldPartNo) {
        this.oldPartNo = oldPartNo;
    }

    /**
     * @return the newPartNo
     */
    public String getNewPartNo() {
        return newPartNo;
    }

    /**
     * @param newPartNo the newPartNo to set
     */
    public void setNewPartNo(String newPartNo) {
        this.newPartNo = newPartNo;
    }

    /**
     * @return the option
     */
    public String getOption() {
        return option;
    }

    /**
     * @param option the option to set
     */
    public void setOption(String option) {
        this.option = option;
    }

    /**
     * @return the modelNo
     */
    public String getModelNo() {
        return modelNo;
    }

    /**
     * @param modelNo the modelNo to set
     */
    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    /**
     * @return the modelList
     */
    public ArrayList<String> getModelList() {
        return modelList;
    }

    /**
     * @param modelList the modelList to set
     */
    public void setModelList(ArrayList<String> modelList) {
        this.modelList = modelList;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the existList
     */
    public ArrayList getExistList() {
        return existList;
    }

    /**
     * @param existList the existList to set
     */
    public void setExistList(ArrayList existList) {
        this.existList = existList;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getEcnNo() {
        return ecnNo;
    }

    public void setEcnNo(String ecnNo) {
        this.ecnNo = ecnNo;
    }

    /**
     * @return the affectedModelList
     */
    public ArrayList<UtilityOptionBean> getAffectedModelList() {
        return affectedModelList;
    }

    /**
     * @param affectedModelList the affectedModelList to set
     */
    public void setAffectedModelList(ArrayList<UtilityOptionBean> affectedModelList) {
        this.affectedModelList = affectedModelList;
    }

    /**
     * @return the affectedModels
     */
    public String[] getAffectedModels() {
        return affectedModels;
    }

    /**
     * @param affectedModels the affectedModels to set
     */
    public void setAffectedModels(String[] affectedModels) {
        this.affectedModels = affectedModels;
    }

    /**
     * @return the attachedlist
     */
    public String[] getAttachedlist() {
        return attachedlist;
    }

    /**
     * @param attachedlist the attachedlist to set
     */
    public void setAttachedlist(String[] attachedlist) {
        this.attachedlist = attachedlist;
    }
}
