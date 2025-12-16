<%-- 
    Document   : printConsignmentDetails
    Created on : Dec 10, 2014, 4:44:32 PM
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
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script type="text/javascript" language="javascript">
  function printview(obj){
        obj.style.display='none';
        window.print();
        window.onfocus=function(){  obj.style.display='block';}
    }

</script>

<center style="background-color: #ffffff;FONT-SIZE: 11px;">
    <div class="content_right1">
        <div class="con_slidediv2" style="position: relative;width: 100%">
           <%-- <h1><bean:message key="label.common.consignmentDetails" /> ${warrantyForm.packingNo}</h1>--%>
           <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="FONT-SIZE: 11px;">
                <tr bgcolor="#eeeeee" height="20" >
                    <%--<td  class="headingSpas" align="left" width="90%">
                        <label>
                            <B><bean:message key="label.common.consignmentDetails"/></B>
                        </label>
                    </td>--%>
                    <td align="right"><a href="#"><img src='<%=cntxpath%>/image/print-icon.png' alt='Get Suggestions' name="img1" border='0' title="print"  onclick="printview(this);"/></a></td>
                </tr>
                <tr>
                    <td >
                        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1"  style="FONT-SIZE: 11px;">
                            <tr>
                                <td  align="right"><span class="headingSpas">
                                        <label><B><bean:message key="label.common.packingNo" />: </B></label></span>
                                </td>
                                <td align="left"  ><span class="headingSpas">${warrantyForm.packingNo}</span>
                                </td>
                                <td align="right" ><span class="headingSpas">
                                        <label><B><bean:message key="label.common.packingDate" />: </B></label>
                                    </span>
                                </td>
                                <td align="left" colspan="3"><span class="headingSpas">${warrantyForm.packingDate}</span>
                                </td>
                            </tr>
                           <%-- <tr>
                                <td  align="right"><span class="headingSpas">
                                        <label><B><bean:message key="label.common.dispatchedFor" />: </B></label></span>
                                </td>
                                <td  align="left"  ><span class="headingSpas">${warrantyForm.dispatchFor}</span>
                                </td>
                                <td align="right" style=""><span class="headingSpas">
                                        <label><B><bean:message key="label.common.dispatchedThrough" />: </B></label>
                                    </span>
                                </td>
                                <td align="left"><span class="headingSpas">${warrantyForm.dispatchBy}</span>
                                </td>
                                <td align="right" style="" ><span class="headingSpas">
                                        <label><B><bean:message key="label.common.dispatchedDate" />: </B></label>
                                    </span></td>
                                <td align="left"><span class="headingSpas">${warrantyForm.dispatchDate}</span>
                                </td>
                            </tr>
                            <tr>
                                <td align="right"><span class="headingSpas">
                                        <label><B><bean:message key="label.common.courierNo" />: </B></label>
                                    </span>
                                </td>
                                <td align="left" style="white-space: nowrap"><span class="headingSpas">${warrantyForm.courierNo}</span>
                                </td>
                                <td align="right"><span class="headingSpas">
                                        <label><B><bean:message key="label.common.courierName" />: </B></label>
                                    </span></td>
                                <td align="left" style="white-space: nowrap"><span class="headingSpas">${warrantyForm.courierName}</span>
                                </td>
                                <td align="right" style="" ><span class="headingSpas">
                                        <label><B> <bean:message key="label.common.courierCopyFile" />: </B></label>
                                    </span></td>
                                <td align="left" id="saledatetd"><span class="headingSpas">${warrantyForm.courierCopyFileName}</span>
                                </td>
                            </tr>
                            <tr>
                                <td  align="right"><span class="headingSpas">
                                        <label><B><bean:message key="label.warranty.receivedBy" />: </B></label></span>
                                </td>
                                <td align="left"  ><span class="headingSpas">${warrantyForm.receivedBy}</span>
                                </td>
                                <td align="right" ><span class="headingSpas">
                                        <label><B><bean:message key="label.warranty.receivedDate" />: </B></label>
                                    </span>
                                </td>
                                <td align="left" colspan="3"><span class="headingSpas">${warrantyForm.receivedDate}</span>
                                </td>
                            </tr>--%>
                        </table>
                    </td>
                </tr>
                <c:if test="${!empty packingList}">
            <tr><td >
            <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" class="LiteBorder" >
                <%--<tr  height="20">
                    <td align="left" class="headingSpas">
                        <B><bean:message key="label.common.packingList" /></B>
                    </td>
                </tr>
                <tr  bgcolor="#eeeeee" height="7">
                </tr>--%>

                <tr height=25 bgcolor="#FFFFFF">
                    <td align= center class="headingSpas">
                        <table width=100%  border=1 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#ffffff style="FONT-SIZE: 11px;">
                            <tr  bgcolor="#eeeeee" height="20">
                               <td   align="center" width="10%" class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.warranty.S.No" /></b></td>
                               <td   align="left" width="15%"  class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.warranty.warrantyClaimNo" /></b></td>
                               <td align="left" width="10%" class="headingSpas"  style="padding-left:5px;" ><B><bean:message key="label.common.partno_small" /></B></td>
                               <td align="left" width="35%" class="headingSpas"  style="padding-left:5px;" ><B><bean:message key="label.common.partdesc_small" /></B></td>
                               <td align="center" width="10%" class="headingSpas"  style="padding-left:5px;"><B><bean:message key="label.warranty.claimQty" />  </B></td>
                               <td align="center" width="10%" class="headingSpas"  style="padding-left:5px;"><B><bean:message key="label.warranty.dispatchQty" />   </B></td>
                               <%--<td align="center" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.saleReturn.ReceivedQty" />   </B></td>--%>
                               <td align="left" width="10%" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.warranty.bixNo" />  </B></td>
                           </tr>
                            <c:set var="srNo" value="1"/>
                            <c:forEach items="${packingList}" var="packingList">
                             <tr height="20">
                                 <td   align="center"  class="headingSpas"  style="padding-left:5px;"><span>${srNo}</span></td>
                               <td   align="left"   class="headingSpas"  style="padding-left:5px;"><span>${packingList.warrantyClaimNo}</span></td>
                               <td align="left" class="headingSpas"  style="padding-left:5px;" ><span>${packingList.partNo}</span></td>
                               <td align="left" class="headingSpas"  style="padding-left:5px;" ><span>${packingList.partDesc}</span></td>
                               <td align="center" class="headingSpas"  style="padding-left:5px;"><span>${packingList.qty}</span></td>
                               <td align="center" class="headingSpas"  style="padding-left:5px;"><span>${packingList.dispatchQty}</span></td>
                               <%--<td align="center" class="headingSpas" nowrap style="padding-left:5px;"><span>${packingList.receivedQty}</span></td>--%>
                               <td align="left" class="headingSpas" nowrap style="padding-left:5px;"><span>${packingList.boxNo}</span></td>
                           </tr>
                            <c:set var="srNo" value="${srNo+1}"/>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
                </table>

                    </td>
                </tr>
               </c:if>
            </table>
        </div>
    </div>
<%--<div align="left" class="footer_print" style="background-color: #ffffff">
    <p style="padding-left: 10px;">If some defect is observed during PDI, the same should be communicated to area office within seven days. Warranty claim of such tractor would not be considered if the PDI form has not been sent to the area office within the prescribed time.</p>
    <p class="textftl"><b>Checked By Name & Signature</b></p>
    <p class="textftr"><b>Dealer representative Name & <br>Signature with Stamp</b></p>
    <p class="textftd"><b>Date</b></p>
</div>--%>
</center>

