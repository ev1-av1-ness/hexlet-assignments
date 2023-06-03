package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    static void beforeAll() {
        app = App.getApp();
        app.start();
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("users");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateUserCorrectData() {
        String firstName = "Elizaveta";
        String lastName = "Zonova";
        String email = "zonova_1@list.ru";
        String password = "12345678910";
        HttpResponse<String> response = Unirest.post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(302);

        User user = new QUser().lastName.equalTo("Zonova").findOne();
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getLastName()).isEqualTo(lastName);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    void testCreateUserIncorrectData() {
        String firstName = "Elizaveta";
        String lastName = "";
        String email = "zonova_1.ru";
        String password = "1";
        HttpResponse<String> response = Unirest.post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);

        User user = new QUser().email.equalTo("zonova_1.ru").findOne();
        assertThat(user).isNull();

        assertThat(response.getBody()).contains("zonova_1.ru");
        assertThat(response.getBody()).contains("Elizaveta");
        assertThat(response.getBody()).contains("Фамилия не должна быть пустой");
        assertThat(response.getBody()).contains("Пароль должен содержать не менее 4 символов");
        assertThat(response.getBody()).contains("Должно быть валидным email");
    }
    // END
}
