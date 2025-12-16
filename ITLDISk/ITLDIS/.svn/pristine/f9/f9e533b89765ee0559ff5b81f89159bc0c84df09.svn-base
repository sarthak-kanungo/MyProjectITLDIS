/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
File Name: 	EAMG_CreateAssemblyByWz.java
PURPOSE: 	TO set Assembly as 'not exist'.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
14/11/11	1.0		AVINASH>PANDEY  $$1 Created
*/
package EAMG.Kit.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *         avinash.pandey
 */
public class EAMG_CreateAssemblyByWz extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
//    private final static String SUCCESS1 = "success1";
//    private final static String SUCCESS2 = "success2";
    private final static String SUCCESS3 = "success3";
//    private final static String SUCCESS4 = "success4";
    private final static String FAILURE = "failure";
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /////////setting value to session and request//////////////////////
        HttpSession hs=request.getSession();
        String comp_type=""+hs.getAttribute("comp_type");
        //System.out.println("get comp_type from session:"+comp_type);
        hs.setAttribute("status","notexist");
//        if(comp_type.equals("PRT"))
//        {
//            return mapping.findForward(SUCCESS1);
//        }
//        else if(comp_type.equals("ASM"))
//        {
//            return mapping.findForward(SUCCESS2);
//        }
//        else 
            if(comp_type.equals("KIT"))
        {
            return mapping.findForward(SUCCESS3);
        }
//        else if(comp_type.equals("TOL"))
//        {
//            return mapping.findForward(SUCCESS4);
//        }
        else{
            return mapping.findForward(FAILURE);
        }
        ////System.out.println("::::::::::Model Status Set:::::::::");
       // return mapping.findForward(SUCCESS1);
        
    }
}