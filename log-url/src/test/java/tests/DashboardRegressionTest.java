package tests;

import org.testng.annotations.Test;

import pages.DashboardPage;
import utils.DriverManager;

public class DashboardRegressionTest {

    @Test(priority = 2)
    public void verifyDashboardMenuClicks() {

        DashboardPage dashboard =
                new DashboardPage(DriverManager.getDriver());

        dashboard.clickCourses();
        dashboard.clickTests();
        dashboard.clickAssignments();
        dashboard.clickSessions();
        dashboard.clickStudentProgress();
        dashboard.clickLeavesAndSlots();
        dashboard.clickPayslips();
        dashboard.clickpaymentdescripancy();
        dashboard.clickCalendar();
        
        

    }
}
