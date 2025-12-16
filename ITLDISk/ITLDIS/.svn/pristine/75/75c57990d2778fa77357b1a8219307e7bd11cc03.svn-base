<%-- 
    Document   : viewBajajExtendedWarrantyInvList
    Created on : July 11, 2018, 12:41:46 PM
    Author     : avinash.pandey
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
<script>

    function search(){

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
        if(dateF>dateT){
            alert(date_validation_msg)
            document.getElementById('From Date').focus();
            return false;
        }
        document.getElementById('etype').value="";
        document.getElementById("searchform").submit();
    }
function Export(){

        if(document.getElementById("Range").checked==true){
            if(document.getElementById("From Date").value==""){
                alert("Please Select From Date.");
                document.getElementById("From Date").focus();
                return false;
            }
            if(document.getElementById("To Date").value==""){
                alert("Please Select To Date.");
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

        if(dateF>dateT){
            alert(date_validation_msg)
            document.getElementById('From Date').focus();
            return false;
        }
        document.getElementById('etype').value="export";
        document.getElementById('searchform').submit();
    }
    function donotsubmit()
    {
        return false;
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
     function printWarInvoice(claimNo)
        {
            var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=printBajajExtendedWarrantyInvoice&extWarrantyClaimNo="+claimNo.trim();
            window.open(strURL,'taxInv','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
        }
   function viewEwRefNo(ewLoaderId,chassisNo) {
        var strURL = '${pageContext.request.contextPath}/serviceProcess.do?option=getExtWarrPopupView&ewRefNo='+ewLoaderId+'&chassisNo='+chassisNo;
        window.open(strURL,'iji','width=980,height=480, resizable=no,scrollbars=no, status=0,titlebar=0,toolbar=0, addressbar=0,top=20,left=10');
    }

    $(document).ready(function(){
    $('.viewJobCard').click(function(){
        var url = "serviceProcess.do?option=viewJobcard&vinNo="+Base64.encode($(this).attr('data-vinNo').trim())+"&jobCardNo="+Base64.encode($(this).text().trim())+"&flag="+Base64.encode("Ginvoice")+"&createInvoice="+Base64.encode("false");
        $(this).attr('href',url);
    });

});
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><html:link action="/initExtWarranty"><bean:message key="label.common.extWarrantyReg" /></html:link></li>
            <li class="breadcrumb_link"><label><bean:message key="label.warranty.viewWarrantyExtClaim" /></label></li>
        </ul>
    </div>


    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1><bean:message key="label.warranty.viewWarrantyExtClaim" /></h1>

                <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>

                    <tr height=50 bgcolor="#FFFFFF">
                        <td colspan="15" align= center class="headingSpas">
                            <html:form action="warrantyAction.do?option=viewBajajExtendedWarrantyInv&flag=check" method="post" styleId="searchform" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                <%if (userFunctionalities.contains("101")) {%>
                                <tr bgcolor="#FFFFFF">
                                    <td align="right" style="padding-left: 5px" width="10%">
                                        <bean:message key="label.common.Chassisno"/></td>
                                    <td align="left">
                                        <input name="chassisNo" type="text" id="Chassis No" value="${serviceForm.chassisNo}" style="width:165px !important;" onblur="this.value=TrimAll(this.value)"/>
                                    </td>
                                    <td    class="headingSpas" nowrap align="right">
                                        <bean:message key="label.common.ClaimNo" />

                                    </td>
                                    <td   align="left">
                                        <input name="warrantyClaimNo" type="text" id="nameSrch" value="${nameSrch}" style="width:100px !important " onblur="this.value=TrimAll(this.value)"/>
                                    </td>
                                    <td class="headingSpas" nowrap align="right">&nbsp;
                                        <c:if test="${range eq '1'}">
                                            <input type="checkbox" name="range" value="1" id="Range" checked onclick="disableDate(this)">
                                        </c:if>
                                        <c:if test="${range ne '1' }">
                                            <input type="checkbox" name="range" value="1" id="Range" >
                                        </c:if>
                                        <bean:message key="label.common.InvoiceDate" /></td>
                                    <td align="left">
                                        <input name="fromDate" type="text" class="datepicker" id="From Date" value="${fromdate}" onkeydown="return false;"/>
                                    </td>
                                    <td class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/>&nbsp;
                                        <input name="toDate" type="text" class="datepicker" id="To Date" value="${todate}" onkeydown="return false;"/>
                                    </td>
                                 </tr>
                                <tr height=20 bgcolor="#FFFFFF">
                                    <td colspan="7" align="right" style="padding-right: 50px;">
                                        <input type="button" value="Search" onclick="search()"/>&nbsp;&nbsp;
                                        <input type="button" value="Export" onclick="Export()"/>
                                        <input type="hidden" name="etype" id="etype" />
                                    </td>
                                </tr>
                                <%} else {%>
                                <tr height=20 bgcolor="#FFFFFF">
                                    <td align="right" style="padding-left: 5px" width="10%">
                                        <bean:message key="label.common.Chassisno"/></td>
                                    <td align="left">
                                        <input name="chassisNo" type="text" id="Chassis No" value="${serviceForm.chassisNo}" style="width:165px !important;" onblur="this.value=TrimAll(this.value)"/>
                                    </td>
                                    <td    class="headingSpas" nowrap align="right">
                                        <bean:message key="label.common.ClaimNo" />

                                    </td>
                                    <td   align="left">
                                        <input name="warrantyClaimNo" type="text" id="nameSrch" value="${nameSrch}" style="width:100px !important " onblur="this.value=TrimAll(this.value)"/>
                                    </td>
                                    <td class="headingSpas" nowrap align="right">&nbsp;
                                        <c:if test="${range eq '1'}">
                                            <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                        </c:if>
                                        <c:if test="${range ne '1'}">
                                            <input type="checkbox" name="range" value="1"  id="Range" >
                                        </c:if>
                                       <bean:message key="label.common.InvoiceDate" /></td>
                                    <td  align="left">
                                        <input name="fromDate" type="text" class="datepicker" id="From Date" value="${fromdate}" onkeydown="return false;"/>
                                    </td>
                                    <td class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/>&nbsp;
                                        <input name="toDate" type="text" class="datepicker" id="To Date" value="${todate}" onkeydown="return false;"/>
                                    </td>
                                 </tr>
                                <tr height=20 bgcolor="#FFFFFF">
                                    <td align="right" width="5%" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                    <td align="left" width="20%" colspan="3" style="padding-right: 10px;">
                                        <select id="Dealer Name" name="dealercode" style="width:300px !important">
                                            <option value="All" >All</option>
                                            <c:forEach items="${dealerList}"  var="labelValue">
                                                <c:if test="${serviceForm.dealercode eq labelValue[0]}">
                                                    <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                </c:if>
                                                <c:if test="${serviceForm.dealercode ne labelValue[0]}">
                                                    <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td colspan="3" align="right" style="padding-right: 62px;">
                                        <input type="button" value="Search" onclick="search()"/>&nbsp;&nbsp;
                                        <input type="button" value="Export" onclick="Export()"/>
                                        <input type="hidden" name="etype" id="etype" />
                                    </td>
                                </tr>
                                <%}%>
                                </table>
                            </html:form>
                        </td>
                    </tr>
                </table>
                <div style="overflow: auto">
                    <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                        <tr bgcolor="#eeeeee" height="20">
                            <td   align="center" width="6%" class="headingSpas" nowrap ><b><bean:message key="label.warranty.S.No" /></b></td>
                            <td   align="left" width="14%" class="headingSpas" nowrap ><b> <bean:message key="label.common.ClaimNo" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap ><b><bean:message key="label.warranty.claimDate" /></b></td>
                            <td   align="left" width="12%"  class="headingSpas" nowrap ><b><bean:message key="label.common.jobcardno" /></b></td>
                            <td   align="left" width="12%" class="headingSpas" nowrap ><b><bean:message key="label.common.Chassisno" /></b></td>
                            <td   align="left" width="5%" class="headingSpas" nowrap ><b><bean:message key="label.common.hrm" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap ><b><bean:message key="label.common.dealerName" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap ><b><bean:message key="label.common.location" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap ><b><bean:message key="label.common.stateName" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap ><b><bean:message key="label.common.extWarrBookNo" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap ><b><bean:message key="label.common.bajajPolicyNo" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap ><b><bean:message key="label.common.bajajPolicyDate" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap ><b><bean:message key="label.common.InvoiceNo" /></b></td>
                            <td   align="left" width="10%" class="headingSpas" nowrap ><b><bean:message key="label.common.InvoiceDate" /></b></td>
                            <td   align="left" width="12%" class="headingSpas" nowrap ><b><bean:message key="label.common.claimInvoicedAmount" /></b></td>
                           
                        </tr>
                        <c:if test="${!empty dataList}">
                            <pg:pager url="warrantyAction.do" maxIndexPages="10" maxPageItems="10">
                                <pg:param name="option" value="viewBajajExtendedWarrantyInv"/>
                                <pg:param name="chassisNo"  value='<%=request.getParameter("chassisNo")%>'/>
                                <pg:param name="warrantyClaimNo"  value='<%=request.getParameter("warrantyClaimNo")%>'/>
                                <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                <pg:param name="fromDate"  value='<%=request.getParameter("fromDate")%>'/>
                                <pg:param name="toDate"  value='<%=request.getParameter("toDate")%>'/>
                                <pg:param name="dealercode"  value='<%=request.getParameter("Dealer Name")%>'/>
                                <pg:param name="flag"  value='check'/>
                                <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="dataList" name="dataList">
                                    <pg:item>
                                        <tr id ="${sno}" height="20">
                                            <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                            <td align="left" bgcolor="#FFFFFF" title="  ${dataList.warrantyClaimNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                   <a onclick="printWarInvoice('${dataList.warrantyClaimNo}');" href="javascript:void(0);"  title="Ext Warranty Claim Invoice Report">${dataList.warrantyClaimNo}</a>
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.claimDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.claimDate}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.jobCardNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                     <a href="#" class="viewJobCard" data-vinNo="${dataList.vinNo}">
                                                     <span id ="jobCardNo${sno}" >${dataList.jobCardNo}</span></a>
                                                   </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.vinNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.vinNo}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.hmr}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.hmr}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.dealerName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px" nowrap>
                                                <span id ="compType${sno}"  >
                                                    ${dataList.dealerName} [ ${dataList.dealer_code} ]
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.location}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.location}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.stateName}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    <a onclick="viewEwRefNo('${dataList.extWarrantyBookletNo}','${dataList.vinNo}');" href="javascript:void(0);" title="View Ext Warranty  Details">
                                                    ${dataList.extWarrantyBookletNo}</a>
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.bajajPolicyNo}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.bajajPolicyDate}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.invNo}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.invDate}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    ${dataList.claimInvoicedAmount}
                                                </span>
                                            </td>
                                            
                                        </tr>
                                    </pg:item>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                                <tr>
                                    <%--  <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >--%>

                                    
                                    <td colspan="15" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                        

                                        <pg:index>
                                            <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                                            <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                                            <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                            <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                                            <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                                            </pg:index>
                                    </td>
                                </tr>

                            </pg:pager>
                        </c:if>
                        <c:if test="${empty dataList}">
                            <tr bgcolor="#FFFFFF">


                                
                                <td valign="top" align="center" colspan="15" class="headingSpas" style="padding-top:10px;color: red">
                                 
                                    <b><bean:message key="label.common.bajajExtendedWarrantyInvoice" /> Not Found.!</b>
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </div>
            </div>
        </div>
    </center>
</div>
