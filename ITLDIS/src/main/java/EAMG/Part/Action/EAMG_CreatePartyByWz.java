/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Part.Action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.WebConstants;

/**
 *
 * @author avinash.pandey
 */
public class EAMG_CreatePartyByWz extends org.apache.struts.action.Action {

    private Logger logger = Logger.getLogger(this.getClass());
    private final static String FAILURE = "failure";
    Connection sqlConnection = null;

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession hs = request.getSession();
        String comp_type = "" + hs.getAttribute("comp_type");
        //System.out.println("get comp_type from session:" + comp_type);
       // request.setAttribute("status", "");
        if (comp_type.equals("PRT")) {
            return mapping.findForward(WebConstants.FORWARD_INIT_PART);
        } else {
            return mapping.findForward(FAILURE);
        }
    }
}
