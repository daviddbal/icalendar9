package net.balsoftware.icalendar.content;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import net.balsoftware.icalendar.VCalendar;
import net.balsoftware.icalendar.VChild;
import net.balsoftware.icalendar.VParent;
import net.balsoftware.icalendar.components.VComponentBase;
import net.balsoftware.icalendar.properties.PropertyBase;
import net.balsoftware.icalendar.properties.component.recurrence.rrule.RecurrenceRuleValue;


/**
 *  Maintains a sort order of {@link VChild} elements of a {@link VParent}
 *
 *  Individual children are added automatically, list-based children are added through calling
 *  {@link #addChild(VChild) addChild} method.
 * 
 * @see VParent
 * @see VCalendar
 * @see VComponentBase
 * @see PropertyBase
 * @see RecurrenceRuleValue
 *  */ 
public class OrdererBase implements Orderer
{
    final private VParent parent;
    final private List<Method> childGetters;
    
    // TODO - SHOULD I USE A WEAK MAP??
    private List<VChild> orderedChildren = new ArrayList<>();

    /*
     * CONSTRUCTOR
     */
    /** Create an {@link OrdererBase} for the {@link VParent} parameter */
    public OrdererBase(VParent aParent, List<Method> childGetters)
    {
        this.parent = aParent;
        this.childGetters = childGetters;
    }

	@Override
	public List<VChild> childrenUnmodifiable()
	{
//		childGetters.forEach(System.out::println);
//		System.out.println("childUN:" + unorderedChildren(parent, childGetters).size() + " " + orderedChildren.size());

		// Remove orphans
		List<VChild> allUnorderedChildren = allUnorderedChildren(parent, childGetters);
		List<VChild> orphans = orderedChildren
			.stream()
			.filter(c -> ! allUnorderedChildren.contains(c))
			.collect(Collectors.toList());
		orphans.forEach(c -> orderedChildren.remove(c));
		List<VChild> allChildren = new ArrayList<>(orderedChildren);

		// Add unordered children
		allUnorderedChildren(parent, childGetters)
				.stream()
//				.peek(System.out::println)
				.filter(c -> ! orderedChildren.contains(c))
				.forEach(unorderedChild -> 
				{
					Class<? extends VChild> clazz = unorderedChild.getClass();
					List<VChild> matchedChildren = allChildren.stream()
						.filter(c2 -> c2.getClass().equals(clazz))
						.collect(Collectors.toList());
					if (! matchedChildren.isEmpty())
					{
						VChild lastMatchedChild = matchedChildren.get(matchedChildren.size()-1);
						int index = allChildren.indexOf(lastMatchedChild)+1;
						allChildren.add(index, unorderedChild); // put after last matched child
					} else
					{
						allChildren.add(unorderedChild); // no match, put at end
					}
				});
		return allChildren;
	}
	
    private List<VChild> allUnorderedChildren(VParent parent, List<Method> childGetters)
    {
    	return Collections.unmodifiableList(childGetters
    		.stream()
    		.map(m -> {
				try {
					return m.invoke(parent);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				return null;
			})
    		.filter(p -> p != null)
    		.flatMap(p -> 
    		{
    			if (p instanceof List)
    			{
    				return ((List<VChild>) p).stream();
    			} else
    			{
    				return Arrays.stream(new VChild[]{ (VChild) p });
    			}
    		})
    		.collect(Collectors.toList())
		);
    }

	@Override
	public void orderChild(VChild newChild)
	{
//		System.out.println("adding:" + newChild + "  " + System.identityHashCode(newChild));
		if ((! orderedChildren.contains(newChild)) && (newChild != null))
		{
			orderedChildren.add(newChild);
			newChild.setParent(parent);
		} else
		{
//			throw new RuntimeException("already added");
		}
	}

//	@Override
//	public void orderChild(VChild oldChild, VChild newChild)
//	{
////		System.out.println("adding:" + newChild);
//		if (oldChild == null)
//		{
//			if (! orderedChildren.contains(newChild))
//			{
//				orderedChildren.add(newChild);
//				newChild.setParent(parent);
//			}
//		} else
//		{
//			int index = orderedChildren.indexOf(oldChild);
//			orderedChildren.remove(oldChild);
//			if (newChild != null)
//			{
//				if (index >= 0)
//				{
//					orderedChildren.add(index, newChild);
//				} else
//				{
//					orderedChildren.add(newChild);				
//				}
//			}
//		}
//	}

	@Override
	public void orderChild(int index, VChild newChild)
	{
		orderedChildren.add(index, newChild);
	}
}