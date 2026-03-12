package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenShotListener implements ITestListener {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            var driver = DriverManager.getDriver();
            if (driver == null) return;

            File screenshotsDir = Path.of("test-output", "screenshots").toFile();
            if (!screenshotsDir.exists()) Files.createDirectories(screenshotsDir.toPath());

            String fileName = result.getMethod().getMethodName() + "_" + LocalDateTime.now().format(formatter) + ".png";
            File out = screenshotsDir.toPath().resolve(fileName).toFile();

            // Selenium 4: getScreenshotAs
            var srcFile = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
            Files.copy(srcFile.toPath(), out.toPath());

            // attach path as test attribute for reports if needed
            result.setAttribute("screenshot", out.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // other overrides left empty (optional)
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
