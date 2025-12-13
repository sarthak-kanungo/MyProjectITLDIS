/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
function LTrimAll(str)
{
    if(str==null)
    {
        return str;
    }
    for(var i=0;str.charAt(i)==" " || str.charAt(i)=="\\n" || str.charAt(i)=="\\t";i++);
    return str.substring(i,str.length);
}
function RTrimAll(str)
{
    if(str==null)
    {
        return str;
    }
    for(var i=str.length-1;str.charAt(i)==" " || str.charAt(i)=="\\n" || str.charAt(i)=="\\t";i--);
    return str.substring(0,i+1);
}
function TrimAll(str)
{
    return LTrimAll(RTrimAll(str));
}


function validSpecialCharactor(obj)
{

    var objSpecExp=/[`'~!#$*(){}<>?,.|^+=";:%&\\@]/g;
    if(objSpecExp.test(obj))
    {

        return false;
    }
    return true;
}
function validSpecialCharactorLedger(obj)
{

    var objSpecExp=/[`'~!#$*(){}<>?|^+=";:%&\\@]/g;
    if(objSpecExp.test(obj))
    {

        return false;
    }
    return true;
}
function isProperParameter(string)
{
    if (!string) return false;
    var iChars = "=_-|+!^,\\:<>[]{}`';()@&$#%~?></"+'"';

    for (var i = 0; i < string.length; i++) {
        if (iChars.indexOf(string.charAt(i)) != -1)
            return false;
    }
    return true;
}
function checkDouble(obj)
{
    var elem_val=obj;
    elem_val=TrimAll(elem_val);

    if(isNaN(elem_val))
    {
        alert("Please enter numeric value only.");
        return false;
    }
    else
    {
        var index=elem_val.indexOf(".");
        //        if(index==-1)
        //        {
        //            //  alert("Please Enter only Real Value.");
        //            return false;
        //        }
        if(index != -1){
            if(elem_val.substring(index+1,elem_val.length)=='')
            {
                alert("Please Enter only valid Real Value.");
                return false;
            }
            if((elem_val.substring(index+1,elem_val.length)).length >'2')
            {
                alert("Only two digits allowed after decimal.");
                return false;
            }
        }
        var res=isProperParameter(elem_val);
        if(res==false)
        {
            alert("Please enter numeric value only.");
            return false;
        }

    }
    return true;
}

function trim(s)
{
    return rtrim(ltrim(s));
}


function ltrim(s)
{
    var l=0;
    while(l < s.length && s.charAt(l) == ' ')
    {
        l++;
    }
    return s.substring(l, s.length);
}


function rtrim(s)
{
    var r=s.length -1;
    while(r > 0 && s.charAt(r) == ' ')
    {
        r-=1;
    }
    return s.substring(0, r+1);
}

//no special symbols are allowed.
function isProperAll(string)
{
    if (!string) return false;
    var iChars = "=_-*|+./!^,\\:<>[]{}`';()@&$#%~?><"+'"';

    for (var i = 0; i < string.length; i++) {
        if (iChars.indexOf(string.charAt(i)) != -1)
            return false;
    }
    return true;
}
//no special symbols are allowed except the underscore(_).
function isProper_underscore(string)
{
    if (!string) return false;
    var iChars = "=-*|+./!^,\\:<>[]{}`';()@&$#%~?><"+'"';

    for (var i = 0; i < string.length; i++) {
        if (iChars.indexOf(string.charAt(i)) != -1)
            return false;
    }
    return true;
}
function checkInteger(obj)
{
    var elem_val=obj;
    elem_val=TrimAll(elem_val);

    if(isNaN(elem_val))
    {
        //alert("Please Enter only Numeric Value.");
        return false;
    }

    var index=elem_val.indexOf(".");

    if(index!=-1)
    {
        //alert("Please Enter only Integer Value.");
        return false;
    }

    var res=isProperAll(elem_val);
    if(res==false)
    {
        //alert("No Special Symbol allowed.");
        return false;
    }

    return true;
}

function getrefrenceno(dis){
    var referenceNo=dis.value;
    var url = contextPath+"/fTransaction/get_ref_AJAX.jsp?referenceNo=" + referenceNo ;
    xmlHttp = GetXmlHttpObject();
    xmlHttp.onreadystatechange = function()
    {
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            if (xmlHttp.responseText.indexOf("session_expired") != -1)
            {
                window.location.href = contextPath + "/session_expired.do";
            }
            else
            {
                res = trim(xmlHttp.responseText.trim());
                if(res!=''){
                    var response= confirm(res + "\n Do you want to continue?");
                    if(!response){
                        dis.value='';
                        dis.focus();
                        return false;
                    } else{
                        document.getElementById('form1').submit();
                    }
                }else{
                    document.getElementById('form1').submit();
                }
            }
        }
    };
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);

}

//string only
function stringOnlyValidation(string)
{
    if (!string) return false;
    var iChars = "0123456789";

    for (var i = 0; i < string.length; i++) {
        if (iChars.indexOf(string.charAt(i)) != -1)
            return false;
    }
    return true;

}

//check for space.
function checkSpace(string)
{
    if (!string) return false;
    var iChars = " ";

    for (var i = 0; i < string.length; i++) {
        if (iChars.indexOf(string.charAt(i)) != -1)
            return false;
    }
    return true;
}

//comparing date.

function compareDates(frmdate,date)
{
    var from=frmdate.value.split('/');
    var to=date.value.split('/');
    var fdate=from[2]+'/'+from[1]+'/'+from[0];
    var tdate=to[2]+'/'+to[1]+'/'+to[0];
    var fromdate=new Date(fdate);
    var todate=new Date(tdate);
    if(todate < fromdate)
    {
        alert('From date cannot be greater than To Date.');
        frmdate.focus();
        return false;
    }
    return true;
}

   function validNumberdigit(obj) {
        var regExp = /^[0-9]+$/g
        if (regExp.test(obj)) {
            return true;
        } else {
            return false;
        }
    }
    
    function onlyAlphabets(obj) {
        var regExp = /^[a-zA-Z ]+$/g
        if (regExp.test(obj)) {
            return true;
        } else {
            return false;
        }
    }
    function onlyAlphabetsWithDot(obj) {
        var regExp = /^[a-zA-Z. ]+$/g
        if (regExp.test(obj)) {
            return true;
        } else {
            return false;
        }
    }
    function emailIdValid(obj) {
        var regExp = /\S+@\S+\.\S+/g
        if (regExp.test(obj)) {
            return true;
        } else {
            return false;
        }
    }
