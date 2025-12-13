<%-- 
    Document   : viewWarrantyDetail
    Created on : Sep 23, 2014, 6:35:18 PM
    Author     : kundan.rastogi
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.File" %>
<c:set var="uplodFailedPartFilePath1" value="${warrantyForm.failedPartFileName1}" scope="request" />
<c:set var="uplodFailedPartFilePath2" value="${warrantyForm.failedPartFileName2}" scope="request" />
<c:set var="uplodFailedPartFilePath3" value="${warrantyForm.failedPartFileName3}" scope="request" />
<c:set var="uplodFailedPartFilePath4" value="${warrantyForm.failedPartFileName4}" scope="request" />
<c:set var="uplodFailedPartFilePath5" value="${warrantyForm.failedPartFileName5}" scope="request" />
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            String value1 = (String) pageContext.getRequest().getAttribute("uplodFailedPartFilePath1");
            String value2 = (String) pageContext.getRequest().getAttribute("uplodFailedPartFilePath2");
            String value3 = (String) pageContext.getRequest().getAttribute("uplodFailedPartFilePath3");
            String value4 = (String) pageContext.getRequest().getAttribute("uplodFailedPartFilePath4");
            String value5 = (String) pageContext.getRequest().getAttribute("uplodFailedPartFilePath5");
