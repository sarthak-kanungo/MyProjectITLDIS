/*
 * EAMG_ValidateXlsWithTemplate.java
 *
 * Created on jan 16, 2012, 4:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package EAMG.Group.Action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

import com.EAMG.common.EAMG_MethodUtility2;
import com.EAMG.common.EAMG_MethodsUtility;

import EAMG.Model.Action.EAMG_Variant_AggregatesActionForm;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author mina.yadav
 ** S.No.   Method                          Author              Version     Methods Detail
 *    1.     isGroupExcelValidated          Avinash.pandey          1.0         to validate Group Excel.
 *    2.     checkParamValue                Avinash.pandey   1.0         to validate Component and Group Bom level parameters in Group Excel.
 *    3.     getGrpBomLevelParamIndexVec    Avinash.pandey   1.0         to get GROUP BOM parameter and their index in Group Excel.
 *    4.     validateLevels                 Avinash.pandey           1.0         to validate levels of Group Bom in Group Excel.
 *    5.     isPartExcelValidated           Avinash.pandey   1.0         to validate part Excel.
 *    6.     isToolExcelValidated           Avinash.pandey   1.0         to validate Tool Excel.
 *    7.     isKitExcel                     Avinash.pandey      1.0         to validate Kit Excel.
 *    8.     isAssemblyExcel                Avinash.pandey      1.0         to validate Assembly excel
 *    9.     isAssemblyExcelValidated       Avinash.pandey   1.0         to validate Assembly Excel with data.
 *    10.    isKitExcelValidated            Avinash.pandey   1.0         to validate Kit Excel with data.
 */
public class EAMG_ValidateXlsWithTemplate {

    /** Creates a new instance of EAMG_ValidateXlsWithTemplate */
    public EAMG_ValidateXlsWithTemplate() {
    }
    String group_no = "";
    int row_index_bom = 0;

