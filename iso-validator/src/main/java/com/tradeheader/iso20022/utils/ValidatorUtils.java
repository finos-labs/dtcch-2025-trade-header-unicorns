package com.tradeheader.iso20022.utils;

import com.handcoded.meta.SchemaRelease;
import com.handcoded.validation.RuleSet;
import com.handcoded.xml.SchemaSet;
import com.handcoded.xml.resolver.Catalog;
import com.handcoded.xml.resolver.CatalogManager;
import org.xml.sax.InputSource;

import javax.xml.validation.Schema;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ValidatorUtils {


    /**
     * Creates a File instance for the given file path without creating the actual file on the filesystem.
     * This method performs validations to ensure the provided path is not null, not empty, is valid,
     * and does not point to an existing directory.
     *
     * @param filePath the path for the file to be instantiated
     * @return a File instance for the specified path
     * @throws IllegalArgumentException if the filePath is null, empty, not valid, or points to an existing directory
     */
    public static File loadInput(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null.");
        }

        if (filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be empty.");
        }

        try {
            Paths.get(filePath);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("The provided file path is not valid.");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("The provided file path does not point to an existing file.");
        }


        if (file.isDirectory()) {
            throw new IllegalArgumentException("The provided path points to a directory, not a file.");
        }

        return file;
    }

    /**
     * Processes the input file. If it's a null or non-valid input file it throws an Exception.
     *
     * @param file the file to be instantiated
     * @return a File instance for the specified path
     * @throws IllegalArgumentException if the filePath is null, empty, not valid, or points to an existing directory
     */
    public static File loadInput(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null.");
        }

        if (file.isDirectory()) {
            throw new IllegalArgumentException("The provided path points to a directory, not a file.");
        }

        return file;
    }

    /**
     * Creates a file from the input path if possible. Throws an exception if there are errors.
     *
     * @param path of the file to be instantiated
     * @return a File instance for the specified path
     * @throws IllegalArgumentException if the filePath is null, empty, not valid, or points to an existing directory
     */
    public static File loadInput(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("File cannot be null.");
        }

        File file = path.toFile();

        return loadInput(file);
    }

    /**
     * Checks if the input InputSource is null
     *
     * @param input of the file to be instantiated
     * @return an InputSource instance
     * @throws IllegalArgumentException if the filePath is null, empty, not valid, or points to an existing directory
     */
    public static InputSource loadInput(InputSource input) {
        if (input == null) {
            throw new IllegalArgumentException("File cannot be null.");
        }

        return input;
    }

    public static String convertInputSourceToString(InputSource inputSource) throws Exception {
        if (inputSource.getCharacterStream() != null) {
            return readFromReader(inputSource.getCharacterStream());
        } else if (inputSource.getByteStream() != null) {
            return readFromInputStream(inputSource.getByteStream());
        } else {
            throw new IllegalArgumentException("InputSource does not contain a valid stream or reader.");
        }
    }

    private static String readFromReader(Reader reader) throws Exception {
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        }
    }

    private static String readFromInputStream(InputStream inputStream) throws Exception {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        }
    }

    /**
     * Method to load the RuleSet from its name
     *
     * @param ruleName Name of the ruleSet in a String
     * @return RuleSet loaded
     */
    public static RuleSet loadRuleSet(String ruleName) throws Exception {
        RuleSet validationRules;
        validationRules = RuleSet.forName(ruleName);
        if (validationRules == null) {
            throw new Exception("An error occurred while loading the rulesSet. ");
        }
        return validationRules;
    }

    /**
     * Method to load the catalog
     *
     * @return Catalog loaded
     */
    public static Catalog loadCatalog(String catalogPath) throws Exception {
        Catalog catalog = null;
        try {
            catalog = CatalogManager.find(catalogPath);
        } catch (Exception e) {
            throw new Exception("An error occurred while loading the catalog. " + e.getMessage());
        }
        return catalog;
    }

    /**
     * Compiles a schema from a Catalog and a SchemaRelease
     *
     * @param catalog Catalog to compile the Schema
     * @param release SchemaRelease to compile the Schema
     * @return Schema compiled
     */
    public static Schema should_compile_schema(Catalog catalog, SchemaRelease release) throws Exception {

        Schema schema = null;
        String schemaNullMessage;

        if (release != null) {
            SchemaSet schemaSet = new SchemaSet();
            schemaSet.add(release);
            schema = schemaSet.getSchema(catalog);

            schemaNullMessage = String.format("Schema for namespace %s could not be loaded. Is it included in %s?", release.getNamespaceUri(), catalog.getUrl());
        } else {
            schemaNullMessage = "SchemaRelease cannot be null";
        }

        if (schema == null)
            throw new Exception(schemaNullMessage);

        return schema;
    }
}
