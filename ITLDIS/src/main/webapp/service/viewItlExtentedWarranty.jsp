
<%--
    Document   : viewExtentedWarranty
    Created on : Apr 19, 2017, 04:51:25 PM
    Author     : prashant.kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>

<c:set var="invoiceDocName" value="${expList.gstInvoiceDocName}" scope="request" />
<%
     
            
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script type="text/javascript" language="javascript">

var contextPath = '${pageContext.request.contextPath}';

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

    function viewEwRefNo(ewLoaderId,chassisNo) {
        var strURL = '${pageContext.request.contextPath}/serviceProcess.do?option=getItlExtWarrPopupView&ewRefNo='+ewLoaderId+'&chassisNo='+chassisNo;
        window.open(strURL,'iji','width=1200,height=480, resizable=no,scrollbars=no, status=0,titlebar=0,toolbar=0, addressbar=0,top=20,left=10');
    }

    function updateEwRefNo(ewLoaderId,chassisNo,dealerCode) {
        var strURL = '${pageContext.request.contextPath}/serviceProcess.do?option=editItlExtWarranty&ewRefNo='+ewLoaderId+'&chassisNo='+chassisNo+'&userCode='+dealerCode;
        window.open(strURL,'iji','width=1200,height=600, resizable=no,scrollbars=no, status=0,titlebar=0,toolbar=0, addressbar=0,top=20,left=10');
    }
    
    var contextPathProject = '<%= request.getAttribute("contextPathProject") %>';
    console.log("contextPathProject "+contextPathProject);
   
    function downloadfile(filename)
    {
    
    	/* var strURL="${pageContext.request.contextPath}/DOCUMENTS/ITL_EXTENDED_WARRANTY/"+filename+".pdf";
        console.log("strURL "+strURL);
        var win=window.open(strURL, '_blank',"directories=no,height=900,width=700,menubar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,top=0,location=no");
        win.focus(); 
        */
        
    	var url=contextPath+"/DOCUMENTS/ItlExtendedWarranty/"+filename+"";
        var win=window.open(url, '_blank',"directories=no,height=700,width=700,menubar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,top=0,location=no");
        win.focus();
    }
    
    function printGSTTaxInvoice(EwRefNo)
    {
        var strURL = "${pageContext.request.contextPath}/serviceProcess.do?option=printGstTaxInvoice&EwRefNo="+Base64.encode(EwRefNo.trim());
      //  window.open(strURL,'taxInv','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
        window.open(strURL);
    }
    
</script>

