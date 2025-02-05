package com.tradeheader.iso20022.testUtils;

import com.tradeheader.iso20022.application.Validator;
import com.tradeheader.iso20022.application.logging.LogHandler;

/**
 * Class to manage a set of Sample objects
 */
public class SamplePile {

    /**
     * Array of Sample objects belonging to this Pile object
     */
    private final Sample[] samples;

    /**
     * LogHandler tied to this Pile object
     */
    private final LogHandler logHandler;

    private final Validator validator;

    /**
     * Constructor
     * @param samples array of Sample objects for this object
     */
    public SamplePile(Sample[] samples, String catalogPath, String ruleSetName) throws Exception {
        this.samples = samples;
        this.logHandler = new LogHandler();
        this.validator = new Validator(catalogPath, ruleSetName);
    }

    /**
     * Trigger validation of all Sample objects belonging this Pile
     * @return LogHandler with logged results
     */
    public LogHandler process() throws Exception {
        this.logHandler.clear();
        for (Sample sample: this.samples) {
            sample.validate(this.validator);
            this.logHandler.add(sample.getLog());
        }
        return this.logHandler;
    }

    /**
     * Trigger CheckResults for every Sample object in this pile
     * @return true, if all checkResults are positive; false, otherwise
     */
    public boolean checkResults() {
        boolean result = true;
        for (Sample sample: this.samples) {
            boolean sampleResult = sample.checkResults();
            if (!sampleResult) result = false;
        }
        return result;
    }
}
