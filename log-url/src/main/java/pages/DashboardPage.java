package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
	
	/* ===== NOTIFICATION POPUP ===== */
    private By laterButton = By.xpath("//button[normalize-space()='Later']");

    /* ===== DASHBOARD LOAD CHECK ===== */
   
   /* ===== LEFT MENU (STABLE LOCATORS) ===== */   
    private By coursesMenu = By.xpath("//p[normalize-space()='Courses']");
    private By testsMenu = By.xpath("//button[contains(@class,'tablinks flex-1 p-3 w-full font-medium whitespace-nowrap')]//p[contains(text(),'Tests')]");
    private By assignmentsMenu = By.xpath("//button[contains(@class,'tablinks flex-1 p-3 w-full font-medium whitespace-nowrap')]//p[contains(text(),'Assignments')]");
    private By sessionsMenu = By.xpath("//button[contains(@class,'tablinks flex-1 p-3 w-full font-medium whitespace-nowrap')]//p[contains(text(),'Sessions')]");
    private By studentProgressMenu = By.xpath("//p[normalize-space()='Student Progress']");
    private By leavesSlotsMenu = By.xpath("//p[normalize-space()='Leaves & Slots']");
    private By payslipsMenu = By.xpath("//p[normalize-space()='Payslips']");
    private By paymentdescripancyMenu = By.xpath("//p[normalize-space()='Payments Discrepancy']");
    private By calendarMenu = By.xpath("//p[normalize-space()='Calendar']");
    
    public DashboardPage(WebDriver driver) {
        super(driver);
        
    }
    
    /* ===== HANDLE POPUP IF PRESENT ===== */
    private void handleNotificationPopupIfPresent() {
        try {
            if (!driver.findElements(laterButton).isEmpty()) {
                waitForVisible(laterButton).click();
               // waitAfterClick();
            }
        } catch (Exception ignored) {
        }
        
        
    }
    /* ===== COMMON CLICK + WAITER 
    private void clickMenu(By locator) {
        waitForClickable(locator).click();
        waitAfterClick();   // 👈 waiter after every click
    } ===== */
    
    /* ===== COMMON CLICK METHOD ===== */
    protected void clickMenu(By locator) {

        // 🔥 Handle popup before any menu click
        handleNotificationPopupIfPresent();

        waitForVisible(locator).click();
        waitAfterClick();
    }


    /* ===== MENU ACTIONS ===== */
    

    public void clickCourses() {
        clickMenu(coursesMenu);
    }

    public void clickTests() {
        clickMenu(testsMenu);
    }

    public void clickAssignments() {
        clickMenu(assignmentsMenu);
    }

    public void clickSessions() {
        clickMenu(sessionsMenu);
    }

    public void clickStudentProgress() {
        clickMenu(studentProgressMenu);
    }

    public void clickLeavesAndSlots() {
        clickMenu(leavesSlotsMenu);
    }

    public void clickPayslips() {
        clickMenu(payslipsMenu);
    }
        
        public void clickpaymentdescripancy() {
            clickMenu(paymentdescripancyMenu);
                     
    }
        public void clickCalendar() {
            clickMenu(calendarMenu);
     }
}

