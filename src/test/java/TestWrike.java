import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.Document;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

public class TestWrike{

    @Test
    public void firstTest() {

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
        email.sendKeys(StringOperation.generateRandomString(3) + "wpt@wriketask.qaa");

        WebElement getCreateButton = driver.findElement(By.xpath(".//label[@class=\"modal-form-trial__label\"]/button"));
        //String startURL = driver.getCurrentUrl();
        getCreateButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.urlMatches("https://www.wrike.com/resend/"));

        //WebElement getQAButton = driver.findElement(By.xpath)
    }
}

