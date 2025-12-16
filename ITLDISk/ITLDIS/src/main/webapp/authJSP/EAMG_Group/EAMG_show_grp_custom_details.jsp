<%-- 
    Document   : EAMG_show_grp_custom_details
   
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>

<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String context = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
String contextPath = request.getContextPath();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <%
                    String grpno = (String) session.getAttribute("group_no");
                    Vector GrpBomVec = (Vector) session.getAttribute("GrpBomVec");
                    Vector CompVec = (Vector) GrpBomVec.elementAt(0);
                    Vector CompTypeVec = (Vector) GrpBomVec.elementAt(1);
                    Vector QtyVec = (Vector) GrpBomVec.elementAt(2);
                    Vector SeqNoVec = (Vector) GrpBomVec.elementAt(3);
                   // Vector RevNoVec = (Vector) GrpBomVec.elementAt(4);
                    //Vector LevelVec = (Vector) GrpBomVec.elementAt(5);
                    Vector CompDescVec = (Vector) GrpBomVec.elementAt(4);
                    Vector IndexVec = (Vector) GrpBomVec.elementAt(5);
                    String ECNImplementation = (String) session.getAttribute("ECNImplementation");
                    if (ECNImplementation == null) {
                        ECNImplementation = "";
                    }
        %>
        <script>
            function updateGrpStatus()
            {
                var strURL ="EAMG_set_grp_status.jsp";
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    stateChanged();
                };
                xmlHttp.open("GET", strURL, true);
                xmlHttp.send(null);
            }
            function stateChanged()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = xmlHttp.responseText;
            <%if (ECNImplementation.equals("ECNImplementation")) {%>
                        window.parent.opener.location.href='<%=context%>/authJSP/EAMG_Group/EAMG_ecn_approve_group.jsp'
                        self.focus();
                        parent.close();
            <%} else {%>
                        alert("Please Approve Group '<%=grpno%>'.");
                        window.parent.opener.location.href='<%=context%>/common/EAMG_home_page.jsp'
                        self.focus();
                        parent.close();
            <%}%>

                    }

                }
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
        </script>
    </head>
    <body>
       <%
                    String tdData="MANAGE GROUP >> ADD NEW GROUP >> INSERT REF COORDS ";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    heading ="INSERT REFERENCE COORDINATES";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="500" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td width="500"><b class="heading">&nbsp;GROUP NUMBER:&nbsp;<%=grpno%> </b>&nbsp; &nbsp;<input type="button" name="finish" value="Finish" onclick="updateGrpStatus();"/></td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Group>> Add New Group >> Insert Ref Coords </b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small"><b>Step 2 OF 3 </b></td>
                </tr>

                
               <%-- <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;INSERT REFERENCE COORDINATES  </b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form>
                                        <br />

                                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                            <!-- <tr>
                                                 <td class="text"><table width="99%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                                         <tr>
                                                             <td height="30" align="left" class="grey"><b class="heading">&nbsp;GROUP NUMBER:&nbsp;<%=grpno%> </b></td>
                                                         </tr>
                                                     </table></td>
                                             </tr>
                                             <tr>
                                                 <td class="text">&nbsp;</td>
                                             </tr>
                                            -->
                                            <tr>
                                                <td align="center">
                                                    <%int l = CompVec.size();
                                                                if (l >= 20) {
                                                    %>
                                                    <div style="border-style:solid;border : 1px;overflow: auto; height: 500px;">
                                                        <%}%>
                                                        <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#b32b24">
                                                            <tr class="blue">
                                                                <td width="10%" align="center"  class="path">S.No.</td>
                                                                <td width="10%" align="center"  class="path">Seq</td>
                                                                <td width="60%" align="center"  class="path">Component</td>
                                                                <td width="10%" align="center"  class="path">Type</td>
                                                                <td width="10%" align="center"  class="path">Qty</td>
                                                            </tr>
                                                            <%
                                                                        for (int i = 0; i < l; i++) {%>
                                                            <tr onMouseOut="style.backgroundColor='#ffffff';" onMouseOver="style.backgroundColor='#cccccc';">
                                                                <td align="center"><font face="Arial" size="1" color="black"><%=IndexVec.elementAt(i)%>.</font></td>
                                                                <%if (("" + SeqNoVec.elementAt(i)).equals("")) {%>
                                                                <td align="center"><font face="Arial" size="1" color="black">&nbsp;</font></td>
                                                                <%} else {%>
                                                                <td align="center"><font face="Arial" size="1" color="black"><%=SeqNoVec.elementAt(i)%></font></td>
                                                                <%}%>
                                                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" bordercolor="#dce0f0">
                                                                        <tr height="20">
                                                                            <%
                                                                                                                                            ///Integer level = (Integer) LevelVec.elementAt(i);
                                                                                                                                            //int space = level.intValue();
                                                                                                                                            //for (int j = 0; j < space; j++) {//%>
                                                                            <td width="10">&nbsp;</td>
                                                                            <%//}
                                                                                                                                            String comptype = "" + CompTypeVec.elementAt(i);
                                                                                                                                            if (comptype.equals("ASM")) {

                                                                            %>
                                                                            <td align="left"><font face="Arial" size="1" color="red"><%=CompVec.elementAt(i)%> (<%=CompDescVec.elementAt(i)%>)</font></td>
                                                                            <%} else {

                                                                            %>
                                                                            <td align="left"><font face="Arial" size="1" color="black"><%=CompVec.elementAt(i)%> (<%=CompDescVec.elementAt(i)%>)</font></td>
                                                                            <%}%>
                                                                        </tr>
                                                                    </table></td>
                                                                <td align="center"><font face="Arial" size="1" color="black"><%=comptype%></font></td>
                                                                <td align="center"><font face="Arial" size="1" color="black"><%=QtyVec.elementAt(i)%></font></td>
                                                            </tr>
                                                            <%}

                                                            %>
                                                        </table>
                                                        <%
                                                                    if (l >= 20) {
                                                        %>
                                                    </div>
                                                    <%}%>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center"><label></label></td>
                                            </tr>
                                        </table>

                                    </form></td>
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


