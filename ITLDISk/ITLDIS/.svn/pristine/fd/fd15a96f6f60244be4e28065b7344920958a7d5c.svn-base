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
File Name: ChangeInfo.java
PURPOSE: Interface for entering the Personal Info.
HISTORY:
DATE				BUILD		AUTHOR				MODIFICATIONS
NA					v3.4		Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class ChangeInfo extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        Connection conn_cat = null;

        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
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

            String footer = "";
            footer = object_pageTemplate.footer();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            if (session_id.equals(getSession)) {
                conn_cat = new dbConnection.dbConnection().getDbConnection();

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));



                ////////////////////////////////////////////////////////////////////////////////
                /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////

                ps.println("<html>");
                ps.println("<head>");
                ps.println("<title>");
                ps.println("" + UtilityMapkeys1.tile1 + "</title>");
                ps.println("<meta http-equiv=Content-Type content=text/html; charset=iso-8859-1>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("<link href=\"" + imagesURL + "css/config.css\" type=\"text/css\" rel=\"stylesheet\">");
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
                ps.println("                str.focus()");
                ps.println("		    return false");
                ps.println("		}");
                ps.println("		if (str.value.indexOf(at)==-1 || str.value.indexOf(at)==0 || str.value.indexOf(at)==lstr)");
                ps.println("		{");
                ps.println("		    alert('Invalid E-mail ID')");
                ps.println("                str.focus()");
                ps.println("		   return false");
                ps.println("		}");
                ps.println("		if (str.value.indexOf(dot)==-1 || str.value.indexOf(dot)==0 || str.value.indexOf(dot)==lstr)");
                ps.println("		{");
                ps.println("		    alert('Invalid E-mail ID')");
                ps.println("                str.focus()");
                ps.println("		    return false");
                ps.println("		}");
                ps.println("		 if (str.value.indexOf(at,(lat+1))!=-1)");
                ps.println("		{");
                ps.println("		    alert('Invalid E-mail ID')");
                ps.println("                str.focus()");
                ps.println("		    return false");
                ps.println("		 }");
                ps.println("		 if (str.value.substring(lat-1,lat)==dot || str.value.substring(lat+1,lat+2)==dot)");
                ps.println("		{");
                ps.println("		    alert('Invalid E-mail ID')");
                ps.println("                str.focus()");
                ps.println("		    return false");
                ps.println("		 }");
                ps.println("		 if (str.value.indexOf(dot,(lat+2))==-1){");
                ps.println("		    alert('Invalid E-mail ID')");
                ps.println("                str.focus()");
                ps.println("		    return false");
                ps.println("		 }");
                ps.println("		");
                ps.println("		 if (str.value.indexOf(' ')!=-1){");
                ps.println("		    alert('Invalid E-mail ID')");
                ps.println("                str.focus()");
                ps.println("		    return false");
                ps.println("		 }");
                ps.println(" 		 return true;");
                ps.println("	}");

                if (userFunctionalities.contains("20")) {
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
                    ps.println("             var stripped = ((document.contact.dealerMobile.value.replace('-','')).replace(',','')).replace(' ','');  ");
                    ps.println("		if(trim(document.contact.dealerMobile.value) == '')");
                    ps.println("		{");
                    ps.println("			alert('Please Enter Mobile .');");
                    ps.println("                        document.contact.dealerMobile.focus();");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		else if(isNaN(stripped))");
                    ps.println("		{");
                    ps.println("                    alert('Mobile Should Be Numeric Only.');");
                    ps.println("                    document.contact.dealerMobile.focus();");
                    ps.println("                    return;");
                    ps.println("		}");

                    ps.println("		if(isNaN(trim(document.contact.dealerPhone.value)))");
                    ps.println("		{");
                    ps.println("                    alert('Phone Should Be Numeric Only.');");
                    ps.println("                    document.contact.dealerPhone.focus();");
                    ps.println("                    return;");
                    ps.println("		}");

                    ps.println("			var cnfrm = confirm('Do You Want To Save the Personal Information?')");
                    ps.println("			if(cnfrm)");
                    ps.println("			{");
                    ps.println("				javascript:document.contact.submit()");
                    ps.println("			}");
                    ps.println("}");

                } else {
                    ps.println("function validate()");
                    ps.println("{ ");
                    ps.println("		if(trim(document.contact.dealName.value) == '')");
                    ps.println("		{");
                    ps.println("			alert('Please Enter User Name.');");
                    ps.println("                        document.contact.dealName.focus();");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		if(trim(document.contact.dealContact.value) == '')");
                    ps.println("		{");
                    ps.println("			alert('Please Enter User Contact Person.');");
                    ps.println("                        document.contact.dealContact.focus();");
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
                    ps.println("			document.contact.pincode.focus()");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		if(document.contact.pincode.value.indexOf(' ')!=-1)");
                    ps.println("		{");
                    ps.println("			alert('Pin Code Should Be Numeric Only.');");
                    ps.println("			document.contact.pincode.focus()");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		else if(isNaN(document.contact.pincode.value))");
                    ps.println("		{");
                    ps.println("			alert('Pin Code Should Be Numeric Only.');");
                    ps.println("			document.contact.pincode.focus()");
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
                    ps.println("             var stripped = ((document.contact.dealerMobile.value.replace('-','')).replace(',','')).replace(' ','');  ");
                    ps.println("		if(trim(document.contact.dealerMobile.value) == '')");
                    ps.println("		{");
                    ps.println("			alert('Please Enter Phone / Mobile .');");
                    ps.println("			document.contact.dealerMobile.focus();");
                    ps.println("			return;");
                    ps.println("		}");
                    ps.println("		else if(isNaN(stripped))");
                    ps.println("		{");
                    ps.println("                    alert('Phone / Mobile Should Be Numeric Only.');");
                    ps.println("                    document.contact.dealerMobile.focus();");
                    ps.println("                    return;");
                    ps.println("		}");

                    ps.println("			var cnfrm = confirm('Do You Want To Save the Personal Information?')");
                    ps.println("			if(cnfrm)");
                    ps.println("			{");
                    ps.println("				javascript:document.contact.submit()");
                    ps.println("			}");
                    ps.println("	 }");
                }

                ps.println("</script>");

                ps.println("</head>");

                ps.println("<body  leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 >");

               // Statement stmt_cat;
                PreparedStatement stmt_cat = null;
                ResultSet rs;

                //stmt_cat = conn_cat.createStatement();
               

                Vector cities = new Vector();
                Vector states = new Vector();
                Vector countries = new Vector();


                //rs = stmt_cat.executeQuery("Select * from GEN_cities order by city_name");
                String query = ("Select * from GEN_cities(NOLOCK) order by city_name");
                stmt_cat = conn_cat.prepareStatement(query);
                rs = stmt_cat.executeQuery();
                while (rs.next()) {
                    cities.add(rs.getString(1));
                }
                rs.close();

               // rs = stmt_cat.executeQuery("Select * from GEN_states order by state_name");
                String query1 = ("Select * from GEN_states(NOLOCK) order by state_name");
                stmt_cat = conn_cat.prepareStatement(query1);
                rs = stmt_cat.executeQuery();
                while (rs.next()) {
                    states.add(rs.getString(1));
                }
                rs.close();

                //rs = stmt_cat.executeQuery("Select * from GEN_countries order by country_name");
                String query2 = ("Select * from GEN_countries(NOLOCK) order by country_name");
                stmt_cat = conn_cat.prepareStatement(query2);
                rs = stmt_cat.executeQuery();
                while (rs.next()) {
                    countries.add(rs.getString(1));
                }
                rs.close();


                String UserName = "";
                String UserAddress1 = "";
                String UserAddress2 = "";
                String dealerCity = "";
                String dealerState = "";
                String UserContact = "";
                String dealerMail1 = "";
                String dealerMail2 = "";
                String dealerMobile = "";
                String dealerCountry = "";
                String pincode = "";
                String dealerPhone = "";

                //rs = stmt_cat.executeQuery("Select * from UM_user_check where user_id='" + userCode + "'");
                String query3 = ("Select * from UM_user_check(NOLOCK) where user_id='" + userCode + "'");
                stmt_cat = conn_cat.prepareStatement(query3);
                rs = stmt_cat.executeQuery();
                
                if (rs.next()) {
                    UserName = rs.getString(6);
                    UserAddress1 = rs.getString(7);
                    UserAddress2 = rs.getString(8);
                    dealerCity = rs.getString(9);
                    dealerState = rs.getString(10);
                    UserContact = rs.getString(11);
                    dealerMail1 = rs.getString(12);
                    dealerMail2 = rs.getString(13);
                    dealerMobile = rs.getString(14);
                    dealerCountry = rs.getString(18);
                    pincode = rs.getString(19);
                    dealerPhone = rs.getString(21);
                }
                rs.close();
                stmt_cat.close();

                if (dealerPhone == null) {
                    dealerPhone = "--";
                }



                if ((UserAddress1 == null) || (UserAddress1.equals("null")) || (UserAddress1.equals(" ")) || (UserAddress1.equals(""))) {

                    UserAddress1 = " ";

                }
                if ((UserAddress2 == null) || (UserAddress2.equals("null")) || (UserAddress2.equals(" ")) || (UserAddress2.equals(""))) {

                    UserAddress2 = " ";

                }
                if ((dealerCity == null) || (dealerCity.equals("null")) || (dealerCity.equals(" ")) || (dealerCity.equals(""))) {
                    if (userFunctionalities.contains("20")) {
                        dealerCity = "--";
                    } else {
                        dealerCity = "";
                    }
                }
                if ((dealerState == null) || (dealerState.equals("null")) || (dealerState.equals(" ")) || (dealerState.equals(""))) {
                    if (userFunctionalities.contains("20")) {
                        dealerState = "--";
                    } else {
                        dealerState = "";
                    }
                }
                if ((UserContact == null) || (UserContact.equals("null")) || (UserContact.equals(" ")) || (UserContact.equals(""))) {
                    if (userFunctionalities.contains("20")) {
                        UserContact = "--";
                    } else {
                        UserContact = "";
                    }
                }
                if ((dealerMail1 == null) || (dealerMail1.equals("null")) || (dealerMail1.equals(" ")) || (dealerMail1.equals(""))) {
                    dealerMail1 = "";
                }
                if ((dealerMail2 == null) || (dealerMail2.equals("null")) || (dealerMail2.equals(" ")) || (dealerMail2.equals(""))) {
                    dealerMail2 = "";
                }
                if ((dealerMobile == null) || (dealerMobile.equals("null")) || (dealerMobile.equals(" ")) || (dealerMobile.equals(""))) {
                    dealerMobile = "";
                }
                if ((dealerCountry == null) || (dealerCountry.equals("null")) || (dealerCountry.equals(" ")) || (dealerCountry.equals(""))) {
                    if (userFunctionalities.contains("20")) {
                        dealerCountry = "--";
                    } else {
                        dealerCountry = "";
                    }
                }
                if ((pincode == null) || (pincode.equals("null")) || (pincode.equals(" ")) || (pincode.equals(""))) {
                    if (userFunctionalities.contains("20")) {
                        pincode = "--";
                    } else {
                        pincode = "";
                    }
                }
                int width = 759;
                int height = 300;

                ps.println("<form name=\"contact\" method=\"post\" action=\"ChangeInfo_1\">");
                ps.println(object_pageTemplate.tableHeader3("Edit Personal Information", width, height));
                ps.println("                 <table width=90% border=0 height=300 valign=top align=\"center\" cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");
                ps.println("                            <tbody>");
                ps.println("                                <tr valign=top>                       ");
                ps.println("              ");

                ps.println("      <td  style=\"padding-top:15px;\" class=links_txt>");
                ps.println("User Code </td>");
                ps.println("      <td  style=\"padding-top:15px;\" class=\"text\">");
                ps.println(userCode);
                ps.println("</td>");
                ps.println("    </tr>");
                ps.println("    <tr >");
