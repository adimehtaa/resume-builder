package cloud.devyard.rbapi.service;


import cloud.devyard.rbapi.dto.AuthResponse;
import cloud.devyard.rbapi.dto.RegisterRequest;

public interface AuthService {
    public AuthResponse register(RegisterRequest request);
    public void verifyEmail(String token);
}
