package net.balsoftware.icalendar.properties.component.timezone;

import net.balsoftware.icalendar.components.StandardTime;
import net.balsoftware.icalendar.properties.PropBaseLanguage;

/**
 * TZNAME
 * Time Zone Name
 * RFC 5545, 3.8.3.2, page 103
 * 
 * This property specifies the customary designation for a time zone description.
 * 
 * EXAMPLES:
 * TZNAME:EST
 * TZNAME;LANGUAGE=fr-CA:HN
 * 
 * @author David Bal
 * @see DaylightSavingsTime
 * @see StandardTime
 */
public class TimeZoneName extends PropBaseLanguage<String, TimeZoneName>
{
    public TimeZoneName(TimeZoneName source)
    {
        super(source);
    }

    public TimeZoneName()
    {
        super();
    }
    
    public static TimeZoneName parse(String content)
    {
    	return TimeZoneName.parse(new TimeZoneName(), content);
    }
}
