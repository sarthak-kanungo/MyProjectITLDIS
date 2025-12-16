/**
 *
 * @author dipti.kewlani
 */
var total_qty = 0;
function getHeaderInfo(form){
    obj = form.custCode;
    custCode = form.custCode.value;
    if(custCode.length==0){
        alert("Customer code can not be blank");
    }else {
        var strURL ="QuotationAjax?custCode="+custCode;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function(){
            stateChanged(form);
        }
        xmlHttp.open("GET", strURL, false);
        xmlHttp.send(null);
        return;
    }
}

function stateChanged(form){
    obj = form.custCode;
    custCode = form.custCode.value;
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete"){
        res = xmlHttp.responseText;
        var tempPartInfo=res.split("@@");
        if(tempPartInfo[0]=='NotValidate'){
            alert("Customer Code '"+custCode+"' is not validated.");
            form.custCode.value="";
            obj.focus();
            return;
        }
        else if(tempPartInfo[0]=='NotExists')
        {
            alert("Customer Code '"+custCode+"' does not exist.");
            form.custCode.value="";
            obj.focus();
            return;
        }
        else
        {
            document.getElementById("custAddr").innerHTML=tempPartInfo[1];
            form.custAddress.value=tempPartInfo[1];
            form.custName.focus();
        }
    }
}

function specialCharacterCheck(text){
    var iChars = "!@#$%^&*()+=-_~[]\';,./{}|\":<>?";
    for(var i = 0; i < text.length; i++) {
        if (iChars.indexOf(text.charAt(i)) != -1){
            return false;
        }
    }
    return true;
}

function notAllowCharCheck(text){
    var iChars = "!@%^&*+=\';,/{}|\"<>?";
    for(var i = 0; i < text.length; i++) {
        if (iChars.indexOf(text.charAt(i)) != -1){
            return text.charAt(i);
        }
    }
    return true;
}

function validateQuotation(form){
    partsNum = Trim(form.numPartsToOrder.value);
    custName = Trim(form.custName.value);
    custCode = Trim(form.custCode.value);
    refno =  Trim(form.refno.value);
    if(custCode==null){
        alert("Please enter Customer Code");
        form.custCode.focus();
        return false;
    }
    else if(!specialCharacterCheck(custCode)){
        alert("Customer Code should not contain special character.");
        form.custCode.focus();
        return false;
    }

    if(custName==null){
        alert("Please enter value in Kind Attention field.");
        form.custName.focus();
        return false;
    }
    else
    {
        charac = notAllowCharCheck(custName);
        if(charac!=true){
            alert("Kind Attention field should not contain "+charac);
            form.custName.focus();
            return false;
        }
    }   
    if(refno==null){
        alert("Please enter Customer Ref. No.");
        form.refno.focus();
        return false;
    }
    else{
        charac = notAllowCharCheck(refno);
        if(charac!=true){
            alert("Customer Ref. No. should not contain "+charac);
            form.refno.focus();
            return false;
        }
    }
    
    remark = Trim(form.remark.value);
    if(remark!=null)
    {
        charac = notAllowCharCheck(remark);
        if(charac!=true){
            alert("Remarks should not contain "+charac);
            form.remark.focus();
            return false;
        }
    }
    
    var orderCriteria = form.orderCriteria;
    if(orderCriteria[0].checked==true){
        form.action = "CreateQuotation_fromExcel";
    }
    else
    {
        if(partsNum==null){
            alert("Please enter number of parts.");
            form.numPartsToOrder.focus();
            return false;
        }else if(isNaN(partsNum)){
            alert("Please enter numeric value of parts.");
            form.numPartsToOrder.value="";
            form.numPartsToOrder.focus();
            return false;
        }
        form.action = "CreateQuotation_Manual";
    }
    form.submit();
    return true;
}

function previousQuotationForm(currentPage){
    
}
function inValidateQuotation(){
    document.order.action = 'CreateQuotation';
    document.order.submit();
}

