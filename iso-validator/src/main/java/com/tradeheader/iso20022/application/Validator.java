package com.tradeheader.iso20022.application;

import com.handcoded.fpml.FpMLUtility;
import com.handcoded.meta.SchemaRelease;
import com.handcoded.meta.Specification;
import com.handcoded.validation.RuleSet;
import com.handcoded.xml.XmlUtility;
import com.handcoded.xml.resolver.Catalog;
import com.tradeheader.iso20022.application.logging.Log;
import com.tradeheader.iso20022.meta.version.ISOVersion;
import com.tradeheader.iso20022.meta.version.ISOVersionFactory;
import com.tradeheader.iso20022.schemes.FilesSchemaFactory;
import com.tradeheader.iso20022.utils.Extractor;
import com.tradeheader.iso20022.utils.ValidatorUtils;
import com.tradeheader.iso20022.validation.dto.ReleaseInformation;
import com.tradeheader.iso20022.validation.dto.ValidationInformation;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static com.tradeheader.iso20022.utils.ValidatorUtils.*;

/**
 * Application class to Validate a test sample
 */
public class Validator {


    /**
     * Log object to store Validation and Syntax errors
     */
    private Log log;
    /**
     * String name of the Toolkit Reference Identifier element
     */
    private final String identifierElement = "TlkitRefId";
    /**
     * String for the extracted identifier from the inputDocument
     */
    private String identifier;
    /**
     * Specification for the Validation
     */
    private Specification specification;
    /**
     * String for the extracted version from the inputDocument
     */
    private ISOVersion version;
    /**
     * String for the extracted namespace from the inputDocument
     */
    private String namespace = "";
    /**
     * RuleSet to validate against
     */
    private final RuleSet ruleSet;
    /**
     * Catalog element
     */
    private final Catalog catalog;
    /**
     * Schema map to store different Schemas
     */
    private Map<SchemaRelease, Schema> schemaMap = new HashMap<>();

    /**
     * Constructor
     *
     * @param catalogPath path to the catalog
     * @param ruleSetName name of the ruleSet
     */
    public Validator(String catalogPath, String ruleSetName) throws Exception {
        // global init
        this.catalog = loadCatalog(catalogPath);
        this.ruleSet = loadRuleSet(ruleSetName);

        schemasFromClasspathWorkaround();
    }

    /**
     * Constructor
     *
     * @param catalogPath path to the catalog
     * @param ruleSet ruleSet
     */
    public Validator(String catalogPath, RuleSet ruleSet) throws Exception {
        // global init
        this.catalog = loadCatalog(catalogPath);
        this.ruleSet = ruleSet;

        schemasFromClasspathWorkaround();
    }

    private void schemasFromClasspathWorkaround() {
        System.setProperty(SchemaFactory.class.getName() + ":http://www.w3.org/2001/XMLSchema", FilesSchemaFactory.class.getName());
    }

    /**
     * Results in log class instance
     * @param input String of the file path
     * @param log Log to save errors
     */
    public ValidationInformation validate(String input, Log log) throws Exception {
        if (log != null)this.log = log;
        else {
            throw new IllegalArgumentException("Logger cannot be null.");
        }
        File inputFile = ValidatorUtils.loadInput(input);
        return validateInput(inputFile);
    }

    public ValidationInformation validate(File input, Log log) throws Exception {
        if (log != null)this.log = log;
        else {
            throw new IllegalArgumentException("Logger cannot be null.");
        }
        File inputFile = ValidatorUtils.loadInput(input);
        return validateInput(inputFile);
    }

    public ValidationInformation validate(Path input, Log log) throws Exception {
        if (log != null)this.log = log;
        else {
            throw new IllegalArgumentException("Logger cannot be null.");
        }
        File inputFile = ValidatorUtils.loadInput(input);
        return validateInput(inputFile);
    }

    public ValidationInformation validate(InputSource input, Log log) throws Exception {
        if (log != null)this.log = log;
        else {
            throw new IllegalArgumentException("Logger cannot be null.");
        }
        InputSource inputSource = ValidatorUtils.loadInput(input);
        return validateInput(inputSource);
    }

