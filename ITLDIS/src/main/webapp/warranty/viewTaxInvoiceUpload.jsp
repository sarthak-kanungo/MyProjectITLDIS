<%-- 
    Document   : ViewPackingList
    Created on : Dec 9, 2014, 4:19:31 PM
    Author     : megha.pandya
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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.*,java.text.*" %>
<%@ page import="java.util.Date,viewEcat.comEcat.PageTemplate" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String dealerFlag="";
            if (!userFunctionalities.contains("101")) {dealerFlag="true";}
%>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
   
    function submitForm()
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
        
        if (document.getElementById("From Date").value.trim() === "") {
            alert("Please Select From Date.");
            document.getElementById("From Date").focus();
            return false;
        }
        
        if (document.getElementById("To Date").value.trim() === "") {
            alert("Please Select To Date.");
            document.getElementById("To Date").focus();
            return false;
        }
        
        const prevFYStart = getPreviousFYStartDate();
        const fromDate  = document.getElementById("From Date").value.trim();
        if (dateF < prevFYStart) {
            alert(
                "From Date cannot be earlier than previous financial year: " +
                formatDateDDMMYYYY(prevFYStart)
            );
            document.getElementById("From Date").focus();
            return false;
        }

        document.getElementById("packingList4view").submit();
    }
    
    function formatDateDDMMYYYY(date) {
        const dd = String(date.getDate()).padStart(2, '0');
        const mm = String(date.getMonth() + 1).padStart(2, '0');
        const yyyy = date.getFullYear();
        return dd + '/' + mm + '/' + yyyy;
    }
    
    
    function getPreviousFYStartDate() {
        const today = new Date();

        const currentYear = today.getFullYear();
        const currentMonth = today.getMonth() + 1; // JS months are 0-based

        let prevFYYear;

        if (currentMonth >= 4) {
            // Current FY started this April, so previous FY started last year
            prevFYYear = currentYear - 1;
        } else {
            // Current FY started last year April, so previous FY started 2 years ago
            prevFYYear = currentYear - 2;
        }

        return new Date(prevFYYear, 3, 1); // April is month 3 (0-based)
    }

    
    function printConsignment(packingNo)
    {
        var strURL="${pageContext.request.contextPath}/warrantyAction.do?option=packingDetails4View&packingNo="+packingNo+"&print=true";
        window.open(strURL,'iji','width=1000,height=950, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }
    function printWarrantyMaterial(packingNo)
    {
        var strURL="${pageContext.request.contextPath}/warrantyAction.do?option=WarrantyMaterialView&packingNo="+packingNo+"&print=true";
        window.open(strURL,'iji','width=800,height=950, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
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
    function viewCourierCopyFile(courierCopyFileName)
    {
        
        var strURL="${pageContext.request.contextPath}/DOCUMENTS/WARRANTY/"+courierCopyFileName+"";
        window.open(strURL,'courierCopyFileName','width=750,height=600, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
        window.moveTo(50, 0);
    }
    function printTaxInvoice(packingNo)
    {
        var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=printDeliveryChallan&packingNo="+packingNo;
        window.open(strURL,'taxInv','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }
    function printWarrantyClaimChallan(packingNo)
    {
        var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=printWarrantyClaimChallan&packingNo="+packingNo;
        window.open(strURL,'taxClaimInv','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }
    
    function validatePDF(input,sno) {
    	
    	const file = input.files[0];
    	if (file.type !== "application/pdf") {
            alert("Please upload a PDF file.");
            input.value = ""; // Clear the input value if not a PDF
            return false;
        }
    	
    	
        var uploadLink = document.getElementById('uploadLink_' + sno);
        if (input.files.length > 0 && input.files[0].type === 'application/pdf') {
            uploadLink.style.display = 'inline'; // Show upload icon
        } else {
            uploadLink.style.display = 'none'; // Hide upload icon
        }
    }

 
    
    function triggerPDFUpload(packingNo, sno) {
        var fileInput = document.getElementById('uploadTaxInvoice_' + sno);
        
        if (fileInput.files.length > 0) {
            var file = fileInput.files[0];
            
            // Check if the file is a PDF
            if (file.type === "application/pdf") {
                // Create a FormData object
                var formData = new FormData();
                formData.append('uploadTaxInvoice', file);  // Append the file
                formData.append('packingNo', packingNo);    // Append the packing number
                
                // Send the formData using AJAX
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "<%=cntxpath%>/warrantyAction.do?option=uploadTaxInvoice", true);
                xhr.onload = function() {
                    if (xhr.status === 200) {
                        alert('File uploaded successfully');
                        window.location.reload();
                    } else {
                        alert('File upload failed');
                    }
                };
                xhr.send(formData);  // Send the file and hidden parameter
            } else {
                alert('Please select a valid PDF file.');
            }
        } else {
            alert('Please select a PDF file first.');
        }
    }
   


    
    
       
    
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.common.taxInvoiceUpload" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED" align="center">${message}</div>

    <center>


        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.taxInvoiceUpload" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <form name="packingList4view" id="packingList4view" method="post" action="<%=cntxpath%>/warrantyAction.do?option=viewTaxInvoiceUpload&flag=check" onsubmit="return false" enctype="multipart/form-data">
                               
                                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
                                    <tbody >
                                        <tr bgcolor="#FFFFFF">
                                            <td style="padding-left:5px;width:5% " class="headingSpas" nowrap align="right"><span class="formLabel"><bean:message key="label.common.packingNo" /></span></td>
                                            <td align="left">
                                                <input name="packingNo" type="text" id="Packing No" value="${warrantyForm.packingNo}" />
                                            </td>
                                            <td  style="padding-left:5px;width:5% "  class="headingSpas" nowrap align="right">
                                                <c:if test="${range eq '1'}">
                                                    <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                                </c:if>
                                                <c:if test="${range ne '1' }">
                                                    <input type="checkbox" name="range" value="1"  id="Range" >
                                                </c:if>
                                                <bean:message key="label.common.packingDate"/></td>
                                            <td  align="left">
                                                <input name="fromDate" type="text" class="datepicker" id="From Date" style="width:60px"  value="${warrantyForm.fromDate}" onkeydown="return false;"/>
                                            </td>
                                            <td  class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                                            <td  align="left">
                                                <input name="toDate" type="text" class="datepicker" id="To Date" value="${warrantyForm.toDate}" style="width:60px" onkeydown="return false;"/>
                                            </td>
                                            <td  style="padding-left:5px;width:5% "  class="headingSpas" nowrap align="center"><bean:message key="label.common.status"/></td>
                                            <td align="left" >
                                                <select name="taxInvoiceStatus" id="Packing Status" class="selectnewbox" class="txtGeneralNW">
                                                    <option value="">All</option>
                                                    <option value="PENDING">PENDING </option>
                                                    <option value="SUBMITTED">SUBMITTED</option>
                                                    <option value="RECEIVED">RECEIVED</option>
                                                    <option value="NOT APPROVED">NOT APPROVED</option>
                                                    <option value="PARTIALLY RECEIVED">PARTIALLY RECEIVED</option>
                                                </select>
                                            </td>
                                             <%if (userFunctionalities.contains("101")) {%>
                                            <%} else{%> 
                                            <td width="15%">
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
                                            <td style="padding-left:5px" align="left"  >
                                                <input name="go" type="submit" id="go" class="headingSpas1" value="search" onclick="submitForm()"/>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </td>
                    </tr>
                    <c:if test="${!empty packingList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="warrantyAction.do" maxIndexPages="10" maxPageItems="15">
                                    <pg:param name="option" value="viewTaxInvoiceUpload"/>
                                    <pg:param name="taxInvoiceStatus"  value='${warrantyForm.taxInvoiceStatus}'/>
                                    <pg:param name="packingNo"  value='${warrantyForm.packingNo}'/>
                                    <pg:param name="fromDate"  value='${warrantyForm.fromDate}'/>
                                    <pg:param name="toDate"  value='${warrantyForm.toDate}'/>
                                    <pg:param name="dealer_code"  value='${warrantyForm.dealer_code}'/>
                                    <pg:param name="flag"  value='check'/>
                                    <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="1%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.packingNo" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.consignmentNo" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.uploadTaxInvoice" />
                                            </td>
                                            <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.uploadTaxInvoiceStatus" />
                                            </td>

                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <c:set var="packingGenDateCheck" value="<%=df1.format(df.parse(PageTemplate.packingGenDateCheck))%>"></c:set>
                                        <logic:iterate id="packingList" name="packingList">
                                            <pg:item>
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <fmt:parseDate value="${packingList.packingDate}" pattern="dd/MM/yyyy" var="myDate"/>
                                                    <fmt:formatDate value="${myDate}" var="startFormat" pattern="yyyyMMdd"/>
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <a href="<%=cntxpath%>/warrantyAction.do?option=packingDetails4View&packingNo=${packingList.packingNo}" >
                                                            <span >${packingList.packingNo}</span></a></td>
                                                    <c:if test="${fn:length(packingList.courierNo) gt 1}">
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${packingList.courierNo} [${packingList.courierName}]</span></td>
                                                    </c:if>
                                                    <c:if test="${fn:length(packingList.courierNo) eq 1}">
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >-</span></td>
                                                    </c:if>
                                                    <td align="center" bgcolor="#FFFFFF" style="padding-left:3px; FONT-SIZE: 11px;">
													    <span style="display: flex; align-items: center;">
													        
													         <input type="file"  id="uploadTaxInvoice_${sno}"  onchange="validatePDF(this,'${sno}');" style="margin-right: 10px; height: 18px; vertical-align: middle;" >
													       
													   
													        <a href="javascript:void(0)" id="uploadLink_${sno}" style="display: none;" >
													            <img src="${pageContext.request.contextPath}/images/upload.png" height="18" width="18" onclick="triggerPDFUpload('${packingList.packingNo}','${sno}');" title="Click to upload PDF" />
													        </a>
													       
													    </span>
													</td>
													
													<td align="left" bgcolor="#FFFFFF"
														style="padding-left: 3px; FONT-SIZE: 11px;">
														
														<c:choose>
															<c:when test="${packingList.taxInvoiceStatus == 'PARTIALLY RECEIVED'}">
																<a href="warrantyAction.do?option=taxInvoiceAcknowledgeView&packingNo=${packingList.packingNo}" style="color: blue;"> <span>${packingList.taxInvoiceStatus}</span>
																</a>
															</c:when>
															<c:otherwise>
																<span>${packingList.taxInvoiceStatus}</span>
															</c:otherwise>
														</c:choose>
													</td>

												</tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr>
                                            <td colspan="9" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                <table  align="center" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td>
                                                            <pg:index>
                                                                <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle" style="float:left;"/></a></pg:first>&nbsp;
                                                                <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle" style="float:left;"/></a></pg:prev>&nbsp;
                                                                <pg:pages>&nbsp;<a style="color: blue!important;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                                <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle" style="float:right"/></a></pg:last>
                                                                <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle" style="float:right;"/></a></pg:next>&nbsp;
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
                    <c:if test="${empty packingList}">
                        <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                            <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>
    </center>
</div>
<script>
    window.onload =function(){
        var status = '${warrantyForm.packingStatus}';
        if(status != ""){
            document.getElementById("Packing Status").value=status;
        }
    }
</script>