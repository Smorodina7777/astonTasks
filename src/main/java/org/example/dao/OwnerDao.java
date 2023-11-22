package org.example.dao;

import org.example.entity.Car;
import org.example.entity.Owner;

import java.util.List;
import java.util.Set;

public interface OwnerDao {
    public void saveOwner(String name, Set<Car> cars);
    public Owner getOwnerById(int id);
    public Owner updateOwnerById(int id, Set<Car> cars);
    public List<Owner> getAllOwners();
}
