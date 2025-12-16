package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: ChangeInfo_1.java
PURPOSE: To update the personal Info in the database.
HISTORY:
DATE				BUILD		AUTHOR				MODIFICATIONS
NA					v3.4		Shivani Chauhan		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class ChangeInfo_1 extends HttpServlet
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
            String session_id = session.getId();
            String message = null;

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

            String imagesURL = object_pageTemplate.imagesURL();

            ps.println("<HTML>");
            ps.println("<HEAD>");
            ps.println("<TITLE>" + UtilityMapkeys1.tile1 + "</TITLE>");
            ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
            ps.println("</head>");



            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession))
            {

                Connection conn_cat = new dbConnection.dbConnection().getDbConnection();

                String dealName = req.getParameter("dealName");
                String dealAddress1 = req.getParameter("dealAddress1");
                String dealAddress2 = req.getParameter("dealAddress2");
                String dealerCity = req.getParameter("dealerCity");
                String dealerCity1 = req.getParameter("dealerCity1");
                String dealerState = req.getParameter("dealerState");
                String dealerState1 = req.getParameter("dealerState1");
                String dealerCountry = req.getParameter("dealerCountry");
                String dealerCountry1 = req.getParameter("dealerCountry1");
                String dealContact = req.getParameter("dealContact");
                String dealerMail1 = req.getParameter("dealerMail1");
                String dealerMail2 = req.getParameter("dealerMail2");
                String dealerMobile = req.getParameter("dealerMobile");
                String dealerCode = req.getParameter("dealerCode");
                String pincode = req.getParameter("pincode");
                String dealerType = req.getParameter("dealerType");
                String dealerPhone = req.getParameter("dealerPhone");


                if ((dealerCode == null) || (dealerCode.equals("")))
                {
                    dealerCode = userCode;
                    dealerType = getType;
                }

                EcatAuthorisation ecatAuthorisation = new EcatAuthorisation();
                PreparedStatement psmt;

                if (dealerCity != null && dealerCity.equals("Other"))
                {
                    dealerCity = dealerCity1;
                }
                if (dealerState != null && dealerState.equals("Other"))
                {
                    dealerState = dealerState1;
                }
                if (dealerCountry != null && dealerCountry.equals("Other"))
                {
                    dealerCountry = dealerCountry1;
                }
                psmt = conn_cat.prepareStatement("update UM_user_check SET NAME = ?, CONTACT=?, ADDRESS1=?, ADDRESS2=?, City=?, State=?, email1=?, email2=?, mobile=?, country=?, pincode=?,phone_no=? where user_id=?");
                psmt.setString(1, dealName);
                psmt.setString(2, dealContact);
                psmt.setString(3, dealAddress1);
                psmt.setString(4, dealAddress2);
                psmt.setString(5, dealerCity);
                psmt.setString(6, dealerState);
                psmt.setString(7, dealerMail1);
                psmt.setString(8, dealerMail2);
                psmt.setString(9, dealerMobile);
                psmt.setString(10, dealerCountry);
                psmt.setString(11, pincode);
                psmt.setString(12, dealerPhone);
                psmt.setString(13, dealerCode);
                psmt.executeUpdate();

                conn_cat.commit();
                psmt.close();

                conn_cat.close();
                // ps.println(header);

                int width = 759;
                int height = 300;


                message = "Personal Information Updated";
                ps.println(object_pageTemplate.tableHeader4(message, width, height));
                // object_pageTemplate.ShowMsg(ps, "FAILURE", "NO MESSAGE AVAILABLE", "NO", "", "", "", imagesURL);

            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println(footer);
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
