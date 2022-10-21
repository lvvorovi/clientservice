package com.roadmap.clientservice.business;

public class LogMessageStore {

    //POJO field validation
    public static final String FIELD_NOT_BLANK = "Shall not be blank";
    public static final String FIELD_NOT_NULL = "Shall not be null";
    public static final String FIELD_PAST_OR_PRESENT = "Shall be in the past or present";
    public static final String FIELD_MAX_LENGTH_45 = "Shall be maximum 45 characters long";
    public static final String FIELD_MAX_DIGITS_25_2 = "Shall have not more, than 25 digits in integral part, " +
            "and not more, than 2 digits in decimal part";

    //Client exception message
    public static final String CLIENT_ID_NOT_FOUND = "Client not found with id: ";
    public static final String CLIENT_PERSONAL_NUMBER_EXISTS = "Client already exists with personal number: ";

    //Client logs
    public static final String CLIENT_SAVE_REQUEST_LOG = "Save Client request with body: ";
    public static final String CLIENT_FIND_BY_ID_REQUEST_LOG = "Find Client by Id request with id: ";
    public static final String CLIENT_UPDATE_REQUEST_LOG = "Update Client request with body: ";
    public static final String CLIENT_DELETE_BY_ID_REQUEST_LOG = "Delete Client request with id: ";
    public static final String CLIENT_FOUND_LOG = "Client found: ";
    public static final String CLIENT_SAVED_LOG = "Client saved: ";
    public static final String CLIENT_UPDATED_LOG = "Client was updated to: ";
    public static final String CLIENT_DELETED_BY_ID_LOG = "Client deleted with id: ";

    //ModelMapper logs
    public static final String MAPPER_LOG_MESSAGE = " mapped to: ";

    //ExceptionHandler logs
    public static final String VALIDATION_FAILED = "Validation failed. ";

    private LogMessageStore() {
    }
}
