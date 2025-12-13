
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>



<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.struts.util.*" %>
<%@ page import="dao.LoginDAO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>ITLDIS</title>

        <link rel="stylesheet" href="layout/css/login.css" type="text/css" />
        <script type="text/javascript" src="layout/js/jquery.min.js"></script>
        <script type="text/javascript" src="layout/js/rotator.js"></script>
        <style>.left p{font-size:12px; font-weight:bold; padding:0 0 0 2px; margin:5px 0; text-align:left; color:#000; }</style>
       <script language="JavaScript">


            function detectPopupBlocker()
            {
             
                document.getElementById("user_id").focus();
                var myTest = window.open("about:blank", "", "directories=no,height=100,width=100,menubar=no,resizable=no,scrollbars=no,status=no,titlebar=no,top=0,left=0");

                if (!myTest) {
                    alert("Your Pop-up Blocker is Enabled. Please Add our site to your trusted sites.");
                    document.location.href = '${pageContext.request.contextPath}/login/Logout.jsp';
                    return false;
                } else {
                    myTest.close();
                    return true;
                }
            }

            function validateForm(obj){
            document.getElementById('log').innerHTML = '';
            var string1 = removeSpaces(document.getElementById('mainCaptcha').value);
            var string2 = removeSpaces(document.getElementById('txtInput').value);
            if(obj=='reset'){
                if (string1 != string2 || string2 == ""){
                    Captcha();
                    document.getElementById('log').innerHTML = '<span style="font-size:14px; padding: 0px;">Entered Invalid Captcha</span> ';
                    return false;
                }
            }else{
              Captcha();
            }
        }
        function Captcha(){
            var alpha = new Array('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0');
            var i;
            for (i=0;i<5;i++){
                var a = alpha[Math.floor(Math.random() * alpha.length)];
                var b = alpha[Math.floor(Math.random() * alpha.length)];
                var c = alpha[Math.floor(Math.random() * alpha.length)];
                var d = alpha[Math.floor(Math.random() * alpha.length)];
                var e = alpha[Math.floor(Math.random() * alpha.length)];
                var f = alpha[Math.floor(Math.random() * alpha.length)];
                //var g = alpha[Math.floor(Math.random() * alpha.length)];
            }
            var code = a + ' ' + b + ' ' + ' ' + c + ' ' + d + ' ' + e + ' '+ f;// + ' ' + g;
            document.getElementById("mainCaptcha").value = code;
            var colors = ["#eeeeee", "#eeeeee", "#eeeeee", "#eeeeee", "#eeeeee", "#eeeeee", "#eeeeee", "#eeeeee", ];
            var rand = Math.floor(Math.random() * colors.length);
            $('#mainCaptcha').css("background-color", colors[rand]);
        }
        function removeSpaces(string){
            return string.split(' ').join('');
        }

        </script>
    </head>

<%

//LinkedHashSet<LabelValueBean>  language=new LoginDAO().getLanguage();

//System.out.println("language"+language);



%>
    <body onload="detectPopupBlocker();validateForm('reset1');">
        



<div class="main">
		<div class="header">
  <div class="logo">
  <a href="#"><img src="layout/images/logo.gif" alt="logo" class="fl"/></a>
  </div>
 <div class="dealer"> <img src="layout/images/logo1.jpg" alt="logo" class="fl logoTag"/></div>
</div>
<div class="navbg"></div>

    <div class="content_cs">
    <div class="homeBanner">
      <div id="v1" class="area fl" style="display: block; z-index: 0 !important; position: relative;">
		<span class="rotator r1" style="left: 0px; top: 0px; position: absolute; display: none;"><img src="layout/images/img1.jpg" alt=""/></span>
		<!-- <span class="rotator r2" style="left: 0px; top: 0px; position: absolute;"><img src="layout/images/img2.jpg" alt=""/></span>
		<span class="rotator r3" style="left: 0px; top: 0px; position: absolute; display: none;"><img src="layout/images/img3.jpg" alt=""/></span>
		<span class="rotator r4" style="left: 0px; top: 0px; position: absolute; display: none;"><img src="layout/images/img4.jpg" alt=""/></span> -->
</div>
    </div>
                <div class="stockInfo">
                    <h2>Login </h2><hr/>
                    <div style="color: red" id="errors"><html:errors/></div>
                     <div style="margin-top: 0px;border-left:0px;margin-bottom:0px; color: #D8000C; background-color: #FFBABA; border-radius: 7px;" id="log"></div>
                    <div class="left">
                        <form id="form1" method="post" action="login.do" onsubmit="return validateForm('reset')">
                            <input type="hidden" name="option" value="login"/>

                            <p>User Name</p>
                            <input name="userid" id="user_id" type="text"  class="inputtext"/>

                            <p>Password</p>
                            <input name="password" type="password" class="inputtext" />

                            <p >Select Language</p>
                           
                            <%--<select name="language"  id="language"  class="selecttext" >
                                <c:set var="language" value='<%=language%>'/>
                                    <c:forEach items="${language}" var="dataList">
                                        <c:set var="default" value="${fn:split(dataList.value,'@@')}"/>
                                        <c:choose>
                                        <c:when test="${default[1] eq 'Y' }">
                                            <option value='${default[0]}' title='${dataList.label}' selected>${dataList.label}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value='${default[0]}' title='${dataList.label}' >${dataList.label}</option>
                                        </c:otherwise>
                                         </c:choose>
                                   </c:forEach>
                             </select>--%>
                               <select name="language" id="language"  class="selecttext"  >
                                <option value="1" title="English">English</option>
                              <%--   <option value="Hindi">Hindi</option>--%>
                            </select>
                                    <%--<label>Captcha <span style="color:#F00;font-size:20px">*</span>:</label>--%>
                                    <p><input type="text" class="inputtext" style="margin-right:2px;margin-bottom: 10px; width: 125px; height: 25px; font-size: 18px; font-weight: bolder; text-decoration: line-through; display: inline-block; background-color: rgb(240, 204, 255);" id="mainCaptcha" disabled=""><button class="btn btn-success" type="button" style="display: inline-block;" id="refresh" onclick="Captcha();">Reset</button>
                                    
                                    <p><input style="margin-top: -30px;width: 130px;" type="text" class="inputtext" placeholder="Enter captcha" name="captcha" id="txtInput" required=""/>
                                &nbsp;
                            
                         
                     <input style="margin-right:2px;margin-top: 10px;" name="Sign IN" type="submit"  class="submit_new " value="Sign In"  /></p>
                        </form>                    
                    </div>
                    
                </div>
               

            </div>

            <div class="footerBottom">
                <span class="leftFooter"><span class="fl" style="width:255px;">&#169; 2014  All right reserved by Sonalika International HD</span></span>

            </div>
        </div>
    </body>

</html>
