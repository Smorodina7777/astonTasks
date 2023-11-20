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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Car {
    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    private  int id;
    private  String mark;
    private  String model;
    private  int year;
    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="owner_id")
    private Owner owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="car_part",
            joinColumns=  @JoinColumn(name="car_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="part_id", referencedColumnName="id") )
    private Set<Part> parts = new HashSet<>();

}
