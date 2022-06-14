import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    /**
     * Test a valid string input which represents full strike frames
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_300() throws InvalidInputException {
        assertEquals(300, Game.CalculateScore("x x x x x x x x x x x x"));
    }

    /**
     * Test only without bonuses
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_51() throws InvalidInputException {
        assertEquals(51, Game.CalculateScore("11 12 13 14 15 16 17 18 21 22"));
    }

    /**
     * Test with a miss at first char without bonuses
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_50() throws InvalidInputException {
        assertEquals(50, Game.CalculateScore("11 -2 13 14 15 16 17 18 21 22"));
    }

    /**
     * Test with a miss at second char without bonuses
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_49() throws InvalidInputException {
        assertEquals(49, Game.CalculateScore("11 1- 13 14 15 16 17 18 21 22"));
    }

    /**
     * Test with one strike bonus
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_62() throws InvalidInputException {
        assertEquals(62, Game.CalculateScore("11 x 13 14 15 16 17 18 21 22"));
    }

    /**
     * Test with two strike bonus
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_80() throws InvalidInputException {
        assertEquals(80, Game.CalculateScore("11 x x 14 15 16 17 18 21 22"));
    }

    /**
     * Test with three strike bonus
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_106() throws InvalidInputException {
        assertEquals(106, Game.CalculateScore("11 x x x 15 16 17 18 21 22"));
    }

    /**
     * Test with three strike bonus with a third miss
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_103() throws InvalidInputException {
        assertEquals(103, Game.CalculateScore("11 x x x -5 16 17 18 21 22"));
    }

    /**
     * Test with spare than a number
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_59() throws InvalidInputException {
        assertEquals(59, Game.CalculateScore("11 1/ 13 14 15 16 17 18 21 22"));
    }

    /**
     * Test with spare than a miss
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_57() throws InvalidInputException {
        assertEquals(57, Game.CalculateScore("11 1/ -3 14 15 16 17 18 21 22"));
    }

    /**
     * Test with spare than a strike
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_79() throws InvalidInputException {
        assertEquals(79, Game.CalculateScore("11 1/ x 14 15 16 17 18 21 22"));
    }

    /**
     * Test a valid string input which contains number, spare and strike. After 10 frames there are 2 more throw.
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_176() throws InvalidInputException {
        assertEquals(176, Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x x 5"));
    }

    /**
     * Test a valid input 10. throw is a spare and the next throw is a 5
     */
    @Test
    public void it_should_be_161() throws InvalidInputException {
        assertEquals(161, Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x 5/ 5"));
    }

    /**
     * Test a valid string input which contains full missed frames
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_0() throws InvalidInputException {
        assertEquals(0, Game.CalculateScore("- - - - - - - - - -"));
    }

    /**
     * Test a valid string input which contains mixed misses
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_18() throws InvalidInputException {
        assertEquals(18, Game.CalculateScore("-3 3- x 1- - - - - - -"));
    }

    /**
     * Test a valid string input which contains a spare in the 10. frame.
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_171() throws InvalidInputException {
        assertEquals(171, Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x 2/ x"));
    }

    /**
     * Test a valid string input which not contains a spare or strike in the 10. frame.
     *
     * @throws InvalidInputException
     */
    @Test
    public void it_should_be_145() throws InvalidInputException {
        assertEquals(145, Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x 25"));
    }

    /**
     * Test an invalid input which is null
     *
     * @throws InvalidInputException
     */
    @Test(expected = NullPointerException.class)
    public void it_should_throw_InvalidInputException_when_input_is_null() throws InvalidInputException {
        Game.CalculateScore(null);
    }

    /**
     * Test an invalid input where an empty string given
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_is_an_empty_string() throws InvalidInputException {
        Game.CalculateScore("");
    }

    /**
     * Test an invalid input which contains less than 10 valid frames
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_too_short() throws InvalidInputException {
        Game.CalculateScore("x x 56 32 3/ -");
    }

    /**
     * Test an invalid input which contains more than 12 valid frames
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_too_long() throws InvalidInputException {
        Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x x 5 41");
    }

    /**
     * Test an invalid input which contains an invalid char
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_has_illegal_char() throws InvalidInputException {
        Game.CalculateScore("x x 56 g2 3/ - x x x x 12");
    }

    /**
     * Test an invalid input which contains an invalid number
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_has_illegal_number() throws InvalidInputException {
        Game.CalculateScore("x x 56 02 3/ - x x x x 12");
    }

    /**
     * Test an invalid input which contains more than 2 throw in a frame
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_more_than_two_throw_in_a_frame() throws InvalidInputException {
        Game.CalculateScore("5372_x x 7/ 9/ 24 6/ x x x x 12");
    }

    /**
     * Test an invalid input where one of its frame has more than 2 'x'
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_more_than_two_strike_code_in_one_frame() throws InvalidInputException {
        Game.CalculateScore("53 x 7/ 9/ 24 6/ xx x x x 12");
    }

    /**
     * Test an invalid input where one of its frame has more than 2 '-'
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_more_than_two_miss_code_in_one_frame() throws InvalidInputException {
        Game.CalculateScore("53 x 7/ 9/ 24 6/ -- x x x 12");
    }

    /**
     * Test an invalid input where one of its frame starting with a '/' char
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_a_frame_starting_with_a_spare_code() throws InvalidInputException {
        Game.CalculateScore("13 x 7/ 9/ /4 6/ x x x x 12");
    }

    /**
     * Test an invalid input where one of its frame has more than 2 '/'
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_more_than_two_spare_code_in_one_frame() throws InvalidInputException {
        Game.CalculateScore("53 x 7/ 9/ 24 6/ // x x x");
    }

    /**
     * Test an invalid input where there are no bonus throws after a strike
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_there_is_no_bonus_frames_after_a_strike() throws InvalidInputException {
        Game.CalculateScore("53 x 7/ 9/ 24 6/ 5/ x x x");
    }

    /**
     * Test an invalid input where there are no second bonus throw after a strike
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_there_is_no_second_bonus_throw_after_a_strike() throws InvalidInputException {
        Game.CalculateScore("53 x 7/ 9/ 24 6/ 5/ x x x x");
    }

    /**
     * Test an invalid input where there is no bonus throw after a spare
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_there_is_no_bonus_throw_after_a_spare() throws InvalidInputException {
        Game.CalculateScore("53 x 7/ 9/ 24 6/ 5/ x x 2/");
    }

    /**
     * Test an invalid input where there is a second bonus frame after a spare
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_there_is_a_second_bonus_frame_after_a_spare() throws InvalidInputException {
        Game.CalculateScore("53 x 7/ 9/ 24 6/ 5/ x x 2/ x x");
    }

    /**
     * Test an invalid input where there is a second bonus throw after a spare
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_there_is_a_second_bonus_throw_after_a_spare() throws InvalidInputException {
        Game.CalculateScore("53 x 7/ 9/ 24 6/ 5/ x x 2/ 12");
    }

    /**
     * Test an invalid input where a strike followed by a spare in one frame
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidException_where_strike_code_followed_by_spare() throws InvalidInputException {
        Game.CalculateScore("x x/ x x x x x x x x x x");
    }

    /**
     * Test an invalid input where a strike followed by a number in one frame
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidException_where_strike_code_followed_by_number() throws InvalidInputException {
        Game.CalculateScore("x x2 x x x x x x x x x x");
    }

    /**
     * Test an invalid input where a strike followed by a miss in one frame
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidException_where_strike_code_followed_by_miss() throws InvalidInputException {
        Game.CalculateScore("x x- x x x x x x x x x x");
    }

    /**
     * Test an invalid input where a miss followed by a strike in one frame
     *
     * @throws InvalidInputException
     */
    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidException_where_miss_code_followed_by_strike() throws InvalidInputException {
        Game.CalculateScore("x -x x x x x x x x x x x");
    }
}
