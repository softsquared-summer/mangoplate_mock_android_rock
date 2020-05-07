package com.softSquared.mangoplate.src.home.discount.models;

import java.util.List;

public class EatDealsResponse {
    private List<EatDealsResult> result = null;
    private Boolean isSuccess;
    private int code;
    private String message;

    public List<EatDealsResult> getResult() {
        return result;
    }

    public void setResult(List<EatDealsResult> result) {
        this.result = result;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
