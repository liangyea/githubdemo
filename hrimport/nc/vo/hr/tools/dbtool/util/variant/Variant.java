package nc.vo.hr.tools.dbtool.util.variant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import nc.vo.pub.lang.UFDate;

/**
 * 
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1713:00:26
 * @author „∆≥§∫£
 */
public class Variant
    implements Serializable, Cloneable
{
	private static final long serialVersionUID = -9157883288242062852L;

	private int a;
    private Object b;

    public Variant(int i)
    {
        a = i;
    }

    public Variant()
    {
        a = 0;
    }

    protected Object clone()
        throws CloneNotSupportedException
    {
        Variant variant;
        (variant = (Variant)super.clone()).setValue(getValue());
        return variant;
    }

    public boolean equals(Object obj)
    {
//        return ObjectUtils.equals(b, ((Variant)obj).getValue());
    	return false;
    }

    public BigDecimal getBigDecimal()
    {
        return VariantHelper.parseBigDecimal(b);
    }

    public boolean getBoolean()
    {
        return VariantHelper.parseBoolean(b);
    }

    public byte getByte()
    {
        return VariantHelper.parseByte(b);
    }

    public int getDataType()
    {
        return a;
    }

    public UFDate getDate()
    {
        return VariantHelper.parseUFDate(b);
    }

    public double getDouble()
    {
        return VariantHelper.parseDouble(b);
    }

    public float getFloat()
    {
        return VariantHelper.parseFloat(b);
    }

    public int getInt()
    {
        return VariantHelper.parseInt(b);
    }

    public long getLong()
    {
        return VariantHelper.parseLong(b);
    }

    public short getShort()
    {
        return VariantHelper.parseShort(b);
    }

    public String getString()
    {
        return VariantHelper.parseString(b);
    }

    public Object getValue()
    {
        return b;
    }

    public int hashCode()
    {
        if(b != null)
            return b.hashCode();
        return 0;
    }

    public boolean isNull()
    {
        return b == null;
    }

    public void setBigDecimal(BigDecimal bigdecimal)
    {
        if(a == 0)
            a = 8;
        Object obj = VariantHelper.toObject(bigdecimal);
        if(a == 8)
        {
            b = obj;
            return;
        } 
        b = VariantHelper.translate(a, obj);
        return;
    }

    public void setBoolean(boolean flag)
    {
        if(a == 0)
            a = 9;
        Object obj = VariantHelper.toObject(flag);
        if(a == 8)
        {
            b = obj;
            return;
        } 
        b = VariantHelper.translate(a, obj);
        return;
    }

    public void setByte(byte byte0)
    {
        if(a == 0)
            a = 2;
        Object obj = VariantHelper.toObject(byte0);
        if(a == 2)
        {
            b = obj;
            return;
        } 
        b = VariantHelper.translate(a, obj);
        return;
    }

    public void setDataType(String s)
    {
//        setDataType(DataType.nameToType(s));
    }

    public void setDataType(int i)
    {
        if(a == i)
        {
            return;
        } 
        a = i;
        return;
    }

    public void setDate(Date date)
    {
        if(a == 0)
            a = 10;
        Object obj = VariantHelper.toObject(date);
        if(a == 10 || a == 11 || a == 12)
        {
            b = obj;
            return;
        } 
        b = VariantHelper.translate(a, obj);
        return;
    }

    public void setDouble(double d)
    {
        if(a == 0)
            a = 7;
        Object obj = VariantHelper.toObject(d);
        if(a == 7)
        {
            b = obj;
            return;
        } 
        b = VariantHelper.translate(a, obj);
        return;
    }

    public void setFloat(float f)
    {
        if(a == 0)
            a = 6;
        Object obj = VariantHelper.toObject(f);
        if(a == 6)
        {
            b = obj;
            return;
        } 
        b = VariantHelper.translate(a, obj);
        return;
    }

    public void setInt(int i)
    {
        if(a == 0)
            a = 4;
        Object obj = VariantHelper.toObject(i);
        if(a == 4)
        {
            b = obj;
            return;
        } 
        b = VariantHelper.translate(a, obj);
        return;
    }

    public void setLong(long l)
    {
        if(a == 0)
            a = 5;
        Object obj = VariantHelper.toObject(l);
        if(a == 5)
        {
            b = obj;
            return;
        } 
        b = VariantHelper.translate(a, obj);
        return;
    }

    public void setNull()
    {
        b = null;
    }

    public void setShort(short word0)
    {
        if(a == 0)
            a = 3;
        Object obj = VariantHelper.toObject(word0);
        if(a == 3)
        {
            b = obj;
            return;
        }
        b = VariantHelper.translate(a, obj);
        return;
    }

    public void setString(String s)
    {
        if(a == 0)
            a = 1;
        Object obj = VariantHelper.toObject(s);
        if(a == 1)
        {
            b = obj;
            return;
        } 
        b = VariantHelper.translate(a, obj);
        return;
    }

    public void setValue(Object obj)
    {
        b = obj;
    }

    public String toString()
    {
        if(b != null)
            return b.toString();
        return (String)b;
    }
}
