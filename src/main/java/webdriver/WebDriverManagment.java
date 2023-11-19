package webdriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public class WebDriverManagment
{
    private WebDriver driver;

    public WebDriver setDriver(Browser browser)
    {


        switch (browser)
        {
            case CHROME:
                driver = getChromeDriver();
                break;
            case YANDEX:
                driver = getYandexDriver();
                break;
        }
        return driver;
    }

    private WebDriver getChromeDriver()
    {
        WebDriverManager.chromedriver().driverVersion("119.0.6045.123").setup();
        return driver = new ChromeDriver();
    }

    private WebDriver getYandexDriver()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return driver = new ChromeDriver(options);
    }
}
