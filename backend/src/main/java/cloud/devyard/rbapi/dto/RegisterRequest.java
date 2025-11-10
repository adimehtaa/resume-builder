package cloud.devyard.rbapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Size(min = 2, max = 20, message = "Name must be in between {min} - {max} characters")
    @NotBlank(message = "Name is required")
    private String name;

    @Size(min = 8, max = 20, message = "Password must be in between {min} - {max} characters")
    @NotBlank(message = "password is required")
    private String password;
    private String profileImageUrl;
}
