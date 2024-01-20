package ftn.team23.service.implementations;

import ftn.team23.entities.User;
import ftn.team23.service.interfaces.IJavaMailService;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class JavaMailService implements IJavaMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(String email, String name, String codeActivation, String siteURL, String controller) throws UnsupportedEncodingException, MessagingException {
        String toAddress = email;
        String fromAddress = "studentftn5@gmail.com";
        String senderName = "Booking application";
        String subject = "Please verify your email";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", name);
        String verifyURL = siteURL + "/" + controller + "/verify?code=" + codeActivation;

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

        System.out.println("Email has been sent");
    }
}
