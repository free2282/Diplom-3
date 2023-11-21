package model.request;

public class UserDeleteRequestModel {
    private final String email;
    private final String password;

    public UserDeleteRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}