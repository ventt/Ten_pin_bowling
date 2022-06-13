import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GameTest {

    @Test
    public void it_should_be_300() throws InvalidInputException {
        assertEquals(300, Game.CalculateScore("x x x x x x x x x x x x"));
    }

    @Test
    public void it_should_be_176() throws InvalidInputException {
        assertEquals(176, Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x x 5"));
    }

    @Test
    public void it_should_be_0() throws InvalidInputException {
        assertEquals(0, Game.CalculateScore("- - - - - - - - - -"));
    }

    @Test(expected = InvalidInputException.class)
    public void it_should_throw_IllegalArgumentException_when_input_too_short(){
        try{
            Game.CalculateScore("x x 56 32 3/ -");
        }catch (InvalidInputException ex){
            assertEquals("There are less than 10 frames!", ex.getMessage());
        }
        fail("Less than 10 frames exception did not throw!");
    }

    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_has_illegal_char(){
        try{
            Game.CalculateScore("x x 56 g2 3/ -");
        }catch (InvalidInputException ex){
            assertEquals("There is an illegal character in the input!", ex.getMessage());
        }
        fail("Illegal character exception did not throw!");
    }

    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_too_long(){
        try{
            Game.CalculateScore("53 72 x x 7/ 9/ 24 6/ x x x 5 41");
        }catch (InvalidInputException ex){
            assertEquals("There are more than 12 frames", ex.getMessage());
        }
        fail("More than 12 frames exception did not throw!");
    }

    @Test(expected = InvalidInputException.class)
    public void it_should_throw_InvalidInputException_when_input_format_invalid(){
        try{
            Game.CalculateScore("5372_x x 7/ 9/ 24 6/ x x x");
        }catch (InvalidInputException ex){
            assertEquals("There are more than 12 frames", ex.getMessage());
        }
        fail("Input format invalid exception did not throw!");
    }




}
