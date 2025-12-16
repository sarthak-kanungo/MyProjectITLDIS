<%-- 
    Document   : manage_inventoryOptions
    Created on : Aug 20, 2014, 6:15:54 PM
    Author     : sreeja.vijayakumaran
--%>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/css/login.css" type="text/css" />
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            //  String imagesURL = (String) session.getAttribute("imagesURL");
            ArrayList lockedDealerList = (ArrayList) session.getAttribute("lockedDealerlist");
            String dealerCode = (String) session.getAttribute("dealerCode");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");

%>
<c:set var="contextPath" value='<%=request.getContextPath()%>'/>
<c:set var="dashboardValues" value="${fn:split(dashboardvalues,'@@')}"/>
<link rel="stylesheet" href="${contextPath}/css/login.css" type="text/css" />
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<script>

/*

    $(document).ready(function(){
        $('.createOrder').click(function(){
            //event.preventDefault();
            var text = $(this).text();
            var url;
            if(text.indexOf('STD') > -1){
                url = "inventoryAction.do?option=createNewOrder&odType="+Base64.encode('STD')
            }else{
                url = "inventoryAction.do?option=createNewOrder&odType="+Base64.encode('VOR')
            }
            $(this).attr("href",url);
        });
    });
*/    
    
    $(document).ready(function(){
        $('.createOrder').click(function(){
            //event.preventDefault();
            var text = $(this).text();
            var url;
            if(text.indexOf('VOR') > -1){
                url = "inventoryAction.do?option=createNewOrder&odType="+Base64.encode('VOR')
            }else{
                url = "inventoryAction.do?option=createNewOrder&odType="+Base64.encode('ALL')
            }
            $(this).attr("href",url);
        });
    });
    
</script>