function uploadQuotationExcel(form){
    form.action = "CreateQuotation_fromExcel_0?refno="+form.refno.value+"&custCode="+form.custCode.value+"&custName="+form.custName.value+"&remark="+form.remark.value+"&custAddress="+form.custAddress.value+"";
    var filePath = form.excelFile.value;
    if(filePath == ''){
        alert('Please browse parts excel file.');
        return;
    }
    if(filePath.indexOf('.xls') != (filePath.length-4)){
        alert('Please browse only excel file.');
        return;
    }
    form.submit();
}

function backToCreateQuotation(form){
    form.action = 'CreateQuotation?refno='+form.refno.value+'&custCode=' +form.custCode.value + '&custName=' + form.custName.value+ '&custAddress=' + form.custAddress.value + "&remark=" +form.remark.value+'';
    form.submit();
}

function formatNumber(T2, Precision)
{
    var obj=T2
    var num = new NumberFormat();
    num.setInputDecimal('.');
    num.setNumber(obj.value);
    num.setPlaces(Precision);
    num.setSeparators(false, ',', ',');
    obj.value = num.toFormatted();
}

function cancelQuotation(form,uri)
{
    if(uri.indexOf("Manual")!=-1){
        form.action = 'CreateQuotation?refno='+form.refno.value+'&custCode=' +form.custCode.value + '&custName=' + form.custName.value+ '&custAddress=' + form.custAddress.value + "&remark=" +form.remark.value+'';
        var cnfrm = confirm('Do you want to Cancel Quotation?');
    }
    else {
        form.action = 'CreateQuotation_fromExcel?refno='+form.refno.value+'&custCode=' +form.custCode.value + '&custName=' + form.custName.value+ '&custAddress=' + form.custAddress.value + "&remark=" +form.remark.value+'';
        var cnfrm = confirm('Do you want to Cancel Quotation & browse excel again?');
    }
    if(cnfrm)
    {
        form.submit();
    }
}


function validateOrder()
{
    total_qty = 0;
    document.quotExcel_0.action = 'OrderPartsQuotation'
    document.quotExcel_0.fileName.value = '" + filename + "';
    var oRows = document.getElementById('myTable1').getElementsByTagName('tr');
    var iRowCount = oRows.length;
    for(var i=0;i<iRowCount;i++)
    {
        if(!validate(i+1))
        {
            return false;
        }
    }
    //var totVal = document.quotExcel_0.totalValue.value;
    var totVal = total_qty;
    //if((totVal=='0.00')||(totVal=='0')||(totVal=='0.0')||(totVal==''))
    if(totVal==0)
    {
        alert('Please enter quantity for alteast one part.');
        return;
    }
    else
    {
        var cnfrm = confirm('Do you want to save the quotation?')
        if(cnfrm)
        {
            document.quotExcel_0.submit();
        }
    }
    total_qty = 0;
}
//$10
function validate(index)
{
    if(isblank(document.quotExcel_0.qty[index-1].value,index))
    {
        if(number_check(document.quotExcel_0.qty[index-1].value))
        {
            if (document.quotExcel_0.qty[index-1].value.indexOf('.')==-1)
            {
                var qml_value=parseFloat(document.quotExcel_0.qml[index-1].value);
                var qty_value=parseFloat(document.quotExcel_0.qty[index-1].value);
                total_qty+=qty_value;
                if (qty_value<qml_value && qty_value != 0)
                {
                    alert("Quantity cannot be less than "+qml_value);
                    document.quotExcel_0.qty[index-1].value=0;
                    document.quotExcel_0.qty[index-1].focus();
                    document.quotExcel_0.qty[index-1].select();
                    return false;
                }
                else
                {
                    var tableElem = document.getElementById('myTable1');
                    if(qty_value == 0)
                    {
                        document.quotExcel_0.qty[index-1].value = parseFloat(document.quotExcel_0.qty[index-1].value);
                        rowElem = tableElem.rows[index-1];
                        rowElem.style.backgroundColor='#E2EEFA';
                        return true;
                    }
                    else if(qml_value==0 && qty_value!=0)
                    {
                        alert("Quantity cannot be entered.");
                        document.quotExcel_0.qty[index-1].value = 0;
                        return false;
                    }
                    else if(qty_value%qml_value!=0)
                    {
                        alert("Quantity must be in multiples of "+qml_value);
                        document.quotExcel_0.qty[index-1].value=0;
                        document.quotExcel_0.qty[index-1].focus();
                        document.quotExcel_0.qty[index-1].select();
                        return false;
                    }
                    else
                    {

                        document.quotExcel_0.qty[index-1].value = parseFloat(document.quotExcel_0.qty[index-1].value);
                        rowElem = tableElem.rows[index-1];
                        rowElem.style.backgroundColor='#E2EEFA';
                        return true;
                    }
                }
            }
            else
            {
                alert("Enter only numeric value.");
                document.quotExcel_0.qty[index-1].value=0;
                document.quotExcel_0.qty[index-1].focus();
                document.quotExcel_0.qty[index-1].select();
                return false;
            }
        }
        else
        {
            alert("Enter only integer values.");
            document.quotExcel_0.qty[index-1].value=0;
            document.quotExcel_0.qty[index-1].focus();
            document.quotExcel_0.qty[index-1].select();
            return false;
        }
    }
}



