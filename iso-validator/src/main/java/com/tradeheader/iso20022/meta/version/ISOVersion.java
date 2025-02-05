package com.tradeheader.iso20022.meta.version;

import com.handcoded.xml.NodeIndex;
import org.w3c.dom.Document;

import java.util.Objects;
import java.util.function.Function;


/**
 * Represents a generic version of an ISO message and provides a foundation for version-specific implementations.
 * <p>
 * This abstract class encapsulates the common functionality for different ISO versions, such as managing the version
 * string value. It allows for the creation of ISO version instances based on different inputs, such as a document or
 * a node index, and provides a method for extracting the ISO version from a document.
 * </p>
 */
public abstract class ISOVersion {

    /**
     * The string representation of the ISO version.
     */
    private final String isoVersionStringValue;

    /**
     * Constructs an ISOVersion instance with the specified version string.
     *
     * @param isoVersionStringValue The string value representing the ISO version.
     */
    protected ISOVersion(String isoVersionStringValue) {
        this.isoVersionStringValue = isoVersionStringValue;
    }

    /**
     * Constructs an ISOVersion instance based on a NodeIndex.
     *
     * @param nodeIndex The NodeIndex object from which to derive the ISO version.
     */
    protected ISOVersion(NodeIndex nodeIndex) {
        this(nodeIndex.getDocument());
    }

    /**
     * Generic method to create an instance of a class that extends ISOVersion using a constructor reference.
     *
     * @param <T>         The type of the input argument for the constructor function.
     * @param <R>         The type of the ISOVersion instance to be returned.
     * @param constructor A function reference to the constructor of the implementing class.
     * @param arg         The argument to be passed to the constructor function.
     * @return An instance of type R that extends ISOVersion.
     */
    public static <T, R> R createInstance(Function<T, R> constructor, T arg) {
        return constructor.apply(arg);
    }

    /**
     * Constructs an ISOVersion instance based on a Document.
     *
     * @param document The Document object from which to derive the ISO version.
     */
    protected ISOVersion(Document document) {
        this.isoVersionStringValue = (document != null) ? getISOVersionFromDocument(document).getISOVersionString() : "";
    }

    /**
     * Returns the string representation of the ISO version for this instance.
     *
     * @return The ISO version string value.
     */
    private String getISOVersionString() {
        return this.isoVersionStringValue;
    }

    /**
     * Copy constructor to create a new ISOVersion instance based on another ISOVersion instance.
     *
     * @param isoVersion An existing ISOVersion instance from which to copy the version string.
     */
    protected ISOVersion(ISOVersion isoVersion) {
        this.isoVersionStringValue = isoVersion.getISOVersionString();
    }

    /**
     * Abstract method to be implemented by subclasses for extracting an ISOVersion instance from a Document.
     *
     * @param document The Document object from which to extract the ISO version.
     * @return An ISOVersion instance derived from the document.
     */
    public abstract ISOVersion getISOVersionFromDocument(Document document);

    /**
     * Returns the string representation of the ISO version for this instance.
     *
     * @return The ISO version string value.
     */
    public String getISOVersionStringValue() {
        return this.isoVersionStringValue;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ISOVersion that = (ISOVersion) object;
        return Objects.equals(isoVersionStringValue, that.isoVersionStringValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isoVersionStringValue);
    }
}
