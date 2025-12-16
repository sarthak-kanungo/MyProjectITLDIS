package com.common;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class LogsUtility {
	private static Logger logger = null;

	public static void main(String[] args) {
		// Initialize the logger with a dynamic log file prefix (without the date part)
		String logFilePrefix = "c:/logs";
		cleanupOldLogs(logFilePrefix);
		deleteTaxInvoiceFiles();
	}

	private static void deleteTaxInvoiceFiles() {
	    String folderPath = "C:\\DMS_APP\\Tomcat9_DMS\\webapps\\ITLDIS\\DOCUMENTS\\WARRANTY_TAX_INVOICES";
	    File folder = new File(folderPath);

	    if (folder.exists() && folder.isDirectory()) {
	        File[] files = folder.listFiles();

	        if (files != null && files.length > 0) {
	            for (File file : files) {
	                if (file.isFile()) {
	                    if (file.delete()) {
	                        System.out.println("Deleted: " + file.getName());
	                    } else {
	                        System.err.println("Failed to delete: " + file.getName());
	                    }
	                }
	            }
	        } else {
	            logger.info("No files to delete in: " + folderPath);
	        }
	    } else {
	        logger.warn("Invalid folder path or directory does not exist: " + folderPath);
	    }
	}

	// Method to clean up all old log files except the most recent one
	private static void cleanupOldLogs(String logDirectory) {
		File logDir = new File(logDirectory);

		// Check if the directory exists
		if (logDir.exists() && logDir.isDirectory()) {

			FilenameFilter logFilter = (dir, name) -> !name.matches("ITLDMS.log");

			// List all files in the directory
			File[] files = logDir.listFiles(logFilter);
			if (files != null && files.length > 0) {
				// Sort files by last modified time (most recent last)
				Arrays.sort(files, (file1, file2) -> Long.compare(file1.lastModified(), file2.lastModified()));

				// Delete all files except the most recent one
				for (int i = 0; i < files.length; i++) {
					File oldLogFile = files[i];
					boolean deleted = oldLogFile.delete();
					if (deleted) {
						System.out.println("Deleted old log file: " + oldLogFile.getName());
					} else {
						System.err.println("Failed to delete old log file: " + oldLogFile.getName());
					}
				}
			}
		}
	}

}