<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.text.DateFormat"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%

            
            String mainURL = (String) session.getAttribute("mainURL");
            String imagesURL = (String) session.getAttribute("imagesURL");
            java.util.Date sysDate = new java.util.Date();
            DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
            String todaysDate = df.format(sysDate);
            String contextPath = request.getContextPath();


%>


<style type="text/css">
    <!--
    .login-text{font-family: Verdana, Arial, Helvetica, sans-serif;
                font-size: 10px;
                color: #FFFFFF;
                padding:0 0 0 10px;
    }
    .Search{font-family: Verdana, Arial, Helvetica, sans-serif;
            font-size: 12px;
            color: #FFFFFF;
            padding:0 0 0 5px;
    }
    .textfield{
        border:1px solid #9A9166;
        width:150px;
        height:18px;
        font-size:11px;
        margin: 1px 10px 0 10px;
    }
    .text {font-family: Verdana, Arial, Helvetica, sans-serif;
           font-size: 9px;
           color: #2D4F8F;
           padding:4px 0 0 5px;
    }
    .scrolling-txt{font-family: Arial, Helvetica, sans-serif;
                   font-size: 10px;
                   color: #000000;
                   padding:0px 5px 0 5px;
                   letter-spacing:1px;
                   font-weight:bolder;
    }
    .link {
        font-family:Arial, Helvetica, sans-serif;
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        font-weight: bold;
        color: #FFFFFF;
        padding: 0px 0 0 15px;
        background-repeat:no-repeat;
        height:21px;
    }
    .link a {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        text-decoration:none;
        color:#FFFFFF;
    }
    .link a:hover {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11px;
        text-decoration: underline;
        color:#FFFFFF;
    }

    img { border: 0px; }
    font, th, td, p { font-family:Arial, Helvetica, sans-serif; }
    a:link { color: #2862B3; text-decoration: none; }
    a:active { color: #2862B3 }
    a:visited { color: #2862B3; text-decoration: none; }
    a:hover { color: #dd6900; text-decoration: underline; }

    .gen, .poll { font-size: 12px }
    .genmed, .name { font-size: 11px; }
    .gensmall, .postdetails { font-size: 10px; }
    .strong { font-weight: bold; }
    -->
</style>
<SCRIPT LANGUAGE=JavaScript>
    function newImage(arg) {
        if (document.images) {
            rslt = new Image();
            rslt.src = arg;
            return rslt;
        }
    }
    function GetXmlHttpObject()
    {
        var objXmlHttp = null;
        if (navigator.userAgent.indexOf('Opera') >= 0)
        {
            xmlHttp=new XMLHttpRequest();
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
            catch(e)
            {
                alert('Your Browser Does Not Support Ajax');
                return;
            }
        }
        if (navigator.userAgent.indexOf('Mozilla') >= 0)
        {
            objXmlHttp = new XMLHttpRequest();
            return objXmlHttp;
        }
    }
    function changeImages() {
        if (document.images && (preloadFlag == true)) {
            for (var i=0; i<changeImages.arguments.length; i+=2) {
                document[changeImages.arguments[i]].src = changeImages.arguments[i+1];
            }
        }
    }

    var preloadFlag = false;
    //function preloadImages() {
       // if (document.images) {
            //header_06_ImageMap_01_over = newImage(<--%=imagesURL%>topHeader/header_06-ImageMap_01_over.jpg);
            //preloadFlag = true;
        //}
    //}
    function Validate()
    {
        var iChars = '!@#$%^&()+=[]\\\';,/{}|:<>?'
        searchText=document.search.search_text.value.replace('\\\\','/');
        for (var i = 0; i < searchText.length; i++)
        {
            if (iChars.indexOf(searchText.charAt(i)) != -1)
            {
                alert (searchText.charAt(i)+' '+'Is Not Vaild.');
                document.search.search_text.value='';
                return false;
            }
        }
    }
    function Validate1()
    {
        var iChars = '!@#$%^&()+=[]\\\';,/{}|:<>?'
        searchText=document.search.search_text.value.replace('\\\\','/');
        for (var i = 0; i < searchText.length; i++)
        {
            if (iChars.indexOf(searchText.charAt(i)) != -1)
            {
                alert (searchText.charAt(i)+' '+'Is Not Vaild.');
                document.search.search_text.value='';
                return false;
            }
        }
        document.search.submit()
    }

    function confirmationApproval(){
        var answer = confirm('You have Message from ITL-DIS')
        if (answer){
            return true;
        }
        else{
            return false;
        }
    }

    function open_Msg()
    {
        var MyArgs = new Array();
        MyArgs[0] = 'false';
        var WinSettings = 'center:yes;resizable:no;dialogHeight:42px;dialogWidth:48px'
        var MyArgs = window.showModalDialog('v3_amw_checkUserActivity', MyArgs, WinSettings);
        //
    }



    function checkMsg(){
        var strURL =v3_amw_checkUserActivity
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            stateChanged();
        };
        xmlHttp.open(POST, strURL, true);
        xmlHttp.send(null);
    }

    function stateChanged()
    {
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == complete)
        {
            res = xmlHttp.responseText;
            if(res){
                var chk=0
                var tempInfo = res.split('@@');
                chk=tempInfo[0]
                if (chk==1){
                    if (tempInfo[1]!=0){
                        alert('You Have message From ITL-DIS')
                    }
                    else if (tempInfo[2]!=0){
                        alert('You Have Items in Cart')
                    }
                }
                else {
                    parent.location.href = '<%=contextPath%>/loginAction.do?target=doLogin'
                }
            }else{
                parent.location.href = '<%=contextPath%>/loginAction.do?target=doLogin'
            }
        }
    }


</SCRIPT>

</HEAD>
<BODY leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 ONLOAD="//preloadImages();"><%--onunload=window.open('v3_amw_signOut')--%>
    <table id="Table_01" width="100%" height="119" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="3%" height="21"><img src="<%=imagesURL%>/topHeaders/AMW_Home_01.jpg" width="37" height="21" alt=""></td>
            <td width="14%"><img src="<%=imagesURL%>/topHeaders/AMW_Home_02.jpg" width="172" height="21" alt=""></td>
            <td width="24%"><img src="<%=imagesURL%>/topHeaders/AMW_Home_03.jpg" height=21" alt=""></td>
            <td width="75%" valign=bottom align=center rowspan="3" background="<%=imagesURL%>/topHeaders/AMW_Home_04.jpg">
                &nbsp;
            </td>
            <td width=10% align=right><img src="<%=imagesURL%>/topHeaders/AMW_Home_05.jpg" width=127 height=21 alt=></td>
            <td width=8% align=right><img src="<%=imagesURL%>/topHeaders/AMW_Home_06.jpg" alt= width=99 height=21 border=0 usemap=#Map></td>
            <td width=11% align=right><img src="<%=imagesURL%>/topHeaders/AMW_Home_07.jpg"  width=98 height=21 border=0 usemap=#Map2></td>
            <td width=7% align=right><img src="<%=imagesURL%>/topHeaders/AMW_Home_08.jpg"  width=84 height=21 border=0 usemap=#Map3></td>
            <td width=6% colspan=2 align=right><img src="<%=imagesURL%>/topHeaders/AMW_Home_09.jpg"  width=69 height=21 border=0 usemap=#Map4></td>
        </tr>
        <tr>
            <td height=50><img src="<%=imagesURL%>/topHeaders/AMW_Home_10.jpg" width=37 height=50 ></td>
            <td><img src="<%=imagesURL%>/topHeaders/AMW_Home_11.jpg" width=172 height=50 ></td>
            <td><img src="<%=imagesURL%>/topHeaders/AMW_Home_12.jpg" height=50 ></td>
            <td align=right><img src="<%=imagesURL%>/topHeaders/AMW_Home_13.jpg" width=127 height=50 ></td>
            <%--<td align=right><img src="<%=imagesURL%>/topHeaders/AMW_Home_14.jpg" width=99 height=50></td>
            <td align=right background="<%=imagesURL%>/topHeaders/AMW_Home_15.jpg"></td>
            <td colspan=3 align=left valign=top background="<%=imagesURL%>/topHeaders/AMW_Home_16.jpg">
                <table width=100% border=0 cellpadding=0 cellspacing=0>
                	<tr>
                	  <td align=left class=text><strong>1.0.0 </strong></td>
                	</tr>
                 	<tr>
                	  <td align=left class=text><strong>0</strong></td>
                	</tr>
                	<tr>
                    	  <td align=left class=text><strong>9</strong></td>
                	</tr>
                  </table>
            </td>--%>
        </tr>
        <tr>
            <td height=29><img src="<%=imagesURL%>/topHeaders/AMW_Home_17.jpg" width=37 height=29 ></td>
            <td><img src="<%=imagesURL%>/topHeaders/AMW_Home_18.jpg" width=172 height=29  ></td>
            <td><img src="<%=imagesURL%>/topHeaders/AMW_Home_19.jpg" height=29 ></td>
            <td colspan=6 align=right background="<%=imagesURL%>/topHeaders/AMW_Home_20.jpg">
             <marquee class=scrolling-txt scrollamount=3>Welcome to Asia Motor Works</marquee>
            </td>
       </tr>
  <tr>
    <td height=24 colspan=10 background="<%=imagesURL%>/topHeaders/1pix.jpg">
        <table width=100% border=0 cellspacing=0 cellpadding=0>
            <tr>
                <td width=35% colspan=2 valign=middle class=login-text>Welcome <b>${sessionScope.username}</b></td>
                <td width=20% colspan=2>&nbsp;</td>
                <td width=23% colspan=2 >&nbsp;</td>
                <td width=22% align=right class=login-text><%=todaysDate %>&nbsp;&nbsp;</td>
            </tr>
        </table>
    </td>
</tr>
</table>
<map name=Map>
    <%--<area shape=rect coords=51,3,85,17 href="<%=contextPath%>/common/amw_openIE.jsp?url=<%= mainURL%>/dealer/hlp/index.html" target=_blank>
          <area shape=rect coords=51,3,85,17 href="<%=mainURL%>/dealer/hlp/index.html" target=_blank>--%>
    <area shape=rect coords=51,3,85,17 href="#" >
          <area shape=rect coords=51,3,85,17 href="#">
</map>
<map name=Map2>
    <area shape=rect coords=11,3,84,17 href="mailto:helpdesk@hitechesoft.com;">
</map>
<map name=Map3>
    <area shape=rect coords=9,1,75,18 href='<%=contextPath%>/common/amw_Contact_US.jsp' target = right>
</map>
<map name=Map4>
    <area shape=rect coords=8,2,56,17 href="<%=contextPath%>/LogoutAction.do"  target="_top" style='text-decoration:none' >
</map>
<map name=Map5><area shape=rect coords=1,1,23,20 href=# onClick ='Validate1()'></map></body>
</BODY>
</HTML>

