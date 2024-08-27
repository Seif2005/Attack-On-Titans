package game.gui.Controller;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.interfaces.Mobil;
import game.engine.lanes.Lane;
import game.engine.titans.*;
import game.engine.weapons.*;
import game.gui.View.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.Objects;
import java.util.PriorityQueue;

import static game.gui.View.Main.*;
import static javafx.scene.input.KeyCode.ENTER;

public class BattleController {
    //region Variable Declarations
    private boolean easy;
    @FXML
    private AnchorPane pauseMenu;
    @FXML
    private Label gameOverLabel;
    @FXML
    private Button restartButton2;
    @FXML
    private Button mainMenuButton2;
    @FXML
    private Pane gameOverPane;

    @FXML
    private Label dangerLevel1;

    @FXML
    private Label dangerLevel2;

    @FXML
    private Label dangerLevel3;

    @FXML
    private Label dangerLevel4;

    @FXML
    private Label dangerLevel5;

    @FXML
    private ProgressBar healthBar1;

    @FXML
    private ProgressBar healthBar2;

    @FXML
    private ProgressBar healthBar3;

    @FXML
    private ProgressBar healthBar4;

    @FXML
    private ProgressBar healthBar5;

    @FXML
    private Button infoButton;

    @FXML
    private Button lane1;

    @FXML
    private Button lane2;

    @FXML
    private Button lane3;

    @FXML
    private Button lane4;

    @FXML
    private Button lane5;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button passButton;

    @FXML
    private ImageView pauseButton;

    @FXML
    private  Label phase;

    @FXML
    private ImageView piercing;

    @FXML
    private Label piercingCount1;

    @FXML
    private Label piercingCount2;

    @FXML
    private Label piercingCount3;

    @FXML
    private Label piercingCount4;

    @FXML
    private Label piercingCount5;


    @FXML
    private Label money ;

    @FXML
    private Button restartButton;

    @FXML
    private Button resumeButton;

    public Label getScore() {
        return score;
    }

    public void setScore(Label score) {
        this.score = score;
    }

    @FXML
    private Label score;

    @FXML
    private ImageView sniper;

    @FXML
    private Label sniperCount1;

    @FXML
    private Label sniperCount2;

    @FXML
    private Label sniperCount3;

    @FXML
    private Label sniperCount4;

    @FXML
    private Label sniperCount5;

    @FXML
    private Label turn;

    @FXML
    private ImageView volleySpread;

    @FXML
    private Label volleySpreadCount1;

    @FXML
    private Label volleySpreadCount2;

    @FXML
    private Label volleySpreadCount3;

    @FXML
    private Label volleySpreadCount4;

    @FXML
    private Label volleySpreadCount5;

    @FXML
    private ImageView wall1;

    @FXML
    private ImageView wall2;

    @FXML
    private ImageView wall3;

    @FXML
    private ImageView wall4;

    @FXML
    private ImageView wall5;

    ArrayList<BorderPane> path1 = new ArrayList<>();
    ArrayList<BorderPane> path2 = new ArrayList<>();
    ArrayList<BorderPane> path3 = new ArrayList<>();
    ArrayList<BorderPane> path4 = new ArrayList<>();
    ArrayList<BorderPane> path5 = new ArrayList<>();
    ArrayList<BorderPane> selectedPath;

    @FXML
    private ImageView wallTrap;

    @FXML
    private ImageView wallTrap1;

    @FXML
    private ImageView wallTrap2;

    @FXML
    private ImageView wallTrap3;

    @FXML
    private ImageView wallTrap4;

    @FXML
    private Label wallTrapCount1;

    @FXML
    private Label wallTrapCount2;

    @FXML
    private Label wallTrapCount3;

    @FXML
    private Label wallTrapCount4;

    @FXML
    private Label wallTrapCount5;

    @FXML
    private Pane weaponsDescription;

    private Battle battle;

    @FXML
    private Button closeException;

    @FXML
    private AnchorPane exceptionPane;

    @FXML
    private Text exceptionText;

    @FXML
    private AnchorPane firstAnchor;

    private String chosenWeapon;

    private int chosenWeaponId = -1;

    private int weaponId;

    private Parent root;

    private int[] arrayLanes = new int[]{0,0,0,0,0};

    @FXML
    private BorderPane pcPane;

    @FXML
    private BorderPane scPane;

    @FXML
    private BorderPane vsPane;

    @FXML
    private BorderPane wtPane;

    @FXML
    private AnchorPane pathAll1;

    @FXML
    private AnchorPane pathAll2;

    @FXML
    private AnchorPane pathAll3;

