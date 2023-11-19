package generator;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.request.UserCreateRequestModel;

public class UserGenerator
{
    private static String name = "ТестИмя";
    private static String password = "232345";
    private static String email = "qwe@yandex.ru";

    public static UserCreateRequestModel generateUser()
    {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyyHH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);

        email = formattedDate+email;
        password = password + formattedDate;
        name = name + formattedDate;

        return new UserCreateRequestModel(email, password, name);
    }
}
