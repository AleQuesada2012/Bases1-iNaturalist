package tec.bases.bases1inaturalist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        testConnection();
    }

    public static void main(String[] args) {
        launch();
    }

    private void testConnection() {
        try {
            // Attempt to establish a connection to the database
            Connection connection = ConnectionManager.getConnection();
            System.out.println("Connected to the database successfully!");
            // Don't forget to close the connection when done
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
