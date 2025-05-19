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

    public CourierCreate(){}

    public String getLogin() {

        return login;
    }

    public String getPassword() {
        return password;
    }

    public CourierCreate setLogin(String login) {
        this.login = login;
        return this;
    }

    public CourierCreate setPassword(String password) {
        this.password = password;
        return this;
    }

    public CourierCreate setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

}
