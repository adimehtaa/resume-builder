package cloud.devyard.rbapi.service;

import cloud.devyard.rbapi.document.Resume;
import cloud.devyard.rbapi.dto.CreateResumeRequestDto;
import org.springframework.security.core.Authentication;

public interface ResumeService {
    public Resume createResume(CreateResumeRequestDto request , Authentication authentication);
}
