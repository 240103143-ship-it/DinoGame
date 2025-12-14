package game;
public class NormalSpeedStrategy implements MovementStrategy {
    @Override
    public double move(double currentX) {
        return currentX - 3;
    }
}
