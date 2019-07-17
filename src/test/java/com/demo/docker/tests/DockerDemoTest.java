package com.demo.docker.tests;

import com.demo.docker.browser.WebDriverProvider;
import org.openqa.selenium.By;
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
    public void runTestOnDockerContainers(String browser,String version) throws Exception {
        WebDriver driver = new WebDriverProvider().getDriver(browser,version);

        // Get URL
        driver.get("https://www.google.com/");
        Thread.sleep(3000);

        // Print Title
        System.out.println(driver.getTitle());

        // Enter the Browser Name and Version in search
        driver.findElement(By.cssSelector("[name=q]")).sendKeys(browser+" "+version);
        Thread.sleep(10000);
    }
}
