/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EAMG.common;

import java.util.Date;


/**
 *
 * @author kumar.avinash
 */
public class VOBase extends org.apache.struts.action.ActionForm{

     private String createdBy;
     private String userName;
     private Long createdById;
     private Date creationDate;
     private String modifiedBy;
     private Long modifiedById;
     private Date modificationDate;
     private Boolean isActive = Boolean.TRUE;
     private String createdDate;
     private String originalyCreatedBy;
     private String[] unassign_Code;
     private String[] assign_Code;
     public String getCreatedBy () {
          return createdBy;
     }
     public void setCreatedBy ( String createdBy ) {
          this.createdBy = createdBy;
     }
     public Long getCreatedById () {
          return createdById;
     }
     public void setCreatedById ( Long createdById ) {
          this.createdById = createdById;
     }
     public Date getCreationDate () {
          return creationDate;
     }
     public void setCreationDate ( Date creationDate ) {
          this.creationDate = creationDate;
     }
     public String getModifiedBy () {
          return modifiedBy;
     }
     public void setModifiedBy ( String modifiedBy ) {
          this.modifiedBy = modifiedBy;
     }
     public Long getModifiedById () {
          return modifiedById;
     }
     public void setModifiedById ( Long modifiedById ) {
          this.modifiedById = modifiedById;
     }
     public Date getModificationDate () {
          return modificationDate;
     }
     public void setModificationDate ( Date modificationDate ) {
          this.modificationDate = modificationDate;
     }
     public Boolean getIsActive () {
          return isActive;
     }
     public void setIsActive ( Boolean isActive ) {
          this.isActive = isActive;
     }
     public String getUserName () {
          return userName;
     }
     public void setUserName ( String userName ) {
          this.userName = userName;
     }
     public String getCreatedDate () {
          return createdDate;
     }
     public void setCreatedDate ( String createdDate ) {
          this.createdDate = createdDate;
     }
     public String getOriginalyCreatedBy () {
          return originalyCreatedBy;
     }
     public void setOriginalyCreatedBy ( String originalyCreatedBy ) {
          this.originalyCreatedBy = originalyCreatedBy;
     }
     /**
      * @return the unassign_Code
      */
     public String[] getUnassign_Code () {
          return unassign_Code;
     }
     /**
      * @param unassign_Code the unassign_Code to set
      */
     public void setUnassign_Code ( String[] unassign_Code ) {
          this.unassign_Code = unassign_Code;
     }
     /**
      * @return the assign_Code
      */
     public String[] getAssign_Code () {
          return assign_Code;
     }
     /**
      * @param assign_Code the assign_Code to set
      */
     public void setAssign_Code ( String[] assign_Code ) {
          this.assign_Code = assign_Code;
     }
}
