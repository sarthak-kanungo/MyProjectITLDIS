<%--
    Document   : v_warrantymodel
    Created on : JUNE 07, 2014, 02:25:09 PM
    Author     : vijay.mishra
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
        var regExp = /^[0-9]+$/g
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

    function cUDAction(str, row,strVal,contentDesc)
    {
      
        if(str == 'add'){
            //Model Code,Month,Hours
            var code = new Array();
            var month = new Array();
            var hrs = new Array();

            code = document.getElementsByName("code"); //Model Code
            month = document.getElementsByName("month");// Month
            hrs = document.getElementsByName("hrs");// Hours

            for(var i=0; i<code.length;i++)
            {
                if(validSpclChar(code[i].value)==false)
                {
                    document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in Model Code";
                    code[i].value="";
                    code[i].focus();
                    return false;
                }
                if(code[i].value=="")
                {
                    document.getElementById("msg_saveFAILED").innerHTML="Please Enter a Model Code";
                    code[i].value="";
                    code[i].focus();
                    return false;
                }


                if(validNumber(month[i].value)==false)
                {
                    document.getElementById("msg_saveFAILED").innerHTML="Please enter a valid value for month.";
                    month[i].value="";
                    month[i].focus();
                    return false;
                }

                if(validNumber(hrs[i].value)==false)
                {
                    document.getElementById("msg_saveFAILED").innerHTML="Please enter a valid value for hour.";
                    hrs[i].value="";
                    hrs[i].focus();
                    return false;
                }
            }

            document.getElementById("addModel").action = "<%=cntxpath%>/masterAction.do?option=manageWarrantyModel&type="+str+"&t="+new Date().getTime();
            document.getElementById("addModel").submit();

        }else{

            var url="<%=cntxpath%>/masterAction.do?option=manageWarrantyModel&status="+str+"&contentDesc="+contentDesc+"&type="+str+"&id="+strVal+"&t="+new Date().getTime();
       
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

                        if(str != 'add' && res.split("@@")[0] =='Success'){
                      
                            if(str=='Y'){
                                document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','"+row+"','"+strVal+"','"+contentDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                            }
                            else if(str=='N'){
                                document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','"+row+"','"+strVal+"','"+contentDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                            }
                        }
                        if(str=='Y' && res.split("@@")[0] =='contentExist' ){
                            document.getElementById('st'+row).innerHTML="<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','"+row+"','"+strVal+"','"+contentDesc+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                        if(str == 'add' && res.split("@@")[0] =='Success'){
                            //submitForm();
                            document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
                            //document.getElementById("Content Description").value='';
                            var contents = document.getElementsByName("name");
                            for (var i = 0; i < contents.length; i++) {

                                contents[i].value='';

                            }
                        }
                    } }
            };
            xmlHttp.open("GET",url, true);
            xmlHttp.send(null);
            return false;
        }
    }
    
    function editRow(row,month,hrs,id,code){

        document.getElementById('modelmonthspan'+row).innerHTML="<input type=\"text\" name=\"newmodelmonth\" maxlength='3' class=\"headingSpas\" id=\"newmodelmonth"+row+"\" style=\"width:65px\" value=\""+month+"\"/>";

        document.getElementById('modelhrsspan'+row).innerHTML="<input type=\"text\" name=\"newmodelhrs\" maxlength='8' class=\"headingSpas\"  id=\"newmodelhrs"+row+"\" style=\"width:65px\" value=\""+hrs+"\"/>";

        document.getElementById('imageButton'+row).innerHTML="<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+id+"','"+code+"')\"/>";
    }

    function updateAction(str, row,id,code)
    {
       
        var elementArr=new Array('newmodelmonth'+row,'newmodelhrs'+row);
        var strValue=null;
        //var strObject=null;

        for(var i=0;i<elementArr.length;i++)
        {
            // strObject=document.getElementById(elementArr[i]);
            strValue =document.getElementById(elementArr[i]).value;
           
            if(validNumber(strValue)==false)
            {
                document.getElementById("msg_saveFAILED").innerHTML="Please enter a valid value.";
                document.getElementById(elementArr[i]).value="";
                document.getElementById(elementArr[i]).focus();
                return false;
            }
        }

        var month=document.getElementById("newmodelmonth"+row).value;
        var hrs=  document.getElementById("newmodelhrs"+row).value;
    

        var url="<%=cntxpath%>/masterAction.do?option=manageWarrantyModel&type="+str+"&modelCode="+code+"&modelid="+id+"&modelmonth="+month+"&modelhrs="+hrs+"&t="+new Date().getTime();

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
                { res = trim(xmlHttp.responseText);
                    document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
                    if(str != 'add' && res.split("@@")[0] =='Success'){

                        document.getElementById('modelmonthspan'+row).innerHTML=""+month.toString().toUpperCase();

                        document.getElementById('modelhrsspan'+row).innerHTML=""+hrs.toString().toUpperCase();

                        document.getElementById('imageButton'+row).innerHTML="<img src=\"images/edit.gif\" onclick=\"editRow("+row+",'"+month+"','"+hrs+"','"+id+"','"+code+"')\" title=\"Edit\"/>";
            
                    }
                } }
        };
        xmlHttp.open("GET",url, true);
        xmlHttp.send(null);
        return false;
    }
    function donotsubmit()
    {
        return false;
    }


    function addRow()
    {
        var oRows = document.getElementById('myTable').getElementsByTagName('tr');
        var  rowLength=oRows.length
        if(rowLength<10){
            var x=document.getElementById('myTable').insertRow(rowLength-1);
            x.bgColor="white";
            x.height='30px';
            x.id=rowLength;
          
            var ch=x.insertCell(0);
            ch.width="33%";
            ch.align="center";
            ch.style.paddingLeft="10px";
            ch.innerHTML="<input type=\"text\" name=\"code\"  id=\"Model Code"+rowLength+"\" class=\"headingSpas\"  maxlength=\"25\" style=\"width:170px\" />";

            var ch=x.insertCell(1);
            ch.width="33%";
            ch.align="center";
            ch.style.paddingLeft="10px";
            ch.innerHTML="<input type=\"text\" name=\"month\"  id=\"Month"+rowLength+"\" class=\"headingSpas\"  maxlength=\"3\" style=\"width:170px\" />";

            var ch=x.insertCell(2);
            ch.width="34%";
            ch.align="center";
            ch.style.paddingLeft="10px";
            ch.innerHTML="<input type=\"text\" name=\"hrs\"  id=\"Hours"+rowLength+"\" class=\"headingSpas\"  maxlength=\"8\" style=\"width:170px\" />";
        }else{
            alert("Only 9 ref No. can be added at a time");
        }
    }
    function deleterow()
    {
        var oRows = document.getElementById('myTable').getElementsByTagName('tr') ;
        var rowLength=oRows.length;
        if(rowLength>3){
            document.getElementById('myTable').deleteRow(rowLength-2);
        }
    }
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a>WARRANTY MODEL MASTER</li>


        </ul>
    </div>
    <html:errors />
    <div class="message" id="msg_saveFAILED">
        <c:if test="${not empty show_message}">
            ${show_message}
        </c:if>

    </div>
    <center>

        <div class="content_right1">

            <div class="con_slidediv1" style="position: relative;width:100%">

                <h1>WARRANTY MODEL MASTER</h1>

                <table cellspacing=1 cellpadding=5 border=0 width=100%>

                    <%-- <tr height=25 class=grey>
                         <td align= center class="heading">
                             MANAGE CUSTOMER TYPE
                         </td>
                     </tr>--%>
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initWarrantyModel&param=search" method="post" styleId="searchBy" onsubmit="donotsubmit();" >

                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="20%" class="headingSpas" nowrap align="left">Model Code</td>
                                        <td style="padding-left:10px" width="30%">
                                            <html:text property="nameSrch" styleId="nameSrch" styleClass="headingSpas"  style="width:170px"/>
                                        </td>
                                        <td style="padding-left:10px" align="left" >
                                            <input type=button name="Go" value="Search" class="headingSpas" onClick = "submitForm()"/>
                                        </td>

                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${!empty warrantymodellist}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initWarrantyModel"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr  bgcolor="#eeeeee" height="20">
                                            <td   align="center" width="25" class="headingSpas" nowrap ><b>S.No</b></td>
                                            <td   align="left" width="150"  class="headingSpas" nowrap><b>Model Code</b></td>
                                            <td   align="center" width="30" class="headingSpas" nowrap><b>Month</b></td>
                                            <td   align="center" width="30" class="headingSpas" nowrap><b>Hours</b></td>
                                            <%--<td   align="center" width="20" class="headingSpas" nowrap><b>Start Date</b></td>--%>
                                            <td   align="center" width="50" class="headingSpas" nowrap><b>Edit</b></td>
                                            <!--                                            <td   align="center" width="35" class="headingSpas" nowrap style="display:none"><b>isActive</b></td>-->
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="warrantymodellist" name="warrantymodellist">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}
                                                        <input type="hidden" name="modelID" title="${warrantymodellist.modelId}" id="Model ID${sno}" value="${warrantymodellist.modelCode}"/>
                                                    </td>


                                                    <td align="left" bgcolor="#FFFFFF" width="200" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <input type="hidden" name="code" title="${warrantymodellist.modelCode}" id="Model Code${sno}" value="${warrantymodellist.modelCode}"/>
                                                        <span id ="modelcodespan${sno}" >
                                                            ${warrantymodellist.modelCode}
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="40" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="modelmonthspan${sno}" >
                                                            <input type="hidden" name="month" title="${warrantymodellist.modelmonth}" id="Model Month${sno}" value="${warrantymodellist.modelmonth}"/>
                                                            ${warrantymodellist.modelmonth}
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="35" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="modelhrsspan${sno}" >
                                                            <input type="hidden" name="hrs" title="${warrantymodellist.modelhrs}" id="Hours${sno}" value="${warrantymodellist.modelhrs}"/>
                                                            ${warrantymodellist.modelhrs}&nbsp;

                                                        </span>
                                                    </td>
                                                    <%--<td align="center" bgcolor="#FFFFFF" width="35" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="startdatespan${sno}" >
                                                            <input type="hidden" name="wty_start_date" title="${warrantymodellist.wty_start_date}"  class="datepicker" id="Start Date${sno}" value="${warrantymodellist.wty_start_date}"/>
                                                            ${warrantymodellist.wty_start_date}&nbsp;

                                                        </span>
                                                    </td>--%>
                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <%--<img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${warrantymodellist.wty_start_date}', ${sno},'${warrantymodellist.modelmonth}','${warrantymodellist.modelhrs}','${warrantymodellist.modelId}',' ${warrantymodellist.modelCode}')" title="Edit"/>--%>
                                                            <img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow(${sno},'${warrantymodellist.modelmonth}','${warrantymodellist.modelhrs}','${warrantymodellist.modelId}',' ${warrantymodellist.modelCode}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr >
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
                    <c:if test="${param ne null}">
                        <c:if test="${empty warrantymodellist}">
                            <tr bgcolor="#FFFFFF">
                                <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                    <%if (request.getParameter("nameSrch") != null) {%>
                                    Model Code '<%=request.getParameter("nameSrch") == null ? "" : request.getParameter("nameSrch")%>' not found
                                    <%}%>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <tr bgcolor="#FFFFFF">
                        <td width=100% valign="top">
                            <form action="masterAction.do" id="addModel" method="post">
                                <table width=100% bgcolor=#000000 border="0" cellpadding="0" cellspacing="0" >


                                    <tr   bgcolor="#eeeeee" height="20">
                                        <td align= left class="headingSpas" style="padding-left: 5px;">
                                            <b>ADD NEW WARRANTY MODEL</b>
                                        </td>
                                        <td align= right class="headingSpas" style="padding-right: 10px;">
                                            <input type="button" onclick="addRow()" name="plus" id="plus" value="ADD" style="border: transparent"/>
                                            <input type="button" onclick="deleterow()" name="delete" id="delete" value="DELETE" style="border: transparent"/>
                                        </td>
                                    <tr>
                                        <td width="100%" colspan="2" valign="top">
                                            <table id="myTable" width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                <tr height="30" bgcolor="#FFFFFF">

                                                    <td  style="padding-left:10px" width="33%" class="headingSpas" nowrap align="center"><B>Model Code</B></td>
                                                    <td  style="padding-left:10px" width="33%" class="headingSpas" nowrap align="center"><B>Month</B></td>
                                                    <td  style="padding-left:10px" width="34%" class="headingSpas" nowrap align="center"><B>Hours</B></td>
                                                    <%--<td style="padding-left:10px" width="25%" class="headingSpas" nowrap align="center"><B>Start Date</B></td>--%>


                                                </tr>


                                                <tr height="30" bgcolor="#FFFFFF">

                                                    <td style="padding-left:10px" width="33%" align="center">
                                                        <input type="text" name="code"  id="Model Code1" class="headingSpas"  maxlength="25" style="width:170px" />
                                                    </td>
                                                    <td style="padding-left:10px" width="33%" align="center">
                                                        <input type="text" name="month"  id="Month1" class="headingSpas"  maxlength="3" style="width:170px" />
                                                    </td>
                                                    <td style="padding-left:10px" width="34%" align="center">
                                                        <input type="text" name="hrs"  id="Hours1" class="headingSpas"  maxlength="8" style="width:170px" />
                                                    </td>
                                                    <%--<td style="padding-left:10px" width="25%" align="left" >
                                                        <input name="startdate" type="text" class="datepicker" id="startdate" style="width:210px" readonly/>

                                                        </td>--%>

                                                </tr>


                                                <tr height="30" bgcolor="#FFFFFF">

                                                    <td colspan="3" style="padding-left:10px;padding-bottom: 5px" align="center" >
                                                        <input type=button name="add" class="headingSpas" value="Submit" onclick="cUDAction('add')"/>
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
<script type="text/javascript" type="text/javascript" >

</script>

