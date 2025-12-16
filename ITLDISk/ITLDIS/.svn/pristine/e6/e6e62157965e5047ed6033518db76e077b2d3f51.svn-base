package viewEcat.orderEcat;

//Modification
//Date            line_no       By
//5/1/2006         306          MB
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import viewEcat.comEcat.ComUtils;
import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;

public class View_cart extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) {
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String getType = (String) session.getValue("user_type");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");


            ///////////////////////////// CREATE SESSION /////////////////////////////

            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PrintWriter ps = res.getWriter();
            res.setContentType("text/html");


            if (session_id.equals(getSession)) {

                String heading = null;
                int width = 950, height = 0;

                int quantity = 0;

                ps.println("<html>");
                ps.println("<head>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");




                //  if (getType.equalsIgnoreCase("dealer")) {
                Vector totalParts = (Vector) session.getValue("cartItems");

                if (totalParts == null) {
                    totalParts = new Vector();
                }

                //if (totalParts != null && totalParts.size() != 0)
                {
                    Connection conn;
                    //Statement stmt;
                    PreparedStatement stmt = null;
                    ResultSet rs;

                    conn = holder.getConnection();
                    //stmt = conn.createStatement();



                    ps.println("<script language=JavaScript type=text/JavaScript>");

                    ps.println("function addNewPartRow()");
                    ps.println("{");
                    ps.println("  var table = document.getElementById(\"MyTable\"); ");
                    ps.println("  var rowCount = table.rows.length; ");
                    ps.println("var counter=rowCount + 1;");
                    ps.println("  var row = table.insertRow(rowCount); ");
                    ps.println("  ");
                    ps.println("  var cell = row.insertCell(0); ");
                    ps.println("  cell.className=\"links_txtBorderFirst\";");
                    ps.println("  cell.align=\"center\";");
                    ps.println("  cell.width=\"8%\";");
                    ps.println("  cell.innerHTML =\"<input type=checkbox name=deleteCheck>\"; ");
                    ps.println("  ");
                    ps.println("  cell = row.insertCell(1); ");
                    ps.println("  cell.className=\"links_txtBorderSecond\";");
                    ps.println("  cell.align=\"center\";");
                    ps.println("  cell.width=\"7%\";");
                    ps.println("  cell.innerHTML = rowCount + 1; ");
                    ps.println("");
                    ps.println("  cell = row.insertCell(2); ");
                    ps.println("  cell.className=\"links_txtBorderSecond\";");
                    ps.println("  cell.align=\"left\";");
                    ps.println("  cell.width=\"25%\";");
                    ps.println("  cell.innerHTML = \"&nbsp; \"+\"<input type='text' class='links_txt' name='partNo' onBlur='addNewPartToCartOrder(this,\"+rowCount+\")'/>\";");
                    ps.println("   ");
                    ps.println("   cell = row.insertCell(3); ");
                    ps.println("  cell.className=\"links_txtBorderSecond\";");
                    ps.println("  cell.align=\"left\";");
                    ps.println("  cell.width=\"45%\";");
                    ps.println("  cell.innerHTML = \"&nbsp; \"+\"<input type='text' class='links_txt' name='descHidden' readonly='true'/>\";");
                    ps.println("");
                    ps.println("   cell = row.insertCell(4); ");
                    ps.println("  cell.className=\"links_txtBorderSecond\";");
                    ps.println("  cell.align=\"center\";");
                    ps.println("  cell.width=\"15%\";");
                    ps.println("  cell.innerHTML =\"<input type = 'text'  name = 'ordQty' size = '8'  value ='0'  onkeypress='validnumber(event);' onBlur='validate(this,\"+counter+\");' maxLength='4' >\";");
                    ps.println("  ");
                    ps.println("}");

                    ps.println("var oldValue=0");

                    ps.println("function validateOrder()");
                    ps.println("{");
                    ps.println("var partArr=document.getElementsByName(\"partNo\");");
                    ps.println("var qtyArr=document.getElementsByName(\"ordQty\");");
                    ps.println("var moqArr=document.getElementsByName(\"moq\");");
                    ps.println("var objRegExp = /^(\\s*)$/;");
                    ps.println("var strValue =null;");
                    ps.println("var strObject=null;");
                    ps.println("var flag=false;");

                    ps.println("for(var k=0;k<partArr.length;k++)");
                    ps.println("{ ");
                    ps.println("strObject=partArr[k];");
                    ps.println("strValue=strObject.value;");
                    ps.println("strValue=strValue.replace(objRegExp, '');");
                    ps.println("if(strValue.length!=0)");
                    ps.println("{flag=true;");
                    ps.println("			if(qtyArr[k].value=='')");
                    ps.println("			{");
                    ps.println("                            alert(\"Please Enter Quantity.\")");
                    ps.println("				qtyArr[k].focus();");
                    ps.println("				return false;");
                    ps.println("			}");
                    ps.println("			else if(qtyArr[k].value==0)");
                    ps.println("			{");
                    ps.println("                            alert(\"Quantity must Be greater Than 0.\")");
                    ps.println("				qtyArr[k].focus();");
                    ps.println("				return false;");
                    ps.println("			}");
//                        ps.println("			else if(parseInt(qtyArr[k].value)<parseInt(moqArr[k].value))");
//                        ps.println("			{");
//                        ps.println("                            alert(\"Quantity Cannot Be Less Than \"+moqArr[k].value+\".\")");
//                        ps.println("				qtyArr[k].focus();");
//                        ps.println("				return false;");
//                        ps.println("			}");
//                        ps.println("			else if((parseInt(qtyArr[k].value)%parseInt(moqArr[k].value)!=0))");
//                        ps.println("			{");
//                        ps.println("                            alert(\"Quantity Must Be In Multiples of \"+moqArr[k].value+\".\")");
//                        ps.println("				qtyArr[k].focus();");
//                        ps.println("				return false;");
//                        ps.println("			}");
                    ps.println("}}");

                    ps.println("if(partArr.length==0)");
                    ps.println("{");
                    ps.println("alert('Please Add Part');");
                    ps.println("return false;");
                    ps.println(" } ");

                    ps.println("if(flag)");
                    ps.println("{");
                    ps.println("	document.order.action = '" + servletURL + "ExportCartToExcel'");
                    ps.println("	document.order.submit();");
                    ps.println(" }");
                    ps.println("else");
                    ps.println("{");
                    ps.println("alert('Please Enter Part No.');");
                    ps.println("return false;");
                    ps.println("}");
                    ps.println("}");


                    ps.println("function validnumber(ev) {");
                    ps.println("ev.returnValue = (ev.keyCode >= 48 && ev.keyCode <= 57);}");

                    ps.println("function validate(disObject,counter)");
                    ps.println("{");
                    ps.println("          var rowCount=counter-1");
                    //ps.println("  var table = document.getElementById(\"MyTable\"); ");
                    //ps.println("  window.prompt('jk',document.getElementById(\"MyTable\").innerHTML); ");
                    ps.println("  var moq=document.getElementById(rowCount+'moq').value; ");
                    ps.println("var objRegExp = /^(\\s*)$/;");
                    ps.println("var strValue =document.getElementsByName(\"partNo\")[counter-1].value;");
                    ps.println("strValue=strValue.replace(objRegExp, '');");
                    ps.println("if(strValue.length!=0 && disObject)");
                    ps.println("{");
//                        ps.println("	if(disObject.value==0)");
//                        ps.println("	{");
//                        ps.println("            alert('Quantity  must be greater than zero.');disObject.focus();return false;");
//                        ps.println("	}");
//                        ps.println("    else if(parseInt(disObject.value)<parseInt(moq))");
//                        ps.println("    {");
//                        ps.println("		alert('Quantity Cannot Be Less Than '+moq+'.');disObject.focus();return false;");
//                        ps.println("		return false;");
//                        ps.println("    }");
//                        ps.println("    else if((parseInt(disObject.value)%parseInt(moq))!=0)");
//                        ps.println("    {");
//                        ps.println("        alert('Quantity Must Be In Multiples of '+moq+'.');disObject.focus();return false;");
//                        ps.println("        return false;");
//                        ps.println("    }");
//                        ps.println("    else{");
                    ps.println("    addQtyToCartOrder(rowCount);");
                    // ps.println("   ");
                    ps.println("}");
                    ps.println("	}");


                    ps.println("function checkForDot(element)");
                    ps.println("{");
                    ps.println("	var len=element.value.length");
                    ps.println("	var value=element.value");
                    ps.println("	for(var i=0;i<(len-1);i++)");
                    ps.println("	{");
                    ps.println("		var temp=value.substring(i,i+1)");
                    ps.println("		if(temp=='.')");
                    ps.println("		{");
                    ps.println("			alert('Quantity cannot be in decimal')");
                    ps.println("			element.value=0");
                    ps.println("		}");
                    ps.println("	}");
                    ps.println("}");

                    ps.println("function totalQuantity()");
                    ps.println("{");
                    ps.println("	var totalPart=document.order.ordQty.length");
                    ps.println("	sum=0;");
                    ps.println("	if(totalPart>1)");
                    ps.println("	{");
                    ps.println("		for(var i=0;i<totalPart;i++)");
                    ps.println("		{");
                    ps.println("			var temp=parseInt(document.order.ordQty[i].value,10)");
                    ps.println("			if(temp==0 || temp==00 || temp==000 || temp==0000)");
                    ps.println("			{");
                    ps.println("				temp=0;");
                    ps.println("			}");
                    ps.println("			sum=parseInt(sum)+(temp)");
                    ps.println("		}");
                    ps.println("	}");
                    ps.println("	else");
                    ps.println("	{");
                    ps.println("		var temp=parseInt(document.order.ordQty.value,10)");
                    ps.println("		if(temp==0 || temp==00 || temp==000 || temp==0000)");
                    ps.println("		{");
                    ps.println("			temp=0;");
                    ps.println("		}");
                    ps.println("		sum=temp");
                    ps.println("	}");
                    ps.println("	document.order.totalValue.value=sum;");
                    ps.println("}");

                    ps.println("function isZero(elem)");
                    ps.println("{");
                    ps.println("		var str = elem.value;");
                    ps.println("		if(str==0||str==00||str==000||str==0000)");
                    ps.println("		 {");
                    ps.println("			alert('Order quantity cannot be less than 1')");
                    ps.println("			totalQuantity()");
                    ps.println("			elem.focus()");
                    ps.println("			return false;");
                    ps.println("		} ");
                    ps.println("		else ");
                    ps.println("		{");
                    ps.println("			return false;");
                    ps.println("		}");
                    ps.println("}");


                    //wertwueoituioweitowuet
                    ps.println("function addQtyToCartOrder(rowCount)");
                    ps.println("{");
                    ps.println("var objRegExp = /^(\\s*)$/;");
                    ps.println("var partValue=document.getElementsByName(\"partNo\")[rowCount].value;");
                    ps.println("partValue=partValue.replace(objRegExp, '');");
                    ps.println("if(partValue.length!=0)");
                    ps.println("{");
                    ps.println("    var strURL=null;");
                    ps.println("     var inputValue=document.getElementsByName(\"ordQty\")[rowCount].value;");

                    ps.println("    strURL=\"Add_2_cart?flag=AddQtyPart2Cart&ordQty=\"+inputValue+\"&partNo=\"+partValue+\"&tm=\"+new Date().getTime();");
                    ps.println("    var xmlHttp = GetXmlHttpObject();");
                    ps.println("    xmlHttp.onreadystatechange = function()");
                    ps.println("    {");
                    ps.println("        stateChangedOrder1(xmlHttp);");
                    ps.println("");
                    ps.println("    };");
                    ps.println("    xmlHttp.open(\"GET\", strURL, true);");
                    ps.println("    xmlHttp.send(null);");
                    //ps.println("  return false;");
                    ps.println("}}");

                    ps.println("function stateChangedOrder1(xmlHttp)");
                    ps.println("{");
                    ps.println("    var response=null;");
                    ps.println("    if (xmlHttp.readyState == 4 || xmlHttp.readyState == \"complete\")");
                    ps.println("    {");
                    ps.println("        response=xmlHttp.responseText;  ");

                    ps.println("  }  }");

                    //ioweiriweoriowieroiweopri

                    //newchanges

                    ps.println("function addNewPartToCartOrder(disObj,rowCount)");
                    ps.println("{");
                    ps.println("var objRegExp = /^(\\s*)$/;");
                    ps.println("var strValue =disObj.value;");
                    ps.println("strValue=strValue.replace(objRegExp, '');");
                    ps.println("if(strValue.length!=0)");
                    ps.println("{");
                    ps.println("    var strURL=null;");
                    ps.println("     var inputValue=document.getElementsByName(\"ordQty\")[rowCount].value;");
                    ps.println("    strURL=\"Add_2_cart?flag=AddNewPart2Cart&ordQty=\"+inputValue+\"&partNo=\"+strValue+\"&tm=\"+new Date().getTime();");
                    ps.println("    var xmlHttp = GetXmlHttpObject();");
                    ps.println("    xmlHttp.onreadystatechange = function()");
                    ps.println("    {");
                    ps.println("        stateChangedOrder(disObj,xmlHttp,rowCount);");
                    ps.println("");
                    ps.println("    };");
                    ps.println("    xmlHttp.open(\"GET\", strURL, true);");
                    ps.println("    xmlHttp.send(null);");
                    //ps.println("  return false;");
                    ps.println("}}");

                    ps.println("function stateChangedOrder(disObj,xmlHttp,rowCount)");
                    ps.println("{");
                    ps.println("    var response=null;");
                    ps.println("    if (xmlHttp.readyState == 4 || xmlHttp.readyState == \"complete\")");
                    ps.println("    {");
                    ps.println("        response=xmlHttp.responseText;  ");

                    ps.println("if(response.indexOf(\"Already\")!=-1)");
                    ps.println("{");
                    ps.println("alert('Part Already Exist in the List');");
                    ps.println(" disObj.value='';");
                    ps.println("}");
                    ps.println("else if(response.indexOf(\"Cannot\")!=-1)");
                    ps.println("{");
                    ps.println("alert('Part cannot be added to Cart');");
                    ps.println(" disObj.value='';");
                    ps.println("}");
//                        ps.println("else if(response.indexOf(\"Servicable\")!=-1)");
//                        ps.println("{");
//                        ps.println("alert('Part is not Servicable');");
//                        ps.println(" disObj.value='';");
//                        ps.println("}");
                    ps.println("else if(response.indexOf(\"Success\")!=-1)");
                    ps.println("{");
                    ps.println("  var desc=response.split('$#')[1];");
                    ps.println("  var moq=response.split('$#')[2];");
                    ps.println("  var table = document.getElementById(\"MyTable\"); ");
                    ps.println("  table.rows[rowCount].cells[0].innerHTML=\"\"; ");//'"+rowCount+"moq'
                    ps.println("  table.rows[rowCount].cells[0].innerHTML=\"<input type='hidden' name='moq' value='\"+moq+\"' id='\"+rowCount+\"moq'><input type=checkbox name=deleteCheck value=\"+disObj.value+\">\";");
                    ps.println("  table.rows[rowCount].cells[2].innerHTML=\"\"; ");
                    ps.println("  table.rows[rowCount].cells[2].innerHTML=\"&nbsp; \"+disObj.value+\"<input type = hidden name = 'partNo'  value=\"+disObj.value+\">\";");
                    ps.println("  table.rows[rowCount].cells[3].innerHTML=\"\"; ");
                    ps.println("  table.rows[rowCount].cells[3].innerHTML=\"&nbsp; \"+desc+\"<input type = hidden name = 'descHidden' value =\"+desc+\">\"; ");
                    ps.println("}");
                    ps.println("else");
                    ps.println("{");
                    ps.println("alert('Part does not Exist');");
                    ps.println(" disObj.value='';");
                    ps.println("}");
                    ps.println("  }  }");

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




                    //new chagnes end


                    ps.println("function number_check(v)");
                    ps.println("{");
                    ps.println("	if(isNaN(v))");
                    ps.println("	{");
                    ps.println("		return false;");
                    ps.println("	}");
                    ps.println("	else");
                    ps.println("	{");
                    ps.println("		return true;");
                    ps.println("	}");
                    ps.println("}");

                    ps.println("");
                    ps.println("function isblank(element)  //check for blanks in text field");
                    ps.println("{");
                    ps.println("	var valueTxt=element.value; ");
                    ps.println("	if(element=='[object]')");
                    ps.println("	{");
                    ps.println("		if(valueTxt.length==0 || valueTxt.charAt(0)==\" \")");
                    ps.println("		{");
                    ps.println("			alert(\"Value Can't Be Blank.\");");
                    ps.println("			element.value=0");
                    ps.println("			totalQuantity()");
                    ps.println("			element.focus();");
                    ps.println("			return false;");
                    ps.println("		}");
                    ps.println("		else");
                    ps.println("		return true;");
                    ps.println("	}");
                    ps.println("	else");
                    ps.println("	{");
                    ps.println("		if(valueTxt.length==0 || valueTxt.charAt(0)==\" \")");
                    ps.println("		{");
                    ps.println("			alert(\"Value Can't Be Blank.\");");
                    ps.println("			element.value=0");
                    ps.println("			totalQuantity()");
                    ps.println("			element.focus();");
                    ps.println("			return false;");
                    ps.println("		}");
                    ps.println("		else");
                    ps.println("		return true;");
                    ps.println("	}");
                    ps.println("}");

                    ps.println("function LTrim(str)");
                    ps.println("{");
                    ps.println("	if(str==null)");
                    ps.println("	{");
                    ps.println("		return null;");
                    ps.println("	}");
                    ps.println("	for(var i=0;str.charAt(i)==\" \";i++);");
                    ps.println("	return str.substring(i,str.length);");
                    ps.println("}");
                    ps.println("function RTrim(str)");
                    ps.println("{");
                    ps.println("	if(str==null)");
                    ps.println("	{");
                    ps.println("		return null;");
                    ps.println("	}");
                    ps.println("	for(var i=str.length-1;str.charAt(i)==\" \";i--);");
                    ps.println("	return str.substring(0,i+1);");
                    ps.println("}");
                    ps.println("function Trim(str)");
                    ps.println("{");
                    ps.println("	return LTrim(RTrim(str));");
                    ps.println("}");
                    ps.println("function LTrimAll(str)");
                    ps.println("{");
                    ps.println("	if(str==null)");
                    ps.println("	{");
                    ps.println("		return str;");
                    ps.println("	}");
                    ps.println("	for(var i=0;str.charAt(i)==\" \" || str.charAt(i)==\"\\n\" || str.charAt(i)==\"\\t\";i++);");
                    ps.println("	return str.substring(i,str.length);");
                    ps.println("}");
                    ps.println("function RTrimAll(str)");
                    ps.println("{");
                    ps.println("	if(str==null)");
                    ps.println("	{");
                    ps.println("		return str;");
                    ps.println("	}");
                    ps.println("	for(var i=str.length-1;str.charAt(i)==\" \" || str.charAt(i)==\"\\n\" || str.charAt(i)==\"\\t\";i--);");
                    ps.println("	return str.substring(0,i+1);");
                    ps.println("}");
                    ps.println("function TrimAll(str)");
                    ps.println("{");
                    ps.println("	return LTrimAll(RTrimAll(str));");
                    ps.println("}");

                    ps.println("function deletePartFromExcel()");
                    ps.println("{");
                    ps.println("var partArr=document.getElementsByName(\"partNo\");");
                    ps.println("var qtyArr=document.getElementsByName(\"ordQty\");");
                    ps.println("var delArr=document.getElementsByName(\"deleteCheck\");");
                    ps.println("var objRegExp = /^(\\s*)$/;");
                    ps.println("var strValue =null;");
                    ps.println("var strObject=null;");
                    ps.println("	var partListToDelete='';");
                    ps.println("	var flag=false;");

                    ps.println("for(var k=0;k<partArr.length;k++)");
                    ps.println("{");
                    ps.println("strObject=partArr[k];");
                    ps.println("strValue=strObject.value;");
                    ps.println("strValue=strValue.replace(objRegExp, '');");
                    ps.println("if(strValue.length!=0)");
                    ps.println("{");
                    ps.println("	if(delArr[k]!=null){");
                    //ps.println("	var deleteCheckLen=delArr[k].length");
                    ps.println("		if(delArr[k].checked==true)");
                    ps.println("		{");
                    ps.println("partListToDelete=partListToDelete+delArr[k].value+'@'");
                    ps.println("					flag=true");
                    ps.println("		}}}}");
                    ps.println("	if(flag==false)");
                    ps.println("	{");
                    ps.println("			alert('No Part Selected For Deletion')");
                    ps.println("		return;");
                    ps.println("	}");
                    ps.println("	else");
                    ps.println("	{");
                    ps.println("		if(confirm('Do you really want to delete selected parts.'))");
                    ps.println("		{");
                    ps.println("			window.open('" + servletURL + "DelPartFrmExcel?partListToDelete='+partListToDelete+'','DETAILS','width=100,height=100')");
                    ps.println("		}");
                    ps.println("	}");
                    ps.println("}");
                    ps.println("</script></head>");
                    /**********************************/
                    ////////////////////////////////////////////////////////////
                    //ps.println("<br>");
                    ////////////////////////////////////////////////////////////
                    ps.println("<form name=order method=post><center>");

                    ////////////////////////////////////////////////////////////


                    ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.partDetails", session), width, height));

                    //ps.println(object_pageTemplate.tableHeader2(heading, width, height, "left"));
                    //ps.println(object_pageTemplate.tableHeader(heading, width, height, "left"));

                    ps.println("<table cellspacing=0 cellpadding=0 border=0  width=920px; ><tr><td style=\"padding-top:5px;\">");
                    ps.println("<table width=920px; cellspacing=0 cellpadding=1   border=0  >");
                    ps.println("<tr>");
                    ps.println("<td align = center class=\"links_txtBoldBorderFirst\" width = 8%>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.delete", session).toUpperCase());
                    ps.println("</td>");

                    ps.println("<td align = center class=\"links_txtBoldBorderSecond\" width = 7%>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.sNo", session).toUpperCase());
                    ps.println("</td>");

