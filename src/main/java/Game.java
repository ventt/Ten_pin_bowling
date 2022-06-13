import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    public static int CalculateScore(String input) throws InvalidInputException {
        if (input == null)
            throw new InvalidInputException("Input is null");
        if (input.equals(""))
            throw new InvalidInputException("Input is an empty String");

        List<String> frames = Arrays.asList(input.split(" "));
        if (frames.size() > 12)
            throw new InvalidInputException("There are more than 12 frames");
        if (frames.size() < 10)
            throw new InvalidInputException("There are less than 10 frames!");

        Pattern pattern = Pattern.compile("[^1-9x/\\-]");
        Matcher m;
        for (String frame : frames) {
            if(frame.length() > 2 )
                throw new InvalidInputException("Invalid input format, more than 2 throw in a frame");
            if(frame.charAt(0) == '/')
                throw new InvalidInputException("Invalid input format, a frame starting with a '/' char");
            if(frame.equals("xx") || frame.equals("--") || frame.equals("//"))
                throw new InvalidInputException("Invalid input format, one frame contains more than 1 x,-,/");
            m = pattern.matcher(frame);
            if(m.find())
                throw new InvalidInputException("There is an illegal character in the input!");
        }

        int score = 0;
        for (int i = 0; i < 8; i++) {
            if(frames.get(i).equals("x")){
                score+= 10;
            }else if(frames.get(i).equals("-")){

            }
        }


        return score;
    }
}
