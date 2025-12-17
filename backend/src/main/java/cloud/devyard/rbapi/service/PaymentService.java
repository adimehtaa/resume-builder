package cloud.devyard.rbapi.service;

import cloud.devyard.rbapi.document.Payment;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PaymentService {
    Payment createOrder(Authentication authentication, String planType);

    Boolean verifyPayment(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature);

    List<Payment> getUserPaymentHistory(Authentication authentication);
}
