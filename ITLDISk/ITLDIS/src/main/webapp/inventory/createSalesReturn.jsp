<%-- 
    Document   : createSalesReturn
    Created on : Sep 5, 2014, 12:31:24 PM
    Author     : kundan.rastogi
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants,org.apache.struts.util.TokenProcessor"%>
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
            String token = TokenProcessor.getInstance().generateToken(request);
            session.setAttribute("org.apache.struts.action.TOKEN", token);

%>

<script>
   
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
    
    function settodayDate(){
        document.getElementById("Return Date").value=tdays+'/'+tmon+'/'+<%=year%>;
    }

   
    function checkQty(row){

        if(document.getElementById("ReceivedQty"+row).value!=""){
            if(!document.getElementById("ReceivedQty"+row).value.match(/^[0-9.]+$/)){
                document.getElementById("ReceivedQty"+row).value='';
                document.getElementById("ReceivedQty" + row).focus();
                alert("ReturnedQty "+numeric_msg);
                return false;
            }
        }

        if(eval(document.getElementById("ReceivedQty"+row).value) > eval(document.getElementById("Qty"+row).value)){
            alert("Returned Qty"+ maxLength_validation_msg +"Invoice Qty");
            document.getElementById("ReceivedQty"+row).focus();
            document.getElementById("ReceivedQty"+row).value="";
            return false;
        }
        if(eval(document.getElementById("ReceivedQty"+row).value) <= eval(document.getElementById("Qty"+row).value)){
            var aa= eval(document.getElementById("Ammount"+row).value)/eval(document.getElementById("Qty"+row).value)
            var retAmm=aa*eval(document.getElementById("ReceivedQty"+row).value);
            document.getElementById("ReturnAmmount"+row).value=retAmm.toFixed(2);
            document.getElementById("UnitValue"+row).value=aa.toFixed(2);
        }
        var totAmm=0;
        var oRows = document.getElementById("myTable").getElementsByTagName('tr');
        for(var i=1;i<=oRows.length-3;i++){
            if(document.getElementById("ReturnAmmount"+i).value!=""){
                // alert(document.getElementById("ReturnAmmount"+i).value)
                totAmm =totAmm +eval(document.getElementById("ReturnAmmount"+i).value);
            }
        }
        document.getElementById("TotalAmmount").value=totAmm.toFixed(2);
    }
    function validateForm(){
        if(document.getElementById("Return By").value==""){
            alert(not_blank_validation_msg+"Return By");
            document.getElementById("Return By").focus();
            return false;
        }
        if(!document.getElementById("Return By").value.match(/^[a-z A-Z]+$/)){
            document.getElementById("Return By").focus();
            alert(specialChar_validation_msg+'Return By');
            return false;
        }

        if(document.getElementById("Return Date").value==""){
            alert(not_blank_validation_msg+"Return Date");
            document.getElementById("Return Date").focus();
            return false;
        }
   
        var invDate=document.getElementById("InvoiceDate").value.split('/');
        var sdate=invDate[2]+'/'+invDate[1]+'/'+invDate[0];
        var inDate=new Date(sdate).getTime();
        var inDateM=inDate +30*24*60*60*1000;
     
        var retDate=document.getElementById("Return Date").value.split('/');
        var rdate=retDate[2]+'/'+retDate[1]+'/'+retDate[0];
        var retnDate=new Date(rdate).getTime();

        if(retnDate < inDate){
            document.getElementById("Return Date").focus();
            alert(returnDateVali_msg);
            return false;
        }

        if(retnDate > inDateM){
            document.getElementById("Return Date").focus();
            alert(retrunDateRange_valid_msg);
            return false;
        }

   
        var oRows = document.getElementById("myTable").getElementsByTagName('tr');
        for(var i=1;i<=oRows.length-3;i++){
            if(document.getElementById("ReceivedQty"+i).value==""){
                document.getElementById("ReceivedQty"+i).focus();
                alert(not_blank_validation_msg+"Returned Qty ");
                return false;
            }
        }

        document.getElementById("myForm1").submit();
    }

    function getInvoiceData(){
        var invNo=document.getElementById("InvoiceNo").value;
        if(invNo==""){
            alert(not_blank_validation_msg+"Invoice No.");
            document.getElementById("InvoiceNo").focus();
            return false;
        }
        document.getElementById("form1").submit();
    }
