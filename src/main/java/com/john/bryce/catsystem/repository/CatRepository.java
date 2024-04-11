package com.john.bryce.catsystem.repository;

import com.john.bryce.catsystem.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@50%
@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {

    //    40% Driven Query
    List<Cat> findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);

    List<Cat> getByNameAndWeight(String name, double weight);

    List<Cat> getByNameOrWeight(String name, double weight);

    List<Cat> findAllByOrderByWeightAsc();

    List<Cat> findAllByOrderByWeightDesc();

    List<Cat> findByNameStartingWith(String startWith);


//    10% native query
//    @Query(value = "cat-system-ks.cats;", nativeQuery = true)
//    List<Cat> getAllCats();

    @Query(value = "cat-system-ks.cats WHERE id = ?;", nativeQuery = true)
    Cat getSingleCat();

//    @Query(value = "SELECT * FROM cat-system-ks.cats ORDER BY weight;", nativeQuery = true)
//    List<Cat> getCatsByWeightASC();
//
//    @Query(value = "SELECT * FROM cat-system-ks.cats ORDER BY weight DESC;", nativeQuery = true)
//    List<Cat> getCatsByWeightDESC();

    @Query(value = "SELECT AVG(weight) FROM cats;", nativeQuery = true)
    double getAVGWeightOfCats();


}

