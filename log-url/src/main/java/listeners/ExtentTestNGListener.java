package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter; // older; still works
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.*;

import utils.DriverManager;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentTestNGListener implements ITestListener {

    private static ExtentReports extent;
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    private synchronized void initReport() {
        if (extent == null) {
            String reportPath = "test-output/extent-report-" + LocalDateTime.now().format(fmt) + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Regression");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));
        }
    }

    @Override
    public void onStart(ITestContext context) {
        initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest t = extent.createTest(result.getMethod().getMethodName());
        testThread.set(t);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().fail(result.getThrowable());

        // attach screenshot if present (from ScreenShotListener)
        Object attr = result.getAttribute("screenshot");
        if (attr != null) {
            String path = attr.toString();
            try { testThread.get().addScreenCaptureFromPath(path); } catch (Exception e) { /* ignore */ }
        } else {
            // try capture now
            try {
                var driver = DriverManager.getDriver();
                if (driver != null) {
                    File src = ((org.openqa.selenium.TakesScreenshot) driver)
                            .getScreenshotAs(org.openqa.selenium.OutputType.FILE);
                    String path = "test-output/screenshots/" + result.getMethod().getMethodName() + "_" + System.currentTimeMillis() + ".png";
                    File dest = new File(path);
                    java.nio.file.Files.copy(src.toPath(), dest.toPath());
                    testThread.get().addScreenCaptureFromPath(dest.getAbsolutePath());
                }
            } catch (Exception e) { /* ignore */ }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) extent.flush();
    }

    // other methods no-op
}
