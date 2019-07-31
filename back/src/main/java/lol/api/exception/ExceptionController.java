package lol.api.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lol.api.exception.BadRequestException;
import lol.api.exception.ResourceNotFoundException;
import lol.api.payload.BaseResponse;

@RestControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> fieldValidationExceptionHandler(MethodArgumentNotValidException ex,
            HttpServletRequest req) {
        log.info("--- " + ex.getClass().getSimpleName() + " Controlled ---");
        HttpStatus hs = HttpStatus.CONFLICT;
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, String> errors = new HashMap<>();
        for (FieldError fe : fieldErrors) {
            errors.put(fe.getField(), fe.getDefaultMessage());
        }
        return new ResponseEntity<BaseResponse>(new BaseResponse(errors, hs, req.getRequestURI()), hs);
    }

    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<BaseResponse> badRequestExceptionHandler(Exception ex, HttpServletRequest req) {
        log.info("--- " + ex.getClass().getSimpleName() + " Controlled ---");
        HttpStatus hs = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<BaseResponse>(new BaseResponse(ex.getMessage(), hs, req.getRequestURI()), hs);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<BaseResponse> resourceNotFoundExceptionHandler(Exception ex, HttpServletRequest req) {
        log.info("--- " + ex.getClass().getSimpleName() + " Controlled ---");
        HttpStatus hs = HttpStatus.NOT_FOUND;
        return new ResponseEntity<BaseResponse>(new BaseResponse(ex.getMessage(), hs, req.getRequestURI()), hs);
    }

    @ExceptionHandler({ UnauthorizedException.class })
    public ResponseEntity<BaseResponse> unauthorizedExceptionHandler(Exception ex, HttpServletRequest req) {
        log.info("--- " + ex.getClass().getSimpleName() + " Controlled ---");
        HttpStatus hs = HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<BaseResponse>(new BaseResponse(ex.getMessage(), hs, req.getRequestURI()), hs);
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<BaseResponse> methodNotSupportedExceptionHandler(Exception ex, HttpServletRequest req) {
        log.info("--- " + ex.getClass().getSimpleName() + " Controlled ---");
        HttpStatus hs = HttpStatus.METHOD_NOT_ALLOWED;
        return new ResponseEntity<BaseResponse>(new BaseResponse(ex.getMessage(), hs, req.getRequestURI()), hs);
    }

    @ExceptionHandler({ NoHandlerFoundException.class })
    public ResponseEntity<BaseResponse> noHandlerFoundExceptionHandler(Exception ex, HttpServletRequest req) {
        log.info("--- " + ex.getClass().getSimpleName() + " Controlled ---");
        HttpStatus hs = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<BaseResponse>(new BaseResponse(ex.getMessage(), hs, req.getRequestURI()), hs);
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ResponseEntity<BaseResponse> messageNotReadableExceptionHandler(Exception ex, HttpServletRequest req) {
        log.info("--- " + ex.getClass().getSimpleName() + " Controlled ---");
        HttpStatus hs = HttpStatus.BAD_REQUEST;
        String message = "Required request body is missing";
        return new ResponseEntity<BaseResponse>(new BaseResponse(message, hs, req.getRequestURI()), hs);
    }

    /* @ExceptionHandler({ InternalAuthenticationServiceException.class })
    public ResponseEntity<BaseResponse> internalAuthenticationExceptionHandler(Exception ex, HttpServletRequest req) {
        log.info("--- " + ex.getClass().getSimpleName() + " Controlled ---");
        HttpStatus hs = HttpStatus.NOT_FOUND;
        return new ResponseEntity<BaseResponse>(new BaseResponse(ex.getMessage(), hs, req.getRequestURI()), hs);
    }

    @ExceptionHandler({ BadCredentialsException.class })
    public ResponseEntity<BaseResponse> badCredentialsExceptionHandler(Exception ex, HttpServletRequest req) {
        log.info("--- " + ex.getClass().getSimpleName() + " Controlled ---");
        HttpStatus hs = HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<BaseResponse>(new BaseResponse(ex.getMessage(), hs, req.getRequestURI()), hs);
    } */

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<BaseResponse> uncontroledExceptionHandle(Exception ex, HttpServletRequest req) {
        log.info("--- " + ex.getClass().getSimpleName() + " Controlled ---");
        HttpStatus hs = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Uncontrolled exception, please contact Administrator";
        return new ResponseEntity<BaseResponse>(new BaseResponse(message, hs, req.getRequestURI()), hs);
    }
}
