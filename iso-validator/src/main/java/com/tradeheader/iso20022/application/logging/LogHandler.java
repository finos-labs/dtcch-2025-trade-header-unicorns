package com.tradeheader.iso20022.application.logging;

import java.util.ArrayList;

/**
 * Class to manage Log objects for a Sample Pile
 */
public class LogHandler {

    /**
     * Set of Log objects belonging to this class
     */
    private ArrayList<Log> logPile = null;

    /**
     * Constructor
     */
    public LogHandler() {
        this.logPile = new ArrayList<>();
    }

    /**
     * Adds a log to the pile
     * @param log
     */
    public void add(Log log) {
        this.logPile.add(log);
    }

    /**
     * Resets logPile
     */
    public void clear() {
        this.logPile.clear();
    }

    /**
     * Prints all logs
     */
    public void print() {
        for (Log log: this.logPile) {
            log.printLog();
        }
    }

}
