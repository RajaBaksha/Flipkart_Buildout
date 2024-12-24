package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
     
     @Test
     public void testCase01() throws InterruptedException
     {
        try
        {
            //driver.get("https://www.flipkart.com/");
            
            // WebElement searchTextBox = driver.findElement(By.xpath("//input[@name='q']"));
            // Wrappers.searchForProduct(searchTextBox, "Washing Machine");
            // Thread.sleep(4000);

            // WebElement searchIcon = driver.findElement(By.xpath("//button[@type='submit']"));
            // searchIcon.click();
            // Thread.sleep(4000);
            String url = "https://www.flipkart.com/";
            
            Wrappers.navigateToURL(driver, url);

            Wrappers.searchProductAndClick(driver, "Washing Machine");
            
            WebElement sortBypopularityEle = driver.findElement(By.xpath("//div[text()='Popularity']"));
            sortBypopularityEle.click();
            Thread.sleep(4000);

            int totalNumberOfProducts = Wrappers.numberOfProductswithSpecifiedRatings(driver, 4.0);
            System.out.println(totalNumberOfProducts);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testCase02() throws InterruptedException
    {
        try
        {
            // driver.get("https://www.flipkart.com/");

            // WebElement searchTextBox = driver.findElement(By.xpath("//input[@name='q']"));
            // Wrappers.searchForProduct(searchTextBox, "iPhone");
            // Thread.sleep(4000);

            // WebElement searchIcon = driver.findElement(By.xpath("//button[@type='submit']"));
            // searchIcon.click();
            // Thread.sleep(6000);
            String url = "https://www.flipkart.com/";
            
            Wrappers.navigateToURL(driver, url);

            Wrappers.searchProductAndClick(driver, "iPhone");

            Wrappers.printTitleandDiscountForProduct(driver,17);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testCase03()
    {
        try
        {
            // driver.get("https://www.flipkart.com/");

            // WebElement searchTextBox = driver.findElement(By.xpath("//input[@name='q']"));
            // Wrappers.searchForProduct(searchTextBox, "Coffee Mug");
            // Thread.sleep(6000);

            // WebElement searchIcon = driver.findElement(By.xpath("//button[@type='submit']"));
            // searchIcon.click();
            // Thread.sleep(6000);

            String url = "https://www.flipkart.com/";
            
            Wrappers.navigateToURL(driver, url);

            Wrappers.searchProductAndClick(driver, "Coffee Mug");

            WebElement fourStarCheckBox = driver.findElement(By.xpath("(//div[contains(@class,'QCKZip ')])[1]"));
            fourStarCheckBox.click();
            Thread.sleep(4000);

            List<WebElement> productReviews = driver.findElements(By.xpath("//span[@class = 'Wphh3N']"));
            Wrappers.printTitleAndImageUrlForProduct(driver,productReviews,5);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        //driver.close();
        driver.quit();

    }
}