package org.example;


import org.example.config.Config;
import org.example.dao.*;
import org.example.entity.Part;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Config.class);
        CarDao carDao = context.getBean(CarDao.class);
        OwnerDao ownerDao = context.getBean(OwnerDao.class);
        PartDao partDao = context.getBean(PartDao.class);
        PassCarDao passCarDao = context.getBean(PassCarDao.class);
        TruckDao truckDao = context.getBean(TruckDao.class);

//        partDao.savePart("abs");
//        partDao.savePart("rear camera");
//        partDao.savePart("trailer");
//        partDao.savePart("van");
//        partDao.savePart("conditioner");
//        partDao.savePart("climate control");
//
//        ownerDao.saveOwner("Andreev", null);
//        ownerDao.saveOwner("Kuzmina", null);

//        truckDao.saveTruck("man", "4562", 2020, ownerDao.getOwnerById(7), new HashSet<>(Arrays.asList(partDao.getPartById(1), partDao.getPartById(4))), "20");
//        truckDao.saveTruck("kamaz", "AZ", 2018, ownerDao.getOwnerById(8), new HashSet<>(Arrays.asList(partDao.getPartById(1), partDao.getPartById(3))), "20");
//        passCarDao.savePassengerCar("toyota", "raum", 2000, ownerDao.getOwnerById(8), new HashSet<>(Arrays.asList(partDao.getPartById(1), partDao.getPartById(6))), "state wagon");
//        passCarDao.savePassengerCar("lada", "vaz2103", 2000, ownerDao.getOwnerById(7), new HashSet<>(Arrays.asList(partDao.getPartById(1), partDao.getPartById(5))), " wagon");
//        ownerDao.updateOwnerById(7, new HashSet<>(Arrays.asList(cartDao.getCartById(1), carDao.getCarById(6)) )

        System.out.println(carDao.getAllCars());
        System.out.println(truckDao.getAllTrucks());
        System.out.println(partDao.getAllParts());
        System.out.println(partDao.getPartById(1));



    }
}