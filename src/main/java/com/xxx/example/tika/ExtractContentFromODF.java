package com.xxx.example.tika;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.odf.OpenDocumentParser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;

public class ExtractContentFromODF {
    public static void main(String[] args) throws Exception {

        //detecting the file type
        BodyContentHandler handler = new BodyContentHandler();

        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File("demODF.odt"));
        ParseContext pcontext = new ParseContext();

        //Open Document Parser
        OpenDocumentParser openofficeparser = new OpenDocumentParser ();

        openofficeparser.parse(inputstream, handler, metadata, pcontext);
        System.out.println("Contents of the document:" + handler.toString());
        System.out.println("Metadata of the document:");
        String[] metadataNames = metadata.names();

        for(String name : metadataNames) {
            System.out.println(name + " :  " + metadata.get(name));
        }
    }
}
