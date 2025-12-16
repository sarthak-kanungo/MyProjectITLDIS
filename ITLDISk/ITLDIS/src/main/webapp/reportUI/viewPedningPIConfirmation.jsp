<%-- 
    Document   : viewPedningPIConfirmation
    Created on : 02-Aug-2016, 04:19:33
    Author     : avinash.Pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%@ page import="java.util.*,java.io.File,viewEcat.comEcat.PageTemplate" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String dealerFlag = "";
            if (!userFunctionalities.contains("101")) {
                dealerFlag = "true";
            }
            String mainURL = (String) session.getValue("mainURL");
            String userCode = (String) session.getValue("userCode");
            String message = (String) request.getAttribute("meaasge");
            String flag = (String) request.getAttribute("flag");
            String saveLocation = mainURL + "dealer/excels/" + userCode + "/";
            File source_file = new File(saveLocation + "PendingPIAtBuyerReportExcel.xls");
            source_file.delete();
            String fileDownloadPath =  saveLocation + "PendingPIAtBuyerReportExcel.xls";
%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

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

    function Export(val)
    {
        var dealerFlag='<%=dealerFlag%>';
        if(dealerFlag=='true')
        {
            var dealer=document.getElementById('Dealer Name').value;
            if(dealer=='')
            {
                alert(not_blank_dropdown_validation_msg+'Dealer Name');
                return false;
            }
        }
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

        var fDate=document.getElementById("From Date").value;
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
        else if((dateT-dateF)/(1000*60*60*24)>183){
            alert("Date range should not more than 6 months.");
            return false;
        }

        document.getElementById('eType').value=val;
        document.getElementById('searchBy').submit();
               
    }


</script>


<div class="contentmain1">
    <head><title>ITLDIS</title></head>
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/reportAction.do?option=options'><bean:message key="label.report.report" /></a></li>
            <li><bean:message key="label.report.PendingPiConfirmation" /></li>
        </ul>
    </div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.report.PendingPiConfirmation" /></h1>
                <div style="overflow: auto">
                    <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                        <tr height=50 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <html:form action="reportAction.do?option=createPedningPIConfirmationReport" method="post"  styleId="searchBy" onsubmit="donotsubmit();">

                                    <table width=80% align="left" border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                       <%-- <tr bgcolor="#FFFFFF" >
                                            <td class="headingSpas" nowrap align="right" width="20%"> <bean:message key="label.common.InvoiceNo" /></td>
                                            <td align="left" width="80%"><input name="invNo" type="text" id="InvNo"  value="${reportForm.invNo}" style="width:140px !important " onblur="this.value=TrimAll(this.value)"/></td>
                                        </tr>--%>
                                        <tr bgcolor="#FFFFFF" >
                                            <td class="headingSpas" nowrap align="right" width="20%"> <bean:message key="label.spare.piNo" /></td>
                                            <td align="left" width="80%"><input name="piNo" type="text" id="piNo" value="${reportForm.piNo}" style="width:100px !important " onblur="this.value=TrimAll(this.value)"/></td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF" >
                                            <td class="headingSpas" nowrap align="right" width="20%"> <bean:message key="label.common.partno_small" /></td>
                                            <td align="left" width="80%"><input name="partnum" type="text" id="PartNo" value="${reportForm.partnum}" style="width:100px !important " onblur="this.value=TrimAll(this.value)"/></td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF" >
                                            <td class="headingSpas" nowrap align="right" width="20%">
                                                <c:if test="${range eq '1'}">
                                                    <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                                </c:if>
                                                <c:if test="${range ne '1' }">
                                                    <input type="checkbox" name="range" value="1"  id="Range" >
                                                </c:if>
                                                <bean:message key="label.spare.piDate" />

                                            </td>

                                            <td class="headingSpas" nowrap align="left" width="80%"  >
                                                <input type="text" align="left" name="fromdate" class="datepicker" id="From Date" style="width:70px !important " class="txtGeneralNW" value="${reportForm.fromdate}"  readonly="readonly"/>
                                                &nbsp;&nbsp;&nbsp;&nbsp;To<input type="text" name="todate" class="datepicker" id="To Date" style="width:70px !important " class="txtGeneralNW" value="${reportForm.todate}"  readonly="readonly"/>
                                            </td>

                                        </tr>

                                        <tr bgcolor="#FFFFFF">
                                            <td align="right" style="white-space: nowrap;">
                                                <bean:message key="label.common.Country" /></td>
                                            <td  align="left">
                                                <select id="Country" name="country"  >
                                                    <%if (!userFunctionalities.contains("101")) {%>
                                                    <option value="">--Select--</option>
                                                    <%}%>
                                                    <c:forEach items="${countryList}"  var="labelValue">
                                                        <c:if test="${labelValue[0] ne '-'}">
                                                            <c:if test="${reportForm.country eq labelValue[0]}">
                                                                <option selected value="${labelValue[0]}@@${labelValue[1]}@@" title="${labelValue[1]}  [${labelValue[0]}]">${labelValue[1]} [${labelValue[0]}]</option>
                                                            </c:if>
                                                            <c:if test="${reportForm.country ne labelValue[0]}">
                                                                <option value="${labelValue[0]}@@${labelValue[1]}@@" title="${labelValue[1]}  [${labelValue[0]}]">${labelValue[1]} [${labelValue[0]}]</option>
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>

                                        <tr bgcolor="#FFFFFF">

                                            <td align="right" style="padding-top: 5px; white-space: nowrap;">
                                                <bean:message key="label.common.dealerName" />
                                            </td>
                                            <td  align="left" style="padding-top: 5px;">
                                                <select id="Dealer Name" name="dealerCode" style="width:300px !important" >
                                                    <option value="ALL">ALL</option>
                                                    <c:forEach items="${dealerList}"  var="labelValue">
                                                        <c:if test="${reportForm.dealerCode eq labelValue[0]}">
                                                            <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                        </c:if>
                                                        <c:if test="${reportForm.dealerCode ne labelValue[0]}">
                                                            <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </td>

                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td  align="left" colspan="2" style="padding-left: 213px;padding-top: 10px;">
                                                <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                                <input type="button" value="Export" onclick="Export(this.value)"/>
                                                <input type="hidden" name="eType" id="eType" />
                                            </td>

                                        </tr>
                                    </table>
                                </html:form>
                            </td>
                        </tr>



                        <%if (flag != null) {
                                        if (flag.equals("flag")) {%>
                        <tr height=50 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <table width="95%" border="0" cellspacing="2" cellpadding="2" bgcolor="#ffffff">
                                    <tr>
                                        <td class="red" width="50%" align="center">
                                            <%if (message.equals("SUCCESS")) {%>
                                            <b><bean:message key="label.report.PendingPiConfirmation" /> Report Exported in Excel Successfully.</b><br>
                                            <br>
                                            <a href=<%=fileDownloadPath%> style="color:red;"><b>Click here to Download</b></a>
                                            <%}%>

                                            <%if (message.equals("FAILURE")) {%>
                                            <bean:message key="label.report.PendingPiConfirmation" /> Report can not be Exported. Please Contact Administrator.<br>
                                            <br>

                                            <%}%>
                                        </td>
                                        
                                    </tr>
                                    <tr>
                                        <td class="red" align="center" style="padding-left: 5px;padding-top: 10px;">
                                            <div align="center" >
                                                <input name="input" type="button" onclick="window.history.back()" value="back">
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <% }
                                    }%>



                    </table>
                </div>


            </div>
        </div>

    </center>
</div>

