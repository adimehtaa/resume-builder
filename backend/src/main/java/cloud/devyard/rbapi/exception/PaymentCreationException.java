package cloud.devyard.rbapi.exception;

import com.razorpay.RazorpayException;

public class PaymentCreationException extends RuntimeException {
    public PaymentCreationException(String message, RazorpayException e) {
        super(message);
    }
}
