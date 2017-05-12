package com.basakpie.security;

import javax.naming.AuthenticationException;

/**
 * Created by basakpie on 2017. 5. 11..
 */
public class RoleTypeNotFundException extends AuthenticationException {

    public RoleTypeNotFundException(String message) {
        super(message);
    }
}
