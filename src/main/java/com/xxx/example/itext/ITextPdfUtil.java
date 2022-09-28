package com.xxx.example.itext;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文档： https://kb.itextpdf.com/home/it7kb/ebooks
 */
public class ITextPdfUtil {
    // 系统默认字体
    private static final String FONT = "/Users/gaopan/Library/Fonts/Meslo LG M Regular for Powerline.ttf";

    //html转pdf
    public static void html2pdf(){
        String html = "";

        //从html文件读取内容
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("/tmp/itext/ht.mhtml"))){
            for (Object o :  reader.lines().toArray()) {
                stringBuilder.append(o);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        html = stringBuilder.toString();

        try (PdfWriter writer = new PdfWriter("/tmp/itext/html2pdf.pdf");
             PdfDocument pdf = new PdfDocument(writer);
        ){
            //转换器属性设置
            ConverterProperties props = new ConverterProperties();
            //字体
            props.setFontProvider(new FontProvider());
            props.getFontProvider().addFont(ITextPdfUtil.FONT);
            //为img图片配置基础路径
            props.setBaseUri("/tmp/itext/");

            //HtmlConverter.convertToDocument
            Document document = HtmlConverter.convertToDocument(html, pdf, props);

            //设置文档属性
            pdf.getDocumentInfo().setAuthor("huanzi-qch");
            pdf.getDocumentInfo().setTitle("IText测试html2pdf");
            pdf.getDocumentInfo().setSubject("XXX公司");
            pdf.getDocumentInfo().setMoreInfo("1","111");
            pdf.getDocumentInfo().setCreator("huanzi");
            pdf.getDocumentInfo().setKeywords("IText");

            //注册事件监听
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new MyEventHandler());

            //设置字体
            document.setFont(ITextPdfUtil.getPdfFont());

            //页边距
            document.setMargins(0, 0, 0, 0);

            document.close();
            System.out.println("操作完成！");
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("操作异常...");
        }
    }

    //生成简单PDF
    public static void test(){
        //语法糖
        try (PdfWriter writer = new PdfWriter("/tmp/itext/test.pdf");
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf, PageSize.A4.rotate());
        ){

            //设置文档属性
            pdf.getDocumentInfo().setAuthor("huanzi-qch");
            pdf.getDocumentInfo().setTitle("IText测试PDF");
            pdf.getDocumentInfo().setSubject("XXX公司");
            pdf.getDocumentInfo().setMoreInfo("1","111");
            pdf.getDocumentInfo().setCreator("huanzi");
            pdf.getDocumentInfo().setKeywords("IText");

            //注册事件监听
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new MyEventHandler());

            //设置字体
            // document.setFont(ITextPdfUtil.getPdfFont());

            //页边距
            document.setMargins(20, 20, 20, 20);

            //简单文字
            document.add(new Paragraph("简单文字"));
            document.add(new Paragraph("Hello Word!").add(new Tab()).add(new Text("你好！").addStyle(new Style().setFontSize(24))));

            //简单图片
            document.add(new Paragraph("简单图片"));
            document.add(new Image(ImageDataFactory.create("/tmp/itext/logo.png")));

            //简单表格
            document.add(new Paragraph("简单表格"));
            Table table = new Table(new float[]{3, 3, 4});
            PdfFont font = ITextPdfUtil.getPdfFont();
            //标题、内容
            process(table, "姓名;年龄;电话号码", font, true);
            for (int i = 0; i < 5; i++) {
                process(table, "张三"+i+";"+(18+i)+";1500000000"+i, font, false);
            }
            document.add(table);

            //超链接
            document.add(new Paragraph("超链接"));
            PdfLinkAnnotation annotation = new PdfLinkAnnotation(new Rectangle(0, 0));
            annotation.setAction(PdfAction.createURI("https://itextpdf.com/"));
            Paragraph p = new Paragraph("更多精彩内容，猛戳：").add(new Link("这里", annotation));
            document.add(p);

            //换一页
            //document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            document.close();
            System.out.println("操作完成！");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("操作异常...");
        }
    }

    //设置表格内容
    public static void process(Table table, String line, PdfFont font, boolean isHeader) {
        String[] split = line.split(";");
        for (String s : split) {
            Cell cell = new Cell().add(new Paragraph(s).setFont(font));
            if (isHeader) {
                table.addHeaderCell(cell);
            } else {
                table.addCell(cell);
            }
        }
    }

    //获取统一字体
    public static PdfFont getPdfFont(){
        PdfFont pdfFont = null;
        try {
            pdfFont = PdfFontFactory.createFont(ITextPdfUtil.FONT, PdfEncodings.IDENTITY_H,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfFont;
    }

    //测试
    public static void main(String[] args) {
         test();

        // html2pdf();
    }

    /**
     * 自定义事件监听
     *
     * 背景颜色
     * 页脚页眉
     * 文字水印
     *
     * 也可以分成多个EventHandler
     */
    protected static class MyEventHandler implements IEventHandler {

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            int pageNumber = pdfDoc.getPageNumber(page);
            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            //背景颜色
            Color backgroundColor = new DeviceRgb(245, 245, 245);;
            pdfCanvas.saveState()
                    .setFillColor(backgroundColor)
                    .rectangle(pageSize.getLeft(), pageSize.getBottom(),pageSize.getWidth(), pageSize.getHeight())
                    .fill().restoreState();

            //页脚页眉
            PdfFont pdfFont = ITextPdfUtil.getPdfFont();
            String header = "我是页眉";
            String footer = "第 "+pageNumber+" 页";
            pdfCanvas.beginText()
                    .setFontAndSize(pdfFont, 9)
                    .moveText((pageSize.getWidth() / 2) - (pdfFont.getWidth(header) / 200), pageSize.getTop() - 20)
                    .showText(header)
                    .moveText((pdfFont.getWidth(header) / 200) - (pdfFont.getWidth(footer) / 200), -pageSize.getTop() + 30)
                    .showText(footer)
                    .endText();

            //文字水印
            Canvas canvas = new Canvas(pdfCanvas, pdfDoc, page.getPageSize());
            canvas.setFontColor(new DeviceRgb(200, 200, 200));
            canvas.setProperty(20, 20);
            canvas.setFont(pdfFont);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    canvas.showTextAligned(
                            new Paragraph("我是文字水印").setOpacity(0.8f),
                            (150 + i * 300),
                            (160 + j * 150),
                            pdfDoc.getPageNumber(page),
                            TextAlignment.CENTER,
                            VerticalAlignment.MIDDLE, 45);
                }
            }

            pdfCanvas.release();
        }
    }
}
