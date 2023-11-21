package transition;

import base.test.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page.AccountPage;
import page.MainPage;
import webdriver.Browser;
import webdriver.WebDriverManagment;
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
    private AccountPage accountPage;
    private Browser browser;
    private WebDriverManagment webDriverManagment;
    private BaseTest baseTest;

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
        webDriverManagment = new WebDriverManagment();
        driver = webDriverManagment.setDriver(browser);
        mainPage = new MainPage(driver);
        accountPage = new AccountPage(driver);
        baseTest = new BaseTest();
        driver.get(LOGIN_URL);

        baseTest.createUserApiForTest();
        baseTest.logInAfterRegistrationUI(driver);
    }

    @Step("Проверка перехода к странице личного профиля из главной страницы")
    @DisplayName("Проверка перехода к странице личного профиля из главной страницы")
    @Description("создание, авторизация, переход в профиль из галвной страницы, удаление аккаунта")
    @Test
    public void checkTransitionToPersonalAccountTest()
    {
        mainPage.waitCreateOrderButton();
        mainPage.clickPersonalAccount();

        accountPage.waitButtonSave();
        assertTrue(accountPage.checkButtonSave());
    }

    @Step("Проверка перехода к странице конструктора по логотипу и тексту конструктор")
    @DisplayName("Проверка перехода к странице конструктора по логотипу и тексту конструктор")
    @Description("создание, авторизация, переход в профиль из галвной страницы, клик по тексту конструктор, потом переход в личный кабинет и потом клик по логотипу, потом удаление аккаунта")
    @Test
    public void checkTransitionToMainPage()
    {
        mainPage.waitCreateOrderButton();
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
