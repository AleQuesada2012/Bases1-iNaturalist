package tec.bases.bases1inaturalist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class ShowAllObsGeneral {

    public Button eliminarBtn;
    public Button returnButton;
    public Button identifyBtn;

    public ArrayList<Integer> idsObservaciones;
    public int idSeleccionado;

    @FXML
    private TableView<ObservaGeneral> tableView;

    @FXML
    private TableColumn<ObservaGeneral, String> ReinoColumn;

    @FXML
    private TableColumn<ObservaGeneral, String> especieColumn;

    @FXML
    private TableColumn<ObservaGeneral, String> fechaColumn;

    @FXML
    private TableColumn<ObservaGeneral, String> latitudeColumn;

    @FXML
    private TableColumn<ObservaGeneral, String> longitudeColumn;

    @FXML
    private TableColumn<ObservaGeneral, String> usernameColumn;

    @FXML
    private TableColumn<ObservaGeneral, String> imageColumn;

    private Connection connection;

    private int userID;
    private String userName;

    private int rowIndex = -1; // Initialize rowIndex

    public void initialize() {
        ReinoColumn.setCellValueFactory(new PropertyValueFactory<>("reino"));
        especieColumn.setCellValueFactory(new PropertyValueFactory<>("especie"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("latitud"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<>("longitud"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("URL"));
        idsObservaciones = new ArrayList<>();
        idSeleccionado = 0;
        poblarTabla();



        // Add event handler to TableView to handle row clicks
        tableView.setRowFactory(tv -> {
            TableRow<ObservaGeneral> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    rowIndex = row.getIndex(); // Set rowIndex to the index of the clicked row
                    System.out.println(rowIndex);
                    idSeleccionado = idsObservaciones.get(rowIndex);
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
            String query = "SELECT o.observation_id as id, t.Name as Taxon, o.date_ as Fecha, co.latidude as Latitud, " +
                    "co.longitude as Longitud, p.first_name as Nombre, p.last_name1 as Apellido, p.last_name2 as Apelli2, im.URL as URL " +
                    "FROM Observation o, Taxonomia t, GPS_Coordinates co, USUARIO u, " +
                    "PERSON p, Image im " +
                    "WHERE t.id_taxonomy = o.fk_id_taxon and co.id_coordinates = " +
                    "o.fk_id_coordinates and o.fk_id_observer = u.id_usuario and " +
                    "u.fk_person_id = p.id_person and im.id_image = o.fk_id_image";
            ResultSet resultSet = statement.executeQuery(query);

            ObservableList<ObservaGeneral> observationRows = FXCollections.observableArrayList();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                idsObservaciones.add(id); // queda en el índice de la observación específica.
                String taxon = resultSet.getString("Taxon");
                String fecha = resultSet.getString("Fecha");
                String latitud = resultSet.getString("Latitud");
                String longitud = resultSet.getString("Longitud");
                String nombre = resultSet.getString("Nombre");
                String apellido1 = resultSet.getString("Apellido");
                String apellido2 = resultSet.getString("Apelli2");
                String imageURL = resultSet.getString("URL");

                String hierarchicalQuery = "WITH Hierarchy AS (" +
                        "    SELECT id_taxonomy, name, SYS_CONNECT_BY_PATH(Name, '\\') AS Path" +
                        "    FROM TAXONOMIA" +
                        "    START WITH id_ancestor = 0" +
                        "    CONNECT BY PRIOR ID_TAXONOMY = id_ancestor" +
                        ")" +
                        "SELECT observation_id, h.Path" +
                        "    FROM Observation o, Hierarchy h" +
                        "    WHERE o.fk_id_taxon = h.id_taxonomy and o.observation_id = ?";
                PreparedStatement hierarchicalStatement = connection.prepareStatement(hierarchicalQuery);
                hierarchicalStatement.setInt(1, id); // Set the observation ID parameter
                ResultSet hierarchicalResultSet = hierarchicalStatement.executeQuery();

                if (hierarchicalResultSet.next()) {
                    String path = hierarchicalResultSet.getString("Path");
                    String[] pathWords = path.split("\\\\");
                    for (String word : pathWords) {
                        System.out.println(word);
                    }
                    String kingdom = pathWords[1];
                    String speciesName = pathWords.length > 6 ? pathWords[6] : null;
                    nombre = nombre + " " + apellido1 + " " + apellido2;
                    observationRows.add(new ObservaGeneral(kingdom, speciesName, fecha, latitud, longitud, nombre, imageURL));
                }

                hierarchicalStatement.close();
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

    public void clickDetalles(){
        if (idSeleccionado <= 0){
            showAlert("Debe seleccionar una observación para ver detalles");
            return;
        }
        Stage currentStage = (Stage) returnButton.getScene().getWindow();
        try {
            // Se carga el archivo FXML para el tablero de juego
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detalles-obs.fxml"));
            Parent observaRoot = loader.load();
            Scene gameBoardScene = new Scene(observaRoot);


            // Se crea una nueva Stage (ventana) para la pantalla de juego
            Stage gameBoardStage = new Stage();
            gameBoardStage.setScene(gameBoardScene);
            gameBoardStage.setTitle("Bases-iNaturalist - Observaciones y Datos Generales");

            // Se obtiene la referencia a la clase de control para la siguiente pantalla
            DetallesController control = loader.getController();
            control.initValores(userID, userName, idSeleccionado);

            // Cierra la ventana actual y abre el tablero
            currentStage.close();
            gameBoardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Method to show alert dialog
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void clickIdentificar(ActionEvent actionEvent) {
        Stage currentStage = (Stage) identifyBtn.getScene().getWindow();
        try {
            // Se carga el archivo FXML para el tablero de juego
            FXMLLoader loader = new FXMLLoader(getClass().getResource("calificacion.fxml"));
            Parent observaRoot = loader.load();
            Scene gameBoardScene = new Scene(observaRoot);


            // Se crea una nueva Stage (ventana) para la pantalla de juego
            Stage gameBoardStage = new Stage();
            gameBoardStage.setScene(gameBoardScene);
            gameBoardStage.setTitle("Bases-iNaturalist - Calificar Observación");

            // Se obtiene la referencia a la clase de control para la siguiente pantalla
            ClasificacionControl control = loader.getController();
            control.initValores(userName, userID, idSeleccionado);

            // Cierra la ventana actual y abre el tablero
            currentStage.close();
            gameBoardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
