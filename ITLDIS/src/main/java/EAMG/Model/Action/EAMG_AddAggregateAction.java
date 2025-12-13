/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Model.Action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
 * @author avinash.pandey
 */
public class EAMG_AddAggregateAction extends Action {
    /* forward name="success" path="" */

    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private Logger logger = Logger.getLogger(this.getClass());
    Connection sqlConnection = null;
    String flag = null;

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
            String result = "";
            int aggId_count = 0;
            //Statement statement = null;
            PreparedStatement statement = null;
            
            ResultSet rs = null;
            PreparedStatement ps = null;
            EAMG_ModelDAO dao = new EAMG_ModelDAO();
            String aggregateName = request.getParameter("aggregateName").trim();
            result = dao.getAggregateName(aggregateName, sqlConnection);
            if (!result.equals("notpresent")) {
                flag = "exist";
            } else {
                flag = "notexist";
            }
            if (flag.equals("notexist")) {
                //statement = sqlConnection.createStatement();
                //selection of Parameter Description from GRP_BOM_PARAM_MASTER
               // rs = statement.executeQuery("Select Count(*) from VARIANT_AGGREGATE_MASTER");
               String query = ("Select Count(*) from VARIANT_AGGREGATE_MASTER(NOLOCK)");
               statement = sqlConnection.prepareStatement(query);
               rs = statement.executeQuery();
            	while (rs.next()) {
                    aggId_count = rs.getInt(1);
                    aggId_count++;
                }
                rs.close();
                statement.close();
                String query2 = "insert into VARIANT_AGGREGATE_MASTER values(?,?)";
                ps = sqlConnection.prepareStatement(query2);
                ps.setInt(1, aggId_count);
                ps.setString(2, aggregateName);
                ps.executeUpdate();
                ps.close();
                hs.setAttribute("flag", "add_Aggregate");
                request.setAttribute("option", "create");
                result = "Aggregate Name \"" + aggregateName + "\" Create Successfully.";
                request.setAttribute("result", result);
                sqlConnection.commit();
                return mapping.findForward(SUCCESS);
            } else {
                hs.setAttribute("aggregate", "exist");
                hs.setAttribute("flag", "add_Aggregate");
                hs.setAttribute("exist_value", result);
                request.setAttribute("option", "create");
                sqlConnection.commit();
                return mapping.findForward("exist");
            }
            ////System.out.println("::::::::::Model Status Set:::::::::");
        } catch (Exception e) {
            logger.error("Caught in Exception", e);
            request.setAttribute("option", "create");
            return mapping.findForward("fail");

        } finally {
            try {
                if (sqlConnection != null) {
                    sqlConnection.close();
                    sqlConnection = null;
                }
            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
    }
}
