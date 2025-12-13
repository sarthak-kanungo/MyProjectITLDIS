<%-- 
    Document   : acknowPacking
    Created on : Dec 10, 2014, 12:00:46 PM
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

function checkQty(row){
      

            if(!document.getElementById("ReceivedQty"+row).value.match(/^[0-9]+$/)){
                document.getElementById("ReceivedQty"+row).value='';
                document.getElementById("ReceivedQty" + row).focus();
                alert("Received Qty "+numeric_msg);
                return false;
            }
              if(eval(document.getElementById("ReceivedQty"+row).value) > eval(document.getElementById("DispatchQty"+row).value)){
                alert("Received Qty "+ maxLength_validation_msg +"Dispatch Qty");
                document.getElementById("ReceivedQty"+row).focus();
                document.getElementById("ReceivedQty"+row).value="";
                return false;
            }
    
    }


function submitForm() {
    let selectedClaims = [];

    // Gather all selected checkboxes
    document.querySelectorAll('.claimCheckbox:checked').forEach((checkbox) => {
    	//selectedClaims.push("'" + checkbox.value + "'");
    	selectedClaims.push(checkbox.value);
    });
    
   // alert(selectedClaims);

    const checkboxes = document.querySelectorAll('.claimCheckbox');
    const checkedCheckboxes = document.querySelectorAll('.claimCheckbox:checked');  // Fix variable reference

    let taxInvoiceStatus = '';

    // Determine taxInvoiceStatus based on checkbox selection
    if (checkedCheckboxes.length === 0) {
        taxInvoiceStatus = 'NOT APPROVED';
    } else if (checkedCheckboxes.length === checkboxes.length) {
        taxInvoiceStatus = 'RECEIVED';
    } else {
        taxInvoiceStatus = 'PARTIALLY RECEIVED';
    }

    // If no checkboxes are selected, ask for confirmation
    if (checkedCheckboxes.length === 0) {
        const userConfirmation = confirm('You have not selected any claim. If you submit, the Tax Invoice Status will be updated to "Not Approved". Do you want to proceed?');
        if (!userConfirmation) {
            return false;  // Cancel submission if user clicks "Cancel"
        }
    }

    // Set the selected claims and tax invoice status in hidden inputs
    document.getElementById('selectedClaimsInput').value = selectedClaims;
    document.getElementById('taxInvoiceStatus').value = taxInvoiceStatus;

    // Submit the form
    document.getElementById("myForm").submit();
}

function downloadTaxInvoice(packingNo) {
  
	
             var formData = new FormData();
             formData.append('packingNo', packingNo);   
             
             var xhr = new XMLHttpRequest();
             xhr.open("POST", "<%=cntxpath%>/warrantyAction.do?option=downloadTaxInvoice", true);
             xhr.onload = function() {
                 if (xhr.status === 200) {
                     alert('Tax Invoice Downloaded successfully');
                     window.location.reload();
                 } else {
                     alert('Error in Downlaoding Tax Invoice');
                 }
             };
             xhr.send(formData); 
      
}

    
    
   function caMaxLength(ca, MaxLen)
    {
        return (ca.value.length <= MaxLen);
    }
   
   function performAction() {
	    // Get the main action checkbox
	    const mainCheckbox = document.querySelector('input[name="actionCheckbox"]');

	    // Get all checkboxes with the class 'claimCheckbox'
	    const claimCheckboxes = document.querySelectorAll('.claimCheckbox');

	    // Set each claim checkbox to match the main checkbox's checked state
	    claimCheckboxes.forEach(checkbox => {
	        checkbox.checked = mainCheckbox.checked;
	    });
	}


