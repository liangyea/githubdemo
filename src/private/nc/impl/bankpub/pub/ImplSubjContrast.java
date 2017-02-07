package nc.impl.bankpub.pub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import nc.bs.bd.CorpDMO;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.ISubjContrast;
import nc.itf.doc.api.Accsubjdoc;
import nc.jdbc.framework.DataSourceCenter;
import nc.jdbc.framework.PersistenceManager;
import nc.vo.bankpub.cache.BDAccessor;
import nc.vo.bankpub.cache.NcSubjContrastCache;
import nc.vo.bankpub.cache.SubjAssConstrastCache;
import nc.vo.bankpub.ncsubject.ass.SimplSubjAssConstrastVO;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastFactory;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastHelper;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO;
import nc.vo.bankpub.subjcontrast.NCSubjContrastVO;
import nc.vo.bankpub.util.PersistenceUtil;
import nc.vo.bd.CorpVO;
import nc.vo.bd.access.BddataVO;
import nc.vo.bd.access.BdinfoVO;
import nc.vo.bd.b02.AccsubjGLVO;
import nc.vo.gl.glreportcache.AccsubjDataCache;
import nc.vo.gl.glreportcache.NCUFOCatchContainer;
import nc.vo.pub.BusinessException;
import nc.vo.sm.nodepower.OrgnizeTypeVO;

public class ImplSubjContrast implements ISubjContrast {
	public void clearSubjcontrast(String corp) throws BusinessException {
		// TODO 自动生成方法存根
		try {
			SubjectDMO dmo = new SubjectDMO();
			dmo.clearSubjcontrast(corp);
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}
	}

	public void copySubjcontrast(String standard, String[] target)
			throws BusinessException {
		// TODO 自动生成方法存根
		try {

			if (target == null || target.length == 0)
				return;
			// 通过接口
			Accsubjdoc itf = (Accsubjdoc) NCLocator.getInstance().lookup(
					Accsubjdoc.class.getName());
			nc.vo.bd.b02.AccsubjVO accst[] = itf.queryAccsubjVOs(standard,
					null, false);
			if (accst == null || accst.length == 0)
				return;
			NCSubjContrastVO st[] = querySubjContrast(standard);
			nc.vo.bd.b02.AccsubjVO vostarnd[] = itf.queryAccsubjVOs(standard,
					null, false);
			if (vostarnd == null || vostarnd.length == 0)
				throw new Exception("基准公司科目档案为空！");
			// clear & init
			java.util.Hashtable htNctobank = new java.util.Hashtable();
			java.util.Hashtable htStandsubj = new java.util.Hashtable();
			java.util.Hashtable htBalbankcode = new java.util.Hashtable();
			java.util.Hashtable htisTrans = new java.util.Hashtable();
			java.util.Hashtable htisAount = new java.util.Hashtable();

			for (int i = 0; i < vostarnd.length; i++) {
				htStandsubj.put(vostarnd[i].getPk_accsubj(), vostarnd[i]
						.getSubjcode());
			}
			for (int i = 0; i < st.length; i++) {
				// key-nc科目编码；value－大机科目编码
				htNctobank.put(htStandsubj.get(st[i].getPk_accsubj()), st[i]
						.getBank_kmbm());
				htBalbankcode.put(htStandsubj.get(st[i].getPk_accsubj()), st[i]
						.getBalbank_kmbm().trim());
				htisTrans.put(htStandsubj.get(st[i].getPk_accsubj()), st[i]
						.getIstrans().trim());
				htisAount.put(htStandsubj.get(st[i].getPk_accsubj()), st[i]
						.getIsAccount().trim());

			}
			// clear
			for (int i = 0; i < target.length; i++) {
				clearSubjcontrast(target[i]);
				// initSubjContrast(target[i]);
			}
			//
			nc.vo.bd.b02.AccsubjVO subjtar[] = null;
			SubjectDMO dmo = new SubjectDMO();
			for (int i = 0; i < target.length; i++) {
				// 目标公司科目档案
				subjtar = itf.queryAccsubjVOs(target[i], null, false);
				if (subjtar == null || subjtar.length == 0)
					continue;
				java.util.Hashtable htsubjtar = new java.util.Hashtable();
				for (int j = 0; j < subjtar.length; j++) {
					htsubjtar.put(subjtar[j].getSubjcode(), subjtar[j]
							.getPk_accsubj());
				}

				NCSubjContrastVO tarst[] = new NCSubjContrastVO[subjtar.length];
				for (int j = 0; j < tarst.length; j++) {
					tarst[j] = new NCSubjContrastVO();
					tarst[j].setPk_glorgbook(target[i]);
					;
					tarst[j].setPk_accsubj(subjtar[j].getPk_accsubj());
					if (htNctobank.containsKey(subjtar[j].getSubjcode())) {
						tarst[j].nckmbm = subjtar[j].getSubjcode();
						tarst[j].setBank_kmbm(htNctobank.get(
								subjtar[j].getSubjcode()).toString());
						tarst[j].setBalbank_kmbm(htBalbankcode.get(
								subjtar[j].getSubjcode()).toString());
						tarst[j].setIstrans(htisTrans.get(
								subjtar[j].getSubjcode()).toString());
						tarst[j].setIsAccount(htisAount.get(
								subjtar[j].getSubjcode()).toString());

					} else {
						tarst[j].setBank_kmbm("#");
						tarst[j].setBank_kmbm("#");
						tarst[j].setBalbank_kmbm("#");
						tarst[j].setIstrans("N");
						tarst[j].setIsAccount("N");

					}

				}
				dmo.insertSubjcontrast(tarst);
				NcSubjContrastCache. getInstance().updateVersion();
			}

		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}
	}

