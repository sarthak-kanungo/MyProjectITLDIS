<%-- 
    Document   : pendingPackingGenList
    Created on : Dec 3, 2014, 11:02:56 AM
    Author     : kundan.rastogi
--%>


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date,viewEcat.comEcat.PageTemplate,java.text.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var Today = new Date();
    Today.setTime(new Date().getTime()-30*1000*24*60*60);


    /* $(document).ready(function() {
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
    }); */
    
    $(document).ready(function() {
        $('#selectall').click(function() {
            if(this.checked) {
                $('.checkbox1').each(function(index) {

                    var cd= $('#myDate'+(index+1)).val();
                    var clDate= new Date(cd.split('/')[2], cd.split('/')[1]-1,cd.split('/')[0]);

                    <%--alert('clDate1:-'+clDate);
                    alert("Today:- "+ Today);--%>

                    if(clDate.getTime()>Today.getTime()) {
                        //alert('todays date is less than claimDate + 30 day');
                        this.checked = true;

                    }else{
                        alert('todays date is greater than claimDate + 30 day for row no :-'+(index+1)+' . Hence you can not check for packaging');
                        this.checked = false;
                    }
                    //this.checked = true;
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

    $(document).ready(function() {
        var row=$('#countId').val();
        for(var i=1;i<=row;i++){
            $('#check').click(function() {
                if(this.checked){
                    $('#WarClaimNo').val('Pending');
                }
                else {
                    $('#WarClaimNo').val(' ');
                }
            });
        }

        $('.viewJobCard').click(function(){
            var url = "serviceProcess.do?option=viewJobcard&vinNo="+Base64.encode($(this).attr("data-vinNo").trim()) +"&jobCardNo="+Base64.encode($(this).text().trim())+"&flag="+Base64.encode("penPackGen");
            $(this).attr("href",url);
        });
    });



    function submitForm(){
        if($('.checkbox1:checkbox:checked').length == 0){
            alert(atleastOneCheck_validation_msg+"Check For Packing");
        }
        else{
            var checkArr=document.getElementsByName("packArr");
            var more="";
            var less="";

            for(var i=0;i<checkArr.length;i++){
                
                if(checkArr[i].checked){
                    if($('#WarClaimDate'+checkArr[i].value).val() >= <%=df1.format(df.parse(PageTemplate.packingGenDateCheck))%>){
                        if(less!=""){
                            alert("Single packing list cannot be generated for claims before and after <%=PageTemplate.packingGenDateCheck%>. Please generate separate packing list for claims before <%=PageTemplate.packingGenDateCheck%> and claims after <%=PageTemplate.packingGenDateCheck%>");
                            return false;
                        }
                        more="more";
                    }else{
                        if(more!=""){
                            alert("Single packing list cannot be generated for claims before and after <%=PageTemplate.packingGenDateCheck%>. Please generate separate packing list for claims before <%=PageTemplate.packingGenDateCheck%> and claims after <%=PageTemplate.packingGenDateCheck%>");
                            return false;
                        }
                        less="less";
                    }
                }
            }

            $('#myForm').submit();
        }
    }
    
 function doalert(obj,cd){

        
        var clDate= new Date(cd.split('/')[2], cd.split('/')[1]-1,cd.split('/')[0]);
        var cnt = <%= request.getAttribute("cnt") %>;
   //     alert(cnt);

        <%--alert('clDate1:-'+clDate);
        alert("Today:- "+ Today);--%>
                if(obj.checked) {
                	
                	var futureDate = new Date(clDate.getTime() + cnt * 24 * 60 * 60 * 1000);

                    //if(clDate.getTime() + cnt >Today.getTime()) {
                	if(futureDate.getTime()  > Today.getTime()){
                        obj.checked = true;
                    }else{
                        alert('todays date is greater than claimDate + 30 day. Hence you can not check for packaging');
                        obj.checked = false;
                    }
                }
    }


</script>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.common.warranty" /></a></li>
            <li class="breadcrumb_link"><label class="hText"><bean:message key="label.warranty.pendingforPackGen" /></label></li>                            <%--<bean:message key="label.warranty.jobCardPendingForWarranty" />--%>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1><bean:message key="label.warranty.pendingforPackGen" /></h1>
                <form action="<%=cntxpath%>/warrantyAction.do" method="post" id="myForm"  >
                    <input type="hidden" name="option" value="getPendingDispatch" >


                    <%--  <div style="overflow:auto; position: relative;width: 100%; height:300px" >--%>
                    <table id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                        <tr  bgcolor="#eeeeee" height="20">
                            <td   align="center" width="6%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.S.No" /></b></td>
                            <td   align="left" width="15%"  class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.warrantyClaimNo" /></b></td>
                            <td   align="left" width="12%"  class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.claimDate" /></b></td>
                            <td   align="left" width="15%"  class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.common.jobcardno" /></b></td>
                            <td   align="left" width="15%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.vehicleNo" /></b></td>
                            <td   align="center" width="6%" class="headingSpas" nowrap>
                                <b> <input type="checkbox" name="" id="selectall"><bean:message key="label.warranty.packingList" />  </b>
                            </td>
                        </tr>

                        <c:if test="${!empty jobCardList}">
                            <c:set var="sno" value="1"></c:set>
                            <logic:iterate id="jobCardList" name="jobCardList">

                                <fmt:parseDate value="${jobCardList.claimDate}" pattern="dd/MM/yyyy" var="myDate"/>
                                <fmt:formatDate value="${myDate}" var="startFormat" pattern="yyyyMMdd"/>
                                <tr id ="${sno}" height="20">
                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                    <td align="left" bgcolor="#FFFFFF" title="${jobCardList.warrantyClaimNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                        <span id ="compType${sno}" >
                                            <a href="warrantyAction.do?option=viewWarrantyClaimDetail&warrntyClaimNo=${jobCardList.warrantyClaimNo}&flag=viewPack">${jobCardList.warrantyClaimNo} </a>
                                        </span>
                                        <input type="hidden" name="warClaimNoArr" id="WarClaimNo" class="warText" value="${jobCardList.warrantyClaimNo}">
                                        <input type="hidden" name="warClaimDateArr" id="WarClaimDate${sno}" class="warText" value="${startFormat}">
                                    	<input type="hidden" id="myDate${sno}" class="warText" value="${jobCardList.claimDate}">
                                    </td>
                                    <td align="left" bgcolor="#FFFFFF" title="${jobCardList.claimDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                        <span id ="compType${sno}" >
                                            ${jobCardList.claimDate}
                                        </span>
                                    </td>
                                    <td align="left" bgcolor="#FFFFFF" title=" ${jobCardList.jobCardNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                        <span id ="compType${sno}" >
                                            <%--  ${jobCardList.jobCardNo}--%>
                                            <a class="viewJobCard" href="#" data-vinNo="${jobCardList.vinNo}"> ${jobCardList.jobCardNo}</a>
                                        </span>
                                    </td>
                                    <td align="left" bgcolor="#FFFFFF" title="  ${jobCardList.vinNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                        <span id ="compType${sno}" >
                                            ${jobCardList.vinNo}
                                        </span>
                                    </td>
                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                        <span id ="st${sno}" >
                                            <input type="checkbox" class="checkbox1" name="packArr" id="check" value="${sno}" onchange="doalert(this, '${jobCardList.claimDate}')">
                                        </span>
                                    </td>
                                </tr>

                                <c:set var="sno" value="${sno + 1 }" />
                            </logic:iterate>

                            <tr height="40" >
                                <td colspan="6"  align="center" bgcolor="#FFFFFF"  >
                                    <input type="button" name="Submit" value="Submit" onclick="submitForm()">
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${empty jobCardList}">
                            <tr bgcolor="#FFFFFF">
                                <td valign="top" align="center" colspan="6" class="headingSpas" style="padding-top:10px;color: red">

                                    Warranty Claim not found for Packing Generation.

                                </td>
                            </tr>
                        </c:if>


                    </table>
                    <%-- </div>--%>
            </div>
            <%--  <div style=" padding-right: 10px;float: none;padding-top: 20px;padding-bottom: 10px;">
                  <input type="button" name="Submit" value="Submit" onclick="submitForm()">
              </div>--%>
            </form>
        </div>
</div>
</center>
</div>

