package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties prop;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setUp(String os, String br) throws IOException {

        //Loading config.properties file
        FileReader file = new FileReader("./src//test//resources//config.properties");
        prop = new Properties();
        prop.load(file);

        //log4j2
        logger = LogManager.getLogger(this.getClass());

        //Remote Machine Execution
        if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            //Operating System
            switch (os.toLowerCase())
            {
                case "windows": capabilities.setPlatform(Platform.WIN11); break;
                case "mac": capabilities.setPlatform(Platform.MAC); break;
                case "linux": capabilities.setPlatform(Platform.LINUX); break;
                default: System.out.print("Invalid Operating System"); return;
            }

            //Browser
            switch (br.toLowerCase())
            {
                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "firefox": capabilities.setBrowserName("firefox"); break;
                case "edge": capabilities.setBrowserName("edge"); break;
                case "safari": capabilities.setBrowserName("safari"); break;
                default: System.out.print("Invalid browser"); return;
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        }

        //Local Machine Execution
        if((prop.getProperty("execution_env").equalsIgnoreCase("local")))
        {
            switch (br.toLowerCase())
            {
                case "chrome": driver = new ChromeDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                case "safari": driver = new SafariDriver(); break;
                case "firefox": driver = new FirefoxDriver(); break;
                default: System.out.print("Invalid browser name"); return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("appURL"));
        driver.manage().window().maximize();

    }


    @AfterClass(groups = {"Sanity", "Regression", "Master", "DataDriven"})
    public void tearDown()
    {
        driver.quit();

    }

    public String randomString(){
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomNumber(){
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomAlphanumeric(){
        String generatedAlphanumeric = RandomStringUtils.randomAlphanumeric(10);
        return generatedAlphanumeric + "$";
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"/screenshots//" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;




    }

}



