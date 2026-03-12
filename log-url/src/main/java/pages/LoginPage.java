package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailField = By.xpath("//input[@type='email']");
    private By passwordField = By.xpath("//input[@type='password']");
    private By loginButton = By.xpath("//button[@id='login_button']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    protected void clickElement(By locator) {
        waitForVisible(locator).click();
        waitAfterClick();
    }

    
    /* ===== COMMON CLICK METHOD ===== */
   

    protected void waitAfterClick() {
		// TODO Auto-generated method stub
		
	}

	public boolean isLoginPageLoaded() {
        return waitForVisible(loginButton).isDisplayed();
    }

    public void enterEmail(String email) {
        waitForVisible(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public String getEnteredEmail() {
        return driver.findElement(emailField).getAttribute("value");
    }

    public void enterPassword(String password) {
        waitForVisible(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        waitForVisible(loginButton).click();
        waitAfterClick();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
}
