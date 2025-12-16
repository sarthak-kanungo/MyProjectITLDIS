<%-- 
    Document   : EAMG_variant_aggregates_value
    Author     : Avinash.pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*" %>
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
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String modelNo = object_pageTemplate.MODEL_NO;
            String table = object_pageTemplate.GROUP;
            String aggregate = object_pageTemplate.AGGREGATE;
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title><%=UtilityMapkeys1.tile%></title>
    <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
    <script type="text/javascript" language="javascript">
            
        function validate()
        {
            var attachedlistvalueArr=document.getElementsByName("attachedlistvalue")
            var objRegExp = /^(\s*)$/;
            var strValue=null;
	  
            for(var k=0;k<attachedlistvalueArr.length;k++)
            {
                strValue =attachedlistvalueArr[k].value;
                strValue.replace(objRegExp, '');
                if(strValue.length == 0)
                {
                    alert("Please Enter "+attachedlistvalueArr[k].id);
                    attachedlistvalueArr[k].focus();
                    return false;
                }
                else
                {
                    var res=isProperAll(strValue);
                    if(res==false)
                    {
                        alert(attachedlistvalueArr[k].id +"Cannot Contain Special Character.");
                        attachedlistvalueArr[k].focus();
                    }
                }
            }
        }
        function CancelProcess()
        {
           // //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
           // if(cncl==true)
            {
                location.href="<%=contextPath%>/EAMG_Variant_Aggregates.do?option=initVariant";
            }
                
        }

    </script>

</head>

<body>
            <%       
                    String tdData ="MANAGE "+modelNo.toUpperCase()+" ADD "+aggregate.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    heading = "ADD "+aggregate.toUpperCase()+"";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
           %>
    <div align="center">
        <table width="700" border="0" cellspacing="1" cellpadding="1">
            <%--<tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                        <tr>
                            <td height="25" align="left" class="Lightblue"><b class="path">&nbsp;Manage <%=modelNo%> >>  Add <%=aggregate%></b></td>
                        </tr>
                    </table></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                        <tr>
                            <td height="25" align="left" class="Lightblue"><b class="heading">&nbsp;ADD <%=aggregate.toUpperCase()%></b></td>
                        </tr>--%>
                        <tr>
                            <td align="center" valign="top" bgcolor="#FFFFFF">
                                <html:form styleId="EAMG_Variant_AggregatesActionForm" method="post"  action="/EAMG_Variant_Aggregates.do">
                                    <br/>
                                    <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                        <tr>
                                            <td colspan="2"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                    <tr>
                                                        <td height="30" align="left" class="blue"><b class="heading">&nbsp;Enter  <%=aggregate%> Details </b></td>
                                                    </tr>
                                                </table></td>
                                        </tr>
                                        <logic:iterate id="data" name="EAMG_Variant_AggregatesActionForm" property="attachedlistArray" scope="session">
                                            <tr>
                                                <td width="40%" height="25" align="left" class="text">&nbsp;<bean:write name="data"/></td>
                                                <td width="60%" align="left" class="text"><label>
                                                        <html:text property="attachedlistvalue" style="width:254px;" styleId="${data}" value=""/>
                                                    </label></td>
                                            </tr>
                                        </logic:iterate>
                                        <tr>
                                            <td colspan="2">
                                                <table width="100%">
                                                    <tr>
                                                        <td width="50%" align="right">
                                                            <html:hidden property="option"  value="addVariant"/>
                                                            <input type="submit"  name="Next" id="Next" value="Submit" style="width:70px;" onClick="return validate();"/>
                                                        </td>
                                                        <td width="50%" align="left">
                                                            <input type="button"  name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>

                                    </table>
                                </html:form></td>
                        </tr>
                    <<%--/table></td>
            </tr>--%>
        </table>
    </div>
                        <%
                                            out.println(object_pageTemplate.tableFooter());
                            %>
</body>
</html>
