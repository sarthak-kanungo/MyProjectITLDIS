<%-- 
    Document   : upload_inventory
    Created on : Aug 20, 2014, 4:49:44 PM
    Author     : sreeja.vijayakumaran
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
%>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
   
    function validateExcel()
    {
       

        var filePath= document.getElementById("excelFile").value;
        if(filePath == '')
        {
            alert('Please Browse Excel  File.');
            return false;
        }
        //else if(filePath.indexOf(':\\') ==-1 || filePath.indexOf(':\\')==0)
        //{
            //alert('Invalid Excel File. Please Browse only .xls file.');
           // return false;
        //}
        else if(filePath.indexOf('.xls') != (filePath.length-4) ) //&& filePath.indexOf('.xlsx') != (filePath.length-5)
        {
            alert('Invalid Excel File. Please Browse only .xls file.');
            return false;
        }
        document.form1.action="<%=cntxpath%>/inventoryAction.do?option=uploadInvtryExcel";
        document.form1.submit();

    }
</script>



<div class="breadcrumb_container">
    <ul>

        <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
        <li><bean:message key="label.common.uploadInventory" />  </li>

    </ul>
</div>
<div class="message1" id="msg_saveFAILED">${message}</div>
<br/>

<div class="content_right1">
    <div class="con_slidediv1" style="position: relative;width: 100%">
        <h1><bean:message key="label.common.uploadInventory" /></h1>

        <form name="form1" id="form1" method="post" onsubmit="return check_method_select();" ENCTYPE="multipart/form-data">
            <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key='label.common.spares'/></a>@@<bean:message key="label.common.uploadInventory" />"/>
            <table width="100%" border="0" cellspacing="0" align="left" class="LiteBorder">

                <tr style="height: 5px"></tr>
                <tr>
                    <td align="center" >
                        <bean:message key="label.common.UploadExcel" />&nbsp;
                        <input name="excelFile" type="file" id="excelFile" style="width: 330px"/>
                    </td>
                </tr>
                <tr>
                    <td  align = right style="padding-right:200px;">
                        <a href = '<%=cntxpath%>/templates/Inventory_Excel.xls' style="color:red !important; text-decoration: none">
                            <bean:message key="label.common.clickhere" /></a> <bean:message key="label.common.ToDownloadExcel" />
                </tr>
                <tr>
                    <td style="height: 5px"></td>
                </tr>
                <tr>
                    <td align="center"> <input type="button" value="Submit" style="float:none; margin: 5px 5px 5px 5px" onclick="validateExcel()"/>
                    </td>
                </tr>
            </table>
        </form>

    </div>
</div>
