package ru.itis.gameobjects;

import javafx.scene.image.Image;
import ru.itis.base.Game;
import ru.itis.base.GameObject;

public class Boom extends GameObject {

    private long initializedTime = 0;

    public Boom(double x, double y) {
        super("boom");
        isCollision = false;
        Image image = new Image(getClass().getResourceAsStream("/images/gr.png"));
        imageProperty().set(image);
        setFitHeight(80);
        setFitWidth(80);
        setLayoutX(x - 50);
        setLayoutY(y - 50);
        initializedTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() - initializedTime > 1500) {
            Game.instance.removeGameObject(this);
        }
    }
}
