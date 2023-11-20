package org.example;


import org.example.dao.*;

public class App
{
    public static void main( String[] args )
    {
        CarDao carDao = new CarDao();
        OwnerDao ownerDao = new OwnerDao();
        PartDao partDao = new PartDao();
        PassCarDao passCarDao = new PassCarDao();
        TruckDao truckDao = new TruckDao();

//        partDao.savePart("steering wheel");
//        partDao.savePart(" heated seats");
//        partDao.savePart("abs");
//        partDao.savePart("climate control");
//        partDao.savePart("air conditioning");
//        partDao.savePart("automatic transmission");
//        partDao.savePart("manual transmission");
//        partDao.savePart("rear view camera");
//        partDao.savePart("trailer");
//        partDao.savePart("van");
//
//        passCarDao.savePassengerCar("toyota", "raum", 2000, "station wagon");
//        passCarDao.savePassengerCar("lada", "2101", 2000, "sedan");
//        truckDao.saveTruck("man", "ex", 2020, "10");
//
//        ownerDao.saveOwner("Ivanov");
//        ownerDao.saveOwner("Petrov");


        System.out.println(carDao.getAllCars());
        System.out.println(truckDao.getAllTrucks());
        System.out.println(partDao.getAllParts());





    }
}
