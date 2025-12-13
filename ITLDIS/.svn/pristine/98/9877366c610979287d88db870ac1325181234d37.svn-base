<%-- 
    Document   : claimReport
    Created on : Jun 17, 2016, 2:27:20 PM
    Author     : Ashutosh.Kumar1
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

    function submitForm() {

        // var searchvalue =document.getElementById('nameSrch').value;
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

        //alert(dateF)
        //alert(dateT)
        if(dateF>dateT){
            alert(date_validation_msg)
            document.getElementById('From Date').focus();
            return false;
        }

        document.getElementById('searchBy').submit();
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
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.common.warranty" /></a></li>
            <li class="breadcrumb_link"><label><bean:message key="label.common.claimReport" /></label></li>
        </ul>
    </div>


    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1><bean:message key="label.common.claimReport" /></h1>

                <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>

                    <tr height=50 bgcolor="#FFFFFF">
                        <td colspan="11" align= center class="headingSpas">
                            <html:form action="warrantyAction.do?option=claimReoprt&flag=check" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF" >

                                        <%--<td    class="headingSpas" nowrap align="right">
                                            <bean:message key="label.common.Chassisno" />

                                        </td>
                                        <td   align="left">
                                            <input name="vinNo" type="text" id="Vin No" value="${nameSrch}" style="width:100px !important " onblur="this.value=TrimAll(this.value)"/>
                                        </td>--%>
                                        <td    class="headingSpas" nowrap align="right">
                                            <bean:message key="label.common.ClaimNo" />

                                        </td>
                                        <td   align="left">
                                            <input name="warrantyClaimNo" type="text" id="nameSrch" value="${nameSrch}" style="width:100px !important " onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <td    class="headingSpas" nowrap align="right">
                                            <bean:message key="label.warranty.claimStatus" />

                                        </td>
                                        <td   align="left">

                                            <select name="claimStatus" id="Claim Status" class="selectnewbox" class="txtGeneralNW" style="width:110px !important ">
                                                <option value="">--Select--</option>
                                                <option value="PENDING">PENDING</option>
                                                <option value="PACKED">PACKED</option>
                                                <option value="RECEIVED">RECEIVED</option>
                                                <option value="DISPATCHED">DISPATCHED</option>
                                                <option value="REJECTED">REJECTED </option>
                                                <option value="APPROVED">APPROVED </option>
                                                <option value="PARTIAL APPROVED">PARTIAL APPROVED</option>
                                            </select>

                                        </td>
                                        <td class="headingSpas" nowrap align="right">
                                            <c:if test="${range eq '1'}">
                                                <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                            </c:if>
                                            <c:if test="${range ne '1' }">
                                                <input type="checkbox" name="range" value="1"  id="Range" >
                                            </c:if>
                                            <bean:message key="label.warranty.claimDate" />
                                        </td>
                                        <td align="left">
                                            <input type="text" name="fromDate" class="datepicker" id="From Date" style="width:80px !important " class="txtGeneralNW" value="${fromDate}"  readonly="readonly"/>
                                        </td>
                                        <td    class="headingSpas" nowrap align="right">
                                            To
                                        </td>
                                        <td   align="left">
                                            <input type="text" name="toDate" class="datepicker" id="To Date" style="width:80px !important " class="txtGeneralNW" value="${toDate}"  readonly="readonly"/>
                                        </td>


                                    </tr>
                                     <tr bgcolor="#FFFFFF" >
                                        <td  style="padding-left:5px;width:12%" nowrap align="right" class="headingSpas"><bean:message key="label.common.status"/></td>
                                        <td align="left" >
                                            <select name="packingStatus" id="Packing Status" class="selectnewbox" class="txtGeneralNW">
                                                <option value="">All</option>
                                                <option value="PACKED">PACKED </option>
                                                <option value="DISPATCHED">DISPATCHED</option>
                                                <option value="RECEIVED">RECEIVED</option>
                                            </select>
                                        </td>
                                        <%if (userFunctionalities.contains("101")) {%>
                                        <%} else {%>
                                        <td width="15%" colspan="2">
                                            <select id="Dealer Name" name="dealer_code" style="width:300px !important">
                                                <option value="ALL" >ALL</option>
                                                <%-- <option value='' >--Select Dealer--</option>--%>
                                                <c:forEach items="${dealerList}"  var="labelValue">
                                                    <c:if test="${warrantyForm.dealer_code eq labelValue[0]}">
                                                        <option value="${labelValue[0]}" title="${labelValue[0]} [${labelValue[1]}] [${labelValue[2]}]" selected>${labelValue[0]} [${labelValue[1]}] [${labelValue[2]}]</option>
                                                    </c:if>
                                                    <c:if test="${warrantyForm.dealer_code ne labelValue[0]}">
                                                        <option value="${labelValue[0]}" title="${labelValue[0]} [${labelValue[1]}] [${labelValue[2]}]">${labelValue[0]} [${labelValue[1]}] [${labelValue[2]}]</option>
                                                    </c:if>

                                                </c:forEach>
                                            </select>
                                        </td>
                                        <%}%>

                                         

                                        <td colspan="2" align="center" >
                                            <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                            <input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>
                                        </td>
                                        <td colspan="2">&nbsp;</td>
                                       
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

<script>
    window.onload =function(){
        var status = '${warrantyForm.claimStatus}';
        if(status != ""){
            document.getElementById("Claim Status").value=status;
        }
    }
</script>