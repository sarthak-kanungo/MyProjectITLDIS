package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: AdvancesearchOption_1.java
PURPOSE: Advance Search .
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class AdvancesearchOption_1 extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            String date_OR_serial = (String) session.getValue("date_OR_serial");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                //
                //

                /*########################################*/


                ps.println("<html><head>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("<script type=\"text/javascript\" src=\"" + req.getContextPath() + "/js/ajax.js\"></script>");
                ps.println("      <title>");
                ps.println("         " + UtilityMapkeys1.tile1 + "");
                ps.println("      </title>");
//                ps.println("			<style type=text/css>");
//                ps.println("			<!--");
//                ps.println("			* { font-family: verdana; font-size: 8pt;}");
//                ps.println("			a:link {text-decoration: none; }");
//                ps.println("			a:visited {text-decoration: none;		  }	");
//                ps.println("			-->");
//                ps.println("			</style>");
                ps.println("</head>");


                ps.println("<script language= JavaScript >");

                ps.println("function validate()");
                ps.println("{");

                //ps.println("	  var modelNo= document.f1.modelNoTxt.value;");
                ps.println("	  var model_no= document.f1.modelTxt.value;");
                ps.println("	  var group_no= document.f1.groupTxt.value.replace('\\\\','/');");
                ps.println("	  var part_no= document.f1.partnoTxt.value.replace('\\\\','/');");
                ps.println("	  var part_d= document.f1.partDescTxt.value.replace('\\\\','/');");
                ps.println("      var iChars = '!@#$%^+=[]\\\';/{}|\":<>?'");
                ps.println("        if(group_no =='' && part_no == '' && part_d == '')");
                ps.println("        {");
                ps.println("            alert('Please enter a value in any of the three fields.')");
                ps.println("            return false;");
                ps.println("        }");
                //****************************************

                ps.println("         for (var i = 0; i < group_no.length; i++)");
                ps.println("          { ");
                ps.println("             if (iChars.indexOf(group_no.charAt(i)) != -1)");
                ps.println("               { ");
                ps.println("                  alert (group_no.charAt(i)+' '+'Is Not Vaild.'); ");
                ps.println("                  document.f1.groupTxt.select();");
                ps.println("                  return false; ");
                ps.println("               } ");
                ps.println("           } ");


                ps.println("        for (var i = 0; i < part_no.length; i++)");
                ps.println("         { ");
                ps.println("           if (iChars.indexOf(part_no.charAt(i)) != -1) ");
                ps.println("             { ");
                ps.println("               alert (part_no.charAt(i)+' '+'Is Not Vaild.'); ");
                ps.println("               document.f1.partnoTxt.select();");
                ps.println("               return false; ");
                ps.println("             } ");
                ps.println("          } ");

                ps.println("        for (var i = 0; i < part_d.length; i++) ");
                ps.println("          { ");
                ps.println("           if (iChars.indexOf(part_d.charAt(i)) != -1) ");
                ps.println("             { ");
                ps.println("               alert (part_d.charAt(i)+' '+'Is Not Vaild.'); ");
                ps.println("               document.f1.partDescTxt.select();");
                ps.println("               return false; ");
                ps.println("             } ");
                ps.println("          } ");

                ps.println("        document.f1.action=\"Adv_search_ind_model\"");
                //ps.println("        document.f1.target='_right';");
                ps.println("        document.f1.submit();");
                ps.println("}");

                ps.println("</script>");

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

                ps.println("function getModelDesc(Value,source)");
                ps.println("{");
                ps.println("         var model = document.getElementById('modelTxt').value;");
                ps.println("         var strURL =\"getModelDescByProduct?value=\"+Value+\"&source=\"+source+\"&model=\"+model+\"\" ");
                ps.println("         xmlHttp = GetXmlHttpObject();");
                ps.println("         xmlHttp.onreadystatechange = function()");
                ps.println("         {");
