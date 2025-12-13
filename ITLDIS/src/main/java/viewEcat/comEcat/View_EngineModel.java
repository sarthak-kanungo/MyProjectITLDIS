package viewEcat.comEcat;

/*
File Name: ViewEngineModel.java
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
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class View_EngineModel extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
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
        String engineSeries = null;
        String imagePath = null;
        Connection conn = null;
        int imageWidth = 0;
        int imageHeight = 0;
        String imageExt = null;
        ArrayList<String> engineModel = null;
        //ArrayList<String> applicationType = null;
       // Statement stmt = null;
        ResultSet rs = null, rs1 = null;
        //String engineModelV = null;
        String imageURL = null;
        String applicationTypSubQuery = null;
        String query = null;
        String date_OR_serial = null;
        String modelNo = null;
        String productFamilySubQuery = null;
        String subQuery = "";
        String message = null;
        PreparedStatement stmt = null;
        try
        {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            session_id = session.getId();

            getSession = (String) session.getValue("session_value");
            server_name = (String) session.getValue("server_name");
            ecatPATH = (String) session.getValue("ecatPATH");
            mainURL = (String) session.getValue("mainURL");
            holder = (ConnectionHolder) session.getValue("servletapp.connection");
            applicationTypSubQuery = "" + session.getAttribute("applicationTypSubQuery");
            productFamilySubQuery = "" + session.getAttribute("productFamilySubQuery");
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

            engineModel = new ArrayList<String>();
            engineSeries = req.getParameter("engineSeries");
            imagePath = object_pageTemplate.ecat_EngineModel_imagesURL();
            imageURL = object_pageTemplate.imagesURL();
            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////
            //ps.println(header);

            if (session_id.equals(getSession))
            {
                conn = holder.getConnection();

                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                //Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                //
                //

                /*########################################*/
                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));


                //stmt = conn.createStatement();
                if(productFamilySubQuery.equals(""))
                {
                query = "select distinct ENGINE_MODEL from CAT_MODEL_CLASSIFICATION(NOLOCK) MC,CAT_MODELS(NOLOCK) M where M.MODEL_NO=MC.MODEL_NO  ";
                }
                else
                {
                 query = "select distinct ENGINE_MODEL from CAT_MODEL_CLASSIFICATION(NOLOCK) MC,CAT_MODELS(NOLOCK) M "+productFamilySubQuery+" and M.MODEL_NO=MC.MODEL_NO  ";

                }
                if (date_OR_serial.equals("serialNo"))
                {
//                    if (productFamilySubQuery.equals(""))
//                    {
//                        subQuery = " where  M.MODEL_NO='" + modelNo + "'";
//                    }
//                    else
                   // {
                        subQuery = " AND  M.MODEL_NO='" + modelNo + "'";
                    //}
                }
                else
                {
//                    if (productFamilySubQuery.equals(""))
//                    {
//                        subQuery = " where ENGINE_SERIES='" + engineSeries + "'";
//                    }
                   // else
                   // {
                        subQuery = " AND ENGINE_SERIES='" + engineSeries + "'";
                  //  }
                }

                if (userFunctionalities.contains("36"))
                {
                   // rs = stmt.executeQuery(query + subQuery + " AND M.STATUS='COMPLETE' " + applicationTypSubQuery);
               String sqlquery = (query + subQuery + " AND M.STATUS='COMPLETE' " + applicationTypSubQuery);
               stmt = conn.prepareStatement(sqlquery);
               rs = stmt.executeQuery();
                }
                else
                {
                  //rs = stmt.executeQuery(query + subQuery + " " + applicationTypSubQuery);
                	String sqlquery1 = (query + subQuery + " " + applicationTypSubQuery);
                	stmt = conn.prepareStatement(sqlquery1);
                	rs = stmt.executeQuery();
                }
                //System.out.println("select distinct ENGINE_MODEL from MODEL_CLASSIFICATION  where ENGINE_SERIES='"+engineSeries+"' "+applicationTypSubQuery);
                // System.out.println(applicationTypSubQuery);
                while (rs.next())
                {
                    engineModel.add(rs.getString(1));
                }
                rs.close();

                imageWidth = PageTemplate.engineModelImageWidth;
                imageHeight = PageTemplate.engineModelImageHeight;
                //pstmt.close();

