/*PURPOSE: it is for display model report.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA		v3.4		Ajay Kumar Barik		$$0 Created
 */
package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import authEcat.UtilityMapkeys1;
/**
 *
 * @author ajay.barik
 */
public class ModelReport extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {

        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");

       // Statement stmt_view = null;
        PreparedStatement stmt_view = null;
        ResultSet rs = null;
        Connection conn_cat = null;

        try
        {
            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String getType = (String) session.getValue("user_type");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            int tableWidth = 780;
            int tableHeight_1 = 365;

            int width = Integer.parseInt((String) session.getValue("screenWidth"));

            if (width == 800)
            {
                tableWidth = 700;
                tableHeight_1 = 280;
            }

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String header = "";
            header = object_pageTemplate.header(getType);

            String footer = "";
            footer = object_pageTemplate.footer();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String imagesURL = object_pageTemplate.imagesURL();

            if (!session_id.equals(getSession))
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
                return;
            }

            ps.println("<center><table id=loading cellspacing=0 cellpadding=0 border=0 height=80% width=90%>");
            ps.println("<tr>");
            ps.println("<td valign = middle>");
            ps.println("<table cellspacing=0 cellpadding=10 border=0 width=100% bordercolor=#D7D7D7>");
            ps.println("<tr>");
            ps.println("<td align = center>");
            ps.println("<font color = #000099><b>");
            ps.println("<img src='" + imagesURL + "loading.gif'><br>Loading....");
            ps.println("</td>");
            ps.println("</tr>");
            ps.println("</table>");
            ps.println("</td>");
            ps.println("</tr>");
            ps.println("</table>");

            /////////////////////////////////////////////////////////////

            ////////////////////BACKEND CONNECTIVITY/////////////////////////

            conn_cat = holder.getConnection();
           // stmt_view = conn_cat.createStatement();
            List modelnoList = new ArrayList();
            //Inventory_util eutil = new Inventory_util(conn_cat);

            Hashtable enggseriesHTab = new Hashtable();
            //Retrive Model Segment
            //rs = stmt_view.executeQuery("select MODEL_NO,ENGINE_SERIES from CAT_MODEL_CLASSIFICATION");
            String query = ("select MODEL_NO,ENGINE_SERIES from CAT_MODEL_CLASSIFICATION(NOLOCK)");
            stmt_view = conn_cat.prepareStatement(query);
            rs = stmt_view.executeQuery();
            while (rs.next())
            {
                enggseriesHTab.put(rs.getString(1), rs.getString(2));
            }
            rs.close();

            Hashtable bannHTab = new Hashtable();
            //Retrive Model Segment
            //rs = stmt_view.executeQuery("select SO_NO, count(SO_NO) from CAT_VEHICLE_SONO(NOLOCK) group by SO_NO");
            String sql = ("select SO_NO, count(SO_NO) from CAT_VEHICLE_SONO(NOLOCK) group by SO_NO");
            stmt_view = conn_cat.prepareStatement(sql);
            rs = stmt_view.executeQuery();
            while (rs.next())
            {
                bannHTab.put(rs.getString(1), rs.getString(2));
            }
            rs.close();

            Hashtable ecatHTab = new Hashtable();
            
           // rs = stmt_view.executeQuery("select MODEL_NO,DESCRIPTION,STATUS from CAT_MODELS(NOLOCK) order by MODEL_NO");
            String sqlQuery = ("select MODEL_NO,DESCRIPTION,STATUS from CAT_MODELS(NOLOCK) order by MODEL_NO");
            stmt_view = conn_cat.prepareStatement(sqlQuery);
            rs = stmt_view.executeQuery();
            while (rs.next())
            {
               ecatHTab.put(rs.getString(1), rs.getString(2) + "@@" + rs.getString(3));
            }
            rs.close();

