package net.balsoftware.icalendar.properties.component.recurrence;

import net.balsoftware.icalendar.components.DaylightSavingTime;
import net.balsoftware.icalendar.components.StandardTime;
import net.balsoftware.icalendar.components.VEvent;
import net.balsoftware.icalendar.components.VJournal;
import net.balsoftware.icalendar.components.VTodo;
import net.balsoftware.icalendar.properties.VPropertyBase;
import net.balsoftware.icalendar.properties.component.recurrence.RecurrenceRule;
import net.balsoftware.icalendar.properties.component.recurrence.rrule.RecurrenceRuleValue;

/**
 * RRULE
 * Recurrence Rule
 * RFC 5545 iCalendar 3.8.5.3, page 122.
 * 
 * This property defines a rule or repeating pattern for
 * recurring events, to-dos, journal entries, or time zone definitions.
 * 
 * Produces a stream of start date/times after applying all modification rules.
 * 
 * @author David Bal
 * @see VEvent
 * @see VTodo
 * @see VJournal
 * @see DaylightSavingTime
 * @see StandardTime
 */
public class RecurrenceRule extends VPropertyBase<RecurrenceRuleValue, RecurrenceRule>
{
    public RecurrenceRule(RecurrenceRuleValue value)
    {
        super(value);
    }
    
    public RecurrenceRule()
    {
        super();
    }

    public RecurrenceRule(RecurrenceRule source)
    {
        super(source);
    }

    @Override
    protected RecurrenceRuleValue copyValue(RecurrenceRuleValue source)
    {
        return new RecurrenceRuleValue(source);
    }

    public static RecurrenceRule parse(String content)
    {
    	return RecurrenceRule.parse(new RecurrenceRule(), content);
    }
}
