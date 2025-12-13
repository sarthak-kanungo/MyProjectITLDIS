
var obj = null;
var contextPath="/ITLDIS"

function getSuggestionsLubesPartDesc(id,compDesc_obj,compDesc_type_obj,img_obj,rowId)
{
    var comp_objj=compDesc_obj;
    var comp_paramarr=new Array;
    var param_type= compDesc_type_obj.value;
    var sparex=new Array();
    var lubesDesc=new Array();
    var lubesNo=new Array();
    var lubesUnitPrice=new Array();
    if(comp_objj.value=='')
    {
        document.getElementById("msg_saveFAILED").innerHTML ="Please enter atleast one Character of Part Description to get suggestions.";
        comp_objj.focus();
    }
    else
    {
        img_obj.style.visibility = "visible";
        obj=new actd3(id, comp_paramarr,contextPath+"/serviceProcess.do?option=getPartDescAjax&comptype="+comp_objj.value+"&partDesc=&t="+new Date().getTime(), img_obj,rowId);
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
                    document.getElementById("msg_saveFAILED").innerHTML="Same part description can not be enter twice";
                    lubesDesc[k].value='';
                    lubesNo[k].value='';
                    lubesUnitPrice[k].value='';
                    lubesDesc[k].focus();
                    window.scrollTo(0,0);
                    img_obj.style.visibility="hidden";
                    return false;
                }
                sparex.push(lubesDesc[k].value);
            }
        }
        comp_objj.focus();
    }
    return false;
}