    @FXML
    private AnchorPane pathAll4;

    @FXML
    private AnchorPane pathAll5;

    @FXML
    private Rectangle pathAllrec1;

    @FXML
    private Rectangle pathAllrec2;

    @FXML
    private Rectangle pathAllrec3;

    @FXML
    private Rectangle pathAllrec4;

    @FXML
    private Rectangle pathAllrec5;

    @FXML
    private HBox approaching;

    @FXML
    private Rectangle pauseMenuTint;

    @FXML
    private Button aiButton;
    //endregion
    public BattleController() {
    }
    @FXML
    void closeExceptionMethod(ActionEvent event){
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        exceptionPane.setVisible(false);
    }

    @FXML
    void chooseWeapon(MouseEvent event) {
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        ImageView img = (ImageView) event.getSource();
         String Id = img.getId();
        pcPane.setStyle("-fx-background-color: transparent;");
        scPane.setStyle("-fx-background-color: transparent;");
        vsPane.setStyle("-fx-background-color: transparent;");
        wtPane.setStyle("-fx-border-color: transparent;");
        pcPane.setStyle("-fx-border-color: black;");
        scPane.setStyle("-fx-border-color: black;");
        vsPane.setStyle("-fx-border-color: black;");
        wtPane.setStyle("-fx-border-color: black;");
        switch (Id) {
            case "piercingCount" -> {
                chosenWeaponId = 1;
                pcPane.setStyle("-fx-background-color: green;");
            }
            case "sniperCount" -> {
                chosenWeaponId = 2;
                scPane.setStyle("-fx-background-color: green;");
            }
            case "volleySpreadCount" -> {
                chosenWeaponId = 3;
                vsPane.setStyle("-fx-background-color: green;");
            }
            case "wallTrapCount" -> {
                chosenWeaponId = 4;
                wtPane.setStyle("-fx-background-color: green;");
            }
        }
    }
    @FXML
    void deployWeapon(MouseEvent event){
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        pcPane.setStyle("-fx-background-color: transparent;");
        scPane.setStyle("-fx-background-color: transparent;");
        vsPane.setStyle("-fx-background-color: transparent;");
        wtPane.setStyle("-fx-border-color: transparent;");
        pcPane.setStyle("-fx-border-color: black;");
        scPane.setStyle("-fx-border-color: black;");
        vsPane.setStyle("-fx-border-color: black;");
        wtPane.setStyle("-fx-border-color: black;");
        Button button = (Button) event.getSource();
        String laneId = button.getId();
        int laneNum = Integer.parseInt(String.valueOf(laneId.charAt(4)))-1;
        if (chosenWeaponId != -1 && !battle.getOriginalLanes().get(laneNum).isLaneLost()){
            try {
                switch (chosenWeaponId) {
                    case 1 -> battle.purchaseWeapon(1, battle.getOriginalLanes().get(laneNum));
                    case 2 -> battle.purchaseWeapon(2, battle.getOriginalLanes().get(laneNum));
                    case 3 -> battle.purchaseWeapon(3, battle.getOriginalLanes().get(laneNum));
                    case 4 -> battle.purchaseWeapon(4, battle.getOriginalLanes().get(laneNum));
                }
                addTitan();
                moveTitans();
                updateApproaching();
            }catch (InvalidLaneException e){
                exceptionText.setText("Lane Unavailable");
                exceptionPane.setVisible(true);
            }catch(InsufficientResourcesException e){
                exceptionText.setText("Not enough resources");
                exceptionPane.setVisible(true);
            }finally {
                chosenWeaponId=-1;
                updateLabels();
            }
        }
        else {
            if(battle.getOriginalLanes().get(laneNum).isLaneLost()){
                exceptionText.setText("Lane Unavailable");
                exceptionPane.setVisible(true);
            }
            else{
                exceptionText.setText("Please choose a weapon");
                exceptionPane.setVisible(true);
            }
        }
    }
    private void changeWeaponLabel(String chosenWeapon,String laneID){
        String temp;
        int count;
        if(Objects.equals(chosenWeapon, "piercingCount")){
            switch (laneID.charAt(4)){
                case('1'): temp = piercingCount1.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));piercingCount1.setText("x"+(++count));break;
                case('2'): temp = piercingCount2.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));piercingCount2.setText("x"+(++count));break;
                case('3'): temp = piercingCount3.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));piercingCount3.setText("x"+(++count));break;
                case('4'): temp = piercingCount4.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));piercingCount4.setText("x"+(++count));break;
                case('5'): temp = piercingCount5.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));piercingCount5.setText("x"+(++count));break;
            }
        }
        else if(Objects.equals(chosenWeapon, "sniperCount")){
            switch (laneID.charAt(4)){
                case('1'): temp = sniperCount1.getText();count = Integer.parseInt(String.valueOf(temp.charAt(1)));sniperCount1.setText("x"+(++count));break;
                case('2'): temp = sniperCount2.getText();count = Integer.parseInt(String.valueOf(temp.charAt(1)));sniperCount2.setText("x"+(++count));break;
                case('3'): temp = sniperCount3.getText();count = Integer.parseInt(String.valueOf(temp.charAt(1)));sniperCount3.setText("x"+(++count));break;
                case('4'): temp = sniperCount4.getText();count = Integer.parseInt(String.valueOf(temp.charAt(1)));sniperCount4.setText("x"+(++count));break;
                case('5'): temp = sniperCount5.getText();count = Integer.parseInt(String.valueOf(temp.charAt(1)));sniperCount5.setText("x"+(++count));break;
            }
        }
        else if(Objects.equals(chosenWeapon, "volleySpreadCount")){
            switch (laneID.charAt(4)){
                case('1'): temp = volleySpreadCount1.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));volleySpreadCount1.setText("x"+(++count));break;
                case('2'): temp = volleySpreadCount2.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));volleySpreadCount2.setText("x"+(++count));break;
                case('3'): temp = volleySpreadCount3.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));volleySpreadCount3.setText("x"+(++count));break;
                case('4'): temp = volleySpreadCount4.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));volleySpreadCount4.setText("x"+(++count));break;
                case('5'): temp = volleySpreadCount5.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));volleySpreadCount5.setText("x"+(++count));break;
            }
        }
        else if(Objects.equals(chosenWeapon, "wallTrapCount")){
            switch (laneID.charAt(4)){
                case('1'): temp = wallTrapCount1.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));wallTrapCount1.setText("x"+(++count));break;
                case('2'): temp = wallTrapCount2.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));wallTrapCount2.setText("x"+(++count));break;
                case('3'): temp = wallTrapCount3.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));wallTrapCount3.setText("x"+(++count));break;
                case('4'): temp = wallTrapCount4.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));wallTrapCount4.setText("x"+(++count));break;
                case('5'): temp = wallTrapCount5.getText(); count = Integer.parseInt(String.valueOf(temp.charAt(1)));wallTrapCount5.setText("x"+(++count));break;
            }
        }
        updateLabels();
        if (battle.isGameOver()){
            gameOver();
        }
    }

    @FXML
    void getPauseMenu(MouseEvent event) {
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        StartController.mediaPlayer1.pause();
        if(timeline != null) timeline.pause();
        pauseMenu.setVisible(true);
        pauseMenuTint.setVisible(true);
    }

    @FXML
    void pass(ActionEvent event) {
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        battle.passTurn();
        updateLabels();
        addTitan();
        moveTitans();
        updateApproaching();
        if (battle.isGameOver()){
            gameOver();
        }
    }

    void addTitan(){
        ArrayList<Lane> lanes = battle.getOriginalLanes();
        int yLevel = 0;
        for (Lane tempLane:lanes){
            if (tempLane.isLaneLost()){
                yLevel++;
                continue;
            }
            ArrayList<Titan> titans = new ArrayList<>(tempLane.getTitans());
            if (titans.isEmpty()) continue;
            if(arrayLanes[yLevel] == titans.size()) continue;
            Titan titan = titans.getLast();
            ImageView img;
            Label progressLabel = new Label();
            Label nameLabel = new Label();
            ProgressBar healthBar = new ProgressBar(titan.getCurrentHealth());
            progressLabel.setText(titan.getCurrentHealth() + "");
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(healthBar, progressLabel);
            ArrayList<BorderPane> selectedPath = switch (yLevel) {
                case 0 -> path1;
                case 1 -> path2;
                case 2 -> path3;
                case 3 -> path4;
                case 4 -> path5;
                default -> null;
            };
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(stackPane);
            healthBar.setStyle("-fx-accent: green;");
            borderPane.setUserData(titan);
            if (titan instanceof ColossalTitan){
                img = new ImageView("game/gui/static/Monster1.png");
                img.setScaleX(-1);
                img.setFitHeight(85);
                img.setPreserveRatio(true);
                nameLabel.setText("Colossal");
                AnchorPane.setLeftAnchor((Node) borderPane, (double) (titan.getDistance()*10));
            }
            else if (titan instanceof AbnormalTitan){
                img = new ImageView("game/gui/static/Monster2.png");
                img.setScaleX(-1);
                img.setFitHeight(85);
                img.setPreserveRatio(true);
                nameLabel.setText("Abnormal");
                AnchorPane.setLeftAnchor((Node) borderPane, (double) (titan.getDistance()*10));
            }
            else if (titan instanceof ArmoredTitan){
                img = new ImageView("game/gui/static/Monster3.png");
                img.setScaleX(-1);
                img.setFitHeight(80);
                img.setPreserveRatio(true);
                nameLabel.setText("Armored");
                AnchorPane.setLeftAnchor((Node) borderPane, (double) (titan.getDistance()*10));
            }
            else if (titan instanceof PureTitan){
                img = new ImageView("game/gui/static/Monster4.png");
                img.setScaleX(-1);
                img.setFitHeight(75);
                img.setPreserveRatio(true);
                nameLabel.setText("Pure");
                AnchorPane.setLeftAnchor((Node) borderPane, (double) (titan.getDistance()*10));
            }
            else img = new ImageView("game/gui/static/sniper.png");
            borderPane.setCenter(img);

            switch(yLevel){
                case 0:pathAll1.getChildren().add(borderPane);break;
                case 1:pathAll2.getChildren().add(borderPane);break;
                case 2:pathAll3.getChildren().add(borderPane);break;
                case 3:pathAll4.getChildren().add(borderPane);break;
                case 4:pathAll5.getChildren().add(borderPane);break;
            }
            arrayLanes[yLevel]++;
            yLevel++;
            selectedPath.add(borderPane);
        }
    }

    void moveTitans(){
        ArrayList<Lane> lanes = new ArrayList<>(battle.getOriginalLanes());
        ImageView tempImg;
        if(!lanes.get(0).isLaneLost()){
            int size = path1.size();
            ArrayList<BorderPane> removable = new ArrayList<>();
            for(int i = 0; i < size; i++){
                BorderPane pane = path1.get(i);
                Titan data = (Titan) pane.getUserData();
                if(data.isDefeated()) {
                    pathAll1.getChildren().remove(pane);
                    removable.add(pane);
                    continue;
                }
                ((ProgressBar) ((StackPane) pane.getTop()).getChildren().get(0)).setProgress((double) data.getCurrentHealth() / data.getBaseHealth());
                ((Label) ((StackPane) pane.getTop()).getChildren().get(1)).setText((data.getCurrentHealth()) + "");
                if(pane.getLayoutX() == 425) continue;
                AnchorPane.setLeftAnchor((Node) pane, (double) (data.getDistance()*10));
            }
            path1.removeAll(removable);
        }
        if(!lanes.get(1).isLaneLost()){
            int size = path2.size();
            ArrayList<BorderPane> removable = new ArrayList<>();
            for(int i = 0; i < size; i++){
                BorderPane pane = path2.get(i);
                Titan data = (Titan) pane.getUserData();
                if(data.isDefeated()) {
                    pathAll2.getChildren().remove(pane);
                    removable.add(pane);
                    continue;
                }
                ((ProgressBar) ((StackPane) pane.getTop()).getChildren().get(0)).setProgress((double) data.getCurrentHealth() / data.getBaseHealth());
                ((Label) ((StackPane) pane.getTop()).getChildren().get(1)).setText((data.getCurrentHealth()) + "");
                if(pane.getLayoutX() == 425) continue;
                AnchorPane.setLeftAnchor((Node) pane, (double) (data.getDistance()*10));
            }
            path2.removeAll(removable);
        }
        if(!lanes.get(2).isLaneLost()){
            int size = path3.size();
            ArrayList<BorderPane> removable = new ArrayList<>();
            for(int i = 0; i < size; i++){
                BorderPane pane = path3.get(i);
                Titan data = (Titan) pane.getUserData();
                if(data.isDefeated()) {
                    pathAll3.getChildren().remove(pane);
                    removable.add(pane);
                    continue;
                }
                ((ProgressBar) ((StackPane) pane.getTop()).getChildren().get(0)).setProgress((double) data.getCurrentHealth() / data.getBaseHealth());
                ((Label) ((StackPane) pane.getTop()).getChildren().get(1)).setText((data.getCurrentHealth()) + "");
                if(pane.getLayoutX() == 425) continue;
                AnchorPane.setLeftAnchor((Node) pane, (double) (data.getDistance()*10));
            }
            path3.removeAll(removable);
        }
        if(!lanes.get(3).isLaneLost()){
            int size = path4.size();
            ArrayList<BorderPane> removable = new ArrayList<>();
            for(int i = 0; i < size; i++){
                BorderPane pane = path4.get(i);
                Titan data = (Titan) pane.getUserData();
                if(data.isDefeated()) {
                    pathAll4.getChildren().remove(pane);
                    removable.add(pane);
                    continue;
                }
                ((ProgressBar) ((StackPane) pane.getTop()).getChildren().get(0)).setProgress((double) data.getCurrentHealth() / data.getBaseHealth());
                ((Label) ((StackPane) pane.getTop()).getChildren().get(1)).setText((data.getCurrentHealth()) + "");
                if(pane.getLayoutX() == 425) continue;
                AnchorPane.setLeftAnchor((Node) pane, (double) (data.getDistance()*10));
            }
            path4.removeAll(removable);
        }
        if(!lanes.get(4).isLaneLost()){
            int size = path5.size();
            ArrayList<BorderPane> removable = new ArrayList<>();
            for(int i = 0; i < size; i++){
                BorderPane pane = path5.get(i);
                Titan data = (Titan) pane.getUserData();
                if(data.isDefeated()) {
                    pathAll5.getChildren().remove(pane);
                    removable.add(pane);
                    continue;
                }
                ((ProgressBar) ((StackPane) pane.getTop()).getChildren().get(0)).setProgress((double) data.getCurrentHealth() / data.getBaseHealth());
                ((Label) ((StackPane) pane.getTop()).getChildren().get(1)).setText((data.getCurrentHealth()) + "");
                if(pane.getLayoutX() == 425) continue;
                AnchorPane.setLeftAnchor((Node) pane, (double) (data.getDistance()*10));
            }
            path5.removeAll(removable);
        }
    }

    public void updateApproaching(){
        ImageView img;
        if(approaching.getChildren().isEmpty()){
            if(!battle.getApproachingTitans().isEmpty()){
                Titan titan = battle.getApproachingTitans().getFirst();
                if(titan instanceof  ColossalTitan) {
                    img = new ImageView("game/gui/static/Monster1.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
                else if (titan instanceof  AbnormalTitan){
                    img = new ImageView("game/gui/static/Monster2.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
                else if (titan instanceof  ArmoredTitan){
                    img = new ImageView("game/gui/static/Monster3.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
                else if (titan instanceof  PureTitan){
                    img = new ImageView("game/gui/static/Monster4.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
            }
            if(battle.getApproachingTitans().size() >= 2){
                Titan titan = battle.getApproachingTitans().get(1);
                if(titan instanceof  ColossalTitan) {
                    img = new ImageView("game/gui/static/Monster1.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
                else if (titan instanceof  AbnormalTitan){
                    img = new ImageView("game/gui/static/Monster2.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
                else if (titan instanceof  ArmoredTitan){
                    img = new ImageView("game/gui/static/Monster3.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
                else if (titan instanceof  PureTitan){
                    img = new ImageView("game/gui/static/Monster4.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
            }
        }
        else{
            approaching.getChildren().removeFirst();
            if(!(battle.getApproachingTitans().isEmpty())){
                Titan titan = battle.getApproachingTitans().getFirst();
                if(titan instanceof  ColossalTitan) {
                    img = new ImageView("game/gui/static/Monster1.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
                else if (titan instanceof  AbnormalTitan){
                    img = new ImageView("game/gui/static/Monster2.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
                else if (titan instanceof  ArmoredTitan){
                    img = new ImageView("game/gui/static/Monster3.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
                else if (titan instanceof  PureTitan){
                    img = new ImageView("game/gui/static/Monster4.png");
                    img.setFitHeight(90);
                    img.setScaleX(-1);
                    img.setPreserveRatio(true);
                    approaching.getChildren().add(img);
                }
            }
        }
    }

    @FXML
    void previewWeaponsOff(MouseEvent event) {
        weaponsDescription.setVisible(false);
    }

    @FXML
    void previewWeaponsOn(MouseEvent event) {
        weaponsDescription.setVisible(true);
    }
    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

//        if(Main.mediaPlayer != null) mediaPlayer.stop();
        if(timeline != null) timeline.stop();
        Media media = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/StartPageMusic.mp3")).toExternalForm());
        Main.mediaPlayer = new MediaPlayer(media);
        Main.mediaPlayer.setAutoPlay(true);
        StartController.fadeOut(StartController.mediaPlayer1, Duration.seconds(2));

        gameOverPane.setVisible(false);
        primaryStage.setScene(Main.mainPage);
        primaryStage.show();
    }
    @FXML
    void restartGame(ActionEvent event) throws Exception {
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        Media media = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/BackgroundSong1.mp3")).toExternalForm());
        StartController.mediaPlayer1 = new MediaPlayer(media);
        StartController.mediaPlayer1.setAutoPlay(true);
        StartController.fadeIn(StartController.mediaPlayer1, Duration.seconds(4));
        if(Main.mediaPlayer != null) mediaPlayer.stop();
        if(timeline != null) timeline.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BattlePage3.fxml"));
        Parent root = loader.load();
        BattleController battleController = loader.getController();
        battleController.startBattle(this.easy,root);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        path1.clear();
        path2.clear();
        path3.clear();
        path4.clear();
        path5.clear();
        pathAll1.getChildren().clear();
        pathAll2.getChildren().clear();
        pathAll3.getChildren().clear();
        pathAll4.getChildren().clear();
        pathAll5.getChildren().clear();

        primaryStage.show();
    }

    @FXML
    void resumeGame(ActionEvent event) {
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        StartController.mediaPlayer1.play();
        if(timeline != null) timeline.play();
        pauseMenu.setVisible(false);
        pauseMenuTint.setVisible(false);
    }

    private void updateLabels(){
        //upper labels
        score.setText("Score "+battle.getScore());
        money.setText(battle.getResourcesGathered()+"");
        turn.setText(battle.getNumberOfTurns()+"");
        phase.setText("Stage "+battle.getBattlePhase());
        //health bars
        healthBar1.setProgress((double) battle.getOriginalLanes().getFirst().getLaneWall().getCurrentHealth() /10000);
        healthBar2.setProgress((double) battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth() /10000);
        healthBar3.setProgress((double) battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth() /10000);
        healthBar4.setProgress((double) battle.getOriginalLanes().get(3).getLaneWall().getCurrentHealth() /10000);
        healthBar5.setProgress((double) battle.getOriginalLanes().get(4).getLaneWall().getCurrentHealth() /10000);
        //danger levels
        dangerLevel1.setText(battle.getOriginalLanes().get(0).getDangerLevel()+"");
        dangerLevel2.setText(battle.getOriginalLanes().get(1).getDangerLevel()+"");
        dangerLevel3.setText(battle.getOriginalLanes().get(2).getDangerLevel()+"");
        dangerLevel4.setText(battle.getOriginalLanes().get(3).getDangerLevel()+"");
        dangerLevel5.setText(battle.getOriginalLanes().get(4).getDangerLevel()+"");
        //TODO:lane1 weapons in lanes count could be modified here
        piercingCount1.setText(""+getWeaponCount(1,1));
        piercingCount2.setText(""+getWeaponCount(2,1));
        piercingCount3.setText(""+getWeaponCount(3,1));
        piercingCount4.setText(""+getWeaponCount(4,1));
        piercingCount5.setText(""+getWeaponCount(5,1));

        sniperCount1.setText(""+getWeaponCount(1,2));
        sniperCount2.setText(""+getWeaponCount(2,2));
        sniperCount3.setText(""+getWeaponCount(3,2));
        sniperCount4.setText(""+getWeaponCount(4,2));
        sniperCount5.setText(""+getWeaponCount(5,2));

        volleySpreadCount1.setText(""+getWeaponCount(1,3));
        volleySpreadCount2.setText(""+getWeaponCount(2,3));
        volleySpreadCount3.setText(""+getWeaponCount(3,3));
        volleySpreadCount4.setText(""+getWeaponCount(4,3));
        volleySpreadCount5.setText(""+getWeaponCount(5,3));

        wallTrapCount1.setText(""+getWeaponCount(1,4));
        wallTrapCount2.setText(""+getWeaponCount(2,4));
        wallTrapCount3.setText(""+getWeaponCount(3,4));
        wallTrapCount4.setText(""+getWeaponCount(4,4));
        wallTrapCount5.setText(""+getWeaponCount(5,4));
//        if (battle.getOriginalLanes().get(0).getLaneWall().isDefeated()){
//            pathAllrec1.setFill(Color.GREY);
//        } else pathAllrec1.setFill(Color.BROWN);
//        if (battle.getOriginalLanes().get(1).getLaneWall().isDefeated()){
//            pathAllrec2.setFill(Color.GREY);
//        } else pathAllrec2.setFill(Color.BROWN);
//        if (battle.getOriginalLanes().get(2).getLaneWall().isDefeated()){
//            pathAllrec3.setFill(Color.GREY);
//        } else pathAllrec3.setFill(Color.BROWN);
//        if (battle.getOriginalLanes().get(3).getLaneWall().isDefeated()){
//            pathAllrec4.setFill(Color.GREY);
//        } else pathAllrec4.setFill(Color.BROWN);
//        if (battle.getOriginalLanes().get(4).getLaneWall().isDefeated()){
//            pathAllrec5.setFill(Color.GREY);
//        } else pathAllrec5.setFill(Color.BROWN);


    }
    private int getWeaponCount(int laneNumber, int weaponId){
        int count = 0;
        Lane lane = battle.getOriginalLanes().get(laneNumber-1);
        ArrayList<Weapon> weapons = lane.getWeapons();
        if (weaponId==1) {
            for (Weapon weapon : weapons) {
                if (weapon instanceof PiercingCannon) {
                    count++;
                }
            }
        } else if (weaponId == 2) {
            for (Weapon weapon : weapons) {
                if (weapon instanceof SniperCannon) {
                    count++;
                }
            }
        } else if (weaponId == 3) {
            for (Weapon weapon : weapons) {
                if (weapon instanceof VolleySpreadCannon) {
                    count++;
                }
            }
        } else if (weaponId == 4) {
            for (Weapon weapon : weapons) {
                if (weapon instanceof WallTrap) {
                    count++;
                }
            }
        }
        return count;
    }
    private void gameOver(){
        gameOverPane.setVisible(true);
        gameOverLabel.setText("Your Score was "+battle.getScore());
    }

    @FXML
    public void startBattle(boolean easy, Parent root) throws IOException {
        this.root =root;
        this.easy = easy;
        if (easy) {
            battle = new Battle(1, 0, 76, 5, 150);
            battle.getOriginalLanes().get(0).getLaneWall().setCurrentHealth(0);
            battle.getOriginalLanes().get(4).getLaneWall().setCurrentHealth(0);
        }else{
            battle = new Battle(1,0,76,5,125);
        }

//        pathAllrec1 = new Rectangle();
//        pathAllrec1.setWidth(828);
//        pathAllrec1.setHeight(105);
//        pathAllrec1.setFill(Color.BROWN);
//        pathAll1.getChildren().add(pathAllrec1);
//        AnchorPane.setTopAnchor(pathAllrec1, 0.0);
//        AnchorPane.setBottomAnchor(pathAllrec1, 0.0);
//        AnchorPane.setLeftAnchor(pathAllrec1, 0.0);
//        AnchorPane.setRightAnchor(pathAllrec1, 0.0);
//
//        pathAllrec2 = new Rectangle();
//        pathAllrec2.setWidth(828);
//        pathAllrec2.setHeight(105);
//        pathAllrec2.setFill(Color.BROWN);
//        pathAll2.getChildren().add(pathAllrec2);
//        AnchorPane.setTopAnchor(pathAllrec2, 0.0);
//        AnchorPane.setBottomAnchor(pathAllrec2, 0.0);
//        AnchorPane.setLeftAnchor(pathAllrec2, 0.0);
//        AnchorPane.setRightAnchor(pathAllrec2, 0.0);
//
//        pathAllrec3 = new Rectangle();
//        pathAllrec3.setWidth(828);
//        pathAllrec3.setHeight(105);
//        pathAllrec3.setFill(Color.BROWN);
//        pathAll3.getChildren().add(pathAllrec3);
//        AnchorPane.setTopAnchor(pathAllrec3, 0.0);
//        AnchorPane.setBottomAnchor(pathAllrec3, 0.0);
//        AnchorPane.setLeftAnchor(pathAllrec3, 0.0);
//        AnchorPane.setRightAnchor(pathAllrec3, 0.0);
//
//        pathAllrec4 = new Rectangle();
//        pathAllrec4.setWidth(828);
//        pathAllrec4.setHeight(105);
//        pathAllrec4.setFill(Color.BROWN);
//        pathAll4.getChildren().add(pathAllrec4);
//        AnchorPane.setTopAnchor(pathAllrec4, 0.0);
//        AnchorPane.setBottomAnchor(pathAllrec4, 0.0);
//        AnchorPane.setLeftAnchor(pathAllrec4, 0.0);
//        AnchorPane.setRightAnchor(pathAllrec4, 0.0);
//
//        pathAllrec5 = new Rectangle();
//        pathAllrec5.setWidth(828);
//        pathAllrec5.setHeight(105);
//        pathAllrec5.setFill(Color.BROWN);
//        pathAll5.getChildren().add(pathAllrec5);
//        AnchorPane.setTopAnchor(pathAllrec5, 0.0);
//        AnchorPane.setBottomAnchor(pathAllrec5, 0.0);
//        AnchorPane.setLeftAnchor(pathAllrec5, 0.0);
//        AnchorPane.setRightAnchor(pathAllrec5, 0.0);

        healthBar1.setStyle("-fx-accent: green;");
        healthBar2.setStyle("-fx-accent: green;");
        healthBar3.setStyle("-fx-accent: green;");
        healthBar4.setStyle("-fx-accent: green;");
        healthBar5.setStyle("-fx-accent: green;");
        updateApproaching();
        updateLabels();
    }

    private boolean stopConditionMet = false;
    Timeline timeline;
    @FXML
    private void aiMode(ActionEvent event){
        timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> {
            try {
                aiPerformMove();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidLaneException ex) {
                throw new RuntimeException(ex);
            } catch (InsufficientResourcesException ex) {
                throw new RuntimeException(ex);
            }

            if (stopConditionMet) {
                timeline.stop();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void aiPerformMove() throws IOException, InvalidLaneException, InsufficientResourcesException {
        try {
            // Pass or Buy
            if (battle.getResourcesGathered() < 25) pass();
            else {
                int maxDL = -1;
                ArrayList<Lane> lanes = battle.getOriginalLanes();
                Lane selectedLane = null;
                int counter = 0;
                if (easy) counter++;
                int laneNum = -1;
                // Get max danger level
                for (Lane lane : lanes) {
                    if (lane.isLaneLost()) continue;
                    if ((counter == 0 || counter == 4) && easy) continue;
                    if (lane.getDangerLevel() > maxDL) {
                        maxDL = lane.getDangerLevel();
                        laneNum = counter;
//                        System.out.println("counter "+counter);
                        selectedLane = lane;
                    }
                    counter++;
                }
//                System.out.println("lane "+laneNum);
                if (selectedLane == null) {
                    stopConditionMet = true;
                    gameOver();
                }
                ArrayList<Titan> titans = new ArrayList<>(selectedLane.getTitans());

                ArrayList<Titan> volleySpreadCheck = new ArrayList<>();
                for (Titan titan : titans)
                    if (titan.getDistance() >= 20 && titan.getDistance() <= 50) volleySpreadCheck.add(titan);

                if (!titans.isEmpty() && titans.getFirst().getCurrentHealth() >= 500 && battle.getResourcesGathered() >= 75) {
//                    System.out.println("0");
                    deployWeapon(4, laneNum);
                } else if (!titans.isEmpty() && titans.getFirst().getDistance() <= 10 && battle.getResourcesGathered() >= 75) {
//                    System.out.println("1");
                    deployWeapon(4, laneNum);
                } else if (!titans.isEmpty() && titans.getFirst().getDistance() >= 60 && battle.getResourcesGathered() >= 25) {
//                    System.out.println("1.5");
                    deployWeapon(2, laneNum);
                } else if (volleySpreadCheck.size() >= 3 && battle.getResourcesGathered() >= 100) {
//                    System.out.println("2");
                    deployWeapon(3, laneNum);
                } else if (titans.size() >= 8 && battle.getResourcesGathered() >= 25) {
//                    System.out.println("3");
                    deployWeapon(1, laneNum);
                } else if (battle.getResourcesGathered() >= 25) {
//                    System.out.println("4");
                    deployWeapon(1, laneNum);
                } else System.err.println("Not Working");

                if (battle.isGameOver()) {
                    stopConditionMet = true;
                    gameOver();
                }
            }
        }
        catch(InvalidLaneException e1){
            pass();
//            System.err.println("Invalid lane");
//            exceptionText.setText("Lane Unavailable");
//            exceptionPane.setVisible(true);
        }
        catch(InsufficientResourcesException e2){
//            System.err.println("Look Here");
            exceptionText.setText("Not enough resources");
            exceptionPane.setVisible(true);
        }
    }
    void deployWeapon(int weaponNum, int laneNum) throws InvalidLaneException, InsufficientResourcesException {
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        try {
            battle.purchaseWeapon(weaponNum, battle.getOriginalLanes().get(laneNum));
            addTitan();
            moveTitans();
            updateApproaching();
            updateLabels();
        }
        catch(InvalidLaneException e1){
            pass();
//            System.err.println("Invalid lane: deploy");
        }
        catch(InsufficientResourcesException e2){
//            System.err.println("Insufficient Resources: deploy");
        }
    }
    void pass() {
        Media mediaClick = new Media(Objects.requireNonNull(getClass().getResource("/game/gui/static/click.mp3")).toExternalForm());
        MediaPlayer click = new MediaPlayer(mediaClick);
        click.play();

        battle.passTurn();
        updateLabels();
        addTitan();
        moveTitans();
        updateApproaching();
        if (battle.isGameOver()){
            gameOver();
            stopConditionMet = true;
        }
    }
}

