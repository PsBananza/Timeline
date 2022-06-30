package com.dmitry.NewsClient.exeption;

public interface ValidationConstants {

    String UNKNOWN = "Unknown";
    String UNAUTHORISED = "Unauthorised";
    String TOKEN_NOT_PROVIDED = "JWT token not provided";
    String MUST_NOT_BE_NULL = "Must not be null";
    String ID_MUST_BE_POSITIVE = "ID must be positive value";
    String HTTP_MESSAGE_NOT_READABLE_EXCEPTION = "HTTP request not valid";
    String CODE_NOT_NULL = "Required String parameter 'code' is not present";
    String EXCEPTION_HANDLER_NOT_PROVIDED = "Exception handler not provided";
    String REQUEST_IS_NOT_MULTIPART = "Current request is not a multipart request";
    String MAX_UPLOAD_SIZE_EXCEEDED = "Maximum upload size exceeded";
    String USERNAME_SIZE_NOT_VALID = "User size not valid";
    String TODO_TEXT_NOT_NULL = "Text not null";
    String TODO_STATUS_NOT_NULL = "Status not null";
    String TASK_NOT_FOUND = "Task not null";
    String TASK_PATCH_UPDATED_NOT_CORRECT_COUNT = "Task patch updated not correct count";
    String TASKS_PAGE_GREATER_OR_EQUAL_1 = "Task page greater or equal 1";
    String TASKS_PER_PAGE_GREATER_OR_EQUAL_1 = "Task per page greater or equal 1";
    String TASKS_PER_PAGE_LESS_OR_EQUAL_100 = "Task per page less or equal 100";
    String TODO_TEXT_SIZE_NOT_VALID = "Text size not valid";

    String USER_NAME_SIZE_NOT_VALID = "User name size not valid";
    String USER_ROLE_SIZE_NOT_VALID = "Role size not valid";
    String USER_EMAIL_SIZE_NOT_VALID = "Email size not valid";
    String USER_NOT_FOUND = "Could not find user";
    String USER_EMAIL_NOT_NULL = "User email must not be null";
    String USER_ROLE_NOT_NULL = "User role must not be null";
    String USER_EMAIL_NOT_VALID = "User email must be a well-formed email address";
    String USER_AVATAR_NOT_NULL = "User avatar must not be null";
    String USER_PASSWORD_NOT_NULL = "User password must not be null";
    String PASSWORD_NOT_NULL = "Password must not be null";
    String USER_NAME_HAS_TO_BE_PRESENT = "User name must not be null";
    String USER_PASSWORD_NOT_VALID = "Password not valid";
    String USER_ALREADY_EXISTS = "User already exists";
    String USER_WITH_THIS_EMAIL_ALREADY_EXIST = "User with this email alrady exists";

    String NEWS_DESCRIPTION_NOT_NULL = "News description must not be null";
    String NEWS_TITLE_SIZE_NOT_VALID = "News title size not valid";
    String NEWS_DESCRIPTION_SIZE_NOT_VALID = "News description size not valid";
    String NEWS_TITLE_NOT_NULL = "News title has to be present";
    String NEWS_NOT_FOUND = "News not found";
    String NEWS_DESCRIPTION_HAS_TO_BE_PRESENT = "News description has to be present";
    String NEWS_IMAGE_HAS_TO_BE_PRESENT = "News image has to be present";

    String TAGS_NOT_VALID = "Tags not valid";

    String PAGE_SIZE_NOT_VALID = "News page must be greater or equal 1";
    String PARAM_PAGE_NOT_NULL = "Required Integer parameter 'page' is not present";
    String PARAM_PER_PAGE_NOT_NULL = "Required Integer parameter 'perPage' is not present";
    String REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT = "Required Integer parameter 'page' is not present";
    String REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT = "Required Integer parameter 'perPage' is not present";
    String PER_PAGE_MIN_NOT_VALID = "Parameter 'perPage' must be greater or equal 1";
    String PER_PAGE_MAX_NOT_VALID = "Parameter 'perPage' must be less or equal 100";

    byte USER_EMAIL_MIN_SIZE = 3;
    byte USER_EMAIL_MAX_SIZE = 100;

    byte PASSWORD_MIN_SIZE = 6;
    byte PASSWORD_MAX_SIZE = 25;

    byte USER_NAME_MIN_SIZE = 3;
    byte USER_NAME_MAX_SIZE = 25;

    byte USER_ROLE_MIN_SIZE = 3;
    byte USER_ROLE_MAX_SIZE = 25;

    byte NEWS_DESCRIPTION_SIZE_MIN = 3;
    int NEWS_DESCRIPTION_SIZE_MAX = 160;

    byte NEWS_TITLE_SIZE_MIN = 3;
    int NEWS_TITLE_SIZE_MAX = 160;

    byte PER_PAGE_MIN = 1;
    byte PER_PAGE_MAX = 100;

    byte TAG_MIN_SIZE = 3;
    byte TAG_MAX_SIZE = 15;

}
