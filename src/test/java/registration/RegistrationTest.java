package registration;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.request.UserCreateRequestModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.LoginPage;
import page.RegisterPage;
import webdriver.Browser;
import webdriver.WebDriverManagment;

import static generator.UserGenerator.generateUser;
import static junit.framework.TestCase.assertEquals;
import static url.UrlConfig.LOGIN_URL;
import static url.UrlConfig.REGISTRATION_URL;
import static webdriver.Browser.CHROME;
import static webdriver.Browser.YANDEX;

@RunWith(Parameterized.class)
public class RegistrationTest
{
    private RegisterPage registerPage;
    private WebDriver driver;
    private UserCreateRequestModel userCreateRequestModel;
    private Browser browser;
    private WebDriverManagment webDriverManagment;

    public RegistrationTest(Browser browser)
    {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getEnterAccount()
    {
        return new Object[][]{
                {YANDEX},
                {CHROME},
        };
    }

    @Before
    public void setUp()
    {
        webDriverManagment = new WebDriverManagment();
        driver = webDriverManagment.setDriver(browser);

        registerPage = new RegisterPage(driver);
        userCreateRequestModel = generateUser();

        driver.get(REGISTRATION_URL);
    }

    @DisplayName("Проверка регистрации пользователя")
    @Test
    public void registrationTest()
    {
        registerPage.registrateUser();

        assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @DisplayName("Проверка несоздания аккаунта при пароле меньше 6 символов")
    @Test
    public void errorPasswordRegistrationTest()
    {
        userCreateRequestModel.setPassword("12345");

        registerPage.registrateUserWrongPassword();
        registerPage.findTextErrorWrongPassword();
    }

    @After
    public void setDown()
    {

        driver.quit();
    }


}
