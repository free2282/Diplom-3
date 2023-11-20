package page;

import io.qameta.allure.Step;
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

    private final By personalAccount = By.xpath(".//*[text()='Личный Кабинет']");
    private final By createOrderButton = By.xpath(".//*[text()='Оформить заказ']");
    private final By breadButton = By.xpath(".//span[text()='Булки']");
    private final By breadText = By.xpath(".//h2[text()='Булки']");
    private final By sauceButton = By.xpath(".//span[text()='Соусы']");
    private final By sauceText = By.xpath(".//h2[text()='Соусы']");
    private final By fillingButton = By.xpath(".//span[text()='Начинки']");
    private final By fillingText = By.xpath(".//h2[text()='Начинки']");


    @Step("Переход по ссылке 'Соусы' на главной странице")
    public void clickSauseButton()
    {
        driver.findElement(sauceButton).click();
    }

    @Step("Переход по ссылке 'Начинки' на главной странице")
    public void clickFillingButton()
    {
        driver.findElement(fillingButton).click();
    }

    @Step("Переход по ссылке 'Булки' на главной странице")
    public void clickBreadButton()
    {
        driver.findElement(breadButton).click();
    }

    @Step("Проверка видимости строки 'Соусы' на главной странице")
    public boolean isSauseSectionVisible()
    {
        return driver.findElement(sauceText).isDisplayed();
    }
    @Step("Проверка видимости строки 'Начинки' на главной странице")
    public boolean isFillingSectionVisible()
    {
        return driver.findElement(fillingText).isDisplayed();
    }

    @Step("Проверка видимости строки 'Булки' на главной странице")
    public boolean isBreadSectionVisible()
    {
        return driver.findElement(breadText).isDisplayed();
    }
    @Step("переход по кнопке 'Личный кабинет' на главной странице")
    public void clickPersonalAccount()
    {
        driver.findElement(personalAccount).click();
    }
    @Step("Проверка видимости кнокпи 'Оформить заказ' на главной странице")

    public boolean createOrderButtonVisible() {
        return driver.findElement(createOrderButton).isDisplayed();
    }
    @Step("Ожидание 4 секунды, когда появится кнопка 'Оформить заказ' на главной странице")
    public void waitCreateOrderButton()
    {
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        driver.findElement(createOrderButton);
    }
}
