package com.taleTrade.MailSender.service;

import com.taleTrade.MailSender.models.Response;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    public Response generateActivationMail(String emailAddress, String activationToken) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            String activationLink = "http://localhost:5173/authentication/AccountVerification/" + activationToken;
            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Email Verification</title>\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css\" rel=\"stylesheet\">\n" +
                    "    <style>\n" +
                    "        /* Custom CSS */\n" +
                    "        .container {\n" +
                    "            display: flex;\n" +
                    "            align-items: center;\n" +
                    "            justify-content: center;\n" +
                    "            flex-direction: column;\n" +
                    "            margin-top: 5rem;\n" +
                    "        }\n" +
                    "        .section {\n" +
                    "            max-width: 42rem;\n" +
                    "            margin: 0 auto;\n" +
                    "            background-color: #ffffff;\n" +
                    "        }\n" +
                    "        .header {\n" +
                    "            height: 200px;\n" +
                    "            background-color: #365CCE;\n" +
                    "            color: #ffffff;\n" +
                    "            display: flex;\n" +
                    "            flex-direction: column;\n" +
                    "            gap: 1.25rem;\n" +
                    "        }\n" +
                    "        .divider {\n" +
                    "            display: flex;\n" +
                    "            align-items: center;\n" +
                    "            gap: 0.75rem;\n" +
                    "        }\n" +
                    "        .divider-line {\n" +
                    "            width: 1px;\n" +
                    "            height: 1px;\n" +
                    "            background-color: #ffffff;\n" +
                    "        }\n" +
                    "        .title-container {\n" +
                    "            display: flex;\n" +
                    "            flex-direction: column;\n" +
                    "            align-items: center;\n" +
                    "            justify-content: center;\n" +
                    "        }\n" +
                    "        .content {\n" +
                    "            padding: 2rem;\n" +
                    "        }\n" +
                    "        .title {\n" +
                    "            color: #374151;\n" +
                    "            font-size: 1.5rem;\n" +
                    "            line-height: 2rem;\n" +
                    "            font-weight: 600;\n" +
                    "            text-align: center;\n" +
                    "            text-transform: uppercase;\n" +
                    "            letter-spacing: 0.1em;\n" +
                    "        }\n" +
                    "        .paragraph {\n" +
                    "            margin-top: 1rem;\n" +
                    "            color: #6B7280;\n" +
                    "            font-size: 1rem;\n" +
                    "            line-height: 1.5rem;\n" +
                    "        }\n" +
                    "        .button {\n" +
                    "            display: inline-block;\n" +
                    "            padding: 0.75rem 1.5rem;\n" +
                    "            margin-top: 1.5rem;\n" +
                    "            font-size: 0.875rem;\n" +
                    "            font-weight: 600;\n" +
                    "            text-transform: uppercase;\n" +
                    "            letter-spacing: 0.1em;\n" +
                    "            color: #ffffff;\n" +
                    "            background-color: #F97316;\n" +
                    "            border: none;\n" +
                    "            border-radius: 0.375rem;\n" +
                    "            transition: background-color 0.3s ease;\n" +
                    "            cursor: pointer;\n" +
                    "        }\n" +
                    "        .button:hover {\n" +
                    "            background-color: #FB923C;\n" +
                    "        }\n" +
                    "        .footer {\n" +
                    "            background-color: #E5E7EB;\n" +
                    "            padding: 2rem;\n" +
                    "        }\n" +
                    "        .footer-text {\n" +
                    "            color: #374151;\n" +
                    "            font-size: 1rem;\n" +
                    "            line-height: 1.5rem;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        .contact-info {\n" +
                    "            margin-top: 1.5rem;\n" +
                    "            color: #374151;\n" +
                    "            font-size: 1rem;\n" +
                    "            line-height: 1.5rem;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        .contact-info a {\n" +
                    "            color: #374151;\n" +
                    "            text-decoration: underline;\n" +
                    "        }\n" +
                    "        .social-icons {\n" +
                    "            display: flex;\n" +
                    "            align-items: center;\n" +
                    "            justify-content: center;\n" +
                    "            gap: 1rem;\n" +
                    "            margin-top: 1.5rem;\n" +
                    "        }\n" +
                    "        .social-icons a {\n" +
                    "            color: #374151;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "    <section class=\"section\">\n" +
                    "        <div class=\"header\">\n" +
                    "            <div class=\"divider\">\n" +
                    "                <div class=\"divider-line\"></div>\n" +
                    "                <svg stroke=\"currentColor\" fill=\"currentColor\" stroke-width=\"0\" viewBox=\"0 0 24 24\" height=\"1em\" width=\"1em\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "                    <path fill=\"none\" d=\"M0 0h24v24H0V0z\"></path>\n" +
                    "                    <path d=\"M20 4H4c-1.1 0-1.99.9-1.99 2L2 18c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 14H4V8l8 5 8-5v10zm-8-7L4 6h16l-8 5z\"></path>\n" +
                    "                </svg>\n" +
                    "                <div class=\"divider-line\"></div>\n" +
                    "            </div>\n" +
                    "            <div class=\"title-container\">\n" +
                    "                <div class=\"title\">THANKS FOR SIGNING UP!</div>\n" +
                    "                <div class=\"title\">Verify your E-mail Address</div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <main class=\"content\">\n" +
                    "            <h2 class=\"text-gray-700\">Hello New User,</h2>\n" +
                    "            <p class=\"paragraph\">Please use the following link below to verify your email address.</p>\n" +
                    "            <p class=\"paragraph\">This link will only be valid for the next <span class=\"font-bold\">2 minutes</span>. If the link does not work, you can try to resend the email.</p>\n" +
                    "            <a href=\"{{Activation-Link}}\" class=\"button\">Verify Email</a>\n" +
                    "            <p class=\"paragraph\">Thank you,<br>Tale Trade Team</p>\n" +
                    "        </main>\n" +
                    "        <footer class=\"footer\">\n" +
                    "            <p class=\"footer-text\">© 2024 Tale Trade. All Rights Reserved.</p>\n" +
                    "            <div class=\"contact-info\">\n" +
                    "                <h1 class=\"text-[#365CCE] font-semibold tracking-wide text-lg\">Get in touch</h1>\n" +
                    "                <a href=\"tel:+91-848-883-8308\">+91-848-883-8308</a>\n" +
                    "                <a href=\"mailto:sales@infynno.com\">taleTrade@gmail.com</a>\n" +
                    "            </div>\n" +
                    "            <div class=\"social-icons\">\n" +
                    "                <a href=\"#\">\n" +
                    "                    <svg stroke=\"currentColor\" fill=\"currentColor\" stroke-width=\"0\" viewBox=\"0 0 24 24\" height=\"20\" width=\"20\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "                        <path d=\"M16.75 0H6.25A6.25 6.25 0 0 0 0 6.25v10.5A6.25 6.25 0 0 0 6.25 23h10.5A6.25 6.25 0 0 0 23 16.75V6.25A6.25 6.25 0 0 0 16.75 0zM7.983 18.5H5.5v-8.008h2.483V18.5zm-.642-9.008a2.58 2.58 0 0 1-2.58-2.58c0-1.425 1.155-2.58 2.58-2.58 1.426 0 2.58 1.155 2.58 2.58 0 1.425-1.154 2.58-2.58 2.58zm9.008 9.008h-2.483v-4.05c0-1.19-.024-2.724-1.656-2.724-1.658 0-1.913 1.296-1.913 2.634v4.14h-2.482v-8.008h2.372v1.394h.032c.33-.63 1.14-1.295 2.344-1.295 2.5 0 2.96 1.647 2.96 3.793v4.116h-.002v.002z\"></path>\n" +
                    "                    </svg>\n" +
                    "                </a>\n" +
                    "                <a href=\"#\">\n" +
                    "                    <svg stroke=\"currentColor\" fill=\"currentColor\" stroke-width=\"0\" viewBox=\"0 0 16 16\" height=\"18\" width=\"18\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "                        <path d=\"M16 8.049c0-4.446-3.582-8.05-8-8.05S3.582 3.603 3.582 8.049c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.049H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z\"></path>\n" +
                    "                    </svg>\n" +
                    "                </a>\n" +
                    "                <a href=\"#\">\n" +
                    "                    <svg stroke=\"currentColor\" fill=\"currentColor\" stroke-width=\"0\" viewBox=\"0 0 1024 1024\" height=\"18\" width=\"18\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "                        <path d=\"M512 378.7c-73.4 0-133.3 59.9-133.3 133.3S438.6 645.3 512 645.3 645.3 585.4 645.3 512 585.4 378.7 512 378.7zm399.8 133.4v309.2c0 17.4-14.1 31.6-31.6 31.6H157.8c-17.4 0-31.6-14.1-31.6-31.6V512c0-198.9 162-360.9 360.9-360.9 71.2 0 139.4 20.7 197.1 59.7 11.5 6.8 24.5 10.2 38 10.2 29.6 0 57.8-16.5 71.1-42.9 7.8-16.5 9.6-35.2 5.6-52.8 4.8 1-241.3-1-241.3-1-17.4 0-31.6-14.1-31.6-31.6s14.1-31.6 31.6-31.6c33.2 0 62.4-21.6 72.4-52.5 4-15.8 1.8-32.8-5.4-46.6-15.1-25.8-43.1-42.1-73.4-42.1-12.4 0-25.1 3-36.4 8.7-62.2 36.5-133.1 55.4-206.8 55.4-124.8 0-233.4-59.2-304.3-150.8-3.4-4.7-6.8-9.7-10.1-14.9-15.1-23-24.1-51.3-24.1-82.1 0-17.4 14.1-31.6 31.6-31.6s31.6 14.1 31.6 31.6c0 39.1 10.2 76.2 28 107.5C92.4 246.8 8.1 328.7 2.1 425.8c-1 13.1 4.2 26 13.8 35.6C40.7 498.8 48 512 48 525.2c0 17.4-14.1 31.6-31.6 31.6S0 542.6 0 525.2c0-22.1 9-43.1 24.6-58.4 10-10.6 22.1-18.7 35.3-24.4 0 0 243.6-126.1 243.7-126.2 9.7-7.7 21.8-11.6 34.4-11.6 25.4 0 53.3 12.9 73.6 34.3 15 15.8 24.2 37.4 26.7 61.7.5 5.5.7 11.1.8 16.7 7.7-13.1 17.6-24.5 29.5-34.3 43.2-37.5 102.2-57.5 162.5-57.5 139.5 0 253.2 113.6 253.2 253.1z\"></path>\n" +
                    "                        <path d=\"M337.7 768h85.3V377.8h-85.3V768zM361.7 341.5h36c18.5 0 33.5 15.1 33.5 33.5v7.3c0 18.5-15.1 33.5-33.5 33.5h-36V341.5zM760 768h-85.3V555.9c0-47.6-17.4-80-60.9-80-33.3 0-53.2 22.4-61.9 44-3.2 7.7-4 18.5-4 29.3V768h-85.3V377.8h80.4v23.6h1.1c11.3-17.1 31.5-41.7 76.9-41.7 56.2 0 98.5 36.9 98.5 116.9V768z\"></path>\n" +
                    "                    </svg>\n" +
                    "                </a>\n" +
                    "            </div>\n" +
                    "        </footer>\n" +
                    "    </section>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>\n";
            htmlContent = htmlContent.replace("{{Activation-Link}}", activationLink);
            helper.setTo(emailAddress);
            helper.setSubject("Account Activation");
            helper.setText(htmlContent, true);
            javaMailSender.send(message);
            return Response.builder()
                    .status(true)
                    .message("Activation mail sent successfully")
                    .build();
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            return Response.builder()
                    .status(false)
                    .message("Failed to send activation mail")
                    .build();
        }
    }
}
