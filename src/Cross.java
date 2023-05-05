import java.util.Random;

public class Cross extends Enum {

    private final Direction verticalDirection;
    private final Direction horizontalDirection;
    private final Point position;
    public boolean enabled;

    public Cross(Point position, Direction verticalDirection, Direction horizontalDirection, boolean enabled) {
        this.position = position;
        this.verticalDirection = verticalDirection;
        this.horizontalDirection = horizontalDirection;
        this.enabled = true;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Point getPosition() {
        return new Point(position.getX(),position.getY());
    }

    public Direction getHorizontalDirection() {
        return horizontalDirection;
    }

    public Direction getRandomDirection() {
        Random random = new Random();
        if (random.nextInt(2) == 1) {
            return verticalDirection;
        }
        return horizontalDirection;
    }

    public Direction getVerticalDirection() {
        return verticalDirection;
    }
}
