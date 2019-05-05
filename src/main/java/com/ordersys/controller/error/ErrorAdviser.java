package com.ordersys.controller.error;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.RexModel;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorAdviser {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<RexModel> businessException(BusinessException e) {
        return ResponseEntity.ok(new RexModel().withMessage(e.getMessage()).withError(e.getError()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RexModel> authenticationException(AuthenticationException e) {
        return ResponseEntity.ok(new RexModel().withMessage(e.getMessage()).withError("authentication_exception"));
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RexModel> authorizationException(AuthorizationException e) {
        return ResponseEntity.ok(new RexModel().withMessage(e.getMessage()).withError("authorization_exception"));
    }
}
