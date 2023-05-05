import java.util.*;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Cars extends Enum {
    private final int SPEED = 3;
    private Integer speed;
    Point destinationPoint;
    Point startPoint;
    private Point currentPosition;
    private Direction direction;
    private final int width;
    private final int height;
    private Image image;
    private final ImageIcon[] images;
    private final Random random = new Random();

    private final ImageIcon[][] carColor = { { new ImageIcon("src/resources/car_colors/car_U0.png"),
            new ImageIcon("src/resources/car_colors/car_R0.png"), new ImageIcon("src/resources/car_colors/car_D0.png"),
            new ImageIcon("src/resources/car_colors/car_L0.png") },
            { new ImageIcon("src/resources/car_colors/car_U1.png"),
                    new ImageIcon("src/resources/car_colors/car_R1.png"),
                    new ImageIcon("src/resources/car_colors/car_D1.png"),
                    new ImageIcon("src/resources/car_colors/car_L1.png") },
            { new ImageIcon("src/resources/car_colors/car_U2.png"),
                    new ImageIcon("src/resources/car_colors/car_R2.png"),
                    new ImageIcon("src/resources/car_colors/car_D2.png"),
                    new ImageIcon("src/resources/car_colors/car_L2.png") } };

    public Cars(Point startPosition, Point destination, Direction dir) {
        speed = 0;
        currentPosition = startPosition;
        startPoint = startPosition;
        destinationPoint = destination;
        direction = dir;
        images = carColor[random.nextInt(3)];
        selectImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Point getPosition() {
        return currentPosition;
    }

    private void selectImage() {
        switch (direction) {
        case UP: {
            image = images[0].getImage();
            break;
        }
        case RIGHT: {
            image = images[1].getImage();
            break;
        }
        case DOWN: {
            image = images[2].getImage();
            break;
        }
        case LEFT: {
            image = images[3].getImage();
            break;
        }
        }
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return currentPosition.getX();
    }

    public int getY() {
        return currentPosition.getY();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void changeSpeed(ArrayList<Cars> otherCars) {
        int carIndexHolder = -1;
        int curY = currentPosition.getY();
        int curX = currentPosition.getX();
        int otherCarsNumber = otherCars.size();
        switch (direction) {
        case UP: {
            for (int i = 1; i <= speed + height; i++) {
                for (int j = 0; j < otherCarsNumber; j++) {
                    if (otherCars.get(j).currentPosition.getY() == curY - i
                            && otherCars.get(j).currentPosition.getX() == curX) {
                        carIndexHolder = j; // if other car is ahead of this car
                    }
                }
            }
            break;
        }
        case DOWN: {
            for (int i = 1; i <= speed + height; i++) {
                for (int j = 0; j < otherCarsNumber; j++) {
                    if (otherCars.get(j).currentPosition.getY() == curY + i
                            && otherCars.get(j).currentPosition.getX() == curX) {
                        carIndexHolder = j; // if other car is ahead of this car
                    }
                }
            }
            break;
        }
        case LEFT: {
            for (int i = 1; i <= speed + height; i++) {
                for (int j = 0; j < otherCarsNumber; j++) {
                    if (otherCars.get(j).currentPosition.getY() == curY
                            && otherCars.get(j).currentPosition.getX() == curX - i) {
                        carIndexHolder = j; // if other car is ahead of this car
                        // System.out.println("C1 "+currentPosition.getX()+" "+currentPosition.getY()+"
                        // C2 "+otherCars.get(j).currentPosition.getX()+"
                        // "+otherCars.get(j).currentPosition.getY()+" C2 ");
                    }
                }

            }
        }
        case RIGHT: {
            for (int i = 1; i <= speed + height; i++) {
                for (int j = 0; j < otherCarsNumber; j++) {
                    if (otherCars.get(j).currentPosition.getY() == curY
                            && otherCars.get(j).currentPosition.getX() == curX + i) {
                        carIndexHolder = j; // if other car is ahead of this car
                    }
                }
            }
        }
        }

        if (carIndexHolder != -1) {
            speed = otherCars.get(carIndexHolder).speed;
            if (speed > 0 && !direction.equals(Enum.Direction.LEFT)) {
                speed--;
            }

        } else {
            if (speed <= SPEED) {
                speed++;
            }
        }
    }

    private int chechCross(ArrayList<Cross> crosses, ArrayList<Cars> otherCars) { // return -1 if already moved
                                                                                  // otherwise 0
        int crossIndexHolder = -1;
        int curY = currentPosition.getY();
        int curX = currentPosition.getX();
        int crossesNumber = crosses.size();
        switch (direction) {
        case UP: {
            for (int i = 1; i <= speed; i++) {
                for (int j = 0; j < crossesNumber; j++) {
                    if (crosses.get(j).getPosition().getY() == curY - i
                            && crosses.get(j).getPosition().getX() == curX) {
                        crossIndexHolder = j; // if cross is achead
                    }
                }
            }
            break;
        }
        case DOWN: {
            for (int i = 1; i <= speed; i++) {
                for (int j = 0; j < crossesNumber; j++) {
                    if (crosses.get(j).getPosition().getY() == curY + i
                            && crosses.get(j).getPosition().getX() == curX) {
                        crossIndexHolder = j; // if cross is achead
                    }
                }
            }
            break;
        }
        case LEFT: {
            for (int i = 1; i <= speed; i++) {
                for (int j = 0; j < crossesNumber; j++) {
                    if (crosses.get(j).getPosition().getY() == curY
                            && crosses.get(j).getPosition().getX() == curX - i) {
                        crossIndexHolder = j; // if cross is achead
                    }
                }

            }
            break;
        }
        case RIGHT: {
            for (int i = 1; i <= speed; i++) {
                for (int j = 0; j < crossesNumber; j++) {
                    if (crosses.get(j).getPosition().getY() == curY
                            && crosses.get(j).getPosition().getX() == curX + i) {
                        crossIndexHolder = j; // if cross is achead
                    }
                }
            }
            break;
        }
        }

        if (crossIndexHolder != -1) {
            Cross cross = crosses.get(crossIndexHolder);
            if (!cross.enabled) {
                turnAround();
                return -1;
            }
            for (Cars car2 : otherCars)
                if (cross.getPosition().equals(car2.currentPosition)) {
                    speed = 0;
                    return -1;
                }

            currentPosition = cross.getPosition();
            if (!cross.getHorizontalDirection().equals(cross.getVerticalDirection())) {
                if (random.nextInt(2) == 1) {
                    changeDirection(cross);
                }
            } else {
                direction = cross.getHorizontalDirection();
            }
            selectImage();

            return -1;
        }
        return 0;
    }

    private void changeDirection(Cross cross) {

        if (cross.getVerticalDirection().equals(direction)) {
            direction = cross.getHorizontalDirection();
        } else if (cross.getHorizontalDirection().equals(direction)) {
            direction = cross.getVerticalDirection();
        }

    }

    private void turnAround() {
        if (direction.equals(Direction.UP)) {
            direction = Direction.DOWN;
            currentPosition.setX(currentPosition.getX() - 13);
        } else if (direction.equals(Direction.DOWN)) {
            direction = Direction.UP;
            currentPosition.setX(currentPosition.getX() + 13);
        } else if (direction.equals(Direction.LEFT)) {
            direction = Direction.RIGHT;
            currentPosition.setY(currentPosition.getY() + 13);
        } else if (direction.equals(Direction.RIGHT)) {
            direction = Direction.LEFT;
            currentPosition.setY(currentPosition.getY() - 13);
        }
    }

    public void move(ArrayList<Cross> crosses, ArrayList<Cars> otherCars) {
        int curY = currentPosition.getY();
        int curX = currentPosition.getX();
        if (chechCross(crosses, otherCars) == 0) {
            if (direction.equals(Direction.UP)) {
                currentPosition.setY(curY - speed);
            } else if (direction.equals(Direction.DOWN)) {
                currentPosition.setY(curY + speed);
            } else if (direction.equals(Direction.LEFT)) {
                currentPosition.setX(curX - speed);
            } else if (direction.equals(Direction.RIGHT)) {
                currentPosition.setX(curX + speed);
            }
        }
        changeSpeed(otherCars);

        if (speed == 0) {
            turnAround();

        }
    }

}
