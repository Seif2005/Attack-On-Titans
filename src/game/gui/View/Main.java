package game.gui.View;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

import static javafx.application.Application.launch;

public class Main extends Application {
    public Parent root;
    public static MediaPlayer mediaPlayer;
    public static FadeTransition fadeOut;
    public static Stage primaryStage;
    public static Scene scene;
    public static javafx.scene.Scene Scene;
    public static Scene mainPage;

    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartPage.fxml")));
        Media media = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/StartPageMusic.mp3")).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        Main.scene = new Scene(root);
        Main.mainPage = Main.scene;

        fadeOut = new FadeTransition(Duration.seconds(3));
        fadeOut.setToValue(0);

        primaryStage.setTitle("Attack on Titan");
        primaryStage.setScene(Main.scene);
        primaryStage.show();
        }
    public static void main(String[] args) {
        launch(args);
    }
}
