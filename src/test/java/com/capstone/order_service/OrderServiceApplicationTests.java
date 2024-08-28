package com.capstone.order_service;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mysqlContainer =
		 new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	public void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
	}

	static {
		mysqlContainer.start();
	}

	@Test
	void shouldSubmitOrder() {
		String submitOrderResponse = """
				{
					"orderNumber": "123456789",
					"skuCode": "123456789",
					"price": 1000,
					"quantity": 1
				}
				""";

		var response = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(submitOrderResponse)
				.when()
				.post("/api/order")
				.then().log().all()
				.statusCode(201)
				.extract()
				.body().asString();

		assertThat(response).isEqualTo("Order successfully created");

	}

}
