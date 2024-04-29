package tec.bases.bases1inaturalist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainMenu {

    public Button btnCrearObs;
    public Button btnMostrarObs;
    public Button btnMostrarTax;
    public Label nombreLabel;

    private String nombre;
    private int userID;


    public void initialize() {
    }

    public void initValores(String nombre, int userID){
        this.nombre = nombre;
        this.userID = userID;
        nombreLabel.setText("Usuario: " + nombre);
    }


    public void clickBtnTodasObs(ActionEvent actionEvent) {
        Stage currentStage = (Stage) nombreLabel.getScene().getWindow();
        try {
            // Se carga el archivo FXML para el tablero de juego
            FXMLLoader loader = new FXMLLoader(getClass().getResource("todas-obs-taxones.fxml"));
            Parent gameBoardRoot = loader.load();
            Scene gameBoardScene = new Scene(gameBoardRoot);


            // Se crea una nueva Stage (ventana) para la pantalla de juego
            Stage gameBoardStage = new Stage();
            gameBoardStage.setScene(gameBoardScene);
            gameBoardStage.setTitle("Bases-iNaturalist - Observaciones y Taxonomía");

            // Se obtiene la referencia a la clase de control para la siguiente pantalla
            ShowAllObsTaxons control = loader.getController();
            control.initValores(nombre, userID);

            // Cierra la ventana actual y abre el tablero
            currentStage.close();
            gameBoardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickBtnCrearObs(ActionEvent actionEvent) {
        Stage currentStage = (Stage) nombreLabel.getScene().getWindow();
        try {
            // Se carga el archivo FXML para el tablero de juego
            FXMLLoader loader = new FXMLLoader(getClass().getResource("crear-observacion.fxml"));
            Parent gameBoardRoot = loader.load();
            Scene gameBoardScene = new Scene(gameBoardRoot);


            // Se crea una nueva Stage (ventana) para la pantalla de juego
            Stage gameBoardStage = new Stage();
            gameBoardStage.setScene(gameBoardScene);
            gameBoardStage.setTitle("Bases-iNaturalist - Crear Observación");

            // Se obtiene la referencia a la clase de control para la siguiente pantalla
            ObservationFormController control = loader.getController();
            control.initValores(userID);

            // Cierra la ventana actual y abre el tablero
            currentStage.close();
            gameBoardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
