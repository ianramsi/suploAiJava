package com.suplo.suplo;

import org.springframework.ai.reader.ExtractedTextFormater;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorestore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import Jakarta.annotation.PostConstruct;

@Component

public class PdfFileReader {
    private final VectorStore vectorStore;

    @Value("classpath:TIOBE.pdf")
    private Resource pdfResource;

    public PdfFileReader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void init() {

        var config = PdfDocumentReaderConfig.builder().withPageExtractFormater (
            new ExtractedTextFormater.Builder().build()).build();

            var pdfReader = new PagePdfDocumentReader (pdfResource, config);
            var textSplitter = new TokenTextSplitter();
            vectorStore.accept(textSplitter.apply(pdfReader.get()));
    
    }
}