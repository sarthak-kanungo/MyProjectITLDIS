/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EAMG.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


/**
 *
 * @author amandeep.juneja
 */
public class EAMG_commonForm {

     private String userId;
     private String password;
     private String selectLang;
     private String firstName;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
     private String lastName;
     private List<EAMG_commonForm> languageNameList = new ArrayList<EAMG_commonForm> ();
     public EAMG_commonForm () {
          super ();
          // TODO Auto-generated constructor stub
     }
     public void reset ( ActionMapping mapping , HttpServletRequest request ) {
          this.setUserId ( "" );
          this.setPassword ( "" );

     }
     /**
      * @return the userId
      */
     public String getUserId () {
          return userId;
     }
     /**
      * @param userId the userId to set
      */
     public void setUserId ( String userId ) {
          this.userId = userId;
     }
     /**
      * @return the password
      */
     public String getPassword () {
          return password;
     }
     /**
      * @param password the password to set
      */
     public void setPassword ( String password ) {
          this.password = password;
     }
     /**
      * @return the selectLang
      */
     public String getSelectLang () {
          return selectLang;
     }
     /**
      * @param selectLang the selectLang to set
      */
     public void setSelectLang ( String selectLang ) {
          this.selectLang = selectLang;
     }
     /**
      * @return the languageNameList
      */
     public List<EAMG_commonForm> getLanguageNameList () {
          return languageNameList;
     }
     /**
      * @param languageNameList the languageNameList to set
      */
     public void setLanguageNameList ( List<EAMG_commonForm> languageNameList ) {
          this.languageNameList = languageNameList;
     }
}
