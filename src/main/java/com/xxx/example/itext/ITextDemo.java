package com.xxx.example.itext;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ITextDemo {
    /**
     * 创建一个空白的PDF：
     * 第 1 步：创建一个 PdfWriter 对象
     * 该PdfWriter类表示PDF文档的作家。此类属于包com.itextpdf.kernel.pdf。此类的构造函数接受一个字符串，表示要在其中创建 PDF 的文件的路径。
     * 通过向其构造函数传递一个字符串值（表示您需要创建 PDF 的路径）来实例化 PdfWriter 类，如下所示。
     *
     * 第 2 步：创建一个 PdfDocument 对象
     * 该PdfDocument类为表示在iText的PDF文档类。此类属于包com.itextpdf.kernel.pdf。要实例化此类（在写入模式下），您需要将PdfWriter类的对象传递给其构造函数。
     * 通过将上面创建的 PdfWriter 对象传递给其构造函数来实例化 PdfDocument 类，如下所示。
     *
     * 第 3 步：添加一个空页面
     * PdfDocument类的addNewPage()方法用于在 PDF 文档中创建一个空白页面。
     * 为上一步创建的 PDF 文档添加一个空白页面，如下所示。
     *
     * 第 4 步：创建一个 Document 对象
     * 包com.itextpdf.layout的Document类是创建自给自足的 PDF 时的根元素。此类的构造函数之一接受类 PdfDocument 的对象。
     * 通过传递在前面的步骤中创建的类PdfDocument的对象来实例化Document类，如下所示。
     *
     * 步骤 5：关闭文档
     * 使用Document类的close()方法关闭文档，如下所示。
     * @throws FileNotFoundException
     */
    @Test
    public void createBlankPDF() throws FileNotFoundException {
        // 1、Creating a PdfWriter
        String dest = "/tmp/itext/t3.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // 2、Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);

        // 3、Adding an empty page
        pdfDoc.addNewPage();

        // 4、Creating a Document
        Document document = new Document(pdfDoc);

        // 5、Closing the document
        document.close();
        System.out.println("PDF Created");
    }

    /**
     * 创建一个 AreaBreak
     *      创建区域中断对象
     *      所述AreaBreak类属于包com.itextpdf.layout.element。在实例化这个类时，当前的上下文区域将被终止并创建一个具有相同大小的新区域（如果我们使用默认构造函数）。
     */
    @Test
    public void addingAreaBreak() throws FileNotFoundException {
        // Creating a PdfWriter
        String dest = "/tmp/itext/t4.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument
        PdfDocument pdf = new PdfDocument(writer);

        // Creating a Document by passing PdfDocument object to its constructor
        Document document = new Document(pdf);

        // Creating an Area Break
        AreaBreak aB = new AreaBreak();

        // Adding area break to the PDF
        document.add(aB);

        // Closing the document
        document.close();
        System.out.println("Pdf created");
    }

    /**
     * 创建段落
     *      创建一个段落对象的段落类表示的文本和图形信息的自包含块。它属于com.itextpdf.layout.element包。
     *      通过将文本内容作为字符串传递给其构造函数来实例化Paragraph类
     */
    @Test
    public void addingParagraph() throws FileNotFoundException {
        // Creating a PdfWriter
        String dest = "/tmp/itext/t5.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument
        PdfDocument pdf = new PdfDocument(writer);

        // Creating a Document
        Document document = new Document(pdf);
        String para1 = "Tutorials Point originated from the idea that there exists " +
                "a class of readers who respond better to online content and prefer to learn " +
                "new skills at their own pace from the comforts of their drawing rooms.";

        String para2 = "The journey commenced with a single tutorial on HTML in 2006" +
                "and elated by the response it generated, we worked our way to adding fresh " +
                " tutorials to our repository which now proudly flaunts a wealth of tutorials " +
                "and allied articles on topics ranging from programming languages to web designing " +
                "to academics and much more.";

        // Creating Paragraphs
        Paragraph paragraph1 = new Paragraph(para1);
        Paragraph paragraph2 = new Paragraph(para2);

        // Adding paragraphs to document
        document.add(paragraph1);
        document.add(paragraph2);

        // Closing the document
        document.close();
        System.out.println("Paragraph added");
    }

    /**
     * 创建列表
     *      创建一个 List 对象
     *      该目录类表示一系列垂直列出的对象。它属于com.itextpdf.layout.element包。
     */
    @Test
    public void addingList() throws FileNotFoundException {
        // Creating a PdfWriter
        String dest = "/tmp/itext/t6.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument
        PdfDocument pdf = new PdfDocument(writer);

        // Creating a Document
        Document document = new Document(pdf);

        // Creating a Paragraph
        Paragraph paragraph = new Paragraph("Tutorials Point provides the following tutorials");

        // Creating a list
        List list = new List();

        // Add elements to the list
        list.add("Java");
        list.add("JavaFX");
        list.add("Apache Tika");
        list.add("OpenCV");
        list.add("WebGL");
        list.add("Coffee Script");
        list.add("Java RMI");
        list.add("Apache Pig");

        // Adding paragraph to the document
        document.add(paragraph);

        // Adding list to the document
        document.add(list);

        // Closing the document
        document.close();
        System.out.println("List added");
    }

    /**
     * 将表格添加到 Pdf
     *      创建一个 Table 对象
     *      该表类表示填充有以行和列排列的二维网格。它属于com.itextpdf.layout.element包。
     */
    @Test
    public void addingTable() throws FileNotFoundException {
        // Creating a PdfDocument object
        String dest = "/tmp/itext/t7.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument object
        PdfDocument pdf = new PdfDocument(writer);

        // Creating a Document object
        Document doc = new Document(pdf);

        // Creating a table
        float [] pointColumnWidths = {150F, 150F, 150F};
        Table table = new Table(pointColumnWidths);

        // Adding cells to the table
        table.addCell("Name");
        table.addCell("Id");
        table.addCell("Designation");

        table.addCell("Raju");
        table.addCell("1001");
        table.addCell("Programmer");

        // Adding Table to document
        doc.add(table);

        // Closing the document
        doc.close();
        System.out.println("Table created successfully..");
    }


}