//                rs = stmt.executeQuery("select Width,Height,ImageExt  from IMAGE_REFERENCE where ImageType='ENGINE MODEL'");
//
//                if (rs.next())
//                {
//                    imageWidth = rs.getString(1);
//                    imageHeight = rs.getString(2);
//                    imageExt = rs.getString(3);
//                }
                stmt.close();
                ps.println("<html>");
                ps.println("   <head>");
                ps.println("<link href=\"" + imageURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
//                ps.println("			<style type=text/css>");
//                ps.println("			<!--");
//                ps.println("			* { font-family: Helvetica;}");
//                ps.println("			a:link {				color: #990000;				text-decoration: none; }");
//                ps.println("			a:visited { color: #990000;				text-decoration: none;		  }	");
//                ps.println("			-->");
//                ps.println("			</style>");
                ps.println("</head>");

                ps.println("<body topmargin=0  marginheight=0 leftmargin=0  marginwidth=0>");
                //changes_01_02_2012
                //header
                int width = 968, height = 450;

                String tdData = "<a href='View_EngineSeries'>" + ComUtils.getLanguageTranslation("label.catalogue.product", session).toUpperCase() + "</a> >> " + engineSeries.toUpperCase() + " >> ";


                object_pageTemplate.pageLink(ps, width, height, tdData);


                if (engineModel.size() == 0)
                {
                    message = ComUtils.getLanguageTranslation("label.catalogue.noModelExist", session)+" For \"" + engineSeries + "\"";
                    ps.println(object_pageTemplate.tableHeader1(message, width, height));
                    //object_pageTemplate.ShowMsg(ps, "FAILURE", "No  " + partNoPrint + " DOES NOT EXIST", "YES", "back", "javascript:history.go(-2);", "", imagesURL);
                    return;
                }

                ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.productAvailable", session)+" \""+ engineSeries + "\"", width, height));

                //ps.println(" <table width=100% border=0 align=\"center\" cellspacing=0 cellpadding=0 bordercolor = #CCCCCC><tr><td valign=top>");
                ps.println("<div style=\"float: left; width: 950px; height: 400px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                //ps.println("<div class=\"maindiv\" style=\"overflow-X:hidden; overflow-Y: scroll; height:380;\">");//380

                ps.println(" <table width=90% border=0 align=\"center\" cellspacing=1 cellpadding=2 bordercolor = #CCCCCC style=padding-top:0px>");
                ps.println("    <tr>                       ");
                ps.println("              ");

                int noOfColumn = 3;
                int columnCounter = 0;
                boolean columnFlag = false;
                imageExt = PageTemplate.engineSeriesImageExt;


                for (String engineModels : engineModel)
                {

                    if (columnFlag)
                    {
                        ps.println("  <tr>  ");
                        ps.println(" ");
                        columnFlag = false;
                    }

                    ps.println("          <td  align=\"center\">");
                    //ps.println("                                            <table cellspacing=0 cellpadding=0><tr><td align=\"center\">");
                    ps.println("<a  href=\"MainModels?engineModel=" + engineModels + "&engineSeries=" + engineSeries + "\" style=\"text-decoration: none\">");
                    ps.println("<IMG SRC='" + imagePath + engineModels + imageExt + "' width =" + imageWidth + "  height = " + imageHeight + "  border = 0 bordercolor = #000000 alt='"+engineModels+"'>");
                    ps.println("                                                        <br/><b><font style=\"font-size: smaller; font-family: Arial ;color: #2862B3\">" + engineModels + "</font></b></a></td>");
                    //ps.println("                                                        </a>");
                  //ps.println("                                               </td> </tr>");
                    /*ps.println("                                                <tr><td align=\"center\"  class=\"links_txt4\">");
                    ps.println("                                                      ");
                    ps.println("                                                         <table  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
                    ps.println("  <tr>");
                    ps.println("    <td ><img src=\"" + imagesURL + "tabeLayout_3/Txt_Bar_left.gif\" width=\"9\" height=\"20\"></td>");
                    ps.println("    <td class=\"links_txt4\" style=\"background-image:url(" + imagesURL + "tabeLayout_3/Txt_Bar_center.gif); background-repeat:repeat-x; \">");
                    ps.println("<a  href=\"MainModels?engineModel=" + engineModels + "&engineSeries=" + engineSeries + "\">");
                    ps.println("        "+engineModels+"");
                    ps.println("          </a>");
                    ps.println("    </td>");
                    ps.println("    <td      align=\"right\" valign=\"top\"><img src=\"" + imagesURL + "tabeLayout_3/Txt_Bar_right.gif\" width=\"9\" height=\"20\"></td>");
                    ps.println("  </tr>");
                    ps.println("</table>");
                    ps.println(" </td></tr>");
                     */
                    //ps.println("</table>>");
                    //ps.println("</td>");

                    columnCounter++;
                    if (columnCounter == noOfColumn)
                    {
                        ps.println("</tr>");
                        columnFlag = true;
                        columnCounter = 0;

                    }

                }

                if (!columnFlag)
                {
                    ps.println("</tr>");
                }

                //  ps.println("                            </tbody>");
                ps.println("                        </table>");
                ps.println("</div>");
                //ps.println("</td></tr></table>");
//Footer

                ps.println(object_pageTemplate.tableFooter());

                //changes_01_02_2012
            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", ComUtils.getLanguageTranslation("label.catalogue.sessionExpired", session), "YES", ComUtils.getLanguageTranslation("label.catalogue.loginAgain", session), "Index", "", imagesURL);
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
            session_id = null;
            getSession = null;
            server_name = null;
            ecatPATH = null;
            mainURL = null;
            holder = null;
            imagesURL = null;
            loginAgain = null;
            engineSeries = null;
            imagePath = null;
            conn = null;
            imageExt = null;
            engineModel = null;
            imageURL = null;
        }
    }
}
