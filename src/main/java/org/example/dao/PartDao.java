package org.example.dao;

import org.example.entity.Car;
import org.example.entity.Part;

import java.util.List;
import java.util.Set;

public interface PartDao {
    public void savePart(String title, Set<Car> carSet);

    public void savePart(String title);

    public void removePartById(int id);

    public Part getPartById(int id);

    public List<Part> getAllParts();

    public void cleanPartTable();
}
