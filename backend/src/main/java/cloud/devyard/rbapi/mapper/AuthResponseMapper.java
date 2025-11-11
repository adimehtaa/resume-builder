package cloud.devyard.rbapi.mapper;


import cloud.devyard.rbapi.document.User;
import cloud.devyard.rbapi.dto.AuthResponse;
import cloud.devyard.rbapi.dto.RegisterRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuthResponseMapper {

    public User registerRequestToUser(RegisterRequest request){
        return User.builder().
            name(request.getName()).
            email(request.getEmail()).
            password(request.getPassword()).
            profileImageUrl(request.getProfileImageUrl()).
            subscriptionPlan("Basic").
            isEmailVerify(false)
            .verificationToken(UUID.randomUUID().toString())
            .verificationExpires(LocalDateTime.now().plusHours(24))
            .build();
    }

    public AuthResponse userToAuthResponse(User user){
        return AuthResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .profileImageUrl(user.getProfileImageUrl())
            .isEmailVerify(user.isEmailVerify())
            .subscriptionPlan(user.getSubscriptionPlan())
            .build();
    }


}
