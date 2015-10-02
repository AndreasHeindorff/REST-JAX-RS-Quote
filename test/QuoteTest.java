/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.JsonObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import com.jayway.restassured.parsing.Parser;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author Andreas
 */
public class QuoteTest {

    public QuoteTest() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8080";
        defaultParser = Parser.JSON;
        basePath = "/REST_JAX_RS_Quote/api/quotes";
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testGet() {
        when()
                .get("/1")
                .then().
                statusCode(200).
                body("quote", equalTo("Friends are kisses blown to us by angels"));
    }

    @Test
    public void testGetAll() {
        when()
                .get(baseURI + basePath)
                .then().
                statusCode(200).
                body("quote", equalTo("Friends are kisses blown to us by angels\",\"Do not take life too seriously. You will never get out of it alive\",\"Behind every great man, is a woman rolling her eyes"));
    }
    
        @Test
    public void testAddQuote() {
        
        JsonObject obj = new JsonObject();
        obj.addProperty("quote", "A testing quote");
        
        given()
            .contentType("application/json")
            .auth().preemptive().basic("test", "test")
            .body(obj)
        .when()
            .post(baseURI + basePath)
        .then()
            .statusCode(403)
            .body("quote", equalTo("A testing quote"));
    }

}
