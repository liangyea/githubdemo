package nc.impl.bankpub.pub;

import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.ISubjBalRuleSet;
import nc.jdbc.framework.PersistenceManager;
import nc.vo.bankpub.cache.BDAccessor;
import nc.vo.bankpub.subjbalrule.SubjBalRuleFactory;
import nc.vo.bankpub.subjbalrule.SubjBalRuleVO;
import nc.vo.bankpub.subjcontrast.BankSubjContrastVO;
import nc.vo.bd.access.BddataVO;
import nc.vo.bd.access.IBDAccessor;
import nc.vo.bd.access.IBdinfoConst;
import nc.vo.pub.BusinessException;
import nc.vo.sm.nodepower.OrgnizeTypeVO;

public class ImplSubjBalRuleSet implements ISubjBalRuleSet {

	public SubjBalRuleVO[] importRules(String pk_glorgbook)
			throws BusinessException {
		// TODO Auto-generated method stub

		try {
			ImplBankSubjContrast bankSubjConstrastAccess = new ImplBankSubjContrast();
			BankSubjContrastVO[] constrastVos = bankSubjConstrastAccess
					.queryBankSubjContrastVOByPkglorgbook(pk_glorgbook);
			if (constrastVos == null) return null;
			// 替换nc会计科目主键为会计科目编码
			replaceNCAccsubjPks(constrastVos, pk_glorgbook);
			
			SubjBalRuleVO[] ruleVos = SubjBalRuleFactory.createSubjBalRules(constrastVos);
			
			String[] keys = PersistenceManager.getInstance().insert(ruleVos);
			
			for (int i = 0; i < ruleVos.length; i++) {
				ruleVos[i].setPrimaryKey(keys[i]);
			}
			
			return ruleVos;

		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
			
			throw new BusinessException(e.getMessage(), e);
		}

//		return null;
	}

	private void replaceNCAccsubjPks(BankSubjContrastVO[] constrastVos,
			String pk_glorgbook) throws BusinessException {
		if (constrastVos == null) return;
		// 先替换NC会计科目主键为会计科目编码
		IBDAccessor bdAccess = BDAccessor.getBDAccessor(IBdinfoConst.ACCSUBJ,
				OrgnizeTypeVO.ZHUZHANG_TYPE, pk_glorgbook);
		for (int i = 0; i < constrastVos.length; i++) {
			BddataVO dataVo = bdAccess.getDocByPk(constrastVos[i]
					.getPk_accsubj());
			if (dataVo == null)
				throw new BusinessException("核心科目[" + constrastVos[i].getDef2()
						+ "]所对应的NC会计科目已经被删除，请重新设置核心传NC科目对照表！");
			constrastVos[i].setPk_accsubj(dataVo.getCode());

		}
	}
}
