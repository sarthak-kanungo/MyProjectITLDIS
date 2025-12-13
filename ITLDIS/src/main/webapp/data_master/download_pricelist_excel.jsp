<%-- 
    Document   : download_pricelist_excel
    Created on : Jun 29, 2016, 12:12:06 PM
    Author     : ashutosh.kumar1
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
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript">

    function submitForm() {

        var exp= /^[a-zA-Z0-9_/-]*$/;
        if(document.getElementById("Price List Name").value==""){
            alert("Please Select Price.");
            document.getElementById("Price List Name").focus();
            return false;
        }
        if(document.getElementById("Part No").value!=""){
            if(!exp.test(document.getElementById("Part No").value)){
                alert("Part No field may contains alphanumeric , _ or - only.");
                document.getElementById("Part No").focus();
                return false;
            }
        }
        document.getElementById('searchBy').submit();
    }
    function donotsubmit()
    {
        return false;
    }
  
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/masterAction.do?option=initOptions'><bean:message key="label.common.Masters" /></a></li>
            <li class="breadcrumb_link"><label><bean:message key="label.common.downloadPriceList" /></label></li>
        </ul>
    </div>


    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1><bean:message key="label.common.downloadPriceList" /></h1>

                <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>

                    <tr height=50 bgcolor="#FFFFFF">
                        <td colspan="11" align= center class="headingSpas">
                            <html:form action="masterAction.do?option=priceListReoprt&flag=check" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>

                                    <tr bgcolor="#FFFFFF" >
                                        <td  style="padding-left:5px;width:12%" nowrap align="right" class="headingSpas"><bean:message key="label.common.priceList"/></td>


                                        <td width="15%" >
                                            <select id="Price List Name" name="priceList_code" style="width:150px !important">
                                                <%--<option value="ALL" >ALL</option>--%>
                                                <option value='' >--Select Dealer--</option>
                                                <c:forEach items="${priceList}"  var="labelValue">
                                                    <c:if test="${masterForm.dealer_code eq labelValue}">
                                                        <option value="${labelValue}" title="${labelValue}" selected>${labelValue} </option>
                                                    </c:if>
                                                    <c:if test="${masterForm.dealer_code ne labelValue}">
                                                        <option value="${labelValue}" title="${labelValue}">${labelValue}</option>
                                                    </c:if>

                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td >&nbsp;</td>
                                    </tr>
                                    <tr bgcolor="#FFFFFF" >

                                        <td  style="padding-left:5px;width:12%" nowrap align="right" class="headingSpas"><bean:message key="label.report.PartNo"/></td>


                                        <td  >
                                            <input  type="text" name="partNo" id="Part No" style="width:147px " />
                                        </td>
                                        <td  align="left" style="padding-left: 20px" >
                                            <%--<input name="Srch" type="text" id="Srch" value="" style="display: none"/>--%>
                                            <input name="go" type="button" value="Export" style="float:none; " onClick = "submitForm()"/>
                                        </td>


                                    </tr>

                                </table>
                            </html:form>
                        </td>
                    </tr>
                </table>

            </div>
        </div>
    </center>
</div>