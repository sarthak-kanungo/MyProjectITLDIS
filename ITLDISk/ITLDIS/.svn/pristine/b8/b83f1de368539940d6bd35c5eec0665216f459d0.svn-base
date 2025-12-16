package viewEcat.comEcat;

/*
File Name: Authenticate_criteria.java
PURPOSE: It is used to divide the E-Catague window into four frames after successfull validation of criteria selected by
user to view e-Catalogue.
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
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import authEcat.UtilityMapkeys1;

public class Authenticate_criteria extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");

        res.setHeader("Cache-Control", "no-cache");
        res.setHeader("Expires", "-1");
        res.setHeader("Pragma", "no-cache");


        try
        {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
         //   String getType = (String) session.getValue("user_type");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String engineSeries = null;
            String engineModel = null;

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String orderRefNoString = object_pageTemplate.orderRefNoString();
            boolean serialNoFlag=false;

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession))
            {
                Connection conn = holder.getConnection();
                


        //        Statement st;
                ResultSet rs;

                //Statement st;
                  PreparedStatement pst = null;
 


          //      st = conn.createStatement();

                //st = conn.createStatement();
             

                java.sql.Date inputDate = new java.sql.Date(0);
                java.sql.Date buckleDate = new java.sql.Date(0);

                String input_serialNo = "";
                String modelNo = "";

               String date_OR_serial = req.getParameter("criteria");
               String qval = req.getParameter("q");

                //String mainQuery = "";

                if (date_OR_serial.equals("latest"))
                {
                    session.putValue("date_OR_serial", "latest");
                    Date date = new Date();
                    inputDate = new java.sql.Date(date.getTime());
                   
                    session.putValue("input_Date", inputDate);
                    session.putValue("modelNo", modelNo);
                    session.putValue("input_serialNo", input_serialNo);
                    session.putValue("buckleDate", buckleDate);

                //   mainQuery = "SELECT MAX(revision) FROM change_mgmt";
                }

                if (date_OR_serial.equals("serialNo"))
                {
                     String serialNo = req.getParameter("serialNo");
                    

                    serialNo = serialNo.trim();
                    if (serialNo == null || serialNo.equals(""))
                    {
                        object_pageTemplate.ShowMsg(ps, "FAILURE", "Enter Tractor Serial No.", "NO", "", "", "javascript:history.go(-1);", imagesURL);
                        return;
                    }
                    else
                    {
                       // rs = st.executeQuery("SELECT distinct VN.VEHICLE_NO,M.MODEL_NO,BUCKLE_UP_DATE,MC.ENGINE_SERIES,MC.ENGINE_MODEL FROM CAT_VEHICLE_SONO() VN, CAT_MODELS M,CAT_MODEL_CLASSIFICATION MC  where VN.VEHICLE_NO ='" + serialNo + "' AND VN.SO_NO = M.MODEL_NO and MC.MODEL_NO=M.MODEL_NO");
                        // Replace Statement with PreparedStatement

                    	String query = ("SELECT distinct VN.VEHICLE_NO,M.MODEL_NO,BUCKLE_UP_DATE,MC.ENGINE_SERIES,MC.ENGINE_MODEL FROM CAT_VEHICLE_SONO(NOLOCK) VN, CAT_MODELS(NOLOCK) M,CAT_MODEL_CLASSIFICATION(NOLOCK) MC  where VN.VEHICLE_NO ='" + serialNo + "' AND VN.SO_NO = M.MODEL_NO and MC.MODEL_NO=M.MODEL_NO");
                        pst = conn.prepareStatement(query);
                        rs = pst.executeQuery();
                      
                    	if (rs.next())

                        {
                            input_serialNo = rs.getString(1);
                            modelNo = rs.getString(2);
                            buckleDate = rs.getDate(3);
                              engineSeries = rs.getString(4);
                               engineModel = rs.getString(5);
                            serialNoFlag=true;
                        }
                        else
                        {
                            object_pageTemplate.ShowMsg(ps, "FAILURE", "Enter Valid Tractor Serial No.", "NO", "", "", "javascript:history.go(-1);", imagesURL);
                           return;
                        }
                        rs.close();

                        session.putValue("date_OR_serial", "serialNo");
                        session.putValue("input_Date", inputDate);
                        session.putValue("modelNo", modelNo);
                        session.putValue("input_serialNo", input_serialNo);
                        session.putValue("actual_serialNo", serialNo);
                        if (buckleDate == null)
                        {
                            buckleDate = new java.sql.Date(0);
                        }
                        session.putValue("buckleDate", buckleDate);

                    //        mainQuery = "SELECT MAX(revision) FROM change_mgmt WHERE serialNo_ID <= " + input_serialNo;
                    }
                }

                if (date_OR_serial.equals("model_date"))
                {
                    String formDate = req.getParameter("date");
                    String formMonth = req.getParameter("month");
                    String formYear = req.getParameter("year");

                    if (formDate.equals("DD") || formMonth.equals("MM") || formYear.equals("YYYY"))
                    {

                        object_pageTemplate.ShowMsg(ps, "FAILURE", "Select Valid Date Format.", "NO", "", "", "javascript:history.go(-1)", imagesURL);

                        return;
                    }
                    else
                    {
                        

                        String combinedDate = formMonth + "/" + formDate + "/" + formYear;
                        java.util.Date combinedDateNew = new java.util.Date(combinedDate);
                        inputDate = new java.sql.Date(combinedDateNew.getTime());
                        Date date = new Date();
                        if (date.before(combinedDateNew))
                        {
                            object_pageTemplate.ShowMsg(ps, "FAILURE", "Input Valid Date.", "NO", "", "", "javascript:history.go(-1)", imagesURL);

                           return;
                        }
                        session.putValue("date_OR_serial", "model_date");
                        session.putValue("input_Date", inputDate);
                        session.putValue("modelNo", modelNo);
                        session.putValue("input_serialNo", input_serialNo);
                        session.putValue("buckleDate", buckleDate);

                   
                    }
                }
                ps.println("<html>");
                ps.println("<head>");
                ps.println("      <title>");
                ps.println("         " + UtilityMapkeys1.tile1 + "");
                ps.println("      </title>");
                ps.println("</head>");
                
                ps.println("<frameset cols=\"*,1002,*\" scrolling=NO  noresize frameborder = 0 framespacing = 0 >");
                ps.println("<frame name=\"left\" src=\"emptyPage.html\" noresize scrolling=NO>");
                ps.println("<frameset rows=112,* frameborder=NO border=0 framespacing=0>");
                ps.println("		<frame src=TopHeader?q=" + qval + " name=topFrame scrolling=NO noresize>");
                if(serialNoFlag)
                   ps.println("			<frame name= right src=\"MainModels?engineSeries="+engineSeries+"&engineModel="+engineModel+"\"  margin=0 height=\"no\" marginwidth=\"0\" noresize>");
                else
                     ps.println("			<frame name= right src=View_EngineSeries  margin=0 height=\"no\" marginwidth=\"0\" noresize>");
//                ps.println("			<frame name= footer  src=\"Authenticate_criteriaimage\"  marginheight=\"no\" marginwidth=\"0\" scrolling=NO noresize>");
                //ps.println("                    <frame name=\"left\" src=\"emptyPage.html\" noresize scrolling=NO>");
                ps.println("		</frameset>");
                ps.println("<frame name=\"rightempty\"  src=\"emptyPage.html\" noresize scrolling=NO>");
                ps.println("</frameset>");

                ps.println("<noframes>");
                ps.println("  <body >");
                ps.println("  <p>This page uses frames, but your browser doesn't support them.</p>");

                ps.println("  </body>");
                ps.println("  </noframes>");
                ps.println("</frameset>");
                ps.println("</html>");
            }
            else
            {
               object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
//            	
            }
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
