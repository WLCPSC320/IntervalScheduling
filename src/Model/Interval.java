package Model;

import java.util.Calendar;
import java.util.Date;

public class Interval {
    private Date start;
    private Date end;
    private Calendar calendar;

    public Interval(Date start, Date end) {
        this.start = start;
        this.end = end;
        calendar = Calendar.getInstance();
    }

    public void setStart(Date date) {
        start = date;
    }

    public void setEnd(Date date) {
        end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
