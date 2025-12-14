package game;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Obstacle {

    public ImageView body;

    public Obstacle(double x) {
        Image img = new Image("file:src/game/cactus.png1.webp");
        body = new ImageView(img);

        body.setFitWidth(30);
        body.setFitHeight(50);

        body.setX(x);
        body.setY(210);
    }

    // ---------- CUSTOM HITBOX for cactus ----------
    public Bounds getHitBox() {
        Bounds b = body.getBoundsInParent();

        return new BoundingBox(
                b.getMinX() + 5,       // сол жағынан азайту
                b.getMinY() + 5,       // үстінен
                b.getWidth() - 10,     // енін азайту
                b.getHeight() - 10     // биігін азайту
        );
    }
}
