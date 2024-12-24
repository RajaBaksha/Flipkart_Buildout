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
            Wrappers wrappers = new Wrappers(driver);

            System.out.println("Start Test Case 01");

            String url = "https://www.flipkart.com/";
            
            //Navigate to url
            wrappers.navigateToURL(url);

            //Search for "Washing Machine"
            wrappers.searchProductAndClick("Washing Machine");
            
            //Sort the products by popularity
            WebElement sortBypopularityEle = driver.findElement(By.xpath("//div[text()='Popularity']"));
            wrappers.clickOnElement(sortBypopularityEle);
            Thread.sleep(4000);

            //Get the count of products having rating less than or equal to 4
            int totalNumberOfProducts = wrappers.numberOfProductswithSpecifiedRatings(4.0);
            System.out.println(totalNumberOfProducts);

            System.out.println("End Test Case 01");
            
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
            Wrappers wrappers = new Wrappers(driver);

            System.out.println("Start Test Case 02");

            String url = "https://www.flipkart.com/";
            
            //Navigate to url
            wrappers.navigateToURL(url);

            //Search for "iPhone"
            wrappers.searchProductAndClick("iPhone");

            //Print title and discount % of all products having discount greater than 17
            wrappers.printTitleandDiscountForProduct(17);

            System.out.println("End Test Case 02");
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
            Wrappers wrappers = new Wrappers(driver);

            System.out.println("Start Test Case 03");

            String url = "https://www.flipkart.com/";
            
            //Navigate to url
            wrappers.navigateToURL(url);

            //Search for "Coffee Mug"
            wrappers.searchProductAndClick("Coffee Mug");

            //Locate and click on 4 star check box
            WebElement fourStarCheckBox = driver.findElement(By.xpath("(//div[contains(@class,'QCKZip ')])[1]"));
            wrappers.clickOnElement(fourStarCheckBox);
            Thread.sleep(4000);

            //Print the title and imageURL of 5 items having highest number of reviews
            List<WebElement> productReviews = driver.findElements(By.xpath("//span[@class = 'Wphh3N']"));
            wrappers.printTitleAndImageUrlForProduct(productReviews,5);

            System.out.println("End Test Case 03");
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