package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="car_part",
            joinColumns=  @JoinColumn(name="part_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="car_id", referencedColumnName="id") )
    @Column(nullable = true)
    private Set<Car> carSet = new HashSet<>();


    public Part(String title, Set<Car> carSet) {
        this.title = title;
        this.carSet = carSet;
    }

    public Part(String title) {
        this.title = title;
    }
}
