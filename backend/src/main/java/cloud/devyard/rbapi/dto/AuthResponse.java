package cloud.devyard.rbapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String id;
    private String name;
    private String email;
    private String profileImageUrl;
    private String subscriptionPlan;
    private boolean isEmailVerify = false;
    private String verificationToken;
    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
