/*
 * EAMG_CreateModel.java
 *
 * Created on November 08, 2011, 4:14 PM
 */
/*
File Name: 	EAMG_CreateModel.java
PURPOSE: 	TO set Model as not exist.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
08/11/11	1.0		avinash.pandey  $$1 Created
 */
package EAMG.Model.Action;

import java.sql.Connection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import EAMG.Model.DAO.EAMG_ModelDAO;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author pramod.vishwakarma
 * @version
 */
public class EAMG_CreateModel extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private Logger logger = Logger.getLogger(this.getClass());
    Connection sqlConnection = null;

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
        try {
            HttpSession hs = request.getSession();
            ConnectionHolder holder = (ConnectionHolder) hs.getValue("servletapp.connection");
            sqlConnection = holder.getConnection();
            
            EAMG_ModelDAO dao = new EAMG_ModelDAO();
            Vector levelidVec = new Vector();
            levelidVec = dao.getModelEngineSeries(sqlConnection);
            hs.setAttribute("modelstatus", "notexist");
            hs.setAttribute("levelidVec", levelidVec);
            ////System.out.println("::::::::::Model Status Set:::::::::");
        } catch (Exception e) {
            logger.error("Caught in Exception", e);

        } finally {
            try {
//                if (sqlConnection != null) {
//                    sqlConnection.close();
//                    sqlConnection = null;
//                }
            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return mapping.findForward("success");
    }
}
