package com.demo.docker.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverProvider {

        private String operatingSystem = System.getProperty("os.name").toLowerCase();

        /**
         * Get driver Instance for the Browser and version.
         * Note: for Local browser Instance the version is not used as Local Instance will be used. browser will be used in Remote Instance.
         *
         * @param browser
         * @param version
         * @return
         */
        public WebDriver getDriver(String browser, String version) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.acceptInsecureCerts();
            WebDriver driver;
            System.out.println("The OS found is: "+ operatingSystem);
            if (isWindows()) {
                driver = createDriverForWindows(browser, version);
            } else
                throw new RuntimeException("Can not create Driver Instance for the unknown Operating System : " + operatingSystem);
            return driver;
        }

        /**
         * Is Windows operating System. Checking this to take the proper Driver executables at run time.
         *
         * @return
         */
        private boolean isWindows() {
            return (operatingSystem.indexOf("win") >= 0);
        }

        /**
         * Is Unix operating System. Checking this to take the proper Driver executables at run time.
         *
         * @return
         */
        private boolean isUnix() {
            return (operatingSystem.indexOf("nux") >= 0);
        }

        /**
         * Create WebDriver Instance for the Windows operating System.
         *
         * @param browser
         * @param version
         * @return
         */
        private WebDriver createDriverForWindows(String browser, String version) {
            System.out.println("Creating Driver Instance for the Windows machine.");
            if (browser.equalsIgnoreCase("chrome")) {
                return getChromeDriverInstance(version, "src/main/resources/drivers/chromedriver.exe");
            }else if (browser.equalsIgnoreCase("firefox")) {
                return getFirefoxDriverInstance(version, "src/main/resources/drivers/geckodriver.exe");
            }else
            throw new RuntimeException("No implementation for the FireFox Browser, Please Run Test only for Chrome");
        }


        /**
         * Decide If Running on Local or Remote machine, based on the Config File parameter.
         *
         * @return
         */
        private WebDriver getChromeDriverInstance(String version, String driverPath) {
                System.out.println("Running Test on Remote URL");
                return createChromeDriverRemote(version, driverPath);
        }

        /**
         * Create Chrome Driver Instance for remote Machine Run.
         *
         * @return Return WebDriver Instance for Chrome for Remote Run.
         */
        private WebDriver createChromeDriverRemote(String version, String driverPath) {
            System.out.println("Creating ChromeDriver Instance for Remote URL");
            DesiredCapabilities dcap = DesiredCapabilities.chrome();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("window-size=1400,900");
            chromeOptions.addArguments("--start-maximized");
            dcap.setCapability("enableVNC", true);
            dcap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            dcap.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
            dcap.setCapability(CapabilityType.BROWSER_VERSION, version);
            URL url;
            try {
                String chromeDriverHost = "localhost";
                String chromeDriverPort = "4444";
                url = new URL("http://" + chromeDriverHost + ":" + chromeDriverPort +"/wd/hub");
            } catch (MalformedURLException e) {
                throw new RuntimeException("Remote URL is Wrong, " + e.getCause());
            }
            return new RemoteWebDriver(url, dcap);
        }
    private WebDriver getFirefoxDriverInstance(String version, String driverPath) {
        System.out.println("Creating Firefox Driver Instance for Remote URL");
        DesiredCapabilities dcap = DesiredCapabilities.firefox();
        dcap.setCapability("enableVNC", true);
        dcap.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
        dcap.setCapability(CapabilityType.BROWSER_VERSION, version);
        URL url;
        try {
            url = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Remote URL is Wrong, " + e.getCause());
        }
        return new RemoteWebDriver(url, dcap);
    }


    }


