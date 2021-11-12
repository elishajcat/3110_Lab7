import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel {


    public static final int SIZE = 3;
    public static final boolean X = true;
    public static final boolean O = false;

    public enum Status {X_WON, O_WON, TIE, UNDECIDED};
    public enum Wins {FIRST_ROW, SECOND_ROW, THIRD_ROW, FIRST_COLUMN, SECOND_COLUMN, THIRD_COLUMN, DOWN_DIAGONAL, UP_DIAGONAL};

    private char[][] grid;
    private boolean turn;
    private Status status;
    private List<TicTacToeView> views;


    public TicTacToeModel() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
        turn = X;
        status = Status.UNDECIDED;
        views = new ArrayList<>();

    }

    public void addTicTacToeView(TicTacToeView view) {
        views.add(view);
    }

    public void removeTicTacToeView(TicTacToeView view) {
        views.remove(view);
    }

    private void changeTurn() {
        turn = !turn;
    }

    public Status getStatus() {
        return status;
    }

    private void updateStatus() {
        int filledSquares = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] != ' '){
                    filledSquares += 1;
                }
            }
            if (filledSquares == 9) {
                status = Status.TIE;
            }
            if (grid[i][0] == 'X' && grid[i][1] == 'X' && grid[i][2] == 'X') {
                status = Status.X_WON;
            } else if (grid[i][0] == 'O' && grid[i][1] == 'O' && grid[i][2] == 'O') {
                status = Status.O_WON;
            } else if (grid[0][i] == 'X' && grid[1][i] == 'X' && grid[2][i] == 'X') {
                status = Status.X_WON;
            } else if (grid[0][i] == 'O' && grid[1][i] == 'O' && grid[2][i] == 'O') {
                status = Status.O_WON;
            } else if (grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X') {
                status = Status.X_WON;
            } else if (grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O') {
                status = Status.O_WON;
            } else if (grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X') {
                status = Status.X_WON;
            } else if (grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O') {
                status = Status.O_WON;
            }
        }

        return;
    }

    public boolean getTurn() {
        return turn;
    }

    public void play(int x, int y) {
        if (grid[x][y] != ' ') return;
        grid[x][y] = turn? 'X' : 'O';
        updateStatus();
        for(TicTacToeView view: views) {
            view.handleTicTacToeStatusUpdate(new TicTacToeEvent(this, status, x, y));
        }
        changeTurn();
    }
}

