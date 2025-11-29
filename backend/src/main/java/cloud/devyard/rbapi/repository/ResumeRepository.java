package cloud.devyard.rbapi.repository;

import cloud.devyard.rbapi.document.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends MongoRepository<Resume , String> {
}