            //Retrive model number and so no
            //rs = stmt_view.executeQuery("(select distinct MODEL_NO from CAT_MODELS(NOLOCK)) UNION (select distinct SO_NO from CAT_VEHICLE_SONO(NOLOCK))");
            String sqlQuery1 = ("(select distinct MODEL_NO from CAT_MODELS(NOLOCK)) UNION (select distinct SO_NO from CAT_VEHICLE_SONO(NOLOCK))");
            stmt_view = conn_cat.prepareStatement(sqlQuery1);
            rs = stmt_view.executeQuery();
            while (rs.next())
            {
                modelnoList.add(rs.getString(1));
            }
            rs.close();

//            if ((modelnoList == null) || modelnoList.size() == 0)
//            {
//                ps.println(header);
//                eutil.ShowMsg(ps, "FAILURE", "No record exists.", "", "", "", "", imagesURL);
//                return;
//            }
            //////////////////PRINTING STARTS/////////////////////

            ///////////////////////////PRINTING/////////////////////
            String hdr = "<html>" + "<head>" + "<title>" +UtilityMapkeys1.tile+"</title><meta http-equiv=Content-Type content=text/html; charset=iso-8859-1>" + "	<style type=text/css>" + "<!--" + "			body" + "		{" //		+	"				scrollbar-face-color: #cccccc; scrollbar-arrow-color: #000000; scrollbar-track-color: #E2EEFA;"
                    + "		}" + "* { font-family: Helvetica; font-size: 9pt; }" + "		-->" + "</style>" + "</head>";
            ps.println(hdr);
            ps.println("<script language=\"JavaScript\" src='" + imagesURL + "js/jquery-latest.js'></script>");
            ps.println("<script language=\"JavaScript\" src='" + imagesURL + "js/jquery.tablesorter.js'></script>");
            //Import Masking Pop Up css file (Start)
            ps.println("<link href='" + imagesURL + "css/Ei_7.css' rel='stylesheet' type='text/css' />");
            ps.println("<style type='text/css'>@import '" + imagesURL + "css/Ei_6.css'; ");

            ps.println(".tt{");
            ps.println("position:relative;");
            ps.println(" z-index:24;");
            ps.println("  color:#000;");
            //ps.println("font-weight:bold;");
            ps.println("text-decoration:none;");
            ps.println("}");
            ps.println(" .tt span{ display: none; }");


            ps.println(".tt:hover{ z-index:25; color: #aaaaff; background:;}");
            ps.println(".tt:hover span.tooltip{");
            ps.println("display:block;");
            ps.println("position:absolute;");
            ps.println("top:0px; left:0;");
            ps.println("padding: 15px 0 0 0;");
            ps.println("width:200px;");
            ps.println("color: #993300;");
            ps.println("font-weight:bold;");
            ps.println("text-align: center;");
            ps.println("filter: alpha(opacity:90);");
            ps.println("KHTMLOpacity: 0.90;");
            ps.println("MozOpacity: 0.90;");
            ps.println("opacity: 0.90;");
            ps.println("}");
            ps.println(".tt:hover span.top{");
            ps.println("display: block;");
            ps.println("padding: 30px 8px 0;");
            ps.println("background: url(" + imagesURL + "bubble.gif) no-repeat top;");
            ps.println("}");
            ps.println(".tt:hover span.middle{");
            ps.println("display: block;");
            ps.println("padding: 0 8px;");
            ps.println("background: url(" + imagesURL + "bubble_filler.gif) repeat bottom;");
            ps.println("}");
            ps.println(".tt:hover span.bottom{");
            ps.println("display: block;");
            ps.println("padding:3px 8px 10px;");
            ps.println("color: #548912;");
            ps.println("background: url(" + imagesURL + "bubble.gif) no-repeat bottom;");
            ps.println("}");



            ps.println("</style>");
            //Import Masking Pop Up css file (End)
            ps.println("<style>.hd{cursor:pointer; }.greenalink{text-decoration:none;color:green;}.redalink{text-decoration:none;color:red;}</style>");

