<%-- 
    Document   : v_manage_functionality
    Created on : May 2, 2014, 11:47:00 AM
    Author     : sreeja.vijayakumaran
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


    
    function GetXmlHttpObject()
    {
        var objXmlHttp = null;
        if (navigator.userAgent.indexOf('Opera') >= 0)
        {
            xmlHttp = new XMLHttpRequest();
            return xmlHttp;
        }
        if (navigator.userAgent.indexOf('MSIE') >= 0)
        {
            var strName = 'Msxml2.XMLHTTP';
            if (navigator.appVersion.indexOf('MSIE 5.5') >= 0)
            {
                strName = 'Microsoft.XMLHTTP';
            }
            try
            {
                objXmlHttp = new ActiveXObject(strName);
                // objXmlHttp.onreadystatechange = handler;
                return objXmlHttp;
            }
            catch (e)
            {
                alert('Your Browser Does Not Support Ajax');
                return;
            }
        }
        if (navigator.userAgent.indexOf('Mozilla') >= 0)
        {
            objXmlHttp = new XMLHttpRequest();
            return objXmlHttp;
        }
    }


    function getTableInfo(funcname)
    {
        var strURL = "${pageContext.request.contextPath}/masterAction.do?option=getTableInfo&funcname="+funcname;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            stateChangedTopic();
        }
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);

    }
    function stateChangedTopic()
    {
        if (xmlHttp.readyState == 4 || xmlHttp.readystate =='complete' )

        {
            res = trim(xmlHttp.responseText);
            // alert(res);
            document.getElementById("tablecoldata").innerHTML=res;

        }
    }
    function up(obj)
    {
        var current;
        var reverse;
        var current_val;
        var reverse_val;

        if(obj.selectedIndex==-1){
            alert("You must select at least one!");
        }
        else {
            if(obj.options[obj.options.selectedIndex].index > 0)
            {
                current = obj.options[obj.options.selectedIndex].text;
                reverse = obj.options[obj.options[obj.options.selectedIndex].index-1].text;
                current_val = obj.options[obj.options.selectedIndex].value;
                reverse_val = obj.options[obj.options[obj.options.selectedIndex].index-1].value;

                obj.options[obj.options.selectedIndex].text = reverse;
                obj.options[obj.options[obj.options.selectedIndex].index-1].text = current;

                obj.options[obj.options.selectedIndex].value = reverse_val;
                obj.options[obj.options[obj.options.selectedIndex].index-1].value = current_val;
                self.focus();
                obj.options.selectedIndex--;
            }
        }
    }

    function down(obj)
    {
        var current;
        var next;
        var current_val;
        var next_val;

        if(obj.selectedIndex==-1){
            alert("You must select at least one!");
        }
        else {
            if(obj.options[obj.options.selectedIndex].index != obj.length-1)
            {
                current = obj.options[obj.options.selectedIndex].text;
                next = obj.options[obj.options[obj.options.selectedIndex].index+1].text;

                current_val = obj.options[obj.options.selectedIndex].value;
                next_val = obj.options[obj.options[obj.options.selectedIndex].index+1].value;

                obj.options[obj.options.selectedIndex].text =  next;
                obj.options[obj.options[obj.options.selectedIndex].index+1].text = current;

                obj.options[obj.options.selectedIndex].value =  next_val;
                obj.options[obj.options[obj.options.selectedIndex].index+1].value = current_val;

                self.focus();
                obj.options.selectedIndex++;
            }
        }
    }
    function selectall(){
        hiddenAssignCode='';
        for (var i=0;i<document.getElementById("tabledata").options.length;i++) {
            document.getElementById("tabledata").options[i].selected=true;
        }
    }
    function updatedata(){
        var functioname=document.getElementById("functionList").value;
        if(functioname=="")
        {
            document.getElementById("msg_saveFAILED").innerHTML="Please Select Function Name.";
            document.getElementById("functionList").focus();
            return false;
        }
        selectall();
        document.FunctionForm.submit();
    }

    function msgclr()
    {
        document.getElementById("msg_saveFAILED").innerHTML="";
    }


</script>



<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a> MANAGE SEQUENCE IN MASTERS</li>


        </ul>
    </div>

    <div class="message" id="msg_saveFAILED">
        <c:if test="${testval eq 1}">Sequence No. Updated Successfully
        </c:if>
    </div>
    <center>

        <div class="content_right1">

            <div class="con_slidediv1" style="position: relative;width: 70%">

                <h1>MANAGE SEQUENCE IN MASTERS</h1>

                <table cellspacing=1 cellpadding=5 border=0 width=100%>
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=updateData" method="post" styleId="FunctionForm" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">
                                        <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="right">Functionality Name :</td>
                                        <td style="padding-left:10px" width="30%" align="left" colspan="">
                                            <%-- <html:select property="funcname" onchange="getTableInfo()">
                                                 <html:option value="">--Select--</html:option>
                                                 <html:options collection="functionlists" property="value" labelProperty="label"/>
                                             </html:select>--%>
                                            <select name="" id="functionList"  onchange="getTableInfo(this.value);msgclr()" style="width: 150px !important">
                                                <option value="">--select--</option>
                                                <c:forEach items="${masterForm.labelList}" var="funTemp">
                                                    <option value="${funTemp.value}">${funTemp.label}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr bgcolor="#FFFFFF">
                                        <td colspan="2" align="center">
                                            <table border="0" style="padding-bottom: 25px;padding-top: 25px">
                                                <tr>
                                                    <td width="90%"  class="text" align=center colspan="" height="50px">
                                                        <span id="tablecoldata">
                                                            <select name="tabledata" id="tabledata" class="text"   multiple="multiple" size="10" style="width:250px!important ;height: 250px !important">
                                                            </select>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr bgcolor="#FFFFFF">
                                        <td class="text" align="center" colspan="2"><input class="text" type="button" onclick="updatedata()" value="SUBMIT"/> </td>
                                    </tr>

                                </table>
                            </html:form>
                        </td>
                    </tr>

                </table>
            </div>
        </div>
    </center>
</div>