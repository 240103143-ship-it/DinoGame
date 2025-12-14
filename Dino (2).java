package game;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Dino {

    public ImageView body;
    private boolean dead = false;
    private boolean isJumping = false;

    public Dino() {
        Image img = new Image("file:src/game/IMG_1083.PNG");
        body = new ImageView(img);

        body.setFitWidth(75);
        body.setFitHeight(70);
        body.setX(50);
        body.setY(205);
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    // ----------- CUSTOM HITBOX for Dino -----------
    public Bounds getHitBox() {
        Bounds b = body.getBoundsInParent();

        return new BoundingBox(
                b.getMinX() + 10,      // сол жақтан кішірейту
                b.getMinY() + 5,       // жоғарыдан
                b.getWidth() - 20,     // енін азайту
                b.getHeight() - 10     // биігін азайту
        );
    }

    public void jump() {
        if (dead) return;
        if (isJumping) return;
        isJumping = true;

        new Thread(() -> {
            try {
                for (int i = 0; i < 40; i++) {
                    if (dead) break;
                    body.setY(body.getY() - 3);
                    Thread.sleep(9);
                }
                for (int i = 0; i < 40; i++) {
                    if (dead) break;
                    body.setY(body.getY() + 3);
                    Thread.sleep(9);
                }

            } catch (Exception ignored) {}

            isJumping = false;
        }).start();
    }
}
