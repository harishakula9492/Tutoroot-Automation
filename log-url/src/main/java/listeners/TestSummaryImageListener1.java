package listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestSummaryImageListener1 implements IReporter {

    @Override
    public void generateReport(
            List<XmlSuite> xmlSuites,
            List<ISuite> suites,
            String outputDirectory) {

        int total = 0;
        int passed = 0;
        int failed = 0;
        int skipped = 0;

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

        // Create folder if missing
        File reportFolder = new File("test-report");
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        }

        // File name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File outputImage = new File(reportFolder + "/TestSummary_" + timestamp + ".png");

        // Generate image
        createSummaryImage(total, passed, failed, skipped, outputImage);

        System.out.println("🔹 Test summary image saved at: " + outputImage.getAbsolutePath());
    }


    private void createSummaryImage(int total, int passed, int failed, int skipped, File file) {
        try {
            int width = 800;
            int height = 500;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();

            // Background color
            g.setColor(new Color(245, 245, 245));
            g.fillRect(0, 0, width, height);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("TEST EXECUTION SUMMARY", 180, 60);

            g.setFont(new Font("Arial", Font.PLAIN, 26));
            g.drawString("Total Tests   : " + total, 100, 150);

            g.setColor(new Color(46, 204, 113)); // green
            g.drawString("Passed        : " + passed, 100, 220);

            g.setColor(new Color(231, 76, 60)); // red
            g.drawString("Failed        : " + failed, 100, 290);

            g.setColor(new Color(241, 196, 15)); // yellow
            g.drawString("Skipped       : " + skipped, 100, 360);

            // Footer timestamp
            g.setColor(Color.GRAY);
            g.setFont(new Font("Arial", Font.ITALIC, 20));
            g.drawString("Generated on: " + new Date(), 100, 430);

            g.dispose();
            ImageIO.write(image, "PNG", file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
