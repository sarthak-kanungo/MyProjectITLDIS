<%-- 
    Document   : v_aggregateMaster
    Created on : Apr 18, 2014, 10:25:09 AM
    Author     : Avinash.Pandey
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
<script type="" language="JavaScript" src='${pageContext.request.contextPath}/js/validation.js'></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
    
<script language="JavaScript" type="text/javascript">
    function submitForm() {
        document.getElementById('searchBy').submit();
    }
    function checkValue(strObject, strValue)
    {
//        var objRegExp = /^(\s*)$/g;
//        var objSpecExp = /['&\\@\!\~\`\#\$\%\(\)\=\{\}\[\]\|\:\;\<\>\,\.\?\+\"\^\*/]/g;
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var objSpecExpDes = /['#\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z0-9]+$/;

        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);
        var tempD= objSpecExpDes.exec(strValue);
        var text = strObject.id;
        if (strObject.id == 'aggCode') {
            text = 'Aggregate Code';
          
        }
        if (strObject.id == 'aggDesc') {
            text = 'Aggregate Description';
        }
        if (strValue.length == 0)
        {

            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Value in " + text + " Field";
            window.scrollTo(0, 0);//go to top of page
            return false;

        }
        else if (strObject.id == 'aggCode') {
            text = 'Aggregate Code';
            if (!ck_code.test(strValue)) {
                       strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
           document.getElementById("msg_saveFAILED").innerHTML = "Special Characters or Space are not allowed in " + text + " field";
            window.scrollTo(0, 0);//go to top of page
            return false;
                          }
        }
        else if (tempD)
        {
            if (strObject.tagName == "INPUT")
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }

            return false;

        }
    }
    function cUDAction(str, row, strVal ,agg_id)
    {

        var url = null;
        
        if (str == 'add') {
            var elementArr = new Array('aggCode', 'aggDesc');
            var strValue = null;
            var strObject = null;

            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }

            var aggCode = TrimAll(document.getElementById("aggCode").value);
            var aggDesc = TrimAll(document.getElementById("aggDesc").value);
            var tempDes=replaceall(aggDesc,'&','`');
            url = "<%=cntxpath%>/masterAction.do?option=manageAggregateMaster&type=" + str + "&aggCode=" + aggCode + "&aggDesc=" + tempDes + "&primary_id="+agg_id+"&t=" + new Date().getTime();
        }
        else {
            url = "<%=cntxpath%>/masterAction.do?option=manageAggregateMaster&status=" + str + "&type=" + str + "&aggCode=" + strVal + "&primary_id="+agg_id+"&t=" + new Date().getTime();
            
        }
        //alert(url);
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
                    if (str != 'add' && res.split("@@")[0] == 'Success') {
                        if (str == 'Y') {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','" + row + "','" + strVal + "','"+agg_id+"')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        } else {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','" + row + "','" + strVal + "','"+agg_id+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                    if (str == 'add' && res.split("@@")[0] == 'Success') {
                        document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                        document.getElementById("aggCode").value = '';
                        document.getElementById("aggDesc").value = '';
                        // setTimeout(function() { submitForm(); }, 3000);
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }

    function editRow(aggCode, row,aggDesc,agg_id){
        var url = "<%=cntxpath%>/masterAction.do?option=manageAggregateMaster&type=check&aggCode="+aggCode+"&t="+new Date().getTime();
        var flag = false;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                if (xmlHttp.responseText.indexOf("Session has been expired") != -1)
                {
                    document.location.href = "${contextPath}/login/SessionExpired.jsp";
                }
                else
                {
                    res = trim(xmlHttp.responseText);
                    if(res == 'notexist'){
                       // document.getElementById('aggCode'+row).innerHTML="<input type=\"text\" name=\"aggCode\" maxlength='5' class=\"headingSpas\" id=\"newAggCode"+row+"\" style=\"width:80px\" value=\""+aggCode+"\"/>";
                        document.getElementById('aggDesc'+row).innerHTML="<input type=\"text\" name=\"aggDesc\" size='150' maxlength='150' class=\"headingSpas\" id=\"newAggDesc"+row+"\" style=\"width:450px\" value=\""+aggDesc+"\"/>";
                        flag = true;
                    }else{
                        document.getElementById('aggDesc'+row).innerHTML="<input type=\"text\" name=\"aggDesc\" size='150' maxlength='150' class=\"headingSpas\" id=\"newAggDesc"+row+"\" style=\"width:450px\" value=\""+aggDesc+"\"/>";
                    }
                }
            }
        };
        xmlHttp.open("GET", url, false);
        xmlHttp.send(null);
        document.getElementById('imageButton'+row).innerHTML="<input type=button class=\"headingSpas\" name=\"apply\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+aggCode+"','"+flag+"','"+agg_id+"')\"/>";

    }

        function updateAction(str, row, oldAggCode,flag,agg_id)
        {
            var newAggCode='';
            var objRegExp = /^(\s*)$/g;
           // var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
            var objSpecExp = /['#\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
             var ck_code = /^[a-zA-Z0-9]+$/;

          <%--  if(flag == 'true'){
                if(document.getElementById("newAggCode"+row).value==""){
                    document.getElementById("newAggCode"+row).focus();
                    document.getElementById("msg_saveFAILED").style.display = "";
                    document.getElementById("msg_saveFAILED").innerHTML="Please enter value in Aggregate Code field";
                    return false;
                }
                var chckValue = document.getElementById("newAggCode"+row).value;
//                chckValue=chckValue.replace(objRegExp,'');
//                var temp=objSpecExp.exec(chckValue);
//                if(temp)
//                {
//                    document.getElementById("newAggCode"+row).focus();
//                    document.getElementById("msg_saveFAILED").style.display = "";
//                    document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in Aggregate Code field";
//                    window.scrollTo(0,0);//go to top of page
//                    return false;
//                }
                text = 'Aggregate Code';
               if (!ck_code.test(chckValue)) {
               
                document.getElementById("newAggCode"+row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
               return false;
                        
              }
                newAggCode = TrimAll(document.getElementById("newAggCode" + row).value);
            }--%>
            
            if (document.getElementById("newAggDesc" + row).value == "") {
                document.getElementById("newAggDesc" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Aggregate Description";
                return false;
            }
            var chckValue = document.getElementById("newAggDesc" + row).value;
            chckValue = chckValue.replace(objRegExp, '');
            var temp = objSpecExp.exec(chckValue);
            if (temp)
            {
                document.getElementById("newAggDesc" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in Aggregate Description field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }

            //  var item_No = TrimAll(document.getElementById("newMechanin_Code"+row).value);
            var aggDesc = TrimAll(document.getElementById("newAggDesc" + row).value);
             var tempDes=replaceall(aggDesc,'&','`');

   var url = "<%=cntxpath%>/masterAction.do?option=manageAggregateMaster&id=" + oldAggCode + "&aggCode=" + newAggCode + "&aggDesc=" + tempDes + "&type=" + str + "&primary_id="+agg_id+"&t=" + new Date().getTime();

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
                        if (str != 'add' && res.split("@@")[0] == 'Success') {
                            //var tableObject=document.getElementById('data');
                            if(flag == 'true'){
                               // document.getElementById('aggCode'+row).innerHTML=""+newAggCode+"";
                                document.getElementById('aggCode'+row).innerHTML=""+oldAggCode+"";
                            }else{
                                document.getElementById('aggCode'+row).innerHTML=""+oldAggCode+"";
                            }
                            document.getElementById('aggDesc' + row).innerHTML = "" + aggDesc + "";
                            document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow('" + oldAggCode + "'," + row + ",'" + aggDesc + "','"+agg_id+"')\" title=\"Edit\"/>";
                        }
                    }
                }
            };
            xmlHttp.open("GET", url, true);
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
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link">MANAGE AGGREGATE</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1>MANAGE AGGREGATE </h1>


                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initAggregateMaster" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="left">Aggregate Description</td>
                                        <td style="padding-left:10px" width="140px">
                                            <html:text property="nameSrch" styleClass="headingSpas"  style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <td style="padding-left:10px" align="left" >
                                            <input type=button name="Go" class="headingSpas" value="Search" onClick = "submitForm()"/>
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty aggregateList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                Aggregate Description not Found. !
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty aggregateList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initAggregateMaster"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td   align="left" class="headingSpas" width="150"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Aggregate Code</td>
                                            <td   align="left" class="headingSpas"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Aggregate Description</td>
                                            <td   align="center" class="headingSpas" width="75" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Edit</td>
                                            <td   align="center" class="headingSpas" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>isActive</td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="aggregateList" name="aggregateList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${aggregateList.aggCode}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="aggCode${sno}" >
                                                            ${aggregateList.aggCode}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${aggregateList.aggDesc}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="aggDesc${sno}" >
                                                            ${aggregateList.aggDesc}
                                                        </span>
                                                    </td>

                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img alt="" src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${aggregateList.aggCode}', ${sno}, '${aggregateList.aggDesc}','${aggregateList.primary_id}','${aggregateList.id}@@${aggregateList.name}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${aggregateList.status}', ${sno}, '${aggregateList.aggCode}','${aggregateList.primary_id}')"  class="headingSpas" style="color: blue;" >
                                                                ${aggregateList.status}&nbsp;
                                                            </a>
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

                    <tr bgcolor="#FFFFFF">
                        <td width=100% valign="top" >
                            <form action="#" >
                                <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                    <tr bgcolor="#eeeeee" height="20">
                                        <td  style="padding-left: 5px" align= left class="headingSpas">
                                            <b>ADD NEW AGGREGATE</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >

                                                <tr height="30">
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Aggregate Code</td>
                                                    <td style="padding-left:10px;padding-top: 5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="aggCode"  id="aggCode" class="headingSpas" onkeypress="return CommentsMaxLength(this, 5);" maxlength="5" style="width:120px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>

                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Aggregate Description</td>
                                                    <td style="padding-left:10px;padding-top: 5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="aggDesc"  id="aggDesc" class="headingSpas" size="150"  maxlength="150" style="width:240px" onkeypress="return CommentsMaxLength(this, 200);" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="4" style="padding-left:10px;padding-bottom: 5px" align="center" bgcolor="#FFFFFF">
                                                        <input type=button name="add" value="Submit" class="headingSpas" onclick="cUDAction('add')"/>
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
