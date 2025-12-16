<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="viewEcat.comEcat.ConnectionHolder,java.sql.Connection,java.io.*,com.EAMG.common.*,EAMG.PartDAO_CUD.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
    <%
			  response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
			  response.setHeader("Expires", "0");
			  response.setHeader("Pragma", "no-cache");
			  ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
			  String server_name = (String) session.getValue("server_name");
			  String ecatPath = (String) session.getValue("ecatPATH");
			  String mainURL = (String) session.getValue("mainURL");
			  PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
			  String servletURL = "";
			  servletURL = object_pageTemplate.servletURL();
			  String imagesURL = "";
			  String contextPath = request.getContextPath();
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
			  Connection conn = null;
			  Vector typeVec = new Vector();
			  /*try {
					conn = holder.getConnection();
					EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
					typeVec = methodutil.getCategoryTypes(conn);
			  } catch (Exception e) {
					e.printStackTrace();
			  }*/
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <script language="javascript">
            var paramValTypeArr=new Array();
            <%
						 response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
						 response.setHeader("Expires", "0");
						 response.setHeader("Pragma", "no-cache");
						 String comp_type = "";
						 String comp_no = "";
						 String iscomppresent = "";

						 try {
							  comp_type = (String) session.getAttribute("comp_type");
							  comp_no = ((String) session.getAttribute("comp_param_list")).toUpperCase();
							  iscomppresent = (String) session.getAttribute("iscomppresent");
							  String type = "";
							  if (comp_type.equals("PRT")) {
									type = "Part";
							  }
							  conn = holder.getConnection();
							  EAMGPartDAO_R partDAO = new EAMGPartDAO_R();
							  Vector ststusVec = partDAO.getPartStatus(conn);
            %>
                function stringiChars(string)
                {
                    if (!string) return false;
                   var iChars = '@&\\\'\"';

                    for (var i = 0; i < string.length; i++) {
                        if (iChars.indexOf(string.charAt(i)) != -1)
                            return false;
                    }
                    return true;
    
                }
                function validate()
                {
                    var partdesc=document.form1.part_desc.value;
                    var part_rmk=document.form1.part_rmk.value;
						  //var categoryTypeOthObject=document.form1.categoryTypeOth;
                    //var partStatus=TrimAll(document.form1.partStatus.value);
                    //var Serviceable=TrimAll(document.form1.Serviceable.value);
                    partdesc=TrimAll(partdesc);
                    part_rmk=TrimAll(part_rmk);
                    if(partdesc=="" || partdesc==null)
                    {
                        alert("Please Enter Part Description.");
                        form1.part_desc.value="";
                        form1.part_desc.focus();
                        return false;
                    }
                    else{
                        var res=stringiChars(partdesc);
                        if(res==false)
                        {
                            alert('Part Description can not contain any special characters.');
                            form1.part_desc.value="";
                            form1.part_desc.focus();
                            return false;
                        }
                    }
                    if(part_rmk!=""){
                        var res=stringOnlyValidation(part_rmk);
                        if(res==false)
                        {
                            alert('Part Remarks can not contain any special characters.');
                            form1.part_rmk.value="";
                            form1.part_rmk.focus();
                            return false;

                        }
                    }

						  <%--if(categoryTypeOthObject.disabled==false)
						  {
								if(TrimAll(categoryTypeOthObject.value)=="")
								{

									 alert('Please Enter Category');
									 categoryTypeOthObject.focus();
									 return false;
								}
								else
								{
									 var res=stringOnlyValidation(categoryTypeOthObject.value);
									 if(res==false)
									 {
										  alert('Category can not contain any special characters.');
										  categoryTypeOthObject.focus();
										  return false;
									 }
								}
						  }--%>
                
                    
                    return true;
                } 
                    
                function CancelProcess()
                {
                    //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                    //if(cncl==true)
                    {
                        parent.content.location="<%=contextPath%>/authJSP/EAMG_Part/EAMG_comp_modify.jsp";
                    }
        
                }

					<%-- function enableCategoryOther()
					 {
						  if(document.getElementById("categoryType").value == '1Oth')
						  {
								document.getElementById("categoryTypeOth").disabled = false
								document.getElementById("categoryTypeOth").style.background = '#FFFFFF'
								document.getElementById("categoryTypeOth").focus()
								return;
						  }
						  else
						  {
								document.getElementById("categoryTypeOth").value=''
								document.getElementById("categoryTypeOth").disabled = true
								return;
						  }
					 }--%>
        </script>
    </head>
    <body>
        <%
                    String tdData = "MANAGE PART >> MODIFY PART >> PART DETAILS";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
										 heading = "MODIFY PART DETAILS";
										 out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
               <%-- <tr>
                    <td>&nbsp;</td>
                </tr>--%>
                <%if (iscomppresent.equals("notpresent")) {%>
                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Part >> Modify Part >> Part Details</b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>

                <tr>
                    <td align="center" class="red" height="30"><%=type%> '<%=comp_no%>' does not exist in database. </td>
                </tr>
                <tr>  <td class="red-for-temmplate-link" align="center" ><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Part/EAMG_comp_modify.jsp';">Try Again</a></td></tr>
                <%} else {%>

                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Part >> Modify Part >> Part Details</b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>


                <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;<%=type.toUpperCase()%> DETAILS </b></td>
                            </tr>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form name="form1"  action="<%=contextPath%>/EAMG_CompModifParamsAction.do" onsubmit="return validate();">
                                        <br />
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <%
																ResultSet rs2 = null;
																Statement stmt3 = null;
																String category = "";
																stmt3 = conn.createStatement();
																// rs2 = stmt3.executeQuery("select p1,p3,p4,np4 from part where part_no='" + comp_no + "'");
																rs2 = stmt3.executeQuery("select p1,p3,p4,np4 from cat_part where part_no='" + comp_no + "'");
																while (rs2.next()) {
																	 String desc = rs2.getString(1);
																	 String partRmk = rs2.getString(2);
																	 if ((partRmk != null)) {
																		  partRmk = partRmk;
																	 } else {
																		  partRmk = " ";
																	 }
																	 String serviciability = rs2.getString(3);
																	 category = rs2.getString("np4");

																	 if (category == null) {
																		  category = "";
																	 }
																	// System.out.println("category"+category);

                                            %>
                                            <tr>
                                                <td width="45%" height="25" align="left" class="text">&nbsp;<%=type%> Number </td>
                                                <td width="55%" height="25" align="left" class="text"> <%=comp_no%> </td>
                                            </tr>
                                            <tr>
                                                <td height="25" align="left" class="text">&nbsp;Part Description</td>
                                                <td height="25" align="left" class="text"><label>
                                                        <input type="text" size=15 name="part_desc" id="part_desc"  value="<%=desc%>" style="width:'150px'" class="text"/>
                                                    </label></td>
                                            </tr>
                                            <tr>
                                                <td height="25" align="left" class="text">&nbsp;Part Remarks</td>
                                                <td height="25" align="left" class="text"><label>
                                                        <input type="text" size=15 name="part_rmk" id="part_rmk" value="<%=partRmk%>" style="width:'150px'" class="text" onblur="stringOnlyValidation(document.form1.part_rmk)"/>
                                                    </label><input type="hidden"  name="categoryType" id="categoryType" value=""></td>
                                            </tr>
                                            <%--<tr>
                                                <td class="text" >&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td class="text" >&nbsp;</td>
                                            </tr>
                                            //if (typeVec.size() == 0) {--%>
														  <%--
                                            <tr>
                                                <td height="100" align="center" class="red" colspan="2" >No Category Classification Level available in Database.. </td>

                                            </tr>
                                            <%} else {%>
                                            <tr>
                                                <td colspan="2">
                                                    <table width="100%" border="0" cellpadding="1" cellspacing="1" bordercolor="#CCCCCC" bgcolor="#CCCCCC">
                                                        <tr>
                                                            <td width="24%" align="center" class="blue"><strong class="heading">Level Description</strong></td>
                                                            <td width="66%" colspan="2" align="center" class="blue"><strong class="heading">Level Value</strong></td>
                                                        </tr>
                                                        <tr>
                                                            <td height="25" align="center" bgcolor="#FFFFFF" class="text">&nbsp;Category</td>
                                                            <td height="25" align="left" bgcolor="#FFFFFF" class="text">
                                                                &nbsp;&nbsp;&nbsp;<select name="categoryType" id="categoryType" style="width:160px" onchange="enableCategoryOther()" class="text">
                                                                    <option value="">--Select--</option>

																						  <%System.out.println("typeVec"+typeVec);%>
                                                                    <%if (typeVec.size() > 0) {
																									 if (typeVec != null) {

																										  for (int j = 0; j < typeVec.size(); j++) {
																												//if(category!=null && category.equals("")){
																												if (category.equalsIgnoreCase(typeVec.elementAt(j).toString())) {
                                                                    %>
                                                                    <option selected><%=typeVec.elementAt(j)%></option>
                                                                    <%} else {%>
                                                                    <option ><%=typeVec.elementAt(j)%></option>
                                                                    <%}
																																																	 }%>
                                                                    <option value="1Oth">OTHER</option>>
                                                                </select>
                                                            </td>
                                                            <td align="center" bgcolor="#FFFFFF"><span class="text">

                                                                    <input type="text" name="categoryTypeOth" id="categoryTypeOth"  value="" class="text"  disabled="true" style="width:154px" onblur="stringOnlyValidation(document.form1.categoryTypeOth)"/>
                                                                </span>
                                                            </td>
                                                            <% }
																					 }
                                                            %>


                                                        </tr>
                                                    </table></td>
                                            </tr>
                                            <% }%>--%>
                                           <tr>
                                                <td height="25" align="left" class="text">&nbsp;Serviciability</td>
                                                <td height="25" align="left" class="text"><label>
                                                        <select name="Serviceable" style="width:'150px'" id="Serviceable" class="text">
                                                            <option value='0'>--Select--</option>
                                                            <%if (serviciability.equals("Y")) {%>
                                                            <option value='Y' selected>YES</option>
                                                            <%} else {%>
                                                            <option value='Y'>YES</option>
                                                            <%}%>
                                                            <%if (serviciability.equals("N")) {%>
                                                            <option value='N' selected>NO</option>
                                                            <%} else {%>
                                                            <option value='N'>NO</option>
                                                            <%}%>

                                                        </select>
                                                    </label></td>
                                            </tr>
                                            

                                            <%--<tr>
                                                <td colspan="2"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                                        <tr>
                                                            <td height="30" align="left" class="Lightblue"><b class="heading">&nbsp;Enter Part Status </b></td>
                                                        </tr>
                                                    </table></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"><table width="100%" border="0" cellpadding="1" cellspacing="1" bordercolor="#333333" bgcolor="#000000">
                                                        <tr>
                                                            <td width="24%" align="center" class="Lightblue"><strong class="heading">Part Status</strong></td>
                                                            <td width="66%" colspan="2" align="center" class="Lightblue"><strong class="heading">Value</strong></td>
                                                        </tr>
                                                        <tr>
                                                            <td height="25" align="center" bgcolor="#FFFFFF" class="text">&nbsp;Status</td>
                                                            <td height="25" align="left" bgcolor="#FFFFFF" class="text"> 
                                                                    &nbsp;&nbsp;<select name="partStatus"  style="width:'150px'" id="partStatus" class="text" onchange="enablePartOther()">
                                                                        <option value='0'>--Select--</option>
                                                                        <%
                                                                                                                            for (int i = 0; i < ststusVec.size(); i++) {
                                                                                                                                String np4 = ((String) ststusVec.elementAt(i));
                                                                                                                                if (np4.equals(partStatus)) {%>
                                                                        <option selected><%=np4%></option>
                                                                        <%} else {%>
                                                                        <option><%=np4%></option>
                                                                        <%}
                                                                                                                            }%>
                                                                        <option value="1Oth">OTHER</option>
                                                                    </select>
                                                                </td>
                                                            <td align="center" bgcolor="#FFFFFF"><span class="text">
                                                                    <input type="text" name="partStatusOth" id="text" value="" class="text"  disabled="true" style="width:154px" onblur="stringOnlyValidation(document.form1.partStatusOth)"/>
                                                                </span>
                                                            </td>

                                                        </tr>
                                                    </table></td>
                                            </tr>--%>
                                            <%}
                                            %>
                                        </table>
                                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td colspan="2">
                                                    <table width="100%">
                                                        <tr>

                                                            <td width="50%" align="right">
                                                                <input  type="submit" name="Next" id="Next" value="Submit" style="width:70px;"/>
                                                            </td>
                                                            <td width="50%" align="left">
                                                                <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>

                                        </table>

                                    </form></td>
                            </tr>
                        </table></td>
                </tr>
                <%}%>
            </table>
        </div>
        <%
										 out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
<%} catch (Exception e) {
			  e.printStackTrace();
		 } finally {
			  //conn.close();
		 }



%>