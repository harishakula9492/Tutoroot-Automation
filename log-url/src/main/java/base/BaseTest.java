package base;

//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utils.ConfigReader;
import utils.DriverManager;

public class BaseTest {

    @BeforeClass(alwaysRun = true)
    public void setUp() {

        DriverManager.initDriver();

        String baseUrl = ConfigReader.get("base.url");
        DriverManager.getDriver().get(baseUrl);
    }

   
}
