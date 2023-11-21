package base.test;

import api.UserApi;
import io.restassured.response.Response;
import model.request.UserCreateRequestModel;
import model.request.UserDeleteRequestModel;
import model.response.UserCreateResponseModel;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import page.MainPage;

import static generator.UserGenerator.generateUser;

public class BaseTest {
    private UserApi userApi;
    private UserCreateRequestModel userCreateRequestModel;
    private String token;
    private LoginPage loginPage;
    private MainPage mainPage;

    public void createUserApiForTest() {
        userApi = new UserApi();
        userCreateRequestModel = generateUser();
        Response response = userApi.createUser(userCreateRequestModel);
        UserCreateResponseModel userCreateResponseModel = response.body().as(UserCreateResponseModel.class);
        token = userCreateResponseModel.getAccessToken();
    }

    public void deleteUserAfterTestApi() {
        UserDeleteRequestModel userDeleteRequestModel = new UserDeleteRequestModel(userCreateRequestModel.getEmail(), userCreateRequestModel.getPassword());
        userApi.deleteUser(userDeleteRequestModel, token);
    }

    public void deleteUserAfterTestApi(String email, String password) {
        UserDeleteRequestModel userDeleteRequestModel = new UserDeleteRequestModel(email, password);
        userApi.deleteUser(userDeleteRequestModel, token);
    }

    public void logInAfterRegistrationUI(WebDriver driver) {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);


        loginPage.setEmail(userCreateRequestModel.getEmail());
        loginPage.setPassword(userCreateRequestModel.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitCreateOrderButton();
    }

    public String getToken() {
        return token;
    }

    public UserCreateRequestModel getUserCreateRequestModel() {
        return userCreateRequestModel;
    }
}
