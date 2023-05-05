import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Road extends Enum {
    private ArrayList<Cross> crosses;
    private final ArrayList<Point> parkings;

    public ArrayList<Cross> getCrosses() {
        return crosses;
    }

    public ArrayList<Point> getParkings() {
        return parkings;
    }

    public Road(String filename, String parkingFilename, String backTracks) {
        crosses = loadCrosses(filename);
        parkings = loadParking(parkingFilename);
        // crosses = loadBackTracks(backTracks);
        if (crosses == null)
            System.out.println("Error!");
    }

    private static Direction intToDir(int num) {
        if (num == 0)
            return Direction.UP;
        else if (num == 1)
            return Direction.RIGHT;
        else if (num == 2)
            return Direction.DOWN;
        else
            return Direction.LEFT;
    }

    private ArrayList<Cross> loadCrosses(String filename) {
        int x, y, hD, vD;
        ArrayList<Cross> crosses = new ArrayList<>();
        File filePointer;
        try {
            filePointer = new File(filename);
            Scanner myReader = new Scanner(filePointer);
            Scanner scanStr;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!data.contains("//")) {
                    data = data.replace('(', ' ');
                    data = data.replace(',', ' ');
                    data = data.replace(')', ' ');
                    scanStr = new Scanner(data);
                    x = scanStr.nextInt();
                    y = scanStr.nextInt();
                    vD = scanStr.nextInt();
                    hD = scanStr.nextInt();
                    Direction horizontalDirection = intToDir(hD);
                    Direction verticalDirection = intToDir(vD);
                    crosses.add(new Cross(new Point(x, y), verticalDirection, horizontalDirection, true));
                }
            }
            myReader.close();
        } catch (Exception e) {
            return null;
        }
        return crosses;
    }
    private ArrayList<Cross> loadBackTracks(String filename) {
        int x, y, hD, vD;
        ArrayList<Cross> crosses = new ArrayList<>();
        File filePointer;
        try {
            filePointer = new File(filename);
            Scanner myReader = new Scanner(filePointer);
            Scanner scanStr;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!data.contains("//")) {
                    data = data.replace('(', ' ');
                    data = data.replace(',', ' ');
                    data = data.replace(')', ' ');
                    scanStr = new Scanner(data);
                    x = scanStr.nextInt();
                    y = scanStr.nextInt();
                    vD = scanStr.nextInt();
                    hD = scanStr.nextInt();
                    Direction horizontalDirection = intToDir(hD);
                    Direction verticalDirection = intToDir(vD);
                    crosses.add(new Cross(new Point(x, y), verticalDirection, horizontalDirection, false));
                }
            }
            myReader.close();
        } catch (Exception e) {
            return null;
        }
        return crosses;
    }
    private ArrayList<Point> loadParking(String filename) {
        int x, y, length, directionInt;
        ArrayList<Point> parkings = new ArrayList<>();
        File filePointer;
        try {
            filePointer = new File(filename);
            Scanner myReader = new Scanner(filePointer);
            Scanner scanStr;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Direction direction;
                data = data.replace('(', ' ');
                data = data.replace(',', ' ');
                data = data.replace(')', ' ');
                scanStr = new Scanner(data);
                x = scanStr.nextInt();
                y = scanStr.nextInt();
                length = scanStr.nextInt();
                directionInt = scanStr.nextInt();
                direction = intToDir(directionInt);
                for (; length >= 0; length -= 3) {
                    if (direction == Direction.RIGHT) {
                        parkings.add(new Point(x + length, y));
                    } else {
                        parkings.add(new Point(x, y + length));
                    }
                }
            }
            myReader.close();
        } catch (Exception e) {
            return null;
        }
        return parkings;
    }
}
