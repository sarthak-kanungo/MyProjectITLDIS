<%-- 
    Document   : EAMG_Advance_Search
    Created on : Aug 1, 2014, 2:44:39 PM
    Author     : sreeja.vijayakumaran
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder, viewEcat.comEcat.ComUtils" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,java.util.ArrayList" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<script type="text/javascript">
    function validate(){
        var count=0;
        var firstcount=0;
        var chassisno=document.getElementById("chassisno");
        var engineno=document.getElementById("engineno");
        var partno=document.getElementById("partno");
        var partdesc=document.getElementById("desc");
        if(notblank(chassisno.value)== true){
            count++;
        }else if(notblank(engineno.value)== true)
        {
            count++;
        }
        if(count == 0)
        {
            alert("Please enter Chassis No or Engine No !");
            document.getElementById("chassisno").focus();
            return false;
        }
        if(notblank(partno.value)== true){
            firstcount++;
        }else if(notblank(partdesc.value)== true)
        {
            firstcount++;
        }
        if(firstcount == 0)
        {
            alert("Please enter Part Code. or Description !");
            document.getElementById("partno").focus();
            return false;
        }
    }
    function notblank(obj)
    {
        var objSpecExp=/\S+/g;
        if(objSpecExp.test(obj))
        {
            return true;
        }
        return false
    }


