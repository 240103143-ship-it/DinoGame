//package game;
//import javafx.animation.AnimationTimer;
//import javafx.scene.layout.Pane;
//import factory.SimpleObstacleFactory;
//import javafx.scene.image.ImageView;
//import javafx.scene.control.Label;
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//private ImageView ground1;
//private ImageView ground2;
//
//private List<Cloud> clouds = new ArrayList<>();
//
//
//
//
//public class GameLoop extends AnimationTimer {
//
//    private Pane gamePane;
//    private Dino dino;
//    private SimpleObstacleFactory obstacleFactory;
//    private Obstacle obstacle;
//
//    private int score = 0;
//    private Label scoreLabel;
//    private boolean gameOver = false;
//
//
//    private List<Snowflake> snowflakes = new ArrayList<>();
//
//    private MovementStrategy strategy = new NormalSpeedStrategy();
//    public void setStrategy(MovementStrategy strategy) {
//        this.strategy = strategy;
//    }
//
//    public GameLoop(Pane gamePane, Dino dino) {
//        this.gamePane = gamePane;
//        this.dino = dino;
//        this.obstacleFactory = new SimpleObstacleFactory();
//
//
//        obstacle = obstacleFactory.createObstacle(600);
//        gamePane.getChildren().add(obstacle.body);
//
//        scoreLabel = new Label("Score: 0");
//        scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
//        scoreLabel.setLayoutX(10);
//        scoreLabel.setLayoutY(10);
//        gamePane.getChildren().add(scoreLabel);
//        createSnow();
//
//
//        ground1 = new ImageView("images/ground.png");
//        ground2 = new ImageView("images/ground.png");
//
//        ground1.setFitWidth(600);
//        ground2.setFitWidth(600);
//
//        ground1.setFitHeight(40);
//        ground2.setFitHeight(40);
//
//        ground1.setY(240);
//        ground2.setY(240);
//
//        ground1.setX(0);
//        ground2.setX(600);
//
//        gamePane.getChildren().addAll(ground1, ground2);
//
//        createClouds();
//
//    }
//
//
//    private void createSnow() {
//        for (int i = 0; i < 80; i++) {
//            Snowflake flake = new Snowflake(Math.random() * 600, Math.random() * 300);
//            snowflakes.add(flake);
//            gamePane.getChildren().add(flake.flake);
//        }
//    }
//
//    private void updateSnow() {
//        for (Snowflake flake : snowflakes) {
//            flake.update();
//        }
//    }
//
//    private void showGameOver() {
//        Label over = new Label("GAME OVER  \n  R to restart");
//        over.setStyle("-fx-font-size: 50px; -fx-text-fill: #087630;");
//        over.setLayoutX(235);
//        over.setLayoutY(100);
//
//        gamePane.getChildren().add(over);
//    }
//
//    private void createClouds() {
//        for (int i = 0; i < 4; i++) {
//            Cloud c = new Cloud(Math.random() * 600, Math.random() * 120);
//            clouds.add(c);
//            gamePane.getChildren().add(c.cloud);
//        }
//    }
//
//
//
//
//
//    private void updateGround() {
//
//        ground1.setX(ground1.getX() - 3);
//        ground2.setX(ground2.getX() - 3);
//
//        if (ground1.getX() <= -600) {
//            ground1.setX(600);
//        }
//        if (ground2.getX() <= -600) {
//            ground2.setX(600);
//        }
//    }
//
//    private void updateClouds() {
//        for (Cloud c : clouds) {
//            c.update();
//        }
//    }
//
//
//
//    @Override
//    public void handle(long now) {
//
//        updateGround();
//        updateClouds();
//
//
//        if (gameOver) return;
//        updateSnow();
//        updateObstacle();
//
//        if (isColliding(dino, obstacle)) {
//            dino.setDead(true);
//            stop();
//            gameOver = true;
//            showGameOver();
//        }
//
//    }
//
//    private boolean isColliding(Dino dino, Obstacle obs) {
//        return dino.getHitBox().intersects(obs.getHitBox());
//    }
//
//
//    private void updateObstacle() {
//        if (gameOver) return;
//
//        double newX = strategy.move(obstacle.body.getX());
//        obstacle.body.setX(newX);
//
//        if (newX < -50) {
//            obstacle.body.setX(600);
//
//            score++;
//            scoreLabel.setText("Score: " + score);
//        }
//    }

