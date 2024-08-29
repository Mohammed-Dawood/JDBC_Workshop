package se.lexicon.dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import se.lexicon.model.City;
import se.lexicon.database.GetConnection;

public class CityDaoJDBC implements CityDao {

    //City findById(int id)
    @Override
    public City findById(int id) {
        City city = null;
        String sql = "SELECT * FROM City WHERE ID = ?";

        try (Connection connection = GetConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                city = new City(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }

    //List<City> findByCode(String code)
    @Override
    public List<City> findByCode(String code) {
        List<City> citiesList = new ArrayList<>();
        String sql = "SELECT * FROM City WHERE CountryCode = ?";

        try (Connection connection = GetConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                citiesList.add(new City(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return citiesList;
    }

    //List<City> findByName(String name)
    @Override
    public List<City> findByName(String name) {
        List<City> citiesList = new ArrayList<>();
        String sql = "SELECT * FROM City WHERE Name LIKE ?";

        try (Connection connection = GetConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + name + "%"); // Set the name parameter with wildcards for partial matches
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                citiesList.add(new City(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }

        return citiesList;
    }

    //LIst<City> findAll()
    @Override
    public List<City> findAll() {
        List<City> citiesList = new ArrayList<>();
        String sql = "SELECT * FROM City";

        try (Connection connection = GetConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql); // Execute the query
            while (resultSet.next()) {
                citiesList.add(new City(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }

        return citiesList;
    }

    //City add(City city)
    @Override
    public City add(City city) {
        String sql = "INSERT INTO City (Name, CountryCode, District, Population) VALUES (?, ?, ?, ?)";

        try (Connection connection = GetConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, city.getCityName());
            preparedStatement.setString(2, city.getCityCode());
            preparedStatement.setString(3, city.getCityDistrict());
            preparedStatement.setInt(4, city.getCityPopulation());

            int affectedRows = preparedStatement.executeUpdate(); // Execute the insert statement

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        city.setCityId(generatedKeys.getInt(1)); // Set the generated ID to the city object
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }

        return city; // Return the city with the potentially updated ID
    }

    //City update(City city)
    @Override
    public City update(City city) {
        String sql = "UPDATE City SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";

        try (Connection connection = GetConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, city.getCityName());
            preparedStatement.setString(2, city.getCityCode());
            preparedStatement.setString(3, city.getCityDistrict());
            preparedStatement.setInt(4, city.getCityPopulation());
            preparedStatement.setInt(5, city.getCityId());

            int affectedRows = preparedStatement.executeUpdate(); // Execute the update statement

            if (affectedRows == 0) {
                System.out.println("No city found with ID: " + city.getCityId());
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }

        return city; // Return the updated city
    }

    //int delete(City city)
    @Override
    public int delete(City city) {
        String sql = "DELETE FROM City WHERE ID = ?";

        int rowsDeleted = 0;

        try (Connection connection = GetConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))  {

            // Set the parameter for the prepared statement
            preparedStatement.setInt(1, city.getCityId());

            rowsDeleted = preparedStatement.executeUpdate(); // Execute the delete statement

            if (rowsDeleted > 0) {
                System.out.println("City with ID " + city.getCityId() + " was deleted successfully.");
            } else {
                System.out.println("No city found with ID: " + city.getCityId());
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }

        return rowsDeleted; // Return the number of rows deleted
    }
}
