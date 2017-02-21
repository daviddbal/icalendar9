package net.balsoftware.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import net.balsoftware.properties.component.relationship.Attendee;

/**
 * Interface for {@link Attendee} property
 * 
 * @author David Bal
 *
 * @param <T>  concrete subclass
 */
public interface VAttendee<T> extends VComponent
{
    /**
     * <p>This property defines an "Attendee" within a calendar component.<br>
     * RFC 5545 iCalendar 3.8.4.1 page 107</p>
     * 
     * <p>Examples:
     * <ul>
     * <li>ATTENDEE;MEMBER="mailto:DEV-GROUP@example.com":<br>
     *  mailto:joecool@example.com
     * <li>ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=ACCEPTED;CN=Jane Doe<br>
     *  :mailto:jdoe@example.com
     *  </ul>
     *  </p>
     */
    List<Attendee> getAttendees();
    void setAttendees(List<Attendee> properties);
    /**
     *  Sets the value of the {@link #attendeesProperty()} }
     *  
     *  @return - this class for chaining
     */
    default T withAttendees(List<Attendee> attendees)
    {
        setAttendees(attendees);
        return (T) this;
    }
    /**
     * Sets the value of the {@link #attendeesProperty()} from a vararg of {@link Attendee} objects.
     * 
     * @return - this class for chaining
     */    
    default T withAttendees(Attendee...attendees)
    {
        setAttendees(new ArrayList<>(Arrays.asList(attendees)));
        return (T) this;
    }
    /**
     * <p>Sets the value of the {@link #attendeesProperty()} by parsing a vararg of
     * iCalendar content text representing individual {@link Attendee} objects.</p>
     * 
     * @return - this class for chaining
     */    
    default T withAttendees(String...attendees)
    {
        List<Attendee> a = Arrays.stream(attendees)
            .map(c -> Attendee.parse(c))
            .collect(Collectors.toList());
        setAttendees(FXCollections.observableArrayList(a));
        return (T) this;
    }
}
