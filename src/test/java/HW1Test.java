import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HW1Test {

    WebDriver driver;
    WebDriverWait wait;
    static String USERNAME;
    static String PASSWORD;

    static String currentLogin;
    static String currentName;

    @BeforeAll
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        USERNAME = System.getProperty("geekbrains_username", System.getenv("geekbrains_username"));
        PASSWORD = System.getProperty("geekbrains_password", System.getenv("geekbrains_password"));
        currentLogin = "Login" + System.currentTimeMillis();
        currentName = "G" + System.currentTimeMillis() + "G";
    }

    @BeforeEach
    public void setUpTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }

    @Test
    public void addGroupTest() throws InterruptedException {
        driver.get("https://test-stand.gb.ru/login");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='text']"))).sendKeys(USERNAME);
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='password']"))).sendKeys(PASSWORD);

        driver.findElement(By.cssSelector("form#login button")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("form#login button"))));

        driver.findElement(By.id("create-btn")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.mdc-dialog__surface")));

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#upsert-item > div:nth-child(5) > label > input")))
                .sendKeys(currentLogin);
        wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("#upsert-item > div:nth-child(1) > label > input")))
                .sendKeys(currentName);
        driver.findElement(By.cssSelector("#upsert-item > div.submit > button")).click();

        Thread.sleep(1200);

        Assertions.assertEquals(driver.findElement(
                By.cssSelector("#app > main > div > div > div.mdc-data-table > " +
                        "div.mdc-data-table__table-container > table > " +
                        "tbody > tr:nth-child(1) > td:nth-child(2)")).getText(), currentName);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}