//                if (userFunctionalities.contains("20"))
//                {
//                    ps.println("      <td  class=links_txt>");
//                    ps.println("User Name");
//                    ps.println("</td>");
//
//                    ps.println("    <td   class=links_txt> " + UserName);
//                    ps.println(" </td>");
//                }
//                else
                {
                    ps.println("      <td  class=links_txt>");
                    ps.println("User Name <span class=\"mandatory\"><font color=#FF3300> *</font></span>");
                    ps.println("</td>");
                    ps.println("    <td   class=\"links_txt\"><input type=\"text\" style=\"width:154px\" name=\"dealName\"   maxlength=200 value = '" + UserName + "' class=\"text\"></td>");

                }
                ps.println("    </tr>");
                ps.println("    <tr >");
//                if (userFunctionalities.contains("20"))
//                {
//                    ps.println("      <td  class=\"links_txt\">User Contact Person </td>");
//                    ps.println("    <td   class=\"links_txt\"> " + UserContact);
//                    ps.println(" </td>");
//                }
//                else
                {
                    ps.println("      <td  class=\"links_txt\" >Contact Person <span class=\"mandatory\"><font color=#FF3300> *</font></span></td>");
                    ps.println("      <td   class=\"links_txt\"><input type=\"text\" style=\"width:154px\" name=\"dealContact\"  maxlength=100 value = '" + UserContact + "' class=\"text\"></td>");
                }
                ps.println("    </tr>");
                ps.println("    <tr >");
                ps.println("      <td  class=\"links_txt\"  >Address1</td>");
