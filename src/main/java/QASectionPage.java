import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.*;

public class QASectionPage extends PageObject{

    @FindBy(xpath = "//span[@class=\"switch__additional\"]/input")
    private WebElement printAnswer;

    @FindBy(xpath = ".//div[@class=\"wg-grid\"]/div[1]/p/button[contains(text(), 'Resend email')]")
    private WebElement resendButton;

    @FindBy(xpath = ".//form[@class=\"survey-form\"]/button")
    private WebElement submitButton;

    public QASectionPage(WebDriver driver) {
        super(driver);
        this.pageURL = "https://www.wrike.com/resend/";
        wait = new WebDriverWait(driver, 10);
        init(driver);
    }

    QASectionPage() {
    }

    //Заполнение формы предполагается для отдельных страниц
    private void fillQuestion(String keywords){
        List<WebElement> answers = driver.findElements(By.xpath(".//div[@data-code=\"" + keywords + "\"]/label/button"));
        Random gen = new Random();
        WebElement answer = answers.get(gen.nextInt(answers.size()));
        answer.click();
        //Предполагаем, что в поле "Другое" всегда требуется ввести текст ответа
        if (answer.getText().equals("Other")) {
            printAnswer.sendKeys(StringOperation.generateRandomString(5));
        }
    }

    /*Заполняем форму, по вызову fillQuestion на один вопрос*/
    void fillTheForm(){
        fillQuestion("interest_in_solution");
        fillQuestion("team_members");
        fillQuestion("primary_business");
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
