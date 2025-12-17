package cloud.devyard.rbapi.controller;

import cloud.devyard.rbapi.document.Payment;
import cloud.devyard.rbapi.exception.BadRequestException;
import cloud.devyard.rbapi.exception.PlanTypeException;
import cloud.devyard.rbapi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create-order")
    public ResponseEntity<Map<String , Object>> createOrder(@RequestBody Map<String , String> request , Authentication authentication){

        String planType = request.get("planType");
        if(!"premium".equalsIgnoreCase(planType)) {
            throw new PlanTypeException("Invalid plan type.");
        }

        Payment payment =  paymentService.createOrder(authentication , planType);
        Map<String , Object> response = Map.of(
                "orderId" , payment.getRazorpayOrderId(),
                "amount" , payment.getAmount(),
                "currency", payment.getCurrency(),
                "receipt" , payment.getReceipt()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String , String>> verifyPayment(@RequestBody Map<String , String> request){

        String razorpayOrderId= request.get("razorpay_order_id");
        String razorpayPaymentId = request.get("razorpay_payment_id");
        String razorpaySignature = request.get("razorpay_signature");

        if (Objects.isNull(razorpayOrderId)
                || Objects.isNull(razorpayPaymentId)
                || Objects.isNull(razorpaySignature)) {
            throw new BadRequestException("Missing Razorpay parameters");
        }

        Boolean isValid =  paymentService.verifyPayment(razorpayOrderId , razorpayPaymentId , razorpaySignature);
        if(isValid){
            return ResponseEntity.ok(Map.of("message","Payment verified successfully.", "status" , "success"));
        }
        return ResponseEntity.ok(Map.of("message","Payment verified failed.", "status" , "failed"));
    }

    @GetMapping("/history")
    public ResponseEntity<List<Payment>> getPaymentHistory(Authentication authentication){

        List<Payment> payments = paymentService.getUserPaymentHistory(authentication);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Payment> getOrderDetails(@PathVariable String orderId){
        Payment payments = paymentService.getOrderDetails(orderId);
        return ResponseEntity.ok(payments);
    }
}