</script>

       <div class="contentmain1">
           <div class="breadcrumb_container" >
               <ul class="hText">
                   <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.common.warranty" /></a></li>
                   <li class="breadcrumb_link" ><a href='<%=cntxpath%>/warrantyAction.do?option=taxInvoiceAcknowledgeViewList'><bean:message key="label.common.taxInvoiceAcknowledgeView" /></a></li>
                   <li class="breadcrumb_link" ><label ><bean:message key="label.common.taxInvoiceAcknowledge" /> </label></li>
               </ul>
           </div>
           
           <div class="message" id="msg_saveFAILED">
           
            <c:if test="${not empty show_message}">
            ${show_message}
            </c:if>
           
           
           </div>
           <center>
               <div class="content_right1">
                   <div class="con_slidediv1" style="position: relative;width: 100%">
                       <h1><bean:message key="label.common.taxInvoiceAcknowledge" /> </h1>
                       <form action="<%=cntxpath%>/warrantyAction.do" method="post" id="myForm" onsubmit="return false;" >
                           <input type="hidden" name="option" value="taxInvoiceAcknowledgeSubmit" >
                           <input type="hidden" name="upperLink" value="<a href ='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a>@@<a href = '<%=cntxpath%>/warrantyAction.do?option=pendingForAcknow'><bean:message key="label.common.taxInvoiceAcknowledge" /></a>@@<bean:message key="label.common.taxInvoiceAcknowledge" />"/>
                        
                           <c:if test="${!empty dispatchList}">
                               <table id="" width="100%" border="0" align="center" cellpadding="3" cellspacing="3"  >
                                   <tr bgcolor="#FFFFFF" class="headingSpas" height="20" style="padding-top: 20px">
                                       <td align="right"><B><bean:message key="label.common.packingNo" /> : </B></td>
                                       <td align="left">${warrantyForm.packingNo}
                                            <input type="hidden" name="packingNo" value="${warrantyForm.packingNo}">
                                       </td>
                                       <td align="right"><B> <bean:message key="label.common.packingDate" />  : </B></td>
                                       <td align="left">
                                           ${warrantyForm.packingDate}
                                       </td>
                                   </tr>
                                   <tr bgcolor="#FFFFFF" class="headingSpas" height="20">
                                       <td align="right"><B> <bean:message key="label.common.dispatchedThrough" />  : </B></td>
                                       <td align="left">
                                           ${warrantyForm.dispatchBy}
                                       </td>

                                       <td align="right"><B> <bean:message key="label.common.courierName" />  : </B></td>
                                       <td align="left">
                                           ${warrantyForm.courierNo} [ ${warrantyForm.courierName} ]
                                       </td>
                                   </tr>
                                   <tr bgcolor="#FFFFFF" class="headingSpas" height="20">
                                       <td align="right"><B> <bean:message key="label.common.dispatchedFor" />  : </B></td>
                                       <td align="left">
                                           ${warrantyForm.dispatchFor}
                                       </td>
                                       <td align="right"><B> <bean:message key="label.common.dispatchedDate" />  : </B></td>
                                       <td align="left">
                                           ${warrantyForm.dispatchDate}
                                       </td>
                                   </tr>
                                   <tr bgcolor="#FFFFFF" class="headingSpas" height="20">
                                       <td align="right"><B> <bean:message key="label.common.storeNo" />  : </B></td>
                                       <td align="left" colspan="3">
                                           ${warrantyForm.storeNoOfPackages}
                                       </td>

                                   </tr>
                                   <tr bgcolor="#FFFFFF" class="headingSpas" height="10">
                                   </tr>
                               </table>


                               <table id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                   <tr  bgcolor="#eeeeee" height="20">
                                       <td align="center"  class="headingSpas" nowrap style="padding-left:2px;"><b><bean:message key="label.warranty.S.No" /></b></td>
                                       <td align="left"   class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.warrantyClaimNo" /></b></td>
                                       <td align="left" class="headingSpas" nowrap style="padding-left:5px;" ><B><bean:message key="label.common.InvoiceNo" /></B></td>
                                       <td align="left" class="headingSpas" nowrap style="padding-left:5px;" ><B><bean:message key="label.common.InvoiceDate" /></B></td>
                                       <td align="left" class="headingSpas" nowrap style="padding-left:5px;" ><B><bean:message key="label.common.action" /></B>
                                       <input type="checkbox" name="actionCheckbox" onclick="performAction()" /></td>
                                   </tr>


                                   <c:set var="sno" value="1"></c:set>
                                   <logic:iterate id="claimInvoiceList" name="claimInvoiceList">
                                       <tr id ="${sno}" height="20">
                                           <td align="center" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:2px; padding-right: 5px">${sno}</td>
                                           <td align="left" bgcolor="#FFFFFF" title="${claimInvoiceList.warrantyClaimNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                               <span id ="compType${sno}" >
                                                   <a href="warrantyAction.do?option=viewWarrantyClaimDetail&warrntyClaimNo=${claimInvoiceList.warrantyClaimNo}&flag=penAck">${claimInvoiceList.warrantyClaimNo} </a>
                                               </span>
                                                   <input type="hidden" name="warClaimNoArr" id="WarClaimNo" class="warText" value="${claimInvoiceList.warrantyClaimNo}">
                                           </td>
                                           <td align="left" bgcolor="#FFFFFF" title="  ${claimInvoiceList.invNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                               <span id ="compType${sno}" >
                                                   ${claimInvoiceList.invNo}   <input type="hidden" name="partNoArr" id="PartNo" class="warText" value="${claimInvoiceList.invNo}">
                                                   
                                               </span>
                                           </td>
                                           <td align="left" bgcolor="#FFFFFF" title="  ${claimInvoiceList.invDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                               <span id ="compType${sno}" >
                                                   ${claimInvoiceList.invDate}
                                               </span>
                                           </td>
                                           
                                           <td align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                           
                                            <span id="compType${sno}">
                                             <input type="checkbox" class="claimCheckbox" name="selectedClaims" value="${claimInvoiceList.warrantyClaimNo}"
                                               <c:choose>
                                                 <c:when test="${claimInvoiceList.taxInvoiceStatus == 'RECEIVED'}"> checked="checked"
                                                     </c:when>
                                                </c:choose>
                                              />
                                             </span>
                                            </td>
                                         </tr>

                                       <c:set var="sno" value="${sno + 1 }" />
                                   </logic:iterate>
                                   <input type="hidden" name="partCount" id="PartCount" value="${sno-1}">

                                 
                                <!--    <tr height="50">
                                       <td colspan="8" align="center" bgcolor="#FFFFFF" class="textPaging" > -->
                                       <input type="hidden" id="selectedClaimsInput" name="checkedClaims" value=""/>
                                       <input type="hidden" id="taxInvoiceStatus" name="taxInvoiceStatus" value=""/>
                                       <input type="hidden" id="packingNo" name="packingNo" value="${warrantyForm.packingNo}"/>
                                          
                                     <!--   </td>
                                   </tr> -->
                               </c:if>

                               <c:if test="${empty claimInvoiceList}">
                                   <tr bgcolor="#FFFFFF">
                                      
                                       <td valign="top" align="center" colspan="8" class="headingSpas" style="padding-top:10px;color: red">
                                      
                                      <bean:message key="label.common.noRecordFound" />

                                       </td>
                                   </tr>
                               </c:if>


                           </table>
     
     
                           
                       </form>
                       <%-- </div>--%>
                   </div>


               </div>
           </center>
       </div>



