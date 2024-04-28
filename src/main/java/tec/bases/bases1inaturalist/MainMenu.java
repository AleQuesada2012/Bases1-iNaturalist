package tec.bases.bases1inaturalist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Modality;

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
}
