<%-- 
    Document   : exportCSProfarmaInvoice
    Created on : 21-Feb-2015, 12:53:57
    Author     : kundan.rastogi
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            response.setHeader("Content-Disposition", "attachment; filename=Proforma_Invoice.xls");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String dealerCode = (String) request.getAttribute("dealerCode");
            String heading = request.getAttribute("heading") == null ? "" : request.getAttribute("heading").toString();
            String headingFlag = request.getAttribute("headingFlag") == null ? "" : request.getAttribute("headingFlag").toString();
%>

<c:set var="heading" value='<%=heading%>'/>

<center style="background-color:#FFFFFF">
    <div class="mainwrap" style="background-color: #ffffff">
        <div class="header_main_print">
            <div class="header_center">

                <%--<div class="">
                    <img src="<%=cntxpath%>/layout/images/logo.png" alt="" class="fl">
                </div>--%>
                <%if (headingFlag.equals("")) {
                                //serviceForm sf=(String)request.getAttribute("dealerCode");
                                LoginDAO dao = new LoginDAO();
                                serviceForm sf = dao.getDealerAddress((String) request.getAttribute("dealerCode"));
                %>
                <h1><%=sf.getDealerName()%></h1>
                <%-- <p></p>--%>
                <p><%=sf.getPaymentMode()%><br><%=sf.getPayeeDistrict() + " Ph. " + sf.getPayeeMobilePhone() + " Tin No. " + sf.getPayeePinCode()%></p>
                    <%} else {%>
                <h1>INTERNATIONAL TRACTORS LIMITED</h1>
                <p>  Vill. Chak Gujran, P.O. Piplanwala, Jalandhar Road, Hoshiarpur.</p>
                <%}%>
                <p style="font-size: large;"><b>${heading}</b></p>

            </div>
        </div>
    </div>

    <div class="content_right1">
        <div class="con_slidediv2" style="position: relative;width: 100%">
            <c:if test="${!empty profarmaList}">
                <table border="0" cellspacing="3" cellpadding="3" width="100%">
                    <tr height=20 bgcolor="#FFFFFF" >
                        <td align="right" width="5%" ></td>
                        <td align="right" width="15%" ><label ><B><bean:message key="label.common.contactname" />: </B></label></td>
                        <td align="left" width="15%" colspan="1"><span>${inventoryForm.cusName}</span>
                        </td>
                        <td align="right" width="15%"><label ><B><bean:message key="label.common.contactno" />: </B></label></td>
                        <td align="left" width="15%" ><span>${inventoryForm.contactNo}</span>
                        </td>
                        <td align="right" width="15%" ><span><label ><B><bean:message key="label.common.InvoiceDate" />: </B></label></span></td>
                        <td align="left" width="10%"><span>${inventoryForm.invDate}</span>
                        </td>
                        <td align="right" width="15%"  ><label ><B><bean:message key="label.common.saleTaxType" />: </B></label></td>
                        <td align="left" width="15%"  ><span>${inventoryForm.saleTaxTypeID}</span>
                        </td>
                    </tr>
                    <tr height=20 bgcolor="#FFFFFF">
                        <td colspan="9"> </td>
                    </tr>
                    <tr bgcolor="#eeeeee" height="20" class="hText">
                        <td align="left" colspan="9" style ="border:solid 1px #000; FONT-SIZE: 12px;" ><B><bean:message key="label.common.label4viewpart" /></B></td>
                    </tr>
                    <tr  class="grid" bgcolor="#eeeeee">
                        <th  align="center" class="tdgridnew1" width="2%" style ="FONT-SIZE: 12px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><label><bean:message key="label.common.sno" /></label></th>
                        <th  align="left" class="tdgridnew1" width="15%" style ="FONT-SIZE: 12px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><label><bean:message key="label.common.partno_small" /></label></th>
                        <th  align="left" class="tdgridnew1" width="25%" style ="FONT-SIZE: 12px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><label><bean:message key="label.common.partdesc_small" /></label></th>
                        <th  align="left" class="tdgridnew1" width="10%" style ="FONT-SIZE: 12px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><label><bean:message key="label.common.category" /></label></th>
                        <th  align="center" class="tdgridnew1" width="5%" style =" FONT-SIZE: 12px;border-bottom:solid 1px #000; border-right:solid 1px #000;"><label><bean:message key="label.common.qty_small" /></label></th>
                        <th  align="center" class="tdgridnew1" width="12%" style =" FONT-SIZE: 12px;border-bottom:solid 1px #000; border-right:solid 1px #000;" ><label><bean:message key="label.common.unitprice_small" /></label></th>
                        <th  align="center" class="tdgridnew1" width="10%" style =" FONT-SIZE: 12px;border-bottom:solid 1px #000; border-right:solid 1px #000;"><label><bean:message key="label.common.discount_small" /></label></th>
                        <th  align="left" class="tdgridnew1" width="10%" style =" FONT-SIZE: 12px;border-bottom:solid 1px #000; border-right:solid 1px #000;"><label><bean:message key="label.common.billto_small" /></label></th>
                        <th  align="right" class="tdgridnew1" width="10%" style =" FONT-SIZE: 12px;border-bottom:solid 1px #000; border-right:solid 1px #000;" ><label><bean:message key="label.common.amt_small" /></label></th>
                    </tr>
                    <c:set var="j" value="0"></c:set>
                    <c:forEach items="${profarmaList}" var="profarmaList">
                        <tr >
                            <td align="center" class="tdgridnew1" style="FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;" >${(j+1)}</td>
                            <td align="left" style ="FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><span>${profarmaList.partno}</span>            </td>
                            <td align="left" style ="FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><span>${profarmaList.part_desc}</span>            </td>
                            <td align="left" style =" FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><span>${profarmaList.partTypeStr}</span>  </td>
                            <td align="center" style =" FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><span>${profarmaList.qty}</span> </td>
                            <td align="center" style =" FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><span>${profarmaList.unitValue}</span>     </td>
                            <td align="center" style =" FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><span>${profarmaList.discount}</span>  </td>
                            <td align="left" style =" FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><span>${profarmaList.billId}</span>   </td>
                            <td align="right" style=" FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><span>${profarmaList.amountPerPrice}</span> </td>
                        </tr>
                        <c:set var="j" value="${j + 1 }" />
                    </c:forEach>

                    <tr>
                        <td colspan="8" align="right" class="tdgridnew1" style="FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;" ><label><B><bean:message key="label.common.totalSparesValue" /></B></label></td>
                        <td align="right" class="tdgridnew1" style="font-size:11px; border-bottom:solid 1px #000; border-right:solid 1px #000;" "><span>${inventoryForm.partValue}</span>

                        </td>
                    </tr>
                    <tr >
                        <td colspan="8" align="right" class="tdgridnew1" style="FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><label><B><bean:message key="label.common.ttllubesvalue_small" /></B></label></td>
                        <td align="right" class="tdgridnew1" style="FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><span>${inventoryForm.lubesValue}</span>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="8" align="right" class="tdgridnew1" style="FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><label><B><bean:message key="label.common.otherdiscount" /></B></label></td>
                        <td align="right" class="tdgridnew1" style="FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"   value="${inventoryForm.otherDiscount}"/></span>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="8" align="right" class="tdgridnew1" style="FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><label><B><bean:message key="label.common.GrandTotal" /></B></label></td>
                        <td align="right" class="tdgridnew1" style="FONT-SIZE: 11px; border-bottom:solid 1px #000; border-right:solid 1px #000;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"   value="${inventoryForm.finalamount}"/></span>
                            <input type="hidden"  name="totalLubesValue" value="" />
                        </td>
                    </tr>                                           
                    <tr>
                        <td colspan="9" height="20"></td>
                    </tr>
                    <tr>
                        <td colspan="9" align="left" style="FONT-SIZE: 12px;">        <p style=" float:left;">I here by certify that I have carefully read the instructions necessary for maintenance and proper use of Tractor.I will follow all the instructions falling which my warranty <br>stands cancelled.</p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="9" height="50"></td>
                    </tr>
                    <tr>
                        <td colspan="5" align="right" class="tdgridnew1" style="FONT-SIZE: 12px;">
                            <p class="" align="left" style="width: 20%!important; float:left!important;"><b>Customer Name & Signature <br><br> Date </b></p>
                        </td>
                        <td colspan="4" align="right" class="tdgridnew1" style="FONT-SIZE: 12px;">
                            <p class="" align="right" style="float: right!important; width: 100px!important"><b>Dealer representative Name & <br>Signature with Stamp</b></p>
                        </td>
                    </tr>
                </table>
            </c:if>
        </div>
    </div>
</center>