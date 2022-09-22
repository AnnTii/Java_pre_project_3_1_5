package spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import spring_boot.model.User;

@RestController
@RequestMapping()
public class RestApiController {

    private final RestTemplate restTemplate;

    @Autowired
    public RestApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/api")
    public String allInOne() {

        String url = "http://94.198.50.185:7081/api/users";

//////// cookies

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String cookies = responseEntity.getHeaders().getFirst("Set-Cookie");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookies);

//////// requestPost

        User userPost = new User(3L,"James", "Brown", (byte) 12);

        HttpEntity<User> requestPost = new HttpEntity<>(userPost, headers);
        ResponseEntity<String> responsePost = restTemplate.exchange(url, HttpMethod.POST, requestPost, String.class);

//////// requestPut

        User userPut = new User(3L,"Thomas", "Shelby", (byte) 12);

        HttpEntity<User> requestPut = new HttpEntity<>(userPut, headers);
        ResponseEntity<String> responsePut = restTemplate.exchange(url, HttpMethod.PUT, requestPut, String.class);

//////// requestDel

        HttpEntity<Integer> requestDel = new HttpEntity<>(headers);
        ResponseEntity<String> responseDel = restTemplate.exchange(url + "/3", HttpMethod.DELETE, requestDel, String.class);

        return "Cookies : " + cookies + ", Code 1 : " + responsePost.getBody() + ", Code 2 : " + responsePut.getBody() + ", Code 3 : " + responseDel.getBody() ;
    }
}