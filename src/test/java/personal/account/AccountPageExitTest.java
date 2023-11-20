package personal.account;

import api.UserApi;
import base.test.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.request.UserCreateRequestModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page.AccountPage;
import page.LoginPage;
import page.MainPage;
import webdriver.Browser;
import webdriver.WebDriverManagment;

import static org.junit.Assert.assertTrue;
import static url.UrlConfig.*;
import static webdriver.Browser.CHROME;
import static webdriver.Browser.YANDEX;

@RunWith(Parameterized.class)
public class AccountPageExitTest
{
    private WebDriver driver;
    private UserApi userApi;
    private BaseTest baseTest;
    private UserCreateRequestModel userCreateRequestModel;
    private String token;
    private LoginPage loginPage;
    private MainPage mainPage;
    private AccountPage accountPage;
    private Browser browser;
    private WebDriverManagment webDriverManagment;
    private String password;
    private String login;

    public AccountPageExitTest(Browser browser)
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
        driver.get(LOGIN_URL);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        accountPage = new AccountPage(driver);
        baseTest = new BaseTest();


        baseTest.createUserForTestApi();
        baseTest.logInAfterRegistrationUI(driver);
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
        baseTest.deleteUserAfterTestApi();

        driver.quit();
    }
}