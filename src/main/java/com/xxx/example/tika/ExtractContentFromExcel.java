package com.xxx.example.tika;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;

/**
 * 使用java从excel表提取内容
 */
public class ExtractContentFromExcel {
    public static void main(String args[]) throws Exception {

        // detecting the file type
        BodyContentHandler handler = new BodyContentHandler();

        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File("/tmp/tika/t.xlsx"));

        ParseContext pcontext = new ParseContext();

        // OOXml parser
        OOXMLParser msofficeparser = new OOXMLParser();

        msofficeparser.parse(inputstream, handler, metadata, pcontext);
        System.out.println("Contents of the document:" + handler.toString());
        System.out.println("Metadata of the document:");
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }
}
