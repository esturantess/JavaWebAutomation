

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Task1 {

    public static void main(String[] args) {
        String pathToChromeDriver = "src\\main/resources\\chromedriver.exe";
        String pathToGeckoDriver = "src\\main\\resources\\geckodriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        System.setProperty("webdriver.firefox.driver", pathToGeckoDriver);


        ChromeOptions options = new ChromeOptions();
//         Не будет явного запуска браузера (передать объект options в хром драйвер)
//        options.addArguments("--headless");
//        Открыть браузер на весь экран
//        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.ru/");
        System.out.println("Page title: " + driver.getTitle());
        driver.quit();
    }
}
