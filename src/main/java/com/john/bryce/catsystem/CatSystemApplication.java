package com.john.bryce.catsystem;

import com.john.bryce.catsystem.entities.Cat;
import com.john.bryce.catsystem.entities.Toy;
import com.john.bryce.catsystem.exceptions.CatsCustomException;
import com.john.bryce.catsystem.repository.CatRepository;
import com.john.bryce.catsystem.repository.ToyRepository;
import com.john.bryce.catsystem.sevices.CatService;
import jakarta.persistence.OneToMany;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CatSystemApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CatSystemApplication.class, args);
        System.out.println("Cat System is running...");


//        CatRepository catRepository = ctx.getBean(CatRepository.class);
//        ToyRepository toyRepository = ctx.getBean(ToyRepository.class);
//
//        Cat cat1 = Cat.builder().id(0).name("Fluffy").weight(7.12).birthday(LocalDate.of(2020, 1, 1)).build();
//        Toy toy1 = Toy.builder().id(0).name("Red Ball").cat(cat1).build();
//        Toy toy2 = Toy.builder().id(0).name("Blue Ball").cat(cat1).build();
//
////        cat1.setToys(List.of(toy1, toy2));
//        catRepository.save(cat1);
//        toyRepository.saveAll(List.of(toy1, toy2));

        CatService catService = ctx.getBean(CatService.class);

        Cat cat1 = Cat.builder().id(0).name("Fluffy").weight(10).birthday(LocalDate.of(2020, 1, 1)).build();
        Toy toy1 = Toy.builder().id(0).name("Red ball").cat(cat1).build();
        cat1.setToys(List.of(toy1));
        Cat cat2 = Cat.builder().id(0).name("Moshe").weight(20).birthday(LocalDate.of(2021, 1, 1)).build();
        Toy toy2 = Toy.builder().id(0).name("Green ball").cat(cat2).build();
        cat2.setToys(List.of(toy2));
        Cat cat3 = Cat.builder().id(0).name("Mitzhi").weight(30).birthday(LocalDate.of(2022, 1, 1)).build();
        Cat cat4 = Cat.builder().id(0).name("Floffy").weight(40).birthday(LocalDate.of(2023, 1, 1)).build();



        try {
            catService.addCat(cat1);
            catService.addCat(cat2);
            catService.addCat(cat3);
            catService.addCat(cat4);
            cat1.setName("Fluffy2");
            cat1.setWeight(100);
            catService.updateCat(cat1);
            catService.deleteCat(cat3.getId());
            System.out.println(catService.getAVGWeightOfCats());
            System.out.println(catService.getCatsByWeightASC());

//            cat1.setWeight(8.13);
//            cat1.setId(2);
//            catService.updateCat(cat1);
        } catch (CatsCustomException e) {
            throw new RuntimeException(e);
        }
    }


}


