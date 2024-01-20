//package ftn.team23.service.implementations;
//
//import com.sendgrid.Method;
//import com.sendgrid.Request;
//import com.sendgrid.Response;
//import com.sendgrid.SendGrid;
//import com.sendgrid.helpers.mail.Mail;
//import com.sendgrid.helpers.mail.objects.Content;
//import com.sendgrid.helpers.mail.objects.Email;
//import ftn.team23.service.interfaces.ISendGridService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SendGridService implements ISendGridService {
//
//    @Value("${sendgrid.api-key}")
//    private String sendGridApiKey;
//
//    public void sendVerificationEmail(String toEmail, String verificationLink) throws Exception {
//        Email from = new Email("studentftn5@gmail.com");
//        String subject = "Email Verification";
//        Email to = new Email(toEmail);
//        Content content = new Content("text/html", "Click on the following link to verify your email: " + verificationLink);
//
//        Mail mail = new Mail(from, subject, to, content);
//
//        SendGrid sg = new SendGrid(sendGridApiKey);
//        Request request = new Request();
//
//        request.setMethod(Method.POST);
//        request.setEndpoint("mail/send");
//        request.setBody(mail.build());
//
//        Response response = sg.api(request);
//
//        if (response.getStatusCode() != 202) {
//            throw new Exception("Failed to send email. Status code: " + response.getStatusCode());
//        }
//    }
//
//}
