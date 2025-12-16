/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Other.Action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import EAMG.Model.DAO.EAMG_ModelDAO;
import EAMG.Other.ActionFormBean.ECNFormBean;
import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author manish.kishore
 */
public class UploadECN extends org.apache.struts.action.Action
{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        ECNFormBean ecnForm = (ECNFormBean) form;
        String option = ecnForm.getOption();
        String forward = "errorPage";

        try
        {
            HttpSession session = request.getSession();
            request.setAttribute("result", "There is some problem.please try later.");
            String result = null;
            String contextPath = request.getContextPath();
            String userCode = (String) session.getValue("userCode");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            Connection conn = holder.getConnection();
            EAMG_ModelDAO dao = new EAMG_ModelDAO();
            if (option.equals("init"))
            {
                result = dao.getModels(ecnForm, conn, "COMPLETE");
                if (result.equals("success"))
                {
                    forward = "uploadECN";
                }
            }
            else if (option.equals("implement") || option.equals("implementECNSelectedModel"))
            {
                conn.setAutoCommit(false);
                dao.getModels(ecnForm, conn, "COMPLETE");
                result = dao.implementECN(ecnForm, conn, userCode,getServlet().getServletContext().getRealPath("/"));
             //   int counter = ecnForm.getCounter();

                if (result.equals("success"))
                {
                    conn.commit();
                    request.setAttribute("tdData", "MANAGE ECN >> IMPLEMENT ECN");
                    request.setAttribute("heading", "IMPLEMENT ECN");
                    request.setAttribute("result", "ECN has been Implemented Successfully.");
                    request.setAttribute("existMessage", "ECN of following " + PageTemplate.GROUP + "s has already been Implemented.");
                    request.setAttribute("optionLink", "Implement more.");
                    request.setAttribute("optionURL", "" + contextPath + "/UploadECN.do?option=init");
                    forward = "success";
                }
                else if(result.equals("MoreModelAffected"))
                {
                    conn.rollback();
                    request.setAttribute("affectedModelList", ecnForm.getAffectedModelList());
                    forward = "affectedModel"; 
                }
                else
                {
                    conn.rollback();
                    ActionMessages am = new ActionMessages();
                    am.add("message", new ActionMessage("my.custom.message", result));
                    saveErrors(request, am);
                    forward = "uploadECN";
                }

            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
}
