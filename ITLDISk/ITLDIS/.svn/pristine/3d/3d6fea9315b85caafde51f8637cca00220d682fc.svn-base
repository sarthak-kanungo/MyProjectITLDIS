
<!DOCTYPE html>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>


<%
            response.setHeader("Content-Disposition", "attachment; filename=warrantyClaimDetail.xls");
            //Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>

<center>
    <div class="content_right1">
        <div class="con_slidediv1" style="position: relative;width: 100%">
            <h2><bean:message key="label.warranty.viewWarrantyClaim" /> </h2>


            <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" >
                <input type="hidden" name="vinid" value="${warrantyForm.vinid}">
                <tr>
                    <td>
                        <table width="100%" border="0" cellspacing="3" cellpadding="3" align="center">
                            <tr bgcolor="#eeeeee" height="20" >
                                <c:if test="${warrantyForm.flag ne 'true'}">
                                    <td colspan="28" class="headingSpas" align="left" >
                                    </c:if>
                                    <c:if test="${warrantyForm.flag eq 'true'}">
                                    <td colspan="17" class="headingSpas" align="left" >
                                    </c:if>
                                    <label>
                                        <B><bean:message key="label.common.customerinfo" /></B>
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td width="12%" align="right" ><label><bean:message key="label.common.dealerName" /> :</label></td>
                                <td width="12%" align="left">${warrantyForm.dealerName} [${warrantyForm.dealer_code}]</td>
                                <td width="12%" align="right"><label><bean:message key="label.common.CustomerNameCaps" />:</label></td>
                                <td  width="14%" align="left">${warrantyForm.customerName}</td>
                                <td width="12%"></td>
                                <td width="12%"></td>
                            </tr>
                            <tr>
                                <td align="right"><label><bean:message key="label.common.location" /> :</label></td> <%--label.delivery.Address--%>
                                <td align="left">${warrantyForm.dealerLocation}</td>
                                <td align="right"><label><bean:message key="label.delivery.Address" /> :</label></td>
                                <td align="left"><span>${warrantyForm.customerAddress}, ${warrantyForm.cusTehsil}<br> ${warrantyForm.cusDistrict} ,${warrantyForm.cusState}</span></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td align="right"></td>
                                <td align="left"></td>
                                <td align="right"><label><bean:message key="label.common.PinCode" /> :</label></td>
                                <td align="left">${warrantyForm.cusPincode} </td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr>
                                <td align="right"><label><bean:message key="label.warranty.warrantyClaimNo" /> :</label></td>
                                <td align="left">${warrantyForm.warrantyClaimNo}<input type="hidden" name="warrantyClaimNo" value="${warrantyForm.warrantyClaimNo}"></td>
                                <td align="right"><label ><bean:message key="label.warranty.claimDate" /> :</label></td>
                                <td align="left">${warrantyForm.claimDate}</td>
                                <td align="right"><label ><b><bean:message key="label.warranty.claimStatus" /> :</b></label></td>
                                <td align="left"><b>${warrantyForm.claimStatus}</b></td>
                            </tr>
                            <tr>
                                <td align="right"><label><bean:message key="label.warranty.TMSRefNo" /> :</label></td>
                                <td align="left">${warrantyForm.tmsRefNo}</td>
                                <td align="right"><label class="red"><bean:message key="label.common.Modelfamily" /> :</label></td>
                                <td align="left">${warrantyForm.modelFamily}</td>
                                <td align="right"><label><bean:message key="label.common.Modelfamilydesc" /> :</label></td>
                                <td align="left">${warrantyForm.modelFamilyDesc}</td>
                            </tr>

                            <tr>
                                <td align="right"><label><bean:message key="label.catalogue.chassisNo" /></label> :</td>
                                <td align="left">${warrantyForm.vinNo}</td>
                                <td align="right"><label><bean:message key="label.warranty.modelNo" /> :</label></td>
                                <td align="left">${warrantyForm.modelNo}</td>
                                <td align="right"><label ><bean:message key="label.catalogue.modelDesc" /> :</label></td>
                                <td align="left">${warrantyForm.modelDesc}</td>
                            </tr>
                            <tr>

                                <td align="right"><label><bean:message key="label.common.engineno" /></label> :</td>
                                <td align="left">${warrantyForm.engineNo}</td>
                                <td align="right"><label ><bean:message key="label.warranty.dateofFailure" /> :</label></td>
                                <td align="left">${warrantyForm.failureDate}</td>
                                <td align="right"><label class="red"><bean:message key="label.warranty.dateofDelivery" /> :</label></td>
                                <td align="left">${warrantyForm.deliveryDate}</td>
                            </tr>
                            <tr>
                                <td align="right"><label><bean:message key="label.common.jobcardno" /> :</label></td>
                                <td align="left">${warrantyForm.jobCardNo}</td>
                                <td align="right"><label><bean:message key="label.common.jobcardDate" /> :</label></td>
                                <td align="left">${warrantyForm.jobCardDate}</td>
                                <td align="right"><label><bean:message key="label.warranty.dateofReplacement" /> :</label></td>
                                <td align="left">${warrantyForm.replacementDate}</td>
                            </tr>
                            <tr>
                                <td align="right"><label><bean:message key="label.common.hrm" /> :</label></td>
                                <td align="left" colspan="4">${warrantyForm.hours} </td>
                            </tr>

                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                            <tr bgcolor="#eeeeee" height="20" >
                                <c:if test="${warrantyForm.flag ne 'true'}">
                                    <td colspan="27" class="headingSpas" align="left" >
                                    </c:if>
                                    <c:if test="${warrantyForm.flag eq 'true'}">
                                    <td colspan="16" class="headingSpas" align="left" >
                                    </c:if>
                                    <label >
                                        <B><bean:message key="label.catalogue.partDetails" /></B>
                                    </label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

            <div style="width:100%;  overflow: auto;">
                <table  id="data" width="150%" border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc >
                    <tr bgcolor="#eeeeee">
                        <th  align="left" style="padding-left:5px; "><bean:message key="label.warranty.S.No" /> </th>
                        <th align="left" style="padding-left:5px; "><bean:message key="label.warranty.defectDescription" /></th>
                        <th align="left" style="padding-left:5px; "><bean:message key="label.warranty.causalConsequential" /></th>
                        <th align="left" style="padding-left:5px; " ><bean:message key="label.catalogue.partCode" /></th>
                        <th align="left" style="padding-left:5px; "><bean:message key="label.common.partDesc" /></th>
                        <th align="left" style="padding-left:5px; "><bean:message key="label.warranty.venderCode" /></th>
                        <th width="5%" align="center" ><bean:message key="label.common.Qty" /></th>
                        <th align="center" style=" padding-right: 5px"><bean:message key="label.common.packingNo" /></th>
                        <th width="5%" align="center" style=" padding-right: 5px"><bean:message key="label.warranty.bixNo" /></th>
                        <th align="center" style=" padding-right: 5px"><bean:message key="label.warranty.dispatchQty" /></th>
                        <th align="center" style=" padding-right: 5px"><bean:message key="label.saleReturn.ReceivedQty" /></th>
                        <th align="right" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.unitPrice" /></th>
                        <th align="center" style="padding-left:5px;padding-right: 5px" nowrap ><bean:message key="label.warranty.qtyApproved" /></th>
                        <th align="right" style="padding-right:5px; "><bean:message key="label.warranty.approvedAmmount" /></th>
                        <th  align="left" style="padding-left:5px; " ><bean:message key="label.warranty.reasonforRejection" /></th>
                        <th  align="left" style="padding-left:5px; " ><bean:message key="label.common.remarks" /></th>
                        <c:if test="${warrantyForm.flag ne 'true'}">
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap ><bean:message key="label.common.hsnCode" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.dealerDiscount" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap ><bean:message key="label.common.taxableValue" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.cgstRate" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.cgstAmt" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.sgstRate" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.sgstAmt" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.ugstRate" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.ugstAmt" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.igstRate" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.igstAmt" /></th>
                        </c:if>
                        <%--<th  align="right" style=" padding-right: 5px" ><bean:message key="label.warranty.claimValue" /></th>--%>
                    </tr>
                    <c:set var="i" value="1"/>
                    <c:forEach items="${partList}" var="partList">
                        <tr >
                            <td align="center" bgcolor="#FFFFFF"  class="headingSpas"  >${i}.</td>
                            <td align="left" bgcolor="#FFFFFF" title="${partList.compDesc}" class="headingSpas" style="padding-left:5px; " >
                                ${partList.compDesc}
                            </td>
                            <td align="left" bgcolor="#FFFFFF" title="${partList.causalOrConseq}" class="headingSpas" style="padding-left:5px; ">
                                ${partList.causalOrConseq}
                            </td>
                            <td align="left" bgcolor="#FFFFFF" title="${partList.partNo}" class="headingSpas" style="padding-left:5px; " >
                                ${partList.partNo}
                            </td>
                            <td align="left" bgcolor="#FFFFFF" title="${partList.partDesc}" class="headingSpas" style="padding-left:5px; " >
                                ${partList.partDesc}
                            </td>
                            <td align="left" bgcolor="#FFFFFF" title="${partList.venderCode}" class="headingSpas" style="padding-left:5px; " >
                                ${partList.venderCode}
                            </td>
                            <td align="center" bgcolor="#FFFFFF"  title="${partList.qty}" class="headingSpas"  >
                                ${partList.qty}
                            </td>
                            <td align="center" bgcolor="#FFFFFF"  title="${partList.packingNo}" class="headingSpas"  >

                                <c:if test="${partList.packingNo eq 'null' || partList.packingNo eq ''}">
                                    - - -
                                </c:if>
                                <c:if test="${partList.packingNo ne 'null' || partList.packingNo ne ''}">
                                    ${partList.packingNo}
                                </c:if>
                            </td>
                            <td align="center" bgcolor="#FFFFFF"  title="${partList.boxNo}" class="headingSpas"  >
                                <c:if test="${partList.boxNo eq 'null' || partList.boxNo eq ''}">
                                    - - -
                                </c:if>
                                <c:if test="${partList.boxNo ne 'null' || partList.boxNo ne ''}">
                                    ${partList.boxNo}
                                </c:if>
                            </td>
                            <td align="center" bgcolor="#FFFFFF"  title="${partList.dispatchQty}" class="headingSpas"  >
                                ${partList.dispatchQty}
                            </td>
                            <td align="center" bgcolor="#FFFFFF"  title="${partList.receivedQty}" class="headingSpas"  >
                                ${partList.receivedQty}
                            </td>
                            <td align="right" bgcolor="#FFFFFF" title="${partList.unitPrice}"  class="headingSpas" style="padding-right: 5px" >
                                ${partList.unitPrice}
                            </td>
                            <td align="center" bgcolor="#FFFFFF" title="${partList.approveQty}"  class="headingSpas"  >
                                ${partList.approveQty}
                            </td>
                            <td align="right" bgcolor="#FFFFFF" title="${partList.approveAmm}"  class="headingSpas" style="padding-right: 5px" >
                                ${partList.approveAmm}
                            </td>


                            <td align="left" bgcolor="#FFFFFF"  title=" ${partList.rejectionDesc}" class="headingSpas" style="padding-left:5px; " >
                                <c:if test="${partList.rejectionDesc eq 'null' || partList.rejectionDesc eq ''}">
                                    - - -
                                </c:if>
                                <c:if test="${partList.rejectionDesc ne 'null' || partList.rejectionDesc ne ''}">
                                    ${partList.rejectionDesc}
                                </c:if>

                            </td>
                            <td align="left" bgcolor="#FFFFFF"  title=" ${partList.remark}" class="headingSpas" style="padding-left:5px; " >
                                <c:if test="${partList.remark eq 'null' || partList.remark eq ''}">
                                    - - -
                                </c:if>
                                <c:if test="${partList.remark ne 'null' || partList.remark ne ''}">
                                    ${partList.remark}
                                </c:if>

                            </td>
                            <c:if test="${warrantyForm.flag ne 'true'}">
                                <td align="right" bgcolor="#FFFFFF" title="${partList.hsnCode}"  class="headingSpas" style="padding-right: 5px" >
                                    ${partList.hsnCode}
                                </td>
                                <td align="right" bgcolor="#FFFFFF" title="${partList.dealerDiscount}"  class="headingSpas" style="padding-right: 5px" >
                                    ${partList.dealerDiscount}
                                </td>
                                <td align="right" bgcolor="#FFFFFF" title="${partList.taxableValue}"  class="headingSpas" style="padding-right: 5px" >
                                    ${partList.taxableValue}
                                </td>
                                <td align="right" bgcolor="#FFFFFF" title="${partList.cgstRate}"  class="headingSpas" style="padding-right: 5px" >
                                    ${partList.cgstRate}
                                </td>
                                <td align="right" bgcolor="#FFFFFF" title="${partList.cgstAmt}"  class="headingSpas" style="padding-right: 5px" >
                                    ${partList.cgstAmt}
                                </td>

                                <td align="right" bgcolor="#FFFFFF" title="${partList.sgstRate}"  class="headingSpas" style="padding-right: 5px" >
                                    ${partList.sgstRate}
                                </td>
                                <td align="right" bgcolor="#FFFFFF" title="${partList.sgstAmt}"  class="headingSpas" style="padding-right: 5px" >
                                    ${partList.sgstAmt}
                                </td>
                                <td align="right" bgcolor="#FFFFFF" title="${partList.ugstRate}"  class="headingSpas" style="padding-right: 5px" >
                                    ${partList.ugstRate}
                                </td>
                                <td align="right" bgcolor="#FFFFFF" title="${partList.ugstAmt}"  class="headingSpas" style="padding-right: 5px" >
                                    ${partList.ugstAmt}
                                </td>
                                <td align="right" bgcolor="#FFFFFF" title="${partList.igstRate}"  class="headingSpas" style="padding-right: 5px" >
                                    ${partList.igstRate}
                                </td>
                                <td align="right" bgcolor="#FFFFFF" title="${partList.igstAmt}"  class="headingSpas" style="padding-right: 5px" >
                                    ${partList.igstAmt}
                                </td>
                            </c:if>
                            <%--<td align="right" bgcolor="#FFFFFF"  title=" ${partList.claimValue}" class="headingSpas" style="padding-left:5px; padding-right: 5px" >
                                ${partList.claimValue}
                            </td>--%>
                        </tr>
                        <c:set var="i" value="${i+1}"/>

                    </c:forEach>


                    <tr bgcolor="#eeeeee">
                        <c:if test="${warrantyForm.flag ne 'true'}">
                            <td colspan="27" class="headingSpas" align="left" ></td>
                        </c:if>
                        <c:if test="${warrantyForm.flag eq 'true'}">
                            <td colspan="16" class="headingSpas" align="left" ></td>
                        </c:if>
                    </tr>
                    <%--<tr bgcolor="#FFFFFF">
                        <c:if test="${warrantyForm.flag ne 'true'}"><td align="right" colspan="27"  class="tdgridnew1 "></c:if>
                        <c:if test="${warrantyForm.flag eq 'true'}"><td align="right" colspan="16"  class="tdgridnew1 "></c:if>
                            <bean:message key="label.warranty.total" /> ${warrantyForm.flagTemp} :</td>
                        <td align="right" class="tdgridnew1 ">${warrantyForm.totalApproveAmmount}</td>
                    </tr>--%>

                    <%-- <c:set var="t" value="1"/>
                     <c:forEach items="${taxList}" var="taxList">



                        <c:if test="${taxList.typeDescription eq 'Less'}">
                            <tr bgcolor="#FFFFFF">
                                <c:if test="${warrantyForm.flag ne 'true'}"><td align="right" colspan="27"  class="tdgridnew1 "></c:if>
                                <c:if test="${warrantyForm.flag eq 'true'}"><td align="right" colspan="16"  class="tdgridnew1 "></c:if>
                                    ${taxList.typeDescription} (${taxList.typePercentage} %) :  </td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.applessAmm}</td>
                            </tr>
                        </c:if>
                        <c:if test="${taxList.typeDescription eq 'CST/VAT'}">
                            <tr bgcolor="#FFFFFF">
                                <c:if test="${warrantyForm.flag ne 'true'}"><td align="right" colspan="27"  class="tdgridnew1 "></c:if>
                                <c:if test="${warrantyForm.flag eq 'true'}"><td align="right" colspan="16"  class="tdgridnew1 "></c:if>
                                    ${taxList.typeDescription} (${taxList.typePercentage} %) : </td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.appCstVat}</td>
                            </tr>
                        </c:if>
                        <c:if test="${taxList.typeDescription eq 'Handling Charges'}">
                            <tr bgcolor="#FFFFFF">
                                <c:if test="${warrantyForm.flag ne 'true'}"><td align="right" colspan="27"  class="tdgridnew1 "></c:if>
                                <c:if test="${warrantyForm.flag eq 'true'}"><td align="right" colspan="16"  class="tdgridnew1 "></c:if>
                                    ${taxList.typeDescription} (${taxList.typePercentage} %) :</td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.appHanCharge}</td>
                            </tr>
                        </c:if>
                        <c:if test="${taxList.typeDescription eq 'GST on Handling Charges'}">
                            <tr bgcolor="#FFFFFF">
                                <c:if test="${warrantyForm.flag ne 'true'}"><td align="right" colspan="27"  class="tdgridnew1 "></c:if>
                                <c:if test="${warrantyForm.flag eq 'true'}"><td align="right" colspan="16"  class="tdgridnew1 "></c:if>
                                    ${taxList.typeDescription} (${taxList.typePercentage} %) :</td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.gstOnHandling}</td>
                            </tr>
                        </c:if>

                        <c:if test="${taxList.typeDescription eq 'GST on Insurance Charges'}">
                            <tr bgcolor="#FFFFFF">
                                <c:if test="${warrantyForm.flag ne 'true'}"><td align="right" colspan="27"  class="tdgridnew1 "></c:if>
                                <c:if test="${warrantyForm.flag eq 'true'}"><td align="right" colspan="16"  class="tdgridnew1 "></c:if>
                                    Insurance charges (Nil) :<input type="hidden" name="discount" id="Discount${t}" value="0"></td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.insuranceCharges}</td>
                            </tr>
                            <c:set var="t" value="${t+1}"/>
                            <tr bgcolor="#FFFFFF">
                                <c:if test="${warrantyForm.flag ne 'true'}"><td align="right" colspan="27"  class="tdgridnew1 "></c:if>
                                <c:if test="${warrantyForm.flag eq 'true'}"><td align="right" colspan="16"  class="tdgridnew1 "></c:if>
                                    ${taxList.typeDescription} (${taxList.typePercentage} %) :<input type="hidden" name="discount" id="Discount${t}" value="${taxList.typePercentage}">        <bean:message key="label.warranty.total" /></td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.gstOnInsurance}</td>
                            </tr>

                        </c:if>



                        <c:set var="t" value="${t+1}"/>
                    </c:forEach>
                    <tr bgcolor="#FFFFFF">
                        <c:if test="${warrantyForm.flag ne 'true'}"><td align="right" colspan="27"  class="tdgridnew1 "></c:if>
                        <c:if test="${warrantyForm.flag eq 'true'}"><td align="right" colspan="16"  class="tdgridnew1 "></c:if>
                            <bean:message key="label.common.GrandTotal" /> ${warrantyForm.flagTemp} :</td>
                        <td align="right" class="tdgridnew1">${warrantyForm.approveAmm}</td>
                    </tr>--%>
                    <c:set var="j" value="1"/>
                    <c:forEach items="${otherPartList}" var="otherPartList">
                        <tr bgcolor="#ffffff">
                            <td align="center" bgcolor="#FFFFFF"  class="headingSpas"  >&nbsp;</td>
                            <td align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; " >&nbsp;</td>
                            <td align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:5px; ">&nbsp;</td>
                            <td align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; " >&nbsp;</td>
                            <td align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; " ></td>
                            <td align="left" bgcolor="#FFFFFF" title="${otherPartList.partDesc}" class="headingSpas" style="padding-left:5px; " colspan="3" ><b>${otherPartList.partDesc}</b></td>
                            <%-- <td align="center" bgcolor="#FFFFFF"   class="headingSpas"  >&nbsp;</td>
                             <td align="center" bgcolor="#FFFFFF"   class="headingSpas"  >&nbsp;</td>--%>
                            <td align="center" bgcolor="#FFFFFF"   class="headingSpas"  >&nbsp;</td>
                            <td align="center" bgcolor="#FFFFFF"   class="headingSpas"  >&nbsp;</td>
                            <td align="center" bgcolor="#FFFFFF"   class="headingSpas"  >&nbsp;</td>
                            <td align="right" bgcolor="#FFFFFF"  class="headingSpas" style="padding-right: 5px" >&nbsp;</td>
                            <td align="center" bgcolor="#FFFFFF"   class="headingSpas"  >&nbsp;</td>
                            <td align="right" bgcolor="#FFFFFF"   class="headingSpas" style="padding-right: 5px" >&nbsp;</td>
                            <td align="left" bgcolor="#FFFFFF"   class="headingSpas" style="padding-left:5px; " >&nbsp;</td>
                            <td align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; " >&nbsp;</td>
                            <td align="right" bgcolor="#FFFFFF" title="${otherPartList.hsnCode}"  class="headingSpas" style="padding-right: 5px" > ${otherPartList.hsnCode}</td>
                            <td align="right" bgcolor="#FFFFFF"   class="headingSpas" style="padding-right: 5px" >&nbsp;</td>
                            <td align="right" bgcolor="#FFFFFF" title="${otherPartList.taxableValue}"  class="headingSpas" style="padding-right: 5px" >${otherPartList.taxableValue}</td>
                            <td align="right" bgcolor="#FFFFFF" title="${otherPartList.cgstRate}"  class="headingSpas" style="padding-right: 5px" >
                                ${otherPartList.cgstRate}
                            </td>
                            <td align="right" bgcolor="#FFFFFF" title="${otherPartList.cgstAmt}"  class="headingSpas" style="padding-right: 5px" >
                                ${otherPartList.cgstAmt}
                            </td>

                            <td align="right" bgcolor="#FFFFFF" title="${otherPartList.sgstRate}"  class="headingSpas" style="padding-right: 5px" >
                                ${otherPartList.sgstRate}
                            </td>
                            <td align="right" bgcolor="#FFFFFF" title="${otherPartList.sgstAmt}"  class="headingSpas" style="padding-right: 5px" >
                                ${otherPartList.sgstAmt}
                            </td>
                            <td align="right" bgcolor="#FFFFFF" title="${otherPartList.ugstRate}"  class="headingSpas" style="padding-right: 5px" >
                                ${otherPartList.ugstRate}
                            </td>
                            <td align="right" bgcolor="#FFFFFF" title="${otherPartList.ugstAmt}"  class="headingSpas" style="padding-right: 5px" >
                                ${otherPartList.ugstAmt}
                            </td>
                            <td align="right" bgcolor="#FFFFFF" title="${otherPartList.igstRate}"  class="headingSpas" style="padding-right: 5px" >
                                ${otherPartList.igstRate}
                            </td>
                            <td align="right" bgcolor="#FFFFFF" title="${otherPartList.igstAmt}"  class="headingSpas" style="padding-right: 5px" >
                                ${otherPartList.igstAmt}
                            </td>
                            <%-- <td align="right" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px" >&nbsp;</td>--%>
                            <c:set property="totalTaxableValueStr" var="totalTaxableValueStr" value="${otherPartList.totalTaxableValue}"/>
                            <c:set property="totalTaxValueStr" var="totalTaxValueStr" value="${otherPartList.totalTaxValue}"/>
                            <c:set property="totalInvoiceAmountStr" var="totalInvoiceAmountStr" value="${otherPartList.totalInvoiceAmount}"/>
                        </tr>
                    </c:forEach>
                    <c:set var="j" value="1"/>
                    <tr  bgcolor="#eeeeee" style="height: 12px;">
                        <td  colspan="27"> </td>
                    </tr>

                </table>
            </div>

            <table border="0" cellspacing="3" cellpadding="3" width="98%">
           
                <tr>
                    <td align="right" width="95%"   colspan="26" class="tdgridnew1 "><bean:message key="label.common.totaltaxablevalue" /> :</td>
                    <td align="right" class="tdgridnew1 "  id="totalTaxableValueStr">${totalTaxableValueStr}</td>
                </tr>
                <tr>
                    <td align="right" width="95%"  colspan="26" class="tdgridnew1 "><bean:message key="label.common.totaltaxvalue" /> :</td>
                    <td align="right" class="tdgridnew1 " id="totalTaxValueStr">${totalTaxValueStr}</td>
                </tr>
                <tr>
                    <td  align="right" class="tdgridnew1 "   colspan="26"><bean:message key="label.common.GrandTotal" /> ${warrantyForm.flagTemp} :</td>
                    <td align="right" class="tdgridnew1 ">${totalInvoiceAmountStr}</td> <!--${warrantyForm.approveAmm}-->
                </tr>
            </table>



            <c:if test="${flag eq 'creditNote' }">
                <table border="0" cellspacing="3" cellpadding="3" width="98%">
                    <tr bgcolor="#eeeeee" height="20" >
                        <td colspan="6" class="headingSpas" align="left" >
                            <label>
                                <B><bean:message key="label.creditNote.creditNoteInfo" /></B>
                            </label>
                        </td>

                    </tr>
                    <tr>
                        <td align="right"><label><bean:message key="label.creditNote.creditNo" /> :</label></td>
                        <td align="left">${warrantyForm.sapCreditNo}</td>
                        <td align="right"><label><bean:message key="label.creditNote.creditDate" /> :</label></td>
                        <td align="left">${warrantyForm.sapCreditDate}</td>
                        <td align="right"><label><bean:message key="label.creditNote.creditAmount" /> :</label></td>
                        <td align="left">${warrantyForm.sapCreditAmount}</td>
                    </tr>
                    <tr bgcolor="#eeeeee" height="20" ></tr>
                </table>
            </c:if>



        </div>
    </div>
</center>