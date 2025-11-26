package cloud.devyard.rbapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyVerifyException extends RuntimeException {
    public EmailAlreadyVerifyException(String message) {
        super(message);
    }
}

