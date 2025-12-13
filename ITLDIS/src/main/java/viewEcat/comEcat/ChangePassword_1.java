package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: ChangePassword_1.java
PURPOSE: Interface for changing the password.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */

import authEcat.UtilityMapkeys1;



public class ChangePassword_1 extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());         PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String getType = (String) session.getValue("user_type");
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

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();


            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            

            ps.println(header);

            if (session_id.equals(getSession)) 
            {
               
                ps.println("<HTML>");
                ps.println("<HEAD>");
                ps.println("<TITLE>"+UtilityMapkeys1.tile1+"</TITLE>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("<script language=JavaScript type=text/JavaScript>");


                ps.println("	function validate()");
                ps.println("	 { ");
                ps.println("			var check = 0");

                ps.println("			var oldPassword = document.form1.oldPassword.value ;");
                ps.println("			var newPassword_1 = document.form1.newPassword_1.value ;");
                ps.println("			var newPassword_2 = document.form1.newPassword_2.value ;");

                ps.println("			if(oldPassword == '')");
                ps.println("			{");
                ps.println("				alert('Please Enter Old Password.');");
                ps.println("				return;");
                ps.println("			}");

                ps.println("			if(newPassword_1 == '')");
                ps.println("			{");
                ps.println("				alert('Please Enter New Password.');");
                ps.println("				return;");
                ps.println("			}");

                ps.println("			if(newPassword_2 == '')");
                ps.println("			{");
                ps.println("				alert('Please Re-Enter New Password.');");
                ps.println("				return;");
                ps.println("			}");

                ps.println("			if(oldPassword == newPassword_1)");
                ps.println("			{");
                ps.println("				alert('New Password Cannot Be Same As Old Password.');");
                ps.println("				return;");
                ps.println("			}");

                ps.println("			if(newPassword_1 != newPassword_2)");
                ps.println("			{");
                ps.println("				alert('New Password Does Not Match With Repeat Password.');");
                ps.println("				return;");
                ps.println("			}");

                ps.println("			if(newPassword_1.indexOf(' ')!=-1)");
                ps.println("			{");
                ps.println("				alert('Space Is Not Allowed in New Password.');");
                ps.println("				return;");
                ps.println("			}");

                ps.println("			if(!isNaN(newPassword_1))");
                ps.println("			{");
                ps.println("				alert('New Password Should Be Alphanumeric.');");
                ps.println("				return;");
                ps.println("			}");

                ps.println("			if((newPassword_1.length)<8)");
                ps.println("			{");
                ps.println("				alert('New Password Cannot Be Less Than 8 Characters.');");
                ps.println("				return;");
                ps.println("			}");

                ps.println("			var checkNumeric = false;");
                ps.println("			var flag = false;");
                ps.println("			for(var i=0; i<newPassword_1.length;i++)");
                ps.println("			{");
                ps.println("				flag = false;");
                ps.println("				var chrCode = newPassword_1.charCodeAt(i);");
                ps.println("				if((chrCode>=48) && (chrCode<=57))");
                ps.println("				{");
                ps.println("					flag=true;");
                ps.println("					checkNumeric = true;");
                ps.println("				}");
                ps.println("				else if((chrCode>=65) && (chrCode<=90))");
                ps.println("				{");
                ps.println("					flag=true;");
                ps.println("				}");
                ps.println("				else if((chrCode>=97) && (chrCode<=122))");
                ps.println("				{");
                ps.println("					flag=true;");
                ps.println("				}");
                ps.println("				if(!flag)");
                ps.println("				{");
                ps.println("					alert('New Password Contains Invalid Characters.');");
                ps.println("					return;");
                ps.println("				}");
                ps.println("			}");

                ps.println("			if(!checkNumeric)");
                ps.println("			{");
                ps.println("				alert('New Password Should Be Alphanumeric.');");
                ps.println("				return;");
                ps.println("			}");

                ps.println("			var cnfrm = confirm('Do you want to change the password?')");
                ps.println("			if(cnfrm)");
                ps.println("			{");
                ps.println("				javascript:document.form1.submit()");
                ps.println("			}");
                ps.println("	 }");

                ps.println("</script>");
                

                ps.println("</HEAD>");
                ps.println("<BODY >");
                
                int width=759;
                int height=300;

                ps.println("<form action=ChangePassword_2 method=post name=form1>");
                ps.println(object_pageTemplate.tableHeader3("Change Password Console", width, height));


                ps.println("                 <table width=90% border=0 valign=top height=300 align=\"center\" cellspacing=0 cellpadding=0 bordercolor = #CCCCCC>");
                ps.println("                            <tbody>");
                ps.println("                                <tr height=25 valign=top>                       ");
                ps.println("              ");

                
                //ps.println("<tr>");
                ps.println("<td  style=\"padding-top:15px;\" class=\"links_txt\" >  ");
                ps.println("LOGIN");
                ps.println("</td>");
                ps.println("<td style=\"padding-top:15px;\" class=\"links_txt\" >");
                ps.println("<input  type=hidden name=login value = " + userCode + ">");
                ps.println(""+userCode);
                ps.println("</td>");
                ps.println("</tr>");

                ps.println("<tr height=25> ");
                ps.println("<td class=\"links_txt\">  ");
                ps.println("OLD PASSWORD");
                ps.println("</td>");
                ps.println("<td >");
                ps.println("<input style=\"width:154px\" type=password name=oldPassword>");
                ps.println("</td>");
                ps.println("</tr>");

                ps.println("<tr height=25> ");
                ps.println("<td class=\"links_txt\"  >  ");
                ps.println("NEW PASSWORD <span class=\"mandatory\"><font color=#FF3300>**</font></span>");
                ps.println("</td>");
                ps.println("<td class=\"links_txt\" >");
                ps.println("<input style=\"width:154px\" type=password name=newPassword_1 maxlength=15>");
                ps.println("</td>");
                ps.println("</tr>");

                ps.println("<tr height=25> ");
                ps.println("<td class=\"links_txt\" >  ");
                ps.println("REPEAT PASSWORD");
                ps.println("</td>");
                ps.println("<td class=\"links_txt\" >");
                ps.println("<input style=\"width:154px\" type=password name=newPassword_2 maxlength=15>");
                ps.println("</td>");
                ps.println("</tr>");

                ps.println("<tr height=25> ");
                ps.println("<td  class=\"links_txt\" align=left colspan = 2> ");
                ps.println("<input   type=button name=Submit value=Submit onClick = validate()>");
                ps.println("</td>");
                ps.println("</tr>");

                //ps.println("</table>");

                //ps.println("<br>");

                //ps.println("<table width=90% border=0 height=300 align=\"center\" cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");
                //ps.println("                            <tbody>");
//                ps.println("                                <tr height=25 valign=top>                       ");
                ps.println("<tr> ");
                ps.println("<td colspan=2 class=\"links_txt\"> ");
                ps.println("<span class=\"mandatory\"><font color=#FF3300>** CRITERIA FOR NEW PASSWORD<font color=#FF3300></span><br>");
                //ps.println("</td> ");
                //ps.println("</tr> ");
                //ps.println("<tr height=25> ");//<td colspan=2 class=\"links_txt\">
                ps.println(" <span class=\"mandatory\"><font color=#FF3300>");
                ps.println("1. New Password Cannot Be Same As Old Password.<br>");
                ps.println("2. Password Should Be of Minimum 8 Characters.<br>");
                ps.println("3. Password Should Be Alphanumeric.<br>");
                ps.println("</font></span></td> ");
                ps.println("</tr> ");
                ps.println("                            </tbody>");
                ps.println("                        </table>");
//Footer

                ps.println(object_pageTemplate.tableFooter());

                //changes_01_02_2012
                ps.println("</form>");
                ps.println("</body>");
                ps.println("</html>");
            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
    }
}
