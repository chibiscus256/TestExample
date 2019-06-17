import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeTest {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        MainPage mainPage = new MainPage(driver);
        mainPage.init(driver);
        mainPage.getMainPage();
        mainPage.createAnAccount();

        QASectionPage qaSectionPage = new QASectionPage();
        qaSectionPage.init(driver);
        qaSectionPage.waitForLoading();
        qaSectionPage.fillTheForm();
        qaSectionPage.resendResult();
        qaSectionPage.followUsOn("twitter");
    }

}