package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: GroupWhereUsed.java
PURPOSE: To find the list of models where a particular group is attached.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class GroupWhereUsed extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        String productFamilySubQuery = null;
        String applicationTypSubQuery = null;
        String subQuery = "";
        String message = null;
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            productFamilySubQuery = "" + session.getAttribute("productFamilySubQuery");
            applicationTypSubQuery = "" + session.getAttribute("applicationTypSubQuery");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();


            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();

                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////
                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                /*########################################*/

               // Statement stmt;
                PreparedStatement stmt = null;
                ResultSet rs;

                //stmt = conn.createStatement();

                String group = req.getParameter("group");
                int width = 602, height = 259;
                ps.println("<html><head>");
                ps.println("      <title>");
                ps.println("         " + UtilityMapkeys1.tile1 + "");
                ps.println("      </title>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("</head>");
                ps.println("<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 >");

                int temp = 1;
                String temp_1 = "";
                String temp_2 = "";

                if (productFamilySubQuery.equals("")) {
                    subQuery = " WHERE MC.MODEL_NO=M.MODEL_NO " + applicationTypSubQuery;
                } else {
                    subQuery = productFamilySubQuery + " AND MC.MODEL_NO=M.MODEL_NO " + applicationTypSubQuery;
                }

                ps.println(object_pageTemplate.tableHeader("Applicable " + PageTemplate.MODEL_NO + "s for " + PageTemplate.GROUP + "", width, height));

                if (userFunctionalities.contains("36")) {
                    //rs = stmt.executeQuery("SELECT distinct M.MODEL_NO, M.DESCRIPTION,GM.GROUP_NO FROM CAT_MODEL_GROUP GM, CAT_MODELS M,CAT_MODEL_CLASSIFICATION MC " + subQuery + "  AND M.MODEL_NO = GM.MODEL_NO AND  GM.GROUP_NO = '" + group + "' AND M.STATUS='COMPLETE' ORDER BY GM.GROUP_NO");
               String query = ("SELECT distinct M.MODEL_NO, M.DESCRIPTION,GM.GROUP_NO FROM CAT_MODEL_GROUP GM(NOLOCK), CAT_MODELS(NOLOCK) M,CAT_MODEL_CLASSIFICATION(NOLOCK) MC " + subQuery + "  AND M.MODEL_NO = GM.MODEL_NO AND  GM.GROUP_NO = '" + group + "' AND M.STATUS='COMPLETE' ORDER BY GM.GROUP_NO");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                } else {
                   // rs = stmt.executeQuery("SELECT distinct M.MODEL_NO, M.DESCRIPTION,GM.GROUP_NO FROM CAT_MODEL_GROUP GM, CAT_MODELS M,CAT_MODEL_CLASSIFICATION MC " + subQuery + "  AND M.MODEL_NO = GM.MODEL_NO AND GM.GROUP_NO = '" + group + "' ORDER BY GM.GROUP_NO");
                	String query = ("SELECT distinct M.MODEL_NO, M.DESCRIPTION,GM.GROUP_NO FROM CAT_MODEL_GROUP(NOLOCK) GM, CAT_MODELS(NOLOCK) M,CAT_MODEL_CLASSIFICATION(NOLOCK) MC " + subQuery + "  AND M.MODEL_NO = GM.MODEL_NO AND GM.GROUP_NO = '" + group + "' ORDER BY GM.GROUP_NO");
               stmt = conn.prepareStatement(query);
               rs = stmt.executeQuery();
                }

                String cssClass = null;
                int counter = 0;

                if (rs.next()) {

                    ps.println("<table width=\"500px\" border=0 cellspacing=0 cellpadding=0><tr><td style=\"padding-top:10px;\">");
                    ps.println("<table width=\"500px\" border=1 cellspacing=0 cellpadding=3 bordercolor = #CCCCCC>");
                    ps.println("<tr>");
                    ps.println("<td align=center width = 55px; class=heading1>");
                    ps.println("S. No.");
                    ps.println("</td> ");

                    ps.println("<td valign=top  class=heading1>" 
  
    
);
                    ps.println("" + PageTemplate.MODEL_NO + " No");
                    ps.println("</td> ");
                    ps.println("</tr></table></td></tr><tr><td>");
                    ps.println("<div style=\"width: 517px; height: 150px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                    ps.println("<table width=\"500px\" border=1 cellspacing=0 cellpadding=3 bordercolor = #CCCCCC>");



                    do {

                        
                        
                        if (counter % 2 == 0) {
                            cssClass = "links_txt";//links_txt_Alt
                        } else {
                            cssClass = "links_txt";
                        }

                        counter++;

                        temp_1 = rs.getString(1);
                        temp_2 = rs.getString(2);
                        
                        //for(int k=0;k<100;k++)
                        {

                        ps.println("<tr>");
                        ps.println("<td align=center width = 55px; class="+cssClass+">");
                        ps.println(temp);
                        ps.println("</td> ");

                        ps.println("<td valign=top class="+cssClass+">");
                        ps.println("<a href='" + servletURL + "ViewModel?model=" + temp_1 + "' target = right>");
                        ps.println(temp_2);
                        ps.println(" [ ");
                        ps.println(temp_1);
                        ps.println(" ] ");
                        ps.println("</a>");
                        ps.println("</td> ");
                        ps.println("</tr>");
                        }
                        temp++;
                    } while (rs.next());
                    ps.println("<tr></table></div></td></tr><tr height=30><td class=links_txt valign=middle>");
                    ps.println("* Click On " + PageTemplate.MODEL_NO + " to view its details.");
                    ps.println("</td> ");
                    ps.println("	</tr>");
                    ps.println("</table>");
                    ps.println(object_pageTemplate.tableFooter());
                } else {
                    //message="NOT USED";
                    //ps.println(object_pageTemplate.tableHeader1(message, width, height));
                    object_pageTemplate.ShowMsg(ps, "FAILURE", "Not Used", "NO", "", "", "", imagesURL);
                }
                rs.close();
                stmt.close();


                ps.println("</body>");
                ps.println("</html>");

            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
        } catch (Exception e) {
            ps.println(e);
        } finally {
            ps.close();
            wps.close();
        }
    }
}
