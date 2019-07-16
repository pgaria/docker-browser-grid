package com.demo.docker.tests;

import com.demo.docker.browser.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DockerDemoTest {

    @DataProvider(name = "browserProvider", parallel=true)
    public Object[][] browserProvider() {
        return new Object[][] {
                new Object[] { "chrome","75.0"},
                new Object[] { "chrome","74.0" },
                new Object[] { "chrome","73.0" },
                new Object[] { "firefox","68.0" },
                new Object[] { "firefox","67.0" },
                new Object[] { "firefox","66.0" }
        };
    }

    @Test(dataProvider = "browserProvider")
    public void runTestOnDocker(String browser,String version) throws Exception {

        WebDriverProvider driverProvider = new WebDriverProvider();
        WebDriver driver = driverProvider.getDriver(browser,version);
        // Get URL
        driver.get("https://www.google.com/");
        Thread.sleep(10000);
        // Print Title
        System.out.println(driver.getTitle());
    }

   /* @Test
    public void runTestOnDockerChrome74() throws Exception {

        WebDriverProvider driverProvider = new WebDriverProvider();
        WebDriver driver = driverProvider.getDriver("chrome","74.0");

        // Get URL
        driver.get("https://www.google.com/");
        // Print Title
        System.out.println(driver.getTitle());
    }
    @Test
    public void runTestOnDockerChrome73() throws Exception {

        WebDriverProvider driverProvider = new WebDriverProvider();
        WebDriver driver = driverProvider.getDriver("chrome","73.0");

        // Get URL
        driver.get("https://www.google.com/");
        // Print Title
        System.out.println(driver.getTitle());
    }
    @Test
    public void runTestOnFirefox68Docker() throws Exception {

        WebDriverProvider driverProvider = new WebDriverProvider();
        WebDriver driver = driverProvider.getDriver("firefox","68.0");

        // Get URL
        driver.get("https://www.google.com/");
        // Print Title
        System.out.println(driver.getTitle());
    }
    @Test
    public void runTestOnFirefox67Docker() throws Exception {

        WebDriverProvider driverProvider = new WebDriverProvider();
        WebDriver driver = driverProvider.getDriver("firefox","67.0");

        // Get URL
        driver.get("https://www.google.com/");
        // Print Title
        System.out.println(driver.getTitle());
    }
    @Test
    public void runTestOnFirefox66Docker() throws Exception {

        WebDriverProvider driverProvider = new WebDriverProvider();
        WebDriver driver = driverProvider.getDriver("firefox","66.0");

        // Get URL
        driver.get("https://www.google.com/");
        // Print Title
        System.out.println(driver.getTitle());
    }*/
}
