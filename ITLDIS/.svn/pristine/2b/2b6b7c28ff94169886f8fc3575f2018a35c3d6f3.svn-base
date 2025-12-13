/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
File Name: 	AT4_CompleteModelAction.java
PURPOSE: 	To set the Status of Model to complete.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
5/11/08		1.0		PRAMOD.VISHWAKARMA  $$1 Created
*/
package EAMG.Model.Action;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author pramod.vishwakarma
 */
public class EAMG_CompleteModelAction extends org.apache.struts.action.Action {
    
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
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //////////////////getting the value from request///////////////////////
        HttpSession hs=request.getSession();
        String mno=request.getParameter("model_no");
        ConnectionHolder holder = (ConnectionHolder) hs.getValue("servletapp.connection");
        /////////////////////////////////////////////////////////////////////
        Connection conn =null;
        PreparedStatement ps=null;
        try{
            //updating the status.
            conn = holder.getConnection();
            ps=conn.prepareStatement("update CAT_MODELS set STATUS='COMPLETE' where MODEL_NO='"+mno+"'");
            ps.executeUpdate();
            ps.close();

            conn.commit();
         }
        catch(SQLException e)
        {
             e.printStackTrace();
             //throw new amw_SQLExceptionCatcher("SQLException",e);
        }
        catch(Exception e)
        {
             e.printStackTrace();
             //throw new amw_ExceptionCatcher("Exception",e);
        }
//        finally
//        {
//            //conn.close();
//        }
        
        return mapping.findForward(SUCCESS);
        
    }
}