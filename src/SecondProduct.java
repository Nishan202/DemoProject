import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecondProduct {

    @FindBy(xpath = "(//*[name()='circle'])[12]")
    private WebElement close;

    @FindBy(xpath = "//a[normalize-space()='TRY NOW']")
    private WebElement tryNow;

    @FindBy(xpath = "//div[contains(text(),'SUBSCRIBE AND SAVE')]")
    private WebElement buyNow;

    @FindBy(xpath = "//div[contains(text(),'YES! UPGRADE MY ORDER')]")
    private WebElement yesUpgrade;

    // Initialization
    public SecondProduct(WebDriver driver){
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
}
