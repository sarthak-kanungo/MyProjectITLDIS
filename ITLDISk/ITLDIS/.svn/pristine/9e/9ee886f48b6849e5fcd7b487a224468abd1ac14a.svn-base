<%-- 
    Document   : baseLayout
    Created on : Jan 29, 2014, 6:15:57 PM
    Author     : manish.kishore
--%>
<%@taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>

<!doctype html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
       <%-- <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"/>--%>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
        <title>ITLDIS</title>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
%>

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var contextPath = '${pageContext.request.contextPath}';
    var GB_ROOT_DIR = "${pageContext.request.contextPath}/greybox/";
    var SERVLET_ROOT_DIR = "${pageContext.request.contextPath}/";
    window.appContextPath = '<%= request.getContextPath() %>';
</script>
<script src="${pageContext.request.contextPath}/js/preventDuplicateTabs.js"></script>
        <link rel="stylesheet" href="layout/css/login.css" type="text/css" />

        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" media="screen" />
        <link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ui.dropdownchecklist.themeroller.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/dhtmlgoodies_calendar.js"></script>
        <script language="JavaScript" src='${pageContext.request.contextPath}/js/browserSniffer.js'></script>
        <script language="JavaScript" src='${pageContext.request.contextPath}/js/validation.js'></script>
        <script language="JavaScript" src='${pageContext.request.contextPath}/js/dynCalendar.js'></script>
        <script language="JavaScript" src='${pageContext.request.contextPath}/js/alert_message.js'></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/greybox/AJS.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/greybox/gb_scripts.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/greybox/gb_scripts.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Master_290414.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"  type="text/css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>



<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/Master_290414.js"></script>
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<script type="text/javascript" type="text/javascript" >

    $(function() {
        var ttdate="";
        var tmon="<%=month%>";
        var tdays="<%=day%>";
        if(eval(tmon)<10)
            tmon="0"+tmon;

        if(eval(tdays)<10)
            tdays="0"+tdays;

        ttdate=+tdays+'/'+tmon+ '/'+<%=year%>;
        //$( ".datepicker" ).datepicker();
        $( ".datepicker" ).datepicker({ dateFormat: "dd/mm/yy"}).datepicker("setDate", ttdate);
        
    });
</script>
        <script language="javascript" type="text/javascript">

            var win = null;
            var winNameArr = new Array();
            var counterWindow = 0;


            function MM_openBrWindow(theURL, winName, features)
            {
                if (win)
                {
                    for (var x = 0; x < winNameArr.length; x++)
                    {
                        if (winNameArr[x] == winName)
                        {
                            win.close();
                            win = null;
                        }
                    }
                }
                winNameArr[counterWindow] = winName;
                win = window.open(theURL, winName, features);
            }

            function validnumber(obj) {
                var regExp = /^\d{0,200}$/g
                if (regExp.test(obj.value)) {
                    return true;
                } else {
                    document.getElementById("msg_saveFAILED").innerHTML ="Please enter only numeric value in " + obj.id + " field.";
                    obj.value = "";
                    obj.focus();
                    return false;
                }
            }

            function isAlphaNumeric(obj)
            {
                if (obj.value != "")
                {
                    var regExp = /^[A-Za-z0-9]{0,200}$/g;
                    if (regExp.test(obj.value)) {
                        return true;
                    }
                    else {
                        document.getElementById("msg_saveFAILED").innerHTML ="Please enter alphanumeric value only.";
                        obj.value = "";
                        obj.focus();
                        return false;
                    }
                }
            }

        function openIframePopup(pageURL, callBackUrl)
    {
        decoGreyboxLinks(pageURL, callBackUrl);
    }
    if (typeof String.prototype.trim != "function") {
        String.prototype.trim = function () {
        return this.replace(/^\s+|\s+$/g, '');
       };
     }
        </script>
    </head>
    <body>
        <div name="header">
            <tiles:insert attribute="header"/>
        </div>


      <div class="content">
            <tiles:insert attribute="body"/>
      </div> 
    </body>
</html>


