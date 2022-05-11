package ru.itis.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import ru.itis.base.Direction;
import ru.itis.base.Game;
import ru.itis.gameobjects.Student;
import ru.itis.gameobjects.Mark;

import java.net.URL;
import java.util.*;

public class GameController implements Initializable {

    @FXML
    private Button helloButton;

    @FXML
    private Label helloLabel;

    @FXML
    public AnchorPane pane;

    @FXML
    private void startButtonTapped(ActionEvent event) {
        pane.getChildren().remove(helloButton);
        helloLabel.setText("НАЧАЛИ");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.), animation -> {
            pane.getChildren().remove(helloLabel);
            startGame();
        }));
        timeline.setCycleCount(1);
        timeline.play();

    }

    private Integer wallNumber = 9;

    public void startGame(){
        Student student1 = new Student("Player1", 1200, 700, "/images/student1.png", true, Direction.LEFT);
        Student student2 = new Student("Player2", 100, 100, "/images/student2.png", false, Direction.RIGHT);
        Game.instance.addGameObject(student1);
        Game.instance.addGameObject(student2);

        double value = 0;

        List<Integer> valueList = new ArrayList<>();
        int min = 5;
        int max = 20;
        Random r = new Random();
        int random = r.nextInt((max - min) + 1) + min;

        for (int i=0; i<wallNumber+1; i++){
            valueList.add(random);
            random = r.nextInt((max - min) + 1) + min;
        }

        for (int i = 1; i < wallNumber+1; i++) {
            switch (i % 2){
                case (0):
                    value = 200;
                    break;
                case(1):
                    value = -100;
                    break;
//                        case (2):
//                            value = -50;
//                            break;
//                        case (3):
//                            value = -250;
//                            break;
            }
            Integer k = valueList.get(valueList.size()-1);
            valueList.remove(valueList.size()-1);

            Mark mark = new Mark("wall", 600 + value, i * 100 - 100, k);

            Game.instance.addGameObject(mark);

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageView imageView = new ImageView();
        pane.getChildren().add(imageView);
        imageView.setFitWidth(pane.getWidth());
        imageView.setFitHeight(pane.getHeight());
        Game.instance.setGamingPane(pane);
    }

    public void endGame(){
        System.out.println("end game");
    }
}

