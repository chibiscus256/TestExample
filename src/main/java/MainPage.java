
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
class MainPage extends PageObject{

    @FindBy(xpath=".//div[@class=\"r\"]/form/button[@class=\"wg-header__free-trial-button wg-btn wg-btn--green\"]")
    WebElement getJoinButton;

    @FindBy(xpath=".//label[@class=\"modal-form-trial__label\"]/input")
    private WebElement email;

    @FindBy(xpath = ".//label[@class=\"modal-form-trial__label\"]/button")
    private WebElement createButton;

    MainPage() {
    }

    MainPage(WebDriver driver) {
        super(driver);
    }

    void createAnAccount() {
        getJoinButton.click();
        switchToWindow();
        email.sendKeys(StringOperation.generateRandomString(5) + "wpt@wriketask.qaa");
        createButton.click();
    }
}
