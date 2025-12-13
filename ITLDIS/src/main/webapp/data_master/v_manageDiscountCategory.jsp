<%--
    Document   : v_manageCustCategory
    Created on : Feb 4, 2016, 3:24:42 PM
    Author     : ashutosh.kumar1
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
    function submitForm(){
        document.getElementById('searchBy').submit();
    }
    function checkValue(strObject,strValue)
    {
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;

        strValue=strValue.replace(objRegExp,'');
        var temp=objSpecExp.exec(strValue);
        var temp1=isNaN(strValue);
        
        if (strValue>100){          
            document.getElementById("msg_saveFAILED").innerHTML= "'" + strObject.id + "'" + "  field must be less than 100 ";
            window.scrollTo(0,0);//go to top of page
            return false;
        }
        
        if(strValue.length == 0)
        {
            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please enter value in " + "'"+strObject.id + "'" +"  field";
            window.scrollTo(0,0);//go to top of page
            return false;

        }
        else if(temp)
        {
            if(strObject.tagName=="INPUT")
            {
                //alert("Please Enter "+elementArr[i]);
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in "+"'"+strObject.id+"'"+"  field";
                window.scrollTo(0,0);//go to top of page
                return false;
            }

            return false;

        }
        else if(temp1)
        {

            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Only Numeric or Float value are allowed in "+ "'"+strObject.id+"'"+"  field";
            window.scrollTo(0,0);//go to top of page
            return false;

        }
    }
    function donotsubmit()
    {
        return false;
    }
    function validate(sno)
    {
       
        for(var i=1;i<sno;i++){
            var strObject=document.getElementById("Discount"+i);
            var strValue =document.getElementById("Discount"+i).value;
            if(checkValue(strObject,strValue)==false)
            {
                return false;
            }
        }
              
        document.getElementById('addCustCategory').submit();
    }
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a> DISCOUNT AGAINST CUSTOMER CATEGORY</li>


        </ul>
    </div>

    <div class="message" id="msg_saveFAILED">${successmsg}</div>
    <center>

        <div class="content_right1">

            <div class="con_slidediv1" style="position: relative;width: 100%">

                <h1>DISCOUNT AGAINST CUSTOMER CATEGORY</h1>

                <table cellspacing=1 cellpadding=5 border=0 width=100%>

                    <c:if test="${!empty DiscountCategoryList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="15">                                    
                                    <pg:param name="option" value="initDiscountCategory"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                        <html:form action="masterAction.do?option=manageDiscountCategory&param=search" method="post" styleId="addCustCategory" >
                                        <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bgcolor=#cccccc>
                                            <tr  bgcolor="#eeeeee" height="25">
                                                <td   align="center" width="25"  class="headingSpas" style="padding-left:5px" nowrap><b>S.No</b></td>
                                                <td   align="left" width="250"  class="headingSpas" style="padding-left:5px" nowrap><b>Description</b></td>
                                                <td   align="center" width="40" class="headingSpas" style="padding-left:5px" nowrap><b>Discount %</b></td>
                                                <%--<td   align="center" width="35" class="headingSpas" nowrap><b>isActive</b></td>--%>
                                            </tr>
                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="DiscountCategoryList" name="DiscountCategoryList">
                                                <pg:item>
                                                    <tr id ="${sno}" height="20">
                                                        <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                        <td align="left" bgcolor="#FFFFFF" title="${DiscountCategoryList.name}" width="250" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                            <span  >
                                                                ${DiscountCategoryList.name}
                                                            </span>
                                                            <input type="hidden" name="Desc${sno}" value="${DiscountCategoryList.name}"  id="Desc${sno}"/>
                                                            <input type="hidden" name="id${sno}" value="${DiscountCategoryList.id}"  id="id${sno}"/>

                                                        </td>

                                                        <td align="center" bgcolor="#FFFFFF"   class="headingSpas" style="padding-left:5px; padding-right: 5px; height: 30px">
                                                            <input type="text" name="discount${sno}"  id="Discount${sno}" class="headingSpas" value="${DiscountCategoryList.discount}" maxlength="200" style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                                        </td>
                                                    </tr>
                                                </pg:item>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </logic:iterate>
                                            <tr >
                                                <td colspan="3" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                    <pg:index>
                                                        <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                                                        <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                                                        <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                        <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                                                        <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                                                        </pg:index>
                                                </td>
                                            </tr>

                                            <tr><td colspan="3" style="padding-left:10px;padding-bottom: 5px; padding-top: 5px" bgcolor="#FFFFFF" align="center" >
                                                    <input type=button name="add" class="headingSpas" value="Submit" onclick="validate(${sno})"/>
                                                    <input type="hidden" name="sno" value="${sno}"  />
                                                </td></tr>
                                        </table>
                                    </html:form>
                                </pg:pager>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${param ne null}">
                        <c:if test="${empty DiscountCategoryList}">
                            <tr bgcolor="#FFFFFF">
                                <td valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                    <%if (request.getParameter("nameSrch") != null) {%>
                                    Description '<%=request.getParameter("nameSrch") == null ? "" : request.getParameter("nameSrch")%>' not found
                                    <%}%>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>


                </table> </div>
        </div>
    </center>
</div>


