package com.tradeheader.iso20022.meta.precondition;

import com.handcoded.meta.SchemaRelease;
import com.handcoded.validation.Precondition;
import com.handcoded.xml.NodeIndex;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Map;

public class ISOToolkitEnvelopeIdentifierPrecondition extends Precondition {

    private final String identifier;

    /**
     * Constructions a <CODE>ISONamespacePrecondition</CODE> instance that checks
     * the namespace URI of documents.
     *
     * @param    release        The schema release containing the namespace URI.
     */
    public ISOToolkitEnvelopeIdentifierPrecondition(SchemaRelease release) {
        this(release.getIdentifier());
    }

    /**
     * Constructions a <CODE>ISONamespacePrecondition</CODE> instance that checks
     * the namespace URI of documents.
     *
     * @param    identifier        The release identifiier.
     */
    public ISOToolkitEnvelopeIdentifierPrecondition(String identifier) {
        this.identifier = identifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(NodeIndex nodeIndex, Map<Precondition, Boolean> map) {
        Element rootElement = nodeIndex.getDocument().getDocumentElement();
        NodeList identifierElementList = rootElement.getElementsByTagName("TlkitRefId");
        if (identifierElementList == null || identifierElementList.getLength() != 1) return false;
        if (identifierElementList.item(0).getTextContent().equals(this.identifier)) return true;
        return false;
    }
}
