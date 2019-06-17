import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.*;

public class QASectionPage extends PageObject{
    private final String pageURL = "https://www.wrike.com/resend/";
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(xpath = ".//span[@class=\"switch__additional\"]/input")
    private WebElement printAnswer;

    @FindBy(xpath = ".//div[@class=\"wg-grid\"]/div[1]/p/button[contains(text(), 'Resend email')]")
    private WebElement resendButton;

    @FindBy(xpath = ".//form[@class=\"survey-form\"]/button")
    private WebElement submitButton;

    public QASectionPage(WebDriver driver) {
        super(driver);
    }

    QASectionPage() {
    }

    private void fillQuestion(String keywords, boolean needText){
        List<WebElement> answers = driver.findElements(By.xpath(".//form[@class=\"survey-form\"]//*[contains(text(), " +
                keywords + "]/label/button"));
        Random gen = new Random();
        WebElement answer = answers.get(gen.nextInt(answers.size()));
        answer.click();
        if (needText) {
            printAnswer.sendKeys(StringOperation.generateRandomString(5));
        }
    }

    void fillTheForm(){
        fillQuestion("interest_in_solution", false);
        fillQuestion("team_members", false);
        fillQuestion("primary_business", true);
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//img[@class=\"mailbox\"]")));
    }

    void resendResult(){
        resendButton.click();
        try{
            wait.until(ExpectedConditions.invisibilityOf(resendButton));
        }catch (RuntimeException e){
            System.err.println("runtime exceeded");
        }
        assertFalse(resendButton.isDisplayed());
    }

}
