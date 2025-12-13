<%-- 
    Document   : v_searchManageTaxCtgryBranch
    Created on : 15 March, 2016, 11:28:59 AM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Customer</title>
        <script type="text/javascript">
  function cUDAction(status,strVal,str,row)
    {

        var url = null;
        url = "<%=cntxpath%>/masterAction.do?option=manageTaxCtgryBranch&status=" + status + "&type=" + str + "&taxctgry_branch_id=" + strVal + "&t=" + new Date().getTime()+"&dealer_code="+strVal;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                if (xmlHttp.responseText.indexOf("Session has been expired") != -1)
                {
                    document.location.href = contextPath + "/login/SessionExpired.jsp";
                }
                else
                {
                    res = trim(xmlHttp.responseText);
                    document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                    if (res.split("@@")[0] == 'Success') {
                        if (status == 'Y') {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','" + strVal + "','edit','"+row+"')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        } else {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','" + strVal + "','edit','"+row+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }
        </script>
    </head>
    <body>
        <div class="contentmain1">
            <div class="breadcrumb_container">
                <ul>
                    <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
                    <li class="breadcrumb_link"><font class="hText"><bean:message key="label.common.managetaxCategories" /></font></li>
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
                    <h1 class="hText"><bean:message key="label.common.managetaxCategories" /></h1>
                    <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                        <tr bgcolor="#FFFFFF">
                            <td align="center" class="headingSpas">
                                <html:form action="masterAction.do?option=initManageTaxCtgryBranch" method="post" styleId="masterForm" >
                                    <table width=100%  border=0 cellspacing=0 cellpadding=0>
                                        <tr bgcolor="#FFFFFF">
                                            <td  width="10%" class="headingSpas" nowrap align="right" style="padding-left: 3px;"><bean:message key="label.master.categoryCode" /></td>
                                            <td style="padding-left:3px" width="5%" align="left">
                                                <html:text property="searchCategoryCode" styleClass="headingSpas" maxlength="15" onblur="this.value=TrimAll(this.value);"/>
                                            </td>
                                            <td  width="10%" class="headingSpas" nowrap align="right" style="padding-left: 3px;"><bean:message key="label.master.categoryDesc" /></td>
                                            <td style="padding-left:3px" width="5%" align="left">
                                                <html:text property="searchCategoryDesc" styleClass="headingSpas" maxlength="15" onblur="this.value=TrimAll(this.value);"/>
                                            </td>
                                            <td style="padding-left:10px" width="15%" align="left" >
                                                <html:submit property="Go" value="Search" styleId="Go" styleClass="headingSpas"/>
                                            </td>
                                            <td style="padding-right:5px" width="15%" align="right" >
                                                <a href='<%=cntxpath%>/masterAction.do?option=addNewTaxCategories'><b><bean:message key="label.master.addNewTaxCategories" /></b></a>
                                            </td>

                                        </tr>
                                    </table>
                                </html:form>
                            </td>
                        </tr>
                        <c:if test="${empty taxCtgryBranchList}">
                            <tr bgcolor="#FFFFFF">
                                <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                     Tax Categories not Found
                                </td>
                            </tr>
                        </c:if>
                    </table>
                    <div style="overflow: auto">
                        <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                            <c:if test="${!empty taxCtgryBranchList}">

                                <tr bgcolor="#FFFFFF">
                                    <td  align= center class="headingSpas">
                                        <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                            <pg:param name="option" value="initManageTaxCtgryBranch"/>
                                            <pg:param name="searchCategoryCode" value="${masterForm.searchCategoryCode}"/>
                                            <pg:param name="searchCategoryDesc" value="${masterForm.searchCategoryDesc}"/>
                                            <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bgcolor=#cccccc>
                                                <tr bgcolor="#eeeeee" height="25px">
                                                    <td   align="center" width="5%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" >S.No</td>
                                                    <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" ><bean:message key="label.master.categoryCode" /></td>
                                                    <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" ><bean:message key="label.master.categoryDesc" /></td>
                                                    <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" ><bean:message key="label.common.edit" /></td>
                                                    <td   align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;width: 40px;white-space: nowrap" ><bean:message key="label.common.isActive" /></td>
                                                </tr>
                                                <c:set var="sno" value="1"></c:set>
                                                <logic:iterate id="taxCtgryBranch" name="taxCtgryBranchList">
                                                    <pg:item>
                                                        <tr bgcolor="#FFFFFF" >
                                                            <td   align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >${sno}</td>
                                                            <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >${taxCtgryBranch.taxCtgryCode}</td>
                                                            <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" >${taxCtgryBranch.taxCtgryDesc}</td>
                                                            <td   align="left" width="12%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" ><a href='<%=cntxpath%>/masterAction.do?option=editTaxCtgryBranch&taxctgry_branch_id=${taxCtgryBranch.taxctgry_branch_id}'><img src="${pageContext.request.contextPath}/images/edit.gif" title="Edit"/></a></td>
                                                            <td   align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;width: 40px;white-space: nowrap" ><span id ="st${sno}" >
                                                                <a href="javascript:void(0)" onclick="cUDAction('${taxCtgryBranch.isActive}','${taxCtgryBranch.taxctgry_branch_id}','edit',${sno})"  class="headingSpas" style="color: blue;" >
                                                                ${taxCtgryBranch.isActive}&nbsp;
                                                            </a></span></td>
                                                        </tr>
                                                    </pg:item>
                                                    <c:set var="sno" value="${sno + 1}"></c:set>
                                                </logic:iterate>
                                                <tr bgcolor="#FFFFFF">
                                                    <td colspan="5" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                            </c:if>
                        </table>
                    </div>
                </div>                
            </div>
        </div>
    </body>
</html>
