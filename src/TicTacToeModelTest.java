import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeModelTest {
    TicTacToeModel tttm1;
    TicTacToeModel tttm2;
    TicTacToeModel tttm3;

    @Test
    public void getStatus() {
        tttm1 = new TicTacToeModel();
        assertEquals(TicTacToeModel.Status.UNDECIDED, tttm1.getStatus()); // when the game just started, status should be undecided

        tttm1.play(0,0);
        tttm1.play(1,1);
        tttm1.play(0,1);
        tttm1.play(2,1);
        tttm1.play(0,2);

        assertEquals(TicTacToeModel.Status.X_WON, tttm1.getStatus()); // when the game just started, status should be undecided

        tttm2 = new TicTacToeModel();
        tttm2.play(0,1);
        tttm2.play(0,0);
        tttm2.play(2,1);
        tttm2.play(1,1);
        tttm2.play(0,2);
        tttm2.play(2,2);

        assertEquals(TicTacToeModel.Status.O_WON, tttm2.getStatus()); // when the game just started, status should be undecided

        tttm3 = new TicTacToeModel();
        tttm3.play(0,0);
        tttm3.play(0,1);
        tttm3.play(0,2);
        tttm3.play(1,1);
        tttm3.play(1,0);
        tttm3.play(1,2);
        tttm3.play(2,1);
        tttm3.play(2,0);
        tttm3.play(2,2);

        assertEquals(TicTacToeModel.Status.TIE, tttm3.getStatus()); // when the game just started, status should be undecided
    }
}