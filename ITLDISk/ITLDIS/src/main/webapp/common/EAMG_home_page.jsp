<%-- 
    Document   : EAMG_Home_page
    Author     : avinash.pandey
--%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title><%=UtilityMapkeys1.tile%></title>
        <%@page contentType="text/html"%>
        <%@page pageEncoding="UTF-8"%>
        <%@ page import="java.sql.*, java.util.*" %>
        <%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
        <%@ page import="viewEcat.comEcat.ConnectionHolder,java.sql.Connection,java.io.*,com.EAMG.common.*,EAMG.PartDAO_CUD.*" %>
        <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
        <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
        <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

        <%
                    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                    response.setHeader("Expires", "0");
                    response.setHeader("Pragma", "no-cache");
                    String server_name = (String) session.getValue("server_name");
                    String ecatPath = (String) session.getValue("ecatPATH");
                    String mainURL = (String) session.getValue("mainURL");
                    ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
                    String contextPath = request.getContextPath();
                    PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
                    String ecatPATH = (String) session.getValue("ecatPATH");
                    String userCode = (String) session.getValue("userCode");
                    String modelNo = object_pageTemplate.MODEL_NO;
                    String table = object_pageTemplate.GROUP;
                    String aggregate = object_pageTemplate.AGGREGATE;
                    String imagesURL = "";
                    imagesURL = object_pageTemplate.imagesURL();
 Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                    Connection conn = null;
                    String heading = null;
                    int width = 659;
                    int height = 84;
        %>
