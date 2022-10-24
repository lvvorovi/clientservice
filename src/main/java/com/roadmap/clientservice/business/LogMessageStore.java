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
    public static final String CLIENT_FOUND_LOG = "Client found with id: ";
    public static final String CLIENT_SAVED_LOG = "Client saved with id: ";
    public static final String CLIENT_UPDATED_LOG = "Client updated with id: ";
    public static final String CLIENT_DELETED_BY_ID_LOG = "Client deleted with id: ";

    //ModelMapper logs
    public static final String CLIENT_ENTITY_TO_RESPONSE_LOG = "ClientEntity mapped to ClientResponse with id: ";
    public static final String CLIENT_CREATE_REQUEST_TO_ENTITY_LOG = "ClientCreateRequest mapped to ClientEntity. No Id assigned yet.";
    public static final String CLIENT_UPDATE_REQUEST_TO_ENTITY_LOG = "ClientUpdateRequest mapped to ClientEntity with id: ";

    //ExceptionHandler logs
    public static final String VALIDATION_FAILED = "Validation failed. ";

    private LogMessageStore() {
    }
}
