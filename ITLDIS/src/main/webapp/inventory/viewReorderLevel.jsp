<%-- 
    Document   : viewReorderLevel
    Created on : 23-Dec-2014, 15:46:47
    Author     : kundan.rastogi
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
<script>
    <c:set var="dCode" value='<%=dealerCode%>'/>
   function search()
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
      // document.getElementById('etype').value="view";
       document.getElementById("searchform").submit();
   }

  function Export()
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
         document.getElementById('etype').value="export";
         document.getElementById('searchform').submit();
    }


    function editRow(partNo, row, minLevel,maxLevel,reorderLevel){
      document.getElementById('MinLevel'+row).innerHTML="<input type=\"text\" name=\"minLevel\"  maxlength=\"7\" class=\"headingSpas\" id=\"NewMinLevel"+row+"\" style=\"width:70px\" value=\""+minLevel+"\"/>";
      document.getElementById('MaxLevel'+row).innerHTML="<input type=\"text\" name=\"maxLevel\"  maxlength=\"7\" class=\"headingSpas\" id=\"NewMaxLevel"+row+"\" style=\"width:70px\" value=\""+maxLevel+"\"/>";
      document.getElementById('ReorderLevel'+row).innerHTML="<input type=\"text\" name=\"reorderLevel\"  maxlength=\"7\" class=\"headingSpas\" id=\"NewReorderLevel"+row+"\" style=\"width:70px\"  value=\""+reorderLevel+"\"/>";              <%--  onblur=\"checkReorderQty('"+reorderLevel+"')\"--%>
      document.getElementById('imageButton'+row).innerHTML="<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+partNo+"','"+minLevel+"','"+maxLevel+"','"+reorderLevel+"')\"/>";
    }

    function updateAction(str, row,partNo)  //,minLevel,maxLevel,reorderLevel
    {

        if(document.getElementById("NewMinLevel"+row).value==""){
            document.getElementById("NewMinLevel"+row).focus();
           alert(not_blank_validation_msg+"Min Qty");
            return false;
        }
        if(document.getElementById("NewMaxLevel"+row).value==""){
            document.getElementById("NewMaxLevel"+row).focus();
           alert(not_blank_validation_msg+"Max Qty");
            return false;
        }
        if(document.getElementById("NewReorderLevel"+row).value==""){
            document.getElementById("NewReorderLevel"+row).focus();
            alert(not_blank_validation_msg+"Reorder Qty");
            return false;
        }

        if(!trim(document.getElementById("NewMinLevel"+row).value).match(/^[0-9]+$/)){
            document.getElementById("NewMinLevel"+row).focus();
            alert("Min Qty "+numeric_msg);
            return false;
        }
        if(!trim(document.getElementById("NewMaxLevel"+row).value).match(/^[0-9]+$/)){
            document.getElementById("NewMaxLevel"+row).focus();
             alert("Max Qty "+numeric_msg);
            return false;
        }
        if(!trim(document.getElementById("NewReorderLevel"+row).value).match(/^[0-9]+$/)){
            document.getElementById("NewReorderLevel"+row).focus();
            alert("Reorder Qty "+numeric_msg);
             return false;
        }

        if( eval(document.getElementById("NewMaxLevel"+row).value) < eval(document.getElementById("NewMinLevel"+row).value)){
             document.getElementById("NewMaxLevel"+row).focus();
             alert("Max Qty "+greater_than_val_msg+" Min Qty");
             return false;
        }
       var minQ=eval(document.getElementById("NewMinLevel"+row).value);
       var maxQ=eval(document.getElementById("NewMaxLevel"+row).value);
       var rQ=eval(document.getElementById("NewReorderLevel"+row).value);

       //alert(" min "+minQ+ "maxQ "+maxQ+ "reqQ "+rQ)
      // alert(rQ <maxQ && rQ >minQ)

        if(!(rQ <= maxQ && rQ >= minQ)){
            document.getElementById("NewReorderLevel"+row).focus();
             alert("Reorder Qty "+range_of_val_msg+"Min & Max Qty");
            return false;
        }

        //  alert( eval(document.getElementById("NewReorderLevel"+row).value) < eval(document.getElementById("NewMaxLevel"+row).value) )
        //  alert(eval(document.getElementById("NewMinLevel"+row).value) < eval(document.getElementById("NewReorderLevel"+row).value)    )
     
      <%--  if( (eval( document.getElementById("NewReorderLevel"+row).value) < eval(document.getElementById("NewMaxLevel"+row).value )) && ( eval(document.getElementById("NewReorderLevel"+row).value)  >  eval(document.getElementById("NewMinLevel"+row).value) )){
              document.getElementById("NewReorderLevel"+row).focus();
              alert("Reorder Qty "+range_of_val_msg+"Min & Max Qty");
              return false;
         }--%>



        <%--var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var chckValue = document.getElementById("newBinLocation"+row).value;
        chckValue=chckValue.replace(objRegExp,'');
        var temp=objSpecExp.exec(chckValue);
        if(temp)
        {
            document.getElementById("newBinLocation"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML=specialChar_validation_msg+"Bin Location";
            window.scrollTo(0,0);
            return false;
        }--%>





        var newMinLevel = TrimAll(document.getElementById("NewMinLevel"+row).value);
        var newMaxLevel = TrimAll(document.getElementById("NewMaxLevel"+row).value);
        var newReorderLevel = TrimAll(document.getElementById("NewReorderLevel"+row).value);
        var url="<%=cntxpath%>/inventoryAction.do?option=updateReorderLevel&partno="+partNo+"&minLevel="+newMinLevel+"&maxLevel="+newMaxLevel+"&reorderLevel="+newReorderLevel+"&type="+str+"&t="+new Date().getTime();
        <%--alert(url)--%>
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
                    if(res =='Success'){
                        document.getElementById("msg_saveFAILED").innerHTML="Re Order Level Successfully Updated for :"+partNo;
                        document.getElementById('MinLevel'+row).innerHTML=""+newMinLevel+"";
                        document.getElementById('MaxLevel'+row).innerHTML=""+newMaxLevel+"";
                        document.getElementById('ReorderLevel'+row).innerHTML=""+newReorderLevel+"";
                        document.getElementById('imageButton'+row).innerHTML="<img src=\"images/edit.gif\" onclick=\"editRow('"+partNo+"',"+row+",'"+newMinLevel+"','"+newMaxLevel+"','"+newReorderLevel+"')\" title=\"Edit\"/>";
                    }
                    else{
                        document.getElementById("msg_saveFAILED").innerHTML=binlocfailure_validation_msg;
                    }
                }
            }
        };
        xmlHttp.open("GET",url, true);
        xmlHttp.send(null);
        return false;
    }

    <%--function checkReorderQty(){
        if( ( document.getElementById("NewReorderLevel"+row).value < document.getElementById("NewMaxLevel"+row).value ) && ( document.getElementById("NewReorderLevel"+row).value  <  document.getElementById("NewMinLevel"+row).value )){
             document.getElementById("NewReorderLevel"+row).focus();
             alert("Reorder Qty "+range_of_val_msg+"Min & Max Qty");
             return false;
        }

    }--%>
    
