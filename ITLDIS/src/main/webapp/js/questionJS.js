/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function numeralsOnly(evt,objj)
{
    evt = (evt) ? evt : event;
    var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode :
        ((evt.which) ? evt.which : 0));
    if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode != 46)
    {
        alert("Please specify numeric value only !!");
        objj.className= "inputbox";
        objj.focus();
        return false;
    }
    return true;
}

function stringOnly(obj)
{
    var flag=true;
    var string=Trim(obj.value);
    var iChars = "=|+!^-,\\:<>[]{}`';()@&$#%~?><"+'"';
    for (var i = 0; i < string.length; i++) {
        if (iChars.indexOf(string.charAt(i)) != -1)
        {
            flag=false;
            break;
        }
    }
    if(!flag)
    {
        alert("Spacial characters are not allowed !!");
        obj.focus();
        return false;
    }
}


function validate()
{
    var radiogroup=null;
    var itemchecked;
    var j = 0 ;
    var el = document.forms[0].elements;
    for(var i = 0 ; i < el.length ; ++i)
    {
        if(el[i].type == "text")
        {
            radiogroup = el[el[i].name];
            itemchecked = false;
            for(j = 0 ; j < radiogroup.length ; ++j)
            {
                if(radiogroup[j].value!="")
                {
                    itemchecked = true;
                    break;
                }
            }
            if(!itemchecked)
            {
                alert("Please enter atleast one numeric value");
                if(el[i].focus)
                    el[i].focus();
                return false;
            }
        }else if(el[i].type == "radio")
        {
            radiogroup = el[el[i].name];
            itemchecked = false;
            for(j = 0 ; j < radiogroup.length ; ++j)
            {
                if(radiogroup[j].checked)
                {
                    itemchecked = true;
                    break;
                }
            }
            if(!itemchecked)
            {
                alert("Please make a selection");
                if(el[i].focus)
                    el[i].focus();
                return false;
            }
        } else if(el[i].type == "checkbox")
          {
            radiogroup = el[el[i].name];
            itemchecked = false;
            for(j = 0 ; j < radiogroup.length ; ++j)
            {
                if(radiogroup[j].checked)
                {
                    itemchecked = true;
                    break;
                }
            }
            if(!itemchecked)
            {
                alert("Please check atleast one checkbox");
                if(el[i].focus)
                    el[i].focus();
                return false;
            }
        }
    }
    var answer = confirm("Do you really want to save ? ")
    if (answer){
        document.getElementById("f1").submit();
    }
    else{
        return false;
    }
}

   