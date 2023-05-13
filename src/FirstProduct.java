import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FirstProduct {

    // Declaration
    @FindBy(xpath = "//button[@aria-label='Close form 1']")
//    @FindBy(xpath = "//*[name()='circle' and contains(@cx,'10')]")
    private WebElement close;

    @FindBy(xpath = "(//span[contains(text(),'TRY NOW')])[2]")
//    @FindBy(xpath = "//a[normalize-space()='TRY NOW']")
    private WebElement tryNow;

    @FindBy(xpath = "//a[normalize-space()='BUY NOW']")
//    @FindBy(xpath = "//div[contains(text(),'Pay Only $8.95')]")
    private WebElement buyNow;

    @FindBy(xpath = "//input[@id='buy_now']")
    //@FindBy(xpath = "//div[contains(text(),'YES! UPGRADE MY ORDER')]")
//    @FindBy(linkText = "No, thanks, I don't need the Immunity Boost.")
    private WebElement yesUpgrade;

    @FindBy(xpath = "//input[@id='billing_email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='billing_first_name']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='billing_last_name']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@id='billing_address_1']")
    private WebElement address;

    @FindBy(xpath = "//input[@id='billing_city']")
    private WebElement city;

//    @FindBy(xpath = "//span[@class='select2 select2-container select2-container--default select2-container--above select2-container--focus']//span[@role='presentation']")
    @FindBy(xpath = "//span[@id='select2-billing_state-container']")
    private WebElement state;

    @FindBy(xpath = "//input[@role='combobox']")
    private WebElement search;

    @FindBy(xpath = "//li[@id='select2-billing_state-result-mwiz-NY']")
    private WebElement newYork;

    @FindBy(xpath = "//input[@id='billing_postcode']")
    private WebElement zipcode;

    @FindBy(xpath = "//input[@id='billing_phone']")
    private WebElement phone;

    @FindBy(xpath = "//input[@id='WC_Gateway_Worldpay-card-number']")
    private WebElement cardNumber;

    @FindBy(xpath = "//input[@id='WC_Gateway_Worldpay-card-expiry']")
    private WebElement validationDate;

    @FindBy(xpath = "//input[@id='WC_Gateway_Worldpay-card-cvc']")
    private WebElement cvvNumber;

    @FindBy(xpath = "//button[@id='place_order']")
    private WebElement placeOrder;

    // Initialization
    public FirstProduct(WebDriver driver){
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

    public void setEmail(){
        email.sendKeys("nishan2@yopmail.com");
    }

    public void setFirstName(){
        firstName.sendKeys("Woo");
    }

    public void setLastName(){
        lastName.sendKeys("Test");
    }

    public void setAddress(){
        address.sendKeys("New York");
    }

    public void setTown(){
        city.sendKeys("New York");
    }

    public void setState(){
        state.click();
    }

    public void searchState(){
        search.sendKeys("New York");
    }

    public void getState(){
        newYork.click();
    }

    public void setZipcode(){
        zipcode.sendKeys("10030");
    }

    public void setPhone(){
        phone.sendKeys("7001198674");
    }

    public void setCardNumber(){
        cardNumber.sendKeys("4111 1111 1111 1111");
    }

    public void setValidationDate(){
        validationDate.sendKeys("05 / 30");
    }

    public void setCvvNumber(){
        cvvNumber.sendKeys("456");
    }

    public void setPlaceOrder(){
        placeOrder.submit();
    }

}
