package tec.bases.bases1inaturalist;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetallesController {
    public ImageView imageView;
    public TextField imgDateField;
    public TextField licenseField;
    public TextField ownerField;
    public TextField taxonField;
    public TextField usernameField;
    public TextField dateField;
    public TextField latitudeField;
    public TextField longitudeField;
    public TextArea commentArea;
    public Button cancelButton;
    public Button submitButton;

    private int userID;
    private String userName;
    private Connection con;
    private int obsID;


    public void initialize() throws SQLException {
        con = ConnectionManager.getConnection();
    }

    public void initValores(int userID, String userName, int obs){
        this.userID = userID;
        this.userName = userName;
        this.obsID = obs;
        poblarCampos();
    }


    public void poblarCampos() {
        String query = "SELECT im.URL AS imageURL, im.date_ AS imageDate, " +
                "ul.license_type AS licenseType, " +
                "p1.first_name || ' ' || p1.last_name1 || ' ' || p1.last_name2 AS ownerFullName, " +
                "p2.first_name || ' ' || p2.last_name1 || ' ' || p2.last_name2 AS observerFullName, " +
                "t.Name AS Taxon, " +
                "o.date_ AS observationDate, " +
                "co.latidude AS latitude, " +
                "co.longitude AS longitude, " +
                "o.comment_ AS comentario " +
                "FROM Observation o " +
                "JOIN Taxonomia t ON o.fk_id_taxon = t.id_taxonomy " +
                "JOIN GPS_Coordinates co ON o.fk_id_coordinates = co.id_coordinates " +
                "JOIN Image im ON o.fk_id_image = im.id_image " +
                "JOIN Use_License ul ON im.fk_license_id = ul.id_license " +
                "JOIN USUARIO u ON o.fk_id_observer = u.id_usuario " +
                "JOIN PERSON p1 ON im.id_owner = p1.id_person " +
                "JOIN PERSON p2 ON u.fk_person_id = p2.id_person " +
                "WHERE o.observation_id = ?";


        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, obsID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                imageView.setImage(new Image(rs.getString("imageURL")));
                imgDateField.setText(rs.getString("imageDate"));
                licenseField.setText(rs.getString("licenseType"));
                ownerField.setText(rs.getString("ownerFullName"));
                taxonField.setText(rs.getString("Taxon"));
                usernameField.setText(rs.getString("observerFullName"));
                dateField.setText(rs.getString("observationDate"));
                latitudeField.setText(rs.getString("latitude"));
                longitudeField.setText(rs.getString("longitude"));
                commentArea.setText(rs.getString("comentario"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void regresar() {
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
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
            control.initValores(userName, userID);

            // Cierra la ventana actual y abre el tablero
            currentStage.close();
            gameBoardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}