    ////1.to validate Group Excel./////////////////////////////////////////////////
    public String isGroupExcelValidated(File xlsfile, Connection conn) throws Exception {
       // Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        int row = 0;
        int column = 0;
        Workbook workbook1 = null;
        ArrayList item_InExcel = new ArrayList();
        ///////////////////////////CHECK FOR CORRUPTED EXCEL//////////////////////////
        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path.";
        }
        //////////////////////////////////////////////////////////////////////////////
        Sheet sheet = workbook1.getSheet(0);
        EAMG_MethodsUtility method_obj = new EAMG_MethodsUtility();
        try {
            ///////////////////CHECKING TOTAL COLUMN COUNT OF TEMPLATE/////////////////
            int templateCols = 8;// + method_obj.getPrtUAsmParamCount(conn);
            if (templateCols != sheet.getColumns()) {
                return "Excel File Columns does not match with Template Columns.";
            }
            ////////////////////TEMPLATE CHECKING STARTS//////////////////////////////
            if (!(sheet.getCell(column, row).getContents().trim().equals("TABLE TEMPLATE"))) {
                return row + "@@" + column;
            }
            row++;
            if (!(sheet.getCell(column, row).getContents().trim().equals(""))) {
                return row + "@@" + column;
            }
            if (!(sheet.getCell((column + 1), row).getContents().trim().equals(""))) {
                return row + "@@" + (column + 1);
            }
            row++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("TABLE NUMBER"))) {
                return row + "@@" + column;
            }

            ////System.out.println("column+1="+(column+1)+", row= "+row+"********"+sheet.getCell(column+1,row).getContents().trim());
            if (sheet.getCell(column + 1, row).getContents().trim().equals("") || sheet.getCell(column + 1, row).getContents().trim().equals("<Table Number>")) {
                return "Table Number at Row '" + (row + 1) + "' can not be blank.";
            } else {
                group_no = sheet.getCell(column + 1, row).getContents().trim();
                //CHECKING SPECIAL SYMBOLS IN GROUP NUMBER/////////////////////////////
                if (group_no.length() > 31) {
                    return "Table Number at Row '" + (row + 1) + "' can not be greater than 31 characters.";
                }
//                if (!method_obj.checkSpecialPart_1(group_no)) {
//                    return "Table Number at Row '" + (row + 1) + "' can not contain special characters or space.<br>Allowed Special Symbols are (_) underscore and (-) Hyphen.";
//                }
            }
            row++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("TABLE DESCRIPTION"))) {
                return row + "@@" + column;
            }
            if (sheet.getCell((column + 1), row).getContents().trim().equals("") || sheet.getCell((column + 1), row).getContents().trim().equals("<Table Description>")) {
                return "Table Description at Row '" + (row + 1) + "' can not be blank.";
            }
            row++;
            row++;
            ////CHECKING BOM HEADER IN TEMPLATE////////////////////////////////////////
            //////BOM HEADER/////////

            if (!(sheet.getCell(column, row).getContents().trim().equals("SEQ. NO."))) {
                return row + "@@" + column;
            }
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("PART"))) {
                return row + "@@" + column;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART DESCRIPTION"))) {
                return row + "@@" + column;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("QTY"))) {
                return row + "@@" + column;
            }
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("REMARKS"))) {
                return row + "@@" + column;
            }
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("INTERCHANGEABILITY"))) {
                return row + "@@" + column;
            }
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("CUT OFF CHASSIS"))) {
                return row + "@@" + column;
            }
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("CUT OFF (dd/mm/yyyy)"))) {
                return row + "@@" + column;
            }
            row++;

            if (sheet.getCell(0, row).getContents().trim().equals("end")) {
                return "No Table Bom Data is available in the Excel File.";
            } else {
                column = 0;
                row_index_bom = row;
                while (!sheet.getCell(0, row).getContents().trim().equals("end")) {
                    //System.out.println("in while");
                    for (int i = 0; i < 8; i++) {
                        String cell_contents = sheet.getCell(i, row).getContents().trim();
                        if (i == 2)//for validating Item Desc
                        {
                            if (cell_contents.equals("")) {
                                return "Part Description column can not be blank at Row '" + (row + 1) + "'.";
                            } else {
                                if (!method_obj.checkSpecialSymbolDescription(cell_contents)) {
                                    return "Part Description at Row '" + (row + 1) + "' can not contain any special character.";
                                }
                            }

                        } else if (i == 3)//for validating Quantity column.It can be either a number value or AR for "As Required"
                        {
                            if (cell_contents.equals("")) {
                                return "Qty column can not be blank at Row '" + (row + 1) + "' in the Group Bom.";
                            } else {
                                if (cell_contents.equals("0")) {
                                    return "Qty column can not be 0 at Row '" + (row + 1) + "' in the Group Bom.";
                                }
                                boolean b = new EAMG_MethodsUtility().isSpecialchar(cell_contents);
                                if (!b && !cell_contents.equalsIgnoreCase("AR")) {
                                    return "No Special Symbols allowed in Qty column at Row '" + (row + 1) + "' in the Table Bom.";
                                }

                                String qty = "0";
                                try {
                                    qty = cell_contents;
                                    if (qty.length() > 15) {
                                        return "Quantity can not be greater than 15 at Row '" + (row + 1) + "' in the Table Bom.";
                                    }
                                } catch (NumberFormatException ex) {
                                    ex.printStackTrace();
                                    // if (!cell_contents.equals("AR")) {
                                    //     return "Only numeric values are allowed as quantity at Row '" + (row + 1) + "' and quantity can not be greater than 500 or Give 'AR' for 'As Required' in the Table Bom.";
                                    //}
                                }
                            }
                        } else if (i == 4)//for validating level column. it can not be empty and only numeric
                        {
                            if (!method_obj.checkSpecialSymbolDescription(cell_contents)) {
                                return "Remarks at Row '" + (row + 1) + "' can not contain any special character.";
                            }

                        } else if (i == 0)//for validating Seq.No. column
                        {
                            if (cell_contents.equals("")) {
                                return "Seq.No. column can not be blank at Row '" + (row + 1) + "' in the Table Bom.";
                            } else if (!method_obj.checkSpecialSymbolDescription(cell_contents)) {
                                return "Seq.No. at Row '" + (row + 1) + "' can not contain any special character.";
                            }


                        } else if (i == 1)//for validating Item column
                        {
                            if (cell_contents.equals("")) {
                                return "Part column can not be blank at Row '" + (row + 1) + "' in the Table Bom.";
                            } else {
                                if (cell_contents.length() > 31) {
                                    return "Part at Row '" + (row + 1) + "' can not be greater than 31 characters.";
                                }
                            }
                            item_InExcel.add(cell_contents.toUpperCase());
                        } else if (i == 5) {//for validating Item INTERCHANGEABILITY
                            if (!method_obj.checkSpecialSymbolDescription(cell_contents)) {
                                return "Interchangeability at Row '" + (row + 1) + "' can not contain any special character.";
                            } else if (!cell_contents.equalsIgnoreCase("") && !(cell_contents.equalsIgnoreCase("YES") || cell_contents.equalsIgnoreCase("NO"))) {
                                return "Interchangeability at Row '" + (row + 1) + "' can contain only 'YES' or 'NO' ";
                            }
                        } else if (i == 6) {//for validating Item CUT_OFF_CHASSIS
                            if (!method_obj.checkSpecialSymbolDescription(cell_contents)) {
                                return "CUT OFF CHASSIS at Row '" + (row + 1) + "' can not contain any special character.";
                            }

                        } else if (i == 7) {
                            if (!cell_contents.equals("")) {
                                if (!method_obj.isValidDateString(cell_contents)) {//for validating Item CUT_OFF
                                    return "CUT OFF at Row '" + (row + 1) + "' can not contain invalid date.";
                                }
                            }
                        }
                    }
                    row++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            return row + "@@" + column;
        }
        return "success@@" + group_no;
    }
    //////////////////////////////////////////////////////////////////////////////
    ////2.to validate Component and Group Bom level parameters in Group Excel./////////////////////////////////////////////////

    public String checkParamValue(EAMG_MethodsUtility method_obj, Sheet sheet, Connection conn) throws Exception {
        int bom_header_index = method_obj.getGRPParameterCount(conn) + 5;
        //System.out.println("bom_header_index::::::"+bom_header_index);
//        Vector grpBomLevelParamvec = method_obj.getGrpBomLevelParametersVec(conn);
        //System.out.println("grpBomLevelParamvec::::::"+grpBomLevelParamvec);
//        Vector grpBomLevelParamIndexesVec = new Vector();
//        grpBomLevelParamIndexesVec = getGrpBomLevelParamIndexVec(method_obj, sheet, bom_header_index, grpBomLevelParamIndexesVec, grpBomLevelParamvec, conn);
        //System.out.println("grpBomLevelParamIndexesVec1::::::"+grpBomLevelParamIndexesVec);
//        int noof_param = grpBomLevelParamIndexesVec.size();
        String result = "success";
        int row = bom_header_index + 1;

        EAMG_ValidateGroupInsertion insert_obj = new EAMG_ValidateGroupInsertion();
        insert_obj.bom_header_index = bom_header_index;

        insert_obj.partParamVec = method_obj.getPartParametersVec(conn);
        Vector partParamIndexVec = new Vector();
        partParamIndexVec = insert_obj.getPartParamIndex(sheet, partParamIndexVec, conn);

        insert_obj.assyParamVec = method_obj.getAssyParametersVec(conn);
        Vector asmParamIndexVec = new Vector();
        asmParamIndexVec = insert_obj.getAssyParamIndex(sheet, asmParamIndexVec, conn);

        //System.out.println("noof_param::::::"+noof_param);
        //System.out.println("row::::::"+row);

        int prt_u_asm_cnt = method_obj.getPrtUAsmParamCount(conn);
        int comp_param_start_index = 6;
        //For validating Component Prameters in BOM.
        int col_index = 0;
        String comp_no = "";
        String comp_type = "";
        String col_name = "";
        String param_value = "";
        int param_id = 0;
        while (!sheet.getCell(0, row).getContents().trim().equals("end")) {
            comp_no = sheet.getCell(2, row).getContents().trim();
            comp_type = sheet.getCell(4, row).getContents().trim();

            for (int i = 0; i < prt_u_asm_cnt; i++) {
                col_index = comp_param_start_index + i;
                param_value = sheet.getCell(col_index, row).getContents().trim();
                if (!param_value.equals("")) {
                    if (comp_type.equals("PRT")) {

                        if (partParamIndexVec.contains(col_index)) {
                            String param_name = "" + partParamIndexVec.elementAt(partParamIndexVec.indexOf(col_index) + 1);
                            result = method_obj.validateCompParameter(comp_no, comp_type, param_name, param_value, conn);
                            if (!result.equals("success")) {
                                return result + " at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
                            }
                        } else {

                            return "Wrong Parameter Value exists in Part '" + comp_no + "' for Assembly Parameter at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
                        }
                    }
                    if (comp_type.equals("ASM")) {
                        if (asmParamIndexVec.contains(col_index)) {
                            String param_name = "" + asmParamIndexVec.elementAt(asmParamIndexVec.indexOf(col_index) + 1);
                            result = method_obj.validateCompParameter(comp_no, comp_type, param_name, param_value, conn);
                            if (!result.equals("success")) {
                                return result + " at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
                            }
                        } else {

                            return "Wrong Parameter Value exists in Assembly '" + comp_no + "' for Part Parameter at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
                        }
                    }
                }
            }

            //For validating GROUP BOM Prameters in BOM.
//            for (int i = 0; i < noof_param; i += 2) {
//                col_index = ((Integer) grpBomLevelParamIndexesVec.elementAt(i)).intValue();
//
//                col_name = "" + grpBomLevelParamIndexesVec.elementAt(i + 1);
//                //System.out.println("col_name::::::"+col_name);
//
//                param_value = sheet.getCell(col_index, row).getContents().trim();
//
//                if (!param_value.equals("")) {
//                    if(param_value.indexOf(",")!=-1)
//                    {
//                       String paramArr[]=null;
//                       paramArr=param_value.split(",");
//
//                       for(int ii=0;ii<paramArr.length;ii++)
//                       {
//                            param_id = method_obj.getGrpBomParamIdForMultiList(col_name, conn);
//
//                            if (param_id != 0)//for LIST parameter
//                            {
//                                Vector valueVec = method_obj.getGrpBomParamListValues(param_id, conn);
//                                //System.out.println("valueVec:"+valueVec);
//                                if (!valueVec.contains(paramArr[ii])) {
//                                    result = "Value for the Parameter '" + col_name + "' does not match at row '" + (row + 1) + "'. <br>It should be with in the List: " + valueVec + ".";
//                                    return result;
//                                }
//                            } else {
//                                result = method_obj.validateGrpBomParameter(comp_no, comp_type, col_name, paramArr[ii], conn);
//                                if (!result.equals("success")) {
//                                    return result + " at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
//                                }
//                            }
//                        }
//                    }
//                    else
//                    {
//                        param_id = method_obj.getGrpBomParamIdForList(col_name, conn);
//
//                        if (param_id != 0)//for LIST parameter
//                        {
//                            Vector valueVec = method_obj.getGrpBomParamListValues(param_id, conn);
//                            //System.out.println("valueVec:"+valueVec);
//                            if (!valueVec.contains(param_value)) {
//                                result = "Value for the Parameter '" + col_name + "' does not match at row '" + (row + 1) + "'. <br>It should be with in the List: " + valueVec + ".";
//                                return result;
//                            }
//                        } else {
//                            result = method_obj.validateGrpBomParameter(comp_no, comp_type, col_name, param_value, conn);
//                            if (!result.equals("success")) {
//                                return result + " at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
//                            }
//                        }
//                    }
//                }
//
//
//            }


            row++;
        }

        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////
    ////3.to get GROUP BOM parameter and their index in Group Excel./////////////////////////////////////////////////

    public Vector getGrpBomLevelParamIndexVec(EAMG_MethodsUtility method_obj, Sheet sheet, int bom_header_index, Vector grpBomLevelParamIndexesVec, Vector grpBomLevelParamvec, Connection conn) throws Exception {

        int param_count = method_obj.getPrtUAsmParamCount(conn);
        int param_index = 6 + param_count;
        //System.out.println("grpBomLevelParamvec:"+grpBomLevelParamvec);
        for (int i = 0; i < grpBomLevelParamvec.size(); i++) {
            String param_str = sheet.getCell(param_index, bom_header_index).getContents().trim();
            ////System.out.println("param_str:"+param_str);
            if (grpBomLevelParamvec.contains(param_str)) {
                grpBomLevelParamIndexesVec.add(param_index);
                grpBomLevelParamIndexesVec.add(param_str);
            }
            param_index++;
        }
        //System.out.println("grpBomLevelParamIndexesVec in *****:"+grpBomLevelParamIndexesVec);
        return grpBomLevelParamIndexesVec;
    }
    //////////////////////////////////////////////////////////////////////////////////
    ////4.to validate levels of Group Bom in Group Excel./////////////////////////////////////////////////

    public String validateLevels(Sheet sheet) {
        while (!sheet.getCell(0, row_index_bom).getContents().trim().equalsIgnoreCase("end")) {
            String comp_no = sheet.getCell(2, row_index_bom).getContents().trim();
            int level = Integer.parseInt(sheet.getCell(0, row_index_bom).getContents().trim());
            String comp_type = sheet.getCell(4, row_index_bom).getContents().trim();
            //checking bom of an assembly///////////////////////////////////////////
            if (comp_type.equals("ASM")) {
                int nextlevel = 0;
                try {
                    nextlevel = Integer.parseInt(sheet.getCell(0, row_index_bom + 1).getContents().trim());
                } catch (NumberFormatException ex) {
                    nextlevel = 0;
                    ex.printStackTrace();
                }
                if (nextlevel != level + 1) {
                    return "Bom of Assembly '" + comp_no + "' at Row '" + (row_index_bom + 1) + "' does not exist.";
                }
            } else// validating next level
            {
                int nextlevel = 0;
                try {
                    nextlevel = Integer.parseInt(sheet.getCell(0, row_index_bom + 1).getContents().trim());
                } catch (NumberFormatException ex)/////////check for end////////////////
                {
                    if ((sheet.getCell(0, row_index_bom + 1).getContents().trim()).equals("end")) {
                        return "success";
                    }
                }
                if (nextlevel > level || nextlevel == 0) {
                    return "Next Level after Item '" + comp_no + "' with Type '" + comp_type + "' at Row '" + (row_index_bom + 1) + "' is not valid.";
                }
            }

            row_index_bom++;
        }
        return "success";
    }
    //////////////////////////////////////////////////////////////////////////////////
    ////5.to validate Part Excel./////////////////////////////////////////////////

    public String isPartExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("pxlsfile:"+xlsfile);
        //declaration of variables used.
       // Statement stmt = null;
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0;
        int sheet_col = 0;
        int count = 0, c = 0;
        ;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        try {
            //stmt = conn.createStatement();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for PART TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART TEMPLATE"))) {
                err = row + "@@" + column + "##'PART TEMPLATE' Missing.";
                ////System.out.println(err);
                return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##Row should be blank.";
                ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
                return err;
            }
            row++;
            //checking for PART NUMBER.
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART NUMBER"))) {
                err = row + "@@" + column + "##'PART NUMBER' Missing.";
                ////System.out.println(row+"@@"+column+"@@PART NUMBER MISSING");
                return err;
            }
            ////System.out.println("column+1="+(column+1)+", row= "+row+"********"+sheet.getCell(column+1,row).getContents().trim());
            column++;
            //checking for PART DESCRIPTION.
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART DESCRIPTION"))) {
                err = row + "@@" + column + "##'PART DESCRIPTION' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;
            //checking for PART PARAMETER DESCRIPTION from COMP_PARAM_MASTER
            //rs = stmt.executeQuery("select PARAM_DESC from COMP_PARAM_MASTER where PARAM_TYPE='PRT' order by PARAM_DESC");
            String query = ("select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='PRT' order by PARAM_DESC");
           stmt = conn.prepareStatement(query);
           rs = stmt.executeQuery();
            //sheet_col=sheet.getColumns();
            ////System.out.println("Sheet Columns:"+sheet_col);
            while (rs.next()) {
                paramVec.add(rs.getString(1));
                count++;
            }
            //checking for number of Parameters.
