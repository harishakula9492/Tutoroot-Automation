package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CoursesPage extends BasePage {
	
	
	
	
	 // Dashboard top navigation link
    private By dashboardLink =
            By.xpath("//a[normalize-space()='Dashboard']");
	
	
    //Left menu - Courses
    private By coursesMenu = By.xpath("//body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/div[20]/div[1]/a[1]");

    // Grade 8 Course Card
    private By grade8CourseCard = By.xpath(
        "//div//div//div//div//div//div//div//li[1]//a[1]//div[1]//div[1]//p[1]"
    );
    private By lessoncard = By.xpath("//h2[normalize-space()='Introduction to Force']/ancestor::div[contains(@class,'cursor-pointer') or contains(@class,'rounded')]");
        
    
    

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

   public void openCourses() {
        click(coursesMenu);
    }
    
   
    public void selectGrade8Course() {
        click(grade8CourseCard);   // ✅ SAFE CLICK
    }
    
    public void selectlesson()
    {
    	click(lessoncard);
    }
    

    public void clickDashboard() {
        click(dashboardLink);
    }
}


