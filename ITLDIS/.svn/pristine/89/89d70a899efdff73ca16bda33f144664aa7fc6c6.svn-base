<%--
    Document   : estimate.jsp
    Created on : Apr 30, 2014, 03:25:09 PM
    Author     : Avinash.Pandey
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
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

    function validate()
    {
        if(document.getElementById("VIN No.").value==""){
              
            document.getElementById("msg_saveFAILED").innerHTML="Please Enter Chassis No."
            document.getElementById("VIN No.").select();
        }
        else{
            document.getElementById("form1").submit();
        }
       
    
    }
    function submitpdiviewform()
    {
        if(document.getElementById("Range").checked==true){
            if(document.getElementById("From Date").value==""){
                alert("Please Select From Date .");
                document.getElementById("From Date").focus();
                return false;
            }
            if(document.getElementById("To Date").value==""){
                alert("Please Select To Date .");
                document.getElementById("To Date").focus();
                return false;
            }
        }
            
        var  fDate=document.getElementById("From Date").value;
        fDate = trim(fDate);
        var x =fDate;     //'1-1-2015';
        var a = x.split('/');
        var dateF = new Date (a[2], a[1] - 1,a[0]);

        var  tDate=document.getElementById("To Date").value;
        tDate = trim(tDate);
        var y =tDate;     //'17-1-2015';
        var b = y.split('/');
        var dateT = new Date (b[2], b[1] - 1,b[0]);

        //alert(dateF)
        //alert(dateT)
        if(dateF>dateT){
            alert(date_validation_msg)
            document.getElementById('From Date').focus();
            return false;
        }


        document.getElementById("Srch").value=document.getElementById("vinNo").value;
        document.getElementById('eType').value=" ";
        document.getElementById("viewPDIList").submit();
    }
    function disableDate(dis)
    {
        if(dis.checked){
            document.getElementById("From Date").disabled=false;
            document.getElementById("To Date").disabled=false;
        }else{
            document.getElementById("From Date").value="";
            document.getElementById("From Date").disabled=true;
            document.getElementById("To Date").value="";
            document.getElementById("To Date").disabled=true;
        }
    }

    $(document).ready(function(){
        $('.viewPDIdata').click(function(){
            var url = "pdiServiceProcess.do?option=viewPDIData&vinNo="+Base64.encode($(this).attr('data-vinNo').trim())+"&pdiNo="+Base64.encode($(this).text().trim());
            $(this).attr('href',url);
        });
    });



    // BY APURV SINGH
     function Export()
    {
            if(document.getElementById("Range").checked==true){
                if(document.getElementById("From Date").value==""){
                alert("Please Select From Date .");
                document.getElementById("From Date").focus();
                return false;
                }
                if(document.getElementById("To Date").value==""){
                alert("Please Select To Date .");
                document.getElementById("To Date").focus();
                return false;
                }
            }

        var  fDate=document.getElementById("From Date").value;
        fDate = trim(fDate);
        var x =fDate;     //'1-1-2015';
        var a = x.split('/');
        var dateF = new Date (a[2], a[1] - 1,a[0]);

        var  tDate=document.getElementById("To Date").value;
        tDate = trim(tDate);
        var y =tDate;     //'17-1-2015';
        var b = y.split('/');
        var dateT = new Date (b[2], b[1] - 1,b[0]);

        //alert(dateF)
        //alert(dateT)
        if(dateF>dateT){
            alert(date_validation_msg)
            document.getElementById('From Date').focus();
            return false;
        }
        document.getElementById('eType').value="export";
        document.getElementById("viewPDIList").submit();
    }
   function downloadfile(filename)
    {
        var url=contextPath+"/DOCUMENTS/PDI-FILES/"+filename+".pdf";
        var win=window.open(url, '_blank',"directories=no,height=700,width=700,menubar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,top=0,location=no");
        win.focus();

    }
