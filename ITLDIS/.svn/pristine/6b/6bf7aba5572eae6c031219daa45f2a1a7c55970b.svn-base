
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="beans.WarrantyForm" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<style>
td {
          /*    border: 1px solid black; Sets a 1px solid black border around each cell */
            padding: 8px; /* Adds some padding inside the cells */
        }
</style>
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

        document.getElementById('searchBy').submit();
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
     function printWarInvoice(claimNo)
        {
         alert("Please enter invoice details before print Tax Invoice.");
         var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=printWarrantyInvoice&claimNo="+claimNo.trim();
         window.open(strURL,'taxInv','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
        }
     function warInvoiceUpdate(claimNo)
        {
            var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=warInvoiceUpdate&claimNo="+claimNo.trim();
            window.open(strURL,'taxInv','width=550,height=280, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
        }
     
     function digitalSign(claimNo) {
    	 
    	        document.getElementById("pageOverlay").style.display = "block";

    	        // Prepare FormData for AJAX upload
    	        var formData = new FormData();
    
    	        formData.append('warrantyClaimNo', claimNo);

    	        // Initialize AJAX request
    	        var xhr = new XMLHttpRequest();
    	        xhr.open("POST", "<%=cntxpath%>/warrantyAction.do?option=digitalSignedTaxInvoice", true);
    	        xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest"); // Mark as AJAX request

    	        // Handle response
    	        xhr.onload = function () {
    	            document.getElementById("pageOverlay").style.display = "none"; // Hide overlay

    	            if (xhr.status === 200) {
    	                try {
    	                    var response = JSON.parse(xhr.responseText); // Parse JSON response
    	                    
    	                    if (response.result === "success") {
    	                        alert('Please connect your DSC token to your computer.');

    	                        // Open widget URL in pop-up if available
    	                        if (response.widgetUrl) {
    	                            window.open(response.widgetUrl, 'DigitalSignPopup',
    	                                'toolbar=no,scrollbars=yes,resizable=no,width=800,height=600');
    	                        }

    	                        // reload the page after signing
    	                        // window.location.reload();
    	                    } else {
    	                        alert(response.message || 'File upload failed');
    	                    }
    	                } catch (e) {
    	                    alert('Unexpected response format');
    	                }
    	            } else {
    	                alert('File upload failed with status: ' + xhr.status);
    	            }
    	        };

    	        // Handle errors
    	        xhr.onerror = function () {
    	            document.getElementById("pageOverlay").style.display = "none"; // Hide overlay
    	            alert('An error occurred while uploading the file');
    	        };

    	        // Send FormData
    	        xhr.send(formData);
    	}
     
     function printDigitallySignedInvoice(filename)
     {
    	 var url=contextPath+"/DOCUMENTS/DIGITAL_SIGNATURE/"+filename+".pdf";
         var win=window.open(url, '_blank',"directories=no,height=1000,width=800,menubar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,top=0,location=no");
         win.focus();
     }


  
</script>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.common.warranty" /></a></li>
            <li class="breadcrumb_link"><label><bean:message key="label.common.updateTaxInvoice" /></label></li>
        </ul>
    </div>


    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%;min-height: 400px;">
                <h1><bean:message key="label.common.updateTaxInvoice" /></h1>
                
<!-- Overlay Loader for freeze page while upload file -->
<div id="pageOverlay" style="display: none;
    position: fixed;
    top: 0; left: 0;
    width: 100%; height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 9999;
    text-align: center;
    color: white;
    font-size: 20px;
    padding-top: 20%;
">
    Please wait... Uploading...
</div>
                

                <table width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>

                    <tr height=50 bgcolor="#FFFFFF">
                        <td colspan="11" align= center class="headingSpas">
                            <html:form action="warrantyAction.do?option=viewTaxInvoiceUpdate&flag=check" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" >
                                    <tr bgcolor="#FFFFFF" >

                                        <td    class="headingSpas" nowrap align="right">
                                            <bean:message key="label.common.Chassisno" />

                                        </td>
                                        <td   align="left">
                                            <input name="vinNo" type="text" id="Vin No" value="${nameSrch}" style="width:100px !important " onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <td    class="headingSpas" nowrap align="right">
                                            <bean:message key="label.common.ClaimNo" />

                                        </td>
                                        <td   align="left">
                                            <input name="warrantyClaimNo" type="text" id="nameSrch" value="${nameSrch}" style="width:100px !important " onblur="this.value=TrimAll(this.value)"/>
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
                <div style="overflow: auto">
                    <table align="center" cellspacing=1 cellpadding=1 border=0 width=100% bordercolor="#cccccc" bgcolor="#cccccc" >
                        <tr bgcolor="#eeeeee" height="20">
                            <td   align="center" width="1%" class="headingSpas" nowrap ><b><bean:message key="label.warranty.S.No" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap ><b> <bean:message key="label.common.ClaimNo" /></b></td>
                            <td   align="left" width="6%" class="headingSpas" nowrap ><b> <bean:message key="label.warranty.viewTaxInvoice" /></b></td>
                            <td   align="left" width="6%" class="headingSpas" nowrap ><b> <bean:message key="label.warranty.updateInvoiceNo" /></b></td>
                            <td   align="left" width="12%" class="headingSpas" nowrap ><b> <bean:message key="label.warranty.taxInvoiceNo" /></b></td>
                            <td   align="left" width="8%" class="headingSpas" nowrap ><b><bean:message key="label.warranty.taxInvoiceDate" /></b></td>
                            <td   align="left" width="8%" class="headingSpas" nowrap ><b><bean:message key="label.common.digitalSignature" /></b></td>
                        </tr>
                        <c:if test="${!empty dataList}">
                            <pg:pager url="warrantyAction.do" maxIndexPages="10" maxPageItems="10">
                                <pg:param name="option" value="viewTaxInvoiceUpdate"/>
                                <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                <pg:param name="claimStatus"  value='${warrantyForm.claimStatus}'/>
                                <pg:param name="fromDate"  value='<%=request.getParameter("fromDate")%>'/>
                                <pg:param name="toDate"  value='<%=request.getParameter("toDate")%>'/>
                                <pg:param name="flag"  value='check'/>
                                <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="dataList" name="dataList">
                                    <pg:item>
                                        <tr id ="${sno}" height="20">
                                            <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:3px; padding-right: 3px">${sno}</td>
                                            <td align="left" bgcolor="#FFFFFF" title="  ${dataList.warrantyClaimNo}" width="150" class="headingSpas" style="padding-left:5px; ">
                                                <span id ="compType${sno}" >
                                                    <a href="warrantyAction.do?option=viewWarrantyClaimDetail&warrntyClaimNo=${dataList.warrantyClaimNo}&flag=view">  ${dataList.warrantyClaimNo} </a>
                                                </span>
                                            </td>
                                            <td align="center" bgcolor="#FFFFFF" title="  ${dataList.warrantyClaimNo}" width="150" class="headingSpas" style="padding-left:5px; ">
                                                <span id ="compType${sno}" >
                                                    <c:if test="${(dataList.claimStatus eq 'APPROVED' or dataList.claimStatus eq 'PARTIAL APPROVED') and dataList.digitalSignStatus ne 'SIGNED'}">
                                                        <a onclick="printWarInvoice('${dataList.warrantyClaimNo}');" href="javascript:void(0);" ><img src="${pageContext.request.contextPath}/images/taxInvoice.png" height="15px" width="15px"  title="Warranty Claim Tax Invoice"/>
                                                        </a>
                                                    </c:if>
                                                  
                                                     <c:if test="${(dataList.claimStatus eq 'APPROVED' or dataList.claimStatus eq 'PARTIAL APPROVED') and dataList.digitalSignStatus eq 'SIGNED'}">
                                                       <a onclick="printDigitallySignedInvoice('${dataList.uuid}');" href="javascript:void(0);" ><img src="${pageContext.request.contextPath}/images/DigitalSignTaxInvoice.png" height="18px" width="18px"  title="Digitally Signed Tax Invoice"/>
                                                        </a>
                                                    </c:if>
                                                    
                                                </span>
                                            </td>
                                             <td align="center" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; ">
                                                <span id ="compType${sno}" >
                                                    <c:if test="${dataList.claimStatus eq 'APPROVED' or dataList.claimStatus eq 'PARTIAL APPROVED'}">
                                                       <a onclick="warInvoiceUpdate('${dataList.warrantyClaimNo}');" href="javascript:void(0);" ><img src="${pageContext.request.contextPath}/images/invoiceEdit.jpg" height="15px" width="15px"   title="Warranty Invoice Update"/>
                                                        </a>
                                                    </c:if>

                                                </span>
                                            </td>
                                            <td align="center" bgcolor="#FFFFFF" title="${dataList.claimDate}"  class="headingSpas" style="padding-left:5px; ">
                                                <span id ="compType${sno}" >
                                                   ${dataList.taxInvoiceNo}
                                                </span>
                                            </td>
                                            <td align="center" bgcolor="#FFFFFF" title="${dataList.claimDate}"  class="headingSpas" style="padding-left:5px; ">
                                                <span id ="compType${sno}" >
                                                    ${dataList.taxInvoiceDate}
                                                </span>
                                            </td>
                                            <td align="center" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; ">
                                                <span id ="compType${sno}" >
                                                   <c:if test="${(dataList.claimStatus eq 'APPROVED' or dataList.claimStatus eq 'PARTIAL APPROVED') and dataList.digitalSignStatus ne 'SIGNED' and dataList.taxInvoiceNo ne null and not empty dataList.taxInvoiceNo}">

                                                       <a onclick="digitalSign('${dataList.warrantyClaimNo}');" href="javascript:void(0);" ><img src="${pageContext.request.contextPath}/images/DigitalSignature.jpg" height="15px" width="15px"   title="Digital Signature"/>
                                                        </a>
                                                    </c:if>

                                                </span>
                                            </td>
                                        </tr>
                                    </pg:item>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                                <tr>

                                    <%if (userFunctionalities.contains("704")) {%>
                                    <td colspan="11" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                        <%} else {%>

                                    <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                                <td valign="top" align="center" colspan="11" class="headingSpas" style="padding-top:10px;color: red">
                                    <%} else {%>

                                <td valign="top" align="center" colspan="10" class="headingSpas" style="padding-top:10px;color: red">
                                    <%}%>

                                    Tax Invoice Not Found.!
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </div>
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
