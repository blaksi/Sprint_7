import io.restassured.RestAssured;
import org.example.steps.OrderSteps;
import org.junit.Before;
import org.junit.Test;

public class OrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI= "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void getOrderList(){
        OrderSteps.getOrderList();
    }
}
