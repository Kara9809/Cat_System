package com.john.bryce.catsystem.repository;

import com.john.bryce.catsystem.entities.Toy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepository extends CrudRepository <Toy, Integer> {

}
