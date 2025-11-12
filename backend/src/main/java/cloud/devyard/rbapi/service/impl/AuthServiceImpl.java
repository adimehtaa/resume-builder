package cloud.devyard.rbapi.service.impl;

import cloud.devyard.rbapi.document.User;
import cloud.devyard.rbapi.dto.AuthResponse;
import cloud.devyard.rbapi.dto.RegisterRequest;
import cloud.devyard.rbapi.mapper.AuthResponseMapper;
import cloud.devyard.rbapi.repository.UserRepository;
import cloud.devyard.rbapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private  final AuthResponseMapper authResponseMapper;

    public AuthResponse register(RegisterRequest request){
        log.info("Inside AuthService: register() {} ",request);

        if (userRepository.existsByEmail(request.getEmail())){
            throw  new RuntimeException("User already exists with this email");
        }

        User newUser = authResponseMapper.registerRequestToUser(request);
        userRepository.save(newUser);

        //Todo: Send verification email

        return authResponseMapper.userToAuthResponse(newUser);
    }
}
