package ru.itis.gameobjects;

import javafx.scene.image.Image;
import ru.itis.Main;
import ru.itis.base.Direction;
import ru.itis.base.Game;
import ru.itis.base.GameObject;
import ru.itis.base.KeyListener;
import ru.itis.controller.GameController;
import ru.itis.gameobjects.common.Destroyable;
import ru.itis.utills.SceneEndChecker;

import java.util.List;
import java.util.Objects;


public class Student extends GameObject {

    public Student(String key, double x, double y, String imagePath, boolean isFirstPlayer, Direction direction) {
        super(key);
        Image image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream(imagePath)));
        imageProperty().set(image);
        setFitHeight(120);
        setFitWidth(120);
        setLayoutX(x);
        setLayoutY(y);
        this.direction = direction;
//        updateTankDirection();
        this.isFirstPlayer = isFirstPlayer;
    }

    private Direction direction;
    private final boolean isFirstPlayer;


    private Integer score = 0;

    public boolean getIsFirstPlayer() {
        return isFirstPlayer;
    }
    public GameController gameController;

    @Override
    public void update() {
        if(KeyListener.instance.up(isFirstPlayer)) {
            direction = Direction.UP;
            if (SceneEndChecker.moveAcceptable(this, Direction.UP)) {
                setLayoutY(getLayoutY() - 10);
            }
        } else if(KeyListener.instance.down(isFirstPlayer)) {
            direction = Direction.DOWN;
            if (SceneEndChecker.moveAcceptable(this, Direction.DOWN)) {
                setLayoutY(getLayoutY() + 10);
            }
        } else if(KeyListener.instance.left(isFirstPlayer)) {
            direction = Direction.LEFT;
            if (SceneEndChecker.moveAcceptable(this, Direction.LEFT)) {
                setLayoutX(getLayoutX() - 10);
            }
        } else if(KeyListener.instance.right(isFirstPlayer)) {
            direction = Direction.RIGHT;
            if (SceneEndChecker.moveAcceptable(this, Direction.RIGHT)) {
                setLayoutX(getLayoutX() + 10);
            }
        }

        List<GameObject> destroyableList = Game.instance.getAllDestroyable();
        for (GameObject gameObject : destroyableList) {
            if (getBoundsInParent().intersects(gameObject.getBoundsInParent())) {
                Destroyable destroyable = (Destroyable) gameObject;
                Mark mark = (Mark)gameObject;
                score += mark.getValue();
                destroyable.destroy();

                if (Game.instance.getAllDestroyable().size() == 1){
                    //TODO end game:
                    gameController.endGame();
//                    System.out.println(destroyableList.size());
                }

                if(isFirstPlayer){
                    System.out.println("1: " + score);
                } else{
                    System.out.println("2: " + score);
                }
            }
        }
//        updateTankDirection();
    }

//    void updateTankDirection() {
//        switch (direction) {
//            case UP: setRotate(-90); break;
//            case DOWN: setRotate(90); break;
//            case RIGHT: setRotate(0); break;
//            case LEFT: setRotate(180); break;
//        }
//    }
//TODO: фотки
}
