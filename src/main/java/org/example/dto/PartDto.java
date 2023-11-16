package org.example.dto;



public class PartDto {
 private int id;
 private String title;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public PartDto(int id, String title) {
  this.id = id;
  this.title = title;
 }
}
