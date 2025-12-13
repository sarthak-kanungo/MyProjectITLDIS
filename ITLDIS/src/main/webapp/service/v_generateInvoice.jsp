<%-- 
    Document   : v_generateInvoice
    Created on : Oct 10, 2014, 5:04:21 PM
    Author     : megha.pandya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var noOfDays = '${noOfDays}';
    var ttdate="";
    var tmon="<%=month%>";
    var tdays="<%=day%>";

    if(eval(tmon)<10)
        tmon="0"+tmon;

    if(eval(tdays)<10)
        tdays="0"+tdays;

    ttdate=<%=year%>+'/'+tmon+'/'+tdays;

    var today = new Date(ttdate);
    var backDate= new Date(ttdate);
    backDate.setDate(backDate.getDate()-noOfDays);
    function validate(){
        var invoicedate=document.getElementById("Invoice Date").value.split('/');
        var sdate=invoicedate[2]+'/'+invoicedate[1]+'/'+invoicedate[0];
        var indt=new Date(sdate).getTime();
        if(today < indt || backDate > indt){
            document.getElementById("Invoice Date").focus();
            alert(invoice_currentDate_validation_msg+noOfDays+dat_validation_msg2);
            return false;

        }
        if (document.getElementById("GST No").value !='' && !isProperSerialCust(document.getElementById("GST No").value)){
            alert("Only Special characters (-, /, _) are allowed in GST No field");
            document.getElementById("GST No").value = '';
            document.getElementById("GST No").focus();
            return false;
        }
        var partNo=document.getElementsByName("partNo");
        var hsnCode=document.getElementsByName("hsnCode");
        for(var i=0;i<hsnCode.length;i++){
            if(hsnCode[i].value==""){
                alert("HSN code not avalable for part no: "+partNo[i].value);
                return false;
            }
        }
        document.getElementById("generateInvoice").submit();
        document.getElementById("genInv").disabled=true;

    }
    function settodayDate(){
        //alert('here'+tdays+'/'+tmon+'/'+<%=year%>)
        document.getElementById("Invoice Date").value=tdays+'/'+tmon+'/'+<%=year%>;
    }
    function getCreditAmm(creditVal){

        if(creditVal==""){
            creditVal=0;
        }
        if(!creditVal.match(/^[0-9.]+$/)){
            alert("Credit Value "+numeric_msg);
            return false;
        }
        if( eval(creditVal) > eval(document.getElementById("totalEstimate").value))
        {
            document.getElementById("CreditValue").value="0";
            alert("Credit Amount "+gt_than_msg+" Total Amount");
            return false;
        }
    }
