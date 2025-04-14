package com.tradeheader.iso20022.meta.version;

import com.handcoded.meta.Release;
import com.handcoded.meta.Specification;
import com.handcoded.xml.NodeIndex;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;
import java.util.function.BiFunction;

import static com.tradeheader.iso20022.meta.version.ToolkitEnvelopeISOVersion.createInstance;

/**
 * Factory class for creating instances of {@link ISOVersion} based on various inputs such as {@link Element},
 * {@link NodeIndex}, {@link Document}, {@link Specification}, and {@link Release}.
 * <p>
 * This class provides static factory methods to instantiate specific ISO version objects by determining the
 * appropriate subclass of {@link ISOVersion} to use based on the provided input. The selection process involves
 * identifying the specification or class name associated with the input and using it to instantiate the correct
 * ISO version object.
 * </p>
 */
public class ISOVersionFactory {
    private static final String toolkitEnvelopeISOVersionClassName = "com.tradeheader.iso20022.meta.version.ToolkitEnvelopeISOVersion";
    private static final String baseISOVersionClassName = "com.tradeheader.iso20022.meta.version.BaseISOVersion";

    /**
     * CreForce load the static block of the classes that extend ISOVersion
     */
    static {
        try {
            Class.forName(toolkitEnvelopeISOVersionClassName);
            Class.forName(baseISOVersionClassName);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an {@link ISOVersion} instance based on the provided {@link Element}.
     *
     * @param element The {@link Element} from which to derive the ISO version.
     * @return An {@link ISOVersion} instance or {@code null} if the element is null or does not contain enough information.
     */
    public static ISOVersion getISOVersion(Element element) {
        return (element != null && element.getOwnerDocument() != null) ? selectIsoVersionSpecification.apply(Specification.forDocument(element.getOwnerDocument()), element.getOwnerDocument()) : null;
    }

    /**
     * Creates an {@link ISOVersion} instance based on the provided {@link NodeIndex}.
     *
     * @param nodeIndex The {@link NodeIndex} from which to derive the ISO version.
     * @return An {@link ISOVersion} instance or {@code null} if the node index is null or does not contain a document.
     */
    public static ISOVersion getISOVersion(NodeIndex nodeIndex) {
        Document document = (nodeIndex != null) ? nodeIndex.getDocument() : null;
        return (document != null) ? selectIsoVersionSpecification.apply(Specification.forDocument(document), document) : null;
    }

    /**
     * Creates an {@link ISOVersion} instance based on the provided {@link Document}.
     *
     * @param document The {@link Document} from which to derive the ISO version.
     * @return An {@link ISOVersion} instance or {@code null} if the document is null or does not specify a valid specification.
     */
    public static ISOVersion getIsoVersion(Document document) {
        Specification specification = (document != null) ? Specification.forDocument(document) : null;
        return (specification != null) ? selectIsoVersionSpecification.apply(specification, document) : null;
    }

    /**
     * Creates an {@link ISOVersion} instance based on the provided {@link Specification} and ISO string version.
     *
     * @param specification    The {@link Specification} to use for determining the ISO version class.
     * @param isoStringVersion The ISO version string to be used in the version-specific implementation.
     * @return An {@link ISOVersion} instance corresponding to the specified specification and ISO string version.
     */
    public static ISOVersion getIsoVersion(Specification specification, String isoStringVersion) {
        return selectIsoVersionSpecification.apply(specification, isoStringVersion);
    }

    /**
     * Creates an {@link ISOVersion} instance based on the provided {@link Release} and {@link Document}.
     *
     * @param release  The {@link Release} to use for determining the ISO version class.
     * @param document The {@link Document} to be used in the version-specific implementation.
     * @return An {@link ISOVersion} instance corresponding to the specified release and document.
     */
    public static ISOVersion getISOVersion(Release release, Document document) {
        return selectIsoVersionSpecification.apply(release.getSpecification(), document);
    }

    /**
     * Creates an {@link ISOVersion} instance based on the specified class name and version.
     *
     * @param className The class name of the ISO version implementation to instantiate.
     * @param version   The version string to be passed to the constructor of the ISO version class.
     * @return An {@link ISOVersion} instance of the specified class and version, or {@code null} if instantiation fails.
     */
    public static ISOVersion getISOVersion(String className, String version) {
        return instantiateSpecificISOVersion.apply(className, version);
    }

    /**
     * Functional interface for instantiating specific ISO version classes based on a class name and an object.
     * <p>
     * This function dynamically creates instances of ISO version subclasses based on the provided class name and
     * an object, which could be a {@link String} representing an ISO version or a {@link Document} from which
     * the ISO version needs to be extracted. The specific constructor to be used is determined at runtime based on
     * the type of the provided object.
     * </p>
     */
    static private BiFunction<String, Object, ISOVersion> instantiateSpecificISOVersion = (className, object) -> {
        if (className == null || object == null) return null;
        if (className.equals(toolkitEnvelopeISOVersionClassName)) {
            if (object instanceof String) {
                return createInstance(ToolkitEnvelopeISOVersion::new, (String) object);
            } else if (object instanceof Document) {
                return createInstance(ToolkitEnvelopeISOVersion::new, (Document) object);
            } else {
                return null;
            }
        } else if (className.equals(baseISOVersionClassName)) {
            if (object instanceof String) {
                return createInstance(BaseISOVersion::new, (String) object);
            } else if (object instanceof Document) {
                return createInstance(BaseISOVersion::new, (Document) object);
            } else {
                return null;
            }
        } else {
            return null;
        }
    };

    /**
     * Functional interface for selecting the appropriate ISO version specification based on a {@link Specification}
     * and an object.
     * <p>
     * This function determines the correct subclass of {@link ISOVersion} to instantiate based on the provided
     * {@link Specification} and an object, which could be a {@link String} representing an ISO version or a
     * {@link Document} from which the ISO version needs to be extracted. The selection is made by querying the
     * {@link ISOVersionSpecificationRegistry} for classes that support the given specification and then using
     * {@code instantiateSpecificISOVersion} to create an instance of the selected class.
     * </p>
     */
    static private BiFunction<Specification, Object, ISOVersion> selectIsoVersionSpecification = (spec, object) -> {
        if (spec == null || object == null) return null;

        List<Class<?>> classesWithValue = ISOVersionSpecificationRegistry.findClassesSupportedSpecifications(spec.getName());

        if (classesWithValue == null || classesWithValue.size() != 1) return null;
        String className = classesWithValue.get(0).getName();
        return instantiateSpecificISOVersion.apply(className, object);
    };


}
