package viewEcat.orderEcat;

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

import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;

/**
To put the items selected with quantity are stored into a Session Cart .
@author  Deepak Mangal
@version v3.4
@since
 */
public class Add_2_cart extends HttpServlet
{

    /**************************************************************************************
     * To put the items selected with quantity are Stored into a Session Cart .
     * <br><br><b>Steps:</b>
     * <br>1. Retrieving the Vector of the previous added parts, Current Part No. and Quantity from session'
     * <br>2.Checking Whether the part id ordered before in the same session
     * <br>3. IF it is new part(ie. not added in the parts vector in the same session ) than added to the Vector of parts
     * <br>4. Else the Message is shown
     * <br><br><b>Author</b> - Deepak Mangal
     * @param HttpServletRequest
     * @param HttpServletResponse
     * @return
     ****************************************************************************************/
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        try
        {

            ///////////////////////////// CREATE SESSION ///////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String getType = (String) session.getValue("user_type");
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
            String header = "";
            header = object_pageTemplate.header(getType);

            String footer = "";
            footer = object_pageTemplate.footer();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String message = null;
            String desc=null;
            String moq=null;
            String flag = req.getParameter("flag") == null ? "" : req.getParameter("flag");//addOrderAjax

            Connection con = null;
            ResultSet rs = null;
            //Statement stmt = null;
            PreparedStatement stmt = null;




            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////


            if (session_id.equals(getSession))
            {
                Vector totalParts;

                //$1**********
                totalParts = (Vector) session.getValue("cartItems");
                String partNo = req.getParameter("partNo") == null ? "" : req.getParameter("partNo").trim();
                String ordQty = req.getParameter("ordQty");



                /******************************/
                if (totalParts == null)
                {
                    totalParts = new Vector();
                }

                if (flag.equals("AddQtyPart2Cart"))
                {

                    if (totalParts.indexOf(partNo) != -1)
                    {
                        totalParts.remove(totalParts.indexOf(partNo)+1);
                        totalParts.remove(partNo);
                    }
                    if (!partNo.toUpperCase().startsWith("TBA"))
                    {
                        totalParts.addElement(partNo);
                        totalParts.addElement(ordQty);
                        //Collections.sort(totalParts);
                        session.putValue("cartItems", totalParts);
                    }
                    return;
                }

                if (!flag.equals("addOrderAjax") && !flag.equals("AddNewPart2Cart"))
                {
                    ps.println(header);
                    ps.println("<html>");
                    ps.println("   <head>");
                    ps.println("<link href=\"" + imagesURL + "css/style.css\" type=\"text/css\" rel=\"stylesheet\">");
                    ps.println("   </head>");

                    ps.println("<table cellspacing=0 cellpadding=0 border=0 width=100%>");
                    ps.println("<tr>");
                    ps.println("<td align=right><a href=javascript:self.close()><img src=" + imagesURL + "close.jpg border=0></a></td>");
                    ps.println("</tr>");
                    ps.println("</table>");

                }


                if (flag.equals("AddNewPart2Cart"))
                {

                    con = holder.getConnection();
                    //stmt = con.createStatement();
//1410501001
                    //rs = stmt.executeQuery("select p1 from cat_part where part_no='" + partNo + "'");
                    String query = ("select p1 from cat_part(NOLOCK) where part_no='" + partNo + "'");
                    stmt = con.prepareStatement(query);
                    rs = stmt.executeQuery();

                    //System.out.println("select p1 from part where part_no='" + partNo + "'");
                    if (rs.next())
                    {
                        desc=rs.getString(1);

                        if(desc==null)
                            desc="";
                        //moq=rs.getString(2)==null?"1":rs.getString(2);

                            if (totalParts.indexOf(partNo) == -1)
                            {
                                if (!partNo.toUpperCase().startsWith("TBA"))
                                {
                                    ps.println("Success" + "$#" + desc+ "$#");
                                    totalParts.addElement(partNo);
                                    totalParts.addElement(ordQty);
                                    ////Collections.sort(totalParts);
                                    session.putValue("cartItems", totalParts);
                                }
                                else
                                {
                                    ps.println("Cannot" + "$#" + "");

                                }
                            }
                            else
                            {
                                ps.println("Already" + "$#-");
                            }

                    }
                    else
                    {
                        ps.println("Failure" + "$#-");
                    }

                    rs.close();
                    stmt.close();
                    //con.close();

                    return;
                }






                //$2 **********
                if (totalParts.indexOf(partNo) == -1)
                {
                    if (!partNo.toUpperCase().startsWith("TBA"))
                    {
                        totalParts.addElement(partNo);
                        totalParts.addElement(ordQty);
                    }

                    if (flag.equals("addOrderAjax"))
                    {
                        if (!partNo.toUpperCase().startsWith("TBA"))
                        {
                            ps.println("PART NO: \"" + partNo + "\" ADDED TO CART");
                        }
                        else
                        {
                            ps.println("PART CANNOT BE ADDED TO CART");
                        }
                    }
                    else
                    {

                        if (!partNo.toUpperCase().startsWith("TBA"))
                        {
                            message = "PART NO: \"" + partNo + "\" ADDED TO CART.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Go to \"Cart\"  option to Save the selected parts. <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* All selected parts will be removed from Cart<br>if not saved before logging out.";
                            object_pageTemplate.ShowMsg(ps, "SUCCESS", message, "NO", "", "", "javascript:history.go(-1);", imagesURL);
                        }
                        else
                        {
                            message = "PART CANNOT BE ADDED TO CART";
                            object_pageTemplate.ShowMsg(ps, "FAILURE", message, "NO", "", "", "javascript:history.go(-1);", imagesURL);
                        }
                    }
                    //object_pageTemplate.ShowMsg(ps, "SUCCESS", "PART NO: " + partNo + " ADDED TO ORDER Go to View Order  option to Save the selected parts. * All selected parts will be removed from Order if not saved before logging out.", "NO", "", "", "", "javascript:history.go(-1)", imagesURL);
                    //ps.println("<center>PART NO: <b>" + partNo + "</b> ADDED TO CART.<br>");

                }
                else
                {
                    if (flag.equals("addOrderAjax"))
                    {
                        ps.println("PART NO: \"" + partNo + "\" ALREADY EXISTS IN CART");
                    }
                    else
                    {
                        message = "PART NO: \"" + partNo + "\" ALREADY EXISTS IN CART<br>";
                        object_pageTemplate.ShowMsg(ps, "FAILURE", message, "NO", "", "", "javascript:history.go(-1);", imagesURL);
                    }
                }
                //Collections.sort(totalParts);
                session.putValue("cartItems", totalParts);
                if (!flag.equals("addOrderAjax"))
                {
                    ps.println(footer);
                }

            }
            else
            {

                if (flag.equals("addOrderAjax"))
                {
                    ps.println("SESSION EXPIRED.");
                }
                else
                {
                   object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();


        }
        finally
        {
            ps.close();

        }
    }
}
 //object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
          //  }