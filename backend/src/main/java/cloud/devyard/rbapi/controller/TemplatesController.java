package cloud.devyard.rbapi.controller;

import cloud.devyard.rbapi.service.TemplatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/templates")
public class TemplatesController {

    private final TemplatesService templatesService;

    @GetMapping
    public ResponseEntity<?> getTemplates(Authentication authentication){
        var templates = templatesService.getTemplates(authentication);
        return ResponseEntity.ok(templates);
    }
}
