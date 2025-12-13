<%-- 
    Document   : warrntyTaxPercMaster
    Created on : Oct 14, 2014, 11:16:18 AM
    Author     : kundan.rastogi
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<c:set var="param" value='<%=request.getParameter("param")%>'/>
<script language="JavaScript" type="text/javascript">
    function getDate(row){

        $(function() {
            $("#newstartdate"+row ).datepicker();
        });

    }
    function submitForm(){
        document.getElementById('searchBy').submit();
    }


    function validNumber(obj) {
        var regExp = /^[0-9.]+$/g
        if (regExp.test(obj)) {
            return true;
        } else {
            return false;
        }
    }

    function validSpclChar(obj) {
        var regExp =/^[a-zA-Z0-9-_.\"\"\&\(\)\/ ]*$/g;
        if (regExp.test(obj)) {
            return true;
        } else {
            return false;
        }
    }

    function cUDAction(str, row,typeId,stateId)
    {
        if(str == 'add'){
            
            if(document.getElementById("taxTypeId").value=="")
            {
                document.getElementById("taxTypeId").focus();
                alert(not_blank_dropdown_validation_msg+'Tax Type')
                return false;
            }
            if(document.getElementById("stateId").value=="")
            {
                document.getElementById("stateId").focus();
                alert(not_blank_dropdown_validation_msg+'State')
                return false;
            }
            if(document.getElementById("percentage").value=="")
            {
                document.getElementById("percentage").focus();
                alert(not_blank_validation_msg+'Percentage')
                return false;
            }
            if(validNumber(document.getElementById("percentage").value)==false)
            {
            alert("Please enter a valid value for Percentage.");
            document.getElementById("percentage").value="";
            document.getElementById("percentage").focus();
            return false;
           }
           if(parseInt(document.getElementById('percentage'+row).value) > 100)
            {
                alert('Percentage cannot be greater than 100.');
                document.getElementById('percentage').value="";
                document.getElementById('percentage').focus();
                return false;
            }
            document.getElementById("warrantytextPer").submit();

        }else{

            var url="<%=cntxpath%>/masterAction.do?option=manageWarrantyTax&type="+str+"&taxTypeId="+typeId+"&stateId="+stateId+"&t="+new Date().getTime();
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
                        document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];

                        if(str=='Y' && res.split("@@")[0] =='contentExist' ){
                            document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','"+row+"','"+strVal+"','"+contentDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                        
                    }
                }
            };
            xmlHttp.open("GET",url, true);
            xmlHttp.send(null);
            return false;
        }
    }

    function editRow(row,typeId,stateId,percentage){

       // document.getElementById('modelmonthspan'+row).innerHTML="<input type=\"text\" name=\"newmodelmonth\" maxlength='3' class=\"headingSpas\" id=\"newmodelmonth"+row+"\" style=\"width:65px\" value=\""+month+"\"/>";

        document.getElementById('taxpercentagespan'+row).innerHTML="<input type=\"text\" name=\"newpercentage\" maxlength='3' class=\"headingSpas\"  id=\"newpercentage"+row+"\" style=\"width:65px\" value=\""+percentage+"\"/>";

        document.getElementById('imageButton'+row).innerHTML="<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+typeId+"','"+stateId+"')\"/>";
    }

    function updateAction(str, row,typeId,stateId)
    {

        var elementArr=new Array('newpercentage'+row);
        var strValue=null;
        var strObject=null;

        strObject=document.getElementById('newpercentage'+row);
        strValue =document.getElementById('newpercentage'+row).value;
        if(validNumber(strValue)==false)
         {
            alert("Please enter a valid value for Percentage.");
            document.getElementById(elementArr[i]).value="";
            document.getElementById(elementArr[i]).focus();
            return false;
         }
        if(parseInt(document.getElementById('newpercentage'+row).value) > 100)
        {
            alert('Percentage cannot be greater than 100.');
            document.getElementById(elementArr[i]).value="";
            document.getElementById(elementArr[i]).focus();
            return false;
        }
        var perc=document.getElementById("newpercentage"+row).value;
        
        var url="<%=cntxpath%>/masterAction.do?option=manageWarrantyTax&type="+str+"&taxTypeId="+typeId+"&stateId="+stateId+"&percentage="+perc+"&t="+new Date().getTime();

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
                { res = trim(xmlHttp.responseText);<%--alert(res)--%>
                    document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
                    if(res.split("@@")[0] =='Success'){
                        <%--alert(res.split("@@")[0])--%>
                        document.getElementById('taxpercentagespan'+row).innerHTML=""+perc;

                        document.getElementById('imageButton'+row).innerHTML="<img src=\"images/edit.gif\" onclick=\"editRow("+row+",'"+typeId+"','"+stateId+"','"+perc+"')\" title=\"Edit\"/>";

                    }
                }
            }
        };
        xmlHttp.open("GET",url, true);
        xmlHttp.send(null);
        return false;
    }
    function donotsubmit()
    {
        return false;
    }
    
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a>WARRANTY TAX PERCENTAGE MASTER</li>
        </ul>
    </div>
    
    <div class="message" id="msg_saveFAILED"><html:errors/></div>
    <center>

        <div class="content_right1">

            <div class="con_slidediv1" style="position: relative;width:100%">

                <h1>WARRANTY TAX PERCENTAGE MASTER</h1>

                <table cellspacing=1 cellpadding=5 border=0 width=100%>
                  
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initWarrantyTax" method="post" styleId="searchBy" onsubmit="donotsubmit();" >

                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="10%" class="headingSpas" nowrap align="right">Tax Type</td>
                                        <td style="padding-left:10px" width="20%" align="left">
                                          <select name="taxTypeName" id="taxTypeName" class="selectnewbox" >   
                                                    <option value="">--Select--</option>
                                                    <c:forEach items="${taxTypeList}" var="taxTypeList">
                                                        <c:if test="${masterForm.taxTypeName eq taxTypeList.value}">
                                                            <option selected value='${taxTypeList.value}' title='${taxTypeList.label}'>${taxTypeList.label}</option>
                                                        </c:if>
                                                        <c:if test="${masterForm.taxTypeName ne taxTypeList.value}">
                                                          <option value='${taxTypeList.value}' title='${taxTypeList.label}'>${taxTypeList.label}</option>
                                                        </c:if>
                                                    </c:forEach>
                                          </select>
                                        </td>
                                        <td  style="padding-left:10px" width="10%" class="headingSpas" nowrap align="right">State</td>
                                        <td style="padding-left:10px" width="20%" align="left">
                                       <select name="stateName" id="stateName" class="selectnewbox" style="width:140px !important" >
                                                    <option value="">--Select--</option>
                                                    <c:forEach items="${stateList}" var="stateList">
                                                        <c:if test="${masterForm.stateName eq stateList.value}">
                                                          <option selected value='${stateList.value}' title='${stateList.label}'>${stateList.label}</option>
                                                        </c:if>
                                                        <c:if test="${masterForm.stateName ne stateList.value}">
                                                          <option value='${stateList.value}' title='${stateList.label}'>${stateList.label}</option>
                                                        </c:if>
                                                    </c:forEach>
                                       </select>
                                        </td>
                                        <td style="padding-left:10px" align="left" width="40%" >
                                            <input type="submit" name="Go" value="Search" class="headingSpas1" />
                                        </td>

                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${!empty warrantyTaxPercList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initWarrantyTax"/>
                                    <pg:param name="taxTypeName"  value='${masterForm.taxTypeName}'/>
                                    <pg:param name="stateName"  value='${masterForm.stateName}'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr  bgcolor="#eeeeee" height="20">
                                            <td   align="center" width="5%" class="headingSpas" nowrap ><b>S.No</b></td>
                                            <td   align="left" width="20%"  class="headingSpas" nowrap><b>Tax Type</b></td>
                                            <td   align="left" width="20%" class="headingSpas" nowrap><b>State</b></td>
                                            <td   align="center" width="30%" class="headingSpas" nowrap><b>Percentage</b></td>
                                            <td   align="center" width="15%" class="headingSpas" nowrap><b>Edit</b></td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="warrantyTaxPercList" name="warrantyTaxPercList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>

                                                    <td align="left" bgcolor="#FFFFFF" width="200" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <input type="hidden"  title="${warrantyTaxPercList.taxTypeName}" id="taxTypeName${sno}" value="${warrantymodellist.taxTypeName}"/>
                                                        <span id ="taxTypeNamespan${sno}" >
                                                            ${warrantyTaxPercList.taxTypeName}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="40" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="stateNamespan${sno}" >
                                                            <input type="hidden"  title="${warrantyTaxPercList.stateName}" id="stateName${sno}" value="${warrantymodellist.stateName}"/>
                                                            ${warrantyTaxPercList.stateName}
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="35" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="taxpercentagespan${sno}" >
                                                            <input type="hidden"  title="${warrantyTaxPercList.percentage}" id="taxpercentage${sno}" value="${warrantymodellist.percentage}"/>
                                                            ${warrantyTaxPercList.percentage}

                                                        </span>
                                                    </td>
                                               
                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow(${sno},'${warrantyTaxPercList.taxTypeId}','${warrantyTaxPercList.stateId}','${warrantyTaxPercList.percentage}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr>
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
                        <c:if test="${empty warrantyTaxPercList}">
                            <tr bgcolor="#FFFFFF">
                                <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                   Warranty Tax Percentage Not Found. !
                                </td>
                            </tr>
                        </c:if>
                    <tr bgcolor="#FFFFFF">
                        <td width=100% valign="top">
                            <form action="masterAction.do?option=manageWarrantyTax" id="warrantytextPer" name="warrantytextPer" method="post">
                                <input type="hidden" name="type" value="add" />
                                <table width=100% bgcolor=#000000 border="0" cellpadding="0" cellspacing="0" >
                                    <tr   bgcolor="#eeeeee" height="20">
                                        <td align= left class="headingSpas" style="padding-left: 5px;">
                                            <b>ADD NEW TAX PERCENTAGE</b>
                                        </td>
                                    
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table id="myTable" width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                <tr height="30" bgcolor="#FFFFFF">
                                                    <td style="padding-left:10px" width="20%" align="right">Tax Type</td>
                                                    <td style="padding-left:10px" width="80%" align="left">
                                                  <select name="taxTypeId" id="taxTypeId" class="selectnewbox" >   <%--onchange="makeEnable(this.value)"--%>
                                                    <option value="">--Select--</option>
                                                    <c:forEach items="${taxTypeList}" var="taxTypeList">
                                                        <option value='${taxTypeList.value}' title='${taxTypeList.label}'>${taxTypeList.label}</option>
                                                    </c:forEach>
                                                </select>
                                                    </td>
                                                </tr>
                                                <tr height="30" bgcolor="#FFFFFF">

                                                    <td style="padding-left:10px" width="20%" align="right">State</td>
                                                    <td style="padding-left:10px" width="80%" align="left">
                                                        <select name="stateId" id="stateId" class="selectnewbox" style="width:140px !important">   <%--onchange="makeEnable(this.value)"--%>
                                                    <option value="">--Select--</option>
                                                    <c:forEach items="${stateList}" var="stateList">
                                                        <option value='${stateList.value}' title='${stateList.label}'>${stateList.label}</option>
                                                    </c:forEach>
                                                </select>
                                                    </td>
                                                </tr>
                                                <tr height="30" bgcolor="#FFFFFF">

                                                    <td style="padding-left:10px" width="20%" align="right">Percentage</td>
                                                    <td style="padding-left:10px" width="80%" align="left">
                                                        <input type="text" name="percentage"  id="percentage" class="headingSpas"  maxlength="3" style="width:120px" />
                                                    </td>
                                                </tr>
                                                <tr height="30" bgcolor="#FFFFFF">
                                                      <td style="padding-left:10px" width="20%" align="right">
                                                     </td>
                                                     <td  width="80%" style="padding-left:15px;padding-bottom: 5px" align="left" >
                                                        <input type=button name="add" class="headingSpas" value="Submit" onclick="cUDAction('add','','','')"/>
                                                    </td>

                                                </tr>

                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </center>
</div>

