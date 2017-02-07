package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;

import nc.vo.hr.tools.dbtool.util.ListMap;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1413:32:49
 * @author „∆≥§∫£
 */
public class ConstraintList implements Serializable{
	private static final long serialVersionUID = 5596291553349415754L;
	// ListMap
	
	private ListMap<IConstraint> list;

	public ListMap<IConstraint> getList() {
		if (list == null) {
			list = new ListMap<IConstraint>();
		}
		return list;
	}
	
	public int size() {
		return getList().size();
	}
	
	public IConstraint getConstraint(int index) {
		return getList().get(index);
	}
	
	public void addConstraint(IConstraint constraint) {
		getList().add(constraint);
	}
	
	public IConstraint removeConstraint(int index ) {
		return getList().remove(index);
	}
	
}
