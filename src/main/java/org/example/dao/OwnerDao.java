package org.example.dao;

import org.example.PostgreSQLConnection;
import org.example.dto.OwnerDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerDao {
    public void createOwnerTable() {
        try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Owner(id int primary key, name varchar(40))");
            System.out.println("Нам удалось создать таблицу с собственниками");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void dropOwnerTable() {
        try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS Owner");
            System.out.println("Удалось удалить таблицу с собственниками");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void cleanOwnerTable() {
        final String DELETE_ALL_OWNERS = "DELETE FROM Owner";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_OWNERS)) {
            int rez = preparedStatement.executeUpdate();
            if (rez != 0) {
                System.out.println("Нам удалось удалить " + rez + " собственников");
            } else {
                System.out.println("Таблица была пуста");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveOwner(int id, String name) {
        final String INSERT_NEW_OWNER = "INSERT INTO Owner(id,name)"
                + " VALUES(?,?)";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_OWNER)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteOwnerById(int id) {
        final String DELETE_OWNER = "DELETE FROM Owner WHERE id = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_OWNER)) {
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Удалось удалить собственника:" + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public OwnerDto getOwnerById(int id) {
        final String GET_OWNER = "SELECT * FROM Owner WHERE id = ?";
        OwnerDto OwnerDto = null;
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_OWNER)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                OwnerDto = new OwnerDto(id, name);
            }
            return OwnerDto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<OwnerDto> getAllOwners() {
        List<OwnerDto> Owners = new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Owner");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                OwnerDto Owner = new OwnerDto(id, name);
                Owners.add(Owner);
            }
            return Owners;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