</script>
<c:if test="${!empty dataList}">
    <body onload="settodayDate()"></body>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <div class="contentmain1">
        <div class="breadcrumb_container">
            <ul>
                <li class="breadcrumb_link"><a href ='<%=cntxpath%>/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
                <li class="breadcrumb_link"><bean:message key="label.common.createSalesReturn" /></li>

            </ul>
        </div>
        <br/>
        <div class="message" id="msg_saveFAILED">
            ${message}
        </div>
        <center>

            <div class="content_right1">
                <div class="con_slidediv2" style="position: relative;width: 100%">
                    <h1><bean:message key="label.common.createSalesReturn" /> </h1>
                    <body>
                        <%--<form action="<%=cntxpath%>/inventoryAction.do?option=addSalesReturn" method="post" id="form1" onsubmit="return false" >--%>
                        <form action="<%=cntxpath%>/inventoryAction.do?option=getInvoiceDetails" method="post" id="form1" onsubmit="return false" >
                            <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a>@@<bean:message key="label.common.createSalesReturn" />"/>
                            <table cellspacing=1 cellpadding=1 border=0 width=100%>
                                <tr align="center" height="40" bgcolor="#FFFFFF" style="padding-bottom: 40px;">
                                    <td width="40%" align="right" class="headingSpas" ><B><bean:message key="label.common.InvoiceNo" /> :</B>
                                    </td>
                                    <td bgcolor="#FFFFFF" align="left">
                                        <input type="text" name="invNo" value="${invNo}" id="InvoiceNo"  style="width: 150px" >&nbsp;&nbsp;&nbsp;&nbsp; <%--  class="headingSpas1"--%>
                                        <input type="button" value="Go"  onclick="getInvoiceData();"  />

                                    </td>

                                </tr>
                            </table>
                            <br>

                            <%-- <div cellspacing=1 cellpadding=1 border=0 width=100% id="InvoiceData">
                             </div>--%>

                        </form>

                        <form action="<%=cntxpath%>/inventoryAction.do?option=addSalesReturn" method="post" id="myForm1" onsubmit="return false" >
                            <input type="hidden" name="<%= Constants.TOKEN_KEY%>" value="<%= token%>" />
                            <c:if test="${!empty dataList}">
                                <table id="" width="100%" border="0" align="center" cellpadding="3" cellspacing="1"  >
                                    <tr bgcolor="#FFFFFF" class="headingSpas" height="20">
                                        <td align="right"><B><bean:message key="label.common.CustomerName" /> : </B></td>
                                        <td align="left">${inventoryForm.cusName} <input type="hidden" name="cusName" value="${inventoryForm.cusName}"><input type="hidden" name="customerId" value="${inventoryForm.customerId}"></td>
                                        <td align="right"><B> <bean:message key="label.common.InvoiceNo" />  : </B></td>
                                        <td align="left">${inventoryForm.invNo}  <input type="hidden" name="invNo" value="${inventoryForm.invNo}"></td>
                                        <td align="right"><B> <bean:message key="label.common.InvoiceDate" />  : </B></td>
                                        <td align="left">${inventoryForm.invDate} <input type="hidden" name="invDate" id="InvoiceDate" value="${inventoryForm.invDate}"></td>

                                    </tr>
                                    <tr bgcolor="#FFFFFF" class="headingSpas" height="20">
                                        <td align="right"> <B> <bean:message key="label.saleReturn.ReturnBy" />  : </B></td>
                                        <td align="left"><input type="text" name="returnBy" id="Return By" value="" maxlength="30" ></td>
                                        <td align="right"> <B> <bean:message key="label.saleReturn.ReturnDate" />   : </B></td>
                                        <td align="left">
                                            <input type="text" name="returnDate"  id="Return Date" class="datepicker" value="" readonly>
                                        </td>
                                        <td align="right" width="15%"  ><label ><B><bean:message key="label.common.saleTaxType" /> : </B></label></td>
                                        <td align="left" width="15%"  ><span>${inventoryForm.saleTaxTypeID}</span>
                                        </td>

                                    </tr>
                                </table>
                                <br>
                                <table id="myTable" width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bordercolor=#cccccc bgcolor="#cccccc" >
                                    <tr bgcolor="#eeeeee" height="20">
                                        <td align="center" width="6%"><B><bean:message key="label.common.sno" /></B></td>
                                        <td align="left" width="10%"><B><bean:message key="label.common.partno_small" /></B></td>
                                        <td align="left" width="20%"><B><bean:message key="label.common.partdesc_small" /></B></td>
                                        <td align="center" width="8%"><B><bean:message key="label.common.invoice" /> <bean:message key="label.common.qty_small" /></B></td>
                                        <td align="center" width="8%"><B><bean:message key="label.common.unitprice_small" /></B></td>
                                        <td align="center" width="8%"><B><bean:message key="label.common.discount_small" /> </B></td>
                                        <td align="center" width="8%"><B><bean:message key="label.common.amt_small" /></B></td>
                                        <td align="center" width="8%"><B><bean:message key="label.saleReturn.ReturnQty" /> </B></td>
                                        <td align="center" width="8%"><B><bean:message key="label.saleReturn.ReturnAmount" /> </B></td>
                                    </tr>
                                    <c:set var="i" value="1"></c:set>
                                    <c:forEach items="${dataList}" var="dataList">
                                        <tr bgcolor="#FFFFFF" class="headingSpas" height="20">
                                            <td align="center" width="6%">${i}</td>
                                            <td align="left" width="10%">${dataList.partno} <input type="hidden" name="partNo" id="Part No${i}" value="${dataList.partno}" maxlength="30" /></td>
                                            <td align="left" width="20%"> ${dataList.part_desc} <input name="partDesc" id="Desc${i}" type="hidden" value="${dataList.part_desc}"/></td>
                                            <td align="center" width="8%"> ${dataList.qty}  <input type="hidden" name="quantity" id="Qty${i}" value="${dataList.qty}" maxlength="7" /> </td>
                                            <td align="center" width="8%"> ${dataList.unitValue}  <input type="hidden" name="unitprice" id="UnitValue${i}" value="" maxlength="7"  />  </td>
                                            <td align="center" width="8%"> ${dataList.discount} </td>
                                            <td align="center" width="8%">  ${dataList.finalamount} <input type="hidden" name="amount" id="Ammount${i}" value="${dataList.finalamount}" maxlength="7"  /> </td>
                                            <td align="center" width="8%">  <input type="text" name="receivedQty" id="ReceivedQty${i}" onblur="checkQty(${i})" maxlength="7"> </td>
                                            <td align="center" width="8%">  <input type="text" name="returnAmmount" id="ReturnAmmount${i}" readonly> </td>
                                        </tr>
                                        <c:set var="i" value="${i + 1 }" />
                                    </c:forEach>

                                    <tr bgcolor="#FFFFFF" class="headingSpas" height="20">
                                        <td colspan="8" align="right" style="padding-right: 10px;" ><B> <bean:message key="label.warranty.total" /> :</B></td>
                                        <td> <input type="text" name="finalamount" id="TotalAmmount" value="" readonly></td>

                                    </tr>

                                    <tr bgcolor="#FFFFFF" class="headingSpas" height="20">
                                        <td colspan="9" align="center" style="padding-right: 10px">
                                            <input type="button" name="Submit" value="Submit" onclick="validateForm();">
                                        </td>
                                    </tr>


                                </table>

                            </c:if>
                            <%-- <c:if test="${empty dataList}">
                                 <bean:message key="label.common.noRecordFound" />
                             </c:if>--%>


                        </form>


                    </body>
                </div>
            </div>
        </center>
    </div>

</html>

<script>
    $(function() {
        $("#Return Date").datepicker();
    });
</script>