import java.util.List;
import java.util.Optional;
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

                    int result = 0;

                    final char[] chars = str.toCharArray();

                    int firstChar = chars[0] == MISS_CHAR ? 0 : Integer.parseInt(String.valueOf(chars[0]));

                    // Check if the frame has 2 char. It is important at the last bonus throw
                    // because there can be a frame with one char
                    if (chars.length == 2) {
                        if (SPARE_CHAR == chars[1]) {
                            return 10;
                        }
                        result += firstChar;
                        result += chars[1] == MISS_CHAR ? 0 : Integer.parseInt(String.valueOf(chars[1]));
                    } else {
                        result += firstChar;
                    }

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
            // Check if not strike but the value of the frame is 10 than it is a spare
            if (!STRIKE.equals(currentFrame) && frameValues.get(i) == 10) {
                // If the next throw is a strike add 10 otherwise add the next frame's first value
                if (STRIKE.equals(nextFrame)) {
                    result += 10;
                } else {
                    final char[] chars = nextFrame.toCharArray();
                    result += chars[0] == MISS_CHAR ? 0 : Integer.parseInt(String.valueOf(chars[0]));
                }
            }
        }


        // Check the 9. 10. frames and add the bonus throws if needed
        for (int i = 8; i < 10; i++) {
            final String currentFrame = frames.get(i);
            final Optional<String> nextFrame = Optional.ofNullable(i + 1 < frames.size() ? frames.get(i + 1) : null);
            final Optional<String> secondNextFrame = Optional.ofNullable(i + 2 < frames.size() ? frames.get(i + 2) : null);

            // Validation of bonus throws
            if (i == 9) {
                // Check if there is a bonus frame after a strike or there is a second bonus frame if the first bonus throw was a strike
                if ((STRIKE.equals(frames.get(9)) && nextFrame.isEmpty()
                        || STRIKE.equals(frames.get(9)) && nextFrame.isPresent() && STRIKE.equals(nextFrame.get()) && secondNextFrame.isEmpty())) {
                    throw new InvalidInputException("Invalid input string");
                }

                // Check if there is a bonus frame after a spare frame
                if (!STRIKE.equals(frames.get(9)) && frameValues.get(9) == 10 && nextFrame.isEmpty()) {
                    throw new InvalidInputException("Invalid input string");
                }

                // Check if there is a 12. bonus frame after a spare frame
                if (!STRIKE.equals(frames.get(9)) && frameValues.get(9) == 10 && secondNextFrame.isPresent()) {
                    throw new InvalidInputException("Invalid input string");
                }
                // Check if there is a second throw bonus after a spare
                if (!STRIKE.equals(frames.get(9)) && frameValues.get(9) == 10
                        && nextFrame.isPresent() && nextFrame.get().length() == 2) {
                    throw new InvalidInputException("Invalid input string");
                }
            }

            // If there is a strike and the next frame is not null add the next frame value
            if (STRIKE.equals(currentFrame) && nextFrame.isPresent()) {
                result += frameValues.get(i + 1);
                // If there is a spare and the next frame is not null add the next frame otherwise just add the frame value
            } else if (!STRIKE.equals(currentFrame) && frameValues.get(i) == 10 && nextFrame.isPresent()) {
                if (STRIKE.equals(nextFrame.get())) {
                    result += 10;
                } else {
                    final char[] chars = nextFrame.get().toCharArray();
                    result += chars[0] == MISS_CHAR ? 0 : Integer.parseInt(String.valueOf(chars[0]));
                }
            }
        }

        return result;
    }
}
