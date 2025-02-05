package com.tradeheader.iso20022.meta.version;

import com.handcoded.xml.NodeIndex;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A concrete implementation of {@link ISOVersion} for a specific ISO 20022 message version.
 * <p>
 * This class provides specific logic to parse and handle ISO 20022 messages of a particular version,
 * extending the generic functionality provided by the {@link ISOVersion} class. It includes methods
 * to extract version-specific information from an ISO 20022 document and to perform version-specific
 * processing.
 * </p>
 * The toolkit envelope logics supports processing for Toolkit Envelope based messages.
 */
public class ToolkitEnvelopeISOVersion extends ISOVersion {

    /**
     * During compilation time, it registers the supported values of this {@link ISOVersion} subclass
     * in the registry support class {@link ISOVersionSpecificationRegistry}.
     */
    static {
        //todo: analyze how to read this info from the config files
        ISOVersionSpecificationRegistry.registerClassValues(ToolkitEnvelopeISOVersion.class, new ArrayList<>(Arrays.asList(
                "securities_base_sr2025"
        )));
    }

    /**
     * Constructs a new {@code ToolkitEnvelopeISOVersion} instance using the specified ISO version string.
     *
     * @param isoVersionStringValue The string value representing the specific ISO version this class handles.
     */
    protected ToolkitEnvelopeISOVersion(String isoVersionStringValue) {
        super(isoVersionStringValue);
    }

    /**
     * Constructs a new {@code ToolkitEnvelopeISOVersion} instance based on the provided {@link NodeIndex}.
     * <p>
     *
     * @param nodeIndex The {@link NodeIndex} from which to extract the ISO version information.
     */
    protected ToolkitEnvelopeISOVersion(NodeIndex nodeIndex) {
        super(nodeIndex);
    }

    /**
     * Constructs a new {@code ToolkitEnvelopeISOVersion} instance based on the provided {@link Document}.
     * <p>
     *
     * @param document The {@link Document} from which to extract the ISO version information.
     */
    protected ToolkitEnvelopeISOVersion(Document document) {
        super(document);
    }

    /**
     * Constructs a new {@code ToolkitEnvelopeISOVersion} instance based on another {@link ISOVersion} provided.
     * <p>
     *
     * @param isoVersion The {@link ISOVersion} from which to copy the ISO version.
     */
    protected ToolkitEnvelopeISOVersion(ISOVersion isoVersion) {
        super(isoVersion);
    }

    /**
     * Constructs a new {@code ToolkitEnvelopeISOVersion} instance based on a {@link Document} provided.
     * <p>
     *
     * @param document The {@link Document} from which to extract the document information.
     */
    @Override
    public ToolkitEnvelopeISOVersion getISOVersionFromDocument(Document document) {

        String result = "";
        if (document != null) {
            NodeList nodelist = document.getElementsByTagName("TlkitRefId");
            if (nodelist != null && nodelist.getLength() == 1) {
                NamedNodeMap namedNodeMap = nodelist.item(0).getAttributes();
                if (namedNodeMap != null && namedNodeMap.getLength() == 1) {
                    Node extractedVersionNode = namedNodeMap.getNamedItem("version");
                    if (extractedVersionNode != null)
                        return new ToolkitEnvelopeISOVersion(extractedVersionNode.getTextContent());

                }
            }
        }
        return new ToolkitEnvelopeISOVersion(result);
    }


}
