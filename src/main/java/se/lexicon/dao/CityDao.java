package se.lexicon.dao;

import java.util.List;
import se.lexicon.model.City;

public interface CityDao {
    /**
     * Finds a city by its ID.
     * @param id The ID of the city.
     * @return The city with the given ID, or null if not found.
     */
    City findById(int id);

    /**
     * Finds cities by their country code.
     * @param code The country code to search by.
     * @return A list of cities with the given country code.
     */
    List<City> findByCode(String code);

    /**
     * Finds cities by their name.
     * @param name The name of the city to search by.
     * @return A list of cities with the given name.
     */
    List<City> findByName(String name);

    /**
     * Finds all cities.
     * @return A list of all cities.
     */
    List<City> findAll();

    /**
     * Adds a new city to the database.
     * @param city The city to add.
     * @return The added city, including its generated ID.
     */
    City add(City city);

    /**
     * Updates an existing city in the database.
     * @param city The city with updated information.
     * @return The updated city.
     */
    City update(City city);

    /**
     * Deletes a city from the database.
     * @param city The city to delete.
     * @return The number of rows affected (should be 1 if successful, 0 if not).
     */
    int delete(City city);
}