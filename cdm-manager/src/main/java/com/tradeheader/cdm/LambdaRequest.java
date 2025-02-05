package com.tradeheader.cdm;

import java.util.Map;

public class LambdaRequest {
    private LambdaAction action;
    private Map<String, Object> payload;

    public LambdaRequest() {}
    public LambdaRequest(LambdaAction action, Map<String, Object> payload) {
        this.action = action;
        this.payload = payload;
    }

    public LambdaAction getAction() {
        return action;
    }

    public void setAction(LambdaAction action) {
        this.action = action;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }
}