package cloud.devyard.rbapi.service.impl;

import cloud.devyard.rbapi.exception.ImageUploadException;
import cloud.devyard.rbapi.service.FileUploadService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    private final Cloudinary cloudinary;

    public Map<String, String> uploadSingleImage(MultipartFile file) {
        validateFile(file);

        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> uploadResult = cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.asMap("resource_type", "image"));
            String url = uploadResult.get("secure_url").toString();
            log.info("FileUploadServiceImpl - uploadSingleImage() : {}",uploadResult.get("secure_url").toString());
            return Map.of("imageUrl", url);
        } catch (IOException e) {
            throw new ImageUploadException("Failed to read file bytes for upload", e);
        } catch (Exception e) {
            // Catch Cloudinary / network / unexpected runtime issues and wrap them
            throw new ImageUploadException("Failed to upload image to Cloudinary", e);
        }
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