                    ps.println("<td width = 25% align = left class=\"links_txtBoldBorderSecond\">");
                    ps.println("&nbsp;" + ComUtils.getLanguageTranslation("label.catalogue.partNo", session).toUpperCase());
                    ps.println("</td>");

                    ps.println("<td width = 45% align = left class=\"links_txtBoldBorderSecond\">");
                    ps.println("&nbsp;" + ComUtils.getLanguageTranslation("label.catalogue.description", session).toUpperCase());
                    ps.println("</td>");


                    ps.println("<td align = center  class=\"links_txtBoldBorderSecond\" width=15%>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.orderedQuantity", session).toUpperCase());
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("<tr>");
                    ps.println("<td>");

                    int counter = 0;


                    if (totalParts.size() > 28) {
                        ps.println("<div style=\" height: 300px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                    }
                    ps.println("<table width=920px; cellspacing=0   cellpadding=1 id='MyTable'   border=0  >");//width=862px;


                    for (int i = 0; i < totalParts.size(); i += 2) {
                        String sqlQuery = "";
                        int check = 0;
                        //rs = stmt.executeQuery("SELECT part_no FROM CAT_PART WHERE part_no = '" + totalParts.elementAt(i) + "'");
                          String query = ("SELECT part_no FROM CAT_PART(NOLOCK) WHERE part_no = '" + totalParts.elementAt(i) + "'");
                          stmt = conn.prepareStatement(query);
                          rs = stmt.executeQuery();
                        if (rs.next()) {
                            sqlQuery = "SELECT p1,np1,np2,p4 FROM CAT_PART(NOLOCK) WHERE part_no ='" + totalParts.elementAt(i) + "'";
                            stmt = conn.prepareStatement(sqlQuery);
                            rs = stmt.executeQuery();
                            rs.close();

                            String description = "";
                            String moq = "";
                            String ndp = "";
                            String serviceable = "";

                            rs = stmt.executeQuery(sqlQuery);
                            if (rs.next()) {
                                description = rs.getString(1);
                                if (description == null) {
                                    description = "--";
                                }

                                moq = rs.getString(2);
                                if (moq == null) {
                                    moq = "1";
                                }

                                ndp = rs.getString(3);
                                if (ndp == null) {
                                    ndp = "1";
                                }

                                if (check == 1) {
                                    serviceable = "Y";
                                } else {
                                    serviceable = rs.getString(4);
                                    if (serviceable == null || serviceable.equals("null")) {
                                        serviceable = "N";
                                    }
                                }

                                ps.println("<tr>");
                                /**********************************/
                                ps.println("<td align = center width=8% class=\"links_txtBorderFirst\">");
                                ps.println("<input type='hidden' name='moq' value='" + moq + "' id='" + counter + "moq'><input type=checkbox name=deleteCheck value='" + totalParts.elementAt(i) + "'>");
                                ps.println("</td>");

                                ps.println("<td align = center width=7% class=\"links_txtBorderSecond\">");
                                ps.println(++counter);
                                ps.println("</td>");

                                ps.println("<td class=\"links_txtBorderSecond\" width=25%>&nbsp;");
                                String tempPartNo = (totalParts.elementAt(i)).toString();

                                if (tempPartNo.length() > 6) {
                                    String part_name = tempPartNo.substring(0, 6);
                                    if (part_name.equals("TBA000")) {
                                        tempPartNo = "&nbsp";
                                    }
                                }
                                ps.println(tempPartNo);

                                ps.println("</td>");

                                ps.println("<td class=\"links_txtBorderSecond\" width=45%>&nbsp;");
                                ps.println(description);
                                ps.println("<input type = hidden name = descHidden value = '" + description + "'>");
                                ps.println("</td>");


                                //ps.println("<input type = hidden name = moq value = '" + moq + "'>");
                                //ps.println("<input type = hidden name = ndp value = '" + ndp + "'>");
                                ps.println("<input type = hidden name = partNo value = '" + totalParts.elementAt(i) + "'>");

                                //ps.println("<input type = hidden name = qtyHidden value = '" + serviceable + "'>");

                                try {
                                    quantity += Integer.parseInt(totalParts.elementAt(i + 1).toString().trim());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                ps.println("<td align = center  class=\"links_txtBorderSecond\" width=15%> ");
                                //ps.println("<input type = text id=id"+totalParts.elementAt(i)+" name = ordQty size = 3 value = 0 onFocus='setOldValue(this)' onBlur='validate("+(i+1)+")'>");
                                ps.println("<input type = text id=id" + totalParts.elementAt(i) + " name = ordQty size = 8  value ='" + totalParts.elementAt(i + 1) + "'  onkeypress=\"validnumber(event);\" onBlur=\"validate(this," + counter + ");\" maxLength=4 >");
                                ps.println("</td>");



                                ps.println("</td>");
                                ps.println("</tr>");
                            }
                        }
                        rs.close();
                        
                    }

                    ps.println("</table>");
                    if (totalParts.size() > 28) {
                        ps.println("</div>");
                    }
                    ps.println("</td>");
                    ps.println("</tr>");

                    ps.println("<tr>");
                    ps.println("<td>");
                    ps.println("<table width=920px; cellspacing=0 cellpadding=1 border=0>");
                    ps.println("<tr height=30>");
                    ps.println("<td align = left  width=40% class=\"links_txtBoldBorder_top_left_bottom\">");
                    ps.println("&nbsp;<input type='button' value='ADD NEW PART' onclick=\"addNewPartRow();\">");
                    //ps.println("ADD PART&nbsp;");
                    ps.println("</td>");
//                        ps.println("<td align = right  width=45% class=\"links_txtBorder_bottom\">");
//                        ps.println("<b>");
//                        ps.println("TOTAL QUANTITY&nbsp;");
//                        ps.println("</td>");
//
//                        ps.println("<td align = center width=15% class=\"links_txtBorder_bottom\">");
//                        ps.println("<input type = text name = totalValue size = 8 value = '" + quantity + "'  readOnly>");
//                        ps.println("</td>");
                    ps.println("</tr>");

                    ps.println("</table>");



                    ////////////////////////////////////////////////////////////
//						ps.println("<br>");
                    ////////////////////////////////////////////////////////////

                    ps.println("<table width=940px; border=0 cellspacing=1 cellpadding=4 bordercolor=#CCCCCC align=center>");
                    ps.println("<tr>");
                    ps.println("<td align = left>");
                    ps.println("<input type=button onClick = deletePartFromExcel() value=' DELETE PART   ' name='deletePart'>");
                    ps.println("</td>");
                    ps.println("<td align = right>");
                    ps.println("<input type=button  onClick=validateOrder() value='SAVE ORDER' name='B1'>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");

//                        ps.println("<input type = hidden name = moq >");
//                        ps.println("<input type = hidden name = ndp>");
//                        ps.println("<input type = hidden name = partNo>");
//                        ps.println("<input type = hidden name = qty>");
//                        ps.println("<input type = hidden name = ordValue>");

                    ps.println("</td></tr></table>");
                    ps.println("</form>");
                    ps.println(object_pageTemplate.tableFooter());



                    stmt.close();
                }
                //                      else {
//
//                        heading = "Parts Cart Empty";
//                        ps.println(object_pageTemplate.tableHeader1(heading, width, height, "center"));
//                        return;
//                    }
                //   } else {

                //    heading = "Option Available in Dealer Mode Only";
                //    ps.println(object_pageTemplate.tableHeader(heading, width, height));
                //ps.println(object_pageTemplate.tableHeader1(heading, width, height, "center"));
                //   return;
                //}
            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", ComUtils.getLanguageTranslation("label.catalogue.loginAgain", session), "Index", "", imagesURL);
            }

        } catch (Exception e) {
            e.printStackTrace();
           // System.out.println(e);
        }
    }
}
