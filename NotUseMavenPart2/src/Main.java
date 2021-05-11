import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public Scene scene;
    public Stage window;
    public Server server = new Server();
    @Override
    public void start(Stage stage) throws Exception {
        server.getConnection();

        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        window.setTitle("Electric");
        scene = new Scene(root);
        window.setScene(scene);
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}