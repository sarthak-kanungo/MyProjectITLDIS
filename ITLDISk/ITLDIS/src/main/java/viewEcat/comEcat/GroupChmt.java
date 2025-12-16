package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: GroupChmt.java
PURPOSE: To show any changes made in a group.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class GroupChmt extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        String message = null;
        try {

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////


            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String footer = "";
            footer = object_pageTemplate.footer();

            //    String servletURL = "";
            //   servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            //     String recordsURL = object_pageTemplate.recordsURL();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                /*    Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                
                
                 */
                /*########################################*/

                String group = req.getParameter("group");

               // Statement stmt;
                PreparedStatement stmt = null;
                ResultSet rs;

                java.sql.Date effective_date = new java.sql.Date(0);

                String description = "";

                Vector changeVec = new Vector();
                int revision_arr_count = 0;

                SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

                //stmt = conn.createStatement();

                Get_groupInfo grpInfo = new Get_groupInfo();

                String newPart = "";
                String oldPart = "";
                String changeType = "";


                //rs = stmt.executeQuery("SELECT * FROM CAT_ECN_IMPL_HISTORY where GROUP_ASSY_NO='" + group + "' ORDER BY EFFECTIVE_DATE desc");
                String query = ("SELECT * FROM CAT_ECN_IMPL_HISTORY(NOLOCK) where GROUP_ASSY_NO='" + group + "' ORDER BY EFFECTIVE_DATE desc");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    do {
                        newPart = rs.getString(4);
                        oldPart = rs.getString(6);
                        changeType = rs.getString(5);

                        if (changeType.equals("Deleted")) {
                            description = grpInfo.getDescription(oldPart, conn);

                            changeVec.add(oldPart); // PART  

                            changeVec.add(description); // DESCRIPTION

                            changeVec.add(changeType); // CHANGE TYPE

                            changeVec.add("-"); // REPLACED COMP

                        } else {
                            description = grpInfo.getDescription(newPart, conn);

                            changeVec.add(newPart); // PART  

                            changeVec.add(description); // DESCRIPTION

                            changeVec.add(changeType); // CHANGE TYPE

                            if (changeType.equals("Replaced")) {
                                changeVec.add(oldPart); // REPLACED COMP

                            } else {
                                changeVec.add("-"); // REPLACED COMP

                            }

                        }

                        changeVec.add(rs.getString(7)); //  STATUS

                        effective_date = rs.getDate(9);// EFFECTIVE DATE

                        changeVec.add(sdf.format(effective_date)); // EFFECTIVE DATE

                        changeVec.add("" + rs.getInt(8)); // QTY
                        changeVec.add(rs.getString(10));    //Effective TSN
                        revision_arr_count++;
                    } while (rs.next());
                }
                rs.close();
                stmt.close();

