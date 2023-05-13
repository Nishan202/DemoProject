import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.layertree.model.StickyPositionConstraint;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        String productName1 = "Risk Free Trial Two 30 Scoop Bags";
        String productName2 = "FREE Shaker Cup";
        String productName3 = "FREE Dr Kellyann's Booklet of Recipes";
        String productName4 = "FREE Grown American Vita-Immune Plus (14-Day supply)";

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //String url = "https://grownamerica.in/";
        String url = "https://grownamericansuperfood.com/";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));


        FirstProduct fp = new FirstProduct(driver);
        fp.popupClose();
        fp.orderPage();
        fp.orderFirstProduct();
        fp.orderBump();

        WebElement pn = driver.findElement(By.xpath("//span[normalize-space()='Risk Free Trial Two 30 Scoop Bags']"));
        WebElement pn2 = driver.findElement(By.xpath("//span[normalize-space()='FREE Shaker Cup']"));
        WebElement pn3 = driver.findElement(By.linkText("FREE Dr Kellyann's Booklet of Recipes"));
        WebElement pn4 = driver.findElement(By.xpath("//span[contains(text(),'FREE Grown American Vita-Immune Plus (14-Day suppl')]"));
        String cartProductName = pn.getText();
        String cartProduct2 = pn2.getText();
        String cartProduct3 = pn3.getText();
        String cartProduct4 = pn4.getText();
        //System.out.println(cartProductName);
        if (productName1.equals(cartProductName)){
            System.out.println("Name matched");
        }
        /*
        else if (productName2.equals(cartProduct2)) {
            System.out.println("Name matched");
        } else if (productName3.equals(cartProduct3)) {
            System.out.println("Name matched");
        } else if (productName4.equals(cartProduct4)) {
            System.out.println("Name matched");
        } */
        else {
            System.out.println("Product name doesn't match");
        }

        driver.close();

    }



    private static void firstUpsell(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://grownamericansuperfood.com/");
        driver.findElement(By.xpath("(//*[name()='circle'])[12]")).click();
        driver.findElement(By.xpath("//a[normalize-space()='TRY NOW']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'BUY NOW')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'YES! UPGRADE MY ORDER')]")).click();
        driver.close();
    }

    private static void secondUpsell(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://grownamericansuperfood.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("(//*[name()='circle'])[12]")).click();
        driver.findElement(By.xpath("//a[normalize-space()='TRY NOW']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'SUBSCRIBE AND SAVE')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'YES! UPGRADE MY ORDER')]")).click();
        driver.close();
    }

    private static void thirdUpsell(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://grownamericansuperfood.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("(//*[name()='circle'])[12]")).click();
        driver.findElement(By.xpath("//a[normalize-space()='TRY NOW']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Pay Only $8.95')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'YES! UPGRADE MY ORDER')]")).click();
        driver.close();
    }

}