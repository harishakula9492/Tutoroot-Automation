package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestsPage;
import utils.DriverManager;

public class AddTestTest {

    @Test
    public void verifyAddTestPopupOpens() {

        TestsPage testsPage =
                new TestsPage(DriverManager.getDriver());

        testsPage.clickAddTest();
            
    }

    @Test
    public void createTestWithValidData() {

        TestsPage testsPage =
                new TestsPage(DriverManager.getDriver());

        testsPage.clickAddTest();
        testsPage.selectStudent("Student A");
        testsPage.selectCourse("Grade 8");
        testsPage.selectSubject("Physics");
        testsPage.selectChapter("Force");
        testsPage.enterDeadline("25-12-2026");
        testsPage.enterNoOfQuestions("10");
        testsPage.enterTotalMarks("50");
        testsPage.submitTest();

        Assert.assertTrue(
                DriverManager.getDriver().getPageSource().contains("Test created"),
                "Test not created successfully"
        );
    }

    @Test
    public void submitWithoutMandatoryFields() {

        TestsPage testsPage =
                new TestsPage(DriverManager.getDriver());

        testsPage.clickAddTest();
        testsPage.submitTest();

        Assert.assertTrue(
                DriverManager.getDriver().getPageSource().contains("required"),
                "Validation messages not displayed"
        );
    }
}