    private ValidationInformation validateInput(File inputFile) throws Exception {
        Document inputDocument = XmlUtility.nonValidatingParse(inputFile);
        SchemaRelease schemaRelease = specificInit(inputDocument);

        Document document = XmlUtility.validatingParse(XmlUtility.DTD_OR_SCHEMA, inputFile, this.schemaMap.get(schemaRelease), this.catalog, this.log.getSyntaxErrorHandler());

        if (document == null) {
            // syntax validation failed
            ValidationInformation validationInformation = ValidationInformation.Builder.anInstance()
                    .withRelease(ReleaseInformation.Builder.anInstance().withIdentifier(this.identifier).withNamespace(this.namespace).withVersion(this.version).withSpecification(this.specification).build())
                    .withCatalog(this.catalog.getUrl())
                    .withRules(this.ruleSet.getName())
                    .withSyntaxErrors(this.log.getSyntaxErrors())
                    .withFileName(inputFile.getName())
                    .build();

            return validationInformation;
        }

        FpMLUtility.validate(document, ruleSet, this.log.getValidationHandler());
        ValidationInformation validationInformation = ValidationInformation.Builder.anInstance()
                .withRelease(ReleaseInformation.Builder.anInstance().withIdentifier(this.identifier).withNamespace(this.namespace).withVersion(this.version).withSpecification(this.specification).build())
                .withCatalog(this.catalog.getUrl())
                .withRules(this.ruleSet.getName())
                .withBusinessErrors(this.log.getBusinessErrors())
                .withSyntaxErrors(this.log.getSyntaxErrors())
                .withFileName(inputFile.getName())
                .build();
        return validationInformation;
    }

    private ValidationInformation validateInput(InputSource inputSource) throws Exception {
        String inputString = ValidatorUtils.convertInputSourceToString(inputSource);

        Document inputDocument = XmlUtility.nonValidatingParse(inputString);
        SchemaRelease schemaRelease = specificInit(inputDocument);
        Document document = XmlUtility.validatingParse(XmlUtility.DTD_OR_SCHEMA, inputString, this.schemaMap.get(schemaRelease), this.catalog, this.log.getSyntaxErrorHandler());

        if (document == null) {
            // syntax validation failed
            ValidationInformation validationInformation = ValidationInformation.Builder.anInstance()
                    .withRelease(ReleaseInformation.Builder.anInstance().withIdentifier(this.identifier).withNamespace(this.namespace).withVersion(this.version).withSpecification(this.specification).build())
                    .withCatalog(this.catalog.getUrl())
                    .withRules(this.ruleSet.getName())
                    .withSyntaxErrors(this.log.getSyntaxErrors())
                    .build();

            return validationInformation;
        }

        FpMLUtility.validate(document, ruleSet, this.log.getValidationHandler());
        ValidationInformation validationInformation = ValidationInformation.Builder.anInstance()
                .withRelease(ReleaseInformation.Builder.anInstance().withIdentifier(this.identifier).withNamespace(this.namespace).withVersion(this.version).withSpecification(this.specification).build())
                .withCatalog(this.catalog.getUrl())
                .withRules(this.ruleSet.getName())
                .withBusinessErrors(this.log.getBusinessErrors())
                .withSyntaxErrors(this.log.getSyntaxErrors())
                .build();
        return validationInformation;
    }

    /**
     * Method to initialize the specific validation
     */
    private SchemaRelease specificInit(Document document) throws Exception {
        //extract identifier
        this.identifier = (document != null) ? Extractor.extractIdentifier(document, this.identifierElement) : null;

        //load specification
        this.specification = (document != null) ? Specification.forDocument(document) : null;

        //get doc version
        this.version = (document != null) ? ISOVersionFactory.getIsoVersion(document) : null;

        //get doc namespace
        this.namespace = (document != null) ? Extractor.extractRootNamespace(document) : null;

        // get schema and store it if not already present in schemaMap
        final SchemaRelease release = Extractor.extractSchemaRelease(this.specification, this.identifier, this.version, this.namespace);
        if (!this.schemaMap.containsKey(release)) this.schemaMap.put(release, (release != null) ? should_compile_schema(catalog, release) : null);

        return release;
    }
}