<%if(userFunctionalities.contains("39"))
    {%>
                <center><table id=loading cellspacing=0 cellpadding=0 border=0 height=80% width=90%>
                <tr>
                <td valign = middle>
                <table cellspacing=0 cellpadding=10 border=0 width=100% bordercolor="#D7D7D7">
                <tr>
                <td align = center>
                <font color ="#000099">
                    <img src='<%=imagesURL%>loading_1.gif'></img>
                </font></td>
                </tr>
                </table>
                </td>
                </tr>
                </table>
                </center>
        <style type="text/css">
            <!--
            body {
                margin-left: 0px;
                margin-top: 0px;
                margin-right: 0px;
                margin-bottom: 0px;

            }
            a:link {  text-decoration: none; image-decoration: none;}
            a:active { text-decoration: none;image-decoration: none;}
            a:visited { text-decoration: none; image-decoration: none;}
            a:hover { text-decoration: none;image-decoration: none;}
            -->
        </style>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <%
                    try {
                        conn = holder.getConnection();
                        ResultSet rs, rs1;
                        Statement stmt = null;
                        PreparedStatement pstmt = null;

                        Vector modelVec = new Vector();
                        String model_no = "", status = "", model = "", query2 = "";
                        query2 = "Select MODEL_NO from CAT_MODEL_GROUP where MODEL_NO=?";
                        pstmt = conn.prepareStatement(query2);
                        //String query1 = "Select MODEL_NO from MODELS where STATUS='INCOMPLETE'";
                        String query1 = "SELECT DISTINCT M.MODEL_NO,M.STATUS,MC.ENGINE_MODEL FROM CAT_MODELS M,CAT_MODEL_CLASSIFICATION MC WHERE M.MODEL_NO=MC.MODEL_NO AND  M.STATUS='INCOMPLETE'"; // AND  M.STATUS='INCOMPLETE'
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(query1);
                        while (rs.next()) {
                            model_no = rs.getString(1);
                            status = rs.getString(2);
                            model = rs.getString(3);
                            modelVec.add(new String(model_no));
                            modelVec.add(new String(status));
                            modelVec.add(new String(model));

                            pstmt.setString(1, model_no);
                            rs1 = pstmt.executeQuery();

                            if (rs1.next()) {
                                //grpAttachedVec.add(new Boolean(true));
                                modelVec.add(new Boolean(true));
                            } else {
                                //grpAttachedVec.add(new Boolean(false));
                                modelVec.add(new Boolean(false));
                            }
                            //System.out.println("grpAttachedVec==="+grpAttachedVec.size());
                            rs1.close();

                        }
                        rs.close();
                        stmt.close();
                        pstmt.close();
                        //System.out.println("grpAttachedVec:" + grpAttachedVec);

        %>
        <script language="javascript">
            function GetXmlHttpObject()
            {
                var objXmlHttp = null;
                if (navigator.userAgent.indexOf('Opera') >= 0)
                {
                    xmlHttp=new XMLHttpRequest();
                    return xmlHttp;
                }
                if (navigator.userAgent.indexOf('MSIE') >= 0)
                {
                    var strName = 'Msxml2.XMLHTTP';
                    if (navigator.appVersion.indexOf('MSIE 5.5') >= 0)
                    {
                        strName = 'Microsoft.XMLHTTP';
                    }
                    try
                    {
                        objXmlHttp = new ActiveXObject(strName);
                        // objXmlHttp.onreadystatechange = handler;
                        return objXmlHttp;
                    }
                    catch(e)
                    {
                        alert('Your Browser Does Not Support Ajax');
                        return;
                    }
                }
                if (navigator.userAgent.indexOf('Mozilla') >= 0)
                {
                    objXmlHttp = new XMLHttpRequest();
                    return objXmlHttp;
                }
            }
            
            var grpattachedarr=new Array();
            function store_array()
            {
            <%for (int i = 0; i < modelVec.size(); i = i + 4) {%>
                    grpattachedarr[<%=i + 3%>]='<%=modelVec.elementAt(i + 3)%>';

            <% }%>
                }
                
                function call_function_model(model_no,i)
                {
                    if(grpattachedarr[i]=='false')
                    {
                        var model_complete=callMsgBox2("No <%=table%> attached in <%=modelNo%> '"+model_no+"'. Do You want to attach <%=table%>?");
                        //alert(model_complete);
                        if(model_complete=='6')
                        {
                            location.href="<%=contextPath%>/temp_model.do?model_no="+model_no+"&flag=add_model&caller=home&model_check=model_groups";
                        }
                    }
                    if(grpattachedarr[i]=='true'){
            <%-- var attach_grps=callMsgBox2("Do You want to attach more <%=table%> in <%=modelNo%> '"+model_no+"'?");
             //alert(model_complete);
             if(attach_grps=='6')
             {
                 location.href="<%=contextPath%>/temp_model.do?model_no="+model_no+"&flag=modify_model&caller=home";
             }
             else
             {--%>
                         var model_complete=callMsgBox2("Do You want to complete <%=modelNo%> '"+model_no+"'?");
                         if(model_complete=='6')
                         {
                             location.href="<%=contextPath%>/EAMG_complete_model.do?model_no="+model_no;
                         }
            <%--  }--%>
                    }
                }
        </script>
        <SCRIPT LANGUAGE=vbscript>
            function callMsgBox2(strMsg)
            callMsgBox2 = msgBox(strMsg,4)
            end function
           
        </SCRIPT>
    </head>
    <body onload="store_array();"><br/>
        <%
                                                    heading = "Pending " + modelNo + "(s)";
                                                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">

            <table width="100%" border="0" cellpadding="10" cellspacing="0" bordercolor="#CCCCCC">

                <tr>
                    <td valign="top"  align="left">
                        <div id="1" style="border-style:solid;border : 1px;overflow: auto; height: 400px;width: 660px;">

                            <table width="95%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                <%if (modelVec.size() > 0) {%>
                                <%-- <tr>
                                     <td height="25" align="left" class="blue"><b class="heading">&nbsp;Pending&nbsp;<%=modelNo%>(s)</b></td>
                                 </tr>--%>

                                <tr>
                                    <td align="left" valign="top" bgcolor="#FFFFFF">
                                        <table width="100%" border="0" cellspacing="2" cellpadding="2">


                                            <tr>
                                                <td>
                                                    <table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC">
                                                        <tr>
                                                            <td width="10%" height="25" align="center" class="blue"><strong class="heading">#</strong></td>
                                                            <td width="20%" align="left" class="blue"><strong class="heading">&nbsp;<%=modelNo%>&nbsp;Number</strong></td>
                                                            <td width="20%" align="left" class="blue"><strong class="heading">&nbsp;Model</strong></td>
                                                            <td colspan="2" align="center" class="blue"><strong class="heading">Status</strong>
                                                                <table width=100%>
                                                                    <tr> <td class="heading" align="center"><font size="-2"><b><%=table%>&nbsp;Attached</b></font></td><td class="heading" align="center"><font size="-2"><b><%=modelNo%>&nbsp;Complete</b></font></td>
                                                                    </tr>
                                                                </table>
                                                            </td>

                                                        </tr>
                                                        <%
                                                            int cnt = 0;
                                                            String model_no1 = "", status1 = "", model1 = "";
                                                            for (int i = 0; i < modelVec.size(); i = i + 4) {
                                                                model_no1 = "" + modelVec.elementAt(i);
                                                                status1 = "" + modelVec.elementAt(i + 1);
                                                                //System.out.println("status=="+status);
                                                                model1 = "" + modelVec.elementAt(i + 2);
                                                        %>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF" class="text"><%=(++cnt)%></td>
                                                            <td align="left" bgcolor="#FFFFFF" class="text">&nbsp;<%=model_no1%></td>
                                                            <td align="left" bgcolor="#FFFFFF" class="text">&nbsp;<%=model1%></td>
                                                            <%

                                                                                                                                                                                                                                if (((Boolean) modelVec.elementAt(i + 3)).booleanValue()) {%>
                                                            <td align="center" bgcolor="#FFFFFF" class="text"><font color="green"><b>Yes</b></font></td>
                                                            <%																																								 } else {%>
                                                            <td align="center" bgcolor="#FFFFFF" class="text"><a href="#" onClick="call_function_model('<%=model_no1%>',<%=i + 3%>)"><font color="red"><b>No</b></font></a></td>
                                                            <%
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                //  }
                                                                                                                                                                                                                                //System.out.println("status12=="+status);
                                                                                                                                                                                                                                if (status1.equalsIgnoreCase("COMPLETE")) {
                                                                                                                                                                                                                                    // System.out.println("status121111111111111");
                                                            %>

                                                            <td align="center" bgcolor="#FFFFFF" class="text"><font color="green"><b>Yes</b></font></td>
                                                            <%																																									} else {
                                                                                                                                                                                                                                                                                                                                                                                                         //System.out.println("status12222222222222");
                                                            %>
                                                            <td align="center" bgcolor="#FFFFFF" class="text"><a href="#" onClick="call_function_model('<%=model_no1%>',<%=i + 3%>)"><font color="red"><b>No</b></font></a></td>
                                                            <%
                                                                                                                                                                                                                                }
                                                            %>
                                                        </tr>
                                                        <%}%>

                                                    </table></td>
                                            </tr>
                                            <%} else {%>
                                            <tr>
                                                <td height="25" align="left" class="red">&nbsp;No Pending <%=modelNo%> available in the Database.</td>
                                            </tr>
                                            <%}%>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <%
                                                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
    <script>
     document.getElementById('loading').style.display='none';
    </script>
</html>
<%} catch (Exception e) {
                e.printStackTrace();
            }

                    }%>