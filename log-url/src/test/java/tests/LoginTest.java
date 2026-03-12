package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverManager;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void validLoginTest() {

        LoginPage login = new LoginPage(DriverManager.getDriver());

        // ✅ Assertion 1: Login page loaded
        Assert.assertTrue(
                login.isLoginPageLoaded(),
                "Login page is not loaded"
        );

        // Perform login
        login.login(
                ConfigReader.get("user.email"),
                ConfigReader.get("user.password")
        );

    }
}
