package com.john.bryce.catsystem.sevices;

import com.john.bryce.catsystem.entities.Cat;
import com.john.bryce.catsystem.entities.Toy;
import com.john.bryce.catsystem.exceptions.CatsCustomException;
import com.john.bryce.catsystem.repository.CatRepository;
import com.john.bryce.catsystem.repository.ToyRepository;
import jdk.jfr.Registered;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {
//     No Good!
//    @Autowired
//    private CatRepository catRepository;

    //    Good
    private final CatRepository catRepository;

    @Transactional
    public void addCat(Cat cat) throws CatsCustomException {
        if (cat.getName().length() > 50) {
            throw new CatsCustomException("Cat name should be lower then 50 characters");
        }
        catRepository.save(cat);
    }


    @Transactional
    public void updateCat(Cat cat) throws CatsCustomException {

        // Perform the update operation
        Cat existingCat = catRepository.findById(cat.getId()).orElseThrow(() -> new CatsCustomException("Failed to find cat by id: " + cat.getId()));
        // Update cat properties
        if (cat.getName().length() > 50) {
            throw new CatsCustomException("Cat name should be lower then 50 characters");
        }

        existingCat.setName(cat.getName());
        existingCat.setBirthday(cat.getBirthday());
        existingCat.setWeight(cat.getWeight());
//        if (cat.getToys() != null && (!cat.getToys().isEmpty())) {
//            for (Toy toy : cat.getToys()) {
//                toy.setCat(cat);
//            }
//            existingCat.setToys(cat.getToys());
//        }
        // Update other properties as needed

        // Save the updated cat
//        catRepository.save(existingCat);
    }

    @Transactional
    public void deleteCat(int catId) throws CatsCustomException {
        if (!catRepository.existsById(catId)) {
            throw new CatsCustomException("There is no cat found with this id, please try again");
        }
        catRepository.deleteById(catId);
    }

    public List<Cat> getAllCats() throws CatsCustomException {
        return catRepository.findAll();
    }

    public Cat getSingleCat(int catId) throws CatsCustomException {
        return catRepository.findById(catId).orElseThrow(() -> new CatsCustomException("There is no cat found with this id, please try again"));
    }

    public List<Cat> getByNameAndWeight(String name, double weight) throws CatsCustomException {
        if (weight < 0) {
            throw new CatsCustomException("There is not a valid weight, please try again");
        }
        if (name.length() > 50) {
            throw new CatsCustomException("Cat name should be lower then 50 characters");
        }
        return catRepository.getByNameAndWeight(name, weight);
    }

    public List<Cat> getByNameOrWeight(String name, double weight) throws CatsCustomException {
        if (weight < 0) {
            throw new CatsCustomException("There is not a valid weight, please try again");
        }
        if (name.length() > 50) {
            throw new CatsCustomException("Cat name should be lower then 50 characters");
        }
        return catRepository.getByNameOrWeight(name, weight);
    }

    public List<Cat> getCatsByWeightASC() {
        return catRepository.findAllByOrderByWeightAsc();
    }

    public List<Cat> getCatsByWeightDESC() {
        return catRepository.findAllByOrderByWeightDesc();
    }

    public List<Cat> findByNameStartingWith(String startWith) throws CatsCustomException {
        if (startWith.length() > 50) {
            throw new CatsCustomException("Cat name should be lower then 50 characters");
        }
        return catRepository.findByNameStartingWith(startWith);
    }

    public double getAVGWeightOfCats() throws CatsCustomException {
        return catRepository.getAVGWeightOfCats();
    }


}
