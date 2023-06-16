/**
 * Point used in a normal tennis game scoring, e.g. 0,15,30,40, Advantage and Win.
 * Tiebreaker scoring is simple increment
 */
public enum Point {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("Advantage"),
    GAME ("Win");
    String displayValue;
    Point(String displayValue)
    {
        this.displayValue = displayValue;
    }
    //return display value for score display
    public String getValue()
    {
        return displayValue;
    }
}
