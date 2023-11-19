package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage
{
    private WebDriver driver;
    private final By saveButton = By.xpath(".//*[text()='Сохранить']");
    private final By konstrukorButton = By.xpath(".//*[text()='Конструктор']");
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");
    private final By exitButton = By.xpath(".//*[text()='Выход']");

    public AccountPage(WebDriver driver)
    {
        this.driver = driver;
    }


    public Boolean checkButtonSave()
    {
        return driver.findElement(saveButton).isDisplayed();
    }

    public void waitButtonSave()
    {
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOfElementLocated(saveButton));
    }
    public void clickKotstruktButton()
    {
        driver.findElement(konstrukorButton).click();
    }

    public void clickLogoButton()
    {
        driver.findElement(logoButton).click();
    }

    public void clickExit()
    {
        driver.findElement(exitButton).click();
    }
}
