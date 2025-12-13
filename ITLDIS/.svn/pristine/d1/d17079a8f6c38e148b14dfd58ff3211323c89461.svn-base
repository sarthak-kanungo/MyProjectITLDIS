<%-- 
    Document   : viewChassis
    Created on : Apr 11, 2014, 3:46:39 PM
    Author     : tarun.lal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();

            String tempParam = request.getParameter("tempParam");

            ArrayList userFunctionalities = (ArrayList) session.getAttribute("userFun");
%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/login.do?option=homePage'>HOME</a></li>
            <li class="breadcrumb_link"> <%=request.getParameter("heading").toUpperCase()%> </li>

        </ul>
    </div>
    <br/>
    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">

            <div class="con_slidediv1" style="position: relative;width: 100%">

                <h1><%=request.getParameter("heading").toUpperCase()%></h1>

                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <c:if test="${!empty pgList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="login.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="viewChassis"/>
                                    <pg:param name="tempParam"  value='<%=request.getParameter("tempParam")%>'/>
                                    <pg:param name="heading"  value='<%=request.getParameter("heading")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap class="headingSpas" align="center" width="8%" ><b>S.No</b></td>
                                            <td  nowrap class="headingSpas" align="left" width="20%" style="padding-left:5px; padding-right: 5px"><b>Chassis No</b></td>
                                            <%if (!tempParam.equals("chassisPending")) {%>
                                            <td  nowrap class="headingSpas" align="left" width="20%" style="padding-left:5px; padding-right: 5px"><b>Model No</b></td>
                                            <td  nowrap class="headingSpas" align="left" width="20%" style="padding-left:5px; padding-right: 5px"><b>Model Desc</b></td>
                                            <td  nowrap class="headingSpas" align="left" width="20%" style="padding-left:5px; padding-right: 5px"><b>Product Group</b></td>
                                            <%}
                                                        if (!tempParam.equals("travelcardPending")) {
                                                            if (userFunctionalities.contains("27")) {
                                            %>
                                            <td  nowrap class="headingSpas" align="center" width="20%" style="padding-left:5px; padding-right: 5px"><b>View Travel Card</b></td>
                                            <%}
                                                                                                        if (!tempParam.equals("chassisPending")) {%>

                                            <%if (tempParam.equals("dispatchPending")) {
                                                                                                                                                                    if (userFunctionalities.contains("31")) {%>
                                            <td  nowrap class="headingSpas" align="center" width="20%"style="padding-left:5px; padding-right: 5px" ><b>
                                                    Dispatch Machine
                                                </b></td>
                                                <%}
                                                } else if (tempParam.equals("verifiPending")) {
                                                    if (userFunctionalities.contains("28")) {
                                                %>
                                            <td  nowrap class="headingSpas" align="center" width="20%"style="padding-left:5px; padding-right: 5px" ><b>
                                                    Verify Travel Card
                                                </b></td>
                                                <%}
                                                } else if (tempParam.equals("verifiapprovalPending")) {
                                                    if (userFunctionalities.contains("29")) {
                                                %>
                                            <td  nowrap class="headingSpas" align="center" width="20%"style="padding-left:5px; padding-right: 5px" ><b>
                                                    Approve Travel Card
                                                </b></td>
                                                <%}
                                                    }%>

                                            <%}
                                                            }%>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="pgList" name="pgList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" width="100" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${pgList.chassisNo}
                                                        </span>
                                                    </td>
                                                    <%if (!tempParam.equals("chassisPending")) {%>
                                                    <td align="left" bgcolor="#FFFFFF" width="100" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="coupon${sno}" >
                                                            ${pgList.modelNo}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="100" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="coupon${sno}" >
                                                            ${pgList.modelDesc}
                                                        </span>
                                                    </td>

                                                    <td align="left" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        ${pgList.productGroupShow}
                                                    </td>
                                                    <%}
                                                                if (!tempParam.equals("travelcardPending")) {%>

                                                    <%if (tempParam.equals("chassisPending") || tempParam.equals("dispatchPending") || tempParam.equals("verifiPending") || tempParam.equals("verifiapprovalPending")) {
                                                                                                                            if (userFunctionalities.contains("27")) {
                                                    %>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="${pageContext.request.contextPath}/MachineCommissioning.do?option=viewMachineHistory&type=step2&chassisNohide=${pgList.chassisNo}" class="headingSpas" style="color: blue !important;" >
                                                                View
                                                            </a>
                                                        </span>
                                                    </td>
                                                    <%}

                                                    %>

                                                    <%if (!tempParam.equals("chassisPending")) {%>

                                                    <%if (tempParam.equals("dispatchPending")) {
                                                            if (userFunctionalities.contains("31")) {%>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="${pageContext.request.contextPath}/MachineCommissioning.do?option=checkChasisNo&type=step2&chassisNo=${pgList.chassisNo}" class="headingSpas" style="color: blue !important;" >
                                                                Dispatch
                                                            </a>
                                                        </span>
                                                    </td>
                                                    <%}
                                                    } else if (tempParam.equals("verifiPending")) {
                                                        if (userFunctionalities.contains("28")) {
                                                    %>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="${pageContext.request.contextPath}/MachineCommissioning.do?option=getChassisNumDetails&chassisNo=${pgList.chassisNo}" class="headingSpas" style="color: blue !important;" >
                                                               Verify
                                                            </a>
                                                        </span>
                                                    </td>
                                                    <%}
                                                    } else if (tempParam.equals("verifiapprovalPending")) {
                                                        if (userFunctionalities.contains("29")) {
                                                    %>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            &nbsp; <a href="${pageContext.request.contextPath}/MachineCommissioning.do?option=initApproveMCDetails&type=step2&chassisNo=${pgList.chassisNo}" class="headingSpas" style="color: blue !important;" >
                                                                Approve
                                                            </a>
                                                        </span>
                                                    </td>
                                                    <%}
                                                    }%>

                                                    <%}
                                                                    }
                                                                }%>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr >
                                            <%if (tempParam.equals("travelcardPending")) {%>
                                            <td colspan="5" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                <%} else if (tempParam.equals("chassisPending")) {%>
                                            <td colspan="3" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                <%} else {%>
                                            <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                <%}%>
                                                <pg:index>
                                                    <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                                                    <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                                                    <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                    <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                                                    <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                                                    </pg:index>
                                            </td>
                                        </tr>
                                    </table>
                                </pg:pager>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${empty pgList}">
                        <tr bgcolor="#FFFFFF" height="20">
                            <td nowrap class="message" align="center" width="8%" >
                                No Data Found
                            </td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>
    </center>
</div>




