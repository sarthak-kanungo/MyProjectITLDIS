<%-- 
    Author     : Avinash.Pandey
--%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=UtilityMapkeys1.tile%></title>

        <%
                    Connection conn = null;
                    String contextPath = request.getContextPath();
                    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                    response.setHeader("Expires", "0");
                    response.setHeader("Pragma", "no-cache");
                    ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
                   // ConnectionHolder holder1 = (ConnectionHolder) session.getValue("servletapp.connection");
                    String server_name = (String) session.getValue("server_name");
                    String ecatPath = (String) session.getValue("ecatPATH");
                    String mainURL = (String) session.getValue("mainURL");
                    PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
                   // String servletURL = "";
                    //servletURL = object_pageTemplate.servletURL();
                    String imagesURL = "";
                    imagesURL = object_pageTemplate.imagesURL();
                    String modelNo = object_pageTemplate.MODEL_NO;
                    String group = object_pageTemplate.GROUP;

                    String session_id = session.getId();
                    String getSession = (String) session.getValue("session_value");
                    if (!session_id.equals(getSession)) {
                        object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                        return;
                    }
                    String heading = null;
                    int width = 659;
                    int height = 84;
        %>
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <%
                    String[] selectedgroups = request.getParameterValues("attachedlist");
                    String modelno = (String) session.getAttribute("modelno");
                    int selectedgroupslength = 0;
                    
                    if(selectedgroups!=null)
                    {
                        selectedgroupslength= selectedgroups.length;
                    }
                    //System.out.println(selectedgroupslength);
                    //System.out.println(selectedgroups[0]);
                    session.setAttribute("selectedgrps", selectedgroups);
                    String flag = "" + session.getAttribute("flag");

                    conn = holder.getConnection();
        
                    String tdData = "";
        %>
        <script>
            var selgrparr=new Array();
            <%
                        for (int j = 0; j < selectedgroupslength; j++) {%>
                            selgrparr[<%=(j)%>]='<%=selectedgroups[j]%>';
            <%}
            %>
                    
                function enableTxt(i)
                {
                    var select_ind= document.getElementById("select"+i).selectedIndex;
                    var len=document.getElementById("select"+i).options.length;
                    if(select_ind==(len-1))
                    {
                        document.getElementById("text"+i).value='';
                        document.getElementById("text"+i).disabled=false;
                        document.getElementById("text"+i).focus();
                    }
                    else
                    {
                        document.getElementById("text"+i).value='NA';
                        document.getElementById("text"+i).disabled=true;
                    }
                }
                function validate()
                {
                    var length1=<%=selectedgroupslength%>

                    for(var i=1;i<=length1;i++)
                    {
                        var val1=document.getElementById("disgrpno"+i).value;
                        var val=TrimAll(val1);
                        if(val=='')
                        {
                            alert("Please Enter the <%=group%> Map for <%=group%> Number '"+selgrparr[i-1]+"'.");
                            document.getElementById("disgrpno"+i).focus();
                            return false;
                        }
                        var select_ind= document.getElementById("select"+i).selectedIndex;
                        var len=document.getElementById("select"+i).options.length;
                        if(select_ind==0)
                        {
                            alert("Please Enter the Type for <%=group%> Number '"+selgrparr[i-1]+"'.");
                            document.getElementById("select"+i).focus();
                            return false;
                        }
                        if(select_ind==(len-1))
                        {
                            var val1=document.getElementById("text"+i).value;
                            var val=TrimAll(val1);
                            if(val=='')
                            {
                                alert("Please Enter the Type for <%=group%> Number '"+selgrparr[i-1]+"'.");
                                document.getElementById("text"+i).focus();
                                document.getElementById("text"+i).disabled=false;
                                return false;
                                            
                                
                            }
                            if(val.indexOf("'")!=-1){

                                alert('Special characters like \' are  not allowed in <%=group%> Type')
                                document.getElementById("text"+i).focus();
                                return false;
                            }
                            else
                            {
                                return true;
                            }
                        }
			
                    }
                }
        
                function CancelProcess()
                {
                   // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                    //if(cncl==true)
                    {
                        location.href="<%=contextPath%>/EAMG_create_model.do";
                    }
	
                }


        </script>

    </head>
    <body>
        <%
                    if (flag.equals("add_model")) {
                        tdData = "MANAGE " + modelNo.toUpperCase() + " >> ADD " + modelNo.toUpperCase() + " >> ASSIGN " + group.toUpperCase() + " MAP";
                    } else {
                        tdData = "MANAGE " + modelNo.toUpperCase() + " >> MODIFY " + modelNo.toUpperCase() + " >> ASSIGN " + group.toUpperCase() + " MAP";
                    }
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <% heading = "ASSIGN " + group.toUpperCase() + " MAP";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));%>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%if (flag.equals("add_model")) {%>
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >> Add <%=modelNo%> >> Assign <%=group%> Map</b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small"><b>Step 3 OF 3</b></td>
                </tr>
                <%-- <tr>
                     <td>&nbsp;</td>
                 </tr>--%>
                <%} else {%>
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Modify <%=modelNo%> >>  Assign <%=group%> Map</b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>--%>

                <%}%>

                 <%
                    if(selectedgroups==null)
                    {%>
                        <tr>
                        <td align="center" class="red">No Group is now attached to <%=modelNo%>  <%=modelno%>.</td>
                    </tr>
                    <% } else {%>

                <%--<tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ASSIGN <%=group.toUpperCase()%> MAP</b></td>
                            </tr>--%>
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form action="<%=contextPath%>/EAMG_attachgroup.do" method="post" name="form1" id="form1">
                            <br />
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                <tr>
                                    <td width="100%"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                                            <tr>
                                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;<%=modelNo%> No: <%=modelno%></b></td>
                                            </tr>
                                        </table></td>
                                </tr>
                                <tr>
                                    <td height="15"></td>
                                </tr>
                                <tr>
                                    <td>

                                        <table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#cccccc">
                                            <tr>
                                                <td width="25%" height="25" align="center" class="blue"><strong class="heading"><%=group%> No.</strong></td>
                                                <td width="25%" align="center" class="blue"><strong class="heading"><%=group%> Map</strong></td>
                                                <td colspan="2" align="center" class="blue"><strong class="heading"><%=group%> Type</strong></td>
                                            </tr>
                                            <%
                                                        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
                                                        String grp_no = "";
                                                        Vector typeVec = methodutil.getAllGrpTypes(conn);
                                                          System.out.println("i m "+modelno);
                                                        ArrayList modelGrpList =  methodutil.getGrpType(modelno,conn);
                                                        HashMap grpTypeMap = (HashMap)modelGrpList.get(0);
                                                        HashMap grpMap = (HashMap)modelGrpList.get(1);
                                                        String groupMap="";

                                                        for (int i = 1; i <= selectedgroupslength; i++) {
                                                            grp_no = selectedgroups[i - 1];
                                                            groupMap = (String)grpMap.get(grp_no);
                                                          
                                                            if(groupMap==null  || groupMap.equalsIgnoreCase(""))
                                                            {
                                                                groupMap = methodutil.getgrpmap(grp_no, conn);
                                                            }
                                            %>

                                            <tr>
                                                <td align="center" bgcolor="#FFFFFF" class="text"><%=selectedgroups[i - 1]%></td>
                                                <td align="center" bgcolor="#FFFFFF" class="text"><span class="text">
                                                        <input type="text" name="disgrpno<%=i%>" id="disgrpno<%=i%>" style="width:154px" value="<%=groupMap%>"/>
                                                    </span></td>
                                                <td align="center" bgcolor="#FFFFFF" class="text">
                                                    <select name="type<%=i%>" id="select<%=i%>" style="width:160px" onChange="enableTxt(<%=i%>);" class="text">
                                                        <option value="">--Select--</option>
                                                        <%                                                            
                                                            
                                                            for (int j = 0; j < typeVec.size(); j++) {
                                                                //System.out.println(typeVec.elementAt(j)+".."+type);
                                                                if (typeVec.elementAt(j).toString().equalsIgnoreCase(""+grpTypeMap.get(grp_no))) {%>
                                                        <option selected value="<%=typeVec.elementAt(j)%>"><%=typeVec.elementAt(j)%></option>
                                                        <%} else {%>
                                                        <option value="<%=typeVec.elementAt(j)%>"><%=typeVec.elementAt(j)%></option>
                                                        <%}
                                                                                                                                            }%>
                                                        <option>Other</option>
                                                    </select></td>
                                                <td align="center" bgcolor="#FFFFFF" class="text"><input type="text" name="txt_type<%=i%>" id="text<%=i%>" value="NA" disabled style="width:154px" /></td>
                                            </tr>
                                            <%}
                                            %>

                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
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

                            </table>
                        </form></td>
                </tr>
                <% } %>
                <%--</table></td>
        </tr>--%>
            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
