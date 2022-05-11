package ru.itis.gameobjects;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import org.w3c.dom.Node;
import ru.itis.base.Game;
import ru.itis.base.GameObject;
import ru.itis.gameobjects.common.Destroyable;

public class Mark extends GameObject implements Destroyable {

    private final int value;
    public Mark(String key, double x, double y, int value) {
        super(key);
        Image image = new Image(getClass().getResourceAsStream("/images/circle.png"));
        imageProperty().set(image);
        setFitHeight(100);
        setFitWidth(100);
        setLayoutX(x);
        setLayoutY(y);

        TextField textField = new TextField();
        Label label = new Label(String.valueOf(value));
        label.setLabelFor(textField);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {
        Boom boom = new Boom(getBoundsInParent().getCenterX(), getBoundsInParent().getCenterY());
        Game.instance.addGameObject(boom);
        Game.instance.removeGameObject(this);
    }

    //TODO: наложить баллы на кругляшок
}
