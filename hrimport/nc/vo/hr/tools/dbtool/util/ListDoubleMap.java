/*
 * 创建日期 2006-4-27
 */
package nc.vo.hr.tools.dbtool.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nc.vo.hr.tools.dbtool.ds.source.ISource;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-27 17:34:31
 * @author ych
 */
public class ListDoubleMap<E> extends ArrayList<E> implements ISource<E>{

		
		private Map<String,Integer> map;	// key, index	
		private Map<String,Integer> map2;	// key, index	
		private String keyFld;
		private String keyFld2;
		
		private boolean isKeyIgnoreCase = true;	// will trans to up
		
		public ListDoubleMap() {
			super();		
		}
		
		public ListDoubleMap(String keyFld, String keyFld2 ) {
			super();
			setKeyFld(keyFld);
			setKeyFld2( keyFld2 );
		}

		public ListDoubleMap(int initialCapacity) {
			super(initialCapacity);			
		}

		public Map<String,Integer> getMap() {
			if( map==null ) {
				map = new HashMap<String,Integer>();
			}
			return map;
		}
		
		/**
		 * @return 返回 map2。
		 */
		public Map<String,Integer> getMap2() {
			if( map2==null ) {
				map2 = new HashMap<String,Integer>();
			}

			return map2;
		}
		
		public void setMap(Map<String,Integer> map) {
			this.map = map;
		}

		public void clear() {
			super.clear();
			getMap().clear();
			getMap2().clear();
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
			String key2= null;
			try {
				 key =  (String) BeanHelper.getProperty(obj, getKeyFld() );
				 key2 =  (String) BeanHelper.getProperty(obj, getKeyFld2() );
			} catch (Exception e) {
				e.printStackTrace();
			}
			if( key==null ) {
				return ;
			}
			if( isKeyIgnoreCase() ) {
				key = key.toUpperCase();
				key2 = key2.toUpperCase();
			}
			getMap().put(key, Integer.valueOf(index) );
			getMap2().put(key2, Integer.valueOf(index) );
		}
		
		protected void removeBuf(int index ) {
			if( index<0 ) {
				index = 0;
			}
			MapUtil.removeValue(getMap(), Integer.valueOf(index) );
			MapUtil.removeValue(getMap2(), Integer.valueOf(index) );
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
			
			String key2= null;
			try {
				 key2 =  (String) BeanHelper.getProperty(obj, getKeyFld2() );
			} catch (Exception e) {
				e.printStackTrace();
			}
			if( key2==null ) {
				return result;
			}
			if( isKeyIgnoreCase() ) {
				key2 = key2.toUpperCase();
			}
			getMap().put(key2, Integer.valueOf( size()-1 ) );
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
		
		public Object getDataByKey2( String key2 ) {
			String innerKey = key2;
			if( isKeyIgnoreCase() ) {
				innerKey = key2.toUpperCase();
			}
			Integer index = getMap2().get(innerKey);
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
		
		public int getIndex2(String key2) {
			String innerKey = key2;
			if( isKeyIgnoreCase() ) {
				innerKey = key2.toUpperCase();
			}
			Integer index = getMap2().get(innerKey);
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
			
			String key2= null;
			try {
				 key2 = (String)BeanHelper.getProperty(obj, getKeyFld2() );
			} catch (Exception e) {
				e.printStackTrace();
			}
			if( key2==null ) {
				return obj;
			}
			if( isKeyIgnoreCase() ) {
				key2 = key2.toUpperCase();
			}
			getMap().remove( key2 );
			
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
			/**
		 * @return 返回 keyFld2。
		 */
		public String getKeyFld2() {
			return keyFld2;
		}
		/**
		 * @param keyFld2 要设置的 keyFld2。
		 */
		public void setKeyFld2(String keyFld2) {
			this.keyFld2 = keyFld2;
		}

}