//
//
//    public void restart() {
//        score=0;
//        gamePane.getChildren().removeIf(node ->
//                node instanceof Label && ((Label) node).getText().contains("GAME OVER")
//        );
//
//        scoreLabel.setText("Score: 0");
//        dino.setDead(false);
//        dino.body.setX(50);
//        dino.body.setY(200);
//        obstacle.body.setX(600);
//
//        start();
//    }
//package game;
//
//import javafx.animation.AnimationTimer;
//import javafx.scene.layout.Pane;
//import factory.SimpleObstacleFactory;
//import javafx.scene.image.ImageView;
//import javafx.scene.control.Label;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GameLoop extends AnimationTimer {
//
//    private Pane gamePane;
//    private Dino dino;
//    private SimpleObstacleFactory obstacleFactory;
//    private Obstacle obstacle;
//
//    private ImageView ground1;
//    private ImageView ground2;
//
//
//    private boolean isNight = false;
//    private long lastTimeSwitch = 0;
//
//
//
//
//
//    private List<Cloud> clouds = new ArrayList<>();
//
//    private int score = 0;
//    private Label scoreLabel;
//    private boolean gameOver = false;
//
//    private List<Snowflake> snowflakes = new ArrayList<>();
//
//    private MovementStrategy strategy = new NormalSpeedStrategy();
//    public void setStrategy(MovementStrategy strategy) {
//        this.strategy = strategy;
//    }
//
//    public GameLoop(Pane gamePane, Dino dino) {
//        this.gamePane = gamePane;
//        this.dino = dino;
//        this.obstacleFactory = new SimpleObstacleFactory();
//
//        // obstacle
//        obstacle = obstacleFactory.createObstacle(600);
//        gamePane.getChildren().add(obstacle.body);
//
//        // score
//        scoreLabel = new Label("Score: 0");
//        scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
//        scoreLabel.setLayoutX(10);
//        scoreLabel.setLayoutY(10);
//        gamePane.getChildren().add(scoreLabel);
//
//        createSnow();
//        createGround();
//        createClouds();
//
//
//        private void toggleDayNight() {
//            if (isNight) {
//                // КҮН
//                gamePane.setStyle("-fx-background-color: white;");
//                scoreLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");
//                isNight = false;
//            } else {
//                // ТҮН
//                gamePane.setStyle("-fx-background-color: black;");
//                scoreLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
//                isNight = true;
//            }
//        }
//
//
//        gamePane.setStyle("-fx-background-color: white;");
//
//
//
//
//
//
//    }
//
//    private void createGround() {
//        ground1 = new ImageView("images/ground.png");
//        ground2 = new ImageView("images/ground.png");
//
//        ground1.setFitWidth(600);
//        ground2.setFitWidth(600);
//
//        ground1.setFitHeight(150);
//        ground2.setFitHeight(150);
//
//        ground1.setY(240);
//        ground2.setY(240);
//
//        ground1.setX(0);
//        ground2.setX(600);
//
//        gamePane.getChildren().addAll(ground1, ground2);
//    }
//
//    private void updateGround() {
//        ground1.setX(ground1.getX() - 3);
//        ground2.setX(ground2.getX() - 3);
//
//        if (ground1.getX() <= -600) {
//            ground1.setX(600);
//        }
//        if (ground2.getX() <= -600) {
//            ground2.setX(600);
//        }
//    }
//
//    private void createClouds() {
//        for (int i = 0; i < 4; i++) {
//            Cloud c = new Cloud(Math.random() * 600, Math.random() * 120);
//            clouds.add(c);
//            gamePane.getChildren().add(c.cloud);
//        }
//    }
//
//    private void updateClouds() {
//        for (Cloud c : clouds) {
//            c.update();
//        }
//    }
//
//    private void createSnow() {
//        for (int i = 0; i < 80; i++) {
//            Snowflake flake = new Snowflake(Math.random() * 600, Math.random() * 300);
//            snowflakes.add(flake);
//            gamePane.getChildren().add(flake.flake);
//        }
//    }
//
//    private void updateSnow() {
//        for (Snowflake flake : snowflakes) {
//            flake.update();
//        }
//    }
//
//    private void showGameOver() {
//        Label over = new Label("GAME OVER\nPress R to restart");
//        over.setStyle("-fx-font-size: 50px; -fx-text-fill: red;");
//        over.setLayoutX(160);
//        over.setLayoutY(100);
//
//        gamePane.getChildren().add(over);
//    }
//
//    @Override
//    public void handle(long now) {
//
//        updateGround();
//        updateClouds();
//
//        if (gameOver) return;
//
//        updateSnow();
//        updateObstacle();
//
//        if (now - lastTimeSwitch > 10_000_000_000L) {  // 10 секунд
//            toggleDayNight();
//            lastTimeSwitch = now;
//        }
//
//
//        if (isColliding(dino, obstacle)) {
//            dino.setDead(true);
//            stop();
//            gameOver = true;
//            showGameOver();
//        }
//    }
//
//    private boolean isColliding(Dino dino, Obstacle obs) {
//        return dino.getHitBox().intersects(obs.getHitBox());
//    }
//
//    private void updateObstacle() {
//        if (gameOver) return;
//
//        double newX = strategy.move(obstacle.body.getX());
//        obstacle.body.setX(newX);
//
//        if (newX < -50) {
//            obstacle.body.setX(600);
//
//            score++;
//            scoreLabel.setText("Score: " + score);
//        }
//    }
//}
//
package game;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import factory.SimpleObstacleFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class GameLoop extends AnimationTimer {

    private Pane gamePane;
    private Dino dino;
    private SimpleObstacleFactory obstacleFactory;
    private Obstacle obstacle;

    private boolean isNight = false;
    private long lastTimeSwitch = 0;

    private List<Cloud> clouds = new ArrayList<>();
    private List<Snowflake> snowflakes = new ArrayList<>();
    private List<Rectangle> groundPixels = new ArrayList<>();

    private int score = 0;
    private Label scoreLabel;
    private boolean gameOver = false;

    private MovementStrategy strategy = new NormalSpeedStrategy();

    public void setStrategy(MovementStrategy strategy) {
        this.strategy = strategy;
    }

    public GameLoop(Pane gamePane, Dino dino) {
        this.gamePane = gamePane;
        this.dino = dino;
        this.obstacleFactory = new SimpleObstacleFactory();

        // Obstacle
        obstacle = obstacleFactory.createObstacle(600);
        gamePane.getChildren().add(obstacle.body);

        // Score label
        scoreLabel = new Label("Score: 0");
        scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
        scoreLabel.setLayoutX(10);
        scoreLabel.setLayoutY(10);
        gamePane.getChildren().add(scoreLabel);

        // Effects
        createSnow();
        createPixelGround();  // ⭐ PIXEL GROUND
        createClouds();

        // Default DAY mode
        gamePane.setStyle("-fx-background-color: white;");
    }

    // ---------------- DAY / NIGHT LOGIC ----------------

    private void toggleDayNight() {
        if (isNight) {
            // DAY
            gamePane.setStyle("-fx-background-color: white;");
            scoreLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");
            isNight = false;
        } else {
            // NIGHT
            gamePane.setStyle("-fx-background-color: green;");
            scoreLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
            isNight = true;
        }
    }

    // ---------------- PIXEL GROUND ----------------

    private void createPixelGround() {
        for (int i = 0; i < 40; i++) {
            Rectangle pixel = new Rectangle(6, 3, Color.GRAY); // пиксель блогы
            pixel.setX(i * 15);
            pixel.setY(260);
            groundPixels.add(pixel);
            gamePane.getChildren().add(pixel);
        }
    }

    private void updatePixelGround() {
        for (Rectangle pixel : groundPixels) {
            pixel.setX(pixel.getX() - 3);

            if (pixel.getX() < -20) {
                pixel.setX(600);
            }
        }
    }

    // ---------------- CLOUDS ----------------

    private void createClouds() {
        for (int i = 0; i < 4; i++) {
            Cloud c = new Cloud(Math.random() * 600, Math.random() * 120);
            clouds.add(c);
            gamePane.getChildren().add(c.cloud);
        }
    }

    private void updateClouds() {
        for (Cloud c : clouds) c.update();
    }

    // ---------------- SNOW ----------------

    private void createSnow() {
        for (int i = 0; i < 80; i++) {
            Snowflake flake = new Snowflake(Math.random() * 600, Math.random() * 300);
            snowflakes.add(flake);
            gamePane.getChildren().add(flake.flake);
        }
    }

    private void updateSnow() {
        for (Snowflake flake : snowflakes) flake.update();
    }

    // ---------------- GAMEPLAY LOGIC ----------------

    private boolean isColliding(Dino dino, Obstacle obs) {
        return dino.getHitBox().intersects(obs.getHitBox());
    }

    private void updateObstacle() {
        if (gameOver) return;

        double newX = strategy.move(obstacle.body.getX());
        obstacle.body.setX(newX);

        if (newX < -50) {
            obstacle.body.setX(600);

            score++;
            scoreLabel.setText("Score: " + score);
        }
    }

    // ---------------- GAME LOOP ----------------

    @Override
    public void handle(long now) {

        updatePixelGround();  // ⭐ PIXEL GROUND UPDATE
        updateClouds();

        if (gameOver) return;

        updateSnow();
        updateObstacle();

        // DAY/NIGHT every 10 seconds
        if (now - lastTimeSwitch > 10_000_000_000L) {
            toggleDayNight();
            lastTimeSwitch = now;
        }

        if (isColliding(dino, obstacle)) {
            dino.setDead(true);
            gameOver = true;
            stop();
            showGameOver();
        }
    }

    private void showGameOver() {
        Label over = new Label("GAME OVER\nPress R to restart");
        over.setStyle("-fx-font-size: 50px; -fx-text-fill: red;");
        over.setLayoutX(160);
        over.setLayoutY(100);

        gamePane.getChildren().add(over);
    }
}

