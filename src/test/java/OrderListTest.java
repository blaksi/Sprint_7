import annotations.BaseTest;
import org.example.steps.OrderSteps;
import org.junit.Test;

public class OrderListTest extends BaseTest {

    @Test
    public void getOrderList() {
        OrderSteps.getOrderList();
    }
}
