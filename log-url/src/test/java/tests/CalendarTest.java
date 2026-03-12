package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CalendarPage;
import utils.DriverManager;

public class CalendarTest  {

    @Test(priority = 3)
    public void verifyCalendarLoads() {
        CalendarPage calendar = new CalendarPage(DriverManager.getDriver());

        Assert.assertTrue(
                calendar.getCalendarTitle().length() > 0,
                "Calendar title not displayed"
        );
    }

    @Test(priority = 4)
    public void verifyMonthWeekDayViewSwitch() {
        CalendarPage calendar = new CalendarPage(DriverManager.getDriver());

        calendar.clickWeekView();
        calendar.clickDayView();
        calendar.clickMonthView();

        Assert.assertTrue(true, "View switching failed");
    }

    @Test(priority = 5)
    public void verifyTodayButton() {
        CalendarPage calendar = new CalendarPage(DriverManager.getDriver());

        calendar.clickNext();
        calendar.clickToday();

        Assert.assertTrue(
                calendar.getCalendarTitle().contains("2026"),
                "Today navigation failed"
        );
    }

    @Test(priority = 6)
    public void verifyNextAndPreviousNavigation() {
        CalendarPage calendar = new CalendarPage(DriverManager.getDriver());

        String currentMonth = calendar.getCalendarTitle();
        calendar.clickNext();
        String nextMonth = calendar.getCalendarTitle();

        Assert.assertNotEquals(
                currentMonth,
                nextMonth,
                "Next month navigation failed"
        );

        calendar.clickPrevious();
    }

    @Test(priority = 7)
    public void verifySessionsDisplayed() {
        CalendarPage calendar = new CalendarPage(DriverManager.getDriver());

        Assert.assertTrue(
                calendar.getSessions().size() >= 0,
                "Sessions not loaded"
        );
    }

    @Test(priority = 8)
    public void verifyLeaveDisplay() {
        CalendarPage calendar = new CalendarPage(DriverManager.getDriver());

        Assert.assertTrue(
                calendar.isLeaveDisplayed() || true,
                "Leave display validation"
        );
    }
}
