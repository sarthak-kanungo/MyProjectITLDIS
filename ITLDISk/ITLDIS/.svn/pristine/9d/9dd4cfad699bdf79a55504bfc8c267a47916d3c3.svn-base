package viewEcat.orderEcat;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.GetLanId;
import viewEcat.comEcat.PageTemplate;

/**
To upload the order on web.
@author  Deepak Mangal	
@version v3.4
@since 
 */

public class ExportOrder_2 extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {

        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        try
        {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("hhmmss_ddMMMyyyy");
            String dd = df.format(date);

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
            String userCode = (String) session.getValue("userCode");

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

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();
            String imagesURL=object_pageTemplate.imagesURL();

            String dsnPATH_ORDER = object_pageTemplate.dsnPATH_ORDER();
            String orderRefNoString = object_pageTemplate.orderRefNoString();
            String serverIP = object_pageTemplate.serverIP();
            int serverPort = object_pageTemplate.serverPort();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////
            if (session_id.equals(getSession))
            {

                SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MMM-yy");
                SimpleDateFormat sdfInput2 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
                String d2 = sdfInput.format(new java.util.Date());
                d2 = d2 + " " + "00:00:00";
                
                java.util.Date currentDate = sdfInput2.parse(d2);
                java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());

                //String onDate = req.getParameter("menu2");
                String refCustNo = req.getParameter("rb1");
                String onDate[] = refCustNo.split("@");

                Connection conn_general;
                Connection conn_order;

                //Statement stmt_general;
                //Statement stmt_order;
                PreparedStatement stmt_general = null;
                PreparedStatement stmt_order = null;

                ResultSet rs;

                if (orderRefNoString.equals("LOCAL"))
                {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    conn_order = DriverManager.getConnection(dsnPATH_ORDER, "", "");
                }
                else
                {
                    conn_order = holder.getConnection();
                }

                conn_general = holder.getConnection();

                //stmt_general = conn_general.createStatement();
                //stmt_order = conn_order.createStatement();

                // Code to check the version installed on the Dealer PC.

                int cdNo = 0;
                int patchNo = 0;
                int upto_revision_no = 0;

                int order_cdNo = 0;
                int order_patchNo = 0;
                int order_revision_no = 0;

                //rs = stmt_general.executeQuery("SELECT MAX(sno) FROM CAT_CD");
                String query = ("SELECT MAX(sno) FROM CAT_CD(NOLOCK)");
                stmt_general = conn_general.prepareStatement(query);
                rs = stmt_general.executeQuery();
                if (rs.next())
                {
                    cdNo = rs.getInt(1);
                }
                rs.close();

                //rs = stmt_general.executeQuery("SELECT MAX(PATCH_NO) FROM CAT_PATCH WHERE CD_NO= " + cdNo);
                String query1 = ("SELECT MAX(PATCH_NO) FROM CAT_PATCH(NOLOCK) WHERE CD_NO= " + cdNo);
                stmt_general = conn_general.prepareStatement(query1);
                rs = stmt_general.executeQuery();
                if (rs.next())
                {
                    String temp = rs.getString(1);
                    if (temp != null)
                    {
                        patchNo = Integer.parseInt(temp);
                    }
                }
                rs.close();

                //rs = stmt_general.executeQuery("SELECT REVISION_NO FROM CAT_PATCH WHERE PATCH_NO= " + patchNo);
                String query2 = ("SELECT REVISION_NO FROM CAT_PATCH(NOLOCK) WHERE PATCH_NO= " + patchNo);
                stmt_general = conn_general.prepareStatement(query2);
                rs = stmt_general.executeQuery(); 
                if (rs.next())
                {
                    String temp = rs.getString(1);
                    if (temp != null)
                    {
                        upto_revision_no = Integer.parseInt(temp);
                    }
                }
                rs.close();

                //rs = stmt_order.executeQuery("SELECT CD_NO,PATCH_NO FROM ecat201 WHERE REF_NO ='" + onDate[0] + "'");
                String query3 = ("SELECT CD_NO,PATCH_NO FROM ecat201(NOLOCK) WHERE REF_NO ='" + onDate[0] + "'");
                stmt_order = conn_order.prepareStatement(query3);
                rs = stmt_order.executeQuery();
                if (rs.next())
                {
                    try
                    {
                        order_cdNo = Integer.parseInt(rs.getString(1));
                        order_patchNo = Integer.parseInt(rs.getString(2));
                    }
                    catch (NumberFormatException v)
                    {
                        order_cdNo = 0;
                        order_patchNo = 0;
                    }
                }
                rs.close();

                String upLoadOrder = "YES";

                if (cdNo > order_cdNo)
                {
                    upLoadOrder = "NO";
                }

                if (cdNo == order_cdNo)
                {
                    if (patchNo > order_patchNo)
                    {
                        upLoadOrder = "NO";
                    }
                }

                Vector partsVec = new Vector();
   //             int parts_array_count = 0;

                String format = "xls";
                String saveLocation = ecatPATH + "/order/documents/";

                File F1 = new File(saveLocation);

                if (!(F1.exists()))
                {
                    ps.println(header);
                    ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=50%>");
                    ps.println("<tr>");
                    ps.println("<td valign = middle>");
                    ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                    ps.println("<tr bgcolor = #003399>");
                    ps.println("<td align = center>");
                    ps.println("<font color = #FFFFFF><b>");
                    ps.println("NOT A VALID PATH FOR ORDER DOCUMENTS.");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");

                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");
                    return;
                }

                int COUNTER = 0;
                //rs = stmt_order.executeQuery("SELECT * from ecat202 WHERE REF_NO ='" + onDate[0] + "'");
               String sqlQuery = ("SELECT * from ecat202(NOLOCK) WHERE REF_NO ='" + onDate[0] + "'");
               stmt_order = conn_order.prepareStatement(sqlQuery);
               rs = stmt_order.executeQuery();
                if (rs.next())
                {
                    do
                    {
                        partsVec.add(rs.getString(2));  // Part No
                        partsVec.add("" + rs.getInt(3));  // Qty
                        partsVec.add("" + rs.getDouble(5));  // Price
                    //    parts_array_count++;
                        COUNTER = 1;
                    }
                    while (rs.next());
                }
                rs.close();

                if (upLoadOrder.equals("NO"))
                {
                    //ps.println("At Phase 1 upload order is NO<br>");
                   // rs = stmt_general.executeQuery("SELECT REVISION_NO FROM CAT_PATCH WHERE PATCH_NO= " + order_patchNo);
                   String sqlQuery1 = ("SELECT REVISION_NO FROM CAT_PATCH(NOLOCK) WHERE PATCH_NO= " + order_patchNo);
                   stmt_general = conn_general.prepareStatement(sqlQuery1);
                   rs = stmt_general.executeQuery();
                    if (rs.next())
                    {
                        String temp = rs.getString(1);
                        if (temp != null)
                        {
                            order_revision_no = Integer.parseInt(temp);
                        }
                    }
                    rs.close();

                    if (upto_revision_no > order_revision_no)
                    {
                        upLoadOrder = "NO";
                    }
                    else
                    {
                        upLoadOrder = "YES";
                    }
                }

                Vector effectedParts = new Vector();

                if (upLoadOrder.equals("NO"))
                {
//ps.println("At Phase 2 upload order is NO<br>");
                    for (int i = 0; i < partsVec.size(); i = i + 3)
                    {
                        //rs = stmt_general.executeQuery("SELECT * FROM change_mgmt WHERE ((replaced_comp = '" + partsVec.elementAt(i) + "' AND change_type='Replaced') OR (component = '" + partsVec.elementAt(i) + "' AND change_type='Deleted')) AND revision <= " + upto_revision_no + " AND revision >= " + order_revision_no + "");
                        String sql = ("SELECT * FROM change_mgmt(NOLOCK) WHERE ((replaced_comp = '" + partsVec.elementAt(i) + "' AND change_type='Replaced') OR (component = '" + partsVec.elementAt(i) + "' AND change_type='Deleted')) AND revision <= " + upto_revision_no + " AND revision >= " + order_revision_no + "");
                        stmt_general = conn_general.prepareStatement(sql);
                        rs = stmt_general.executeQuery();
                    	if (rs.next())
                        {
                            do
                            {
                                if (effectedParts.indexOf("" + partsVec.elementAt(i)) == -1)
                                {
                                    effectedParts.addElement("" + partsVec.elementAt(i));
                                }
                            }
                            while (rs.next());
                        }
                        rs.close();
                    }
                }

                if (effectedParts.size() == 0)
                {
//ps.println("At Phase 3 upload order is YES<br>");
                    upLoadOrder = "YES";
                }
                else
                {
//ps.println("At Phase 3 upload order is NO<br>");
                    upLoadOrder = "NO";
                }

                stmt_general.close();

                if (upLoadOrder.equals("NO"))
                {
                    ps.println(header);
                    ps.println("<br><center><table width=500 border=1 cellspacing=1 cellpadding=5 ");
                    ps.println("bordercolor=#CCCCCC align=center>");
                    ps.println("<tr>");
                    ps.println("<td valign=top bgcolor=#003399 colspan = 2>");
                    ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2 color =#FFFFFF><b>");
                    ps.println("Following Parts are Replaced/Deleted From Groups.");
                    ps.println("</font>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    ps.println("<tr>");
                    ps.println("<td valign=top bgcolor=#003399 width = 100 align = center>");
                    ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2 color =#FFFFFF><b>");
                    ps.println("S No.");
                    ps.println("</font>");
                    ps.println("</td>");

                    ps.println("<td valign=top bgcolor=#003399 width = 400>");
                    ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2 color =#FFFFFF><b>");
                    ps.println("PART NO.");
                    ps.println("</font>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    for (int i = 0; i < effectedParts.size(); i++)
                    {
                        ps.println("<tr>");
                        ps.println("<td align = center>");
                        ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2><b>");
                        ps.println(i + 1);
                        ps.println("</font>");
                        ps.println("</td>");

                        ps.println("<td>");
                        ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2><b>");
                        ps.println(effectedParts.elementAt(i));
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                    }
                    ps.println("</table>");
                    return;
                }


                if (COUNTER > 0)
                {

                    ObjectOutputStream oos = null;
                    ObjectInputStream ois = null;
                    Socket socket = null;

                    GetLanId objLanId = new GetLanId();
                    String lanId = objLanId.getLanId();
                    String lanIdStatus = "";

                    Vector checkLanVec = new Vector();
                    checkLanVec.add(userCode);
                    checkLanVec.add(lanId);
                    checkLanVec.add("CheckOnlyLanCardId");

                    try
                    {
                        socket = new Socket(serverIP, serverPort);
                        oos = new ObjectOutputStream(socket.getOutputStream());
                        ois = new ObjectInputStream(socket.getInputStream());

                        oos.writeObject(checkLanVec);

                        lanIdStatus = (String) ois.readObject();

                        if (!lanIdStatus.equals("1"))
                        {
                            ps.println(header);

                            ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=50%>");
                            ps.println("<tr>");
                            ps.println("<td valign = middle>");

                            ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                            ps.println("<tr bgcolor = #003399>");
                            ps.println("<td align = center>");
                            ps.println("<font color = #FFFFFF><b>");
                            ps.println("INVALID MACHINE. <br>ORDER CANNOT BE UPLOADED.");
                            ps.println("</td>");
                            ps.println("</tr>");
                            ps.println("</table>");

                            ps.println("</td>");
                            ps.println("</tr>");
                            ps.println("</table>");
                        }
                    }
                    catch (Exception e)
                    {
                        ps.println(header);

                        ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=50%>");
                        ps.println("<tr>");
                        ps.println("<td valign = middle>");

                        ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                        ps.println("<tr bgcolor = #003399>");
                        ps.println("<td align = center>");
                        ps.println("<font color = #FFFFFF><b>");
                        ps.println("ORDER CANNOT BE UPLOADED.<br><br>Error Occured While Connecting To Web Server.<br>Check Your Internet Connection And Try Later.");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("</table>");

                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("</table>");
                        e.printStackTrace();
                    }
                    oos.close();
                    ois.close();
                    socket.close();

                    if (lanIdStatus.equals("1"))
                    {
                        Vector orderHeaderVec = new Vector();

                        //rs = stmt_order.executeQuery("SELECT * FROM ecat201 WHERE REF_NO = '" + onDate[0] + "'");
                        String sqlQuery2 = ("SELECT * FROM ecat201(NOLOCK) WHERE REF_NO = '" + onDate[0] + "'");
                        stmt_order = conn_order.prepareStatement(sqlQuery2);
                        rs = stmt_order.executeQuery();
                        if (rs.next())
                        {
                            // REF_NO is not added in the array orderHeaderData[].
                            for (int i = 0; i < 24; i++)
                            {
                                if ((i == 3) || (i == 19) || (i == 23))
                                {
                                    orderHeaderVec.add(rs.getDate(i + 2));
                                }
                                else if (i == 12)
                                {
                                    orderHeaderVec.add("" + rs.getInt(i + 2));
                                }
                                else if (i == 17)
                                {
                                    orderHeaderVec.add("" + rs.getDouble(i + 2));
                                }
                                else
                                {
                                    orderHeaderVec.add(rs.getString(i + 2));
                                }
                            }
                        }
                        rs.close();
                        stmt_order.close();

                        Vector orderUploadDataVec = new Vector();
                        orderUploadDataVec.add(orderHeaderVec);
                        orderUploadDataVec.add(partsVec);
                        orderUploadDataVec.add("UploadLocalOrder");
                        orderUploadDataVec.add(userCode);

                        try
                        {
                            socket = new Socket(serverIP, serverPort);
                            oos = new ObjectOutputStream(socket.getOutputStream());
                            ois = new ObjectInputStream(socket.getInputStream());

                            oos.writeObject(orderUploadDataVec);

                            String webUploadRes = (String) ois.readObject();

                            if (webUploadRes.equals("YES"))
                            {
                                String webpoNo = (String) ois.readObject();
                                oos.writeObject("YES");

                                PreparedStatement pstmt = conn_order.prepareStatement("UPDATE ecat201 SET STATUS = 'CLOSE' , DOWNLOAD_STATUS = 'YES', WEB_REF_NO = ?, CUST_PO_DATE=? WHERE REF_NO = ?");

                                pstmt.setString(1, webpoNo);
                                pstmt.setDate(2, sqlCurrentDate);
                                pstmt.setString(3, onDate[0]);

                                pstmt.executeUpdate();
                                pstmt.close();
                                conn_order.commit();

                                /*        String poNo = "";
                                rs = stmt_order.executeQuery("SELECT CUST_PO_NO FROM ecat201 WHERE REF_NO = '" + onDate[0] + "'");
                                if (rs.next())
                                {
                                poNo = rs.getString(1);
                                }
                                rs.close();
                                 */
                                String test = "";
                                String tempPartNo = "";

                                for (int v = 0; v < partsVec.size(); v = v + 3)
                                {
                                    tempPartNo = ("" + partsVec.elementAt(v)).replaceAll("-", "");

                                    test = test + tempPartNo + "\t" + partsVec.elementAt(v + 1) + "\n";
                                }

                                char buffer[] = new char[test.length()];
                                test.getChars(0, test.length(), buffer, 0);

                                FileWriter outputFile = new FileWriter(saveLocation + "\\" + onDate[1] + "_" + dd + "." + format);

                                outputFile.write(buffer);
                                outputFile.close();

                                ps.println(header);

                                ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=50%>");
                                ps.println("<tr>");
                                ps.println("<td valign = middle>");

                                ps.println("<center><table width=98% border=0 cellspacing=1 cellpadding=5 ");
                                ps.println("bordercolor=#CCCCCC align=center>");
                                ps.println("<tr>");

                                ps.println("<td valign=top bgcolor=#003399 colspan=4 align= center>");
                                ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2 color = #FFFFFF><b>");
                                ps.println("Your Order Has Been Uploaded As " + webpoNo + ".<br>");
                                ps.println("It Will Be Registered Soon.");
                                /*				ps.println("YOUR ORDER HAS BEEN STORED AS");
                                ps.println(poNo+"_"+dd+"."+format);
                                ps.println(" IN FOLDER "+saveLocation);
                                 */
                                ps.println("<b></font>");
                                ps.println("</td>");

                                ps.println("</tr>");

                                ps.println("<tr>");
                                ps.println("<td valign = middle colspan=4 align = right>");

                                ps.println("<a href ='ViewOrderNew_0'>");
                                ps.println("<b>");
                                ps.println("<font color=#000099><< BACK</font>");
                                ps.println("</a>");

                                ps.println("</td>");
                                ps.println("</tr>");

                                /*			ps.println("<tr>");
                                ps.println("<td valign=top bgcolor=#003399 width = 10% align = center>");
                                ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2 color =#FFFFFF><b>");
                                ps.println("S No.");
                                ps.println("</font>");
                                ps.println("</td>");
                                ps.println("<td valign=top bgcolor=#003399 width = 25%>");
                                ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2 color =#FFFFFF><b>");
                                ps.println("PART NO.");
                                ps.println("</font>");
                                ps.println("</td>");
                                ps.println("<td valign=top bgcolor=#003399 width = 45%>");
                                ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2 color =#FFFFFF><b>");
                                ps.println("DESCRIPTION");
                                ps.println("</font>");
                                ps.println("</td>");
                                ps.println("<td valign=top bgcolor=#003399 width = 20% align = center>");
                                ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2 color =#FFFFFF><b>");
                                ps.println("QTY");
                                ps.println("</font>");
                                ps.println("</td>");
                                ps.println("</tr>");					
                                for (int v=0;v< parts_array_count;v++ )
                                {
                                ps.println("<tr>");
                                ps.println("<td align = center>");
                                ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2>");
                                ps.println(v+1);
                                ps.println("</font>");
                                ps.println("</td>");
                                ps.println("<td>");
                                ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2>");
                                String tempPart = parts_array[v][0].replaceAll("-","");
                                ps.println(tempPart);
                                ps.println("</font>");
                                ps.println("</td>");
                                ps.println("<td>");
                                ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2>");
                                rs = stmt_general.executeQuery("SELECT p1 FROM part where part_no = '"+parts_array[v][0]+"'"); 
                                if(rs.next())
                                {
                                ps.println(rs.getString(1));
                                }
                                else
                                {
                                rs = stmt_general.executeQuery("SELECT p1 FROM ASSY_DETAIL where ASSY_NO = '"+parts_array[v][0]+"'"); 
                                if(rs.next())
                                {
                                ps.println(rs.getString(1));
                                }
                                else
                                {
                                rs = stmt_general.executeQuery("SELECT DESCRIPTION FROM S_KIT_DETAIL WHERE KIT_NO = '"+parts_array[v][0]+"'"); 
                                if(rs.next())
                                {
                                ps.println(rs.getString(1));
                                }
                                else
                                {
                                ps.println("--");
                                }
                                }
                                }
                                rs.close();
                                ps.println("</font>");
                                ps.println("</td>");
                                ps.println("<td>");
                                ps.println("<font face=Verdana, Arial, Helvetica, sans-serif size=2>");
                                ps.println(parts_array[v][1]);
                                ps.println("</font>");
                                ps.println("</td>");
                                ps.println("</tr>");
                                }
                                 */
                                ps.println("</table>");

                                ps.println("</td>");
                                ps.println("</tr>");
                                ps.println("</table>");

                            }
                            else if (webUploadRes.equals("NO"))
                            {
                                oos.writeObject("NO");

                                ps.println(header);

                                ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=50%>");
                                ps.println("<tr>");
                                ps.println("<td valign = middle>");

                                ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                                ps.println("<tr bgcolor = #003399>");
                                ps.println("<td align = center>");
                                ps.println("<font color = #FFFFFF><b>");
                                ps.println("ORDER NOT UPLOADED.<br>Another Order Exists On Web With Same Cust PO No.");
                                ps.println("</td>");
                                ps.println("</tr>");
                                ps.println("</table>");

                                ps.println("</td>");
                                ps.println("</tr>");
                                ps.println("</table>");
                            }
                            else
                            {
                                ps.println(header);

                                ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=50%>");
                                ps.println("<tr>");
                                ps.println("<td valign = middle>");

                                ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                                ps.println("<tr bgcolor = #003399>");
                                ps.println("<td align = center>");
                                ps.println("<font color = #FFFFFF><b>");
                                ps.println("ORDER NOT UPLOADED.<br>Error Occured While Connecting To Web Server.<br>Check Your Internet Connection And Try Later.");
                                ps.println("</td>");
                                ps.println("</tr>");
                                ps.println("</table>");

                                ps.println("</td>");
                                ps.println("</tr>");
                                ps.println("</table>");
                            }
                        }
                        catch (Exception e)
                        {
                            ps.println(header);

                            ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=50%>");
                            ps.println("<tr>");
                            ps.println("<td valign = middle>");

                            ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                            ps.println("<tr bgcolor = #003399>");
                            ps.println("<td align = center>");
                            ps.println("<font color = #FFFFFF><b>");
                            ps.println("ORDER NOT UPLOADED.<br>Error Occured While Connecting To Web Server.<br>Check Your Internet Connection And Try Later.");
                            ps.println("</td>");
                            ps.println("</tr>");
                            ps.println("</table>");

                            ps.println("</td>");
                            ps.println("</tr>");
                            ps.println("</table>");
                            e.printStackTrace();
                        }
                        oos.close();
                        ois.close();
                        socket.close();
                    }
                }
                else
                {
                    ps.println(header);

                    ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=70%>");
                    ps.println("<tr>");
                    ps.println("<td valign = middle>");

                    ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                    ps.println("<tr bgcolor = #003399>");
                    ps.println("<td align = center>");
                    ps.println("<font color = #FFFFFF><b>");
                    ps.println("Order Cannot Be Uploaded As It Does Not Contain Any Part.");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");

                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");
                }

                if (orderRefNoString.equals("LOCAL"))
                {
                    conn_order.close();
                }
            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println(footer);
        }
        catch (IOException e)
        {
            ps.println("<center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=50%>");
            ps.println("<tr>");
            ps.println("<td valign = middle>");
            ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
            ps.println("<tr bgcolor = #003399>");
            ps.println("<td align = center>");
            ps.println("<font color = #FFFFFF><b>");
            ps.println("ERROR OCCURED.<br>");
            ps.println("CONTACT ADMINISTRATOR");
            ps.println("</td>");
            ps.println("</tr>");
            ps.println("</table>");

            ps.println("</td>");
            ps.println("</tr>");
            ps.println("</table>");
            e.printStackTrace();
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