</script><body onload="settodayDate()">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=initCreateInvoiceList"><bean:message key="label.common.generateinvoice" /></a></li>
            <li class="breadcrumb_link">${jobcardDetails.jobCardNo}</li>
        </ul>
    </div>

    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.generateinvoice" /></h1>
                <form action="<%=cntxpath%>/serviceProcess.do?option=generateInvoice" method="post" id="generateInvoice" name="generateInvoice">
                    <input type="hidden" name="upperLink" value="<a href ='<%=cntxpath%>/initService.do'><bean:message key="label.common.Service" /></a><a href ='<%=cntxpath%>/serviceProcess.do?option=initCreateInvoiceList'><bean:message key="label.common.generateinvoice" /></a>${jobcardDetails.jobCardNo}"/>
                    <table width="100%" border="0" cellspacing="2" cellpadding="4">
                        <%--<tr bgcolor="#eeeeee" height="20" >
                            <td align="left" colspan="6"><label><B><bean:message key="label.common.jobCardSummary" /></B></label></td>
                        </tr>--%>
                        <tr>
                            <td align="right"><label ><B><bean:message key="label.common.dealerName" />: </B></label></td>
                            <td align="left"><span>${jobcardDetails.dealerName}[ ${jobcardDetails.dealercode} ]</span>
                                <input type="hidden" name="dealercode" value="${jobcardDetails.dealercode}" />
                            </td>
                            <td align="right"><label ><B><bean:message key="label.common.jobcardno" />: </B></label></td>
                            <td align="left"><span>${jobcardDetails.jobCardNo}</span>
                                <input type="hidden" name="jobCardNo" value="${jobcardDetails.jobCardNo}" />
                                <input type="hidden" name="vinNo" value="${jobcardDetails.vinNo}" />
                                <input type="hidden" name="jobType" value="${jobcardDetails.jobType}" />
                            </td>
                            <td align="right"><label ><B><bean:message key="label.common.tractorindate" />: </B></label></td>
                            <td align="left"><span>${jobcardDetails.jobCardDate}</span>
                                <input type="hidden" name="jobCardDate" value="${jobcardDetails.jobCardDate}" />
                            </td>
                        </tr>
                        <tr>
                            <td align="right"><label ><B><bean:message key="label.common.contactname" />: </B></label></td>
                            <td align="left"><span>${jobcardDetails.payeeName}</span>
                                <input type="hidden" name="payeeName" value="${jobcardDetails.payeeName}" />
                            </td>
                            <td align="right"><label ><B><bean:message key="label.common.contactno" />: </B></label></td>
                            <td align="left"><span>${jobcardDetails.payeeMobilePhone}</span>
                                <input type="hidden" name="payeeMobilePhone" value="${jobcardDetails.payeeMobilePhone}" />
                            </td>
                            <td align="right"><span><label ><B><bean:message key="label.common.InvoiceDate" />: </B></label></span></td>
                            <td align="left" style="white-space: nowrap">
                                <input name="invoicedate" type="text" class="datepicker" id="Invoice Date" size="15" value=""  onkeydown="return false;"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" style="color:red"><label ><B><bean:message key="label.spare.amountDue" /> : </B></label></td>
                            <td align="left" style="color:red">
                                ${serviceForm.paymentDue}
                            </td>
                            <td align="right" ><label ><B><bean:message key="label.common.gstNo" /> : </B></label></td>
                            <td align="left" style="white-space: nowrap">
                                <html:text property="gstNo" styleId="GST No" maxlength="49" value="${serviceForm.gstNo}"  onblur="this.value=TrimAll(this.value)" />
                            </td>
                        </tr>
                        <%--<tr>
                            <td align="right"><span><label ><B><bean:message key="label.common.InvoiceNo" />: </B></label></span></td>
                            <td align="left"><span>${jobcardDetails.invoiceno}</span>
                                <input type="hidden" name="invoiceno" value="${jobcardDetails.invoiceno}" />
                            </td>
                        </tr>--%>
                        <%--<c:if test="${jobcardDetails.totalEstimate ne '0.0'}">--%>
                        <c:if test="${!empty jobcardDetails.partNo}">
                            <tr bgcolor="#eeeeee" height="20" >
                                <td align="left" colspan="6"><label><B><bean:message key="label.warranty.jobCardDetails" /></B></label></td>
                            </tr>

                            <tr>
                                <td align="left" colspan="6"><B><bean:message key="label.common.label4viewpart" /></B></td>
                            </tr>
                            <tr>
                                <td align="left" colspan="6"><table  border="0" cellspacing="3" cellpadding="3" width="100%">
                                        <tr class="grid ">
                                            <th  align="center" class="tdgridnew1"><label><bean:message key="label.common.sno" /></label></th>
                                            <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.partno" /></label></th>
                                            <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.partdesc" /></label></th>
                                            <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.hsnCode" /></label></th>
                                            <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.category" /></label></th>
                                            <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.qty" /></label></th>
                                            <th  align="center" class="tdgridnew1" ><label><bean:message key="label.common.unitprice" /></label></th>
                                            <th  align="center" class="tdgridnew1"><label><bean:message key="label.common.discount" /> (%)</label></th>
                                            <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.billto" /></label></th>
                                            <th  align="right" class="tdgridnew1" style="padding-right: 35px;"><label><bean:message key="label.common.amount" /></label></th>
                                        </tr>
                                        <c:set var="j" value="0"></c:set>
                                        <c:forEach items="${jobcardDetails.partNo}" var="dataList">
                                            <tr >
                                                <td align="center" class="tdgridnew1" >${(j+1)}</td>
                                                <td align="left" ><span>${jobcardDetails.partNo[j]}</span>
                                                    <input type="hidden"  name="partNo" value="${jobcardDetails.partNo[j]}" />
                                                </td>
                                                <td align="left" ><span>${jobcardDetails.partDesc[j]}</span>
                                                    <input type="hidden"  name="partDesc" value="${jobcardDetails.partDesc[j]}" />
                                                </td>
                                                 <td align="left" ><span>${jobcardDetails.hsnCode[j]}</span>
                                                    <input type="hidden"  name="hsnCode"  value="${jobcardDetails.hsnCode[j]}" />
                                                </td>
                                                <td align="left" ><span>${jobcardDetails.comptype[j]}</span>
                                                    <input type="hidden"  name="comptype" value="${jobcardDetails.comptype[j]}" />
                                                </td>
                                                <td align="left" ><span>${jobcardDetails.quantityS[j]}</span>
                                                    <input type="hidden"  name="quantityS" value="${jobcardDetails.quantityS[j]}" />
                                                </td>
                                                <td align="right" style="padding-right: 50px;"><span>${jobcardDetails.unitPrice[j]}</span>
                                                    <input type="hidden"  name="unitPrice" value="${jobcardDetails.unitPrice[j]}" />
                                                </td>
                                                <td align="right" style="padding-right: 50px;" ><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"  value="${jobcardDetails.discount_perc[j]}"/></span>
                                                    <input type="hidden"  name="discount_amt" value="${jobcardDetails.discount_amt[j]}" />
                                                </td>
                                                <td align="left" ><span>${jobcardDetails.billTo[j]}</span>
                                                    <input type="hidden"  name="billCode" value="${jobcardDetails.billCode[j]}" />
                                                </td>
                                                <td align="right" style="padding-right: 35px;"><span>${jobcardDetails.partPriceAmount[j]}</span>
                                                    <input type="hidden"  name="partPriceAmount" value="${jobcardDetails.partPriceAmount[j]}" />
                                                </td>
                                            </tr>
                                            <c:set var="j" value="${j + 1 }" />
                                        </c:forEach>
                                        <tr >
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.totalSparesValue" /></B></label></td>
                                            <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalPartsValue}</span>
                                                <input type="hidden"  name="totalPartsValue" value="${jobcardDetails.totalPartsValue}" />
                                            </td>
                                        </tr>
                                        <tr >
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.totallubesvalue" /></B></label></td>
                                            <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalLubesValue}</span>
                                                <input type="hidden"  name="totalLubesValue" value="${jobcardDetails.totalLubesValue}" />
                                            </td>
                                        </tr>
                                    </table></td>
                            </tr>
                        </c:if>
                        <c:if test="${fn:length(jobcardDetails.workDescription) gt 0 || fn:length(jobcardDetails.cmpid) gt 0 }">
                            <tr bgcolor="#eeeeee" height="20" >
                                <td align="left" colspan="6"><label><B><bean:message key="label.common.othercharges" /></B></label></td>
                            </tr></c:if>
                            <tr>
                                <td align="left" colspan="6">
                                    <table  border="0" cellspacing="3" cellpadding="3" width="100%">
                                    <c:if test="${!empty jobcardDetails.workDescription || !empty jobcardDetails.cmpid}">
                                        <tr class="grid ">
                                            <th width="7%"  align="center" class="tdgridnew1"><label><bean:message key="label.common.sno" /></label></th>
                                            <th width="30%"  align="left" class="tdgridnew1"><label><bean:message key="label.common.workType" /></label></th>
                                            <th width="10%" align="left" class="tdgridnew1"><label><bean:message key="label.common.category" /></label></th>
                                            <th width="40%"  align="left" class="tdgridnew1"><label><bean:message key="label.common.workDesc" /></label></th>
                                            <th width="13%"  align="right" class="tdgridnew1" style="padding-right: 35px;"><label><bean:message key="label.common.amount" /></label></th>
                                        </tr>

                                        <c:set var="k" value="0"></c:set>
                                        <c:forEach items="${jobcardDetails.workDescription}" var="dataList">
                                            <tr >
                                                <td align="center" class="tdgridnew1" ><span>${(k+1)}</span></td>
                                                <td align="left" ><span>${jobcardDetails.workCode[k]}</span></td>
                                                <td align="left" ><span><bean:message key="label.common.Sublets" /></span></td>
                                                <td align="left" ><span>${jobcardDetails.workDescription[k]}</span></td>
                                                <td align="right" style="padding-right: 35px;"><span>${jobcardDetails.workAmount[k]}</span></td>
                                            </tr>
                                            <c:set var="k" value="${k + 1 }" />
                                        </c:forEach>
                                        <c:set var="i" value="0"></c:set>
                                        <c:forEach items="${jobcardDetails.cmpid}" var="dataList">
                                            <c:set var="i1" value="0"></c:set>
                                            <c:forEach items="${jobcardDetails.action_taken[i]}" var="actionList">
                                                <tr >
                                                    <td align="center" class="tdgridnew1" ><span>${(k+1)}</span></td>
                                                    <td align="left" ><span>${jobcardDetails.compCode[i]}</span></td>
                                                    <td align="left" ><span><bean:message key="label.common.Labour" /></span></td>
                                                    <td align="left" ><span>${jobcardDetails.action_taken[i][i1]}</span></td>
                                                    <td align="right" style="padding-right: 35px;"><span>${jobcardDetails.labourCharges[i][i1]}</span></td>
                                                </tr>
                                                <c:set var="k" value="${k + 1 }" /><c:set var="i1" value="${i1 + 1 }" />
                                            </c:forEach>
                                            <%-- <tr >
                                             <td align="center" class="tdgridnew1" ><span>${(k+1)}</span></td>
                                             <td align="left" ><span>${jobcardDetails.compCode[i]}</span></td>
                                             <td align="left" ><span>Labour</span></td>
                                             <td align="left" ><span>${jobcardDetails.actionTaken[i]}</span></td>
                                             <td align="right" style="padding-right: 35px;"><span>${jobcardDetails.labourChargesAmount[i]}</span></td>
                                           </tr>--%>
                                            <c:set var="i" value="${i + 1 }" />
                                        </c:forEach>
                                        <tr >
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.totalSublets" /></B></label></td>
                                            <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalOtherCharges}</span>
                                                <input type="hidden"  name="totalOtherCharges" value="${jobcardDetails.totalOtherCharges}" />
                                            </td>
                                        </tr>
                                        <tr >
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.totallabourcharge" /></B></label></td>
                                            <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalLabourCharges}</span>
                                                <input type="hidden"  name="totalLabourCharges" value="${jobcardDetails.totalLabourCharges}" />
                                            </td>
                                        </tr>
                                        <tr >
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.otherdiscount" /></B></label></td>
                                            <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totaldiscount}</span>
                                                <input type="hidden"  name="totaldiscount" value="${jobcardDetails.totaldiscount}" />
                                            </td>
                                        </tr>
                                        <%--<tr >
                                          <td align="left" class="tdgridnew1">&nbsp;</td>
                                          <td align="left" class="tdgridnew1">&nbsp;</td>
                                          <td align="left" class="tdgridnew1">&nbsp;</td>
                                          <td align="right" class="tdgridnew1">&nbsp;</td>
                                          <td align="left" class="tdgridnew1">&nbsp;</td>
                                        </tr>--%></c:if>
                                        <tr >
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                            <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.GrandTotal" /> (<bean:message key="label.common.spares" /> + <bean:message key="label.common.lubes" /> + <bean:message key="label.common.Labour" /> +<bean:message key="label.common.Sublets" />)</B></label></td>
                                        <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalEstimate}</span>
                                            <input type="hidden" id="totalEstimate"  name="totalEstimate" value="${jobcardDetails.totalEstimate}" />
                                        </td>
                                    </tr>
                                    <tr >
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <%--<td align="right" class="tdgridnew1">&nbsp;</td>
                                        <td align="left" class="tdgridnew1">&nbsp;</td>--%>
                                        <td align="right" class="tdgridnew1"><B><%--<bean:message key="label.counterSale.creditAmount" />--%></B></td>
                                        <td align="right" class="tdgridnew1" style="padding-right: 35px">
                                            <input type="hidden" class="headingSpas" value="0" name="creditValue" id="CreditValue" maxlength="7"  onkeyup="getCreditAmm(this.value);" />
                                        </td>
                                    </tr>
                                </table></td>
                        </tr>
                        <%-- </c:if>--%>

                        <input type="hidden" name="status" value="BILLED" id="Status">
                        <tr>
                            <td align="center" colspan="6">
                                <input id="genInv" name="input" type="button" value="Generate Invoice" class="headingSpas1" onclick="validate()">
                            </td>
                        </tr>

                    </table>
                </form>
            </div>
        </div>
    </center>
</body>