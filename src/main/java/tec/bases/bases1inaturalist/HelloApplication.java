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
        conexionSQL = getConnection();
        List<String> countryNames = new ArrayList<>(List.of(
                "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda",
                "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
                "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia",
                "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso",
                "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic",
                "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica", "Croatia",
                "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
                "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini",
                "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana",
                "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras",
                "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy",
                "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan",
                "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania",
                "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
                "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro",
                "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand",
                "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan",
                "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland",
                "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia",
                "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia",
                "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
                "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname",
                "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste",
                "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda",
                "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan",
                "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
        ));
        List<String> licencias = new ArrayList<>(List.of("Creative Commons","Royalty-free Extended License","Enterprise License",
                "Public Domain", "Commercial Image License", "Rights Managed License"));
        //insertCountries(conexionSQL, countryNames);
        //insertLicenses(conexionSQL, licencias);
        conexionSQL.close();
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
