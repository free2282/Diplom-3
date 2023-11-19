package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static url.UrlConfig.LOGIN_URL;
import static url.UrlConfig.MAIN_URL;

public class MainPage
{
    private WebDriver driver;
    public MainPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccount = By.xpath(".//*[text()='Личный Кабинет']");
    private final By createOrderButton = By.xpath(".//*[text()='Оформить заказ']");
    private final By breadButton = By.xpath(".//span[text()='Булки']");
    private final By breadText = By.xpath(".//h2[text()='Булки']");
    private final By sauceButton = By.xpath(".//span[text()='Соусы']");
    private final By sauceText = By.xpath(".//h2[text()='Соусы']");
    private final By fillingButton = By.xpath(".//span[text()='Начинки']");
    private final By fillingText = By.xpath(".//h2[text()='Начинки']");

    public void clickSauseButton()
    {
        driver.findElement(sauceButton).click();
    }

    public void clickFillingButton()
    {
        driver.findElement(fillingButton).click();
    }

    public void clickBreadButton()
    {
        driver.findElement(breadButton).click();
    }

    public boolean isSauseSectionVisible()
    {
        return driver.findElement(sauceText).isDisplayed();
    }

    public boolean isFillingSectionVisible()
    {
        return driver.findElement(fillingText).isDisplayed();
    }

    public boolean isBreadSectionVisible()
    {
        return driver.findElement(breadText).isDisplayed();
    }

    public void clickPersonalAccount()
    {
        driver.findElement(personalAccount).click();
    }

    public boolean createOrderButtonVisible() {
        return driver.findElement(createOrderButton).isDisplayed();
    }
    public boolean loginButton()
    {
        return driver.findElement(loginButton).isDisplayed();
    }

    public void waitCreateOrderButton()
    {
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        driver.findElement(createOrderButton);
    }

    public void waitLoadMainPage()
    {
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.urlToBe(MAIN_URL));
    }
}
