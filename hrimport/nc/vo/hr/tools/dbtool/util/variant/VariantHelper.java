package nc.vo.hr.tools.dbtool.util.variant;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import nc.jdbc.framework.type.BlobParamType;
import nc.jdbc.framework.type.ClobParamType;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.lang.UFTime;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1710:58:13
 * @author 闫长海
 */
public class VariantHelper {

    private VariantHelper()
    {
    }

    public static final BigDecimal parseBigDecimal(Object obj)
    {
        if(obj == null)
            return BigDecimal.valueOf(0L);
        if(obj instanceof BigDecimal)
            return (BigDecimal)obj;
        if(obj instanceof Number)
            return BigDecimal.valueOf(((Number)obj).longValue());
        if(obj instanceof Boolean)
            return BigDecimal.valueOf(((Boolean)obj).booleanValue() ? 1L : 0L);
        String s;
        if((s = obj.toString()).equals(""))
            return BigDecimal.valueOf(0L);
        return new BigDecimal(s);
    }

    public static final boolean parseBoolean(Object obj)
    {
        if(obj == null)
            return false;
        if(obj instanceof Boolean)
            return ((Boolean)obj).booleanValue();
        return parseBoolean(obj.toString());
    }

    public static final boolean parseBoolean(String s)
    {
        if(s == null)
            return false;
        return s.equalsIgnoreCase("true") || s.equals("1") || s.equalsIgnoreCase("Y");
    }

    public static final byte parseByte(Object obj)
    {
        if(obj == null)
            return 0;
        if(obj instanceof Number)
            return ((Number)obj).byteValue();
        if(obj instanceof Boolean)
            return (byte)(((Boolean)obj).booleanValue() ? 1 : 0);
        String s;
        if((s = obj.toString()).equals(""))
            return 0;
        return Byte.parseByte(s);
    }

