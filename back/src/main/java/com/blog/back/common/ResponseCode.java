package com.blog.back.common;

import java.security.Key;

public interface ResponseCode {
    //HTTP STATUS 200
    String SUCCESS = "SU";

    //HTTP STATUS 400
    String VALIDATION_FAILED ="VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_TEL_NUMBER = "DT";
    String NOT_EXISTED_USER = "NU";
    String NO_EXISTED_BOARD= "NB";

    //HTTP STATUS 401
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL= "DE";

    //HTTP STATUS 403
    String NO_PERMISSION = "NP";

    //HTTP STATUS500
    String DATABASE_ERROR = "DBE";
}
