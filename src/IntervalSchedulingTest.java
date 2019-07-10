import Model.Interval;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class IntervalSchedulingTest {
    private Calendar calendar;
    private Interval i1, i2, i3, i4, i5;
    private HashSet<Interval> intervalSet;
    private IntervalScheduling intervalScheduling;

    @Before
    public void runBefore() {
        Date sDate;
        Date eDate;
        calendar = Calendar.getInstance();
        setCalendar(2019, 7,10,1,0,0);
        sDate = calendar.getTime();
        setCalendar(2019, 7,10,2,0,0);
        eDate = calendar.getTime();
        i1 = new Interval(sDate, eDate);
        setCalendar(2019, 7,10,1,0,0);
        sDate = calendar.getTime();
        setCalendar(2019, 7,10,3,0,0);
        eDate = calendar.getTime();
        i2 = new Interval(sDate, eDate);
        setCalendar(2019, 7,10,1,0,0);
        sDate = calendar.getTime();
        setCalendar(2019, 7,10,4,0,0);
        eDate = calendar.getTime();
        i3 = new Interval(sDate, eDate);
        setCalendar(2019, 7,10,5,0,0);
        sDate = calendar.getTime();
        setCalendar(2019, 7,10,6,0,0);
        eDate = calendar.getTime();
        i4 = new Interval(sDate, eDate);
        setCalendar(2019, 7,10,8,0,0);
        sDate = calendar.getTime();
        setCalendar(2019, 7,10,9,0,0);
        eDate = calendar.getTime();
        i5 = new Interval(sDate, eDate);
        intervalSet = new HashSet<>();
    }

    @Test
    public void testTrivalEmpty() {
        System.out.println("testTrivalEmpty");
        intervalScheduling = new IntervalScheduling();
        HashSet<Interval> result = intervalScheduling.findIntervalSet();
        intervalScheduling.printOut();
        assertEquals(result.size(), 0);
    }

    @Test
    public void testTrivalOneInterval() {
        System.out.println("testTrivalOneInterval");
        intervalSet.add(i1);
        intervalScheduling = new IntervalScheduling(intervalSet);
        HashSet<Interval> result = intervalScheduling.findIntervalSet();
        intervalScheduling.printOut();
        assertEquals(result.size(), 1);
        assertTrue(result.contains(i1));
    }

    @Test
    public void testScheduleIntervalsSimple() {
        System.out.println("testScheduleIntervalsSimple");
        intervalSet.add(i1);
        intervalSet.add(i4);
        intervalScheduling = new IntervalScheduling(intervalSet);
        HashSet<Interval> result = intervalScheduling.findIntervalSet();
        intervalScheduling.printOut();
        assertEquals(result.size(), 2);
        assertTrue(result.contains(i1));
        assertTrue(result.contains(i4));
    }

    @Test
    public void testScheduleIntervalsAdvanced() {
        System.out.println("testScheduleIntervalsAdvanced");
        intervalSet.add(i1);
        intervalSet.add(i2);
        intervalSet.add(i3);
        intervalSet.add(i4);
        intervalSet.add(i5);
        intervalScheduling = new IntervalScheduling(intervalSet);
        HashSet<Interval> result = intervalScheduling.findIntervalSet();
        intervalScheduling.printOut();
        assertEquals(result.size(), 3);
        assertTrue((result.contains(i1) ^ result.contains(i2)) ^ result.contains(i3));
        assertTrue(result.contains(i4));
        assertTrue(result.contains(i5));
    }

    private void setCalendar(int year, int month, int day, int hour, int minute, int second) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_YEAR, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
    }
}