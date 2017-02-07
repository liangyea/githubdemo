package nc.vo.bankpub.subjbalrule;

import java.util.Comparator;

class RuleComparator implements Comparator<SubjBalRuleVO> {

//	public int compare(Object o1, Object o2) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	public int compare(SubjBalRuleVO o1, SubjBalRuleVO o2) {
		// TODO Auto-generated method stub
		if (o1 == o2) return 0;
		if (o1 == null) return -1;
		if (o2 == null) return 1;
		
		if (o1.getNc_subjcodes() == o2.getNc_subjcodes()) return 0;
		
		if (o1.getNc_subjcodes() == null) return -1;
		
		return o1.getNc_subjcodes().compareTo(o2.getNc_subjcodes());
	}
	
}