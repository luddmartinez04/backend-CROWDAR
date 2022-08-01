package com.challenge.crowdar.dto;

import com.challenge.crowdar.utils.ErrorCodes;

public class ErrorDTO {
    private Integer errorCode;
    private String errorDescription;

    public ErrorDTO(Integer code, String desc) {
        this.errorCode = code;
        this.errorDescription = desc;
    }

    public ErrorDTO( ErrorCodes code ) {
        this.errorCode = code.getCode();
        this.errorDescription = code.getDescription();
    }

    public ErrorDTO( ErrorCodes code, String description ) {
        this.errorCode = code.getCode();
        this.errorDescription = description;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }
}
