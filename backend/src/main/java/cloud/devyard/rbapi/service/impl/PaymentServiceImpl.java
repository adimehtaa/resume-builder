package cloud.devyard.rbapi.service.impl;

import cloud.devyard.rbapi.repository.PaymentRepository;
import cloud.devyard.rbapi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
}