function number_check(v)
{
    if(isNaN(v))
    {
        return false;
    }
    else
    {
        return true;
    }
}

function isblank(s,index)																																			
{																																											
    if(s.length==0 || s.indexOf(' ')!=-1)
    {
        alert("Value can neither be blank nor it can have space.");
        document.order.qty[index-1].value=0;
        document.order.qty[index-1].focus();
        document.order.qty[index-1].select();
        return false;
    }
    else if(s>9999)
    {
        alert("Quantity entered should not be greater than 9999.");
        document.order.qty[index-1].value=0;
        document.order.qty[index-1].focus();
        document.order.qty[index-1].select();
        return false;
    }
    else
    {
        return true;
    }
}


function showValue(projectId,id,elem,imageURL)
{
    id=document.quotExcel_0.partNo[elem-1];
    projectId=id.value
    var oRows = document.getElementById('myTable1').getElementsByTagName('tr');
    var iRowCount = oRows.length;
    if(projectId.indexOf(' ')!=-1)
    {
        alert('Part no can not have space.');
        blankContent(elem,imageURL)
        document.quotExcel_0.partNo[elem-1].value=projectId
        return ;
    }
    else if(projectId.indexOf('+')!=-1)
    {
        alert("Component '"+projectId+"' is not validated");
        blankContent(elem,imageURL)
        return;
    }
    else if(projectId.length==0)
    {
        blankContent(elem,imageURL)
        return ;
    }
    else
    {
        var strURL ="ValidatePartQuotationAjax?partno="+projectId;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            validatePartSuccessfull(elem,projectId,imageURL);
        };
        xmlHttp.open("GET", strURL, false);
        xmlHttp.send(null);
        return ;
    }
}


function blankContent(elem,imageURL)
{
    var a=document.getElementById('myTable1').rows[elem-1].cells;
    a[1].innerHTML='<div align=left><input type=text name=partNo size=9 maxlength=15 onblur=showValue(this.value,this,'+elem+',"'+imageURL+'") >&nbsp;<a href=javascript:showValue(this.value,this,'+elem+',"'+imageURL+'")><img src=' + imageURL + 'help_icon.gif border=0 alt="Validate Part" ></a></div>';
    a[2].innerHTML='<div align=center>&nbsp;</div>';
    a[3].innerHTML='<div align=center>&nbsp;</div>';
    document.quotExcel_0.qml[elem-1].value=0;
    document.quotExcel_0.qty[elem-1].value=0;
    total_qty = 0;
    validate(elem);
}

