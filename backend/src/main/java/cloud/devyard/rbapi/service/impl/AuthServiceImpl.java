package cloud.devyard.rbapi.service.impl;

import cloud.devyard.rbapi.document.User;
import cloud.devyard.rbapi.dto.AuthResponse;
import cloud.devyard.rbapi.dto.RegisterRequest;
import cloud.devyard.rbapi.exception.AlreadyExistsException;
import cloud.devyard.rbapi.exception.NotFoundException;
import cloud.devyard.rbapi.mapper.AuthResponseMapper;
import cloud.devyard.rbapi.repository.UserRepository;
import cloud.devyard.rbapi.service.AuthService;
import cloud.devyard.rbapi.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${app.base.url}")
    private String appBaseUrl;

    private final UserRepository userRepository;
    private  final AuthResponseMapper authResponseMapper;
    private final EmailService emailService;

    public AuthResponse register(RegisterRequest request){
        log.info("Inside AuthService: register() {} ",request);

        if (userRepository.existsByEmail(request.getEmail())){
            throw  new AlreadyExistsException("User already exists with this email");
        }

        User newUser = authResponseMapper.registerRequestToUser(request);
        userRepository.save(newUser);

        verificationEmail(newUser);

        return authResponseMapper.userToAuthResponse(newUser);
    }

    @Override
    public void verifyEmail(String token) {
        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new NotFoundException("Invaild or Expired verification token."));

        if(user.getVerificationExpires() != null && user.getVerificationExpires().isBefore(LocalDateTime.now())){
            throw  new RuntimeException("Verification Token has expired. Please try again.");
        }

        user.setEmailVerify(true);
        user.setVerificationExpires(null);
        user.setVerificationToken(null);
        userRepository.save(user);
    }

    private void verificationEmail(User user) {
        try {

            String link = appBaseUrl+"/api/auth/verify-email?token="+user.getVerificationToken();
            String html = "<div style='font-family:sans-serif'>" +
                    "<h2>Verify your email</h2>" +
                    "<p>Hi " + user.getName() + ", please confirm your email to activate your account.</p>" +
                    "<p><a href='" + link + "' style='display:inline-block;padding:10px 16px;background:#6366f1;color:#fff;text-decoration:none;border-radius:6px'>Verify Email</a></p>" +
                    "<p>Or copy this link: " + link + "</p>" +
                    "<p>This link expires in 24 hours.</p>" +
                    "</div>";

            emailService.sendHtmlEmail(user.getEmail(),"Verify Your email",html);
        } catch (Exception e)
        {
            throw new RuntimeException("Fail to Send Varification email: " + e);
        }
    }
}
