package cloud.devyard.rbapi.controller;

import cloud.devyard.rbapi.document.Resume;
import cloud.devyard.rbapi.dto.CreateResumeRequestDto;
import cloud.devyard.rbapi.service.ResumeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/create")
    public ResponseEntity<Resume> createResume(@Valid @RequestBody CreateResumeRequestDto request,
                                          Authentication authentication){
        Resume resume = resumeService.createResume(request , authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(resume);
    }

    @GetMapping
    public ResponseEntity<List<Resume>> getUserResumes(Authentication authentication){
        List<Resume> resumes = resumeService.getUserResumes(authentication);
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable String id , Authentication authentication){
        Resume resume = resumeService.getResumeById(id , authentication);
        return ResponseEntity.ok(resume);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateResume(@PathVariable String id , @RequestBody Resume updatedData){
        return ResponseEntity.ok("");
    }

    @PutMapping("/{id}/upload-images")
    public ResponseEntity<?> uploadResumeImages(@PathVariable String id , @RequestPart(value = "thumbnail" , required = true)MultipartFile thumbnail ,
                                                @RequestPart(value = "profileImage" , required = false) MultipartFile profileImage,
                                                HttpServletRequest request){
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResume(@PathVariable String id){
        return ResponseEntity.ok("");
    }
 }
