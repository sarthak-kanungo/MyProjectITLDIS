<%--
    Document   : genratePackingList
    Created on : Dec 3, 2014, 11:02:56 AM
    Author     : kundan.rastogi
--%>


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

function checkQty(row){

  if(document.getElementById("DisRequired"+row).value=='Yes' && document.getElementById("DispatchQty"+row).value!=""){
            if(!document.getElementById("DispatchQty"+row).value.match(/^[0-9]+$/)){
                document.getElementById("DispatchQty"+row).value='';
               // document.getElementById("DispatchQty" + row).focus();
                alert("Dispatched Qty "+numeric_msg);
                return false;
            }
        }

        if(document.getElementById("DisRequired"+row).value=='Yes'){
            if(eval(document.getElementById("DispatchQty"+row).value) > eval(document.getElementById("ClaimQty"+row).value)){
                alert("Dispatched Qty "+ maxLength_validation_msg +"Claimed Qty");
               // document.getElementById("DispatchQty"+row).focus();
                document.getElementById("DispatchQty"+row).value="";
                return false;
            }
        }
        if(document.getElementById("DisRequired"+row).value=='Yes' && document.getElementById("DisRequired"+row).value!=""){
            if(!eval(document.getElementById("DispatchQty"+row).value) > 0){
                alert("Dispatched Qty must be Greater Than 0");
              //  document.getElementById("DispatchQty"+row).focus();
                document.getElementById("DispatchQty"+row).value="";
                return false;
            }
        }



        if((document.getElementById("DisRequired"+row).value=='No'|| document.getElementById("DisRequired"+row).value=='CFT') && document.getElementById("DispatchQty"+row).value!=""){
          document.getElementById("DispatchQty"+row).focus();
          document.getElementById("DispatchQty"+row).value="";
            alert(noNeed_value_validation_msg+"Dispatch Qty when Dispatched Required : No / Sent to CFT");
            return false;
        }
         if((document.getElementById("DisRequired"+row).value=='No'|| document.getElementById("DisRequired"+row).value=='CFT') && document.getElementById("BoxNo"+row).value!=""){
          document.getElementById("BoxNo"+row).focus();
          document.getElementById("BoxNo"+row).value="";
           alert(noNeed_value_validation_msg+"Box No when Dispatched Required : No / Sent to CFT");
           
            return false;
        }
    }
 function caMaxLength(ca, MaxLen)
    {
        return (ca.value.length <= MaxLen);
    }
    
    function submitForm(){

        var count=document.getElementById("PartCount").value;
         for( var i=1;i<=count;i++){
         if(document.getElementById("DisRequired"+i).value=='Yes' && document.getElementById("DispatchQty"+i).value==""){
           //document.getElementById("DispatchQty"+i).focus();
            alert(not_blank_validation_msg+"Dispatch Qty");
            return false;
        }
         if(document.getElementById("DisRequired"+i).value=='Yes' && document.getElementById("BoxNo"+i).value==""){
          document.getElementById("BoxNo"+i).focus();
            alert(not_blank_validation_msg+"Box No");
            return false;
        }
         if(document.getElementById("DisRequired"+i).value=='Yes' && !document.getElementById("BoxNo"+i).value.match(/^[0-9 A-Za-z]+$/)){
          document.getElementById("BoxNo"+i).focus();
            alert(specialChar_validation_msg+" BoxNo");
            return false;
        }

        <%--if(document.getElementById("DisRequired"+i).value=='No' && document.getElementById("DispatchQty"+i).value!=""){
          document.getElementById("DispatchQty"+i).focus();
          document.getElementById("DispatchQty"+i).value="";
            alert(noNeed_value_validation_msg+"Dispatch Qty when Dispatched Required : No");
            return false;
        }
         if(document.getElementById("DisRequired"+i).value=='No' && document.getElementById("BoxNo"+i).value!=""){
          document.getElementById("BoxNo"+i).focus();
          document.getElementById("BoxNo"+i).value="";
           alert(noNeed_value_validation_msg+"Box No when Dispatched Required : No");
            return false;
        }--%>
       


      }
     document.getElementById("myForm").submit();
    }



