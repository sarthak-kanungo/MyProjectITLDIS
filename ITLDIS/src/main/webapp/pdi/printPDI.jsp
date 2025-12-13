<%-- 
    Document   : printPDI
    Created on : Nov 20, 2014, 11:34:20 AM
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
           <%-- <h1><bean:message key="label.common.PDIForChassisno" />  ${pdiform.vinNo}</h1>--%>
           <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="FONT-SIZE: 11px;">
                <tr bgcolor="#eeeeee" height="20" >
                    <td  class="headingSpas" align="left" width="90%">
                        <label>
                            <B><bean:message key="label.common.vehicleinfo"/></B>
                        </label>
                    </td>
                    <td align="right"><a href="#"><img src='<%=cntxpath%>/image/print-icon.png' alt='Get Suggestions' name="img1" border='0' title="print"  onclick="printview(this);"/></a></td>
                </tr>
                <tr>
                    <td colspan="2">
                            <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1"  style="FONT-SIZE: 11px;">
                                <%--<tbody>--%>
                                    <tr>
                                        <td width="15%" align="right"><span class="headingSpas">
                                                <label><B><bean:message key="label.common.Prodcat" />: </B></label></span>
                                        </td>
                                         <td width="15%" align="left"  ><span class="headingSpas">Tractor</span>
                                            <%--<select name="productCategory"  id="productCategory" disabled="disabled">
                                                <option value='Tractor' title='Tractor' selected>Tractor</option>
                                            </select>--%>
                                        </td>
                                        <td width="20%" align="right" ><span class="headingSpas">
                                                <label><B><bean:message key="label.common.Modelfamily" />: </B></label>
                                            </span>
                                        </td>
                                        <td width="15%" align="left" ><span class="headingSpas">${pdiform.modelFamily}</span>
                                            <%--<input name="modelFamily" type="text" id="Model Family"  maxlength="" value="${pdiform.modelFamily}" readonly="true" onkeydown="return false;"/>--%>
                                        </td>
                                        <td width="15%" align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.Modelcode" />: </B></label>
                                        </span></td>
                                        <td width="15%" align="left" ><span class="headingSpas">${pdiform.modelCode}</span>
                                            <input name="jobType" type="hidden" value="${pdiform.jobType }">
                                            <input name="modelFamily" type="hidden" value="${pdiform.modelFamily }">
                                           <%-- <input name="modelCode" type="text" id="Model Code"  maxlength="" value="${pdiform.modelCode}" readonly="true" onkeydown="return false;"/>--%>
                                        </td>
                                </tr>
                                <tr>
                                    <td  align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.Chassisno" />: </B></label></span>
                                    </td>
                                    <td  align="left"  ><span class="headingSpas">${pdiform.vinNo}</span>
                                        <%--<input name="vinNo" type="text" id="VIN No." maxlength="50" value="${pdiform.vinNo}" readonly="true" onkeydown="return false;"/>--%>
                                    </td>
                                   <td align="right" style=""><span class="headingSpas">
                                                <label><B><bean:message key="label.common.familydesc" />: </B></label>
                                            </span>
                                   </td>
                                    <td align="left"><span class="headingSpas">${pdiform.modelFamilyDesc}</span>
                                        <%--<input name="modelFamilyDesc" type="text" id="Model Family Desc"  maxlength="" value="${pdiform.modelFamilyDesc}" readonly="true" onkeydown="return false;"/>--%>
                                    </td>
                                    <td align="right" style="" ><span class="headingSpas">
                                                <label><B><bean:message key="label.common.codedesc" />: </B></label>
                                            </span></td>
                                    <td align="left"><span class="headingSpas">${pdiform.modelCodeDesc}</span>
                                        <%--<input name="modelCodeDesc" type="text" id="Model Code Desc." maxlength="" value="${pdiform.modelCodeDesc}" readonly="true" onkeydown="return false;"/>--%>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.InvoiceDate" />: </B></label>
                                        </span>
                                    </td>
                                    <td align="left" style="white-space: nowrap"><span class="headingSpas">${pdiform.invoiceDate}</span>
                                    </td>
                                    <td align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.tractorRecdate" />: </B></label>
                                        </span></td>
                                    <td align="left" style="white-space: nowrap"><span class="headingSpas">${pdiform.jobCardDate}</span>
                                    </td>
                                    <td align="right" style="" ><span class="headingSpas">
                                            <label><B> <bean:message key="label.common.pdiNo" />: </B></label>
                                        </span></td>
                                    <td align="left" id="saledatetd"><span class="headingSpas">${pdiform.pdiNo}</span>
                                    </td>
                                </tr>
                                <tr>
                                     <td align="right" style="" ><span class="headingSpas">
                                            <label><B><bean:message key="label.common.InvoiceNo" />: </B></label>
                                        </span>
                                    </td>
                                    <td align="left"><span class="headingSpas">${pdiform.invoiceNo}</span>
                                         <%--<input name="invoiceNo" type="text" id="Invoice No"  maxlength="50" value="${pdiform.invoiceNo}"  />--%>
                                    </td>
                                    <td align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.engineno" />: </B></label>
                                        </span></td>
                                    <td align="left" ><span class="headingSpas">${pdiform.engineNumber}</span>
                                        <%--<input name="engineNumber" type="text" id="Engine Number" value="${pdiform.engineNumber}" readonly="true" onkeydown="return false;"/>--%>
                                    </td>
                                    <td align="right" style="" ><span class="headingSpas">
                                            <label><B> <bean:message key="label.common.pDIDonedate" />: </B></label>
                                        </span></td>
                                    <td align="left" id="saledatetd"><span class="headingSpas">${pdiform.pdiDate}</span>
                                    </td>

                                </tr>
                                <tr>
                                    <td align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.dealershipName" />: </B></label>   <%--label.common.dealerName--%>
                                        </span></td>
                                        <td align="left" colspan="3" ><span class="headingSpas">${pdiform.dealerCode} [ ${pdiform.dealerName} ] [ ${pdiform.locationName} ]</span>
                                    </td>
                                  <%--  <td align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.dealerCode" />: </B></label>
                                        </span></td>
                                    <td align="left" ><span class="headingSpas">${pdiform.dealerCode}</span>
                                    </td>--%>
                                    <td align="right" style="" ><span class="headingSpas">
                                            <label><B><bean:message key="label.pdi.pdiDoneBy" />: </B></label>   <%--label.common.location--%>
                                        </span>
                                    </td>
                                    <td align="left">
                                       <%-- <span class="headingSpas">${pdiform.locationName}</span>--%>
                                        <span class="headingSpas">${pdiform.repname} </span>
                                   </td>
                                </tr>
                            </table>
                    </td>
                </tr>
            <%--</table>--%>
            <tr><td colspan="2">
