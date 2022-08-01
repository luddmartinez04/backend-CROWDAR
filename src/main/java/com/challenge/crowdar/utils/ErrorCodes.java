package com.challenge.crowdar.utils;

public enum ErrorCodes {

    NO_ERROR(0, "E000"),
    ENTITY_CREATED       (200, "E200"),
    ENTITY_UPDATED       (201, "E201"),
    ENTITY_DELETED       (202, "E202"),
    ENTITY_EXISTS        (203, "E203"),


    // Códigos para errores:
    INVALID_PARAMETERS   (204, "E204"),
    EMPTY_PARAMETERS     (205, "E205"),
    ENTITY_NOT_FOUND     (206, "E206"),
    GET_ENTITY_ERROR     (208, "E208"),
    CREATE_ENTITY_ERROR  (209, "E209"),
    UPDATE_ENTITY_ERROR  (210, "E210"),
    DELETE_ENTITY_ERROR  (211, "E211"),
    UNEXPECTED_ERROR     (216, "E216");

    private final Integer code;
    private final String description;

    private ErrorCodes(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.code + ": " + this.description;
    }
}