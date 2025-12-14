package patterns;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import facade.GameFacade;
import game.FastSpeedStrategy;
import game.NormalSpeedStrategy;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        GameFacade game = new GameFacade();
        Pane root = game.startGame();

        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Dino Runner - Сдудент Edition");
        stage.setScene(scene);
        stage.show();

        game.run();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE:
                    game.getDino().jump();
                    break;

                case F:
                    game.getLoop().setStrategy(new FastSpeedStrategy());
                    break;

                case N:
                    game.getLoop().setStrategy(new NormalSpeedStrategy());
                    break;

                case R:
                    game.restartGame();
                    break;
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }
}