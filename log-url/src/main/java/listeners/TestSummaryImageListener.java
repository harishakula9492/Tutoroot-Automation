package listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.*;
import javax.mail.internet.*;

import javax.imageio.ImageIO;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestSummaryImageListener implements IReporter {

    private File generatedImageFile;

    @Override
    public void generateReport(
            List<XmlSuite> xmlSuites,
            List<ISuite> suites,
            String outputDirectory) {

        int total = 0, passed = 0, failed = 0, skipped = 0;

        for (ISuite suite : suites) {
            for (ISuiteResult sr : suite.getResults().values()) {
                ITestContext ctx = sr.getTestContext();
                total += ctx.getPassedTests().size()
                        + ctx.getFailedTests().size()
                        + ctx.getSkippedTests().size();
                passed += ctx.getPassedTests().size();
                failed += ctx.getFailedTests().size();
                skipped += ctx.getSkippedTests().size();
            }
        }

        // 1️⃣ Create Report Folder
        File folder = new File("test-report");
        if (!folder.exists()) folder.mkdirs();

        // 2️⃣ Generate PNG Image
        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        generatedImageFile = new File(folder + "/TestSummary_" + ts + ".png");

        createSummaryImage(total, passed, failed, skipped, generatedImageFile);

        // 3️⃣ Send Email with attachment
        sendEmailWithAttachment(
                "harishakula1996@gmail.com",   // FROM
                "llqq mdut acgy hpoi",          // Gmail App Password
                "harishakula9492@gmail.com",   // TO
                generatedImageFile
        );

        System.out.println("📧 Email sent with test summary image.");
    }

    /** Generate Summary Image */
    private void createSummaryImage(int total, int passed, int failed, int skipped, File file) {
        try {
            int width = 800, height = 500;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();

            g.setColor(new Color(245, 245, 245));
            g.fillRect(0, 0, width, height);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("TEST EXECUTION SUMMARY", 180, 60);

            g.setFont(new Font("Arial", Font.PLAIN, 26));
            g.drawString("Total Tests   : " + total, 100, 150);

            g.setColor(new Color(46, 204, 113));
            g.drawString("Passed        : " + passed, 100, 220);

            g.setColor(new Color(231, 76, 60));
            g.drawString("Failed        : " + failed, 100, 290);

            g.setColor(new Color(241, 196, 15));
            g.drawString("Skipped       : " + skipped, 100, 360);

            g.setColor(Color.GRAY);
            g.setFont(new Font("Arial", Font.ITALIC, 20));
            g.drawString("Generated on: " + new Date(), 100, 430);

            g.dispose();
            ImageIO.write(image, "PNG", file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Email Sender */
    private void sendEmailWithAttachment(
            String from,
            String password,
            String to,
            File attachment) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject("Automation Test Summary Report");

            BodyPart textPart = new MimeBodyPart();
            textPart.setText(
                    "Hi,\n\nPlease find the attached Test Summary Report.\n\nRegards,\nAutomation Framework"
            );

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setDataHandler(new DataHandler(new FileDataSource(attachment)));
            attachmentPart.setFileName(attachment.getName());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            msg.setContent(multipart);

            Transport.send(msg);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
