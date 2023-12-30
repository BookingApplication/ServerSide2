package ftn.team23.service.interfaces;//package ftn.team23.service.interfaces;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public interface ISendGridService {

    void sendVerificationEmail(String toEmail, String verificationLink) throws Exception;
}
