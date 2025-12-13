package viewEcat.comEcat;

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

/*
File Name: View_users_info.java
PURPOSE: To view and edit the personal information of selected user.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Shivani Chauhan		$$0 Created
 */

import authEcat.UtilityMapkeys1;

public class View_users_info extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());         PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");

        try
        {
            ///////////////////////////// CREATE SESSION /////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            Connection conn = holder.getConnection();



            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            if (session_id.equals(getSession))
            {
                String dealerCode = req.getParameter("dealerCode");
                Connection conn_cat;
                //Statement stmt_cat;
                PreparedStatement stmt_cat = null;
                ResultSet rs;

                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                conn_cat = holder.getConnection();
                //stmt_cat = conn_cat.createStatement();

                String dealerType = "";
                String delName = "";
                String delAddress1 = "";
                String delAddress2 = "";
                String delCity = "";
                String delState = "";
                String delContact = "";
                String delMail1 = "";
                String delMail2 = "";
                String delMobile = "";
                String dealerCountry = "";
                String pincode = "";

                //rs = stmt_cat.executeQuery("Select * from UM_user_check where user_id='" + dealerCode + "'");
                String query = ("Select * from UM_user_check(NOLOCK) where user_id='" + dealerCode + "'");
                stmt_cat = conn_cat.prepareStatement(query);
                rs = stmt_cat.executeQuery();
                if (rs.next())
                {
                    dealerType = rs.getString(3);
                    delName = rs.getString(6);
                    delAddress1 = rs.getString(7);
                    delAddress2 = rs.getString(8);
                    delCity = rs.getString(9);
                    delState = rs.getString(10);
                    delContact = rs.getString(11);
                    delMail1 = rs.getString(12);
                    delMail2 = rs.getString(13);
                    delMobile = rs.getString(14);
                    dealerCountry = rs.getString(18);
                    pincode = rs.getString(19);
                }
                rs.close();

                EcatAuthorisation ecatAuthorisation = new EcatAuthorisation();
                Vector userFunctionalities = ecatAuthorisation.getUserFunctionalties(conn, dealerType);

                ps.println("<html>");
                ps.println("<head>");
                ps.println("<title>");
                ps.println(""+UtilityMapkeys1.tile1+"</title>");
                ps.println("<meta http-equiv=Content-Type content=text/html; charset=iso-8859-1>");
                ps.println("<style type=text/css>");
                ps.println("<!--");
                ps.println("* { font-family: Helvetica; font-size: 10pt; }");
                ps.println("  .submit-button{width:150px;height:20px;color:#ffffff;background-color:#003399;border:1px solid #666666;font-size:11px;}");
                ps.println(".star{font-family:Arial, Helvetica, sans-serif;font-size:18px;font-weight:bold;color:red;}");
                ps.println(".text{font-family:Arial, Helvetica, sans-serif;font-size:12px;padding:0 0 0 5px;}");
                ps.println(" a:link {				color: #000000;				text-decoration: none; }");
                ps.println(" a:visited { color: #000000;				text-decoration: none;		  }	");
                ps.println("		-->");
                ps.println("</style>");

                ps.println("<script language=JavaScript type=text/JavaScript>");

                ps.println("function trim(s)");
                ps.println("{");
                ps.println("		return rtrim(ltrim(s));");
                ps.println("}");

                ps.println("function ltrim(s)");
                ps.println("{");
                ps.println("		var l=0;");
                ps.println("		while(l < s.length && s.charAt(l) == ' ')");
                ps.println("		{	l++; }");
                ps.println("			return s.substring(l, s.length);");
                ps.println("	}");

                ps.println("function rtrim(s)");
                ps.println("{");
                ps.println("		var r=s.length -1;");
                ps.println("		while(r > 0 && s.charAt(r) == ' ')");
                ps.println("		{	r-=1;	}");
                ps.println("		return s.substring(0, r+1);");
                ps.println("}");

                ps.println("function enableCity()");
                ps.println("{");
                ps.println("		if(document.contact.dealerCity.value == 'Other')");
                ps.println("		{");
                ps.println("			document.contact.dealerCity1.disabled = false");
                ps.println("			document.contact.dealerCity1.style.background = '#FFFFFF'");
                ps.println("			document.contact.dealerCity1.focus()");
                ps.println("			return;");
                ps.println("		}");
                ps.println("		else");
                ps.println("		{");
                ps.println("			document.contact.dealerCity1.value=''");
                ps.println("			document.contact.dealerCity1.style.background = '#E2EEFA'");
                ps.println("			document.contact.dealerCity1.disabled = true");
                ps.println("			return;");
                ps.println("		}");
                ps.println("}");

                ps.println("function enableState()");
                ps.println("{");
                ps.println("		if(document.contact.dealerState.value == 'Other')");
                ps.println("		{");
                ps.println("			document.contact.dealerState1.disabled = false");
                ps.println("			document.contact.dealerState1.style.background = '#FFFFFF'");
                ps.println("			document.contact.dealerState1.focus()");
                ps.println("			return;");
                ps.println("		}");
                ps.println("		else");
                ps.println("		{");
                ps.println("			document.contact.dealerState1.value=''");
                ps.println("			document.contact.dealerState1.style.background = '#E2EEFA'");
                ps.println("			document.contact.dealerState1.disabled = true");
                ps.println("			return;");
                ps.println("		}");
                ps.println("}");

                ps.println("function enableCountry()");
                ps.println("{");
                ps.println("		if(document.contact.dealerCountry.value == 'Other')");
                ps.println("		{");
                ps.println("			document.contact.dealerCountry1.disabled = false");
                ps.println("			document.contact.dealerCountry1.style.background = '#FFFFFF'");
                ps.println("			document.contact.dealerCountry1.focus()");
                ps.println("			return;");
                ps.println("		}");
                ps.println("		else");
                ps.println("		{");
                ps.println("			document.contact.dealerCountry1.value=''");
                ps.println("			document.contact.dealerCountry1.style.background = '#E2EEFA'");
                ps.println("			document.contact.dealerCountry1.disabled = true");
                ps.println("			return;");
                ps.println("		}");
                ps.println("}");


                ps.println("function validateAddress()");
                ps.println("{");
                ps.println("		if((document.contact.dealAddress1.value == '') && (document.contact.dealAddress2.value == ''))");
                ps.println("		{");
                ps.println("			return;");
                ps.println("		}");
                ps.println("		if(trim(document.contact.dealAddress1.value) == trim(document.contact.dealAddress2.value))");
                ps.println("		{");
                ps.println("			alert('Address1 Cannot Be Same As Address2.');");
                ps.println("			document.contact.dealAddress2.focus();");
                ps.println("			return;");
                ps.println("		}");
                ps.println("}");

                ps.println("function validateEmail(str)");
                ps.println("{");
                ps.println("		if(str.value == '')");
                ps.println("		{");
                ps.println("			return;");
                ps.println("		}");
                ps.println("		else if(echeck(str))");
                ps.println("		{");
                ps.println("			if(trim(document.contact.dealerMail1.value) == trim(document.contact.dealerMail2.value))");
                ps.println("			{");
                ps.println("				alert('E-Mail 1 Cannot Be Same As E-Mail 2.');");
                ps.println("				return;");
                ps.println("			}");
                ps.println("		}");
                ps.println("}");

                ps.println("function echeck(str)");
                ps.println("{");
                ps.println("		var at='@'");
                ps.println("		var dot='.'");
                ps.println("		var lat=str.value.indexOf(at)");
                ps.println("		var lstr=str.value.length");
                ps.println("		var ldot=str.value.indexOf(dot)");
                ps.println("		if(lstr==0)");
                ps.println("		{");
                ps.println("			return true;");
                ps.println("		}");
                ps.println("		if (str.value.indexOf(at)==-1)");
                ps.println("		{");
                ps.println("		    alert('Invalid E-Mail ID')");
//			ps.println("		    str.value='';");
                ps.println("			str.focus()");
                ps.println("		    return false");
                ps.println("		}");
                ps.println("		if (str.value.indexOf(at)==-1 || str.value.indexOf(at)==0 || str.value.indexOf(at)==lstr)");
                ps.println("		{");
                ps.println("		    alert('Invalid E-mail ID')");
                //		ps.println("		    str.value='';");
                ps.println("			str.focus()");
                ps.println("		   return false");
                ps.println("		}");
                ps.println("		if (str.value.indexOf(dot)==-1 || str.value.indexOf(dot)==0 || str.value.indexOf(dot)==lstr)");
                ps.println("		{");
                ps.println("		    alert('Invalid E-mail ID')");
//			ps.println("		    str.value='';");
                ps.println("			str.focus()");
                ps.println("		    return false");
                ps.println("		}");
                ps.println("		 if (str.value.indexOf(at,(lat+1))!=-1)");
                ps.println("		{");
                ps.println("		    alert('Invalid E-mail ID')");
//			ps.println("		    str.value='';");
                ps.println("			str.focus()");
                ps.println("		    return false");
                ps.println("		 }");
                ps.println("		 if (str.value.substring(lat-1,lat)==dot || str.value.substring(lat+1,lat+2)==dot)");
                ps.println("		{");
                ps.println("		    alert('Invalid E-mail ID')");
                //		ps.println("		    str.value='';");
                ps.println("			str.focus()");
                ps.println("		    return false");
                ps.println("		 }");
                ps.println("		 if (str.value.indexOf(dot,(lat+2))==-1){");
                ps.println("		    alert('Invalid E-mail ID')");
//			ps.println("		    str.value='';");
                ps.println("			str.focus()");
                ps.println("		    return false");
                ps.println("		 }");
                ps.println("		");
                ps.println("		 if (str.value.indexOf(' ')!=-1){");
                ps.println("		    alert('Invalid E-mail ID')");
//			ps.println("		    str.value='';");
                ps.println("			str.focus()");
                ps.println("		    return false");
                ps.println("		 }");
                ps.println(" 		 return true;");
                ps.println("	}");


                if (userFunctionalities.contains("20"))
                {
                    ps.println("function validate()");
                    ps.println("{ ");
                    ps.println("		if(!((document.contact.dealerMail1.value == '') && (document.contact.dealerMail2.value == '')))");
                    ps.println("		{");
                    ps.println("			if(!echeck(document.contact.dealerMail1))");
                    ps.println("			{");
                    ps.println("				return;");
                    ps.println("			}");
                    ps.println("			if(!echeck(document.contact.dealerMail2))");
                    ps.println("			{");
                    ps.println("				return;");
                    ps.println("			}");
                    ps.println("			if(trim(document.contact.dealerMail1.value) == trim(document.contact.dealerMail2.value))");
                    ps.println("			{");
                    ps.println("				alert('E-Mail 1 Cannot Be Same As E-Mail 2.');");
                    ps.println("				document.contact.dealerMail2.focus();");
                    ps.println("				return;");
                    ps.println("			}");
                    ps.println("		}");
                    ps.println("             var stripped = ((document.contact.dealerMobile.value.replace('-','')).replace(',','')).replace(' ','');  "   );
                    ps.println("		if(trim(document.contact.dealerMobile.value) == '')");
                    ps.println("		{");
                    ps.println("			alert('Please Enter Phone / Mobile .');");
                    ps.println("			return;");
                    ps.println("		}");
                     ps.println("		else if(isNaN(stripped))");
                    ps.println("		{");
                    ps.println("                    alert('Phone / Mobile Should Be Numeric Only.');");
                    ps.println("                    document.contact.dealerMobile.focus();");
                    ps.println("                    return;");
                    ps.println("		}");

                    ps.println("		var cnfrm = confirm('Do you want to save the personal information?')");
                    ps.println("		if(cnfrm)");
                    ps.println("		{");
                    ps.println("			document.contact.submit();");
                    ps.println("		}");
                    ps.println("}");

                }
                else
                {
                    ps.println("function validate()");
                    ps.println("{ ");
                    ps.println("		if(trim(document.contact.dealName.value) == '')");
                    ps.println("		{");
                    ps.println("			alert('Please Enter Dealer Name.');");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		if(trim(document.contact.dealContact.value) == '')");
                    ps.println("		{");
                    ps.println("			alert('Please Enter Dealer Contact Person.');");
                    ps.println("			return;");
                    ps.println("		}");

                    ps.println("		if(!((document.contact.dealAddress1.value == '') && (document.contact.dealAddress2.value == '')))");
                    ps.println("		{");
                    ps.println("			if(trim(document.contact.dealAddress1.value) == trim(document.contact.dealAddress2.value))");
                    ps.println("			{");
                    ps.println("				alert('Address1 Cannot Be Same As Address2.');");
                    ps.println("				document.contact.dealAddress2.focus();");
                    ps.println("				return;");
                    ps.println("			}");
                    ps.println("		} ");

                    ps.println("		if(document.contact.dealerCity.value == 'Select')");
                    ps.println("		{");
                    ps.println("			alert('Please Select City.');");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		else if(document.contact.dealerCity.value == 'Other')");
                    ps.println("		{");
                    ps.println("			document.contact.dealerCity1.disabled = false");
                    ps.println("			document.contact.dealerCity1.style.background = '#FFFFFF'");
                    ps.println("			if(trim(document.contact.dealerCity1.value) == '')");
                    ps.println("			{");
                    ps.println("				alert('Please Enter City.');");
                    ps.println("				document.contact.dealerCity1.focus()");
                    ps.println("				return;");
                    ps.println("			}");
                    ps.println("		}");


                    ps.println("		if(document.contact.dealerState.value == 'Select')");
                    ps.println("		{");
                    ps.println("			alert('Please Select State.');");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		else if(document.contact.dealerState.value == 'Other')");
                    ps.println("		{");
                    ps.println("			document.contact.dealerState1.disabled = false");
                    ps.println("			document.contact.dealerState1.style.background = '#FFFFFF'");
                    ps.println("			if(trim(document.contact.dealerState1.value) == '')");
                    ps.println("			{");
                    ps.println("				alert('Please Enter State.');");
                    ps.println("				document.contact.dealerState1.focus()");
                    ps.println("				return;");
                    ps.println("			}");
                    ps.println("		}");

                    ps.println("		if(document.contact.dealerCountry.value == 'Select')");
                    ps.println("		{");
                    ps.println("			alert('Please Select Country.');");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		else if(document.contact.dealerCountry.value == 'Other')");
                    ps.println("		{");
                    ps.println("			document.contact.dealerCountry1.disabled = false");
                    ps.println("			document.contact.dealerCountry1.style.background = '#FFFFFF'");
                    ps.println("			if(trim(document.contact.dealerCountry1.value) == '')");
                    ps.println("			{");
                    ps.println("				alert('Please Enter Country.');");
                    ps.println("				document.contact.dealerCountry1.focus()");
                    ps.println("				return;");
                    ps.println("			}");
                    ps.println("		}");

                    ps.println("		if(trim(document.contact.pincode.value) == '')");
                    ps.println("		{");
                    ps.println("			alert('Please Enter Pin Code.');");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		if(document.contact.pincode.value.indexOf(' ')!=-1)");
                    ps.println("		{");
                    ps.println("			alert('Pin Code Should Be Numeric Only.');");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		else if(isNaN(document.contact.pincode.value))");
                    ps.println("		{");
                    ps.println("			alert('Pin Code Should Be Numeric Only.');");
                    ps.println("			return;");
                    ps.println("		}");


                    ps.println("		if(!((document.contact.dealerMail1.value == '') && (document.contact.dealerMail2.value == '')))");
                    ps.println("		{");
                    ps.println("			if(!echeck(document.contact.dealerMail1))");
                    ps.println("			{");
                    ps.println("				return;");
                    ps.println("			}");
                    ps.println("			if(!echeck(document.contact.dealerMail2))");
                    ps.println("			{");
                    ps.println("				return;");
                    ps.println("			}");
                    ps.println("			if(trim(document.contact.dealerMail1.value) == trim(document.contact.dealerMail2.value))");
                    ps.println("			{");
                    ps.println("				alert('E-Mail 1 Cannot Be Same As E-Mail 2.');");
                    ps.println("				document.contact.dealerMail2.focus();");
                    ps.println("				return;");
                    ps.println("			}");
                    ps.println("		}");

                    ps.println("		if(trim(document.contact.dealerMobile.value) == '')");
                    ps.println("		{");
                    ps.println("			alert('Please Enter Phone / Mobile .');");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		else");
                    ps.println("		{");
                    ps.println("			for(var i=0; i<document.contact.dealerMobile.value.length;i++)");
                    ps.println("			{");
                    ps.println("				var chrCode = document.contact.dealerMobile.value.charCodeAt(i);");
                    ps.println("				if((chrCode>=65) && (chrCode<=90))");
                    ps.println("				{");
                    ps.println("					alert('Phone / Mobile Should Not Have Any Alphabet.');");
                    ps.println("					return;");
                    ps.println("				}");
                    ps.println("				else if((chrCode>=97) && (chrCode<=122))");
                    ps.println("				{");
                    ps.println("					alert('Phone / Mobile Should Not Have Any Alphabet.');");
                    ps.println("					return;");
                    ps.println("				}");
                    ps.println("			}");
                    ps.println("		}");

                    ps.println("		var cnfrm = confirm('Do you want to save the personal information?')");
                    ps.println("		if(cnfrm)");
                    ps.println("		{");
                    ps.println("			document.contact.submit();");
                    ps.println("		}");
                    ps.println("}");
                }
                ps.println("</script>");

                ps.println("</head>");

                ps.println("<body  leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 >");
                ps.println("<table width=100% border=0 align=center cellpadding=0 cellspacing=0>");
                ps.println("<tr>");
                ps.println("<td height=350 valign=top>");
                ps.println("<div align=left>");


                Vector cities = new Vector();
                Vector states = new Vector();
                Vector countries = new Vector();

                if (!userFunctionalities.contains("20"))
                {
                    //rs = stmt_cat.executeQuery("Select * from GEN_cities(NOLOCK) order by city_name");
                    String sqlQuery = ("Select * from GEN_cities(NOLOCK) order by city_name");
                    stmt_cat = conn.prepareStatement(sqlQuery);
                    rs = stmt_cat.executeQuery();
                	while (rs.next())
                    {
                        cities.add(rs.getString(1));
                    }
                    rs.close();


                    //rs = stmt_cat.executeQuery("Select * from GEN_states(NOLOCK) order by state_name");
                    String sqlQuery1 = ("Select * from GEN_states(NOLOCK) order by state_name");
                    stmt_cat = conn.prepareStatement(sqlQuery1);
                    rs = stmt_cat.executeQuery();
                    while (rs.next())
                    {
                        states.add(rs.getString(1));
                    }
                    rs.close();

                   // rs = stmt_cat.executeQuery("Select * from GEN_countries(NOLOCK) order by country_name");
                   String sqlQuery3 = ("Select * from GEN_countries(NOLOCK) order by country_name");
                   stmt_cat = conn.prepareStatement(sqlQuery3);
                   rs = stmt_cat.executeQuery();
                    while (rs.next())
                    {
                        countries.add(rs.getString(1));
                    }
                    rs.close();
                }
                
                stmt_cat.close();


                if ((delAddress1 == null) || (delAddress1.equals("null")) || (delAddress1.equals(" ")) || (delAddress1.equals("")))
                {

                    delAddress1 = " ";

                }
                if ((delAddress2 == null) || (delAddress2.equals("null")) || (delAddress2.equals(" ")) || (delAddress2.equals("")))
                {

                    delAddress2 = " ";

                }
                if ((delCity == null) || (delCity.equals("null")) || (delCity.equals(" ")) || (delCity.equals("")))
                {
                    if (userFunctionalities.contains("20"))
                    {
                        delCity = "--";
                    }
                    else
                    {
                        delCity = "";
                    }
                }
                if ((delState == null) || (delState.equals("null")) || (delState.equals(" ")) || (delState.equals("")))
                {
                    if (userFunctionalities.contains("20"))
                    {
                        delState = "--";
                    }
                    else
                    {
                        delState = "";
                    }
                }
                if ((delContact == null) || (delContact.equals("null")) || (delContact.equals(" ")) || (delContact.equals("")))
                {
                    if (userFunctionalities.contains("20"))
                    {
                        delContact = "--";
                    }
                    else
                    {
                        delContact = "";
                    }
                }
                if ((delMail1 == null) || (delMail1.equals("null")) || (delMail1.equals(" ")) || (delMail1.equals("")))
                {
                    delMail1 = "";
                }
                if ((delMail2 == null) || (delMail2.equals("null")) || (delMail2.equals(" ")) || (delMail2.equals("")))
                {
                    delMail2 = "";
                }
                if ((delMobile == null) || (delMobile.equals("null")) || (delMobile.equals(" ")) || (delMobile.equals("")))
                {
                    delMobile = "";
                }
                if ((dealerCountry == null) || (dealerCountry.equals("null")) || (dealerCountry.equals(" ")) || (dealerCountry.equals("")))
                {
                    if (userFunctionalities.contains("20"))
                    {
                        dealerCountry = "--";
                    }
                    else
                    {
                        dealerCountry = "";
                    }
                }
                if ((pincode == null) || (pincode.equals("null")) || (pincode.equals(" ")) || (pincode.equals("")))
                {
                    if (userFunctionalities.contains("20"))
                    {
                        pincode = "--";
                    }
                    else
                    {
                        pincode = "";
                    }
                }


                ps.println("<br>");
                ps.println("<form name=\"contact\" method=\"post\" action=\"ChangeInfo_1\">");
                ps.println("  <table width=\"600\"  border=\"1\" align=\"center\" cellpadding=\"3\" cellspacing=\"1\" bordercolor=#CCCCCC>");
                ps.println("<tr height=\"25\" bgcolor =#003399 >");
                ps.println("<td colspan = 2 align = center><b>");
                ps.println("<font color=#FFFFFF>	");
                ps.println("CHANGE PERSONAL INFORMATION OF \"" + dealerCode + "\'");
                ps.println("</font></td>");
                ps.println("</tr>");

                ps.println("    <tr >");
                ps.println("      <td height=\"24\" class=text>");
                ps.println("<b>Dealer Code </td>");
                ps.println("      <td class=text>");
                ps.println(dealerCode);
                ps.println("<input type=Hidden name=dealerCode value='" + dealerCode + "'");
                ps.println("</font>");
                ps.println("</td>");
                ps.println("    </tr>");

                ps.println("    <tr >");



                if (userFunctionalities.contains("20"))
                {
                    ps.println("      <td height=\"24\" class=text>");
                    ps.println("<b>Dealer Name</font>");
                    ps.println("</td>");

                    ps.println("    <td align = left class=text> " + delName);
                    ps.println(" </td>");
                }
                else
                {
                    ps.println("      <td height=\"24\" class=text>");
                    ps.println("<b>Dealer Name <span class=\"star\"> *</span>");
                    ps.println("</td class=text>");
                    ps.println("      <td align = left><input type=\"text\" name=\"dealName\"  maxlength=200 size =30 value = '" + delName + "'></td>");
                }
                ps.println("    </tr>");
                ps.println("    <tr >");
                if (userFunctionalities.contains("20"))
                {
                    ps.println("      <td height=\"24\" class=text>");
                    ps.println("<b>Contact Person</font>");
                    ps.println("</td>");

                    ps.println("    <td align = left class=text> " + delContact);
                    ps.println(" </td >");
                }
                else
                {
                    ps.println("      <td height=\"24\" class=text><b>Dealer Contact Person <span class=\"star\"> *</span></td>");
                    ps.println("      <td align = left class=text><input type=\"text\" name=\"dealContact\" size =30 maxlength=100 value = '" + delContact + "'></td>");
                }
                ps.println("    </tr>");
                ps.println("    <tr >");
                if (userFunctionalities.contains("20"))
                {
                    ps.println("      <td height=\"24\" class=text>");
                    ps.println("<b>Address1</font>");
                    ps.println("</td>");

                    ps.println("    <td align = left class=text> " + delAddress1);
                    ps.println(" </td>");
                }
                else
                {
                    ps.println("      <td height=\"24\" align = left class=text><b>Address1</font></td>");
                    ps.println("      <td align=left class=text><input type = text size =30 name='dealAddress1' maxlength=200 value ='" + delAddress1 + "'></td>");
                }
                ps.println("    </tr>");
                ps.println("    <tr >");
                if (userFunctionalities.contains("20"))
                {
                    ps.println("      <td height=\"24\" class=text>");
                    ps.println("<b>Address2</font>");
                    ps.println("</td>");

                    ps.println("    <td align = left class=text> " + delAddress2);
                    ps.println(" </td>");
                }
                else
                {
                    ps.println("      <td height=\"24\" align = left class=text><b>Address2</font></td>");
                    ps.println("      <td align=left class=text><input type = text size =30 name='dealAddress2' maxlength=200 value ='" + delAddress2 + "' onblur=validateAddress()></td>");
                }
                ps.println("    </tr>");
                ps.println("    <tr >");
                if (userFunctionalities.contains("20"))
                {
                    ps.println("      <td height=\"24\" align = left class=text><b>City </font></td>");
                    ps.println("    <td align = left class=text> " + delCity);
                    ps.println(" </td>");
                }
                else
                {
                    ps.println("      <td height=\"24\" align = left class=text><b>City <span class=\"star\"> *</span></td>");
                    ps.println("      <td align=left class=text>");
                    ps.println("				<select name='dealerCity' style='width:215px' onchange=enableCity()>");

                    boolean flag = false;

                    if (cities.contains(delCity))
                    {
                        flag = true;
                    }


                    ps.println("<option value='Select' selected>Select City</option>");

                    for (int i = 0; i < cities.size(); i++)
                    {
                        if (delCity.equals("" + cities.elementAt(i)))
                        {
                            ps.println("<option value='" + cities.elementAt(i) + "' selected>" + cities.elementAt(i) + "</option>");
                        }
                        else if (("" + cities.elementAt(i)).equals("Other"))
                        {
                        //			ps.println("<option value='"+cities.elementAt(i)+"' selected><b>......."+cities.elementAt(i)+"....</b></option>");
                        }
                        else
                        {
                            ps.println("<option value='" + cities.elementAt(i) + "' >" + cities.elementAt(i) + "</option>");
                        }
                    }

                    if (flag)
                    {
                        ps.println("<option value='Other'><strong>.......Other.......</strong></option>");
                    }
                    else
                    {
                        ps.println("<option value='Other' selected><strong>.......Other.......</strong></option>");
                    }

                    ps.println("</select>");
                    if (flag)
                    {
                        ps.println("If Other, <input type = text size =15 name='dealerCity1'  style='background-color:#E2EEFA' maxlength=100 value ='' disabled>");
                    }
                    else
                    {
                        ps.println("If Other, <input type = text size =15 name='dealerCity1'  style='background-color:#FFFFFF' maxlength=100 value ='" + delCity + "' enabled>");
                    }
                    ps.println("		</td>");
                }
                ps.println("    </tr>");

                ps.println("    <tr >");
                if (userFunctionalities.contains("20"))
                {
                    ps.println("      <td height=\"24\" align = left class=text><b>State </font></td>");
                    ps.println("    <td align = left class=text> " + delState);
                    ps.println(" </td>");
                }
                else
                {
                    ps.println("      <td height=\"24\" align = left class=text><b>State <span class=\"star\"> *</span></td>");
                    ps.println("      <td align=left class=text>");
                    //			ps.println("				<input type = text size =30 name='dealerState' value ='"+dealerState+"'>");
                    ps.println("				<select name='dealerState'  style='width:215px' onchange=enableState()>");

                    boolean flag = false;

                    if (states.contains(delState))
                    {
                        flag = true;
                    }

                    ps.println("<option value='Select' selected>Select State</option>");

                    for (int i = 0; i < states.size(); i++)
                    {
                        if (delState.equals("" + states.elementAt(i)))
                        {
                            ps.println("<option value='" + states.elementAt(i) + "' selected>" + states.elementAt(i) + "</option>");
                        }
                        else if (("" + states.elementAt(i)).equals("Other"))
                        {
                        //			ps.println("<option value='"+states.elementAt(i)+"' selected><b>......."+states.elementAt(i)+"....</b></option>");
                        }
                        else
                        {
                            ps.println("<option value='" + states.elementAt(i) + "' >" + states.elementAt(i) + "</option>");
                        }
                    }

                    if (flag)
                    {
                        ps.println("<option value='Other'><b>.......Other.......</b></option>");
                    }
                    else
                    {
                        ps.println("<option value='Other' selected><b>.......Other.......</b></option>");
                    }

                    ps.println("</select>");
                    if (flag)
                    {
                        ps.println("If Other, <input type = text size =15 name='dealerState1'  style='background-color:#E2EEFA' maxlength=100 value ='' disabled>");
                    }
                    else
                    {
                        ps.println("If Other, <input type = text size =15 name='dealerState1' style='background-color:#FFFFFF' maxlength=100 value ='" + delState + "' enabled>");
                    }
                    ps.println("");
                    ps.println("		</td>");
                }
                ps.println("    </tr>");

                ps.println("    <tr >");

                if (userFunctionalities.contains("20"))
                {
                    ps.println("      <td height=\"24\" align = left class=text><b>Country </font></td>");
                    ps.println("    <td align = left class=text> " + dealerCountry);
                    ps.println(" </td>");
                }
                else
                {
                    ps.println("      <td height=\"24\" align = left class=text><b>Country <span class=\"star\"> *</span></td>");
                    ps.println("      <td align=left class=text>");
                    //			ps.println("				<input type = text size =30 name='dealerCountry' value ='"+dealerCountry+"'>");
                    ps.println("				<select name='dealerCountry' style='width:215px' onchange=enableCountry()>");

                    boolean flag = false;
                    if (countries.contains(dealerCountry))
                    {
                        flag = true;
                    }

                    ps.println("<option value='Select' selected>Select Country</option>");

                    for (int i = 0; i < countries.size(); i++)
                    {
                        if (dealerCountry.equals("" + countries.elementAt(i)))
                        {
                            ps.println("<option value='" + countries.elementAt(i) + "' selected>" + countries.elementAt(i) + "</option>");
                        }
                        else if (("" + countries.elementAt(i)).equals("Other"))
                        {
                        //			ps.println("<option value='"+countries.elementAt(i)+"' selected><b>......."+countries.elementAt(i)+"....</b></option>");
                        }
                        else
                        {
                            ps.println("<option value='" + countries.elementAt(i) + "' >" + countries.elementAt(i) + "</option>");
                        }
                    }

                    if (flag)
                    {
                        ps.println("<option value='Other'><strong>.......Other.......</strong></option>");
                    }
                    else
                    {
                        ps.println("<option value='Other' selected><strong>.......Other.......</strong></option>");
                    }

                    ps.println("</select>");

                    if (flag)
                    {
                        ps.println("If Other, <input type = text size =15 name='dealerCountry1'  style='background-color:#E2EEFA' maxlength=100 value ='' disabled>");
                    }
                    else
                    {
                        ps.println("If Other, <input type = text size =15 name='dealerCountry1'  style='background-color:#FFFFFF' maxlength=100 value ='" + dealerCountry + "' enabled>");
                    }

                    ps.println("		</td>");
                }
                ps.println("    </tr>");

                ps.println("	<tr >");
                if (userFunctionalities.contains("20"))
                {
                    ps.println("      <td height=\"24\" align = left class=text><b>pincode </font></td>");
                    ps.println("    <td align = left class=text> " + pincode);
                    ps.println(" </td>");
                }
                else
                {
                    ps.println("		<td class=text>");
                    ps.println("			<b>");
                    ps.println("				Pin Code <span class=\"star\"> *");
                    ps.println("		</td>");
                    ps.println("		<td class=text>");
                    ps.println("			<input type=\"text\" name=\"pincode\"  size =30 maxlength=50 value = '" + pincode + "'>");
                    ps.println("		</td>");
                }
                ps.println("	</tr>");


                ps.println("	<tr >");
                ps.println("		<td class=text>");
                ps.println("			<b>");
                ps.println("				E-Mail 1");
                ps.println("		</td>");
                ps.println("		<td class=text>");
                ps.println("			<input type=\"text\" name=\"dealerMail1\"  size =30 maxlength=100 value = '" + delMail1 + "'  onblur=validateEmail(this) >");
                ps.println("		</td>");
                ps.println("	</tr>");
                ps.println("	<tr >");
                ps.println("		<td class=text>");
                ps.println("			<b>");
                ps.println("				E-Mail 2");
                ps.println("		</td>");
                ps.println("		<td class=text>");
                ps.println("			<input type=\"text\" name=\"dealerMail2\"  size =30 maxlength=100 value = '" + delMail2 + "'  onblur=validateEmail(this) >");
                ps.println("		</td>");
                ps.println("	</tr>");
                ps.println("	<tr >");
                ps.println("		<td class=text>");
                ps.println("			<b>");
                ps.println("				Phone / Mobile <span class=\"star\"> * </span>");
                ps.println("		</td>");
                ps.println("		<td class=text>");
                ps.println("			<input type=\"text\" name=\"dealerMobile\"  size =30 maxlength=100 value = '" + delMobile + "'>");
                ps.println("		</td>");
                ps.println("	</tr>");


                ps.println("	<tr>");
                ps.println("		<td colspan = 2 align = center>");
                ps.println("			<input type=\"button\" name=\"Submit\" value=\"Save Changes\" onclick=validate()>");
                ps.println("		</td>");
                ps.println("	</tr>");
                ps.println("  </table>");

                //			ps.println("<br>");

                ps.println("<input type=Hidden name=dealerType value='" + dealerType + "'");


                ps.println("  <center>");
                ps.println("<table align=center width=600 border=0 cellspacing=1 cellpadding=0 bordercolor=#CCCCCC>");
                ps.println("<tr> ");
                //ps.println("<td> ");
                ps.println("<td style=\"color:#FF0000; font-family:Arial, Helvetica, sans-serif; font-size:10px; padding-left:5px; font-weight:800;\"><strong class=\"star\">*</strong>  <strong>Field Marked (*) Is Mandatory</strong>");
                ps.println("</td> ");
                ps.println("</tr> ");
                ps.println("</table>");
                ps.println("</form>");
                ps.println("</html>");
            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
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
