<%--
    Document   : estimate.jsp
    Created on : Apr 30, 2014, 03:25:09 PM
    Author     : Avinash.Pandey
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
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
%>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_1.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_2.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_3.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<link rel="stylesheet" href="layout/css/login.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" media="screen" />
<link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Master_290414.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"  type="text/css"/>


<script  type="text/javascript" language="javascript">

    function EstimatedPartNo()
    {
        $('.partnumber').each(function(idx,val){
            val.value = val.value.replace(/\s+/g, ' ');
        });

    }
    
    // Function to update Select All checkbox state for Spares
    function updateSelectAllSparesCheckbox() {
        var checkboxes = document.querySelectorAll('input[name="sparesRowCheckbox"]');
        var selectAllCheckbox = document.getElementById('selectAllSpares');
        if (selectAllCheckbox) {
            if (checkboxes.length === 0) {
                selectAllCheckbox.checked = false;
            } else {
                var allChecked = true;
                for (var i = 0; i < checkboxes.length; i++) {
                    if (!checkboxes[i].checked) {
                        allChecked = false;
                        break;
                    }
                }
                selectAllCheckbox.checked = allChecked;
            }
        }
    }
    
    // Function to update Select All checkbox state for Lubes
    function updateSelectAllLubesCheckbox() {
        var checkboxes = document.querySelectorAll('input[name="lubesRowCheckbox"]');
        var selectAllCheckbox = document.getElementById('selectAllLubes');
        if (selectAllCheckbox) {
            if (checkboxes.length === 0) {
                selectAllCheckbox.checked = false;
            } else {
                var allChecked = true;
                for (var i = 0; i < checkboxes.length; i++) {
                    if (!checkboxes[i].checked) {
                        allChecked = false;
                        break;
                    }
                }
                selectAllCheckbox.checked = allChecked;
            }
        }
    }
    
    // Function to select/deselect all spares rows
    function selectAllSparesRows(checkbox) {
        var checkboxes = document.querySelectorAll('input[name="sparesRowCheckbox"]');
        checkboxes.forEach(function(cb) {
            cb.checked = checkbox.checked;
        });
    }
    
    // Function to select/deselect all lubes rows
    function selectAllLubesRows(checkbox) {
        var checkboxes = document.querySelectorAll('input[name="lubesRowCheckbox"]');
        checkboxes.forEach(function(cb) {
            cb.checked = checkbox.checked;
        });
    }
    


    var totalPartsValueS = 0.0;
    var totalLabourChargesS = 0.0;
    var totalOtherChargesS = 0.0;
    var totalLubesValueS = 0.0;
    function GetXmlHttpObject()
    {
        var objXmlHttp = null;
        if (navigator.userAgent.indexOf('Opera') >= 0)
        {
            var xmlHttp = new XMLHttpRequest();
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
                alert(browser_validation_msg);
                return;
            }
        }
        if (navigator.userAgent.indexOf('Mozilla') >= 0)
        {
            objXmlHttp = new XMLHttpRequest();
            return objXmlHttp;
        }
    }

    var count = "3";
    function addRowLubes(in_tbl_name)
    {
        var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
        // create row
        var row = document.createElement("TR");
        lubesNo = document.getElementsByName("lubesNo");
        lubecount=lubesNo.length+1;
        
        // Check if "Select All" checkbox exists - only add checkbox if Delete Selected button is visible
        // The Select All checkbox is only rendered when poCreated != 'Y', same condition as Delete Selected button
        var selectAllCheckbox = document.getElementById("selectAllLubes");
        var showCheckbox = (selectAllCheckbox !== null && selectAllCheckbox.offsetParent !== null);
        
        // Create checkbox TD only if Delete Selected button is visible
        var td0 = null;
        if (showCheckbox) {
            td0 = document.createElement("TD");
            td0.innerHTML = "<input type=\"checkbox\" name=\"lubesRowCheckbox\" class=\"lubesRowCheckbox\" value=\"" + lubecount + "\" />";
            td0.setAttribute("align","center");
            td0.setAttribute("width","3%");
        }
        
        var td1 = document.createElement("TD")
        var strHtml1 = "<input name=\"lubesNo\" type=\"text\" id=\"lubesNo" + lubecount + "\"  class=\"partnumber\" onblur=\"this.value=TrimAll(this.value)\"/>&nbsp;<img src='" + contextPath + "/image/search.png' alt='Get Suggestions' border='0' onclick=\"getSuggestionsLubesPart('lubesNo" + lubecount + "',document.getElementById('lubesNo" + lubecount + "'),document.getElementById('lubesComptype" + lubecount + "'),document.getElementById('imgLubes" + lubecount + "')," + lubecount + ");\"/><img  id='imgLubes" + lubecount + "' align=\"top\" style='visibility:hidden;' border='0' src='" + contextPath + "/image/load.gif'/><input type=\"hidden\" name=\"lubesPartNumber\" id=\"lubesPartNumber" + lubecount + "\" value=\"\">";
        td1.innerHTML = strHtml1.replace(/!count!/g, lubecount);
        td1.setAttribute("align","left");
       


        var td2 = document.createElement("TD")
        var strHtml2 = "<input name=\"lubesDesc\" type=\"text\" id=\"lubesDesc" + lubecount + "\" class=\"partdescription\" readOnly=\"readonly\" onblur=\"this.value=TrimAll(this.value)\"/><input type=\"hidden\" name=\"lubesComptype\" id=\"lubesComptype" + lubecount + "\" value=\"LUBES\">";
        td2.innerHTML = strHtml2.replace(/!count!/g, lubecount);
        td2.setAttribute("align","left");

        var td3 = document.createElement("TD")
        var strHtml3 = "<input name=\"lubesUnitPrice\" type=\"text\" id=\"lubesUnitPrice" + lubecount + "\" value='' class=\"qty\" readonly id=\"text8\"/>";
        td3.innerHTML = strHtml3.replace(/!count!/g, lubecount);
        td3.setAttribute("align","left");

        var td4 = document.createElement("TD")
        var strHtml4 = "<input name=\"lubesQuantityS\" type=\"text\" id=\"lubesQuantityS" + lubecount + "\" maxlength=\"10\"  onkeypress=\"return isNumberKey(event);\"   onblur=\"validateFloat(this);lubesAmountTotal('lubesQuantityS" + lubecount + "','lubesUnitPrice" + lubecount + "','lubesPriceAmount" + lubecount + "'," + lubecount + ");\" class=\"qty\"/>";
        td4.innerHTML = strHtml4.replace(/!count!/g, lubecount);
        td4.setAttribute("align","left");

        var td5 = document.createElement("TD")
        var strHtml5 = "<input name=\"lubesPriceAmount\" type=\"text\" class=\"qty\" value='' readonly id=\"lubesPriceAmount" + lubecount + "\"/>";
        td5.innerHTML = strHtml5.replace(/!count!/g, lubecount);
        td5.setAttribute("align","left");

        var td6 = document.createElement("TD")
        var strHtml6 = "<select name=\"lubesBillCode\" id=\"lubesBillCode" + lubecount + "\" class=\"bill\" onchange=\"onChangeLubesBillToFunc(this," + lubecount + ");\"><option value=''>--Select--</option><c:forEach items='${billListLubes}' var='dataList'><c:set value="${fn:split(dataList.value,'@@')}" var="bill"/><option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option></c:forEach></select>";
        td6.innerHTML = strHtml6.replace(/!count!/g, lubecount);
        td6.setAttribute("align","left");

        var td7 = document.createElement("TD")
        var strHtml7 = "<input name=\"lubesWarranty\" type=\"text\" id=\"lubesWarranty" + lubecount + "\" readonly class=\"qty\" maxlength=\"5\" !important\" onkeypress=\"return numeralsOnly(event,getElementById('lubesWarranty" + lubecount + "'))\"  onblur=\"validateFloat(this);lubesBillToTotal('lubesPriceAmount" + lubecount + "'," + lubecount + ");\"/>";
        td7.innerHTML = strHtml7.replace(/!count!/g, lubecount);
        td7.setAttribute("align","left");

        var td8 = document.createElement("TD")
        var strHtml8 = "<input name=\"lubesFinalAmount\" type=\"text\" class=\"qty\" id=\"lubesFinalAmount" + lubecount + "\" readonly/>";
        td8.innerHTML = strHtml8.replace(/!count!/g, lubecount);
        td8.setAttribute("align","left");
        
     // Create hidden Moq input (without creating a visible TD)
        var moqInput = document.createElement("input");
        moqInput.type = "hidden";
        moqInput.id = "Moq" + lubecount;
        moqInput.value = "";
        // Append the hidden Moq input directly to the row, no TD
        row.appendChild(moqInput);

        // Append the hidden PartExistsInLubesFlagInput
        var PartExistsInLubesFlagInput = document.createElement("input");
        PartExistsInLubesFlagInput.type = "hidden";
        PartExistsInLubesFlagInput.id = "partExistsInLubesFlag" + lubecount;
        PartExistsInLubesFlagInput.value = "";
        
        // Append the hidden 
        row.appendChild(PartExistsInLubesFlagInput);

        if (td0 !== null) {
            row.appendChild(td0); // Checkbox column - only if Delete Selected button is visible
        }
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
        row.appendChild(td7);
        row.appendChild(td8);
        

        count = parseInt(count) + 1;
        tbody.appendChild(row);
        
        // Update Select All checkbox state after adding row
        updateSelectAllLubesCheckbox();

    }
    function deleteLubesRow(in_tbl_name)
    {
        var checkboxes = document.querySelectorAll('input[name="lubesRowCheckbox"]:checked');
        if (checkboxes.length === 0) {
            alert("Please select at least one part to delete.");
            return;
        }
        
        var table = document.getElementById(in_tbl_name);
        var oRows = table.getElementsByTagName('tr');
        var minRows = 4; // Header row + 2 data rows + button row
        
        if (oRows.length <= minRows) {
            alert("Cannot delete. Minimum rows required.");
            return;
        }
        
        // Get rows to delete by finding the parent row of each checked checkbox
        var rowsToDelete = [];
        checkboxes.forEach(function(checkbox) {
            var row = checkbox.closest('tr');
            if (row && row.parentNode === table.getElementsByTagName('tbody')[0]) {
                rowsToDelete.push(row);
            }
        });
        
        // Delete the rows (from bottom to top to avoid index shifting)
        for (var i = rowsToDelete.length - 1; i >= 0; i--) {
            var rowIndex = Array.prototype.indexOf.call(oRows, rowsToDelete[i]);
            if (rowIndex > 0 && rowIndex < oRows.length - 1) { // Not header or button row
                table.deleteRow(rowIndex);
            }
        }
        
        // Update Select All checkbox state
        updateSelectAllLubesCheckbox();
        
        // Recalculate totals after deletion
        recalculateLubesTotals();
    }
    function addRowSpares(in_tbl_name)
    {
        var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
        // create row
        var row = document.createElement("TR");
        partNo = document.getElementsByName("partNo");//lubesNo = document.getElementsByName("lubesNo");
        partcount=partNo.length +1;
        
        // Check if "Select All" checkbox exists - only add checkbox if Delete Selected button is visible
        // The Select All checkbox is only rendered when poCreated != 'Y', same condition as Delete Selected button
        var selectAllCheckbox = document.getElementById("selectAllSpares");
        var showCheckbox = (selectAllCheckbox !== null && selectAllCheckbox.offsetParent !== null);
        
        // Create checkbox TD only if Delete Selected button is visible
        var td0 = null;
        if (showCheckbox) {
            td0 = document.createElement("TD");
            td0.innerHTML = "<input type=\"checkbox\" name=\"sparesRowCheckbox\" class=\"sparesRowCheckbox\" value=\"" + partcount + "\" />";
            td0.setAttribute("align","center");
            td0.setAttribute("width","3%");
        }
        
        var td1 = document.createElement("TD")
        var strHtml1 = "<input name=\"partNo\" type=\"text\" id=\"partNo" + partcount + "\" class=\"partnumber\" onblur=\"this.value=TrimAll(this.value)\"/>&nbsp;<img src='" + contextPath + "/image/search.png' alt='Get Suggestions' border='0' onclick=\"getSuggestionsPart('partNo" + partcount + "',document.getElementById('partNo" + partcount + "'),document.getElementById('comptype" + partcount + "'),document.getElementById('img" + partcount + "')," + partcount + ");\"/><img  id='img" + partcount + "' align=\"top\" style='visibility:hidden;' border='0' src='" + contextPath + "/image/load.gif'/><input type=\"hidden\" name=\"partNumber\" id=\"partNumber" + partcount + "\" value=\"\">";
        td1.innerHTML = strHtml1.replace(/!count!/g, partcount);
        td1.setAttribute("align","left");
        


        var td2 = document.createElement("TD")
        var strHtml2 = "<input name=\"partDesc\" type=\"text\" id=\"partDesc" + partcount + "\" class=\"partdescription\" readOnly=\"readonly\" onblur=\"this.value=TrimAll(this.value)\"/><input type=\"hidden\" name=\"comptype\" id=\"comptype" + partcount + "\" value=\"PRT\">";
        td2.innerHTML = strHtml2.replace(/!count!/g, partcount);
        td2.setAttribute("align","left");

        var td3 = document.createElement("TD")
        var strHtml3 = "<input name=\"unitPrice\" type=\"text\" id=\"unitPrice" + partcount + "\" value='' class=\"qty\" readonly id=\"text8\"/>";
        td3.innerHTML = strHtml3.replace(/!count!/g, partcount);
        td3.setAttribute("align","left");

        var td4 = document.createElement("TD")
        var strHtml4 = "<input name=\"quantityS\" type=\"text\" id=\"quantityS" + partcount + "\" maxlength=\"10\"  onkeypress=\"return isNumberKeySpares(event);\"  onblur=\"partAmountTotal('quantityS" + partcount + "','unitPrice" + partcount + "','partPriceAmount" + partcount + "'," + partcount + ");\" class=\"qty\"/>";
        td4.innerHTML = strHtml4.replace(/!count!/g, partcount);
        td4.setAttribute("align","left");

        var td5 = document.createElement("TD")
        var strHtml5 = "<input name=\"partPriceAmount\" type=\"text\" class=\"qty\" value='' readonly id=\"partPriceAmount" + partcount + "\"/>";
        td5.innerHTML = strHtml5.replace(/!count!/g, partcount);
        td5.setAttribute("align","left");

        var td6 = document.createElement("TD")
        var strHtml6 = "<select name=\"billCode\" id=\"billCode" + partcount + "\" class=\"bill\" onchange=\"onChangeBillToFunc(this," + partcount + ");\"><option value=''>---select---</option><c:forEach items='${billList}' var='dataList'><option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option></c:forEach></select>";
        td6.innerHTML = strHtml6.replace(/!count!/g, partcount);
        td6.setAttribute("align","left");

        var td7 = document.createElement("TD")
        var strHtml7 = "<input name=\"warranty\" type=\"text\" id=\"warranty" + partcount + "\" readonly class=\"qty\" maxlength=\"5\" !important\" onfocus=\"checkBlank(this," + partcount + ")\" onkeypress=\"return numeralsOnly(event,getElementById('warranty" + partcount + "'))\"  onblur=\"validateFloat(this);billToTotal('partPriceAmount" + partcount + "'," + partcount + ");\"/>";
        td7.innerHTML = strHtml7.replace(/!count!/g, partcount);
        td7.setAttribute("align","left");

        var td8 = document.createElement("TD")
        var strHtml8 = "<input name=\"finalAmount\" type=\"text\" class=\"qty\" id=\"finalAmount" + partcount + "\" readonly/>";
        td8.innerHTML = strHtml8.replace(/!count!/g, partcount);
        td8.setAttribute("align","left");

        var td9 = document.createElement("TD");
        var strHtml9 = "<select name=\"complaint_Code\" id=\"complaint_Code" + partcount + "\" ><option value=''>--select--</option><c:forEach items='${complaintCodeList}' var='dataList'><option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option></c:forEach></select>";
        td9.innerHTML = strHtml9.replace(/!count!/g, partcount);
        td9.setAttribute("align","left");

        var td10 = document.createElement("TD");
        var strHtml10 = "<select name=\"causal_Code\"  id=\"causal_Code" + partcount + "\"><option value=''>--select--</option><option value='Causal'>Causal</option><option value='Consequential'>Consequential</option></select>";
        td10.innerHTML = strHtml10.replace(/!count!/g, partcount);
        td10.setAttribute("align","left");

        if (td0 !== null) {
            row.appendChild(td0); // Checkbox column - only if Delete Selected button is visible
        }
        row.appendChild(td1);
        row.appendChild(td2);
        // row.appendChild(td9);
        //  row.appendChild(td10);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
        row.appendChild(td7);
        row.appendChild(td8);

        count = parseInt(count) + 1;
        tbody.appendChild(row);
        
        // Update Select All checkbox state after adding row
        updateSelectAllSparesCheckbox();

    }
    function deleteSparesRow(in_tbl_name)
    {
        console.log("=== deleteSparesRow DEBUG START ===");
        console.log("Table name:", in_tbl_name);
        
        var checkboxes = document.querySelectorAll('input[name="sparesRowCheckbox"]:checked');
        console.log("Checked checkboxes found:", checkboxes.length);
        
        if (checkboxes.length === 0) {
            console.log("ERROR: No checkboxes selected");
            alert("Please select at least one part to delete.");
            return;
        }
        
        var table = document.getElementById(in_tbl_name);
        if (!table) {
            console.error("ERROR: Table not found with ID:", in_tbl_name);
            return;
        }
        console.log("Table found:", table);
        
        var oRows = table.getElementsByTagName('tr');
        console.log("Total rows in table:", oRows.length);
        var minRows = 4; // Header row + 2 data rows + button row
        
        if (oRows.length <= minRows) {
            console.log("ERROR: Cannot delete. Current rows:", oRows.length, "Minimum required:", minRows);
            alert("Cannot delete. Minimum rows required.");
            return;
        }
        
        // Get rows to delete by finding the parent row of each checked checkbox
        var rowsToDelete = [];
        checkboxes.forEach(function(checkbox, index) {
            console.log("Processing checkbox", index + 1, ":", checkbox);
            var row = checkbox.closest('tr');
            console.log("  - Closest row:", row);
            if (row) {
                var tbody = table.getElementsByTagName('tbody')[0];
                console.log("  - TBody:", tbody);
                console.log("  - Row parent:", row.parentNode);
                if (row.parentNode === tbody) {
                    rowsToDelete.push(row);
                    console.log("  - Row added to delete list");
                } else {
                    console.log("  - Row NOT added (parent mismatch)");
                }
            } else {
                console.log("  - ERROR: Could not find parent row");
            }
        });
        
        console.log("Rows to delete count:", rowsToDelete.length);
        console.log("Rows to delete:", rowsToDelete);
        
        // Delete the rows (from bottom to top to avoid index shifting)
        for (var i = rowsToDelete.length - 1; i >= 0; i--) {
            var rowIndex = Array.prototype.indexOf.call(oRows, rowsToDelete[i]);
            console.log("Deleting row at index:", rowIndex, "Row:", rowsToDelete[i]);
            if (rowIndex > 0 && rowIndex < oRows.length - 1) { // Not header or button row
                console.log("  - Row index valid, deleting...");
                table.deleteRow(rowIndex);
                console.log("  - Row deleted successfully");
            } else {
                console.log("  - Row index invalid (skipped):", rowIndex, "Total rows:", oRows.length);
            }
        }
        
        console.log("Rows remaining after deletion:", table.getElementsByTagName('tr').length);
        
        // Update Select All checkbox state
        console.log("Updating Select All checkbox state...");
        updateSelectAllSparesCheckbox();
        
        // Recalculate totals after deletion
        console.log("Recalculating parts totals...");
        recalculatePartsTotals();
        
        console.log("=== deleteSparesRow DEBUG END ===");
    }

    function addRowLabour(in_tbl_name)
    {
        var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
        // create row
        var row = document.createElement("TR");

        var td1 = document.createElement("TD")
        td1.setAttribute("align","left");
        var strHtml1 = "<c:if test="${serviceForm.constantValue eq 'Yes'}"><select name=\"complaintCode\" id=\"complaintCode" + count + "\" onchange=\"getCommonCode(this,'Complaint'," + count + ");\"><option value=''>--Select--</option><c:forEach items='${complaintCodeList}' var='dataList'><option value='${dataList.value}' title='${dataList.label} [ ${dataList.value} ]'>${dataList.label}</option></c:forEach></select></c:if>\n\
    <c:if test="${serviceForm.constantValue eq 'No'}"><select name=\"complaintCode\" id=\"complaintCode" + count + "\" onchange=\"enableLabourCharges(this," + count + ");\"><option value=''>--Select--</option><c:forEach items='${complaintCodeList}' var='dataList'><option value='${dataList.value}' title='${dataList.label} [ ${dataList.value} ]'>${dataList.label}</option></c:forEach></select></c:if>";
            td1.innerHTML = strHtml1.replace(/!count!/g, count);
            td1.setAttribute("align","left");
            var td2 = document.createElement("TD")
            td2.setAttribute("align","left");
            var strHtml2 = "<c:if test="${serviceForm.constantValue eq 'Yes'}"><div id=\"Complaint" + count + "\"><select name=\"actionCode\" id=\"actionCode" + count + "\" onchange=\"getServiceHrsAjax(this,'Action_Taken'," + count + ");stringOnly(this);\" style=\"width:200px !important\"><option value=''>--Select--</option></select></div></c:if>\n\
    <c:if test="${serviceForm.constantValue eq 'No'}"><input name=\"actionCode\" type=\"text\" id=\"actionCode" + count + "\"  onkeypress=\"return CommentsMaxLength(this, 200)\" onblur=\"this.value=TrimAll(this.value);stringOnly(this);\"            maxlength=\"200\" class=\"partnumber\"/></c:if>";
            td2.innerHTML = strHtml2.replace(/!count!/g, count);
            td2.setAttribute("align","left");
            var td3 = document.createElement("TD")
            td3.setAttribute("align","left");
            var strHtml3 = "<c:if test="${serviceForm.constantValue eq 'Yes'}"><div id=\"Action_Taken" + count + "\"><input type=\"text\" name=\"labourChargesAmount\" id=\"labourChargesAmount" + count + "\" class=\"AmountTxtBox\"  readonly/></div></c:if>\n\
    <c:if test="${serviceForm.constantValue eq 'No'}"><input type=\"text\" name=\"labourChargesAmount\" id=\"labourChargesAmount" + count + "\" class=\"qty\" maxlength=\"15\" value=\"0.0\" onkeypress=\"return numeralsOnly(event,getElementById('labourChargesAmount" + count + "'))\"  onblur=\"validateFloat(this);labourChargesAmt();\"/></c:if>";
            td3.innerHTML = strHtml3.replace(/!count!/g, count);
            td3.setAttribute("align","left");
            row.appendChild(td1);
            row.appendChild(td2);
            row.appendChild(td3);

            count = parseInt(count) + 1;
            tbody.appendChild(row);

        }
        function deleteRowLabour(in_tbl_name)
        {
            var oRows = document.getElementById(in_tbl_name).getElementsByTagName('tr');
            var rowLength = oRows.length - 1;
            if (rowLength > 4) {
                document.getElementById(in_tbl_name).deleteRow(rowLength - 1);
            }
        }
        function addRowCharges(in_tbl_name)
        {
            var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
            // create row
            var row = document.createElement("TR");

            var td1 = document.createElement("TD")
            var strHtml1 = "<select name=\"workCode\"   id=\"workCode" + count + "\" onchange=\"clenAmountValue('workDescription" + count + "','workAmount" + count + "'," + count + ");\"><option value=''>--Select--</option><c:forEach items='${jobWorkList}' var='dataList'><option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option></c:forEach></select>";//
            td1.innerHTML = strHtml1.replace(/!count!/g, count);
            td1.setAttribute("align","left");
            var td2 = document.createElement("TD")
            var strHtml2 = "<input name=\"workDescription\" type=\"text\" id=\"workDescription" + count + "\"  onkeypress=\"return CommentsMaxLength(this, 200);\" onblur=\"this.value=TrimAll(this.value);stringOnly(this);\" maxlength=\"200\" class=\"partdescription\"/>";
            td2.innerHTML = strHtml2.replace(/!count!/g, count);
            td2.setAttribute("align","left");
            var td3 = document.createElement("TD")
            var strHtml3 = "<input name=\"workAmount\" type=\"text\" class=\"qty\" id=\"workAmount" + count + "\" onkeypress=\"return numeralsOnly(event,getElementById('workAmount" + count + "'))\" maxlength=\"15\" onBlur=\"totalCharges('workAmount" + count + "'," + count + ");validateFloat(this);\"/>";
            td3.innerHTML = strHtml3.replace(/!count!/g, count);
            td3.setAttribute("align","left");
            var td4 = document.createElement("TD")
            var strHtml4 = "<font align=\"center\"></font>";//<a href='#'><img  id=\"billDetails" + count + "\" name=\"billDetails\" onClick=\"browsefile(" + count + ");\"  border='0' src='" + contextPath + "/layout/images/file_add.png' width=\"24\" height=\"24\" title=\"Upload File\" /></a><input type=\"hidden\" name=\"selectedFilesHid\" id=\"selectedFilesHid" + count + "\"></font>";
            td4.innerHTML = strHtml4.replace(/!count!/g, count);
            td4.setAttribute("align","left");
            row.appendChild(td1);
            row.appendChild(td2);
            row.appendChild(td3);
            row.appendChild(td4);

            count = parseInt(count) + 1;
            tbody.appendChild(row);
           

        }
        function deleteRowCharges(in_tbl_name)
        {
            var oRows = document.getElementById(in_tbl_name).getElementsByTagName('tr');
            var rowLength = oRows.length - 1;
            if (rowLength > 4) {
                document.getElementById(in_tbl_name).deleteRow(rowLength - 1);
            }
        }

        function notblank(obj)
        {
            var objSpecExp = /\S+/g;
            if (objSpecExp.test(obj))
            {
                return true;
            }
            return false
        }
        function getCommonCode(obj, nameObj, row)
        {
            enableLabourCharges(obj,row);
            var objjCode = obj.value;
            var strURL = "<%=cntxpath%>/data_master/getCompCodeListAjax.jsp?objjCode=" + objjCode + "&nameObj=" + nameObj + "&rowCount=" + row;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = trim(xmlHttp.responseText);
                    if (res != '') {
                        document.getElementById(nameObj + row).innerHTML = '';
                        document.getElementById("labourChargesAmount" + row).value = '';
                        document.getElementById("totalLabourCharges").value = '';
                        // alert(res);
                        document.getElementById(nameObj + row).innerHTML = res;
                    }

                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
        }

        function getServiceHrsAjax(obj, nameObj, row)
        {
          

            var val= document.getElementById("actionCode"+row).value;

            if(val.indexOf("@@") > -1){
                val= val.split('@@');
                document.getElementById("complainhrs").value= val[1];
            }

            else{
                document.getElementById("complainhrs").value= val;
            }
            //complainhrs

            //actionCode1

            var labourChargesAmount = 0;
            var partPriceAmoun = 0;
            var totalPrice = 0.0;
            var objjCode = obj.value;
            //alert(objjCode);
            var strURL = "<%=cntxpath%>/serviceCreateJC.do?option=getServiceHrsAjax&objjCode=" + objjCode + "&nameObj=" + nameObj + "&rowCount=" + row + "&tm=" + new Date().getTime();

            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")

                {
                    res = trim(xmlHttp.responseText);
                    // alert(res);
                    document.getElementById(nameObj + row).innerHTML = '';
                    document.getElementById(nameObj + row).innerHTML = "<input type=\"text\" name=\"labourChargesAmount\" id=\"labourChargesAmount" + row + "\" class=\"AmountTxtBox\" value=" + res + "   readonly/>";

                    document.getElementById("labourChargesAmount"+row).value=eval(val[1])*eval(res);


                    var tableName = document.getElementsByName('labourChargesAmount');
                    for (var i = 0; i < tableName.length; i++) {

                        labourChargesAmount = tableName[i].value == '' ? 0 : tableName[i].value;
                        totalPrice += parseFloat(labourChargesAmount);
                    }
                    document.getElementById("totalLabourCharges").value = totalPrice.toFixed(2);
                    //labourChargesAmount
                    totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
                    totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
                    totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
                    ;
                    //document.forms[0].totalEstimate.value=((parseFloat(totalPartsValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
                    totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
                    document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);

                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);


        }
        var sparex = new Array();
        var partNo = new Array();
        var partDesc = new Array();
        var unitPrice = new Array();

        function getPartPrice(partNo, row) {
            
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var partNo = partNo.value;
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/serviceCreateJC.do?option=getPartPriceBypartNo&cat=PRT&partno=' + partNo + '&jobType='+${serviceform.jobType}+'&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                stateChangedPrice(xmlHttp, row, partNo);
            };
            xmlHttp.open("GET", strURL, true);
            xmlHttp.send(null);
        }

        function stateChangedPrice(xmlHttp, row, partNos)
        {
            var context = '<%=cntxpath%>';
            var res = null;
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {

                if (xmlHttp.responseText.indexOf("session_expired") != -1)
                {
                    window.location.href = context + "/session_expired.do";
                }
                else
                {
                    res = trimS(xmlHttp.responseText);
                    if(res=="notexist" || res=="notexistBEW"){
                        if(res=="notexistBEW"){
                            alert(jobcardBEW_validation_msg);
                        }else{
                            alert(partnotfound_validation_msg);
                        }
                        document.getElementById("partNo" + row).value = '';
                        document.getElementById("partDesc" + row).value = '';
                        document.getElementById("unitPrice" + row).value = '';
                        document.getElementById("quantityS" + row).value = '';
                        document.getElementById("finalAmount" + row).value = '';
                        document.getElementById("warranty" + row).value = '';
                    }
                    else if(res==""){
                        alert(partnofound_validation_msg);
                        document.getElementById("partNo" + row).value = '';
                        document.getElementById("partDesc" + row).value = '';
                        document.getElementById("unitPrice" + row).value = '';
                        document.getElementById("quantityS" + row).value = '';
                        document.getElementById("finalAmount" + row).value = '';
                        document.getElementById("warranty" + row).value = '';
                        document.getElementById("partNo" + row).focus();
                    }
                    else{
                        var valArr = res.split('@@');

                        if (valArr.length > 0 && res != '')
                        {
                            sparex = new Array();
                            partNo = document.getElementsByName("partNo")
                            partDesc = document.getElementsByName("partDesc")
                            unitPrice = document.getElementsByName("unitPrice")
                            for (var k = 0; k < partNo.length; k++) {
                                if (partNo[k].value != '')
                                {
                                    var res1 = true;
                                    for (var m = 0; m < sparex.length; m++)
                                    {
                                        if (sparex[m] == trimS(partNo[k].value))
                                        {
                                            res1 = false;
                                        }
                                    }
                                    if (res1 == false)
                                    {
                                   
                                        alert(partunique_validation_msg);
                                        document.getElementById("partNo" + row).value = '';
                                        document.getElementById("partDesc" + row).value = '';
                                        document.getElementById("unitPrice" + row).value = '';
                                    
                                        // window.scrollTo(0, 0);
                                        img_obj.style.visibility = "hidden";
                                        return false;
                                    }

                                    sparex.push(partNo[k].value);
                                }
                            }
                            document.getElementById("partDesc" + row).value = valArr[1];
                            document.getElementById("partDesc" + row).title = valArr[1];
                            document.getElementById("unitPrice" + row).value = valArr[0];
                            document.getElementById("partNo" + row).readOnly = true;
                            document.getElementById("quantityS" + row).value = "0";
                            document.getElementById("quantityS" + row).focus();
                            document.getElementById("img" + row).style.dispaly = "none";
                            onChangeBillToFunc(document.getElementById("billCode" + row), row);
                        } else
                        {
                            document.getElementById("partDesc" + row).value = "";
                            document.getElementById("unitPrice" + row).value = "";
                            document.getElementById("partNo" + row).value = '';
                            document.getElementById("partNo" + row).title="";
                            document.getElementById("partDesc" + row).title="";
                            document.getElementById("img" + row).style.dispaly = "none";
                        }
                    }

                }
            }
        }

        function getPartPriceByPart(partDesc, row) {
           
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var partDesc = partDesc.value;
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/serviceCreateJC.do?option=getPartPriceBypartDesc&partDesc=' + partDesc + '&jobType='+${serviceform.jobType}+'&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                stateChangedP(xmlHttp, row, partDesc);
            };
            xmlHttp.open("GET", strURL, true);
            xmlHttp.send(null);
        }

        function stateChangedP(xmlHttp, row, partDescS)
        {
            var context = '<%=cntxpath%>';
            var res = null;

            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {

                if (xmlHttp.responseText.indexOf("session_expired") != -1)
                {
                    window.location.href = context + "/session_expired.do";
                }
                else
                {
                    
                    res = trimS(xmlHttp.responseText);
                    if(res=="notexist" || res=="notexistBEW"){
                        if(res=="notexistBEW"){
                            alert(jobcardBEW_validation_msg);
                        }else{
                            alert(partdescnotfound_validation_msg);
                        }
                        document.getElementById("partNo" + row).value = '';
                        document.getElementById("partDesc" + row).value = '';
                        document.getElementById("unitPrice" + row).value = '';
                        document.getElementById("quantityS" + row).value = '';
                        document.getElementById("finalAmount" + row).value = '';
                        document.getElementById("warranty" + row).value = '';
                    }
                    else if(res==""){
                        alert(descnotfound_validation_msg);
                        document.getElementById("partNo" + row).value = '';
                        document.getElementById("partDesc" + row).value = '';
                        document.getElementById("unitPrice" + row).value = '';
                        document.getElementById("quantityS" + row).value = '';
                        document.getElementById("finalAmount" + row).value = '';
                        document.getElementById("warranty" + row).value = '';
                        document.getElementById("partDesc" + row).focus();
                    }
                    else{
                        var valArr = res.split('@@');
                        if (valArr.length > 0 && res != '')
                        {
                            sparex = new Array();
                            partNo = document.getElementsByName("partNo")
                            partDesc = document.getElementsByName("partDesc")
                            unitPrice = document.getElementsByName("unitPrice")
                            for (var k = 0; k < partDesc.length; k++) {
                                if (partDesc[k].value != '')
                                {
                                    var res1 = true;
                                    for (var m = 0; m < sparex.length; m++)
                                    {
                                        if (sparex[m] == trimS(partDesc[k].value))
                                        {
                                            res1 = false;
                                        }
                                    }
                                    if (res1 == false)
                                    {
    <%--document.getElementById("msg_saveFAILED").innerHTML = "Same part description can not be enter twice";--%>
                                        alert(partDescunique_validation_msg);
                                        document.getElementById("partNo"+row).value = '';
                                        document.getElementById("partDesc"+row).value = '';
                                        document.getElementById("unitPrice"+row).value = '';
                                        document.getElementById("partDesc"+row).focus();
                                        window.scrollTo(0, 0);
                                        img_obj.style.visibility = "hidden";
                                        return false;
                                    }

                                    sparex.push(partDesc[k].value);
                                }
                            }
                            document.getElementById("partNo"+row).value = valArr[1];
                            document.getElementById("partNo"+row).title = valArr[1];
                            document.getElementById("unitPrice"+row).value = valArr[0];
                            document.getElementById("image"+row).style.dispaly = "none";
                            onChangeBillToFunc(document.getElementById("billCode" + row), row);
                        } else
                        {
                            document.getElementById("partNo" + row).value = "";
                            document.getElementById("unitPrice" + row).value = "";
                            document.getElementById("partNo" + row).title="";
                            document.getElementById("partDesc" + row).title="";
                            document.getElementById("image" + row).style.dispaly = "none";
                        }
                    }
                }
            }
        }
        function onChangeBillToFunc(objjCode, row)
        {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var partPriceAmount = 0;
            var totalPrice = 0.0;
            if (Trim(objjCode.value) == '') {
                document.getElementById("warranty" + row).readOnly = true;
                document.getElementById("warranty" + row).value = '';
                document.getElementById("finalAmount" + row).value = document.getElementById("partPriceAmount" + row).value;

                var tableName = document.getElementsByName('finalAmount');
                for (var i = 0; i < tableName.length; i++) {

                    partPriceAmount = tableName[i].value == '' ? 0 : tableName[i].value;
                    totalPrice += parseFloat(partPriceAmount);
                }
                document.getElementById("finalAmount" + row).value = document.getElementById('partPriceAmount' + row).value;
                document.getElementById("totalPartsValue").value = totalPrice.toFixed(2);

                totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
                totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
                totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
                //document.forms[0].totalEstimate.value=((parseFloat(totalPartsValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
                totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
                document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);

            } else {
                var valArr = objjCode.value.split('@@');
                if (valArr[2] == '0.0') {
                    if(valArr[0]=='1' && document.getElementById("totalDiscountPercentage").value!=""){
                        document.getElementById("warranty" + row).value = document.getElementById("totalDiscountPercentage").value;
                    }else{
                        document.getElementById("warranty" + row).value = valArr[2];
                    }
                    document.getElementById("warranty" + row).focus();
                    document.getElementById("warranty" + row).readOnly = false;
                    document.getElementById("warranty" + row).style.backgroundColor="#FFFFFF";
                } else {
                    document.getElementById("warranty" + row).value = valArr[2];
                    document.getElementById("warranty" + row).readOnly = true;
                    document.getElementById("warranty" + row).style.backgroundColor="#E6E4E4";

                }
                totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
                totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
                totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
                ;
                //document.forms[0].totalEstimate.value=((parseFloat(totalPartsValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
                totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
                document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
                billToTotal('partPriceAmount' + row, row);
            }

        }
        var sparex = new Array();
        var actionCode = new Array();
        var labourChargesAmount = new Array();
        var complaintCode = new Array();

        function enableLabourCharges(objjCode, row) {

            document.getElementById("msg_saveFAILED").innerHTML ="";
            if (Trim(objjCode.value) == '') {
                // document.getElementById("actionCode" + row).readOnly = true;
                document.getElementById("labourChargesAmount" + row).readOnly = true;
                //document.getElementById("labourChargesAmount"+row).value='';
            } else {
                sparex = new Array();
                complaintCode = document.getElementsByName("complaintCode")
                actionCode = document.getElementsByName("actionCode")
                labourChargesAmount = document.getElementsByName("labourChargesAmount");
                for (var k = 0; k < complaintCode.length; k++) {
                    if (complaintCode[k].value != '')
                    {
                        var res1 = true;
                        for (var m = 0; m < sparex.length; m++)
                        {
                            if (sparex[m] == (objjCode.value))
                            {
                                res1 = false;
                            }
                        }
    <%-- if (res1 == false)
     {
         document.getElementById("msg_saveFAILED").innerHTML = "Same complaint can not be enter twice";
         alert(compunique_validation_msg);
         document.getElementById("totalLabourCharges").value = (document.getElementById("totalLabourCharges").value - document.getElementById("labourChargesAmount" + row).value);
         estimateTotalValue();
         document.getElementById("actionCode" + row).value = '';
         document.getElementById("labourChargesAmount" + row).value = '';
         complaintCode[k].value = '';
         complaintCode[k].focus();
         document.getElementById("actionCode" + row).readOnly = true;
         document.getElementById("labourChargesAmount" + row).readOnly = true;
         window.scrollTo(0, 0);
         return false;
     }--%>

                         sparex.push(complaintCode[k].value);
                     }
                 }
                 document.getElementById("actionCode" + row).readOnly = false;
                 document.getElementById("labourChargesAmount" + row).readOnly = false;
                 document.getElementById("totalLabourCharges").value = (document.getElementById("totalLabourCharges").value - document.getElementById("labourChargesAmount" + row).value);
                 estimateTotalValue();
                 document.getElementById("labourChargesAmount" + row).value = "0";
             }

         }

         function partAmountTotal(obj1, obj2, obj3, rowId) {
             // Validate parameters
             if (!obj1 || !obj2 || !obj3 || rowId === undefined || rowId === null) {
                 return; // Exit if required parameters are missing
             }
             
             var msgElement = document.getElementById("msg_saveFAILED");
             if (msgElement) {
                 msgElement.innerHTML = "";
             }
             
             var totalPrice = 0.0;
             var unitPrice = 0.0;
             var subTotal = 0.0;
             var partPriceAmount = 0;

             var obj1Element = document.getElementById(obj1);
             var obj2Element = document.getElementById(obj2);
             var obj3Element = document.getElementById(obj3);
             
             if (!obj1Element || !obj2Element || !obj3Element) {
                 return; // Exit if any required element is missing
             }

             var quantity = obj1Element.value;
             if (quantity == null || quantity == "") {
                 obj1Element.value = '0';
                 quantity = 0;
             }
             unitPrice = obj2Element.value;
             if (unitPrice == null || unitPrice == "") {
                 obj2Element.value = '0';
                 unitPrice = 0;
             }
             unitPrice = parseFloat(unitPrice);
             if (isNaN(unitPrice)) {
                 unitPrice = 0;
             }
             unitPrice = unitPrice.toFixed(2);
             var partPriceAmount = parseFloat(quantity) * parseFloat(unitPrice);
             if (isNaN(partPriceAmount)) {
                 partPriceAmount = 0;
             }
             obj3Element.value = partPriceAmount.toFixed(2);
             subTotal = subTotal + parseFloat(partPriceAmount);
             
             var finalAmountElement = document.getElementById("finalAmount" + rowId);
             var partPriceAmountElement = document.getElementById('partPriceAmount' + rowId);
             if (finalAmountElement && partPriceAmountElement) {
                 finalAmountElement.value = partPriceAmountElement.value;
             }
             
             var tableName = document.getElementsByName('finalAmount');
             for (var i = 0; i < tableName.length; i++) {

                 partPriceAmount = tableName[i].value == '' ? 0 : tableName[i].value;
                 totalPrice += parseFloat(partPriceAmount);
             }
             
             var totalPartsValueElement = document.getElementById("totalPartsValue");
             if (totalPartsValueElement) {
                 totalPartsValueElement.value = totalPrice.toFixed(2);
             }

             var billCodeElement = document.getElementById("billCode" + rowId);
             if (billCodeElement && billCodeElement.value != '')
             {
                 billToTotal('partPriceAmount' + rowId, rowId);
                 
                 var valArr = billCodeElement.value.split('@@');
                 var warrantyElement = document.getElementById("warranty" + rowId);
                 if (valArr.length > 1 && valArr[1] == 'Yes' && warrantyElement && warrantyElement.value == valArr[2]) {
                     //document.getElementById("msg_saveFAILED").innerHTML = "Discount on goodwill should be numeric and greater than zero.";
                     //document.getElementById("warranty" + rowId).focus();
                 }
             }
             
             var totalPartsValueElement2 = document.getElementById("totalPartsValue");
             var totalLabourChargesElement = document.getElementById("totalLabourCharges");
             var totalOtherChargesElement = document.getElementById("totalOtherCharges");
             var totalLubesValueElement = document.getElementById("totalLubesValue");
             
             var totalPartsValueS = (totalPartsValueElement2 && totalPartsValueElement2.value != '') ? totalPartsValueElement2.value : 0;
             var totalLabourChargesS = (totalLabourChargesElement && totalLabourChargesElement.value != '') ? totalLabourChargesElement.value : 0;
             var totalOtherChargesS = (totalOtherChargesElement && totalOtherChargesElement.value != '') ? totalOtherChargesElement.value : 0;
             var totalLubesValueS = (totalLubesValueElement && totalLubesValueElement.value != '') ? totalLubesValueElement.value : 0;
             
             if (document.forms[0] && document.forms[0].totalEstimate) {
                 document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
             }

         }
         
         function recalculatePartsTotals() {
             console.log("=== recalculatePartsTotals DEBUG START ===");
             var totalPrice = 0.0;
             var tableName = document.getElementsByName('finalAmount');
             console.log("finalAmount elements found:", tableName.length);
             
             for (var i = 0; i < tableName.length; i++) {
                 var partPriceAmount = tableName[i].value == '' ? 0 : tableName[i].value;
                 console.log("  finalAmount[" + i + "]:", partPriceAmount);
                 totalPrice += parseFloat(partPriceAmount);
             }
             console.log("Total parts price calculated:", totalPrice);
             
             var totalPartsValueElement = document.getElementById("totalPartsValue");
             if (totalPartsValueElement) {
                 totalPartsValueElement.value = totalPrice.toFixed(2);
                 console.log("totalPartsValue updated to:", totalPrice.toFixed(2));
             } else {
                 console.error("ERROR: totalPartsValue element not found");
             }
             
             // Recalculate total estimate
             var totalPartsValueElement2 = document.getElementById("totalPartsValue");
             var totalLabourChargesElement = document.getElementById("totalLabourCharges");
             var totalOtherChargesElement = document.getElementById("totalOtherCharges");
             var totalLubesValueElement = document.getElementById("totalLubesValue");
             
             var totalPartsValueS = (totalPartsValueElement2 && totalPartsValueElement2.value != '') ? totalPartsValueElement2.value : 0;
             var totalLabourChargesS = (totalLabourChargesElement && totalLabourChargesElement.value != '') ? totalLabourChargesElement.value : 0;
             var totalOtherChargesS = (totalOtherChargesElement && totalOtherChargesElement.value != '') ? totalOtherChargesElement.value : 0;
             var totalLubesValueS = (totalLubesValueElement && totalLubesValueElement.value != '') ? totalLubesValueElement.value : 0;
             
             console.log("Total values - Parts:", totalPartsValueS, "Lubes:", totalLubesValueS, "Labour:", totalLabourChargesS, "Other:", totalOtherChargesS);
             
             var totalEstimate = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
             console.log("Calculated total estimate:", totalEstimate);
             
             if (document.forms[0] && document.forms[0].totalEstimate) {
                 document.forms[0].totalEstimate.value = totalEstimate;
                 console.log("totalEstimate field updated to:", totalEstimate);
             } else {
                 console.error("ERROR: form or totalEstimate field not found");
             }
             
             console.log("=== recalculatePartsTotals DEBUG END ===");
         }

         function billToTotal(obj3, rowId) {
             document.getElementById("msg_saveFAILED").innerHTML ="";
             var warranty = 0;
             var partPriceAmount = 0;
             var finalAmountT = 0;
             var totalPrice = 0;
             var finalAmountS = 0.0;
             if (parseInt(document.getElementById("warranty" + rowId).value) > 100) {
    <%--document.getElementById("msg_saveFAILED").innerHTML = "Discount must not be greater than 100";--%>
                alert(discount_validation_msg);
                document.getElementById("warranty" + rowId).value=0.0;
                document.getElementById("warranty" + rowId).focus();

            }
            else {
                var partPriceAmount = document.getElementById(obj3).value == '' ? 0 : document.getElementById(obj3).value;
                document.getElementById("totalPartsValue").value = '';

                warranty = document.getElementById("warranty" + rowId).value == '' ? 0 : document.getElementById("warranty" + rowId).value;
                finalAmountS = ((partPriceAmount * warranty) / 100);
                totalPrice = parseFloat((parseFloat(partPriceAmount) - parseFloat(finalAmountS)));
                document.getElementById("finalAmount" + rowId).value = totalPrice.toFixed(2);

                var finalAmount = document.getElementsByName('finalAmount');
                totalPrice = 0;
                for (var i = 0; i < finalAmount.length; i++) {
                    finalAmountT = finalAmount[i].value == '' ? 0 : finalAmount[i].value;
                    totalPrice += parseFloat(finalAmountT);
                }
                document.getElementById("totalPartsValue").value = totalPrice.toFixed(2);
                totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
                totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
                totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
                ;
                //document.forms[0].totalEstimate.value=((parseFloat(totalPartsValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
                totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
                document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
                document.getElementById("msg_saveFAILED").innerHTML = "";
            }
        }

        function totalCharges(obj, rowId) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var chargesAmount = 0;
            var workAmount = 0;
            var subTotal = 0.0;
            var workAmountTotal = 0.0;
            var workAmountArr = document.getElementsByName('workAmount');
            for (var i = 0; i < workAmountArr.length; i++) {

                workAmountTotal = workAmountArr[i].value == '' ? 0 : workAmountArr[i].value;
                subTotal += parseFloat(workAmountTotal);
            }
            document.getElementById("totalOtherCharges").value = subTotal.toFixed(2);
            totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
            totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
            totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
            totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
            document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);

        }
        var sparex = new Array();
        var workCode = new Array();
        var workDescription = new Array();
        var workAmount = new Array();
        function clenAmountValue(obj, obj1, rowId) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            sparex = new Array();
            workCode = document.getElementsByName("workCode")
            workDescription = document.getElementsByName("workDescription")
            workAmount = document.getElementsByName("workAmount")
            /*for (var k = 0; k < workCode.length; k++) {
                if (workCode[k].value != '')
                {
                    var res1 = true;
                    for (var m = 0; m < sparex.length; m++)
                    {
                        if (sparex[m] == (workCode[k].value))
                        {
                            res1 = false;
                        }
                    }
                    if (res1 == false)
                    {
                        alert(workunique_validation_msg);
                        document.getElementById("totalOtherCharges").value = (document.getElementById("totalOtherCharges").value - document.getElementById("workAmount" + rowId).value);
                        estimateTotalValue();
                        workCode[k].value = '';
                      //  document.getElementById("workDescription" + rowId).value = '';
                      //  document.getElementById("workAmount" + rowId).value = '';
                        workCode[k].focus();
                        window.scrollTo(0, 0);
                        return false;
                    }

                    sparex.push(workCode[k].value);
                }
            }*/
            document.getElementById("totalOtherCharges").value = (document.getElementById("totalOtherCharges").value - document.getElementById(obj1).value);
            estimateTotalValue();
            //document.getElementById(obj).value = '';
            // document.getElementById(obj1).value = '';
            //partAmountTotal("quantityS"+rowId,"unitPrice1"+rowId,"partPriceAmount1"+rowId,rowId);
        }

        function estimateTotalValue() {
            var estimateTotal = 0;
            var totalPartsValue = document.forms[0].totalPartsValue.value == '' ? 0 : document.forms[0].totalPartsValue.value;
            var totalLabourCharges = document.forms[0].totalLabourCharges.value == '' ? 0 : document.forms[0].totalLabourCharges.value;
            var totalOtherCharges = document.forms[0].totalOtherCharges.value == '' ? 0 : document.forms[0].totalOtherCharges.value;
            var totalLubesValue = document.forms[0].totalLubesValue.value == '' ? 0 : document.forms[0].totalLubesValue.value;
            if (totalPartsValue != '' || totalLabourCharges != '' || totalOtherCharges != '' || totalLubesValue != '') {
                estimateTotal = ((parseFloat(totalPartsValue)) + (parseFloat(totalLabourCharges)) + (parseFloat(totalOtherCharges) + (parseFloat(totalLubesValue))));
                document.forms[0].totalEstimate.value = estimateTotal.toFixed(2);
            }
        }
        function labourChargesAmt() {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var labourChargesAmount = 0;
            var totalPrice = 0.0;
            var tableName = document.getElementsByName('labourChargesAmount');
            for (var i = 0; i < tableName.length; i++) {

                labourChargesAmount = tableName[i].value == '' ? 0 : tableName[i].value;
                totalPrice += parseFloat(labourChargesAmount);
            }
            document.getElementById("totalLabourCharges").value = totalPrice.toFixed(2);
            totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
            totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
            totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
            ;
            totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
            document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);

        }

        function checkQuantity(qty)
        {
            if (isNaN(qty.replace('-', '')))
                return false;
            else if (qty == 0)
                return false;
            else if (qty.indexOf(".") != -1)
                return false;
            else
                return true;
        }
        /// LUBES///
        function lubesBillToTotal(obj3, rowId) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var warranty = 0;
            var partPriceAmount = 0;
            var finalAmountT = 0;
            var totalPrice = 0;
            var finalAmountS = 0.0;
            if (parseInt(document.getElementById("lubesWarranty" + rowId).value) > 100) {
    <%--document.getElementById("msg_saveFAILED").innerHTML = "Discount must not be greater than 100";--%>
                alert(discount_validation_msg);
                document.getElementById("lubesWarranty" + rowId).value=0.0;
                document.getElementById("lubesWarranty" + rowId).focus();

            }
            else{
                var partPriceAmount = document.getElementById(obj3).value == '' ? 0 : document.getElementById(obj3).value;
                document.getElementById("totalLubesValue").value = '';

                warranty = document.getElementById("lubesWarranty" + rowId).value == '' ? 0 : document.getElementById("lubesWarranty" + rowId).value;
                finalAmountS = ((partPriceAmount * warranty) / 100);
                totalPrice = parseFloat((parseFloat(partPriceAmount) - parseFloat(finalAmountS)));
                document.getElementById("lubesFinalAmount" + rowId).value = totalPrice.toFixed(2);

                var finalAmount = document.getElementsByName('lubesFinalAmount');
                totalPrice = 0;
                for (var i = 0; i < finalAmount.length; i++) {
                    finalAmountT = finalAmount[i].value == '' ? 0 : finalAmount[i].value;
                    totalPrice += parseFloat(finalAmountT);
                }
                document.getElementById("totalLubesValue").value = totalPrice.toFixed(2);
                totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
                totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
                totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
                //totalLubesValueS=document.getElementById("totalLubesValue").value==''?0:document.getElementById("totalLubesValue").value;
                totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
                document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);

            }
        }
        function lubesAmountTotal(obj1, obj2, obj3, rowId) {
            // Validate parameters
            if (!obj1 || !obj2 || !obj3 || rowId === undefined || rowId === null) {
                return; // Exit if required parameters are missing
            }
            
            var msgElement = document.getElementById("msg_saveFAILED");
            if (msgElement) {
                msgElement.innerHTML = "";
            }
            
            var totalPrice = 0.0;
            var unitPrice = 0.0;
            var subTotal = 0.0;
            var partPriceAmount = 0;
            
            var obj1Element = document.getElementById(obj1);
            var obj2Element = document.getElementById(obj2);
            var obj3Element = document.getElementById(obj3);
            
            if (!obj1Element || !obj2Element || !obj3Element) {
                return; // Exit if any required element is missing
            }
            
            var quantity = obj1Element.value;
            if (quantity == null || quantity == "") {
                obj1Element.value = '0';
                quantity = 0;
            }
            unitPrice = obj2Element.value;
            if (unitPrice == null || unitPrice == "") {
                obj2Element.value = '0';
                unitPrice = 0;
            }
            unitPrice = parseFloat(unitPrice);
            if (isNaN(unitPrice)) {
                unitPrice = 0;
            }
            unitPrice = unitPrice.toFixed(2);
            var partPriceAmount = parseFloat(quantity) * parseFloat(unitPrice);
            if (isNaN(partPriceAmount)) {
                partPriceAmount = 0;
            }
            obj3Element.value = partPriceAmount.toFixed(2);
            subTotal = subTotal + parseFloat(partPriceAmount);
            
            var lubesFinalAmountElement = document.getElementById("lubesFinalAmount" + rowId);
            var lubesPriceAmountElement = document.getElementById('lubesPriceAmount' + rowId);
            if (lubesFinalAmountElement && lubesPriceAmountElement) {
                lubesFinalAmountElement.value = lubesPriceAmountElement.value;
            }
            
            var tableName = document.getElementsByName('lubesFinalAmount');
            for (var i = 0; i < tableName.length; i++) {

                partPriceAmount = tableName[i].value == '' ? 0 : tableName[i].value;
                totalPrice += parseFloat(partPriceAmount);
            }
            
            var totalLubesValueElement = document.getElementById("totalLubesValue");
            if (totalLubesValueElement) {
                totalLubesValueElement.value = totalPrice.toFixed(2);
            }

            var lubesBillCodeElement = document.getElementById("lubesBillCode" + rowId);
            if (lubesBillCodeElement && lubesBillCodeElement.value != '')
            {
                lubesBillToTotal('lubesPriceAmount' + rowId, rowId);
                
                var valArr = lubesBillCodeElement.value.split('@@');
            //            if(valArr[1]=='Yes' && document.getElementById("lubesWarranty"+rowId).value==valArr[2]){
            //                document.getElementById("msg_saveFAILED").innerHTML="Discount on goodwill should be numeric and greater than zero.";
            //                document.getElementById("lubesWarranty"+rowId).focus();
            //            }
            }
            
            var totalPartsValueElement = document.getElementById("totalPartsValue");
            var totalLabourChargesElement = document.getElementById("totalLabourCharges");
            var totalOtherChargesElement = document.getElementById("totalOtherCharges");
            var totalLubesValueElement2 = document.getElementById("totalLubesValue");
            
            var totalPartsValueS = (totalPartsValueElement && totalPartsValueElement.value != '') ? totalPartsValueElement.value : 0;
            var totalLabourChargesS = (totalLabourChargesElement && totalLabourChargesElement.value != '') ? totalLabourChargesElement.value : 0;
            var totalOtherChargesS = (totalOtherChargesElement && totalOtherChargesElement.value != '') ? totalOtherChargesElement.value : 0;
            var totalLubesValueS = (totalLubesValueElement2 && totalLubesValueElement2.value != '') ? totalLubesValueElement2.value : 0;
            
            if (document.forms[0] && document.forms[0].totalEstimate) {
                document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
            }
        }
        
        function recalculateLubesTotals() {
            var totalPrice = 0.0;
            var tableName = document.getElementsByName('lubesFinalAmount');
            for (var i = 0; i < tableName.length; i++) {
                var partPriceAmount = tableName[i].value == '' ? 0 : tableName[i].value;
                totalPrice += parseFloat(partPriceAmount);
            }
            var totalLubesValueElement = document.getElementById("totalLubesValue");
            if (totalLubesValueElement) {
                totalLubesValueElement.value = totalPrice.toFixed(2);
            }
            
            // Recalculate total estimate
            var totalPartsValueElement = document.getElementById("totalPartsValue");
            var totalLabourChargesElement = document.getElementById("totalLabourCharges");
            var totalOtherChargesElement = document.getElementById("totalOtherCharges");
            var totalLubesValueElement2 = document.getElementById("totalLubesValue");
            
            var totalPartsValueS = (totalPartsValueElement && totalPartsValueElement.value != '') ? totalPartsValueElement.value : 0;
            var totalLabourChargesS = (totalLabourChargesElement && totalLabourChargesElement.value != '') ? totalLabourChargesElement.value : 0;
            var totalOtherChargesS = (totalOtherChargesElement && totalOtherChargesElement.value != '') ? totalOtherChargesElement.value : 0;
            var totalLubesValueS = (totalLubesValueElement2 && totalLubesValueElement2.value != '') ? totalLubesValueElement2.value : 0;
            
            if (document.forms[0] && document.forms[0].totalEstimate) {
                document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
            }
            if (typeof caldiscount === 'function') {
                caldiscount();
            }
        }

        function onChangeLubesBillToFunc(objjCode, row)
        {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var partPriceAmount = 0;
            var totalPrice = 0.0;
            if (Trim(objjCode.value) == '') {
                document.getElementById("lubesWarranty" + row).readOnly = true;
                document.getElementById("lubesWarranty" + row).value = '';
                document.getElementById("lubesFinalAmount" + row).value = document.getElementById("lubesPriceAmount" + row).value;

                var tableName = document.getElementsByName('lubesFinalAmount');
                for (var i = 0; i < tableName.length; i++) {

                    partPriceAmount = tableName[i].value == '' ? 0 : tableName[i].value;
                    totalPrice += parseFloat(partPriceAmount);
                }
                document.getElementById("lubesFinalAmount" + row).value = document.getElementById('lubesPriceAmount' + row).value;
                document.getElementById("totalLubesValue").value = totalPrice.toFixed(2);

                totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
                totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
                totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
                ;
                totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
                document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
            } else {
                var valArr = objjCode.value.split('@@');
                if (valArr[2] == '0.0') {
                    if(valArr[0]=='1' && document.getElementById("totalDiscountPercentage").value!=""){
                        document.getElementById("lubesWarranty" + row).value = document.getElementById("totalDiscountPercentage").value;
                    }else{
                        document.getElementById("lubesWarranty" + row).value = valArr[2];
                    }
                    //document.getElementById("msg_saveFAILED").innerHTML = "Discount on goodwill should be numeric and greater than zero.";
                    document.getElementById("lubesWarranty" + row).focus();
                    document.getElementById("lubesWarranty" + row).readOnly = false;
                } else {
                    document.getElementById("lubesWarranty" + row).value = valArr[2];
                    document.getElementById("lubesWarranty" + row).readOnly = true;
                }
                totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
                totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
                totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
                ;
                totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
                document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
                lubesBillToTotal('lubesPriceAmount' + row, row);
            }

        }
        var sparex = new Array();
        var lubesNo = new Array();
        var lubesDesc = new Array();
        var lubesUnitPrice = new Array();

        function getLubesPartPrice(lubesNo, row) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var lubesNo = lubesNo.value;
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/serviceCreateJC.do?option=getPartPriceBypartNo&cat=LUBES&partno=' + lubesNo + '&jobType='+${serviceform.jobType}+'&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                stateChangedLubesPrice(xmlHttp, row, lubesNo);
            };
            xmlHttp.open("GET", strURL, true);
            xmlHttp.send(null);
        }

        function stateChangedLubesPrice(xmlHttp, row, lubesNoS)
        {
            var context = '<%=cntxpath%>';
            var res = null;

            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {

                if (xmlHttp.responseText.indexOf("session_expired") != -1)
                {
                    window.location.href = context + "/session_expired.do";
                }
                else
                {
                   
                    res = trimS(xmlHttp.responseText);
                    //alert(res);
                    if(res=="notexist" || res=="notexistBEW"){
                        if(res=="notexistBEW"){
                        alert(jobcardBEW_validation_msg);
                        }else{
                            alert(partnotfound_validation_msg);
                        }
                        document.getElementById("lubesNo" + row).value = '';
                        document.getElementById("lubesDesc" + row).value = '';
                        document.getElementById("lubesUnitPrice" + row).value = '';
                        document.getElementById("lubesQuantityS" + row).value = '';
                        document.getElementById("lubesPriceAmount" + row).value = '';
                        document.getElementById("lubesWarranty" + row).value = '';
                        document.getElementById("lubesFinalAmount" + row).value = '';

                    }
                    else if(res==""){
                        alert(partnofound_validation_msg);
                        document.getElementById("lubesNo" + row).value = '';
                        document.getElementById("lubesDesc" + row).value = '';
                        document.getElementById("lubesUnitPrice" + row).value = '';
                        document.getElementById("lubesQuantityS" + row).value = '';
                        document.getElementById("lubesPriceAmount" + row).value = '';
                        document.getElementById("lubesWarranty" + row).value = '';
                        document.getElementById("lubesFinalAmount" + row).value = '';
                        document.getElementById("lubesNo" + row).focus();
                        document.getElementById("Moq" + row).value = '';
                        document.getElementById("partExistsInLubesFlag" + row).value = '';
                    }
                    else{
                        var valArr = res.split('@@');

                        if (valArr.length > 0 && res != '')
                        {
                            sparex = new Array();
                            lubesNo = document.getElementsByName("lubesNo")
                            lubesDesc = document.getElementsByName("lubesDesc")
                            lubesUnitPrice = document.getElementsByName("lubesUnitPrice")
                            for (var k = 0; k < lubesNo.length; k++) {
                                if (lubesNo[k].value != '')
                                {
                                    var res1 = true;
                                    for (var m = 0; m < sparex.length; m++)
                                    {
                                        if (sparex[m] == trimS(lubesNo[k].value))
                                        {
                                            res1 = false;
                                        }
                                    }
                                    if (res1 == false)
                                    {
                                
                                        alert(lubeunique_validation_msg);
                                        document.getElementById("lubesNo" + row).value = '';
                                        document.getElementById("lubesUnitPrice" + row).value = '';
                                        document.getElementById("lubesDesc" + row).value = '';
                                        document.getElementById("lubesNo" + row).focus();
                                        document.getElementById("Moq" + row).value='';
                                        document.getElementById("partExistsInLubesFlag" + row).value='';
                                        
                                        window.scrollTo(0, 0);
                                        img_obj.style.visibility = "hidden";
                                        return false;
                                    }

                                    sparex.push(lubesNo[k].value);
                                }
                            }
                            document.getElementById("lubesDesc" + row).value = valArr[1];
                            document.getElementById("lubesDesc" + row).title = valArr[1];
                            document.getElementById("lubesUnitPrice" + row).value = valArr[0];
                            document.getElementById("Moq" + row).value=valArr[3];
                            document.getElementById("partExistsInLubesFlag" + row).value=valArr[4];
                            
                            document.getElementById("imgLubes" + row).style.dispaly = "none";
                            document.getElementById("lubesNo"+row).readOnly = true;
                            document.getElementById("lubesQuantityS"+row).value = "0";
                            document.getElementById("lubesQuantityS"+row).focus();
                            onChangeLubesBillToFunc(document.getElementById("lubesBillCode" + row), row);
                        } else
                        {
                            document.getElementById("lubesNo" + row).value = "";
                            document.getElementById("lubesDesc" + row).value = "";
                            document.getElementById("lubesUnitPrice" + row).value = "";
                            document.getElementById("lubesNo" + row).title="";
                            document.getElementById("lubesDesc" + row).title="";
                            document.getElementById("imgLubes" + row).style.dispaly = "none";
                            document.getElementById("Moq" + row).value="";
                            document.getElementById("partExistsInLubesFlag" + row).value="";
                        }}
                }
            }
        }

        function getLubesPartPriceByPart(lubesDesc, row) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var lubesDesc = lubesDesc.value;
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/serviceCreateJC.do?option=getPartPriceBypartDesc&cat=LUBES&partDesc=' + lubesDesc + '&jobType='+${serviceform.jobType}+'&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                stateChangedLubesP(xmlHttp, row, lubesDesc);
            };
            xmlHttp.open("GET", strURL, true);
            xmlHttp.send(null);
        }

        function stateChangedLubesP(xmlHttp, row, lubesDescS)
        {
            var context = '<%=cntxpath%>';
            var res = null;

            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {

                if (xmlHttp.responseText.indexOf("session_expired") != -1)
                {
                    window.location.href = context + "/session_expired.do";
                }
                else
                {
                    res = trimS(xmlHttp.responseText);
                    if(res==""){
                        alert(descnotfound_validation_msg);
                        document.getElementById("lubesNo" + row).value = '';
                        document.getElementById("lubesDesc" + row).value = '';
                        document.getElementById("lubesUnitPrice" + row).value = '';
                        document.getElementById("lubesQuantityS" + row).value = '';
                        document.getElementById("lubesPriceAmount" + row).value = '';
                        document.getElementById("lubesWarranty" + row).value = '';
                        document.getElementById("lubesFinalAmount" + row).value = '';
                        document.getElementById("lubesDesc" + row).focus();
                    }
                    var valArr = res.split('@@');
                    if (valArr.length > 0 && res != '')
                    {
                        sparex = new Array();
                        lubesNo = document.getElementsByName("lubesNo")
                        lubesDesc = document.getElementsByName("lubesDesc")
                        lubesUnitPrice = document.getElementsByName("lubesUnitPrice")
                        for (var k = 0; k < lubesDesc.length; k++) {
                            if (lubesDesc[k].value != '')
                            {
                                var res1 = true;
                                for (var m = 0; m < sparex.length; m++)
                                {
                                    if (sparex[m] == trimS(lubesDesc[k].value))
                                    {
                                        res1 = false;
                                    }
                                }
                                if (res1 == false)
                                {
    <%--document.getElementById("msg_saveFAILED").innerHTML = "Same part description can not be enter twice in lubes";--%>
                                    alert(lubeDescunique_validation_msg);
                                    document.getElementById("lubesNo" + row).value = '';
                                    document.getElementById("lubesUnitPrice" + row).value = '';
                                    document.getElementById("lubesDesc" + row).value = '';
                                    document.getElementById("lubesDesc" + row).focus();
                                    window.scrollTo(0, 0);
                                    img_obj.style.visibility = "hidden";
                                    return false;
                                }

                                sparex.push(lubesDesc[k].value);
                            }
                        }
                        document.getElementById("lubesNo" + row).value = valArr[1];
                        document.getElementById("lubesNo" + row).title = valArr[1];
                        document.getElementById("lubesUnitPrice" + row).value = valArr[0];
                        document.getElementById("imageLubes" + row).style.dispaly = "none";
                        onChangeLubesBillToFunc(document.getElementById("lubesBillCode" + row), row);
                    } else
                    {
                        document.getElementById("lubesNo" + row).value = "";
                        document.getElementById("lubesUnitPrice" + row).value = "";
                        document.getElementById("lubesDesc" + row).value = "";
                        document.getElementById("lubesNo" + row).title="";
                        document.getElementById("lubesDesc" + row).title="";
                        document.getElementById("imageLubes" + row).style.dispaly = "none";
                    }
                }
            }
        }
        function getLubesPartCheck(lubesNo, row) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var partNo = lubesNo.value;
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/serviceCreateJC.do?option=getPartCheck&partno=' + partNo + '&jobType='+${serviceform.jobType}+'&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                stateLubesPartCheck(xmlHttp, row);
            };
            xmlHttp.open("GET", strURL, true);
            xmlHttp.send(null);
        }

        function stateLubesPartCheck(xmlHttp, row)
        {
            var context = '<%=cntxpath%>';
            var res = null;

            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {

                if (xmlHttp.responseText.indexOf("session_expired") != -1)
                {
                    window.location.href = context + "/session_expired.do";
                }
                else
                {
                    res = trimS(xmlHttp.responseText);
                    //alert(res.split('@@'));
                    if (res == 'notexists' || res=='notexistBEW')
                    {
                        document.getElementById("lubesPartNumber" + row).value = res;
                        document.getElementById("msg_saveFAILED").innerHTML = partno_msg + res + exist_msg;
                        document.getElementById("lubesNo" + row).focus();
                        document.getElementById("lubesNo" + row).value = '';
                        return false;
                    }


                }
            }
        }
        //END///
        
        var lubesLen  = '';
        var partsLen  = '';
        
        function validate(obj)
        {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var jobcardno = document.getElementById("jobCardNo").value;
            var quantityS = new Array();
            var complaintCode = new Array();
            var actionCode = new Array();
            var labourChargesAmount = new Array();
            var workCode = new Array();
            var workDescription = new Array();
            var workAmount = new Array();
            var sparex = new Array();
            var sparexComp = new Array();
            var sparexAct = new Array();
            var billCode = new Array();
            var warranty = new Array();
            var chCount = 0;
            var partNo = new Array();
            var selectedFilesHid = new Array();

            var lubesNo = new Array();
            var lubesDesc = new Array();
            var lubesQuantityS = new Array();
            var lubesBillCode = new Array();
            var lubesWarranty = new Array();

            partNo = document.getElementsByName("partNo");//lubesNo = document.getElementsByName("lubesNo");
            var qtycount=0;

            quantityS = document.getElementsByName("quantityS");
            //complaintCode = document.getElementsByName("complaint_Code");
            complaintCode = document.getElementsByName("complaintCode");

            actionCode = document.getElementsByName("actionCode");

            labourChargesAmount = document.getElementsByName("labourChargesAmount");
            workCode = document.getElementsByName("workCode");
            workDescription = document.getElementsByName("workDescription");
            workAmount = document.getElementsByName("workAmount");
            billCode = document.getElementsByName("billCode");
            warranty = document.getElementsByName("warranty");
            selectedFilesHid = document.getElementsByName("selectedFilesHid");
            complaint_Code = document.getElementsByName("complaint_Code");
            causal_Code = document.getElementsByName("causal_Code");
            lubesNo = document.getElementsByName("lubesNo");
            lubesDesc = document.getElementsByName("lubesDesc");
            lubesQuantityS = document.getElementsByName("lubesQuantityS");
            lubesBillCode = document.getElementsByName("lubesBillCode");
            lubesWarranty = document.getElementsByName("lubesWarranty");
            lubesLen = lubesNo;
            partsLen = partNo;
            
            if (jobcardno == '')
            {

                alert(vehInfo_validation_msg);

                return false;
            }

            for (var k = 0; k < partNo.length; k++) {
                

                if (partNo[k].value != '')
                {

                    var res1 = true;
                    for (var m = 0; m < sparex.length; m++)
                    {
                        if (sparex[m] == (partNo[k].value))
                        {
                            res1 = false;
                        }
                    }

                    if (res1 == false)
                    {

                        alert(partunique_validation_msg);
                        estimateTotalValue();
                        partNo[k].value = '';
                        partNo[k].focus();
                        window.scrollTo(0, 0);
                        return false;
                    }

                    if (!(checkWhitStringCommon(partNo[k])) && partNo[k].value != "")
                    {


                        alert(spc_validation_msg+'Part Number.');
                        partNo[k].value = "";
                        partNo[k].focus();

                        return false;
                    }

                    // getPartCheck(partNo[k], k + 1)
                    sparex.push(partNo[k].value);

                    if (quantityS[k].value.trim() == "")
                    {

                        alert(not_blank_validation_msg+'Quantity.')
                        quantityS[k].focus();
                        window.scrollTo(0, 0);
                        return false;
                    }
                    if (quantityS[k].value.trim() == "0.0" || quantityS[k].value.trim() == "0")
                    {
                        qtycount++;
                    }
                    

                    if (billCode[k].value.trim() == "")
                    {

                        alert(not_blank_dropdown_validation_msg+'bill To.')
                        billCode[k].focus();
                        window.scrollTo(0, 0);
                        return false;
                    }
                    var wrttype='${serviceform.warrantyApplicable}'
                    if (wrttype == "N")
                    {
                        if(billCode[k].value.split("@@")[0]=="2"){
                            alert(wrrtyapp_validation_msg)
                            billCode[k].focus();

                            return false;
                        }

                    }




    

                }


            }

            for(var j=0;j<complaint_Code.length;j++){


                if(billCode[j].value!=""){
                    var val=billCode[j].value.split('@@')[1];

                    if (val == "Yes")
                    {
                        if(complaint_Code[j].value==""){

                            alert(not_blank_dropdown_validation_msg+'Complaint.')
                            complaint_Code[j].focus();
                            window.scrollTo(0, 0);//go to top of page
                            return false;
                        }
                        if(causal_Code[j].value==""){

                            alert(not_blank_dropdown_validation_msg+'Causal/Consequential.')
                            causal_Code[j].focus();
                            window.scrollTo(0, 0);//go to top of page
                            return false;
                        }

                    }
                }
            }

    
            for (var k = 0; k < lubesNo.length; k++) {

                if (lubesNo[k].value != '')
                {
                    var res1 = true;
                    for (var m = 0; m < sparex.length; m++)
                    {
                        if (sparex[m] == (lubesNo[k].value))
                        {
                            res1 = false;
                        }
                    }
                    if (res1 == false)
                    {
    <%--document.getElementById("msg_saveFAILED").innerHTML = "Same part number can not be enter twice";--%>
                        alert(partunique_validation_msg);
                        estimateTotalValue();
                        lubesNo[k].value = '';
                        lubesNo[k].focus();

                        return false;
                    }
                    if (!(checkWhitStringCommon(lubesNo[k])) && lubesNo[k].value != "")
                    {
    <%--document.getElementById("msg_saveFAILED").innerHTML = "Spacial characters semicolon and AND(') are not permitted part number";--%>
                        alert(spc_validation_msg+'Part Number.');
                        lubesNo[k].value = "";
                        lubesNo[k].focus();
                        // window.scrollTo(0, 0);
                        return false;
                    }
                    //  getLubesPartCheck(lubesNo[k], k + 1)
                    sparex.push(lubesNo[k].value);

                    if (lubesQuantityS[k].value.trim() == "")
                    {
    <%--document.getElementById("msg_saveFAILED").innerHTML = "Quantity can not be blank";--%>
                        alert(not_blank_validation_msg+'Quantity.')
                        lubesQuantityS[k].focus();
                        //  window.scrollTo(0, 0);
                        return false;
                    }
                    if (lubesQuantityS[k].value.trim() == "0.0" || lubesQuantityS[k].value.trim() == "0")
                    {
                        qtycount++;
                    }
    <%--if (!checkQuantity(lubesQuantityS[k].value))
    {
        document.getElementById("msg_saveFAILED").innerHTML = "Quantity should be numeric and greater than zero.";
        lubesQuantityS[k].value = "";
        lubesQuantityS[k].focus();
        window.scrollTo(0, 0);
        return false;

                    }--%>
                                    if (lubesNo[k].value != "" && lubesBillCode[k].value.trim() == "")
                                    {
    <%--document.getElementById("msg_saveFAILED").innerHTML = "Please select lubes bill to";--%>
                        alert(not_blank_dropdown_validation_msg+'bill To.')
                        lubesBillCode[k].focus();
                        return false;
                    }

                    var wrttype='${serviceform.warrantyApplicable}'
                    if (wrttype == "N")
                    {
                        if(lubesBillCode[k].value.split("@@")[0]=="2"){
                            alert(wrrtyapp_validation_msg)
                            lubesBillCode[k].focus();//lubesBillCode

                            return false;
                        }

                    }
                    var valArr = lubesBillCode[k].value.split('@@');
    
                }
            }
            //complaintCode
            for (var k = 0; k < complaintCode.length; k++) {


                if (complaintCode[k].value.trim() != "")
                {
                    if (actionCode[k].value.trim() == "")
                    {

                        alert(not_blank_dropdown_validation_msg+'action taken.')
                        actionCode[k].focus();

                        return false;
                    }
                    if (labourChargesAmount[k].value.trim() == "")
                    {

                        alert(not_blank_validation_msg+'Labour Charges.');
                        labourChargesAmount[k].focus();

                        return false;
                    }
           

                }


                if (actionCode[k].value.trim() != "")
                {
                    if (complaintCode[k].value.trim() == "")
                    {

                        alert(not_blank_dropdown_validation_msg+'complaint.')
                        complaintCode[k].focus();

                        return false;
                    }
                    if (labourChargesAmount[k].value.trim() == "")
                    {

                        alert(not_blank_validation_msg+'Labour Charges.');
                        labourChargesAmount[k].focus();

                        return false;
                    }
                 


                }

                if ((labourChargesAmount[k].value != '0.00' && labourChargesAmount[k].value != '0.0' && labourChargesAmount[k].value != '0' && labourChargesAmount[k].value != '') ) {
    <c:if test="${serviceForm.constantValue eq 'Yes'}" >


                    if (complaintCode[k].value.trim() == "")
                    {

                        alert(not_blank_dropdown_validation_msg+'complaints.')
                        complaintCode[k].focus();

                        return false;
                    }
                    if (actionCode[k].value.trim() == "")
                    {

                        alert(not_blank_validation_msg+'action taken.');
                        actionCode[k].focus();

                        return false;
                    }





    </c:if>
                }


                if ((labourChargesAmount[k].value != '0.00' && labourChargesAmount[k].value != '0.0' && labourChargesAmount[k].value != '0' && labourChargesAmount[k].value != '') ) {
    <c:if test="${serviceForm.constantValue eq 'No'}" >


                    if (complaintCode[k].value.trim() == "")
                    {

                        alert(not_blank_dropdown_validation_msg+'complaint.')
                        complaintCode[k].focus();

                        return false;
                    }
                    if (actionCode[k].value.trim() == "")
                    {

                        alert(not_blank_validation_msg+'action taken.');
                        actionCode[k].focus();

                        return false;
                    }
                    if (!(checkWhitStringCommon(actionCode[k])) && actionCode[k].value != "")
                    {

                        alert(spc_validation_msg+'Action Taken.');
                        actionCode[k].focus();
                        return false;
                    }


    </c:if>
                }

                var res1 = true;
                if(complaintCode[k].value!=""){
                    for (var m = 0; m < sparexComp.length; m++)
                    {
                        if (sparexComp[m] == (complaintCode[k].value))
                        {
                            res1 = false;
                        }
                    }
                }

                sparexComp.push(complaintCode[k].value);

            }




            for (var k = 0; k < workCode.length; k++) {

                if (workAmount[k].value.trim() != "" && workAmount[k].value.trim() != "0.00" && workAmount[k].value.trim() != "0.0" && workAmount[k].value.trim() != "0" )
                {
                    if (workCode[k].value.trim() == "")
                    {
                        alert(not_blank_dropdown_validation_msg+' Work.')
                        workCode[k].focus();
                        return false;
                    }
                    if(workDescription[k].value==""){
                        alert(not_blank_dropdown_validation_msg+' Description.')
                        workDescription[k].focus();
                        return false;
                    }
                }


                if (workCode[k].value.trim() != "")
                {
                    if(workDescription[k].value==""){
                        alert(not_blank_dropdown_validation_msg+' Description.')
                        workDescription[k].focus();
                        return false;
                    }
                    if(workAmount[k].value==""){
                        alert(not_blank_dropdown_validation_msg+' Amount.')
                        workAmount[k].focus();
                        return false;
                    }
                    if ((workAmount[k].value == '0.00' || workAmount[k].value == '0.0' || workAmount[k].value == '0' || workAmount[k].value == '') ) {
                        alert(actionamount_validation_msg)
                        workAmount[k].focus();
                        return false;
                    }


                }


                if (workDescription[k].value.trim() != "")
                {

                    if(workCode[k].value==""){
                        alert(not_blank_dropdown_validation_msg+' Work.')
                        workCode[k].focus();
                        return false;
                    }
                    if(workAmount[k].value==""){
                        alert(not_blank_dropdown_validation_msg+' Amount.')
                        workAmount[k].focus();
                        return false;
                    }
                    if ((workAmount[k].value == '0.00' || workAmount[k].value == '0.0' || workAmount[k].value == '0' || workAmount[k].value == '') ) {
                        alert(actionamount_validation_msg)
                        workAmount[k].focus();
                        return false;
                    }


                }

            }



            for (var k = 0; k < workCode.length; k++) {

                if (k != 0 && workCode[k].value != '') {
                    var res1 = true;
                    for (var m = 0; m < sparexAct.length; m++)
                    {
                        if (sparexAct[m] == (workCode[k].value))
                        {
                            res1 = false;
                        }
                    }
  


                                    if (!(checkWhitStringCommon(workDescription[k])) && workDescription[k].value != "")
                                    {

                                        alert(spc_validation_msg+'Work Description');
                                        workDescription[k].value = "";
                                        workDescription[k].focus();
                                        //window.scrollTo(0, 0);
                                        return false;
                                    }


                                    if (validateFloat(workAmount[k].value))
                                    {

                                        alert(workAmt_validation_msg);
                                        workAmount[k].value = "";
                                        workAmount[k].focus();

                                        return false;

                                    }

                                }
                                sparexAct.push(workCode[k].value);
                                if (workAmount[k].value != '' && workAmount[k].value != '0.0' && workAmount[k].value != '0.00' && workAmount[k].value != '0') {
                                    if (workDescription[k].value.trim() == "")
                                    {

                                        alert(not_blank_validation_msg+'work description.')
                                        workDescription[k].focus();

                                        return false;
                                    }
                                    if (!(checkWhitStringCommon(workDescription[k])) && workDescription[k].value != "")
                                    {

                                        alert(spc_validation_msg+'Work Description');

                                        workDescription[k].value = "";
                                        workDescription[k].focus();

                                        return false;
                                    }


                                    if (validateFloat(workAmount[k].value))
                                    {

                                        alert(workAmt_validation_msg);
                                        workAmount[k].value = "";
                                        workAmount[k].focus();

                                        return false;

                                    }

                                }

                            }




                            var jobcarddate='${serviceform.jobCardDate}'

                            var pdate= document.getElementById("promised");
                            var rdate=document.getElementById("requiredBYCustomer");
                            var cdate= document.getElementById("currentEstimate");

                            var jdate=jobcarddate.split('/');
                            var jdate1=jdate[2]+'/'+jdate[1]+'/'+jdate[0];
                            var jobdate=new Date(jdate1);
                            var ttdate="";
                            var tmon="<%=month%>";
                            var tdays="<%=day%>";

                            if(eval(tmon)<10)
                                    tmon="0"+tmon;

                            if(eval(tdays)<10)
                                    tdays="0"+tdays;

                            ttdate=<%=year%>+'/'+tmon+'/'+tdays;

                            var today = new Date(ttdate);
                            //alert(today);
                            if(pdate.value!=""){
                                var pddate=pdate.value.split('/');
                                var pdate1=pddate[2]+'/'+pddate[1]+'/'+pddate[0];
                                var prdate=new Date(pdate1);
                                if(jobdate>prdate){
                                    alert(prdate_validation_msg+"("+jobcarddate+")");
                                    pdate.focus();
                                    return false;
                                }
                                if(today<prdate){
                                    alert(prdatedatecurrent_validation_msg+"("+pdate1+")");
                                    pdate.focus();
                                    return false;
                                }
                            }
                            if(rdate.value!=""){
                                var rddate=rdate.value.split('/');
                                var rdate1=rddate[2]+'/'+rddate[1]+'/'+rddate[0];
                                var rcdate=new Date(rdate1);

                                if(jobdate>rcdate){
                                    alert(rcdate_validation_msg+"("+jobcarddate+")");
                                    rdate.focus();
                                    return false;
                                }
                                if(today<rcdate){
                                    alert(rcdatedatecurrent_validation_msg+"("+rdate1+")");
                                    rdate.focus();
                                    return false;
                                }
                            }

                            if(cdate.value!=""){
                                var cddate=cdate.value.split('/');
                                var cdate1=cddate[2]+'/'+cddate[1]+'/'+cddate[0];
                                var curdate=new Date(cdate1);

                                if(jobdate>curdate){
                                    alert(actdate_validation_msg+"("+jobcarddate+")");
                                    cdate.focus();
                                    return false;
                                }
                                if(today<curdate){
                                    alert(actdatedatecurrent_validation_msg+"("+cdate1+")");
                                    cdate.focus();
                                    return false;
                                }
                            }
                            //alert("before submit");
                            EstimatedPartNo();
                           
                            
                            validateAndSubmitForm();
//                            document.getElementById("form1").submit();
                        }
        
        function checkPartInLubesAjax(partNo, callback) {
            var xhr = new XMLHttpRequest();
            var strURL = '<%=cntxpath%>/serviceCreateJC.do?option=checkPartInLubes&partNo=' + encodeURIComponent(partNo);

            xhr.open("GET", strURL, true);

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var result = xhr.responseText.trim(); // "true" or "false"
                    callback(result);
                }
            };

            xhr.send(null);
        }

        function validateAndSubmitForm() {

        	
        	// Check for max qty allowed at VOR J/C
        	var vor = "${VOR}";
        	var count = 0;
        	
        	if (vor && vor.trim().toUpperCase() === "YES") {
        		if (partsLen && partsLen.length > 0) {
        			for (var k = 1; k <= partsLen.length; k++) {
                        var partNoElement = document.getElementById("partNo" + k);
                        if (partNoElement) {
                            var partNo = partNoElement.value;
                            if (partNo != '') {
                            	count++;
                            }
                        }
                    }
        		}
        		
        		if (lubesLen && lubesLen.length > 0) {
        			for (var k = 1; k <= lubesLen.length; k++) {
                        var lubesNoElement = document.getElementById("lubesNo" + k);
                        if (lubesNoElement) {
                            var partNo = lubesNoElement.value;
                            if (partNo != '') {
                                count++;
                            }
                        }
                    }
        		}
        		
        		if(count === 0){
        			alert("Please fill Parts in the Estimate for VOR Job Cards");
        			return false;
        		}
        		
        	}
        
            var invalidFound = false;
            var completed = 0;
            var totalToCheck = 0;
        
            if (lubesLen && lubesLen.length > 0) {
                for (var k = 1; k <= lubesLen.length; k++) {
                    var qtyElement = document.getElementById("lubesQuantityS" + k);
                    var partNoElement = document.getElementById("lubesNo" + k);
                    
                    if (!qtyElement || !partNoElement) {
                        continue; // Skip if elements don't exist (deleted rows)
                    }
                    
                    var qty = qtyElement.value;
                    var partNo = partNoElement.value;
                    
                    if (!qty || qty === "") {
                        continue; // Skip empty quantities
                    }

                    var decimalPart = qty.split(".")[1]; // part after dot

                    if (decimalPart && parseInt(decimalPart) > 0) {
                        totalToCheck++; // only check parts with decimal qty

                        checkPartInLubesAjax(partNo, function (result) {
                            completed++;

                            if (result === "false" && !invalidFound) {
                                invalidFound = true;
                                alert("Decimal Qty is not allowed in lube parts");
                            }

                            // When all checks finish
                            if (completed === totalToCheck) {
                                if (!invalidFound) {
                                    var form1Element = document.getElementById("form1");
                                    if (form1Element) {
                                        form1Element.submit();
                                    }
                                }
                            }
                        });
                    }
                }
            }

            // if no decimal quantities found at all
            if (totalToCheck === 0) {
                var form1Element = document.getElementById("form1");
                if (form1Element) {
                    form1Element.submit();
                }
            }
        }

        
  
                        function getPartCheck(partNo, row) {
                            var partNo = partNo.value;
                            var todate = new Date().getTime();
                            var strURL = '<%=cntxpath%>/serviceCreateJC.do?option=getPartCheck&partno=' + partNo + '&tm=' + todate;
                            xmlHttp = GetXmlHttpObject();
                            xmlHttp.onreadystatechange = function()
                            {
                                statePartCheck(xmlHttp, row);
                            };
                            xmlHttp.open("GET", strURL, true);
                            xmlHttp.send(null);
                        }

                        function statePartCheck(xmlHttp, row)
                        {
                            var context = '<%=cntxpath%>';
                            var res = null;

                            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                            {

                                if (xmlHttp.responseText.indexOf("session_expired") != -1)
                                {
                                    window.location.href = context + "/session_expired.do";
                                }
                                else
                                {
                                    res = trimS(xmlHttp.responseText);
                                    //var valArr = res.split('@@');
                                    if (res == 'notexists')
                                    {
                                        document.getElementById("partNumber" + row).value = res;
                                        document.getElementById("msg_saveFAILED").innerHTML = partno_msg + res + exist_msg;
                                        document.getElementById("partNo" + row).focus();
                                        document.getElementById("partNo" + row).value = '';
                                        return false;
                                    }


                                }
                            }
                        }
                        function browsefile(elem)
                        {
                            var workCode = new Array();
                            var workDescription = new Array();

                            workCode = document.getElementsByName("workCode");
                            workDescription = document.getElementsByName("workDescription");
                            for (var k = 0; k < workCode.length; k++) {
                                var w = 480, h = 300;
                                if (document.all || document.layers)
                                {
                                    w = screen.availWidth;
                                    h = screen.availHeight;
                                }
                                var popW = 300, popH = 100;
                                var leftPos = (w - popW) / 2, topPos = (h - popH) / 2;
                                //                if(k==0)
                                //                {
                                //                    if(workCode[0].value=="" && workDescription[0].value=="")
                                //                    {
                                //                        document.getElementById("msg_saveFAILED").innerHTML="Please Select the work code before attaching the File.";
                                //                        workCode[0].focus();
                                //                        return false;
                                //                    }
                                //                    var strURL =window.open('<%=cntxpath%>/service/v_uploadfile.jsp?option=WCF&workCode='+workCode[0].value+'&index='+elem+'&add=add','mywindow','width=' + popW + ',height=' + popH + ',top=' + topPos + ',left=' + leftPos);
                                //                    //  document.form1.WCF.value='attachedlp';
                                //                }
                                //                if(k>1)
                                //                {
                                //
                                //                    if((workCode[k].value=='' && workDescription[k].value==''))
                                //                    {
                                //                        document.getElementById("msg_saveFAILED").innerHTML="Please Select the work code before attaching the File.";
                                //                        workCode[k].focus();
                                //                        return false;
                                //                    }
                                //                    var strURL =window.open('<%=cntxpath%>/service/v_uploadfile.jsp?option=WCF&workCode='+workCode[k].value+'&index='+elem+'&add=add','mywindow','width=' + popW + ',height=' + popH + ',top=' + topPos + ',left=' + leftPos);
                                //
                                //                }
                            }
                        }
                        function checkBlank(ob, row) {


                            document.getElementById("warranty" + row).focus();
                            document.getElementById("warranty" + row).select();


                        }




                        //WTY Warrenty


                        function CauseAndConquences(row){

                            obj=document.getElementById("billCode"+row);

                            var val=(document.getElementById("billCode"+row).value).split('@@')[0];
    
   
                            if(obj.options[obj.selectedIndex].text!=null && (val=="2" )){
                                document.getElementById("complaint_Code"+row).disabled = false;
                                document.getElementById("causal_Code"+row).disabled = false;

       
                            }
                            else{
                                document.getElementById("complaint_Code"+row).disabled = true;

                                document.getElementById("causal_Code"+row).disabled = true;

                            }

                        }


                        function getbillcode(row){

        
                            //billCode = document.getElementsByName("billCode");
                            cmpcode = document.getElementsByName("complaint_Code");
                            causalCode = document.getElementsByName("causal_Code");




                        }

                        var t=0;

                        function checkcausal(){

                            causalCode = document.getElementsByName("causal_Code");
                            complaintCode = document.getElementsByName("complaint_Code");
            
                            complaintlist=new Array();


                            for(var i=0;i<complaintCode.length;i++)
                            {
                                complaintlist[i]=complaintCode[i];
                            }

                            for (var k = 0; k < complaintCode.length; k++) {
                                t=0;
                                if (complaintCode[k].value != "")
                                {
                                    var res1 = true;
                                    for (var m = 0; m <= k; m++)
                                    {

                                        if (complaintlist[m].value == trimS(complaintCode[k].value))
                                        {
                                            if(causalCode[m].value=="Causal"){

                                                t=t+1;

                                            }
                                            if(1<t)
                                                res1 = false;
                                        }
                                    }
                                    if (res1 == false)
                                    {
                                        alert(compCausal_validation_msg);
                                        complaintCode[k].focus();

                                        return false;
                                    }
                                }
                            }
                            return true;
                        }

                        function printList()
                        {
                            var jobCardNo='${serviceform.jobCardNo}';
                            var vinid='${serviceform.vinid}';
                            var vinNo='${serviceform.vinNo}';
                            var strURL = "${pageContext.request.contextPath}/serviceCreateJC.do?option=printEstimateData&jobCardNo="+jobCardNo+"&vinid="+vinid+"&vinNo="+vinNo+"&type=print";
                            window.open(strURL,'iji','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
                        }
                        
            $(document).ready(function () {

            $(function() {
                var ttdate="";
                var tmon="<%=month%>";
                var tdays="<%=day%>";
                if(eval(tmon)<10)
                    tmon="0"+tmon;

                if(eval(tdays)<10)
                tdays="0"+tdays;

                ttdate=+tdays+'/'+tmon+ '/'+<%=year%>;

                var today = new Date(ttdate);

                var promised=document.getElementById("promised").value;
                var requiredBYCustomer=document.getElementById("requiredBYCustomer").value;
                var currentEstimate=document.getElementById("currentEstimate").value;

                if(promised!=""){
                    $( ".datepicker" ).datepicker();
                }
                if(requiredBYCustomer!=""){
                    $( ".datepicker" ).datepicker();
                }
                if(currentEstimate!=""){
                    $( ".datepicker" ).datepicker();
                }else{
                  $( ".datepicker" ).datepicker( "setDate", ttdate);
                }
                
                // Update Select All checkboxes on page load
                updateSelectAllSparesCheckbox();
                updateSelectAllLubesCheckbox();
                
                // Add event listeners to individual checkboxes to sync Select All state
                document.addEventListener('change', function(e) {
                    if (e.target && e.target.name === 'sparesRowCheckbox') {
                        updateSelectAllSparesCheckbox();
                    }
                    if (e.target && e.target.name === 'lubesRowCheckbox') {
                        updateSelectAllLubesCheckbox();
                    }
                });
            });
        });

</script>
<div class="breadcrumb_container">
    <ul>
        <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link> </li>
        <c:if test="${openJC eq 'viewAll'}">
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=init_viewallJobcarddetails&jobType=ViewJC"><bean:message key="label.common.viewalljobcard" /></a></li>
        </c:if>
        <li class="breadcrumb_link"><bean:message key="label.common.createjobcard" /></li>

    </ul>
</div>


<div class="message" id="msg_saveFAILED"><html:errors/>${message}</div>
<center>
    <div class="content_right1">
        <div class="con_slidediv2" style="width: 100%">


            <h1 ><span class="formLabel"> <bean:message key="label.common.estimate" /></span></h1>




            <c:if test="${serviceform.jobcardview eq 'true' }">
                <%@ include file="topbandshow.jsp" %>
            </c:if>


            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
                <tr>
                    <td>
                        <table width="100%"  border="0" align="center" cellpadding="3" cellspacing="3">
                            <tr align="center" valign="middle" class="formHeader">

<!--                    <td><label><a href="<%=cntxpath%>/serviceProcess.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}" class="formLabel">Vehicle Information</a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceProcess.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}" class="formLabel">Customer Information</a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceProcess.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}" class="formLabel">Standard Checks</a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceProcess.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}" class="formLabel">Complaint</a></label></td>
                    <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceProcess.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}" class="linkGreyBg">Estimate</a></label></td>
                    <td><label><a href="#" class="formLabel">Actuals</a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceProcess.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}" class="formLabel">Status</a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceProcess.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}" class="formLabel">History</a></label></td>

                                -->
                                <c:choose> <c:when test="${pathNm eq 'fillJC'}">
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.vehicleinfo" /></a></label></td>

                                        <td ><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>

                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                                        <td ><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                                        <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="linkGreyBg"><bean:message key="label.common.estimate" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                                        <c:if test="${showHistory ne 'Y'}">
                                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                                        </c:if>

                                    </c:when>
                                    <c:otherwise>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.vehicleinfo" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                                        <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="linkGreyBg"><bean:message key="label.common.estimate" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                                        <c:if test="${showHistory ne 'Y'}">
                                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose> </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td>
                        <form name="form1" id="form1" method="post" action="<%=cntxpath%>/serviceCreateJC.do?option=manageEstimate&jctype=${serviceform.jctype}&jobType=${serviceform.jobType}" onsubmit="return false;">
                            <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/masterAction.do?option=initEstimate'>MANAGE JOB CARD</a>@@ADD ACTION"/>
                            <input type="hidden" name="jobCardNo" id="jobCardNo" value="${serviceform.jobCardNo}"/>
                            <input type="hidden" name="vinid" id="vinid" value="${vinid}"/>
                            <input type="hidden" name="jobidvalue" id="jobidvalue" value="${jobidvalue}"/>
                            <input type="hidden" name="jobId" value="${serviceform.jobId}">
                            <input name="showHistory" type="hidden" value="${showHistory}">
                            <input  type="hidden" name="TotalDiscountPercentage" id="totalDiscountPercentage"  value="${serviceform.totalDiscountPercentage}"/>
                           
                            
                            <table width="100%"  border="0" cellspacing="2">
                                <tr>
                                    <td colspan="2" valign="top">
                                        <table id="myTable" width="100%" cellpadding="2" cellspacing="4" class="LiteBorder">
                                            <tbody>
                                                <tr class="formCatHeading">
                                                    <c:choose>
                                                        <c:when test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                            <td colspan="10" align="center"><bean:message key="label.common.spares" /></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td colspan="9" align="center"><bean:message key="label.common.spares" /></td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </tr>
                                                <tr>
                                                    <c:if test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                        <td align="left" class="tdnew" width="3%"> <label><input type="checkbox" id="selectAllSpares" onclick="selectAllSparesRows(this);" title="Select All"/></label></td>
                                                    </c:if>
                                                    <td align="left" class="tdnew" > <label><bean:message key="label.common.partno" /></label></td>
                                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.partdesc" /></label></td>
                                                    <%-- <td> <label><bean:message key="label.common.selectcomplaint" /></label></td>
                                                     <td> <label><bean:message key="label.common.casualconq" /></label></td>--%>
                                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.unitprice" /></label></td>
                                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.qty" /></label></td>
                                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.amount" /></label></td>
                                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.billto" /></label></td>
                                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.discount" />(%)</label></td>
                                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.finalamount" /></label></td>
                                                </tr>
                                                <c:choose>

                                                    <c:when test="${serviceform.jobcardview eq 'true' and fn:length(partList) gt 0}">
                                                        <c:if test="${!empty partList}">

                                                            <c:set var="sno" value="1"></c:set>
                                                            <c:forEach items="${partList}" var="partList">

                                                                <tr id ="${sno}">
                                                                    <c:if test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                                        <td align="center" width="3%"><input type="checkbox" name="sparesRowCheckbox" class="sparesRowCheckbox" value="${sno}" /></td>
                                                                    </c:if>
                                                                    <td  align="left"><input name="partNo" type="text" class="partnumber" id="partNo${sno}" value="${partList.partNo_str}" onblur="this.value=TrimAll(this.value)"  />&nbsp;<a href='#'><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions' border='0'  onclick=""/><img alt=""  id='img${sno}' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif' /><input type="hidden" name="partNumber" id="partNumber${sno}" value=""></td>
                                                                            <td  align="left"><input name="partDesc" type="text" class="partdescription"  id="partDesc${sno}" title="${partList.partDesc_str}" readOnly="readonly" value="${partList.partDesc_str}"maxlength="250"  onblur="this.value=TrimAll(this.value)"/><%--<a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPartDesc('partDesc${sno}', document.getElementById('partDesc${sno}'), document.getElementById('comptype${sno}'), document.getElementById('image${sno}'), '${sno}');"/><img alt=""  id='image2' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>--%><input type="hidden" name="comptype" id="comptype${sno}" value="PRT"></td>

                                                                            <td  align="left"><input name="unitPrice" type="text" class="qty" value="${partList.unitprice_str}" readonly id="unitPrice${sno}"/></td>
                                                                            <td  align="left"><input name="quantityS" type="text" class="qty" id="quantityS${sno}" value="${partList.qty_str}"maxlength="10" onkeypress="return isNumberKeySpares(event);"  maxlength="15" onblur="partAmountTotal('quantityS${sno}', 'unitPrice${sno}', 'partPriceAmount${sno}', ${sno});"/></td>
                                                                            <td  align="left"><input name="partPriceAmount" type="text" class="qty"  id="partPriceAmount${sno}" value="${partList.amount_str}"readonly /></td>
                                                                            <td  align="left"><select name="billCode" class="bill"  id="billCode${sno}" onchange="onChangeBillToFunc(this, ${sno});">
                                                                                    <option value="">--Select--</option>
                                                                                    <c:forEach items="${billList}" var="dataList">
                                                                                        <c:set value="${fn:split(dataList.value,'@@')}" var="bill"/>
                                                                                        <c:if test="${partList.billto_str eq  bill[0] }">

                                                                                            <option value='${dataList.value}' title='${dataList.label}' selected >${dataList.label}</option>


                                                                                        </c:if>
                                                                                        <c:if test="${partList.billto_str ne  bill[0] }">

                                                                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>
                                                                                        </c:if>

                                                                                    </c:forEach>
                                                                                </select></td>
                                                                            <td  align="left"><input name="warranty" type="text" class="qty" maxlength="5" id="warranty${sno}" value="${partList.discount_str}" readonly onfocus="checkBlank(this, ${sno})" onkeypress="return numeralsOnly(event, getElementById('warranty${sno}'))"  onblur="validateFloat(this);billToTotal('partPriceAmount${sno}', ${sno});"/></td>
                                                                            <td  align="left"><input name="finalAmount" type="text" class="qty" id="finalAmount${sno}" value="${partList.finalamt_str}"readonly/></td>
                                                                </tr>

                                                                <c:set var="sno" value="${sno + 1 }" />
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="i" value="0" scope="page" />
                                                        <c:if test="${serviceform.stndprt eq 'YES'}">

                                                            <c:forEach items="${serviceform.comptype}" var="dataList">
                                                                <tr>

                                                                    <%--onblur="getPartPrice(document.getElementById('partNo1'),1);"--%>
                                                            <input type="hidden" name="comptype" id="comptype${i + 1 }" value="PRT">
                                                            <c:if test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                                <td align="center" width="3%"><input type="checkbox" name="sparesRowCheckbox" class="sparesRowCheckbox" value="${i + 1}" /></td>
                                                            </c:if>
                                                            <td align="left"><input name="partNo"  type="text" id="partNo${i + 1 }" value="${serviceform.partNo[i]}"  onblur="this.value=TrimAll(this.value)" style="width:150px"/>
                                                                <a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPart('partNo${i + 1 }', document.getElementById('partNo${i + 1 }'), document.getElementById('comptype${i + 1 }'), document.getElementById('img${i + 1 }'), '${i + 1 }');"/><img alt=""  id='img${i + 1 }' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>
                                                                <input type="hidden" name="partNumber" id="partNumber${i + 1 }" value=""></td>
                                                            <td align="left"><input name="partDesc" type="text" id="partDesc${i + 1 }" value="${serviceform.partDesc[i]}" readOnly="readonly"  onblur="this.value=TrimAll(this.value)"/>
                                                                <%--<a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPartDesc('partDesc${i + 1 }', document.getElementById('partDesc${i + 1 }'), document.getElementById('comptype${i + 1 }'), document.getElementById('image${i + 1 }'), '${i + 1 }');"/><img alt=""  id='image${i + 1 }' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>--%>
                                                            </td>

                                                            <td align="left"><input name="unitPrice" type="text" class="AmountTxtBox" readonly id="unitPrice${i + 1 }" value="${serviceform.unitPrice[i]}" /></td>
                                                            <td align="left"><input name="quantityS" type="text" id="quantityS${i + 1 }" maxlength="10" value="${serviceform.quantityS[i]}" onkeypress="return isNumberKeySpares(event);"  onblur="partAmountTotal('quantityS${i + 1 }', 'unitPrice${i + 1 }', 'partPriceAmount${i + 1 }', ${i + 1 });" /></td>
                                                            <td align="left"><input name="partPriceAmount" type="text" value="${serviceform.partPriceAmount[i]}" class="AmountTxtBox" id="partPriceAmount${i + 1 }" value="" readonly/></td>
                                                            <td align="left"><select name="billCode"   id="billCode${i + 1 }" onchange="onChangeBillToFunc(this, ${i + 1 });CauseAndConquences(${i + 1 })">
                                                                    <option value="">--Select--</option>
                                                                    <c:forEach items="${billList}" var="dataList">
                                                                        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                                    </c:forEach>
                                                                </select></td>
                                                            <td align="left"><input name="warranty" type="text" id="warranty${i + 1 }" value="0" maxlength="5" readonly onfocus="checkBlank(this, ${i + 1 })" onkeypress="return numeralsOnly(event, getElementById('warranty${i + 1 }'))" onblur="validateFloat(this);billToTotal('partPriceAmount${i + 1 }', ${i + 1 });"/></td>
                                                            <td align="left"><input name="finalAmount" type="text" class="AmountTxtBox"  id="finalAmount${i + 1 }" readonly/></td>

                                                </tr>

                                                <c:set var="i" value="${i + 1}" scope="page"/>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${serviceform.stndprt eq 'NO'  or fn:length(serviceform.comptype) eq 0}">
                                            <tr>
                                                <%--onblur="getPartPrice(document.getElementById('partNo1'),1);"--%>
                                            <input type="hidden" name="comptype" id="comptype1" value="PRT">
                                            <c:if test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                <td align="center" width="3%"><input type="checkbox" name="sparesRowCheckbox" class="sparesRowCheckbox" value="1" /></td>
                                            </c:if>
                                            <td align="left" ><input name="partNo" type="text" class="partnumber" id="partNo1" onblur="this.value=TrimAll(this.value)" />&nbsp;<img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPart('partNo1', document.getElementById('partNo1'), document.getElementById('comptype1'), document.getElementById('img1'), '1');"/><img alt=""  id='img1' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/><input type="hidden" name="partNumber" id="partNumber1" value=""></td>
                                            <td align="left"><input name="partDesc" type="text" class="partdescription" id="partDesc1"  readOnly="readonly" onblur="this.value=TrimAll(this.value)"/><%--<a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPartDesc('partDesc1', document.getElementById('partDesc1'), document.getElementById('comptype1'), document.getElementById('image1'), '1');"/><img alt=""  id='image1' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>--%></td>
                                                <%--<td>
                                                                    <select name="complaint_Code"   id=complaint_Code1 >
                                                                        <option value="">--Select--</option>
                                                                        <c:forEach items="${complaintCodeList}" var="dataList">
                                                                            <c:if test="${serviceform.complaintCode_str eq dataList.value}">
                                                                                <option value='${dataList.value}' title='${dataList.label} [ ${dataList.value} ]' selected>${dataList.label}</option>
                                                                            </c:if>
                                                                            <c:if test="${serviceform.complaintCode_str ne dataList.value}">
                                                                                <option value='${dataList.value}' title='${dataList.label} [ ${dataList.value} ]'>${dataList.label}</option>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td><td>

                                                    <select name="causal_Code"   id=causal_Code1 >
                                                  <option value="">--select--</option>
                                                  <option value="Causal">Causal</option>
                                                  <option value="Consequential">Consequential</option>
                                                  </select>
                                                    <!--<a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPartDesc('partDesc${i + 1 }', document.getElementById('partDesc${i + 1 }'), document.getElementById('comptype${i + 1 }'), document.getElementById('image${i + 1 }'), '${i + 1 }');"/><img alt=""  id='image${i + 1 }' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>-->
                                                </td>--%>
                                            <td align="left"><input name="unitPrice" type="text" class="qty" readonly id="unitPrice1"  /></td>
                                            <td align="left"><input name="quantityS" type="text" class="qty" id="quantityS1" maxlength="10" onkeypress="return isNumberKeySpares(event);"  onblur="partAmountTotal('quantityS1', 'unitPrice1', 'partPriceAmount1', 1);" /></td>
                                            <td align="left"><input name="partPriceAmount" type="text" class="qty" id="partPriceAmount1" value="" readonly/></td>
                                            <td align="left"><select name="billCode"   class="bill" id="billCode1" onchange="onChangeBillToFunc(this, 1);CauseAndConquences(1)">
                                                    <%--<option value="">--Select--</option>--%>
                                                    <option value="">--select--</option>
                                                    <c:forEach items="${billList}" var="dataList">
                                                        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                    </c:forEach>
                                                </select></td>
                                            <td align="left"><input name="warranty" type="text" class="qty" id="warranty1" value="0" maxlength="5" readonly  onfocus="checkBlank(this, 1)" onkeypress="return numeralsOnly(event, getElementById('warranty1'))" onblur="validateFloat(this);billToTotal('partPriceAmount1', 1);"/></td>
                                            <td align="left"><input name="finalAmount" class="qty"  type="text"  id="finalAmount1" readonly/></td>

                                            </tr>
                                            <tr>
                                                <c:if test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                    <td align="center" width="3%"><input type="checkbox" name="sparesRowCheckbox" class="sparesRowCheckbox" value="2" /></td>
                                                </c:if>
                                                <td align="left"><input name="partNo" type="text" id="partNo2" class="partnumber" onblur="this.value=TrimAll(this.value)" />&nbsp;<img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions' border='0'  onclick="javascript:getSuggestionsPart('partNo2', document.getElementById('partNo2'), document.getElementById('comptype2'), document.getElementById('img2'), '2');"/><img alt=""  id='img2' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif' /><input type="hidden" name="partNumber" id="partNumber2" value=""></td>
                                                <td align="left"><input name="partDesc" type="text" id="partDesc2" class="partdescription" readOnly="readonly" maxlength="250"  onblur="this.value=TrimAll(this.value)"/><%--<a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPartDesc('partDesc2', document.getElementById('partDesc2'), document.getElementById('comptype2'), document.getElementById('image2'), '2');"/><img alt=""  id='image2' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>--%><input type="hidden" name="comptype" id="comptype2" value="PRT"></td>
                                                    <%--<td>
                                                                    <select name="complaint_Code"   id=complaint_Code2 >
                                                                        <option value="">--Select--</option>

                                                                        <c:forEach items="${complaintCodeList}" var="dataList">
                                                                            <c:if test="${serviceform.complaintCode_str eq dataList.value}">
                                                                                <option value='${dataList.value}' title='${dataList.label} [ ${dataList.value} ]' selected>${dataList.label}</option>
                                                                            </c:if>
                                                                            <c:if test="${serviceform.complaintCode_str ne dataList.value}">
                                                                                <option value='${dataList.value}' title='${dataList.label} [ ${dataList.value} ]'>${dataList.label}</option>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td><td>

                                                    <select name="causal_Code"   id=causal_Code2 >
                                                   <option value="">--select--</option>
                                                  <option value="Causal">Causal</option>
                                                  <option value="Consequential">Consequential</option>
                                                  </select>
                                                    <!--<a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPartDesc('partDesc${i + 1 }', document.getElementById('partDesc${i + 1 }'), document.getElementById('comptype${i + 1 }'), document.getElementById('image${i + 1 }'), '${i + 1 }');"/><img alt=""  id='image${i + 1 }' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>-->
                                                </td>--%>
                                                <td align="left"><input name="unitPrice" type="text" class="qty" readonly id="unitPrice2"/></td>
                                                <td align="left"><input name="quantityS" type="text" class="qty" id="quantityS2" maxlength="10" onkeypress="return isNumberKeySpares(event);"  maxlength="15" onblur="partAmountTotal('quantityS2', 'unitPrice2', 'partPriceAmount2', 2);" /></td>
                                                <td align="left"><input name="partPriceAmount" type="text" class="qty" class="AmountTxtBox" id="partPriceAmount2" readonly /></td>
                                                <td align="left"><select name="billCode"   id="billCode2" class="bill" onchange="onChangeBillToFunc(this, 2);CauseAndConquences(2)">
                                                        <option value="">--select--</option>
                                                        <%--<option value="">--Select--</option>--%>
                                                        <c:forEach items="${billList}" var="dataList">
                                                            <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                        </c:forEach>
                                                    </select></td>
                                                <td align="left"><input name="warranty" type="text" class="qty"  maxlength="5" id="warranty2" value="0" readonly   onfocus="checkBlank(this, 2)" onkeypress="return numeralsOnly(event, getElementById('warranty2'))"  onblur="validateFloat(this);billToTotal('partPriceAmount2', 2);"/></td>
                                                <td align="left"><input name="finalAmount" type="text" class="qty" id="finalAmount2" readonly/></td>
                                            </tr>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>

                                </tbody>
                                <tr>
                                    <%if (userFunctionalities.contains("608")) {%><c:if test="${serviceform.status eq 'OPEN'}">
                                        <c:choose>
                                            <c:when test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                <td align="left" colspan="10"><label><input name="Button" type="button" value="Add" onclick="addRowSpares('myTable')"/>
                                                        <input name="Button" type="button" value="Delete Selected" onclick="deleteSparesRow('myTable')" />
                                                    </label>
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td align="left" colspan="9"><label><input name="Button" type="button" value="Add" onclick="addRowSpares('myTable')"/></label></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if><%}%>
                                </tr>
                                <tr height="4">
                                    <c:choose>
                                        <c:when test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                            <td align="left" colspan="10" height="4"><label style="color: red;font-size:10px " ><B> <bean:message key="label.common.zeroqtypart" /></B></label></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td align="left" colspan="9" height="4"><label style="color: red;font-size:10px " ><B> <bean:message key="label.common.zeroqtypart" /></B></label></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </table>
                    </td>

                </tr>
                <tr>
                    <td colspan="2" valign="top">
                        <table id="myTableLubes" width="100%" cellpadding="2" cellspacing="4" class="LiteBorder">
                            <tbody>
                                <tr class="formCatHeading1">
                                    <c:choose>
                                        <c:when test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                            <td colspan="10" align="center"><bean:message key="label.common.lubes" /></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td colspan="9" align="center"><bean:message key="label.common.lubes" /></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                                <tr>
                                    <c:if test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                        <td align="left" class="tdnew" width="3%"> <label><input type="checkbox" id="selectAllLubes" onclick="selectAllLubesRows(this);" title="Select All"/></label></td>
                                    </c:if>
                                    <td align="left" class="tdnew" > <label><bean:message key="label.common.partno" /></label></td>
                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.partdesc" /></label></td>
                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.unitprice" /></label></td>
                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.qty" /></label></td>
                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.amount" /></label></td>
                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.billto" /></label></td>
                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.discount" />(%)</label></td>
                                    <td align="left" class="tdnew"> <label><bean:message key="label.common.finalamount" /></label></td>
                                </tr>
                                <c:choose>
                                    <c:when test="${serviceform.jobcardview eq 'true' and fn:length(lubesList) gt 0}">
                                        <c:if test="${!empty lubesList }">
                                            <c:set var="sno" value="1"></c:set>
                                            <c:forEach items="${lubesList}" var="lubesList">
                                            
               


                                                <tr><input type="hidden" name="lubesComptype"  id="lubesComptype${sno}" value="LUBES">
                                            <c:if test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                <td align="center" width="3%"><input type="checkbox" name="lubesRowCheckbox" class="lubesRowCheckbox" value="${sno}" /></td>
                                            </c:if>
                                            <td align="left" ><input name="lubesNo"  type="text" class="partnumber" id="lubesNo${sno}" readOnly="readonly" value="${lubesList.partNo_str}" onblur="this.value=TrimAll(this.value)" /><a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick=""/><img alt=""  id='imgLubes${sno}' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/><input type="hidden" name="lubesPartNumber" id="lubesPartNumber${sno}" value=""></td>
                                                    <td align="left"><input name="lubesDesc" type="text" class="partdescription" id="lubesDesc${sno}" readOnly="readonly" value="${lubesList.partDesc_str}"    onblur="this.value=TrimAll(this.value)"/><%--<a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsLubesPartDesc('lubesDesc${sno}', document.getElementById('lubesDesc${sno}'), document.getElementById('lubesComptype${sno}'), document.getElementById('imageLubes${sno}'), '${sno}');"/><img alt=""  id='imageLubes${sno}' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>--%></td>
                                                    <td align="left"><input name="lubesUnitPrice" type="text" class="qty"  readonly id="lubesUnitPrice${sno}" value="${lubesList.unitprice_str}" /></td>
                                                    <td align="left"><input name="lubesQuantityS" type="text" class="qty" id="lubesQuantityS${sno}" value="${lubesList.qty_str}" maxlength="10" onkeypress="return isNumberKey(event)"  onblur="validateFloat(this);lubesAmountTotal('lubesQuantityS${sno}', 'lubesUnitPrice${sno}', 'lubesPriceAmount${sno}', ${sno});" /></td>
                                                     <!-- adding by annpurna -->
                                                     <input type="hidden" id="partExistsInLubesFlag${sno}" value="" />
                                                      <input type="hidden" id="Moq${sno}" value="" />
                                                   <%--  <input type="hidden" name="moq" id="MOQ" value="<%= request.getAttribute("moqValue") %>"> --%>
                                                    <td align="left"><input name="lubesPriceAmount" class="qty" type="text" class="AmountTxtBox" id="lubesPriceAmount${sno}"value="${lubesList.amount_str}" readonly/></td>
                                                    <td align="left"><select name="lubesBillCode"  class="bill" id="lubesBillCode${sno}" onchange="onChangeLubesBillToFunc(this, ${sno});">
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${billListLubes}" var="dataList">

                                                                <c:set value="${fn:split(dataList.value,'@@')}" var="bill"/>

                                                                <c:if test="${lubesList.billto_str eq  bill[0] }">
                                                                    <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                                                </c:if>
                                                                <c:if test="${lubesList.billto_str ne bill[0] }">
                                                                    <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>
                                                                </c:if>

                                                            </c:forEach>

                                                        </select></td>
                                                    <td  align="left"><input name="lubesWarranty" type="text" class="qty" id="lubesWarranty${sno}" value="${lubesList.discount_str}" maxlength="5" readonly  onkeypress="return numeralsOnly(event, getElementById('lubesWarranty${sno}'))" onblur="validateFloat(this);lubesBillToTotal('lubesPriceAmount${sno}', ${sno});"/></td>
                                                    <td  align="left"><input name="lubesFinalAmount" type="text" class="qty"  id="lubesFinalAmount${sno}" value="${lubesList.finalamt_str}"readonly/></td>
                                                    </tr>

                                                    <c:set var="sno" value="${sno + 1 }" />
                                                </c:forEach>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="j" value="0" scope="page" />
                                            <c:if test="${!empty serviceform.lubesComptype and fn:length(serviceform.lubesComptype) gt 0}">
                                                <c:forEach items="${serviceform.lubesComptype}" var="dataList">
                                                    <tr ><input type="hidden" name="lubesComptype" id="lubesComptype${j+1}" value="LUBES">
                                                    <c:if test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                        <td align="center" width="3%"><input type="checkbox" name="lubesRowCheckbox" class="lubesRowCheckbox" value="${j+1}" /></td>
                                                    </c:if>
                                                    <td align="left"><input name="lubesNo" class="partnumber" type="text" id="lubesNo${j+1}" value="${serviceform.lubesNo[j]}"  onblur="this.value=TrimAll(this.value)"/>&nbsp;<%--<a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsLubesPart('lubesNo${j+1}', document.getElementById('lubesNo${j+1}'), document.getElementById('lubesComptype${j+1}'), document.getElementById('imgLubes${j+1}'), '${j+1}');"/>--%><img alt=""  id='imgLubes${j+1}' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/><input type="hidden" name="lubesPartNumber" id="lubesPartNumber${j+1}" value=""></td>
                                                    <td align="left"><input name="lubesDesc" class="partdescription" type="text" id="lubesDesc${j+1}" value="${serviceform.lubesDesc[j]}" readOnly="readonly"    onblur="this.value=TrimAll(this.value)" /><%--<a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsLubesPartDesc('lubesDesc${j+1}', document.getElementById('lubesDesc${j+1}'), document.getElementById('lubesComptype${j+1}'), document.getElementById('imageLubes${j+1}'), '${j+1}');"/><img alt=""  id='imageLubes${j+1}' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>--%></td>
                                                    <td align="left"><input name="lubesUnitPrice" class="qty" type="text" value="${serviceform.lubesUnitPrice[j]}" class="AmountTxtBox" readonly id="lubesUnitPrice${j+1}"  /></td>
                                                    <td align="left"><input name="lubesQuantityS" class="qty" type="text" value="${serviceform.lubesQuantityS[j]}" id="lubesQuantityS${j+1}" maxlength="10" onkeypress="return isNumberKey(event)"  onblur="validateFloat(this);lubesAmountTotal('lubesQuantityS${j+1}', 'lubesUnitPrice${j+1}', 'lubesPriceAmount${j+1}', ${j+1});" style="width:50px !important "/></td>
                                                    <td align="left"><input name="lubesPriceAmount" class="qty" type="text" value="${serviceform.lubesPriceAmount[j]}" class="AmountTxtBox" id="lubesPriceAmount${j+1}" value="" readonly/></td>
                                                    <td align="left"><select name="lubesBillCode" class="bill"  id="lubesBillCode${j+1}" onchange="onChangeLubesBillToFunc(this, ${j+1});">
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${billListLubes}" var="dataList">
                                                                <c:set value="${fn:split(dataList.value,'@@')}" var="bill"/>

                                                                <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>

                                                            </c:forEach>




                                                        </select></td>
                                                        <input type="hidden" id="partExistsInLubesFlag${j+1}" value="" />
                                                        <input type="hidden" id="Moq${j+1}" value="" />
                                                    <td align="left"><input name="lubesWarranty" class="qty" type="text" id="lubesWarranty${j+1}" value="0" maxlength="5" readonly  onkeypress="return numeralsOnly(event, getElementById('lubesWarranty${j+1}'))" onblur="validateFloat(this);lubesBillToTotal('lubesPriceAmount${j+1}', ${j+1});"/></td>
                                                    <td align="left"><input name="lubesFinalAmount" class="qty" type="text" class="AmountTxtBox"  id="lubesFinalAmount${j+1}" readonly/></td>
                                                    </tr>

                                                    <c:set var="j" value="${j + 1}" scope="page"/>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty serviceform.lubesComptype and fn:length(serviceform.lubesComptype) eq 0}">

                                                <tr><input type="hidden" name="lubesComptype" id="lubesComptype1" value="LUBES">
                                                <c:if test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                    <td align="center" width="3%"><input type="checkbox" name="lubesRowCheckbox" class="lubesRowCheckbox" value="1" /></td>
                                                </c:if>
                                                <td align="left" ><input name="lubesNo" class="partnumber" type="text" id="lubesNo1"  onblur="this.value=TrimAll(this.value)"/>&nbsp;<img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsLubesPart('lubesNo1', document.getElementById('lubesNo1'), document.getElementById('lubesComptype1'), document.getElementById('imgLubes1'), '1');"/><img alt=""  id='imgLubes1' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/><input type="hidden" name="lubesPartNumber" id="lubesPartNumber1" value=""></td>
                                                <td align="left"><input name="lubesDesc" class="partdescription" type="text" id="lubesDesc1" readOnly="readonly"     onblur="this.value=TrimAll(this.value)" /><%--<a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsLubesPartDesc('lubesDesc1', document.getElementById('lubesDesc1'), document.getElementById('lubesComptype1'), document.getElementById('imageLubes1'), '1');"/><img alt=""  id='imageLubes1' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>--%></td>
                                                <td align="left"><input name="lubesUnitPrice" class="qty" type="text" class="AmountTxtBox" readonly id="lubesUnitPrice1"  /></td>
                                                <td align="left"><input name="lubesQuantityS" class="qty" type="text" id="lubesQuantityS1" maxlength="10" onkeypress="return isNumberKey(event)"  onblur="validateFloat(this);lubesAmountTotal('lubesQuantityS1', 'lubesUnitPrice1', 'lubesPriceAmount1', 1);"/></td>
                                                <td align="left"><input name="lubesPriceAmount" type="text" class="qty" id="lubesPriceAmount1" value="" readonly/></td>
                                                <input type="hidden" id="partExistsInLubesFlag1" value="" />
                                                <input type=hidden id="Moq1" value="" />
                                                <td align="left"><select name="lubesBillCode"  class="bill" id="lubesBillCode1" onchange="onChangeLubesBillToFunc(this, 1);">
                                                        <option value="">--Select--</option>
                                                        <c:forEach items="${billListLubes}" var="dataList">
                                                            <c:set value="${fn:split(dataList.value,'@@')}" var="bill"/>

                                                            <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>

                                                        </c:forEach>




                                                    </select></td>
                                                <td align="left"><input name="lubesWarranty" type="text" class="qty" id="lubesWarranty1" value="0" maxlength="5" readonly  onkeypress="return numeralsOnly(event, getElementById('lubesWarranty1'))" onblur="validateFloat(this);lubesBillToTotal('lubesPriceAmount1', 1);"/></td>
                                                <td align="left"><input name="lubesFinalAmount" type="text" class="qty" class="AmountTxtBox"  id="lubesFinalAmount1" readonly/></td>
                                                </tr>
                                                <tr>
                                                    <c:if test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                        <td align="center" width="3%"><input type="checkbox" name="lubesRowCheckbox" class="lubesRowCheckbox" value="2" /></td>
                                                    </c:if>
                                                    <td align="left" ><input name="lubesNo" class="partnumber" type="text" id="lubesNo2" onblur="this.value=TrimAll(this.value);" />&nbsp;<img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions' border='0'  onclick="javascript:getSuggestionsLubesPart('lubesNo2', document.getElementById('lubesNo2'), document.getElementById('lubesComptype2'), document.getElementById('imgLubes2'), '2');"/><img alt=""  id='imgLubes2' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif' /><input type="hidden" name="lubesPartNumber" id="lubesPartNumber2" value=""></td>
                                                    <td align="left"><input name="lubesDesc" type="text" id="lubesDesc2" class="partdescription"  readOnly="readonly" maxlength="250"   onblur="this.value=TrimAll(this.value);"/><%--<a href='#' ><img src='<%=cntxpath%>/image/search.png' alt='Get Suggestions'  border='0'  onclick="getSuggestionsLubesPartDesc('lubesDesc2', document.getElementById('lubesDesc2'), document.getElementById('lubesComptype2'), document.getElementById('imageLubes2'), '2');"/><img alt=""  id='imageLubes2' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>--%><input type="hidden" name="lubesComptype" id="lubesComptype2" value="LUBES"></td>
                                                    <td align="left"><input name="lubesUnitPrice" type="text" class="qty" readonly id="lubesUnitPrice2"/></td>
                                                    <td align="left"><input name="lubesQuantityS" type="text" class="qty" id="lubesQuantityS2" maxlength="10" onkeypress="return isNumberKey(event)"  maxlength="15" onblur="validateFloat(this);lubesAmountTotal('lubesQuantityS2', 'lubesUnitPrice2', 'lubesPriceAmount2', 2);" /></td>
                                                    <td align="left"><input name="lubesPriceAmount" type="text" class="qty" id="lubesPriceAmount2" readonly /></td>
                                                    <td align="left"><select name="lubesBillCode"  class="bill" id="lubesBillCode2" onchange="onChangeLubesBillToFunc(this, 2);">
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${billListLubes}" var="dataList">
                                                                <c:set value="${fn:split(dataList.value,'@@')}" var="bill"/>

                                                                <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>

                                                            </c:forEach>




                                                        </select></td>
                                                        <input type="hidden" id="partExistsInLubesFlag2" value="" />
                                                        <input type=hidden id="Moq2" value="" />
                                                    <td align="left"><input name="lubesWarranty" class="qty" type="text" maxlength="5" id="lubesWarranty2" value="0" readonly  onkeypress="return numeralsOnly(event, getElementById('lubesWarranty2'))"  onblur="validateFloat(this);lubesBillToTotal('lubesPriceAmount2', 2);"/></td>
                                                    <td align="left"><input name="lubesFinalAmount" class="qty" type="text" class="AmountTxtBox" id="lubesFinalAmount2" readonly/></td>
                                                </tr>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>

                                    </tbody>
                                    <tr>
                                        <%if (userFunctionalities.contains("608")) {%><c:if test="${serviceform.status eq 'OPEN'}">
                                            <c:choose>
                                                <c:when test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                    <td align="left" colspan="10"><label><input name="Button" type="button" value="Add" onclick="addRowLubes('myTableLubes')"/>
                                                            <input name="Button" type="button" value="Delete Selected" onclick="deleteLubesRow('myTableLubes')"/>
                                                        </label>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td align="left" colspan="9"><label><input name="Button" type="button" value="Add" onclick="addRowLubes('myTableLubes')"/></label></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if><%}%>
                                    </tr>
                                    <tr height="4">
                                        <c:choose>
                                            <c:when test="${serviceform.poCreated != null && serviceform.poCreated != 'Y'}">
                                                <td align="left" colspan="10" height="4"><label style="color: red;font-size:10px " ><B> <bean:message key="label.common.zeroqtypart" /></B></label></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td align="left" colspan="9" height="4"><label style="color: red;font-size:10px " ><B> <bean:message key="label.common.zeroqtypart" /></B></label></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <table id="myTableLabour" width="100%" border="0" align="center" cellpadding="2" cellspacing="4" class="LiteBorder"  >
                            <tbody>

                                <tr class="formCatHeading">
                                    <td colspan="3" align="center"><bean:message key="label.common.labourcharge" /></td>
                                </tr>
                                <tr>
                                    <td class="tdnew" align="left" >
                                        <label><bean:message key="label.common.selectcomplaint" /></label></td>
                                    <td class="tdnew" align="left" ><label><bean:message key="label.common.actiontaken" /></label></td>
                                    <td class="tdnew" align="left">
                                        <label><bean:message key="label.common.amount" /></label><input type="hidden" name="complainhrs" id="complainhrs"></td>

                                </tr>
                                <c:if test="${!empty labourchargeList  }">
                                    <c:set var="sno" value="1"></c:set>
                                    <c:forEach items="${labourchargeList}" var="labourchargeList">
                                        <tr>
                                            <td align="left" >
                                                <c:set var="spliteval" value="${fn:split(labourchargeList.complaintCode_str,'@@')}"/>
                                                <input name="complaintCodevalue" class="partdescription"  readonly="true" type="text" id="complaintCodevalue${sno}" value="${spliteval[0]}"   />
                                                <input name="complaintCode"  type="hidden" id="complaintCode${sno}" value="${spliteval[1]}"   />


                                            </td>

                                            <td align="left">
                                                <c:if test="${serviceForm.constantValue eq 'Yes'}" >
                                                    <div id="Complaint${sno}">
                                                        <select name="actionCode" id="actionCode${sno}" onchange="getServiceHrsAjax(this, 'Action_Taken', ${sno});" >
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${actionTakenList}" var="dataList">
                                                                <c:if test="${labourchargeList.actionTaken_str eq dataList.value}">
                                                                    <option value='${dataList.value}' title='${dataList.label} [ ${dataList.value} ]' selected>${dataList.label}</option>
                                                                </c:if>
                                                                <c:if test="${labourchargeList.actionTaken_str ne dataList.value}">
                                                                    <option value='${dataList.value}' title='${dataList.label} [ ${dataList.value} ]'>${dataList.label}</option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </c:if>
                                                <c:if test="${serviceForm.constantValue eq 'No'}">
                                                    <c:set var="actionval" value="${fn:split(labourchargeList.actionTaken_str,'@@')}"/>
                                                    <input name="actionCode" type="text" id="actionCode${sno}" class="partnumber"  value="${actionval[0]}"   onkeypress="return CommentsMaxLength(this, 200);"  maxlength="200" />
                                                </c:if>
                                            </td>
                                            <td align="left">

                                                <c:if test="${serviceForm.constantValue eq 'Yes'}">
                                                    <div id="Action_Taken${sno}"><input type="text" name="labourChargesAmount" id="labourChargesAmount${sno}" value="${labourchargeList.labourChargesAmount_str}" class="qty"  /></div>
                                                    </c:if>
                                                    <c:if test="${serviceForm.constantValue eq 'No'}">
                                                    <div id="Action_Taken${sno}"><input type="text"  name="labourChargesAmount" value="${labourchargeList.labourChargesAmount_str}"  id="labourChargesAmount${sno}" class="qty" maxlength="15" value="0.0" onkeypress="return numeralsOnly(event, getElementById('labourChargesAmount1'))" onblur="validateFloat(this);
                                                        labourChargesAmt();"/></div>
                                                    </c:if>
                                            </td>
                                        </tr>
                                        <c:set var="sno" value="${sno + 1 }" />
                                    </c:forEach>
                                </c:if>

                                <c:if test="${empty labourchargeList  }">
                                    <tr><td colspan="3" align="center">&nbsp;</td></tr>
                                    <tr><td colspan="3" align= "center" style="color: red;"><bean:message key="label.common.complaintnotfound" /></td></tr>
                                </c:if>


                            </tbody>

                        </table></td>
                    <td valign="top" width="49%">
                        <table id="myTableCharges" width="100%" cellpadding="2" cellspacing="4" class="LiteBorder"><tbody>
                                <tr class="formCatHeading">
                                    <td colspan="4" align="center"><bean:message key="label.common.othercharges" /></td>
                                </tr>
                                <tr>
                                    <td align="left" class="tdnew"><label><bean:message key="label.common.work" /></label></td>
                                    <td align="left" class="tdnew"><label><bean:message key="label.common.description" /></label></td>
                                    <td align="left" class="tdnew"><label><bean:message key="label.common.amount" /></label></td>
                                    <td ><label></label></td>
                                </tr>
                                <c:choose>
                                    <c:when test="${serviceform.jobcardview eq 'true' and fn:length(otherchargeList) gt 0}">
                                        <c:if test="${!empty otherchargeList  }">
                                            <c:set var="sno" value="1"></c:set>
                                            <c:forEach items="${otherchargeList}" var="otherchargeList">
                                                <tr>
                                                    <td align="left">
                                                        <select name="workCode"   id=workCode${sno}" onchange="clenAmountValue('workDescription1', 'workAmount1', ${sno});">
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${jobWorkList}" var="dataList">
                                                                <c:if test="${otherchargeList.workCode_str eq dataList.value}">
                                                                    <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                                                </c:if>
                                                                <c:if test="${otherchargeList.workCode_str eq dataList.value}">
                                                                    <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                                </c:if>

                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td align="left">
                                                        <input name="workDescription" type="text" class="partdescription" id="workDescription${sno}" value="${otherchargeList.workDescription_str}" onkeypress="return CommentsMaxLength(this, 200);" onblur="this.value = TrimAll(this.value);
                                                            stringOnly(this);" maxlength="200" />
                                                    </td>
                                                    <td align="left"><input name="workAmount" type="text" class="qty" id="workAmount${sno}" value="${otherchargeList.workAmount_str}" maxlength="15" onkeypress="return numeralsOnly(event, getElementById('workAmount${sno}'))"  onBlur="totalCharges('workAmount${sno}', ${sno});
                                                        validateFloat(this)"/></td>
                                                    <td align="left" ><input type="hidden" name="selectedFilesHid" id="selectedFilesHid${sno}"></td>
                                                </tr>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </c:forEach>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td align="left">
                                                <select name="workCode"   id=workCode1" onchange="clenAmountValue('workDescription1', 'workAmount1', 1);">
                                                    <option value="">--Select--</option>
                                                    <c:forEach items="${jobWorkList}" var="dataList">
                                                        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td align="left">
                                                <input name="workDescription" type="text" id="workDescription1"  onkeypress="return CommentsMaxLength(this, 200);" onblur="this.value = TrimAll(this.value);
                                                    stringOnly(this);" maxlength="200" class="partdescription"/>
                                            </td>
                                            <td align="left"><input name="workAmount" type="text" class="qty" id="workAmount1" maxlength="15" onkeypress="return numeralsOnly(event, getElementById('workAmount1'))"  onBlur="totalCharges('workAmount1', 1);
                                                validateFloat(this)"/></td>
                                            <td align="left" ><input type="hidden" name="selectedFilesHid" id="selectedFilesHid1"></td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                <select name="workCode"   id=workCode2" onchange="clenAmountValue('workDescription2', 'workAmount2', 2);">
                                                    <option value="">--Select--</option>
                                                    <c:forEach items="${jobWorkList}" var="dataList">
                                                        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td align="left">
                                                <input name="workDescription" type="text" id="workDescription2" maxlength="200" class="partdescription" onkeypress="return CommentsMaxLength(this, 200);" onblur="this.value = TrimAll(this.value);
                                                    stringOnly(this);" />
                                            </td>
                                            <td align="left"><input name="workAmount" type="text" class="qty" id="workAmount2" maxlength="15" onkeypress="return numeralsOnly(event, getElementById('workAmount2'))" onBlur="totalCharges('workAmount2', 2);
                                                validateFloat(this)"/></td> <!-- this.value=formatCurrency(this.value); -->
                                            <td align="left"><input type="hidden" name="selectedFilesHid" id="selectedFilesHid2"></td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>


                            </tbody>
                            <tr>
                                <%if (userFunctionalities.contains("608")) {%><c:if test="${serviceform.status eq 'OPEN'}">
                                    <td align="left" colspan="3"><label>
                                            <input name="input2" type="button" value="ADD" onclick="addRowCharges('myTableCharges')"/>
                                            <input name="Button" type="button" value="Delete Selected" onclick="deleteRowCharges('myTableCharges')"/></label>

                                    </td>
                                </c:if><%}%>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td valign="top"><table width="100%" border="0" align="center" cellpadding="2" cellspacing="4" class="LiteBorder"  >
                            <tbody>
                                <tr class="formCatHeading">
                                    <td colspan="4" align="center"> <label> <bean:message key="label.common.estimate" /></label></td>
                                </tr>
                                <tr>
                                    <td   align="right" >
                                        <label><bean:message key="label.common.totalpartvalue" /></label></td>
                                    <td  align="left" ><input name="totalPartsValue" type="text" class="qty" id="totalPartsValue" value="${serviceform.totalPartsValue}" readonly>
                                    <td  align="center" ><strong><bean:message key="label.common.totalestimate" />
                                        </strong>
                                </tr>
                                <tr>
                                    <td align="right" >
                                        <label><bean:message key="label.common.totallubesvalue" /></label></td>
                                    <td align="left" ><input name="totalLubesValue" type="text" class="qty" id="totalLubesValue" value="${serviceform.totalLubesValue}"  readonly="true" onblur="this.value = formatCurrency(this.value);"/>
                                    <td rowspan="2" align="center" valign="top"><input name="totalEstimate" type="text" class="qty" id="totalEstimate" value="${serviceform.totalEstimate}" onblur="this.value = formatCurrency(this.value);
                                        estimateTotalValue();" readonly/></td>
                                </tr>
                                <tr>
                                    <td align="right"><label><bean:message key="label.common.totallabourcharge" /></label></td>
                                    <td align="left"><input name="totalLabourCharges" type="text" class="qty" id="totalLabourCharges" value="${serviceform.totalLabourCharges}"  readonly onblur="this.value = formatCurrency(this.value);"/></td>
                                </tr>
                                <tr>
                                    <td align="right" ><label><bean:message key="label.common.totalothercharge" /></label></td>
                                    <td align="left" ><input name="totalOtherCharges" type="text" class="qty" id="totalOtherCharges" value="${serviceform.totalOtherCharges}" readonly onblur="this.value = formatCurrency(this.value);"/></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                    <td valign="top">
                        <table width="100%" border="0" align="center" cellpadding="2" cellspacing="4" class="LiteBorder"  >
                            <tbody>
                                <tr class="formCatHeading">
                                    <td colspan="3" align="center"><bean:message key="label.common.deliverytime" /></td>
                                </tr>
                                <tr>
                                    <td  align="right" >
                                        <label><bean:message key="label.common.promised" /></label></td>
                                    <td align="left" > <input name="promised" type="text" id="promised" class="datepicker" readOnly="readonly" value="${serviceform.promised}">
                                        <select name="promisedTime"  id="promisedTime" class="hrs">
                                            <c:forEach var="i" begin="0" end="23">

                                                <c:if test="${i lt 10 }">
                                                    <c:set var="temp" value="0${i}"></c:set>
                                                    <c:if test="${serviceform.promisedHrs eq temp}">
                                                        <option value="0${i}" selected>0${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.promisedHrs ne temp}">
                                                        <option value="0${i}">0${i}</option><c:out value="${('0')+i}"/>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${i gt 10 or i eq 10}">

                                                    <c:if test="${serviceform.promisedHrs eq i }">
                                                        <option value="${i}" selected>${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.promisedHrs ne i }">
                                                        <option value="${i}" >${i}</option>
                                                    </c:if>
                                                </c:if>

                                            </c:forEach>
                                        </select>
                                        :
                                        <select name="promisedHrs"  id="promisedHrs" class="hrs">
                                            <c:set var="i" value="0"></c:set>
                                            <c:forEach begin="0" end="11">
                                                <c:set var="temp1" value="0${i}"></c:set>
                                                <c:if test="${i lt 10 }">
                                                    <c:if test="${serviceform.promisedTime eq temp1 }">
                                                        <option value="0${i}" selected>0${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.promisedTime ne temp1 }">
                                                        <option value="0${i}">0${i}</option>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${i gt 10 or i eq 10}">
                                                    <c:if test="${serviceform.promisedTime eq i }">
                                                        <option value="${i}" selected>${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.promisedTime ne i }">
                                                        <option value="${i}" >${i}</option>
                                                    </c:if>
                                                </c:if>
                                                <c:set var="i" value="${i + 5 }" />
                                            </c:forEach>
                                        </select>
                                </tr>
                                <tr>
                                    <td align="right"><label> <bean:message key="label.common.reqbycust" /></label></td>
                                    <td align="left">
                        <input name="requiredBYCustomer" id="requiredBYCustomer" type="text" readOnly="readonly" class="datepicker" value="${serviceform.requiredBYCustomer}"/>
                                        <select name="requiredBYCustomerTime" id="requiredBYCustomerTime" class="hrs" >
                                            <c:forEach var="i" begin="0" end="23" >

                                                <c:if test="${i lt 10 }">
                                                    <c:set var="temp" value="0${i}"></c:set>
                                                    <c:if test="${serviceform.requiredBYCustomerHrs eq temp}">
                                                        <option value="0${i}" selected>0${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.requiredBYCustomerHrs ne temp}">
                                                        <option value="0${i}">0${i}</option><c:out value="${('0')+i}"/>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${i gt 10 or i eq 10}">

                                                    <c:if test="${serviceform.requiredBYCustomerHrs eq i }">
                                                        <option value="${i}" selected>${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.requiredBYCustomerHrs ne i }">
                                                        <option value="${i}" >${i}</option>
                                                    </c:if>
                                                </c:if>

                                            </c:forEach>
                                        </select>
                                        :
                                        <select name="requiredBYCustomerHrs" id="requiredBYCustomerHrs" class="hrs" >
                                            <c:set var="i" value="0"></c:set>
                                            <c:forEach begin="0" end="11">
                                                <c:set var="temp1" value="0${i}"></c:set>
                                                <c:if test="${i lt 10 }">
                                                    <c:if test="${serviceform.requiredBYCustomerTime eq temp1 }">
                                                        <option value="0${i}" selected>0${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.requiredBYCustomerTime ne temp1 }">
                                                        <option value="0${i}">0${i}</option>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${i gt 10 or i eq 10}">
                                                    <c:if test="${serviceform.requiredBYCustomerTime eq i }">
                                                        <option value="${i}" selected>${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.requiredBYCustomerTime ne i }">
                                                        <option value="${i}" >${i}</option>
                                                    </c:if>
                                                </c:if>
                                                <c:set var="i" value="${i + 5 }" />
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td  align="right" ><label><bean:message key="label.common.estimatedate" /></label></td>
                    <td align="left" ><input name="currentEstimate" id="currentEstimate" type="text" readOnly="readonly" class="datepicker" value="${serviceform.currentEstimate}"/>
                                        <select name="currentEstimateTime"  id="currentEstimateTime" class="hrs" >
                                            <c:forEach var="i" begin="0" end="23" >

                                                <c:if test="${i lt 10 }">
                                                    <c:set var="temp" value="0${i}"></c:set>
                                                    <c:if test="${serviceform.currentEstimateHrs eq temp}">
                                                        <option value="0${i}" selected>0${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.currentEstimateHrs ne temp}">
                                                        <option value="0${i}">0${i}</option><c:out value="${('0')+i}"/>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${i gt 10 or i eq 10}">

                                                    <c:if test="${serviceform.currentEstimateHrs eq i }">
                                                        <option value="${i}" selected>${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.currentEstimateHrs ne i }">
                                                        <option value="${i}" >${i}</option>
                                                    </c:if>
                                                </c:if>

                                            </c:forEach>
                                        </select>
                                        :
                                        <select name="currentEstimateHrs" id="currentEstimateHrs" class="hrs" >
                                            <c:set var="i" value="0"></c:set>
                                            <c:forEach begin="0" end="11">
                                                <c:set var="temp1" value="0${i}"></c:set>
                                                <c:if test="${i lt 10 }">
                                                    <c:if test="${serviceform.currentEstimateTime eq temp1 }">
                                                        <option value="0${i}" selected>0${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.currentEstimateTime ne temp1 }">
                                                        <option value="0${i}">0${i}</option>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${i gt 10 or i eq 10}">
                                                    <c:if test="${serviceform.currentEstimateTime eq i }">
                                                        <option value="${i}" selected>${i}</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.currentEstimateTime ne i }">
                                                        <option value="${i}" >${i}</option>
                                                    </c:if>
                                                </c:if>
                                                <c:set var="i" value="${i + 5 }" />
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr height="30">
                                    <td height="30" align="right" colspan="3">&nbsp;</td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr align="right">  <%if (userFunctionalities.contains("608")) {%><c:if test="${serviceform.status eq 'OPEN'}">
                        <td colspan="2" > <input name="input" type="button" value="Save"  onclick="validate()"/>
                        </c:if> <%}%>
                        &nbsp; <a href="#" ><img src='<%=cntxpath%>/image/print-icon.png'  name="img1" border='0' title="print"  onclick="printList()"/></a>
                    </td>
                </tr>
            </table>
            </form>
            </td>
            </tr>
            </table>
        </div></div></center>
<!--                <script>estimateTotalValue()</script>-->