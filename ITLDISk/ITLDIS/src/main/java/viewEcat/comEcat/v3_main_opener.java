/*
File Name: v3_main_opener.java
PURPOSE: To open index file
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA		v3.4		Anuj Raj Singh			$$0 Created
 */
package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authEcat.UtilityMapkeys1;


public class v3_main_opener extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException
	{
		PrintStream wps = new PrintStream(res.getOutputStream());
		PrintWriter ps = new PrintWriter(wps,true);
		res.setContentType("text/html");
		try
		{	
			ps.println("<html>");
			ps.println("<head>");
			ps.println("<title>");
			ps.println(""+UtilityMapkeys1.tile1+" - E-CATALOGUE.");
			ps.println("</title>");
			ps.println("</head>");
			ps.println("<body onLoad='javascript:document.location.href=\"Index\"'>");
			ps.println("</body>");
			ps.println("</html>");	
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