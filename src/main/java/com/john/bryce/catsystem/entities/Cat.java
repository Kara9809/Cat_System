package com.john.bryce.catsystem.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Entity is a class that represents a table in a database
public class Cat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private double weight;
  @JsonFormat(pattern = "d/M/yyyy")
  private LocalDate birthday;

  // One cat can have many toys
  // One-to-many relationship
  @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  @JsonIgnore
  private List<Toy> toys;


}


