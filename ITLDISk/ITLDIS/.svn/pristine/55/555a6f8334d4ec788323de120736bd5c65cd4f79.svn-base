<%--
    Document   : createGRNEXP
    Created on : 08-APR-2016, 10:19:33
    Author     : avinash.pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page import="java.util.*,beans.masterForm,beans.inventoryForm" %>
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
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" media="screen" />
<link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhtmlgoodies_calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/intermediate.js"></script>
<script>
    var tmon="<%=month%>";
    var tdays="<%=day%>";
    if(eval(tmon)<10)
        tmon="0"+tmon;
    if(eval(tdays)<10)
        tdays="0"+tdays;
    ttdate=<%=year%>+'/'+tmon+'/'+tdays;
    var today = new Date(ttdate);
    function settodayDate(){
       if(document.getElementById("ReceivedOn")!=null){
            document.getElementById("ReceivedOn").value=tdays+'/'+tmon+'/'+<%=year%>;
        }
   }
    function validate(){
        if (document.getElementById("ReceivedBy").value==""){
            document.getElementById("ReceivedBy").focus();
            alert(not_blank_validation_msg+"Received By");
            return false;
        }
        if (!trim(document.getElementById("ReceivedBy").value).match(/^[A-Z a-z]+$/)){
                document.getElementById("ReceivedBy").focus();
                document.getElementById("ReceivedBy").value="";
                alert(only_alphab_validation_msg+"Received By");
                return false;
            }
        if (document.getElementById("ReceivedOn").value==""){
            document.getElementById("ReceivedOn").focus();
            alert(not_blank_validation_msg+"Received On");
            return false;
        }
        var  invDate=document.getElementById("InvDate").value;
        invDate = trim(invDate);
        var x =invDate;     //'1-1-2015';
        var a = x.split('/');
        var invDate1 = new Date (a[2], a[1] - 1,a[0]);

        var  receDate=document.getElementById("ReceivedOn").value;
        receDate = trim(receDate);
        var y =receDate;     //'17-1-2015';
        var b = y.split('/');
        var receDate1 = new Date (b[2], b[1] - 1,b[0]);
        if(invDate1 > receDate1){
            alert(receivedOnDate_val_msg)
            document.getElementById('ReceivedOn').focus();
            return false;
        }
         document.getElementById("form2").submit();
    }
    function getInvoiceData(val){
       var strURL=contextPath + "/inventoryEXPAction.do?option=getInvoiceData&invNo="+val+"&flag=part&t="+new Date().getTime();
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readystate == 'complete')
            {
                res = trimS(xmlHttp.responseText);
                document.getElementById('InvoiceData').innerHTML=res;
                document.getElementById('heading').style.display='block';
                var inv=document.getElementById('InvNo').value;
                document.getElementById('InvSpan').innerHTML=inv.split("@@")[0];
                document.getElementById('InvDateSpan').innerHTML=inv.split("@@")[1];
            }
        };
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);
        settodayDate();
    }
    function validateGo(){
        if (document.getElementById("InvNo").value==""){
            document.getElementById("InvNo").focus();
            alert(not_blank_dropdown_validation_msg+"Invoice No");
            return false;
        }
        document.getElementById("form1").submit();
    }
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <div class="contentmain1">
        <div class="breadcrumb_container">
            <ul class="hText">
                <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
                <li class="breadcrumb_link"><bean:message key="label.order.createGRN" /></li>
            </ul>
        </div>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
              <h1 class="hText"><bean:message key="label.order.createGRN" /></h1>
                 <body>
                    <form action="<%=cntxpath%>/inventoryEXPAction.do?option=createGRNEXP" method="post" id="form1" onsubmit="return false" >
                        <table cellspacing=1 cellpadding=5 border=0 width=100%>
                            <tr  height="40" bgcolor="#FFFFFF">
                                <td  align="right" class="headingSpas" width="40%" align="left" ><bean:message key='label.common.InvoiceNo' />
                                </td>
                                <td bgcolor="#FFFFFF" align="left" width="20%" >
                                    <select name="invNo"  id="InvNo" style="width:210px !important " class="headingSpas" >   <%--settodayDate();  onchange="getInvoiceData(this.value)"--%>
                                        <option value="">--Select Here--</option>
                                        <c:forEach items="${inventoryForm.penInviceList}" var="PendigInvoiceList">
                                            <c:choose>
                                                <c:when test="${PendigInvoiceList.invNo eq inventoryForm.invNo}">
                                                    <option value="${PendigInvoiceList.invNo}" selected>${PendigInvoiceList.invNo}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${PendigInvoiceList.invNo}">${PendigInvoiceList.invNo}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select> 
                                </td> 
                                <td  align="left" class="headingSpas" width="40%" align="left" >
                                <input type="button" name="Submit" value="Go" onclick="validateGo()"></td>
                            </tr>
                        </table>
                    </form>
                 <c:if test="${not empty inventoryForm.grnPartList}">
                    <form action="<%=cntxpath%>/inventoryEXPAction.do?option=addGRNEXP" method="post" id="form2" onsubmit="return false" >
                            <div class="content_right1">
                                <div style="overflow:auto; position: relative;width: 100%; height:450px">
                                    <table border=0 cellspacing=1 cellpadding=1  width=100%  bordercolor=#cccccc >
                                        <tr>
                                            <td width="20%" align="right" ><label><bean:message key="label.common.InvoiceNo" /> :</label></td>
                                            <td width="20%" align="left" style="padding-left: 5px"> ${inventoryForm.invNo} <input type="hidden" name="invNo" value="${inventoryForm.invNo}">  </td>
                                            <td width="20%" align="right"><label><bean:message key="label.common.InvoiceDate" /> :</label></td>
                                            <td  width="20%" align="left" style="padding-left: 5px">${inventoryForm.invDate} <input type="hidden" name="invDate" id="InvDate" value="${inventoryForm.invDate}"> </td>
                                        </tr>
                                        <tr>
                                            <td width="20%" align="right" ><label><bean:message key="label.warranty.receivedBy" /> :</label></td>
                                            <td width="20%" align="left" ><input type="text" name="receivedBy" id="ReceivedBy" maxlength="30"></td>
                                            <td width="20%" align="right"><label><bean:message key="label.common.tractorRecdate" /> :</label></td>
                                            <td  width="20%" align="left" >
                                                <input type="text" id="ReceivedOn" name="receivedOn" value="" class="datepicker" maxlength="10" style="width: 60px" readonly onkeydown="return false;"/>
                                            </td>
                                        </tr>
                                    </table>
                                    <table border=0 cellspacing=1 cellpadding=1  width=100%  bordercolor=#cccccc bgcolor=#cccccc id="myTb" name="myTb">
                                        <tr  bgcolor="#eeeeee" height="20" >
                                            <td align="center" width="5%" class="headingSpas" style="border-top:solid 1px #ccc;padding-left:5px" ><b><bean:message key='label.common.sno' /></b></td>
                                            <%--<td align="left" width="15%" class="headingSpas" style="border-top:solid 1px #ccc;padding-left:5px" ><b><bean:message key='label.order.orderNo' /></b></td>--%>
                                            <td width="15%" align="left" class="headingSpas" style="border-top:solid 1px #ccc;padding-left:5px" ><b><bean:message key='label.common.invoice.shiped.part' /> </b></td>
                                            <td width="30%" align="left" class="headingSpas"style="border-top:solid 1px #ccc;padding-left:5px"  ><b><bean:message key='label.common.partdesc_small' /></b></td>
                                            <td align="right" width="10%" class="headingSpas" style="border-top:solid 1px #ccc;padding-right: 5px"><B><bean:message key="label.common.unitprice_small" /></B></td>
                                            <td width="10%" align="center" class="headingSpas" style="border-top:solid 1px #ccc" ><b><bean:message key='label.Grn.invQty' /> </b></td>
                                            <td width="10%" align="center" class="headingSpas" style="border-top:solid 1px #ccc" ><b><bean:message key='label.saleReturn.ReceivedQty' /> </b></td>
                                        </tr>
                                        <c:set var="sno" value="1"/>
                                        <c:forEach items="${inventoryForm.grnPartList}" var="invoiceDataList">
                                            <tr bgcolor="#FFFFFF">
                                                <td align="center" bgcolor="#FFFFFF" width="5%" title="${sno}" class="headingSpas" style="padding-left:5px; padding-right: 5px" >
                                                    ${sno}  <input type="hidden" name="" value="">
                                                </td>
                                               <%-- <td align="left" bgcolor="#FFFFFF" width="15%" class="headingSpas" style="padding-left:5px; padding-right: 5px" >
                                                    ${invoiceDataList.invOrderNo} <input type="hidden" name="invOrderNo" value="">
                                                </td>--%>
                                               <td width="15%" align="left" bgcolor="#FFFFFF" title="${invoiceDataList.partno}" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                   ${invoiceDataList.partno} <input type="hidden" name="partNo" value="${invoiceDataList.partno}">
                                                </td>
                                                <td width="30%" align="left" bgcolor="#FFFFFF" title="${invoiceDataList.part_desc}" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    ${invoiceDataList.part_desc} <input type="hidden" name="partDesc" value="${invoiceDataList.part_desc}">
                                                </td>
                                                <td width="10%" align="right" bgcolor="#FFFFFF" title="${invoiceDataList.unitValue}" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    ${invoiceDataList.unitValue}  <input type="hidden" name="unitprice" value="${invoiceDataList.unitValue}">
                                                </td>
                                                <td width="10%" align="center" bgcolor="#FFFFFF" title="${invoiceDataList.qty}" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    ${invoiceDataList.qty}  <input type="hidden" name="quantity" id="InvoiceQty${sno}" value="${invoiceDataList.qty}" maxlength="7">
                                                </td>
                                                <td width="10%" align="center" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <input type="text" name="receivedQty" id="ReceivedQty${sno}" value="${invoiceDataList.qty}" maxlength="7" readonly="true">
                                                    <input type="hidden" name="np1Array" value="${invoiceDataList.np1}"/>
                                                </td>
                                            </tr>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </c:forEach>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td colspan="6"  align="center" bgcolor="#FFFFFF" width="100%" style="padding-top:25px;">
                                                <input type="button" value="Submit" class="headingSpas1" style="float:none;" onclick="validate()"/>
                                                <input type="hidden" name="invNo" id="InvNo" value="${inventoryForm.invNo}@@${inventoryForm.invDate}">
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                        </div>
                    </form>
                  </c:if>
                </body>
            </div>
        </div>
    </center>
</html>

<script>
    $(function() {
        $( ".datepicker" ).datepicker();
    });
    settodayDate();
</script>