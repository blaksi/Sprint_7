package models;

import org.example.steps.CourierSteps;

public class CourierCreate extends CourierSteps {
    private String login;
    private String password;
    private String firstName;

    public CourierCreate(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CourierCreate() {
    }

    public CourierCreate setLogin(String login) {
        this.login = login;
        return this;
    }

    public CourierCreate setPassword(String password) {
        this.password = password;
        return this;
    }

}
