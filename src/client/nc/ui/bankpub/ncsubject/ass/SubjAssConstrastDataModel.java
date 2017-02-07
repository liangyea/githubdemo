package nc.ui.bankpub.ncsubject.ass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import nc.vo.bankpub.gl.AssVOs;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastFactory;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO;
import nc.vo.pub.BusinessException;

public class SubjAssConstrastDataModel implements Serializable {
	
	

	/**
	 * 应该采用观察者模式，目前先这样 
	 */
	private static final long serialVersionUID = 1L;
	
	private AssVOs assVos;
	private String pk_accsubj;
	private List<SubjAssConstrastVO> subjAssConstrasts = new ArrayList<SubjAssConstrastVO>();
	
	/** 编辑后再取消，用这个恢复 */
	private List<SubjAssConstrastVO> subjAssConstrastsBackup = new ArrayList<SubjAssConstrastVO>();

	
	public void addAssConstrast(SubjAssConstrastVO vo) {
		subjAssConstrasts.add(vo);
	}

	public void addDefaultAssConstrast() {
		SubjAssConstrastVO vo = SubjAssConstrastFactory.createDefaultSubjAssConstrast(assVos);
		vo.setPk_accsubj(pk_accsubj);
		subjAssConstrasts.add(vo);
	}
	
	public void addAssConstrast(SubjAssConstrastVO[] vos) {
		for (int i = 0; i < vos.length; i++) {
			this.subjAssConstrasts.add(vos[i]);
		}
		
	}
	
	public void insertAssConstrast(SubjAssConstrastVO vo, int index) {
		subjAssConstrasts.add(index, vo);
		
	}
	
	public void insertDefaultAssConstrast(int index) {
		SubjAssConstrastVO vo = SubjAssConstrastFactory.createDefaultSubjAssConstrast(assVos);
		vo.setPk_accsubj(pk_accsubj);
		subjAssConstrasts.add(index, vo);
		
	}
	
	public int sizeAssConstrast() {
		return subjAssConstrasts.size();
	}

	public SubjAssConstrastVO getAssConstrast(int i) {
		return subjAssConstrasts.get(i);
	}

	public void removeAssConstrast(int i) {
		subjAssConstrasts.remove(i);
	}
	
	public int indexAssConstrast(SubjAssConstrastVO vo) {
		return subjAssConstrasts.indexOf(vo);		
	}
	
	public void remove(SubjAssConstrastVO vo) {
		subjAssConstrasts.remove(vo);
	}
	
	public void clear() {
		subjAssConstrasts.clear();
	}
	
	public SubjAssConstrastVO[] toArray() {
		return subjAssConstrasts.toArray(new SubjAssConstrastVO[0]);
	}

	public AssVOs getAssVos() {
		return assVos;
	}

	public void setAssVos(AssVOs assVos) {
		this.assVos = assVos;
	}

	public String getPk_accsubj() {
		return pk_accsubj;
	}

	public void setPk_accsubj(String pk_accsubj) {
		this.pk_accsubj = pk_accsubj;
	}
	
	public void backupSubjAssConstrasts() {
		subjAssConstrastsBackup.clear();
		SubjAssConstrastVO[] vos = toArray();
		SubjAssConstrastVO[] backupVos = null;
		if (vos != null) {
			backupVos = new SubjAssConstrastVO[vos.length];
			for (int i = 0; i < vos.length; i++) {
				backupVos[i] = (SubjAssConstrastVO) vos[i].clone();
				subjAssConstrastsBackup.add(backupVos[i]);
			}
		}
	}
	
	public void restoreSubjAssConstrasts() {
		subjAssConstrasts.clear();
		SubjAssConstrastVO[] vos = toArray();
		
		if (vos != null) {
			
			for (int i = 0; i < subjAssConstrastsBackup.size(); i++) {
				
				subjAssConstrasts.add(subjAssConstrastsBackup.get(i));
			}
		}
	}
	
	public void check() throws BusinessException {
		SubjAssConstrastVO[] vos = toArray();
		StringBuffer messageBuf = new StringBuffer();
		HashMap<String, ArrayList<Integer>> duplicateRecordMap = new HashMap<String, ArrayList<Integer>>();
		for (int i = 0; i < vos.length; i++) {
			if (vos[i].getBank_zh() == null
					|| vos[i].getBank_zh().trim().length() == 0) {
				messageBuf.append("第 [" + (i + 1) + "] 行的核心账户为空，不能保存！\n");
			}
			if (!vos[i].hasAssValue()) {
				messageBuf.append("第 [" + (i + 1) + "] 行的辅助核算项为空，不能保存！\n");
			}
			String voStr = vos[i].toString_value();
			ArrayList<Integer> lst = duplicateRecordMap.get(voStr);
			if (lst == null) {
				lst = new ArrayList<Integer>();
				duplicateRecordMap.put(voStr, lst);
			}
			lst.add(new Integer(i + 1));
		}

		Iterator<String> voStrIte = duplicateRecordMap.keySet().iterator();
		while (voStrIte.hasNext()) {
			ArrayList<Integer> lst = duplicateRecordMap.get(voStrIte.next());
			if (lst.size() > 1) {
				messageBuf.append("第 [" + lst.toString() + "] 重复，不能保存！\n");
			}
		}

		if (messageBuf.length() > 0) {
			throw new BusinessException(messageBuf.toString());
		}
		
	}
}
