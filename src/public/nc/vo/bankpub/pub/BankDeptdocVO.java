package nc.vo.bankpub.pub;

import nc.vo.bd.b04.DeptdocVO;

public class BankDeptdocVO extends DeptdocVO {
	public String toString(){
		return this.getDeptname();
	}

}
