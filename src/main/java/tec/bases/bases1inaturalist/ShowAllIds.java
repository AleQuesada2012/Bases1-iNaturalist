package tec.bases.bases1inaturalist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class ShowAllIds {
    public TableView <IdentificacionTabulada>tableView;
    public ArrayList<Integer> idsIdentificaciones;
    public int idSeleccionado;
    public TableColumn<IdentificacionTabulada, Integer> idColumn;
    public TableColumn<IdentificacionTabulada, String> posterColumn;
    public TableColumn<IdentificacionTabulada, Integer> obsIdColumn;
    public TableColumn<IdentificacionTabulada, String> timestampColumn;
    public TableColumn<IdentificacionTabulada, String> observerColumn;
    public TableColumn<IdentificacionTabulada, String> ratingColumn;

    public Button eliminarBtn;
    public Button returnButton;
    private Connection connection;

    private int userID;
    private String userName;

    private int rowIndex = -1; // Initialize rowIndex

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        posterColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        obsIdColumn.setCellValueFactory(new PropertyValueFactory<>("obsID"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("fechaPub"));
        observerColumn.setCellValueFactory(new PropertyValueFactory<>("nombreObserver"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));


        idsIdentificaciones = new ArrayList<>();
        idSeleccionado = 0;
        poblarTabla();




        tableView.setRowFactory(tv -> {
            TableRow<IdentificacionTabulada> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    rowIndex = row.getIndex();
                    System.out.println(rowIndex);
                    idSeleccionado = idsIdentificaciones.get(rowIndex);
                }
            });
            return row;
        });
    }

    public void initValores(String nombre, int userID){
        this.userName = nombre;
        this.userID = userID;
    }

    private void poblarTabla() {
        try {
            connection = ConnectionManager.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT i.id_identification AS id, " +
                    "       p_user.first_name || ' ' || p_user.last_name1 || ' ' || p_user.last_name2 AS identificationUserFullName, " +
                    "       p_observer.first_name || ' ' || p_observer.last_name1 || ' ' || p_observer.last_name2 AS observationUserFullName, " +
                    "       oi.observation_id AS observationId, " +
                    "       oi.date_ AS observationDate, " +
                    "       r.quality_value AS ratingValue " +
                    "FROM Identification i " +
                    "JOIN USUARIO u_user ON i.fk_id_user = u_user.id_usuario " +
                    "JOIN PERSON p_user ON u_user.fk_person_id = p_user.id_person " +
                    "JOIN Observation oi ON i.fk_id_observation = oi.observation_id " +
                    "JOIN USUARIO u_observer ON oi.fk_id_observer = u_observer.id_usuario " +
                    "JOIN PERSON p_observer ON u_observer.fk_person_id = p_observer.id_person " +
                    "JOIN Rating r ON i.fk_id_rating = r.id_rating";

            ResultSet resultSet = statement.executeQuery(query);

            ObservableList<IdentificacionTabulada> observationRows = FXCollections.observableArrayList();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                idsIdentificaciones.add(id); // queda en el índice de la observación específica.
                String nombre = resultSet.getString("identificationUserFullName");
                int obsID = resultSet.getInt("observationId");
                String fechaPub = resultSet.getString("observationDate");
                String nombreObserver = resultSet.getString("observationUserFullName");
                String rating = resultSet.getString("ratingValue");
                observationRows.add(new IdentificacionTabulada(id, nombre, obsID, fechaPub, nombreObserver, rating));
            }

            tableView.setItems(observationRows);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void launchMainMenu() {
        Stage currentStage = (Stage) tableView.getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            Parent gameBoardRoot = loader.load();
            Scene gameBoardScene = new Scene(gameBoardRoot);

            Stage gameBoardStage = new Stage();
            gameBoardStage.setScene(gameBoardScene);
            gameBoardStage.setTitle("Bases-iNaturalist - Menú Principal");

            MainMenu control = loader.getController();
            control.initValores(this.userName, this.userID);

            currentStage.close();
            gameBoardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void eliminarIdentificacion(ActionEvent actionEvent) throws SQLException {
        if (idSeleccionado <= 0){
            showAlert("no ha seleccionado una identificación o no hay identificaciones por eliminar.");
            return;
        }
        connection = ConnectionManager.getConnection();
        String query = "SELECT * FROM IDENTIFICATION WHERE id_identification = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idsIdentificaciones.get(rowIndex));
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            // mostrar una alerta que le confirme si desea eliminar la observación y todas sus identificaciones.
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Eliminando Identificación...");
            confirmationDialog.setHeaderText("Cuidado!");
            confirmationDialog.setContentText("¿Está seguro que desea eliminar esta identificación?");

            // Se agregan botones de confirmación y anulación
            confirmationDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            // Se muestra el diálogo y se espera la respuesta del usuario
            ButtonType userResponse = confirmationDialog.showAndWait().orElse(ButtonType.CANCEL);

            // Si el usuario da clic a OK, se cierra la aplicación
            if (userResponse == ButtonType.OK) {
                String deleteIdentificationQuery = "DELETE FROM IDENTIFICATION WHERE ID_IDENTIFICATION = ?";

                PreparedStatement deleteIdentificationStmt = connection.prepareStatement(deleteIdentificationQuery);
                deleteIdentificationStmt.setInt(1, idSeleccionado);
                deleteIdentificationStmt.executeUpdate();
                deleteIdentificationStmt.close();

                showAlert("Se eliminó la identificación seleccionada");

            }
        }
    }
}
