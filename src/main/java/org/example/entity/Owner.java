package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany
    @Column(nullable = true)
    private Set<Car> cars = new HashSet<>();

    public Owner(String name, Set<Car> cars) {
        this.name = name;
        this.cars = cars;
    }
}
