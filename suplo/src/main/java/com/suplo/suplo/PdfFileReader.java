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
    
}