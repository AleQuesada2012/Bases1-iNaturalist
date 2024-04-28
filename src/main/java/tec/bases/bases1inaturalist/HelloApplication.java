package tec.bases.bases1inaturalist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private Connection conexionSQL;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        //lógica
    }

    public static void main(String[] args) {
        launch();
    }

    private Connection getConnection() {
        try {
            // Attempt to establish a connection to the database
            Connection connection = ConnectionManager.getConnection();
            System.out.println("Connected to the database successfully!");
            // Don't forget to close the connection when done
            return connection; //TODO: cerrar la conexion al terminar de usarla
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            return null;
        }
    }

    // Method to insert a new country into the Country table
    public static void insertCountries(Connection connection, List<String> countryNames) throws SQLException {
        // Prepare the SQL statement with a placeholder for the country name
        String sql = "INSERT INTO SYSTEM.Country (country_name) VALUES (?)";

        // Create a PreparedStatement object
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Disable auto-commit to start a transaction
            connection.setAutoCommit(false);

            // Iterate through the list of country names
            for (String countryName : countryNames) {
                // Set the parameter values
                pstmt.setString(1, countryName);

                // Add the current statement to the batch
                pstmt.addBatch();
            }

            // Execute the batch
            pstmt.executeBatch();

            // Commit the transaction
            connection.commit();

            System.out.println("Countries inserted successfully.");
        } finally {
            // Enable auto-commit
            connection.setAutoCommit(true);
        }
    }

    public static void insertLicenses(Connection connection, List<String> licenseTypes) throws SQLException {
        // Prepare the SQL statement with a placeholder for license type
        String sql = "INSERT INTO SYSTEM.Use_License (license_type) VALUES (?)";


        // Create a PreparedStatement object
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Disable auto-commit to start a transaction
            connection.setAutoCommit(false);

            // Iterate through the list of license types
            for (String licenseType : licenseTypes) {
                // Set the parameter values
                pstmt.setString(1, licenseType);

                // Add the current statement to the batch
                pstmt.addBatch();
            }

            // Execute the batch
            pstmt.executeBatch();

            // Commit the transaction
            connection.commit();

            System.out.println("Licenses inserted successfully.");
        } finally {
            // Enable auto-commit
            connection.setAutoCommit(true);
        }
    }
}
