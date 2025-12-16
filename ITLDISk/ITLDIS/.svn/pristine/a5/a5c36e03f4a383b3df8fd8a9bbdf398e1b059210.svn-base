
var obj = null;
var contextPath="/ITLDIS"
var sparex=new Array();
var partDesc=new Array();
var partNo=new Array();
var unitPrice=new Array();

function getSuggestionsPart(id,comp_obj,comp_type_obj,img_obj,rowId)
{
    var comp_objj=comp_obj;
    var comp_paramarr=new Array;
    var param_type= comp_type_obj.value;

    if(comp_objj.value.length<4){
        alert(partNosugg_validation_msg);
        comp_objj.focus();
        return false;
    }

   
    else
    {
        img_obj.style.visibility = "visible";
        obj=new actb(id, comp_paramarr,contextPath+"/serviceProcess.do?option=getPartNumberAjax&comptype="+param_type+"&partno="+comp_objj.value+"&t="+new Date().getTime(),img_obj,rowId);
        obj.actb_mouse_on_list = 0;
        obj.actb_removedisp();
        if(!obj.actb_display)
        {
            obj.actb_toid = setTimeout(function()
            {
                obj.actb_tocomplete.call(obj, -1);
            },
            25);

        }
        else
        {
            obj.actb_godown();
            obj.actb_caretmove = 1;
        }
        partNo=document.getElementsByName("partNo")
        partDesc=document.getElementsByName("partDesc")
        unitPrice=document.getElementsByName("unitPrice")
        sparex=new Array();
        for(var k=0;k<partNo.length;k++){
            if(partNo[k].value!='')
            {
                var res1=true;
                for(var m=0;m<sparex.length;m++)
                {
                    if(sparex[m]==(partNo[k].value))
                    {
                        res1=false;
                    }
                }
                if(res1==false)
                {
                    document.getElementById("msg_saveFAILED").innerHTML="Same part number can not be enter twice";
                    partNo[k].value="";
                    partDesc[k].value="";
                    unitPrice[k].value="";
                    partNo[k].focus();
                    //window.scrollTo(0,0);
                    img_obj.style.visibility="hidden";
                    return false;
                }
                sparex.push(partNo[k].value);
            }
        }
        comp_objj.focus();
    }
    return false;
} 
function getSuggestionsPartDesc(id,compDesc_obj,compDesc_type_obj,img_obj,rowId)
{
    var comp_objj=compDesc_obj;
    var comp_paramarr=new Array;
    var param_type= compDesc_type_obj.value;
    if(comp_objj.value=='')
    {
        document.getElementById("msg_saveFAILED").innerHTML ="Please enter atleast one character of part description to get suggestions.";
        comp_objj.focus();
    }
    else
    {
        img_obj.style.visibility = "visible";
        obj=new actb1(id,comp_paramarr,contextPath+"/serviceProcess.do?option=getPartDescAjax&comptype="+param_type+"&partDesc="+comp_objj.value+"&t="+new Date().getTime(),img_obj,rowId);
        obj.actb1_mouse_on_list = 0;
        obj.actb1_removedisp();
        if(!obj.actb1_display)
        {
            obj.actb1_toid = setTimeout(function()
            {
                obj.actb1_tocomplete.call(obj, -1);
            },
            25);

        }
        else
        {
            obj.actb1_godown();
            obj.actb1_caretmove = 1;
        }
        partDesc=document.getElementsByName("partDesc")
        partNo=document.getElementsByName("partNo")
        unitPrice=document.getElementsByName("unitPrice")
        sparex=new Array();
        for(var k=0;k<partDesc.length;k++){
            if(partDesc[k].value!='')
            {
                var res1=true;
                for(var m=0;m<sparex.length;m++)
                {
                    if(sparex[m]==(partDesc[k].value))
                    {
                        res1=false;
                    }
                }
                if(res1==false)
                {
//                    document.getElementById("msg_saveFAILED").innerHTML="Same part description can not be enter twice";
//                    partDesc[k].value="";
//                    partNo[k].value="";
//                    unitPrice[k].value="";
//                    partDesc[k].focus();
//                    window.scrollTo(0,0);
//                    img_obj.style.visibility="hidden";
//                    return false;
                }
                sparex.push(partDesc[k].value);
            }
        }

        comp_objj.focus();
    }
    return false;
}
var sparex=new Array();
var lubesDesc=new Array();
var lubesNo=new Array();
var lubesUnitPrice=new Array();
function getSuggestionsLubesPart(id,comp_obj,comp_type_obj,img_obj,rowId)
{
    
    var comp_objj=comp_obj;
    var comp_paramarr=new Array;
    var param_type= comp_type_obj.value;
    if(comp_obj.value.length<4){
        alert(partNosugg_validation_msg);
       
        comp_obj.focus();
        return false;
    }
    
    else
    {
        img_obj.style.visibility = "visible";
        obj=new actd2(id, comp_paramarr,contextPath+"/serviceProcess.do?option=getPartNumberAjax&comptype="+param_type+"&partno="+comp_objj.value+"&t="+new Date().getTime(),img_obj,rowId);
        obj.actd2_mouse_on_list = 0;
        obj.actd2_removedisp();
        if(!obj.actd2_display)
        {
            obj.actd2_toid = setTimeout(function()
            {
                obj.actd2_tocomplete.call(obj, -1);
            },
            25);

        }
        else
        {
            obj.actd2_godown();
            obj.actd2_caretmove = 1;
        }
        lubesNo=document.getElementsByName("lubesNo")
        lubesDesc=document.getElementsByName("lubesDesc")
        lubesUnitPrice=document.getElementsByName("lubesUnitPrice")
        sparex=new Array();
        for(var k=0;k<lubesNo.length;k++){
            if(lubesNo[k].value!='')
            {
                var res1=true;
                for(var m=0;m<sparex.length;m++)
                {
                    if(sparex[m]==(lubesNo[k].value))
                    {
                        res1=false;
                    }
                }
                if(res1==false)
                {
                    document.getElementById("msg_saveFAILED").innerHTML=lubeunique_validation_msg;
                    lubesNo[k].value='';
                    lubesDesc[k].value='';
                    lubesUnitPrice[k].value='';
                    lubesNo[k].focus();
                    window.scrollTo(0,0);
                    img_obj.style.visibility="hidden";
                    return false;
                }

                sparex.push(lubesNo[k].value);
            }
        }
        comp_objj.focus();
    }
    return false;
}
function getSuggestionsLubesPartDesc(id,compDesc_obj,compDesc_type_obj,img_obj,rowId)
{
    var comp_objj=compDesc_obj;
    var comp_paramarr=new Array;
    var param_type= compDesc_type_obj.value;
    
    if(comp_objj.value=='')
    {
        document.getElementById("msg_saveFAILED").innerHTML ="Please enter atleast one character of part description to get suggestions in Lubes.";
        comp_objj.focus();
    }
    else
    {
        img_obj.style.visibility = "visible";
        obj=new actd3(id,comp_paramarr,contextPath+"/serviceProcess.do?option=getPartDescAjax&comptype="+param_type+"&partDesc="+comp_objj.value+"&t="+new Date().getTime(),img_obj,rowId);
        obj.actd3_mouse_on_list = 0;
        obj.actd3_removedisp();
        if(!obj.actd3_display)
        {
            obj.actd3_toid = setTimeout(function()
            {
                obj.actd3_tocomplete.call(obj, -1);
            },
            25);

        }
        else
        {
            obj.actd3_godown();
            obj.actd3_caretmove = 1;
        }
        lubesDesc=document.getElementsByName("lubesDesc")
        lubesNo=document.getElementsByName("lubesNo")
        lubesUnitPrice=document.getElementsByName("lubesUnitPrice")
        sparex=new Array();
        for(var k=0;k<lubesDesc.length;k++){
            if(lubesDesc[k].value!='')
            {
                var res1=true;
                for(var m=0;m<sparex.length;m++)
                {
                    if(sparex[m]==(lubesDesc[k].value))
                    {
                        res1=false;
                    }
                }
                if(res1==false)
                {
//                    document.getElementById("msg_saveFAILED").innerHTML="Same part description can not be enter twice in Lubes";
//                    lubesDesc[k].value='';
//                    lubesNo[k].value='';
//                    lubesUnitPrice[k].value='';
//                    lubesDesc[k].focus();
//                    window.scrollTo(0,0);
//                    img_obj.style.visibility="hidden";
//                    return false;
                }
                sparex.push(lubesDesc[k].value);
            }
        }
        comp_objj.focus();
    }
    return false;


}


