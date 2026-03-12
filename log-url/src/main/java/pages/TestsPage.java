package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestsPage extends BasePage {
	
	// Buttons
    private By addTestButton = By.xpath("//button[normalize-space()='Add Test']");
    private By submitButton = By.xpath("//button[normalize-space()='Submit']");
    private By closeButton = By.xpath("//button[contains(@class,'close')]");

    // Dropdowns
    private By studentDropdown = By.xpath("//label[text()='Student']/following::select[1]");
    private By courseDropdown = By.xpath("//label[text()='Course']/following::select[1]");
    private By subjectDropdown = By.xpath("//label[text()='Subject']/following::select[1]");
    private By chapterDropdown = By.xpath("//label[text()='Chapter']/following::select[1]");

    // Inputs
    private By deadlineDate = By.xpath("//input[@placeholder='dd-mm-yyyy']");
    private By noOfQuestions = By.xpath("//input[@placeholder='No of questions']");
    private By totalMarks = By.xpath("//input[@placeholder='Total Marks']");

    public TestsPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddTest() {
        click(addTestButton);
    }

    public void selectStudent(String student) {
        selectByVisibleText(studentDropdown, student);
    }

    public void selectCourse(String course) {
        selectByVisibleText(courseDropdown, course);
    }

    public void selectSubject(String subject) {
        selectByVisibleText(subjectDropdown, subject);
    }

    public void selectChapter(String chapter) {
        selectByVisibleText(chapterDropdown, chapter);
    }

    public void enterDeadline(String date) {
        type(deadlineDate, date);
    }

    public void enterNoOfQuestions(String value) {
        type(noOfQuestions, value);
    }

    public void enterTotalMarks(String value) {
        type(totalMarks, value);
    }

    public void submitTest() {
        click(submitButton);
    }

    public void closePopup() {
        click(closeButton);
    }
}
