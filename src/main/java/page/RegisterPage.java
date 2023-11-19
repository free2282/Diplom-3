package page;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

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

    public void setName(String name)
    {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setEmail(String email)
    {
        driver.findElement(emailInput).sendKeys(email);
    }
    public void clickRegistration()
    {
        driver.findElement(registerButton).click();
    }

    public void setPassword(String password)
    {
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void findTextErrorWrongPassword()
    {
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.visibilityOfElementLocated(errorWrongPassword));
        driver.findElement(errorWrongPassword);
    }
}
