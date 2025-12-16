<%-- 
    Document   : counterSaleInit
    Created on : Mar 5, 2016, 3:39:58 PM
    Author     : Avinash.Pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%> 
<c:set var="contextPath" value='<%=request.getContextPath()%>'/>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>
<script src="js/Master_290414.js"></script>
<script src="js/intermediate.js"></script>
<link rel="stylesheet" href="css/Master_290414.css" type="text/css" >
<script language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
    function getCustNamebyId(val)
    {
        var todate=new Date().getTime();
        if(val!=undefined)
            var custcategoryID=val;
        var strURL = contextPath+"/inventoryAction.do?option=getcustNameByCustcategoryID&custcategoryID="+custcategoryID+"&todate="+todate;
        xmlHttp = GetXmlHttpObject();
        ajaxindicatorstart('loading data.. please wait..');
        xmlHttp.onreadystatechange = function()
        {
            stateChangedCustName();
        }
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);
        ajaxindicatorstop();

    }
    function stateChangedCustName()
    {
        
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")

        {
            res = trim(xmlHttp.responseText);
            var select_ops=res.split("||");
            var selectObj=document.forms[0].customerId;
            selectObj.options.length=1;
            for (var i=0; i<select_ops.length-1; i++){
                var warshow=select_ops[i].substring(0, select_ops[i].indexOf("$$"));
                var warval=select_ops[i].substring(select_ops[i].indexOf("$$")+2);
                var objopt=new Option(warval,warshow);
                objopt.id=warval;
                objopt.title=warval;
                selectObj.options[selectObj.options.length]=objopt;
            }
            document.forms[0].customerId.value="";
        }
        
    }
    function trim(str) {
        return str.replace(/^\s+|\s+$/g,"");
    }
    function validateGo()
    {
            var customerCategory=document.getElementById('Customer Category').value;
            if(customerCategory=='')
            {
                alert(not_blank_dropdown_validation_msg+'Customer Category');
                return false;
            }
            var customerName=document.getElementById('Customer Name').value;
            if(customerName=='')
            {
                alert(not_blank_dropdown_validation_msg+'Customer Name');
                return false;
            }
            document.getElementById("CounterSaleForm").submit();
    }
</script>
<html>
    <head><title>ITLDIS</title></head>
    <div class="contentmain1">
        <div class="breadcrumb_container">
            <ul class="hText">
                <li class="breadcrumb_link"><html:link action="/inventoryAction.do?option=initInvOptions"><bean:message key="label.common.spares" /></html:link></li>
                <li class="breadcrumb_link"><bean:message key="label.common.counterSale" /></li>
            </ul>
        </div>
        <center>
            <div class="message" id="msg_saveFAILED" align="center">${message}</div>
            <div class="content_right1">
                <div class="con_slidediv2" >
                    <h1 class="hText"><bean:message key="label.common.counterSale" />  </h1>
                    <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                        <tr height=50 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">

                                <html:form action="inventoryAction.do?option=counterSale" method="post" styleId="CounterSaleForm" onsubmit="return false;">
                                    <input type="hidden" name="<%=Constants.TOKEN_KEY%>" value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" />
                                    <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#FFFFFF>
                                        <tr bgcolor="#FFFFFF">
                                            <td width="20%" bgcolor="#FFFFFF" style="padding-left:10px; padding-right: 10px" class="headingSpas" nowrap align="right" >
                                                <span class="formLabel"><bean:message key="label.spare.customerCategory" /></span>
                                            </td>
                                            <td  width="20%"align="left" bgcolor="#FFFFFF">
                                                <html:select property="custcategoryID" styleId="Customer Category" styleClass="headingSpas" style="width:150px !important" onchange="javascript:getCustNamebyId(this.value)">
                                                    <html:option value="">--Select--</html:option>
                                                    <c:forEach items="${categoryList}" var="dataList">
                                                        <html:option value ="${dataList.value}">${dataList.label}</html:option>
                                                    </c:forEach>
                                                </html:select>
                                            </td>
                                            <td  width="15%" style="padding-left:5px; padding-right: 5px" class="headingSpas" nowrap align="right" >
                                                <span class="formLabel"><bean:message key="label.common.CustomerName" /></span>
                                            </td>
                                            <td align="left" width="25%">
                                                <html:select property="customerId" styleId="Customer Name"  styleClass="headingSpas" style="width:250px !important">
                                                    <html:option value="">--Select--</html:option>
                                                </html:select>
                                            </td>

                                            <td width="20%"  height="25" align="left" style="padding-right:5px; padding-top:5px">
                                                <input type="button" name="Submit" class="text" value="Go"  onclick="return validateGo()"/>
                                            </td>
                                    </table>
                                </html:form>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </center>
    </div>
</body>
</html>

