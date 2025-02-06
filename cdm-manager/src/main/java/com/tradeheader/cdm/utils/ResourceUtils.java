package com.tradeheader.cdm.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.io.Resources;
import com.regnosys.rosetta.common.hashing.ReferenceResolverProcessStep;
import com.regnosys.rosetta.common.serialisation.RosettaObjectMapper;
import com.rosetta.model.lib.RosettaModelObject;
import org.isda.cdm.processor.CdmReferenceConfig;


import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ResourceUtils {

    private static final ObjectMapper mapper = createObjectMapper();
    private static final ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
    private static final ReferenceResolverProcessStep resolver = new ReferenceResolverProcessStep(CdmReferenceConfig.get());

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = RosettaObjectMapper.getNewRosettaObjectMapper();

        // Configure polymorphic type handling
//        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
//                .allowIfBaseType("com.tradeheader.")
//                .allowIfBaseType("com.rosetta.")  // Allows all subpackages of `com.rosetta`
//                .allowIfBaseType("cdm.")          // Allows all subpackages of `cdm`
//                .build();

        // ✅ FIX: Use `JsonTypeInfo.As.PROPERTY` instead of `As.WRAPPER_ARRAY`
        //mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        // Optional: Ignore unknown properties to prevent failures
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);


        return mapper;
    }

    public static String getJson(String resourceName) throws IOException {
            URL url = Resources.getResource(resourceName);
            return Resources.toString(url, StandardCharsets.UTF_8);
    }

    public static <T> T getObject(Class<T> clazz, String resourceName) throws IOException {
            String json = getJson(resourceName);
            return mapper.readValue(json, clazz);
    }

    public static <T extends RosettaModelObject> T getObjectAndResolveReferences(Class<T> clazz, String resourceName) throws IOException {
        T object = getObject(clazz, resourceName);
        return resolveReferences(object);
    }

    public static <T extends RosettaModelObject> T resolveReferences(T object) {
        RosettaModelObject builder = object.toBuilder();
        resolver.runProcessStep(builder.getType(), builder);
        return (T) builder.build();
    }

    public static String serialiseAsJson(Object o) throws JsonProcessingException {
            return writer.writeValueAsString(o);
    }

    public static <T> T toObject(Class<T> clazz, Map<String, Object> node) {
        return mapper.convertValue(node, clazz);
    }

    /**
     * Converts an object (T) into a Map<String, Object>.
     *
     * @param object The object to convert.
     * @param <T>    The type of the input object.
     * @return A Map<String, Object> representing the object's properties.
     */
    public static <T> Map<String, Object> asMap(T object) {
        if (object == null) {
            return null; // Return null if input is null
        }
        return mapper.convertValue(object, Map.class);
    }

//    public static JsonNode toJsonNode(Object object) {
//        return mapper.valueToTree(object);
//    }
//
//    public static <T> T fromJsonNode(Class<T> clazz, JsonNode node) throws JsonProcessingException {
//        return mapper.treeToValue(node, clazz);
//    }
//
//    public static <T extends RosettaModelObject> T fromJsonNodeAndResolveReferences(Class<T> clazz, JsonNode node) throws IOException {
//        T object = fromJsonNode(clazz, node);
//        return resolveReferences(object);
//    }
}
