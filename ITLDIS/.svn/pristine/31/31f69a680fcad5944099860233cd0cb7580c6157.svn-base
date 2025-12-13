<%-- 
    Document   : viewTaxAssignToDealer
    Created on : Apr 14, 2016, 11:02:08 AM
    Author     : prashant.kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Tax Assign to Dealer</title>
        <script src="<%=cntxpath%>/js/intermediate.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#selectall').click(function() {
                    if(this.checked) {
                        $('.checkbox1').each(function() {
                            this.checked = true;
                            // $('.warText').val('Pending');
                        });
                    }else{
                        $('.checkbox1').each(function() {
                            this.checked = false;
                            // $('.warText').val(' ');
                        });
                    }
                });
            });

           
            function submitForm(){
                
                var rowLength=document.getElementById("data").rows.length-2;
                var dealerCodeArr= new Array(rowLength);
                var commodityCodeArr=new Array(rowLength);
                var effectiveDateArr=new Array(rowLength);
                if($('.checkbox1:checkbox:checked').length == 0){
                    alert(atleastOneCheck_validation_msg+"Assign Tax to Dealer");
                }
                else{
                    for(var i=0;i<rowLength;i++){
                        var id=document.getElementById("check"+(i+1));
                        if(id.checked) {
                            dealerCodeArr[i]=document.getElementById("dealerCodeArr"+(i+1)).value;
                            commodityCodeArr[i]=document.getElementById("commodityCodeArr"+(i+1)).innerHTML;
                            effectiveDateArr[i]=document.getElementById("effectiveDateArr"+(i+1)).innerHTML;
                        }else{
                            dealerCodeArr[i]="";
                            commodityCodeArr[i]="";
                            effectiveDateArr[i]="";
                        }
                    }
                    document.getElementById("commodityCode").value=commodityCodeArr;
                    document.getElementById("effectiveDate").value=effectiveDateArr;
                    document.getElementById("dealerCode").value=dealerCodeArr;

                    if (confirm('Do you want to delete selected Assign Taxes to Dealer') == true) {
                        $('#myForm').submit();
                    }
                }
            }

                function validate(){
                    document.getElementById("msg_saveFAILED").innerHTML=""
                    document.getElementById("masterForm").submit();
                }


                function newTaxAssignedToDealer(){
                    document.getElementById("msg_saveFAILED").innerHTML=""
                    var url="<%=cntxpath%>/masterAction.do?option=addTaxAssignedToDealer";
                    var left = (screen.width/2)-(800/2);
                    var top = (screen.height/2)-(500/2);
                    window.open(url, "TaxAssigned", 'directories=no,height=350,width=650,resizable=no,scrollbars=no,location=no ,status=no,titlebar=no,toolbar=no, addressbar=no, top='+top+', left='+left+"'");
                }
        </script>
    </head>
    <body>
        <div class="contentmain1">
            <div class="breadcrumb_container">
                <ul>
                    <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initOptions'><bean:message key="label.common.Masters"/></a></li>
                    <li class="breadcrumb_link"><label class="hText"><bean:message key="label.common.viewTaxAssignedToDealer"/></label></li>
                </ul>
            </div>
            <br/>

            <div class="message" id="msg_saveFAILED">
                <c:if test="${not empty message}">
                    ${message}
                </c:if>
            </div>
            <div class="content_right1">
                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1 class="hText"><bean:message key="label.common.viewTaxAssignedToDealer"/></h1>
                    <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                        <tr bgcolor="#FFFFFF">
                            <td align="center" class="headingSpas">
                                <html:form action="masterAction.do?option=initTaxAssignToDealerMasters" method="post" styleId="masterForm" >
                                    <table width=100%  border=0 cellspacing=0 cellpadding=0>
                                        <tr bgcolor="#FFFFFF">
                                            <td  width="10%" class="headingSpas" nowrap align="right" style="padding-left: 3px;"><bean:message key="label.common.dealerCode"/></td>
                                            <td style="padding-left:2px" width="10%" align="left">
                                                <html:text property="dealerCode" styleClass="headingSpas" maxlength="15" onblur="this.value=TrimAll(this.value);"/>
                                            </td>                                                                                    
                                            <td  width="10%" class="headingSpas" nowrap align="right" style="padding-left: 3px;"><bean:message key="label.common.stateName"/></td>
                                            <td style="padding-left:2px" width="10%" align="left">
                                                <html:select property="stateName" styleId="stateNameId">
                                                    <html:option value=""><bean:message key="label.common.selectoption"/></html:option>
                                                    <html:optionsCollection property="stateNameList" name="masterForm" label="label" value="value" />
                                                </html:select>
                                            </td>   

                                            <td style="padding-left:5px" width="15%" align="left" >
                                                <html:submit property="Go" value="Search" styleId="Go" styleClass="headingSpas" onclick="validate()"/>
                                            </td>
                                            <% if (userFunctionalities.contains("539")) {%>
                                            <td style="padding-right:5px" width="15%" align="right" >
                                                <B><a href='javascript:void(0)' onclick="newTaxAssignedToDealer()"><bean:message key="label.common.newTaxAssignedToDealer"/></a></B>
                                            </td>
                                            <%}%>

                                        </tr>
                                    </table>
                                </html:form>
                            </td>
                        </tr>
                        <c:if test="${empty taxAssignedList}">
                            <tr bgcolor="#FFFFFF">
                                <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                    <bean:message key="label.common.noRecordFound"/>
                                </td>
                            </tr>
                        </c:if>
                    </table>
                    <form action="<%=cntxpath%>/masterAction.do" method="post" id="myForm" name="myForm">
                        <input type="hidden" name="option" value="deleteTaxAssignedToDealer" >
                        <input type="hidden" name="dealerCode" id="dealerCode" value="" >
                        <input type="hidden" name="commodityCodeTax"  id="commodityCode" value="" >
                        <input type="hidden" name="effectiveDate" id="effectiveDate" value="" >
                        <div style="overflow: auto">
                            <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                                <c:if test="${!empty taxAssignedList}">

                                    <tr bgcolor="#FFFFFF">
                                        <td  align= center class="headingSpas">
                                            <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bgcolor=#cccccc>
                                                <tr bgcolor="#eeeeee" height="25px">
                                                    <td   align="center" width="10%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >S.No</td>
                                                    <td   align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" > <bean:message key="label.common.dealer"/></td>
                                                    <td   align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" > <bean:message key="label.common.commodityCode"/></td>
                                                    <td   align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" > <bean:message key="label.common.effectiveDate"/></td>
                                                    <td   align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" > <bean:message key="label.master.taxCategory"/></td>
                                                    <td   align="center" width="6%" class="headingSpas" nowrap>
                                                        <b> <input type="checkbox" name="" id="selectall"> Delete All </b>
                                                    </td>
                                                </tr>
                                                <c:set var="sno" value="1"></c:set>
                                                <logic:iterate id="taxAssign" name="taxAssignedList">

                                                    <tr bgcolor="#FFFFFF" >
                                                        <td   align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >
                                                            ${sno}
                                                        </td>
                                                        <td   align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >
                                                            ${taxAssign.dealer_code}
                                                            <input type="hidden" id="dealerCodeArr${sno}" value="${taxAssign.dealerCode}">
                                                        </td>


                                                        <td   align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >
                                                            <span id ="commodityCodeArr${sno}">${taxAssign.commodityCodeTax}
                                                            </span>
                                                        </td>
                                                        <td   align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >
                                                            <span id ="effectiveDateArr${sno}">${taxAssign.effectiveDate}</span>

                                                        </td>
                                                        <td   align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >
                                                            <span id ="compType${sno}">${taxAssign.taxCategory}
                                                            </span>
                                                        </td>
                                                        <td align="center" width="10%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >
                                                            <span id ="st${sno}" >
                                                                <input type="checkbox" class="checkbox1" name="taxListArr" id="check${sno}" value="${sno}">
                                                            </span>
                                                        </td>

                                                    </tr>

                                                    <c:set var="sno" value="${sno + 1}"></c:set>
                                                </logic:iterate>
                                                <tr height="40" >
                                                    <td colspan="6"  align="center" bgcolor="#FFFFFF"  >
                                                        <input type="button" name="Submit" value="Submit" onclick="submitForm()">
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </c:if>
                            </table>
                        </div>
                    </form>
                </div>
            </div>           
        </div>
    </body>

</html>
