package game.gui.Controller;


import game.gui.View.Main;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.net.URL;
import java.security.KeyException;
import java.security.PublicKey;
import java.util.Objects;

public class StartController {

    @FXML
    private Button startButton;
    @FXML
    private Button easyButton;
    @FXML
    private Button hardButton;

    @FXML
    private HBox approaching;

    public static MediaPlayer mediaPlayer1;
    public static Scene secondaryPage;
    public static int noOfLanes;
    public static int resourcesPerLane;

    @FXML
    void setLevel(ActionEvent event) {
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        startButton.setVisible(false);
        easyButton.setVisible(true);
        hardButton.setVisible(true);
    }

    @FXML
     void easyLevel(ActionEvent event) throws Exception {
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        startButton.setVisible(true);
        easyButton.setVisible(false);
        hardButton.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BattlePage3.fxml"));
        Parent root = loader.load();
        BattleController battleController = loader.getController();
        battleController.startBattle(true,root);
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        StartController.secondaryPage = scene;

        fadeOut(Main.mediaPlayer, Duration.seconds(2));

        Media media = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/BackgroundSong1.mp3")).toExternalForm());
        mediaPlayer1 = new MediaPlayer(media);
        mediaPlayer1.setAutoPlay(true);
        fadeIn(mediaPlayer1, Duration.seconds(4));

        Main.primaryStage.show();
    }

    @FXML
    void hardLevel(ActionEvent event) throws Exception {
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        startButton.setVisible(true);
        easyButton.setVisible(false);
        hardButton.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BattlePage3.fxml"));
        Parent root = loader.load();
        BattleController battleController = loader.getController();
        battleController.startBattle(false,root);
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);

        fadeOut(Main.mediaPlayer, Duration.seconds(2));

        Media media = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/BackgroundSong1.mp3")).toExternalForm());
        mediaPlayer1 = new MediaPlayer(media);
        mediaPlayer1.setAutoPlay(true);
        fadeIn(mediaPlayer1, Duration.seconds(4));

        Main.primaryStage.show();
    }

    @FXML
    void Hover(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setTextFill(Color.web("#FFFFFF"));
    }
    @FXML
    void Unhover(MouseEvent event){
        Button button = (Button) event.getSource();
        button.setTextFill(Color.web("#010101"));
    }

    static void fadeOut(MediaPlayer mediaPlayer, Duration duration) {
        javafx.animation.Timeline timeline = new javafx.animation.Timeline(
                new javafx.animation.KeyFrame(
                        Duration.ZERO,
                        new javafx.animation.KeyValue(mediaPlayer.volumeProperty(), mediaPlayer.getVolume())
                ),
                new javafx.animation.KeyFrame(
                        duration,
                        new javafx.animation.KeyValue(mediaPlayer.volumeProperty(), 0)
                )
        );
        timeline.setOnFinished(event -> mediaPlayer.stop());
        timeline.play();
    }

    static void fadeIn(MediaPlayer mediaPlayer, Duration duration) {
        mediaPlayer.setVolume(0);
        javafx.animation.Timeline timeline = new javafx.animation.Timeline(
                new javafx.animation.KeyFrame(
                        Duration.ZERO,
                        new javafx.animation.KeyValue(mediaPlayer.volumeProperty(), 0)
                ),
                new javafx.animation.KeyFrame(
                        duration,
                        new javafx.animation.KeyValue(mediaPlayer.volumeProperty(), 0.3)
                )
        );
        timeline.play();
    }

}