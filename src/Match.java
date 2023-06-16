/**
 * Match class implements external methods to access score
 */

public class Match {

    private Player player1;
    private Player player2;
    private final int minimumRequiredGames = 6;
    private final int leadingGames = 2;
    private final int leadingTieBreakerPoints = 2;

    /**
     * Constructor
     * @param player1 player 1 name
     * @param player2 player 2 name
     */
    public Match(String player1 , String player2)
    {
        this.player1 = new Player( player1);
        this.player2  = new Player(player2);
    }

    /**
     * pointWonBy method registers each point won by player
     * @param pName name of the player who scored the point
     */
    public void pointWonBy(String pName)
    {

    }

    /**
     * score method formats and returns the display string for Set and current game
     * @return Formatted display score string
     */
    public String score()
    {
        return null;
    }


}
