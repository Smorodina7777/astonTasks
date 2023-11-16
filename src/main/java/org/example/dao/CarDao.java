package org.example.dao;

import org.example.PostgreSQLConnection;
import org.example.dto.CarDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    public void createCarTable() {
        try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Car(id int primary key , mark varchar(40), " +
                    "model varchar(40), year int, owner_id int )");
            System.out.println("Нам удалось создать таблицу с машинами");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void dropCarTable() {
        try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS Car");
            System.out.println("Удалось удалить таблицу с машинами");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void cleanCarTable() {
        final String DELETE_ALL_CARS = "DELETE FROM Car";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_CARS)) {
            int rez = preparedStatement.executeUpdate();
            if (rez != 0) {
                System.out.println("Нам удалось удалить " + rez + " машины");
            } else {
                System.out.println("Таблица была пуста");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveCar(int id, String mark, String model, int year, int owner_id) {
        final String INSERT_NEW_CAR = "INSERT INTO Car(id, mark, model, year, owner_id)"
                + " VALUES(?,?,?,?,?)";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_CAR)) {
            statement.setInt(1, id);
            statement.setString(2, mark);
            statement.setString(3, model);
            statement.setInt(4, year);
            statement.setInt(5, owner_id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCarById(int id) {
        final String DELETE_CAR = "DELETE FROM Car WHERE id = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CAR)) {
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Удалось удалить автомобиль:" + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public CarDto getCarById(int id) {
        final String GET_CAR = "SELECT * FROM Car WHERE id = ?";
        CarDto carDto = null;
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String mark = resultSet.getString("mark");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                int owner_id = resultSet.getInt("owner_id");
                carDto = new CarDto(id, mark, model, year, owner_id);
            }
            return carDto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
