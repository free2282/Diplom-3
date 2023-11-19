package api;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.request.UserCreateRequestModel;
import model.request.UserDeleteRequestModel;
import model.request.UserLoginRequestModel;

import static url.UrlConfig.*;


public class UserApi extends BaseApi
{
    @Step("Отправка запроса на ручку по созданию пользователя")
    public Response createUser(UserCreateRequestModel userApiRequestModel)
    {
        return baseRequest()
                .body(userApiRequestModel)
                .post(MAIN_API_URL + CREATE_USER_URL_API);
    }
    @Step("Отправка запроса на ручку по входу пользователя")
    public Response loginUser(UserLoginRequestModel userLoginRequestModel)
    {
        return baseRequest()
                .body(userLoginRequestModel)
                .post(MAIN_API_URL + LOGIN_USER_URL_API);
    }

    @Step("Отправка запроса на ручку по удалению пользователя с его токеном")
    public Response deleteUser(UserDeleteRequestModel userDeleteRequestModel, String accessToken)
    {
        return baseRequest()
                .header("Authorization", accessToken)
                .body(userDeleteRequestModel)
                .delete(MAIN_API_URL + UPDATE_DELETE_USER_URL_API);
    }
}