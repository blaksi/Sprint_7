import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CourierCreate;
import org.example.steps.CourierSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.example.utils.Utils.randomString;

public class CourierLoginTest {

    @Before
    public void setUp() {
        RestAssured.baseURI= "https://qa-scooter.praktikum-services.ru/";
    }

    private CourierSteps newCoruers = new CourierSteps();
    private CourierCreate courier = new CourierCreate("Luka", "3456", "sdfgh");
    private int id;
    private boolean deleteIdAfterTest=true;


    @Test
    public void checkAuthorization(){
        CourierSteps.createCourier(courier);
        id= CourierSteps.getIdCourier(courier);
    }

    @Test
    public void checkRequiredFieldsLogin(){
        courier = new CourierCreate()
                .setPassword("Luka");
        Response response = CourierSteps.postLogin(courier);
        CourierSteps.checkResponseNegative(response,400, "Недостаточно данных для входа");
        deleteIdAfterTest=false;
    }

    @Test
    public void checkUnknownLogin() {
        courier = new CourierCreate()
                .setLogin(randomString())
                .setPassword(randomString());
        Response response = CourierSteps.postLogin(courier);
        CourierSteps.checkResponseNegative(response, 404, "Учетная запись не найдена");
        deleteIdAfterTest = false;
    }


    @After
    public void delete(){
        if (deleteIdAfterTest) {
            newCoruers.delete(id);
        }
    }
}