//                ps.println("             stateChangedModel(xmlHttp,source);");
                ps.println("    var response=null;");
                ps.println("    if (xmlHttp.readyState == 4 || xmlHttp.readyState == \"complete\")");
                ps.println("    {");

                ps.println("        response=xmlHttp.responseText;");
                ps.println("        if(source=='products'){");
                ps.println("        document.getElementById('selectDiv').innerHTML=response");
                ps.println("        if(Value=='All'){");
                ps.println("            var selectboxAgg = document.getElementById('aggTxt3');");
                ps.println("            var selectboxGrp = document.getElementById('groupTxt3');");
                ps.println("            for(var i=selectboxAgg.options.length - 1; i>=1; i--){");
                ps.println("            selectboxAgg.remove(i);");
                ps.println("            }");
                ps.println("            for(var i=selectboxGrp.options.length - 1; i>=1; i--){");
                ps.println("            selectboxGrp.remove(i);");
                ps.println("            }");
                ps.println("  }");
                ps.println("}else if (source=='models'){");
                ps.println("        if(Value=='All'){");
                ps.println("            var selectboxAgg = document.getElementById('aggTxt3');");
                ps.println("            var selectboxGrp = document.getElementById('groupTxt3');");
                ps.println("            for(var i=selectboxAgg.options.length - 1; i>=1; i--){");
                ps.println("            selectboxAgg.remove(i);");
                ps.println("            }");
                ps.println("            for(var i=selectboxGrp.options.length - 1; i>=1; i--){");
                ps.println("            selectboxGrp.remove(i);");
                ps.println("            }");
                ps.println("  }");
                ps.println("        document.getElementById('selectDiv1').innerHTML=response");
                ps.println("}else if (source=='aggregates'){");
                ps.println("        document.getElementById('selectDiv2').innerHTML=response");
                ps.println("        if(Value=='All'){");
                ps.println("            var selectboxGrp = document.getElementById('groupTxt3');");
                ps.println("            for(var i=selectboxGrp.options.length - 1;i>=1;i--){");
                ps.println("            selectboxGrp.remove(i);");
                ps.println("            }");
                ps.println("  }");
                ps.println("}");
                ps.println("  }");

                ps.println("         };");
                ps.println("       xmlHttp.open(\"GET\", strURL, true);");
                ps.println("       xmlHttp.send(null);");
                ps.println("       return;");
                ps.println(" }");

//                ps.println("function stateChangedModel(xmlHttp)");
//                ps.println("{");
//
//                ps.println("}");

                ps.println("function GetXmlHttpObject()");
                ps.println("{");
                ps.println("    var objXmlHttp = null;");
                ps.println("    if (navigator.userAgent.indexOf('Opera') >= 0)");
                ps.println("    {");
                ps.println("        xmlHttp=new XMLHttpRequest();");
                ps.println("        return xmlHttp;");
                ps.println("    }");
                ps.println("    if (navigator.userAgent.indexOf('MSIE') >= 0)");
                ps.println("    {");
                ps.println("        var strName = 'Msxml2.XMLHTTP';");
                ps.println("        if (navigator.appVersion.indexOf('MSIE 5.5') >= 0)");
                ps.println("        {");
                ps.println("            strName = 'Microsoft.XMLHTTP';");
                ps.println("        }");
                ps.println("        try");
                ps.println("        {");
                ps.println("            objXmlHttp = new ActiveXObject(strName);");
                ps.println("            // objXmlHttp.onreadystatechange = handler;");
                ps.println("            return objXmlHttp;");
                ps.println("        }");
                ps.println("        catch(e)");
                ps.println("        {");
                ps.println("            alert('Your Browser Does Not Support Ajax');");
                ps.println("            return;");
                ps.println("        }");
                ps.println("    }");
                ps.println("    if (navigator.userAgent.indexOf('Mozilla') >= 0)");
                ps.println("    {");
                ps.println("        objXmlHttp = new XMLHttpRequest();");
                ps.println("        return objXmlHttp;");
                ps.println("    }");
                ps.println("}");
                ps.println("</script>");


