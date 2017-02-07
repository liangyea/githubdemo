package nc.vo.bankpub.util;

import java.util.List;

import nc.bs.logging.Logger;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValueObject;

public class PersistenceUtil {
	public static void splitInsertOrUpdates(ValueObject[] vos,
			List insertList, List updateList) {
		if (vos == null) return;
		try {
		for (int i = 0; i < vos.length; i++) {
			if (StringUtils.isNull(vos[i].getPrimaryKey())) {
			    insertList.add(vos[i]);	
			}
			else {
				updateList.add(vos[i]);
			}
		}
		} catch (BusinessException e) {
			Logger.error(e.getMessage(), e);			
		}
	}
}
