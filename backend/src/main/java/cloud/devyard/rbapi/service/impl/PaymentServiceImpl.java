package cloud.devyard.rbapi.service.impl;

import cloud.devyard.rbapi.document.Payment;
import cloud.devyard.rbapi.repository.PaymentRepository;
import cloud.devyard.rbapi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;
    @Value("${razorpay.key.secret}")
    private String razorpaySecret;

    @Override
    public Payment createOrder(Authentication authentication, String planType) {
        return null;
    }
}
