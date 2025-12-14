package factory;
import game.Obstacle;

public class SimpleObstacleFactory implements ObstacleFactory {

    @Override
    public Obstacle createObstacle(double x) {
        return new Obstacle(x);
    }
}
