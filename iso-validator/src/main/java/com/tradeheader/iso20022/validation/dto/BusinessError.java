package com.tradeheader.iso20022.validation.dto;

/**
 * Represents a business error encountered during the validation of ISO 20022 messages.
 * It encapsulates details such as the error level, code, rule that was violated,
 * context of the error, a description of the error, and any additional data relevant to the error.
 */
public class BusinessError {

    /**
     * Enumerates the possible levels of business errors.
     */
    public enum BusinessErrorLevel {
        WARNING,
        ERROR,
        FATAL
    }

    private final BusinessErrorLevel level;
    private final String code;
    private final String rule;
    private final String context;
    private final String description;
    private final String additionalData;

    /**
     * Constructs a new BusinessError with the specified details.
     *
     * @param level           The severity level of the error.
     * @param code            A unique code identifying the error.
     * @param rule            The rule that was violated, leading to this error.
     * @param context         The context in which the error occurred.
     * @param description     A human-readable description of the error.
     * @param additionalData  Any additional data relevant to the error.
     */
    private BusinessError(
            BusinessErrorLevel level,
            String code,
            String rule,
            String context,
            String description,
            String additionalData
    ) {
        this.level = level;
        this.code = code;
        this.rule = rule;
        this.context = context;
        this.description = description;
        this.additionalData = additionalData;
    }

    /**
     * Returns the level of this business error.
     *
     * @return The error level.
     */
    public BusinessErrorLevel getLevel() {
        return level;
    }

    /**
     * Returns the unique code of this business error.
     *
     * @return The error code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the rule that was violated.
     *
     * @return The violated rule.
     */
    public String getRule() {
        return rule;
    }

    /**
     * Returns the context in which the error occurred.
     *
     * @return The error context.
     */
    public String getContext() {
        return context;
    }

    /**
     * Returns a description of the error.
     *
     * @return The error description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns any additional data relevant to the error.
     *
     * @return The additional data.
     */
    public String getAdditionalData() {
        return additionalData;
    }

    @Override
    public String toString() {
        return "BusinessError{" +
                "level=" + level +
                ", code='" + code + '\'' +
                ", rule='" + rule + '\'' +
                ", context='" + context + '\'' +
                ", description='" + description + '\'' +
                ", additionalData='" + additionalData + '\'' +
                '}';
    }

    /**
     * The builder class for creating BusinessError instances.
     */
    public static final class Builder {

        private BusinessErrorLevel level;
        private String code;
        private String rule;
        private String context;
        private String description;
        private String additionalData;

        private Builder() {
        }

        /**
         * Creates a new Builder instance.
         *
         * @return A new instance of Builder.
         */
        public static Builder anInstance() {
            return new Builder();
        }

        public Builder withLevel(BusinessErrorLevel level) {
            this.level = level;
            return this;
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withRule(String rule) {
            this.rule = rule;
            return this;
        }

        public Builder withContext(String context) {
            this.context = context;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withAdditionalData(String additionalData) {
            this.additionalData = additionalData;
            return this;
        }

        /**
         * Builds and returns a BusinessError instance based on the builder's settings.
         *
         * @return A new BusinessError instance.
         */
        public BusinessError build() {
            return new BusinessError(level, code, rule, context, description, additionalData);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            BusinessError that = (BusinessError) obj;
            return (that.getCode().equals(this.code));
        }


    }


}
