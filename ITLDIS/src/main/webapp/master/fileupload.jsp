<! DOCTYPE html PUBLIC "- / / W3C / / DTD HTML 4.01 Transitional / / EN" " 
<html> 
<head> 

<script language = "javascript"> 

function myFormCheck (theform) 
{ 
if (theform.theFile.value, == "") 
{ 
alert ("Please click the Browse button, select the file you want to upload!") 
theform.theFile.focus; 
return (false); 
} 
else 
{ 
str = theform.theFile.value; 
strs = str.toLowerCase (); 
lens = strs.length; 
extname = strs.substring (lens - 4, lens); 
the if (extname! = "xls") 
{ 
alert ("Please select the excel file!") 
return (false); 
} 

} 
} 
</script> 

<meta http-equiv = "Content-Type" content = "text / html; charset = GBK"> 
<title> Insert title here </title> 
</head> 
<body> 
<form name = "uploadform" action = "importdata.do" enctype = "multipart / form-data" method = post onsubmit = "return myFormCheck (this)"> 
<input type = "file" name = "theFile"> 
<input type = "submit" value = "Import"> 
</form> 

</body> 
</html>