package cloud.devyard.rbapi.repository;

import cloud.devyard.rbapi.document.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment , String> {
}
