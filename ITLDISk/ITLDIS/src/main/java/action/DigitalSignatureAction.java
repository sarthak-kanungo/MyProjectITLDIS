/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import dao.WarrantyDAO;
import viewEcat.comEcat.ComUtils;

public class DigitalSignatureAction extends DispatchAction {

	private static final String SUCCESS = "success";


	// https://crm.sonalika.com:7070/ITLDIS/DigitalSignatureAction.do?uuid=USGWSNLK14052025183949138&status=SIGNED
	// http://localhost:8080/ITLDIS/DigitalSignatureAction.do?uuid=USGWSNLK14052025183949138&status=SIGNED

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	                             HttpServletResponse response) throws Exception {
	    String uuid = request.getParameter("uuid");
	    String status = request.getParameter("status");
	    String realPath = getServlet().getServletContext().getRealPath("/");
	    String contextPath = request.getContextPath();

	    System.out.println("Inside DigitalSignatureAction - execute");
	    System.out.println("UUID: " + uuid);
	    System.out.println("Status: " + status);

	    // Validate input
	    if (uuid == null || uuid.trim().isEmpty()) {
	        response.getWriter().write("Missing or invalid UUID.");
	        return null;
	    }

	    if ("SIGNED".equalsIgnoreCase(status)) {
	        WarrantyDAO dao = new WarrantyDAO();
	        String apiKey = ComUtils.getPropertyValue("API_KEY").trim();
	        String fileFetchUrl = ComUtils.getPropertyValue("FILE_FETCH_URL").trim();

	        Map<String, String> result = dao.fetchAndSaveSignedFile(uuid, apiKey, realPath, contextPath, fileFetchUrl);

	        if ("success".equalsIgnoreCase(result.get("result"))) {
	            response.getWriter().write("Signed file fetched successfully. Status: " + result.get("status") +
	                                       ", Message: " + result.get("message"));
	        } else {
	            response.getWriter().write("Failed to fetch signed file. Status: " + result.get("status") +
	                                       ", Message: " + result.get("message"));
	        }
	    } else {
	        response.getWriter().write("Signature not completed or failed. Status: " + status);
	    }

	    return null; 
	}



}