<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" > 
    </head>
    <body>
        <div class="contentmain1">
            <div class="breadcrumb_container">
                <ul class="hText">
                    <li class="breadcrumb_link"><html:link action="warrantyAction.do?option=initItlExtWarranty"><bean:message key="label.warranty.ItlExtendedWarranty" /></html:link></li>
                    <li class="breadcrumb_link"><label><bean:message key="label.common.viewItlExtWarranty" /></label></li>
                </ul>
            </div>
            <div class="message" id="msg_saveFAILED"></div>
            <center>
                <div class="content_right1">
                    <div class="con_slidediv1" style="position: relative;width: 100%">
                        <h1> <bean:message key="label.common.viewExtWarranty" /></h1>
                        <table id="data" width=100% border=0 cellspacing=1 cellpadding=4 bordercolor=#cccccc>
                            <form action="serviceProcess.do?option=viewItlExtendedWarranty&flag=check" onsubmit="" method="post" id="searchform">
                                <%if (userFunctionalities.contains("101")) {%>
                                <tr bgcolor="#FFFFFF">
                                    <td align="right" style="padding-left: 5px" width="10%">
                                        <bean:message key="label.common.Chassisno"/></td>
                                    <td align="left">
                                        <input name="chassisNo" type="text" id="Chassis No" value="${serviceForm.chassisNo}" style="width:165px !important;" onblur="this.value=TrimAll(this.value)"/>
                                    </td>                                    
                                    <td class="headingSpas" nowrap align="right">&nbsp;
                                        <c:if test="${range eq '1'}">
                                            <input type="checkbox" name="range" value="1" id="Range" checked onclick="disableDate(this)">
                                        </c:if>
                                        <c:if test="${range ne '1' }">
                                            <input type="checkbox" name="range" value="1" id="Range" >
                                        </c:if>
                                        <bean:message key="label.common.dateSaleOfCertificate"/></td>
                                    <td align="left">
                                        <input name="fromdate" type="text" class="datepicker" id="From Date" value="${fromdate}" onkeydown="return false;"/>
                                    </td>
                                    <td class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/>&nbsp;                                        
                                        <input name="todate" type="text" class="datepicker" id="To Date" value="${todate}" onkeydown="return false;"/>
                                    </td>
                                    <td class="headingSpas" nowrap align="right">
                                        <bean:message key="label.common.status" />
                                    </td>
                                    <td align="left" bgcolor="#FFFFFF" class="text">
                                        <select id="Status" name="status" class="headingSpas" style="width:155px !important;" >
                                            <option value="All" >All</option>
                                            <c:forEach items="${extWarrStatus}" var="extList">
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
                                    <td colspan="7" align="right" style="padding-right: 50px;">
                                        <input type="button" value="Search" onclick="search()"/>&nbsp;&nbsp;
                                      <input type="button" value="Export" onclick="Export()"/>                                          
                                        <input type="hidden" name="etype" id="etype" />
                                    </td>                                  
                                </tr>
                                <%} else {%>
                                <tr height=20 bgcolor="#FFFFFF">
                                    <td align="right" style="padding-left: 5px" width="10%">
                                        <bean:message key="label.common.Chassisno"/></td>
                                    <td align="left">
                                        <input name="chassisNo" type="text" id="Chassis No" value="${serviceForm.chassisNo}" style="width:165px !important;" onblur="this.value=TrimAll(this.value)"/>
                                    </td>
                                    <td class="headingSpas" nowrap align="right">&nbsp;
                                        <c:if test="${range eq '1'}">
                                            <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                        </c:if>
                                        <c:if test="${range ne '1'}">
                                            <input type="checkbox" name="range" value="1"  id="Range" >
                                        </c:if>
                                        <bean:message key="label.common.dateSaleOfCertificate"/></td>
                                    <td  align="left">
                                        <input name="fromdate" type="text" class="datepicker" id="From Date" value="${fromdate}" onkeydown="return false;"/>
                                    </td>
                                    <td class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/>&nbsp;                                   
                                        <input name="todate" type="text" class="datepicker" id="To Date" value="${todate}" onkeydown="return false;"/>
                                    </td>
                                    <td class="headingSpas" nowrap align="right">
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
                                    <td align="right" width="5%" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                    <td align="left" width="20%" colspan="3" style="padding-right: 10px;">
                                        <select id="Dealer Name" name="dealercode" style="width:300px !important">
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
                                    <td colspan="3" align="right" style="padding-right: 62px;">
                                        <input type="button" value="Search" onclick="search()"/>&nbsp;&nbsp;          
                                        <input type="button" value="Export" onclick="Export()"/>                           
                                        <input type="hidden" name="etype" id="etype" />
                                    </td>
                                </tr>
                                <%}%>
                            </form>
                            <c:if test="${empty viewExpWarrList}">
                                <tr bgcolor="#FFFFFF">
                                    <td valign="top" align="center" colspan="10" class="headingSpas" style="padding-top:10px;color: red">
                                        <bean:message key="label.common.noRecordFound" />
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty viewExpWarrList}">
                                <tr>
                                    <td colspan="7">
                                        <pg:pager url="serviceProcess.do" maxIndexPages="12" maxPageItems="10">
                                            <pg:param name="option" value="viewItlExtendedWarranty"/>
                                            <pg:param name="chassisNo" value="${serviceForm.chassisNo}"/>                                            
                                            <pg:param name="dealercode" value="${serviceForm.dealercode}"/>
                                            <pg:param name="fromdate" value="${fromdate}"/>
                                            <pg:param name="todate" value="${todate}"/>
                                            <pg:param name="status" value="${serviceForm.status}"/>
                                            <pg:param name="flag"  value='check'/>
                                            <pg:param name="etype" value='etype'/>                                            

                                            <table width="100%" border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                                <tr bgcolor="#eeeeee" height="20">
                                                    <td align="left" width="3%"  nowrap style="padding-left:3px;padding-right:3px;"><b><bean:message key="label.catalogue.sNo"/></b> </td>
                                                    <td align="left" width="12%" nowrap style="padding-left:3px;padding-right:3px;"><b><bean:message key="label.common.itlExtWtyRefNo"/></b></td>
                                                    <td align="center" width="10%" nowrap style="padding-left:3px;padding-right:3px;"><b><bean:message key="label.common.itlExtWtyRegDate"/></b></td>
                                                    <td align="left" width="12%" nowrap style="padding-left:3px;padding-right:3px;"><b><bean:message key="label.common.Chassisno"/></b></td>
                                                    <td align="left" width="8%" nowrap style="padding-left:3px;padding-right:3px;"><b><bean:message key="label.common.modelName"/></b></td>
                                                    <td align="left" width="15%" nowrap style="padding-left:3px;padding-right:3px;"><b><bean:message key="label.common.nameOfCust"/></b></td>
                                                    <td align="left" width="8%" nowrap style="padding-left:3px;padding-right:3px;"><b><bean:message key="label.common.itlExtWtyPolicyStatus"/></b></td>
                                                    <td align="left" width="8%" nowrap style="padding-left:3px;padding-right:3px;"><b><bean:message key="label.common.itlEwDocument"/></b></td>
                                                    <%if (!userFunctionalities.contains("101")) {%>
                                                    <td  width="15%" align="left" style="padding-left:3px;font-weight:bold;white-space: nowrap" ><bean:message key="label.common.dealerName" /></td>
                                                    <%}%>
                                                    <%if (userFunctionalities.contains("1003")) {%>
                                                    <td align="center" width="5%" nowrap style="padding-left:3px;padding-right:3px;"><b><bean:message key="label.common.Edit" /> </b></td>
                                                    <%}%>
                                                </tr>
                                                <c:set var="sno" value="1"></c:set>
                                                <logic:iterate name="viewExpWarrList" id="expList">
                                                    <pg:item>
                                                        <tr bgcolor="#FFFFFF">
                                                            <td align="left">${sno}</td>
                                                            <td align="left" >
                                                                <a onclick="viewEwRefNo('${expList.ewLoaderId}','${expList.chassisNo}');" href="javascript:void(0);" title="View Details">
                                                                    <span >${expList.ewLoaderId}</span></a>
                                                            </td>
                                                            <td align="center" style="padding-left:3px;"><span>${expList.dateOfSaleOfCertificate}</span></td>
                                                            <td align="left" style="padding-left:3px;"><span>${expList.chassisNo}</span></td>
                                                            <td align="left" style="padding-left:3px;"><span>${expList.modelCodeDesc}</span></td>
                                                            <td align="left" style="padding-left:3px;"><span>${expList.customerName}</span></td>
                                                            <td align="left" style="padding-left:3px;"><span>${expList.status}</span></td>
                                                            <td align="left" style="padding-left:3px;"><span>
                                                            
                                                            <a href="#"><img src="${pageContext.request.contextPath}/image/gst_invoice.jpg" height="21" width="21" title="GST Invoice" onclick="printGSTTaxInvoice('${expList.ewLoaderId}')"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
                                                            
                                                            <c:if test="${not empty expList.itlEwCertificateName}">
                                                            <a href="#"><img src="${pageContext.request.contextPath}/image/warranty.png" height="21" width="21" title="Warranty Certificate" onclick="downloadfile('${expList.itlEwCertificateName}')"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
                                                            </c:if>
                                                            <c:if test="${not empty expList.itlEwDebitInvoice}">
                                                            <a href="#"><img src="${pageContext.request.contextPath}/image/debitInvoice.png" height="21" width="21" title="Warranty Debit Invoice" onclick="downloadfile('${expList.itlEwDebitInvoice}')"/></a>
                                                            </c:if>
                                                            </span></td>
                                                           
                                                            <%if (!userFunctionalities.contains("101")) {%>
                                                            <td align="left" style="padding-left:3px;">${expList.dealerCode} [${expList.dealerName}]</td>
                                                            <%}%>
                                                            <%if (userFunctionalities.contains("1003")) {%>
                                                            <td align="center">
                                                                <a onclick="updateEwRefNo('${expList.ewLoaderId}','${expList.chassisNo}','${expList.dealercode}');" href="javascript:void(0);" >
                                                                    <img alt="" src="${pageContext.request.contextPath}/images/edit.gif" title="Edit"/>
                                                                </a>
                                                            </td>
                                                            <%}%>
                                                        </tr>
                                                    </pg:item>
                                                    <c:set var="sno" value="${sno + 1 }" />
                                                </logic:iterate>
                                                <tr>
                                                    <%if (userFunctionalities.contains("101")) {%>
                                                    <td colspan="9" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                        <%} else {%>
                                                    <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                        <%}%>
                                                        <table  align="center" cellspacing="0" cellpadding="0">
                                                            <tr>
                                                                <td>
                                                                    <pg:index>
                                                                        <pg:first><a href="${pageUrl}"><img alt="First" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle" style="float:left;"/></a></pg:first>&nbsp;
                                                                        <pg:prev><a href="${pageUrl}"><img alt="Previous" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle" style="float:left;"/></a></pg:prev>&nbsp;
                                                                        <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;                                                                        
                                                                        <pg:last><a href="${pageUrl}"><img alt="Last" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle" style="float:right"/></a></pg:last>
                                                                        <pg:next><a href="${pageUrl}"><img alt="Next" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle" style="float:right"/></a></pg:next>&nbsp;
                                                                        </pg:index>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </pg:pager>
                                    </td>
                                </tr>
                            </c:if>
                        </table>
                    </div>
                </div>
            </center>
        </div>
    </body>
</html>