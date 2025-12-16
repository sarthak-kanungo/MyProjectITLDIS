/**
 *
 * @author dipti.kewlani
 */

function GetXmlHttpObject(){
    var objXmlHttp = null;
    if (navigator.userAgent.indexOf('Opera') >= 0){
        xmlHttp=new XMLHttpRequest();
        return xmlHttp;
    }
    if (navigator.userAgent.indexOf('MSIE') >= 0){
        var strName = 'Msxml2.XMLHTTP';
        if (navigator.appVersion.indexOf('MSIE 5.5') >= 0){
            strName = 'Microsoft.XMLHTTP';
        }
        try{
            objXmlHttp = new ActiveXObject(strName);
            //objXmlHttp.onreadystatechange = handler;
            return objXmlHttp;
        }
        catch(e){
            alert('Your Browser Does Not Support Ajax');
            return;
        }
    }
    if (navigator.userAgent.indexOf('Mozilla') >= 0){
        objXmlHttp = new XMLHttpRequest();
        return objXmlHttp;
    }
}

function LTrim(str){
    if(str==null)return null;
    else if(str.length==0)return null;
    for(var i=0;str.charAt(i)==" ";i++);
        return str.substring(i,str.length);
}

function RTrim(str){
    if(str==null)return null;
    else if(str.length==0)return null;
    for(var i=str.length-1;str.charAt(i)==" ";i--);
        return str.substring(0,i+1);
}

function Trim(str){
    return LTrim(RTrim(str));
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

