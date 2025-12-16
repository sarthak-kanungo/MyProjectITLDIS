<%-- 
    Document   : mnal_session_expired
    Created on : Jul 19, 2011, 12:39:32 PM
    Author     : avinash.pandey
--%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>MNAL-Workshop Manual</title>
<link href="/SCMS/css/config.css" rel="stylesheet" type="text/css" />
</head>
<script>
var myWidth = 0, myHeight = 0;
function form_submit()
{
  //alert(opener);
  getSize();
  //window.alert( 'Width = ' + myWidth );
  //window.alert( 'Height = ' + myHeight );
  if(myWidth < 800 || myHeight < 600)
  {
  window.moveTo(100,100);
  window.resizeTo(800,600);
  }
  document.session_expired_1_form.submit();
}

function getSize() {

  if( typeof( window.innerWidth ) == 'number' ) {
    //Non-IE
    myWidth = window.innerWidth;
    myHeight = window.innerHeight;
  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
    //IE 6+ in 'standards compliant mode'
    myWidth = document.documentElement.clientWidth;
    myHeight = document.documentElement.clientHeight;
  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
    //IE 4 compatible
    myWidth = document.body.clientWidth;
    myHeight = document.body.clientHeight;
  }

}
</script>
</head>
<body bgcolor="#f7f7e7" onload="form_submit();">
<form name="session_expired_1_form" action="/AMW-AuthEcat/common/amw_session_expiredLogin.jsp" target="_top">
</form>
</body>
</html>

