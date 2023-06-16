import static java.lang.Math.abs;

/**
 * Match class implements external methods to access score
 */

public final class Match {

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
            calculateTieBreakerScore(pName);
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
        String displayScore = String.format("\"%d-%d, %s\"",player1.getSetsWon(),player2.getSetsWon(),getGameScore() );

        //Format score when atleast one set is won and Game is paused or no more points are scored yet.
        if(player1.getSetsWon()!=0 ||player2.getSetsWon() !=0 && (player1.getGamePoint()==Point.ZERO && player2.getGamePoint() ==Point.ZERO))
            displayScore = String.format("\"%d-%d\"",player1.getSetsWon(),player2.getSetsWon());

        return displayScore;
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
    private void calculateTieBreakerScore(String pName)
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

    /**
     * getGameScore is private method to format the game output.
     * @return String formatted for display
     */
    private String getGameScore()
    {
        Point player1Point = player1.getGamePoint();
        Point player2Point = player2.getGamePoint();
        String displayGameScore = String.format("%s-%s",player1Point.getValue(), player2Point.getValue());
        if(checkTieBreakerCondition()) {
            displayGameScore = String.format("%d-%d", player1.getTieBreakerPoint(), player2.getTieBreakerPoint());
        }
        //change display based on specials cons
        if(player1Point==Point.ADVANTAGE)
            displayGameScore= String.format("%s %s", Point.ADVANTAGE.getValue(), player1.getName());
        if(player2Point == Point.ADVANTAGE)
            displayGameScore= String.format("%s %s", Point.ADVANTAGE.getValue(), player2.getName());
        if(player1Point==player2Point) {
            if (player1Point == Point.FORTY)
                displayGameScore = String.format("%s", "Deuce");
        }
        return displayGameScore;
    }


}
