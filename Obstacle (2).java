package game;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Obstacle {

    public ImageView body;

    public Obstacle(double x, double groundY) {
        Image img = new Image(
                getClass().getResourceAsStream("/images/cactus.png")
        );

        body = new ImageView(img);
        body.setFitHeight(50);
        body.setPreserveRatio(true);

        body.setLayoutX(x);
        body.setLayoutY(groundY - body.getFitHeight());
    }

    public Bounds getHitBox() {
        return body.getBoundsInParent();
    }
}
