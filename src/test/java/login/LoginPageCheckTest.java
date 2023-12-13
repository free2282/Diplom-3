package login;

import api.UserApi;
import base.test.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.request.UserCreateRequestModel;
import model.request.UserDeleteRequestModel;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import page.MainPage;
import webdriver.Browser;
import webdriver.WebDriverManagment;

import static url.UrlConfig.*;
import static webdriver.Browser.CHROME;
import static webdriver.Browser.YANDEX;

@RunWith(Parameterized.class)
public class LoginPageCheckTest {
    private final String url;
    private final String button;
    private WebDriver driver;
    private UserApi userApi;
    private UserCreateRequestModel userCreateRequestModel;
    private UserDeleteRequestModel userDeleteRequestModel;
    private MainPage mainPage;
    private LoginPage loginPage;
    private String token;
    private WebDriverManagment webDriverManagment;
    private final Browser browser;
    private BaseTest baseTest;

    public LoginPageCheckTest(String url, String button, Browser browser) {
        this.url = url;
        this.button = button;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getEnterAccount() {
        return new Object[][]{
                {MAIN_URL, "Личный Кабинет", YANDEX},
                {MAIN_URL, "Войти в аккаунт", YANDEX},
                {REGISTRATION_URL, "Войти", YANDEX},
                {RESSET_PASSWORD_URL, "Войти", YANDEX},
                {MAIN_URL, "Личный Кабинет", CHROME},
                {MAIN_URL, "Войти в аккаунт", CHROME},
                {REGISTRATION_URL, "Войти", CHROME},
                {RESSET_PASSWORD_URL, "Войти", CHROME},
        };
    }

    @Step("Проверка перехода к странице авторизации")
    @DisplayName("Проверка перехода к странице авторизации")
    @Description("Создание аккаунта, переход на страницу, где есть ссылка, авторизация, удаление аккаунта")
    @Test
    public void accountToLoginPageRefCheck() {
        //генерируем пользователя, достаем его токен
        baseTest = new BaseTest();
        baseTest.createUserApiForTest();

        webDriverManagment = new WebDriverManagment();
        driver = webDriverManagment.setDriver(browser);
        driver.get(url);

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);

        driver.findElement(By.xpath(".//*[text()='" + button + "']")).click();


        loginPage.setEmail(baseTest.getUserCreateRequestModel().getEmail());
        loginPage.setPassword(baseTest.getUserCreateRequestModel().getPassword());
        loginPage.clickEnterButton();

        mainPage.waitCreateOrderButton();

    }

    @After
    public void setDown() {
        baseTest.deleteUserAfterTestApi();
        driver.quit();
    }
}
