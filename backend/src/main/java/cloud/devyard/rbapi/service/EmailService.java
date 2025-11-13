package cloud.devyard.rbapi.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendHtmlEmail(String to , String subject, String content)throws MessagingException;;
}
