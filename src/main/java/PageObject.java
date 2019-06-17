import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.Assert.assertEquals;
import static org.openqa.selenium.support.PageFactory.*;

class PageObject {
    private static String driverPath = "";
    WebDriver driver;
    WebDriverWait wait;
    String pageURL = "https://www.wrike.com";

    PageObject(WebDriver driver){
        this.driver = driver;
        //this.wait = new WebDriverWait(driver, 10);
    }

    PageObject() {
    }

    void getMainPage(){
        String mainPage = "https://www.wrike.com";
        driver.get(mainPage);
        driver.manage().window().maximize();
    }

    void switchToWindow(){
        String handle = driver.getWindowHandle();
        driver.switchTo().window(handle);
    }

    void waitForLoading(){
        try{
            wait.until(ExpectedConditions.urlMatches(pageURL));
        }catch(RuntimeException e){
            System.err.println("runtime exceeded");
        }
        assertEquals(pageURL, driver.getCurrentUrl());
    }

    static WebDriver initChromeDriver(){
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", driverPath);
        return new ChromeDriver();
    }

    void followUsOn(String socialNetwork){
            WebElement element = driver.findElement(By.xpath(".//a[@href=\"https://" + socialNetwork +
                    ".com/wrike\"]//ancestor::li"));
            assertEquals("https://" + socialNetwork + ".com/wrike", driver.getCurrentUrl());
    }
    public void init(final WebDriver driver) {
        initElements(driver, this);
    }
}
