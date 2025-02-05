package com.tradeheader.iso20022.schemes;

import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.apache.xerces.jaxp.validation.XMLSchemaFactory;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class FilesSchemaFactory extends SchemaFactory {

    private final SchemaFactory schemaFactory;
    private final FilesResourceResolver resourceResolver;

    public FilesSchemaFactory() {
        this(new XMLSchemaFactory());
    }

    public FilesSchemaFactory(SchemaFactory schemaFactory) {
        this.schemaFactory = schemaFactory;
        this.resourceResolver = new FilesResourceResolver();
        this.schemaFactory.setResourceResolver(resourceResolver);
    }

    @Override
    public boolean isSchemaLanguageSupported(String schemaLanguage) {
        return schemaFactory.isSchemaLanguageSupported(schemaLanguage);
    }

    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {
        schemaFactory.setErrorHandler(errorHandler);
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return schemaFactory.getErrorHandler();
    }

    @Override
    public void setResourceResolver(LSResourceResolver resourceResolver) {
        throw new RuntimeException("Call to setResourceResolver is forbidden");
    }

    @Override
    public LSResourceResolver getResourceResolver() {
        return resourceResolver;
    }

    @Override
    public Schema newSchema(Source[] schemas) throws SAXException {
        return schemaFactory.newSchema(schemas);
    }

    @Override
    public Schema newSchema() throws SAXException {
        return schemaFactory.newSchema();
    }
}