<%--<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
                                         <tr>
                            <td id="travelcardData">--%>
                                <table  width=100%  border=1 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#ffffff style="FONT-SIZE: 11px;">
                                    <tbody id="tractorinfo">
                                        <tr bgcolor="#eeeeee" height="20"  class="headingSpas">
                                            <td width="10%" align="center" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.sno" /></B></td>
                                            <td width="30%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partname" /></B></td>
                                            <td width="30%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partvendorname" /></B></td>
                                            <td width="20%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partSerialNo" /> </B></td>
                                        </tr>
                                        <c:set var="srNo" value="1"/>
                                        <c:set var="tsrNo" value="5"/>
                                        <c:set var="totallist" value="0"/>
                                        <c:forEach items="${travelcardPartList}" var="travelcardPartList">
                                            <c:if test="${!empty travelcardPartList}">
                                                <tr bgcolor="#FFFFFF" height="20">
                                            <input type="hidden" name="actiontakens"  value="${srNo}"/>
                                            <td align="center">${srNo}</td>
                                            <td align="left" nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" ><span>${travelcardPartList.contentDesc}</span>
                                              <%--  <input name="partNo${srNo}" type="text" id="Part No${srNo}" style="width:300px ; background-color:#E6E4E4;" title="${travelcardPartList.contentDesc}" value="${travelcardPartList.contentDesc}" readonly="true" maxlength="20"/>--%>
                                            </td>

                                            <td align="left" nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" ><span>${travelcardPartList.observation}</span>
                                               <%-- <input name="partVendorName${srNo}" type="text" id="PartVendor Name${srNo}" style="width:300px !important" title="" value="${travelcardPartList.observation}" maxlength="40"/>--%>

                                            </td>

                                            <td align="left"  nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px"><span>${travelcardPartList.serialno}</span>
                                               <%-- <input name="partSerialNo${srNo}" type="text" id="PartSerial No${srNo}" style="width:250px !important" title="" value="${travelcardPartList.serialno}" maxlength="40"/>--%>
                                            </td>
                                            </tr>
                                         <c:set var="srNo" value="${srNo+1}"/>
                                         <c:set var="totallist" value="${totallist+1}"/>
                            </c:if>

                        </c:forEach>
