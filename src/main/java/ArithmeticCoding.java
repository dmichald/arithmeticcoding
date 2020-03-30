import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArithmeticCoding {
    private Interval interval;
    private char[] messageToCode;
    private double[] probabilityPoints;
    private char[] alphabet;

    public ArithmeticCoding(char[] messageToCode, double[] probabilityPoints, char[] alphabet) {
        this.interval = new Interval(0, 1);
        this.messageToCode = messageToCode;
        this.probabilityPoints = probabilityPoints;
        this.alphabet = alphabet;
    }

    public ArithmeticCoding(String messageToCode) {
        interval = new Interval(0, 1);
        getAlphabetFromString(messageToCode);
    }

    public double encode() {
        List<Interval> subIntervals;
        for (char c : messageToCode) {
            subIntervals = interval.splitInterval(probabilityPoints);

            for (int j = 0; j < alphabet.length; j++) {
                if (c == alphabet[j]) {
                    interval = subIntervals.get(j);
                }
            }

        }
        return interval.getLower();
    }

    public String decode(double code) {
        Interval interval = new Interval(0, 1);
        List<Interval> subIntervals;
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < messageToCode.length; i++) {
            subIntervals = interval.splitInterval(probabilityPoints);
            for (int j = 0; j < subIntervals.size(); j++) {
                if (subIntervals.get(j).isInRange(code)) {
                    message.append(alphabet[j]);
                    interval = subIntervals.get(j);
                }
            }
        }
        return message.toString();
    }

    public void getAlphabetFromString(String toCode) {
        messageToCode = toCode.toCharArray();
        Stream<Character> charStream = new String(messageToCode).chars().mapToObj(i -> (char) i);
        ArrayList<Character> chars = new ArrayList<>();
        ArrayList<Double> probabilities = new ArrayList<>();


        charStream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((key, value) -> {
                    chars.add(key);
                    double d = (double) value / (double) messageToCode.length;
                    probabilities.add(d);
                });

        alphabet = new char[chars.size()];
        probabilityPoints = new double[probabilities.size()];
        for (int i = 0; i < chars.size(); i++) {
            alphabet[i] = chars.get(i);
            probabilityPoints[i] = probabilities.get(i);
        }
    }
}
