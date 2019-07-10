import Model.Interval;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class IntervalScheduling {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd yyyy hh:mm aaa");
    private HashSet<Interval> intervalList;
    private HashSet<Interval> solutionSet;
    private Interval helper;

    public IntervalScheduling() {
        intervalList = new HashSet<>();
        solutionSet = new HashSet<>();
    }

    public IntervalScheduling(HashSet<Interval> intervalList) {
        this.intervalList = intervalList;
        solutionSet = new HashSet<>();
    }

    public void addInterval(Interval interval) {
        intervalList.add(interval);
    }

    public HashSet<Interval> findIntervalSet() {
        solutionSet = new HashSet<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1);
        Date endDate = calendar.getTime();
        while (intervalLeft()) {
            solutionSet.add(helper);
        }
        return solutionSet;
    }

    // EFFECT: returns true if there is an interval left
    private Boolean intervalLeft() {
        for (Interval interval : intervalList) {
            if (!doesOverlap(interval)) {
                helper = interval;
                return true;
            }
        }
        return false;
    }

    // EFFECT: returns true if interval overlaps in solution set
    private Boolean doesOverlap(Interval interval) {
        long startTime = interval.getStart().getTime();
        long endTime = interval.getEnd().getTime();
        for (Interval solution : solutionSet) {
            if ((solution.getStart().getTime() <= endTime && solution.getStart().getTime() >= startTime) ||
            solution.getEnd().getTime() <= endTime && solution.getEnd().getTime() >= startTime) {
                return true;
            }
        }
        return false;
    }

    // EFFECT: prints out solution set
    public void printOut() {
        for (Interval interval : solutionSet) {
            System.out.println(printToString(interval.getStart()) + " to " + printToString(interval.getEnd()));
        }
    }

    // EFFECTS: returns a string representation of the date in the following format
    //     day-of-week month day year hour:minute
    //  example: Sun Jan 25 2019 10:30 AM
    private String printToString(Date date) {
        return simpleDateFormat.format(date);
    }
}
