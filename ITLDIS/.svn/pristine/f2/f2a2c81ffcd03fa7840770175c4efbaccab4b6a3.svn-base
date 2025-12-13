/*
File Name   : 	AT4_DeleteUnusedKitsAction.java
PURPOSE     : 	TO DELETE UNUSED KITS FROM DATABASE.
HISTORY     :
DATE                    BUILD     AUTHOR    		MODIFICATIONS
December 22, 2008	1.0       SURENDER KUMAR        $$1 Creation
*/

package EAMG.Tool.Action;


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


public class EAMG_DeleteUnusedToolsAction extends Action {
    
    private final static String SUCCESS = "success";
        
     public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    
         /*****************Getting  the values through request parameter.***************/
         HttpSession session = request.getSession();
           String[] deletedarr=(String[])request.getParameterValues("unusedtoollist");
			  String comp_type=request.getParameter("comp_type");
			  request.setAttribute("comp_type",comp_type);
           ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
         /***************************************************************************/
        /*************************Initializing the variables*************************/
              Connection conn=null; 
              
              PreparedStatement pstmt=null;
          /**************************************************************************/
        try{
            /*****************Getting Connection**********************/     
              conn= holder.getConnection();
            /*********************************************************/
            
              /*********************************
               * Deletion of UnusedKits Starts.
               *********************************/
				  pstmt=conn.prepareStatement("DELETE FROM cat_part WHERE part_NO=?");
              for(int i=0;i<deletedarr.length;i++)
              {
                  
                pstmt.setString(1, deletedarr[i]);
					 pstmt.addBatch();

					 if(i%200==0)
						  pstmt.executeBatch();
              }
              pstmt.executeBatch();
              conn.commit();
				  pstmt.close();
        }
          catch(SQLException e)
            {
              e.printStackTrace();
              
            }
            catch(Exception e)
            {
              e.printStackTrace();
              
            }
            
         /**************Setting variables in session***********************/
           request.setAttribute("deletedarr",deletedarr);
         /*****************************************************************/   
           return mapping.findForward(SUCCESS);  
   }
     
}
