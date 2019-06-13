import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TestWrike{

    @Test
    public void firstTest() throws InterruptedException {

        System.setProperty("webdriner.chrome.driver", "/chromedriver");
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.wrike.com");
        driver.manage().window().maximize();

        WebElement getJoinButton = driver.findElement(By.xpath(".//div[@class=\"r\"]/form/" +
        "button[@class=\"wg-header__free-trial-button wg-btn wg-btn--green\"]"));
        getJoinButton.click();

        String handle = driver.getWindowHandle();
        driver.switchTo().window(handle);

        WebElement email = driver.findElement(By.xpath(".//label[@class=\"modal-form-trial__label\"]/input"));
        email.sendKeys(StringOperation.generateRandomString(5) + "wpt@wriketask.qaa");

        WebElement getCreateButton = driver.findElement(By.xpath(".//label[@class=\"modal-form-trial__label\"]/button"));
        //String startURL = driver.getCurrentUrl();
        getCreateButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlMatches("https://www.wrike.com/resend/"));

        WebElement getQAButton1 = driver.findElement(By.xpath(".//div[@data-code=\"interest_in_solution\"]/label[" +
                StringOperation.getRandomNumber(1, 2) + "]/button"));
        getQAButton1.click();
        WebElement getQAButton2 = driver.findElement(By.xpath(".//div[@data-code=\"team_members\"]/label[" +
                StringOperation.getRandomNumber(1, 5) + "]/button"));
        getQAButton2.click();
        WebElement getQAButton3 = driver.findElement(By.xpath(".//div[@data-code=\"primary_business\"]/label[" +
                StringOperation.getRandomNumber(1, 2) + "]/button"));
        getQAButton3.click();
        getQAButton3.sendKeys(StringOperation.generateRandomString(5));
        WebElement getSubmitButton = driver.findElement(By.xpath(".//form[@class=\"survey-form\"]/button"));
        getSubmitButton.click();

        (new WebDriverWait(driver, 10)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//img[@class=\"mailbox\"]")));

        WebElement getResendButton = driver.findElement(By.xpath(".//div[@class=\"wg-grid\"]/div[1]/p/" +
                "button[contains(text(), 'Resend email')]"));
        getResendButton.click();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOf(getResendButton));
        assertFalse(getResendButton.isDisplayed());


        List<WebElement> textfields = ImmutableList.of(driver.findElement(By.xpath("//div[@class=\"wg-footer__bottom-section-wrapper\"]/descendant::*[@id=\"twitter\"]")));
        assertTrue(driver.findElements(By.id("twitter")).size() != 0);
                                      
        WebElement getTwitterButton = driver.findElement(By.linkText("href=\"https://twitter.com/wrike\""));
        
        driver.quit();
    }
}

