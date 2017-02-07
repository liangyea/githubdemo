package nc.vo.bankpub.subjbalrule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.vo.bankpub.subjcontrast.BankSubjContrastVO;
import nc.vo.bankpub.util.ArrayUtil;
import nc.vo.bankpub.util.VOUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.trade.pub.HYBillVO;

public class SubjBalRuleFactory {

	private static void analyze(
			String ncSubjCode,
			Map<String, ArrayList<CircularlyAccessibleValueObject>> ncsubjMap,
			Map<String, ArrayList<CircularlyAccessibleValueObject>> banksubjMap,
			Map<String, String> ncSubjCodesMap,
			Map<String, String> bankSubjCodesMap) {

		// 如果已经处理过，则不再重复处理
		if (ncSubjCodesMap.containsKey(ncSubjCode))
			return;

		ncSubjCodesMap.put(ncSubjCode, "");

		ArrayList lstNcsubj = ncsubjMap.get(ncSubjCode);
		BankSubjContrastVO[] ncConstrastVos = (BankSubjContrastVO[]) lstNcsubj
				.toArray(new BankSubjContrastVO[0]);
		List<String> tmpbankSubjCodes = VOUtil.getLstAttributeValues(
				ncConstrastVos, "def1", true, true);

		// bankSubjCodesMap.

		// 由核心科目列表反向追溯nc科目列表
		for (int i = 0; i < tmpbankSubjCodes.size(); i++) {
			String bankSubjCode = tmpbankSubjCodes.get(i);
			if (bankSubjCodesMap.containsKey(bankSubjCode))
				continue;

			bankSubjCodesMap.put(bankSubjCode, "");
			ArrayList lstBanksubj = banksubjMap.get(bankSubjCode);
			BankSubjContrastVO[] bankConstrastVos = (BankSubjContrastVO[]) lstBanksubj
					.toArray(new BankSubjContrastVO[0]);
			List<String> tmpncSubjCodes = VOUtil.getLstAttributeValues(
					bankConstrastVos, "pk_accsubj", true, true);
			// 再由nc科目列表追溯核心科目列表
			for (int ii = 0; ii < tmpncSubjCodes.size(); ii++) {
				ncSubjCode = tmpncSubjCodes.get(ii);
				if (ncSubjCodesMap.containsKey(ncSubjCode)) {
					continue;
				}
				// 递归
				analyze(ncSubjCode, ncsubjMap, banksubjMap, ncSubjCodesMap,
						bankSubjCodesMap);
			}
		}

	}

	public static SubjBalRuleVO[] createSubjBalRules(
			BankSubjContrastVO[] constrastVos) {
		if (constrastVos == null || constrastVos.length == 0)
			return null;

		// 首先，按NC会计科目分组
		Map<String, ArrayList<CircularlyAccessibleValueObject>> ncsubjMap = VOUtil
				.groupBy(constrastVos, new String[] { "pk_accsubj" });
		// 按核心会计科目分组
		// 核心会计科目存储在def2字段
		Map<String, ArrayList<CircularlyAccessibleValueObject>> banksubjMap = VOUtil
				.groupBy(constrastVos, new String[] { "def1" });

		Iterator<String> keyIte = ncsubjMap.keySet().iterator();

		Map<String, String> hasProsessedNcCodeMap = new HashMap<String, String>();

		ArrayList<SubjBalRuleVO> resultLst = new ArrayList<SubjBalRuleVO>();

		while (keyIte.hasNext()) {
			String ncSubjCode = keyIte.next();
			// 已经处理过
			if (hasProsessedNcCodeMap.containsKey(ncSubjCode))
				continue;

			Map<String, String> ncSubjCodesMap = new HashMap<String, String>();
			Map<String, String> bankSubjCodesMap = new HashMap<String, String>();
			// 递归解析
			analyze(ncSubjCode, ncsubjMap, banksubjMap, ncSubjCodesMap,
					bankSubjCodesMap);

			// 排序
			String[] ncSubjCodes = ncSubjCodesMap.keySet().toArray(
					new String[0]);
			Arrays.sort(ncSubjCodes);
			String[] bankSubjCodes = bankSubjCodesMap.keySet().toArray(
					new String[0]);
			Arrays.sort(bankSubjCodes);

			SubjBalRuleVO ruleVo = new SubjBalRuleVO();
			ruleVo.setNc_subjcodes(ArrayUtil.toString(ncSubjCodes, "+"));
			ruleVo.setBank_subjcodes(ArrayUtil.toString(bankSubjCodes, "+"));
			resultLst.add(ruleVo);
			
			// 记录已经处理过的
			for (int i = 0; i < ncSubjCodes.length; i++) {
				hasProsessedNcCodeMap.put(ncSubjCodes[i], "");
			}
		}

		SubjBalRuleVO[] ruleVos = resultLst.toArray(new SubjBalRuleVO[0]);
		Arrays.sort(ruleVos, new RuleComparator());

		return ruleVos;
	}
	
	
	public static HYBillVO[] createBillVos(SubjBalRuleVO[] ruleVos) {
		if (ruleVos == null) return null;
		HYBillVO[] billVos = new HYBillVO[ruleVos.length];
		for (int i = 0; i < billVos.length; i++) {
			billVos[i] = new HYBillVO();
			billVos[i].setParentVO(ruleVos[i]);
			billVos[i].setChildrenVO(null);
		}
		return billVos;
	}
	
	
}
