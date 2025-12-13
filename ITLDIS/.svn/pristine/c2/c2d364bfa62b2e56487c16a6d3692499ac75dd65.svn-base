<%-- 
    Document   : printprintExportDetail
    Created on : Dec 16, 2015, 11:03:39 AM
    Author     : Ashutosh.Kumar1
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
%>

<script>
    function printview(obj){
        obj.style.display='none';
        window.print();
        window.onfocus=function(){  obj.style.display='block';}
    }

</script>


<center style="background-color:#FFFFFF">
    <div class="content_right1">
        <div class="con_slidediv2" style="position: relative;width: 100%">
            <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" class="LiteBorder" style="FONT-SIZE: 11px;">
                <tr >
                    <td  class="headingSpas hText" align="center" style="padding:3px;">

                    </td>
                    <td align="right"><a href="#"><img src='<%=cntxpath%>/image/print-icon.png' alt='Get Suggestions' name="img1" border='0' title="print"  onclick="printview(this);"/></a></td>
                </tr>
                <tr>
                    <td colspan="2">

                            <table width="100%" border="0" align="center" cellpadding="3" cellspacing="0" bordercolor=#cccccc >
                                <tr height=20 bgcolor="#FFFFFF">

                                <td align="right" ><label ><B><bean:message key="label.common.stockAdjno" />: </B></label></td>
                                <td align="left" ><span>${serviceForm.stkAdjNo}</span>
                                </td>

                                <td align="right" ><span><label ><B><bean:message key="label.common.stockAdjdate" />: </B></label></span></td>
                               <td align="left" ><span>${serviceForm.stkAdjDate}</span>
                               </td>

                              </tr>
                              <tr height=20 bgcolor="#FFFFFF">

                               <td align="right" ><label ><B><bean:message key="label.common.totalpurchageval" />: </B></label></td>
                               <td align="left" ><span>${serviceForm.totalPuschaseValue}</span>
                               </td>
                                <td align="right" ><label ><B><bean:message key="label.common.totalsalesval" />: </B></label></td>
                                <td align="left" ><span>${serviceForm.totalSaleValue}</span>
                                </td>
                              </tr>
                              <tr height=20 bgcolor="#FFFFFF">&nbsp;</tr>

                              <c:if test="${!empty datalist}">
                              <tr bgcolor="#eeeeee" height="20" class="hText">
                                  <td align="left" colspan="6" ><B><bean:message key="label.common.label4viewpart" /></B></td>
                              </tr>
                              <tr height=20 bgcolor="#FFFFFF">
                                  <td align="left" colspan="6">
                                      <table  border="0" cellspacing="3" cellpadding="3" width="100%" style="FONT-SIZE: 11px;">
                                          <tr class="grid">
                                              <td align="center" width="6%"><B><bean:message key="label.common.sno" /></B></td>
                                              <td align="left" width="10%"><B><bean:message key="label.common.partno_small" /></B></td>
                                              <td align="left" width="20%"><B><bean:message key="label.common.partdesc_small" /></B></td>
                                              <td align="center" width="8%"><B><bean:message key="label.common.qty" /></B></td>
                                              <td align="center" width="8%"><B>  <bean:message key="label.common.unitprice_small" /></B></td>
                                              <td align="left" width="8%"><B><bean:message key="label.common.salepurchage" /></B></td>
                                              <td align="left" width="18%"><B><bean:message key="label.common.remarks" /> </B></td>


                                          </tr>
                                          <c:set var="j" value="1"></c:set>
                                          <c:forEach items="${datalist}" var="datalist">
                                              <tr >
                                                  <td align="center" class="tdgridnew1" style="FONT-SIZE: 11px;">${j}</td>
                                                  <td align="left"><span>${datalist.partNoStr}</span>

                                                  </td>
                                                  <td align="left"><span>${datalist.partDesc_str}</span>

                                                  </td>
                                                  <td align="center"><span>${datalist.qty_str}</span>

                                                  </td>
                                                    <td align="center"  ><span>${datalist.unitprice_str}</span>

                                                  </td>
                                                  <td align="left"><span>${datalist.salePurchage}</span>

                                                  </td>
                                                  <td align="left"><span>${datalist.remarks}</span>
                                                  </td>



                                              </tr>
                                              <c:set var="j" value="${j + 1 }" />
                                          </c:forEach>

                                          <%--  <tr class="grid">
                                                <td colspan="6" align="right" width="6%" ><B><bean:message key="label.common.totalpurchageval" /> </B></td>
                                                <td align="right" width="6%"><B>${serviceForm.totalPuschaseValue}</B></td>


                                          </tr>
                                            <tr class="grid">
                                                <td colspan="6" align="right" width="6%" ><B><bean:message key="label.common.totalsalesval" /> </B></td>
                                                <td align="right" width="6%"><B>${serviceForm.totalSaleValue}</B></td>


                                          </tr>--%>
                                   </table>
                                  </td>
                              </tr>
                              </c:if>


                              </table>
                              </td>
                    </tr>
                </table>
        </div>
    </div>
</center>










