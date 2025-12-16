package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: RefNoDetails.java
PURPOSE: It is used to open the part Details or Assy details for the corresponding ref no.
HISTORY:
DATE		BUILD	AUTHOR			MODIFICATIONS
NA			v3.4	Deepak Mangal	$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class RefNoDetails extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        try
        {

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            //           String session_id = session.getId();

            //           String getSession = (String) session.getValue("session_value");
            String getType = (String) session.getValue("user_type");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////


            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String header = "";
            header = object_pageTemplate.header(getType);

            String footer = "";
            footer = object_pageTemplate.footer();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String scriptPRINT = "";
            scriptPRINT = object_pageTemplate.scriptPRINT();

            String databaseNAME = object_pageTemplate.databaseNAME();

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            /*
            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();
             */
            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            //       if (session_id.equals(getSession))
            //        {
            ps.println(header);
            ps.println(scriptPRINT);

            Connection conn;
            //     conn = holder.getConnection();

            String XPCounter = req.getParameter("XPCounter");
            if ((XPCounter == null) || (XPCounter.equals("null")))
            {
                Session dsn1 = new Session();
                servletURL = dsn1.getServletURL();
                //          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                //         conn = DriverManager.getConnection(dsn1.getDsnPath(), dsn1.getUserName(), dsn1.getPassword());
                conn = dsn1.getConnection();
            }
            else
            {
                conn = holder.getConnection();
            }


           // Statement stmt = conn.createStatement();
            PreparedStatement stmt = null;
            ResultSet rs;

            ps.println("<html>");
            ps.println("		<head>");
            ps.println("      <title>");
            ps.println("         "+UtilityMapkeys1.tile1+"");
            ps.println("      </title>");
            ps.println("			<style type=text/css>");
            ps.println("			<!--");
            ps.println("			* { font-family: Helvetica; font-size: 8pt;}");
            ps.println("			a:link {				color: #000000;				text-decoration: none; }");
            ps.println("			a:visited { color: #000000;				text-decoration: none;		  }	");
            ps.println("			-->");
            ps.println("			</style>");

            String refno = req.getParameter("partNo");
           String group = req.getParameter("group");
             refno = refno.trim();
            refno = refno.replaceAll("\'", "''");

            String part_No = "";
         
           int refPresence = 0;
           // rs = stmt.executeQuery("SELECT component FROM CAT_GROUP_KIT_BOM WHERE " + databaseNAME + "(GRP_KIT_NO) =" + databaseNAME + "('" + group + "') and " + databaseNAME + "(sequence) =" + databaseNAME + "('" + refno + "')");
           String sql = ("SELECT component FROM CAT_GROUP_KIT_BOM(NOLOCK) WHERE " + databaseNAME + "(GRP_KIT_NO) =" + databaseNAME + "('" + group + "') and " + databaseNAME + "(sequence) =" + databaseNAME + "('" + refno + "')");
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
           if (rs.next())
            {
                refPresence++;
                part_No = rs.getString(1);
            }
            rs.close();

          

            if (refPresence != 0 )
            {
                ps.println("<script language=JavaScript type=text/JavaScript>");

                ps.println("		function MM_openBrWindow(theURL,winName,features)");
                ps.println("		{ ");
                ps.println("			var isValidPopUpBlocker=detectPopupBlocker()");
                ps.println("			if (isValidPopUpBlocker== false)");
                ps.println("			{");
                ps.println("				return");
                ps.println("			}");
                ps.println("			window.open(theURL,winName,features);");
                ps.println("		}");

                ps.println("function detectPopupBlocker()");
                ps.println("{ ");
                ps.println("	var myTest = window.open(\"about:blank\",\"\",\"directories=no,height=100,width=100,menubar=no,resizable=no,scrollbars=no,status=no,titlebar=no,top=0,location=no\");");
                ps.println("	if (!myTest)");
                ps.println("	{");
                ps.println("		alert(\"Your Pop-up Blocker is Enabled. Please Add our site http://" + server_name + " to your trusted sites.\");");
                ps.println("		parent.location.href = 'Logout'");
                ps.println("		return false;");
                ps.println("	} ");
                ps.println("	else ");
                ps.println("	{");
                ps.println("		myTest.close();");
                ps.println("		return true;");
                ps.println("	}");
                ps.println("}");

                ps.println("	function openWin()");
                ps.println("		{ ");

                ps.println("MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + part_No + "','DETAILS','scrollbars=yes,width=700,height=605');");
               
                ps.println("		window.close();");
                ps.println("		}");
                ps.println("</script>");
            }



            ps.println("</head>");
            if (refPresence == 0)
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", "Ref. No. Not Found ", "NO", "", "", "", imagesURL);
            }
            else
            {
                ps.println("<body onLoad = openWin() >");
            }       
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ps.println(e);
        }
        finally
        {
            ps.close();
            wps.close();
        }
    }
}
