import static java.lang.Math.abs;

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
        //Check for tie breaker as it has different rules for scoring
        if (checkTieBreakerCondition()) {
            calculateTieBreaker(pName);
        } else {
            calculateGameScore(pName);
        }

    }

    /**
     * score method formats and returns the display string for Set and current game
     * @return Formatted display score string
     */
    public String score()
    {
        return null;
    }


    /**
     * checkTieBreaker  method perform precondition check for tieBreaker
     * @return true if tiebreaker condition of  minimum number games has been won by each player
     * */
    private boolean checkTieBreakerCondition()
    {
        return ( player1.getGamesWon() == minimumRequiredGames && player2.getGamesWon()==minimumRequiredGames );
    }

    /**
     * calculateTieBreaker - calculates score when set is in tie breaker
     * @param pName Name of the player
     */
    private void calculateTieBreaker(String pName)
    {
        //its a tie breaker

        //Choose player and increment, assume valid data
        if (player1.getName().equals(pName))
            player1.incrementTieBreakerPoint();
        else
            player2.incrementTieBreakerPoint();
        //Check for who
        if (abs(player1.getTieBreakerPoint() - player2.getTieBreakerPoint()) == leadingTieBreakerPoints) {
            //game finished
            if ((player1.getTieBreakerPoint() - player2.getTieBreakerPoint()) == leadingTieBreakerPoints) {
                player1.incrementGamesWon();
                player1.incrementSetsWon();
                player1.resetGame();
            } else {
                player2.incrementGamesWon();
                player2.incrementSetsWon();
                player2.resetGame();
            }
        }


    }

    /**
     * calculateGameScore method deduces the score for game and set and  updates players point, game and set data
     * @param pName Name of the player
     */
    private void calculateGameScore( String pName)
    {
        //Choose player and increment, assume valid player name per instructions
        if(player1.getName().equals(pName))
            player1.setGamePoint(player1.getNextPoint(player2));
        else
            player2.setGamePoint(player2.getNextPoint(player1));

        //Check for games won and set the data for sets
        if( abs(player1.getGamesWon() -player2.getGamesWon()) >=leadingGames )
        {
            if (player1.getGamesWon() >= minimumRequiredGames)
            {
                player1.incrementSetsWon();
            }
            else if(player2.getGamesWon() >= minimumRequiredGames)
                player2.incrementSetsWon();
        }
    }


}