    public static final Date parseDate(Object obj)
    {
        String s;
        if(obj == null)
            return null;
        if(obj instanceof Date)
            return (Date)obj;
        if(obj instanceof Number)
            return new Date(((Number)obj).longValue());

        if(!nc.vo.hr.tools.dbtool.util.StringHelper.isNotEmpty(s = String.valueOf(obj)))
            return null;
/*        if(StringUtils.isNumeric(s))
        {
            long l = Long.parseLong(s);
            return new Date(l);
        }*/
        
        try {
			return DateFormat.getInstance().parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }
    
	  public static final UFDate parseUFDate(Object obj)
	    {
	        String s;
	        if(obj == null)
	            return null;
	        if(obj instanceof Date)
	            return new UFDate( (Date)obj );
	        if(obj instanceof Number)
	            return new UFDate( new Date(((Number)obj).longValue()) );

	        if(!nc.vo.hr.tools.dbtool.util.StringHelper.isNotEmpty(s = String.valueOf(obj)))
	            return null;
	        

	        UFDate ufDate = new UFDate( s );
	        return ufDate;

	    }
	
    public static UFTime parseTime(Object obj) {
    	if(obj instanceof UFTime) {
    		return (UFTime) obj;
    	}
    	return new UFTime( obj.toString() );
    }

    public static final double parseDouble(Object obj)
    {
        if(obj == null)
            return 0.0D;
        if(obj instanceof Number)
            return ((Number)obj).doubleValue();
        if(obj instanceof Boolean)
            return !((Boolean)obj).booleanValue() ? 0.0D : 1.0D;
        String s;
        if((s = obj.toString()).equals(""))
            return 0.0D;
        return Double.parseDouble(s);
    }

    public static final float parseFloat(Object obj)
    {
        if(obj == null)
            return 0.0F;
        if(obj instanceof Number)
            return ((Number)obj).floatValue();
        if(obj instanceof Boolean)
            return !((Boolean)obj).booleanValue() ? 0.0F : 1.0F;
        String s;
        if((s = obj.toString()).equals(""))
            return 0.0F;
        return Float.parseFloat(s);
    }

    public static final int parseInt(Object obj)
    {
        if(obj == null)
            return 0;
        if(obj instanceof Number)
            return ((Number)obj).intValue();
        if(obj instanceof Boolean)
            return !((Boolean)obj).booleanValue() ? 0 : 1;
        String s;
        if((s = obj.toString()).equals(""))
            return 0;
        return Integer.parseInt(s);
    }

    public static final long parseLong(Object obj)
    {
        if(obj == null)
            return 0L;
        if(obj instanceof Number)
            return ((Number)obj).longValue();
        if(obj instanceof Boolean)
            return !((Boolean)obj).booleanValue() ? 0L : 1L;
        String s;
        if((s = obj.toString()).equals(""))
            return 0L;
        return Long.parseLong(s);
    }

    public static final short parseShort(Object obj)
    {
        if(obj == null)
            return 0;
        if(obj instanceof Number)
            return ((Number)obj).shortValue();
        if(obj instanceof Boolean)
            return (short)(((Boolean)obj).booleanValue() ? 1 : 0);
        String s;
        if((s = obj.toString()).equals(""))
            return 0;
        return Short.parseShort(s);
    }

    public static final String parseString(Object obj)
    {
        if(obj == null)
            return null;
        if(obj instanceof Date)
            return String.valueOf(((Date)obj).getTime());
        return obj.toString();
    }

    public static final Object toObject(String s)
    {
        return s;
    }

    public static final Object toObject(BigDecimal bigdecimal)
    {
        return bigdecimal;
    }

    public static final Object toObject(Date date)
    {
        return date;
    }

    public static final Object toObject(boolean flag)
    {
        return new Boolean(flag);
    }

    public static final Object toObject(double d)
    {
        return new Double(d);
    }

    public static final Object toObject(float f)
    {
        return new Float(f);
    }

    public static final Object toObject(long l)
    {
        return new Long(l);
    }

    public static final Object toObject(int i)
    {
        return new Integer(i);
    }

    public static final Object toObject(short word0)
    {
        return new Short(word0);
    }

    public static final Object toObject(byte byte0)
    {
        return new Byte(byte0);
    }

    public static final Object translate(int i, Object obj)
    {
        if(obj == null || (obj instanceof String) && ((String)obj).length() == 0){
            if(i == 1)
                return obj;
            return null;
        }
        switch(i)
        {
        case 1: // '\001'
            return parseString(obj);

        case 9: // '\t'
            return toObject(parseBoolean(obj));

        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
            return parseDate(obj);

        case 4: // '\004'
            return toObject(parseInt(obj));

        case 7: // '\007'
            return toObject(parseDouble(obj));

        case 5: // '\005'
            return toObject(parseLong(obj));

        case 6: // '\006'
            return toObject(parseFloat(obj));

        case 8: // '\b'
            return parseBigDecimal(obj);

        case 2: // '\002'
            return toObject(parseByte(obj));

        case 3: // '\003'
            return toObject(parseShort(obj));
        }
        return obj;
    }
    
	public static Object transObj(DataRow row, Object obj, int type ) throws BusinessException {
		try {
			obj = VariantHelper.transObj( obj, type );
		} catch (Exception e) {
			e.printStackTrace();
			String rowHint = row.getDataTable().getTableMeta().getRowHintBuilder().creHint(row);
			throw new BusinessException(rowHint +"数据格式不正确!\n"+ e.getMessage());
		}
		return obj;
	}
    
    /** 
     * for the jdbcsession
     * @param source
     * @param type sql type.
     * @return
     */
    public static Object transObj(Object source, int type) {
    	Object obj= source;
    	
    	if( source==null ) {
    		return null;
    	}
    	
    	switch( type ) {
		    case java.sql.Types.CHAR:
		    case java.sql.Types.VARCHAR:
		    case java.sql.Types.LONGVARCHAR:
		    case java.sql.Types.BIT:
		        obj = source.toString();
		        break;
		    case java.sql.Types.INTEGER:
		    case java.sql.Types.SMALLINT:
		    case java.sql.Types.TINYINT:
		    case java.sql.Types.BIGINT:
		    	if( source instanceof Integer ||  source instanceof Short
		    			|| source instanceof Long ) {
		    		return source;
		    	}
		    	obj = new Integer( source.toString() );
		        break;
		    case java.sql.Types.DOUBLE:
		    case java.sql.Types.REAL:
		    	obj = new Double( source.toString() );
		        break;
		    case java.sql.Types.FLOAT:
		    	if( source instanceof Float ) {
		    		return source;
		    	}
		    	obj = new Float( source.toString() );
		        break;
		    case java.sql.Types.DECIMAL:
		    case java.sql.Types.NUMERIC:
		    	if( source instanceof Double || source instanceof UFDouble ) {
		    		return source;
		    	}
		    	
		    	obj = new Double(source.toString());
		        break;
		    case Types.DATE:		    	
		    	obj = parseUFDate(source);	// now use ufdate
		    	break;
		    case Types.TIME:
		    	obj = parseTime(source);
		    	break;
		    case Types.TIMESTAMP:
		        obj = parseDateTime(source);
		        break;
		    case Types.BLOB:
		    	obj = new BlobParamType((byte[]) obj );
		    	break;
		    case Types.CLOB:
		    	obj = new ClobParamType( obj.toString() );
		    	break;
		    default:
		    	break;
    	}
    	
    	return obj;
    }

	/**
	 * @param source
	 * @return
	 */
	public static UFDateTime parseDateTime(Object source) {
		if( source instanceof UFDateTime ) {
			return (UFDateTime) source;
		}
		
		return new UFDateTime(source.toString());
	}
}
