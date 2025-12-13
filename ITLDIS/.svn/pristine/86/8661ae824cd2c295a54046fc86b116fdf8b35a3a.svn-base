<%-- 
    Document   : printHeader
    Created on : Nov 20, 2014, 11:15:58 AM
    Author     : megha.pandya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8"  />
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<%
            String heading = request.getAttribute("heading")==null?"":request.getAttribute("heading").toString();
            String headingFlag =request.getAttribute("headingFlag")==null?"":request.getAttribute("headingFlag").toString();
            
%>
<c:set var="heading" value='<%=heading%>'/>
<div class="mainwrap" style="background-color: #ffffff">
    <div class="header_main_print">
        <div class="header_center">
             <div class="logo">
            <a href="#"><img src="layout/images/logo.png" alt="" class="fl"></a>
        </div>
            <div style="margin:0 auto; ">
          <%if(headingFlag.equals("")){
            //serviceForm sf=(String)request.getAttribute("dealerCode");
            LoginDAO dao = new LoginDAO();
            serviceForm sf= dao.getDealerAddress((String)request.getAttribute("dealerCode"));
            %>
          <h1><%=sf.getDealerName()%></h1>
         <%-- <p></p>--%>
          <p><%=sf.getPaymentMode()%><br><%=sf.getPayeeDistrict()+" Ph. "+sf.getPayeeMobilePhone()+" Tin No. "+sf.getPayeePinCode()+" GST No. "+sf.getGstCode()%></p>
          <%} else {%>
          <h1>INTERNATIONAL TRACTORS LIMITED</h1>
          <p>  Vill. Chak Gujran, P.O. Piplanwala, Jalandhar Road, Hoshiarpur.</p>
          <%}%>
          <p style="font-size: large;padding: 5px;text-align: center"><b>${heading}</b></p>
            </div>
        </div>
        </div>
    </div>



