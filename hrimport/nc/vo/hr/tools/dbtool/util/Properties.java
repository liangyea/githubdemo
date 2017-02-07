/*
 * Created on 2005-7-5
 * 
 */
package nc.vo.hr.tools.dbtool.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe:
 * 		string->Object properties
 * @version 1.0	2005-7-5 13:43:44
 * @author „∆≥§∫£
 */
public class Properties implements Serializable {
	private static final long serialVersionUID = 5718027141839747523L;

	private Map<String,Object> peopertiesMap = null;
    // PropertyChangeListeners are not serialized.
    private transient PropertyChangeSupport listeners =
      new PropertyChangeSupport(this);
    
    /* (non-Javadoc)
     * @see nc.ui.print.util.IProperties#getPropertyCount()
     */
    public int getPropertyCount() {
        return getPeopertiesMap().size();
    }

    /* (non-Javadoc)
     * @see nc.ui.print.util.IProperties#getProperty(java.lang.String)
     */
    public Object getProperty(String key) {
        return getPeopertiesMap().get(key);
    }
    
    public int getPropertyInt(String key, int defaultVal) {
        Integer val = (Integer) getPeopertiesMap().get(key);
        if( val!=null ) {
        	return val.intValue();
        }
        return defaultVal;
    }

    /* (non-Javadoc)
     * @see nc.ui.print.util.IProperties#setProperty(java.lang.String, java.lang.Object)
     */
    public void setProperty(String key, Object value) {
        Object old = getProperty(key);
        getPeopertiesMap().put(key,value);
        
        firePropertyChange(key,old, value);
    }

    /**
     * @return Returns the peoperties.
     */
    public Map<String,Object> getPeopertiesMap() {
        if( peopertiesMap == null ) {
            peopertiesMap = new HashMap<String,Object>();
        }
        
        return peopertiesMap;
    }
    
    /**
     * @param peoperties The peoperties to set.
     */
    public void setPeopertiesMap(Map<String,Object> peoperties) {
        this.peopertiesMap = peoperties;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
      listeners.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
      listeners.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
      listeners.firePropertyChange(propertyName, oldValue, newValue);
    }

}
