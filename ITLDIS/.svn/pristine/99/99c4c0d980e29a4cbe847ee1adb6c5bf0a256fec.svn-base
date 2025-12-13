<%-- 
    Document   : amw_add_kit_quantity
    Created on : Nov 15, 2011, 1:14:28 PM
    Author     : avinash.pandey
--%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <%@ page import="java.sql.*, java.util.*" %>
        <%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
        <%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>

        <%
					response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
					response.setHeader("Expires", "0");
					response.setHeader("Pragma", "no-cache");
					String context = request.getContextPath();
					Connection conn = null;
					ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
					String server_name = (String) session.getValue("server_name");
					String ecatPath = (String) session.getValue("ecatPATH");
					String mainURL = (String) session.getValue("mainURL");
					PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
					String servletURL = "";
					String contextPath = request.getContextPath();
					servletURL = object_pageTemplate.servletURL();
					String imagesURL = "";
					imagesURL = object_pageTemplate.imagesURL();
					String session_id = session.getId();
					String getSession = (String) session.getValue("session_value");
					if (!session_id.equals(getSession)) {
						 object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
						 return;
					}

					String heading = null;
					int width = 659;
					int height = 84;
					String tdData = "";
					String[] components = request.getParameterValues("attachedlist");
					String kitno = (String) session.getAttribute("kitno");
					String option = (String) session.getAttribute("option");
					LinkedHashMap<String, String> partMap = (LinkedHashMap) session.getAttribute("partMap");
					session.setAttribute("components",components);
					int selectedcompslength = components.length;

        %>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script src="<%=imagesURL%>js/validations.js" language="javascript" type="text/javascript"></script>
        <script>
            var selcomparr=new Array();
            <%
						 for (int j = 0; j < selectedcompslength; j++) {%>
							  selcomparr[<%=(j)%>]='<%=components[j]%>';
            <%}
            %>
                function validate()
                {
                    var length=<%=selectedcompslength%>;

                    for(var i=0;i<length;i++)
                    {
                        var val1=document.getElementById("qty"+i).value;
                        var quantity=TrimAll(val1);
                        if(quantity=='')
                        {
                            alert("Please Enter the Quantity.");
                            document.getElementById("qty"+i).value='';
                            document.getElementById("qty"+i).focus();
                            return false;
                        }
                        else if(quantity!='AR')
                        {
                            if(quantity==0)
                            {
                                alert("Quantity  can not be 0, Please give AR for 'As Required'.");
                                document.getElementById("qty"+i).value='';
                                document.getElementById("qty"+i).focus();
                                return false;
                            }
                            if(quantity>500)
                            {
                                alert("Quantity  can not be greater than 500, Please give AR for 'As Required'.");
                                document.getElementById("qty"+i).value='';
                                document.getElementById("qty"+i).focus();
                                return false;

                            }
                            var res=isProperAll(quantity);
                            if(res==false)
                            {
                                alert("No Special Symbols allowed. Please Enter only Integer Value for Quantity , or Give AR for 'As Required'.");
                                document.getElementById("qty"+i).value='';
                                document.getElementById("qty"+i).focus();
                                return false;
                            }
                            
                            var res=checkQuantity(quantity);
                            if(res==false)
                            {
                                alert("No Special Symbols allowed. Please Enter only Integer Value for Quantity , or Give AR for 'As Required'.");
                                document.getElementById("qty"+i).value='';
                                document.getElementById("qty"+i).focus();
                                return false;
                            }
                        }
                    
                    }
                    return true;
                }
        
                function CancelProcess()
					 {
						  var cncl=confirm("Do You Really want to Cancel the Current Process ?");
						  if(cncl==true)
								parent.content.location="<%=context%>/common/EAMG_home_page.jsp";

					 }

        </script>

    </head>
    <body>
        <%

					if (option.equals("create")) {
						 heading = "ADD QUANTITY KIT FOR \"" + kitno + "\"";
						 tdData = "MANAGE KIT >> ADD NEW KIT >> CREATE KIT BOM >> ADD QUANTITY";
					} else {
						 heading = "MODIFY QUANTITY FOR KIT \"" + kitno + "\"";
						 tdData = "MANAGE KIT >> MODIFY KIT >> MODIFY KIT BOM >> MODIFY QUANTITY";
					}


					object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);

					out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
					 <%-- <tr>
							<td>&nbsp;</td>
					  </tr>

					  <tr>
							<td align="center">
								 <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
									  <tr>
											<%
											 if (option.equals("create")) {
											 %>
												<td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Kit >> Add New Kit >> Create Kit BOM >> Add Quantity</b></td>
										  <% }
											else
											{
											 %>
												<td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Kit >> Modify Kit >> Modify Kit BOM >> Modify Quantity</b></td>
										 <%  }%>
									  </tr>
								 </table>
							</td>
					  </tr>
					  <tr>
							<td align="right" class="small">&nbsp;</td>
					  </tr>

                <tr>
                    <td valign="top">
                        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                 <%
                                 if (option.equals("create")) {
                                 %>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ADD QUANTITY</b></td>
                                <% }
                                else
                                {
                                 %>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;MODIFY QUANTITY</b></td>
                                <%}%>
                            </tr>--%>






					 <tr>
						  <td align="center" valign="top" bgcolor="#FFFFFF">
								<form action="<%=context%>/EAMG_kitInsertionByWz.do" method="post" name="form1" id="form1">

									 <br />
									 <table width="95%" border="0" cellspacing="2" cellpadding="2">

										  <%

													try {
														 conn = holder.getConnection();
														 ResultSet rs;
														 Statement stmt = null;
														 String comp_type = "";

										  %>
										  <tr>
												<td><table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC" bordercolor="#CCCCCC">
														  <tr>
																<td width="5%" align="center" class="blue"><strong class="heading">S.No.</strong></td>
																<td width="35%" height="25" align="left" class="blue"><strong class="heading">Component Number</strong></td>
																<td align="center" class="blue"><strong class="heading">Component Type</strong></td>
																<td align="center" class="blue"><strong class="heading">Quantity</strong></td>
														  </tr>
														  <%
																																						  for (int i = 0; i < components.length; i++) {
														  %>
														  <tr>
																<td align="center" bgcolor="#FFFFFF" class="text"><%=(i + 1) + "."%></td>
																<td align="left" bgcolor="#FFFFFF" class="text"><%="" + components[i]%><input type="hidden" name="components" value="<%=components[i]%>"/></td>


																<%comp_type = partMap.get(components[i]).toUpperCase();
																																																																if (comp_type.equals("PRT")) {
																																																																	 comp_type = "PART";
																																																																}

																%>
																<td align="center" bgcolor="#FFFFFF" class="text"><%=comp_type%><input type="hidden" name="comp_type" value="<%=comp_type%>"/></td>
																<td align="center" bgcolor="#FFFFFF" class="text"><span class="text">
																		  <%
																																																																		  String query1 = "Select QTY from CAT_S_KIT_BOM where  KIT_NO='" + kitno + "' and COMPONENT='" + components[i] + "'";
																																																																		  ////System.out.println("Query:"+query1);
																																																																		  stmt = conn.createStatement();
																																																																		  rs = stmt.executeQuery(query1);
																																																																		  comp_type = "PRT";
																																																																		  if (rs.next()) {
																																																																				String qty = rs.getString(1);
																		  %>
																		  <input type="text" style='width:100px;height:20px;' style='width:100px;height:20px;' name="qty" id="qty<%=i%>" value="<%=qty%>" />
																		  <%} else {%>
																		  <input type="text" style='width:100px;height:20px;' name="qty" id="qty<%=i%>" value=""/>
																		  <%}%>
																		  <%}%>



																	 </span></td>
														  </tr>



													 </table></td>
										  </tr>
										  <tr>
												<td>&nbsp;</td>
										  </tr>
										  <% } catch (Exception e) {
																						 e.printStackTrace();
																					}%>
										  <tr>
												<td colspan="2">
													 <table width="100%">
														  <tr>

																<td width="50%" align="right">
																	 <input type="submit" name="Next" id="Next" value="Submit" style="width:70px;" onClick="return validate();"/>
																</td>
																<td width="50%" align="left">
																	 <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
																</td>
														  </tr>
													 </table>
												</td>
										  </tr>

										  <tr><td class="red"> * Put AR in Quantity for As Required.</td></tr>



									 </table>
								</form></td>
					 </tr>
				</table>
				<%-- </td>
			</tr>
	  </table>--%>

        </div>
        <%
					out.println(object_pageTemplate.tableFooter());
        %>

    </body>
</html>

