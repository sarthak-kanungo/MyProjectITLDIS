package viewEcat.comEcat;

/*
File Name: SignOut.java
PURPOSE: To sign out from e-Catalogue.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOut extends HttpServlet
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

            String session_id = "" + session.getValue("session_value");
            if (!session_id.equalsIgnoreCase("null"))
            {
                for (int i = 0; i < LoginVector.loginVec.size(); i++)
                {
                    Vector userSessionVec = (Vector) LoginVector.loginVec.elementAt(i);
                    HttpSession tempSession = (HttpSession) userSessionVec.elementAt(2);
                    if (session_id.equals(tempSession.getId()))
                    {
                        String userCode = "" + tempSession.getValue("userCode");
                        if (userSessionVec.contains(userCode))
                        {
                            LoginVector.loginVec.remove(userSessionVec);
                            break;
                        }
                    }
                }
            }

            session.removeValue("user_type");
            session.removeValue("userFun");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            if (holder != null)
            {
                Connection conn = holder.getConnection();
                conn.rollback();
                conn.close();
            }

            session.invalidate();

            ps.println("<html>");
            ps.println("   <head>");
            ps.println("</HEAD>");
            ps.println("<BODY LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 onload=\"window.close()\">");
            ps.println("</body></html>");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ps.close();
            wps.close();
        }
    }
}