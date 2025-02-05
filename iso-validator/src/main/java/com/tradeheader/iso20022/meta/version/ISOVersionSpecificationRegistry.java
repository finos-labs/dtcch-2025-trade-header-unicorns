package com.tradeheader.iso20022.meta.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages the mapping between ISO version classes and their supported release names.
 * <p>
 * This class is responsible for registering and providing access to the relationships between
 * various ISO 20022 version classes and the specific release names they support. The mappings
 * are loaded at compile time and can be queried during runtime.
 * </p>
 */
public class ISOVersionSpecificationRegistry {
    /**
     * Logger for reporting errors.
     */
    private static Logger logger = LoggerFactory.getLogger(ISOVersionSpecificationRegistry.class);

    /**
     * Maps ISO version classes to their supported release names.
     * <p>
     * This map is populated at compile time with the relationships between version classes
     * and their supported ISO release names.
     * </p>
     */
    private static Map<Class<?>, List<String>> isoVersionClassesAndSupportedSpecifications = new HashMap<>();

    /**
     * Registers the association between an ISO version class and its supported releases.
     *
     * @param clazz  The ISO version class to register.
     * @param values The list of release names supported by the {@code clazz}.
     */
    protected static void registerClassValues(Class<?> clazz, List<String> values) {
        isoVersionClassesAndSupportedSpecifications.put(clazz, values);
    }

    /**
     * Retrieves the list of supported release names for a given ISO version class.
     *
     * @param clazz The ISO version class to query.
     * @return The list of release names supported by the {@code clazz}.
     */
    protected static List<String> getValuesForClass(Class<?> clazz) {
        return isoVersionClassesAndSupportedSpecifications.get(clazz);
    }

    /**
     * Finds and returns the list of ISO version classes that support a given specification.
     * <p>
     * This method searches through the registered mappings to find classes that are capable
     * of processing versions for the specified ISO release name.
     * </p>
     *
     * @param value The specification to find supporting classes for.
     * @return A list of classes that support the specified ISO version specification.
     */
    public static List<Class<?>> findClassesSupportedSpecifications(String value) {
        List<Class<?>> classesContainingValue = new ArrayList<>();
        for (Map.Entry<Class<?>, List<String>> entry : isoVersionClassesAndSupportedSpecifications.entrySet()) {
            if (entry.getValue().contains(value)) {
                classesContainingValue.add(entry.getKey());
            }
        }
        if (classesContainingValue.isEmpty() && logger.isErrorEnabled())
            logger.error("ISOVersion is missing support for specification: " + value);
        return classesContainingValue;
    }
}
