package login;
import static org.apache.http.HttpStatus.*;
import api.UserApi;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.request.UserCreateRequestModel;
import model.request.UserDeleteRequestModel;
import model.response.UserCreateResponseModel;
import model.response.UserDeleteResponseModel;
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

import java.io.IOException;

import static generator.UserGenerator.generateUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static url.UrlConfig.*;
import static webdriver.Browser.CHROME;
import static webdriver.Browser.YANDEX;

@RunWith(Parameterized.class)
public class LoginPageCheckTest
{
    private String url;
    private String button;
    private WebDriver driver;
    private UserApi userApi;
    private UserCreateRequestModel userCreateRequestModel;
    private UserDeleteRequestModel userDeleteRequestModel;
    private MainPage mainPage;
    private LoginPage loginPage;
    private String token;
    private WebDriverManagment webDriverManagment;
    private Browser browser;
        public LoginPageCheckTest(String url, String button, Browser browser)
        {
            this.url = url;
            this.button = button;
            this.browser = browser;
        }

        @Parameterized.Parameters
        public static Object[][] getEnterAccount()
        {
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

        @DisplayName("Проверка перехода к странице авторизации")
        @Description("Создание аккаунта, переход на страницу, где есть ссылка, авторизация, удаление аккаунта")
        @Test
        public void accountToLoginPageRefCheck()
        {
            //генерируем пользователя, достаем его токен
            userApi = new UserApi();
            userCreateRequestModel = generateUser();

            Response response = userApi.createUser(userCreateRequestModel);
            UserCreateResponseModel userCreateResponseModel = response.body().as(UserCreateResponseModel.class);
            token = userCreateResponseModel.getAccessToken();

            webDriverManagment = new WebDriverManagment();
            driver = webDriverManagment.setDriver(browser);
            driver.get(url);

            driver.findElement(By.xpath(".//*[text()='" + button + "']")).click();

            loginPage = new LoginPage(driver);
            mainPage = new MainPage(driver);

            loginPage.setEmail(userCreateRequestModel.getEmail());
            loginPage.setPassword(userCreateRequestModel.getPassword());
            loginPage.clickEnterButton();

            mainPage.waitCreateOrderButton();

        }

        @After
        public void setDown()
        {
            userDeleteRequestModel = new UserDeleteRequestModel(userCreateRequestModel.getEmail(), userCreateRequestModel.getPassword());
            Response responseOfDeleting = userApi.deleteUser(userDeleteRequestModel, token);
            UserDeleteResponseModel userDeleteResponseModel = responseOfDeleting.body().as(UserDeleteResponseModel.class);

            assertEquals(SC_ACCEPTED, responseOfDeleting.getStatusCode());
            assertTrue(userDeleteResponseModel.isSuccess());
            driver.quit();
        }
}