//            if(count!=(sheet_col-2))
//            {
//                column=sheet_col;
//                if(count<(sheet_col-2))
//                {
//                    column=sheet_col-1;
//                }
//                err=row+"@@"+column+"##Number of Parameters does not match.";
//                ////System.out.println(row+"@@"+column+"@@NUMBER OF PARAMETERS ARE DIFFERENT"+sheet_col);
//                return err;
//            }
            //checking for Parameter Names.
            for (int k = 0; k < paramVec.size(); k++) {
                String part = "";
                try {
                    part = sheet.getCell(column, row).getContents().trim();
                } catch (ArrayIndexOutOfBoundsException exception) {
                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + paramVec.elementAt(k) + "'.";
                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
                    return err;
                }
                if (!(part.equals(paramVec.elementAt(k)))) {
                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + paramVec.elementAt(k) + "'.";
                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
                    return err;
                }
                column++;
            }
            sheet_col = paramVec.size() + 2;
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("end"))) {

                err = row + "@@" + column + "##No Records Available.";
                ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+part);
                return err;
            }

            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters.
            while (row < totalrows) {
                comp_no = "";
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 2; column++) {
                        //if Part Number is null.
                        if (sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("")) {
                            if (column == 0) {
                                err = row + "@@" + column + "##Part Number can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@PART NUMBER CAN NOT BE NULL");
                                return err;
                            } //if Part Description is null.
                            else {
                                err = row + "@@" + column + "##Description can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } //if Part Number is '0'.
                        //                        else if(sheet.getCell(column,row).getContents().trim().equalsIgnoreCase("0"))
                        //                        {
                        //                            if(column==0)
                        //                            {
                        //                                err=row+"@@"+column+"##Part Number can not be 0";
                        //                                ////System.out.println(row+"@@"+column+"@@PART NUMBER CAN NOT BE NULL");
                        //                                return err;
                        //                            }
                        //                        }
                        //checking for special symbols.
                        else if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.length() > 31) {
                                return row + "@@" + column + "##Part Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(partno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Part Number. Allowed Special Symbols are (_) underscore and (-) Hyphen.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        }

                    }
                    c = column;
                    //checking for PART Parameter Values.
                    for (column = c; column < sheet_col; column++) {
                        String value = sheet.getCell(column, row).getContents().trim();
                        String param_desc = sheet.getCell(column, 2).getContents().trim();
                        if (!value.equals("")) {
                            String res = methodutil.validateCompParameter(comp_no, "PRT", param_desc, value, conn);
                            if (!res.equals("success")) {
                                err = row + "@@" + column + "##" + res + ".";
                                ////System.out.println(row+"@@"+column+"##"+res+".");
                                return err;
                            }
                        }
                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }
            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' MISSING");
                return err;
            }

            rs.close();
            stmt.close();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row+"@@"+column+"@@TEMPLATE MISMATCH");
            return err;

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }
    ////////////////////////////////////////////////////////////////////////////////
    ////6.to validate Tool Excel./////////////////////////////////////////////////

    public String isToolExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("xlsfile:"+xlsfile);
        //declaration of variables used.
        //Statement stmt = conn.createStatement();
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0, c = 0;
        int sheet_col = 0;
        int count = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        String tool = "";
        String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();

        try {
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for TOOL TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("TOOL TEMPLATE"))) {
                err = row + "@@" + column + "##'TOOL TEMPLATE' Missing.";
                ////System.out.println(err);
                return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##Row should be blank.";
                ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
                return err;
            }
            row++;
            //checking for TOOL NUMBER.
            if (!(sheet.getCell(column, row).getContents().trim().equals("TOOL NUMBER"))) {
                err = row + "@@" + column + "##'TOOL NUMBER' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL NUMBER MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("TOOL DESCRIPTION"))) {
                err = row + "@@" + column + "##'TOOL DESCRIPTION' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION MISSING");
                return err;
            }
            column++;
            //checking for TOOL PARAMETER DESCRIPTION from COMP_PARAM_MASTER
           // rs = stmt.executeQuery("select PARAM_DESC from COMP_PARAM_MASTER where PARAM_TYPE='TOL' order by PARAM_DESC");
            String sql = ("select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='TOL' order by PARAM_DESC");
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            //sheet_col=sheet.getColumns();
            ////System.out.println("Sheet Columns:"+sheet_col);

            while (rs.next()) {
                paramVec.add(rs.getString(1));
                count++;
            }
            //checking for number of parameters.
