package com.john.bryce.catsystem.controllers;

import com.john.bryce.catsystem.entities.Cat;
import com.john.bryce.catsystem.entities.Toy;
import com.john.bryce.catsystem.exceptions.CatsCustomException;
import com.john.bryce.catsystem.sevices.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cats")
@CrossOrigin
@RequiredArgsConstructor
public class CatController {


    private final CatService catService;


//    // RequestBody
    // RequestBody => Object
    // RequestParam => name, weight... id/uuid https//localhost:8080/api/cats/?name="mitizi"&weight="";
//    PathVariable => id/uuid https//localhost:8080/api/cats/1


    // 200  - 200 Get | Void 204
    // 300
    // 400
    // 500
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCat(@RequestBody Cat cat) throws CatsCustomException {
        catService.addCat(cat);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCat(@RequestBody Cat cat) throws CatsCustomException {
        catService.updateCat(cat);
    }

    @DeleteMapping(value = "/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCat(@PathVariable int catId) throws CatsCustomException {
        catService.deleteCat(catId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // לא חייב כי זה הברירת מחדל
    public List<Cat> getAllCats() throws CatsCustomException {
        return catService.getAllCats();
    }


    @GetMapping(value = "/{catId}")
    @ResponseStatus(HttpStatus.OK) // לא חייב כי זה הברירת מחדל
    public Cat getSingleCat(@PathVariable int catId) throws CatsCustomException {
        return catService.getSingleCat(catId);
    }

    @GetMapping(value = "/start-with")
    public List<Cat> findByNameStartingWith(@RequestParam String name) throws CatsCustomException {
        return catService.findByNameStartingWith(name);
    }

}
