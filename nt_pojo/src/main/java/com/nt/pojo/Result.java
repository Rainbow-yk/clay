package com.nt.pojo;

import java.io.Serializable;

/**
 * @author 99362
 */
public class Result implements Serializable {
    private String message;
    private boolean result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Result() {
    }

    public Result(String message, boolean result) {
        this.message = message;
        this.result = result;
    }
}
