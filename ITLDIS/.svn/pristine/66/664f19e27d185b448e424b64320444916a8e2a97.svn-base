<%-- 
    Document   : viewBajajExtendedWarrantyInvList
    Created on : July 11, 2018, 12:41:46 PM
    Author     : avinash.pandey
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<script>

    function search(){

        if(document.getElementById("Range").checked==true){
            if(document.getElementById("From Date").value==""){
                alert("Please Select From Date .");
                document.getElementById("From Date").focus();
                return false;
            }
            if(document.getElementById("To Date").value==""){
                alert("Please Select To Date .");
                document.getElementById("To Date").focus();
                return false;
            }
        }
        var  fDate=document.getElementById("From Date").value;
        fDate = trim(fDate);
        var x =fDate;     //'1-1-2015';
        var a = x.split('/');
        var dateF = new Date (a[2], a[1] - 1,a[0]);

        var  tDate=document.getElementById("To Date").value;
        tDate = trim(tDate);
        var y =tDate;     //'17-1-2015';
        var b = y.split('/');
        var dateT = new Date (b[2], b[1] - 1,b[0]);
        if(dateF>dateT){
            alert(date_validation_msg)
            document.getElementById('From Date').focus();
            return false;
        }
        document.getElementById('etype').value="";
        document.getElementById("searchform").submit();
    }
function Export(){
	
	 if(document.getElementById("Range").checked==false){
		 alert("Please Select Date Range");
		 return false;
	 }

        if(document.getElementById("Range").checked==true){
            if(document.getElementById("From Date").value==""){
                alert("Please Select From Date.");
                document.getElementById("From Date").focus();
                return false;
            }
            if(document.getElementById("To Date").value==""){
                alert("Please Select To Date.");
                document.getElementById("To Date").focus();
                return false;
            }
        }
        var  fDate=document.getElementById("From Date").value;
        fDate = trim(fDate);
        var x =fDate;     //'1-1-2015';
        var a = x.split('/');
        var dateF = new Date (a[2], a[1] - 1,a[0]);

        var  tDate=document.getElementById("To Date").value;
        tDate = trim(tDate);
        var y =tDate;     //'17-1-2015';
        var b = y.split('/');
        var dateT = new Date (b[2], b[1] - 1,b[0]);

        if(dateF>dateT){
            alert(date_validation_msg)
            document.getElementById('From Date').focus();
            return false;
        }
        document.getElementById('etype').value="export";
        document.getElementById('searchform').submit();
    }
    function donotsubmit()
    {
        return false;
    }
    function disableDate(dis)
    {
        if(dis.checked){
            document.getElementById("From Date").disabled=false;
            document.getElementById("To Date").disabled=false;
        }else{
            document.getElementById("From Date").value="";
            document.getElementById("From Date").disabled=true;
            document.getElementById("To Date").value="";
            document.getElementById("To Date").disabled=true;
        }
    }
    
    $(document).ready(function(){
    $('.viewJobCard').click(function(){
        var url = "serviceProcess.do?option=viewJobcard&vinNo="+Base64.encode($(this).attr('data-vinNo').trim())+"&jobCardNo="+Base64.encode($(this).text().trim())+"&flag="+Base64.encode("Ginvoice")+"&createInvoice="+Base64.encode("false");
        $(this).attr('href',url);
    });

});
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><html:link action="warrantyAction.do?option=initItlExtWarranty"><bean:message key="label.warranty.ItlExtendedWarranty" /></html:link></li>
            <li class="breadcrumb_link"><label><bean:message key="label.common.viewItlExtWarrantyClaimStatus" /></label></li>
        </ul>
    </div>


    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1><bean:message key="label.common.viewItlExtWarrantyClaimStatus" /></h1>

                <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>

                    <tr height=50 bgcolor="#FFFFFF">
                        <td colspan="15" align= center class="headingSpas">
                            <html:form action="warrantyAction.do?option=exportWarrantyClaimStatusReport&flag=check" method="post" styleId="searchform" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                
                                <tr bgcolor="#FFFFFF">
                                    <td align="right" style="padding-left: 5px" width="10%">
                                        <bean:message key="label.common.Chassisno"/></td>
                                    <td align="left">
                                        <input name="chassisNo" type="text" id="Chassis No" value="${serviceForm.chassisNo}" style="width:165px !important;" onblur="this.value=TrimAll(this.value)"/>
                                    </td>
                                    
                                    <% if (!userFunctionalities.contains("101")) { %>
                                    <td align="right" style="padding-left: 5px" width="10%">
                                        <bean:message key="label.common.dealerName"/></td>
                                   <td align="left">
                                        <select id="Dealer Name" name="dealer_code" style="width:200px !important">
                                            <option value="All" >All</option>
                                            <c:forEach items="${dealerList}"  var="labelValue">
                                                <c:if test="${serviceForm.dealercode eq labelValue[0]}">
                                                    <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                </c:if>
                                                <c:if test="${serviceForm.dealercode ne labelValue[0]}">
                                                    <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <% } %>
                                    
                                    
                                    <td class="headingSpas" nowrap align="right">&nbsp;
                                        <c:if test="${range eq '1'}">
                                            <input type="checkbox" name="range" value="1" id="Range" checked onclick="disableDate(this)">
                                        </c:if>
                                        <c:if test="${range ne '1' }">
                                            <input type="checkbox" name="range" value="1" id="Range" >
                                        </c:if>
                                        <bean:message key="label.common.dateSaleOfCertificate" /></td>
                                    <td align="left">
                                        <input name="fromDate" type="text" class="datepicker" id="From Date" value="${fromdate}" onkeydown="return false;"/>
                                    </td>
                                    <td class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/>&nbsp;
                                        <input name="toDate" type="text" class="datepicker" id="To Date" value="${todate}" onkeydown="return false;"/>
                                    </td>
                                    
                                    <td    class="headingSpas" nowrap align="right">
                                        <bean:message key="label.common.status" />

                                    </td>
                                    <td align="left" bgcolor="#FFFFFF" class="text">
                                        <select id="Status" name="status" class="headingSpas" style="width:150px !important;" >
                                            <option value="All" >All</option>
                                            <c:forEach items="${extWarrStatus}"  var="extList">
                                                <c:if test="${serviceForm.status eq extList}">
                                                    <option value='${extList}' title='${extList}' selected>${extList}</option>
                                                </c:if>
                                                <c:if test="${serviceForm.status ne extList}">
                                                    <option value='${extList}' title='${extList}'>${extList}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    
                                 </tr>
                                <tr height=20 bgcolor="#FFFFFF">
                                    <td colspan="9" align="right" >
                                        <input type="button" value="Export" onclick="Export()"/>
                                        <input type="hidden" name="etype" id="etype" />
                                    </td>
                                </tr>
                                
                                </table>
                            </html:form>
                        </td>
                    </tr>
                </table>
              
            </div>
        </div>
    </center>
</div>
