package com.tradeheader.iso20022.utils;

import com.handcoded.meta.SchemaRelease;
import com.handcoded.meta.Specification;
import com.tradeheader.iso20022.meta.version.ISOVersion;
import com.tradeheader.iso20022.meta.version.ISOVersionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Enumeration;


/**
 * Extractor utils methods for extracting elements from Document
 */
public final class Extractor {
    /**
     * Logging instance used for error reporting.
     */
    private static Logger logger
            = LoggerFactory.getLogger(Extractor.class);

    private Extractor() {
    }

    /**
     * Extract the Toolkit Reference Identifier given the Document and the identifier element name
     *
     * @param document              Document to extract the Toolkit Reference Identifier
     * @param identifierElementName Name of the Toolkit Reference Identifier element in the Document
     * @return String of the Toolkit Reference Identifier
     */
    public static String extractIdentifier(Document document, String identifierElementName) {
        if (document == null || identifierElementName == null || identifierElementName.equals("")) return null;
        Element element = document.getDocumentElement();
        if (element == null) return null;
        NodeList nodeList = (element != null) ? element.getElementsByTagName(identifierElementName) : null;
        if (nodeList == null || nodeList.getLength() != 1) return null;
        Node node = nodeList.item(0);
        if (node == null) return null;
        return (node.getFirstChild() != null
                && node.getFirstChild().getNodeValue() != null) ? node.getFirstChild().getNodeValue() : "";

    }

    /**
     * Extract the Toolkit Reference Identifier given an ISO envelope Document. It will use TlkitRefId as
     * identifier value
     *
     * @param document Document to extract the Toolkit Reference Identifier
     * @return String of the Toolkit Reference Identifier
     */
    public static String extractIdentifier(Document document) {
        return extractIdentifier(document, "TlkitRefId");
    }

    /**
     * Given a Document, extracts its namespace
     *
     * @param document Document to extract the namespace from
     * @return String of the root element of the Document.
     */
    public static String extractRootNamespace(Document document) {
        if (document == null) return null;
        Element documentElement = document.getDocumentElement();
        return (documentElement != null) ? documentElement.getNamespaceURI() : null;
    }

    /**
     * Given a DOM node, extract the SchemaRelease and return it
     *
     * @param node Node that contains the dom context information from which to extract information.
     * @return the SchemaRelease found
     */
    public static SchemaRelease extractSchemaRelease(Node node) {
        if (node == null) return null;

        Document document = node.getOwnerDocument();
        if (document == null) return null;

        String namespace = extractRootNamespace(document);
        ISOVersion isoVersion = ISOVersionFactory.getIsoVersion(document);
        String identifier = extractIdentifier(document);

        if (namespace == null || isoVersion == null) {
            if (logger.isErrorEnabled()) logger.error("SchemaRelease could not be resolved.");
            return null;
        }


        return resolveRelease(namespace, isoVersion, identifier);
    }

    /**
     * Resolves and returns the release for a given version and namespace URI.
     *
     * @param version      the version of the release
     * @param namespaceUri the namespace URI of the release
     * @param identifier   the identifier of the release
     * @return the matched release or null if no match is found
     */
    private static SchemaRelease resolveRelease(String namespaceUri, ISOVersion version, String identifier) {
        Enumeration<Specification> allSpecifications = Specification.specifications();
        SchemaRelease release = null;

        while (allSpecifications.hasMoreElements()) {
            Specification specification = allSpecifications.nextElement();
            String specificationName = specification.getName();

            if (specificationName == null) {
                continue;
            }
            release = extractSchemaRelease(specification, identifier, version, namespaceUri);

            if (release != null) {
                break;
            }
        }

        return release;
    }

    /**
     * Given a specification, an identifier, a version and a namespace, extract the SchemaRelease
     * that belongs to that specification and return it
     *
     * @param specification Specification of the SchemaRelease
     * @param identifier    String Toolkit Reference Identifier of the SchemaRelease
     * @param version       ISOVersion version of the SchemaRelease
     * @param namespace     String namespace of the SchemaRelease
     * @return the SchemaRelease found
     */
    public static SchemaRelease extractSchemaRelease(Specification specification, String identifier, ISOVersion version, String namespace) {
        if (specification == null) return null;

        SchemaRelease schemaRelease = (identifier != null) ? specification.getReleaseForIdentifierVersionAndNamespace(identifier, version.getISOVersionStringValue(), namespace) : specification.getReleaseForVersionAndNamespace(version.getISOVersionStringValue(), namespace);
        if (schemaRelease != null) return schemaRelease;
        else return null;

    }
}
