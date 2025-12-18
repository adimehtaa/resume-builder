package cloud.devyard.rbapi.service.impl;

import cloud.devyard.rbapi.exception.EmailSendException;
import cloud.devyard.rbapi.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String fromEmail;

    private final JavaMailSender mailSender;

    @Override
    public void sendHtmlEmail(String to, String subject, String content) throws MessagingException {
        log.info("Inside EmailServiceImpl in sendHtmlEmail(): {},{},{}", to,subject ,content);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message , true , "UTF-8");
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content , true);
        mailSender.send(message);

    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String body, byte[] attachment, String filename) {
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            helper.addAttachment(filename, new ByteArrayResource(attachment));

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new EmailSendException(e.getMessage());
        }

    }
}
