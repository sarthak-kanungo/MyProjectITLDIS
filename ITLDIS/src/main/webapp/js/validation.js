
function showmenu(str)
{
    var divStr='div'+str;
    var iframeStr='Iframe'+str;
    parent.frames[1].document.getElementById(divStr).style.visibility='visible';
    parent.frames[1].document.getElementById(iframeStr).style.visibility='visible';
}

function hideMenu(str)
{
    var divStr='div'+str;
    var iframeStr='Iframe'+str;
    parent.frames[1].document.getElementById(divStr).style.visibility='hidden';
    parent.frames[1].document.getElementById(iframeStr).style.visibility='hidden';
}

function MM_openBrWindow(theURL,winName,features)
{ 
    window.open(theURL,winName,features);
}

function isNotEmpty(elem, display_name)
{
    var str = elem.value;
    str=TrimAll(str)
    if(str == null || str.length == 0)
    {
        alert('Please fill in the '+display_name+' field.')
        elem.focus()
        return false
    } 
    else 
    {
        return true;
    }
}
function isNotEmptyCust(elem, display_name)
{
    var str = elem.value;
    str=TrimAll(str)
    if(str == null || str.length == 0)
    {
        document.getElementById("msg_saveFAILED").style.display = "";
        document.getElementById("msg_saveFAILED").innerHTML="Please fill in the "+display_name+" field."
        elem.focus()
        return false
    }
    else
    {
        return true;
    }
}
function isNotEmpty1(elem, display_name)
{
    var str = elem.value;
    str=TrimAll(str)
    if(str == null || str.length == 0)
    {
        alert('Please fill in the '+display_name+' field.')
        elem.focus()
        return true
    } 
    else 
    {
        return false;
    }
}


function wildcharCheck(ff)
{	
    var str=ff.value
    var len=str.length
    var index = 0
    var err = 0
    for(var i=0;i<len;i++)
    {
        if((str.charAt(i)=='\"')|| (str.charAt(i)=='\'')|| (str.charAt(i)=='\\'))
        {
            err = 1
            index=i
        }
    }
    if(err==1)
    {
        alert('Wildcard found in text')
        ff.value=str.substring(0,index)+str.substring(index+1,str.length)
        return false
    }
    else
    {
        return true
    }
}