function getVinDetails(id,comp_obj,img_obj,jctype)
{
  if(comp_obj.value.length>=3){

        document.getElementById("Engine Number").value="";
        document.getElementById("Sale Date").value="";
        document.getElementById("Registration No.").value="";
        document.getElementById("Service Booklet No.").value="";
        document.getElementById("Model Family").value="";
        document.getElementById("Model Code").value="";
        document.getElementById("Model Family Desc").value="";
        document.getElementById("Model Code Desc.").value="";
     
    var comp_objj=comp_obj;
    var comp_paramarr=new Array;
    var rowId="";
    if(comp_objj.value=='')
    {
        document.getElementById("msg_saveFAILED").innerHTML ="Please enter atleast one character of Chassis No  number to get suggestions.";
        comp_objj.focus();
    }
    else
    {
        img_obj.style.visibility = "visible";
        obj=new actd4(id, comp_paramarr,contextPath+"/serviceProcess.do?option=getvinNumberAjax&vinNo="+comp_objj.value+"&jctype="+jctype+"&t="+new Date().getTime(),img_obj,rowId);
        obj.actd4_mouse_on_list = 0;
        obj.actd4_removedisp();
        // alert(obj.actd4_tocomplete.call(obj, -1));
        if(!obj.actd4_display)
        {
            
            obj.actd4_toid = setTimeout(function()
            {
                obj.actd4_tocomplete.call(obj, -1);
            },
            25);

        }
        else
        {
          
            obj.actd4_godown();
            obj.actd4_caretmove = 1;
        }




    
        comp_objj.focus();
    }
    return false;
}
else{

    alert(autochasissno_validation_msg);

}
}
function getVinDetails_install(id,comp_obj,img_obj)
{
        document.getElementById("Engine Number").value="";
        document.getElementById("Sale Date").value="";
        document.getElementById("Registration No.").value="";
        document.getElementById("Service Booklet No.").value="";
        document.getElementById("Model Family").value="";
        document.getElementById("Model Code").value="";
        document.getElementById("Model Family Desc").value = "";
        document.getElementById("Model Code Desc.").value = "";

        document.getElementById("Customer Name").value = "";
        document.getElementById("Village").value = "";
        document.getElementById("Taluka").value = "";
        document.getElementById("tehsil").value = "";
        document.getElementById("District").value = "";
        document.getElementById("Pin Code").value = "";
        document.getElementById("State").value = "";
        document.getElementById("Country").value = "";
        document.getElementById("Mobile Phone").value = "";
        document.getElementById("Landline").value ="";
        document.getElementById("E-mail ID").value = "";
        document.getElementById("Size of Land Holding").value = "";
        document.getElementById("Main Crops").value = "";
        document.getElementById("Occupation").value="";
    var comp_objj=comp_obj;
    var comp_paramarr=new Array;
    var rowId="";
    if(comp_objj.value=='')
    {
        document.getElementById("msg_saveFAILED").innerHTML ="Please enter atleast one character of Chassis No  number to get suggestions.";
        comp_objj.focus();
    }
    else
    {
        img_obj.style.visibility = "visible";
        obj=new actd5(id, comp_paramarr,contextPath+"/installProcess.do?option=getvinNumberAjax&vinNo="+comp_objj.value+"&t="+new Date().getTime(),img_obj,rowId);
        obj.actd5_mouse_on_list = 0;
        obj.actd5_removedisp();
        // alert(obj.actd5_tocomplete.call(obj, -1));
        if(!obj.actd5_display)
        {
            
            obj.actd5_toid = setTimeout(function()
            {
                obj.actd5_tocomplete.call(obj, -1);
            },
            25);

        }
        else
        {
          
            obj.actd5_godown();
            obj.actd5_caretmove = 1;
        }

        comp_objj.focus();
    }
    return false;
}
function getVinDetails_PDI(id,comp_obj,img_obj)
{
  

    var comp_objj=comp_obj;
    var comp_paramarr=new Array;
    var rowId="";
    if(comp_objj.value=='')
    {
        document.getElementById("msg_saveFAILED").innerHTML ="Please enter atleast one character of Chassis No  number to get suggestions.";
        comp_objj.focus();
    }
    else
    {
        img_obj.style.visibility = "visible";
        obj=new actd6(id, comp_paramarr,contextPath+"/pdiServiceProcess.do?option=getvinNumberAjax&vinNo="+comp_objj.value+"&t="+new Date().getTime(),img_obj,rowId);
        obj.actd6_mouse_on_list = 0;
        obj.actd6_removedisp();
        // alert(obj.actd6_tocomplete.call(obj, -1));
        if(!obj.actd6_display)
        {
            
            obj.actd6_toid = setTimeout(function()
            {
                obj.actd6_tocomplete.call(obj, -1);
            },
            25);

        }
        else
        {
          
            obj.actd6_godown();
            obj.actd6_caretmove = 1;
        }
 comp_objj.focus();
    }
    return false;
}