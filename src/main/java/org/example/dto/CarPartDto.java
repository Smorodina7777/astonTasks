package org.example.dto;



public class CarPartDto {
 private int car_id;
 private int part_id;

 public CarPartDto(int car_id, int part_id) {
  this.car_id = car_id;
  this.part_id = part_id;
 }

 public int getCar_id() {
  return car_id;
 }

 public void setCar_id(int car_id) {
  this.car_id = car_id;
 }

 public int getPart_id() {
  return part_id;
 }

 public void setPart_id(int part_id) {
  this.part_id = part_id;
 }
}
