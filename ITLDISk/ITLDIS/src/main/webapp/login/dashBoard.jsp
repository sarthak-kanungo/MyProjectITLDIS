<%-- 
    Document   : home screen
    Created on : May 21, 2014, 10:26:37 AM
    Author     : Jasmeen.Kaur
--%>
<%@ page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8"  />

<%
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");

%>

<c:set var="contextPath" value='<%=request.getContextPath()%>'/>
<c:set var="dashboardValues" value="${fn:split(dashboardvalues,'@@')}"/>
<link rel="stylesheet" href="${contextPath}/css/login.css" type="text/css" />


<div class="contentmain1">
    <div class="dashboard hText" style="position: relative;">

        <div class="dashboardcs">

            <%if (userFunctionalities.contains("610")){%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href="${contextPath}/pdiServiceProcess.do?option=init_viewallPendingChassisdetails&jobType=PDI"><p> <bean:message key="label.common.pendingforpdi" /></p>
                    </a>
                </div>
            </div>
                <%}%>
            <%if (userFunctionalities.contains("605"))
             {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href="${contextPath}/pdiServiceProcess.do?option=init_viewPDIDetails&jobType=PDI"><p> <bean:message key="label.common.viewpdidetail" /></p>
                        </a>
                </div>
            </div>
             <%}%>
            <%if (userFunctionalities.contains("611"))
             {%>

            <div class="dashbox">
                <div class="dashimg3">
                    <a href="${contextPath}/installProcess.do?option=initViewAllInstallList"><p> <bean:message key="label.common.pendinginstallation" /> </p>
                        </a>
                </div>
            </div>
 <%}%>
            <%if (userFunctionalities.contains("607"))
             {%>
            <div class="dashbox">
             <div class="dashimg4">
                <a href="${contextPath}/installProcess.do?option=init_viewDetails"><p> <bean:message key="label.common.viewinstallation" /></p>
                 </a>
                </div>
            </div>
 <%}%>
            <%if (userFunctionalities.contains("608"))
             {%>
            <div class="dashbox">

                <div class="dashimg5">
                    <a href="${contextPath}/serviceCreateJC.do?option=initVehicleInformation&jobType=PDI&pathNm=fillJC"><p><bean:message key="label.common.createjobcard" /></p>
                   <%-- <a href="${contextPath}/serviceOptProcess.do?option=initJobCardForPDI&jobType=PDI&pathNm=fillJC"><p><bean:message key="label.common.jobcardforpdi" /></p>--%>
                        </a>
                </div>
            </div>
            <%}%>

            <%if (userFunctionalities.contains("609"))
             {%>
            <div class="dashbox">
                <div class="dashimg6">
                    <a href="${contextPath}/serviceProcess.do?option=init_viewallJobcarddetails&jobType=ViewJC"><p>  <bean:message key="label.common.viewalljobcard" /> </p>
                       </a>
                </div>
            </div>         

            <%}%>
            <%if (userFunctionalities.contains("616")){%>
            <div class="dashbox">
                <div class="dashimg3">
                    <a href="${contextPath}/serviceProcess.do?option=initCloseJobCards&pathNm=fillJC"><p> <bean:message key="label.common.closejobcard" />  </p>
                        </a>
                </div>
            </div>
           <%}%>
           <%-- <%if (userFunctionalities.contains("612")){%>
            <div class="dashbox">
                <div class="dashimg5">
                    <a href="${contextPath}/serviceProcess.do?option=initPendingDE4Approval"><p> <bean:message key="label.common.pending4DEapproval" /></p>
                    </a>
                </div>
            </div>
            <%}%>--%>
            <%if (userFunctionalities.contains("615")){%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href="${contextPath}/serviceProcess.do?option=initPending4validateVinNo"><p> <bean:message key="label.common.pending4chassisvalidate" /></p>
                    </a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("617")){%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href="${contextPath}/serviceProcess.do?option=initReOpenJobCards"><p> <bean:message key="label.common.reOpenJobCard" /></p>
                    </a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("618")) {%>
            <div class="dashbox">
                <div class="dashimg6">
                    <a href="${contextPath}/serviceProcess.do?option=initCreateInvoiceList"><bean:message key="label.common.generateinvoice" /></a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("620")){%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href="${contextPath}/serviceProcess.do?option=initSearchHistory"><p> <bean:message key="label.common.tractorhistory" /></p>
                    </a>
                </div>
            </div>
            <%}%>

        <%if (userFunctionalities.contains("621")){%>
            <div class="dashbox">
                <div class="dashimg5">
                    <a href="${contextPath}/serviceProcess.do?option=initsercviceReminder"><p><bean:message key="label.common.serviceReminder" /></p>
                    </a>
                </div>
            </div>
            <%}%>


            <%if (userFunctionalities.contains("622")){%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href="${contextPath}/serviceProcess.do?option=initServiceDoneLapse"><p> <bean:message key="label.common.serDoneLapseReprt" /> </p>
                    </a>
                </div>
            </div>
            <%}%>

        </div>
    </div>
</div>
