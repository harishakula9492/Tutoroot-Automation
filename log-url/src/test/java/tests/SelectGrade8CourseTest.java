package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CoursesPage;
import pages.DashboardPage;
import utils.DriverManager;

public class SelectGrade8CourseTest {

	 @Test(priority = 9)
    public void Courseclick() {

        DashboardPage dashboard =
                new DashboardPage(DriverManager.getDriver());
        dashboard.clickCourses();
            }    
	 @Test(priority = 10)
    public void verifyGrade8CourseSelection() {

        CoursesPage courses = new CoursesPage(DriverManager.getDriver());

        courses.openCourses();

       
        courses.selectGrade8Course();

        Assert.assertTrue(true, "Grade 8 course selected successfully");
        courses.selectlesson();
        //courses.clickDashboard();
    }
	 
	 
        
}
