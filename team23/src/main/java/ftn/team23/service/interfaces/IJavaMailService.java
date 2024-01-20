package ftn.team23.service.interfaces;

import ftn.team23.entities.User;
import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface IJavaMailService {

    void sendVerificationEmail(String email, String name, String codeActivation, String siteURL, String controller)
            throws UnsupportedEncodingException, MessagingException;

}
