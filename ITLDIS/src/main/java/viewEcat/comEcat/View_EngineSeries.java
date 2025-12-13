package viewEcat.comEcat;

/*
File Name: View_EngineSeries.java
PURPOSE: To show the list of main models.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import viewEcat.orderEcat.PriceDetails;

public class View_EngineSeries extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");

        String session_id = null;
        String getSession = null;
        String server_name = null;
        String ecatPATH = null;
        String mainURL = null;
        ConnectionHolder holder = null;
        String imagesURL = null;
        String loginAgain = null;
        String orderRefNoString = null;
        String imagePath = null;
        Connection conn = null;
        int imageWidth = 0;
        int imageHeight = 0;
        String productFamilySubQuery = null;
        String imageExt = null;
        ArrayList<String> engineSeries = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String date_OR_serial = null;
        String modelNo = null;
        String subQuery = "";
        String message = null;


        try {


            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            session_id = session.getId();

            getSession = (String) session.getValue("session_value");
            server_name = (String) session.getValue("server_name");
            ecatPATH = (String) session.getValue("ecatPATH");
            mainURL = (String) session.getValue("mainURL");
            productFamilySubQuery = "" + session.getAttribute("productFamilySubQuery");
            holder = (ConnectionHolder) session.getValue("servletapp.connection");
            date_OR_serial = (String) session.getValue("date_OR_serial");
            modelNo = (String) session.getValue("modelNo");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////


            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            imagesURL = object_pageTemplate.imagesURL();
            loginAgain = object_pageTemplate.loginAgain();
            orderRefNoString = object_pageTemplate.orderRefNoString();
            imagePath = object_pageTemplate.ecat_EngineSeries_imagesURL();
            engineSeries = new ArrayList<String>();
            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////
            //ps.println(header);

            if (session_id.equals(getSession)) {

                conn = holder.getConnection();
                //stmt = conn.createStatement();


                /*################## Login Track ######################*/
                if (orderRefNoString.equals("WEB")) {
                    new PriceDetails(conn).loginTrakerByUserId((String) session.getValue("userCode"), 2, req.getRemoteAddr(), conn);
                }

                /*########################################*/
                if (date_OR_serial.equals("serialNo")) {
                    if (productFamilySubQuery.equals("")) {
                        subQuery = " where MODEL_NO='" + modelNo + "'";
                    } else {
                        subQuery = " and  MODEL_NO='" + modelNo + "'";
                    }


                }
                //System.out.println("select distinct ENGINE_SERIES from MODEL_CLASSIFICATION "+productFamilySubQuery +subQuery);

                //rs = stmt.executeQuery("select distinct ENGINE_SERIES from CAT_MODEL_CLASSIFICATION " + productFamilySubQuery + subQuery);
                  String sql = ("select distinct ENGINE_SERIES from CAT_MODEL_CLASSIFICATION(NOLOCK) " + productFamilySubQuery + subQuery);
                  stmt = conn.prepareStatement(sql);
                  rs = stmt.executeQuery();



                //System.out.println("select distinct ENGINE_SERIES from MODEL_CLASSIFICATION "+productFamilySubQuery);
                //System.out.println(productFamilySubQuery);

                while (rs.next()) {
                    engineSeries.add(rs.getString(1));
                }
                rs.close();


