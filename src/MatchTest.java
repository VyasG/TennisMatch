/**
 * Test class for tennis score and other boundary conditions
 */
public class MatchTest {

    public static void main(String[] args) {
        //Execute test assertions
        testDeuce();
        testAdvantage();
        testTieBreaker();
        testNewGame();
        testSetComplete();
    }

    //Test

    /**
     * Method to test the deuce condition
     */
    private static  void testDeuce()
    {
        Match match = new Match("Player1","Player2");
        match.pointWonBy("Player1");
        match.pointWonBy("Player1");
        match.pointWonBy("Player1");
        match.pointWonBy("Player2");
        match.pointWonBy("Player2");
        match.pointWonBy("Player2");
        System.out.println(match.score());
        assert match.score().equals("\"0-0, Deuce\"") : "method fail testDeuce";

    }

    /**
     * Method to test Advantage  score
     */
    private static void testAdvantage()
    {
        Match match = new Match("Player1","Player2");
        match.pointWonBy("Player1");
        match.pointWonBy("Player1");
        match.pointWonBy("Player1");
        match.pointWonBy("Player2");
        match.pointWonBy("Player2");
        match.pointWonBy("Player2");
        match.pointWonBy("Player2");
        System.out.println(match.score());
        assert match.score().equals("\"0-0, Advantage Player2\"") : "method fail testAdvantage";

    }

    /**
     * Method to test logic for tiebreaker  game
     */
    private static void testTieBreaker()
    {
        int minGamesSet = 5;
        Match match = new Match("Player1","Player2");
        //{ match.pointWonBy("Player1");})


        for (int i=0;i<minGamesSet;i++)
        {
            match.pointWonBy("Player1");
            match.pointWonBy("Player1");
            match.pointWonBy("Player1");
            match.pointWonBy("Player1");
        }
        for (int i=0;i<minGamesSet;i++) {
            match.pointWonBy("Player2");
            match.pointWonBy("Player2");
            match.pointWonBy("Player2");
            match.pointWonBy("Player2");
        }
        match.pointWonBy("Player1");
        match.pointWonBy("Player1");
        match.pointWonBy("Player1");
        match.pointWonBy("Player1");

        match.pointWonBy("Player2");
        match.pointWonBy("Player2");
        match.pointWonBy("Player2");
        match.pointWonBy("Player2");

        match.pointWonBy("Player2");
        match.pointWonBy("Player1");
        match.pointWonBy("Player2");

        System.out.println(match.score());
        assert match.score().equals("\"0-0, 1-2\"") : "method fail testTieBreaker";

    }

    /**
     * Method to test logic for game variable rest and game won (i.e. game decided and a player has won)
     */
    private static void testNewGame()
    {
        Match match = new Match("Player1","Player2");

        match.pointWonBy("Player1");
        match.pointWonBy("Player1");
        match.pointWonBy("Player2");
        match.pointWonBy("Player2");
        match.pointWonBy("Player1");
        match.pointWonBy("Player1");
        match.pointWonBy("Player1");
        System.out.println(match.score());
        assert match.score().equals("\"0-0, 15-0\"") : "method fail testNewGame";

    }

    /**
     * Method to test when a set is complete, this game only has one set
     */
    private static void testSetComplete()
    {
        Match match = new Match("Player1","Player2");
        for (int i=0;i<6;i++)
        {
            match.pointWonBy("Player2");
            match.pointWonBy("Player2");
            match.pointWonBy("Player2");
            match.pointWonBy("Player2");
        }
        System.out.println(match.score());
        assert match.score().equals("\"0-1\"") : "method fail testSetComplete";

    }


}
