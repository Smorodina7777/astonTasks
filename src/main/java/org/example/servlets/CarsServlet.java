package org.example.servlets;

import org.example.PostgreSQLConnection;
import org.example.dto.CarDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cars")

public class CarsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

            List<CarDto> cars = new ArrayList<>();
            try (Connection connection = PostgreSQLConnection.getConnection(); Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Car");
                while (resultSet.next()) {
                    CarDto car = new CarDto();
                    car.setId(resultSet.getInt("id"));
                    car.setMark(resultSet.getString("mark"));
                    car.setModel(resultSet.getString("model"));
                    car.setYear(resultSet.getInt("year"));
                    car.setOwner_id(resultSet.getInt("owner_id"));
                    cars.add(car);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        req.setAttribute("car", cars);
        req.getRequestDispatcher("cars.jsp").forward(req, resp);
    }
}
