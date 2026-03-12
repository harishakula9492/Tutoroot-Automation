package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /* ===== COMMON LOADERS ===== */
    private By loader = By.cssSelector(".loader, .spinner, .overlay");

    public BasePage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    /* ===== GENERIC WAITS ===== */

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        waitForLoaderToDisappear();
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /* ===== WAIT FOR LOADER (SAFE) ===== */

    protected void waitForLoaderToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception ignored) {
            // Loader not present – safe to continue
        }
    }

    /* ===== COMMON CLICK METHOD (RETRY + STALE SAFE) ===== */

    protected void click(By locator) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                waitForClickable(locator).click();
                waitAfterClick();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException(
                "Unable to click element due to stale reference: " + locator
        );
    }

    /* ===== TYPE METHOD ===== */

    protected void type(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /* ===== DROPDOWN SELECTION ===== */

    protected void selectByVisibleText(By locator, String visibleText) {
        WebElement dropdown = waitForVisible(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    /* ===== OPTIONAL HARD WAIT ===== */

    protected void waitAfterClick() {
        try {
            Thread.sleep(3000); // UI settle time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
