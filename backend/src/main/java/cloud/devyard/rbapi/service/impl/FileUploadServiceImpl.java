package cloud.devyard.rbapi.service.impl;

import cloud.devyard.rbapi.service.FileUploadService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private Cloudinary cloudinary;

    public Map<String , String> uploadSingleImage(MultipartFile file) throws IOException {
        Map<String , Objects> imageUploadResult =  cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type" , "image"));
        return Map.of("imageUrl", imageUploadResult.get("secure_url").toString());
    }
}
