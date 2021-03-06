package net.balsoftware.icalendar.parameter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.balsoftware.icalendar.parameters.ValueParameter;
import net.balsoftware.icalendar.properties.ValueType;

public class ValueTypeTest
{
//    @Test
//    public void canBuildValueParameter()
//    {
//        ValueParameter parameter = new ValueParameter(ValueType.DATE);
//        String expectedContent = "VALUE=DATE";
//        assertEquals(expectedContent, parameter.toString());
//        assertEquals(parameter.getValue(), ValueType.DATE);
//    }
    
    @Test
    public void canParseValueParameter()
    {
        ValueParameter parameter = new ValueParameter();
        parameter.setValue(ValueType.enumFromName("URI"));
        String expectedContent = "VALUE=URI";
        assertEquals(expectedContent, parameter.toString());
        assertEquals(parameter.getValue(), ValueType.UNIFORM_RESOURCE_IDENTIFIER);
    }
    
    /*
     * Applications MUST preserve the value data for x-name and iana-
      token values that they don't recognize without attempting to
      interpret or parse the value data.
     */
    @Test
    public void canParseValueParameter2()
    {
        ValueParameter parameter = ValueParameter.parse("COLOR");
        String expectedContent = "VALUE=COLOR";
        assertEquals(expectedContent, parameter.toString());
        assertEquals(parameter.getValue(), ValueType.UNKNOWN);
    }
}
