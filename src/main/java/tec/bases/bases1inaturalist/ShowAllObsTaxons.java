package tec.bases.bases1inaturalist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class ShowAllObsTaxons {

    @FXML
    private TableView<Observation> tableView;

    @FXML
    private TableColumn<Observation, Integer> obsIdColumn;

    @FXML
    private TableColumn<Observation, String> taxonColumn;

    @FXML
    private Button returnButton;

    private Connection conn;
    private String nombre;
    private int userID;

    @FXML
    public void initialize() throws SQLException {
        conn = ConnectionManager.getConnection();
        // Set cell value factories
        obsIdColumn.setCellValueFactory(new PropertyValueFactory<>("observationId"));
        taxonColumn.setCellValueFactory(new PropertyValueFactory<>("taxonomyPath"));

        // Populate table with data
        populateTable();
    }

    public void initValores(String nombre, int userID){
        this.nombre = nombre;
        this.userID = userID;
    }

    private void populateTable() {
        try{
            // Create SQL statement
            String sql = "WITH Hierarchy AS (" +
                    "    SELECT id_taxonomy, name, SYS_CONNECT_BY_PATH(Name, '\\') AS Path" +
                    "    FROM TAXONOMIA" +
                    "    START WITH id_ancestor = 0" +
                    "    CONNECT BY PRIOR ID_TAXONOMY = id_ancestor" +
                    ")" +
                    "SELECT observation_id, h.Path" +
                    "    FROM Observation o, Hierarchy h" +
                    "    WHERE o.fk_id_taxon = h.id_taxonomy";

            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                ObservableList<Observation> observations = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    int observationId = resultSet.getInt("observation_id");
                    String taxonomyPath = resultSet.getString("Path");
                    observations.add(new Observation(observationId, taxonomyPath));
                }
                tableView.setItems(observations);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void launchMainMenu() {
        // Now you can access the selected player count from here
        // Se obtiene la referencia a la ventana actual (stage)
        Stage currentStage = (Stage) returnButton.getScene().getWindow();

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
            control.initValores(this.nombre, this.userID);

            // Cierra la ventana actual y abre el tablero
            currentStage.close();
            gameBoardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // You can add action methods for your UI components here
}