function isChosen(select_choice,display_name, select_other)
{
    if (select_choice.options[select_choice.selectedIndex].value == 'Other')
    {
        if (isNotEmpty(select_other,'Other'))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    else
    {
        return true;
    }
}

function isChosen1(select_choice,select_other)
{
	 
    if (select_choice.value=='Select Here')
    {
        alert('Please select '+select_other);
        return false;

    }
    else
    {
        return true;
    }
}
function isChosenCust(select_choice,select_other)
{

    if (select_choice.value=='')
    {
        document.getElementById("msg_saveFAILED").style.display = "";
        document.getElementById("msg_saveFAILED").innerHTML="Please select "+select_other+"";
        return false;

    }
    else
    {
        return true;
    }
}

function isEMailAddr(elem)
{
    var str = elem.value;
    str=TrimAll(str)
    str = str.toLowerCase( );
    if (str=='na')
        return true
    else
    {
        if (str.indexOf("@") > 1) {
            var addr = str.substring(0, str.indexOf("@"));
            var domain = str.substring(str.indexOf("@") + 1, str.length);
            if (domain.indexOf(".") == -1)
            {
                alert("Verify the domain portion of the email address.");
                elem.focus();
                return false;
            }
            // parse address portion first, character by character
            for (var i = 0; i < addr.length; i++) {
                oneChar = addr.charAt(i).charCodeAt(0);
                // dot or hyphen not allowed in first position; dot in last
                if ((i == 0 && (oneChar == 45 || oneChar == 46))  || 
                    (i == addr.length - 1 && oneChar == 46)) {
                    alert("Verify the user name portion of the email address.");
                    elem.focus();
                    return false;
                }
                // acceptable characters (- . _ 0-9 a-z)
                if (oneChar == 45 || oneChar == 46 || oneChar == 95 || 
                    (oneChar > 47 && oneChar < 58) || (oneChar > 96 && oneChar < 123)) {
                    continue;
                } else {
                    alert("Verify the user name portion of the email address.");
                    elem.focus();
                    return false;
                }
            }
            for (i = 0; i < domain.length; i++) {
                oneChar = domain.charAt(i).charCodeAt(0);
                if ((i == 0 && (oneChar == 45 || oneChar == 46)) || 
                    ((i == domain.length - 1  || i == domain.length - 2) && oneChar == 46)) {
                    alert("Verify the domain portion of the email address.");
                    elem.focus();
                    return false;
                }
                if (oneChar == 45 || oneChar == 46 || oneChar == 95 || 
                    (oneChar > 47 && oneChar < 58) || (oneChar > 96 && oneChar < 123)) {
                    continue;
                } else {
                    alert("Verify the domain portion of the email address.");
                    elem.focus();
                    return false;
                }
            }
            return true;
        }
        alert("The email address may not be formatted correctly. Please verify.");
        elem.focus();
        return false;
    }
}

function integer_check(elem,display_name)
{
    var str=elem.value
    if (isNaN(str))
    {
        alert("Please enter valid "+display_name+"")
        elem.value=''
        elem.focus();
        return false
    }
    else
    {
        return true
    }
	
}

function integer_check_1(elem)
{
    var str=elem.value
    if (isNaN(str))
    {
        alert("Please enter numeric value only")
        elem.value=''
        elem.focus();
        return false
    }
    else
    {
        return true
    }
	
}

function LTrim(str)
{
    if(str==null)
    {
        return null;
    }
    for(var i=0;str.charAt(i)==" ";i++);
    return str.substring(i,str.length);
}

function RTrim(str)
{
    if(str==null)
    {
        return null;
    }
    for(var i=str.length-1;str.charAt(i)==" ";i--);
    return str.substring(0,i+1);
}

function Trim(str)
{
    return LTrim(RTrim(str));
}

function trim(str)
{
    return LTrim(RTrim(str));
}

function LTrimAll(str)
{
    if(str==null)
    {
        return str;
    }
    for(var i=0;str.charAt(i)==" " || str.charAt(i)=="\n" || str.charAt(i)=="\t";i++);
    return str.substring(i,str.length);
}

function RTrimAll(str)
{
    if(str==null)
    {
        return str;
    }
    for(var i=str.length-1;str.charAt(i)==" " || str.charAt(i)=="\n" || str.charAt(i)=="\t";i--);
    return str.substring(0,i+1);
}

function TrimAll(str)
{
    return LTrimAll(RTrimAll(str));
}


function trimTextArea(value)
{
    // If it matches leading and trailing spaces
    var matchSpace = /^\s+(.*?)\s+$/;
    return value.replace(matchSpace, "$1");
}
 

function isNotEmptyWoFocus(elem, display_name)
{
    var str = elem.value;
    str=TrimAll(str)
    if(str == null || str == 'Click Button' || str.length == 0)
    {
        alert('Please fill in the '+display_name+' field.')
        return true
    } 
    else 
    {
        return false;
    }
}

function wildcharCheck_for_name(ff)
{	
    var str=ff.value
    var len=str.length
    var err = 0
    var index=0
    for(var i=0;i<len;i++)
    {
        if((str.charAt(i)=='`') || (str.charAt(i)=='@')|| (str.charAt(i)=='~') || (str.charAt(i)=='!') || (str.charAt(i)=='@') || (str.charAt(i)=='#') || (str.charAt(i)=='$') || (str.charAt(i)=='%') || (str.charAt(i)=='^') || (str.charAt(i)=='&') || (str.charAt(i)=='*') || (str.charAt(i)=='(') || (str.charAt(i)==')') || (str.charAt(i)=='-') || (str.charAt(i)=='=') || (str.charAt(i)=='+') || (str.charAt(i)=='') || (str.charAt(i)=='|') || (str.charAt(i)==':') || (str.charAt(i)==';') || (str.charAt(i)=='<') || (str.charAt(i)=='>') || (str.charAt(i)==',') || (str.charAt(i)=='?') || (str.charAt(i)=='/') || (str.charAt(i)=='\"')|| (str.charAt(i)=='\'') || (str.charAt(i)=='\\') || (str.charAt(i)=='\"')|| (str.charAt(i)=='\'')|| (str.charAt(i)=='\\'))
        {
            err = 1
            index=i
        }
    }
    if(err==1)
    {
        alert('Wildcard found in text')
        ff.value=str.substring(0,index)+str.substring(index+1,str.length)
        return false
    }
    else
    {
        return true
    }
}

function wildcharCheck_for_name1(ff,elem)
{	
    var str=ff.value
    var len=str.length
    var err = 0
    var index=0
    for(var i=0;i<len;i++)
    {
        if((str.charAt(i)=='`') || (str.charAt(i)=='@')|| (str.charAt(i)=='~') || (str.charAt(i)=='!') || (str.charAt(i)=='@') || (str.charAt(i)=='#') || (str.charAt(i)=='$') || (str.charAt(i)=='^') || (str.charAt(i)=='&') || (str.charAt(i)=='*') || (str.charAt(i)=='-') || (str.charAt(i)=='=') || (str.charAt(i)=='+') || (str.charAt(i)=='') || (str.charAt(i)=='|') || (str.charAt(i)==':') || (str.charAt(i)==';') || (str.charAt(i)=='<') || (str.charAt(i)=='>') || (str.charAt(i)=='?') || (str.charAt(i)=='/') || (str.charAt(i)=='\"')|| (str.charAt(i)=='\'') || (str.charAt(i)=='\\') || (str.charAt(i)=='\"')|| (str.charAt(i)=='\'')|| (str.charAt(i)=='\\'))
        {
            err = 1
            index=i
        }
    }
    if(err==1)
    {
        alert("Please Check "+elem)
        ff.focus();
        //ff.value=str.substring(0,index)+str.substring(index+1,str.length)
        return false
    }
    else
    {
        return true
    }
}

function formatNumber(T2, Precision) 
{ 
    var obj=T2
    //alert(obj.value);
    //var enabling_var='false'
	
    var num = new NumberFormat();
    num.setInputDecimal(".");
    num.setNumber(obj.value); 
    num.setPlaces(Precision);
    num.setSeparators(false, ",", ",");
    obj.value = num.toFormatted();
	
}

function echeck(elem)
{
  
    var str = elem.value;
    str = str.toLowerCase( );
    if (str.indexOf("@") > 1) {
        var addr = str.substring(0, str.indexOf("@"));
        var domain = str.substring(str.indexOf("@") + 1, str.length);
        if (domain.indexOf(".") == -1)
        {
            alert("Verify the domain portion of the Email address.");									
            elem.focus();
            return false;
        }
        // parse address portion first, character by character
        for (var i = 0; i < addr.length; i++) {
            oneChar = addr.charAt(i).charCodeAt(0);
            // dot or hyphen not allowed in first position; dot in last
            if ((i == 0 && (oneChar == 45 || oneChar == 46))  || 
                (i == addr.length - 1 && oneChar == 46)) {
                alert("Verify the user name portion of the Email address.");
                elem.focus();

                return false;
            }
            // acceptable characters (- . _ 0-9 a-z)
            if (oneChar == 45 || oneChar == 46 || oneChar == 95 || 
                (oneChar > 47 && oneChar < 58) || (oneChar > 96 && oneChar < 123)) {
                continue;
            } else {
                alert("Verify the user name portion of the Email address.");
                elem.focus();
                return false;
            }
        }
        for (i = 0; i < domain.length; i++) {
            oneChar = domain.charAt(i).charCodeAt(0);
            if ((i == 0 && (oneChar == 45 || oneChar == 46)) || 
                ((i == domain.length - 1  || i == domain.length - 2) && oneChar == 46)) {
                alert("Verify the domain portion of the Email address.");
                elem.focus();
                return false;
            }
            if (oneChar == 45 || oneChar == 46 || oneChar == 95 || 
                (oneChar > 47 && oneChar < 58) || (oneChar > 96 && oneChar < 123)) {
                continue;
            } else {
                alert("Verify the domain portion of the Email address.");
                elem.focus();

                return false;
            }
        }
        return true;
    }
    alert("Email address may not be formatted correctly. Please verify.");
    elem.focus();
    return false;
							
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

function checkInteger(obj)
{
    var elem_val=obj.value;
    elem_val=TrimAll(elem_val);

    if(isNaN(elem_val))
    {
        alert("Please Enter only Numeric Value.");
        //obj.value='';
        obj.focus();
        return false;
    }
    else
    {
        if(elem_val.indexOf(".")!=-1)
        {
            alert("Please Enter only Integer Value.");
            //obj.value='';
            obj.focus();
            return false;
        }
        if(elem_val.indexOf("-")!=-1)
        {
            alert("Please Enter only Positive Integer Value.");
            //obj.value='';
            obj.focus();
            return false;
        }
        if(elem_val.indexOf("+")!=-1)
        {
            alert("No Special Symbol allowed.");
            //obj.value='';
            obj.focus();
            return false;
        }
    }
    return true;
}

function checkDouble(obj)
{
    var elem_val=obj.value;
    elem_val=TrimAll(elem_val);
    
    if(isNaN(elem_val))
    {
        alert("Please Enter only Real Value.");
        //obj.value='';
        obj.focus();
        return false;
    }
    else
    {
        var index=elem_val.indexOf(".");
        if(index==-1)
        {
            alert("Please Enter only Real Value.");
            // obj.value='';
            obj.focus();
            return false;
        }

        if(elem_val.substring(index+1,elem_val.length)=='')
        {
            alert("Please Enter only valid Real Value.");
            // obj.value='';
            obj.focus();
            return false;
        }
        if(elem_val.indexOf("-")!=-1)
        {
            alert("Please Enter only Positive Real Value.");
            // obj.value='';
            obj.focus();
            return false;
        }
        if(elem_val.indexOf("+")!=-1)
        {
            alert("No Special Symbol allowed.");
            //obj.value='';
            obj.focus();
            return false;
        }
        
    }
    return true;
}

function checkNumber(obj)
{
    var elem_val=obj.value;
    elem_val=TrimAll(elem_val);

    if(isNaN(elem_val))
    {
        alert("Please Enter only Numeric Value.");
       
        obj.focus();
        return false;
    }
    else
    {
        var index=elem_val.indexOf(".");
        

        if(elem_val.substring(index+1,elem_val.length)=='')
        {
            alert("Please Enter only valid Numeric Value.");
          
            obj.focus();
            return false;
        }
        if(elem_val.indexOf("-")!=-1)
        {
            alert("No Special Symbol allowed.");
           
            obj.focus();
            return false;
        }
        if(elem_val.indexOf("+")!=-1)
        {
            alert("No Special Symbol allowed.");
         
            obj.focus();
            return false;
        }

    }
    return true;
}

function checkText(obj)
{
    var elem_val=obj.value;
    elem_val=TrimAll(elem_val);
    
    /**
     ***commented by Mina******
    var res=isProper(elem_val);
    if(res==false)
    {
       alert('Value should not contain any special characters. Allowed Characters are Underscore(_), Hyphen(-), Dot(.).');
       elem_val.value='';
       elem_val.focus();
       return false;
     }  */
    return true;
}

function checkBoolean(obj)
{
    var elem_val=obj.value;
    elem_val=TrimAll(elem_val);
    if(!(elem_val=='YES'||elem_val=='NO'))
    {
        alert("Value can either be YES or NO.");
        obj.value='';
        obj.focus();
        return false;
    }
   
    return true;
}


/**
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
}*/

//only (.), (_) and (-) is allowed.
function isProper(string) 
{
    if (!string) return false;
    var iChars = "=*|+!^,\\:<>[]{}`';()@&$#%~?><"+'"';

    for (var i = 0; i < string.length; i++) {
        if (iChars.indexOf(string.charAt(i)) != -1)
            return false;
    }
    return true;
}

//only (.)(,) (_) and (-) is allowed.
function isProperSpecial(string)
{
    if (!string) return false;
    var iChars = "=*|+!^\\:<>[]{}`';()@&$#%~?><//"+'"';

    for (var i = 0; i < string.length; i++) {
        if (iChars.indexOf(string.charAt(i)) != -1)
            return false;
    }
    return true;
}

//only (), / (.) and (-) is allowed.
function isProperSerial(string) 
{
    if (!string) return false;
    var iChars = "=*|_+!^,\\:<>[]{}`';@&$#%~?><"+'"';

    for (var i = 0; i < string.length; i++) {
        if (iChars.indexOf(string.charAt(i)) != -1)
            return false;
    }
    return true;
} 
//no special symbols are allowed.
function isProperAll(string) 
{
   
    if (!string) return false;
    
    var checkchar=0;
    for(var i=0; i<string.length;i++)
    {
           
        var chrCode = string.charCodeAt(i);
            
        if((chrCode>=48) && (chrCode<=57))  //0-9
        {
            checkchar=1;
        }
        else if((chrCode>=65) && (chrCode<=90)) //A-Z
        {
            checkchar=1
        }
        else if((chrCode>=97) && (chrCode<=122)) //a-z
        {
            checkchar=1
        }
        else if(chrCode==32 || chrCode==46) //space || .(dot)
        {
            checkchar=1
        }
        else
        {
            checkchar=0
            break;
        }
    }
    if(checkchar==0)
    {
        return false;
    }
    else
    {
        return true;
    }
} 
//only (_) and (-) is allowed.
function isProperComponent(string) 
{
    if (!string) return false;
    var checkchar=0;
    for(var i=0; i<string.length;i++)
    {
           
        var chrCode = string.charCodeAt(i);
        //alert(chrCode);
        if((chrCode>=48) && (chrCode<=57))
        {
            checkchar=1
        }
        else if((chrCode>=65) && (chrCode<=90))
        {
            checkchar=1
        }
        else if((chrCode>=97) && (chrCode<=122))
        {
            checkchar=1
        }
        else if((chrCode==45) || (chrCode==95) || (chrCode==32))
        {
            checkchar=1
        }
        else
        {
            checkchar=0
            break;
        }
    }
    if(checkchar==0)
    {
        return false;
    }
    else
    {
        return true;
    }
} 


//only _ and hypen is allowed
function checkWildCharOnBlur(elem)
{
    if(elem.value!='' && !isProperComponent(elem.value))
    {
        document.getElementById("msg_saveFAILED").innerHTML ="Special Symbols Found . Only (-) and (_) are Allowed. ";
        elem.value="";
        elem.focus();
        return false;
    }
    return true;
}
//only (_) and (-) and [] is allowed.
function isProperComponent1(string) 
{
    if (!string) return false;
    var checkchar=0;
    for(var i=0; i<string.length;i++)
    {
           
        var chrCode = string.charCodeAt(i);
        //alert(chrCode);
        if((chrCode>=48) && (chrCode<=57))
        {
            checkchar=1
        }
        else if((chrCode>=65) && (chrCode<=90))
        {
            checkchar=1
        }
        else if((chrCode>=97) && (chrCode<=122))
        {
            checkchar=1
        }
        else if((chrCode==45) || (chrCode==95) || (chrCode==32) || (chrCode==91) || (chrCode==93))
        {
            checkchar=1
        }
        else
        {
            checkchar=0
            break;
        }
    }
    if(checkchar==0)
    {
        return false;
    }
    else
    {
        return true;
    }
} 
//only (_) is allowed.
function isProperParameter(string) 
{
    if (!string) return false;
    var checkchar=0;  
    for(var i=0; i<string.length;i++)
    {
           
        var chrCode = string.charCodeAt(i);
        if((chrCode>=48) && (chrCode<=57))
        {
            checkchar=1;
        }
        else if((chrCode>=65) && (chrCode<=90))
        {
            checkchar=1
        }
        else if((chrCode>=97) && (chrCode<=122))
        {
            checkchar=1;
        }
        else if((chrCode==95) || (chrCode==32))
        {
            checkchar=1;
        }
        else
        {
            checkchar=0;
            break;
        }
    }
    if(checkchar==0)
    {
        return false;
    }
    else
    {
        return true;
    }
} 
//check for length 31.
function isValidCharacterLength(string) 
{
    if (!string) return false;
    if(string.length>31)
        return false;
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
function checkQuantity(qty) 
{
    if(isNaN(qty.replace('-','')))
        return false;
    else if(qty==0)
        return false;
    else if(qty.indexOf(".")!=-1)
        return false;
    else 
        return true;
}

function checkNumber(obj)
{
    var elem_val=obj.value;
    elem_val=TrimAll(elem_val);
    
    if(isNaN(elem_val))
    {
        alert("Please Enter only Numeric Value.");
        obj.value='';
        obj.focus();
        return false;
    }
    else
    {
        var index=elem_val.indexOf(".");
       
        if(elem_val.substring(index+1,elem_val.length)=='')
        {
            alert("Please Enter proper Numeric Value.");
            obj.value='';
            obj.focus();
            return false;
        }
        if(elem_val.indexOf("-")!=-1)
        {
            alert("Please Enter only Positive Real Value.");
            obj.value='';
            obj.focus();
            return false;
        }
        if(elem_val.indexOf("+")!=-1)
        {
            alert("No Special Symbol allowed.");
            obj.value='';
            obj.focus();
            return false;
        }
        
    }
    return true;
}
function parseZeroVal(str,index)
{
    //alert(str.value);
    if(!str)
        return false;
  
    var val=str.value;
      
      
     
    for(var i=0;i<=index;i++)
    {
        if(val.indexOf("0")==0)
        {
            
            val= val.replace("0",'');

        }
    }
     
   
    if(val=='')
    {
        str.value=0;
    }
    else
    {
        str.value=val;
    }
}

///////////////////disable right click////////////////
/*function clickIE() {if (document.all) {(message);return false;}}
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}

document.oncontextmenu=new Function("return false")*/



///////////////////////////Ajax Method////////////////////
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

////////End Of Ajax Method///////////////////////////////
function formatNumber(T2, Precision)
{
    var num = new NumberFormat();
    num.setInputDecimal('.');
    num.setNumber(T2);
    num.setPlaces(Precision);
    num.setSeparators(false, ',', ',');
    T2 = num.toFormatted();
    return T2;
}

function moveDualList( srcList, destList, moveAll )
{
                
    // Do nothing if nothing is selected
    if (  ( srcList.selectedIndex == -1 ) && ( moveAll == false )   )
    {
        return;
    }
    newDestList = new Array( destList.options.length );
    var len = 0;
    for( len = 0; len < destList.options.length; len++ )
    {
        if ( destList.options[ len ] != null )
        {
            newDestList[ len ] = new Option( destList.options[ len ].text, destList.options[ len ].value, destList.options[ len ].defaultSelected, destList.options[ len ].selected );
        }
    }
    for( var i = 0; i < srcList.options.length; i++ )
    {
        if ( srcList.options[i] != null && ( srcList.options[i].selected == true || moveAll ) )
        {
            // Statements to perform if option is selected
            // Incorporate into new list

            newDestList[ len ] = new Option( srcList.options[i].text, srcList.options[i].value, srcList.options[i].defaultSelected, srcList.options[i].selected );
            len++;
        }
    }
    // Sort out the new destination list
    // newDestList.sort( compareOptionValues );   // BY VALUES
    // newDestList.sort( compareOptionText );   // BY TEXT
    // Populate the destination with the items from the new array
    for ( var j = 0; j < newDestList.length; j++ )
    {
        if ( newDestList[ j ] != null )
        {
            destList.options[ j ] = newDestList[ j ];
        }
    }
    // Erase source list selected elements
    for( var i = srcList.options.length - 1; i >= 0; i-- )
    {
        if ( srcList.options[i] != null && ( srcList.options[i].selected == true || moveAll ) )
        {
            // Erase Source
            srcList.options[i].value = "";
            srcList.options[i].text  = "";
            srcList.options[i]       = null;
        }
    }
} // End of moveDualList()
//  End -->



function CommentsMaxLength(text,maxLength)
{
    var diff =  text.value.length-maxLength ;
   
    if (diff < 0){
        text.value = text.value.substring(0,maxLength);
        diff=0;
        document.getElementById("msg_saveFAILED").innerHTML = "Cannot be greater than " + maxLength + "";
    }
    document.getElementById("msg_saveFAILED").style.display = "";
    
}
function trimS(str) {
    return str.replace(/^\s+|\s+$/g,"");
}
function stringOnly(obj)
{
    var flag=true;
    var string=Trim(obj.value);
    var iChars = "|+!^\\`'&~?";

    for (var i = 0; i < string.length; i++) {
        if (iChars.indexOf(string.charAt(i)) != -1)
        {
            flag=false;

            break;
        }
    }
    if(!flag)
    {
        //alert("Spacial character not allow !!");
        document.getElementById("msg_saveFAILED").style.display = "";
        document.getElementById("msg_saveFAILED").innerHTML="Spacial characters are not allowed !!";
        window.scrollTo(0,0);//go to top of page
        obj.focus();
        return false;
    }
    else
    {
        clearERRMSG(obj);
        return true;
    }


}
function numeralsOnly(evt,objj)
{

    evt = (evt) ? evt : event;
    var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode :
        ((evt.which) ? evt.which : 0));
    if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode != 46)
    {
        document.getElementById("msg_saveFAILED").style.display = "";
        document.getElementById("msg_saveFAILED").innerHTML="Please specify numeric value only !!";
        //document.getElementById(elment_id).value="VALUE REQUIRED";
        //objj.className= "clrtxt";
        objj.focus();
        window.scrollTo(0,0);//go to top of page
        return false;
    }
    return true;
}
function clearERRMSG(obj)
{
    document.getElementById("msg_saveFAILED").innerHTML="";
    obj.className="text";
}
function validateFloat(field) {
    var floatValue = parseFloat(field.value);
    if (isNaN(floatValue))
        field.value = '0.0';
    else
        field.value = floatValue.toFixed(2);
}
function checkWhitStringCommon(obj)
{
    var flag=true;
    var string=Trim(obj.value);
    var iChars = "|+!^\\`'~?";

    for (var i = 0; i < string.length; i++) {
        if (iChars.indexOf(string.charAt(i)) != -1)
        {
            flag=false;

            break;
        }
    }
    if(!flag)
    {
        //alert("Spacial character not allow !!");
        document.getElementById("msg_saveFAILED").style.display = "";
        document.getElementById("msg_saveFAILED").innerHTML="Spacial characters semicolon and AND(') are not permitted.!!";
        window.scrollTo(0,0);//go to top of page
        obj.focus();
        return false;
    }
    else
    {
        clearERRMSG(obj);
        return true;
    }
}
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    return !(charCode > 31 &&  (charCode < 46 || charCode ==47 || charCode > 57));
}