//                int tableWidth = 770;
//                int tableHeight_1 = 300;
//                int rowBenchMark = 17;

                ps.println("<html>");
                ps.println("   <head>");
                ps.println("      <title>");
                ps.println("         " + UtilityMapkeys1.tile1 + "");
                ps.println("      </title>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("<script language=JavaScript type=text/JavaScript>");

                ps.println("	function MM_openBrWindow(theURL,winName,features)");
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

                ps.println("</script>");

                ps.println("</head>");
                ps.println("<body >");
                //else
                {
                   // Statement stmt1 = conn.createStatement();
                	PreparedStatement stmt1 = null;
                    ResultSet rs1 = null;
                    String groupDesc = "";
                    //ResultSet rs1 = stmt1.executeQuery("SELECT P1 FROM CAT_GROUP_KIT_DETAIL WHERE GRP_KIT_NO = '" + group + "'");
                    String query1 = ("SELECT P1 FROM CAT_GROUP_KIT_DETAIL(NOLOCK) WHERE GRP_KIT_NO = '" + group + "'");
                    stmt1 = conn.prepareStatement(query1);
                    rs1 = stmt1.executeQuery();
                    if (rs1.next()) {
                        groupDesc = rs1.getString(1);
                    }
                    rs1.close();

                    String groupMap = "";

                    //rs1 = stmt1.executeQuery("SELECT MAP_NAME FROM CAT_MODEL_GROUP WHERE GROUP_NO = '" + group + "'");
                    String query2 = ("SELECT MAP_NAME FROM CAT_MODEL_GROUP(NOLOCK) WHERE GROUP_NO = '" + group + "'");
                    stmt1 = conn.prepareStatement(query2);
                    rs1 = stmt1.executeQuery();
                    if (rs1.next()) {
                        groupMap = rs1.getString(1);
                    }
                    rs1.close();

                    stmt1.close();


                    int width = 930, height = 460;

                    if (revision_arr_count == 0) {
                        message = "No Modification Done.";
                        //ps.println(object_pageTemplate.tableHeader1(message, width, height));
                        object_pageTemplate.ShowMsg(ps, "FAILURE", message, "NO", "", "", "", imagesURL);
                        return;
                    }


                    ps.println(object_pageTemplate.tableHeader("Change History For : " + groupMap + " [ " + groupDesc + " ]", width, height));

                    ps.println(" <table width=900px; border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC>");

                    ps.println(" <tr><td  style=\"padding-top:5px;\"><table width=900px; border=0 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>");
                    ps.println("<tr>");
                    ps.println("<td width = 7% align = center class=heading1>");
                    ps.println("S No.");
                    ps.println("</td>");

                    ps.println("<td width = 12% align=left class=heading1>");
                    ps.println("Component");
                    ps.println("</td>");

                    ps.println("<td width = 30% align=left class=heading1>");
                    ps.println("Description");
                    ps.println("</td>");

                    ps.println("<td width = 14% align=left class=heading1>");
                    ps.println("Old Component");
                    ps.println("</td>");

                    ps.println("<td width = 11% align=left class=heading1>");
                    ps.println("Status");
                    ps.println("</td>");

                    ps.println("<td width = 14% align=left class=heading1>");
                    ps.println("From TSN");
                    ps.println("</td>");

                    ps.println("<td width = 12% align=left class=heading1>");
                    ps.println("Effective From");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");

                    ps.println(" </td></tr><tr><td><div valign=top STYLE=\" overflow-X:hidden; overflow-y: scroll; height:380;\">");

                    ps.println("                 <table width=900px; border=0 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>");
                    ps.println("              ");

//                    if (revision_arr_count > rowBenchMark)
//                    {
//                        ps.println("<div class=\"maindiv\" STYLE=\" overflow-X:hidden; overflow-y: scroll; height:" + tableHeight_1 + ";\">");
//                    }
//                    ps.println("	<table cellspacing=0 cellpadding=1 border=1 width=" + tableWidth + " bordercolor=#D7D7D7 id=myTable>");

                    String cssClass = null;
                    int l=0;
                    for (int i = 0; i < changeVec.size(); i = i + 8) {
                        
                        if (l % 2 == 0) {
                             cssClass = "links_txt";//links_txt_Alt
                        } else {
                            cssClass = "links_txt";
                        }
                        l++;


                        ps.println("<tr>");

                        ps.println("<td align=center class="+cssClass+" style=\"word-wrap: break-word\" width=7% >");
                        ps.println((i / 8) + 1);
                        ps.println("</td>");

                        ps.println("<td  align=left class="+cssClass+" style=\"word-wrap: break-word\" width=12% >");
                        ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + changeVec.elementAt(i) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                        ps.println(changeVec.elementAt(i));
                        ps.println("</a>");
                        ps.println("</td>");

                        ps.println("<td  align=left class="+cssClass+" style=\"word-wrap: break-word\" width=30% >");
                        ps.println(changeVec.elementAt(i + 1));
                        ps.println("</td>");

                        ps.println("<td align=left class="+cssClass+" style=\"word-wrap: break-word\" width=14% >");
                        ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + changeVec.elementAt(i+3) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                        ps.println(changeVec.elementAt(i + 3));
                        ps.println("</a>");
                        ps.println("</td>");

                        ps.println("<td align=left class="+cssClass+" style=\"word-wrap: break-word\" width=11% >");
                        ps.println(changeVec.elementAt(i + 4));
                        ps.println("</td>");
                        /*ps.println("<td align=center style=\"word-wrap: break-word\" width=14% >");
                        ps.println(changeVec.elementAt(i + 4));
                        ps.println("</td>");*/

                        ps.println("<td align=left class="+cssClass+" style=\"word-wrap: break-word\" width=14% >");
                        ps.println("" + changeVec.elementAt(i + 7));
                        ps.println("</td>");

                        ps.println("<td align=left class="+cssClass+" style=\"word-wrap: break-word\" width=12% >");
                        ps.println(changeVec.elementAt(i + 5));
                        ps.println("</td>");

                        ps.println("</tr>");
                    }

                    ps.println("</table> ");
                    ps.println("</div>");

                    ps.println("</td>");
                    ps.println("</tr>");



                    ps.println("</table>");

                    ps.println(object_pageTemplate.tableFooter());

                }

                /*
                java.sql.Date testDate = new java.sql.Date(0);

                String component = "";
                String change_type = "";
                String replaced_comp = "";
                String description = "";
                String model_group = "";
                String revision = "";
                java.sql.Date effective_date = new java.sql.Date(0);
                java.sql.Date change_date = new java.sql.Date(0);
                String engine_sr_no = "";
                String modifier = "";

                String image_modified = "";
                String dealer_description = "";

                String interchangeable = "";
                String documents = "";

                String lastRevision = "";

                Vector revision_arr = new Vector();
                int revision_arr_count = 0;
                rs = stmt.executeQuery("SELECT DISTINCT revision FROM change_mgmt where changed_group_engine='" + group + "' ORDER BY revision");
                if (rs.next())
                {
                do
                {
                revision_arr.add(rs.getString(1));
                revision_arr_count++;
                }
                while (rs.next());
                }
                rs.close();

                if (revision_arr_count == 0)
                {
                ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=50%>");
                ps.println("<tr>");
                ps.println("<td valign = middle>");
                ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                ps.println("<tr bgcolor = #003399>");
                ps.println("<td align = center>");
                ps.println("<font color = #FFFFFF><b>");
                ps.println("NO MODIFICATION DONE.");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");

                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");
                return;
                }
                else
                {
                ps.println("<center><table cellspacing=1 cellpadding=3 border=1 width=99% bordercolor = #C0C0C0>");
                }
                Date tempDate =null;
                Date javaTestDate = null;

                for (int i = 0; i < revision_arr.size(); i++)
                {
                rs = stmt.executeQuery("SELECT * FROM IMAGE_BACKUP where GROUP_NO = '" + group + "' AND REVISION_NO = " + revision_arr.elementAt(i));
                if (rs.next())
                {
                image_modified = "YES";
                }
                rs.close();

                rs = stmt.executeQuery("SELECT * FROM change_mgmt WHERE revision = " + revision_arr.elementAt(i) + " AND (change_type = 'Replaced' OR change_type = 'Added' OR change_type = 'Deleted' OR component = '" + group + "' OR changed_group_engine = '" + group + "') ORDER BY LEVELS DESC");
                if (rs.next())
                {
                do
                {
                lastRevision = revision;

                component = rs.getString(1);
                if (component == null)
                {
                component = "";
                }

                change_type = rs.getString(2);
                if (change_type == null)
                {
                change_type = "";
                }

                replaced_comp = rs.getString(3);
                if (replaced_comp == null)
                {
                replaced_comp = "";
                }

                description = rs.getString(4);
                change_date = rs.getDate(5);
                model_group = rs.getString(6);
                revision = rs.getString(7);

                effective_date = rs.getDate(9);
                engine_sr_no = rs.getString(10);
                modifier = rs.getString(11);
                dealer_description = rs.getString(12);
                interchangeable = rs.getString(13);
                documents = rs.getString(14);

                if (!(revision.equals(lastRevision)))
                {
                ps.println("<tr bgcolor = #003399>");
                ps.println("<td colspan = 2>");
                ps.println("<font color=#FFFFFF>");
                ps.println("<b>REVISION NO:</b>");
                ps.println("</font>");
                ps.println("</td>");

                ps.println("<td colspan = 4>");
                ps.println("<font color=#FFFFFF><b>");
                ps.println(revision);
                ps.println("</b></font>");
                ps.println("</font>");
                ps.println("</td>");

                ps.println("</tr>");

                if (userFunctionalities.contains("55"))
                {
                if (description != null)
                {
                ps.println("<tr>");
                ps.println("<td colspan =2>");
                ps.println("<b>"+UtilityMapkeys1.tile1+" DESCRIPTION:</b>");
                ps.println("</td>");

                ps.println("<td colspan = 4>");
                ps.println(description);
                ps.println("</td>");

                ps.println("</tr>");
                }
                }

                if (dealer_description != null)
                {
                ps.println("<tr>");
                ps.println("<td colspan = 2>");
                ps.println("<b>DESCRIPTION:</b>");
                ps.println("</td>");

                ps.println("<td colspan = 4>");
                ps.println(dealer_description);

                if (documents != null)
                {
                if (!(documents.equals("NO")))
                {
                ps.println("<a href='" + recordsURL + revision + "/" + documents + "' target = _blank>");
                ps.println("<IMG SRC=" + imagesURL + "perceedence.gif border = 0>");
                ps.println("</a>");
                }
                }
                ps.println("</td>");

                ps.println("</tr>");
                }

                if (effective_date != null)
                {
                ps.println("<tr>");
                ps.println("<td colspan = 2>");
                ps.println("<b>EFFECTIVE DATE:</b>");
                ps.println("</td>");

                ps.println("<td colspan = 4>");
                tempDate = sdfInput.parse(effective_date.toString());
                javaTestDate = sdfInput.parse(testDate.toString());

                if (javaTestDate.equals(tempDate))
                {
                ps.println("&nbsp;");
                }
                else
                {
                ps.println(sdf.format(tempDate));
                }

                ps.println("</td>");

                ps.println("</tr>");
                }

                if (userFunctionalities.contains("56"))
                {
                if (description != null)
                {
                ps.println("<tr>");
                ps.println("<td colspan =2>");
                ps.println("<b>CHANGE DONE ON</b>");
                ps.println("</td>");

                ps.println("<td colspan = 4>");

                tempDate = sdfInput.parse(change_date.toString());
                javaTestDate = sdfInput.parse(testDate.toString());

                if (javaTestDate.equals(tempDate))
                {
                ps.println("&nbsp;");
                }
                else
                {
                ps.println(sdf.format(tempDate));
                }

                ps.println("</td>");

                ps.println("</tr>");
                }
                }
                ps.println("<tr>");
                ps.println("<td colspan = 2>");
                ps.println("<b>IMAGE BACKUP:</b>");
                ps.println("</td>");

                ps.println("<td colspan = 4>");
                ps.println("<font face=Helvetica size=1 color=#000000>");
                if (image_modified.equals("YES"))
                {
                ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "ListBACKUPIMG_2?revisionNO=" + revision + "&group=" + group + "','IMAGE','resizable=no,scrollbars=yes,width=1010,height=700')>");
                ps.println("<IMG SRC=" + imagesURL + "perceedence.gif border = 0>");
                ps.println("</a>");
                }
                else
                {
                ps.println("NO");
                }
                ps.println("</font>");
                ps.println("</td>");

                ps.println("</tr>");

                if (userFunctionalities.contains("57"))
                {
                ps.println("<tr>");
                ps.println("<td colspan = 2>");
                ps.println("<b>MODIFIER:</b>");
                ps.println("</font>");
                ps.println("</td>");

                ps.println("<td colspan = 4>");
                ps.println("<font face=Helvetica size=1 color=#000000>");
                ps.println(modifier);
                ps.println("</font>");
                ps.println("</td>");

                ps.println("</tr>");
                }


                ps.println("<tr> ");
                ps.println("<td width=18%>");
                ps.println("<b>COMPONENT</b>");
                ps.println("</td>");

                ps.println("<td width=20%>");
                ps.println("<b>CHANGE TYPE</b>");
                ps.println("</td>");

                ps.println("<td width=18%>");
                ps.println("<b>OLD COMPONENT</b>");
                ps.println("</td>");

                ps.println("<td width=4% align = center>");
                ps.println("<b>IC</b>");
                ps.println("</td>");

                ps.println("<td width=30%>");
                ps.println("<b>CHANGED SUB-ASSY /<br>GROUP / MODEL</b>");
                ps.println("</td>");

                ps.println("<td width=10%>");
                ps.println("<b>TRACTOR NO</b>");
                ps.println("</td>");
                ps.println("</tr>");
                }

                ps.println("<tr>");

                ps.println("<td>");
                int exists_1 = 0;
                int exists_2 = 0;
                String pComponent_2 = "";
                String pComponent_1 = "";

                exists_1 = component.indexOf("-");
                pComponent_1 = component;
                if (exists_1 > 0)
                {
                pComponent_1 = component.substring(0, component.lastIndexOf("_"));
                pComponent_1 = pComponent_1.replace('-', '.');
                pComponent_1 = pComponent_1.replace('_', ' ');
                }
                ps.println(pComponent_1);
                ps.println("</td>");

                ps.println("<td>");
                if (change_type != null)
                {
                ps.println(change_type);
                }
                else
                {
                ps.println("&nbsp;");
                }
                ps.println("</td>");

                ps.println("<td>");

                ps.println(replaced_comp);

                if (replaced_comp == null || replaced_comp.equals("") || replaced_comp.equals(" "))
                {
                ps.println("&nbsp;");
                }

                ps.println("</td>");

                ps.println("<td align = center>");

                if (interchangeable != null)
                {
                if (interchangeable.equals(""))
                {
                ps.println("&nbsp;");
                }
                else
                {
                ps.println(interchangeable);
                }
                }
                else
                {
                ps.println("&nbsp;");
                }
                ps.println("</td>");

                ps.println("<td>");

                if (model_group == null || model_group.equals("") || model_group.equals(" "))
                {
                ps.println("&nbsp;");
                }
                else
                {

                exists_2 = model_group.indexOf("-");
                pComponent_2 = model_group;
                if (exists_2 > 0)
                {
                pComponent_2 = model_group.substring(0, model_group.lastIndexOf("_"));
                pComponent_2 = pComponent_2.replace('-', '.');
                pComponent_2 = pComponent_2.replace('_', ' ');
                }
                ps.println(pComponent_2);
                }

                ps.println("</td>");

                ps.println("<td>");

                if (engine_sr_no == null)
                {
                engine_sr_no = "&nbsp;";
                }
                else
                {
                if (engine_sr_no.equals(""))
                {
                engine_sr_no = "&nbsp;";
                }
                }

                if (change_type.equals("Group Modified"))
                {
                ps.println(engine_sr_no);
                }
                ps.println("&nbsp;");

                ps.println("</td>");

                ps.println("</tr>");
                }
                while (rs.next());
                }
                image_modified = "";
                rs.close();
                }

                stmt.close();
                 */
            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println(footer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
    }
}
