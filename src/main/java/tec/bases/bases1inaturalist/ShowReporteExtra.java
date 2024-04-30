package tec.bases.bases1inaturalist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowReporteExtra {
    public TableView<FilaReporteExtra> tableView;
    public TableColumn<FilaReporteExtra, Integer> idUserCol;
    public TableColumn<FilaReporteExtra, Integer> idPersonCol;
    public TableColumn<FilaReporteExtra, Integer> idIdentifCol;
    public TableColumn<FilaReporteExtra, String> nombreColumn;
    public TableColumn<FilaReporteExtra, String> countryColumn;
    public Button returnButton;

    private Connection connection;

    private int userID;
    private String userName;


    public void initialize() {
        idUserCol.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
        idPersonCol.setCellValueFactory(new PropertyValueFactory<>("id_person"));
        idIdentifCol.setCellValueFactory(new PropertyValueFactory<>("id_identification"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("personName"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("countryName"));

        poblarTabla();

    }

    private void poblarTabla() {
        try {
            connection = ConnectionManager.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT " +
                    "usuario.id_usuario as id, " +
                    "person.id_person as pId, " +
                    "Identification.id_identification as iId, " +
                    "person.first_name as nom1, " +
                    "person.last_name1 as nom2, " +
                    "person.last_name2 as nom3, " +
                    "country.country_name as cntry " +
                    "FROM " +
                    "Identification " +
                    "INNER JOIN Observation ON Identification.fk_id_observation = Observation.observation_id " +
                    "INNER JOIN Usuario ON Identification.fk_id_user = Usuario.id_usuario " +
                    "INNER JOIN Person ON Usuario.fk_person_id = Person.id_person " +
                    "INNER JOIN Country ON Person.fk_id_country = Country.id_country";
            ResultSet resultSet = statement.executeQuery(query);

            ObservableList<FilaReporteExtra> observationRows = FXCollections.observableArrayList();

            while (resultSet.next()) {
                int id_usuario = resultSet.getInt("id");
                int id_person = resultSet.getInt("pId");
                int id_identification = resultSet.getInt("iId");
                String personName = resultSet.getString("nom1");
                String personName1 = resultSet.getString("nom2");
                String personName2 = resultSet.getString("nom3");
                String countryName = resultSet.getString("cntry");
                String nombre = personName + ' ' + personName1 + ' ' + personName2;
                observationRows.add(new FilaReporteExtra(id_usuario, id_person, id_identification, nombre, countryName));
            }
            tableView.setItems(observationRows);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initValores(String nombre, int userID){
        this.userName = nombre;
        this.userID = userID;
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
}
