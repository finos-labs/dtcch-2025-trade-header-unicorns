package com.tradeheader.iso20022.application.logging;

import com.handcoded.validation.ValidationErrorHandler;
import com.handcoded.xml.XPath;
import com.tradeheader.iso20022.validation.dto.BusinessError;
import com.tradeheader.iso20022.validation.dto.SyntaxError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage Validation and Syntax errors for Validator class
 */
public class Log {

    private static Logger log = LoggerFactory.getLogger(Log.class);

    private final ValidationErrorHandlerImpl validationErrorHandler;
    private final SyntaxErrorHandlerImpl syntaxErrorHandler;

    /**
     * Constructor
     */
    public Log() {
        validationErrorHandler = new ValidationErrorHandlerImpl();
        syntaxErrorHandler = new SyntaxErrorHandlerImpl();
    }

    /**
     * Checks if the validationErrorHandler has errors
     * @return true, if validationErrorHandler has errors; false, otherwise
     */
    public boolean hasBusinessErrors() {
        return this.validationErrorHandler.errors.size() > 0;
    }

    /**
     * Returns a list of BusinessError from validationErrorHandler
     * @return list of BusinessError from validationErrorHandler
     */
    public List<BusinessError> getBusinessErrors() {
        return this.validationErrorHandler.errors;
    }

    /**
     * Checks if the syntaxErrorHandler has errors
     * @return true, if syntaxErrorHandler has errors; false, otherwise
     */
    public boolean hasSyntaxErrors() {
        return this.syntaxErrorHandler.errors.size() > 0;
    }

    /**
     * Returns a list of SyntaxError from validationErrorHandler
     * @return list of SyntaxError from validationErrorHandler
     */
    public List<SyntaxError> getSyntaxErrors() {
        return this.syntaxErrorHandler.errors;
    }
    /**
     * Checks if the syntaxErrorHandler or the validationErrorHandler has errors
     * @return true, if they have errors; false, otherwise
     */
    public boolean hasErrors() {
        return hasSyntaxErrors() || hasBusinessErrors();
    }

    /**
     * Prints errors from the syntaxErrorHandler and the validationErrorHandler
     */
    public void printLog() {
        List<BusinessError> validationErrors = this.validationErrorHandler.errors;
        List<SyntaxError> syntaxErrors = this.syntaxErrorHandler.errors;
        if (validationErrors.size() > 0)  System.out.println(validationErrors);
        if (syntaxErrors.size() > 0)  System.out.println(syntaxErrors);
    }

    /**
     * Returns syntaxErrorHandler
     * @return syntaxErrorHandler
     */
    public ErrorHandler getSyntaxErrorHandler() {
        return this.syntaxErrorHandler;
    }

    /**
     * Returns validationErrorHandler
     * @return validationErrorHandler
     */
    public ValidationErrorHandler getValidationHandler() {
        return this.validationErrorHandler;
    }

    /**
     * Implements ErrorHandler by SyntaxErrorHandlerImpl
     */
    private class SyntaxErrorHandlerImpl implements ErrorHandler {

        private final List<SyntaxError> errors = new ArrayList();

        @Override
        public void warning(SAXParseException exception) {
            SyntaxError se = SyntaxError.Builder.anInstance()
                    .withLevel(SyntaxError.SyntaxErrorLevel.WARNING)
                    .withMessage(exception.getMessage())
                    .build();
            errors.add(se);
            if (log.isWarnEnabled()) log.warn(se.toString());
        }

        @Override
        public void error(SAXParseException exception) {
            SyntaxError se = SyntaxError.Builder.anInstance()
                    .withLevel(SyntaxError.SyntaxErrorLevel.ERROR)
                    .withMessage(exception.getMessage())
                    .build();
            errors.add(se);
            if (log.isErrorEnabled()) log.error(se.toString());
        }

        @Override
        public void fatalError(SAXParseException exception) {
            SyntaxError se = SyntaxError.Builder.anInstance()
                    .withLevel(SyntaxError.SyntaxErrorLevel.FATAL)
                    .withMessage(exception.getMessage())
                    .build();
            errors.add(se);
            if (log.isErrorEnabled()) log.error(se.toString());
        }


    }

    /**
     * Implements ValidationErrorHandler by ValidationErrorHandlerImpl
     */
    public static class ValidationErrorHandlerImpl implements ValidationErrorHandler {

        private final List<BusinessError> errors = new ArrayList();

        @Override
        public void error(String code, Node context, String description, String ruleName, String additionalData) {
            BusinessError be = BusinessError.Builder.anInstance()
                    .withLevel(BusinessError.BusinessErrorLevel.ERROR)
                    .withCode(code)
                    .withContext(XPath.forNode(context))
                    .withDescription(description)
                    .withRule(ruleName)
                    .withAdditionalData(additionalData)
                    .build();
            errors.add(be);
            if (log.isErrorEnabled())log.error(be.toString());
        }
    }
}
