package facade;
import game.*;
import javafx.scene.layout.Pane;

public class GameFacade {
    private Pane gamePane;
    private GameLoop loop;
    private Dino dino;

    public Pane startGame() {
        gamePane = new Pane();
        gamePane.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #e3f2fd);");

        dino = new Dino();
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
        gamePane.getChildren().clear();

        dino = new Dino();
        gamePane.getChildren().add(dino.body);

        loop.stop();
        loop = new GameLoop(gamePane, dino);
        loop.start();
    }
}
