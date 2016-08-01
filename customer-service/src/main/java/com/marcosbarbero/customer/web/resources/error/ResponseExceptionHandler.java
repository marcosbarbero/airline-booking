package com.marcosbarbero.customer.web.resources.error;

import com.marcosbarbero.customer.dto.error.ErrorResourceDTO;
import com.marcosbarbero.customer.dto.error.FieldErrorResourceDTO;
import com.marcosbarbero.customer.exception.InvalidRequestException;
import com.marcosbarbero.customer.exception.ResourceAlreadyExists;
import com.marcosbarbero.customer.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcos Barbero
 */
@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler {

    private static final int SCOPE_REQUEST = RequestAttributes.SCOPE_REQUEST;

    private ErrorResourceDTO errorResource(String code, String message) {
        return new ErrorResourceDTO(code, message);
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    private ResponseEntity<ErrorResourceDTO> sendResponse(ErrorResourceDTO error, HttpStatus status) {
        return new ResponseEntity<>(error, headers(), status);
    }

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<ErrorResourceDTO> handleInvalidRequest(InvalidRequestException ire) {
        List<FieldErrorResourceDTO> fieldErrorResources = new ArrayList<>();
        List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
        fieldErrors.forEach(fieldError -> fieldErrorResources.add(FieldErrorResourceDTO.build(fieldError)));
        ErrorResourceDTO error = errorResource("InvalidRequest", ire.getMessage());
        error.setFieldErrors(fieldErrorResources);
        log.warn("[INVALID REQUEST] Error: {}", error.toString());
        return sendResponse(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({ResourceNotFoundException.class, NoHandlerFoundException.class})
    protected ResponseEntity<ErrorResourceDTO> handleResourceNotFound(Exception exception) {
        ErrorResourceDTO error = errorResource("ResourceNotFound", exception.getMessage());
        log.warn("[RESOURCE NOT FOUND] Error: {}", error.toString());
        return sendResponse(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResourceDTO> handleMessageNotReadable(HttpMessageNotReadableException e) {
        ErrorResourceDTO error = errorResource("BadRequest", "The request body cannot be read due to a malformation or it is missing");
        log.warn("[BAD REQUEST] Error: {}", error.toString());
        return sendResponse(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    protected ResponseEntity<ErrorResourceDTO> handleClientAlreadyExistsException(ResourceAlreadyExists e) {
        ErrorResourceDTO error = errorResource("ResourceAlreadyExists", e.getMessage());
        return sendResponse(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResourceDTO> globalExceptionHandler(RuntimeException e, WebRequest request) {
        HttpStatus httpStatus = getHttpStatus(getErrorStatus(request));
        ErrorResourceDTO error = errorResource(e.getMessage(), getErrorMessage(request, httpStatus));
        log.warn("[UNEXPECTED EXCEPTION] Error: {}", error.toString());
        return sendResponse(error, httpStatus);
    }

    private String getErrorMessage(WebRequest request, HttpStatus httpStatus) {
        Throwable exc = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION, SCOPE_REQUEST);
        return exc != null ? exc.getMessage() : httpStatus.getReasonPhrase();
    }

    private int getErrorStatus(WebRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE, SCOPE_REQUEST);
        return statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private HttpStatus getHttpStatus(int statusCode) {
        return HttpStatus.valueOf(statusCode);
    }
}
