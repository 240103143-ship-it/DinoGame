package game;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cloud {

    public ImageView cloud;

    public Cloud(double x, double y) {
        cloud = new ImageView(new Image("images/cloud.png"));
        cloud.setX(x);
        cloud.setY(y);
        cloud.setFitWidth(60);
        cloud.setFitHeight(30);
    }

    public void update() {
        cloud.setX(cloud.getX() - 1.5);

        if (cloud.getX() < -100) {
            cloud.setX(700);
            cloud.setY(Math.random() * 150);
        }
    }
}