//                rs = stmt.executeQuery("select Width,Height,ImageExt  from IMAGE_REFERENCE where ImageType='ENGINE SERIES'");
//
//                if (rs.next())
//                {
//                    imageWidth = rs.getString(1);
//                    imageHeight = rs.getString(2);
//                    imageExt = rs.getString(3);
//                }
//                rs.close();
                stmt.close();

                imageExt = PageTemplate.engineSeriesImageExt;
                imageWidth = PageTemplate.engineSeriesWidth;
                imageHeight = PageTemplate.engineSeriesHeight;

                ps.println("<html>");
                ps.println("   <head>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("</head>");

                ps.println("<body topmargin=0  rightmargin=0 leftmargin=0  >");
                //changes_01_02_2012

                int width = 970;
                int height = 500;//400

                if (engineSeries.size() == 0) {
                    ps.println(object_pageTemplate.tableHeader1(ComUtils.getLanguageTranslation("label.catalogue.noProductExist", session), width, height));
                    //object_pageTemplate.ShowMsg(ps, "FAILURE", "No  " + partNoPrint + " DOES NOT EXIST", "YES", "back", "javascript:history.go(-2);", "", imagesURL);
                    return;
                }
                //header
                //ps.println(object_pageTemplate.tableHeader(PageTemplate.ENGINE_SERIES + " Available", width, height));
                ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.products", session), width, height));


                ps.println("                 <table width=90% border=0 height=400 align=\"center\" cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");
                ps.println("                            <tbody>");
                ps.println("                                <tr valign=top>                       ");
                ps.println("              ");

                int noOfColumn = 3;
                int columnCounter = 0;
                boolean columnFlag = false;
                for (String productFamily : engineSeries) {

                    if (columnFlag) {
                        ps.println("                                <tr>                       ");
                        ps.println("              ");
                        columnFlag = false;
                    }

                    ps.println("                                        <td  align=\"center\">");
                    // ps.println("                                            <table cellspacing=0 cellpadding=0><tr><td align=\"center\">");
                    ps.println("                                                        <a href='View_EngineModel?engineSeries=" + productFamily + "' style=\"text-decoration: none\">");
                    ps.println("                                                            <img width=\"" + imageWidth + "\" height=\"" + imageHeight + "\" border=\"0\" src='" + imagePath + productFamily + imageExt + "'  alt='" + productFamily + "'/>");
                    ps.println("                                                        <br/><b><font style=\"font-size: smaller; font-family: Arial ;color: #2862B3\">" + productFamily + "</font></b></a></td>");
                    //ps.println("                                                </tr>");
                    /*ps.println("                                                <tr><td align=\"center\"  class=\"links_txt4\">");
                    ps.println("                                                      ");
                    ps.println("                                                         <table  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
                    ps.println("  <tr>");
                    ps.println("    <td ><img src=\"" + imagesURL + "tabeLayout_3/Txt_Bar_left.gif\" width=\"9\" height=\"20\"></td>");
                    ps.println("    <td class=\"links_txt4\" style=\"background-image:url(" + imagesURL + "tabeLayout_3/Txt_Bar_center.gif); background-repeat:repeat-x; \">");
                    ps.println("       <a href='View_EngineModel?engineSeries=" + productFamily + "'>");
                    ps.println("        "+productFamily+"");
                    ps.println("          </a>");
                    ps.println("    </td>");
                    ps.println("    <td      align=\"right\" valign=\"top\"><img src=\"" + imagesURL + "tabeLayout_3/Txt_Bar_right.gif\" width=\"9\" height=\"20\"></td>");
                    ps.println("  </tr>");
                    ps.println("</table>");
                    ps.println(" </td></tr>");
                     */
                    //ps.println(" </table></td>");
                    ps.println("</td>");
                    columnCounter++;
                    if (columnCounter == noOfColumn) {
                        ps.println("</tr>");
                        columnFlag = true;
                        columnCounter = 0;

                    }
                }

                if (!columnFlag) {
                    ps.println("</tr>");
                }
                ps.println("                            </tbody>");
                ps.println("                        </table>");
//Footer

                ps.println(object_pageTemplate.tableFooter());

                //changes_01_02_2012
            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE",ComUtils.getLanguageTranslation("label.catalogue.sessionExpired", session), "YES", ComUtils.getLanguageTranslation("label.catalogue.loginAgain", session), "Index", "", imagesURL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ps.println(e);
        } finally {
            ps.close();
            wps.close();
            session_id = null;
            getSession = null;
            server_name = null;
            ecatPATH = null;
            mainURL = null;
            holder = null;
            imagesURL = null;
            loginAgain = null;
            orderRefNoString = null;
            imagePath = null;
            imageExt = null;
            engineSeries = null;
        }
    }
}