</tbody></table>


            <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" class="LiteBorder" >
                <tr  height="20">
                    <td align="left" class="headingSpas">
                        <B><bean:message key="label.common.pdilabel4checklist" /></B>
                    </td>
                </tr>
                <tr  bgcolor="#eeeeee" height="7">
                </tr>
               <%-- <div class="message1" id="msg_saveFAILED"></div>--%>
                <tr height=25 bgcolor="#FFFFFF">
                    <td align= center class="headingSpas">
                        <table width=100%  border=1 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#ffffff style="FONT-SIZE: 11px;">
                            <tr  bgcolor="#eeeeee" height="20">
                                <td nowrap align="center" width="7%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.sno" /></label></td>
                                <td nowrap align="left" width="33%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.activity" /></label></td>
                                <td nowrap align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.ok" /> </label></td>
                                <td nowrap align="left" width="25%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.actiontaken" /></label></td>
                                <td nowrap align="left" width="25%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.remarks" /></label></td>
                                <!--<td width="13%"><label>Status</label></td>-->
                            </tr>

                            <c:set var="count" value="1"/>
                            <c:forEach items="${pdiform.dataMap}" var="contentList">
                               <c:choose>
                                    <c:when test="${empty contentList.value}">
                                        <tr bgcolor="#FFFFFF">
                                            <td width="3%" align="center">&nbsp;</td>
                                            <td ><label><strong>${contentList.key.contentDesc}</strong></label><input type="hidden" name="contentId" value="${contentList.key.contentId}"/></td>
                                            <td align="center">${contentList.key.contentId}
                                                <select name="${contentList.key.contentId}checkpoints" style="width:75px !important ">
                                                    <option value="OK" selected>OK</option>
                                                    <option value="NOT OK">NOT OK</option>
                                                </select>
                                            </td>
                                            <td align="left" style="padding-bottom:3px;padding-top:2px;padding-left:3px;padding-right:3px;"><input type="text" name="${contentList.key.contentId}actionTaken" id="Action Taken" maxlength="100" style="width:240px !important "/></td>
                                            <td align="left" style="padding-bottom:3px;padding-top:2px;padding-left:3px;padding-right:3px;"><input type="text" name="${contentList.key.contentId}observations" id="Observation" maxlength="100" style="width:240px !important "/></td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr bgcolor="#FFFFFF" >
                                            <td height="25" colspan="5">
                                                <div align="left">
                                                    <label><strong> &nbsp;${contentList.key.contentDesc}</strong></label>
                                                </div><input type="hidden" name="contentId" value="${contentList.key.contentId}"/></td>
                                        </tr>
                                        <%-- <c:set var="count" value="1"/>--%>
                                        <c:set var="i" value="1"/>
                                        <c:forEach items="${contentList.value}" var="subCList">
                                            <tr bgcolor="#FFFFFF">
                                                <td width="3%" align="center">${i}</td>
                                                <td align="left" style="padding-left:3px;padding-right:3px;">${subCList.subContentDesc}<input type="hidden" name="${contentList.key.contentId}SubContent" value="${subCList.subContentId}"/></td>
                                                <td align="center"><span class="headingSpas">${subCList.textBoxOption}</span>
                                                </td>
                                                <td align="left" style="padding-bottom:3px;padding-top:2px;padding-left:3px;padding-right:3px;">
                                                    <span class="headingSpas">${subCList.actiontaken}</span>
                                                    <%--<input type="text" name="${contentList.key.contentId}actionTaken" value="${subCList.actiontaken}" id="Action Taken${count}" maxlength="100"  style="width:240px !important " readonly="true" onkeydown="return false;"/>--%>
                                                </td>
                                                <td align="left" style="padding-bottom:3px;padding-top:2px;padding-left:3px;padding-right:3px;">
                                                    <span class="headingSpas">${subCList.observations}</span>
                                                    <%--<input type="text" name="${contentList.key.contentId}observations" value="${subCList.observations}" id="Observation${count}" maxlength="100"  style="width:240px !important " readonly="true" onkeydown="return false;"/>--%>
                                                </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}"/>
                                            <c:set var="count" value="${count+1}"/>
                                        </c:forEach>

                                    </c:otherwise>

                                </c:choose>

                            </c:forEach>
                            <tr bgcolor="#FFFFFF" height="40">
                                                 <td align="left" >
                                                  <span class="headingSpas">
                                                   <label><B><bean:message key="label.common.anyobservation" /></B></label>
                                                 </span></td>
                                               <td colspan="4" align="left"  width="80%"><span class="headingSpas">${pdiform.remark}</span>
                                                   <%--<textarea name="remark" rows="4" id="Any Observation" onblur="if(!CommentsMaxLength(this, 450)){this.value=this.value.substring(0, 450);}">${serviceform.compVerbatim[count]}${pdiform.remark}</textarea>--%>
                                               </td>
                           </tr>
                        </table>
                    </td>
                </tr>
                </table>

                    </td>
                </tr>
            </table>
        </div>
    </div>
<div align="left" class="footer_print" style="background-color: #ffffff">
    <p style="padding-left: 10px;">If some defect is observed during PDI, the same should be communicated to area office within seven days. Warranty claim of such tractor would not be considered if the PDI form has not been sent to the area office within the prescribed time.</p>
    <p class="textftl"><b>Checked By Name & Signature <br>
              ${pdiform.repname} 
         </b></p>
   
    <p class="textftr"><b>Dealer representative Name & <br>Signature with Stamp</b></p>
    <p class="textftd"><b>Date</b></p>
</div>
</center>
