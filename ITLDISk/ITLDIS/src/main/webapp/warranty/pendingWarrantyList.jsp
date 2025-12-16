<%-- 
    Document   : pendingWarrantyList
    Created on : Nov 4, 2014, 10:51:24 AM
    Author     : kundan.rastogi
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
        
        if(document.getElementById("Range").checked==false){
        	document.getElementById("To Date").value="";
        	document.getElementById("From Date").value="";
        	document.getElementById('searchBy').submit();
        	return true;
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
    
    $(document).ready(function() {
        var fromDateValue = "${fromDate}" || new Date().toLocaleDateString('en-GB');  // Ensure this is in the correct format dd/MM/yyyy
        var toDateValue = "${toDate}" || new Date().toLocaleDateString('en-GB');     // Ensure this is in the correct format dd/MM/yyyy
        
        
        document.getElementById('From Date').value = fromDateValue;
        document.getElementById('To Date').value = toDateValue;
        

    
    });


</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.common.warranty" /></a></li>
            <li class="breadcrumb_link"><label><bean:message key="label.warranty.pendingWarrantyClaim" /></label></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1><bean:message key="label.warranty.pendingWarrantyClaim" /></h1>

                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td colspan="11" align= center class="headingSpas">
                            <html:form action="warrantyAction.do?option=viewPendingWarrantyClaim&flag=check" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF" >
                                        <td    class="headingSpas" nowrap align="right">
                                            <bean:message key="label.common.Chassisno" />

                                        </td>
                                        <td   align="left">
                                            <input name="vinNo" type="text" id="Vin No" value="${vinNo}" style="width:100px !important " onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <td    class="headingSpas" nowrap align="right">
                                            <bean:message key="label.common.ClaimNo" />

                                        </td>
                                        <td   align="left">
                                            <input name="warrantyClaimNo" type="text" id="nameSrch" value="${nameSrch}" style="width:100px !important " onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <%if (userFunctionalities.contains("101")) {%>
                                        <%} else {%>
                                        <td align="right" > <bean:message key="label.common.dealerName" />    </td>
                                        <td colspan="4" align="left">
                                            <select id="Dealer Name" name="dealer_code" style="width:300px !important">
                                                <option value="ALL">ALL</option>
                                                <%--<option value='' >--Select Dealer--</option>--%>
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
                                            <input type="text" name="fromDate" class="datepicker" id="From Date" style="width:80px !important " class="txtGeneralNW" value=""  readonly="readonly"/>
                                        </td>
                                        <td    class="headingSpas" nowrap align="right">
                                            To
                                        </td>
                                        <td   align="left">
                                            <input type="text" name="toDate" class="datepicker" id="To Date" style="width:80px !important " class="txtGeneralNW" value=""  readonly="readonly"/>
                                        </td>
                                        <td  align="left" >
                                            <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                            <input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>
                                        </td>
                                    </tr>

                                </table>
                            </html:form>
                        </td>
                    </tr>
                </table>
                <c:if test="${!empty dataList}">
                <div style="overflow: auto">
                    <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>

                        <tr bgcolor="#eeeeee" height="20">
                            <td   align="center" width="6%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.S.No" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap style="padding-left:5px;"><b> <bean:message key="label.common.ClaimNo" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.claimDate" /></b></td>
                            <td   align="left" width="10%"  class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.common.jobcardno" /></b></td>
                            <td   align="left" width="12%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.TMSRefNo" /></b></td>
                            <td   align="left" width="12%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.common.Chassisno" /></b></td>
                            <td   align="left" width="12%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.common.dealerName" /></b></td>
                            <td   align="left" width="12%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.common.location" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.common.status" /></b></td>
                            <%if (userFunctionalities.contains("704")) {%>
                            <td   align="center" width="15%" class="headingSpas" nowrap><b><bean:message key="label.common.action" /></b></td>
                            <%}%>
                        </tr>


                        

                            <pg:pager url="warrantyAction.do" maxIndexPages="2" maxPageItems="2">
                                <pg:param name="option" value="viewPendingWarrantyClaim"/>
                                <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                <pg:param name="warrantyClaimNo"  value='${warrantyForm.warrantyClaimNo}'/>
                                <pg:param name="dealer_code"  value='${warrantyForm.dealer_code}'/>
                                <pg:param name="flag"  value='check'/>
                                <pg:param name="fromDate"  value='<%=request.getParameter("fromDate")%>'/>
                                <pg:param name="toDate"  value='<%=request.getParameter("toDate")%>'/>

                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="dataList" name="dataList">
                                    <pg:item>
                                        <tr id ="${sno}" height="20">
                                            <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                            <td align="left" bgcolor="#FFFFFF" title="  ${dataList.warrantyClaimNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    <a href="warrantyAction.do?option=viewWarrantyClaimDetail&warrntyClaimNo=${dataList.warrantyClaimNo}&flag=Pen">  ${dataList.warrantyClaimNo} </a>
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.claimDate}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.claimDate}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.jobCardNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.jobCardNo}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.tmsRefNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.tmsRefNo}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.vinNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.vinNo}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.dealerName}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.dealerName}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.location}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.location}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.claimStatus}
                                                </span>
                                            </td>
                                            <%if (userFunctionalities.contains("704")) {%>
                                            <td align="center" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <c:if test="${dataList.claimStatus eq 'RECEIVED'}" >
                                                    <a href="warrantyAction.do?option=approveWarranty&warrntyClaimNo=${dataList.warrantyClaimNo}&jobCardNo=${dataList.jobCardNo}&claimDate=${dataList.claimDate}&flag=Approve"  class="headingSpas" style="color: blue;" >
                                                        <img src="${pageContext.request.contextPath}/image/approve2.jpg"  title="Approve Warranty Claim"/>
                                                    </a>
                                                </c:if>
                                                <c:if test="${dataList.claimStatus ne 'RECEIVED'}" >  PENDING
                                                    - -
                                                </c:if>
                                            </td>
                                            <%}%>
                                        </tr>
                                    </pg:item>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                                <tr>
                                    <%--  <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >--%>

                                    <%if (userFunctionalities.contains("704")) {%>
                                    <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                        <%} else {%>

                                    <td colspan="9" align="center" bgcolor="#FFFFFF" class="textPaging" >
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

                            </pg:pager>

                        </c:if>

                        <c:if test="${empty dataList}">
                            <tr bgcolor="#FFFFFF">


                                <%if (userFunctionalities.contains("704")) {%>
                                <td valign="top" align="center" colspan="10" class="headingSpas" style="padding-top:10px;color: red">
                                    <%} else {%>

                                <td valign="top" align="center" colspan="9" class="headingSpas" style="padding-top:10px;color: red">
                                    <%}%>

                                    Warranty Claim Not Found.!
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </div>

            </div>
        </div>
    </center>
</div>