function isNumberKeySpares(evt){

    var charCode = (evt.which) ? evt.which : event.keyCode
   // alert(charCode);
    return !(charCode > 31 &&  (charCode < 48 ||  charCode > 57));
}


function replaceall(str,replace,with_this)
{
    var str_hasil ="";
    var temp;

    for(var i=0;i<str.length;i++)
    {
        if (str[i] == replace)
        {
            temp = with_this;
        }
        else
        {
                temp = str[i];
        }

        str_hasil += temp;
    }

    return str_hasil;
}
function isProperSpaceNotA(string)
{

    if (!string) return false;

    var checkchar=0;
    for(var i=0; i<string.length;i++)
    {

        var chrCode = string.charCodeAt(i);

        if((chrCode>=48) && (chrCode<=57))  //0-9
        {
            checkchar=1;
        }
        else if((chrCode>=65) && (chrCode<=90)) //A-Z
        {
            checkchar=1
        }
        else if((chrCode>=97) && (chrCode<=122)) //a-z
        {
            checkchar=1
        }
        else
        {
            checkchar=0
            break;
        }
    }
    if(checkchar==0)
    {
        return false;
    }
    else
    {
        return true;
    }
}

function checkNumberPayment(obj)
{
    var elem_val=obj.value;
    elem_val=TrimAll(elem_val);

    if(isNaN(elem_val))
    {
        alert("Please Enter only Numeric Value.");

        obj.focus();
        return false;
    }
    else
    {
        var index=elem_val.indexOf(".");


        if(elem_val.substring(index+1,elem_val.length)=='')
        {
            alert("Please Enter only valid Numeric Value.");

            obj.focus();
            return false;
        }
        if(elem_val.indexOf("+")!=-1)
        {
            alert("No Special Symbol allowed.");

            obj.focus();
            return false;
        }

    }
    return true;
}
function checkNumberCust(obj)
{
    var elem_val=obj.value;
    elem_val=TrimAll(elem_val);

    if(isNaN(elem_val))
    {
        document.getElementById("msg_saveFAILED").style.display = "";
        document.getElementById("msg_saveFAILED").innerHTML="Please Enter only Numeric Value.";
        obj.focus();
        return false;
    }
    else
    {
        var index=elem_val.indexOf(".");


        if(elem_val.substring(index+1,elem_val.length)=='')
        {
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please Enter only valid Numeric Value.";
            obj.focus();
            return false;
        }
        if(elem_val.indexOf("-")!=-1)
        {
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="No Special Symbol allowed.";
            obj.focus();
            return false;
        }
        if(elem_val.indexOf("+")!=-1)
        {
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="No Special Symbol allowed.";
            obj.focus();
            return false;
        }

    }
    return true;
}

    function isProperSerialCustCode(string)
    {
        if (!string) return false;
        var iChars = "=_|!^,\\:<>[]{}`'.;&$#@~?><"+'" "';

        for (var i = 0; i < string.length; i++) {
            if (iChars.indexOf(string.charAt(i)) != -1)
                return false;
        }
        return true;
    }
    function isProperSerialTax(string)
    {
        if (!string) return false;
        var iChars = "=_|!^,\\:<>[]{}`'.;&$#@~?><"+'" "';

        for (var i = 0; i < string.length; i++) {
            if (iChars.indexOf(string.charAt(i)) != -1)
                return false;
        }
        return true;
    }

    function isProperSerialCust(string)
    {
        if (!string) return false;
        var iChars = "=|!^,\\:<>[]{}`';&$#@~?><"+'"';

        for (var i = 0; i < string.length; i++) {
            if (iChars.indexOf(string.charAt(i)) != -1)
                return false;
        }
        return true;
    }
    
   







        