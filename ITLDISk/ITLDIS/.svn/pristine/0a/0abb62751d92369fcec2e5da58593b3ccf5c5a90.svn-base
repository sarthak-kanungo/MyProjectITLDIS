package com.common;

import java.sql.Connection;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

public class FindTextCoordinates {

    public static String getSignatureLocation(String filePath, String searchText, Connection conn) throws Exception {

        PdfReader reader = new PdfReader(filePath);
        int totalPages = reader.getNumberOfPages();
        StringBuilder result = new StringBuilder();
        MethodUtility obj = new MethodUtility();

        // Get X and Y values from DB
        String x = obj.getSPASConstants(conn, 15);
        String y = obj.getSPASConstants(conn, 16);

        for (int page = 1; page <= totalPages; page++) {
            final int currentPage = page;

            TextExtractionStrategy strategy = new TextExtractionStrategy() {
                boolean alreadyAdded = false;

                @Override public void beginTextBlock() {}
                @Override public void endTextBlock() {}
                @Override public void renderImage(ImageRenderInfo renderInfo) {}

                @Override
                public void renderText(TextRenderInfo renderInfo) {
                    String text = renderInfo.getText();
                    if (!alreadyAdded && text != null && text.contains(searchText)) {
                        if (result.length() > 0) {
                            result.append(",");
                        }
                        result.append(currentPage).append("[").append(x).append(":").append(y).append("]");
                        alreadyAdded = true;
                    }
                }

                @Override public String getResultantText() { return null; }
            };

            PdfTextExtractor.getTextFromPage(reader, page, strategy);
        }

        reader.close();
        return result.toString();
    }

}
