package page;
import io.qameta.allure.Step;
import model.request.UserCreateRequestModel;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static generator.UserGenerator.generateUser;
import static url.UrlConfig.LOGIN_URL;

public class RegisterPage
{
    private WebDriver driver;
    private By nameInput = By.xpath(".//*[text()='Имя']/parent::div/input");
    private By emailInput = By.xpath(".//*[text()='Email']/parent::div/input");
    private By passwordInput = By.xpath(".//*[text()='Пароль']/parent::div/input");
    private By registerButton = By.xpath(".//*[text()='Зарегистрироваться']");
    private By errorWrongPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private By loginButton = By.xpath(".//a[text()='Войти']");
    public RegisterPage(WebDriver driver)
    {
        this.driver = driver;
    }

    @Step("Передаем в поле имя данные на странице регистрации")
    public void setName(String name)
    {
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("передаем в поле email данные на странице регистрации")
    public void setEmail(String email)
    {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("передаем в поле пароль данные на странице регистрации")
    public void setPassword(String password)
    {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("кликаем по кнопке 'Регистрация'")
    public void clickRegistration()
    {
        driver.findElement(registerButton).click();
    }

    @Step("Поиск строки ошибки при создании пользователя с некоректной длиной пароля")
    public void findTextErrorWrongPassword()
    {
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.visibilityOfElementLocated(errorWrongPassword));
        driver.findElement(errorWrongPassword);
    }

    @Step("Создание пользователя через ui")
    public void registrateUser()
    {
        UserCreateRequestModel userCreateRequestModel = generateUser();

        setName(userCreateRequestModel.getName());
        setEmail(userCreateRequestModel.getEmail());
        setPassword(userCreateRequestModel.getPassword());

        clickRegistration();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoadLoginPage();
    }

    @Step("Регистрация пользователя с ошибочной длиной пароля")
    public void registrateUserWrongPassword()
    {
        UserCreateRequestModel userCreateRequestModel = generateUser();

        setName(userCreateRequestModel.getName());
        setEmail(userCreateRequestModel.getEmail());
        setPassword("12345");

        clickRegistration();
    }
}
