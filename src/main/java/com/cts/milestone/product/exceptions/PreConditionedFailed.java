package com.cts.milestone.product.exceptions;

public class PreConditionedFailed extends RuntimeException {

    public PreConditionedFailed(String msg) {
        super(msg);
    }
    public PreConditionedFailed(String msg, Exception e) {
        super(msg,e);
    }
}
