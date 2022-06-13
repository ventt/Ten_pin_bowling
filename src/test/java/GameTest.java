import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GameTest {

    //Test a valid string input which represents full strike frames. It should give back 300 points as a return.
    @Test
    public void it_should_be_300() throws InvalidInputException {
        assertEquals(300, Game.CalculateScore("x x x x x x x x x x x x"));
        fail("Full strike test failed!");
    }

    //Test a valid string input which contains number, spare and strike. After 10 frames there were given 2 more throw.
    @Test
    public void it_should_be_176() throws InvalidInputException {
        assertEquals(176, Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x x 5"));
        fail("2 Bonus throw test failed!");
    }

    //Test a valid string input which contains full missed frames which coded as a single '-' char.
    @Test
    public void it_should_be_0() throws InvalidInputException {
        assertEquals(0, Game.CalculateScore("- - - - - - - - - -"));
        fail("Full miss test failed!");
    }

    //Test a valid string input which contains mixed misses. Frames where are points and misses mixed and frames where the two throw was miss.
    @Test
    public void it_should_be_18() throws InvalidInputException {
        assertEquals(18, Game.CalculateScore("-3 3- x 1- - - - - - -"));
        fail("Full missed frames and mixed miss and points frames failed!");
    }
    //Test a valid string input which contains a spare in the 10. frame.
    @Test
    public void it_should_be_163() throws InvalidInputException {
        assertEquals(163, Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x 2/ x"));
        fail("Spare in the 10. frames and one bonus test failed!");
    }
    //Test a valid string input which not contains a spare or strike in the 10. frame.
    @Test
    public void it_should_be_153() throws InvalidInputException {
        assertEquals(153, Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x 25"));
        fail("Not spare nor strike in the 10. frames failed!");
    }

    //Test an invalid input which contains less than 10 valid frames
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_IllegalArgumentException_when_input_too_short() {
        try {
            Game.CalculateScore("x x 56 32 3/ -");
        } catch (InvalidInputException ex) {
            assertEquals("There are less than 10 frames!", ex.getMessage());
        }
        fail("Less than 10 frames exception did not throw!");
    }

    //Test an invalid input which contains more than 12 valid frames
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_too_long() {
        try {
            Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x x 5 41");
        } catch (InvalidInputException ex) {
            assertEquals("There are more than 12 frames", ex.getMessage());
        }
        fail("More than 12 frames exception did not throw!");
    }

    //Test an invalid input which contains an invalid char
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_has_illegal_char() {
        try {
            Game.CalculateScore("x x 56 g2 3/ -");
        } catch (InvalidInputException ex) {
            assertEquals("There is an illegal character in the input!", ex.getMessage());
        }
        fail("Illegal character exception did not throw!");
    }

    //Test an invalid input which contains invalid delimiters
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_format_invalid() {
        try {
            Game.CalculateScore("5372_x x 7/ 9/ 24 6/ x x x");
        } catch (InvalidInputException ex) {
            assertEquals("Invalid input format", ex.getMessage());
        }
        fail("Input format invalid exception did not throw!");
    }

    //Test an invalid input which is null
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_is_null() {
        try {
            Game.CalculateScore(null);
        } catch (InvalidInputException ex) {
            assertEquals("Input is null", ex.getMessage());
        }
        fail("Input format invalid exception did not throw!");
    }

    //Test an invalid input where an empty string given
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_is_an_empty_string() {
        try {
            Game.CalculateScore("");
        } catch (InvalidInputException ex) {
            assertEquals("Input is an empty String", ex.getMessage());
        }
        fail("Input format invalid exception did not throw!");
    }

    //Test an invalid input where the 11. bonus throw is not a strike and one more frame were given
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_where_three_more_bonus_throw_were_given() {
        try {
            Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x 53 5");
        } catch (InvalidInputException ex) {
            assertEquals("Input is an empty String", ex.getMessage());
        }
        fail("Input format invalid exception did not throw!");
    }

    //Test an invalid input where the 11. bonus throw is a strike and one more whole frame were given
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_after_bonus_strike_and_whole_frame_follows() {
        try {
            Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x x 53");
        } catch (InvalidInputException ex) {
            assertEquals("Input is an empty String", ex.getMessage());
        }
        fail("Input format invalid exception did not throw!");
    }

}
