import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;
import zmq.socket.reqrep.Rep;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class ThirdProduct {
    public WebDriver driver;

    @BeforeMethod
//    public void demo(){
//        Reporter.log("Hello java", true);
//    }
    public void openApp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://grownamerica.in/";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='circle' and contains(@cx,'10')]")));
//        Robot r = new Robot();
//        r.keyPress(KeyEvent.VK_TAB);
//        r.keyPress(KeyEvent.VK_TAB);
//        r.keyPress(KeyEvent.VK_TAB);
//        r.keyPress(KeyEvent.VK_TAB);
//        r.keyPress(KeyEvent.VK_ENTER);
//        r.keyRelease(KeyEvent.VK_TAB);
//        r.keyRelease(KeyEvent.VK_TAB);
//        r.keyRelease(KeyEvent.VK_TAB);
//        r.keyRelease(KeyEvent.VK_TAB);
//        r.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    public void tc1() throws AWTException {

        FirstProduct fp = new FirstProduct(driver);
        fp.popupClose();
        fp.orderPage();
        fp.orderFirstProduct();
        fp.orderBump();
        fp.setEmail();
        fp.setFirstName();
        fp.setLastName();
        fp.setAddress();
        fp.setTown();
        fp.setState();
        fp.searchState();
//        fp.getState();
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        fp.setZipcode();
        fp.setPhone();
        fp.setCardNumber();
        fp.setValidationDate();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        fp.setCvvNumber();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        fp.setPlaceOrder();


        String productName1 = "Grown American Superfood Vita-Immune Plus 4-week bag";
        WebElement pn = driver.findElement(By.xpath("//td[@class='product-name']"));
        String cartProductName = pn.getText();
        if (productName1.equals(cartProductName)){
            Reporter.log("Product name matched", true);
        }else {
            Reporter.log("Not matched", false);
        }

    }

//    @AfterMethod
//    public void closeApp(){
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
//        driver.close();
//    }

    /*
    @FindBy(xpath = "(//*[name()='circle'])[12]")
    private WebElement close;

    @FindBy(xpath = "//a[normalize-space()='TRY NOW']")
    private WebElement tryNow;

    @FindBy(xpath = "//div[contains(text(),'Pay Only $8.95')]")
    private WebElement buyNow;

    @FindBy(xpath = "//div[contains(text(),'YES! UPGRADE MY ORDER')]")
    private WebElement yesUpgrade;

    // Initialization
    public ThirdProduct(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    // Utilization
    public void popupClose(){
        close.click();
    }

    public void orderPage(){
        tryNow.click();
    }

    public void orderFirstProduct(){
        buyNow.click();
    }

    public void orderBump(){
        yesUpgrade.click();
    }
    */
}
