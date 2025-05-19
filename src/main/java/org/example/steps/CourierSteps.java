package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.CourierCreate;
import models.CourierId;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierSteps {

  @Step("create a new courier")
    public static Response createCourier(CourierCreate courier){
      Response response = given()
              .header("Content-type", "application/json")
              .body(courier).log().all()
              .when()
              .post("/api/v1/courier");
    return response;
  }

  @Step("check positive response")
  public static void checkResponsePositiveCourier(Response response){
    response.then().assertThat().statusCode(201).and()
            .body("ok", equalTo(true));
  }

  @Step("check negative response")
  public static void checkResponseNegative(Response response,int status, String text){
    response.then().assertThat().statusCode(status).and()
            .body("message", equalTo(text));
  }

//login
  @Step("get id courier")
    public static int getIdCourier(CourierCreate courier){
      Response response = given()
              .header("Content-type", "application/json")
              .body(courier).log().all()
              .when()
              .post("/api/v1/courier/login");
      response.then().assertThat().statusCode(200).and().body("id", notNullValue());
       int id = response.as(CourierId.class).getId();
      return id;
  }

  @Step("send post login")
  public  static Response postLogin(CourierCreate courier){
    Response response = given()
            .header("Content-type", "application/json")
            .body(courier).log().all()
            .when()
            .post("/api/v1/courier/login");
            return response;
  }

  //delete
  @Step("delete courier")
    public void delete (int id){
      given().log().all()
              .delete("/api/v1/courier/" + id)
              .then().statusCode(200);
  }

}
