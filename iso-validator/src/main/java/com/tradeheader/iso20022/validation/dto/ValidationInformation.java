package com.tradeheader.iso20022.validation.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationInformation {


    private final String fileName;
    private final ReleaseInformation release;
    private final String catalog;
    private final String rules;
    private final List<BusinessError> businessErrors;
    private final List<SyntaxError> syntaxErrors;

    private ValidationInformation (
            String fileName,
            ReleaseInformation release,
            String catalog,
            String rules,
            List<BusinessError> businessErrorList,
            List<SyntaxError> syntaxErrorList
    ) {
        this.fileName = fileName;
        this.release = release;
        this.catalog = catalog;
        this.rules = rules;
        this.businessErrors = emptyOrUnmodifiableListOf(businessErrorList);
        this.syntaxErrors = emptyOrUnmodifiableListOf(syntaxErrorList);
    }

    private <T> List<T> emptyOrUnmodifiableListOf(List<T> list) {
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    public String getFileName() {
        return fileName;
    }

    public List<BusinessError> getBusinessErrors() {
        return businessErrors;
    }

    public List<SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

    public ReleaseInformation getReleaseInformation() {
        return release;
    }

    public String getCatalogName() {
        return catalog;
    }

    public String getRulesName() {
        return rules;
    }

    public static final class Builder {
        private String fileName;
        private ReleaseInformation release;
        private String catalog;
        private String rules;
        private List<BusinessError> businessErrors;
        private List<SyntaxError> syntaxErrors;

        private Builder() {
        }

        public static Builder anInstance() {
            return new Builder();
        }

        public ValidationInformation.Builder withRelease(ReleaseInformation release) {
            this.release = release;
            return this;
        }

        public ValidationInformation.Builder withCatalog(String catalog) {
            this.catalog = catalog;
            return this;
        }

        public ValidationInformation.Builder withRules(String rules) {
            this.rules = rules;
            return this;
        }

        public ValidationInformation.Builder withBusinessErrors(List<BusinessError> businessErrors) {
            this.businessErrors = businessErrors;
            return this;
        }

        public ValidationInformation.Builder withSyntaxErrors(List<SyntaxError> syntaxErrors) {
            this.syntaxErrors = syntaxErrors;
            return this;
        }

        public ValidationInformation.Builder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public ValidationInformation build() {
            return new ValidationInformation(fileName, release, catalog, rules, businessErrors, syntaxErrors);
        }
    }
}