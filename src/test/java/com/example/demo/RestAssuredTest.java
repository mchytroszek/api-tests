package com.example.demo;

import com.example.demo.dto.AuctionDTO;
import com.example.demo.dto.OrderDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RestAssuredTest {

    @Test
    public void placeAnOrderStringBody() {

        given()
                .contentType(ContentType.JSON)
                .port(8080)
                .body("{\n" +
                        "    \"auction\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"testItemName\",\n" +
                        "        \"description\": \"testItemDescription\",\n" +
                        "        \"price\": 30.0\n" +
                        "    },\n" +
                        "    \"quantity\": 1\n" +
                        "}")
                .log().all()
                .when()
                .post("order")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("id", is(10));
    }

    @Test
    public void order() {
        OrderDTO orderDTO = OrderDTO.builder()
                .auctionID(1)
                .quantity(1)
                .build();

        given()
                .contentType(ContentType.JSON)
                .port(8080)
                .body(orderDTO)
                .log().all()
                .when()
                .post("order")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("id", is(10));
    }

    @Test
    public void placeAnOrder() {
        //given
        AuctionDTO auctionDTO = AuctionDTO.builder()
                .name("testItemName")
                .description("testItemDescription")
                .price(30)
                .build();
        Integer auctionId = given()
                .contentType(ContentType.JSON)
                .port(8080)
                .body(auctionDTO)
                .log().all()
                .when()
                .post("auction")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().as(AuctionDTO.class).getId();
        //when
        OrderDTO orderDTO = OrderDTO.builder()
                .auctionID(auctionId)
                .quantity(1)
                .build();
        Integer orderId = given()
                .contentType(ContentType.JSON)
                .port(8080)
                .body(orderDTO)
                .log().all()
                .when()
                .post("order")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().as(OrderDTO.class).getId();
        //then
        given()
                .contentType(ContentType.JSON)
                .port(8080)
                .pathParam("orderId", orderId)
                .log().all()
                .when()
                .get("order/{orderId}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    private RequestSpecification prepareRequestSpecification(Object body) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        return requestSpecBuilder
                .setPort(8080)
                .setContentType(ContentType.JSON)
                .setBody(body)
                .log(LogDetail.ALL)
                .build();
    }
}