	public void copySubjcontrastFz(String standard, String[] target)
			throws BusinessException {
		// TODO 自动生成方法存根
		try {
			if (target == null || target.length == 0)
				return;
			nc.bs.bd.b02.AccsubjBO bo = new nc.bs.bd.b02.AccsubjBO();

			nc.vo.bd.b02.AccsubjVO accst[] = bo.queryAll(standard);
			if (accst == null || accst.length == 0)
				return;

			NCSubjContrastVO st[] = querySubjContrast(standard);
			nc.vo.bd.b02.AccsubjVO vostarnd[] = bo.queryAll(standard);
			if (vostarnd == null || vostarnd.length == 0)
				throw new Exception("基准公司科目档案为空！");
			// clear & init
			java.util.Hashtable htNctobank = new java.util.Hashtable();
			java.util.Hashtable htStandsubj = new java.util.Hashtable();
			for (int i = 0; i < vostarnd.length; i++) {
				htStandsubj.put(vostarnd[i].getPk_accsubj(), vostarnd[i]
						.getSubjcode());
			}
			for (int i = 0; i < st.length; i++) {
				// key-nc科目编码；value－大机科目编码
				htNctobank.put(htStandsubj.get(st[i].getPk_accsubj()), st[i]
						.getBank_kmbm());
			}
			// clear
			for (int i = 0; i < target.length; i++) {
				clearSubjcontrast(target[i]);
				// initSubjContrast(target[i]);
			}
			//
			nc.vo.bd.b02.AccsubjVO subjtar[] = null;
			SubjectDMO dmo = new SubjectDMO();

			CorpVO[] corps = (new CorpDMO()).queryAll(null);
			HashMap hmCorps = new HashMap();
			for (int i = 0; i < corps.length; i++) {
				hmCorps.put(corps[i].getPk_corp(), corps[i].getUnitcode());
			}

			for (int i = 0; i < target.length; i++) {
				// 目标公司科目档案
				subjtar = bo.queryAll(target[i]);
				if (subjtar == null || subjtar.length == 0)
					continue;
				java.util.Hashtable htsubjtar = new java.util.Hashtable();
				for (int j = 0; j < subjtar.length; j++) {
					htsubjtar.put(subjtar[j].getSubjcode(), subjtar[j]
							.getPk_accsubj());
				}

				NCSubjContrastVO tarst[] = new NCSubjContrastVO[subjtar.length];
				for (int j = 0; j < tarst.length; j++) {
					tarst[j] = new NCSubjContrastVO();
					tarst[j].setPk_glorgbook(target[i]);
					;
					tarst[j].setPk_accsubj(subjtar[j].getPk_accsubj());
					if (htNctobank.containsKey(subjtar[j].getSubjcode())) {
						tarst[j].nckmbm = subjtar[j].getSubjcode();
						// tarst[j].setBankKmbm(htNctobank.get(subjtar[j].getSubjcode()).toString());
						String bankKmbm = htNctobank.get(
								subjtar[j].getSubjcode()).toString();
						String targetBankKmbm = null;
						if (bankKmbm.length() == 28) {
							targetBankKmbm = bankKmbm.substring(0, 4)
									+ hmCorps.get(target[i])
									+ bankKmbm.substring(9, 28);
						} else {
							targetBankKmbm = bankKmbm;
						}
						tarst[j].setBank_kmbm(targetBankKmbm);

					} else {
						tarst[j].setBank_kmbm("@@");
					}
				}
				dmo.insertSubjcontrast(tarst);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}
	}

	public NCSubjContrastVO[] initSubjContrast(String pk_deptdoc)
			throws BusinessException {
		// TODO 自动生成方法存根
		return null;
		
		/*try {
			IDeptSubjContrast itf = (IDeptSubjContrast) NCLocator.getInstance().lookup(
					IDeptSubjContrast.class.getName());
			nc.vo.bd.b02.AccsubjVO vo[] = itf.queryAccsubjbyPKDept(pk_deptdoc);
			NCSubjContrastVO contrast[] = new NCSubjContrastVO[vo.length];
			for (int i = 0; i < contrast.length; i++) {
				contrast[i] = new NCSubjContrastVO();
				contrast[i].setPk_accsubj(vo[i].getPk_accsubj());
				contrast[i].nckmbm = vo[i].getSubjcode();
//				contrast[i].setPk_glorgbook(pk_glorgbook);
//				contrast[i].setPk_dept(pk_deptdoc);
				contrast[i].setPk_dept(pk_deptdoc);
				contrast[i].setIstrans("N");
				contrast[i].setIsAccount("N");

			}
			new SubjectDMO().insertSubjcontrast(contrast);
			NcSubjContrastCache. getInstance().updateVersion();
			return new SubjectDMO().queryNCSubjContrastbyPKDept(pk_deptdoc);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}*/

	}

	public String[] insertSubjContrast(NCSubjContrastVO[] vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		try {
			new SubjectDMO().insertSubjcontrast(vo);
			NcSubjContrastCache. getInstance().updateVersion();
			return null;
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}
	}

	public NCSubjContrastVO[] querySubjContrast(String pkglorgbook)
			throws BusinessException {
		// TODO 自动生成方法存根
		try {
			return new SubjectDMO().querySubjContrast(pkglorgbook);
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}
	}

	public void updateSubjContrast(NCSubjContrastVO[] vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		try {
			SubjectDMO dmo = new SubjectDMO();
			dmo.updateSubjContrast(vo);
			NcSubjContrastCache.getInstance().updateVersion();
			//NcSubjContrastCache.getInstance().initCache();
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}
	}

	public NCSubjContrastVO[] queryAllSubjContrast() throws BusinessException {
		// TODO 自动生成方法存根
		NCSubjContrastVO[] vos = null;
		try {
			DataItfDAO dao = new DataItfDAO();
			vos = dao.queryAllSubjContrast();
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}
		return vos;
	}

	public void deleteSubjAssConstrasts(SubjAssConstrastVO[] subjAssConstrastVos)
			throws BusinessException {
		// TODO Auto-generated method stub
		if (subjAssConstrastVos == null)
			return;
		try {
			SimplSubjAssConstrastVO[] simpVos = SubjAssConstrastHelper
					.catSimplSubjAssConstrasts(subjAssConstrastVos);
			PersistenceManager psm = PersistenceManager.getInstance();
			psm.delete(simpVos);
			SubjAssConstrastCache.getInstance().updateVersions(
					subjAssConstrastVos);
			NcSubjContrastCache. getInstance().updateVersion();
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e);
		}

	}