</script>
<html>
    <%
                String contextPath = request.getContextPath();
                response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                response.setHeader("Expires", "0");
                response.setHeader("Pragma", "no-cache");
                session.removeAttribute("ECNImplementation");
                session.setAttribute("comp_type", "PRT");
                //System.out.println("set comp_type in session:PRT");
                String server_name = (String) session.getValue("server_name");
                String ecatPath = (String) session.getValue("ecatPATH");
                String mainURL = (String) session.getValue("mainURL");

                PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
                String servletURL = "";
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

    %>
    <center>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><%=UtilityMapkeys1.tile%></title>

            <link href="<%=imagesURL%>/style.css" rel="stylesheet" type="text/css" />

        </head>

        <%
                    out.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.advanceSearchHeader", session), width, height));
        %>
        <div align="center" width="750">
            <table width="940" border="0" cellspacing="1" cellpadding="1" align="center">
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form name="form1" onsubmit="" ENCTYPE="multipart/form-data" method="post" action="<%=contextPath%>/webservice.do">

                            <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                <tr>
                                    <td>
                                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td>
                                                    <table width=100% border=0 align=center cellspacing=1 cellpadding=2 bordercolor=#333333>
                                                        <tr style="height: 35%">
                                                            <td  width="19%"  align="left" class="links_txt" style="border-top: solid 1px #ccc;border-left: solid 1px #ccc;">&nbsp;<label><bean:message key="label.catalogue.searchModelCategory"/></label></td>
                                                            <td  width="1%"  align="left" class="links_txt" style="border-top: solid 1px #ccc"><label>
                                                                    <input type="radio" checked name="radio1" id="domestic" value="domestic" onclick=""/></label>
                                                            </td>
                                                            <td  width="11%"  align="left" class="links_txt"  style="border-top: solid 1px #ccc;"><label><bean:message key="label.catalogue.forDomestic"/></label></td>
                                                            <td  width="1%"  align="left" class="links_txt" style="border-top: solid 1px #ccc;"><label>
                                                                    <input type="radio"  name="radio1" id="export" value="export" onclick=""/> </label>
                                                            </td>
                                                            <td width="13%"   align="left" class="links_txt" style="border-top: solid 1px #ccc;"><label><bean:message key="label.catalogue.forExport"/></label></td>

                                                            <td  width="25%"  align="right" class="links_txt" style="border-top: solid 1px #ccc;"><label><bean:message key="label.catalogue.searchPartCategory"/></label>&nbsp;</td>
                                                            <td  width="4%"  align="right" class="links_txt" style="border-top: solid 1px #ccc"><label>
                                                                    <input type="radio" checked name="radio2" id="tractorparts" value="tractorparts" onclick=""/> </label>
                                                            </td>
                                                            <td  width="13%"  align="left" class="links_txt" style="border-top: solid 1px #ccc"><label><bean:message key="label.catalogue.forTractorParts"/></label></td>
                                                            <td  width="1%"  align="left" class="links_txt" style="border-top: solid 1px #ccc"><label>
                                                                    <input type="radio"  name="radio2" id="engineparts" value="engineparts" onclick=""/> </label>
                                                            </td>
                                                            <td   align="left" class="links_txt" colspan="2" style="border-top: solid 1px #ccc;border-right: solid 1px #ccc"><label><bean:message key="label.catalogue.forEngineParts"/></label></td>
                                                        </tr>

                                                        <tr style="height: 35%">
                                                            <td colspan="15" style="border-top: solid 1px #ccc;border-bottom: solid 1px #ccc;border-left: solid 1px #ccc;border-right: solid 1px #ccc">
                                                                <table width="100%" border="0" cellspacing="2" cellpadding="2"><tr>
                                                                        <td   align="left" class="links_txt" ><label><bean:message key="label.catalogue.chassisNo"/></label>&nbsp;
                                                                            <input type="links_txt"  name="chassisno" id="chassisno" value="" style="width: 150px"/>
                                                                        </td>
                                                                        <td   align="left" class="links_txt" ><label><bean:message key="label.catalogue.engineNo"/></label>&nbsp;
                                                                            <input type="links_txt"  name="engineno" id="engineno" value="" style="width: 150px" />
                                                                        </td>

                                                                        <td  align="left" class="links_txt" ><label><bean:message key="label.catalogue.partCode"/></label>&nbsp;
                                                                            <input type="links_txt"  name="partno" id="partno" value="" style="width: 150px"/>
                                                                        </td>
                                                                        <td   align="left" class="links_txt"><label><bean:message key="label.catalogue.description"/></label>&nbsp;
                                                                            <input type="links_txt"  name="desc" id="desc" value="" style="width: 150px"/>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td  align="center" class="links_txt" colspan="15" style="border-bottom: solid 1px #ccc;border-left: solid 1px #ccc;border-right: solid 1px #ccc">
                                                                &nbsp;&nbsp;<input type="submit"  name="" id="" value="<bean:message key="label.catalogue.advanceSearch"/>" onclick="return validate()" style="height: 110%"/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>


                        </form>
                    </td>
                </tr>
                <c:if test="${step eq '2'}">

                    <%
                                ArrayList<String> chassisno = (ArrayList) request.getAttribute("chassisno");
                                ArrayList<String> engineno = (ArrayList) request.getAttribute("engineno");
                                ArrayList<String> partcode = (ArrayList) request.getAttribute("partcode");
                                ArrayList<String> partdesc = (ArrayList) request.getAttribute("partdescr");
                                ArrayList<String> qty = (ArrayList) request.getAttribute("qty");
                                ArrayList<String> bomcd = (ArrayList) request.getAttribute("bomcd");
                                ArrayList<String> bomdesc = (ArrayList) request.getAttribute("bomdesc");
                    %>
                    <script>
                        function MM_openBrWindow(theURL,winName,features)
                        {
                            var isValidPopUpBlocker=detectPopupBlocker()
                            if (isValidPopUpBlocker== false)
                            {
                                return ;
                            }
                            window.open(theURL,winName,features)
                        }

                        function detectPopupBlocker()
                        {
                            var myTest = window.open("about:blank","","directories=no,height=100,width=100,menubar=no,resizable=no,scrollbars=no,status=no,titlebar=no,top=0,location=no")
                            if (!myTest)
                            {
                                alert("Your Pop-up Blocker is Enabled. Please Add our site http://<%=server_name%> to your trusted sites.");
                                parent.location.href = 'Logout'
                                return false;
                            }
                            else
                            {
                                myTest.close()
                                return true;
                            }
                        }

                    </script>
                    <tr><td colspan="15" class="links_txt">Search Results For Chassis No "${webserviceform.chassisno}" and Engine No "${webserviceform.engineno}" and Part No. "${webserviceform.partno}" and Description "${webserviceform.desc}"</td></tr>


                    <tr>
                        <td colspan="15">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                <tr>
                                    <td>
                                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td>
                                                    <table width=100%  border=0 align=center cellspacing=1 cellpadding=2 bordercolor=#333333>
                                                        <tr style="height: 15px">
                                                            <td class="heading1" align=left ><b>Chassis No.</b></td>
                                                            <td class="heading1" align=left ><b>Engine No.</b></td>
                                                            <td class="heading1" align=left ><b>Part No.</b></td>
                                                            <td class="heading1" align=left ><b>Description</b></td>
                                                            <!--<td class="links_txt" align=left><b>qty</b></td>-->
                                                            <td class="heading1" align=left ><b>Variant</b></td>
                                                            <td class="heading1" align=left ><b>Variant Description</b></td>
                                                            <td class="heading1" align=center  title="Part Where Used"><b>?</b></td>
                                                        </tr>
                                                        <%if (chassisno.size() == 0) {%>
                                                        <tr  style="height: 55px"><td colspan="15" class="links_txt" align="center"><b>No Data Found.</b></td>

                                                        </tr>
                                                        <%} else {

                                                            for (int i = 0; i < chassisno.size(); i++) {
                                                        %>
                                                        <tr  style="height: 25px">
                                                            <td class="links_txt" align=left ><%=chassisno.get(i)%> </td>
                                                            <td class="links_txt" align=left ><%=engineno.get(i)%> </td>
                                                            <td class="links_txt" align=left ><a href="javascript:MM_openBrWindow('<%=servletURL%>PartDetails?partNo=<%=partcode.get(i)%>&compType=PRT','DETAILS','scrollbars=yes,width=700,height=605')"><%=partcode.get(i)%></a></td>
                                                            <td class="links_txt" align=left ><a href="javascript:MM_openBrWindow('<%=servletURL%>PartDetails?partNo=<%=partcode.get(i)%>&compType=PRT','DETAILS','scrollbars=yes,width=700,height=605')"><%=partdesc.get(i)%> </a></td>
                                                            <!-- <td class="links_txt" align=left> </td>-->
                                                            <td class="links_txt" align=left ><%=bomcd.get(i)%> </td>
                                                            <td class="links_txt" align=left ><%=bomdesc.get(i)%> </td>
                                                            <td class="links_txt" align=center  title="Part Where Used"><a href="javascript:MM_openBrWindow('<%=servletURL%>Part_where_used?part=<%=partcode.get(i)%>','WHEREUSED','scrollbars=yes,width=700,height=605')">?</a></td>
                                                        </tr>
                                                        <%}%>
                                                        <%}%>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </c:if>
            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>

    </center>
</html>