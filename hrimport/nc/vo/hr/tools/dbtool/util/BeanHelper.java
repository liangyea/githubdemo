/*
 * 创建日期 2006-5-18
 */
package nc.vo.hr.tools.dbtool.util;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Collections;
/**
 * Describe:HR_PUB3.5
 * 		temp for the ui beanutil.
 * @version 1.0	2006-5-18 11:12:07
 * @author ych
 */
public class BeanHelper {
	   protected static final Object[] NULL_ARGUMENTS = {};

	    private static Map<MethodDescriptor,Method> cache = Collections.synchronizedMap(new WeakHashMap<MethodDescriptor,Method>(100,
	            100));

	    protected static PropertyDescriptor getPropertyDescriptor(Object bean,
	            String propertyName) throws Exception {
	        try {
	            Class beanClass = bean.getClass();
	            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
	            PropertyDescriptor[] descriptors = beanInfo
	                    .getPropertyDescriptors();
	            if (descriptors != null) {
	                int size = descriptors.length;
	                for (int i = 0; i < size; i++) {
	                    PropertyDescriptor descriptor = descriptors[i];
	                    String name = descriptor.getName();
	                    if (propertyName.equals(name)) {
	                        return descriptor;
	                    }
	                }
	            }
	            throw new Exception("No such property: " + propertyName
	                    + " on bean: " + bean);
	        } catch (IntrospectionException e) {
	            throw new Exception("Failed to instrospect bean: " + bean
	                    + ". Exception: " + e);
	        }
	    }

	    public static List getPropertys(Object bean)     {
	        List<String> props=new ArrayList<String>();
	        try {
	            Class beanClass = bean.getClass();
	            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
	            PropertyDescriptor[] descriptors = beanInfo
	                    .getPropertyDescriptors();

	            if (descriptors != null) {
	             
	                int size = descriptors.length;
	                for (int i = 0; i < size; i++) {
	                    if (!"class".equals(descriptors[i].getName()))
	                        props.add(descriptors[i].getName());
	                }
	            }

	        } catch (IntrospectionException e) {
	            System.out.println("Failed to instrospect bean: " + bean
	                    + ". Exception: " + e);
	        }
	        return props;

	    }

	    public static Object getProperty(Object bean, String propertyName) {

	        try {
	            MethodDescriptor key = new MethodDescriptor("get" + propertyName,
	                    bean.getClass());
	            Method method = cache.get(key);
	            if (method == null) {
	                PropertyDescriptor descriptor = getPropertyDescriptor(bean,
	                        propertyName);
	                method = descriptor.getReadMethod();
	                cache.put(key, method);
	            }

	            return method.invoke(bean, NULL_ARGUMENTS);
	        } catch (Exception e) {
	            System.out.println("Failed to get property: " + propertyName
	                    + " on bean: " + bean + ". Exception: " + e);
	        }
	        return null;
	    }

	    public static void setProperty(Object bean, String propertyName,
	            Object value) {
	    	
	        try {
	            MethodDescriptor key = new MethodDescriptor("set" + propertyName,
	                    bean.getClass());
	            Method method = cache.get(key);
	            if (method == null) {
	                PropertyDescriptor descriptor = getPropertyDescriptor(bean,
	                        propertyName);
	                //    type = descriptor.getPropertyType();
	                method = descriptor.getWriteMethod();
	                cache.put(key, method);
	            }
	            //Class[] classes = method.getParameterTypes();

//	            value = BeanConvertor.convert(value, classes[0]);
	          //  System.out.println("value--"+value+"value type"+value.getClass()+"method name"+method.getName()+"method set type"+classes[0]);
	            Object[] arguments = { value };
	            method.invoke(bean, arguments);
	        } catch (Exception e) {

	            System.out.println("Failed to set property: " + propertyName
	                    + " on bean: " + bean.getClass().getName() +" with value:"+value);

	        }
	    }

	    static class MethodDescriptor {
	        Class thdClass;

	        String propertyName;

	        public MethodDescriptor(String propertyName, Class thdClass) {
	            this.propertyName = propertyName;
	            this.thdClass = thdClass;
	        }

	        public String getPropertyName() {
	            return propertyName;
	        }

	        public void setPropertyName(String propertyName) {
	            this.propertyName = propertyName;
	        }

	        public Class getThdClass() {
	            return thdClass;
	        }

	        public void setThdClass(Class thdClass) {
	            this.thdClass = thdClass;
	        }

	        public boolean equals(Object o) {
	            if (this == o)
	                return true;
	            if (o == null || getClass() != o.getClass())
	                return false;

	            final MethodDescriptor that = (MethodDescriptor) o;

	            if (propertyName != null ? !propertyName.equals(that.propertyName)
	                    : that.propertyName != null)
	                return false;
	            return !(thdClass != null ? !thdClass.equals(that.thdClass)
	                    : that.thdClass != null);

	        }

	        public int hashCode() {
	            int result;
	            result = (thdClass != null ? thdClass.hashCode() : 0);
	            result = 29 * result
	                    + (propertyName != null ? propertyName.hashCode() : 0);
	            return result;
	        }

	    }
	
}
