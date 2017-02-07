package nc.impl.bankpub.pub;

import java.util.List;
import java.util.Vector;

import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.vo.bankpub.dealcode.DealCodeVO;
import nc.vo.bankpub.especialsubj.EspecialSubjVO;
import nc.vo.bankpub.respondcode.RespondCodeVO;
import nc.vo.bankpub.vouchertype.VouTypeVO;
import nc.vo.bankpub.vouchtemp.VouchTempVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class DataDAO {
	public DealCodeVO[] queryDealCode() throws Exception{
		DealCodeVO[] vos = null;
    	String sql = "select pk_dealcode,dealindex,dealcontent,dealcode"
    		+" from bank_dealcode ";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{

			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				vos = new DealCodeVO[os.length];
				for(int i=0;i<os.length;i++){
					DealCodeVO vo = new DealCodeVO();
					Object[] o = (Object[])os[i];
					vo.setPk_dealcode(o[0]==null?"":o[0].toString().trim());
					vo.setDealindex(o[1]==null?"":o[1].toString().trim());
					vo.setDealcontent(o[2]==null?"":o[2].toString().trim());
					vo.setDealcode(o[3]==null?"":o[3].toString().trim());
					v.addElement(vo);
				}
				if(v.size()>0){
					v.copyInto(vos);
				}
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
 	
		return vos;
	}
	
	public String queryDealCodeByIndex(String dealindex) throws Exception{
		
		if(dealindex==null){
			return null;
		}
		String dealcode = null;
		String sql="select dealcode from bank_dealcode where dealindex='"+dealindex+"'";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			if(os!=null && os.length!=0){

				Object[] o = (Object[])os[0];
				dealcode = o[0].toString();
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
		
		return dealcode;
	}
	
	public String getRespondInfoByRespongCode(String respondcode) throws Exception{
		if(respondcode==null){
			return null;
		}
		String hintinfo = null;
		
		String sql="select hintinfo from bank_respondcode where respondcode='"+respondcode+"'";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			if(os!=null && os.length!=0){

				Object[] o = (Object[])os[0];
				hintinfo = o[0].toString();
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
		
		return hintinfo;
	}
	
	public void updateDealCode(DealCodeVO vo) throws Exception{
		if(vo==null){
			return ;
		}
		PersistenceManager sessionManager=null;
		try{
    		sessionManager = PersistenceManager.getInstance();
    		JdbcSession session = sessionManager.getJdbcSession();
    		String sql="update bank_dealcode set "
    			+" dealcode='"+vo.getDealcode()+"'"
				+" where pk_dealcode='"+vo.getPk_dealcode()+"'";
    		session.executeUpdate(sql);
		
	    	} finally {
	  		try {
	  			if (sessionManager != null) {
	  				sessionManager.release();
	  			}
	  		}catch (Exception e) {}
  		
    	}
		
	}
	public void deleteRespondCodeVOByVO(RespondCodeVO vo)
	throws Exception{
		if(vo==null){
			return;
			
		}
    	PersistenceManager sessionManager=null;
    	try{
		sessionManager = PersistenceManager.getInstance();
		JdbcSession session = sessionManager.getJdbcSession();
		String sql="delete from bank_respondcode "
			+" where pk_respondcode='"+vo.getPk_respondcode()+"'";
		session.executeUpdate(sql);
		
    	} finally {
    		try {
    			if (sessionManager != null) {
    				sessionManager.release();
    			}
    		}catch (Exception e) {}
  		
    	}
		
	}
	public RespondCodeVO[] queryAllRespondCodeVOs() throws Exception{
		RespondCodeVO[] revos = null;
    	String sql="select pk_respondcode,respondindex,respondcode,hintinfo,memo"
    		+" from bank_respondcode"
    		+" order by respondindex";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{

			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				revos = new RespondCodeVO[os.length];
				for(int i=0;i<os.length;i++){
					RespondCodeVO vo = new RespondCodeVO();
					Object[] o = (Object[])os[i];
					vo.setPk_respondcode(o[0].toString()==null?"":o[0].toString().trim());
					vo.setRespondindex(o[1].toString()==null?"":o[1].toString().trim());
					vo.setRespondcode(o[2].toString()==null?"":o[2].toString().trim());
					vo.setHintinfo(o[3].toString()==null?"":o[3].toString().trim());
					vo.setMemo(o[4].toString()==null?"":o[4].toString().trim());
					v.addElement(vo);
				}
				if(v.size()>0){
					v.copyInto(revos);
				}
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
		
		return revos;
	}
	public void updateRespondCodeVOByVO(RespondCodeVO vo)
	throws Exception{
		if(vo==null){
			return;
			
		}
    	PersistenceManager sessionManager=null;
    	try{
		sessionManager = PersistenceManager.getInstance();
		JdbcSession session = sessionManager.getJdbcSession();
		String sql="update bank_respondcode set "
			+" pk_respondcode='"+vo.getPk_respondcode()+"',"
			+" respondindex='"+vo.getRespondindex()+"',"
			+" respondcode='"+vo.getRespondcode()+"',"
    		+" hintinfo='"+ vo.getHintinfo()+"',"
    		+" memo='"+ vo.getMemo()+"'"
			+" where pk_respondcode='"+vo.getPk_respondcode()+"'";
		session.executeUpdate(sql);
		
    	} finally {
  		try {
  			if (sessionManager != null) {
  				sessionManager.release();
  			}
  		}catch (Exception e) {}
  		
    }
		
		
	}
	public void deleteEspecialSubjVOByVO(EspecialSubjVO vo)throws Exception{
		
		if(vo==null){
			return;
		}
    	PersistenceManager sessionManager=null;
    	try{
		sessionManager = PersistenceManager.getInstance();
		JdbcSession session = sessionManager.getJdbcSession();
		String sql="delete from bank_especialsubj "
			+" where pk_especialsubj='"+vo.getPk_especialsubj()+"'";
		session.executeUpdate(sql);
		
    	} finally {
    		try {
    			if (sessionManager != null) {
    				sessionManager.release();
    			}
    		}catch (Exception e) {}
  		
    	}
		
	}
	public EspecialSubjVO[] queryAllEspecialSubjVOs() throws Exception{
		EspecialSubjVO[] vos = null;
    	String sql="select e.pk_especialsubj,e.accsubjcode,e.accsubjname,e.dcflag,e.subjcode,"
    		+" a.subjname"
    		+" from bank_especialsubj e,bd_accsubj a"
    		+" where e.subjcode=a.subjcode and a.pk_glorgbook='0001'"
    		+" order by accsubjcode";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{

			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				vos = new EspecialSubjVO[os.length];
				for(int i=0;i<os.length;i++){
					EspecialSubjVO vo = new EspecialSubjVO();
					Object[] o = (Object[])os[i];
					vo.setPk_especialsubj(o[0].toString()==null?"":o[0].toString().trim());
					vo.setAccsubjcode(o[1].toString()==null?"":o[1].toString().trim());
					vo.setAccsubjname(o[2].toString()==null?"":o[2].toString().trim());
					vo.setDcflag(o[3].toString()==null?"":o[3].toString().trim());
					vo.setSubjcode(o[4].toString()==null?"":o[4].toString().trim());
					vo.subjname = o[5].toString()==null?"":o[5].toString().trim();
					v.addElement(vo);
				}
				if(v.size()>0){
					v.copyInto(vos);
				}
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
		
		return vos;
		
	}
	public void updateEspecialSubjVOByVO(EspecialSubjVO vo) throws Exception{
		
		if(vo==null){
			return;
			
		}
    	PersistenceManager sessionManager=null;
    	try{
		sessionManager = PersistenceManager.getInstance();
		JdbcSession session = sessionManager.getJdbcSession();
		String sql="update bank_especialsubj set "
			+" pk_especialsubj='"+vo.getPk_especialsubj()+"',"
			+" accsubjcode='"+vo.getAccsubjcode()+"',"
			+" accsubjname='"+vo.getAccsubjname()+"',"
    		+" dcflag='"+ vo.getDcflag()+"',"
    		+" subjcode='"+ vo.getSubjcode()+"' "
			+" where pk_especialsubj='"+vo.getPk_especialsubj()+"'";
		session.executeUpdate(sql);
		
    	} finally {
  		try {
  			if (sessionManager != null) {
  				sessionManager.release();
  			}
  		}catch (Exception e) {}
  		
    }
		
	}
	public VouTypeVO[] queryAllVouTypeVOs() throws Exception{
		VouTypeVO[] vos = null;
    	String sql="select v.pk_bankvouchertype,v.pk_vouchertype,v.istrans,"
    		+"a.forshort,a.vouchtypename"
    		+" from bank_vouchertype v,bd_vouchertype a"
    		+" where v.pk_vouchertype=a.pk_vouchertype ";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{

			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				vos = new VouTypeVO[os.length];
				for(int i=0;i<os.length;i++){
					VouTypeVO vo = new VouTypeVO();
					Object[] o = (Object[])os[i];
					vo.setPk_bankvouchertype(o[0].toString()==null?"":o[0].toString().trim());
					vo.setPk_vouchertype(o[1].toString()==null?"":o[1].toString().trim());
					vo.setIstrans(o[2].toString()==null?"":o[2].toString().trim());
					vo.forshort = o[3].toString()==null?"":o[3].toString().trim();
					vo.vouchtypename = o[4].toString()==null?"":o[4].toString().trim();
					v.addElement(vo);
				}
				if(v.size()>0){
					v.copyInto(vos);
				}
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
		return vos;
		
	}
	public void updateVouTypeVOByVO(VouTypeVO vo) throws Exception{
		
		if(vo==null){
			return;
		}
    	PersistenceManager sessionManager=null;
    	try{
		sessionManager = PersistenceManager.getInstance();
		JdbcSession session = sessionManager.getJdbcSession();
		String sql="update bank_vouchertype set "
			+" pk_bankvouchertype='"+vo.getPk_bankvouchertype()+"',"
			+" pk_vouchertype='"+vo.getPk_vouchertype()+"',"
			+" istrans='"+vo.getIstrans()+"'"
			+" where pk_bankvouchertype='"+vo.getPk_bankvouchertype()+"'";
		session.executeUpdate(sql);
		
    	} finally {
  		try {
  			if (sessionManager != null) {
  				sessionManager.release();
  			}
  		}catch (Exception e) {}
  		
    }
	}
	public String executeSQL(String sql)throws Exception{
		
		if(sql==null){
			return null;
		}
		Object o = new Object();
		String str = null;
    	PersistenceManager sessionManager=null;

    	try{
    		sessionManager=PersistenceManager.getInstance();
            JdbcSession sesison =sessionManager.getJdbcSession();
            ArrayProcessor a=new ArrayProcessor();
            Object [] res=(Object [])sesison.executeQuery(sql, a);
			
			if(res!=null && res.length!=0 && res[0]!=null){

				o = res[0];
			}
		
			str = o.toString();
        } finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
		return str;
	
	}
	public VouchTempVO[] queryVouchTempVOSByWhereSql(String wheresql) throws Exception{
		VouchTempVO[] vos = null;
		String sql="select pk_vouchertemp,"
			+" def1,def2,def3,def4,def5,def6,def7,def8,def9,def10,"
			+" def11,def12,def13,def14,def15,def16,def17,def18,def19,def20,"
			+" overflag,memo"
			+" from bank_vouchertemp  ";
		if(wheresql!=null && wheresql.length()!=0){
			sql+=" where "+wheresql;
		}
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{

			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				vos = new VouchTempVO[os.length];
				for(int i=0;i<os.length;i++){
					VouchTempVO vo = new VouchTempVO();
					Object[] o = (Object[])os[i];
					vo.setPk_vouchertemp(o[0].toString()==null?"":o[0].toString().trim());
					vo.setDef1(o[1]==null?"":o[1].toString().trim());
					vo.setDef2(o[2]==null?"":o[2].toString().trim());
					vo.setDef3(o[3]==null?"":o[3].toString().trim());
					vo.setDef4(o[4]==null?"":o[4].toString().trim());
					vo.setDef5(o[5]==null?new UFDouble(0):new UFDouble(o[5].toString().trim()));
					vo.setDef6(o[6]==null?"":o[6].toString().trim());
					vo.setDef7(o[7]==null?"":o[7].toString().trim());
					vo.setDef8(o[8]==null?"":o[8].toString().trim());
					vo.setDef9(o[9]==null?"":o[9].toString().trim());
					vo.setDef10(o[10]==null?"":o[10].toString().trim());
					vo.setDef11(o[11]==null?"":o[11].toString().trim());
					vo.setDef12(o[12]==null?"":o[12].toString().trim());
					vo.setDef13(o[13]==null?"":o[13].toString().trim());
					vo.setDef14(o[14]==null?"":o[14].toString().trim());
					vo.setDef15(o[15]==null?"":o[15].toString().trim());
					vo.setDef16(o[16]==null?"":o[16].toString().trim());
					vo.setDef17(o[17]==null?"":o[17].toString().trim());
					vo.setDef18(o[18]==null?"":o[18].toString().trim());
					vo.setDef19(o[19]==null?"":o[19].toString().trim());
					vo.setDef11(o[20]==null?"":o[20].toString().trim());
					v.addElement(vo);
				}
				if(v.size()>0){
					v.copyInto(vos);
				}
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
		
		
		return vos ;
	}
    public void updateBalanceFlag(UFDate date)throws Exception{
    	if(date==null){
    		return;
    	}
    	PersistenceManager sessionManager=null;
    	String strdate = date.toString().substring(0, 4)
    	+date.toString().substring(5, 7)
    	+date.toString().substring(8, 10);
    	try{
    		sessionManager = PersistenceManager.getInstance();
    		JdbcSession session = sessionManager.getJdbcSession();
    		String sql="update bank_balance set "
    			+" isfinish='Y'"
				+" where acountdate='"+strdate+"'";
    		session.executeUpdate(sql);
		
	    	} finally {
	  		try {
	  			if (sessionManager != null) {
	  				sessionManager.release();
	  			}
	  		}catch (Exception e) {}
  		
    	}
    }
}
