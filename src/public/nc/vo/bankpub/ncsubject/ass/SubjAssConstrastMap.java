package nc.vo.bankpub.ncsubject.ass;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.itf.bankpub.pub.BankPubItfProxy;
import nc.vo.pub.BusinessException;

public class SubjAssConstrastMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** pk_accsubj - > SubjAssConstrastVOs */
	private Map<String, SubjAssConstrastVO_OneSubj> map = new HashMap<String, SubjAssConstrastVO_OneSubj>();

	public void put(String pk_accsubj, SubjAssConstrastVO_OneSubj vo) {
		map.put(pk_accsubj, vo);
	}

	public SubjAssConstrastVO_OneSubj get(String pk_accsubj)
			throws BusinessException {

		SubjAssConstrastVO_OneSubj vo = map.get(pk_accsubj);
		if (vo == null) {
			SubjAssConstrastVO[] vos = BankPubItfProxy.getISubjItfProxy()
					.querySubjAssConstrastBy(pk_accsubj);
			vo = new SubjAssConstrastVO_OneSubj();
			vo.setPk_accsubj(pk_accsubj);
			if (vos != null) {
				vo.add(vos);
			}
			map.put(pk_accsubj, vo);
		}

		return vo;
	}

	public void remove(String pk_accsubj) {
		map.remove(pk_accsubj);
	}

}