/// FOR TABLE .....

                //stmt = conn.createStatement();
                

                List<String> engineSeriesList = new ArrayList<String>();
                //Vector engineModelVec = new Vector();
                String engineSeries = "";
                //rs = stmt.executeQuery("SELECT DISTINCT ENGINE_SERIES FROM CAT_MODEL_CLASSIFICATION order by ENGINE_SERIES");
               String sql = ("SELECT DISTINCT ENGINE_SERIES FROM CAT_MODEL_CLASSIFICATION(NOLOCK) order by ENGINE_SERIES");
               stmt = conn.prepareStatement(sql);
               rs = stmt.executeQuery();
              
               while (rs.next()) {
                    engineSeries = rs.getString(1);
                    engineSeriesList.add(engineSeries);
                }
                rs.close();


                ps.println("<body topmargin=0  marginheight=0 leftmargin=0  marginwidth=0 >");


                int width = 420, height = 250;
                ps.println(object_pageTemplate.tableHeader("Advance Search", width, height));

                if (date_OR_serial.equals("latest")) {

                    ps.println("<center>");
                    if (userFunctionalities.contains("217")) {



                        ps.println("<form name=f1 method=post target=\"right\"><center>");

                        ps.println("<table width=100% border=0 cellpadding=2 cellspacing=0 align = center>");

                        ps.println("<tr> ");
                        ps.println("<td class=links_txt> ");
                        ps.println("<div align=center>&nbsp;");
                        ps.println("<input name=modelNo  type=hidden  id=modelNo value=modelNo>");
                        ps.println("</div></td>");

                        ps.println("<td class=links_txt >" + ComUtils.getLanguageTranslation("label.catalogue.product", session) + "</td>");
                        ps.println("<td class=links_txt> ");
                        ps.println("<div align=center> ");
                        ps.println("<select name='modelNoTxt' style=\"width:210px\" id=modelNoTxt class=\"text\" onchange=\"javascript:getModelDesc(this.value,'products')\")\">");
                        ps.println("<option value='All' title='ALL'>ALL</option>");
                        for (int i = 0; i < engineSeriesList.size(); i++) {
                            ps.println("<option value='" + engineSeriesList.get(i) + "' title='" + engineSeriesList.get(i) + "'>" + engineSeriesList.get(i) + "</option>");

                        }
                        ps.println("</select></div></td>");
                        ps.println("<td class=links_txt>&nbsp;</td>");
                        ps.println("</tr>");

                        ps.println("<tr> ");
                        ps.println("<td class=links_txt> ");
                        ps.println("<div align=center>&nbsp;");
                        ps.println("<input name=model  type=hidden  id=model value=model>");
                        ps.println("</div></td>");

                        ps.println("<td class=links_txt>" + ComUtils.getLanguageTranslation("label.catalogue.model", session) + "</td>");

                        ps.println("<td class=links_txt> ");
                        ps.println("<div align=center id=selectDiv> ");
                        ps.println("<select name='modelTxt' style=\"width:210px\" id=modelTxt class=\"text\" >");
                        ps.println("<option value='All' >ALL</option>");

                        ps.println("</select></div></td>");
                        ps.println("<td class=links_txt>&nbsp;</td>");
                        ps.println("</tr>");

                        /**************************************Changes By Apurv*********************************************/
                        ps.println("<tr> ");
                        ps.println("<td class=links_txt> <div align=center>&nbsp;");
//                        ps.println("<input name=group type=hidden  id=partName value=group>");
                        ps.println("</div></td>");
                        ps.println("<td class=links_txt>" + ComUtils.getLanguageTranslation("label.catalogue.aggregate", session) + "</td>");
                        ps.println("<td class=links_txt> <div align=center id=selectDiv1> ");
//                        ps.println("<input name=groupTxt  type=text id=groupTxt3 maxlength='100' size=30 onblur=\"this.value=TrimAll(this.value)\">");

                        ps.println("<select name='aggTxt' style=\"width:210px\" id='aggTxt3' class=\"text\" >");
                        ps.println("<option value='All' >ALL</option>");

                        ps.println("</select>");

                        ps.println("</div></td>");
                        ps.println("<td class=links_txt> &nbsp;</td>");
                        ps.println("</tr>");



                        ps.println("<tr> ");
                        ps.println("<td class=links_txt> <div align=center>&nbsp;");
//                        ps.println("<input name=group type=hidden  id=partName value=group>");
                        ps.println("</div></td>");
                        ps.println("<td class=links_txt>" + ComUtils.getLanguageTranslation("label.catalogue.group", session) + "</td>");
                        ps.println("<td class=links_txt> <div align=center id=selectDiv2> ");
//                        ps.println("<input name=groupTxt  type=text id=groupTxt3 maxlength='100' size=30 onblur=\"this.value=TrimAll(this.value)\">");

                        ps.println("<select name='groupTxt' style=\"width:210px\" id=groupTxt3 class=\"text\" >");
                        ps.println("<option value='All' >ALL</option>");

                        ps.println("</select>");

                        ps.println("</div></td>");
                        ps.println("<td class=links_txt> &nbsp;</td>");
                        ps.println("</tr>");

                        /**************************************************************************************************/
                        ps.println("<tr bordercolor=#000000> ");
                        ps.println("<td class=links_txt> <div align=center>&nbsp;");
                        ps.println("<input name=partno type=hidden  id=Group value=partno onblur=\"this.value=TrimAll(this.value)\">");
                        ps.println("</div></td>");

                        ps.println("<td class=links_txt>" + ComUtils.getLanguageTranslation("label.catalogue.partNo", session) + "</td> ");
                        ps.println("<td class=links_txt> <div align=center> ");
                        ps.println("<input name=partnoTxt  type=text id=partnoTxt2 maxlength='50' size=31 class='text' onblur=\"this.value=TrimAll(this.value)\">");
                        ps.println("</div></td>");
                        ps.println("<td class=links_txt>&nbsp;</td>");

                        ps.println("</tr>");

                        ps.println("<tr bordercolor=#000000> ");
                        ps.println("<td class=links_txt> <div align=center>&nbsp;");
                        ps.println("<input name=partDesc type=hidden id=partDesc2 value=partDesc >");
                        ps.println("</div></td>");

                        ps.println("<td class=links_txt>" + ComUtils.getLanguageTranslation("label.catalogue.partDesc", session) + "</td>");

                        ps.println("<td class=links_txt> <div align=center> ");
                        ps.println("<input name=partDescTxt  type=text id=partnameTxt maxlength='200' size=31 class='text'>");
                        ps.println("</div></td>");

                        ps.println("<td class=links_txt>&nbsp;</td>");

                        ps.println("<td class=links_txt><div align=center> ");
                        ps.println("<input type=Button onClick='return validate();' name=SUBMIT  value='GO'>");
                        ps.println("</div></td>");

                        ps.println("</tr>");


                        ps.println("<tr>");
                        //ps.println("<td style=\"padding-left:10px;\" colspan=4><span class=\"mandatory\"><font color=#FF3300>put * for partial search</font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("</table>");
                        ps.println("</form>");

                        ps.println(object_pageTemplate.tableFooter());



                    }


                }
                ps.println("</body>");
                ps.println("</html>");
            } else {
                object_pageTemplate.ShowMsg1(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", ComUtils.getLanguageTranslation("label.catalogue.loginAgain", session), "Index", "", imagesURL);
            }
        } catch (Exception e) {
            ps.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                ps.close();
                wps.close();
            } catch (Exception e) {
            }

        }
    }
}
