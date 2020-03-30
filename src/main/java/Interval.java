import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Interval {


    private double lower;
    private double upper;

    public Interval(double lower, double upper) {
        this.lower = lower;
        this.upper = upper;

    }

    public double getLower() {
        return lower;
    }

    public double getUpper() {
        return upper;
    }

    public boolean isInRange(double number) {
        return number >= this.lower && number < this.upper;
    }

    public List<Interval> splitInterval(double[] probabilityPoint) {
        ArrayList<Interval> intervals = new ArrayList<>();
        double upper = this.lower + ((this.upper - this.lower) * probabilityPoint[0]);
        Interval firstInterval = new Interval(this.lower, upper);
        intervals.add(firstInterval);
        for (int i = 1; i < probabilityPoint.length; i++) {
            double lower = intervals.get(i - 1).getUpper();
            double upperRange = lower + ((this.upper - this.lower) * probabilityPoint[i]);
            Interval intervalToAdd = new Interval(lower, upperRange);
            intervals.add(intervalToAdd);
        }
        return intervals;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "lower=" + lower +
                ", upper=" + upper +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Double.compare(interval.lower, lower) == 0 &&
                Double.compare(interval.upper, upper) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lower, upper);
    }
}
