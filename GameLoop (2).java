package game;

import factory.ObstacleFactory;
import factory.SimpleObstacleFactory;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class GameLoop extends AnimationTimer {

    private final Pane gamePane;
    private final Dino dino;

    private static final double WIDTH = 800;
    private static final double HEIGHT = 400;
    private static final double GROUND_HEIGHT = 30;
    private static final double GROUND_Y = HEIGHT - GROUND_HEIGHT;

    private Obstacle obstacle;
    private final ObstacleFactory obstacleFactory = new SimpleObstacleFactory();

    private final List<Cloud> clouds = new ArrayList<>();
    private final List<Snowflake> snowflakes = new ArrayList<>();
    private final List<Rectangle> groundPixels = new ArrayList<>();

    private int score = 0;
    private final Label scoreLabel;
    private boolean gameOver = false;

    private MovementStrategy strategy = new NormalSpeedStrategy();

    public GameLoop(Pane gamePane, Dino dino) {
        this.gamePane = gamePane;
        this.dino = dino;

        Font.loadFont(
                getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
                20
        );

        gamePane.setStyle("-fx-background-color: #707070;");

        createGround();
        createClouds();
        createSnow();

        obstacle = obstacleFactory.createObstacle(WIDTH + 100, GROUND_Y);
        gamePane.getChildren().add(obstacle.body);

        scoreLabel = new Label("Score: 0");
        scoreLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        scoreLabel.setLayoutX(10);
        scoreLabel.setLayoutY(10);
        gamePane.getChildren().add(scoreLabel);

        obstacle.body.toFront();
        dino.body.toFront();
        scoreLabel.toFront();
    }

    public void setStrategy(MovementStrategy strategy) {
        this.strategy = strategy;
    }

    private void createGround() {
        for (int i = 0; i < 60; i++) {
            Rectangle pixel = new Rectangle(8, 3, Color.LIGHTGRAY);
            pixel.setX(i * 14);
            pixel.setY(GROUND_Y);
            groundPixels.add(pixel);
            gamePane.getChildren().add(pixel);
        }
    }

    private void updateGround() {
        for (Rectangle pixel : groundPixels) {
            pixel.setX(pixel.getX() - 3);
            if (pixel.getX() < -20) pixel.setX(WIDTH);
        }
    }

    private void createClouds() {
        for (int i = 0; i < 4; i++) {
            Cloud c = new Cloud(Math.random() * WIDTH, Math.random() * 150);
            clouds.add(c);
            gamePane.getChildren().add(c.cloud);
        }
    }

    private void updateClouds() {
        for (Cloud c : clouds) c.update();
    }

    private void createSnow() {
        for (int i = 0; i < 100; i++) {
            Snowflake flake = new Snowflake(
                    Math.random() * WIDTH,
                    Math.random() * HEIGHT
            );
            snowflakes.add(flake);
            gamePane.getChildren().add(flake.flake);
        }
    }

    private void updateSnow() {
        for (Snowflake flake : snowflakes) {
            flake.update(HEIGHT, WIDTH);
        }
    }

    private void updateObstacle() {
        double newX = strategy.move(obstacle.body.getLayoutX());
        obstacle.body.setLayoutX(newX);

        if (newX < -50) {
            obstacle.body.setLayoutX(WIDTH);
            score++;
            scoreLabel.setText("Score: " + score);
        }
    }

    @Override
    public void handle(long now) {
        if (gameOver) return;

        updateGround();
        updateClouds();
        updateSnow();
        updateObstacle();

        if (dino.getHitBox().intersects(obstacle.getHitBox())) {
            gameOver = true;
            dino.freeze();
            stop();
            showGameOver();
        }
    }
