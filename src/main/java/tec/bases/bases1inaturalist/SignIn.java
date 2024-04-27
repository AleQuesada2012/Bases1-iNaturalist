package tec.bases.bases1inaturalist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignIn {
    public TextField emailField;
    public Connection connection;
    public TextField usernameField;

    @FXML
    public void initialize() throws SQLException {
        // inicializa la conexión a la base de datos Oracle XE
        connection = ConnectionManager.getConnection();
    }
    public void signInButtonClicked(ActionEvent actionEvent) throws SQLException {
        System.out.println("sign in clicked");
        String nombre = usernameField.getText();
        System.out.println("username: " + nombre);
        String Email = emailField.getText();
        System.out.println("email: " + Email);
        String apellido = "";
        String apellido2 = "";
        String[] arr = UsernameSplitter.splitUsername(nombre);
        nombre = arr[0]; apellido = arr[1]; apellido2 = arr[2];
        usernameField.setText("");
        emailField.setText("");
        if (userExists(connection, nombre, apellido, apellido2, Email)) {
            System.out.println("User found, real user acquired");
        }
        else {
            System.out.println("User not found.");
            showErrorMessage("Credenciales incorrectas");
        }
    }

    public static boolean userExists(Connection connection, String firstName, String lastName1, String lastName2, String email) {
        boolean exists = false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sqlQuery = "SELECT 1 FROM PERSON p " +
                    "JOIN USUARIO u ON p.id_person = u.fk_person_id " +
                    "WHERE p.first_name = ? " +
                    "AND p.last_name1 = ? " +
                    "AND p.last_name2 = ? " +
                    "AND p.email = ?";

            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, firstName);
            System.out.println(firstName);
            preparedStatement.setString(2, lastName1);
            System.out.println(lastName1);
            preparedStatement.setString(3, lastName2);
            System.out.println(lastName2);
            preparedStatement.setString(4, email);
            System.out.println(email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                exists = true; // User exists
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly in your application
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exception properly in your application
            }
        }
        return exists;
    }
    /**
     * Método apartado para mostrar mensajes como alerta al usuario, con el objetivo de reutilizar el código y no repetir
     * el bloque de instrucciones cada vez que se desea imprimir un mensaje.
     * @param errorMessage la cadena de texto que se desea mostrar al usuario.
     */
    private void showErrorMessage(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);

        // Set the dialog modality to APPLICATION_MODAL
        alert.initModality(Modality.APPLICATION_MODAL);

        // Create an "OK" button to close the dialog
        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonTypeOk);

        // Show the dialog and wait for the user's response
        alert.showAndWait();
    }
}
