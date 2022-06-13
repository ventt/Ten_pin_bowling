import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class GameTest {
    //Test a valid string input which represents full strike frames. It should give back 300 points as a return.
    @Test
    public void it_should_be_300() throws InvalidInputException {
        assertEquals(300, Game.CalculateScore("x x x x x x x x x x x x"));
    }

    /**
     * Test only without bonuses
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_51() throws InvalidInputException {
        assertEquals(51, Game.CalculateScore("11 12 13 14 15 16 17 18 21 22"));
    }
    /**
     * Test with a miss at first char without bonuses
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_50() throws InvalidInputException {
        assertEquals(50, Game.CalculateScore("11 -2 13 14 15 16 17 18 21 22"));
    }
    /**
     * Test with a miss at second char without bonuses
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_49() throws InvalidInputException {
        assertEquals(49, Game.CalculateScore("11 1- 13 14 15 16 17 18 21 22"));
    }
    /**
     * Test with one strike bonus
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_62() throws InvalidInputException {
        assertEquals(62, Game.CalculateScore("11 x 13 14 15 16 17 18 21 22"));
    }
    /**
     * Test with two strike bonus
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_80() throws InvalidInputException {
        assertEquals(80, Game.CalculateScore("11 x x 14 15 16 17 18 21 22"));
    }
    /**
     * Test with three strike bonus
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_106() throws InvalidInputException {
        assertEquals(106, Game.CalculateScore("11 x x x 15 16 17 18 21 22"));
    }
    /**
     * Test with three strike bonus with a third miss
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_103() throws InvalidInputException {
        assertEquals(103, Game.CalculateScore("11 x x x -5 16 17 18 21 22"));
    }

    //Test a valid string input which contains number, spare and strike. After 10 frames there were given 2 more throw.
    @Test
    public void it_should_be_176() throws InvalidInputException {
        assertEquals(176, Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x x 5"));
    }

    //Test a valid string input which contains full missed frames which coded as a single '-' char.
    @Test
    public void it_should_be_0() throws InvalidInputException {
        assertEquals(0, Game.CalculateScore("- - - - - - - - - -"));
    }

    //Test a valid string input which contains mixed misses. Frames where are points and misses mixed and frames where the two throw was miss.
    @Test
    public void it_should_be_18() throws InvalidInputException {
        assertEquals(18, Game.CalculateScore("-3 3- x 1- - - - - - -"));
    }

    //Test a valid string input which contains a spare in the 10. frame.
    @Test
    public void it_should_be_163() throws InvalidInputException {
        assertEquals(163, Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x 2/ x"));
    }

    //Test a valid string input which not contains a spare or strike in the 10. frame.
    @Test
    public void it_should_be_153() throws InvalidInputException {
        assertEquals(153, Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x 25"));
    }

    //Test an invalid input which is null
    @Test(expected = NullPointerException.class)
    public void it_should_throw_InvalidInputException_when_input_is_null() throws InvalidInputException {
        Game.CalculateScore(null);
    }

    //Test an invalid input where an empty string given
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_is_an_empty_string() throws InvalidInputException {
        Game.CalculateScore("");
    }

    //Test an invalid input which contains less than 10 valid frames
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_too_short() throws InvalidInputException {
        Game.CalculateScore("x x 56 32 3/ -");
    }

    //Test an invalid input which contains more than 12 valid frames
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_too_long() throws InvalidInputException {
        Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x x 5 41");
    }

    //Test an invalid input which contains an invalid char
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_has_illegal_char() throws InvalidInputException {
        Game.CalculateScore("x x 56 g2 3/ - x x x x 12");
    }
    //Test an invalid input which contains an invalid number
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_has_illegal_number() throws InvalidInputException {
        Game.CalculateScore("x x 56 02 3/ - x x x x 12");
    }

    //Test an invalid input which contains more than 2 throw in a frame
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_more_than_two_throw_in_a_frame() throws InvalidInputException {
        Game.CalculateScore("5372_x x 7/ 9/ 24 6/ x x x x 12");
    }
    //Test an invalid input where one of its frame has more than 2 'x'
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_more_than_two_strike_code_in_one_frame() throws InvalidInputException {
        Game.CalculateScore("53 x 7/ 9/ 24 6/ xx x x x 12");
    }
    //Test an invalid input where one of its frame has more than 2 '-'
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_more_than_two_miss_code_in_one_frame() throws InvalidInputException {
        Game.CalculateScore("53 x 7/ 9/ 24 6/ -- x x x 12");
    }
    //Test an invalid input where one of its frame starting with a '/' char
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_a_frame_starting_with_a_spare_code() throws InvalidInputException {
        Game.CalculateScore("13 x 7/ 9/ /4 6/ x x x x 12");
    }
    //Test an invalid input where one of its frame has more than 2 '/'
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_more_than_two_spare_code_in_one_frame() throws InvalidInputException {
        Game.CalculateScore("53 x 7/ 9/ 24 6/ // x x x");
    }


    //Test an invalid input where the 11. bonus throw is not a strike and one more frame were given
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_where_three_more_bonus_throw_were_given() throws InvalidInputException {
        Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x 53 5");
    }

    //Test an invalid input where the 11. bonus throw is a strike and one more whole frame were given
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_after_bonus_strike_and_whole_frame_follows() throws InvalidInputException {
        Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x x 53");
    }

    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidException_where_strike_code_followed_by_spare() throws InvalidInputException {
        Game.CalculateScore("x x/ x x x x x x x x x x");
    }
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidException_where_strike_code_followed_by_number() throws InvalidInputException {
        Game.CalculateScore("x x2 x x x x x x x x x x");
    }

    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidException_where_strike_code_followed_by_miss() throws InvalidInputException {
        Game.CalculateScore("x x- x x x x x x x x x x");
    }

    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidException_where_miss_code_followed_by_strike() throws InvalidInputException {
        Game.CalculateScore("x -x x x x x x x x x x x");
    }
}
