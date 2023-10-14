package dev.cocoa.uspgymbooking.email;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import dev.cocoa.uspgymbooking.booking.Booking;
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

    public void sendBookingEmail(Booking booking) throws IOException{
        System.out.println(apiKey);
        System.out.println(bookingTemplate);
        Email from = new Email("pranavchand777@gmail.com");
        Email toEmail = new Email(booking.getUser().getEmail());
        Mail mail = new Mail();

        mail.setFrom(from);
        mail.setSubject("UniFit Booking Confirmation");
        mail.setTemplateId(bookingTemplate);

        Personalization personalization = new Personalization();
        personalization.addTo(toEmail);
        Map<String, String> data = new HashMap<>();

        // Add firstname
        data.put("firstName", booking.getUser().getFirstName());
        data.put("facilityName",booking.getFacility().getName());
        data.put("bookingDate",booking.getBookedDate().toString());
        data.put("bookingStart",booking.getStart().toString());
        data.put("bookingEnd",booking.getEnd().toString());
        data.put("dateFormat","DD MMM YYYY h:mm A");
        data.put("total",booking.getFacility().getFacilityType().getRate().toString());
        data.put("transactionId","T2345");

        for (Map.Entry<String,String> entry : data.entrySet()){
            personalization.addDynamicTemplateData(entry.getKey(), entry.getValue());
        }

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