            ps.println("<script>");
            //js for sorting (Start)
            ps.println("var up='up.gif';");
            ps.println("var dn='dn.gif';");
            ps.println("var srcc='';");
            ps.println("var prev_id='';");
            ps.println("function chngSortingIcon(objj)");
            ps.println("{ ");
            ps.println("srcc=objj.src.substring(objj.src.lastIndexOf('/')+1,objj.src.length);");
            ps.println("if(srcc==up)");
            ps.println("objj.src='" + imagesURL + "'+dn;");
            ps.println("if(srcc==dn)");
            ps.println("objj.src='" + imagesURL + "'+up;");
            ps.println("if(prev_id!=objj)");
            ps.println("prev_id.src='" + imagesURL + "'+dn;");
            ps.println("prev_id=objj;");
            ps.println("}");
            //js for sorting (End)

            //Js for Masking Pop Up (Start)
            ps.println("function openPopup(sts,modno)");
            ps.println("{");
            ps.println("document.getElementById('modal').style.display='';");
            ps.println("document.getElementById('mask').style.display='';");
            ps.println("document.getElementById('chngstsIFrame').src = 'UpdateModelStatus?sts='+sts+'&modno='+modno;");
            ps.println("}");

            ps.println("function closePopup()");
            ps.println("{");
            ps.println("document.getElementById('modal').style.display='none';");
            ps.println("document.getElementById('mask').style.display='none';");
            ps.println("document.location.href = 'ModelReport';");
            ps.println("}");
            //Js for Masking Pop Up (End)
            ps.println("</script>");

            ps.println("<body >");

            ps.println("<table id=mainTable style=display:none width=" + tableWidth + " border=1 cellspacing=0 cellpadding=5 bordercolor=#CCCCCC >");
            ps.println("<tr>");
            ps.println("<td align=center bgcolor=#003399 colspan=7>");
            ps.println("<font face=Helvetica size=4 color=#ffffff>");
            ps.println("<b> Models Report");
            ps.println("</font>");
            ps.println("</td>");
            ps.println("</tr>");

            ps.println("<tr>");
            ps.println("<td width=8% class=hd align=center valign=middle bgcolor=#003399 onclick= document.getElementById('sno').click(); >");
            ps.println("<font face=Helvetica size=4 color=#ffffff>");
            ps.println("<b>S No. <img src = " + imagesURL + "dn.gif border = 0 height=16 width=16px  id=chkImg1 onclick=chngSortingIcon(this);>");
            ps.println("</font>");
            ps.println("</td>");

            ps.println("<td width=22% class=hd align=center valign=middle bgcolor=#003399 onclick= document.getElementById('ModelNo').click(); >");
            ps.println("<font face=Helvetica size=4 color=#ffffff>");
            ps.println("<b>Model No. <img src = " + imagesURL + "dn.gif border = 0 height=16 width=16px  id=chkImg2 onclick= document.getElementById('ModelNo').click();chngSortingIcon(this);>");
            ps.println("</font>");
            ps.println("</td>");

            /*ps.println("<td width=24% class=hd align=center valign=middle bgcolor=#003399 onclick= document.getElementById('Description').click();  >");
            ps.println("<font face=Helvetica size=4 color=#ffffff>");
            ps.println("<b>Description  <img src = " + imagesURL + "dn.gif border = 0 height=16 width=16px  id=chkImg3 onclick=chngSortingIcon(this);>");
            ps.println("</font>");
            ps.println("</td>");*/

            ps.println("<td width=18% class=hd align=center valign=middle bgcolor=#003399 onclick= document.getElementById('ExistsinECAT').click(); >");
            ps.println("<font face=Helvetica size=4 color=#ffffff>");
            ps.println("<b>Exists in ECAT  <img src = " + imagesURL + "dn.gif border = 0 height=16 width=16px  id=chkImg4 onclick=chngSortingIcon(this);>");
            ps.println("</font>");
            ps.println("</td>");