//                if (userFunctionalities.contains("20"))
//                {
//                    ps.println("    <td   class=\"links_txt\"> " + UserAddress1);
//                    ps.println(" </td>");
//                }
//                else
                {
                    ps.println("      <td   class=\"links_txt\"><input type = text  style=\"width:154px\" name='dealAddress1' maxlength=200 value ='" + UserAddress1 + "' class=\"text\"></td>");
                }
                ps.println("    </tr>");
                ps.println("    <tr >");
                ps.println("      <td  class=\"links_txt\"  >Address2</td>");
//                if (userFunctionalities.contains("20"))
//                {
//                    ps.println("    <td   class=\"links_txt\"> " + UserAddress2);
//                    ps.println(" </td>");
//                }
//                else
                {
                    ps.println("      <td   class=\"links_txt\"><input type = text  style=\"width:154px\" name='dealAddress2' maxlength=200 value ='" + UserAddress2 + "' class=\"text\" onchange=validateAddress()></td>");
                }
                ps.println("    </tr>");

                ps.println("    <tr >");
                boolean flag = false;
//                if (userFunctionalities.contains("20"))
//                {
//                    ps.println("      <td  class=\"links_txt\"  >City </td>");
//                    ps.println("    <td   class=\"links_txt\"> " + dealerCity);
//                    ps.println(" </td>");
//                }
//                else
                {
                    ps.println("      <td  class=\"links_txt\"  >City <span class=\"mandatory\"><font color=#FF3300> *</font></span></td>");
                    ps.println("      <td   class=\"text\">");
                    ps.println("				<select name='dealerCity' style=\"width:154px\" class=\"text\" onchange=enableCity()>");


                    if (cities.contains(dealerCity)) {
                        flag = true;
                    }

                    ps.println("<option value='Select' selected>Select City</option>");

                    for (int i = 0; i < cities.size(); i++) {
                        if (dealerCity.equals("" + cities.elementAt(i))) {
                            ps.println("<option value='" + cities.elementAt(i) + "'selected>" + cities.elementAt(i) + "</option>");
                        } else if (!("" + cities.elementAt(i)).equals("Other")) {
                            ps.println("<option value='" + cities.elementAt(i) + "' >" + cities.elementAt(i) + "</option>");
                        }
                    }

                    if (flag) {
                        ps.println("<option value='Other'>.......Other.......</option>");
                    } else {
                        ps.println("<option value='Other' selected>.......Other.......</option>");
                    }

                    ps.println("</select>");
                    if (flag) {
                        ps.println("If Other, <input type = \"text\" style=\"width:154px\" name='dealerCity1'   maxlength=100  value ='' disabled class=\"text\">");
                    } else {
                        ps.println("If Other, <input style=\"width:154px\" type = \"text\"  name='dealerCity1'   maxlength=100 value ='" + dealerCity + "' enabled class=\"text\">");
                    }
                    ps.println("		</td>");
                    ps.println("    </tr>");


                }
                ps.println("    <tr >");
                if (userFunctionalities.contains("20")) {
                    ps.println("      <td  class=\"links_txt\">State </td>");

                    ps.println("    <td   class=\"links_txt\"> " + dealerState);
                    ps.println(" </td>");
                } else {
                    ps.println("      <td    class=\"links_txt\">State <span class=\"mandatory\"><font color=#FF3300> *</font></span></td>");
                    ps.println("      <td   class=\"text\">");
                    ps.println("				<select name='dealerState'  style=\"width:154px\" onchange=enableState() class=\"text\">");

                    flag = false;

                    if (states.contains(dealerState)) {
                        flag = true;
                    }

                    ps.println("<option value='Select' selected>Select State</option>");

                    for (int i = 0; i < states.size(); i++) {
                        if (dealerState.equals("" + states.elementAt(i))) {
                            ps.println("<option value='" + states.elementAt(i) + "' selected>" + states.elementAt(i) + "</option>");
                        } else if (!("" + states.elementAt(i)).equals("Other")) {
                            ps.println("<option value='" + states.elementAt(i) + "' >" + states.elementAt(i) + "</option>");
                        }
                    }

                    if (flag) {
                        ps.println("<option value='Other'>.......Other.......</option>");
                    } else {
                        ps.println("<option value='Other' selected>.......Other.......</option>");
                    }

                    ps.println("</select>");
                    if (flag) {
                        ps.println("If Other, <input type = text style=\"width:154px\" name='dealerState1'   maxlength=100 value ='' disabled class=\"text\">");
                    } else {
                        ps.println("If Other, <input type = text style=\"width:154px\" name='dealerState1'  maxlength=100 value ='" + dealerState + "' enabled class=\"text\">");
                    }
                    ps.println("");
                    ps.println("		</td>");
                }
                ps.println("    </tr>");

                ps.println("    <tr >");
                if (userFunctionalities.contains("20")) {
                    ps.println("      <td  class=\"links_txt\">Country </td>");
                    ps.println("    <td   class=\"links_txt\"> " + dealerCountry);
                    ps.println(" </td>");
                } else {
                    ps.println("      <td    class=\"links_txt\">Country <span class=\"mandatory\"><font color=#FF3300> *</font></span></td>");
                    ps.println("      <td   class=\"text\">");
                    ps.println("				<select name='dealerCountry' style=\"width:154px\" onchange=enableCountry() class=\"text\">");

                    flag = false;
                    if (countries.contains(dealerCountry)) {
                        flag = true;
                    }

                    ps.println("<option value='Select' selected>Select Country</option>");

                    for (int i = 0; i < countries.size(); i++) {
                        if (dealerCountry.equals("" + countries.elementAt(i))) {
                            ps.println("<option value='" + countries.elementAt(i) + "' selected>" + countries.elementAt(i) + "</option>");
                        } else if (!("" + countries.elementAt(i)).equals("Other")) {
                            ps.println("<option value='" + countries.elementAt(i) + "' >" + countries.elementAt(i) + "</option>");
                        }
                    }

                    if (flag) {
                        ps.println("<option value='Other'>.......Other.......</option>");
                    } else {
                        ps.println("<option value='Other' selected>.......Other.......</option>");
                    }

                    ps.println("</select>");

                    if (flag) {
                        ps.println("If Other, <input type = text  style=\"width:154px\" name='dealerCountry1'   maxlength=100 value ='' disabled class=\"text\">");
                    } else {
                        ps.println("If Other, <input type = text  style=\"width:154px\" name='dealerCountry1'   maxlength=100 value ='" + dealerCountry + "' enabled class=\"text\">");
                    }
                    ps.println("		</td>");
                }
                ps.println("    </tr>");

                ps.println("	<tr >");
                if (userFunctionalities.contains("20")) {
                    ps.println("      <td    class=\"links_txt\">Pin Code </td>");

                    ps.println("    <td   class=\"links_txt\"> " + pincode);
                    ps.println(" </td>");
                } else {

                    ps.println("		<td  class=\"links_txt\">");
                    ps.println("			");
                    ps.println("				Pin Code <span class=\"mandatory\"><font color=#FF3300> *</font></span>");
                    ps.println("		</td>");
                    ps.println("		<td  class=\"links_txt\">");
                    ps.println("			<input type=\"text\" style=\"width:154px\" name=\"pincode\"   maxlength=50 value = '" + pincode + "' >");
                    ps.println("		</td>");
                }
                ps.println("	</tr>");

                ps.println("	<tr >");
                ps.println("		<td  class=\"links_txt\">");
                ps.println("			");
                ps.println("				E-Mail 1");
                ps.println("		</td>");
                ps.println("		<td  class=\"links_txt\">");
                ps.println("			<input type=\"text\" style=\"width:154px\" name=\"dealerMail1\"   maxlength=100 value = '" + dealerMail1 + "'    >");
                ps.println("		</td>");
                ps.println("	</tr>");
                ps.println("	<tr >");
                ps.println("		<td  class=\"links_txt\">");
                ps.println("			");
                ps.println("				E-Mail 2");
                ps.println("		</td>");
                ps.println("		<td  class=\"links_txt\">");
                ps.println("			<input type=\"text\" style=\"width:154px\" name=\"dealerMail2\"   maxlength=100 value = '" + dealerMail2 + "'  >");
                ps.println("		</td>");
                ps.println("	</tr>");
                ps.println("	<tr >");
                ps.println("		<td  class=\"links_txt\">");
                ps.println("			");
                ps.println("				Mobile <span class=\"mandatory\"><font color=#FF3300> *</font></span>");
                ps.println("		</td>");
                ps.println("		<td  class=\"links_txt\">");
                ps.println("		<input type=\"text\" style=\"width:154px\" name=\"dealerMobile\"   maxlength=100 value = '" + dealerMobile + "'>");
                ps.println("		</td>");
                ps.println("	</tr>");
                ps.println("	<tr >");
                ps.println("		<td  class=\"links_txt\">");
                ps.println("			");
                ps.println("				Phone ");
                ps.println("		</td>");
                ps.println("		<td  class=\"links_txt\">");
                ps.println("		<input type=\"text\" style=\"width:154px\" name=\"dealerPhone\"   maxlength=100 value = '" + dealerPhone + "'>");
                ps.println("		</td>");
                ps.println("	</tr>");

                ps.println("	<tr>");
                ps.println("		<td  colspan = 2 align = left class=\"links_txt\">");
                ps.println("			<input type=\"button\" name=\"Submit\" value=\"Save Changes\" onclick=validate()>");
                ps.println("		</td>");
                ps.println("	</tr>");
                ps.println("                            </tbody>");
                ps.println("                        </table>");
//Footer

                ps.println(object_pageTemplate.tableFooter());



                //changes_01_02_2012
                ps.println("</form>");
            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println(footer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn_cat != null) {
                    conn_cat.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
