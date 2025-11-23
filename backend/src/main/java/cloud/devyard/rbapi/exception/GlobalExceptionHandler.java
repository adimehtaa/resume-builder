package cloud.devyard.rbapi.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex)
    {
        log.error("Inside GlobalExceptionHandler - handleGeneralException()");
        ErrorResponse error  = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value() , ex.getMessage());
        return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        log.error("Inside GlobalExceptionHandler - handleMethodArgumentNotValidException()");
        Map<String , String > errors = new HashMap<>();
        BindingResult bResult = ex.getBindingResult();
        List<FieldError> errorList = bResult.getFieldErrors();
        for(FieldError error : errorList)
        {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("Inside GlobalExceptionHandler - handleConstraintViolationException()");
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(cv -> {
            String path = cv.getPropertyPath().toString();
            String message = cv.getMessage();
            errors.put(path, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex)
    {
        log.error("Inside GlobalExceptionHandler - handleNotFoundException()");
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value() , ex.getMessage());
        return new  ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException ex)
    {
        log.error("Inside GlobalExceptionHandler - handleAlreadyExistsException()");
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value() , ex.getMessage());
        return new  ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<ErrorResponse> handleImageUploadException(ImageUploadException ex) {
        log.error("Image upload failed", ex);
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MultipartException.class, MaxUploadSizeExceededException.class})
    public ResponseEntity<ErrorResponse> handleMultipartException(Exception ex) {
        log.error("Multipart error", ex);
        String msg = ex instanceof MaxUploadSizeExceededException
                ? "Uploaded file exceeds maximum allowed size"
                : "Invalid multipart request";
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), msg);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotVerifyException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotVerifyException(EmailNotVerifyException ex){
        log.error("Email is not verify: ", ex);
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value() , ex.getMessage());
        return  new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
    }
}
