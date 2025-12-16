<%-- 
    Document   : sparesAndLubesReport
    Created on : Feb 24, 2016, 3:10:21 PM
    Author     : prashant.kumar
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
         //   String cntxpath = request.getContextPath();
         //   Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            Calendar cal = Calendar.getInstance();
            int baseYear = 2015;
            int yearNext = cal.get(Calendar.YEAR) + 1;
            List yrList = new ArrayList();
            for (int i = baseYear; i < yearNext; i++) {
                yrList.add((i) + "-" + (i + 1));
            }

%>
<c:set var="yrList" value='<%=yrList%>'/>
<script src="js/Master_290414.js"></script>
<script src="js/intermediate.js"></script>
<link rel="stylesheet" href="css/Master_290414.css" type="text/css" >

<script type="text/javascript">
        
    function submitSearchByForm(){
        
        var country=document.getElementById("countryId").value;        
        var zone=document.getElementById("zoneId").value;        
        var state=document.getElementById("stateId").value;
        var ccm=document.getElementById("ccmId").value;
        var cce=document.getElementById("cceId").value;
        var dealer=document.getElementById("dealerId").value;
        var finYear=document.getElementById("finYearId").value;
        var month=document.getElementById("monthId").value;
        var week=document.getElementById("weekId").value;
        
        var url="reportAction.do?option=spareAndLubeReportSTK&flag=STK&country="+country+ "&zone="+zone
            + "&state="+state+ "&ccm=" + ccm +"&cce=" + cce +"&dealer=" + dealer + "&finYear=" + finYear
            + "&month=" + month + "&week="+week;
        
        window.open(encodeURI(url));
    }    

     function getListOnSelectCountry(obj) {
         var conId=obj.toString();
        conId = replaceall(conId, " ", "@");       

            if(conId=='All')
            {
                $('Select[name="zone"] option').remove();
                $('Select[name="zone"]').append($("<option>").attr("value", 'All').text('-ALL-'));
            }
            else
            {
                    $.ajax({
                        type: "POST",
                        url: "reportAction.do?option=getSubFieldDetails&getColName=PKZoneID,ZoneDesc&whereColName=COUNTRY&flag=STK&colValue="+conId,

                        success: function(response){

                            if(response!=""){
                                var array = response.split("@");
                                $('Select[name="zone"] option').remove();
                                $('Select[name="zone"]').append($("<option>").attr("value", "All").text('-ALL-'));

                                for (i=0;i<array.length;i=i+2){
                                    $('Select[name="zone"]').append($("<option>").attr("value", array[i]).text(array[i+1]));
                                }
                            }
                            else{
                                $('Select[name="zone"] option').remove();
                                $('Select[name="zone"]').append($("<option>").attr("value", 'All').text('-ALL-'));
                            }


                        },
                        error: function(e){
                            alert('Error: ' + e);
                        }
                    });
            }


                $('Select[name="state"] option').remove();
                $('Select[name="state"]').append($("<option>").attr("value", 'All').text('-ALL-'));

                $('Select[name="ccm"] option').remove();
                $('Select[name="ccm"]').append($("<option>").attr("value", 'All').text('-ALL-'));

                $('Select[name="cce"] option').remove();
                $('Select[name="cce"]').append($("<option>").attr("value", 'All').text('-ALL-'));

                $('Select[name="dealer"] option').remove();
                $('Select[name="dealer"]').append($("<option>").attr("value", 'All').text('-ALL-'));
    }

    function getListOnSelectZone(obj) {
        var zoneId=obj;

        if(zoneId=='All')
        {
             $('Select[name="state"] option').remove();
             $('Select[name="state"]').append($("<option>").attr("value", 'All').text('-ALL-'));
        }
        else
        {
                 $.ajax({
                type: "POST",
                url: "reportAction.do?option=getSubFieldDetails&getColName=StateID,StateName&whereColName=PKZoneID&flag=STK&colValue="+zoneId,


                success: function(response){
                    if(response!=""){
                    var array = response.split("@");
                    $('Select[name="state"] option').remove();
                    $('Select[name="state"]').append($("<option>").attr("value", 'All').text('-ALL-'));

                    for (i=0;i<array.length;i=i+2){
                        $('Select[name="state"]').append($("<option>").attr("value", array[i]).text(array[i+1]));
                    }
                    }else{
                        $('Select[name="state"] option').remove();
                        $('Select[name="state"]').append($("<option>").attr("value", 'All').text('-ALL-'));
                    }

                },
                error: function(e){
                    alert('Error: ' + e);
                }
            });
        }

          $('Select[name="ccm"] option').remove();
            $('Select[name="ccm"]').append($("<option>").attr("value", 'All').text('-ALL-'));

            $('Select[name="cce"] option').remove();
            $('Select[name="cce"]').append($("<option>").attr("value", 'All').text('-ALL-'));

            $('Select[name="dealer"] option').remove();
            $('Select[name="dealer"]').append($("<option>").attr("value", 'All').text('-ALL-'));
       
    }

     function getListOnSelectState(obj) {
        var stateId=obj;    
        
        if(stateId=='All')
        {
             $('Select[name="ccm"] option').remove();
             $('Select[name="ccm"]').append($("<option>").attr("value", 'All').text('-ALL-'));
        }
        else
        {
            $.ajax({
            type: "POST",
            url: "reportAction.do?option=getSubFieldDetails&getColName=CCMID,CCM&whereColName=STATEID&flag=STK&colValue="+stateId,

            success: function(response){
                 if(response!=""){
                var array = response.split("@");
                $('Select[name="ccm"] option').remove();
                $('Select[name="ccm"]').append($("<option>").attr("value", 'All').text('-ALL-'));

                for (i=0;i<array.length;i=i+2){
                    $('Select[name="ccm"]').append($("<option>").attr("value", array[i]).text(array[i+1]));
                }
                 }else{
                    $('Select[name="ccm"] option').remove();
                    $('Select[name="ccm"]').append($("<option>").attr("value", 'All').text('-ALL-'));
                }
            
                },
                error: function(e){
                    alert('Error: ' + e);
                }
            });
        }

        $('Select[name="cce"] option').remove();
        $('Select[name="cce"]').append($("<option>").attr("value", 'All').text('-ALL-'));

        $('Select[name="dealer"] option').remove();
        $('Select[name="dealer"]').append($("<option>").attr("value", 'All').text('-ALL-'));
    }

    function getListOnSelectCcm(obj) {
        var ccmId=obj;

        if(ccmId=='All')
        {
             $('Select[name="cce"] option').remove();
             $('Select[name="cce"]').append($("<option>").attr("value", 'All').text('-ALL-'));
        }
        else
        {
            $.ajax({
                type: "POST",
                url: "reportAction.do?option=getSubFieldDetails&getColName=CCEID,CCE&whereColName=CCMID&flag=STK&colValue="+ccmId,

                success: function(response){
                    if(response!=""){
                        var array = response.split("@");
                        $('Select[name="cce"] option').remove();
                        $('Select[name="cce"]').append($("<option>").attr("value", 'All').text('-ALL-'));

                        for (i=0;i<array.length;i=i+2){
                            $('Select[name="cce"]').append($("<option>").attr("value", array[i]).text(array[i+1]));
                        }
                    }
                    else{
                        $('Select[name="cce"] option').remove();
                        $('Select[name="cce"]').append($("<option>").attr("value", 'All').text('-ALL-'));
                    }


                  <%--getListOnSelectCce("%");
                  getListOnSelectDealer("%");--%>
                },

                error: function(e){
                    alert('Error: ' + e);
                }
            });
        }
         $('Select[name="dealer"] option').remove();
             $('Select[name="dealer"]').append($("<option>").attr("value", 'All').text('-ALL-'));
    }

     function getListOnSelectCce(obj) {
        var cceId=obj;

        if(cceId=='All')
        {
             $('Select[name="dealer"] option').remove();
                $('Select[name="dealer"]').append($("<option>").attr("value", 'All').text('-ALL-'));
        }
        else
            {

            $.ajax({
                type: "POST",
                url: "reportAction.do?option=getSubFieldDetails&getColName=DEALER_CODE,DEALER_NAME,LOCATION&whereColName=CCEID&flag=STK&colValue="+cceId,

                success: function(response){
                     if(response!=""){
                    var array = response.split("@");
                    $('Select[name="dealer"] option').remove();
                    $('Select[name="dealer"]').append($("<option>").attr("value", 'All').text('-ALL-'));

                    for (i=0;i<array.length;i=i+2){                        
                        $('Select[name="dealer"]').append($("<option>").attr("value", array[i]).text(array[i]+array[i+1]));
                    }
                   }
                },
                error: function(e){
                    alert('Error: ' + e);
                }
            });
        }
    }
