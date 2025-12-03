package cloud.devyard.rbapi.service;

import org.springframework.security.core.Authentication;

import java.util.Map;

public interface TemplatesService {
    Map<String, Object> getTemplates(Authentication authentication);
}