</script>

    <div class="contentmain1">
        <div class="breadcrumb_container" >
            <ul class="hText">
                <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.common.warranty" /></a></li>
                <li class="breadcrumb_link" ><a href='<%=cntxpath%>/warrantyAction.do?option=pendingForDispatch'><bean:message key="label.warranty.pendingforPackGen" /></a></li>
                <li class="breadcrumb_link" ><label ><bean:message key="label.warranty.genPacking" /></label></li>                            <%--<bean:message key="label.warranty.jobCardPendingForWarranty" />--%>
            </ul>
        </div>
        <div class="message" id="msg_saveFAILED"></div>
        <center>
            <div class="content_right1">
                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1><bean:message key="label.warranty.genPacking" /></h1>
                    <form action="<%=cntxpath%>/warrantyAction.do" method="post" id="myForm" onsubmit="return false;" >
                        <input type="hidden" name="option" value="savePartDispatch" >
                         <input type="hidden" name="upperLink" value="<a href ='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a>@@<a href = '<%=cntxpath%>/warrantyAction.do?option=pendingForDispatch'><bean:message key="label.warranty.pendingforPackGen" /></a>@@<bean:message key="label.warranty.genPacking" />"/>
                       <%-- <div style="overflow:auto; position: relative;width: 100%; height:300px" >--%>
                        <table id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                            <tr  bgcolor="#eeeeee" height="20">
                                <td   align="center"  class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.S.No" /></b></td>
                                <td   align="left"   class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.warrantyClaimNo" /></b></td>
                                <td   align="left"   class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.claimDate" /></b></td>
                                <td   align="left"   class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.common.jobcardno" /></b></td>
                               <td align="left" class="headingSpas" nowrap style="padding-left:5px;" ><B><bean:message key="label.common.partno_small" /></B></td>
                              <td align="left" class="headingSpas" nowrap style="padding-left:5px;" ><B><bean:message key="label.common.partdesc_small" /></B></td>
                              <td align="center" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.warranty.claimQty" />  </B></td>
                              <td align="left" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.warranty.dispatchRequired" />  </B></td>
                              <td align="left" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.warranty.dispatchQty" />   </B></td>
                              <td align="left" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.warranty.bixNo" />  </B></td>


                               
                            </tr>

                            <c:if test="${!empty jobCardList}">
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="jobCardList" name="jobCardList">

                                    <tr id ="${sno}" height="20">
                                        <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                        <td align="left" bgcolor="#FFFFFF" title="${jobCardList.warrantyClaimNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span id ="compType${sno}" >
                                                <a href="warrantyAction.do?option=viewWarrantyClaimDetail&warrntyClaimNo=${jobCardList.warrantyClaimNo}&flag=viewGenPack">${jobCardList.warrantyClaimNo} </a>
                                            </span>
                                            <input type="hidden" name="warClaimNoArr" id="WarClaimNo" class="warText" value="${jobCardList.warrantyClaimNo}">
                                        </td>
                                        <td align="left" bgcolor="#FFFFFF" title="${jobCardList.claimDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span id ="compType${sno}" >
                                                ${jobCardList.claimDate}
                                            </span>
                                        </td>
                                        <td align="left" bgcolor="#FFFFFF" title=" ${jobCardList.jobCardNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span id ="compType${sno}" >

                                                <%--  ${jobCardList.jobCardNo}--%>
                                                <a href="<%=cntxpath%>/serviceProcess.do?option=viewJobcard&vinNo=${jobCardList.vinNo}&jobCardNo=${jobCardList.jobCardNo}&flag=penPackGen" > ${jobCardList.jobCardNo}</a>
                                                    
                                            </span>
                                        </td>
                                        
                                        <td align="left" bgcolor="#FFFFFF" title="  ${jobCardList.partNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span id ="compType${sno}" >
                                                ${jobCardList.partNo}   <input type="hidden" name="partNoArr" id="PartNo" class="warText" value="${jobCardList.partNo}">
                                                <input type="hidden" name="partCategoryArr" id="PartCat${sno}" class="warText" value="${jobCardList.partCategory}">
                                            </span>
                                        </td>
                                        <td align="left" bgcolor="#FFFFFF" title="  ${jobCardList.partDesc}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span id ="compType${sno}" >
                                                ${jobCardList.partDesc}
                                            </span>
                                        </td>
                                        <td align="center" bgcolor="#FFFFFF" title="  ${jobCardList.qty}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span id ="compType${sno}" >                                            </span>

                                            ${jobCardList.qty} <input type="hidden" name="qty" value="${jobCardList.qty}" id="ClaimQty${sno}">
                                        </td>
                                        <td align="left" bgcolor="#FFFFFF" title="  "  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span id ="compType${sno}" >YES
                                              <%--<select name="dispatchReqArr" id="DisRequired${sno}">
                                                  <option value="">- Select -</option>
                                                 <c:if test="${jobCardList.partCategory ne 'LUBES'}">
                                                  <option value="Yes">YES</option>
                                                 </c:if>
                                                  <option value="No">NO</option>
                                                  <option value="CFT">SENT TO CFT</option>
                                               
                                              </select>--%>
                                              <input type="hidden"  name="dispatchReqArr" id="DisRequired${sno}" value="Yes">
                                            </span>
                                        </td>
                                        <td align="left" bgcolor="#FFFFFF" title=""  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span id ="compType${sno}" >
                                               <input type="text" name="dispatchQtyArr" value="${jobCardList.qty}" id="DispatchQty${sno}" onblur="checkQty(${sno})" maxlength="7">
                                            </span>
                                        </td>
                                        <td align="left" bgcolor="#FFFFFF" title=""  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span id ="compType${sno}" >
                                             <input type="text" name="boxNoArr" value="" id="BoxNo${sno}" maxlength="25" onblur="checkQty(${sno})">
                                            </span>
                                        </td>
                                       
                                        


                                    </tr>

                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>

                                     <input type="hidden" name="partCount" id="PartCount" value="${sno-1}">

                                    <tr height="50">
                                        <td colspan="2" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                         <B><bean:message key="label.warranty.remark" />     </B>
                                        </td>
                                    <td colspan="8" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                        <textarea name="remark" cols="" rows="3" id="Remarks" onkeypress="return caMaxLength(this, 250);"> </textarea>


                                    </td>
                                </tr>
                                    <tr height="50">
                                    <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                        <input type="button" name="Submit" value="Submit" onclick="submitForm()">
                                    </td>
                                </tr>

                         
                            </c:if>



                            <c:if test="${empty jobCardList}">
                                <tr bgcolor="#FFFFFF">
                                    <%if (userFunctionalities.contains("702")) {%>
                                    <td valign="top" align="center" colspan="10" class="headingSpas" style="padding-top:10px;color: red">
                                        <%} else {%>

                                    <td valign="top" align="center" colspan="9" class="headingSpas" style="padding-top:10px;color: red">
                                        <%}%>

                                        Warranty Claim not found for Dispatch.

                                    </td>
                                </tr>
                            </c:if>


                        </table>
                    </form>
                    <%-- </div>--%>
                </div>
              
            
            </div>
        </center>
    </div>
       
 
