package tec.bases.bases1inaturalist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ClasificacionControl {

    public ChoiceBox choiceBox;
    public TextArea comentarioArea;
    public Button backBtn;
    public Button sendBtn;

    private int userID;
    private String nombre;
    private int obsID;

    public void initialize() {
    }

    public void initValores(String nombre, int userID, int obs){
        this.nombre = nombre;
        this.userID = userID;
        this.obsID = obs;
    }


    public void launchObsGen(ActionEvent actionEvent) {
        Stage currentStage = (Stage) backBtn.getScene().getWindow();
        try {
            // Se carga el archivo FXML para el tablero de juego
            FXMLLoader loader = new FXMLLoader(getClass().getResource("todas-info-general.fxml"));
            Parent observaRoot = loader.load();
            Scene gameBoardScene = new Scene(observaRoot);


            // Se crea una nueva Stage (ventana) para la pantalla de juego
            Stage gameBoardStage = new Stage();
            gameBoardStage.setScene(gameBoardScene);
            gameBoardStage.setTitle("Bases-iNaturalist - Observaciones y Datos Generales");

            // Se obtiene la referencia a la clase de control para la siguiente pantalla
            ShowAllObsGeneral control = loader.getController();
            control.initValores(nombre, userID);

            // Cierra la ventana actual y abre el tablero
            currentStage.close();
            gameBoardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendResult(ActionEvent actionEvent) {
        String selectedRating = (String) choiceBox.getValue();
        if (selectedRating == null || selectedRating.isEmpty()) {
            // Show alert for missing rating selection
            showAlert(Alert.AlertType.ERROR, "Error", "Calificación faltante", "Seleccione una calificación para continuar.");
            return;
        }

        // Perform SQL insertion
        try {
            Connection connection = ConnectionManager.getConnection();
            String query = "INSERT INTO Identification(fk_id_observation, fk_id_user, fk_id_rating, date_) " +
                    "VALUES (?, ?, (SELECT id_rating FROM Rating WHERE QUALITY_VALUE = ?), ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, obsID);
            statement.setInt(2, userID);
            statement.setString(3, selectedRating);
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Identificación Creada", "Se creó con éxito.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Error en DB", "No se pudo crear la identificación: " + e.getMessage());
        }
    }

    // Helper method to show an alert
    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

