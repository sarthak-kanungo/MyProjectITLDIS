package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
File Name: Blank.java
PURPOSE: Blank Page.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
*/
import authEcat.UtilityMapkeys1;


public class Blank extends HttpServlet
{
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
			ps.println("<HTML>");
			ps.println("<HEAD>");
			ps.println("<TITLE>"+UtilityMapkeys1.tile1+"</TITLE>");

			ps.println("<script language = JavaScript>");

			ps.println("var oLastBtn=0;");
			ps.println("bIsMenu = false;");
			ps.println("");
			ps.println("//No RIGHT CLICK");
			ps.println("");
			ps.println("if (window.Event) ");
			ps.println("document.captureEvents(Event.MOUSEUP); ");
			ps.println("function nocontextmenu()");
			ps.println("{ ");
			ps.println("	event.cancelBubble = true ");
			ps.println("	event.returnValue = false; ");
			ps.println("	return false; ");
			ps.println("} ");
			ps.println("function norightclick(e) ");
			ps.println("{ ");
			ps.println("	if (window.Event) ");
			ps.println("	{ ");
			ps.println("		if (e.which !=1) ");
			ps.println("		return false; ");
			ps.println("	} ");
			ps.println("	else if (event.button !=1) ");
			ps.println("	{ ");
			ps.println("		event.cancelBubble = true ");
			ps.println("		event.returnValue = false; ");
			ps.println("		return false; ");
			ps.println("	} ");
			ps.println("} ");
			ps.println("document.oncontextmenu = nocontextmenu; ");
			ps.println("document.onmousedown = norightclick; ");
			ps.println("");
			ps.println("// Block backspace onKeyDown ");
			ps.println("function onKeyDown()");
			ps.println("{");
			ps.println("	if ( (event.altKey) || ((event.keyCode == 8) &&  (event.srcElement.type != \"text\" && event.srcElement.type != \"textarea\" && event.srcElement.type != \"password\")) || ((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82)) ) || (event.keyCode == 116) )");
			ps.println("	{");
			ps.println("		event.keyCode = 0;");
			ps.println("		event.returnValue = false;");
			ps.println("	}");
			ps.println("}");
			ps.println("</SCRIPT>");

			ps.println("</HEAD>");
			/////////////////////////////////////////////////////////////////////////////////////
			ps.println("<BODY LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0  bgcolor=#FFFFFF onKeyDown=onKeyDown()>");

			ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=90%>");
			ps.println("<tr>");
			ps.println("<td valign = middle>");
				ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
				ps.println("<tr bgcolor = #666699>");
				ps.println("<td align = center>");
				ps.println("<font color = #FFFFFF face = verdana size = 2><b>");
				ps.println("CONNECTING TO INTERNET.........<br>");						
				ps.println("</td>");
				ps.println("</tr>");
				ps.println("</table>");
			ps.println("</td>");
			ps.println("</tr>");
			ps.println("</table>");

			ps.println("</BODY>");
			ps.println("</HTML>");
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