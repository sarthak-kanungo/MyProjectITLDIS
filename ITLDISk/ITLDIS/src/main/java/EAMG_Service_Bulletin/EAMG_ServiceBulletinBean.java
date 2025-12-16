/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG_Service_Bulletin;

import java.util.ArrayList;
import java.util.Vector;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author satyaprakash.verma
 */
public class EAMG_ServiceBulletinBean extends org.apache.struts.action.ActionForm {
    private String option;
    private int sbID;
    private int yearOfIssue;
    private String type;
    private String issueDate;
    private String subject;
    private FormFile document;
    private String formFileName;
    private ArrayList<LabelValueBean> yearList;
    private Vector<String> yearsVectorList = null;
    private Vector<String> yearsTypeVectorList = null;
    private ArrayList dataList;
    

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
     * @return the sbID
     */
    public int getSbID() {
        return sbID;
    }

    /**
     * @param sbID the sbID to set
     */
    public void setSbID(int sbID) {
        this.sbID = sbID;
    }

    /**
     * @return the yearOfIssue
     */
    public int getYearOfIssue() {
        return yearOfIssue;
    }

    /**
     * @param yearOfIssue the yearOfIssue to set
     */
    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the issueDate
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * @param issueDate the issueDate to set
     */
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the document
     */
    public FormFile getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(FormFile document) {
        this.document = document;
    }

    /**
     * @return the formFileName
     */
    public String getFormFileName() {
        return formFileName;
    }

    /**
     * @param formFileName the formFileName to set
     */
    public void setFormFileName(String formFileName) {
        this.formFileName = formFileName;
    }

    /**
     * @return the yearsVectorList
     */
    public Vector<String> getYearsVectorList() {
        return yearsVectorList;
    }

    /**
     * @param yearsVectorList the yearsVectorList to set
     */
    public void setYearsVectorList(Vector<String> yearsVectorList) {
        this.yearsVectorList = yearsVectorList;
    }

    /**
     * @return the yearsTypeVectorList
     */
    public Vector<String> getYearsTypeVectorList() {
        return yearsTypeVectorList;
    }

    /**
     * @param yearsTypeVectorList the yearsTypeVectorList to set
     */
    public void setYearsTypeVectorList(Vector<String> yearsTypeVectorList) {
        this.yearsTypeVectorList = yearsTypeVectorList;
    }

    /**
     * @return the yearList
     */
    public ArrayList<LabelValueBean> getYearList() {
        return yearList;
    }

    /**
     * @param yearList the yearList to set
     */
    public void setYearList(ArrayList<LabelValueBean> yearList) {
        this.yearList = yearList;
    }

    /**
     * @return the dataList
     */
    public ArrayList getDataList() {
        return dataList;
    }

    /**
     * @param dataList the dataList to set
     */
    public void setDataList(ArrayList dataList) {
        this.dataList = dataList;
    }


    
}
