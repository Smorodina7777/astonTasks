package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="car_part",
            joinColumns=  @JoinColumn(name="part_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="car_id", referencedColumnName="id") )
    private Set<Car> carSet = new HashSet<>();

}