</script>

<html>
    <head><title>ITLDIS</title></head>
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/reportAction.do?option=options'><bean:message key="label.report.report" /></a></li>
            <li><bean:message key="label.report.spareAndLubeSTK" /></li>
        </ul>
    </div>

    <center>
        <div class="content_right1">
            <div class="con_slidediv2" >
                <h1 class="hText"><bean:message key="label.report.spareAndLubeSTK" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">

                            <html:form action="reportAction.do?option=spareAndLubeReportSTK&flag=STK" method="post" styleId="searchBy" onsubmit="return false;">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#FFFFFF>
                                    <tr bgcolor="#FFFFFF">
                                        <td bgcolor="#FFFFFF" style="padding-left:10px; padding-right: 10px" class="headingSpas" nowrap align="right" width="60px">
                                            <span class="formLabel"><bean:message key="label.common.Country" /></span>
                                        </td>
                                        <td align="left" bgcolor="#FFFFFF">
                                            <html:select property="country" styleId="countryId" styleClass="headingSpas" style="width:120px !important" onchange="getListOnSelectCountry(this.value)"> 
                                                <html:option value="All">-ALL-</html:option>
                                                <c:forEach items="${country}" var="con">
                                                    <html:option value ="${con}">${con} </html:option>
                                                </c:forEach>                                             
                                            </html:select>
                                        </td>                                     
                                        <td  style="padding-left:10px; padding-right: 10px" class="headingSpas" nowrap align="right" width="60px">
                                            <span class="formLabel"><bean:message key="label.common.zone" /></span>
                                        </td>
                                        <td align="left">
                                            <html:select property="zone" styleId="zoneId"  styleClass="headingSpas" style="width:120px !important" onchange="getListOnSelectZone(this.value)">
                                                <html:option value="All">-ALL-</html:option>
                                                <c:forEach items="${zone}"  var="zone">
                                                    <c:set var="zone" value="${fn:split(zone, ',')}" />
                                                    <html:option value ="${zone[1]}">${zone[0]} </html:option>
                                                    <html:option value ="${zone}">${zone} </html:option>
                                                </c:forEach>
                                            </html:select>
                                        </td>
                                        <td  style="padding-left:10px; padding-right: 10px" class="headingSpas" nowrap align="right" width="60px">
                                            <span class="formLabel"><bean:message key="label.common.state" /></span>
                                        </td>
                                        <td align="left">
                                            <html:select property="state" styleId="stateId" styleClass="headingSpas" style="width:120px !important" onchange="getListOnSelectState(this.value)">
                                                <html:option value="All">-ALL-</html:option>
                                                <c:forEach items="${state}"  var="state">
                                                    <c:set var="state" value="${fn:split(state, ',')}" />
                                                    <html:option value ="${state[1]}">${state[0]} </html:option>                                                    
                                                </c:forEach>
                                            </html:select>
                                        </td>
                                    <tr>
                                        <td  style="padding-left:10px; padding-right: 10px; padding-top: 10px" class="headingSpas" nowrap align="right" width="60px">
                                            <span class="formLabel"><bean:message key="label.common.spm" /></span>
                                        </td>
                                        <td align="left" style="padding-top: 10px">
                                            <html:select property="ccm" styleId="ccmId" styleClass="headingSpas" style="width:120px !important" onchange="getListOnSelectCcm(this.value)">
                                                <html:option value="All">-ALL-</html:option>
                                                <c:forEach items="${ccm}"  var="ccm">
                                                    <c:set var="ccm" value="${fn:split(ccm, ',')}" />
                                                    <html:option value ="${ccm[1]}">${ccm[0]} </html:option>                                                    
                                                </c:forEach>
                                            </html:select>
                                        </td>

                                        <td  style="padding-left:10px; padding-right: 10px;padding-top: 10px" class="headingSpas" nowrap align="right" width="60px">
                                            <span class="formLabel"><bean:message key="label.common.spe" /></span>
                                        </td>
                                        <td align="left" style="padding-top: 10px">
                                            <html:select property="cce" styleId="cceId" styleClass="headingSpas" style="width:120px !important" onchange="getListOnSelectCce(this.value)">
                                                <html:option value="All">-ALL-</html:option>
                                                <c:forEach items="${cce}"  var="cce">
                                                    <c:set var="cce" value="${fn:split(cce, ',')}" />
                                                    <html:option value ="${cce[1]}">${cce[0]} </html:option>                                                   
                                                </c:forEach>
                                            </html:select>
                                        </td>                                 
                                        <td  style="padding-left:10px; padding-right: 10px; padding-top: 10px" class="headingSpas" nowrap align="right" width="60px">
                                            <span class="formLabel"><bean:message key="label.common.stockist" /></span>
                                        </td>
                                        <td align="left" style="padding-top: 10px" colspan="3">
                                            <html:select property="dealer" styleId="dealerId" styleClass="headingSpas" style="width:330px !important">
                                                <html:option value="All">-ALL-</html:option>
                                                <c:forEach items="${dealer}"  var="dealer">
                                                    <c:set var="dealerCode" value="${fn:split(dealer, ',')}" />
                                                    <html:option value ="${dealerCode[1]}">${dealerCode[0]}[${dealerCode[1]}] </html:option>                                                    
                                                </c:forEach>
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr style="padding-top: 10px">
                                        <td  style="padding-left:10px; padding-right: 10px; padding-top: 10px" class="headingSpas" nowrap align="right" width="100px">
                                            <span class="formLabel"><bean:message key="label.common.finYear" /></span>
                                        </td>
                                        <td align="left" style="padding-top: 10px">
                                            <html:select property="finYear" styleId="finYearId" styleClass="headingSpas" style="width:120px !important">
                                                <c:forEach items="${yrList}"  var="yr">
                                                    <html:option value ="${yr}">${yr}</html:option>
                                                </c:forEach>
                                            </html:select>
                                        </td>
                                        <td  style="padding-left:10px; padding-right: 10px; padding-top: 10px" class="headingSpas" nowrap align="right" width="80px">
                                            <span class="formLabel"><bean:message key="label.common.month" /></span>
                                        </td>
                                        <td align="left" style="padding-top: 10px">
                                            <html:select property="month" styleId="monthId" styleClass="headingSpas" style="width:120px !important">                                                
                                                <html:option value="1">January</html:option>
                                                <html:option value="2">February</html:option>
                                                <html:option value="3">March</html:option>
                                                <html:option value="4">April</html:option>
                                                <html:option value="5">May</html:option>
                                                <html:option value="6">June</html:option>
                                                <html:option value="7">July</html:option>
                                                <html:option value="8">August</html:option>
                                                <html:option value="9">September</html:option>
                                                <html:option value="10">October</html:option>
                                                <html:option value="11">November</html:option>
                                                <html:option value="12">December</html:option>
                                            </html:select>
                                        </td>
                                        <td  style="padding-left:10px; padding-right: 10px; padding-top: 10px" class="headingSpas" nowrap align="right" width="80px">
                                            <span class="formLabel"><bean:message key="label.common.week" /></span>
                                        </td>
                                        <td align="left" style="padding-top: 10px">
                                            <html:select property="week" styleId="weekId" styleClass="headingSpas" style="width:120px !important">                                                
                                                <html:option value="1">Week - 1</html:option>
                                                <html:option value="2">Week - 2</html:option>
                                                <html:option value="3">Week - 3</html:option>
                                                <html:option value="4">Week - 4</html:option>
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="11" style="padding-right:80px;padding-top:5px" align="right" >
                                            <input name="submit" type="submit" value="Submit" class="headingSpas" onclick="submitSearchByForm();"/>
                                        </td>
                                    </tr>
                                    <tr>
                                    </tr>
                                    <tr><td>&nbsp;</td></tr>
                                </table>
                            </html:form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </center>
</body>
</html>


