package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    static WebDriverWait wait;
        // public static void searchForProduct(WebElement element, String ProductName)
        // {
        //     try
        //     {
        //         element.click();
        //         element.clear();
        //         element.sendKeys(ProductName);
                
        //     }
        //     catch(Exception e)
        //     {
        //         e.printStackTrace();
        //     }
        // }
        public static void navigateToURL(ChromeDriver driver,String url)
        {
            try
            {
                driver.get(url);
                wait.until(ExpectedConditions.urlContains("flipkart"));
                Thread.sleep(3000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        public static void searchProductAndClick(ChromeDriver driver,String productName) throws InterruptedException
        {
            WebElement searchTextBox = driver.findElement(By.xpath("//input[@name='q']"));
            searchTextBox.click();
            searchTextBox.clear();
            Thread.sleep(3000);
            searchTextBox.sendKeys(productName);
            Thread.sleep(5000);

            WebElement searchIcon = driver.findElement(By.xpath("//button[@type='submit']"));
            Thread.sleep(3000);
            searchIcon.click();
            Thread.sleep(4000);

        }

        public static int numberOfProductswithSpecifiedRatings(ChromeDriver driver, double value)
        {
            try
            {
                int totalCountOfProducts = 0;

                List<WebElement> products = driver.findElements(By.xpath("//div[@class='yKfJKb row']"));

                System.out.println(products.size());

                for(WebElement product:products)
                {
                    WebElement rating = product.findElement(By.xpath(".//div[@class='XQDdHH']"));
  
                    String ratingValue = rating.getText();
                    double dvalue = Double.parseDouble(ratingValue);
                
                    if(dvalue <= value)
                    {
                        totalCountOfProducts+=1;
                    }
                }
                return totalCountOfProducts;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return 0;
            }
        }

        public static void printTitleandDiscountForProduct(ChromeDriver driver,int discount)
        {
            try
            {
                Map<String,Integer> productsTitleAndDiscount = new HashMap<>();
             
                List<WebElement> productsDiscount = driver.findElements(By.xpath("//div[@class='UkUFwK']/span"));

                System.out.println(productsDiscount.size());
                
                for(WebElement product:productsDiscount)
                {
                    String text = product.getText();

                    int discountValue = Integer.parseInt(text.replaceAll("[^\\d]", ""));
                    
                    if(discountValue > discount)
                    {
                        String title = product.findElement(By.xpath("./ancestor::a/descendant::div[@class='KzDlHZ']")).getText();

                        productsTitleAndDiscount.put(title,discountValue);
                        Thread.sleep(5000);
                    }
                }
                for(Map.Entry<String,Integer> productTitleAndDisc : productsTitleAndDiscount.entrySet())
                {
                    System.out.println("Product Title is:" +productTitleAndDisc.getKey()+ "and its Discount is:" +productTitleAndDisc.getValue()+ "% discount");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    public static void printTitleAndImageUrlForProduct(ChromeDriver driver, List<WebElement> productReviews, int n)
    {
        try
        {
            Set<Integer> productsReviewSet = new HashSet<>();
            for (WebElement productReview : productReviews) 
            {
                int reviews = Integer.parseInt(productReview.getText().replaceAll("[^\\d]", ""));
                productsReviewSet.add(reviews);
            }

            List<Integer> productsReviewList = new ArrayList<>(productsReviewSet);
            Collections.sort(productsReviewList, Collections.reverseOrder());
            System.out.println(productsReviewList.size());
            //System.out.println(productsReviewList);

            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            LinkedHashMap<String, String> productDetailsMap = new LinkedHashMap<>();

            for(int i=0;i<n;i++) 
            {
                String formattedUserReviewCount = "(" + numberFormat.format(productsReviewList.get(i)) + ")";
                
                String productTitle = driver.findElement(By.xpath("//span[@class='Wphh3N' and text()='"+formattedUserReviewCount+"']/ancestor::div[contains(@class,'slAVV')]/a[@class='wjcEIp']")).getText();

                String productImageURL = driver.findElement(By.xpath("//span[@class='Wphh3N' and text()='"+formattedUserReviewCount+"']/ancestor::div[contains(@class,'slAVV')]//img[contains(@class,'DByuf')]")).getAttribute("src");

                String highestReviewCountAndProductTitle = String.valueOf(
                        (i + 1) + " Highest review count " + formattedUserReviewCount + " Title is " + productTitle);

                productDetailsMap.put(highestReviewCountAndProductTitle, productImageURL);
                
            } 
            
            for (Map.Entry<String, String> productDetails : productDetailsMap.entrySet()) 
            {
                System.out.println(productDetails.getKey() + " and product image url is " + productDetails.getValue());
                System.out.println("------------------------------------------------------------------------------");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
