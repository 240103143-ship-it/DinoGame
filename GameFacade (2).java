package facade;
import game.*;
import javafx.scene.layout.Pane;

public class GameFacade {

    private Pane gamePane;
    private GameLoop loop;
    private Dino dino;

    public static final double SCENE_WIDTH = 800;
    public static final double SCENE_HEIGHT = 400;
    public static final double GROUND_HEIGHT = 30;

    public Pane startGame() {
        gamePane = new Pane();
        double groundY = SCENE_HEIGHT - GROUND_HEIGHT;
        dino = new Dino(groundY);
        gamePane.getChildren().add(dino.body);
        loop = new GameLoop(gamePane, dino);
        return gamePane;
    }

    public void run() {
        loop.start();
    }

    public Dino getDino() {
        return dino;
    }

    public GameLoop getLoop() {
        return loop;
    }

    public void restartGame() {
        loop.stop();
        gamePane.getChildren().clear();

        double groundY = SCENE_HEIGHT - GROUND_HEIGHT;

        dino = new Dino(groundY);
        gamePane.getChildren().add(dino.body);

        loop = new GameLoop(gamePane, dino);
        loop.start();
    }
}
