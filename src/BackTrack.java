import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JToggleButton;

public class BackTrack extends Enum {
    private ArrayList<Cross> backTrack;
    private ArrayList<Boolean> onOff;
    private JToggleButton[] buttons;

    public BackTrack(JToggleButton[] tab) {
        buttons = tab;
    }

    public void buttonChecker() {
        for (int i = 0, j = 0; i < buttons.length; i++) {
            if (!buttons[i].isSelected()) {
                for (int k = 0; k < 8; k++)
                    onOff.set(j++, true);
            }
            else{
                for (int k = 0; k < 8; k++)
                    onOff.set(j++, false);
            }
        }
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
        int x, y, hD, vD, booli;
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
                    booli = scanStr.nextInt();
                    Direction horizontalDirection = intToDir(hD);
                    Direction verticalDirection = intToDir(vD);
                    crosses.add(new Cross(new Point(x, y), verticalDirection, horizontalDirection, true));
                    if (booli == 0)
                        onOff.add(false);
                    else
                        onOff.add(true);
                }
            }
            myReader.close();
        } catch (Exception e) {
            return null;
        }
        return crosses;
    }

}