//            if(count!=(sheet_col-2))
//            {
//                column=sheet_col;
//                if(count<(sheet_col-2))
//                {
//                    column=sheet_col-1;
//                }
//                err=row+"@@"+column+"##Number of Parameters does not match.";
//                ////System.out.println(row+"@@"+column+"@@NUMBER OF PARAMETERS ARE DIFFERENT"+sheet_col);
//                return err;
//            }
            //checking for parameter names.
            for (int k = 0; k < paramVec.size(); k++) {
                try {
                    tool = sheet.getCell(column, row).getContents().trim();
                } catch (ArrayIndexOutOfBoundsException exception) {
                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + paramVec.elementAt(k) + "'.";
                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
                    return err;
                }
                if (!(tool.equals(paramVec.elementAt(k)))) {
                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + paramVec.elementAt(k) + "'.";
                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
                    return err;
                }
                column++;
            }
            sheet_col = paramVec.size() + 2;
            column = 0;
            row++;

            if ((sheet.getCell(column, row).getContents().trim().equals("end"))) {

                err = row + "@@" + column + "##No Records Available.";
                ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+part);
                return err;
            }
            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters
            while (row < totalrows) {
                //if element is not equal to 'end'
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 2; column++) {
                        if (sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("")) {
                            //if Tool Number is null.
                            if (column == 0) {
                                err = row + "@@" + column + "##Tool Number can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@TOOL NUMBER CAN NOT BE NULL");
                                return err;
                            } //if Tool Description is null.
                            else {
                                err = row + "@@" + column + "##Tool Description can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } //if Tool Number is '0'.
                        //                        else if(sheet.getCell(column,row).getContents().trim().equalsIgnoreCase("0"))
                        //                        {
                        //                            if(column==0)
                        //                            {
                        //                                err=row+"@@"+column+"##Tool Number can not be 0";
                        //                                ////System.out.println(row+"@@"+column+"@@Tool NUMBER CAN NOT BE NULL");
                        //                                return err;
                        //                            }
                        //                        }
                        //checking for special symbols.
                        else if (column == 0) {
                            String toolno = sheet.getCell(column, row).getContents().trim();
                            if (toolno.length() > 31) {
                                return row + "@@" + column + "##Tool Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(toolno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Tool Number. Allowed Special Symbols are (_) underscore and (-) Hyphen.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        }

                    }
                    c = column;
                    //checking for TOOL Parameter Values.
                    for (column = c; column < sheet_col; column++) {
                        String value = sheet.getCell(column, row).getContents().trim();
                        String param_desc = sheet.getCell(column, 2).getContents().trim();
                        if (!value.equals("")) {
                            String res = methodutil.validateCompParameter(comp_no, "TOL", param_desc, value, conn);
                            if (!res.equals("success")) {
                                err = row + "@@" + column + "##" + res;
                                ////System.out.println(row+"@@"+column+"##"+res);
                                return err;
                            }
                        }

                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }
            //if 'end' missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' Missing");
                return err;
            }
        } //if 'end' missing.
        catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row + "@@" + column + "@@'END' MISSING");
            return err;

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }
    ////////////////////////////////////////////////////////////////////////////////
    ////7.to validate KIT Excel./////////////////////////////////////////////////

    public String isKitExcel(String fileName, int index, Sheet sheet, Connection conn) throws Exception {

        String result = "";
        String result1 = "<br>Hence Kit Creation Process aborted. Attach valid Excel to complete the Process successfully.";
        HashSet allData = new HashSet();
        ArrayList kitParams = new ArrayList();
        ArrayList dbParam = new ArrayList();
        ArrayList level_InExcel = new ArrayList();
        ArrayList compType_InExcel = new ArrayList();
        ArrayList item_InExcel = new ArrayList();
        ArrayList des_InExcel = new ArrayList();
        ArrayList qty_InExcel = new ArrayList();
        HashSet kitExcelData = new HashSet();

        int columns = sheet.getColumns();
        int rows = sheet.getRows();
        ResultSet rs = null;
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        boolean r1 = false;

        try {

            PreparedStatement stmt, stmt1 = null;
            String selectQury = "select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='KIT' order by PARAM_DESC";
            stmt = conn.prepareStatement(selectQury);
            rs = stmt.executeQuery();
            //stmt = conn.createStatement();
            //stmt1 = conn.createStatement();
            //rs = stmt.executeQuery(selectQury);

            while (rs.next()) {
                kitParams.add(rs.getString(1));
            }
            dbParam = new EAMG_MethodUtility2().param_partUassyUkit(conn);

            int kitParam_size = kitParams.size();

            if ((sheet.getCell(0, 0).getContents().trim()).equals("KIT TEMPLATE")) {

                if (!sheet.getCell(0, 1).getContents().equals("")) {
                    result = "Row should be blank.";
                    return "There is an error at Column 1, Row 2. " + result + result1;
                    //return result;
                }
                if ((sheet.getCell(0, 2).getContents().trim()).equals("KIT NUMBER")) {


                    String kitno = sheet.getCell(1, 2).getContents().trim();
                    if (kitno.equals("")) {
                        result = "Kit Number cannot be blank.";
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }
                    String comp_in_db = new EAMG_MethodsUtility().isCompPresent(kitno, conn);
                    if (!comp_in_db.equals("notpresent")) {
                        result = comp_in_db;
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }
//                    if(kitno.equals("0")) {
//                        result="Kit Number cannot be 0.";
//                        return "There is an error at Column 2, Row 3. "+result;
//                    }
                    if (kitno.length() > 31) {
                        return "There is an error at Column 2, Row 3. " + "Kit Number can not be greater than 31 characters." + result1;
                    }
                    r1 = methodutil.checkSpecialPart_1(kitno);
                    if (r1 != true) {
                        return "There is an error at Column 2, Row 3. " + "Space and Special Symbols are not allowed in Kit Number.<br>Allowed Special Symbols are (_) underscore and (-) Hyphen." + result1;
                    }
                    if ((sheet.getCell(0, 3).getContents().trim()).equals("KIT DESCRIPTION")) {
                        if (sheet.getCell(1, 3).getContents().equals("")) {
                            result = "Kit Description cannot be blank.";
                            return "There is an error at Column 2, Row 4. " + result + result1;
                        }

                    } else {
                        result = "'KIT DESCRIPTION' Missing.";
                        return "There is an error at Column 2, Row 4. " + result + result1;
                    }
                    int row = 4;
                    for (int j = 0; j < kitParams.size(); j++) {

                        if (!(sheet.getCell(0, row).getContents().trim()).equals(kitParams.get(j)) && row < rows) {
                            result = "Parameter Name doesn't match with '" + kitParams.get(j) + "'.";
                            return "There is an error at Column 1, Row " + (row + 1) + ". " + result + result1;

                        } else {
                            String comp_no = sheet.getCell(1, 2).getContents().trim();
                            String value = sheet.getCell(1, row).getContents().trim();
                            String param_desc = "" + kitParams.get(j);
                            if (!value.equals("")) {
                                String res = methodutil.validateCompParameter(comp_no, "KIT", param_desc, value, conn);
                                if (!res.equals("success")) {
                                    return "There is an error at Column 2, Row " + (row + 1) + ". " + res + result1;
                                }
                            }
                        }
                        row++;

                    }

                    int levelRow = kitParam_size + 5;
                    if (!sheet.getCell(0, levelRow - 1).getContents().equals("")) {
                        result = "Row should be blank.";
                        return "There is an error at Column 1, Row " + (levelRow) + ". " + result + result1;
                        //return result;
                    }
                    //System.out.println("Level row :"+levelRow);
                    if (!sheet.getCell(0, levelRow).getContents().equals("LEVEL")) {
                        result = "'LEVEL' Missing.";
                        return "There is an error at Column 1, Row " + (levelRow + 1) + ". " + result + result1;
                        //return result;
                    }
                    if (!sheet.getCell(1, levelRow).getContents().equals("ITEM")) {
                        result = "'ITEM' Missing.";
                        return "There is an error at Column 2, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (!sheet.getCell(2, levelRow).getContents().equals("DESCRIPTION")) {
                        result = "'DESCRIPTION' Missing.";
                        return "There is an error at Column 3, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (!sheet.getCell(3, levelRow).getContents().equals("TYPE")) {
                        result = "'TYPE' Missing.";
                        return "There is an error at Column 4, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (!sheet.getCell(4, levelRow).getContents().equals("QTY")) {
                        result = "'QTY' Missing.";
                        return "There is an error at Column 5, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (sheet.getCell(0, levelRow + 1).getContents().equals("end")) {
                        result = "No Records Available for Assembly BOM.";
                        return "There is an error at Column 1, Row " + (levelRow + 2) + ". " + result + result1;
                    }
                    Vector allData1 = new Vector();
                    for (int col = 0; col < columns; col++) {
                        allData1.add(sheet.getCell(col, levelRow).getContents().trim());

                    }
                    for (int i = 0; i < dbParam.size(); i++) {
                        String param_InExcel = "";
                        try {
                            param_InExcel = "" + allData1.elementAt(i + 5);
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            result = "Parameter Name doesn't match with '" + dbParam.get(i) + "'.";
                            return "There is an error at Column " + (i + 6) + ", Row " + (levelRow + 1) + ". " + result + result1;
                        }

                        if (!param_InExcel.equals(dbParam.get(i))) {

                            result = "Parameter Name doesn't match with '" + dbParam.get(i) + "'.";
                            return "There is an error at Column " + (i + 6) + ", Row " + (levelRow + 1) + ". " + result + result1;
                        }
                    }


                    int endRow = 0;
                    int r = levelRow + 1;
                    try {
                        while (!(sheet.getCell(0, r).getContents().trim()).equals("end")) {
                            if ((sheet.getCell(0, r).getContents().trim()).equals("")) {
                                result = "Level can not be blank. Specify 'end' if Required.";
                                return "There is an error at Column 1, Row " + r + ". " + result + result1;
                            } else {
                                level_InExcel.add(sheet.getCell(0, r).getContents());
                                item_InExcel.add(sheet.getCell(1, r).getContents());
                                des_InExcel.add(sheet.getCell(2, r).getContents());
                                compType_InExcel.add(sheet.getCell(3, r).getContents());
                                qty_InExcel.add(sheet.getCell(4, r).getContents());
                                r++;
                            }

                        }
                        endRow = r;

                    } catch (Exception ne) {
                        result = "Excel should be end with 'end'";
                        return "There is an error at Column 1, Row " + r + ". " + result + result1;
                    }
                    ArrayList assy_Detail = new ArrayList();
                    assy_Detail.add(item_InExcel);
                    assy_Detail.add(level_InExcel);
                    assy_Detail.add(compType_InExcel);

                    if (!level_InExcel.get(0).equals("1")) {
                        result = "Kit BOM should start with Level '1' only.";
                        return "There is an error at Column 1, Row " + (levelRow + 2) + ". " + result + result1;
                    }

                    for (int i = 0; i < compType_InExcel.size(); i++) {
                        try {
                            String level = "" + level_InExcel.get(i);
                            int level_val = Integer.parseInt(level);

                            if (level_InExcel.get(i).equals("0")) {
                                result = "Level can not be 0.";
                                return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                            }

                            if (i != level_InExcel.size() - 1) {
                                String levela = "" + level_InExcel.get(i + 1);
                                int levela_val = Integer.parseInt(levela);
                                if (compType_InExcel.get(i).equals("ASM") || compType_InExcel.get(i).equals("KIT")) {
                                    if ((level_val + 1) != levela_val) {
                                        result = "Level is not valid.";
                                        return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                                    }
                                } else {
                                    if ((level_val) != levela_val && levela_val != 1) {
                                        result = "Kit BOM should start with Level '1' only.";
                                        return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                                    }
                                }
                            }


                            //return true;
                        } catch (Exception e) {
                            //System.out.println("Exception :"+ e);

                            result = "Only numeric values are allowed in Level.";
                            return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                        }

                        if (item_InExcel.get(i).equals("")) {
                            result = "Item can not be blank.";
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        if (item_InExcel.get(i).toString().equalsIgnoreCase(kitno)) {
                            result = "Item can not be same as Kit Number.";
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        if (("" + item_InExcel.get(i)).length() > 31) {
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + "Item can not be greater than 31 characters." + result1;
                        }
                        r1 = methodutil.checkSpecialPart_1(("" + item_InExcel.get(i)));
                        if (r1 != true) {
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + "Space and Special Symbols are not allowed in Item Number.<br>Allowed Special Symbols are (_) underscore and (-) Hyphen." + result1;
                        }
                        String compexistindb = new EAMG_MethodsUtility().checkCompExistsinDB(("" + item_InExcel.get(i)).toUpperCase(), conn);
                        if (!compexistindb.equals("" + compType_InExcel.get(i))) {
                            String type = "";
                            if (compexistindb.equals("PRT")) {
                                type = "Part";
                            } else if (compexistindb.equals("ASM")) {
                                type = "Assembly";
                            } else if (compexistindb.equals("GROUP")) {
                                type = "Group";
                            } else if (compexistindb.equals("MODEL")) {
                                type = "Model";
                            } else {
                                type = "Tool";
                            }
                            result = "Item '" + "" + item_InExcel.get(i) + "' exists as '" + type + "' in database.";
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        if (des_InExcel.get(i).equals("")) {
                            result = "Description can not be blank.";
                            return "There is an error at Column 3, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        //System.out.println("compType_InExcel.get(i)::"+compType_InExcel.get(i));
                        if (!((("" + compType_InExcel.get(i)).equals("KIT")) || (("" + compType_InExcel.get(i)).equals("ASM")) || (("" + compType_InExcel.get(i)).equals("PRT")))) {
                            result = "Type Shoud be ASM , PRT or KIT only. ";
                            return "There is an error at Column 4, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }


                        String qty = "" + qty_InExcel.get(i);
                        //System.out.println("qty::"+qty);
                        if (qty.equals("")) {
                            result = "Quantity of any Component can not be blank.";
                            return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                        } else {
                            if (qty.equals("0")) {
                                result = "Quantity can not be 0.";
                                return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                            }
                            boolean b = new EAMG_MethodsUtility().checkNumeric(qty);
                            if (b == false) {
                                result = "No Special Symbols allowed in quantity.";
                                return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                            }
                            if (!qty.equals("AR")) {
                                try {

                                    int quan = Integer.parseInt(qty);
                                    if (quan > 500) {
                                        result = "Quantity can not be greater than 500. Give 'AR' for 'As Required'.";
                                        return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                                    }
                                } catch (Exception e) {
                                    //System.out.println("Exception :"+ e);
                                    result = "Only numeric values are allowed as quantity and quantity can not be greater than 500 or Give 'AR' for 'As Required'.";
                                    return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                                }
                            }
                        }

                    }

                    result = new EAMG_MethodUtility2().checkDuplicates(item_InExcel, compType_InExcel);
                    if (!result.equals("")) {
                        return result + result1;
                    }
                    result = new EAMG_MethodUtility2().checkAssyBom(assy_Detail, "KIT");
                    //System.out.println("result :" + result);
                    if (!result.equals("success")) {
                        return result + result1;
                    }
                    result = new EAMG_MethodUtility2().checkAssyBom(assy_Detail, "ASM");
                    //System.out.println("result :" + result);
                    if (!result.equals("success")) {
                        return result + result1;
                    }
                    Vector params = new Vector();
                    for (int i = 0; i < (allData1.size() - 5); i++) {
                        params.add(sheet.getCell(i + 5, levelRow).getContents());
                    }
                    //System.out.println("params :" + params);
                    for (int i = 0; i < params.size(); i++) {
                        int colmn = levelRow + 1;
                        ArrayList tempAl = new ArrayList();
                        int colUsed = (5 + i);
                        int cnt = 1;
                        for (int rw = colmn; rw < endRow; rw++) {
                            tempAl.add(sheet.getCell(colUsed, (levelRow + cnt)).getContents());
                            cnt++;
                        }

                        for (int j = 0; j < tempAl.size(); j++) {
                            String comp_no = "" + item_InExcel.get(j);
                            String comp_type = "" + compType_InExcel.get(j);
                            String param_desc = "" + params.elementAt(i);
                            String value = "" + tempAl.get(j);
                            String message = "";
                            //System.out.println("all values are :"+comp_no + "; "+comp_type+ ": "+param_desc+ ":"+ value);

                            if (!value.equals("")) {
                                message = new EAMG_MethodsUtility().validateCompParameter(comp_no, comp_type, param_desc, value, conn);

                                //System.out.println("message:"+message);
                                if (!message.equals("success")) {
                                    result = message;
                                    return "There is an error at Column " + (colUsed + 1) + ", Row " + (levelRow + j + 2) + ". " + result + result1;
                                }
                            }
                        }
                    }

//                    for(int row = 0;row < endRow;row++){
//                        for(int col = 0;col < columns;col++) {
//                            String special=sheet.getCell(col, row).getContents();
//
//                            boolean b= new EAMG_MethodsUtility().checkSpecialSymbol(special);
//                            if(b==false) {
//                                result="Special characters are not allowed.";
//                                return "There is an error at Column "+(col+1)+", Row " +(row+1)+". "+result+result1;
//                            }
//                        }
//                    }

                    result = "Excel Validation Successful";

                } else {
                    result = "'KIT NUMBER' Missing.";
                    return "There is an error at Column 1, Row 3. " + result + result1;
                }

            } else {
                result = "'KIT TEMPLATE' Missing.";
                return "There is an error at Column 1, Row 1. " + result + result1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "TEMPLATE MISMATCH or 'end' Missing.";
            return result + result1;
        }
        return result;
    }

    public String isAssemblyExcel(String fileName, int index, Sheet sheet, Connection conn) throws Exception {

        String result = "";
        String result1 = "<br>Hence Assembly Creation Process aborted. Attach valid Excel to complete the Process successfully.";

        HashSet allData = new HashSet();
        ArrayList assyParams = new ArrayList();
        ArrayList dbParam = new ArrayList();
        ArrayList level_InExcel = new ArrayList();
        ArrayList compType_InExcel = new ArrayList();
        ArrayList item_InExcel = new ArrayList();
        ArrayList des_InExcel = new ArrayList();
        ArrayList qty_InExcel = new ArrayList();

        HashSet assyExcelData = new HashSet();
        int columns = sheet.getColumns();
        int rows = sheet.getRows();
        ResultSet rs, rs1 = null;
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        boolean r1 = false;

        try {
        	//Statement stmt, stmt1 = null;
            PreparedStatement stmt, stmt1 = null;
            String selectQury = "select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='ASM' ";
            stmt = conn.prepareStatement(selectQury);
            rs = stmt.executeQuery();
           // stmt = conn.createStatement();
            //stmt1 = conn.createStatement();

            //rs = stmt.executeQuery(selectQury);

            while (rs.next()) {
                assyParams.add(rs.getString(1));
            }
            dbParam = new EAMG_MethodUtility2().param_partUassy(conn);

            int assyParams_size = assyParams.size();


            String testSpecialChar = "";

            if ((sheet.getCell(0, 0).getContents().trim()).equals("ASSEMBLY TEMPLATE")) {
                if (!sheet.getCell(0, 1).getContents().equals("")) {
                    result = "Row should be blank.";
                    return "There is an error at Column 1, Row 2. " + result + result1;
                    //return result;
                }
                if ((sheet.getCell(0, 2).getContents().trim()).equals("ASSEMBLY NUMBER")) {


                    String assno = sheet.getCell(1, 2).getContents().trim();
                    if (assno.equals("")) {
                        result = "Assembly Number cannot be blank.";
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }
                    String comp_in_db = new EAMG_MethodsUtility().isCompPresent(assno, conn);
                    if (!comp_in_db.equals("notpresent")) {
                        result = comp_in_db;
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }
                    if (assno.length() > 31) {
                        result = "Assembly Number can not be greater than 31 characters.";
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }

                    r1 = methodutil.checkSpecialPart_1(assno);
                    if (r1 != true) {
                        result = "Space and Special Symbols are not allowed in Assembly Number.<br>Allowed Special Symbols are (_) underscore and (-) Hyphen.";
                        return "There is an error at Column 2, Row 3. " + result + result1;

                    }

                    if ((sheet.getCell(0, 3).getContents().trim()).equals("ASSEMBLY DESCRIPTION")) {
                        if (sheet.getCell(1, 3).getContents().equals("")) {
                            result = "Assembly Description cannot be blank.";
                            return "There is an error at Column 2, Row 4. " + result + result1;
                        }

                    } else {
                        result = "'ASSEMBLY DESCRIPTION' Missing.";
                        return "There is an error at Column 1, Row 4. " + result + result1;
                    }
                    int row = 4;
                    for (int j = 0; j < assyParams.size(); j++) {

                        if (!(sheet.getCell(0, row).getContents().trim()).equals(assyParams.get(j)) && row < rows) {
                            result = "Parameter Name doesn't match with '" + assyParams.get(j) + "'.";
                            return "There is an error at Column 1, Row " + (row + 1) + ". " + result + result1;

                        } else {
                            String comp_no = sheet.getCell(1, 2).getContents().trim();
                            String value = sheet.getCell(1, row).getContents().trim();
                            String param_desc = "" + assyParams.get(j);
                            if (!value.equals("")) {
                                String res = methodutil.validateCompParameter(comp_no, "ASM", param_desc, value, conn);
                                if (!res.equals("success")) {
                                    return "There is an error at Column 2, Row " + (row + 1) + ". " + res + result1;
                                }
                            }
                        }
                        row++;

                    }
                    int levelRow = assyParams_size + 5;
                    if (!sheet.getCell(0, levelRow - 1).getContents().equals("")) {
                        result = "Row should be blank.";
                        return "There is an error at Column 1, Row " + (levelRow) + ". " + result + result1;
                        //return result;
                    }

                    if (!sheet.getCell(0, levelRow).getContents().equals("LEVEL")) {
                        result = "'LEVEL' Missing.";
                        return "There is an error at Column 1, Row " + (levelRow + 1) + ". " + result + result1;
                        //return result;
                    }
                    if (!sheet.getCell(1, levelRow).getContents().equals("ITEM")) {
                        result = "'ITEM' Missing.";
                        return "There is an error at Column 2, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (!sheet.getCell(2, levelRow).getContents().equals("DESCRIPTION")) {
                        result = "'DESCRIPTION' Missing.";
                        return "There is an error at Column 3, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (!sheet.getCell(3, levelRow).getContents().equals("TYPE")) {
                        result = "'TYPE' Missing.";
                        return "There is an error at Column 4, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (!sheet.getCell(4, levelRow).getContents().equals("QTY")) {
                        result = "'QTY' Missing.";
                        return "There is an error at Column 5, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (sheet.getCell(0, levelRow + 1).getContents().equals("end")) {
                        result = "No Records Available for Assembly BOM.";
                        return "There is an error at Column 1, Row " + (levelRow + 2) + ". " + result + result1;
                    }
//                    if(sheet.getCell(0,levelRow+1).getContents().equals("")) {
//                        result="Level should not be blank. Specify 'end' if Required";
//                        return "There is an error at Column 1, Row "+(levelRow+2)+". "+result+result1;
//                    }
//                    if(!sheet.getCell(0,levelRow+1).getContents().equals("1")) {
//                        result="First value for Level column should be '1' only.";
//                        return "There is an error at Column 1, Row "+(levelRow+2)+". "+result;
//                    }
                    Vector allData1 = new Vector();
                    for (int col = 0; col < columns; col++) {
                        //System.out.println(sheet.getCell(col,assyParams_size+5).getContents().trim());
                        //allData.add(sheet.getCell(col,assyParams_size+5).getContents().trim());
                        allData1.add(sheet.getCell(col, assyParams_size + 5).getContents().trim());
                    }
//                    //System.out.println("dbParam :"+dbParam);
//                    //System.out.println("allData :"+allData);
                    //System.out.println("allData1 :"+allData1);

                    for (int i = 0; i < dbParam.size(); i++) {
                        String param_InExcel = "";
                        try {
                            param_InExcel = "" + allData1.elementAt(i + 5);
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            result = "Parameter Name doesn't match with '" + dbParam.get(i) + "'.";
                            return "There is an error at Column " + (i + 6) + ", Row " + (levelRow + 1) + ". " + result + result1;
                        }

                        if (!param_InExcel.equals(dbParam.get(i))) {

                            result = "Parameter Name doesn't match with '" + dbParam.get(i) + "'.";
                            return "There is an error at Column " + (i + 6) + ", Row " + (levelRow + 1) + ". " + result + result1;
                        }
                    }




                    int endRow = 0;

                    int r = levelRow + 1;
                    try {
                        while (!(sheet.getCell(0, r).getContents().trim()).equals("end")) {
                            //System.out.println(sheet.getCell(0,r).getContents().trim());
                            if ((sheet.getCell(0, r).getContents().trim()).equals("")) {
                                result = "Level can not be blank. Specify 'end' if Required.";
                                return "There is an error at Column 1, Row " + (r + 1) + ". " + result + result1;
                            } else {
                                level_InExcel.add(sheet.getCell(0, r).getContents());
                                item_InExcel.add(sheet.getCell(1, r).getContents());
                                des_InExcel.add(sheet.getCell(2, r).getContents());
                                compType_InExcel.add(sheet.getCell(3, r).getContents());
                                qty_InExcel.add(sheet.getCell(4, r).getContents());
                                r++;
                            }

                        }
                        endRow = r;

                    } catch (Exception ne) {
                        result = "Excel should be end with 'end'";
                        return "There is an error at Column 1, Row " + (r + 3) + ". " + result + result1;
                    }
                    ArrayList assy_Detail = new ArrayList();
                    assy_Detail.add(item_InExcel);
                    assy_Detail.add(level_InExcel);
                    assy_Detail.add(compType_InExcel);

                    if (!level_InExcel.get(0).equals("1")) {
                        result = "Assembly BOM should start with Level '1' only.";
                        return "There is an error at Column 1, Row " + (levelRow + 2) + ". " + result + result1;
                    }

                    for (int i = 0; i < compType_InExcel.size(); i++) {
                        try {
                            String level = "" + level_InExcel.get(i);
                            ////System.out.println("level data ;"+ level);
                            int level_val = Integer.parseInt(level);

                            if (level_InExcel.get(i).equals("0")) {
                                result = "Level can not be 0.";
                                return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                                //return result;
                            }

                            if (i != level_InExcel.size() - 1) {
                                String levela = "" + level_InExcel.get(i + 1);
                                int levela_val = Integer.parseInt(levela);
                                if (compType_InExcel.get(i).equals("ASM")) {
                                    if ((level_val + 1) != levela_val) {
                                        result = "Level is not valid.";
                                        return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                                    }
                                } else {
                                    if ((level_val) != levela_val && levela_val != 1) {
                                        result = "Assembly BOM should start with Level '1' only.";
                                        return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                                    }
                                }
                            }

                            //return true;
                        } catch (Exception e) {
                            //System.out.println("Exception :"+ e);
                            // return false;
                            result = "Only numeric values are allowed in Level.";
                            return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                        }
                        if (item_InExcel.get(i).equals("")) {
                            result = "Item can not be blank.";
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        if (item_InExcel.get(i).toString().equalsIgnoreCase(assno)) {
                            result = "Item can not be same as Assembly Number.";
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        if (("" + item_InExcel.get(i)).length() > 31) {
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + "Item can not be greater than 31 characters." + result1;
                        }
                        r1 = methodutil.checkSpecialPart_1(("" + item_InExcel.get(i)));
                        if (r1 != true) {
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + "Space and Special Symbols are not allowed in Item Number.<br>Allowed Special Symbols are (_) underscore and (-) Hyphen." + result1;
                        }
                        String compexistindb = new EAMG_MethodsUtility().checkCompExistsinDB(("" + item_InExcel.get(i)).toUpperCase(), conn);
                        if (!compexistindb.equals("" + compType_InExcel.get(i))) {
                            String type = "";
                            if (compexistindb.equals("PRT")) {
                                type = "Part";
                            } else if (compexistindb.equals("KIT")) {
                                type = "Kit";
                            } else if (compexistindb.equals("GROUP")) {
                                type = "Group";
                            } else if (compexistindb.equals("MODEL")) {
                                type = "Model";
                            } else {
                                type = "Tool";
                            }
                            result = "Item '" + "" + item_InExcel.get(i) + "' exists as '" + type + "' in database.";
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        if (des_InExcel.get(i).equals("")) {
                            result = "Description can not be blank.";
                            return "There is an error at Column 3, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        if (!(compType_InExcel.get(i).equals(("ASM")) || compType_InExcel.get(i).equals("PRT"))) {
                            result = "Type Should be ASM or PRT only. ";
                            return "There is an error at Column 4, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        String qty = "" + qty_InExcel.get(i);
                        //   //System.out.println("qty::"+qty);
                        if (qty.equals("")) {
                            //return false;
                            result = "Quantity of any Component can not be blank.";
                            return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                        } else {
                            if (qty.equals("0")) {
                                result = "Quantity can not be 0.";
                                return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                            }

                            boolean b = new EAMG_MethodsUtility().checkNumeric(qty);
                            if (b == false) {
                                result = "No Special Symbols allowed in quantity.";
                                return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                            }
                            if (!qty.equals("AR")) {
                                try {
                                    //System.out.println("Quantity :"+qty);

                                    int quan = Integer.parseInt(qty);
                                    if (quan > 500) {
                                        result = "Quantity can not be greater than 500. Give 'AR' for 'As Required'.";
                                        return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                                    }
                                } catch (Exception e) {
                                    //System.out.println("Exception :"+ e);
                                    result = "Only numeric values are allowed as quantity and quantity can not be greater than 500 or Give 'AR' for 'As Required'.";
                                    return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                                }
                            }


                        }
                    }
                    for (int i = 0; i < level_InExcel.size(); i++) {
                        if (compType_InExcel.get(i).equals("ASM")) {
                            if (level_InExcel.size() == 1) {
                                result = "An Assembly must have sub elements.";
                                return "There is an error at Column 2, Row " + (levelRow + 2) + ". " + result + result1;
                            }
                            if (i < level_InExcel.size()) {
                                String temp_flevel = "" + level_InExcel.get(i);
                                String temp_slevel = "";
                                try {
                                    temp_slevel = "" + level_InExcel.get(i + 1);
                                } catch (Exception exception) {
                                    result = "An Assembly must have sub elements.";
                                    return "There is an error at Column 2, Row " + (levelRow + i + 3) + ". " + result + result1;

                                }


                                int f_level = Integer.parseInt(temp_flevel);
                                int s_level = Integer.parseInt(temp_slevel);

                                if (s_level > f_level) {
                                } else {
                                    result = "An Assembly must have sub elements.";
                                    return "There is an error at Column 2, Row " + (levelRow + i + 3) + ". " + result + result1;
                                }
                            }
                        }
                    }

                    result = new EAMG_MethodUtility2().checkDuplicates(item_InExcel, compType_InExcel);
                    if (!result.equals("")) {
                        return result + result1;
                    }
                    result = new EAMG_MethodUtility2().checkAssyBom(assy_Detail, "ASM");
                    ////System.out.println("result :" + result);
                    if (!result.equals("success")) {
                        return result + result1;
                    }
                    Vector params = new Vector();
                    for (int i = 0; i < (allData1.size() - 5); i++) {
                        params.add(sheet.getCell(i + 5, levelRow).getContents());
                    }

                    for (int i = 0; i < params.size(); i++) {
                        int colmn = levelRow + 1;
                        Vector tempAl = new Vector();
                        int colUsed = (5 + i);
                        int cnt = 1;
                        for (int rw = colmn; rw < endRow; rw++) {
                            tempAl.add(sheet.getCell(colUsed, (levelRow + cnt)).getContents());
                            cnt++;
                        }
                        //System.out.println("tempAl :"+tempAl);
                        for (int j = 0; j < tempAl.size(); j++) {
                            String comp_no = "" + item_InExcel.get(j);
                            String comp_type = "" + compType_InExcel.get(j);
                            String param_desc = "" + params.elementAt(i);
                            String value = "" + tempAl.elementAt(j);
                            String message = "";
                            // //System.out.println("all values are :"+comp_no + "; "+comp_type+ ": "+param_desc+ ":"+ value);

                            if (!value.equals("")) {
                                message = new EAMG_MethodsUtility().validateCompParameter(comp_no, comp_type, param_desc, value, conn);

                                //  //System.out.println("message:"+message);
                                if (!message.equals("success")) {
                                    result = message;
                                    return "There is an error at Column " + (colUsed + 1) + ", Row " + (levelRow + j + 2) + ". " + result + result1;
                                }
                            }
                        }
                    }

//                    for(int row = 0;row < endRow;row++){
//                        for(int col = 0;col < columns;col++) {
//                            String special=sheet.getCell(col, row).getContents();//
//
//                            boolean b= new EAMG_MethodsUtility().checkSpecialSymbol(special);
//                            if(b==false) {
//                                result="Special characters are not allowed.";
//                                return "There is an error at Column "+(col+1)+", Row " +(row+1)+". "+result+result1;
//                            }
//                        }
//                    }

                    result = "Excel Validation Successful";

                } else {
                    result = "'ASSEMBLY NUMBER' Missing.";
                    return "There is an error at Column 1, Row 3. " + result + result1;
                }

            } else {
                result = "'ASSEMBLY TEMPLATE' Missing.";
                return "There is an error at Column 1, Row 1. " + result + result1;

            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "TEMPLATE MISMATCH or 'end' Missing.";
            return result + result1;
        }

        return result;
    }
    ///////////////////////////////////////////////////////////////////////////////////
    ////9.to validate Assembly Excel with data./////////////////////////////////////////////////

    public String isAssemblyExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("xlsfile:"+xlsfile);
        //declaration of variables used.
       // Statement stmt = conn.createStatement();
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0, c = 0;
        int sheet_col = 0;
        int count = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        String param = "";
        String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();

        try {
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for ASSEMBLY TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("ASSEMBLY TEMPLATE"))) {
                err = row + "@@" + column + "##'ASSEMBLY TEMPLATE' Missing.";
                ////System.out.println(err);
                return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##ROW SHOULD BE BLANK.";
                ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
                return err;
            }
            row++;
            //checking for ASSEMBLY NUMBER.
            if (!(sheet.getCell(column, row).getContents().trim().equals("ASSEMBLY NUMBER"))) {
                err = row + "@@" + column + "##'ASSEMBLY NUMBER' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL NUMBER MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("ASSEMBLY DESCRIPTION"))) {
                err = row + "@@" + column + "##'ASSEMBLY DESCRIPTION' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION MISSING");
                return err;
            }
            column++;
            //checking for ASSEMBLY PARAMETER DESCRIPTION from COMP_PARAM_MASTER
           // rs = stmt.executeQuery("select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='ASM' order by PARAM_DESC");
            String query = ("select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='ASM' order by PARAM_DESC");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            sheet_col = sheet.getColumns();
            ////System.out.println("Sheet Columns:"+sheet_col);

            while (rs.next()) {
                paramVec.add(rs.getString(1));
                count++;
            }
            //checking for number of parameters.
            if (count != (sheet_col - 2)) {
                column = sheet_col;
                if (count < (sheet_col - 2)) {
                    column = sheet_col - 1;
                }
                err = row + "@@" + column + "##Number of Parameters does not match.";
                ////System.out.println(row+"@@"+column+"@@NUMBER OF PARAMETERS ARE DIFFERENT"+sheet_col);
                return err;
            }
            //checking for parameter names.
            for (int k = 0; k < paramVec.size(); k++) {
                param = "" + paramVec.elementAt(k);
                if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(param))) {
                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + param + "'.";
                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
                    return err;
                }
                column++;
            }

            column = 0;
            row++;
            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters
            while (row < totalrows) {
                comp_no = "";
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 2; column++) {
                        //if Assembly Number is null.
                        if (sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("")) {
                            if (column == 0) {
                                err = row + "@@" + column + "##Assembly Number can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@PART NUMBER CAN NOT BE NULL");
                                return err;
                            } //if Part Description is null.
                            else {
                                err = row + "@@" + column + "##Assembly Description can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } //checking for special symbols.
                        else if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.length() > 31) {
                                return row + "@@" + column + "##Assembly Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(partno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Assembly Number. Allowed Special Symbols are (_) underscore and (-) Hyphen.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        }

                    }


                    c = column;
                    //checking for Assembly Parameter Values.
                    for (column = c; column < sheet_col; column++) {
                        String value = sheet.getCell(column, row).getContents().trim();
                        String param_desc = sheet.getCell(column, 2).getContents().trim();
                        if (!value.equals("")) {
                            String res = methodutil.validateCompParameter(comp_no, "ASM", param_desc, value, conn);
                            if (!res.equals("success")) {
                                err = row + "@@" + column + "##" + res;
                                ////System.out.println(row+"@@"+column+"##"+res);
                                return err;
                            }
                        }

                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }
            //if 'end' missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' Missing");
                return err;
            }
        } //if 'end' missing.
        catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row + "@@" + column + "@@'END' MISSING");
            return err;

        } //if excel is currupted.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }
    ////////////////////////////////////////////////////////////////////////////////
    ////10.to validate Kit Excel with data./////////////////////////////////////////////////

    public String isKitExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("xlsfile:"+xlsfile);
        //declaration of variables used.
        //Statement stmt = conn.createStatement();
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0, c = 0;
        int sheet_col = 0;
        int count = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        String param = "";
        String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();

        try {
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for KIT TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("KIT TEMPLATE"))) {
                err = row + "@@" + column + "##'KIT TEMPLATE' Missing.";
                ////System.out.println(err);
                return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##ROW SHOULD BE BLANK.";
                ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
                return err;
            }
            row++;
            //checking for KIT NUMBER.
            if (!(sheet.getCell(column, row).getContents().trim().equals("KIT NUMBER"))) {
                err = row + "@@" + column + "##'KIT NUMBER' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL NUMBER MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("KIT DESCRIPTION"))) {
                err = row + "@@" + column + "##'KIT DESCRIPTION' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION MISSING");
                return err;
            }
            column++;
            //checking for KIT PARAMETER DESCRIPTION from COMP_PARAM_MASTER
            //rs = stmt.executeQuery("select PARAM_DESC from COMP_PARAM_MASTER where PARAM_TYPE='KIT' order by PARAM_DESC");
            String query = ("select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='KIT' order by PARAM_DESC");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            sheet_col = sheet.getColumns();
            ////System.out.println("Sheet Columns:"+sheet_col);

            while (rs.next()) {
                paramVec.add(rs.getString(1));
                count++;
            }
            //checking for number of parameters.
            if (count != (sheet_col - 2)) {
                column = sheet_col;
                if (count < (sheet_col - 2)) {
                    column = sheet_col - 1;
                }
                err = row + "@@" + column + "##Number of Parameters does not match.";
                ////System.out.println(row+"@@"+column+"@@NUMBER OF PARAMETERS ARE DIFFERENT"+sheet_col);
                return err;
            }
            //checking for parameter names.
            for (int k = 0; k < paramVec.size(); k++) {
                param = "" + paramVec.elementAt(k);
                if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(param))) {
                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + param + "'.";
                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
                    return err;
                }
                column++;
            }

            column = 0;
            row++;
            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters
            while (row < totalrows) {
                comp_no = "";
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 2; column++) {
                        //if Assembly Number is null.
                        if (sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("")) {
                            if (column == 0) {
                                err = row + "@@" + column + "##Kit Number can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@PART NUMBER CAN NOT BE NULL");
                                return err;
                            } //if Part Description is null.
                            else {
                                err = row + "@@" + column + "##Kit Description can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } //checking for special symbols.
                        else if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.length() > 31) {
                                return row + "@@" + column + "##Kit Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(partno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Kit Number. Allowed Special Symbols are (_) underscore and (-) Hyphen.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        }

                    }


                    c = column;
                    //checking for Kit Parameter Values.
                    for (column = c; column < sheet_col; column++) {
                        String value = sheet.getCell(column, row).getContents().trim();
                        String param_desc = sheet.getCell(column, 2).getContents().trim();
                        if (!value.equals("")) {
                            String res = methodutil.validateCompParameter(comp_no, "KIT", param_desc, value, conn);
                            if (!res.equals("success")) {
                                err = row + "@@" + column + "##" + res;
                                ////System.out.println(row+"@@"+column+"##"+res);
                                return err;
                            }
                        }

                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }
            //if 'end' missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' Missing");
                return err;
            }
        } //if 'end' missing.
        catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row + "@@" + column + "@@'END' MISSING");
            return err;

        } //if excel is currupted.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }

    public ArrayList isVehicleExcelValidated(File xlsfile, Connection conn) throws Exception {
        //Statement st = conn.createStatement();
       PreparedStatement st = null;
    	ResultSet rs = null;
        int row = 0;
        int column = 0;
        Workbook workbook1 = null;
        EAMG_Variant_AggregatesActionForm formBean = null;
        ArrayList result = new ArrayList();
        ArrayList<EAMG_Variant_AggregatesActionForm> dataInExcel = new ArrayList<EAMG_Variant_AggregatesActionForm>();

        ///////////////////////////CHECK FOR CORRUPTED EXCEL//////////////////////////
        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.add(0, "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path.");
            return result;

        }
        //////////////////////////////////////////////////////////////////////////////
        Sheet sheet = workbook1.getSheet(0);
        EAMG_MethodsUtility method_obj = new EAMG_MethodsUtility();
        try {
            int templateCols = 5;// + method_obj.getPrtUAsmParamCount(conn);
            if (templateCols != sheet.getColumns()) {
                System.out.println("sheet.getColumns():" + sheet.getColumns());
                result.add(0, "Excel File Columns does not match with Template Columns.");
                return result;
            }
            //////////////////////////////////////////////////////////////////////////

            ////////////////////TEMPLATE CHECKING STARTS//////////////////////////////
            if (!(sheet.getCell(column, row).getContents().trim().equals("ADD VEHICLE TEMPLATE"))) {
                result.add(0, row + "@@" + column);
                return result;
            }
            row += 2;

            if (!(sheet.getCell(column, row).getContents().trim().equals("VEHICLE NUMBER"))) {
                result.add(0, row + "@@" + column);
                return result;
            }
            if (!(sheet.getCell(column + 1, row).getContents().trim().equals("VARIANT NAME"))) {
                result.add(0, row + "@@" + column);
                return result;
            }
            if (!(sheet.getCell(column + 2, row).getContents().trim().equals("MFG. DATE(DD/MM/YYYY)"))) {
                result.add(0, row + "@@" + column);
                return result;
            }
            if (!(sheet.getCell(column + 3, row).getContents().trim().equals("DESCRIPTION"))) {
                result.add(0, row + "@@" + column);
                return result;
            }
            if (!(sheet.getCell(column + 4, row).getContents().trim().equals("STATUS"))) {
                result.add(0, row + "@@" + column);
                return result;
            }
            row++;

            if (sheet.getCell(0, row).getContents().trim().equals("end")) {
                result.add(0, "No  Data is available in the Excel File.");
                return result;

            } else {

                row_index_bom = row;
                while (!sheet.getCell(0, row).getContents().trim().equals("end")) {
                    formBean = new EAMG_Variant_AggregatesActionForm();
                    //System.out.println("in while");
                    for (int i = 0; i < 5; i++) {

                        String cell_contents = sheet.getCell(i, row).getContents().trim();

                        if (i == 0)//for validating Seq.No. column
                        {
                            if (cell_contents.equals("")) {
                                result.add(0, "Vehicle Number column can not be blank at Row '" + (row + 1) + "'.");
                                return result;
                            } else {
                                if (!method_obj.checkSpecialPart_1(cell_contents)) {
                                    result.add(0, "Vehicle Number at Row '" + (row + 1) + "' can not contain any special character.");
                                    return result;
                                }
                            }
                            formBean.setVehiclenumber(cell_contents);
                        } else if (i == 1)//for validating Seq.No. column
                        {
                            if (cell_contents.equals("")) {
                                result.add(0, "Variant Name column can not be blank at Row '" + (row + 1) + "'.");
                                return result;
                            } else if (!method_obj.checkSpecialSymbolDescription(cell_contents)) {
                                result.add(0, "Variant Name at Row '" + (row + 1) + "' can not contain any special character.");
                                return result;
                            } else if (!method_obj.isVariantPresent(conn, cell_contents)) {
                                result.add(0, "Variant Name at Row '" + (row + 1) + "' is not exist in database.");
                                return result;
                            }

                            formBean.setVariantname(cell_contents);
                        } else if (i == 2)//for validating Seq.No. column
                        {
                            if (cell_contents.equals("")) {
                                result.add(0, "Mfg. Date column can not be blank at Row '" + (row + 1) + "'.");
                                return result;
                            }
                            formBean.setMfgdate(cell_contents);
                        } else if (i == 3)//for validating Seq.No. column
                        {
                            if (cell_contents.equals("")) {
                                result.add(0, "Description column can not be blank at Row '" + (row + 1) + "'.");
                                return result;
                            } else {
                                if (!method_obj.checkSpecialSymbolDescription(cell_contents)) {
                                    result.add(0, "Description at Row '" + (row + 1) + "' can not contain any special character.");
                                    return result;
                                }
                            }
                            formBean.setDescription(cell_contents);
                        } else if (i == 4)//for validating Seq.No. column
                        {
                            if (cell_contents.equals("")) {
                                result.add(0, "Status column can not be blank at Row '" + (row + 1) + "'.");
                                return result;
                            } else {
                                if (!method_obj.checkSpecialSymbolDescription(cell_contents)) {
                                    result.add(0, "Status at Row '" + (row + 1) + "' can not contain any special character.");
                                    return result;
                                }
                            }
                            formBean.setStatus(cell_contents);
                        }
                    }
                    dataInExcel.add(formBean);
                    row++;
                }

            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            result.add(0, row + "@@" + column);
            return result;

        }
        result.add(0, "success@@" + "");
        result.add(1, dataInExcel);
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////
}
