package com.i4o.dms.kubota.utils.excelmanager.util;

import com.i4o.dms.kubota.utils.excelmanager.exception.InvalidColumnException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ExcelImportManager {

    public  List<String> getXLSHeaders(Workbook wb) {
        List<String> headers = new ArrayList<>();

        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        row.cellIterator().forEachRemaining(cell -> headers.add(cell.getStringCellValue()));
        return headers;
    }

    public String checkXLSValidity(String []preDefinedColumns, List<String> headers) throws InvalidColumnException {
        StringBuilder msg = new StringBuilder();

        IntStream.range(0, preDefinedColumns.length - 1).forEach(pos -> {
            if (!preDefinedColumns[pos].equals(headers.get(pos))) {
                throw new InvalidColumnException("Invalid column at the position " + pos + " Invalid Column Name is " + headers.get(pos) + "Expected Column is "+preDefinedColumns[pos]);
            }
        });
        if (headers.size() > preDefinedColumns.length) {
            throw new InvalidColumnException("You have an extra column");
        } else if (headers.size() < preDefinedColumns.length) {
            throw new InvalidColumnException("You have an less columns than expected");
        }
        return msg.toString();
    }
}
