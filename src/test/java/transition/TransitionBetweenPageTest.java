package transition;

import api.UserApi;
import base.test.BaseTest;
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
import static junit.framework.TestCase.*;
import static url.UrlConfig.LOGIN_URL;
import static url.UrlConfig.MAIN_URL;
import static webdriver.Browser.CHROME;
import static webdriver.Browser.YANDEX;

@RunWith(Parameterized.class)
public class TransitionBetweenPageTest
{
    private MainPage mainPage;
    private WebDriver driver;
    private String token;
    private LoginPage loginPage;
    private AccountPage accountPage;
    private Browser browser;
    private WebDriverManagment webDriverManagment;
    private BaseTest baseTest;
    private String password;
    private String email;

    public TransitionBetweenPageTest(Browser browser)
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
        mainPage = new MainPage(driver);
        accountPage = new AccountPage(driver);
        baseTest = new BaseTest();

        webDriverManagment = new WebDriverManagment();
        driver = webDriverManagment.setDriver(browser);
        driver.get(LOGIN_URL);

        baseTest.createUserForTestApi();
        baseTest.logInAfterRegistrationUI(driver);
    }
    @DisplayName("Проверка перехода к странице личного профиля из главной страницы")
    @Description("создание, авторизация, переход в профиль из галвной страницы, удаление аккаунта")
    @Test
    public void checkTransitionToPersonalAccountTest()
    {
        mainPage.clickPersonalAccount();

        accountPage.waitButtonSave();
        assertTrue(accountPage.checkButtonSave());
    }

    @DisplayName("Проверка перехода к странице конструктора по логотипу и тексту конструктор")
    @Description("создание, авторизация, переход в профиль из галвной страницы, клик по тексту конструктор, потом переход в личный кабинет и потом клик по логотипу, потом удаление аккаунта")
    @Test
    public void checkTransitionToMainPage()
    {
        mainPage.clickPersonalAccount();


        accountPage.clickKotstruktButton();
        assertEquals(MAIN_URL, driver.getCurrentUrl());

        mainPage.waitCreateOrderButton();
        assertTrue(mainPage.createOrderButtonVisible());
        mainPage.clickPersonalAccount();


        accountPage.clickLogoButton();
        assertEquals(MAIN_URL, driver.getCurrentUrl());
        assertTrue(mainPage.createOrderButtonVisible());
    }
    @After
    public void setDown()
    {
        baseTest.deleteUserAfterTestApi();
        driver.quit();
    }

}
