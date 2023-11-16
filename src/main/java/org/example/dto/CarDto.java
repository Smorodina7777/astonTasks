package org.example.dto;



public class CarDto {
 private  int id;
 private  String mark;
 private  String model;
 private  int year;
private  int owner_id;

 public CarDto(int id, String mark, String model, int year, int owner_id) {
  this.id = id;
  this.mark = mark;
  this.model = model;
  this.year = year;
  this.owner_id = owner_id;
 }

 public CarDto() {

 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getMark() {
  return mark;
 }

 public void setMark(String mark) {
  this.mark = mark;
 }

 public String getModel() {
  return model;
 }

 public void setModel(String model) {
  this.model = model;
 }

 public int getYear() {
  return year;
 }

 public void setYear(int year) {
  this.year = year;
 }

 public int getOwner_id() {
  return owner_id;
 }

 public void setOwner_id(int owner_id) {
  this.owner_id = owner_id;
 }
}
