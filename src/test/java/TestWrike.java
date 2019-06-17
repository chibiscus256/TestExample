import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertFalse;

public class TestWrike{

        @Test
        public void firstTest() throws InterruptedException {

            System.setProperty("chrome.ChromeDriver", "src/main/resources/chromedriver");
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
            getCreateButton.click();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.urlMatches("https://www.wrike.com/resend/"));

            WebElement getQAButton1 = driver.findElement(By.xpath(".//div[@data-code=\"interest_in_solution\"]/label[@for=\"interest_in_solution_1\" | @for=\"interest_in_solution_2\"]/button"));
            getQAButton1.click();
            WebElement getQAButton2 = driver.findElement(By.xpath(".//div[@data-code=\"team_members\"]/label[" +
                    StringOperation.getRandomNumber(1, 5) + "]/button"));
            getQAButton2.click();
            String num = StringOperation.getRandomNumber(1, 3);
            WebElement getQAButton3 = driver.findElement(By.xpath(".//div[@data-code=\"primary_business\"]/label[" +
                    num + "]/button"));
            getQAButton3.click();
            if (num.equals("3")){
                WebElement printAnswer = driver.findElementByXPath(".//span[@class=\"switch__additional\"]/input");
                printAnswer.sendKeys(StringOperation.generateRandomString(5)+"");
            }
            WebElement getSubmitButton = driver.findElement(By.xpath(".//form[@class=\"survey-form\"]/button"));
            getSubmitButton.click();

            (new WebDriverWait(driver, 10)).until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath(".//img[@class=\"mailbox\"]")));

            WebElement getResendButton = driver.findElement(By.xpath(".//div[@class=\"wg-grid\"]/div[1]/p/" +
                    "button[contains(text(), 'Resend email')]"));
            getResendButton.click();
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOf(getResendButton));
            assertFalse(getResendButton.isDisplayed());
            try {
                WebElement getTwitter = driver.findElement(By.xpath(".//a[@href=\"https://twitter.com/wrike\"]//ancestor::li"));
                getTwitter.click();
                assertFalse(getResendButton.isDisplayed());
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.err.println("incorrect or invalid link");
            }
            sleep(5000);
            driver.quit();
        }
    }