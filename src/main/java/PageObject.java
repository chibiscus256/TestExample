import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.Assert.assertEquals;

class PageObject {
    WebDriverWait wait;
    WebDriver driver;
    String pageURL;


    PageObject(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        this.pageURL = "https://www.wrike.com/";
        init(driver);
    }

    PageObject() {
    }

    void getMainPage(){
        String mainPage = "https://www.wrike.com/";
        driver.get(mainPage);
        driver.manage().window().maximize();
    }

    void switchToWindow(){
        String handle = driver.getWindowHandle();
        driver.switchTo().window(handle);
    }

    void waitForLoading(){
        try{
            wait.until(ExpectedConditions.urlMatches(this.pageURL));
        }catch(RuntimeException e){
            System.err.println("runtime exceeded");
        }
        assertEquals(this.pageURL, driver.getCurrentUrl());
    }

    void waitForLoading(String URL){
        try{
            wait.until(ExpectedConditions.urlMatches(URL));
        }catch(RuntimeException e){
            System.err.println("runtime exceeded");
        }
        assertEquals(URL, driver.getCurrentUrl());
    }

    /*Не понял, что значит проверить корректность иконки Твиттера. Метод жмет на кнопку и переходит по ссылке, проверяя
    * соотвественно кликабельность кнопки и наличие ссылки*/
    void followUsOn(String socialNetwork){
            WebElement element = driver.findElement(By.xpath(".//a[@href=\"https://" + socialNetwork +
                    ".com/wrike\"]//ancestor::li"));
            element.click();
            driver.get("https://" + socialNetwork + ".com/wrike");
            waitForLoading("https://" + socialNetwork + ".com/wrike");
            assertEquals("https://" + socialNetwork + ".com/wrike", driver.getCurrentUrl());
    }
    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
