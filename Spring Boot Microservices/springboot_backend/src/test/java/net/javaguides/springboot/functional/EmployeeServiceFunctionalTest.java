package net.javaguides.springboot.functional;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class EmployeeServiceFunctionalTest {

    @Test
    public void testAddEmp(){

        ExtractableResponse<Response> response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\"firstname\": \"Sahal\"," +
                        " \"lastname\":\"Yousuf\"," +
                        " \"emailid\":\"sahal.yousuf10@gmail.com\"," +
                        " \"departmentId\":\"13\"}") // try to get this body from a file (file.json)
                .when()
                .post("http://localhost:8083/employees/add")
                .then()
                .statusCode(201)
                .extract();
    }

    @Test
    public void testAddAllEmp(){

        ExtractableResponse<Response> response = RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .contentType(ContentType.JSON)
                .body("[{\"firstname\": \"Sahal\", \"lastname\":\"Yousuf\", \"emailid\":\"sahal.yousuf10@gmail.com\", \"departmentId\":\"13\"}," +
                        "{\"firstname\": \"Saib\", \"lastname\":\"Yousuf\", \"emailid\":\"saib.yousuf10@gmail.com\", \"departmentId\":\"14\"}," +
                        "{\"firstname\": \"Mahad\", \"lastname\":\"Khan\", \"emailid\":\"mahad.khan@gmail.com\", \"departmentId\":\"15\"}," +
                        "{\"firstname\": \"Muhammad\", \"lastname\":\"Ahsan\", \"emailid\":\"ahsan@gmail.com\", \"departmentId\":\"12\"}," +
                        "{\"firstname\": \"Sameer\", \"lastname\":\"Ahmed\", \"emailid\":\"sameerahmed@gmail.com\", \"departmentId\":\"16\"}]") // try to get this body from a file (file.json)
                .when()
                .post("http://localhost:8083/employees/all")
                .then()
                .statusCode(200)
                .extract();
    }

    @Test
    public void  testGetEmp(){

        RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .when()
                .get("http://localhost:8083/employees/api/49")
                .then()
                .body("id", Matchers.notNullValue())
                .body("firstname", Matchers.equalTo("Sahal"))
                .body("lastname", Matchers.equalTo("Yousuf"))
                .body("emailid", Matchers.equalTo("sahal.yousuf10@gmail.com"))
                .body("departmentId", Matchers.equalTo(13));
    }

    @Test
    public void  testGetAllEmp(){

        RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .when()
                .get("http://localhost:8083/employees")
                .then()
                .body("id[8]", Matchers.notNullValue())
                .body("firstname[8]", Matchers.equalTo("Asad"))
                .body("lastname[8]", Matchers.equalTo("Khan"))
                .body("emailid[8]", Matchers.equalTo("asad.khan@gmail.com"))
                .body("departmentId[8]", Matchers.equalTo(15));
    }

    @Test
    public void testDeleteEmp(){

        RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .when()
                .delete("http://localhost:8083/employees/55")
                .then()
                .statusCode(204);
    }

    @Test
    public void testDeleteAllEmp(){

        RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .when()
                .delete("http://localhost:8083/employees/deleteAll")
                .then()
                .statusCode(204);
    }

    @Test
    public void testUpdateEmp(){

        ExtractableResponse<Response> response = RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .contentType(ContentType.JSON)
                .body("{\"firstname\": \"Haris\"," +
                        " \"lastname\":\"Khurram\"," +
                        " \"emailid\":\"haris.khurram10@gmail.com\"," +
                        " \"departmentId\":\"14\"}") // try to get this body from a file (file.json)
                .when()
                .put("http://localhost:8083/employees/63")
                .then()
                .statusCode(200)
                .extract();
    }

    @Test
    public void  testGetEmpWithDep(){

        RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .when()
                .get("http://localhost:8083/employees/64")
                .then()
                .body("id", Matchers.notNullValue())
                .body("firstname", Matchers.equalTo("Mahad"))
                .body("lastname", Matchers.equalTo("Khan"))
                .body("emailid", Matchers.equalTo("mahad.khan@gmail.com"))
                .body("departmentId", Matchers.equalTo(15))
                .body("departmentId", Matchers.equalTo(15))
                .body("depName", Matchers.equalTo("Q/A-Automation"))
                .body("depAddress", Matchers.equalTo("Qasr-e-Shereen-Bidg"))
                .body("depCode", Matchers.equalTo(1104))
        ;

    }
}
