package cloud.devyard.rbapi.controller;

import cloud.devyard.rbapi.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final EmailService emailService;

    @PostMapping(value = "/send-resume" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String , Object>> sendResumeByEmail(
            @RequestPart("recipientEmail") String recipientEmail,
            @RequestPart("subject") String subject,
            @RequestPart("message") String message,
            @RequestPart("pdfFile") MultipartFile pdfFile,
            Authentication authentication
    ){
        var response = new HashMap<String , Object>();

        if(Objects.isNull(recipientEmail) || Objects.isNull(pdfFile)){
            response.put("message" , "Missing required fields");
            response.put("success" , false);
            return ResponseEntity.badRequest().body(response);
        }

        byte[] pdfbytes;
        String originalFilename;

        try {
             pdfbytes = pdfFile.getBytes();
             originalFilename = pdfFile.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String filename = Objects.nonNull(originalFilename) ? originalFilename : "resume.pdf";
        String emailSubject = Objects.nonNull(subject) ? subject : "Resume Application";
        String emailBody = Objects.nonNull(message) ? message : "please find my resume attached. \n\n Best Regards";

        emailService.sendEmailWithAttachment(recipientEmail , emailSubject , emailBody , pdfbytes , originalFilename);

        response.put("message", "resume send successfully to "+ recipientEmail);
        response.put("success", true);
        return ResponseEntity.ok(response);
    }

}
