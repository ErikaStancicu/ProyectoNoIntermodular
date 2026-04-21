package daw.code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestionViajesMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vista_principal.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.setWidth(1050);
        stage.setHeight(650);
        stage.setResizable(false);
        stage.setTitle("Proyecto No Intermodular");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}