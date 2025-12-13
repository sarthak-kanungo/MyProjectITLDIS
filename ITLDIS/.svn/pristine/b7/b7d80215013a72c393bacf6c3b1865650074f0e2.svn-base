<%-- 
    Document   : assingRoles
    Created on : Apr 9, 2014, 4:39:56 PM
    Author     : tarun.lal
--%>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<c:set var="contextPath" value='<%=request.getContextPath()%>'/>
<%
    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
    response.setHeader("Expires", "0");
    response.setHeader("Pragma", "no-cache");
    String cntxpath = request.getContextPath();
%>
<%--////////////////////////////INITIAL PAGE TO SELECT USER TYPE////////////////////////////////--%>
<script language="javascript">
    window.onload=function(){
        var data = '${masterForm.status}';
        if(data!=null && data!=""){
        var showdata = data.split('@');
        document.getElementById("msg_formchecklist").innerHTML="Please select the Checklist for Job Type \""+showdata[2]+"\" and Model Code \""+showdata[1]+"\"";
        }
    }
    function checkContent(objsubcont,objcont)
    {
        var content = document.getElementById(objcont);
        var arr = new Array();
        arr = document.getElementsByName(content.value + "SubContent");


        if (objsubcont.checked)
        {
           content.checked=true;
        }
        else
        {
            flag=false;
            for (i = 0; i < arr.length; i++) {
                if(arr[i].checked){
                    flag=true;
                }
               
               
            }
            
            if(!flag){
                  content.checked=false; 
               }
        }
    }
     function checkContent1(objcont)
    {
        var content = document.getElementById(objcont);
        if (!content.checked)
        {
            content.checked = true;
        }
        


    }
    function validate_f()
    {

        var jobtype = document.getElementById('jobType').value;
        var modelcode = document.getElementById('Model Code').value;
        var skillsSelect = document.getElementById("jobType");
        var selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
        if (jobtype == '0')
        {
            document.getElementById("msg_saveFAILED").innerHTML = "Please Select Jobtype";
            document.getElementById('jobType').focus();
            return false;
        }
        if (modelcode == '')
        {
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Model Code";
            document.getElementById('Model Code').focus();
            return false;
        }
        document.getElementById('status').value=jobtype+'@'+modelcode+'@'+selectedText;
        <%--document.getElementById('submit').style.display='';--%>
        document.getElementById("getData").click();
        <%--document.formCheckList.submit();--%>

    }

    function validate_f1(count)
    {
        var checkedcount=0;
        if(count>1)
        {
            for(var i=1;i<count;i++)
            {
                var contentId=document.getElementById("contentId"+i).value;
                if(document.getElementById(""+contentId).checked)
                {
                  checkedcount++;
                }
            }
        }
        if(checkedcount>0)
        { 
            document.getElementById("finalsubmit").click();
        }
        else{
            <%--document.getElementById("msg_formchecklist").innerHTML="Please select atleast one sub content id."--%>
            alert('Please select atleast one sub content id.')
            return false;
        }
        //document.formCheckList1.submit();

    }