	public SubjAssConstrastVO[] querySubjAssConstrastBy(String pk_accsubj)
			throws BusinessException {
		// TODO Auto-generated method stub
		try {
			// SimplSubjAssConstrastVO[]
			PersistenceManager psm = PersistenceManager.getInstance();
			Collection<SubjAssConstrastVO> collect = psm.retrieveByClause(
					SimplSubjAssConstrastVO.class, " pk_accsubj = '"
							+ pk_accsubj + "' ");
			if (collect != null) {
				SimplSubjAssConstrastVO[] simplVos = collect
						.toArray(new SimplSubjAssConstrastVO[0]);
				// 
				String dataSource = DataSourceCenter.getInstance()
						.getSourceName();
				NCUFOCatchContainer.hm_AccsubjContainer.put(Thread
						.currentThread(), dataSource);
				fillAssContent(pk_accsubj, simplVos);

				return SubjAssConstrastFactory
						.createSubjAssConstrasts(simplVos);
			}
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e);
		}
		return null;
	}

	private void fillAssContent(String pk_accsubj,
			SimplSubjAssConstrastVO[] simpVos) throws BusinessException {
		// 根据会计科目去主体账簿主键
		try {
			///田春勇
			AccsubjGLVO subjVo = AccsubjDataCache.getInstance().getAccsubjVOByPK(
					pk_accsubj);
			String pk_glorgbook = subjVo.getPk_glorgbook();
			// String pk_corp = BDGLOrgBookAccessor.getPk_corp(pk_glorgbook);
			for (int i = 0; i < simpVos.length; i++) {
				BddataVO dataVo = BDAccessor.getBDData(simpVos[i]
						.getPk_asstype(), OrgnizeTypeVO.ZHUZHANG_TYPE,
						pk_glorgbook, simpVos[i].getPk_assvalue());
				BdinfoVO infoVo = BDAccessor.getBdInfo(simpVos[i]
						.getPk_asstype());
				simpVos[i].setAsstypecode(infoVo.getBdcode());
				simpVos[i].setAsstypename(infoVo.getBdname());
				simpVos[i].setAssvaluecode(dataVo.getCode());
				simpVos[i].setAssvaluename(dataVo.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
		}

	}

	public SubjAssConstrastVO[] saveSubjAssConstrast(
			SubjAssConstrastVO[] subjAssConstrastVos) throws BusinessException {
		try {			
			SubjAssConstrastHelper.fillRowIdsIfNo(subjAssConstrastVos);			
			SimplSubjAssConstrastVO[] simpVos = SubjAssConstrastHelper
					.fecthValitConstrasts(subjAssConstrastVos);
			if (simpVos == null || simpVos.length == 0)
				return subjAssConstrastVos;
			List<SimplSubjAssConstrastVO> insertList = new ArrayList<SimplSubjAssConstrastVO>();
			List<SimplSubjAssConstrastVO> updateList = new ArrayList<SimplSubjAssConstrastVO>();
			PersistenceUtil.splitInsertOrUpdates(simpVos, insertList,
					updateList);
			PersistenceManager psm = PersistenceManager.getInstance();
			psm.update(updateList.toArray(new SimplSubjAssConstrastVO[0]));
			String[] keys = psm.insert(insertList
					.toArray(new SimplSubjAssConstrastVO[0]));
			for (int i = 0; i < insertList.size(); i++) {
				SimplSubjAssConstrastVO vo = insertList.get(i);
				vo.setPrimaryKey(keys[i]);
			}
			SubjAssConstrastCache.getInstance().updateVersions(
					subjAssConstrastVos);
			NcSubjContrastCache. getInstance().updateVersion();
			return subjAssConstrastVos;
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e);
		}

	}
}
