package cloud.devyard.rbapi.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileUploadService {
    public Map<String , String> uploadSingleImage(MultipartFile file);
    Map<String, String> uploadResumeImages(String id, Authentication authentication, MultipartFile thumbnail, MultipartFile profileImage);
}
