
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/css/login.css" type="text/css" />
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            //  String imagesURL = (String) session.getAttribute("imagesURL");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<body>
    <div class="con_slidediv" style="position: relative; ">
        <h1>MASTERS </h1>
         <script src="<%=cntxpath%>/js/intermediate.js"></script>
        <ul class="hText">

            <%if (userFunctionalities.contains("520")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initApplicationMaster'>MANAGE APPLICATION MASTER</a></li>
            <%}%>

            <%if (userFunctionalities.contains("511")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initAggregateMaster'>MANAGE AGGREGATE MASTER</a></li>
            <%}%>

            <%if (userFunctionalities.contains("512")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initSubAggregateMaster'>MANAGE SUB-AGGREGATE MASTER</a></li>
            <%}%>

            <%if (userFunctionalities.contains("513")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initSubAssemblyMaster'>MANAGE SUB-ASSEMBLY MASTER</a></li>
            <%}%>

            <%if (userFunctionalities.contains("514")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initComplaintCode'>MANAGE DEFECT CODE MASTER</a></li>
            <%}%>

            <%if (userFunctionalities.contains("515")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initCausalCode'>MANAGE CAUSE CODE MASTER</a></li>
            <%}%>

            <%if (userFunctionalities.contains("516")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initConsequenceMaster'>MANAGE CONSEQUENCE</a></li>
            <%}%>

            <%if (userFunctionalities.contains("521")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initCausalVsConsequence'>CAUSE VS CONSEQUENCE </a></li>
            <%}%>

            <%if (userFunctionalities.contains("502")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initJobLocation'>MANAGE JOB LOCATION</a></li>
            <%}%>

            <%if (userFunctionalities.contains("501")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initJobType'>MANAGE JOB TYPE  </a></li>
            <%}%>

            <%if (userFunctionalities.contains("504")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initServiceType'>MANAGE SERVICE TYPE  </a></li>
            <%}%>

            <%--    <%if (userFunctionalities.contains("508")) {%>
                <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initNextServiceFrom'>NEXT SERVICE FROM</a></li>
                <%}%>
            --%>
            <%--   <%if (userFunctionalities.contains("509")) {%>
               <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initOwnerDriven'>MANAGE DRIVEN BY MASTER</a></li>
               <%}%>
            --%>

            <%if (userFunctionalities.contains("522")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initMechanicMaster'>MANAGE MECHANIC MASTER  </a></li>
            <%}%>

            <%if (userFunctionalities.contains("505")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initBayMaster'>MANAGE BAY MASTER  </a></li>
            <%}%>

            <%if (userFunctionalities.contains("506")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initWageMaster'>MANAGE WAGE MASTER</a></li>
            <%}%>

            <%if (userFunctionalities.contains("523")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initManageAction'>MANAGE ACTION MASTER</a></li>
            <%}%>

            <%if (userFunctionalities.contains("510")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initBillMaster'>MANAGE BILL TYPE</a></li>
            <%}%>

            <%if (userFunctionalities.contains("507")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initOtherJobWork'>MANAGE OTHER JOB WORK</a></li>
            <%}%>

            <%if (userFunctionalities.contains("524")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initManageFunc'>MANAGE SEQUENCE IN MASTERS</a></li>
            <%}%>

            <%if (userFunctionalities.contains("518")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initContentMaster'>MANAGE CHECKLIST CONTENT</a></li>
            <%}%>

            <%if (userFunctionalities.contains("519")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initSubContentMaster'>MANAGE CHECKLIST SUB CONTENT</a></li>
            <%}%>

            <%if (userFunctionalities.contains("526")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initFormCheckList'>MANAGE FORM CHECKLIST</a></li>
            <%}%>

            <%--  <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initLabourCode'>MANAGE LABOUR CODE</a></li>
            --%>
            <%if (userFunctionalities.contains("525")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initPartMaster'>MANAGE DEFAULT SPARES /LUBES MASTER</a></li>
            <%}%>
            <%--
               <%if (userFunctionalities.contains("14")) {%>
              <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initManageJobCard'>MANAGE JOB CARD</a></li>
              <%}%>--%>

            <%if (userFunctionalities.contains("527")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initWarrantyModel'>WARRANTY MODEL MASTER</a></li>
            <%}%>

            <%if (userFunctionalities.contains("528")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initWarrantyTax'>WARRANTY TAX PERCENTAGE</a></li>
            <%}%>

            <%if (userFunctionalities.contains("529")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initpdi_ins_parameterVsmake'>Manage PDI/INS Parameter Vs Make</a></li>
            <%}%>

            <%if (userFunctionalities.contains("530")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initRejectionCodeMaster'>Manage Warranty Rejection Codes</a></li>
            <%}%>

            <%if (userFunctionalities.contains("907")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initManageSellingPercentage'>MANAGE SELLING PERCENTAGE</a></li>
            <%}%>

            <%if (userFunctionalities.contains("533")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initCustCategory'>MANAGE CUSTOMER CATEGORY</a></li>
            <%}%>
            <%if (userFunctionalities.contains("534")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initDiscountCategory'>MANAGE DISCOUNT AGAINST CUSTOMER CATEGORY</a></li>
             <%}%>
            <% if (userFunctionalities.contains("532")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initUploadSAPPartMaster'>UPLOAD SAP PART MASTER</a></li>
            <%}%>

            <% if (userFunctionalities.contains("531")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initPricelistExcelMaster'>UPLOAD PRICE LIST</a></li>
            <%}%>
            <% if (userFunctionalities.contains("535")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/manageCustomerAction.do?option=initCustomerMasters'>MANAGE CUSTOMER</a></li>
            <%}%>

            <% if (userFunctionalities.contains("536")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=iniTaxChargeOEM'><bean:message key="label.common.manageTaxType" /></a></li>
              <%}%>  
           <% if (userFunctionalities.contains("537")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initManageChargeBranch'><bean:message key="label.common.manageChargeBranch" /></a></li>
             <%}%>                    
           <% if (userFunctionalities.contains("538")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initManageTaxCtgryBranch'><bean:message key="label.common.managetaxCategories" /></a></li>
             <%}%>
             <% if (userFunctionalities.contains("539")) {%> 
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initTaxAssignToDealerMasters'><bean:message key="label.common.viewTaxAssignedToDealer"/></a></li>
            <%}%>
             <% if (userFunctionalities.contains("543")) {%>
            <%--<li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initDownloadPartlistMaster'><bean:message key="label.common.downloadPartList"/></a></li>--%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initDownloadPartlistMaster'><bean:message key="label.common.downloadPartList"/></a></li>
             <%}%>
             <% if (userFunctionalities.contains("543")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initDownloadPricelistMaster'><bean:message key="label.common.downloadPriceList"/></a></li>
             <%}%>
             <% if (userFunctionalities.contains("1012")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initVendorCodeMaster'><bean:message key="label.common.manageVendorCodeMaster"/></a></li>
             <%}%>


        </ul>
    </div>
</body>

