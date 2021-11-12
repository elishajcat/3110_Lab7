import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame implements TicTacToeView {

    private JButton[][] buttons;

    public TicTacToeFrame() {
        super("Tic Tac Toe!");

        this.setLayout(new GridLayout(TicTacToeModel.SIZE, TicTacToeModel.SIZE));

        TicTacToeModel model = new TicTacToeModel();

        model.addTicTacToeView(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);

        buttons = new JButton[TicTacToeModel.SIZE][TicTacToeModel.SIZE];

        //TicTacToeController tttc = new TicTacToeController(model);

        for (int i = 0; i < TicTacToeModel.SIZE; i++) {
            for (int j = 0; j < TicTacToeModel.SIZE; j++) {
                JButton button = new JButton(" ");
                //button.setActionCommand(i + " " + j);
                buttons[i][j] = button;
                int x = i;
                int y = j;
                button.addActionListener( e -> model.play(x, y));
                this.add(button);
            }
        }
        this.setVisible(true);
    }

    @Override
    public void handleTicTacToeStatusUpdate(TicTacToeEvent e) {
        TicTacToeModel ticTacToeModel = (TicTacToeModel) e.getSource();
        String label = ticTacToeModel.getTurn()? "X" : "O";
        buttons[e.getX()][e.getY()].setText(label);

        checkWin(ticTacToeModel);
    }

    private void checkWin(TicTacToeModel ticTacToeModel) {
        if (ticTacToeModel.getStatus() == TicTacToeModel.Status.X_WON) {
            JOptionPane.showMessageDialog(new JFrame(), "X Won! The Game is Over.");
            setVisible(false);
            dispose();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else if (ticTacToeModel.getStatus() == TicTacToeModel.Status.O_WON) {
            JOptionPane.showMessageDialog(new JFrame(), "O Won! The Game is Over.");
            setVisible(false);
            dispose();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else if (ticTacToeModel.getStatus() == TicTacToeModel.Status.TIE) {
            JOptionPane.showMessageDialog(new JFrame(), "It's a Tie! The Game is Over.");
            setVisible(false);
            dispose();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public static void main(String args[]) {
        new TicTacToeFrame();
    }
}
