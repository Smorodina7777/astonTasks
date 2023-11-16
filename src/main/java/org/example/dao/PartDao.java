package org.example.dao;

import org.example.PostgreSQLConnection;
import org.example.dto.PartDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartDao {
    public void createPartTable() {
        try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Part(id int primary key auto_increment, title varchar(40))");
            System.out.println("Нам удалось создать таблицу с запчастями");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void dropPartTable() {
        try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS Part");
            System.out.println("Удалось удалить таблицу с запчастями");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void cleanPartTable() {
        final String DELETE_ALL_PartS = "DELETE FROM Part";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_PartS)) {
            int rez = preparedStatement.executeUpdate();
            if (rez != 0) {
                System.out.println("Нам удалось удалить " + rez + " запчасти");
            } else {
                System.out.println("Таблица была пуста");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void savePart(String title) {
        final String INSERT_NEW_Part = "INSERT INTO Part(title)"
                + " VALUES(?)";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_Part)) {
            statement.setString(1, title);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePartById(int id) {
        final String DELETE_Part = "DELETE FROM Part WHERE id = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_Part)) {
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Удалось удалить запчасть:" + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public PartDto getPartById(int id) {
        final String GET_Part = "SELECT * FROM Part WHERE id = ?";
        PartDto PartDto = null;
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_Part)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                PartDto = new PartDto(id, title);
            }
            return PartDto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<PartDto> getAllParts() {
        List<PartDto> Parts = new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Part");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                PartDto Part = new PartDto(id, title);
                Parts.add(Part);
            }
            return Parts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
