package net.balsoftware.icalendar.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.balsoftware.icalendar.VCalendar;
import net.balsoftware.icalendar.VChild;
import net.balsoftware.icalendar.VElement;
import net.balsoftware.icalendar.VParent;
import net.balsoftware.icalendar.components.StandardOrDaylight;
import net.balsoftware.icalendar.components.VAlarm;
import net.balsoftware.icalendar.components.VAttendee;
import net.balsoftware.icalendar.components.VCommon;
import net.balsoftware.icalendar.components.VComponent;
import net.balsoftware.icalendar.components.VComponentBase;
import net.balsoftware.icalendar.components.VDateTimeEnd;
import net.balsoftware.icalendar.components.VDescribable;
import net.balsoftware.icalendar.components.VDescribable2;
import net.balsoftware.icalendar.components.VDisplayable;
import net.balsoftware.icalendar.components.VDuration;
import net.balsoftware.icalendar.components.VEvent;
import net.balsoftware.icalendar.components.VFreeBusy;
import net.balsoftware.icalendar.components.VJournal;
import net.balsoftware.icalendar.components.VLastModified;
import net.balsoftware.icalendar.components.VLocatable;
import net.balsoftware.icalendar.components.VPersonal;
import net.balsoftware.icalendar.components.VPrimary;
import net.balsoftware.icalendar.components.VRepeatable;
import net.balsoftware.icalendar.components.VTimeZone;
import net.balsoftware.icalendar.components.VTodo;
import net.balsoftware.icalendar.parameters.VParameterElement;
import net.balsoftware.icalendar.properties.calendar.CalendarScale;
import net.balsoftware.icalendar.properties.calendar.ProductIdentifier;
import net.balsoftware.icalendar.properties.calendar.Version;
import net.balsoftware.icalendar.properties.component.alarm.Action;
import net.balsoftware.icalendar.properties.component.alarm.RepeatCount;
import net.balsoftware.icalendar.properties.component.alarm.Trigger;
import net.balsoftware.icalendar.properties.component.change.DateTimeCreated;
import net.balsoftware.icalendar.properties.component.change.DateTimeStamp;
import net.balsoftware.icalendar.properties.component.change.LastModified;
import net.balsoftware.icalendar.properties.component.change.Sequence;
import net.balsoftware.icalendar.properties.component.descriptive.Attachment;
import net.balsoftware.icalendar.properties.component.descriptive.Categories;
import net.balsoftware.icalendar.properties.component.descriptive.Classification;
import net.balsoftware.icalendar.properties.component.descriptive.Comment;
import net.balsoftware.icalendar.properties.component.descriptive.Description;
import net.balsoftware.icalendar.properties.component.descriptive.GeographicPosition;
import net.balsoftware.icalendar.properties.component.descriptive.Location;
import net.balsoftware.icalendar.properties.component.descriptive.PercentComplete;
import net.balsoftware.icalendar.properties.component.descriptive.Priority;
import net.balsoftware.icalendar.properties.component.descriptive.Resources;
import net.balsoftware.icalendar.properties.component.descriptive.Status;
import net.balsoftware.icalendar.properties.component.descriptive.Summary;
import net.balsoftware.icalendar.properties.component.misc.NonStandardProperty;
import net.balsoftware.icalendar.properties.component.misc.RequestStatus;
import net.balsoftware.icalendar.properties.component.recurrence.ExceptionDates;
import net.balsoftware.icalendar.properties.component.recurrence.RecurrenceDates;
import net.balsoftware.icalendar.properties.component.recurrence.RecurrenceRule;
import net.balsoftware.icalendar.properties.component.relationship.Attendee;
import net.balsoftware.icalendar.properties.component.relationship.Contact;
import net.balsoftware.icalendar.properties.component.relationship.Organizer;
import net.balsoftware.icalendar.properties.component.relationship.RecurrenceId;
import net.balsoftware.icalendar.properties.component.relationship.RelatedTo;
import net.balsoftware.icalendar.properties.component.relationship.UniformResourceLocator;
import net.balsoftware.icalendar.properties.component.relationship.UniqueIdentifier;
import net.balsoftware.icalendar.properties.component.time.DateTimeCompleted;
import net.balsoftware.icalendar.properties.component.time.DateTimeDue;
import net.balsoftware.icalendar.properties.component.time.DateTimeEnd;
import net.balsoftware.icalendar.properties.component.time.DateTimeStart;
import net.balsoftware.icalendar.properties.component.time.DurationProp;
import net.balsoftware.icalendar.properties.component.time.FreeBusyTime;
import net.balsoftware.icalendar.properties.component.time.TimeTransparency;
import net.balsoftware.icalendar.properties.component.timezone.TimeZoneIdentifier;
import net.balsoftware.icalendar.properties.component.timezone.TimeZoneName;
import net.balsoftware.icalendar.properties.component.timezone.TimeZoneOffsetFrom;
import net.balsoftware.icalendar.properties.component.timezone.TimeZoneOffsetTo;
import net.balsoftware.icalendar.properties.component.timezone.TimeZoneURL;


