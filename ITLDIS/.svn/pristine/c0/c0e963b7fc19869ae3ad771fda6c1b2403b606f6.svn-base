<%-- 
    Document   : acknowPacking
    Created on : Dec 10, 2014, 12:00:46 PM
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
      

            if(!document.getElementById("ReceivedQty"+row).value.match(/^[0-9]+$/)){
                document.getElementById("ReceivedQty"+row).value='';
                document.getElementById("ReceivedQty" + row).focus();
                alert("Received Qty "+numeric_msg);
                return false;
            }
              if(eval(document.getElementById("ReceivedQty"+row).value) > eval(document.getElementById("DispatchQty"+row).value)){
                alert("Received Qty "+ maxLength_validation_msg +"Dispatch Qty");
                document.getElementById("ReceivedQty"+row).focus();
                document.getElementById("ReceivedQty"+row).value="";
                return false;
            }
    
    }


    function submitForm(){

        var count=document.getElementById("PartCount").value;
         for( var i=1;i<=count;i++){
         if( document.getElementById("ReceivedQty"+i).value==""){
          document.getElementById("ReceivedQty"+i).focus();
            alert(not_blank_validation_msg+"Received Qty");
            return false;
        }
   
      }
     document.getElementById("myForm").submit();
    }
function caMaxLength(ca, MaxLen)
    {
        return (ca.value.length <= MaxLen);
    }


