package se.lexicon;

import se.lexicon.dao.CityDao;
import se.lexicon.dao.CityDaoJDBC;
import se.lexicon.model.City;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CityDao cityDao = new CityDaoJDBC();

        while (true) {
            // Display menu
            System.out.println("\nChoose an option:");
            System.out.println("1. Find City by ID");
            System.out.println("2. Find City by Code");
            System.out.println("3. Find City by Name");
            System.out.println("4. Find All Cities");
            System.out.println("5. Add a City");
            System.out.println("6. Update a City");
            System.out.println("7. Delete a City");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Find City by ID
                    System.out.print("Enter City ID: ");

                    // Check if the input is an integer
                    if (scanner.hasNextInt()) {
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        City cityById = cityDao.findById(id);
                        if (cityById != null) {
                            System.out.println(cityById);
                        } else {
                            System.out.println("City not found for the given ID.");
                        }
                    } else {
                        // If the input is not an integer
                        System.out.println("Invalid input. Please enter a valid integer for the City ID.");
                        scanner.nextLine(); // Clear the invalid input
                    }
                    break;

                case 2:
                    // Find City by Code
                    System.out.print("Enter Country Code: ");
                    String code = scanner.nextLine();
                    List<City> citiesByCode = cityDao.findByCode(code);

                    if (!citiesByCode.isEmpty()) {
                        citiesByCode.forEach(System.out::println);
                    } else {
                        System.out.println("No city found for the given country code.");
                    }
                    break;

                case 3:
                    // Find City by Name
                    System.out.print("Enter City Name: ");
                    String name = scanner.nextLine();
                    List<City> citiesByName = cityDao.findByName(name);

                    if (!citiesByName.isEmpty()) {
                        citiesByName.forEach(System.out::println);
                    } else {
                        System.out.println("No city found for the given city name.");
                    }
                    break;

                case 4:
                    // Find All Cities
                    List<City> allCities = cityDao.findAll();

                    if (!allCities.isEmpty()) {
                        allCities.forEach(System.out::println);
                    } else {
                        System.out.println("The list is empty.");
                    }
                    break;

                case 5:
                    // Add a City
                    System.out.print("Enter City Name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter Country Code: ");
                    String newCode = scanner.nextLine();

                    System.out.print("Enter District: ");
                    String newDistrict = scanner.nextLine();

                    System.out.print("Enter Population: ");

                    // Check if the input is an integer
                    if (scanner.hasNextInt()) {
                        int newPopulation = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        // Proceed to add the city
                        City newCity = new City(newName, newCode, newDistrict, newPopulation);
                        cityDao.add(newCity);
                        System.out.println("City added: " + newCity);
                    } else {
                        // If the input is not an integer
                        System.out.println("Invalid input. Please enter a valid integer for the population.");
                        scanner.nextLine(); // Clear the invalid input
                    }
                    break;

                case 6:
                    // Update a City
                    System.out.print("Enter City ID to update: ");

                    // Check if the input is an integer
                    if (scanner.hasNextInt()) {
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Enter new City Name: ");
                        String updateName = scanner.nextLine();

                        System.out.print("Enter new Country Code: ");
                        String updateCode = scanner.nextLine();

                        System.out.print("Enter new District: ");
                        String updateDistrict = scanner.nextLine();

                        System.out.print("Enter new Population: ");

                        // Check if the input for population is an integer
                        if (scanner.hasNextInt()) {
                            int updatePopulation = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            // Proceed to update the city
                            City updatedCity = new City(updateId, updateName, updateCode, updateDistrict, updatePopulation);
                            cityDao.update(updatedCity);
                            System.out.println("City updated: " + updatedCity);
                        } else {
                            // If the population input is not an integer
                            System.out.println("Invalid input. Please enter a valid integer for the population.");
                            scanner.nextLine(); // Clear the invalid input
                        }

                    } else {
                        // If the City ID input is not an integer
                        System.out.println("Invalid input. Please enter a valid integer for the City ID.");
                        scanner.nextLine(); // Clear the invalid input
                    }
                    break;

                case 7:
                    // Delete a City
                    System.out.print("Enter City ID to delete: ");

                    // Check if the input is an integer
                    if (scanner.hasNextInt()) {
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        City cityToDelete = new City(deleteId, null, null, null, 0);
                        int rowsDeleted = cityDao.delete(cityToDelete);

                        if (rowsDeleted > 0) {
                            System.out.println("City deleted successfully.");
                        } else {
                            System.out.println("City deletion failed. No city found with the given ID.");
                        }
                    } else {
                        // If the input is not an integer
                        System.out.println("Invalid input. Please enter a valid integer for the City ID.");
                        scanner.nextLine(); // Clear the invalid input
                    }
                    break;

                case 0:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
