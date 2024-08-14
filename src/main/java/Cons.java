import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.stream.Collectors;


public class Cons {

    /**
    *   Сохранить пользователя с id = 3, name = James, lastName = Brown, age = на ваш выбор. В случае успеха вы получите первую часть кода.

        Изменить пользователя с id = 3. Необходимо поменять name на Thomas, а lastName на Shelby. В случае успеха вы получите еще одну часть кода.

        Удалить пользователя с id = 3. В случае успеха вы получите последнюю часть кода.
    * */

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://94.198.50.185:7081/api/users";
        String delete_url = "http://94.198.50.185:7081/api/users/3";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", restTemplate.headForHeaders(url).getFirst("Set-Cookie"));

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append(saveUser(headers, url, restTemplate, new User(3L, "James", "Brown", (byte)1)))
                .append(updateUser(headers, url, restTemplate, new User(3L, "Thomas", "Shelby", (byte)1)))
                .append(deleteUser(headers, delete_url, restTemplate));

        System.out.println(stringBuilder);

    }

    public static String saveUser(HttpHeaders headers, String url, RestTemplate restTemplate, User user) {

       return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(user, headers), String.class).getBody();

    }

    public static String updateUser(HttpHeaders headers, String url, RestTemplate restTemplate, User user) {

        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(user, headers), String.class).getBody();
    }

    public static String deleteUser(HttpHeaders headers, String url, RestTemplate restTemplate) {

        return restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(headers), String.class).getBody();
    }
}
