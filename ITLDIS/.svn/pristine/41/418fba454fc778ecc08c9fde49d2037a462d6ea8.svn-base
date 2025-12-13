<%-- 
    Document   : lockInventory
    Created on : Aug 26, 2014, 12:36:59 PM
    Author     : sreeja.vijayakumaran
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
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
    function validateCheck()
    {
        if (document.getElementById("checkbox").checked==false){
            alert ('Please Check the Checkbox to Agree.');
            return false;
	} else { 	
            document.form1.action="<%=cntxpath%>/inventoryAction.do?option=completeLockInventory";
            document.form1.submit();

	}
    }

</script>



<div class="breadcrumb_container">
    <ul>

        <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
        <li><bean:message key="label.common.lockInventory" />   </li>

    </ul>
</div>
<br/>
<div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                 <h1><bean:message key="label.common.lockInventory" /></h1>
<form name="form1" id="form1" method="post" onsubmit="" ENCTYPE="multipart/form-data">
    <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a>@@<bean:message key="label.common.lockInventory" />"/>

    <table width="100%" border="0" cellspacing="0" align="left" class="LiteBorder">
     
        <tr style="height: 15px"></tr>
        <tr>
            <td align="center" style="padding-left: 15px">
                <input name="" type="checkbox" id="checkbox" style="width: 15px"/>&nbsp; <bean:message key="label.common.lockInventoryStatement" />
              <%--  I Agree that All Current Inventory has been Uploaded & Updated & if any further modification , Please contact DMS.--%>
            </td>
        </tr>
        <tr>
            <td style="height: 25px"></td>
        </tr>
        <tr>
            <td align="center"> <input type="button" value="SUBMIT" style="float:none; margin: 5px 5px 5px 5px" onclick="validateCheck()"/>
            </td>
        </tr>
    </table>
</form>


</div>
</div>