            ps.println("<td width=18% class=hd align=center valign=middle bgcolor=#003399 onclick= document.getElementById('ExistsinBAAN').click(); >");
            ps.println("<font face=Helvetica size=4 color=#ffffff>");
            ps.println("<b>No. of Tractors  <img src = " + imagesURL + "dn.gif border = 0 height=16 width=16px  id=chkImg5 onclick=chngSortingIcon(this);>");
            ps.println("</font>");
            ps.println("</td>");

            ps.println("<td width=18% class=hd align=center valign=middle bgcolor=#003399 onclick= document.getElementById('Status').click(); >");
            ps.println("<font face=Helvetica size=4 color=#ffffff>");
            ps.println("<b>Status  <img src = " + imagesURL + "dn.gif border = 0 height=16 width=16px  id=chkImg6 onclick=chngSortingIcon(this);>");
            ps.println("</font>");
            ps.println("</td>");

            ps.println("<td width=18% class=hd align=center valign=middle bgcolor=#003399 onclick= document.getElementById('ModelSegment').click(); >");
            ps.println("<font face=Helvetica size=4 color=#ffffff>");
            ps.println("<b>Model Segment  <img src = " + imagesURL + "dn.gif border = 0 height=16 width=16px  id=chkImg7 onclick=chngSortingIcon(this);>");
            ps.println("</font>");
            ps.println("</td>");

            ps.println("</tr>");

            ps.println("</table>");


            ps.println("<div id=maindiv class=\"maindiv\" STYLE=\"display:none; overflow-X:hidden; overflow-y: scroll; height:" + tableHeight_1 + ";\">");
            ps.println("<table id=myTable cellspacing=0 cellpadding=1 border=1 width=" + tableWidth + " bordercolor=#D7D7D7>");

            ps.println("<thead>");
            ps.println("<tr style='display:none;'>");
            ps.println("<th id='sno'>S No.</th>");
            ps.println("<th id='ModelNo'>Model No</th>");
            // ps.println("<th id='Description'>Description</th>");
            ps.println("<th id='ExistsinECAT'>Exists in ECAT</th>");
            ps.println("<th id='ExistsinBAAN'>No. of Tractors</th>");
            ps.println("<th id='Status'>Status</th>");
            ps.println("<th id='ModelSegment' >Model Segment</th>");
            ps.println("</tr>");
            ps.println("</thead>");


            ps.println("<tbody>");
            
