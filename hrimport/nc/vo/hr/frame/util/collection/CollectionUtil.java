/*
 * Created on 2005-6-14
 */
package nc.vo.hr.frame.util.collection;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Describe:printTemplate collection util
 * @version 1.0 2005-6-14 19:30:33
 * @author „∆≥§∫£
 */
public class CollectionUtil
{
    
    // list oper
    
    /*
     * cre the clazz array ,and fill every element with the obj new instnace
     */
    public static Object[] creArrays(Class clazz, int length)
    {
        if (clazz == null || length < 1)
        {
            return null;
        }
        Object[] result = (Object[]) Array.newInstance(clazz, length);
        try
        {
            for (int i = 0; i < length; i++)
            {
                result[i] = clazz.newInstance();
            }
            return result;
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * insert list range with the val
     * @param list
     * @param fromIndex
     * @param toIndex
     * @param val
     */
    public static void insertListMulti(List list, int index, int count, Object val)
    {
        if (list == null || count < 1 || index < 0 || index > list.size())
        {
            return;
        }
        Object[] vals = new Object[count];
        Arrays.fill(vals, val);
        list.addAll(index, Arrays.asList(vals));
    }
    
    /**
     * remove the range obj of list
     * @param list
     * @param fromIndex
     * @param toIndex
     */
    public static void removeListRange(List list, int fromIndex, int toIndex)
    {
        if (list == null || fromIndex < 0 || toIndex < 0 || fromIndex >= list.size() ||
                toIndex >= list.size())
        {
            return;
        }
        
        for (int i = toIndex; i >= fromIndex; i--)
        {
            list.remove(i);
        }
    }
    
    /**
     * reset the size of list.
     * @param list
     * @param size
     */
    public static void setListSize(List list, int size)
    {
        if (list == null || size < 0 || size == list.size())
        {
            return;
        }
        
        boolean isAdd = false;
        int count = 0;
        if (size > list.size())
        {
            isAdd = true;
            count = size - list.size();
        }
        else
        {
            count = list.size() - size;
        }
        
        if (isAdd)
        {
            Object[] vals = new Object[count];
            Arrays.fill(vals, null);
            list.addAll(Arrays.asList(vals));
        }
        else
        {
            int fromIndex = list.size() - count;
            int toIndex = list.size() - 1;
            removeListRange(list, fromIndex, toIndex);
        }
    }
}
