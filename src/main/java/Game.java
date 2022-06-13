import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Game {
    public static int CalculateScore(String input) throws InvalidInputException {
        /*
         * This regex checks if the input has between 10 and 12 frames and if there are any invalid "xx", "--" or
         * anything starting with "/" also checks if there are numbers between 1-9,"x","-" or the second character
         * is a "/"
         */
        if (!Pattern.compile("^(?:(?!xx)(?!--)(?:[1-9x\\-][1-9/]?)\\s?){10,12}$").matcher(input).matches()) {
            throw new InvalidInputException("Invalid input string");
        }

        final List<String> frames = List.of(input.split(" "));




        return 0;
    }
}
