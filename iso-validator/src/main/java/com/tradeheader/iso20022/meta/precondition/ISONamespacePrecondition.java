package com.tradeheader.iso20022.meta.precondition;

import com.handcoded.meta.SchemaRelease;
import com.handcoded.validation.Precondition;
import com.handcoded.xml.NodeIndex;
import org.w3c.dom.Element;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Tradeheader, SL
 *
 * @author mmartos
 * @since 18/09/2019
 */
public class ISONamespacePrecondition extends Precondition {

    private final String namespaceUri;

    /**
     * Constructions a <CODE>ISONamespacePrecondition</CODE> instance that checks
     * the namespace URI of documents.
     *
     * @param    release        The schema release containing the namespace URI.
     */
    public ISONamespacePrecondition(SchemaRelease release) {
        this(release.getNamespaceUri());
    }

    /**
     * Constructions a <CODE>ISONamespacePrecondition</CODE> instance that checks
     * the namespace URI of documents.
     *
     * @param    namespaceUri        The namespace URI.
     */
    public ISONamespacePrecondition(String namespaceUri) {
        this.namespaceUri = namespaceUri;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(NodeIndex nodeIndex, Map<Precondition, Boolean> cache) {
        Element rootElement = nodeIndex.getDocument().getDocumentElement();
        String ns = rootElement.getNamespaceURI();
        return ns != null ? ns.compareTo(this.namespaceUri) == 0 : false;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "targetNamespace=" + this.namespaceUri;
    }

}
