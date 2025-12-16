/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import authEcat.UtilityMapkeys1;

/**
 *
 * @author vijay.mishra
 */
public class ChangeAction extends HttpServlet
{

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        /*  HttpSession session=null;
        String forward = null;
        try{
        session=request.getSession();
        RequestDispatcher rd=request.getRequestDispatcher("/Authenticate_user");
        rd.forward(request, response);

        }catch(Exception e){
        System.out.println("exception"+e);
        }
         */
        PrintStream wps = new PrintStream(response.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);

        HttpSession session=request.getSession();
        ps.println("<HEAD>");
        ps.println("<TITLE>" + UtilityMapkeys1.tile1 + "</TITLE>");
        ps.println("<META HTTP-EQUIV=Content-Type CONTENT=text/html; charset=iso-8859-1>");
        ps.println("</HEAD>");
        ps.println("<body onLoad=\"javascript:document.homeForm.submit();\">");
        ps.println("<form action='Authenticate_criteria' method='POST' name='homeForm'>");
        ps.println("<input type='hidden' name='criteria' value='latest'/>");
        ps.println("<input type='hidden' name='q' value='40'/>");
        ps.println("<input type='hidden' name='id' value='" + session.getAttribute("user_id") + "'/>");
        ps.println("</form>");
        ps.println("</BODY>");
        ps.println("</HTML>");
    }
}