</script>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
            <li class="breadcrumb_link"><bean:message key="label.common.viewpdidetail" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED" align="center">${message}</div>

    <center>


        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.viewpdidetail" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <form name="viewPDIList" id="viewPDIList" method="post" action="<%=cntxpath%>/pdiServiceProcess.do?option=init_viewPDIDetails&jobType=PDI&flag=check" onsubmit="return false;">
                                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
                                    <tbody >
                                        <tr bgcolor="#FFFFFF">
                                            <td  style="width:10%"  class="headingSpas" nowrap align="right">
                                                <bean:message key="label.common.Chassisno" />
                                            </td>
                                            <td style="width:10%" align="left">
                                                <input name="vinNo" type="text" id="vinNo" value="${nameSrch}" onblur="this.value=TrimAll(this.value)"/>
                                            </td>
                                            <td  style="width:5% "  class="headingSpas" nowrap align="right">
                                                <c:if test="${range eq '1'}">
                                                    <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                                </c:if>
                                                <c:if test="${range ne '1' }">
                                                    <input type="checkbox" name="range" value="1"  id="Range" >
                                                </c:if>

                                                <bean:message key="label.common.pDIDonedate"/>
                                            </td>
                                            <td style="width:8%" align="left">
                                                <input name="fromDate" type="text" class="datepicker" id="From Date" style="width: 60px" value="${fromDate}" onkeydown="return false;"/>
                                            </td>
                                            <td  style="width:2% "  class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                                            <td style="width:8%" align="left">
                                                <input name="toDate" type="text" class="datepicker" id="To Date" style="width: 60px" value="${toDate}"  onkeydown="return false;"/>
                                            </td>
                                            <td  style="width:5% "  class="headingSpas" nowrap align="center"><bean:message key="label.common.status"/></td>
                                            <td style="width:10%" align="left">
                                                <select name="status" id="Status">
                                                    <c:if test="${pdiForm.status eq 'all'}">
                                                        <option value="all" selected>All</option>
                                                        <option value="ok">OK</option>
                                                        <option value="Atleast One NotOk">Atleast One NotOk</option>
                                                    </c:if>
                                                    <c:if test="${pdiForm.status eq 'ok'}">
                                                        <option value="all">All</option>
                                                        <option value="ok" selected>OK</option>
                                                        <option value="Atleast One NotOk">Atleast One NotOk</option>
                                                    </c:if>
                                                    <c:if test="${pdiForm.status eq 'Atleast One NotOk'}">
                                                        <option value="all">All</option>
                                                        <option value="ok">OK</option>
                                                        <option value="Atleast One NotOk" selected>Atleast One NotOk</option>
                                                    </c:if>
                                                </select>
                                            </td>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%>
                                            <td align="right" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                            <td align="left">
                                                <select id="Dealer Name" name="dealerCode" style="width:260px !important">
                                                    <%-- <option value='' >--Select Dealer--</option>--%>
                                                    <option value="ALL">ALL</option>
                                                    <c:forEach items="${dealerList}"  var="labelValue">
                                                        <c:if test="${pdiForm.dealerCode eq labelValue[0]}">
                                                            <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                        </c:if>
                                                        <c:if test="${pdiForm.dealerCode ne labelValue[0]}">
                                                            <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <%}%>
                                            <td style="padding-left:10px" align="left"  >
                                                <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                                
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td style="padding-left:10px" align="center" colspan="11">
                                                <input name="go" type="button" id="go" class="headingSpas1" value="search" onclick="submitpdiviewform()"/>
                                                <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="eType" id="eType" />
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </td>
                    </tr>
                    <c:if test="${!empty chassisList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="pdiServiceProcess.do" maxIndexPages="10" maxPageItems="15">
                                    <pg:param name="option" value="init_viewPDIDetails"/>
                                    <pg:param name="jobType" value="PDI"/>
                                    <pg:param name="Srch"  value='<%=request.getParameter("Srch")%>'/>
                                    <pg:param name="fromDate"  value='<%=request.getParameter("fromDate")%>'/>
                                    <pg:param name="toDate"  value='<%=request.getParameter("toDate")%>'/>
                                    <pg:param name="dealerCode"  value='${pdiForm.dealerCode}'/>
                                    <pg:param name="status"  value='${pdiForm.status}'/>
                                    <pg:param name="flag"  value='check'/>
                                    <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.Chassisno" />
                                            </td>
                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.pdiNo" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.pDIDonedate" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.Modelfamily" />
                                            </td>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%>
                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.dealerName" />
                                            </td>
                                            <td nowrap align="left" width="25%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.location" />
                                            </td>
                                            <%}%>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="chassisList" name="chassisList">
                                            <pg:item>
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" width="5%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left" width="10%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${chassisList.vinNo}</span></td>
                                                    <td align="left" nowrap width="20%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <%--<a href="${pageContext.request.contextPath}/pdiServiceProcess.do?option=viewPDIData&vinNo=${chassisList.vinNo}&pdiNo=${chassisList.pdiNo}" >--%>
                                                        <c:if test="${chassisList.refPDIno eq ''}">
                                                        <a href="#" class="viewPDIdata" data-vinNo="${chassisList.vinNo}" >
                                                            ${chassisList.pdiNo}
                                                        </a>
                                                        </c:if>
                                                        <c:if test="${chassisList.refPDIno ne ''}">
                                                            ${chassisList.pdiNo}<a onclick="downloadfile('${fn:replace(chassisList.vinNo, '/', '-')}');" href="javascript:void(0);" style="white-space: normal;vertical-align: middle"><img src="/ITLDIS/images/pdi_download.png" height="25px" width="25px" alt='Download File'  title="Click here view PDI">
                                                        </a>
                                                        </c:if>
                                                    </td>
                                                    <td align="left" width="10%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${chassisList.pdiDate}</span></td>
                                                    <td align="left" width="15%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${chassisList.modelFamily}</span></td>
                                                    <%if (userFunctionalities.contains("101")) {%>
                                                    <%} else {%>
                                                    <td align="left" width="20%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${chassisList.dealerCode} [${chassisList.dealerName}]</span></td>
                                                    <td align="left" width="25%" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${chassisList.locationName}</span></td>
                                                    <%}%>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <td colspan="5" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                <%} else {%>
                                            <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                <%}%>

                                                <pg:index>
                                                    <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                                                    <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                                                    <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                    <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                                                    <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                                                    </pg:index>
                                            </td>
                                        </tr>

                                    </table>
                                </pg:pager>
                            </td>
                        </tr>
                        <%-- <tr bgcolor="#FFFFFF">
                             <td style="padding-top:10px;color: red"><bean:message key="label.common.viewPDINote" /></td>
                         </tr>--%>
                    </c:if>
                    <c:if test="${empty chassisList}">
                        <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                            <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>
    </center>
</div>
