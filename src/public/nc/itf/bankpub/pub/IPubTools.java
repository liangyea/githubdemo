package nc.itf.bankpub.pub;
import nc.vo.bankpub.currrate.CurrrateVO;
import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.bd.b02.AccsubjVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

public interface IPubTools {
	public String getPubInitSys(String initcode)throws BusinessException;
	public String getStrByPk(String tablename,String selfield,String wherefield,String value)throws BusinessException;
	public String getStrByPk1(String tablename,String selfield,String wherefield,String value)throws BusinessException;
	public  String [] split(String str,String sp)throws BusinessException;
    public String getPkcorpByPkglorgbook(String pk_glorgbook)throws BusinessException;
    public String[] getPkglorgbookByPkcorp(String pk_corp)throws BusinessException;
	public CurrrateVO[] readCurrRateFile(SystemTypeVO vo,UFDate date) throws BusinessException;
	public UFDate getServerDate()throws BusinessException;
	public AccsubjVO[] getNcAccsujVosByCondition(String condition)
			throws BusinessException;
	public void ExcuteUpdateSQL(String sql) throws Exception;
}
