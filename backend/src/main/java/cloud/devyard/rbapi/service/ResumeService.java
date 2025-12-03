package cloud.devyard.rbapi.service;

import cloud.devyard.rbapi.document.Resume;
import cloud.devyard.rbapi.dto.CreateResumeRequestDto;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface ResumeService {
    public Resume createResume(CreateResumeRequestDto request , Authentication authentication);
    public List<Resume> getUserResumes(Authentication authentication);
    public Resume getResumeById(String resumeId , Authentication authentication);
    Resume updateResume(String id, Resume updatedData, Authentication authentication);
    void deleteResume(String resumeId, Authentication authentication);
}
