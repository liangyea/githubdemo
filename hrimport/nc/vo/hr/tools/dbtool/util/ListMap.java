package nc.vo.hr.tools.dbtool.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nc.vo.hr.tools.dbtool.ds.source.ISource;

/**
 * Describe:HR_PUB3.5
 * 		one like javascript/.net array.
 * 		if set the keyfld will auto buf to map, else can use add(key,obj) or list.
 * @version 1.0	2006-4-1316:57:38
 * @author 闫长海
 */
public class ListMap<E> extends ArrayList<E> implements ISource<E>{
	private static final long serialVersionUID = -6998722000696789282L;
	
	private Map<String,Integer> map;	// key, index	
	private String keyFld;
	
	private boolean isKeyIgnoreCase = true;	// will trans to up
	
	public ListMap() {
		super();		
	}
	
	public ListMap(String kdyFld) {
		super();
		setKeyFld(kdyFld);
	}

	public ListMap(int initialCapacity) {
		super(initialCapacity);
		
	}

	public Map<String,Integer> getMap() {
		if( map==null ) {
			map = new HashMap<String,Integer>();
		}
		return map;
	}
	
	public void setMap(Map<String,Integer> map) {
		this.map = map;
	}

	public void clear() {
		super.clear();
		getMap().clear();
	}

	public E remove(int index) {
		E obj= super.remove(index);
		removeBuf( index );
		return obj;
	}
	
	public void add(int index, E element) {
		super.add(index, element);		
		addBuf( index, element );
	}

	public boolean add(E obj) {
		boolean result = super.add(obj);
		addBuf(super.size()-1, obj);
		
		return result;
	}

	public boolean remove(Object o) {
		int index = super.indexOf(o);
		boolean result = super.remove(o);
		removeBuf( index );
		return result;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		if( c==null ) {
			return true;
		}
		boolean result  = super.addAll(index, c);
		addBuf( index, c.toArray() );
		return result;
	}

	public boolean addAll(Collection<? extends E> c) {
		if( c==null ) {
			return true;
		}
		int index = super.size()-1;
		boolean result  = super.addAll( c);
		addBuf( index, c.toArray() );
		return result;
	}

	public boolean removeAll(Collection c) {
		
		boolean modified = false;
		Iterator e = iterator();
		int index = 0;
		while (e.hasNext()) {
		    if(c.contains(e.next())) {
		    removeBuf( index );
			e.remove();
			modified = true;
		    }
		    index++;
		}
		return modified;
	}

	public E set(int index, E element) {
		E result = super.set(index, element);
		addBuf(index,element);
		return result;
	}

	public String getKeyFld() {
		return keyFld;
	}


	public void setKeyFld(String keyFld) {
		this.keyFld = keyFld;
	}
	
	protected void addBuf(int index, Object[] objs) {
		if( objs==null ) {
			return ;
		}
		for (int i = 0; i < objs.length; i++) {
			addBuf( index+i, objs[i] );
		}
	}
	
	protected void addBuf(int index, Object obj) {
		if( obj==null || getKeyFld()==null) {
			return;
		}
		if(index<0 ) {
			index = 0;
		}
		String key= null;
		try {
			 key = (String) BeanHelper.getProperty(obj, getKeyFld() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( key==null ) {
			return ;
		}
		if( isKeyIgnoreCase() ) {
			key = key.toUpperCase();
		}
		getMap().put(key, new Integer(index) );
	}
	
	protected void removeBuf(int index ) {
		if( index<0 ) {
			index = 0;
		}
		MapUtil.removeValue(getMap(), new Integer(index) );
	}
	
	/**
	 * in new add or exist will update.
	 * @param key
	 * @param obj
	 * @return
	 */
	public boolean addByKey(String key, E obj) {
		String innerKey = key;
		if( isKeyIgnoreCase() ) {
			innerKey = key.toUpperCase();
		}
		
		if( getMap().containsKey(innerKey) ) {
			Integer index = getMap().get(innerKey);
			super.set(index.intValue(), obj);
			return true;
		}
		boolean result = super.add(obj);
		getMap().put(innerKey, Integer.valueOf( size()-1 ) );
		return result;
	}
	
	/**
	 * get the obj by key.
	 * @param key
	 * @return
	 */
	public E getData( String key ) {
		String innerKey = key;
		if( isKeyIgnoreCase() ) {
			innerKey = key.toUpperCase();
		}
		Integer index = getMap().get(innerKey);
		if( index==null ) {
			return null;
		}
		return this.get(index.intValue());
	}
	
	/**
	 * get the key index.
	 * @param key
	 * @return
	 */
	public int getIndex(String key) {
		String innerKey = key;
		if( isKeyIgnoreCase() ) {
			innerKey = key.toUpperCase();
		}
		Integer index = getMap().get(innerKey);
		if( index==null ) {
			return -1;
		}
		return index.intValue();
	}
	
	/**
	 * remove the val by key.
	 * @param key
	 * @return
	 */
	public Object removeByKey(String key) {
		String innerKey = key;
		if( isKeyIgnoreCase() ) {
			innerKey = key.toUpperCase();
		}
		Integer index = getMap().get(innerKey);
		if( index==null ) {
			return null;
		}
		Object obj = this.remove(index.intValue());
		getMap().remove(key);
		return obj;
	}

	public void setData(String key, E val) {
		String innerKey = key;
		if( isKeyIgnoreCase() ) {
			innerKey = key.toUpperCase();
		}
		Integer index = getMap().get(innerKey);
		if( index==null ) {
			return;
		}
		this.set(index.intValue(), val);
	}
	/**
	 * @return 返回 isKeyIgnoreCase。
	 */
	public boolean isKeyIgnoreCase() {
		return isKeyIgnoreCase;
	}
	/**
	 * @param isKeyIgnoreCase 要设置的 isKeyIgnoreCase。
	 */
	public void setKeyIgnoreCase(boolean isKeyIgnoreCase) {
		this.isKeyIgnoreCase = isKeyIgnoreCase;
	}
}
