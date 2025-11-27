package cloud.devyard.rbapi.service;


import cloud.devyard.rbapi.document.User;
import cloud.devyard.rbapi.dto.AuthResponse;
import cloud.devyard.rbapi.dto.LoginRequest;
import cloud.devyard.rbapi.dto.RegisterRequest;
import org.springframework.security.core.Authentication;

public interface AuthService {
    public AuthResponse register(RegisterRequest request);
    public void verifyEmail(String token);
    public AuthResponse login(LoginRequest request);
    public String resendVerification(String email);
    public AuthResponse getProfile(Object principalObject);
}
