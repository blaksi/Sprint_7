import annotations.BaseTest;
import io.restassured.response.Response;
import models.CourierCreate;
import org.example.steps.CourierSteps;
import org.junit.After;
import org.junit.Test;

import static org.example.utils.Utils.randomString;

public class CourierLoginTest extends BaseTest {

    private final CourierSteps newCoruers = new CourierSteps();
    private CourierCreate courier = new CourierCreate("Luka", "3456", "sdfgh");
    private int id;
    private boolean deleteIdAfterTest = true;

    @Test
    public void checkAuthorization() {
        CourierSteps.createCourier(courier);
        id = CourierSteps.getIdCourier(courier);
    }

    @Test
    public void checkRequiredFieldsLogin() {
        deleteIdAfterTest = false;
        courier = new CourierCreate()
                .setPassword("Luka");
        Response response = CourierSteps.postLogin(courier);
        CourierSteps.checkResponseNegative(response, 400, "Недостаточно данных для входа");
    }

    @Test
    public void checkUnknownLogin() {
        deleteIdAfterTest = false;
        courier = new CourierCreate()
                .setLogin(randomString())
                .setPassword(randomString());
        Response response = CourierSteps.postLogin(courier);
        CourierSteps.checkResponseNegative(response, 404, "Учетная запись не найдена");
    }

    @After
    public void delete() {
        if (deleteIdAfterTest) {
            newCoruers.delete(id);
        }
    }
}
