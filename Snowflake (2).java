package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Snowflake {

    public Circle flake;
    private double speed;

    public Snowflake(double x, double y) {
        flake = new Circle(2, Color.rgb(230, 230, 230));
        flake.setCenterX(x);
        flake.setCenterY(y);
        speed = 1 + Math.random() * 2;
    }

    public void update(double height, double width) {
        flake.setCenterY(flake.getCenterY() + speed);
        if (flake.getCenterY() > height) {
            flake.setCenterY(0);
            flake.setCenterX(Math.random() * width);
        }
    }
}
