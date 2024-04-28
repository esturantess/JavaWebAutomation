import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HW1Task2Test {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeAll
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeEach
    public void setUpTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @Test
    public void dragAndDropTest() {
        driver.get("https://www.globalsqa.com/demoSite/practice/droppable/photo-manager.html#:~:text=%3Cul%20id%3D%22,image%3C/a%3E");
        WebElement element1 = driver.findElement(By.cssSelector("#gallery > li:nth-child(1) > img"));
        WebElement target = driver.findElement(By.cssSelector("#trash"));
        actions.dragAndDrop(element1, target).build().perform();
        Assertions.assertTrue(true);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
