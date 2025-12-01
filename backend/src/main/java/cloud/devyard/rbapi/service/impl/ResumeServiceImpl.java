package cloud.devyard.rbapi.service.impl;

import cloud.devyard.rbapi.document.Resume;
import cloud.devyard.rbapi.dto.AuthResponse;
import cloud.devyard.rbapi.dto.CreateResumeRequestDto;
import cloud.devyard.rbapi.exception.AccessDeniedException;
import cloud.devyard.rbapi.exception.NotFoundException;
import cloud.devyard.rbapi.repository.ResumeRepository;
import cloud.devyard.rbapi.service.AuthService;
import cloud.devyard.rbapi.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final AuthService authService;

    @Override
    public Resume createResume(CreateResumeRequestDto request , Authentication authentication) {
        Resume newResume = new Resume();

        AuthResponse authResponse = authService.getProfile(authentication.getPrincipal());
        newResume.setUserId(authResponse.getId());
        newResume.setTitle(request.getTitle());

        setDefaultResumeData(newResume);

        return resumeRepository.save(newResume);
    }

    @Override
    public List<Resume> getUserResumes(Authentication authentication) {
        AuthResponse authResponse = authService.getProfile(authentication.getPrincipal());
        List<Resume> resumes = resumeRepository.findByUserIdOrderByUpdatedAtDesc(authResponse.getId());
        return resumes;
    }

    @Override
    public Resume getResumeById(String resumeId, Authentication authentication) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new NotFoundException("Resume Not found."));

        AuthResponse response = authService.getProfile(authentication.getPrincipal());
        if(!response.getId().equals(resume.getUserId()))
        {
            throw new AccessDeniedException("You are not allowed to access this resume.");
        }
        return resume;
    }

    @Override
    public Resume updateResume(String id, Resume updatedData, Authentication authentication) {
        return null;
    }

    private void setDefaultResumeData(Resume resume){
        resume.setProfileInfo(new Resume.ProfileInfo());
        resume.setContactInfo(new Resume.ContactInfo());
        resume.setWorkExperience(new ArrayList<>());
        resume.setWorkExperience(new ArrayList<>());
        resume.setSkill(new ArrayList<>());
        resume.setProject(new ArrayList<>());
        resume.setCertification(new ArrayList<>());
        resume.setLanguage(new ArrayList<>());
        resume.setInterests(new ArrayList<>());
    }
}
