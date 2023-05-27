import java.util.Random;

public class TestDataGenerator {

    public static User getData(){
        String name = "mu" + new Random().nextInt(10000);
        String email = name + "@ya.ru";
        String password = Password.PASSWORD;
        return new User(email, password, name);

    }
}
