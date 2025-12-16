/*
 *
File Name   : 	EAMG_DeleteUnusedpartsAction.java
PURPOSE     : 	TO Delete Unused Parts from the DATABASE.
HISTORY     :
DATE        	BUILD     	AUTHOR    	MODIFICATIONS
09/11/11	1.0		Avinash Pandey	$$1 	Created
 *
 */
package EAMG.Part.Action;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import viewEcat.comEcat.ConnectionHolder;

public class EAMG_DeleteUnusedpartsAction extends Action {

    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /*****************Getting  the values through request parameter.***************/
        HttpSession session = request.getSession();
        String[] deletedarr = request.getParameterValues("unusedpartslist");
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        /****************************************************************************/
       /*************************Initializing the variables*************************/
        Connection conn = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        String query="";
        int result=0;
       /*************************************************************************/ 
        try {
              /*****************Getting Connection*****************************/ 
               conn =holder.getConnection();
               //stmt = conn.createStatement();
              /********************************** 
                 * Deletion of UnusedParts Starts.
                 **********************************/
                for (int i = 0; i < deletedarr.length; i++) {
                query = "Delete from CAT_PART where PART_NO ='" + deletedarr[i] + "'";
                stmt = conn.prepareStatement(query);
                result = stmt.executeUpdate();
                 /********************************** 
                  * Deletion of UnusedParts Ends.
                  **********************************/
                //query="Delete from COMP_PARAM_VALUES where COMP_NO ='"+deletedarr[i]+"'";
                //result=stmt.executeUpdate(query);
               
            }
               stmt.close();
               conn.commit();
         /**************Setting variables in session***********************/    
            request.setAttribute("deletedarr", deletedarr);
         /****************************************************************/    
        } catch(SQLException e)
        {
          e.printStackTrace();
          //throw new amw_SQLExceptionCatcher("SQLException",e);
        }
        catch(Exception e)
        {
          e.printStackTrace();
          //throw new amw_ExceptionCatcher("Exception",e);
        }
        finally {
            //conn.close();/********************Connection Closed**************************/
        }
        return mapping.findForward(SUCCESS);

    }
}
