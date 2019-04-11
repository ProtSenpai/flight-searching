package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Screen.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Searching Flights");
		stage.setScene(scene);
		stage.getIcons().add(new Image("airportIcon.png"));
		stage.show();
		stage.setResizable(false);
	}

}
