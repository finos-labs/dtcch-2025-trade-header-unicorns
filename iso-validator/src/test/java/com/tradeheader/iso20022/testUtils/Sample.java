package com.tradeheader.iso20022.testUtils;

import com.tradeheader.iso20022.validation.dto.BusinessError;
import com.tradeheader.iso20022.application.logging.Log;
import com.tradeheader.iso20022.application.Validator;
import com.tradeheader.iso20022.validation.dto.ValidationInformation;

import java.util.Arrays;
import java.util.Objects;

/**
 * Class to manage test samples
 */
public class Sample {

    /**
     * String path to the location of the test sample
     */
    private final String path;

    /**
     * Expected error codes when this sample is executed
     */
    private final String[] errorCodes;

    /**
     * Log where the validation results will be saved
     */
    private final Log log;

    /**
     * Test case name extracted from path element
     */
    private final String testCaseName;

    /**
     * Constructor
     * @param path location of the test sample
     * @param errorCodes Expected error codes
     */
    public Sample(String path, String errorCodes) {
        this.path = path;
        this.testCaseName = path.split("/")[path.split("/").length - 1];
        if (!Objects.equals(errorCodes, ""))
            this.errorCodes = errorCodes.split(",\\s*");
        else
            this.errorCodes = new String[]{};
        this.log = new Log();
    }

    /**
     * Trigger validation through validator
     */
    public ValidationInformation validate(Validator validator) throws Exception {
        return validator.validate(this.path, this.log);
    }

    /**
     * Contrast log results vs expected error codes
     * @return true, if expected and actual errors match; false, otherwise
     */
    public boolean checkResults() {
        StringBuilder resultTable = new StringBuilder();
        boolean overallResult = true;

        int testCaseNameWidth = Math.max(this.testCaseName.length(), "TEST CASE".length()) + 6;
        int errorTypeWidth = Math.max("Business Error".length(), "ERROR TYPE".length()) + 6;
        int actualWidth = "ERROR".length() + 10;
        int expectedWidth = Math.max("None".length(), "EXPECTED ERROR".length()) + 6;
        int passFailWidth = "STATUS".length() + 10;

        String headerFormat = "| %-" + testCaseNameWidth + "s | %-" + errorTypeWidth + "s | %-" + actualWidth + "s | %-" + expectedWidth + "s | %-" + passFailWidth + "s |%n";
        resultTable.append(String.format("%" + (testCaseNameWidth + errorTypeWidth + actualWidth + expectedWidth + passFailWidth + 15) + "s%n", "").replace(' ', '-')).append(String.format(headerFormat, "TEST CASE", "ERROR TYPE", "ERROR", "EXPECTED ERROR", "STATUS"));

        String rowFormat = "| %-" + testCaseNameWidth + "s | %-" + errorTypeWidth + "s | %-" + actualWidth + "s | %-" + expectedWidth + "s | %-" + passFailWidth + "s |%n";

        if (this.log.hasSyntaxErrors()) {
            String result = "Fail";
            boolean syntaxErrorExpected = Arrays.asList(this.errorCodes).contains("syntaxError");
            if (syntaxErrorExpected) {
                result = "Pass";
            } else {
                overallResult = false;
            }
            resultTable.append(String.format(rowFormat, this.testCaseName, "Syntax Error", "Syntax Error", syntaxErrorExpected ? "Yes" : "No", result));
        }

        if (this.log.hasBusinessErrors()) {
            if (this.errorCodes.length == 0) {
                overallResult = false;
                for (BusinessError bErr : this.log.getBusinessErrors()) {
                    String codeBErr = bErr.getCode();
                    resultTable.append(String.format(rowFormat, this.testCaseName, "Business Errors", codeBErr, Arrays.toString(this.errorCodes), "Fail"));
                }
            } else {
                for (BusinessError bErr : this.log.getBusinessErrors()) {
                    String codeBErr = bErr.getCode();
                    boolean match = Arrays.asList(this.errorCodes).contains(codeBErr);
                    if (!match) {
                        overallResult = false;
                        resultTable.append(String.format(rowFormat, this.testCaseName, "Business Error", codeBErr, Arrays.toString(this.errorCodes), "Fail"));
                    } else {
                        resultTable.append(String.format(rowFormat, this.testCaseName, "Business Error", codeBErr, Arrays.toString(this.errorCodes), "Pass"));
                    }
                }
            }
        }

        if (!this.log.hasSyntaxErrors() && !this.log.hasBusinessErrors() && this.errorCodes.length > 0) {
            overallResult = false;
            resultTable.append(String.format(rowFormat, this.testCaseName, "None", "None", Arrays.toString(this.errorCodes), "Fail"));
        } else if (!this.log.hasSyntaxErrors() && !this.log.hasBusinessErrors()) {
            resultTable.append(String.format(rowFormat, this.testCaseName, "None", "None", "None", "Pass"));
        }

        resultTable.append(String.format("%" + (testCaseNameWidth + errorTypeWidth + actualWidth + expectedWidth + passFailWidth + 15) + "s", "").replace(' ', '-'));
        System.out.println(resultTable.toString());
        return overallResult;
    }

    /**
     * Get log from this sample
     * @return log
     */
    public Log getLog() { return this.log; }

}