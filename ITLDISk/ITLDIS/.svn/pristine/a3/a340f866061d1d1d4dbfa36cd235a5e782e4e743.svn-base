
var obj = null;
var contextPath="/ITLDIS"
function getSuggestionsLubesPart(id,comp_obj,comp_type_obj,img_obj,rowId)
{
    var comp_objj=comp_obj;
    var comp_paramarr=new Array;
    var param_type= comp_type_obj.value;
    var sparex=new Array();
    var lubesNo=new Array();
    var lubesDesc=new Array();
    var lubesUnitPrice=new Array();
    if(comp_objj.value=='')
    {
        document.getElementById("msg_saveFAILED").innerHTML ="Please enter atleast one Character of Part Number to get suggestions.";
        comp_objj.focus();
    }
    else
    {
        img_obj.style.visibility = "visible";
        obj=new actd2(id, comp_paramarr,contextPath+"/serviceProcess.do?option=getPartNumberAjax&comptype="+comp_objj.value+"&partno=&t="+new Date().getTime(), img_obj,rowId);
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
                    document.getElementById("msg_saveFAILED").innerHTML="Same part number can not be enter twice";
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


