/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var obj = null;
var contextPath="/ITLDIS"
var sparex=new Array();
var partDesc=new Array();
var partNo=new Array();
var unitPrice=new Array();

function getSuggestionsPart4master(id,comp_obj,comp_type_obj,img_obj,rowId)
{
    var comp_objj=comp_obj;
    var comp_paramarr=new Array;
    var param_type= comp_type_obj.value;//alert(id+'2-'+comp_objj.value+'3--'+comp_type_obj.value+'-4-'+img_obj+'--'+rowId);
    if(comp_objj.value=='')
    {
        document.getElementById("msg_saveFAILED").innerHTML ="Please enter atleast one character of part number to get suggestions.";
        comp_objj.focus();
    }
    else
    {
        img_obj.style.visibility = "visible";
        obj=new act7(id, comp_paramarr,contextPath+"/masterAction.do?option=getPartNumberAjax&comptype="+param_type+"&partno="+comp_objj.value+"&t="+new Date().getTime(),img_obj,rowId);
        obj.act7_mouse_on_list = 0;
        obj.act7_removedisp();
        if(!obj.act7_display)
        {
            obj.act7_toid = setTimeout(function()
            {
                obj.act7_tocomplete.call(obj, -1);
            },
            25);

        }
        else
        {
            obj.act7_godown();
            obj.act7_caretmove = 1;
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
                    window.scrollTo(0,0);
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

function getSuggestionsPartDesc4master(id,compDesc_obj,compDesc_type_obj,img_obj,rowId)
{
    var comp_objj=compDesc_obj;
    var comp_paramarr=new Array;
    var param_type= compDesc_type_obj.value;//alert(''+comp_objj.value+'--'+compDesc_type_obj.value);
    if(comp_objj.value=='')
    {
        document.getElementById("msg_saveFAILED").innerHTML ="Please enter atleast one character of part description to get suggestions.";
        comp_objj.focus();
    }
    else
    {
        img_obj.style.visibility = "visible";
        obj=new act8(id,comp_paramarr,contextPath+"/masterAction.do?option=getPartDescAjax&comptype="+param_type+"&partDesc="+comp_objj.value+"&t="+new Date().getTime(),img_obj,rowId);
        obj.act8_mouse_on_list = 0;
        obj.act8_removedisp();
        if(!obj.act8_display)
        {
            obj.act8_toid = setTimeout(function()
            {
                obj.act8_tocomplete.call(obj, -1);
            },
            25);

        }
        else
        {
            obj.act8_godown();
            obj.act8_caretmove = 1;
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
                    document.getElementById("msg_saveFAILED").innerHTML="Same part description can not be enter twice";
                    partDesc[k].value="";
                    partNo[k].value="";
                    unitPrice[k].value="";
                    partDesc[k].focus();
                    window.scrollTo(0,0);
                    img_obj.style.visibility="hidden";
                    return false;
                }
                sparex.push(partDesc[k].value);
            }
        }

        comp_objj.focus();
    }
    return false;
}


