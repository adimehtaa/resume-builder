package cloud.devyard.rbapi.service.impl;

import cloud.devyard.rbapi.dto.AuthResponse;
import cloud.devyard.rbapi.service.AuthService;
import cloud.devyard.rbapi.service.TemplatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplatesServiceImpl implements TemplatesService {

    private final AuthService authService;

    @Override
    public Map<String, Object> getTemplates(Authentication authentication) {
        AuthResponse authResponse = authService.getProfile(authentication.getPrincipal());

        List<String> availableTemplates;

        Boolean isPremium = "premium".equalsIgnoreCase(authResponse.getSubscriptionPlan());

        if (isPremium)
        {
            availableTemplates = List.of("t1" , "t2" , "t3");
        } else {
            availableTemplates = List.of("t1");
        }

        Map<String , Object> restrictions = new HashMap<>();
        restrictions.put("availableTemplates" , availableTemplates);
        restrictions.put("allTemplates" , List.of("t1" , "t2" , "t3"));
        restrictions.put("subscriptionPlan" , authResponse.getSubscriptionPlan());
        restrictions.put("isPremium" , isPremium);
        return restrictions;
    }
}
