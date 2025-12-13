/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG_Service_Bulletin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author satyaprakash.verma
 */
public class FileDownloadServlet extends HttpServlet {

    String getContentType(String fileName) {
        String extension[] = { // File Extensions
            "txt", //0 - plain text
            "htm", //1 - hypertext
            "jpg", //2 - JPEG image
            "png", //2 - JPEG image
            "gif", //3 - gif image
            "pdf", //4 - adobe pdf
            "doc", //5 - Microsoft Word
            "docx",// you can add more
            "sql",}; // you can add more
        String mimeType[] = { // mime types
            "text/plain", //0 - plain text
            "text/html", //1 - hypertext
            "image/jpg", //2 - image
            "image/jpg", //2 - image
            "image/gif", //3 - image
            "application/pdf", //4 - Adobe pdf
            "application/msword", //5 - Microsoft Word
            "application/msword", //5 - Microsoft Word
            "text/plain", //5 - Microsoft Word
        }, // you can add more
                contentType = "text/html";    // default type
        // dot + file extension
        int dotPosition = fileName.lastIndexOf('.');

        // get file extension
        String fileExtension =
                fileName.substring(dotPosition + 1);
        // match mime type to extension
        for (int index = 0; index < mimeType.length; index++) {
            if (fileExtension.equalsIgnoreCase(extension[index])) {
                contentType = mimeType[index];
                break;
            }
        }
        return contentType;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String folderName = request.getParameter("folderName");
        String realname = request.getParameter("fileName");
        //String realPath = getServletContext().getRealPath("/");
        String fullPath =folderName + "/" + realname;
        File file = new File(fullPath);
        String contentType = getContentType(realname);
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment; filename=" + realname);
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        if (file.exists()) {
            FileInputStream fin = new FileInputStream(file);
            fin.read(bytes);
            ServletOutputStream os = response.getOutputStream();
            os.write(bytes);
            os.flush();
        }
    }
}
