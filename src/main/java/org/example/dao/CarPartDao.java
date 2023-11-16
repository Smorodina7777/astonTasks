package org.example.dao;

import org.example.PostgreSQLConnection;
import org.example.dto.CarPartDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarPartDao {
    public void createCarPartTable() {
        try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS CarPart(car_id int foreign key (car_id) references car(id), "+
                    "part_id int foreign key (part_id) references part(id))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void dropCarPartTable() {
        try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS CarPart");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void cleanCarPartTable() {
        final String DELETE_ALL_CarPartS = "DELETE FROM CarPart";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_CarPartS)) {
            int rez = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveCarPart(int car_id, int part_id) {
        final String INSERT_NEW_CarPart = "INSERT INTO CarPart(car_id, part_id)"
                + " VALUES(?, ?)";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_CarPart)) {
            statement.setInt(1, car_id);
            statement.setInt(2, part_id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<CarPartDto> getAllCarParts() {
        List<CarPartDto> CarParts = new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CarPart");
            while (resultSet.next()) {
                int car_id = resultSet.getInt("car_id");
                int part_id = resultSet.getInt("part_id");
                CarPartDto CarPart = new CarPartDto(car_id, part_id);
                CarParts.add(CarPart);
            }
            return CarParts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
