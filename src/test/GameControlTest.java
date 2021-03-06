/**
 * 
 */

package test;

import game.GameControl;
import game.Piece;
import game.Player;
import game.RandomPlayerStrategy;
import game.StupidPlayerStrategy;
import game.Piece.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author TCPsi
 */
public class GameControlTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link game.GameControl#start()}.
     */
    @Test
    public void testStart() {
        String player0name = "Pablo0";
        Color color0 = Color.YELLOW;

        String player1name = "Juan1";
        Color color1 = Color.BLUE;

        String player2name = "Echegu2";
        Color color2 = Color.GREEN;

        String player3name = "Kike3";
        Color color3 = Color.RED;

        Player[] players = new Player[4];
        players[0] = new Player(player0name, color0, 4, new StupidPlayerStrategy());
        players[1] = new Player(player1name, color1, 4, new RandomPlayerStrategy());
        players[2] = new Player(player2name, color2, 4, new StupidPlayerStrategy());
        players[3] = new Player(player3name, color3, 4, new RandomPlayerStrategy());

        GameControl gameControl = new GameControl();
        gameControl.addPlayer(players[0]);
        gameControl.addPlayer(players[1]);
        gameControl.addPlayer(players[2]);
        gameControl.addPlayer(players[3]);

        gameControl.start();
    }
}