%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

    function Export(wClaimNo,flag)
    {
        // alert(wClaimNo);
        window.location.href='<%=cntxpath%>/warrantyAction.do?option=viewWarrantyClaimDetail&eType=export&warrntyClaimNo='+wClaimNo+'&flag='+flag;
     
    }
    function downloadfile(filename)
    {
        var url=contextPath+"/DOCUMENTS/WARRANTY/"+filename;
        var win=window.open(url, '_blank',"directories=no,height=600,width=600,menubar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,top=0,location=no");
        win.focus();

    }
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a></li>
            <c:if test="${flag eq 'view'}">
                <li class="breadcrumb_link"><a href='<%=cntxpath%>/warrantyAction.do?option=viewWarrantyClaim'><bean:message key="label.warranty.viewWarrantyClaim" /></a></li>
            </c:if>
            <c:if test="${flag eq 'Pen'}">
                <li class="breadcrumb_link"><a href='<%=cntxpath%>/warrantyAction.do?option=viewPendingWarrantyClaim'><bean:message key="label.warranty.pendingWarrantyClaim" /></a></li>
            </c:if>
            <c:if test="${flag eq 'viewPack' }">
                <li class="breadcrumb_link"><a href='<%=cntxpath%>/warrantyAction.do?option=pendingForDispatch'><bean:message key="label.warranty.pendingforPackGen" /></a></li>
            </c:if>
            <c:if test="${flag eq 'viewGenPack' }">
                <li class="breadcrumb_link"><a href='<%=cntxpath%>/warrantyAction.do?option=pendingForDispatch'><bean:message key="label.warranty.pendingforPackGen" /></a></li>
            </c:if>
            <c:if test="${flag eq 'penAck' }">
                <li class="breadcrumb_link"><a href='<%=cntxpath%>/warrantyAction.do?option=pendingForAcknow'><bean:message key="label.common.pendingForAcknow" /></a></li>
            </c:if>
            <c:if test="${flag eq 'pendSAP' }">
                <li class="breadcrumb_link"><a href="<%=cntxpath%>/warrantyAction.do?option=pendingSAPList"><bean:message key="label.common.pendingSAPgeneration" /></a></li>
            </c:if>
            <c:if test="${flag eq 'creditNote' }">
                <li class="breadcrumb_link"><a href="<%=cntxpath%>/warrantyAction.do?option=viewCreditNote"><bean:message key="label.common.viewCreditNote" /></a></li>
            </c:if>
            <li class="breadcrumb_link">${warrantyForm.warrantyClaimNo}</li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1><bean:message key="label.warranty.viewWarrantyClaim" /> </h1>


                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" >
                    <input type="hidden" name="vinid" value="${warrantyForm.vinid}">
                    <tr>
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="4" align="center">
                                <tr bgcolor="#eeeeee" height="20" >
                                    <td colspan="5" class="headingSpas" align="left" >
                                        <label>
                                            <B><bean:message key="label.common.customerinfo" /></B>
                                        </label>
                                    </td>
                                    <td  class="headingSpas" align="right" >

                                        <input type="button" value="Export" onclick="Export('${warrantyForm.warrantyClaimNo}','${flag}')"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="12%" align="right" ><label><bean:message key="label.common.dealerName" /> :</label></td>
                                    <td width="12%" align="left">${warrantyForm.dealerName} [${warrantyForm.dealer_code}]
                                    </td>
                                    <td width="12%" align="right"><label><bean:message key="label.common.CustomerNameCaps" />:</label></td>
                                    <td  width="14%" align="left">${warrantyForm.customerName}</td>
                                    <td width="12%"></td>
                                    <td width="12%"></td>
                                </tr>
                                <tr>
                                    <td align="right"><label><bean:message key="label.common.location" /> :</label></td> <%--label.delivery.Address--%>
                                    <td align="left">${warrantyForm.dealerLocation}
                                    </td>
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

                                    <td align="right"><label ><bean:message key="label.warranty.claimStatus" /> :</label></td>
                                    <td align="left">${warrantyForm.claimStatus}</td>
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
                                    <td align="left">${warrantyForm.jobCardNo} </td>
                                    <td align="right"><label><bean:message key="label.common.jobcardDate" /> :</label></td>
                                    <td align="left">${warrantyForm.jobCardDate}</td>
                                    <td align="right"><label><bean:message key="label.warranty.dateofReplacement" /> :</label></td>
                                    <td align="left">${warrantyForm.replacementDate} </td>
                                </tr>
                                <tr>
                                    <td align="right"><label><bean:message key="label.common.hrm" /> :</label></td>
                                    <td align="left" >${warrantyForm.hours} </td>
                                    <% String s = getServletContext().getRealPath("/") + "/DOCUMENTS/WARRANTY/" + value1;
                                       File f = new File(s);
									   if (f.exists() && (value1 != null && !value1.isEmpty())) { %>
	                                    <td align="right"><label><bean:message key="label.warranty.viewFailedPartFile" />1 :</label>
								        </td>
								        <td align="left" class="headingSpas" style="white-space: nowrap">&nbsp;
								            <img src='${pageContext.request.contextPath}/image/download.jpg' border='0' alt='Download File' title="Click here view failed part" onclick="downloadfile('${warrantyForm.failedPartFileName1}')"/>
								        </td>
							           <%} %>
                                </tr>
                                <%  s = getServletContext().getRealPath("/") + "/DOCUMENTS/WARRANTY/" + value2;
								    f = new File(s);
								if (f.exists() && (value2 != null && !value2.isEmpty())) { %>
							    <tr>
							        <td align="right"></td><td align="left"></td>
							        <td align="right"><label><bean:message key="label.warranty.viewFailedPartFile" />2 :</label>
							        </td>
							        <td align="left" class="headingSpas" style="white-space: nowrap">&nbsp;
							            <img src='${pageContext.request.contextPath}/image/download.jpg' border='0' alt='Download File' title="Click here view failed part" onclick="downloadfile('${warrantyForm.failedPartFileName2}')"/>
							        </td>
							    </tr>
								<% } 
                                    s = getServletContext().getRealPath("/") + "/DOCUMENTS/WARRANTY/" + value3;
								    f = new File(s);
								    if (f.exists() && (value3 != null && !value3.isEmpty())) { %>
							    <tr>
							        <td align="right"></td><td align="left"></td>
							        <td align="right"><label><bean:message key="label.warranty.viewFailedPartFile" />3 :</label>
							        </td>
							        <td align="left" class="headingSpas" style="white-space: nowrap">&nbsp;
							            <img src='${pageContext.request.contextPath}/image/download.jpg' border='0' alt='Download File' title="Click here view failed part" onclick="downloadfile('${warrantyForm.failedPartFileName3}')"/>
							        </td>
							    </tr>
								<% }
                                    s = getServletContext().getRealPath("/") + "/DOCUMENTS/WARRANTY/" + value4;
								    f = new File(s);
								    if (f.exists() && (value4 != null && !value4.isEmpty())) { %>
							    <tr>
							        <td align="right"></td><td align="left"></td>
							        <td align="right"><label><bean:message key="label.warranty.viewFailedPartFile" />4 :</label>
							        </td>
							        <td align="left" class="headingSpas" style="white-space: nowrap">&nbsp;
							            <img src='${pageContext.request.contextPath}/image/download.jpg' border='0' alt='Download File' title="Click here view failed part" onclick="downloadfile('${warrantyForm.failedPartFileName4}')"/>
							        </td>
							    </tr>
								<% } 
								    s = getServletContext().getRealPath("/") + "/DOCUMENTS/WARRANTY/" + value5;
								    f = new File(s);
								    if (f.exists() && (value5 != null && !value5.isEmpty())) { %>
							    <tr>
							        <td align="right"></td><td align="left"></td>
							        <td align="right"><label><bean:message key="label.warranty.viewFailedPartFile" />5 :</label>
							        </td>
							        <td align="left" class="headingSpas" style="white-space: nowrap">&nbsp;
							            <img src='${pageContext.request.contextPath}/image/download.jpg' border='0' alt='Download File' title="Click here view failed part" onclick="downloadfile('${warrantyForm.failedPartFileName5}')"/>
							        </td>
							    </tr>
								<% } %>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr bgcolor="#eeeeee" height="20" >
                                    <td colspan="6" class="headingSpas" align="left" >
                                        <label >
                                            <B><bean:message key="label.catalogue.partDetails" /></B>
                                        </label>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <div style="width:99%;  overflow: auto;">
                    <table  id="data" width="120%" border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc >
                        <tr bgcolor="#eeeeee">
                            <th align="left" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.warranty.S.No" /> </th>
                            <th align="left" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.warranty.defectDescription" /></th>
                            <th align="left" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.warranty.causalConsequential" /></th>
                            <th align="left" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.catalogue.partCode" /></th>
                            <th align="left" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.partDesc" /></th>
                            <th align="left" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.warranty.venderCode" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.Qty" /></th>
                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.packingNo" /></th>
                            <th  nowrap><span class="verticalText"><bean:message key="label.warranty.bixNo" /></span></th>
                            <th nowrap><span class="verticalText"><bean:message key="label.warranty.dispatchQty" /></span></th>
                            <th nowrap><span class="verticalText"><bean:message key="label.saleReturn.ReceivedQty" /></span></th>
                            <th nowrap><span class="verticalText"><bean:message key="label.common.unitPrice" /></span></th>
                            <th nowrap ><span class="verticalText"><bean:message key="label.warranty.qtyApproved" /></span></th>
                            <th ><span class="verticalText"><bean:message key="label.warranty.approvedAmmount" /></span></th>
                            <th ><span class="verticalText"><bean:message key="label.warranty.reasonforRejection" /></span></th>
                            <th ><span class="verticalText"><bean:message key="label.common.remarks" /></span></th>
                            <c:if test="${warrantyForm.flag ne 'true'}">
                                <th    nowrap ><span class="verticalText"><bean:message key="label.common.hsnCode" /></span></th>
                                <th    nowrap><span class="verticalText"><bean:message key="label.common.dealerDiscount" /></span></th>
                                <th    nowrap ><span class="verticalText"><bean:message key="label.common.taxableValue" /></span></th>
                                <th    nowrap><span class="verticalText"><bean:message key="label.common.cgstRate" /></span></th>
                                <th    nowrap><span class="verticalText"><bean:message key="label.common.cgstAmt" /></span></th>
                                <th    nowrap><span class="verticalText"><bean:message key="label.common.sgstRate" /></span></th>
                                <th    nowrap><span class="verticalText"><bean:message key="label.common.sgstAmt" /></span></th>
                                <th    nowrap><span class="verticalText"><bean:message key="label.common.ugstRate" /></span></th>
                                <th    nowrap><span class="verticalText"><bean:message key="label.common.ugstAmt" /></span></th>
                                <th    nowrap><span class="verticalText"><bean:message key="label.common.igstRate" /></span></th>
                                <th    nowrap><span class="verticalText"><bean:message key="label.common.igstAmt" /></span></th>
                            </c:if>

                            <%--<th nowrap ><span class="verticalText"><bean:message key="label.warranty.claimValue" /></span></th>--%>
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
                                        <a href="<%=cntxpath%>/warrantyAction.do?option=packingDetails4View&packingNo=${partList.packingNo}" >
                                            <span >${partList.packingNo}</span>
                                        </a>
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
                            <td  colspan="28"> </td>
                        </tr>

                    </table>
                </div>

                <table border="0" cellspacing="3" cellpadding="3" width="98%">
