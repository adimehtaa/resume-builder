package cloud.devyard.rbapi.controller;

import cloud.devyard.rbapi.document.Payment;
import cloud.devyard.rbapi.exception.PlanTypeException;
import cloud.devyard.rbapi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String , String> request){

        return null;
    }

    @GetMapping("/history")
    public ResponseEntity<?> getPaymentHistory(Authentication authentication){

        return null;
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable String orderId){
        return null;
    }
}
