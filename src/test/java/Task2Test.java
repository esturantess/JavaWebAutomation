import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    WebDriver driver;
    WebDriverWait wait;
    static String USERNAME;
    static String PASSWORD;

    @BeforeAll
    public static void setupClass() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        USERNAME = System.getProperty("geekbrains_username", System.getenv("geekbrains_username"));
        PASSWORD = System.getProperty("geekbrains_password", System.getenv("geekbrains_password"));
    }

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testStandGeekbrains() {

        driver.get("https://test-stand.gb.ru/login");

        driver.findElement(By.cssSelector("form#login input[type='text']")).sendKeys(USERNAME);
        driver.findElement(By.cssSelector("form#login input[type='password']")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector("form#login button")).click();

        WebElement usernameLink = driver.findElement(By.partialLinkText(USERNAME));
        assertEquals(String.format("Hello, %s", USERNAME), usernameLink.getText().replace("\n", " ").trim());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
