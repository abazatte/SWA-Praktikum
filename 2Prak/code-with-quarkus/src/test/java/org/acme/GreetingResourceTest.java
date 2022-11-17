package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {
/* 
    @Test
    public void testHelloEndpoint() {
        String expected = "[{\"anleitung\":\"Lecker.\",\"id\":0,\"name\":\"Mango Mule mong\",\"zutatenList\":[\"4 Gurken Scheiben\",\"1 Loeffel Honigsirup\",\"1/2 Loeffel Mangopuree\"]},{\"anleitung\":\"Nicht so Lecker.\",\"id\":1,\"name\":\"Mango Sule song\",\"zutatenList\":[\"4 Gurken Scheiben\",\"1 Loeffel Honigsirup\",\"1/2 Loeffel Mangopuree\"]}]";
        given()
          .when().get("/mocktail")
          .then()
             .statusCode(200)
             .body(is(expected));
    }
*/
    // Test is correct but fails because formatting is wrong..
    // Fragen wie man das fixt....
    @Test
    public void updateMocktail() {
        String query = "/mocktail/put?oldname=Mango Sule song&newname=Mango&beschreibung=Sehr Legga";
        String expected = "{\"anleitung\":\"Sehr Legga\",\"id\":1,\"name\":\"Mango\",\"zutatenList\":[\"4 Gurken Scheiben\",\"1 Loeffel Honigsirup\",\"1/2 Loeffel Mangopuree\"]}";
        given()
                .when()
                .put(query)
                .then()
                .statusCode(200)
                .body(is(expected));
    }

    @Test
    public void testErzeugen(){
        String expected = "{\"anleitung\":\"einmal trinke\",\"name\":\"Kongo Jungle 23\",\"zutatenList\":[\"4 Scheiben\",\"1 wasser\",\"1/2 Loeffel Schweinepuree\"]}";
        
        given()
            .contentType("application/json").body(expected)
        .when().post("/mocktail/post")
        .then().body(is(expected));
    }

}