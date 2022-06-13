import java.util.List;
import java.util.regex.Pattern;

public class Game {
    private static final String STRIKE = "x";
    private static final char SPARE_CHAR = '/';
    private static final String MISS = "-";
    private static final char MISS_CHAR = '-';

    public static int CalculateScore(String input) throws InvalidInputException {
        /*
         * This regex checks if the input has between 10 and 12 frames and if there are any invalid "xx", "--" or
         * anything starting with "/" also checks if there are numbers between 1-9,"x","-" or the second character
         * is a "/"
         */
        if (!Pattern.compile("^(?:(?!x\\S)(?!\\Sx)(?!--)(?:[1-9x\\-][1-9/\\-]?)\\s?){10,12}$").matcher(input).matches()) {
            throw new InvalidInputException("Invalid input string");
        }

        final List<String> frames = List.of(input.split(" "));

        // Calculate individual frame values
        final List<Integer> frameValues = frames.stream()
                .sequential()
                .map(str -> {
                    if (STRIKE.equals(str)) {
                        return 10;
                    }
                    if (MISS.equals(str)) {
                        return 0;
                    }

                    // String must have 2 chars, there is no point of testing it
                    final char[] chars = str.toCharArray();

                    if (SPARE_CHAR == chars[1]) {
                        return 10;
                    }

                    int result = 0;
                    result += chars[0] == MISS_CHAR ? 0 : Integer.parseInt(String.valueOf(chars[0]));
                    result += chars[1] == MISS_CHAR ? 0 : Integer.parseInt(String.valueOf(chars[1]));
                    return result;
                }).toList();

        // Sum all the frame values without bonuses
        int result = frameValues.stream().mapToInt(Integer::valueOf).sum();

        // Iterate through the frames to sum the values and bonuses
        for (int i = 0; i < 8; i++) {
            final String currentFrame = frames.get(i);
            final String nextFrame = frames.get(i + 1);
            final String secondNextFrame = frames.get(i + 2);

            // If there is a strike add next two throws value
            if (STRIKE.equals(currentFrame)) {
                // If next throw is a strike add 10 bonus
                if (STRIKE.equals(nextFrame)) {
                    result += 10;
                    // If second next throw is a strike add 10 otherwise add the second next frame's first value
                    if (STRIKE.equals(secondNextFrame)) {
                        result += 10;
                    } else {
                        final char[] chars = secondNextFrame.toCharArray();
                        // Check if the first value is a miss
                        result += chars[0] == MISS_CHAR ? 0 : Integer.parseInt(String.valueOf(chars[0]));
                    }
                }
                // If next throw is not a strike just add the value
                else {
                    result += frameValues.get(i + 1);
                }
            }
        }
//        final Optional<String> nextFrame = Optional.ofNullable(i + 1 < frames.size() ? frames.get(i + 1) : null);
//        final Optional<String> secondNextFrame = Optional.ofNullable(i + 2 < frames.size() ? frames.get(i + 2) : null);

        return result;
    }
}