</script>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='${contextPath}/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link">MANAGE FORM CHECKLIST</li>
        </ul>
    </div>
    <br/>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
            <div class="content_right1">


                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1>MANAGE FORM CHECKLIST</h1>
                                <div  align="center">
                                    <html:form action="masterAction.do?option=initFormCheckList" method="post" styleId="formCheckList">
                                        <html:hidden property="dataflag" value="true"/>
                                        <input type="hidden" id="status" name="status" value=""/>
                                        <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#ffffff">
                                            <tr>
                                                <td width="10%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:5px">Select JobType <span class="mandatory">*</span></td>
                                                <td width="20%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:5px">
                                                    <html:select property="jobType" styleId="jobType" styleClass="headingSpas" style="width:150px !important">
                                                        <html:option value="0" >-- Select Here --</html:option >
                                                        <html:options collection="jobTypeList" labelProperty="label" property="value"/>
                                                    </html:select>

                                                </td>
                                                <td width="10%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:5px">Enter Model Code <span class="mandatory">*</span></td>
                                                <td width="20%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:5px">

                                                    <input type="text" name="modelFamily" id="Model Code" value="${masterForm.modelFamily}" class="headingSpas"  maxlength="20" style="width:150px"/>
                                                    <input type="button" value="Go" id="go" class="headingSpas" style="float:none;width: 30px;padding: 2px;margin-left: 3px;height: 25px;" onclick="validate_f()"/>
                                                    <input type="submit" id="getData" value="" style="display: none;" />
                                                </td>
                                            </tr>
                                        </table>
                                    </html:form>
                                </div>
                           
                                <div  align="center">
                                    <html:form action="masterAction.do?option=addFormCheckList" method="post" styleId="formCheckList1">
                                        <div class="message" id="msg_formchecklist"></div>
                                        <input type="hidden" name="modelFamily" value="${masterForm.modelFamily}"/>
                                        <input type="hidden" name="jobType" value="${masterForm.jobType}"/>
                                        <input type="hidden" name="op" value="insert"/>
                                        <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/masterAction.do?option=initOptions'>MASTERS</a>@@MANAGE FORM CHECKLIST"/>
                                        <table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#ffffff">
                                            <c:set var="count" value="1"/>
                                            <c:set var="contentcount" value="1"/>
                                            <c:forEach items="${masterForm.dataMap}" var="contentList">

                                                <c:choose>
                                                    <c:when test="${empty contentList.value}">

                                                        <tr>
                                                            <td width="3%" align="center">&nbsp;</td>
                                                            <td ><label><strong>${contentList.key.contentDesc}</strong></label><input type="hidden" name="contentId" value="${contentList.key.contentId}"/></td>

                                                        </tr>
                                                    </c:when>
                                                    <c:otherwise>

                                                        <tr bgcolor="#FFFFFF" >
                                                            <td height="25" colspan="5">
                                                                <div align="left">
                                                                    <label><INPUT TYPE='checkbox' CLASS="headingSpas" NAME="contentId" ID="${contentList.key.contentId}" value="${contentList.key.contentId}" style="margin-left: 50px;"><strong> &nbsp;${contentList.key.contentDesc}</strong></label>
                                                                </div>
                                                                    <input type="hidden" id="contentId${contentcount}" value="${contentList.key.contentId}"/>
                                                            </td>
                                                        </tr>
                                                        <%-- <c:set var="count" value="1"/>--%>
                                                        <c:set var="i" value="1"/>
                                                        <c:forEach items="${contentList.value}" var="subCList">

                                                            <tr>
                                                                <td width="3%" align="right">
                                                                    <c:if test="${subCList.textBoxOption ne null}">
                                                                        <INPUT TYPE='checkbox' CLASS="headingSpas" NAME='${contentList.key.contentId}SubContent' value="${subCList.subContentId}" style="margin-left: 250px;" onclick="checkContent(this,'${contentList.key.contentId}')" checked>
                                                                        <script>
                                                                           
                                                                            checkContent1('${contentList.key.contentId}');
                                                                        </script> 
                                                                    </c:if>
                                                                    <c:if test="${subCList.textBoxOption eq null}">
                                                                        <INPUT TYPE='checkbox' CLASS="headingSpas" NAME='${contentList.key.contentId}SubContent' value="${subCList.subContentId}" style="margin-left: 250px;" onclick="checkContent(this,'${contentList.key.contentId}')">
                                                                    </c:if>
                                                                </td>
                                                                <td align="left">${subCList.subContentDesc}
                                                                    <%--<input type="hidden" name="${contentList.key.contentId}SubContent" value="${subCList.subContentId}"/>--%>
                                                                </td>

                                                            </tr>
                                                            <c:set var="i" value="${i+1}"/>
                                                            <c:set var="count" value="${count+1}"/>
                                                        </c:forEach>
                                                        <c:set var="contentcount" value="${contentcount+1}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>


                                        </table>
                                            <c:if test="${count gt 1}">
                                            <input type="button" id="submit" styleClass="headingSpas" value='Submit' onclick='return validate_f1(${contentcount})' />
                                            </c:if>
                                            <input type="submit" id="finalsubmit" style="display: none"/>
                                    </html:form>

                                </div>
                </div>
            </div>
    </center>
</div>

