package nc.ui.bankpub.especialsubj;

import nc.itf.dap.priv.IInsubjclass;
import nc.vo.dap.subjclass.InsubjclassCopyVO;

public class InsubjclassBO_Client {

	private static String beanName = "nc.itf.dap.priv.IInsubjclass";

	public static void delete(nc.vo.dap.subjclass.InsubjclassVO[] arg0) throws Exception {

		IInsubjclass bo = (IInsubjclass) nc.bs.framework.common.NCLocator.getInstance().lookup(beanName);
		bo.deleteInsubjclass(arg0);

	}

	public static java.lang.String insert(nc.vo.dap.subjclass.InsubjclassVO arg0) throws Exception {

		IInsubjclass bo = (IInsubjclass) nc.bs.framework.common.NCLocator.getInstance().lookup(beanName);
		java.lang.String o = null;
		o = bo.insertInsubjclass(arg0);
		return o;

	}

	public static void update(nc.vo.dap.subjclass.InsubjclassVO arg0) throws Exception {

		IInsubjclass bo = (IInsubjclass) nc.bs.framework.common.NCLocator.getInstance().lookup(beanName);
		bo.updateInsubjclass(arg0);

	}

	public static nc.vo.dap.subjclass.InsubjclassVO findByPrimaryKey(java.lang.String arg0) throws Exception {

		IInsubjclass bo = (IInsubjclass) nc.bs.framework.common.NCLocator.getInstance().lookup(beanName);
		nc.vo.dap.subjclass.InsubjclassVO o = null;
		o = bo.findInsubjclassByPrimaryKey(arg0);
		return o;

	}

	public static java.lang.String[] insertArray(nc.vo.dap.subjclass.InsubjclassVO[] arg0) throws Exception {

		IInsubjclass bo = (IInsubjclass) nc.bs.framework.common.NCLocator.getInstance().lookup(beanName);
		java.lang.String[] o = null;
		o = bo.insertInsubjclassArray(arg0);
		return o;

	}

	public static nc.vo.dap.subjclass.InsubjclassVO[] queryAll(java.lang.String arg0) throws Exception {

		IInsubjclass bo = (IInsubjclass) nc.bs.framework.common.NCLocator.getInstance().lookup(beanName);
		nc.vo.dap.subjclass.InsubjclassVO[] o = null;
		o = bo.queryAllInsubjclass(arg0);
		return o;

	}

	public static nc.vo.dap.subjclass.InsubjclassVO[] queryByVO(nc.vo.dap.subjclass.InsubjclassVO arg0, java.lang.Boolean arg1) throws Exception {

		IInsubjclass bo = (IInsubjclass) nc.bs.framework.common.NCLocator.getInstance().lookup(beanName);
		nc.vo.dap.subjclass.InsubjclassVO[] o = null;
		o = bo.queryInsubjclassByVO(arg0, arg1);
		return o;

	}

	public static nc.vo.dap.accrule.OrgaccDefVO[] queryOrgaccDefByCorp(java.lang.String arg0) throws Exception {

		IInsubjclass bo = (IInsubjclass) nc.bs.framework.common.NCLocator.getInstance().lookup(beanName);
		nc.vo.dap.accrule.OrgaccDefVO[] o = null;
		o = bo.queryOrgaccDefByCorp(arg0);
		return o;

	}

	public static void deleteOrgaccDef(nc.vo.dap.accrule.OrgaccDefVO arg0) throws Exception {

		IInsubjclass bo = (IInsubjclass) nc.bs.framework.common.NCLocator.getInstance().lookup(beanName);
		bo.deleteOrgaccDef(arg0);

	}

	public static java.lang.String updateOrgaccDef(nc.vo.dap.accrule.OrgaccDefVO arg0) throws Exception {

		IInsubjclass bo = (IInsubjclass) nc.bs.framework.common.NCLocator.getInstance().lookup(beanName);
		java.lang.String o = null;
		o = bo.updateOrgaccDef(arg0);
		return o;

	}

	public static void copyForOrgbook(InsubjclassCopyVO copy) throws Exception {
		IInsubjclass bo = (IInsubjclass) nc.bs.framework.common.NCLocator.getInstance().lookup(beanName);
		java.lang.String o = null;
		bo.copyForOrgbook(copy);

	}


}
