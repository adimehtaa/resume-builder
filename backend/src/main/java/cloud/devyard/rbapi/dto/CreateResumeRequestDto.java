package cloud.devyard.rbapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateResumeRequestDto {

    @NotBlank(message = "Title is required.")
    private String title;
}
