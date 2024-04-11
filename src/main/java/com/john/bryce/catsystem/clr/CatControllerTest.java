package com.john.bryce.catsystem.clr;

import com.john.bryce.catsystem.entities.Cat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodType;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CatControllerTest implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/api/cats";

    @Override
    public void run(String... args) throws Exception {
        addNewCat();
        updateCat();
        deleteCat();
        getAllCats();
        getSingleCat();
        getCatByName();
    }

    public void addNewCat() {
        Cat cat1 = new Cat(0, "max", 200, LocalDate.of(1950, 1, 1), null);
        Cat cat2 = new Cat(0, "max1", 222, LocalDate.of(1234, 1, 1), null);
        Cat cat3 = new Cat(0, "max2", 333, LocalDate.of(4321, 1, 1), null);

        ResponseEntity<Void> responseEntity1 = restTemplate.exchange(URL, HttpMethod.POST, new HttpEntity<>(cat1), Void.class);
        ResponseEntity<Void> responseEntity2 = restTemplate.exchange(URL, HttpMethod.POST, new HttpEntity<>(cat2), Void.class);
        ResponseEntity<Void> responseEntity3 = restTemplate.exchange(URL, HttpMethod.POST, new HttpEntity<>(cat3), Void.class);

        System.err.println("Create cat successfully " + responseEntity1.getStatusCode());
        System.err.println("Create cat successfully " + responseEntity2.getStatusCode());
        System.err.println("Create cat successfully " + responseEntity3.getStatusCode());

    }

    public void updateCat() {
        Cat cat = new Cat(1, "max The Rage", 300, LocalDate.of(1950, 1, 1), null);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, new HttpEntity<>(cat), Void.class);
        System.err.println("Update cat successfully " + responseEntity.getStatusCode());
    }

    public void deleteCat() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange(URL + "/1", HttpMethod.DELETE, null, Void.class);
        System.err.println("Delete cat successfully " + responseEntity.getStatusCode());
    }


    public void getAllCats() {
        ResponseEntity<List<Cat>> responseEntity = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {
                }
        );
        List<Cat> cats = responseEntity.getBody();
        System.out.println("All Cats successfully " + responseEntity.getStatusCode());
        System.out.println(cats);
    }

    public void getSingleCat() {
        ResponseEntity<Cat> responseEntity = restTemplate.exchange(URL + "/2", HttpMethod.GET, null, Cat.class
        );
        System.err.println("Get Single cat successfully " + responseEntity.getBody());
    }

    public void getCatByName() {
        ResponseEntity<List<Cat>> responseEntity = restTemplate.exchange(URL + "/start-with?name=M", HttpMethod.GET, null, new ParameterizedTypeReference<List<Cat>>() {
                }
        );
        System.err.println("Get all cats by name successfully " + responseEntity.getBody());
    }
}
