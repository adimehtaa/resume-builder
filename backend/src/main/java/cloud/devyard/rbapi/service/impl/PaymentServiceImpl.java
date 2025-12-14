package cloud.devyard.rbapi.service.impl;

import cloud.devyard.rbapi.document.Payment;
import cloud.devyard.rbapi.dto.AuthResponse;
import cloud.devyard.rbapi.exception.PaymentCreationException;
import cloud.devyard.rbapi.repository.PaymentRepository;
import cloud.devyard.rbapi.service.AuthService;
import cloud.devyard.rbapi.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final AuthService  authService;
    private final PaymentRepository paymentRepository;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;
    @Value("${razorpay.key.secret}")
    private String razorpaySecret;

    @Override
    public Payment createOrder(Authentication authentication, String planType) {

        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId , razorpaySecret);

            AuthResponse authResponse  = authService.getProfile(authentication.getPrincipal());
            JSONObject options = new JSONObject();
            options.put("amount", 50000); // amount in paise
            options.put("currency", "INR");
            options.put("receipt", "premium_"+UUID.randomUUID().toString().substring(0 , 10));

            Order order = razorpayClient.orders.create(options);

            Payment payment =  Payment.builder()
                    .userId(authResponse.getId())
                    .razorpayOrderId(order.get("id"))
                    .amount(order.get("amount"))
                    .currency(order.get("currency"))
                    .receipt(order.get("receipt"))
                    .status("created")
                    .planType(planType)
                    .build();

            return paymentRepository.save(payment);

        } catch (RazorpayException e) {
            throw new PaymentCreationException("Failed to create Razorpay order", e);
        }
    }
}
