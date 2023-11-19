package login;

import api.UserApi;
import groovy.util.logging.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.request.UserCreateRequestModel;
import model.request.UserDeleteRequestModel;
import model.response.UserCreateResponseModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.AccountPage;
import page.LoginPage;
import page.MainPage;
import webdriver.Browser;
import webdriver.WebDriverManagment;

import static generator.UserGenerator.generateUser;
import static org.junit.Assert.assertTrue;
import static url.UrlConfig.*;
import static url.UrlConfig.RESSET_PASSWORD_URL;
import static webdriver.Browser.CHROME;
import static webdriver.Browser.YANDEX;

@RunWith(Parameterized.class)
public class LoginPageExitTest
{
    private WebDriver driver;
    private UserApi userApi;
    private UserCreateRequestModel userCreateRequestModel;
    private String token;
    private LoginPage loginPage;
    private MainPage mainPage;
    private AccountPage accountPage;
    private Browser browser;
    private WebDriverManagment webDriverManagment;

    public LoginPageExitTest(Browser browser)
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
        userApi = new UserApi();
        userCreateRequestModel = generateUser();
        Response response = userApi.createUser(userCreateRequestModel);
        UserCreateResponseModel userCreateResponseModel = response.body().as(UserCreateResponseModel.class);
        token = userCreateResponseModel.getAccessToken();

        webDriverManagment = new WebDriverManagment();
        driver = webDriverManagment.setDriver(browser);
        driver.get(LOGIN_URL);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        accountPage = new AccountPage(driver);

        loginPage.setEmail(userCreateRequestModel.getEmail());
        loginPage.setPassword(userCreateRequestModel.getPassword());
        loginPage.clickEnterButton();
    }

    @DisplayName("Проверка выхода из аккаунта")
    @Description("Создание аккаунта, авторизация, переход в личный профиль и выход из аккаунта. В конце теста удаление аккаунта")
    @Test
    public void exitFromAccountTest()
    {
        mainPage.clickPersonalAccount();
        accountPage.waitButtonSave();
        accountPage.clickExit();

        loginPage.waitLoadLoginPage();
        assertTrue(loginPage.isEnterTextDisplayed());
    }

    @After
    public void setDown()
    {
        UserDeleteRequestModel userDeleteRequestModel = new UserDeleteRequestModel(userCreateRequestModel.getEmail(), userCreateRequestModel.getPassword());
        userApi.deleteUser(userDeleteRequestModel, token);

        driver.quit();
    }
}
