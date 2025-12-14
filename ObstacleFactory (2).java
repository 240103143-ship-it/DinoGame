package factory;
import game.Obstacle;

public interface ObstacleFactory {
    Obstacle createObstacle(double x);
}
