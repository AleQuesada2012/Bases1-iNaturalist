package tec.bases.bases1inaturalist;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.sql.*;

public class ObservationFormController {

    public DatePicker photoDatePicker;
    public ChoiceBox licenseChoiceBox;
    public TextField emailField;
    @FXML
    private ImageView imageView;

    @FXML
    private TextField imageUrlField;

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextArea commentArea;

    @FXML
    private Button loadImageButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button submitButton;

    // Class attributes for storing IDs
    private int ownerID;
    private int licenseID;
    private int userID;
    private String email;
    private String username;

    @FXML
    private void initialize() {

        // Set default text for the latitude and longitude fields
        latitudeField.setPromptText("Latitud");
        longitudeField.setPromptText("Longitud");

        // Set default text for the comment area
        commentArea.setPromptText("Comentario");

        // Add event handler for the load image button
        loadImageButton.setOnAction(event -> loadImage());
    }

    public void initValores(int userID, String nombre){
        this.userID = userID;
        this.username = nombre;
    }

    private void loadImage() {
        String imageUrl = imageUrlField.getText();
        if (!imageUrl.isEmpty()) {
            try {
                if (imageUrlField.getText().length() > 3000) {
                    showAlert("La URL de la imagen supera el tamaño máximo (3000 caracteres)");
                    return;
                }
                Image image = new Image(imageUrl);
                imageView.setImage(image);
            } catch (Exception e) {
                // Display error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se pudo cargar la imagen desde la URL proporcionada.");
                alert.showAndWait();

                // Clear the URL text field
                imageUrlField.clear();
            }
        }
    }

    // Method to configure date pickers
    private void configureDatePicker(DatePicker datePicker) {
        String pattern = "dd-MM-yyyy";
        datePicker.setConverter(new StringConverter<>() {
            final java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dtf.format(date);
                }
                return "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dtf);
                }
                return null;
            }
        });
    }

    public void handleCancelClick(){
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            Parent gameBoardRoot = loader.load();
            Scene gameBoardScene = new Scene(gameBoardRoot);

            Stage gameBoardStage = new Stage();
            gameBoardStage.setScene(gameBoardScene);
            gameBoardStage.setTitle("Bases-iNaturalist - Menú Principal");

            MainMenu control = loader.getController();
            control.initValores(this.username, this.userID);

            currentStage.close();
            gameBoardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to handle submit button click
    public void handleSubmitButtonClick() {
        // Perform validations
        if (imageUrlField.getText().isEmpty() || imageView.getImage() == null) {
            showAlert("La URL de la imagen no puede estar vacía y debe cargarse una imagen.");
            return;
        }

        if (photoDatePicker.getValue() == null) {
            showAlert("Debe seleccionar una fecha válida para la foto.");
            return;
        }

        if (nameField.getText().isEmpty()) {
            showAlert("El campo del nombre del taxón no puede estar vacío.");
            return;
        }

        if (datePicker.getValue() == null) {
            showAlert("Debe seleccionar una fecha válida.");
            return;
        }

        if (latitudeField.getText().isEmpty() || longitudeField.getText().isEmpty()) {
            showAlert("Las coordenadas no pueden estar vacías.");
            return;
        }

        // SQL queries
        try (Connection connection = ConnectionManager.getConnection()) {

            // Find taxon ID
            PreparedStatement taxonStatement = connection.prepareStatement("SELECT id_taxonomy FROM Taxonomia WHERE Name = ?");
            taxonStatement.setString(1, nameField.getText());
            ResultSet taxonResult = taxonStatement.executeQuery();
            int taxonID;
            if (taxonResult.next()) {
                taxonID = taxonResult.getInt("id_taxonomy");
            } else {
                showAlert("No se encontró el taxón especificado.");
                return;
            }
            taxonStatement.close();

            // Insert coordinates into GPS_coordinates table
            PreparedStatement coordStatement = connection.prepareStatement("INSERT INTO GPS_Coordinates (SYSTEM.GPS_COORDINATES.ID_COORDINATES, SYSTEM.GPS_COORDINATES.LATIDUDE, SYSTEM.GPS_COORDINATES.LONGITUDE) VALUES (coord_sequence.NEXTVAL, ?, ?)");
            coordStatement.setString(1, latitudeField.getText());
            coordStatement.setString(2, longitudeField.getText());
            coordStatement.executeUpdate();
            coordStatement.close();

            email = emailField.getText();
            // Check if email is null or empty
            if (email == null || email.isEmpty()) {
                // Perform query to select email from Person table using UserID
                PreparedStatement userEmailStatement = connection.prepareStatement("SELECT p.email FROM PERSON p JOIN USUARIO u ON p.id_person = u.fk_person_id WHERE u.id_usuario = ?");
                userEmailStatement.setInt(1, userID);
                ResultSet userEmailResult = userEmailStatement.executeQuery();
                if (userEmailResult.next()) {
                    email = userEmailResult.getString("email");
                    ownerID = userID;
                } else {
                    showAlert("No se encontró un usuario con el correo proporcionado.");
                    return;
                }
                userEmailStatement.close();
            }
            else{
                // Find Person record by email
                PreparedStatement personStatement = connection.prepareStatement("SELECT ID_PERSON FROM Person WHERE email = ?");
                personStatement.setString(1, email);
                ResultSet personResult = personStatement.executeQuery();
                if (personResult.next()) {
                    ownerID = personResult.getInt("id_person");
                } else {
                    showAlert("No se encontró un usuario con el correo proporcionado.");
                    return;
                }
                personStatement.close();
            }


            // Find Use_License record by choice box value
            PreparedStatement licenseStatement = connection.prepareStatement("SELECT id_license FROM Use_License WHERE license_type = ?");
            licenseStatement.setString(1, (String) licenseChoiceBox.getValue());
            ResultSet licenseResult = licenseStatement.executeQuery();
            if (licenseResult.next()) {
                licenseID = licenseResult.getInt("id_license");
            } else {
                showAlert("No se encontró una licencia con el nombre proporcionado.");
                return;
            }
            licenseStatement.close();

            // Insert image into Image table
            PreparedStatement imageStatement = connection.prepareStatement("INSERT INTO Image (id_image, id_owner, date_, fk_id_coordinates, fk_license_id, URL) VALUES (image_sequence.NEXTVAL, ?, TO_DATE(?, 'dd-MM-yyyy'), coord_sequence.CURRVAL, ?, ?)");
            imageStatement.setInt(1, ownerID);
            imageStatement.setString(2, formatSelectedDate(photoDatePicker.getValue()));
            imageStatement.setInt(3, licenseID);
            imageStatement.setString(4, imageUrlField.getText());
            imageStatement.executeUpdate();
            imageStatement.close();


            // Insert observation into Observation table
            PreparedStatement observationStatement = connection.prepareStatement("INSERT INTO Observation (fk_id_taxon, fk_id_observer, fk_id_coordinates, date_, comment_, fk_id_image) VALUES (?, ?, coord_sequence.CURRVAL, TO_DATE(?, 'dd-MM-yyyy'), ?, image_sequence.CURRVAL)");
            observationStatement.setInt(1, taxonID);
            observationStatement.setInt(2, userID);
            observationStatement.setString(3, formatSelectedDate(datePicker.getValue()));
            observationStatement.setString(4, commentArea.getText());
            observationStatement.executeUpdate();
            observationStatement.close();

            showSuccess("La observación se ha registrado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error al registrar la observación.");
        }
    }

    // Method to format selected date
    private String formatSelectedDate(LocalDate date) {
        return date.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    // Method to show alert dialog
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