<%--
                    <tr>
                        <td align="right" width="95%"  class="tdgridnew1 "><bean:message key="label.warranty.total" /> ${warrantyForm.flagTemp} :</td>
                        <td align="right" class="tdgridnew1 ">${warrantyForm.totalApproveAmmount}</td>
                    </tr>--%>
                    <tr>
                        <td align="right" width="95%"  class="tdgridnew1 "><bean:message key="label.common.totaltaxablevalue" /></td>
                        <td align="right" class="tdgridnew1 " id="totalTaxableValueStr">${totalTaxableValueStr}</td>
                    </tr>
                    <tr>
                        <td align="right" width="95%"  class="tdgridnew1 "><bean:message key="label.common.totaltaxvalue" /></td>
                        <td align="right" class="tdgridnew1 " id="totalTaxValueStr">${totalTaxValueStr}</td>
                    </tr>

                    <%--<c:set var="t" value="1"/>
                    <c:forEach items="${taxList}" var="taxList">

                        <c:if test="${taxList.typeDescription eq 'Less'}">
                            <tr>
                                <td  align="right" class="tdgridnew1 "> ${taxList.typeDescription} (${taxList.typePercentage} %) :  </td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.applessAmm}</td>
                            </tr>
                        </c:if>
                        <c:if test="${taxList.typeDescription eq 'CST/VAT'}">
                            <tr>
                                <td  align="right" class="tdgridnew1 ">${taxList.typeDescription} (${taxList.typePercentage} %) : </td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.appCstVat}</td>
                            </tr>
                        </c:if>

                        <c:if test="${taxList.typeDescription eq 'Handling Charges'}">
                            <tr>
                                <td  align="right" class="tdgridnew1 "> ${taxList.typeDescription} (${taxList.typePercentage} %) :</td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.appHanCharge}</td>
                            </tr>
                        </c:if>
                        <c:if test="${taxList.typeDescription eq 'GST on Handling Charges'}">
                            <tr>
                                <td width="89%" align="right" class="tdgridnew1 "> ${taxList.typeDescription} (${taxList.typePercentage} %) :</td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.gstOnHandling}</td>
                            </tr>
                        </c:if>
                        <c:if test="${taxList.typeDescription eq 'GST on Insurance Charges'}">
                            <tr>
                                <td width="100%" align="right" class="tdgridnew1 "> Insurance charges :</td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.insuranceCharges}</td>
                            </tr>
                            <c:set var="t" value="${t+1}"/>
                            <tr>
                                <td width="100%" align="right" class="tdgridnew1 "> ${taxList.typeDescription} (${taxList.typePercentage} %) :         <bean:message key="label.warranty.total" /></td>
                                <td align="right" class="tdgridnew1 ">${warrantyForm.gstOnInsurance}</td>
                            </tr>

                        </c:if>




                        <c:set var="t" value="${t+1}"/>
                    </c:forEach>--%>
                    <tr>
                        <td  align="right" class="tdgridnew1 " ><bean:message key="label.common.GrandTotal" /> ${warrantyForm.flagTemp} :</td>
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
</div>




