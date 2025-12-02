package cloud.devyard.rbapi.service.impl;

import cloud.devyard.rbapi.document.Resume;
import cloud.devyard.rbapi.dto.AuthResponse;
import cloud.devyard.rbapi.exception.AccessDeniedException;
import cloud.devyard.rbapi.exception.ImageUploadException;
import cloud.devyard.rbapi.exception.NotFoundException;
import cloud.devyard.rbapi.repository.ResumeRepository;
import cloud.devyard.rbapi.service.AuthService;
import cloud.devyard.rbapi.service.FileUploadService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    private final Cloudinary cloudinary;
    private final ResumeRepository resumeRepository;
    private final AuthService authService;

    public Map<String, String> uploadSingleImage(MultipartFile file) {
        validateFile(file);

        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> uploadResult = cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.asMap("resource_type", "image"));
            String url = uploadResult.get("secure_url").toString();
            log.info("FileUploadServiceImpl - uploadSingleImage() : {}", uploadResult.get("secure_url").toString());
            return Map.of("imageUrl", url);
        } catch (IOException e) {
            throw new ImageUploadException("Failed to read file bytes for upload", e);
        } catch (Exception e) {
            // Catch Cloudinary / network / unexpected runtime issues and wrap them
            throw new ImageUploadException("Failed to upload image to Cloudinary", e);
        }
    }

    @Transactional
    @Override
    public Map<String, String> uploadResumeImages(
            String resumeId,
            Authentication authentication,
            MultipartFile thumbnail,
            MultipartFile profileImage) {

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new NotFoundException("Resume not found."));

        AuthResponse response = authService.getProfile(authentication.getPrincipal());
        if (!Objects.equals(response.getId(), resume.getUserId())) {
            throw new AccessDeniedException("You are not allowed to access this resume.");
        }

        Map<String, String> returnValue = new HashMap<>();
        Map<String, String> uploadedResult;

        // Thumbnail
        if (thumbnail != null && !thumbnail.isEmpty()) {
            uploadedResult = uploadSingleImage(thumbnail);

            if (uploadedResult == null || !uploadedResult.containsKey("imageUrl")
                    || uploadedResult.get("imageUrl") == null
                    || uploadedResult.get("imageUrl").isBlank()) {
                throw new IllegalStateException("Thumbnail upload failed or returned invalid result.");
            }

            String thumbnailUrl = uploadedResult.get("imageUrl");
            resume.setThumbnailLink(thumbnailUrl);
            returnValue.put("thumbnailLink", thumbnailUrl);
        }

        // Profile image
        if (profileImage != null && !profileImage.isEmpty()) {
            uploadedResult = uploadSingleImage(profileImage);

            if (uploadedResult == null || !uploadedResult.containsKey("imageUrl")
                    || uploadedResult.get("imageUrl") == null
                    || uploadedResult.get("imageUrl").isBlank()) {
                throw new IllegalStateException("Profile image upload failed or returned invalid result.");
            }

            String profileUrl = uploadedResult.get("imageUrl");
            if (resume.getProfileInfo() == null) {
                resume.setProfileInfo(new Resume.ProfileInfo());
            }
            resume.getProfileInfo().setProfilePreviewUrl(profileUrl);
            returnValue.put("profilePreviewUrl", profileUrl);
        }

        resumeRepository.save(resume);
        returnValue.put("message", "Images uploaded successfully");
        return returnValue;
    }


    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new ImageUploadException("No file provided or file is empty");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new ImageUploadException("Invalid file type. Only images are allowed.");
        }

        long maxBytes = 5 * 1024 * 1024L;
        if (file.getSize() > maxBytes) {
            throw new ImageUploadException("File too large. Max allowed size is 5MB.");
        }
    }
}