function validatePartSuccessfull(elem,projectId,imageURL)
{
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        res = xmlHttp.responseText;
        var oRows = document.getElementById('myTable1').getElementsByTagName('tr');
        var tempPartInfo = res.split('@@');
        if(tempPartInfo[0]=='NotServiceable')
        {
            if(tempPartInfo.length==1)
            {
                alert("Part No '"+projectId+"' is not serviceable.");
                blankContent(elem,imageURL);
                return;
            }
            else
            {
                var replacePart='';
                var type=tempPartInfo[1];
                for(var i=2;i<tempPartInfo.length;i++)
                {
                    replacePart=replacePart+' ' +tempPartInfo[i]
                }
                if(type=='replace')
                {
                    alert("Part No '"+projectId+"' is not serviceable. \nIt has been replaced by '"+replacePart+"' \nPlease enter replaced by part no.");
                    blankContent(elem,imageURL)
                    return;
                }
                else if(type=='alternate')
                {
                    alert("Part No '"+projectId+"' is not serviceable. \nIt has alternate part number '"+replacePart+"' \nPlease enter alternate part no.");
                    blankContent(elem,imageURL)
                    return;
                }
                else
                {
                    alert("Part No '"+projectId+"' is not serviceable.");
                    blankContent(elem,imageURL)
                    return;
                }
            }
        }
        else if(tempPartInfo[0]=='NotValidate')
        {
            if(tempPartInfo.length==1)
            {
                alert("Component '"+projectId+"' is not validated");
                blankContent(elem,imageURL)
                return;
            }
            else
            {
                var newPart='';
                for(var i=1;i<tempPartInfo.length;i++)
                {
                    newPart=newPart+'\n ' +tempPartInfo[i]
                }
                alert("Component '"+projectId+"' is not validated.\nThe Correct part number might be one of the following:"+newPart+"Please enter correct part no.");
                blankContent(elem,imageURL)
                return;
            }
        }
        else
        {
            total_qty = 0;
            var a=document.getElementById('myTable1').rows[elem-1].cells
            a[1].innerHTML='<div align=left><input type=text name=partNo size=9 maxlength=15 onblur=showValue(this.value,this,'+elem+',"'+imageURL+'") value='+projectId+'></div>';
            a[2].innerHTML='<div align=left>'+tempPartInfo[0]+'</div>';
            a[3].innerHTML='<div align=center>'+tempPartInfo[1]+'</div>';
            document.quotExcel_0.qml[elem-1].value=tempPartInfo[1];
            validate(elem);
            document.quotExcel_0.qty[elem-1].focus();
            document.quotExcel_0.qty[elem-1].select();
            document.quotExcel_0.qml[elem-1].value=tempPartInfo[1];//                    document.order.ordValue[elem-1].value=0;
        }
    }
}

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

function addnewPart(form,imageURL)
{
    var oRows = document.getElementById('myTable1').getElementsByTagName('tr');
    var iRowCount = oRows.length;
    for(var i=0;i<iRowCount;i++)
    {
        if(form.partNo[i].value=='')
        {
            alert('Please enter part no.');
            form.partNo[i].focus();
            return false;
        }
    }
    var rcount1=iRowCount+1;
    var x=document.getElementById('myTable1').insertRow(iRowCount);
    x.className = 'tableactive';
    var a=x.insertCell(0)
    var b=x.insertCell(1)
    var c=x.insertCell(2)
    var d=x.insertCell(3)
    var e=x.insertCell(4)
    a.width='10%'
    b.width='20%'
    c.width='40%'
    d.width='15%'
    e.width='15%'
    a.innerHTML='<div align=center>'+rcount1+'</div>'
    b.innerHTML='<div align=left><input type=text name=partNo size=9 maxlength=15 onblur=showValue(this.value,this,'+rcount1+',"'+imageURL+'")>&nbsp;<a href=javascript:showValue(this.value,this,'+rcount1+',"'+imageURL+'")><img src=' + form.imagesURL.value + 'help_icon.gif border=0 alt="Validate Part" ></a></div>'
    c.innerHTML='<div align=left>&nbsp;</div>'
    d.innerHTML='<div align=center>&nbsp;</div>'
    e.innerHTML='<div align=center><input type=hidden name=qml value=0><input type=hidden name=ndp value=0><input type = text size = 3 name = qty value=0  onblur=validate('+rcount1+') maxlength=4 ></div>'
    form.partNo[rcount1-1].focus();
}


                 