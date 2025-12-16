/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Model.Action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;

import EAMG.Model.DAO.EAMG_ModelDAO;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author manish.kishore
 */
public class EAMG_Variant_AggregatesAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private static final String FAIL = "failure";
    private static final String INIT = "init";
    private static final String INITAGGREGATE = "initAggregate";
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        EAMG_Variant_AggregatesActionForm aggregateForm = (EAMG_Variant_AggregatesActionForm) form;
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String option = aggregateForm.getOption();
        Connection conn = null;
        EAMG_MethodsUtility amw_Utility = null;
        String sql = null;
        ArrayList<String> result = null;
        EAMG_ModelDAO dao = null;
        String forward = FAIL;

        try {
            conn = holder.getConnection();
            conn.setAutoCommit(false);
            dao = new EAMG_ModelDAO();


            if (option.equals("initVariant")) {
                amw_Utility = new EAMG_MethodsUtility();
                sql = "select distinct model_no from cat_models";
                result = amw_Utility.AutoComplete(conn, sql);
                request.setAttribute("result", result);
                aggregateForm.setModel(result);
                result = dao.getAggregatesValue(conn);
                aggregateForm.setAggregates(result);
                forward = INIT;
            } else if (option.equals("variantAggregates")) {
                String[] aggr = aggregateForm.getAttachedlist();
                result = new ArrayList<String>();
                for (String temp : aggr) {
                    result.add(temp);
                }
                aggregateForm.setAttachedlistArray(result);
                forward = INITAGGREGATE;
            } else if (option.equals("addVariant")) {
                String res = dao.insertVariantAggregates(conn, aggregateForm);
                if (!res.equals("fail")) {
                    request.setAttribute("success", res);
                } else {
                    request.setAttribute("fail", res);
                }
                forward = SUCCESS;
            }
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
            forward = FAIL;
            request.setAttribute("aggregateResult", "Unable to Add Variant.Please Contact to System Administrator.");
            logger.error("Caught in Exception", e);
        } finally {
            try {
//                if (conn != null) {
//                    conn.close();
//                    conn = null;
//                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Caught in Final Exception", e);
            }
        }
        return mapping.findForward(forward);
    }
}
