package org.example.dao;

import org.example.entity.Owner;
import org.example.entity.Part;
import org.example.entity.Truck;

import java.util.List;
import java.util.Set;

public interface TruckDao {
    public void saveTruck(String mark, String model, int year, Owner owner, Set<Part> parts, String tonnage);

    public void removeTruckById(int id);

    public Truck getTruckById(int id);

    public List<Truck> getAllTrucks();

    public void cleanTruckTable();
}
