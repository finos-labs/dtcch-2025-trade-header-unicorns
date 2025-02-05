package com.tradeheader.iso20022.meta.version;

import com.handcoded.xml.NodeIndex;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseISOVersion extends ISOVersion {
    
    /**
     * Pattern used to extract the version from the root namespace of some ISO documents
     */
    private static final Pattern pattern = Pattern.compile(".*([0-9]\\d\\d.[0-9]\\d\\d.[0-9]\\d)");

    /**
     * During compilation time, it registers the supported values of this {@link ISOVersion} subclass
     * in the registry support class {@link ISOVersionSpecificationRegistry}.
     */
    static {

        ISOVersionSpecificationRegistry.registerClassValues(BaseISOVersion.class, new ArrayList<>(Arrays.asList(
                ""
        )));

    }

    /**
     * Constructs a new {@code BaseISOVersion} instance based on the provided {@link String}.
     * <p>
     * This constructor extracts version-specific information from the document to initialize the instance.
     * </p>
     *
     * @param isoVersionStringValue The {@link String} from which to extract the ISO version information.
     */
    protected BaseISOVersion(String isoVersionStringValue) {
        super(isoVersionStringValue);
    }

    /**
     * Constructs a new {@code BaseISOVersion} instance based on the provided {@link NodeIndex}.
     * <p>
     * This constructor extracts version-specific information from the document to initialize the instance.
     * </p>
     *
     * @param nodeIndex The {@link NodeIndex} from which to extract the ISO version information.
     */
    protected BaseISOVersion(NodeIndex nodeIndex) {
        super(nodeIndex);
    }

    /**
     * Constructs a new {@code BaseISOVersion} instance based on the provided {@link Document}.
     * <p>
     * This constructor extracts version-specific information from the document to initialize the instance.
     * </p>
     *
     * @param document The {@link Document} from which to extract the ISO version information.
     */
    protected BaseISOVersion(Document document) {
        super(document);
    }

    /**
     * Constructs a new {@code BaseISOVersion} instance by copying the version information from another {@link ISOVersion} instance.
     *
     * @param isoVersion An existing {@link ISOVersion} instance from which to copy the version information.
     */
    protected BaseISOVersion(ISOVersion isoVersion) {
        super(isoVersion);
    }

    /**
     * Extracts and returns a {@code ISOVersion} instance from the provided {@link Document}.
     * <p>
     * This method should implement the logic to extract ISO version information from a document.
     * Currently, it returns {@code null} and needs to be overridden with the appropriate logic.
     * </p>
     *
     * @param document The {@link Document} from which to extract the ISO version.
     * @return A new {@code ISOVersion} instance representing the version extracted from the document, or {@code null} if the extraction cannot be performed.
     */
    @Override
    public BaseISOVersion getISOVersionFromDocument(Document document) {
        return null;
    }

    /**
     * Auxiliary method that, given a namespace, extrats the ISO version value if possible.
     * It returns an empty valued ISO Version if the patter did not match.
     */
    private BaseISOVersion getVersionFromNamespaceUri(String namespaceUri) {
        Matcher matcher = pattern.matcher(namespaceUri);
        if (matcher.matches()) {
            return new BaseISOVersion(matcher.group(1).replaceAll("\\.", "-"));
        }
        return new BaseISOVersion("");
    }

}