</script>

       <div class="contentmain1">
           <div class="breadcrumb_container" >
               <ul class="hText">
                   <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.common.warranty" /></a></li>
                   <li class="breadcrumb_link" ><a href='<%=cntxpath%>/warrantyAction.do?option=pendingForAcknow'><bean:message key="label.common.pendingForAcknow" /></a></li>
                   <li class="breadcrumb_link" ><label ><bean:message key="label.warranty.genAcknow" /> </label></li>
               </ul>
           </div>
           <div class="message" id="msg_saveFAILED"></div>
           <center>
               <div class="content_right1">
                   <div class="con_slidediv1" style="position: relative;width: 100%">
                       <h1><bean:message key="label.warranty.genAcknow" /> </h1>
                       <form action="<%=cntxpath%>/warrantyAction.do" method="post" id="myForm" onsubmit="return false;" >
                           <input type="hidden" name="option" value="createPackingAcknow" >
                           <input type="hidden" name="upperLink" value="<a href ='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a>@@<a href = '<%=cntxpath%>/warrantyAction.do?option=pendingForAcknow'><bean:message key="label.common.pendingForAcknow" /></a>@@<bean:message key="label.warranty.genAcknow" />"/>
                           <%-- <div style="overflow:auto; position: relative;width: 100%; height:300px" >--%>

                           <c:if test="${!empty dispatchList}">
                               <table id="" width="100%" border="0" align="center" cellpadding="3" cellspacing="3"  >
                                   <tr bgcolor="#FFFFFF" class="headingSpas" height="20" style="padding-top: 20px">
                                       <td align="right"><B><bean:message key="label.common.packingNo" /> : </B></td>
                                       <td align="left">${warrantyForm.packingNo}
                                            <input type="hidden" name="packingNo" value="${warrantyForm.packingNo}">
                                       </td>
                                       <td align="right"><B> <bean:message key="label.common.packingDate" />  : </B></td>
                                       <td align="left">
                                           ${warrantyForm.packingDate}
                                       </td>
                                   </tr>
                                   <tr bgcolor="#FFFFFF" class="headingSpas" height="20">
                                       <td align="right"><B> <bean:message key="label.common.dispatchedThrough" />  : </B></td>
                                       <td align="left">
                                           ${warrantyForm.dispatchBy}
                                       </td>

                                       <td align="right"><B> <bean:message key="label.common.courierName" />  : </B></td>
                                       <td align="left">
                                           ${warrantyForm.courierNo} [ ${warrantyForm.courierName} ]
                                       </td>
                                   </tr>
                                   <tr bgcolor="#FFFFFF" class="headingSpas" height="20">
                                       <td align="right"><B> <bean:message key="label.common.dispatchedFor" />  : </B></td>
                                       <td align="left">
                                           ${warrantyForm.dispatchFor}
                                       </td>
                                       <td align="right"><B> <bean:message key="label.common.dispatchedDate" />  : </B></td>
                                       <td align="left">
                                           ${warrantyForm.dispatchDate}
                                       </td>
                                   </tr>
                                   <tr bgcolor="#FFFFFF" class="headingSpas" height="20">
                                       <td align="right"><B> <bean:message key="label.common.storeNo" />  : </B></td>
                                       <td align="left" colspan="3">
                                           ${warrantyForm.storeNoOfPackages}
                                       </td>

                                   </tr>
                                   <tr bgcolor="#FFFFFF" class="headingSpas" height="10">
                                   </tr>
                               </table>


                               <table id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                   <tr  bgcolor="#eeeeee" height="20">
                                       <td align="center"  class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.S.No" /></b></td>
                                       <td align="left"   class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.warrantyClaimNo" /></b></td>
                                       <td align="left" class="headingSpas" nowrap style="padding-left:5px;" ><B><bean:message key="label.common.partno_small" /></B></td>
                                       <td align="left" class="headingSpas" nowrap style="padding-left:5px;" ><B><bean:message key="label.common.partdesc_small" /></B></td>
                                       <td align="left" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.warranty.bixNo" />  </B></td>
                                       <td align="center" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.warranty.claimQty" />  </B></td>
                                       <td align="center" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.warranty.dispatchQty" />   </B></td>
                                       <td align="center" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.saleReturn.ReceivedQty" />   </B></td>
                                   </tr>


                                   <c:set var="sno" value="1"></c:set>
                                   <logic:iterate id="dispatchList" name="dispatchList">
                                       <tr id ="${sno}" height="20">
                                           <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                           <td align="left" bgcolor="#FFFFFF" title="${dispatchList.warrantyClaimNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                               <span id ="compType${sno}" >
                                                   <a href="warrantyAction.do?option=viewWarrantyClaimDetail&warrntyClaimNo=${dispatchList.warrantyClaimNo}&flag=penAck">${dispatchList.warrantyClaimNo} </a>
                                               </span>
                                                   <input type="hidden" name="warClaimNoArr" id="WarClaimNo" class="warText" value="${dispatchList.warrantyClaimNo}">
                                           </td>
                                           <td align="left" bgcolor="#FFFFFF" title="  ${dispatchList.partNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                               <span id ="compType${sno}" >
                                                   ${dispatchList.partNo}   <input type="hidden" name="partNoArr" id="PartNo" class="warText" value="${dispatchList.partNo}">
                                                   <%--<input type="hidden" name="partCategoryArr" id="PartCat${sno}" class="warText" value="${dispatchList.partCategory}">--%>
                                               </span>
                                           </td>
                                           <td align="left" bgcolor="#FFFFFF" title="  ${dispatchList.partDesc}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                               <span id ="compType${sno}" >
                                                   ${dispatchList.partDesc}
                                               </span>
                                           </td>
                                               <td align="left" bgcolor="#FFFFFF" title="  "  class="headingSpas" style="padding-left:5px; padding-right: 5px">

                                               ${dispatchList.boxNo}
                                           </td>
                                           <td align="center" bgcolor="#FFFFFF" title="  ${dispatchList.qty}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                               <span id ="compType${sno}" >                                            </span>

                                               ${dispatchList.qty} <input type="hidden" name="qty" value="${dispatchList.qty}" id="ClaimQty${sno}">
                                           </td>


                                           <td align="center" bgcolor="#FFFFFF" title=" "  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span id ="compType${sno}" >
                                                    <input type="hidden" name="dispatchQtyArr" value="${dispatchList.dispatchQty}" id="DispatchQty${sno}" maxlength="7">
                                                </span>
                                               ${dispatchList.dispatchQty}
                                           </td>
                                           <td align="center" bgcolor="#FFFFFF" title=" "  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                               <span id ="compType${sno}" >
                                                   <input type="text" name="receivedQtyArr" value="${dispatchList.dispatchQty}" id="ReceivedQty${sno}" onblur="checkQty(${sno})" maxlength="7">
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
                                    <td colspan="6" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                        <textarea name="remark" cols="" rows="3" id="Remarks" onkeypress="return caMaxLength(this, 250);"> </textarea>


                                    </td>
                                </tr>

                                   <tr height="50">
                                       <td colspan="8" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                           <input type="button" name="Submit" value="Submit" onclick="submitForm()">
                                       </td>
                                   </tr>
                               </c:if>

                               <c:if test="${empty dispatchList}">
                                   <tr bgcolor="#FFFFFF">
                                      <%-- <%if (userFunctionalities.contains("702")) {%>--%><%--
                                       <td valign="top" align="center" colspan="10" class="headingSpas" style="padding-top:10px;color: red">
                                           <%} else {%>--%>

                                       <td valign="top" align="center" colspan="8" class="headingSpas" style="padding-top:10px;color: red">
                                          <%-- <%}%>--%>

                                      <bean:message key="label.common.noRecordFound" />

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



