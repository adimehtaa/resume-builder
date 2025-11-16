package cloud.devyard.rbapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface FileUploadService {
    public Map<String , String> uploadSingleImage(MultipartFile file) throws IOException;
}
