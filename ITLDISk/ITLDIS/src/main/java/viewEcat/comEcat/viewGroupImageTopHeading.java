package viewEcat.comEcat;

/*
File Name: ViewModel.java
PURPOSE: It shows the list view of groups in a particular model.
HISTORY:
DATE		BUILD	 AUTHOR				MODIFICATIONS
NA			v3.4	 Deepak Mangal		$$0 Created
 */
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class viewGroupImageTopHeading extends HttpServlet
{

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        String message = null;
        ResultSet rs = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        try
        {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String getType = (String) session.getValue("user_type");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            Vector userFunctionalities = (Vector) session.getValue("userFun");
            String mainURL = (String) session.getValue("mainURL");
            String screenWidth = (String) session.getValue("screenWidth");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            String date_OR_serial = (String) session.getValue("date_OR_serial");


            ///////////////////////////// CREATE SESSION /////////////////////////////

            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////


            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String modelImage = "";
            modelImage = object_pageTemplate.modelImage();

            String orderRefNoString = "";
            orderRefNoString = object_pageTemplate.orderRefNoString();

            String animURL = "";
            animURL = object_pageTemplate.animURL();




            String model = req.getParameter("model");
            String engineSeries = req.getParameter("engineSeries");
            String engineModel = req.getParameter("engineModel");
            String aggregate = req.getParameter("aggregate");
            String partSearch = req.getParameter("partSearch");
            String group = req.getParameter("group");
            Vector groupDescription = (Vector) session.getAttribute("groupDescription");

            if (groupDescription == null)
            {
                groupDescription = new Vector();
            }

            if (!((aggregate == null) || aggregate.equals("") || aggregate.equalsIgnoreCase("null")))
            {
                aggregate = aggregate.replaceAll("_", " ");
                aggregate = aggregate.replaceAll("@@", "&");
            }
            else
            {
                aggregate = "";
            }


            String groupMap = "";
            String groupDesc = "";
            //stmt = holder.getConnection().createStatement();
            

            String grp = "";
            String grpD = "";
            String grpM = "";

            if(!groupDescription.isEmpty() && groupDescription.contains(group))
            {
                //rs = stmt.executeQuery("SELECT MAP_NAME FROM CAT_MODEL_GROUP WHERE GROUP_NO = '" + group + "' and MODEL_NO='" + model + "' ");
                String query = ("SELECT MAP_NAME FROM CAT_MODEL_GROUP(NOLOCK) WHERE GROUP_NO = '" + group + "' and MODEL_NO='" + model + "' ");
                stmt = holder.getConnection().prepareStatement(query);
                rs = stmt.executeQuery();
            	if (rs.next())
                {
                    groupMap = rs.getString(1);
                }
                rs.close();


                //rs = stmt.executeQuery("SELECT P1,REMARKS FROM CAT_GROUP_KIT_DETAIL WHERE GRP_KIT_NO = '" + group + "'");
               String query1 = ("SELECT P1,REMARKS FROM CAT_GROUP_KIT_DETAIL(NOLOCK) WHERE GRP_KIT_NO = '" + group + "'");
               stmt = holder.getConnection().prepareStatement(query1);
               rs = stmt.executeQuery();
               if (rs.next())
                {
                    groupDesc = rs.getString(1);
                    // groupRemark = rs1.getString(2);
                }
                rs.close();
            }
            else
            {
                //rs = stmt.executeQuery("SELECT MG.GROUP_NO,MG.MAP_NAME, GKD.P1 FROM CAT_MODEL_GROUP MG, CAT_GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + model + "' AND MG.GROUP_NO = GKD.GRP_KIT_NO ORDER BY GKD.P1");
            	String sql = ("SELECT MG.GROUP_NO,MG.MAP_NAME, GKD.P1 FROM CAT_MODEL_GROUP(NOLOCK) MG, CAT_GROUP_KIT_DETAIL(NOLOCK) GKD WHERE MG.MODEL_NO = '" + model + "' AND MG.GROUP_NO = GKD.GRP_KIT_NO ORDER BY GKD.P1");
            	stmt = holder.getConnection().prepareStatement(sql);
            	rs = stmt.executeQuery();
            	//System.out.println("SELECT MG.GROUP_NO,MG.MAP_NAME, GKD.P1 FROM " + tableName_MG + " MG, GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + model + "' AND MG.GROUP_TYPE = '" + groupTypes.elementAt(i) + "'  AND MG.GROUP_NO = GKD.GRP_KIT_NO ORDER BY GKD.GK_TYPE,GKD.P1");
                while (rs.next())
                {
                    grp = rs.getString(1);
                    grpM = rs.getString(2);
                    grpD = rs.getString(3);

                    if (grp.equalsIgnoreCase(group))
                    {
                        groupDesc = grpD;
                        groupMap = grpM;
                    }
                    groupDescription.add(grpM);
                    groupDescription.add(grp);
                    groupDescription.add(grpD);
                }
                rs.close();
            }
            
            stmt.close();

            // String modelDescription = "";
            ps.println("<html><head>");
            ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");

            ps.println("<script language=JavaScript type=text/JavaScript>");

            //new changes_manish_06_02_2012
            ps.println("	function MM_openBrWindow(theURL,winName,features)");
            ps.println("		{ ");
            ps.println("			var isValidPopUpBlocker=detectPopupBlocker()");
            ps.println("			if (isValidPopUpBlocker== false)");
            ps.println("			{");
            ps.println("				return");
            ps.println("			}");
            ps.println("			var win=window.open(theURL,winName,features);");
            ps.println("                    win.focus();");
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

            ps.println("function changeGroupImage(obj)");
            ps.println("{");
            ps.println("var group=obj.value;");
            ps.println("parent.document.all(\"right_middle\").src=\"ViewGroupImages?aggregate=" + aggregate + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=\"+group");
            ps.println("parent.document.all(\"right_main\").src=\"Group_details_only?aggregate=" + aggregate + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=\"+group+\"&model=" + model + "&partSearch=" + partSearch + "\"");
            ps.println("parent.document.all(\"top\").src=\"viewGroupImageTopHeading?aggregate=" + aggregate + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=\"+group+\"&model=" + model + "&partSearch=" + partSearch + "\";");
            ps.println("parent.document.all(\"middle\").src=\"\";");
            ps.println("}");
            ps.println("</script>");



            ps.println("</head>");


            ps.println("<body topmargin=0  marginheight=0 leftmargin=0  marginwidth=0>");

            String groupLink = "", highGroup = "";
            String tdData = "<a target=\"_parent\" target=\"_parent\" href='View_EngineSeries'>" + PageTemplate.ENGINE_SERIES.toUpperCase() + "</a> >> ";
            if ((engineSeries != null) && !engineSeries.equalsIgnoreCase("null"))
            {
                tdData += "<a target=\"_parent\" href='View_EngineModel?engineSeries=" + engineSeries + "'>" + engineSeries.toUpperCase() + "</a> >> ";
            }
            if ((engineModel != null) && !engineModel.equalsIgnoreCase("null"))
            {
                tdData += "<a target=\"_parent\" href=\"MainModels?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "\">" + engineModel.toUpperCase() + "</a> >> ";
            }
            if ((model != null) && !model.equalsIgnoreCase("null"))
            {
                tdData += "<a target=\"_parent\" href=\"ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "\">" + model.toUpperCase() + "</a> >> ";
            }
            if (aggregate != null && !aggregate.equals("") && !aggregate.equalsIgnoreCase("null"))
            {
                tdData += "<a target=\"_parent\" href=\"ViewModel_pictureView1?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&highGroup=" + highGroup + "&aggregate=" + aggregate + "\">" + aggregate.replaceAll("@@", "&").toUpperCase() + "</a> >> ";
                //tdData += "<a target=\"_parent\" href=\"ViewModel?aggregate=" + aggregate.replaceAll("&", "@@") + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "\">" + aggregate.replaceAll("@@", "&").toUpperCase() + "</a> >> ";
            }

            if (groupMap != null && !groupMap.equals("") && !groupMap.equalsIgnoreCase("null"))
            {
                //groupLink = "" + groupMap.toUpperCase() + " >>";
                tdData += "<select name=\"group\" onChange=\"changeGroupImage(this);\"  class=\"GroupSelect\">"; //style=\"width:250px\"

                for (int i = 0; i < groupDescription.size(); i = i + 3)
                {
                    if (group.equals("" + groupDescription.get(i + 1)))
                    {
                        tdData += "<option title=\"" + groupDescription.get(i + 2) + "\" value=\"" + groupDescription.get(i + 1) + "\" selected>" + groupDescription.get(i + 2) + "</option>";
                    }
                    else
                    {
                        tdData += "<option title=\"" + groupDescription.get(i + 2) + "\" value=\"" + groupDescription.get(i + 1) + "\" >" + groupDescription.get(i + 2) + "</option>";

                    }
                }
                tdData += "</select>";
            }

            int width = 970, height = 490;

            //tdData += groupLink;

            object_pageTemplate.pageLink(ps, width, height, tdData);

            ps.println("<center><TABLE width=980 cellpadding=0 cellspacing=0 border=0>");
            ps.println("<TR>");
            ps.println("	<TD width=800 class=\"heading-main\"><center>");
            ps.println("<b>" + groupDesc + "</b>");
            ps.println("	</TD>");
            ps.println("	<TD width=180 align=right>");

            ps.println("<table border=0 cellpadding=0 cellspacing=0>");//
            ps.println("<tr> ");



            if (userFunctionalities.contains("45"))
            {
                //ps.println("<td width=5% valign=middle>");
                ps.println("<td width=30 valign=middle >");
                ps.println("<a href = 'GroupPrintable?group=" + group + "&model=" + model + "' target = _blank>");
                ps.println("<img border=0 src = " + imagesURL + "PRINT-ICON.jpg alt='Printable View'>");
                //ps.println("Printable View");
                ps.println("</a>");
                ps.println("</td>");
            }




            if (date_OR_serial.equals("latest"))
            {
                if (userFunctionalities.contains("46"))
                {
                    {
                        //ps.println("<td valign  = middle>");
                        ps.println("<td width=30 valign=middle >");
                        if (orderRefNoString.equals("WEB"))
                        {
                            ps.println("<a href=javascript:MM_openBrWindow('" + animURL + group + ".html','DETAILS','width=1000,height=710')>");
                        }
                        else
                        {
                            ps.println("<a href=javascript:MM_openBrWindow('OpenIE?url=" + animURL + group + ".html','DETAILS','width=50,height=50')>");
                        }
                        ps.println("<img border=0 src = " + imagesURL + "group_3D.gif alt='3D View'></a></td>");
//								ps.println("3D View");
//								ps.println("</td>");
                    }
                }
            }



            if (date_OR_serial.equals("latest"))
            {
//                if (userFunctionalities.contains("50")) {
//                    //ps.println("<td valign=middle>");
//                    ps.println("<td width=30 valign=middle width=30>");
//                    if (orderRefNoString.equals("WEB")) {
//                        ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "GroupChmt?group=" + group + "&model=" + model + "','CDETAILS','width=950,height=510')>");
//                    } else {
//                        ps.println("<a href=javascript:MM_openBrWindow('OpenIE?url=" + servletURL + "GroupChmt?group=" + group + "&model=" + model + "','CDETAILS','width=50,height=50')>");
//                    }
//                    ps.println("<img border=0 src = " + imagesURL + "MODIFICATION-ICON.jpg alt='" + PageTemplate.ECN + "'>");
//                    // ps.println(PageTemplate.ECN + "");
//                    ps.println("</a>");
//                    ps.println("</td>");
//                }
            }
            if (date_OR_serial.equals("latest"))
            {
                if (userFunctionalities.contains("51"))
                {
                    //ps.println("<td valign=middle></td>");
                    ps.println("<td width=30 valign  = middle >");
                    ps.println("<a href = \"javascript:MM_openBrWindow('GroupWhereUsed?group=" + group + "','','width=625,height=300');\" >");//target = right_main
                    ps.println("<img src = '" + imagesURL + "group_where.gif'  border=0 alt='Where used'>");
                    //ps.println("Where used? ");
                    ps.println("</a>");
                    ps.println("</td>");
                }
            }

            ps.println("</tr>");
            ps.println("</table>");

            ps.println("	</TD>");
            ps.println("</TABLE>");


            ps.println("</body>");
            ps.println("</html>");



        }
        catch (Exception e)
        {
            ps.println(e);
            e.printStackTrace();
        }
        finally
        {
            ps.close();
            wps.close();
        }
    }
}
