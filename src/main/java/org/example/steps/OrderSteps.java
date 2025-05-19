package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.OrderCreateField;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderSteps {

    @Step("check create order")
    public static Response orderCreate(OrderCreateField order){
        Response response = given()
                .header("Content-type", "application/json")
                .body(order).log().all()
                .when()
                .post("/api/v1/orders");
        return response;
    }

    @Step("check positive response")
    public static void checkResponsePositiveOrder(Response response){
        response.then().assertThat().statusCode(201).and()
                .body("track", notNullValue());
    }
    @Step("check order list")
    public static void getOrderList(){
        Response response = given()
                .get("/api/v1/orders");
        response.then().assertThat().statusCode(200).and()
                .body("orders", notNullValue());

    }
}
