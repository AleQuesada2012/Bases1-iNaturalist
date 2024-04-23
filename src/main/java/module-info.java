module tec.bases.bases1inaturalist {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens tec.bases.bases1inaturalist to javafx.fxml;
    exports tec.bases.bases1inaturalist;
}