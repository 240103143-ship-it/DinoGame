package game;

public class FastSpeedStrategy implements MovementStrategy {
    @Override
    public double move(double currentX) {
        return currentX - 6;
    }
}
