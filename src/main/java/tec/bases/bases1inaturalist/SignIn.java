package tec.bases.bases1inaturalist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignIn {
    public TextField emailField;
    public Connection connection;
    public TextField usernameField;

    private int userID; // para almacenar el ID del usuario que está logeado
    private String username;

    @FXML
    public void initialize() throws SQLException {
        // inicializa la conexión a la base de datos Oracle XE
        connection = ConnectionManager.getConnection();
    }
    public void signInButtonClicked(ActionEvent actionEvent) throws SQLException {
        System.out.println("sign in clicked");
        String nombre = usernameField.getText();
        this.username = nombre;
        System.out.println("username: " + nombre);
        String Email = emailField.getText();
        System.out.println("email: " + Email);
        String apellido = "";
        String apellido2 = "";
        String[] arr = UsernameSplitter.splitUsername(nombre);
        nombre = arr[0]; apellido = arr[1]; apellido2 = arr[2];
        if (userExists(connection, nombre, apellido, apellido2, Email)) {
            System.out.println("User found, real user acquired");
            connection.close();
            launchMainMenu();
        }
        else {
            System.out.println("User not found.");
            showErrorMessage("Credenciales incorrectas");
            this.username = null;
            this.userID = 0;
        }
        usernameField.setText("");
        emailField.setText("");
    }

    public boolean userExists(Connection connection, String firstName, String lastName1, String lastName2, String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sqlQuery = "SELECT p.id_person, p.first_name, p.last_name1, p.last_name2 FROM PERSON p " +
                    "JOIN USUARIO u ON p.id_person = u.fk_person_id " +
                    "WHERE p.first_name = ? " +
                    "AND p.last_name1 = ? " +
                    "AND p.last_name2 = ? " +
                    "AND p.email = ?";

            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName1);
            preparedStatement.setString(3, lastName2);
            preparedStatement.setString(4, email);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // User exists
                userID = resultSet.getInt("id_person");
                username = resultSet.getString("first_name") + " " +
                        resultSet.getString("last_name1") + " " +
                        resultSet.getString("last_name2");
                return true;
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
        return false; // User does not exist
    }

    private void launchMainMenu() {
        // Now you can access the selected player count from here
        System.out.println("ID del usuario loggeado: " + userID);
        System.out.println("Nombre del usuario: " + username);
        // Se obtiene la referencia a la ventana actual (stage)
        Stage currentStage = (Stage) emailField.getScene().getWindow();

        try {
            // Se carga el archivo FXML para el tablero de juego
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            Parent gameBoardRoot = loader.load();
            Scene gameBoardScene = new Scene(gameBoardRoot);


            // Se crea una nueva Stage (ventana) para la pantalla de juego
            Stage gameBoardStage = new Stage();
            gameBoardStage.setScene(gameBoardScene);
            gameBoardStage.setTitle("Bases-iNaturalist - Menú Principal");

            // Se obtiene la referencia a la clase de control para la siguiente pantalla
            MainMenu control = loader.getController();
            control.initValores(username, userID);

            // Cierra la ventana actual y abre el tablero
            currentStage.close();
            gameBoardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
