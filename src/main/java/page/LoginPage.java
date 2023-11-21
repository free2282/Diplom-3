package page;

import groovy.util.logging.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static url.UrlConfig.LOGIN_URL;

public class LoginPage
{
    private final By emailInput = By.xpath(".//input[@name='name']");
    private final By passwordInput = By.xpath(".//input[@name='Пароль']");
    private final By enterButton = By.xpath(".//*[text()='Войти']");
    private final By enterText = By.xpath(".//*[text()='Вход']");
    private WebDriver driver;
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    @Step("Передаем в поле email данные на странцие авторизации")
    public void setEmail(String email)
    {
        driver.findElement(emailInput).sendKeys(email);
    }
    @Step("Передаем в поле пароль данные на странцие авторизации")
    public void setPassword(String password)
    {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Кликаем по кнопке 'Войти' на странцие авторизации")
    public void clickEnterButton()
    {
        driver.findElement(enterButton).click();
    }

    @Step("Ожидание 4 секунлы, когда откроется страница авторизации")
    public void waitLoadLoginPage()
    {
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.urlToBe(LOGIN_URL));
    }

    @Step("Проверка видимости текста 'Вход' на странцие авторизации")
    public Boolean isEnterTextDisplayed()
    {
        return driver.findElement(enterText).isDisplayed();
    }
}
