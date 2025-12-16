<%-- 
    Document   : v_jobcard_master_option
    Created on : May 19, 2014, 9:51:22 AM
    Author     : jasmeen.kaur
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>

   
 <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/css/login.css" type="text/css" />


    
    <%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            //  String imagesURL = (String) session.getAttribute("imagesURL");
            ArrayList userFunctionalities = (ArrayList) session.getAttribute("userFun");

%>

    <body>
        
           <div class="con_slidediv" style="position: relative; ">
               
                <h1>JOB CARD MASTERS </h1>
                <ul>
                 <%if (userFunctionalities.contains("15")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initJobCardStatus'>JOB CARD STATUS</a></li>
            <%}%>
            
            <%if (userFunctionalities.contains("16")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initJobLocation'>JOB LOCATION</a></li>
            <%}%>
            
                <%if (userFunctionalities.contains("5")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initJobType'>MANAGE JOB TYPE  </a></li>
            <%}%>
            
            <%if (userFunctionalities.contains("6")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initServiceType'>MANAGE SERVICE TYPE  </a></li>
            <%}%>
            
                <%if (userFunctionalities.contains("17")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initNextServiceFrom'>NEXT SERVICE FROM</a></li>
            <%}%>
            
            
             <%if (userFunctionalities.contains("18")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initOwnerDriven'>OWNER DRIVEN</a></li>
            <%}%>
                </ul>
            
           </div>
            
    </body>

