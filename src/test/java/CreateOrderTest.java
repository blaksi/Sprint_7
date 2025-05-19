import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.OrderCreateField;
import org.example.steps.OrderSteps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class CreateOrderTest {
    private  final String[] colour;

    public CreateOrderTest(String[] colour) {
        this.colour = colour;

    }


    @Parameterized.Parameters(name = "Test with card color: {0}")
    public static Collection<Object[]> orderColors() {
        return Arrays.asList(new Object[][]{
                {new String[]{"black"}},
                {new String[]{"gray"}},
                {new String[]{"black", "gray"}},
                {new String[]{}}
        });
    }

    @Before
    public void setUp() {
        RestAssured.baseURI= "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void createOrder(){

        OrderCreateField order = new OrderCreateField(  "alex","leon","moskva","metro","+79555",5,"2025-05-26","comment", colour);
        Response response = OrderSteps.orderCreate(order);
        OrderSteps.checkResponsePositiveOrder(response);
    }
}




























