package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
/*
File Name: Status.java
PURPOSE: File To show the status.
HISTORY:
DATE		BUILD	 AUTHOR				MODIFICATIONS
NA			v3.4	 Deepak Mangal		$$0 Created
*/
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Status extends HttpServlet
{
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void service(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException
	{
		PrintStream wps = new PrintStream(res.getOutputStream());
		PrintWriter ps = new PrintWriter(wps,true);
		res.setContentType("text/html");

		res.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
		res.setHeader("Expires", "0");
		res.setHeader("Pragma", "no-cache");

		try
		{		
			ps.println("<html>");
			ps.println("   <head>");
			ps.println("   <title>LOGGED OUT</title>");
			ps.println("</HEAD>");
			ps.println("<BODY LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>");

			ps.println("<script>");
			ps.println(req.getParameter("status"));
			ps.println("</script>");

			ps.println("</body></html>");
		}
		catch(Exception e)
		{
			ps.println(e);
		}
		finally 
		{
			ps.close();
			wps.close();
		}
	}
}