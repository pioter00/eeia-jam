import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class Board extends JPanel implements ActionListener {
    private final int MAX_CARS_ON_SCREEN = 20;
    private final int DELAY = 10;
    private final int CAR_OFFSET = -8;

    private final ArrayList<Cars> cars = new ArrayList<>();
    private Timer timer;
    private Road road;
    BackTrack backTrack;
    ImageIcon imOn = new ImageIcon("src/resources/button_on.png");
    ImageIcon imOff = new ImageIcon("src/resources/button_off.png");

    private JToggleButton[] buttons = { new JToggleButton(imOn, true), new JToggleButton(imOn, true),
            new JToggleButton(imOn, true), new JToggleButton(imOn, true), new JToggleButton(imOn, true),
            new JToggleButton(imOn, true), new JToggleButton(imOn, true), new JToggleButton(imOn, true),
            new JToggleButton(imOn, true), new JToggleButton(imOn, true), new JToggleButton(imOn, true),
            new JToggleButton(imOn, true), new JToggleButton(imOn, true), new JToggleButton(imOn, true),
            new JToggleButton(imOn, true), new JToggleButton(imOn, true), new JToggleButton(imOn, true),
            new JToggleButton(imOn, true), new JToggleButton(imOn, true), new JToggleButton(imOn, true),
            new JToggleButton(imOn, true), new JToggleButton(imOn, true), new JToggleButton(imOn, true),
            new JToggleButton(imOn, true) };
    private Point[] points = { new Point(44, 64), new Point(128, 64), new Point(271, 64), new Point(420, 64),
            new Point(607, 64), new Point(691, 64), new Point(420, 148), new Point(44, 232), new Point(212, 232),
            new Point(332, 232), new Point(420, 276), new Point(691, 276), new Point(332, 360), new Point(420, 360),
            new Point(44, 444), new Point(180, 444), new Point(332, 444), new Point(564, 444), new Point(648, 444),
            new Point(180, 528), new Point(332, 592), new Point(564, 592), new Point(92, 671), new Point(180, 671) };

    private Object prev = null;

    public JToggleButton[] getButtons() {
        return buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (prev != source || source == timer){
            int k = 0;
            for (JToggleButton butt : buttons) {
                if (butt == e.getSource()){
                    if (butt.isSelected()){
                        butt.setIcon(imOn);
                        int xd = 4 * k;
                        road.getCrosses().set(xd, new Cross(road.getCrosses().get(xd).getPosition(), road.getCrosses().get(xd).getVerticalDirection(), road.getCrosses().get(xd++).getHorizontalDirection(), true));
                        road.getCrosses().set(xd, new Cross(road.getCrosses().get(xd).getPosition(), road.getCrosses().get(xd).getVerticalDirection(), road.getCrosses().get(xd++).getHorizontalDirection(), true));
                        road.getCrosses().set(xd, new Cross(road.getCrosses().get(xd).getPosition(), road.getCrosses().get(xd).getVerticalDirection(), road.getCrosses().get(xd++).getHorizontalDirection(), true));
                        road.getCrosses().set(xd, new Cross(road.getCrosses().get(xd).getPosition(), road.getCrosses().get(xd).getVerticalDirection(), road.getCrosses().get(xd++).getHorizontalDirection(), true));
                    }
                    else{
                        butt.setIcon(imOff);
                        int xd = 4 * k;
                        road.getCrosses().set(xd, new Cross(road.getCrosses().get(xd).getPosition(), road.getCrosses().get(xd).getVerticalDirection(), road.getCrosses().get(xd++).getHorizontalDirection(), false));
                        road.getCrosses().set(xd, new Cross(road.getCrosses().get(xd).getPosition(), road.getCrosses().get(xd).getVerticalDirection(), road.getCrosses().get(xd++).getHorizontalDirection(), false));
                        road.getCrosses().set(xd, new Cross(road.getCrosses().get(xd).getPosition(), road.getCrosses().get(xd).getVerticalDirection(), road.getCrosses().get(xd++).getHorizontalDirection(), false));
                        road.getCrosses().set(xd, new Cross(road.getCrosses().get(xd).getPosition(), road.getCrosses().get(xd).getVerticalDirection(), road.getCrosses().get(xd++).getHorizontalDirection(), false));
                    } 
                    break;
                }
                k++;
            }
            prev = source;
            int i = 0;
            ArrayList<Integer> remove = new ArrayList<>();
            for (Cars car : cars) {
                car.move(road.getCrosses(), cars);

                if (goToParking(car)) {
                    remove.add(i);
                }
                ++i;
            }
            i = 0;
            for (Integer j : remove) {
                cars.remove(cars.get(j + i));
                --i;
            }
            if (cars.size() < MAX_CARS_ON_SCREEN) {
                addCars();
            }
            step();
        }
    }

    public void addCars() {
        Random random = new Random();
        for (Cross cross : road.getCrosses()) {
            if (random.nextInt(5) == 1) {
                cars.add(new Cars(cross.getPosition(), new Point(150, 150), cross.getRandomDirection()));
            }
        }
    }

    public Board() {
        initBoard();
    }

    public boolean goToParking(Cars car) {
        Random random = new Random();
        int goOrNOt = random.nextInt(200);
        Point carPosition = car.getPosition();
        if (goOrNOt == 1) {
            for (Point point : road.getParkings()) {
                if (point.equals(carPosition)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void addButtons() {
        for (int i = 0; i < 24; i++) {
            buttons[i].setBounds(points[i].getX(), points[i].getY(), 24, 24);
            add(buttons[i]);
        }
    }
    private void addListeners(){
        for (JToggleButton toggleButton : buttons) {
            toggleButton.addActionListener(this);
        }
    }
    private void doDrawing(Graphics graphics) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        addButtons();
        addListeners();
        ImageIcon imageRoad = new ImageIcon("src/resources/roads/roads_clear.png");
        graphics2d.drawImage(imageRoad.getImage(), 0, 0, this);

        ImageIcon imageBuildings = new ImageIcon("src/resources/roads/buildings.png");
        graphics2d.drawImage(imageBuildings.getImage(), 0, 0, this);

        graphics2d.drawString(String.valueOf(cars.size()), 10, 10);
        for (Cars car : cars) {
            graphics2d.drawImage(car.getImage(), car.getX() + CAR_OFFSET, car.getY() + CAR_OFFSET, this);
        }
    }

    private void initBoard() {
        setBackground(new java.awt.Color(33, 156, 74));
        setFocusable(true);
        road = new Road("src/coords.txt", "src/parking.txt", "src/back_coords.txt");
        addCars();
        backTrack= new BackTrack(buttons);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void step() {
        repaint();
        // for (Cars car : cars) {
        // repaint(car.getX() - 10, car.getY() - 10, car.getWidth() + 20,
        // car.getHeight() + 20);
        // }
    }
}
