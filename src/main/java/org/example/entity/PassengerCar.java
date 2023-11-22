package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PassengerCar extends Car{

    private int id;
    private String bodyType;

    public PassengerCar(String mark, String model, int year, Owner owner, Set<Part> parts, String bodyType) {
        super(mark, model, year, owner, parts);
        this.bodyType = bodyType;
    }
}
