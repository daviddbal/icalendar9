package net.balsoftware.icalendar.properties.component.recurrence.rrule.byxxx;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import net.balsoftware.icalendar.utilities.DateTimeUtilities;

/**
 * By Month Day
 * BYMONTHDAY
 * RFC 5545, iCalendar 3.3.10, page 42
 * 
 * The BYMONTHDAY rule part specifies a COMMA-separated list of days
      of the month.  Valid values are 1 to 31 or -31 to -1.  For
      example, -10 represents the tenth to the last day of the month.
      The BYMONTHDAY rule part MUST NOT be specified when the FREQ rule
      part is set to WEEKLY.
  *
  * @author David Bal
  * 
 * */
public class ByMonthDay extends ByRuleIntegerAbstract<ByMonthDay>
{
    /** sorted array of days of month
     * (i.e. 5, 10 = 5th and 10th days of the month, -3 = 3rd from last day of month)
     * Uses a varargs parameter to allow any number of days
     */
    
    /*
     * CONSTRUCTORS
     */
    
    public ByMonthDay()
    {
        super();
    }

    public ByMonthDay(Integer... daysOfMonth)
    {
        super(daysOfMonth);
    }
    
    public ByMonthDay(ByMonthDay source)
    {
        super(source);
    }
    
    @Override
    Predicate<Integer> isValidValue()
    {
        return (value) -> (value >= -31) && (value <= 31) && (value != 0);
    }
    
    /**
     * Return stream of valid dates made by rule (infinite if COUNT or UNTIL not present)
     */
    @Override
    public Stream<Temporal> streamRecurrences(Stream<Temporal> inStream, ChronoUnit chronoUnit, Temporal dateTimeStart)
    {
        switch (chronoUnit)
        {
        case HOURS:
        case MINUTES:
        case SECONDS:
        case DAYS:
            return inStream.filter(d ->
                    { // filter out all but qualifying days
                        int myDay = d.get(ChronoField.DAY_OF_MONTH);
                        int myDaysInMonth = LocalDate.from(d).lengthOfMonth();
                        for (int day : getValue())
                        {
                            if (myDay == day) return true;
                            // negative daysOfMonth (-3 = 3rd to last day of month)
                            if ((day < 0) && (myDay == myDaysInMonth + day + 1)) return true;
                        }
                        return false;
                    });
        case YEARS:
            return inStream.flatMap(d -> 
            { // Expand to be daysOfMonth days in current month
                List<Temporal> dates = new ArrayList<>();
                for (Month month : Month.values())
                {
                    Temporal monthAdjustedTemporal = d.with(ChronoField.MONTH_OF_YEAR, month.getValue());
                    dates.addAll(extracted(monthAdjustedTemporal, dateTimeStart));
                }
                return dates.stream().sorted(DateTimeUtilities.TEMPORAL_COMPARATOR);
            });
        case MONTHS:
            return inStream.flatMap(d -> 
            { // Expand to be daysOfMonth days in current month
                List<Temporal> dates = new ArrayList<>();
                dates.addAll(extracted(d, dateTimeStart));
                return dates.stream().sorted(DateTimeUtilities.TEMPORAL_COMPARATOR);
            });
        case WEEKS:
            throw new IllegalArgumentException(name().toString() + " is not available for " + chronoUnit + " frequency."); // Not available
        default:
            throw new IllegalArgumentException("Not implemented: " + chronoUnit);
        }
    }

    /* process dayOfMonth for YEARS and MONTHS */
    private List<Temporal> extracted(Temporal initialTemporal, Temporal dateTimeStart)
    {
        List<Temporal> dates = new ArrayList<>();
        for (int dayOfMonth : getValue())
        {           
            final Temporal correctMonthTemporal = (dayOfMonth > 0) ? initialTemporal : initialTemporal.minus(1, ChronoUnit.MONTHS);
            int daysInMonth = (int) ChronoUnit.DAYS.between(correctMonthTemporal.with(TemporalAdjusters.firstDayOfMonth()),
                                                            correctMonthTemporal.with(TemporalAdjusters.firstDayOfNextMonth()));
            int finalDayOfMonth = 0;
            if (dayOfMonth > 0)
            {
                if (dayOfMonth <= daysInMonth)
                {
                    finalDayOfMonth = dayOfMonth;
                }
            } else if (dayOfMonth < 0)
            {
                int newDayOfMonth = daysInMonth + dayOfMonth + 1;
                if (newDayOfMonth > 0)
                {
                    finalDayOfMonth = newDayOfMonth;
                }
            } else
            {
                throw new IllegalArgumentException(name().toString() + " can't have a value of zero");
            }
            Temporal newTemporal = (finalDayOfMonth != 0) ? correctMonthTemporal.with(ChronoField.DAY_OF_MONTH, finalDayOfMonth) : null;
            
            // ensure day of month hasn't changed.  If it changed the date was invalid and should be ignored.
            if (newTemporal != null)
            {
                dates.add(newTemporal);
            }
        }
        return dates;
    }
    
    public static ByMonthDay parse(String content)
    {
        ByMonthDay element = new ByMonthDay();
        element.parseContent(content);
        return element;
    }
}