            String[] modelArr = new String[2];
            for (int i = 0; i < modelnoList.size(); i = i + 1)
            {
                if (((i / 1) % 2) == 0)
                {
                    ps.println("<tr bgcolor='#D4E7FC'>");
                }
                else
                {
                    ps.println("<tr>");
                }

                //Retrive Model Data          
                if (ecatHTab.containsKey(modelnoList.get(i)))
                {
                    modelArr = ecatHTab.get(modelnoList.get(i)).toString().split("@@");
                }
                else
                {
                   modelArr[0]="-";
                   modelArr[1]="-";
                }

                ps.println("<td width=8% align=center valign=middle >");
                ps.println("<font face=Helvetica size=4 color=#000000>");
                ps.println((i / 1) + 1); //S No.

                ps.println("</font>");
                ps.println("</td>");

                ps.println("<td width=22% align=left valign=middle >");
                ps.println("<font face=Helvetica size=4 color=#000000>");
                ps.println("<a href='#' class='tt' alt='Description'>" + modelnoList.get(i) + "<span class='tooltip'><span class='top'></span>");
                ps.println("<span class='middle'> " + modelArr[0] + " </span>");
                ps.println("<span class='bottom'></span></span></a>");
                //ps.println(modelnoList.get(i)); //Model No.
                ps.println("</font>");
                ps.println("</td>");

                /*ps.println("<td width=24% align=left valign=middle >");
                ps.println("<font face=Helvetica size=4 color=#000000>");
                ps.println(DESCRIPTION); //Description
                ps.println("</font>");<b>Description</b><br>
                ps.println("</td>");*/

                ps.println("<td width=18% align=center valign=middle >");
               if (ecatHTab.containsKey(modelnoList.get(i)))
                {
                    ps.println("<font face=Helvetica size=4 color=green>");
                    ps.println("Yes");   //Exists in BAAN
                }
                else
                {
                    ps.println("<font face=Helvetica size=4 color=red>");
                    ps.println("No");   //Exists in BAAN
                }

                ps.println("</td>");


                ps.println("<td width=18% align=center valign=middle >");
                if (bannHTab.containsKey(modelnoList.get(i)))
                {
                    ps.println("<font face=Helvetica size=4 color=green>");
                    ps.println(bannHTab.get(modelnoList.get(i)));   //Exists in BAAN
                }
                else
                {
                    ps.println("<font face=Helvetica size=4 color=red>");
                    ps.println("0");   //Exists in BAAN
                }
                ps.println("</font>");
                ps.println("</td>");

                ps.println("<td width=18% align=left valign=middle >");
                if (modelArr[1].equals("COMPLETE"))
                {
                    ps.println(" <a class=greenalink href=# onclick=openPopup('" + modelArr[1] + "','" + modelnoList.get(i) + "')>");
                    ps.println(modelArr[1]);  //Status
                    ps.println("</a> ");
                }
                else
                {
                    ps.println(" <a class=redalink href=# onclick=openPopup('" + modelArr[1] + "','" + modelnoList.get(i) + "')>");
                    ps.println(modelArr[1]);  //Status
                    ps.println("</a> ");
                }
                ps.println("</td>");

                ps.println("<td width=18% align=center valign=middle >");
                if (enggseriesHTab.get(modelnoList.get(i)) != null)
                {
                    ps.println(enggseriesHTab.get(modelnoList.get(i)));  //Model Segment

                }
                else
                {
                    ps.println("-");  //Model Segment

                }
                ps.println("</font>");
                ps.println("</td>");


                ps.println("</tr>");
            }
            ps.println("</tbody>");
            ps.println("</table>");
            ps.println("</div>");

            //Content for Masking Pop Up (Start)
            ps.println("<div class='progressBackgroundFilter' id='modal' style='display:none'></div>");
            ps.println("<div id='mask' style='display:none'  class='processMessage'>");
            ps.println("<table width=610 height=219 border=1 cellspacing=0 cellpadding=5 >");
            ps.println("<tr>");
            ps.println("<td align=right>");
            ps.println("<a href='#' onclick='closePopup()'>");
            ps.println("<img src = " + imagesURL + "close_.gif border = 0 alt = Close></a>");
            ps.println("</td>");
            ps.println("</tr>");
            ps.println("<tr>");
            ps.println("<td height=219>");
            ps.println("<iframe src='#' id='chngstsIFrame' width=610 height=219 frameborder='0' scrolling='no'></iframe>");
            ps.println("</td>");
            ps.println("</tr>");
            ps.println("</table>");
            ps.println("</div>");
            //Content for Masking Pop Up (End)


            ps.println("<table id=myTable cellspacing=0 cellpadding=1 border=0 width=" + tableWidth + " bordercolor=#D7D7D7>");
            ps.println("<tr>");
            ps.println("<td>* Move your mouse on Model No. to get the Model Description</td>");
            ps.println("</tr>");
            ps.println("<tr><td>** Click On Model Status to change the status.</td>");
            ps.println("</tr>");
            ps.println("</table>");

            ps.println(footer);

            ps.println("<script type='text/javascript'>");
            ps.println("$(document).ready(function() ");
            ps.println("{ ");
            ps.println("$('#myTable').tablesorter( {sortList: [[0,0], [1,0]]} );");
            ps.println("} ");
            ps.println(");");
            ps.println("</script>");

            ps.println("<script>");
            ps.println(" document.getElementById('loading').style.display='none';");
            ps.println(" document.getElementById('mainTable').style.display='';");
            ps.println(" document.getElementById('maindiv').style.display='';");
            ps.println("</script>");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close
            try
            {
                stmt_view.close();
            }
            catch (Exception e)
            {
              //  System.out.println("Error  : " + e);
            }
            ps.close();
            wps.close();

        }

    }
}
