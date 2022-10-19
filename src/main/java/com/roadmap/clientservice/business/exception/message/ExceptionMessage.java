package com.roadmap.clientservice.business.exception.message;

import org.springframework.stereotype.Component;

@Component
public class ExceptionMessage {

    private ExceptionMessage() {
    }

    public static final String FIELD_NOT_BLANK = "Shall not be blank";
    public static final String FIELD_NOT_NULL = "Shall not be null";
    public static final String FIELD_PAST_OR_PRESENT = "Shall be in the past or present";
    public static final String FIELD_MAX_LENGTH_45 = "Shall be maximum 45 characters long";
    public static final String FIELD_MAX_DIGITS_25_2 = "Shall have not more, than 25 digits in integral part, " +
            "and not more, than 2 digits in decimal part";
    public static final String CLIENT_WITH_ID_NOT_FOUND = "Client not found with id: ";
}
