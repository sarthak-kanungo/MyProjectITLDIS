package viewEcat.orderEcat;

//Modification
//Date            line_no       By
//5/1/2006         306          MB
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;

public class DelPartFrmExcel extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) {
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
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

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String ecat_imagesURL = "";
            ecat_imagesURL = object_pageTemplate.ecat_imagesURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String printURL = "";
            printURL = object_pageTemplate.printURL();

            String mhtURL = "";
            mhtURL = object_pageTemplate.mhtURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String scriptPRINT = "";
            scriptPRINT = object_pageTemplate.scriptPRINT();

            String dsnPATH_ORDER = object_pageTemplate.dsnPATH_ORDER();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PrintStream ps = new PrintStream(res.getOutputStream());
            res.setContentType("text/html");
            ps.println(header);
            int index = 0;

            if (session_id.equals(getSession)) {
                if (getType.equalsIgnoreCase("dealer")) {
                    Vector totalParts = (Vector) session.getValue("cartItems");

                    if (totalParts != null) {

                        Connection conn;
                        //Statement stmt;
                        PreparedStatement stmt = null;
                        ResultSet rs;
                        String partListToDelete = req.getParameter("partListToDelete");

                        String partList[] = partListToDelete.split("@");
                        for (int i = 0; i < partList.length; i++) {

                            totalParts.remove(totalParts.indexOf(partList[i])+1);
                            totalParts.remove(partList[i]);
                        }

                        session.putValue("cartItems", totalParts);


                        conn = holder.getConnection();
                        //stmt = conn.createStatement();

                        ps.println("<center>");
                        ps.println("<script language=JavaScript type=text/JavaScript>");
                        ps.println("	function refreshParentWndow()");
                        ps.println("	{");
                        ps.println("		if(window.opener && !window.opener.closed)");
                        ps.println("		window.opener.history.go(0);");
                        ps.println("		self.close();");
                        ps.println("	}");
                        ps.println("");
                        ps.println("</script>");
                        ps.println("<body onLoad=refreshParentWndow()>");
                    }
                }

            } else {
                ps.println(loginAgain);
            }
            ps.println(footer);
        } catch (Exception e) {
            e.printStackTrace();
           // System.out.println(e);
        }
    }
}
