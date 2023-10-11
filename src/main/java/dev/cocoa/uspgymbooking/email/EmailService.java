package dev.cocoa.uspgymbooking.email;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Component
public class EmailService {
    @Value("${sendgrid.apikey}")
    private String apiKey;

    @Value("${sendgrid.template.booking}")
    private String bookingTemplate;

    public void sendBookingEmail(String to, Map<String,String> dynamicTemplateData) throws IOException{
        System.out.println(apiKey);
        System.out.println(bookingTemplate);
        Email from = new Email("pranavchand777@gmail.com");
        Email toEmail = new Email(to);
        Mail mail = new Mail();

        mail.setFrom(from);
        mail.setSubject("UniFit Booking Confirmation");
        mail.setTemplateId(bookingTemplate);

        Personalization personalization = new Personalization();
        personalization.addTo(toEmail);

        for (Map.Entry<String,String> entry : dynamicTemplateData.entrySet()){
            personalization.addDynamicTemplateData(entry.getKey(), entry.getValue());
        }

        /*Map<String, String> data = new HashMap<>();

        // Add firstname
        data.put("firstName", "TESTEMAIL");
        data.put("facilityName","Pool");
        data.put("bookingStart","2023-10-01T10:00:00.000Z");
        data.put("bookingEnd","2023-10-01T11:00:00.000Z");
        data.put("dateFormat","DD MMM YYYY h:mm A");
        data.put("total","50");
        data.put("transactionId","T2345");


        try {
            email.sendBookingEmail("pranavchand777@gmail.com",data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
        }catch (IOException ex) {
            System.out.println("BOOKING EMAIL SERVICE: "+ ex.getMessage());
            throw ex;
        }
    }

}
