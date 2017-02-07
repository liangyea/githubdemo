package nc.vo.bankpub.gl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nc.vo.bankpub.util.CompareUtil;
import nc.vo.glcom.ass.AssVO;

public class AssVOs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<AssVO> assVos = new ArrayList<AssVO>();

	public void add(AssVO assVo) {
		assVos.add(assVo);
	}

	public void add(AssVO[] assVos) {
		for (int i = 0; i < assVos.length; i++) {
			this.assVos.add(assVos[i]);
		}
		
	}
	
	public int size() {
		return assVos.size();
	}

	public AssVO get(int i) {
		return assVos.get(i);
	}

	public void remove(int i) {
		assVos.remove(i);
	}

	public AssVO findBy(String pk_asstype, String pk_assvalue) {
		int i = indexBy(pk_asstype, pk_assvalue);
		
		if (i >= 0) {
			return get(i);
		}
		return null;
	}
	
	public int indexBy(String pk_asstype, String pk_assvalue) {
		for (int i = 0; i < assVos.size(); i++) {
			AssVO assVo = get(i);
			if (CompareUtil.equals(pk_asstype, assVo.getPk_Checktype())
					&& CompareUtil
							.equals(pk_assvalue, assVo.getPk_Checkvalue()))
				return i;
		}
		return -1;
	}
	
	
	public AssVO findBy(String asstypename) {
		int i = indexBy(asstypename);
		
		if (i >= 0) {
			return get(i);
		}
		return null;
	}
	
	public int indexBy(String asstypename) {
		for (int i = 0; i < assVos.size(); i++) {
			AssVO assVo = get(i);
			if (CompareUtil.equals(asstypename, assVo.getChecktypename()))
				return i;
		}
		return -1;
	}
	
	public AssVO[] toArray() {
		return assVos.toArray(new AssVO[0]);
	}
}
