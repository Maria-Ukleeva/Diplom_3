import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginApi {
    public static Response response;

    public static void loginUser(User user){
        String email = user.getEmail();
        String password = user.getPassword();
        Credentials credentials = new Credentials(email, password);
        response = given()
                .baseUri(Constants.BASEURL)
                .header("Content-type", "application/json")
                .and()
                .body(credentials)
                .when()
                .post(Constants.LOGIN);
    }

    public static String getAccessToken(){
        return response.then().extract().path("accessToken").toString().substring(7);
    }
}
