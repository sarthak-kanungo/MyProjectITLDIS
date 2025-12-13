package com.i4o.dms.itldis.configurations;

import com.i4o.dms.itldis.utils.excelmanager.util.ExcelImportManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManageExcelImport {
    @Bean
    public ExcelImportManager excelImportManager() {
        return new ExcelImportManager();
    }
}
