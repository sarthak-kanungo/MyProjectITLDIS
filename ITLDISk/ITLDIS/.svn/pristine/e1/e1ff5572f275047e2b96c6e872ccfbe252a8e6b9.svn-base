<%-- 
    Document   : manageReorderLevel
    Created on : 22-Dec-2014, 18:49:40
    Author     : kundan.rastogi
--%>


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
        String dealerFlag="";
            if (!userFunctionalities.contains("101")) {dealerFlag="true";}
%>
<c:set var="dCode" value='<%=dealerCode%>'/>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

    function validateExcel()
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
        var filePath= document.getElementById("excelFile").value;
        if(filePath == '')
        {
            alert('Please Browse Excel  File.');
            return false;
        }
        //else if(filePath.indexOf(':\\') ==-1 || filePath.indexOf(':\\')==0)
        //{
            //alert('Invalid Excel File. Please Browse only .xls file.');
            //return false;
        //}
        else if(filePath.indexOf('.xls') != (filePath.length-4) ) //&& filePath.indexOf('.xlsx') != (filePath.length-5)
        {
            alert('Invalid Excel File. Please Browse only .xls file.');
            return false;
        }
        document.form1.action="<%=cntxpath%>/inventoryAction.do?option=uploadReOrderLevel";
        document.form1.submit();

    }
</script>



<div class="breadcrumb_container">
    <ul class="hText">

        <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
        <li><bean:message key="label.spare.manageReOrderLevel" />  </li>

    </ul>
</div>
<div class="message1" id="msg_saveFAILED">${message}</div>
<br/>

<div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.spare.manageReOrderLevel" /></h1>

<form name="form1" id="form1" method="post" onsubmit="return check_method_select();" ENCTYPE="multipart/form-data">
    <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key='label.common.spares'/></a>@@<bean:message key="label.spare.manageReOrderLevel" />"/>
    <table width="100%" border="0" cellspacing="0" align="left" class="LiteBorder">

        <tr height="10">   </tr>
         <%if  (!userFunctionalities.contains("101")) {%>
        <tr >
            <%-- <td align="right" width="40%" style="padding-right: 10px;">
          <bean:message key="label.common.UploadExcel" />Dealer Name</td>
             <td align="left" width="60%" style="padding-left:5px;">
                 <input name="dealerCode" type="text" id="DealerCode" style="padding-right:25px;" />
             </td>--%>

            <td  width="40%" align="right" class="headingSpas" nowrap style="padding-right:10px" > <bean:message key="label.common.dealerName" />    </td>            <td width="60%">
                <select id="Dealer Name" name="dealerCode" class="headingSpas" style="width:280px !important;">
                    <option value="" >--Select Here--</option>
                    <c:forEach items="${dealerList}"  var="labelValue">
                        <c:if test="${dCode eq labelValue[0]}">
                            <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                        </c:if>
                        <c:if test="${dCode ne labelValue[0]}">
                            <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr >
          <%}%>
        <tr height="10">   </tr>
        <tr >
            <td width="40%" align="right" class="headingSpas" style="padding-right: 10px;">
         <bean:message key="label.common.UploadExcel" />
            </td>
            <td width="60%" align="left" class="headingSpas" style="padding-left:9px;">
                <input name="excelFile" type="file" id="excelFile" style="width: 330px"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align = right style="padding-right:200px;">
                <a href = '<%=cntxpath%>/templates/ReOrderLevel_Excel.xls' style="color:red !important; text-decoration: none">
              <bean:message key="label.common.clickhere" /></a> <bean:message key="label.common.ToDownloadExcel" />
            </td>
        </tr>


        
        <tr>
            <td colspan="2" align="center"> <input type="button" value="Submit" style="float:none; margin: 5px 5px 5px 5px" onclick="validateExcel()"/>
            </td>
        </tr>
    </table>
</form>

            </div>
       </div>
