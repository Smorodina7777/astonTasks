package org.example;

import org.example.dao.CarDao;
import org.example.dao.OwnerDao;
import org.example.dto.CarDto;

public class App
{
    public static void main( String[] args )  {
        CarDao carDao = new CarDao();
        carDao.dropCarTable();
        carDao.createCarTable();
        carDao.saveCar(1, "Lada", "Granta", 2020, 1);
        carDao.saveCar(2, "Toyota", "Raum", 2000, 2);
        carDao.saveCar(3, "Toyota", "Harrier", 2001, 3);
        carDao.saveCar(4, "Nissan", "Infinity",  2023, 3);
//        System.out.println(carDao.getAllCars());
        carDao.deleteCarById(1);
//        System.out.println(carDao.getAllCars());
        CarDto carDto = carDao.getCarById(3);
        System.out.println("Попытка найти автомобиль " + 4);
        System.out.println(carDto);
//        System.out.println(carDao.getAllCars());

        OwnerDao ownerDao = new OwnerDao();
        ownerDao.dropOwnerTable();
        ownerDao.createOwnerTable();
        ownerDao.saveOwner(1, "Иванов");
        ownerDao.saveOwner(2, "Петров");
        ownerDao.saveOwner(3, "Сидоров");
        System.out.println(ownerDao.getAllOwners().toString());


        }
    }

