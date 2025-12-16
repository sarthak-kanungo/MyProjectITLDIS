package viewEcat.comEcat;

/*
File Name: OpenIE.java
PURPOSE: It is used for opening files like index.html, zip files etc using java.lang.Runtime.getRuntime().exec().
		 It is mainly used at Local AMchine where path for the files is like file:\\\c:\
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
*/
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OpenIE extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException
	{
		PrintStream wps = new PrintStream(res.getOutputStream());
		PrintWriter ps = new PrintWriter(wps,true);
		res.setContentType("text/html");
		try
		{	
			///////////////////////////// CREATE SESSION /////////////////////////////
			////////////////////////////////////////////////////////////////////////////////

			HttpSession session = req.getSession(true);
			String session_id = session.getId();
			
			String getSession = (String)session.getValue("session_value");
			String server_name = (String)session.getValue("server_name");
			String ecatPATH = (String)session.getValue("ecatPATH");
			String mainURL = (String)session.getValue("mainURL");
		
			///////////////////////////// CREATE SESSION /////////////////////////////
			////////////////////////////////////////////////////////////////////////////////
			
			////////////////////////////////////////////////////////////////////////////////
			/////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
			////////////////////////////////////////////////////////////////////////////////

			PageTemplate object_pageTemplate = new PageTemplate(server_name,ecatPATH,mainURL);
			
			
			String loginAgain = "";
			loginAgain = object_pageTemplate.loginAgain();
                        String imagesURL=object_pageTemplate.imagesURL();

			
			////////////////////////////////////////////////////////////////////////////////
			/////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
			////////////////////////////////////////////////////////////////////////////////

			if (session_id.equals(getSession))
			{
				String url = req.getParameter("url");
				url = url.replaceAll("@@@","&");

				String os = System.getProperty("os.name");
				if (os.equals("Windows NT"))
				{
					java.lang.Runtime.getRuntime().exec("\"C:/Program Files/Plus!/Microsoft Internet/IEXPLORE.EXE\""+url);
				}
				else
				{
					java.lang.Runtime.getRuntime().exec("\"C:/Program Files/Internet Explorer/IEXPLORE.EXE\""+url);
				}
				ps.println("<script>");
				ps.println("window.close()");
				ps.println("</script>");
			}
			else
			{
				object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
			}
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