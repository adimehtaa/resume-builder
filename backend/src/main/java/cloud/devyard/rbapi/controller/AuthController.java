package cloud.devyard.rbapi.controller;

import cloud.devyard.rbapi.dto.AuthResponse;
import cloud.devyard.rbapi.dto.RegisterRequest;
import cloud.devyard.rbapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final AuthService authService;

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
}