<div class="contentmain1">
    <div class="dashboard" style="position: relative;">

        <div class="dashboardcs hText">

            <%if (!lockedDealerList.contains(dealerCode)) {%>

            <%if (userFunctionalities.contains("802")) {%>
            <div class="dashbox">
                <div class="dashimg6">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=uploadInventory'><bean:message key="label.common.uploadInventory" /></a>

                </div>
            </div>
            <%}%>

            <%if (userFunctionalities.contains("803")) {%>
            <div class="dashbox">
                <div class="dashimg5">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=lockinventory'> <bean:message key="label.common.lockInventory" /> </a>
                </div>
            </div>
            <%}%>
            <%}%>
            <%if (userFunctionalities.contains("804") && userFunctionalities.contains("101")) {%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=viewInventory'><bean:message key="label.common.viewInventory" /> </a>
                </div>
            </div>
            <%} 
            else if (userFunctionalities.contains("804"))
            {%>
            <div class="dashbox">
                <div class="dashimg6">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=viewInventory&getDataFlag=false'><bean:message key="label.common.viewInventory" /> </a>
                </div>
            </div>
            <%}%>

            <%if (lockedDealerList.contains(dealerCode)) {%>

            <%if (userFunctionalities.contains("855")) {%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href="${contextPath}/inventoryAction.do?option=initUploadStockAdjustment"><p> <bean:message key="label.spare.stockAdjustment" /></p></a>
                </div>
            </div>
            <%}%>

            <%if (userFunctionalities.contains("806")) {%>
            <div class="dashbox">
                <div class="dashimg5">

                    <a href = '<%=cntxpath%>/inventoryAction.do?option=initCounterSale'><bean:message key="label.common.counterSale" /></a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("805")) {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=initaddInventory'><bean:message key="label.common.addInventory" /></a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("808")) {%>
            <div class="dashbox">
                <div class="dashimg3">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=createSalesReturn'><bean:message key="label.common.createSalesReturn" />   </a>
                </div>
            </div>
            <%}%>


            <%}%>
            <%if (userFunctionalities.contains("809")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href = '<%=cntxpath%>/serviceProcess.do?option=invoiceTransctionList'> <bean:message key="label.spares.transDetail" />  </a>
                </div>
            </div>
            <%}%>
            <%-- <%if (userFunctionalities.contains("618")) {%>
             <div class="dashbox">
                 <div class="dashimg1">
                     <a href="<%=cntxpath%>/serviceProcess.do?option=initCreateInvoiceList"><bean:message key="label.common.generateinvoice" /></a>
                 </div>
             </div>
             <%}%>--%>
            <%if (userFunctionalities.contains("619")) {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href="<%=cntxpath%>/serviceProcess.do?option=initviewInvoiceList"><bean:message key="label.common.viewInvoice" /></a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("810")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=inventoryLedgerReport'> <bean:message key="label.common.ledgerReport" /></a>
                </div>
            </div>
            <%}%>

            <%if (userFunctionalities.contains("811")) {%>
            <div class="dashbox">
                <div class="dashimg5">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=uploadBinLoc'> <bean:message key="label.common.uploadBinLoc" /></a>
                </div>
            </div>
            <%}%>

            <%if (userFunctionalities.contains("812")) {%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=manageReorderLevel'> <bean:message key="label.spare.manageReOrderLevel" /> </a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("813")) {%>
            <div class="dashbox">
                <div class="dashimg3">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=viewReorderLevel'> <bean:message key="label.spare.viewReorderLevel" /> </a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("814")) {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=addReorderPartToCart'> <bean:message key="label.spare.addReorderPartstoCart" />  </a>
                </div>
            </div>
            <%}%>

            <%if (userFunctionalities.contains("851")) {%>
            <div class="dashbox">
                <div class="dashimg6">
                    <%--<a href = '<%=cntxpath%>/inventoryAction.do?option=createNewOrder&odType=STD' class="createOrder"> <bean:message key="label.spare.createNewOrder" />  </a>--%>
                    <a href = '#' class="createOrder"> <bean:message key="label.spare.createNewOrder" />  </a>
                </div>
            </div>
            <div class="dashbox">
                <div class="dashimg5">
                    <%--<a href = '<%=cntxpath%>/inventoryAction.do?option=createNewOrder&odType=VOR' class="createOrder"> <bean:message key="label.spare.createNewOrderVOR" /> </a>--%>
                    <a href = '#' class="createOrder"> <bean:message key="label.spare.createNewOrderVOR" /> </a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("852")) {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=viewOrder'> <bean:message key="label.spare.viewOrder" /> </a>
                </div>
            </div>
            <%}%>

           <%if (userFunctionalities.contains("860")) {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=initPendingCancelLinesForAcceptance'> <bean:message key="label.spare.pendingCancelLinesForAcceptance" /> </a>
                </div>
            </div>
            <%}%>


            <%if (userFunctionalities.contains("853")) {%>
            <div class="dashbox">
                <div class="dashimg3">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=viewOrderInvoice'> <bean:message key="label.spare.viewOrderInvoice" />  </a>
                </div>
            </div>
            <%}%>
            <%if (lockedDealerList.contains(dealerCode) && userFunctionalities.contains("815")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href = '<%=cntxpath%>/inventoryAction.do?option=createGRN'> <bean:message key="label.order.createGRN" />  </a>
                </div>
            </div>
            <%}%>

            <%if (userFunctionalities.contains("854")) {%>
            <div class="dashbox">
                <div class="dashimg5">
                    <a href="${contextPath}/inventoryAction.do?option=backOrderReport"><p> <bean:message key="label.common.back.order.report" /></p>
                    </a>
                </div>
            </div>
            <%}%>
            <%if (lockedDealerList.contains(dealerCode) && userFunctionalities.contains("870")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href = '<%=cntxpath%>/inventoryEXPAction.do?option=createGRNEXP'> <bean:message key="label.order.createGRN" />  </a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("857")) {%>
            <%if (userFunctionalities.contains("858")) {%>
            <div class="dashbox">
                <div class="dashimg6">
                    <a href = '<%=cntxpath%>/inventoryEXPAction.do?option=initCreateEXPOrder' > <bean:message key="label.spare.initCreateOrderEXP" />  </a>
                </div>
            </div>

            <%}%>

            <%if (userFunctionalities.contains("859")) {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href = '<%=cntxpath%>/inventoryEXPAction.do?option=viewEXPOrder'> <bean:message key="label.spare.viewOrderEXP" /> </a>
                </div>
            </div>
            <%}%>
          <%if (userFunctionalities.contains("861")) {%>
            <div class="dashbox">
                <div class="dashimg6">
                    <%--<a href = '<%=cntxpath%>/createPIAction.do?option=getorderListForCreatePI&searchFlag=false' > <bean:message key="label.spare.initCreatePI" />  </a>--%>
                    <a href = '<%=cntxpath%>/createPIAction.do?option=getListForInitPI&orderType=ALL' > <bean:message key="label.spare.initCreatePI" /> </a>
                </div>
            </div>
           <%}%>
           <%if (userFunctionalities.contains("862")) {%>
            <div class="dashbox">
                <div class="dashimg3">
                    <a href = '<%=cntxpath%>/createPIAction.do?option=getPIListForView&orderType=ALL' > <bean:message key="label.spare.initViewPI" />  </a>                    
                </div>
            </div>
            <%}%>
            <%}%>
             <%if (userFunctionalities.contains("865")) {%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href = '<%=cntxpath%>/inventoryEXPAction.do?option=viewEXPOrderInvoice'> <bean:message key="label.spare.viewSAPInvoice" />  </a>
                </div>
            </div>
            <%}%>

            <%if (userFunctionalities.contains("866")) {%>
            <div class="dashbox">
                <div class="dashimg2">
                    <a href = '<%=cntxpath%>/inventoryEXPAction.do?option=downloadPIOrderSAP'> <bean:message key="label.spare.downloadPIOrder" />  </a>
                </div>
            </div>
            <%}%>
            
            <%if (userFunctionalities.contains("867")) {%>
            <div class="dashbox">
                <div class="dashimg1">
                    <a href = '<%=cntxpath%>/inventoryEXPAction.do?option=initUploadSAPAck'> <bean:message key="label.spare.uploadSapAck" />  </a>
                </div>
            </div>
            <%}%>
           <%if (userFunctionalities.contains("868")) {%>
            <div class="dashbox">
                <div class="dashimg5">
                    <a href = '<%=cntxpath%>/inventoryEXPAction.do?option=initUploadSAPCommercial'> <bean:message key="label.spare.uploadSapComm" />  </a>
                </div>
            </div>
            <%}%>
            
            <%if (userFunctionalities.contains("869")) {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href = '<%=cntxpath%>/inventoryEXPAction.do?option=initUploadCancelledExp'> <bean:message key="label.spare.uploadCancelled" />  </a>
                </div>
            </div>
            <%}%>
            <%if (userFunctionalities.contains("816")) {%>
            <div class="dashbox">
                <div class="dashimg4">
                    <a href="<%=cntxpath%>/reportAction.do?option=viewOrderInvDetReport&cce=ordDet"><bean:message key="label.report.OrderDet" /></a>
                </div>
            </div>
           <%}%>
           <%if (userFunctionalities.contains("817")) {%>
            <div class="dashbox">
                <div class="dashimg3">
                    <a href="<%=cntxpath%>/reportAction.do?option=viewOrderInvDetReport&cce=invDet"><bean:message key="label.report.InvDet" /></a>
                </div>
            </div>
           <%}%>
        </div>
    </div>
</div>





