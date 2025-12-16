<%--
    Document   : addReorderPartToCart
    Created on : 29-Dec-2014, 15:05:47
    Author     : kundan.rastogi
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            String dealerCode = (String) session.getAttribute("dealerCode");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");

            String dealerFlag = "";
            if (!userFunctionalities.contains("101")) {
                dealerFlag = "true";
            }



%>
<script>
    <c:set var="dCode" value='<%=dealerCode%>'/>
        function search()
        {

            var dealerFlag='<%=dealerFlag%>';
            if(dealerFlag=='true')
            {
                var dealer=document.getElementById('Dealer Name').value;
                if(dealer=='')
                {
                    alert(not_blank_dropdown_validation_msg+'Dealer Name');
                    return false;
                }
            }
            document.getElementById("searchform").submit();
        }

        $(document).ready(function() {
            $('#selectall').click(function() {
                if(this.checked) {
                    $('.checkbox1').each(function() {
                        this.checked = true;
                    });
                }else{
                    $('.checkbox1').each(function() {
                        this.checked = false;
                    });
                }
            });
        });

        function submitForm(){

           if($('.checkbox1:checkbox:checked').length == 0){
                alert(atleastOneCheck_validation_msg +" check .");
                return false;
            }

       var checkArr=document.getElementsByName("checkedArr");
        var id=document.getElementsByName("quantity");
         
        for( var i=0;i<checkArr.length;i++){
                if(checkArr[i].checked && id[i].value==""){
                   // document.getElementById("ReplanishtoMax"+i).focus();
                    id[i].focus();
                    alert(not_blank_validation_msg +" Replanish To Max");
                    return false;
                }
                if(checkArr[i].checked && id[i].value<=0){
                   id[i].focus();
                    alert("Quantity "+ greater_than_val_msg+" 0");
                    return false;
                }
               if(checkArr[i].checked && !trim(id[i].value).match(/^[0-9]+$/)){
                   // document.getElementById("ReplanishtoMax"+i).focus();
                    //document.getElementById("ReplanishtoMax"+i).value="";
                    id[i].focus();
                    alert("Replanish To Max Qty " +numeric_msg);
                    id[i].value='';
                    return false;
                }
            }




         $('#myForm').submit();
        }


</script>
<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
    </head>
    <body>
        <div class="contentmain1">
            <div class="breadcrumb_container">
                <ul class="hText">
                    <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>

                    <li class="breadcrumb_link"><label><bean:message key="label.spare.addReorderPartstoCart" /></label></li>
                </ul>
            </div>
            <div class="message" id="msg_saveFAILED"></div>
            <center>
                <div class="content_right1">
                    <div class="con_slidediv1" style="position: relative;width: 100%">
                        <h1 class="hText"> <bean:message key="label.spare.addReorderPartstoCart" /></h1>
                        <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >
                            <form action="inventoryAction.do?option=addReorderPartToCart" onsubmit="" method="post" id="searchform">
                                <%if (userFunctionalities.contains("101")) {%>
                                <tr bgcolor="#FFFFFF">
                                    <td width="10%" align="right" style="padding-left: 5px" > <bean:message key="label.catalogue.partNo" />    </td>
                                    <td width="10%" align="left"><input type="text" name="partnum" style="width: 180px" value="${inventoryForm.partnum}"/>
                                     <%--<input type="button" value="GO" onclick="search()"/>--%>
                                     
                                    </td>
                                    <td width="10%" align="left">
                                       <input type="button" value="GO" onclick="search()"/>
                                    </td>
                                </tr>
                                <%} else {%>
                                <tr bgcolor="#FFFFFF">
                                    <td align="right" style="padding-left: 5px" > <bean:message key="label.catalogue.partNo" />    </td>
                                    <td align="left"><input type="text" name="partnum" style="width: 180px" value="${inventoryForm.partnum}"/></td>
                                    <td  align="right" class="headingSpas" nowrap style="padding-right:10px" > <bean:message key="label.common.dealerName" />    </td>
                                    <td align="left">
                                        <select id="Dealer Name" name="dealerCode" style="width:300px !important">
                                            <option value="" >--Select Here--</option>
                                            <c:forEach items="${dealerList}"  var="labelValue">
                                                <c:if test="${inventoryForm.dealerCode eq labelValue[0]}">
                                                    <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                </c:if>
                                                <c:if test="${inventoryForm.dealerCode ne labelValue[0]}">
                                                    <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                  
                                        <input type="button" value="GO" onclick="search()"/>
                                     </td>
                                </tr>
                                <%}%>
                            </form>
                            <c:if test="${empty dataList}">
                                <tr bgcolor="#FFFFFF">
                                    <td valign="top" align="center" colspan="10" class="headingSpas" style="padding-top:10px;color: red">
                                        <bean:message key="label.common.noRecordFound" />
                                    </td>
                                </tr>
                            </c:if>
                         <form action="inventoryAction.do?option=setReorderPartToCart" onsubmit="" method="post" id="myForm">
                            <c:if test="${!empty dataList}">
                                <tr>
                                    <%if (userFunctionalities.contains("101")) {%>
                                    <td colspan="3">
                                        <%} else {%>
                                    <td colspan="5">
                                        <%}%>
                                        <table width="100%" border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td  align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.catalogue.sNo" />   </B> </td>
                                                <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partno_small" /></B></td>
                                                <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partdesc_small" /></B></td>
                                                <%--<td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.spare.minQty" /> </B></td>
                                                <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.spare.maxQty" /> </B></td>--%>
                                                <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.currentQty" /> </B></td>
                                                <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.spare.reorderQty" /></B></td>
                                                <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.spare.maxQty" /> </B></td>
                                                <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.spare.replanishTomax" /> </B></td>
                                                <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B> <input type="checkbox" name="" id="selectall"><bean:message key="label.spare.checkAll" /> </B>
                                                </td>
                                            </tr>
                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="dataList" name="dataList">
                                                <tr bgcolor="#FFFFFF">
                                                    <td align="center" >${sno}</td>
                                                    <td align="left" ><span><input type="hidden" name="partNo" value="${dataList.partno}">${dataList.partno}</span></td>
                                                    <td align="left" ><span><input type="hidden" name="partDesc" value="${dataList.part_desc}">${dataList.part_desc}</span></td>
                                                    <td align="center" ><span id="MinLevel${sno}">${dataList.currQty}</span></td>
                                                    <td align="center" ><span id="ReorderLevel${sno}">${dataList.reorderLevel}</span></td>

                                                    <td align="center" ><span id="MaxLevel${sno}">${dataList.maxLevel}</span></td>
                                                    <td align="left" >
                                                       <%-- <input type="hidden" name="quantity" id="" value="${dataList.replanishtoMax}">--%>
                                                       <input type="text" name="quantity" id="ReplanishtoMax${sno}" value="${dataList.replanishtoMax}" maxlength="6">
                                                    </td>
                                                    <td align="center" >
                                                        <input type="checkbox" class="checkbox1" name="checkedArr" id="check" value="${sno}" maxlength="6">
                                                    </td>
                                                </tr>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </logic:iterate>
                                            <tr height="40" >
                                                <td colspan="8"  align="center" bgcolor="#FFFFFF"  >
                                                    <input type="button" name="Submit" value="Submit" onclick="submitForm()">
                                                </td>
                                            </tr>
                                            <tr>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </c:if>
                         </form>
                       </table>
                    </div>
                </div>
            </center>
        </div>
    </body>
</html>
