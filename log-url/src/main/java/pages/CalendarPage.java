package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CalendarPage extends BasePage {

    // View buttons
    private By monthViewBtn = By.xpath("//button[normalize-space()='month']");
    private By weekViewBtn  = By.xpath("//button[normalize-space()='week']");
    private By dayViewBtn   = By.xpath("//button[normalize-space()='day']");

    // Navigation
    private By todayBtn     = By.xpath("//button[normalize-space()='today']");
    private By nextBtn      = By.xpath("//span[@class='fc-icon fc-icon-chevron-right']");
    private By prevBtn      = By.xpath("//span[@class='fc-icon fc-icon-chevron-left']");

    // Calendar data
    private By calendarTitle = By.cssSelector(".fc-toolbar-title");
    private By sessions      = By.cssSelector(".fc-event");
   // private By leaveLabel    = By.xpath("//span[contains(text(),'On Leave')]");

    public CalendarPage(WebDriver driver) {
        super(driver);
    }
    
    protected void clickElement(By locator) {
        waitForVisible(locator).click();
        waitAfterClick();
    }
    
    /* ===== COMMON CLICK METHOD ===== */
 

    public void clickMonthView() {
        driver.findElement(monthViewBtn).click();
    }

    public void clickWeekView() {
        driver.findElement(weekViewBtn).click();
    }

    public void clickDayView() {
        driver.findElement(dayViewBtn).click();
    }

    public void clickToday() {
        driver.findElement(todayBtn).click();
    }

    public void clickNext() {
        driver.findElement(nextBtn).click();
    }

    public void clickPrevious() {
        driver.findElement(prevBtn).click();
    }

    public String getCalendarTitle() {
        return driver.findElement(calendarTitle).getText();
    }

    public List<WebElement> getSessions() {
        return driver.findElements(sessions);
    }

    public boolean isLeaveDisplayed() {
        return driver.findElements(leaveLabel).size() > 0;
    }
}
