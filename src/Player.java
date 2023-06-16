/**
 * Class Player represents a player and keeps track of player's game wins and sets win
 *
 */
public class Player {

    //Player name can't be changed
    private final String name;
    private int setsWon = 0; //no of sets won
    private int tieBreakerPoint = 0; //Keeps track of tie breaker  points
    private int gamesWon = 0;// tracking games win for a player
    private Point gamePoint = Point.ZERO;

    //Ctor
    public Player(String name)
    {
        this.name = name;
    }

    //Getter, setter and incrementers
    public String getName() {
        return name;
    }

    //Register games win
    public void incrementGamesWon()
    {
        gamesWon++;
    }

    //Register set win
    public void incrementSetsWon()
    {
        setsWon++;
    }

    //register tiebreaker point won
    public void incrementTieBreakerPoint()
    {
        this.tieBreakerPoint++;
    }

    /**
     * Sets scoring Point, when needed to adjust based on other player score such as advantage or deuce.
     * @param gamePoint Point to be set as players game point
     */
    public void setGamePoint(Point gamePoint) {
        this.gamePoint = gamePoint;
    }

    public Point getGamePoint() {
        return gamePoint;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getSetsWon() {
        return setsWon;
    }


    public int getTieBreakerPoint() {
        return tieBreakerPoint;
    }

    /**
     * resetGame  method resets all scoring point vriables when its concluded.
     */
    public void resetGame() {
        this.tieBreakerPoint =0;
        this.gamePoint = Point.ZERO;
    }

    /**
     * getNextPoint method calculates and return next scored point for the player based on other players point scores in a game
     * @param otherPlayer Other player in the Match
     * @return Point scored by the player
     */
    public Point  getNextPoint( Player otherPlayer)
    {
        Point point = Point.ZERO;
        switch (this.gamePoint)
        {
            case ZERO:
                point = Point.FIFTEEN;
                break;
            case FIFTEEN:
                point = Point.THIRTY;
                break;
            case THIRTY:
                point = Point.FORTY;
                break;
            case FORTY:
            {
                point =   Point.GAME;
                if (otherPlayer.getGamePoint() == Point.FORTY) {
                    point =   Point.ADVANTAGE;
                } else if (otherPlayer.getGamePoint() == Point.ADVANTAGE) {
                    otherPlayer.setGamePoint(Point.FORTY);
                    point =   Point.FORTY;
                }

            }
            break;

            case ADVANTAGE:
                point =  Point.GAME;
                break;
            default:
                point = Point.ZERO;
                break;
        }

        //Win prompts a reset
        if(point == Point.GAME)
        {
            point = Point.ZERO;
            incrementGamesWon();
            this.resetGame();
            otherPlayer.resetGame();
        }
        return point;
    }


}
