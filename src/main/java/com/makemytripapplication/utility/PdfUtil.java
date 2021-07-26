package com.makemytripapplication.utility;

import com.makemytripapplication.constants.IConstants;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

public class PdfUtil {

    public String readPdf(String pdfPath) {
        String content = null;
        try {
            URL pdfUrl = new URL(pdfPath);
            InputStream in = pdfUrl.openStream();
            BufferedInputStream bf = new BufferedInputStream(in);
            PDDocument doc = PDDocument.load(bf);
            int numberOfPages = getPageCount(doc);
            System.out.println("The total number of pages "+numberOfPages);
            content = new PDFTextStripper().getText(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    private int getPageCount(PDDocument doc) {
        //get the total number of pages in the pdf document
        return doc.getNumberOfPages();
    }

    public static void main(String[] args) {
        PdfUtil pdfUtil = new PdfUtil();
        String content = pdfUtil.readPdf(IConstants.PDF_URL);
        String[] content1 = content.split(":");
        System.out.println("content 1"+Arrays.toString(content1));

        for (int i=1; i<content1.length; i=i+2) {
            System.out.println("content 2  "+content1[i]);
        }
    }
}
