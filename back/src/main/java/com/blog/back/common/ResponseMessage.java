package com.blog.back.common;

public interface ResponseMessage {
    //HTTP STATUS 200
    String SUCCESS = "Success";

    //HTTP STATUS 400
    String VALIDATION_FAILED ="Validation failed";
    String DUPLICATE_EMAIL = "Duplicate email";
    String DUPLICATE_NICKNAME = "Duplication nickname";
    String DUPLICATE_TEL_NUMBER = "Duplication tel  number";
    String NOT_EXISTED_USER = "This user dose not exist";
    String NO_EXISTED_BOARD= "This board dose not exist";

    //HTTP STATUS 401
    String SIGN_IN_FAIL = "Login information mismatch";
    String AUTHORIZATION_FAIL= "Authorization failed";

    //HTTP STATUS 403
    String NO_PERMISSION = "Do not have permission";

    //HTTP STATUS500
    String DATABASE_ERROR = "Database error";
}

