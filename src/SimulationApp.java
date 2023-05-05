import java.awt.EventQueue;
import javax.swing.JFrame;

public class SimulationApp extends JFrame {
    private final int SIZEX = 768;
    private final int SIZEY = 768;

    private final Board board = new Board();
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SimulationApp ex = new SimulationApp();
            ex.setVisible(true);
        });

    }

    public SimulationApp() {
        initUI();
    }

    private void initUI() {
        add(board);
        setTitle("Simulation");
        setSize(SIZEX, SIZEY);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