</script>
<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
    </head>
    <body>
  <div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>

            <li class="breadcrumb_link"><label><bean:message key="label.spare.viewReorderLevel" /></label></li>
      </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
         <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1 class="hText"> <bean:message key="label.spare.viewReorderLevel" /></h1>

                <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >
            <form action="inventoryAction.do?option=viewReorderLevel" onsubmit="" method="post" id="searchform">
                <%if (userFunctionalities.contains("101")) {%>
                <tr bgcolor="#FFFFFF">
                    <td width="10%" align="right" style="padding-left: 5px" > <bean:message key="label.catalogue.partNo" />    </td>
                    <td width="10%" align="left"><input type="text" name="partnum" style="width: 180px" value="${inventoryForm.partnum}"/></td>
                    <td align="left"><input type="button" value="GO" onclick="search()"/>
                        <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="etype" id="etype" />
                    </td>
                </tr>
                <%} else {%>
                <tr bgcolor="#FFFFFF">
                    <td align="right" style="padding-left: 5px" > <bean:message key="label.catalogue.partNo" />    </td>
                    <td align="left"><input type="text" name="partnum" style="width: 180px" value="${inventoryForm.partnum}"/></td>
                    <td  align="right" class="headingSpas" nowrap style="padding-right:10px" > <bean:message key="label.common.dealerName" />    </td>
                    <td align="left">
                        <select id="Dealer Name" name="dealerCode" style="width:300px !important">
                            <option value="" >--Select Here--</option>
                            <c:forEach items="${dealerList}"  var="labelValue">
                                <c:if test="${inventoryForm.dealerCode eq labelValue[0]}">
                                    <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                </c:if>
                                <c:if test="${inventoryForm.dealerCode ne labelValue[0]}">
                                    <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                   <td align="left"><input type="button" value="GO" onclick="search()"/>
                        <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="etype" id="etype" />
                    </td>
                </tr>
                <%}%>

            </form>


            <c:if test="${empty dataList}">
                <tr bgcolor="#FFFFFF">
                    <td valign="top" align="center" colspan="10" class="headingSpas" style="padding-top:10px;color: red">
                        <bean:message key="label.common.noRecordFound" />
                    </td>
                </tr>
            </c:if>


            <c:if test="${!empty dataList}">
                <tr>
                    <%if (userFunctionalities.contains("101")) {%>
                    <td colspan="3">
                        <%} else {%>
                    <td colspan="5">
                        <%}%>
                        <pg:pager url="inventoryAction.do" maxIndexPages="10" maxPageItems="15">
                            <pg:param name="option" value="viewReorderLevel"/>
                         <%--   <pg:param name="partnum" value="${dataList.partnum}"/>--%>
                            <table width="100%" border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                <tr bgcolor="#eeeeee" height="20">
                                    <td  align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.catalogue.sNo" />   </B> </td>
                                    <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partno_small" /></B></td>
                                    <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partdesc_small" /></B></td>
                                    
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.spare.minQty" /> </B></td>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.spare.maxQty" /> </B></td>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.spare.reorderQty" /></B></td>
                                    
                                   <%-- <%if (userFunctionalities.contains("807")){%>--%>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.action" /> </B></td>
                                  <%--  <%}%>--%>
                                </tr>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="dataList" name="dataList">
                                    <pg:item>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="center" >${sno}</td>
                                            <td align="left" ><span>${dataList.partno}</span></td>
                                            <td align="left" ><span>${dataList.part_desc}</span></td>
                                           <td align="center" ><span id="MinLevel${sno}">${dataList.minLevel}</span></td>
                                            <td align="center" ><span id="MaxLevel${sno}">${dataList.maxLevel}</span></td>
                                            <td align="center" ><span id="ReorderLevel${sno}">${dataList.reorderLevel}</span></td>
                                           
                                          <%--  <%if (userFunctionalities.contains("807")){%>--%>
                                            <td align="center">
                                                <span id ="imageButton${sno}" >
                                                <img alt="" src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${dataList.partno}', ${sno}, '${dataList.minLevel}','${dataList.maxLevel}','${dataList.reorderLevel}' )" title="Edit"/>
                                                </span>
                                            </td>
                                           <%-- <%}%>--%>
                                        </tr>
                                    </pg:item>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                                <tr>
                                   <%-- <%if (userFunctionalities.contains("807")){%>
                                    <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                    <% } else { %>--%>
                                    <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                    <%-- <%}%>--%>
                                        <table  align="center" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <td>
                                                    <pg:index>
                                                        <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle" style="float:left;"/></a></pg:first>&nbsp;
                                                        <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle" style="float:left;"/></a></pg:prev>&nbsp;
                                                        <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                        <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle" style="float:right"/></a></pg:next>&nbsp;
                                                        <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle" style="float:right"/></a></pg:last>
                                                        </pg:index>
                                                </td>
                                            </tr>
                                        </table>
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
    </center>
  </div>
</body>
</html>