/**
 * For each VComponent property (RFC 5545, 3.8, page 80) contains the following: <br>
 * <br>
 * Property name {@link #toString()} <br>
 * Allowed property value type (first is default value type) {@link PropertyType#allowedValueTypes()}<br>
 * Allowed parameters {@link #allowedParameters()}<br>
 * Property class {@link #getPropertyClass()}<br>
 * Method to get property from component {@link #getProperty(VComponent)}<br>
 * Method to parse property string into parent component {@link #parse(VComponent, String)}<br>
 * Method to copy property into new parent component {@link #copyProperty(VProperty, VComponent)}<br>
 * 
 * @author David Bal
 *
 */
@Deprecated // Replace with Elements
public enum PropertyType
{
    // Alarm
    ACTION ("ACTION", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            Action.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VAlarm castComponent = (VAlarm) vComponent;
            return castComponent.getAction();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            Action child = Action.parse(propertyContent);
            VAlarm castComponent = (VAlarm) vParent;
            castComponent.setAction(child);
            return child;
        }
        
        @Override
        public boolean isRequired(VParent parent ) { return true; }
    },
    // property class
    ATTACHMENT ("ATTACH" // property name
            , Arrays.asList(ValueType.UNIFORM_RESOURCE_IDENTIFIER, ValueType.BINARY) // valid property value types, first is default
            , Arrays.asList(VParameterElement.FORMAT_TYPE, VParameterElement.INLINE_ENCODING, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD) // allowed parameters
            , Attachment.class)
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDescribable<?> castComponent = (VDescribable<?>) vComponent;
            return castComponent.getAttachments();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VDescribable<?> castComponent = (VDescribable<?>) vParent;
            final List<Attachment<?>> list;
            if (castComponent.getAttachments() == null)
            {
                list = new ArrayList<>();
                castComponent.setAttachments(list);
            } else
            {
                list = castComponent.getAttachments();
            }
            Attachment<?> child = Attachment.parse(propertyContent);
            list.add(child);
            vParent.orderChild(child);
            return child;
        }
    },

    ATTENDEE ("ATTENDEE"    // property name
            , Arrays.asList(ValueType.CALENDAR_USER_ADDRESS) // valid property value types, first is default
            , Arrays.asList(VParameterElement.COMMON_NAME, VParameterElement.CALENDAR_USER_TYPE, VParameterElement.DELEGATEES,
                    VParameterElement.DELEGATORS, VParameterElement.DIRECTORY_ENTRY_REFERENCE,
                    VParameterElement.GROUP_OR_LIST_MEMBERSHIP, VParameterElement.LANGUAGE, VParameterElement.PARTICIPATION_ROLE,
                    VParameterElement.PARTICIPATION_STATUS, VParameterElement.RSVP_EXPECTATION, VParameterElement.SENT_BY,
                    VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD) // allowed parameters
            , Attendee.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VAttendee<?> castComponent = (VAttendee<?>) vComponent;
            return castComponent.getAttendees();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VAttendee<?> castComponent = (VAttendee<?>) vParent;
            final List<Attendee> list;
            if (castComponent.getAttendees() == null)
            {
                list = new ArrayList<>();
                castComponent.setAttendees(list);
            } else
            {
                list = castComponent.getAttendees();
            }
            Attendee child = Attendee.parse(propertyContent);
            list.add(child);
            vParent.orderChild(child);
            return child;
        }
    },
    // Calendar property
    CALENDAR_SCALE ("CALSCALE" // property name
            , Arrays.asList(ValueType.TEXT) // valid property value types, first is default
            , Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD) // allowed parameters
            , CalendarScale.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            throw new RuntimeException(toString() + " is a calendar property.  It can't be a component property.");
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            throw new RuntimeException(toString() + " is a calendar property.  It can't be a component property.");
        }
    },
    // Descriptive
    CATEGORIES ("CATEGORIES" // property name
            , Arrays.asList(ValueType.TEXT) // valid property value types, first is default
            , Arrays.asList(VParameterElement.LANGUAGE, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD) // allowed parameters
            , Categories.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vComponent;
            return castComponent.getCategories();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vParent;
            final List<Categories> list;
            if (castComponent.getCategories() == null)
            {
                list = new ArrayList<>();
                castComponent.setCategories(list);
            } else
            {
                list = castComponent.getCategories();
            }
            Categories child = Categories.parse(propertyContent);
            list.add(child);
            vParent.orderChild(child);
            return child;
        }
    },
    // Descriptive
    CLASSIFICATION ("CLASS", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            Classification.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vComponent;
            return castComponent.getClassification();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vParent;
            Classification child = Classification.parse(propertyContent);
            castComponent.setClassification(child);
            return child;
        }
    },
    // Descriptive
    COMMENT ("COMMENT", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.ALTERNATE_TEXT_REPRESENTATION, VParameterElement.LANGUAGE, VParameterElement.VALUE_DATA_TYPES,
                    VParameterElement.NON_STANDARD), // allowed parameters
            Comment.class)
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VPrimary<?> castProperty = (VPrimary<?>) vComponent;
            return castProperty.getComments();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VPrimary<?> castComponent = (VPrimary<?>) vParent;
            final List<Comment> list;
            if (castComponent.getComments() == null)
            {
                list = new ArrayList<>();
                castComponent.setComments(list);
            } else
            {
                list = castComponent.getComments();
            }
            Comment child = Comment.parse(propertyContent);
            list.add(child);
            vParent.orderChild(child);
            return child;
        }
    },
    // Relationship
    CONTACT ("CONTACT", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.ALTERNATE_TEXT_REPRESENTATION, VParameterElement.LANGUAGE, VParameterElement.VALUE_DATA_TYPES,
                    VParameterElement.NON_STANDARD), // allowed parameters
            Contact.class)
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            if (vComponent instanceof VFreeBusy)
            {// VJournal has one Contact
                VFreeBusy castComponent = (VFreeBusy) vComponent;
                return castComponent.getContact();                
            } else
            { // Other components have a list of Contacts
                VDisplayable<?> castComponent = (VDisplayable<?>) vComponent;
                return castComponent.getContacts();
            }
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            Contact child = Contact.parse(propertyContent);
            if (vParent instanceof VFreeBusy)
            {// VJournal has one Contact
                VFreeBusy castComponent = (VFreeBusy) vParent;
                castComponent.setContact(child);                
            } else
            { // Other components have a list of Contacts
                VDisplayable<?> castComponent = (VDisplayable<?>) vParent;
                final List<Contact> list;
                if (castComponent.getContacts() == null)
                {
                    list = new ArrayList<>();
                    castComponent.setContacts(list);
                } else
                {
                    list = castComponent.getContacts();
                }
                list.add(child);
                vParent.orderChild(child);
            }
            return child;
        }
    },
    // Date and Time
    DATE_TIME_COMPLETED ("COMPLETED", // property name
            Arrays.asList(ValueType.DATE_TIME), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            DateTimeCompleted.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VTodo castComponent = (VTodo) vComponent;
            return castComponent.getDateTimeCompleted();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VTodo castComponent = (VTodo) vParent;
            DateTimeCompleted child = DateTimeCompleted.parse(propertyContent);
            castComponent.setDateTimeCompleted(child);
            return child;
        }
    },
    // Change management
    DATE_TIME_CREATED ("CREATED", // property name
            Arrays.asList(ValueType.DATE_TIME), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            DateTimeCreated.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vComponent;
            return castComponent.getDateTimeCreated();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vParent;
            DateTimeCreated child = DateTimeCreated.parse(propertyContent);
            castComponent.setDateTimeCreated(child);
            return child;
        }
    },
    // Date and time
    DATE_TIME_DUE ("DUE", // property name
            Arrays.asList(ValueType.DATE_TIME, ValueType.DATE), // valid property value types, first is default
            Arrays.asList(VParameterElement.TIME_ZONE_IDENTIFIER, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            DateTimeDue.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VTodo castComponent = (VTodo) vComponent;
            return castComponent.getDateTimeDue();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VTodo castComponent = (VTodo) vParent;
            DateTimeDue child = DateTimeDue.parse(propertyContent);
            castComponent.setDateTimeDue(child);
            return child;
        }
    },
    // Date and Time
    DATE_TIME_END ("DTEND", // property name
            Arrays.asList(ValueType.DATE_TIME, ValueType.DATE), // valid property value types, first is default
            Arrays.asList(VParameterElement.TIME_ZONE_IDENTIFIER, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            DateTimeEnd.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDateTimeEnd<?> castComponent = (VDateTimeEnd<?>) vComponent;
            return castComponent.getDateTimeEnd();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VDateTimeEnd<?> castComponent = (VDateTimeEnd<?>) vParent;
            DateTimeEnd child = DateTimeEnd.parse(propertyContent);
            castComponent.setDateTimeEnd(child);
            return child;
        }
        
        @Override
        public boolean isRequired(VParent parent ) { return true; }
    },
    // Change management
    DATE_TIME_STAMP ("DTSTAMP", // property name
            Arrays.asList(ValueType.DATE_TIME), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            DateTimeStamp.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VPersonal<?> castComponent = (VPersonal<?>) vComponent;
            return castComponent.getDateTimeStamp();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VPersonal<?> castComponent = (VPersonal<?>) vParent;
            DateTimeStamp child = DateTimeStamp.parse(propertyContent);
            castComponent.setDateTimeStamp(child);
            return child;
        }
        
        @Override
        public boolean isRequired(VParent parent ) { return true; }
    },
    DATE_TIME_START ("DTSTART", // property name
            Arrays.asList(ValueType.DATE_TIME, ValueType.DATE), // valid property value types, first is default
            Arrays.asList(VParameterElement.TIME_ZONE_IDENTIFIER, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            DateTimeStart.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VPrimary<?> castComponent = (VPrimary<?>) vComponent;
            return castComponent.getDateTimeStart();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VPrimary<?> castComponent = (VPrimary<?>) vParent;
            DateTimeStart child = DateTimeStart.parse(propertyContent);
            castComponent.setDateTimeStart(child);
            return child;
        }
        
        @Override
        public boolean isRequired(VParent parent )
        {
            return (parent instanceof VEvent) ? true : false;
        }
    },
    // Descriptive
    DESCRIPTION ("DESCRIPTION",
            Arrays.asList(ValueType.TEXT),
            Arrays.asList(VParameterElement.ALTERNATE_TEXT_REPRESENTATION, VParameterElement.LANGUAGE, VParameterElement.NON_STANDARD),
            Description.class)
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            if (vComponent instanceof VJournal)
            {// VJournal has list of Descriptions
                VJournal castComponent = (VJournal) vComponent;
                return castComponent.getDescriptions();                
            } else
            { // Other components have only one Description
                VDescribable2<?> castComponent = (VDescribable2<?>) vComponent;
                return castComponent.getDescription();
            }
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            Description child = Description.parse(propertyContent);
            if (vParent instanceof VJournal)
            { // VJournal has list of Descriptions
                VJournal castComponent = (VJournal) vParent;
                final List<Description> list;
                if (castComponent.getDescriptions() == null)
                {
                    list = new ArrayList<>();
                    castComponent.setDescriptions(list);
                } else
                {
                    list = castComponent.getDescriptions();
                }
                list.add(child);
                vParent.orderChild(child);
            } else
            { // Other components have only one Description
                VDescribable2<?> castComponent = (VDescribable2<?>) vParent;
                if (castComponent.getDescription() == null)
                {
                    castComponent.setDescription(child);                                
                } else
                {
                    throw new IllegalArgumentException(toString() + " can only occur once in a calendar component");
                }
            }
            return child;
        }
    },
    // Date and Time
    DURATION ("DURATION", // property name
            Arrays.asList(ValueType.DURATION), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            DurationProp.class)
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDuration<?> castComponent = (VDuration<?>) vComponent;
            return castComponent.getDuration();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            DurationProp child = DurationProp.parse(propertyContent);            
            VDuration<?> castComponent = (VDuration<?>) vParent;
            if (castComponent.getDuration() == null)
            {
                castComponent.setDuration(child);                                
            } else
            {
                throw new IllegalArgumentException(toString() + " can only occur once in a calendar component");
            }
            return child;
        }
        
        @Override
        public boolean isRequired(VParent parent ) { return true; }
    },
    // Recurrence
    EXCEPTION_DATE_TIMES ("EXDATE", // property name
            Arrays.asList(ValueType.DATE_TIME, ValueType.DATE), // valid property value types, first is default
            Arrays.asList(VParameterElement.TIME_ZONE_IDENTIFIER, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            ExceptionDates.class)
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vComponent;
            return castComponent.getExceptionDates();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            ExceptionDates child = ExceptionDates.parse(propertyContent);
            VDisplayable<?> castComponent = (VDisplayable<?>) vParent;
            final List<ExceptionDates> list;
            if (castComponent.getExceptionDates() == null)
            {
                list = new ArrayList<>();
                castComponent.setExceptionDates(list);
            } else
            {
                list = castComponent.getExceptionDates();
            }

            String error = VRepeatable.checkPotentialRecurrencesConsistency(list, child);
            if (error == null)
            {
                list.add(child);
                vParent.orderChild(child);
                return child;
            }
            return null; // invalid content
        }
    },
    // Date and Time
    FREE_BUSY_TIME ("FREEBUSY", // property name
            Arrays.asList(ValueType.PERIOD), // valid property value types, first is default
            Arrays.asList(VParameterElement.FREE_BUSY_TIME_TYPE, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            FreeBusyTime.class)
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VFreeBusy castComponent = (VFreeBusy) vComponent;
            return castComponent.getFreeBusyTime();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VFreeBusy castComponent = (VFreeBusy) vParent;
            FreeBusyTime child = FreeBusyTime.parse(propertyContent);
            castComponent.setFreeBusyTime(child);
            return child;
        }
    },
    // Descriptive
    GEOGRAPHIC_POSITION ("GEO", // property name
            Arrays.asList(ValueType.TEXT), // In GeographicPosition there are two doubles for latitude and longitude
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            GeographicPosition.class)
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VLocatable<?> castComponent = (VLocatable<?>) vComponent;
            return castComponent.getGeographicPosition();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VLocatable<?> castComponent = (VLocatable<?>) vParent;
            GeographicPosition child = GeographicPosition.parse(propertyContent);
            castComponent.setGeographicPosition(child);
            return child;
        }
    },
    // Change management
    LAST_MODIFIED ("LAST-MODIFIED", // property name
            Arrays.asList(ValueType.DATE_TIME), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            LastModified.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VLastModified<?> castComponent = (VLastModified<?>) vComponent;
            return castComponent.getDateTimeLastModified();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VLastModified<?> castComponent = (VLastModified<?>) vParent;
            LastModified child = LastModified.parse(propertyContent);
            castComponent.setDateTimeLastModified(child);
            return child;
        }
    },
    // Descriptive
    LOCATION ("LOCATION", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.ALTERNATE_TEXT_REPRESENTATION, VParameterElement.LANGUAGE, VParameterElement.VALUE_DATA_TYPES,
                    VParameterElement.NON_STANDARD), // allowed parameters
            Location.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VLocatable<?> castComponent = (VLocatable<?>) vComponent;
            return castComponent.getLocation();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VLocatable<?> castComponent = (VLocatable<?>) vParent;
            Location child = Location.parse(propertyContent);
            castComponent.setLocation(child);
            return child;
        }
    },
    // Calendar property
    METHOD ("METHOD" // property name
            , Arrays.asList(ValueType.TEXT) // valid property value types, first is default
            , Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD) // allowed parameters
            , net.balsoftware.icalendar.properties.calendar.Method.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            throw new RuntimeException(toString() + " is a calendar property.  It can't be a component property.");
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            throw new RuntimeException(toString() + " is a calendar property.  It can't be a component property.");
        }
    },
    // Miscellaneous
    NON_STANDARD ("X-", // property name begins with X- prefix
//    		collectGetters(NonStandardProperty.class),
            Arrays.asList(ValueType.values()), // valid property value types, first is default (any value allowed)
            Arrays.asList(VParameterElement.values()), // all parameters allowed
//            Arrays.asList(ParameterType.VALUE_DATA_TYPES, ParameterType.NON_STANDARD, ParameterType.LANGUAGE), // allowed parameters (RFC 5545 says only IANA, non-standard, and language property parameters can be specified on this property, but examples of other parameters are in RFC 5545, so I am allowing all parameters)
            NonStandardProperty.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VCommon<?> castComponent = (VCommon<?>) vComponent;
            return castComponent.getNonStandard();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VCommon<?> castComponent = (VCommon<?>) vParent;
            final List<NonStandardProperty> list;
            if (castComponent.getNonStandard() == null)
            {
                list = new ArrayList<>();
                castComponent.setNonStandard(list);
            } else
            {
                list = castComponent.getNonStandard();
            }
            NonStandardProperty child = NonStandardProperty.parse(propertyContent);
            list.add(child);
            vParent.orderChild(child);
            return child;
        }
    },
    // Relationship
    ORGANIZER ("ORGANIZER", // name
            Arrays.asList(ValueType.CALENDAR_USER_ADDRESS), // valid property value types, first is default
            Arrays.asList(VParameterElement.COMMON_NAME, VParameterElement.DIRECTORY_ENTRY_REFERENCE, VParameterElement.LANGUAGE,
                    VParameterElement.SENT_BY, VParameterElement.NON_STANDARD), // allowed parameters
            Organizer.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VPersonal<?> castComponent = (VPersonal<?>) vComponent;
            return castComponent.getOrganizer();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VPersonal<?> castComponent = (VPersonal<?>) vParent;
            Organizer child = Organizer.parse(propertyContent);
            castComponent.setOrganizer(child);
            return child;
        }
    },
    // Descriptive
    PERCENT_COMPLETE ("PERCENT-COMPLETE", // property name
            Arrays.asList(ValueType.INTEGER), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            PercentComplete.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VTodo castComponent = (VTodo) vComponent;
            return castComponent.getPercentComplete();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VTodo castComponent = (VTodo) vParent;
            PercentComplete child = PercentComplete.parse(propertyContent);
            castComponent.setPercentComplete(child);
            return child;
        }
    },
    // Descriptive
    PRIORITY ("PRIORITY", // property name
            Arrays.asList(ValueType.INTEGER), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            Priority.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VLocatable<?> castComponent = (VLocatable<?>) vComponent;
            return castComponent.getPriority();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VLocatable<?> castComponent = (VLocatable<?>) vParent;
            Priority child = Priority.parse(propertyContent);
            castComponent.setPriority(child);
            return child;
        }
    },
    // Calendar property
    PRODUCT_IDENTIFIER ("PRODID" // property name
            , Arrays.asList(ValueType.TEXT) // valid property value types, first is default
            , Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD) // allowed parameters
            , ProductIdentifier.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            throw new RuntimeException(toString() + " is a calendar property.  It can't be a component property.");
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            throw new RuntimeException(toString() + " is a calendar property.  It can't be a component property.");
        }
    },
    // Recurrence
    RECURRENCE_DATE_TIMES ("RDATE", // property name
            Arrays.asList(ValueType.DATE_TIME, ValueType.DATE, ValueType.PERIOD), // valid property value types, first is default
            Arrays.asList(VParameterElement.TIME_ZONE_IDENTIFIER, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            RecurrenceDates.class)
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VRepeatable<?> castComponent = (VRepeatable<?>) vComponent;
            return castComponent.getRecurrenceDates();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VRepeatable<?> castComponent = (VRepeatable<?>) vParent;
            final List<RecurrenceDates> list;
            if (castComponent.getRecurrenceDates() == null)
            {
                list = new ArrayList<>();
                castComponent.setRecurrenceDates(list);
            } else
            {
                list = castComponent.getRecurrenceDates();
            }
            RecurrenceDates child = RecurrenceDates.parse(propertyContent);
            list.add(child);
            vParent.orderChild(child);
            return child;
        }
    },
    // Relationship
    RECURRENCE_IDENTIFIER ("RECURRENCE-ID", // property name
            Arrays.asList(ValueType.DATE_TIME, ValueType.DATE), // valid property value types, first is default
            Arrays.asList(VParameterElement.RECURRENCE_IDENTIFIER_RANGE, VParameterElement.TIME_ZONE_IDENTIFIER, VParameterElement.VALUE_DATA_TYPES,
                    VParameterElement.NON_STANDARD), // allowed parameters
            RecurrenceId.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vComponent;
            return castComponent.getRecurrenceId();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vParent;
            RecurrenceId child = RecurrenceId.parse(propertyContent);
            castComponent.setRecurrenceId(child);
            return child;
        }
    },
    // Recurrence
    RECURRENCE_RULE ("RRULE", // property name
            Arrays.asList(ValueType.RECURRENCE_RULE), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            RecurrenceRule.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VRepeatable<?> castComponent = (VRepeatable<?>) vComponent;
            return castComponent.getRecurrenceRule();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VRepeatable<?> castComponent = (VRepeatable<?>) vParent;
            RecurrenceRule child = RecurrenceRule.parse(propertyContent);
            castComponent.setRecurrenceRule(child);
            return child;
        }
    },
    // Relationship
    RELATED_TO ("RELATED-TO", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.RELATIONSHIP_TYPE, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            RelatedTo.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vComponent;
            return castComponent.getRelatedTo();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vParent;
            final List<RelatedTo> list;
            if (castComponent.getRelatedTo() == null)
            {
                list = new ArrayList<>();
                castComponent.setRelatedTo(list);
            } else
            {
                list = castComponent.getRelatedTo();
            }
            RelatedTo child = RelatedTo.parse(propertyContent);
            list.add(child);
            vParent.orderChild(child);
            return child;
        }
    },
    // Alarm
    REPEAT_COUNT ("REPEAT", // property name
            Arrays.asList(ValueType.INTEGER), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            RepeatCount.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VAlarm castComponent = (VAlarm) vComponent;
            return castComponent.getRepeatCount();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VAlarm castComponent = (VAlarm) vParent;
            RepeatCount child = RepeatCount.parse(propertyContent);
            castComponent.setRepeatCount(child);
            return child;
        }
    },
    // Miscellaneous
    REQUEST_STATUS ("REQUEST-STATUS", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.LANGUAGE, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            RequestStatus.class)
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VPersonal<?> castComponent = (VPersonal<?>) vComponent;
            return castComponent.getRequestStatus();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VPersonal<?> castComponent = (VPersonal<?>) vParent;
            final List<RequestStatus> list;
            if (castComponent.getRequestStatus() == null)
            {
                list = new ArrayList<>();
                castComponent.setRequestStatus(list);
            } else
            {
                list = castComponent.getRequestStatus();
            }
            RequestStatus child = RequestStatus.parse(propertyContent);
            list.add(child);
            vParent.orderChild(child);
            return child;
        }
    },
    // Descriptive
    RESOURCES ("RESOURCES", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.ALTERNATE_TEXT_REPRESENTATION, VParameterElement.LANGUAGE, VParameterElement.VALUE_DATA_TYPES,
                    VParameterElement.NON_STANDARD), // allowed parameters
            Resources.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VLocatable<?> castProperty = (VLocatable<?>) vComponent;
            return castProperty.getResources();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VLocatable<?> castComponent = (VLocatable<?>) vParent;
            final List<Resources> list;
            if (castComponent.getResources() == null)
            {
                list = new ArrayList<>();
                castComponent.setResources(list);
            } else
            {
                list = castComponent.getResources();
            }
            Resources child = Resources.parse(propertyContent);
            list.add(child);
            vParent.orderChild(child);
            return child;
        }
    },
    // Change management
    SEQUENCE ("SEQUENCE", // property name
            Arrays.asList(ValueType.INTEGER), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            Sequence.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vComponent;
            return castComponent.getSequence();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vParent;
            Sequence child = Sequence.parse(propertyContent);
            castComponent.setSequence(child);
            return child;
        }
    },
    // Descriptive
    STATUS ("STATUS", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            Status.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vComponent;
            return castComponent.getStatus();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VDisplayable<?> castComponent = (VDisplayable<?>) vParent;
            Status child = Status.parse(propertyContent);
            castComponent.setStatus(child);
            return child;            
        }
    },
    // Descriptive
    SUMMARY ("SUMMARY", // property name
//    		collectGetters(Summary.class),
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.ALTERNATE_TEXT_REPRESENTATION, VParameterElement.LANGUAGE,
                    VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            Summary.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VDescribable<?> castComponent = (VDescribable<?>) vComponent;
            return castComponent.getSummary();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VDescribable<?> castComponent = (VDescribable<?>) vParent;
            Summary child = Summary.parse(propertyContent);
            castComponent.setSummary(child);
            return child;
        }
    },
    // Date and Time
    TIME_TRANSPARENCY ("TRANSP", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            TimeTransparency.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VEvent castComponent = (VEvent) vComponent;
            return castComponent.getTimeTransparency();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VEvent castComponent = (VEvent) vParent;
            TimeTransparency child = TimeTransparency.parse(propertyContent);
            castComponent.setTimeTransparency(child);
            return child;
        }
    },
    // Time Zone
    TIME_ZONE_IDENTIFIER ("TZID", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            TimeZoneIdentifier.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VTimeZone castComponent = (VTimeZone) vComponent;
            return castComponent.getTimeZoneIdentifier();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VTimeZone castComponent = (VTimeZone) vParent;
            TimeZoneIdentifier child = TimeZoneIdentifier.parse(propertyContent);
            castComponent.setTimeZoneIdentifier(child);
            return child;
        }
        
        @Override
        public boolean isRequired(VParent parent )
        {
            return (parent instanceof VTimeZone) ? true : false;
        }
    },
    // Time Zone
    TIME_ZONE_NAME ("TZNAME", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.LANGUAGE, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            TimeZoneName.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            StandardOrDaylight<?> castProperty = (StandardOrDaylight<?>) vComponent;
            return castProperty.getTimeZoneNames();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            StandardOrDaylight<?> castComponent = (StandardOrDaylight<?>) vParent;
            final List<TimeZoneName> list;
            if (castComponent.getTimeZoneNames() == null)
            {
                list = new ArrayList<>();
                castComponent.setTimeZoneNames(list);
            } else
            {
                list = castComponent.getTimeZoneNames();
            }
            TimeZoneName child = TimeZoneName.parse(propertyContent);
            list.add(child);
            vParent.orderChild(child);
            return child;
        }
    },
    // Time Zone
    TIME_ZONE_OFFSET_FROM ("TZOFFSETFROM", // property name
            Arrays.asList(ValueType.UTC_OFFSET), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            TimeZoneOffsetFrom.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            StandardOrDaylight<?> castComponent = (StandardOrDaylight<?>) vComponent;
            return castComponent.getTimeZoneOffsetFrom();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            StandardOrDaylight<?> castComponent = (StandardOrDaylight<?>) vParent;
            TimeZoneOffsetFrom child = TimeZoneOffsetFrom.parse(propertyContent);
            castComponent.setTimeZoneOffsetFrom(child);
            return child;
        }
        
        @Override
        public boolean isRequired(VParent parent ) { return true; }
    },
    // Time Zone
    TIME_ZONE_OFFSET_TO ("TZOFFSETTO", // property name
            Arrays.asList(ValueType.UTC_OFFSET), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            TimeZoneOffsetTo.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            StandardOrDaylight<?> castComponent = (StandardOrDaylight<?>) vComponent;
            return castComponent.getTimeZoneOffsetTo();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            StandardOrDaylight<?> castComponent = (StandardOrDaylight<?>) vParent;
            TimeZoneOffsetTo child = TimeZoneOffsetTo.parse(propertyContent);
            castComponent.setTimeZoneOffsetTo(child);
            return child;
        }
        
        @Override
        public boolean isRequired(VParent parent ) { return true; }
    },
    // Time Zone
    TIME_ZONE_URL ("TZURL", // property name
            Arrays.asList(ValueType.UNIFORM_RESOURCE_IDENTIFIER), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            TimeZoneURL.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VTimeZone castComponent = (VTimeZone) vComponent;
            return castComponent.getTimeZoneURL();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VTimeZone castComponent = (VTimeZone) vParent;
            TimeZoneURL child = TimeZoneURL.parse(propertyContent);
            castComponent.setTimeZoneURL(child);
            return child;
        }
    },
    // Alarm
    TRIGGER ("TRIGGER", // property name
            Arrays.asList(ValueType.DURATION, ValueType.DATE_TIME), // valid property value types, first is default
            Arrays.asList(VParameterElement.ALARM_TRIGGER_RELATIONSHIP, VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            Trigger.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VAlarm castComponent = (VAlarm) vComponent;
            return castComponent.getTrigger();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VAlarm castComponent = (VAlarm) vParent;
            Trigger<?> child = Trigger.parse(propertyContent);
            castComponent.setTrigger(child);
            return child;            
        }
        
        @Override
        public boolean isRequired(VParent parent ) { return true; }
    },
    // Relationship
    UNIQUE_IDENTIFIER ("UID", // property name
            Arrays.asList(ValueType.TEXT), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            UniqueIdentifier.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VPersonal<?> castComponent = (VPersonal<?> ) vComponent;
            return castComponent.getUniqueIdentifier();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VPersonal<?> castComponent = (VPersonal<?> ) vParent;
            UniqueIdentifier child = UniqueIdentifier.parse(propertyContent);
            castComponent.setUniqueIdentifier(child);
            return child;
        }
    },
    // Relationship
    UNIFORM_RESOURCE_LOCATOR ("URL", // property name
            Arrays.asList(ValueType.UNIFORM_RESOURCE_IDENTIFIER), // valid property value types, first is default
            Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD), // allowed parameters
            UniformResourceLocator.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            VPersonal<?> castComponent = (VPersonal<?> ) vComponent;
            return castComponent.getURL();
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VPersonal<?> castComponent = (VPersonal<?> ) vParent;
            UniformResourceLocator child = UniformResourceLocator.parse(propertyContent);
            castComponent.setURL(child);
            return child;
        }
        
        @Override
        public boolean isRequired(VParent parent ) { return true; }
    },
    // Calendar property
    VERSION ("VERSION" // property name
            , Arrays.asList(ValueType.TEXT) // valid property value types, first is default
            , Arrays.asList(VParameterElement.VALUE_DATA_TYPES, VParameterElement.NON_STANDARD) // allowed parameters
            , Version.class) // property class
    {
        @Override
        public Object getProperty(VComponent vComponent)
        {
            throw new RuntimeException(toString() + " is a calendar property.  It can't be a component property.");
        }

        @Override
        public VChild parse(VParent vParent, String propertyContent)
        {
            VCalendar vCalendar = (VCalendar) vParent;
            Version child = Version.parse(propertyContent);
            vCalendar.setVersion(child);
            return child;
        }
    };
	    
    private static Map<String, PropertyType> enumFromNameMap = makeEnumFromNameMap();
    private static Map<String, PropertyType> makeEnumFromNameMap()
    {
        Map<String, PropertyType> map = new HashMap<>();
        PropertyType[] values = PropertyType.values();
        for (int i=0; i<values.length; i++)
        {
            map.put(values[i].toString(), values[i]);
        }
        return map;
    }
    public static PropertyType enumFromName(String propertyName)
    {
        final PropertyType prop;
        if (propertyName.length() < 3) return null; // minimum property name is 3 characters
        if (propertyName.substring(0, PropertyType.NON_STANDARD.toString().length()).equals(PropertyType.NON_STANDARD.toString()))
        {
            prop = PropertyType.NON_STANDARD;
//        } else if ((IANAProperty.getRegisteredIANAPropertys() != null) && IANAProperty.getRegisteredIANAPropertys().contains(propertyName))
//        {
//            prop = PropertyType.IANA_PROPERTY;            
        } else
        {
            prop = enumFromNameMap.get(propertyName);   
        }
        return prop;
    }
    
    // Map to match up class to enum
    private static Map<Class<? extends VProperty>, PropertyType> enumFromClassMap = makeEnumFromClassMap();
    private static Map<Class<? extends VProperty>, PropertyType> makeEnumFromClassMap()
    {
        Map<Class<? extends VProperty>, PropertyType> map = new HashMap<>();
        PropertyType[] values = PropertyType.values();
        for (int i=0; i<values.length; i++)
        {
            map.put(values[i].myClass, values[i]);
        }
        return map;
    }
    /** get enum from map */
    public static PropertyType enumFromClass(Class<? extends VElement> myClass)
    {
        return enumFromClassMap.get(myClass);
    }
    
    private Class<? extends VProperty> myClass;
    public Class<? extends VProperty> getPropertyClass() { return myClass; }

    @Override
    public String toString() { return name; }
    private String name;
    
    private List<ValueType> valueTypes;
    public List<ValueType> allowedValueTypes() { return valueTypes; }

    private List<VParameterElement> allowedParameters;
    public List<VParameterElement> allowedParameters() { return allowedParameters; }

    /*
     * CONSTRUCTOR
     */
    PropertyType(
    		String name, 
//    		List<Method> getters,
    		List<ValueType> valueTypes, 
    		List<VParameterElement> allowedParameters,
    		Class<? extends VProperty> myClass)
    {
        this.allowedParameters = allowedParameters;
        this.name = name;
        this.valueTypes = valueTypes;
        this.myClass = myClass;
    }
    /*
     * ABSTRACT METHODS
     */
    /** Returns associated Property<?> or List<Property<?>> */
    abstract public Object getProperty(VComponent vComponent);

    /** Parses string and sets property.  Called by {@link VComponentBase#parseContent()} */
    // use parse method from class
    @Deprecated
    abstract public VChild parse(VParent vParent, String propertyContent);
    
    /** If property is required returns true, false otherwise */
    public boolean isRequired(VParent parent ) { return false; }
}
