package com.tradeheader.iso20022.validation.dto;

/**
 * Represents a syntax error identified during the validation of ISO 20022 messages.
 * This class encapsulates the error level and a descriptive message about the syntax error.
 */
public class SyntaxError {

    /**
     * Enumerates the possible levels of syntax errors.
     */
    public enum SyntaxErrorLevel {
        WARNING,
        ERROR,
        FATAL
    }

    private final SyntaxErrorLevel level;
    private final String message;

    /**
     * Constructs a new SyntaxError with the specified level and message.
     *
     * @param level   The severity level of the syntax error.
     * @param message A descriptive message about the syntax error.
     */
    private SyntaxError(SyntaxErrorLevel level, String message) {
        this.level = level;
        this.message = message;
    }

    /**
     * Returns the level of this syntax error.
     *
     * @return The error level.
     */
    public SyntaxErrorLevel getLevel() {
        return level;
    }

    /**
     * Returns the descriptive message of this syntax error.
     *
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SyntaxError{" +
                "level=" + level +
                ", message='" + message + '\'' +
                '}';
    }

    /**
     * The builder class for creating SyntaxError instances.
     */
    public static final class Builder {

        private SyntaxErrorLevel level;
        private String message;

        private Builder() {
        }

        /**
         * Creates a new Builder instance for building a SyntaxError object.
         *
         * @return A new instance of Builder.
         */
        public static Builder anInstance() {
            return new Builder();
        }

        /**
         * Sets the level of the syntax error for the SyntaxError object being built.
         *
         * @param level The severity level of the syntax error.
         * @return The current Builder instance, for fluent interface chaining.
         */
        public Builder withLevel(SyntaxErrorLevel level) {
            this.level = level;
            return this;
        }

        /**
         * Sets the descriptive message for the SyntaxError object being built.
         *
         * @param message The descriptive message about the syntax error.
         * @return The current Builder instance, for fluent interface chaining.
         */
        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Builds and returns a SyntaxError instance based on the builder's settings.
         *
         * @return A new SyntaxError instance.
         */
        public SyntaxError build() {
            return new SyntaxError(level, message);
        }
    }


}
