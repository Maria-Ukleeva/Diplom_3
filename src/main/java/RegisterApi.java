import com.github.javafaker.Faker;
import io.restassured.response.Response;
import lombok.Getter;

import static io.restassured.RestAssured.given;

public class RegisterApi {

    @Getter
    public static String username;
    @Getter
    public static String email;
    @Getter
    public static String password;
    public static Response response;
    public static String accessToken;


    public static User createUser() {
        Faker faker = new Faker();
        username = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password(6, 10);
        return new User(email, password, username);
    }

    public static void registerUser(User user) {
        response = given()
                .baseUri(Constants.BASEURL)
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(Constants.REGISTER);
        accessToken = response.then().extract().path("accessToken").toString().substring(7);
    }

    public static void deleteUser() {
        try {
            System.out.println(accessToken);
            given()
                    .baseUri(Constants.BASEURL)
                    .header("Content-type", "application/json")
                    .auth()
                    .oauth2(accessToken)
                    .delete(Constants.USER);
            System.out.println( "Юзер удален");
        } catch (NullPointerException nullPointerException) {
            System.out.println("Юзер не удален");
        }
    }

    public static void deleteUser(String token) {
            given()
                    .baseUri(Constants.BASEURL)
                    .header("Content-type", "application/json")
                    .auth()
                    .oauth2(token)
                    .delete(Constants.USER);
            System.out.println("Юзер удален");

    }
}