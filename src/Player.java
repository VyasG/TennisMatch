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

}
