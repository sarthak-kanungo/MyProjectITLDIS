<%-- 
    Document   : manage_inventoryOptions
    Created on : Aug 20, 2014, 6:15:54 PM
    Author     : sreeja.vijayakumaran
--%>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/css/login.css" type="text/css" />
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();

%>
<!doctype html>
<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >

        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>

        <script>
            function validateExcel()
            {
                if(document.getElementById("Order Type").value==""){
                    alert(not_blank_validation_msg+' Order Type');
                    document.getElementById("Order Type").focus();
                    return false;
                }

                if(document.getElementById("PreviousPO").checked ){
            <%-- <c:if test="${fn:length(prevPoList)>0}" >
                         if(document.getElementById("Previous Po No").value==""){
                             alert(not_blank_dropdown_validation_msg+'Previous Po No');
                             document.getElementById("Previous Po No").focus();
                             return false;
                         }
             </c:if>--%>
            <%-- <c:if test="${fn:length(prevPoList)==0}" >
                         alert("Previous Po No is Not available ! Please create New Order. ");
                         return false;
             </c:if>--%>

                         if(document.getElementById("prevPoList").value==""){
                             alert("Previous Po No is Not available ! Please create New Order. ");
                             return false;
                         }
                         if(document.getElementById("Previous Po No").value==""){
                             alert(not_blank_dropdown_validation_msg+'Previous Po No');
                             document.getElementById("Previous Po No").focus();
                             return false;
                         }
                     }



                     if(document.getElementById("Order Type").value=="STD"){
                         document.form1.action="<%=cntxpath%>/inventoryEXPAction.do?option=createNewEXPOrder&odType="+Base64.encode('STD');
                     }else{
                         document.form1.action="<%=cntxpath%>/inventoryEXPAction.do?option=createNewEXPOrder&odType="+Base64.encode('VOR');
                     }
                     document.form1.submit();
                 }
                 function getOrderType(orderType)
                 {
                     if(orderType!=""){
                         var strURL="<%=cntxpath%>/inventoryEXPAction.do?option=getPoListByodType&odType=" + orderType;
                         xmlHttp = GetXmlHttpObject();
                         xmlHttp.onreadystatechange = function()
                         {
                             if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                             {
                                 res = xmlHttp.responseText;
                                 document.getElementById('divId').innerHTML= res;
                                 //document.getElementById('divId').style.display='block';
                             }
                         };
                         xmlHttp.open("GET", strURL, true);
                         xmlHttp.send(null);
                         return false;
            
                     }
                 }

                 function selectPreviousRadio(){
                     document.getElementById("PreviousPO").checked ='true';
                 }
                
        </script>
        <c:out value="${prevPoList}" />
    <body>
        <div class="contentmain1">
            <div class="breadcrumb_container">
                <ul class="hText">
                    <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
                    <li><bean:message key="label.spare.initCreateOrderEXP" />  </li>
                </ul>
            </div>
            <div class="message" id="msg_saveFAILED"></div>
            <center>
                <div class="content_right1">
                    <div class="con_slidediv1" style="position: relative;width: 100%">
                        <h1 class="hText">
                            <bean:message key="label.spare.initCreateOrderEXP" />

                        </h1>
                        <form name="form1" id="form1" method="post"  >
                            <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryEXPAction.do?option=initInvEXPOptions'><bean:message key='label.common.spares'/></a>@@<bean:message key="label.spare.initCreateOrderEXP" />"/>
                            <table width="100%" border="0" cellspacing="2" cellpadding="2" align="left" class="LiteBorder">
                                <tr>
                                    <td  width="40%" align="right" class="headingSpas" nowrap style="padding-right:10px;padding-right:100px" >
                                        <bean:message key="label.spare.orderType" /> :
                                    </td>
                                    <td width="40%" align="left" >
                                        <select id="Order Type" name="orderType" class="headingSpas" style="width:200px !important; margin:0px!important"  onchange="getOrderType(this.value);" >   <%--onchange="getOrderDes(this.value)"--%>
                                            <option value="" >--Select Here--</option>
                                            <option value='STD' title='STD'>STD</option>
                                            <option value='VOR' title='VOR'>VOR</option>
                                        </select>
                                    </td>
                                    <%--<td colspan="2">&nbsp;</td>--%>
                                </tr>
                                <tr  bgcolor=#ffffff ><td colspan="2" class=text align=center><span id="divId" ></span></td></tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <input type="button" value="Submit" style="float:none; margin: 5px 5px 5px 5px" onclick="validateExcel()"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </center>
        </div>




