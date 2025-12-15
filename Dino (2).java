package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Dino {

    public ImageView body;

    private boolean dead = false;
    private boolean isJumping = false;

    private Timeline jumpUp;
    private Timeline jumpDown;

    private static final double WIDTH = 75;
    private static final double HEIGHT = 70;

    public Dino(double groundY) {
        Image img = new Image(
                getClass().getResource("/game/Dino.PNG").toExternalForm()
        );

        body = new ImageView(img);
        body.setFitWidth(WIDTH);
        body.setFitHeight(HEIGHT);
        body.setX(50);
        body.setY(groundY - HEIGHT);
    }

    public void freeze() {
        dead = true;
        isJumping = false;

        if (jumpUp != null) jumpUp.stop();
        if (jumpDown != null) jumpDown.stop();
    }

    public Bounds getHitBox() {
        Bounds b = body.getBoundsInParent();
        return new BoundingBox(
                b.getMinX() + 12,
                b.getMinY() + 8,
                b.getWidth() - 24,
                b.getHeight() - 16
        );
    }

    public void jump() {
        if (dead || isJumping) return;

        isJumping = true;

        jumpUp = new Timeline(
                new KeyFrame(Duration.millis(10),
                        e -> body.setY(body.getY() - 3))
        );
        jumpUp.setCycleCount(35);

        jumpDown = new Timeline(
                new KeyFrame(Duration.millis(10),
                        e -> body.setY(body.getY() + 3))
        );
        jumpDown.setCycleCount(35);

        jumpUp.setOnFinished(e -> {
            if (!dead) jumpDown.play();
        });

        jumpDown.setOnFinished(e -> isJumping = false);

        jumpUp.play();
    }
}
