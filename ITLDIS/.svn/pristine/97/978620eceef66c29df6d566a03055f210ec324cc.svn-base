<%--
    Document   : v_complaint.jsp
    Created on : May 01, 2014, 03:25:09 PM
    Author     : Avinash.Pandey
--%>

<%@page import="beans.serviceForm"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ page import="java.util.*" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="layout/css/login.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" media="screen" />
<link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Master_290414.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"  type="text/css"/>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<head ><meta http-equiv="content-type" content="application" charset="UTF-8" />

    <script language="JavaScript" src='${pageContext.request.contextPath}/js/jquery-ui-1.9.2.min.js'></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ui.dropdownchecklist.standalone.css"  type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.dropdownchecklist-1.4-min.js"></script>
    <script type="text/javascript"src="${pageContext.request.contextPath}/js/Master_290414.js"></script>
    <script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
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
    <script type="text/javascript" language="javascript">

        function CustomerComplainArea()
        {
            $('.customerComplainArea').each(function(idx,val){
                val.value = val.value.replace(/\s+/g, ' ');
            });

        }
        function ForemanObservationArea()
        {
            $('.foremanObservationArea').each(function(idx,val){
                val.value = val.value.replace(/\s+/g, ' ');
            });

        }
        <c:set var="rownum" value="${fn:length(serviceform.compVerbatim)}" scope="page" />
            rownum=${rownum};

            $(document).ready(function() {

                if(rownum!=null && rownum!=""){
                    for(i=1;i<=rownum;i++)
                    {
                        $("#consequenceCode"+i).dropdownchecklist({ maxDropHeight: "Please select..",width: 100  });
                    }

                }
            });


            function CommentsMaxLength(text,maxLength)
            {
                var diff =  maxLength-text.value.length ;

                if (diff < 0){
                    text.value = text.value.substring(0,maxLength);
                    diff=0;
                    alert(obs_validation_msg + maxLength + "");
                    text.focus();
                }
                else{document.getElementById("msg_saveFAILED").innerHTML = "";}
                //
            }

            var contextPath = '${pageContext.request.contextPath}';
            function GetXmlHttpObject()
            {
                var objXmlHttp = null;
                if (navigator.userAgent.indexOf('Opera') >= 0)
                {
                    var xmlHttp=new XMLHttpRequest();
                    return xmlHttp;
                }
                if (navigator.userAgent.indexOf('MSIE') >= 0)
                {
                    var strName = 'Msxml2.XMLHTTP';
                    if (navigator.appVersion.indexOf('MSIE 5.5') >=0)
                    {
                        strName = 'Microsoft.XMLHTTP';
                    }
                    try
                    {
                        objXmlHttp = new ActiveXObject(strName);
                        // objXmlHttp.onreadystatechange = handler;
                        return objXmlHttp;
                    }
                    catch(e)
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

            function getCommonCode(obj,nameObj,row)
            {
                var objjCode = obj.value;
                var strURL="<%=cntxpath%>/data_master/getCompCodeListAjax.jsp?objjCode=" + objjCode+"&nameObj="+nameObj+"&rowCount="+row;
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")

                    {
                        res = trim(xmlHttp.responseText);
                    
                        document.getElementById(nameObj+row).innerHTML=res;

                        // if(nameObj=="Causal_Code"){
                        //   $("#consequenceCode"+row).dropdownchecklist({ maxDropHeight: "Please select..",width: 100  });

                        //  }


        <%--if(nameObj=="Agg_Code")
            getSubAggregateCode(obj,"subAgg_Code",row);
    }--%>
                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
        }


        function getSubAggregateCode(obj,nameObj,row)
        {
            var objjCode = document.getElementById("aggCode"+row).value//obj.value;aggCode

            //alert(objjCode)
            var strURL="<%=cntxpath%>/data_master/getCompCodeListAjax.jsp?objjCode=" + objjCode+"&nameObj="+nameObj+"&rowCount="+row;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")

                {
                    res = trim(xmlHttp.responseText);

                    document.getElementById(nameObj+row).innerHTML=res;

                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
        }





        var count = "2";
        tt=0;
        function addRowC(in_tbl_name,c)
        {
            if(tt==0 && c>0){
                count=c+1;
                tt++;
            }
        <%--if(${fn:length(serviceform.compVerbatim)}>0){
            
            var oRows = document.getElementById(in_tbl_name).getElementsByTagName('tr');
            //var rowLength=oRows.length+4;
            alert(rowLength);
            count=eval(rowLength)/8;
            count=count+1;
            
        }--%>

                //count=eval(count)+1;
                var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];

                var row = document.createElement("TR");
                var td1 = document.createElement("TD");
                td1.height="5";
                td1.colSpan='6';
                td1.align="right";
                var strHtml1 = "&nbsp;";
                td1.innerHTML = strHtml1.replace(/!count!/g,count);
                row.appendChild(td1);
                //alert(count)
                var row1 = document.createElement("TR");
                var td2 = document.createElement("TD")
                td2.align="right";
        
                var strHtml2 = "<strong><bean:message key="label.common.customercomplaint" /> "+count+" <br><br><bean:message key="label.common.verbatim" /></strong>&nbsp;<span class=\"mandatory\">*</span>";
                td2.innerHTML = strHtml2.replace(/!count!/g,count);

   
                row1.appendChild(td2);


                var td3 = document.createElement("TD")
                td3.colSpan='2';
                td3.align="left";
                td3.colSpan='6';
                var strHtml3 = "<textarea class=\"customerComplainArea\" name=\"compVerbatim\" rows=\"4\" id=\"compVerbatim"+count+"\"  onblur=\"if(!CommentsMaxLength1(this, 250)){this.value=this.value.substring(0, 250);}\"></textarea>";
                td3.innerHTML = strHtml3.replace(/!count!/g,count);
                row1.appendChild(td3);

                var row2 = document.createElement("TR");

                var td4 = document.createElement("TD");
                td4.align="right";
                td4.width="18%";
                var strHtml4 = "<label><bean:message key="label.common.application" /></label>&nbsp;<span class=\"mandatory\">*</span>";
                td4.innerHTML = strHtml4.replace(/!count!/g,count);
                row2.appendChild(td4);

                var td5 = document.createElement("TD");
                td5.align="left";
                td5.width="12%";
                var strHtml5 = "<select  name=\"applicationCode\" id=\"Application"+count+"\"><option value=''>--Select--</option><c:forEach items="${applicationList}" var='dataList'><option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option></c:forEach></select>";
                td5.innerHTML = strHtml5.replace(/!count!/g,count);
                row2.appendChild(td5);

                var td10 = document.createElement("TD");
                td10.align="right";
                var strHtml10 = "<label><bean:message key="label.common.defect" />&nbsp;<span class=\"mandatory\">*</span></label>";
                td10.innerHTML = strHtml10.replace(/!count!/g,count);
                row2.appendChild(td10);


                var td11 = document.createElement("TD");
                td11.align="left";
                // var strHtml11 = "<div id=\"subassembly_Code"+count+"\"><select name=\"compCode\"  id=\"compCode"+count+"\" onchange=\"getCommonCode(this,'Comp_Code',"+parseInt(count)+")\"><option value=''>--Select--</option></select></div>";
                var strHtml11 = "<div id=\"subassembly_Code"+count+"\"><select name=\"compCode\"  id=\"compCode"+count+"\" ><option value=''>--Select--</option></select></div>";
                td11.innerHTML = strHtml11.replace(/!count!/g,count);
                row2.appendChild(td11);


                var td6 = document.createElement("TD");
                td6.align="left";
                td6.width="70%";
                var strHtml6 = "<label><B><bean:message key="label.common.foremanobservation" /></B></label>";
                td6.innerHTML = strHtml6.replace(/!count!/g,count);
                row2.appendChild(td6);

        
                var row7 = document.createElement("TR");
                //
       
        

        

                var td7 = document.createElement("TD");
                td7.align="right";
                var strHtml7 = "<label><bean:message key="label.common.aggregate" /></label>&nbsp;<span class=\"mandatory\">*</span>";
                td7.innerHTML = strHtml7.replace(/!count!/g,count);
                row7.appendChild(td7);

                var td8 = document.createElement("TD");
                td8.align="left";
                var strHtml8 = "<select name=\"aggCode\"  id=\"aggCode"+count+"\" onchange=\"getCommonCode(this,'Agg_Code',"+parseInt(count)+")\"><option value=''>--Select--</option><c:forEach items='${aggregateList}' var='aggregateList'><option value='${aggregateList.value}' title='${aggregateList.label}'>${aggregateList.label}</option></c:forEach></select>";
                td8.innerHTML = strHtml8.replace(/!count!/g,count);
                row7.appendChild(td8);
        
                var td12 = document.createElement("TD");
                td12.align="right";
                // var strHtml12 = "<label><bean:message key="label.common.cause" />&nbsp;<span class=\"mandatory\">*</span></label>";
                var strHtml12 = "<label>&nbsp;</label>";
                td12.innerHTML = strHtml12.replace(/!count!/g,count);
                row7.appendChild(td12);


                var td13 = document.createElement("TD");
                td13.align="left";
                // var strHtml3 = "<div id=\"Comp_Code"+count+"\"><select name=\"causalCode\"  id=\"causalCode"+count+"\" ><option value=''>--Select--</option></select></div>";//onchange=\"getCommonCode(this,'Causal_Code',"+parseInt(count)+")\"
                var strHtml3 = ""
                td13.innerHTML = strHtml3.replace(/!count!/g,count);
                row7.appendChild(td13);

                var td09 = document.createElement("TD");
                td09.align="left";
                td09.rowSpan="5";
                td09.vAlign="top";
                var strHtml09 = "<textarea class=\"foremanObservationArea\" name=\"foremanObservation\" rows=\"6\" id=\"foremanObservation"+parseInt(count)+"\" onblur=\"if(!CommentsMaxLength(this, 250)){this.value=this.value.substring(0, 250);}\" onblur=\"this.value=TrimAll(this.value)\"></textarea>";
                td09.innerHTML = strHtml09.replace(/!count!/g,count);
                row7.appendChild(td09);

                var row3 = document.createElement("TR");

                var td16 = document.createElement("TD");
                td16.align="right";
                var strHtml7 = "<label><bean:message key="label.common.subaggregate" />&nbsp;<span class=\"mandatory\">*</span></label>";
                td16.innerHTML = strHtml7.replace(/!count!/g,count);
                row3.appendChild(td16);

                var td17 = document.createElement("TD");
                td17.align="left";
                var strHtml8 = "<div id=\"Agg_Code"+count+"\"><select name=\"subaggCode\"  id=\"subaggCode"+count+"\" onchange=\"getCommonCode(this,'subAgg_Code',"+parseInt(count)+")\"><option value=''>--Select--</option></select></div>";
                td17.innerHTML = strHtml8.replace(/!count!/g,count);
                row3.appendChild(td17);

                var td14 = document.createElement("TD");
                td14.align="right";
                td14.rowSpan="2";
                var strHtml4 = "<label><bean:message key="label.common.consequences" />&nbsp;<span class=\"mandatory\">*</span></label>";
                //   td14.innerHTML = strHtml4.replace(/!count!/g,count);
                row3.appendChild(td14);


                var td15 = document.createElement("TD");
                td15.align="left";
                td15.rowSpan="2";
                var strHtml5 = "<div id=\"Causal_Code"+count+"\"><select type=\"list\" style=\"height:60px !important\" multiple name=\"consequenceCode"+parseInt(count)+"\"  id=\"consequenceCode"+parseInt(count)+"\" ></select></div>";
                // td15.innerHTML = strHtml5.replace(/!count!/g,count);
                row3.appendChild(td15);
        

                 var row4 = document.createElement("TR");

                 var td0 = document.createElement("TD");
                 td0.align="right";
                 var strHtml7 = "<label><bean:message key="label.common.subassembly" />&nbsp;<span class=\"mandatory\">*</span></label>";
                 td0.innerHTML = strHtml7.replace(/!count!/g,count);
                 row4.appendChild(td0);

                 var td08 = document.createElement("TD");
                 td08.align="left";
                 var strHtml08 = "<div id=\"subAgg_Code"+count+"\"><select name=\"subassemblyCode\"  id=\"subassemblyCode"+count+"\" ><option value=''>--Select--</option><option value='subassembly'>subassembly</option></select>";
                 td08.innerHTML = strHtml08.replace(/!count!/g,count);
                 row4.appendChild(td08);
                 
                 var row8 = document.createElement("TR");
                 
                 var td20 = document.createElement("TD");
                 td20.align="right";
                 td20.width="18%";
                 var strHtml20 = "<label><bean:message key="label.common.biPart" /></label>&nbsp;<span class=\"mandatory\">*</span>";
                 td20.innerHTML = strHtml20.replace(/!count!/g,count);
                 row8.appendChild(td20);

                 var td21 = document.createElement("TD");
                 td21.align="left";
                 td21.width="12%";
                 var strHtml21 = "<select  name=\"biPartCode\" id=\"biPartCode"+count+"\"><option value=''>--Select--</option><c:forEach items="${biPartList}" var='dataList'><option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option></c:forEach></select>";
                 td21.innerHTML = strHtml21.replace(/!count!/g,count);
                 row8.appendChild(td21);
                 
                 
                 
                 var row9 = document.createElement("TR");
                 
                 var td21 = document.createElement("TD");
                 td21.align="right";
                 td21.width="18%";
                 var strHtml21 = "<label><bean:message key="label.common.atmCase" /></label>";
                 td21.innerHTML = strHtml21.replace(/!count!/g,count);
                 row9.appendChild(td21);

                 var td22 = document.createElement("TD");
                 td22.align="left";
                 td22.width="12%";
                 var strHtml22 = "<select  name=\"atmCaseCode\" id=\"atmCaseCode"+count+"\"><option value=''>--Select--</option><c:forEach items="${biPartList}" var='dataList'><option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option></c:forEach></select>";
                 td22.innerHTML = strHtml22.replace(/!count!/g,count);
                 row9.appendChild(td22);
               

       
                var row5 = document.createElement("TR");

            
                var row6 = document.createElement("TR");

            



                count = parseInt(count) + 1;
                // append row to table
                tbody.appendChild(row);
                tbody.appendChild(row1);
                tbody.appendChild(row2);
                tbody.appendChild(row7);
                tbody.appendChild(row3);
                tbody.appendChild(row4);
                tbody.appendChild(row8);
                tbody.appendChild(row9);
                
                tbody.appendChild(row5);
                tbody.appendChild(row6);
        

            }
            function deleteRowC(in_tbl_name,ct)
            {
            
                var num=ct*8;
                var oRows = document.getElementById(in_tbl_name).getElementsByTagName('tr') ;
                var rowLength=oRows.length-1;

          
                if(rowLength=="7"){

                    alert(onecompmand_validation_msg);

                }
                else if(rowLength>num){
                    document.getElementById(in_tbl_name).deleteRow(rowLength-1);
               
                    document.getElementById(in_tbl_name).deleteRow(rowLength-2);
               
                    document.getElementById(in_tbl_name).deleteRow(rowLength-3);
              
                    document.getElementById(in_tbl_name).deleteRow(rowLength-4);
                
                    document.getElementById(in_tbl_name).deleteRow(rowLength-5);
             
                    document.getElementById(in_tbl_name).deleteRow(rowLength-6);
                
                    document.getElementById(in_tbl_name).deleteRow(rowLength-7);
                
                    document.getElementById(in_tbl_name).deleteRow(rowLength-8);
                
                    count = parseInt(count) -1;
                }
                else{

                    alert(deletepreviouscomp_validation_msg);
                }
            }

            function checkQuotes(str)
            {
                if (str.indexOf('"') >= 0) {
                    return true;
                }else{
                    return false;
                }

            }
            function validate()
            {
                var ck_code = /^[a-zA-Z\.\-\/\ ]+$/;
                var cks_code = /^[a-zA-Z\d\-\/\.\,\\\ ]+$/;
                var jobcardno=document.getElementById("jobCardNo").value;

                var customerVerbatim=new Array();
                var subaggreate=new Array();
                var aggCode=new Array();
                //var compCode=new Array();
                //var causalCode=new Array();
                //var consequenceCode=new Array();
                var subassamb=new Array();
                var foremanObservation =new Array();
                var applicationCode=new Array();
                var sparex=new Array();
                
                var biPartCode=new Array();
                
                var count=0;
                foremanObservation=document.getElementsByName("foremanObservation");
                applicationCode=document.getElementsByName("applicationCode");
                customerVerbatim=document.getElementsByName("compVerbatim");
                aggCode=document.getElementsByName("aggCode");
                compCode=document.getElementsByName("compCode");
           
                //  causalCode=document.getElementsByName("causalCode");
                //subassamb=document.getElementsByName("subassembly");//subassemblyCode
                subassamb=document.getElementsByName("subassemblyCode");//subassemblyCode
                subaggreate=document.getElementsByName("subaggCode");
                
                
                biPartCode=document.getElementsByName("biPartCode");
            
                
                // consequenceCode=document.getElementsByName("consequenceCode");
                if(jobcardno=='')
                {
        <%--document.getElementById("msg_saveFAILED").innerHTML="Please Fill The Vehicle Information Details First"--%>
                    alert(vehInfo_validation_msg);
                    return false;
                }
                for(var i=0;i<customerVerbatim.length;i++ ){
                
                    if(customerVerbatim[i].value=='')
                    {
                        alert(not_blank_validation_msg+"Customer Verbatim");
                        customerVerbatim[i].focus();
                        return false;
                    }

                    if(checkQuotes(customerVerbatim[i].value))
                    {
                        document.getElementById("msg_saveFAILED").innerHTML = "Special character(\") is not allowed in Customer Verbatim"
                        customerVerbatim[i].focus();
                        return false;
                    }
                     
                    var res1=true;
                    for(var k=0;k<sparex.length;k++)
                    {
                        if(sparex[k]==(customerVerbatim[i].value))
                        {
                            res1=false;
                        }
                    }
                    if(res1==false)
                    {
                        alert(uniquecustVerbatim_validation_msg);
                        customerVerbatim[k].value='';
                        customerVerbatim[k].focus();
                        return false;
                    }
        <%--  if (!trim( customerVerbatim[i].value).match(/^[a-zA-Z0-9]/) &&  customerVerbatim[i].value !="")
          {
              customerVerbatim[i].value="";
              customerVerbatim[i].focus();
              alert(custVerbatim_validation_msg);
              return false;
          }--%>
                      sparex.push(customerVerbatim[i].value);
        <%--
                        if (!trim( foremanObservation[i].value).match(/^[a-zA-Z0-9]/) &&  foremanObservation[i].value !="")
                        {
                            foremanObservation[i].value="";
                            foremanObservation[i].focus();
                            alert(obsForeman_validation_msg);
                            return false;
                        }--%>

                                    if(applicationCode[i].value=='')
                                    {
                                        alert(not_blank_dropdown_validation_msg+"Application");
                                        applicationCode[i].focus();
                                        return false;
                                    }
               
                                    if(aggCode[i].value=='')
                                    {
                                        alert(not_blank_dropdown_validation_msg+"Aggregate");
                                        aggCode[i].focus();
                                        return false;
                                    }
                
                                    if(subaggreate[i].value=='')
                                    {
                                        alert(not_blank_dropdown_validation_msg+"Sub Aggregate");
                                        subaggreate[i].focus();
                                        return false;
                                    }
                                    if(subassamb[i].value=='')
                                    {
                                        alert(not_blank_dropdown_validation_msg+"Sub Assambly");
                                        subassamb[i].focus();
                                        return false;
                                    }
                                    if(compCode[i].value=='')
                                    {
                                        alert(not_blank_dropdown_validation_msg+"Defect");
                                        compCode[i].focus();
                                        return false;
                                    }
                                    
                                    if(biPartCode[i].value=='')
                                    {
                                        alert(not_blank_dropdown_validation_msg+"Bi-Part");
                                        biPartCode[i].focus();
                                        return false;
                                    }
                                    
      
                      
                }



 


                               CustomerComplainArea();
                for(var j=0;j<foremanObservation.length;j++ ){
                    if(foremanObservation[j].value!='')
                    {
                        ForemanObservationArea();
                    }

                    if(checkQuotes(foremanObservation[j].value))
                    {
                        document.getElementById("msg_saveFAILED").innerHTML = "Special character (\") is not allowed in Foreman Observation."
                        foremanObservation[j].focus();
                        return false;
                    }
                }
                var tractorindate=document.getElementById("Tractor In Date").value;
                var tractor=tractorindate.split('/');
                var tractorfinaldate=tractor[2]+'/'+tractor[1]+'/'+tractor[0];
                var d1=new Date(tractorfinaldate);

                var complaintDate=document.getElementById("Complaint Date").value;
                var complaintD=complaintDate.split('/');
                var complaintfinalDate=complaintD[2]+'/'+complaintD[1]+'/'+complaintD[0];
                var d2=new Date(complaintfinalDate);
                //alert(d1+"-----"+ d2)
                var ttdate="";
                var tmon="<%=month%>";
                var tdays="<%=day%>";

                if(eval(tmon)<10)
                    tmon="0"+tmon;

                if(eval(tdays)<10)
                    tdays="0"+tdays;

                ttdate=<%=year%>+'/'+tmon+'/'+tdays;

                var today = new Date(ttdate);
                var constantval=${serviceform.constantValue}
                var condate =new Date(ttdate);
                condate.setDate(condate.getDate()-constantval);

                if(condate>d2){
                    document.getElementById("Complaint Date").style.border="1px solid #ff0000";
                    alert(dateofComplaintwith_validation_msg +" "+constantval+" "+dat_validation_msg2);
                    document.getElementById("Complaint Date").select();
                    return false;

                }
                else if(d1 > d2)
                {
                    document.getElementById("Complaint Date").style.border="1px solid #ff0000";
                    alert(complaintDategreater_validation_msg);
                    return false;
                }
                else if(today < d2)
                {
                    document.getElementById("Complaint Date").style.border="1px solid #ff0000";
                    alert(complaintDate_validation_msg);
                    document.getElementById("Complaint Date").focus();
                    return false;
                }else{
                    document.getElementById("Complaint Date").style.border="1px solid #cccccc";
                }


                    document.getElementById("masterForm").submit();
                    

                }

                function CommentsMaxLength1(text,maxLength)
                {
                    var diff =  maxLength-text.value.length ;

                    if (diff < 0){
                        text.value = text.value.substring(0,maxLength);
                        diff=0;
                        alert(custver_validation_msg + maxLength + "");
                        text.focus();
                    }
                    else{document.getElementById("msg_saveFAILED").innerHTML = "";}
                    //
                }
                function searchComplain()
                {
                    var strURL = "${pageContext.request.contextPath}/serviceCreateJC.do?option=initSearchComplain";
                    window.open(strURL,'iji','width=800,height=500, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
                }
                $(document).ready(function () {
                    var ttdate="";
                    var tmon="<%=month%>";
                    var tdays="<%=day%>";
                    if(eval(tmon)<10)
                        tmon="0"+tmon;

                    if(eval(tdays)<10)
                        tdays="0"+tdays;

                    ttdate=+tdays+'/'+tmon+ '/'+<%=year%>;

                    var today = new Date(ttdate);

                    var complaintDate=document.getElementById("Complaint Date").value;

                    if(complaintDate!=""){
                        $( ".datepicker" ).datepicker();
                    }else{
                        $( ".datepicker" ).datepicker( "setDate", ttdate);
                    }
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

            <h1 ><span class="formLabel"> <bean:message key="label.common.complaint" /></span></h1>

            <c:if test="${serviceform.jobcardview eq 'true' }">
                <%@ include file="topbandshow.jsp" %>
            </c:if>

            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
                <tr><td>
                        <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                            <tr align="center" valign="middle" class="formHeader">
                                <c:choose> <c:when test="${pathNm eq 'fillJC'}">
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.vehicleinfo" /></a></label></td>

                                        <td ><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>

                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                                        <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.estimate" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                                        <c:if test="${showHistory ne 'Y'}">
                                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                                        </c:if>

                                    </c:when>
                                    <c:otherwise>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.vehicleinfo" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                                        <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.estimate" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vinid=${vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                                        <c:if test="${showHistory ne 'Y'}">
                                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                                        </c:if>
                                    </c:otherwise>  </c:choose>  </tr>
                            </table>
                        </td></tr>

                    <tr>
                        <td>
                            <form action="<%=cntxpath%>/serviceCreateJC.do?option=manageComplaintdetail&jctype=${serviceform.jctype}&jobType=${serviceform.jobType}" method="post" id="masterForm" onsubmit="return false;">
                            <input type="hidden" name="upperLink" value="<a href = '${contextPath}/masterAction.do?option=initOptions'>SERVICE</a>@@CREATE JOB CARD"/>
                            <input type="hidden" name="jobCardNo" id="jobCardNo" value="${serviceform.jobCardNo}"/>
                            <input type="hidden" name="vinid"  value="${serviceform.vinid}"/>
                            <input name="showHistory" type="hidden" value="${showHistory}">
                            <table width="100%" border="0" >
                                <tr>
                                    <td align="right" style="padding:5px;"><strong><bean:message key="label.service.complaintDate" /></strong>
                                        <input name="deliveryDate" type="hidden"  id="Tractor In Date" value="${serviceform.jobCardDate}"/>
                                        <c:choose>
                                            <c:when test="${serviceform.complaintDate eq '' }">
                                                <input name="complaintDate" type="text" class="datepicker" maxlength="10" id="Complaint Date" value="${serviceform.jobCardDate}" readonly style="background-color: #E6E4E4" />
                                            </c:when>
                                            <c:otherwise>
                                                <input name="complaintDate" type="text" class="datepicker"  maxlength="10" id="Complaint Date" value="${serviceform.complaintDate}" readOnly/>
                                            </c:otherwise>
                                        </c:choose>&nbsp;
                                        <input type="button" onclick="searchComplain();" value="Search Defects" class="headingSpas1"/>
                                    </td>
                                </tr>
                            </table>
                            <table id="myTable"  width="100%" border="0" align="center" cellpadding="3" cellspacing="3"  >
                                <c:choose>
                                    <c:when test="${serviceform.jobcardview eq 'true' }">


                                        <tbody>

                                            <c:set var="count" value="0" scope="page" />
                                            <c:if test="${fn:length(serviceform.compVerbatim) > 0}">
                                                <c:forEach items="${serviceform.compVerbatim}" var="data">




                                                    <tr>

                                                        <td align="right" style="white-space: nowrap"><strong><bean:message key="label.common.customercomplaint" />&nbsp;${count+1}<br>
                                                                <input type="hidden" name="count" id="count" value=""/>
                                                                <input type="hidden" name="operate" value="update"/>
                                                                <input type="hidden" name="compid" value="${serviceform.compid}"/>

                                                                <br>
                                                                <bean:message key="label.common.verbatim" /></strong>&nbsp;<span class="mandatory">*</span></td>
                                                        <td align="left"  colspan="6">
                                                            <textarea class="customerComplainArea" name="compVerbatim" rows="4" id="compVerbatim${count+1}"  onblur="if(!CommentsMaxLength1(this, 250)){this.value=this.value.substring(0, 250);}">${serviceform.compVerbatim[count]}</textarea></td>
                                                    </tr>



                                                    <tr>
                                                        <td width="18%" align="right" style="white-space: nowrap"> <label><bean:message key="label.common.application" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                        <td width="12%" align="left">
                                                            

                                                            <select  name="applicationCode" id="applicationCode${count+1}"  >
                                                                <option value="">--Select--</option>
                                                                <c:forEach items="${applicationList}" var="dataList">

                                                                    <c:if test="${serviceform.applicationCode[count] eq dataList.value }">

                                                                        <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                                                    </c:if>

                                                                    <c:if test="${serviceform.applicationCode[count] ne dataList.value }">

                                                                        <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                                                    </c:if>
                                                                </c:forEach>

                                                            </select>

                                                        </td>
                                                        <td align="right" style="white-space: nowrap"> <label><bean:message key="label.common.defect" /> </label>&nbsp;<span class="mandatory">*</span></td>
                                                        <td align="left" >
                                                            <div id="subassembly_Code${count+1}">
                                                                <select name="compCode"  id="compCode${count+1}" >
                                                                    <option value="">--Select--</option>
                                                                    <c:forEach items="${serviceform.defectcode[count]}" var="dataList">



                                                                        <c:if test="${serviceform.compCode[count] eq dataList.value }">

                                                                            <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                                                        </c:if>

                                                                        <c:if test="${serviceform.compCode[count] ne dataList.value }">

                                                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                                                        </c:if>

                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </td>



                                                        <td width="70%" align="left"  style=""><label><B><bean:message key="label.common.foremanobservation" /></B> </label></td>
                                                    </tr>


                                                    <tr>
                                                        <td align="right" style="white-space: nowrap"> <label><bean:message key="label.common.aggregate" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                        <td align="left"  >
                                                            <select name="aggCode"   id="aggCode${count+1}" onchange="getCommonCode(this,'Agg_Code',${count+1});">
                                                                <option value="">--Select--</option>
                                                                <c:forEach items="${aggregateList}" var="dataList">
                                                                    <c:if test="${serviceform.aggCode[count] eq dataList.value }">

                                                                        <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                                                    </c:if>

                                                                    <c:if test="${serviceform.aggCode[count] ne dataList.value }">

                                                                        <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                                                    </c:if>

                                                                </c:forEach>
                                                            </select>
                                                        </td>

                                                        <td align="right" style="white-space: nowrap"> <label><%--<bean:message key="label.common.cause" />--%></label>&nbsp;<%--<span class="mandatory">*</span>--%></td>
                                                        <td align="left"  >
                                                            
                                                        </td>

                                                        <td rowspan="5" align="left" valign="top"><textarea class="foremanObservationArea" name="foremanObservation" rows="6" id="foremanObservation${count+1}" onblur="if(!CommentsMaxLength(this, 250)){this.value=this.value.substring(0, 250);}" >${serviceform.foremanObservation[count]}</textarea>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="right" style="white-space: nowrap"> <label><bean:message key="label.common.subaggregate" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                        <td align="left"  >
                                                            <div id="Agg_Code${count+1}">
                                                                <select name="subaggCode"   id="subaggCode${count+1}" onchange="getCommonCode(this,'subAgg_Code',${count+1});">
                                                                    <option value="">--Select--</option>
                                                                    <c:forEach items="${serviceform.subagg[count]}" var="dataList">



                                                                        <c:if test="${serviceform.subaggCode[count] eq dataList.value }">

                                                                            <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                                                        </c:if>

                                                                        <c:if test="${serviceform.subaggCode[count] ne dataList.value }">

                                                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                                                        </c:if>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </td>
                                                       
                                                    </tr>

                                                    <tr>
                                                        <td align="right" style="white-space: nowrap"> <label><bean:message key="label.common.subassembly" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                        <td align="left"  >
                                                            <div id="subAgg_Code${count+1}">
                                                                <select name="subassemblyCode"   id="subassembly${count+1}" onchange="getCommonCode(this,'subassembly_Code',${count+1});" >
                                                                    <option value="">--Select--</option>
                                                                    <c:forEach items="${serviceform.subassmblylabel[count]}" var="dataList">
                                                                        <c:if test="${serviceform.subassembly[count] eq dataList.value }">
                                                                            <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                                                        </c:if>

                                                                        <c:if test="${serviceform.subassembly[count] ne dataList.value }">

                                                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                                                        </c:if>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </td>

                                                    </tr>
                                                    
                                                    <tr>
                                                    <td width="18%" align="right" style="white-space: nowrap"> <label><bean:message key="label.common.biPart" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                        <td width="12%" align="left">
                                                            

                                                            <select  name="biPartCode" id="biPartCode${count+1}"  >
                                                                <option value="">--Select--</option>
                                                                <c:forEach items="${biPartList}" var="dataList">

                                                                    <c:if test="${serviceform.biPartCode[count] eq dataList.value }">

                                                                        <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                                                    </c:if>

                                                                    <c:if test="${serviceform.biPartCode[count] ne dataList.value }">

                                                                        <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                                                    </c:if>
                                                                </c:forEach>

                                                            </select>

                                                        </td>
                                                        </tr>
                                                        <tr>
                                                    <td width="18%" align="right" style="white-space: nowrap"> <label><bean:message key="label.common.atmCase" /></label></td>
                                                        <td width="12%" align="left">
                                                            

                                                            <select  name="atmCaseCode" id="atmCaseCode${count+1}"  >
                                                                <option value="">--Select--</option>
                                                                <c:forEach items="${atmCaseList}" var="dataList">

                                                                    <c:if test="${serviceform.atmCaseCode[count] eq dataList.value }">

                                                                        <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                                                    </c:if>

                                                                    <c:if test="${serviceform.atmCaseCode[count] ne dataList.value }">

                                                                        <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                                                    </c:if>
                                                                </c:forEach>

                                                            </select>

                                                        </td>
                                                        </tr>
                                                    
                                                    
                                                    
                                                    

                                                    <tr>
                                                        <td height="10" colspan="3" align="right" >&nbsp;</td>
                                                    </tr>

                                                    <tr>
                                                        <td height="10" colspan="3" align="right" >&nbsp;</td>
                                                    </tr>

                                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${fn:length(serviceform.compVerbatim) == 0}">
                                                <c:set var="count" value="1" scope="page" />
                                                <tr>
                                                    <td align="right" style="white-space: nowrap"><strong><bean:message key="label.common.customercomplaint" /> 1<br>
                                                            <br>
                                                            <bean:message key="label.common.verbatim" /> </strong>&nbsp;<span class="mandatory">*</span></td>
                                                    <td align="left"  colspan="6">
                                                        <textarea class="customerComplainArea" name="compVerbatim" rows="4" id="compVerbatim1" onblur="if(!CommentsMaxLength1(this, 250)){this.value=this.value.substring(0, 250);}"></textarea></td>
                                                </tr>
                                            <input type="hidden" name="count" id="count" value=""/>
                                            <input type="hidden" name="operate" value="insert"/>
                                            <tr>
                                                <td width="18%" align="right" style="white-space: nowrap"> <label><bean:message key="label.common.application" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                <td width="12%" align="left">
                                                    <%--   <c:out value="${serviceform.applicationCode[count]}" />  --%>

                                                    <select  name="applicationCode" id="applicationCode1"  >
                                                        <option value="">--Select--</option>
                                                        <c:forEach items="${applicationList}" var="dataList">



                                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>


                                                        </c:forEach>

                                                    </select>

                                                </td>
                                                <td align="right" style="white-space: nowrap"> <label><bean:message key="label.common.defect" />  </label>&nbsp;<span class="mandatory">*</span></td>
                                                <td align="left" >
                                                    <div id="subassembly_Code1">
                                                        <select name="compCode"  id="compCode1" >
                                                            <option value="">--Select--</option>

                                                        </select>
                                                    </div>
                                                </td>



                                                <td width="70%" align="left"  style=""><label><B><bean:message key="label.common.foremanobservation" /> </B></label></td>
                                            </tr>


                                            <tr>
                                                <td align="right" style="white-space: nowrap"> <label>Aggregate</label>&nbsp;<span class="mandatory">*</span></td>
                                                <td align="left"  >
                                                    <select name="aggCode"   id="aggCode1" onchange="getCommonCode(this,'Agg_Code',1);">
                                                        <option value="">--Select--</option>
                                                        <c:forEach items="${aggregateList}" var="dataList">


                                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>



                                                        </c:forEach>
                                                    </select>
                                                </td>

                                                <td align="right" style="white-space: nowrap"> <label><%--<bean:message key="label.common.cause" />--%></label>&nbsp;<%--<span class="mandatory">*</span>--%></td>
                                                <td align="left"  >
                                                   
                                                </td>

                                                <td rowspan="5" align="left" valign="top"><textarea class="foremanObservationArea" name="foremanObservation" rows="6" id="foremanObservation1" onblur="if(!CommentsMaxLength(this, 250)){this.value=this.value.substring(0, 250);}"></textarea>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="right" style="white-space: nowrap"> <label><bean:message key="label.common.subaggregate" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                <td align="left"  >
                                                    <div id="Agg_Code1">
                                                        <select name="subaggCode"   id="subaggCode1" onchange="getCommonCode(this,'subAgg_Code',1);">
                                                            <option value="">--Select--</option>

                                                        </select>
                                                    </div>
                                                </td>
                                                

                                            </tr>


                                            <tr>
                                                <td align="right" style="white-space: nowrap"> <label><bean:message key="label.common.subassembly" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                <td align="left"  >
                                                    <div id="subAgg_Code1">
                                                        <select name="subassemblyCode"   id="subassemblyCode1" >
                                                            <option value="">--Select--</option>

                                                        </select>
                                                    </div>
                                                </td>

                                            </tr>
                                            <tr>
                                            <td width="18%" align="right" style="white-space: nowrap"> <label><bean:message key="label.common.biPart" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                <td width="12%" align="left">
                                                   
                                                    <select  name="biPartCode" id="biPartCode1"  >
                                                        <option value="">--Select--</option>
                                                        <c:forEach items="${biPartList}" var="dataList">
                                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>
                                                        </c:forEach>
                                                    </select>

                                                </td>
                                             </tr>
                                             
                                             <tr>   
                                           <td width="18%" align="right" style="white-space: nowrap"> <label><bean:message key="label.common.atmCase" /></label></td>
                                                <td width="12%" align="left">
                                                   
                                                    <select  name="atmCaseCode" id="atmCaseCode1"  >
                                                        <option value="">--Select--</option>
                                                        <c:forEach items="${atmCaseList}" var="dataList">
                                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>
                                                        </c:forEach>
                                                    </select>

                                                </td>
                                             </tr>

                                            <tr>
                                                <td height="10" colspan="3" align="right" >&nbsp;</td>
                                            </tr>

                                            <tr>
                                                <td height="10" colspan="3" align="right" >&nbsp;</td>
                                            </tr>


                                        </c:if>
                                        </tbody>


                                    </c:when>
                                    <c:otherwise>
                                        <tbody>
                                            <c:set var="count" value="1" scope="page" />
                                            <tr>
                                                <td align="right" style="white-space: nowrap"><strong><bean:message key="label.common.customercomplaint" /><br>
                                                        <br>
                                                        (<bean:message key="label.common.verbatim" /> 1)</strong>&nbsp;<span class="mandatory">*</span></td>
                                                <td align="left"  colspan="6">
                                                    <textarea class="customerComplainArea" name="compVerbatim" rows="4" id="compVerbatim1" onblur="if(!CommentsMaxLength1(this, 250)){this.value=this.value.substring(0, 250);}"></textarea></td>
                                            </tr>
                                        <input type="hidden" name="count" id="count" value=""/>
                                        <input type="hidden" name="operate" value="insert"/>
                                        <tr>
                                            <td width="18%" align="right" style="white-space: nowrap"> <label><bean:message key="label.common.application" /></label>&nbsp;<span class="mandatory">*</span></td>
                                            <td width="12%" align="left">
                                                <%--   <c:out value="${serviceform.applicationCode[count]}" />  --%>

                                                <select  name="applicationCode" id="applicationCode1"  >
                                                    <option value="">--Select--</option>
                                                    <c:forEach items="${applicationList}" var="dataList">



                                                        <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>


                                                    </c:forEach>

                                                </select>

                                            </td>
                                            <td align="right" style="white-space: nowrap"> <label><bean:message key="label.common.defect" />  </label>&nbsp;<span class="mandatory">*</span></td>
                                            <td align="left" >
                                                <div id="subassembly_Code1">
                                                    <select name="compCode"  id="compCode1" >
                                                        <option value="">--Select--</option>

                                                    </select>
                                                </div>
                                            </td>



                                            <td width="70%" align="left"  style=""><label><B><bean:message key="label.common.foremanobservation" /></B> </label></td>
                                        </tr>


                                        <tr>
                                            <td align="right" style="white-space: nowrap"> <label><bean:message key="label.common.aggregate" /></label>&nbsp;<span class="mandatory">*</span></td>
                                            <td align="left"  >
                                                <select name="aggCode"   id="aggCode1" onchange="getCommonCode(this,'Agg_Code',1);">
                                                    <option value="">--Select--</option>
                                                    <c:forEach items="${aggregateList}" var="dataList">


                                                        <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>



                                                    </c:forEach>
                                                </select>
                                            </td>

                                            <td align="right" style="white-space: nowrap"> <label><%--<bean:message key="label.common.cause" />--%></label>&nbsp;<%--<span class="mandatory">*</span>--%></td>
                                            <td align="left"  >
                                                <%--<div id="Comp_Code1">
                                                    <select name="causalCode"  id="causalCode1" onchange="getCommonCode(this,'Causal_Code',1)">
                                                        <option value="">--Select--</option>

                                        </select>
                                    </div>--%>
                                            </td>

                                            <td rowspan="5" align="left" valign="top"><textarea class="foremanObservationArea" name="foremanObservation" rows="6" id="foremanObservation1" onblur="if(!CommentsMaxLength(this, 250)){this.value=this.value.substring(0, 250);}"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" style="white-space: nowrap"> <label><bean:message key="label.common.subaggregate" /></label>&nbsp;<span class="mandatory">*</span></td>
                                            <td align="left"  >
                                                <div id="Agg_Code1">
                                                    <select name="subaggCode"   id="subaggCode1" onchange="getCommonCode(this,'subAgg_Code',1);">
                                                        <option value="">--Select--</option>

                                                    </select>
                                                </div>
                                            </td>
                                            <%--<td align="right" style="white-space: nowrap" rowspan="2"><label><bean:message key="label.common.consequences" /></label></td>
                                            <td align="left" rowspan="2">
                                                <div id="Causal_Code1">
                                                    <select  multiple name="consequenceCode1"  id="consequenceCode1" style="height:60px !important">

                                        </select>
                                    </div>
                                </td>--%>

                                        </tr>


                                        <tr>
                                            <td align="right" style="white-space: nowrap"> <label><bean:message key="label.common.subassembly" /></label>&nbsp;<span class="mandatory">*</span></td>
                                            <td align="left"  >
                                                <div id="subAgg_Code1">
                                                    <select name="subassemblyCode"   id="subassemblyCode1" onchange="getCommonCode(this,'subassembly_Code',1);">
                                                        <option value="">--Select--</option>

                                                    </select>
                                                </div>
                                            </td>

                                        </tr>
                                        
                                        <tr>
                                            <td width="18%" align="right" style="white-space: nowrap"> <label><bean:message key="label.common.biPart" /></label>&nbsp;<span class="mandatory">*</span></td>
                                            <td width="12%" align="left">
                                                

                                                <select  name="biPartCode" id="biPartCode1"  >
                                                    <option value="">--Select--</option>
                                                    <c:forEach items="${biPartList}" var="dataList">



                                                        <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>


                                                    </c:forEach>

                                                </select>

                                            </td>
                                            
                                          </tr>
                                          
                                          
                                          <tr>   
                                           <td width="18%" align="right" style="white-space: nowrap"> <label><bean:message key="label.common.atmCase" /></label></td>
                                                <td width="12%" align="left">
                                                   
                                                    <select  name="atmCaseCode" id="atmCaseCode1"  >
                                                        <option value="">--Select--</option>
                                                        <c:forEach items="${atmCaseList}" var="dataList">
                                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>
                                                        </c:forEach>
                                                    </select>

                                                </td>
                                             </tr>


                                        <tr>
                                            <td height="10" colspan="3" align="right" >&nbsp;</td>
                                        </tr>

                                        <tr>
                                            <td height="10" colspan="3" align="right" ></td>
                                        </tr>


                                        </tbody>
                                    </c:otherwise>
                                </c:choose>
                                <tr>
                                    <%if (userFunctionalities.contains("608")) {%>
                                    <c:if test="${serviceform.status eq 'OPEN'}">
                                        <td align="right"   >&nbsp;</td>
                                        <td align="left" >&nbsp;</td>
                                        <td align="right"   >&nbsp;</td>
                                        <td align="right"   >&nbsp;</td>
                                        <td align="right" valign="top">
                                            <%--<input type="button" name="Button" value="Add More Complaint" onfocus="setCount(${count + 1})" onclick="addRowC('myTable')" />&nbsp;--%>
                                            <input type="button" name="Button" value="Add More Complaint"  onclick="addRowC('myTable',${count})" />&nbsp;
                                            <input type="button" name="Button" value="Delete Complaint" onclick="deleteRowC('myTable',${count})" />
                                            <input type="button" name="Button2" value="SAVE" onclick="return validate()"/></td>
                                    </c:if><%}%> </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
        </div></div></center>
