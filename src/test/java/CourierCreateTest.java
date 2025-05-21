import annotations.BaseTest;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.CourierCreate;
import org.example.steps.CourierSteps;
import org.junit.After;
import org.junit.Test;

public class CourierCreateTest extends BaseTest {
    private final CourierSteps newCoruers = new CourierSteps();
    private CourierCreate courier = new CourierCreate("Luka", "3456", "sdfgh");
    private boolean deleteIdAfterTest = true;

    @Test
    @Description("Success.check create courier")
    public void checkCreateCourier() {
        Response response = CourierSteps.createCourier(courier);
        CourierSteps.checkResponsePositiveCourier(response);
    }

    @Test
    @Description("Negative.check required fields")
    public void checkRequiredFields() {
        deleteIdAfterTest = false;
        courier = new CourierCreate()
                .setLogin("dfghjd");
        Response response = CourierSteps.createCourier(courier);
        CourierSteps.checkResponseNegative(response, 400, "Недостаточно данных для создания учетной записи");
        courier = new CourierCreate()
                .setPassword("3456");
        response = CourierSteps.createCourier(courier);
        CourierSteps.checkResponseNegative(response, 400, "Недостаточно данных для создания учетной записи");

    }

    @Test
    @Description("Negative.create double couriers is forbidden")
    public void createDoubleCourierIsFobbiden() {
        CourierSteps.createCourier(courier);
        Response response = CourierSteps.createCourier(courier);
        CourierSteps.checkResponseNegative(response, 409, "Этот логин уже используется. Попробуйте другой.");
    }

    @After
    public void delete() {
        if (deleteIdAfterTest) {
            int id = CourierSteps.getIdCourier(courier);
            newCoruers.delete(id);
        }
    }
}
