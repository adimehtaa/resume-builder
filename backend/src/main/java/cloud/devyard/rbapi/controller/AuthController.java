package cloud.devyard.rbapi.controller;

import cloud.devyard.rbapi.dto.AuthResponse;
import cloud.devyard.rbapi.dto.LoginRequest;
import cloud.devyard.rbapi.dto.RegisterRequest;
import cloud.devyard.rbapi.exception.RequiredValueException;
import cloud.devyard.rbapi.mapper.AuthResponseMapper;
import cloud.devyard.rbapi.service.AuthService;
import cloud.devyard.rbapi.service.FileUploadService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final AuthService authService;
    private final FileUploadService fileUploadService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        log.info("Inside AuthController - register(): {}",request);
        AuthResponse response =  authService.register(request);
        log.info("Response from service: {} ",response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<Void> verifyEmail(@RequestParam String token){
        log.info("Inside AuthController - verifyEmail(): {}",token);
        authService.verifyEmail(token);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/upload-image")
    public ResponseEntity<Map<String, String>> imageUpload(@RequestPart("images") MultipartFile file){
        log.info("Inside AuthCOntroller - imageUpload()");
        Map<String, String> result = fileUploadService.uploadSingleImage(file);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/resend-email")
    public ResponseEntity<Map<String , String>> resendVerification(@RequestBody Map<String , String> body){
        String email = body.get("email").trim();

        if (email.isBlank() || email.isEmpty())
        {
            throw new RequiredValueException("Email is required value is missing.");
        }

        String message = authService.resendVerification(email);
        return ResponseEntity.ok(Map.of("message" , message ));
    }

    @GetMapping("/profile")
    public ResponseEntity<AuthResponse> getProfile(Authentication authentication)
    {
        Object principalObject = authentication.getPrincipal();
        AuthResponse user = authService.getProfile(principalObject);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/checkAuthToken")
    public ResponseEntity<String> checkAuthToken(){
        return ResponseEntity.ok("token validation is working");
    }

}
