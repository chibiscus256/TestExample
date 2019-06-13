import java.security.SecureRandom;
import java.util.Random;

public class StringOperation{
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Generates random string of given length from Base65 alphabet (numbers, lowercase letters, uppercase letters).
     *
     * @param count length
     * @return random string of given length
     */
    protected static String generateRandomString(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    protected static String getRandomNumber(int startNum, int endNum) {
        Random random = new Random();
        int outNum = startNum + random.nextInt(endNum - startNum + 1);
        return Integer.toString(outNum);

    }
}