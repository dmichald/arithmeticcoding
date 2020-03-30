import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IntervalTest {

    private Interval interval;


    @Before
    public void setUp() {
        interval = new Interval(0, 1);
    }

    @Test
    public void shouldReturnTrueIfNumberIsInRange() {
        assertTrue(interval.isInRange(0.2));
    }

    @Test
    public void shouldReturnFalseIfNumberIsOutRange() {
        assertFalse(interval.isInRange(45));
    }

    @Test
    public void shouldCorrectSplitInterval() {
        double[] probabilities = {0.5, 0.5};
        Interval firstSubInterval = new Interval(0, 0.5);
        Interval secondSubInterval = new Interval(0.5, 1);
        List<Interval> expectedIntervals = new ArrayList<>();
        expectedIntervals.add(firstSubInterval);
        expectedIntervals.add(secondSubInterval);

        List<Interval> intervals = interval.splitInterval(probabilities);

        assertEquals(intervals, expectedIntervals);

    }
}