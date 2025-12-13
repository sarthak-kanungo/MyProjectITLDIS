<%-- 
    Document   : v_comp_master_option
    Created on : May 19, 2014, 9:38:18 AM
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
               
                <h1>COMPLAINT MASTERS </h1>
                <ul>
                    
                     <%if (userFunctionalities.contains("19")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initApplicationMaster'>MANAGE APPLICATION MASTER</a></li>
            <%}%>
            
     <%if (userFunctionalities.contains("11")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initAggregateMaster'>MANAGE AGGREGATE MASTER</a></li>
            <%}%>
            
           
             
            <%if (userFunctionalities.contains("13")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initComplaintCode'>MANAGE DEFECT CODE MASTER</a></li>
            <%}%>
            <%if (userFunctionalities.contains("12")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initCausalCode'>MANAGE CAUSE CODE MASTER</a></li>
            <%}%>
  <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initSubAggregateMaster'>MANAGE SUB-AGGREGATE MASTER</a></li>
           
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initConsequenceMaster'>MANAGE CONSEQUENCE</a></li>

<li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initVendorMaster'>MANAGE VENDOR MASTER</a></li>
              <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initCausalVsConsequence'>CAUSE VS CONSEQUENCE </a></li>

                </ul>
            
           </div>
            
    </body>

