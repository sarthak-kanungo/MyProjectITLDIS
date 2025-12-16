<%-- 
    Document   : FillForm
    Created on : Jan 31, 2014, 5:16:44 PM
    Author     : manish.kishore
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form name="form1" autocomplete="off" method="post"  action="ProcessAction.do">
    <input type="hidden" name="option" value="initForm"/>
    
    <table width=50% align=center border="0" cellpadding="1" cellspacing="1" bordercolor="#333333" >
        <tr height=25>
            <td  align="left"  class="grey">
                <table width=100% border=0 cellspacing=1 cellpadding=0 style="background-color:#cccccc;">

                    <tr style="background-color:#ffffff;">
                        <td width=25% align=center >Enter Chasis No. &nbsp;<input type="text" name="chassisNo" value="" > 
                        </td>
                    </tr>
<!--                    <tr style="background-color:#ffffff;">
                        <td width=25% align=center >
                            <input type="radio" name="formName" value="PDI"/>&nbsp; PDI
                        </td>
                    </tr>-->
                    <tr style="background-color:#ffffff;">
                        <td width=25% align=center >
                            <input type="radio" name="formName" value="INS"/>&nbsp; INS
                        </td>
                    </tr>
                    <tr style="background-color:#ffffff;">
                        <td width=25% align=center >
                            <input type="radio" name="formName" value="FSR"/>&nbsp; FSR
                        </td>
                    </tr>
<!--                    <tr style="background-color:#ffffff;">
                        <td width=25% align=center >
                            <input type="radio" name="formName" value="CSR"/>&nbsp; CSR
                        </tdC
                    </tr>-->
                    <tr style="background-color:#ffffff;">
                        <td width=25% align=center >
                            <input type="submit"  value="Submit"/>
                        </td>
                    </tr>
                    
                </table> 
            </td>
        </tr>
    </table>
</form>


