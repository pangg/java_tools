package com.xxx.example.tika;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 使用java从XML文档中提取内容
 */
public class ExtractContentFromXMLDoc {
    public static void main(String[] args) throws IOException, SAXException, TikaException {

        // detecting the file type
        BodyContentHandler handler = new BodyContentHandler();

        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File("/tmp/tika/t.xml"));
        ParseContext pcontext = new ParseContext();

        // Html parser
        HtmlParser htmlparser = new HtmlParser();

        htmlparser.parse(inputstream, handler, metadata, pcontext);
        System.out.println("Contents of the document:" + handler.toString());
        System.out.println("Metadata of the document:");
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }
}
