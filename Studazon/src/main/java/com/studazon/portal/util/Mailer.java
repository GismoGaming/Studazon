package com.studazon.portal.util;

import com.studazon.portal.entity.Book;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class Mailer {
    protected static Properties props;
    private static String MAIL_FROM = null;
    private static String MAIL_PORT = null;
    private static String MAIL_HOST = null;
    private static String MAIL_USR = null;
    private static String MAIL_PASS = null;
    protected String buyer_name;
    protected String buyer_email;
    protected String to_name;
    protected String to_email;
    protected String type_of_email;
    protected String notes;

    protected Book book;

    public Mailer() {
        props = new Properties();
        try {
            props.load(Mailer.class.getClassLoader().getResourceAsStream("mail.properties"));
            MAIL_USR = props.getProperty("mail.username");
            MAIL_HOST = props.getProperty("mail.host");
            MAIL_PASS = props.getProperty("mail.password");
            MAIL_PORT = props.getProperty("mail.port");
            MAIL_FROM = props.getProperty("mail.from");
        } catch (IOException e) {
            System.out.println("Error loading mail.properties file: " + e);
            throw new RuntimeException(e);
        }
        System.out.println("Mailer initialized");
    }

    public static Properties getProps() {
        return props;
    }

    public static void setProps(Properties props) {
        Mailer.props = props;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getBuyer_email() {
        return buyer_email;
    }

    public void setBuyer_email(String buyer_email) {
        this.buyer_email = buyer_email;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public String getTo_email() {
        return to_email;
    }

    public void setTo_email(String to_email) {
        this.to_email = to_email;
    }

    public String getType_of_email() {
        return type_of_email;
    }

    public void setType_of_email(String type_of_email) {
        this.type_of_email = type_of_email;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void send() throws MessagingException, IOException {
        String subject = "";
        String body = "";
        String ff = "font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;";
        switch (type_of_email) {
            case "new_account":
                subject += "Welcome to Studazon!";
                body += "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<title>Welcome to Studazon</title>\n" +
                        "</head>\n" +
                        "<body style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 1.6; color: #333; background-color: #f9f9f9;\">\n" +
                        "\t<h1 style=\"color: #F29D4D; font-size: 28px; font-weight: bold; margin-top: 30px; margin-bottom: 10px;\">Welcome to Studazon!</h1>\n" +
                        "\t<h3 style=\"font-size: 20px; color: #666; margin-top: 20px; margin-bottom: 10px; font-weight: normal;\">Dear " + to_name + ",</h3>\n" +
                        "\t<p>We are thrilled to have you join our community of book lovers and sellers! At Studazon, we are committed to providing the best possible experience for our users.</p>\n" +
                        "\t<p>To get started, here are a few things you can do:</p>\n" +
                        "\t<ul>\n" +
                        "\t\t<li>Upload your book collection to our platform</li>\n" +
                        "\t\t<li>Browse and search for books you want to buy</li>\n" +
                        "\t\t<li>Connect with other book enthusiasts and sellers</li>\n" +
                        "\t\t<li>Explore our features and settings to customize your experience</li>\n" +
                        "\t</ul>\n" +
                        "\t<h4 style=\"font-size: 20px; color: #666; margin-top: 20px; margin-bottom: 10px; font-weight: normal;\">As always, if you have any questions or feedback, don't hesitate to reach out to our support team.</h4>\n" +
                        "\t<a href=\"http://18.206.192.122:8080/studazon/\" class=\"cta-button\" style=\"display: inline-block; padding: 10px 15px; background-color: #41B883; color: #fff; border-radius: 4px; text-decoration: none; margin-top: 20px; font-weight: bold; text-align: center;\">Get started now</a>\n" +
                        "\t<p>Happy reading!</p>\n" +
                        "\t<h4 style=\"font-size: 20px; color: #666; margin-top: 20px; margin-bottom: 10px; font-weight: normal;\">The Studazon Team</h4>\n" +
                        "</body>\n" +
                        "</html>";
                break;
            case "book_interest":
                subject += "Someone is interested in your book, " + book.getTitle();
                body += "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<title>" + book.getTitle() + "</title>\n" +
                        "</head>\n" +
                        "<body style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 1.6; color: #333; background-color: #f9f9f9;\">\n" +
                        "\t<h1 style=\"color: #D7B358; font-size: 28px; font-weight: bold; margin-top: 30px; margin-bottom: 10px;\">" + book.getTitle() + "</h1>\n" +
                        "\t<h3 style=\"font-size: 20px; color: #666; margin-top: 20px; margin-bottom: 10px; font-weight: normal;\">Hello " + to_name + ",</h3>\n" +
                        "\t<p><b>" + buyer_name + " is interested in purchasing this book.</b></p>\n" +
                        "\t<p><b>Here is their information:</b></p>\n" +
                        "\t<p style=\"color: #7bb97b;\"><i>Email - " + buyer_email + "</i><p>\n" +
                        "\t<h4 style=\"font-size: 20px; color: #666; margin-top: 20px; margin-bottom: 10px; font-weight: normal;\">As always, if you have any questions or feedback, don't hesitate to reach out to our support team.</h4>\n" +
                        "\t<a href=\"http://18.206.192.122:8080/studazon/listing\" class=\"cta-button\" style=\"display: inline-block; padding: 10px 15px; background-color: #6fc2d9; color: #fff; border-radius: 4px; text-decoration: none; margin-top: 20px; font-weight: bold; text-align: center;\">View your listings</a>\n" +
                        "\t<p>Happy selling!</p>\n" +
                        "\t<h4 style=\"font-size: 20px; color: #666; margin-top: 20px; margin-bottom: 10px; font-weight: normal;\">The Studazon Team</h4>\n" +
                        "</body>\n" +
                        "</html>";
                break;
            case "book_interest_notes":
                subject += "Someone is interested in your book, " + book.getTitle();
                body += "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<title>" + book.getTitle() + "</title>\n" +
                        "</head>\n" +
                        "<body style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 1.6; color: #333; background-color: #f9f9f9;\">\n" +
                        "\t<h1 style=\"color: #D7B358; font-size: 28px; font-weight: bold; margin-top: 30px; margin-bottom: 10px;\">" + book.getTitle() + "</h1>\n" +
                        "\t<h3 style=\"font-size: 20px; color: #666; margin-top: 20px; margin-bottom: 10px; font-weight: normal;\">Hello " + to_name + ",</h3>\n" +
                        "\t<p><b>" + buyer_name + " is interested in purchasing this book.</b></p>\n" +
                        "\t<p><b>Here is their information:</b></p>\n" +
                        "\t<p style=\"color: #7bb97b;\"><i>Email - " + buyer_email + "</i><p>\n" +
                        "\t<p><b>Message from the buyer: </b></p>\n" +
                        "\t<p style=\"color: #7bb97b;\"><i>" + notes + "</i></p>\n" +
                        "\t<h4 style=\"font-size: 20px; color: #666; margin-top: 20px; margin-bottom: 10px; font-weight: normal;\">As always, if you have any questions or feedback, don't hesitate to reach out to our support team.</h4>\n" +
                        "\t<a href=\"http://18.206.192.122:8080/studazon/listing\" class=\"cta-button\" style=\"display: inline-block; padding: 10px 15px; background-color: #6fc2d9; color: #fff; border-radius: 4px; text-decoration: none; margin-top: 20px; font-weight: bold; text-align: center;\">View your listings</a>\n" +
                        "\t<p>Happy selling!</p>\n" +
                        "\t<h4 style=\"font-size: 20px; color: #666; margin-top: 20px; margin-bottom: 10px; font-weight: normal;\">The Studazon Team</h4>\n" +
                        "</body>\n" +
                        "</html>";
                break;

        }

        // Set up mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", MAIL_HOST);
        props.put("mail.smtp.port", MAIL_PORT);


        // Get Session object with authentication credentials
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_USR, MAIL_PASS);
            }
        });

        try {
            // Create a new message
            Message emailMessage = new MimeMessage(session);

            // Set sender and recipient addresses
            emailMessage.setFrom(new InternetAddress(MAIL_FROM));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email));

            // Set message subject and content
            emailMessage.setSubject(subject);
            emailMessage.setContent(body, "text/html; charset=utf-8");

            // Send the message
            Transport.send(emailMessage);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
        }

    }
}
