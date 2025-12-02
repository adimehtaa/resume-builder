package cloud.devyard.rbapi.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "resumes")
public class Resume {
    @Id
    @JsonProperty("_id")
    private String id;

    private String userId;
    private String title;
    private String thumbnailLink;

    private Template template;

    private ProfileInfo profileInfo;

    private ContactInfo contactInfo;

    private List<WorkExperience> workExperience;

    private List<Education> education;

    private List<Skill> skill;

    private List<Project> project;

    private List<Certification> certification;

    private List<Language> language;

    private List<String> interests;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Template {
        private String theme;
        private List<String> colorPalette;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProfileInfo {
        private String profilePreviewUrl;
        private String fullName;
        private String designation;
        private String summary;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ContactInfo {
        private String email;
        private String phone;
        private String location;
        private String linkedIn;
        private String github;
        private String website;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WorkExperience {
        private String company;
        private String role;
        private String startDate;
        private String endDate;
        private String description;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Education {
        private String degree;
        private String institution;
        private String startDate;
        private String endDate;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Skill {
        private String name;
        private Integer progress;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Project {
        private String title;
        private String description;
        private String github;
        private String liveDemo;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Certification {
        private String title;
        private String issuer;
        private String Year;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Language {
        private String name;
        private Integer progress;
    }
}
