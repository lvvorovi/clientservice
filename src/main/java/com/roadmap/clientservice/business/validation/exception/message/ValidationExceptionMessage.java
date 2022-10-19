package com.roadmap.clientservice.business.validation.exception.message;

public class ValidationExceptionMessage {

    public static final String FIELD_NOT_BLANK = "Shall not be blank";
    public static final String FIELD_NOT_NULL = "Shall not be null";
    public static final String FIELD_PAST_OR_PRESENT = "Shall be in the past or present";
    public static final String FIELD_MAX_LENGTH_45 = "Shall be maximum 45 characters long";
    public static final String FIELD_MAX_DIGITS_25_2 = "Shall have not more, than 25 digits in integral part, " +
            "and not more, than 2 digits in decimal part";
    public static final String VALIDATION_FAILED = "Validation failed. ";

    public static final String CLIENT_ID_NOT_FOUND = "Client not found with id: ";
    public static final String CLIENT_FOUND = "Client found: ";
    public static final String CLIENT_SAVED = "Client saved: ";
    public static final String CLIENT_UPDATED = "Client was updated to: ";
    public static final String CLIENT_DELETED_BY_ID = "Client deleted with id: ";

    public static final String CLIENT_PERSONAL_NUMBER_EXISTS = "Client already exists with personal number: ";

    private ValidationExceptionMessage() {
    }
}
