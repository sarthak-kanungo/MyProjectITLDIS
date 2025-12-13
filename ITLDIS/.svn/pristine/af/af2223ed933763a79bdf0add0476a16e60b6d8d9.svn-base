/*
 * AT4_TempModelCreationAction.java
 *
 * Created on December 16, 2008, 6:19 PM
 */
/*
File Name: 	AT4_TempModelCreationAction.java
PURPOSE: 	TO forward the request from home page to Attach Groups to Model.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
16/12/08	1.0		PRAMOD.VISHWAKARMA  $$1 Created
 */
package EAMG.Model.Action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author avinash.pandey
 * @version
 */
public class EAMG_TempModelCreationAction extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /////////getting and setting values to and from session//////////////////////
        HttpSession hs = request.getSession();
        String mno = request.getParameter("model_no");
        ConnectionHolder holder = (ConnectionHolder) hs.getValue("servletapp.connection");
        String model_check = request.getParameter("model_check");      
        String server_name = (String) hs.getValue("server_name");
        String ecatPath = (String) hs.getValue("ecatPATH");
        String mainURL = (String) hs.getValue("mainURL");
        PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
        String modelNo = object_pageTemplate.MODEL_NO;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection conn = holder.getConnection();
        //if(model_check!=null){
        if (model_check.equals("model_status")) {

            String modelStatus=request.getParameter("modelStatus");
            try
            {
                String query1 = "update CAT_MODELS set STATUS=? where MODEL_NO=?";
                ps = conn.prepareStatement(query1);
                ps.setString(1, modelStatus);
                ps.setString(2, mno);
                ps.executeUpdate();
                ps.close();
                String result = "" + modelNo + " '" + mno + "' Status Modified Successfully.";
                conn.commit();
                request.setAttribute("result", result);
                return mapping.findForward("statusSuccess");
            }catch(Exception e){e.printStackTrace();}
         //}
        } else {
            hs.setAttribute("modelno", mno);
            String flag = request.getParameter("flag");
            String caller = request.getParameter("caller");
            hs.setAttribute("flag", flag);
            hs.setAttribute("caller", caller);
            
        }
       return mapping.findForward(SUCCESS);

    }
}
