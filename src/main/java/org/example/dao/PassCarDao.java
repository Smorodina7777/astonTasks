package org.example.dao;

import org.example.entity.Owner;
import org.example.entity.Part;
import org.example.entity.PassengerCar;

import java.util.List;
import java.util.Set;

public interface PassCarDao {
    public void savePassengerCar(String mark, String model, int year, Owner owner, Set<Part> parts, String bodyType);



    public void removePassengerCarById(int id);


    public PassengerCar getPassById(int id);


    public List<PassengerCar> getAllPassengerCars();


    public void cleanPassengerCarTable();

}
