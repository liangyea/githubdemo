package nc.impl.bankpub.pub;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import nc.bs.logging.Logger;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.vo.bankpub.currrate.CurrrateVO;
import nc.vo.bankpub.filedefine.FileDefineVO;
import nc.vo.bankpub.subjcolshow.SubjColShowVO;
import nc.vo.bankpub.subjcontrast.BankSubjContrastVO;
import nc.vo.bankpub.subjcontrast.NCSubjContrastVO;
import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.bankpub.voucherelement.VoucherElementVO;
import nc.vo.bd.b02.AccsubjVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class DataItfDAO {
	public String getPubInitSys(String initcode) throws Exception {
		// TODO 自动生成方法存根
    	String value=null;
        String sql="select * from pub_sysinit where pk_org='0001' and initcode='"+initcode+"'";
        PersistenceManager ps=null;
        try {
            ps=PersistenceManager.getInstance();
            JdbcSession sesison =ps.getJdbcSession();
            MapProcessor a=new MapProcessor();
            HashMap res=(HashMap)sesison.executeQuery(sql, a);
            value=(String)res.get("value");
            if(value==null || value.trim().equals("")){
            	throw new Exception(initcode+"没有设置");
            }

        } finally {
    		try {
    			if (ps != null) {
    				ps.release();
    			}
    		}catch (Exception e) {}
        }
        return value;
	}
    public String getPkCurrInfo(String pk_corp,String pk_currtype)throws Exception{
    	
    	if(pk_corp==null || pk_currtype==null){
    		return null;
    	}
    	String pk_currinfo = null;
    	String sql = "select pk_currinfo "
    		+" from bd_currinfo "
    		+" where pk_corp='"+pk_corp+"'"
    		+" and pk_currtype='"+pk_currtype.toString()+"'";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			if(os!=null && os.length!=0){
				    Object[] o = (Object[])os[0];
				    pk_currinfo =o[0].toString().trim();
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
    	return pk_currinfo;
    }
    public String getStrByPk(String tablename,String selfield,String wherefield,String value)throws Exception{
    	String re=null;
    	String sql="select "+selfield+" from "+tablename+" where "+wherefield+" ='"+value+"'";
    	  PersistenceManager ps=null;
          try {
              ps=PersistenceManager.getInstance();
              JdbcSession sesison =ps.getJdbcSession();
              ArrayProcessor a=new ArrayProcessor();
              Object [] res=(Object [])sesison.executeQuery(sql, a);
              if(res==null || res.length==0){
            	  	Logger.info(sql);
                	throw new Exception("在表"+tablename+"中按"+wherefield+"("+value+")查找"+selfield+"有误！[sql:"+sql+"]");
                }
              re=res[0].toString();
              
          } finally {
      		try {
      			if (ps != null) {
      				ps.release();
      			}
      		}catch (Exception e) {}
      		
          }
    	return re;
    }
    public String getStrByPk1(String tablename,String selfield,String wherefield,String value)throws Exception{
    	String re=null;
    	String sql="select "+selfield+" from "+tablename+" where "+wherefield+" ='"+value+"'";
    	  PersistenceManager ps=null;
          try {
              ps=PersistenceManager.getInstance();
              JdbcSession sesison =ps.getJdbcSession();
              ArrayProcessor a=new ArrayProcessor();
              Object [] res=(Object [])sesison.executeQuery(sql, a);
              if(res==null || res.length==0){
            	  	Logger.info("没有找到科目错误."+sql);
            	  	re ="";
            	  	return re;
//                	throw new Exception("在表外系统科目对照表["+tablename+"]中按"+wherefield+"("+value+")查找"+selfield+"有误！[sql:"+sql+"]");
                }
              re=res[0].toString();
              
          } finally {
      		try {
      			if (ps != null) {
      				ps.release();
      			}
      		}catch (Exception e) {}
      		
          }
    	return re;
    }
    public NCSubjContrastVO[] queryAllSubjContrast()throws Exception{
    	
    	NCSubjContrastVO[] result = null;
    	String sql = "select pk_ncsubjcontrast,pk_accsubj,bank_kmbm,pk_glorgbook,istrans,balbank_kmbm"
    		+" from bank_ncsubjcontrast ";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();	JdbcSession session= sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			if(os!=null && os.length!=0){
				result = new NCSubjContrastVO[os.length];
				for(int i=0;i<os.length;i++){
					
					Object[] o = (Object[])os[i];
					result[i] = new NCSubjContrastVO();
					result[i].setPk_ncsubjcontrast(o[0] == null ? null : o[0].toString().trim());
					result[i].setPk_accsubj(o[1] == null ? null : o[1].toString().trim());
					result[i].setBank_kmbm(o[2] == null ? null : o[2].toString().trim());
					result[i].setPk_glorgbook(o[3] == null ? null : o[3].toString().trim());
					result[i].setIstrans(o[3] == null ? null : o[4].toString().trim());
					result[i].setBalbank_kmbm(o[5] == null ? null : o[5].toString().trim());
				}
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
    	return result;
    }

//    public FlowContrastVO queryFlowcontrastVO(String pk_voucher)throws Exception{
//    	FlowContrastVO rvo = null;
//
//    	String sql = "select pk_flowcontrast,flowid,flowdate "
//    		+" from bank_flowcontrast "
//    		+" where pk_voucher='"+pk_voucher+"'";
//    	PersistenceManager sessionManager=null;
//    	Object[] os = null;
//    	try{
//			sessionManager = PersistenceManager.getInstance();
//			JdbcSession session = sessionManager.getJdbcSession();
//			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
//			os = list.toArray();
//			if(os!=null && os.length!=0){
//				rvo = new FlowContrastVO();
//					
//					Object[] o = (Object[])os[0];
//					rvo = new FlowContrastVO();
//					rvo.setPk_flowcontrast(o[0].toString().trim());
//					rvo.setFlowid(o[1].toString().trim());
//					rvo.setFlowdate(new UFDate(o[2].toString().trim()));
//
//
//			}
//    		
//    	} finally {
//      		try {
//      			if (sessionManager != null) {
//      				sessionManager.release();
//      			}
//      		}catch (Exception e) {}
//      		
//        }
//    	
//    	return rvo ;
//    }
    public String getPkByFlowid(UFDate date, String flowid) throws Exception{
    	if(date==null || flowid==null){
    		return null;
    		
    	}
    	String pk_voucher = null;
    	String sql = "select pk_voucher "
    		+" from bank_flowcontrast "
    		+" where flowid='"+flowid+"'"
    		+" and flowdate='"+date.toString()+"'";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			if(os!=null && os.length!=0){
				    Object[] o = (Object[])os[0];
					pk_voucher =o[0].toString().trim();
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
    	
    	return pk_voucher ;
    	
    }
    
//    public void deleteFlowcontrastVOs(FlowContrastVO[] vos)throws Exception{
//    	if(vos==null || vos.length==0){
//    		return;
//    		
//    	}
//    	PersistenceManager sessionManager=null;
//
//    	try{
//			sessionManager = PersistenceManager.getInstance();
//			JdbcSession session = sessionManager.getJdbcSession();
//			for(int i=0;i<vos.length;i++){
//				String sql ="delete from bank_flowcontrast "
//					+" where pk_voucher='"+vos[i].getPk_voucher()+"'"
//					+" and flowid='"+vos[i].getFlowid()+"'"
//					+" and flowdate='"+vos[i].getFlowdate()+"'";
//				session.executeUpdate(sql);
//			}
//   		
//    	} finally {
//      		try {
//      			if (sessionManager != null) {
//      				sessionManager.release();
//      			}
//      		}catch (Exception e) {}
//      		
//        }
//    }
	public String getPkcorpByPkglorgbook(String pk_glorgbook){
		String pk_corp = null;
		Object[] os = null;
		String sql = "select c.pk_corp from bd_corp c, bd_glorg g,bd_glorgbook gl " 
			+" where gl.pk_glorgbook ='"+pk_glorgbook+"' and gl.pk_glorg = g.pk_glorg " 
			+" and c.pk_corp = g.pk_entityorg";
		PersistenceManager sessionManager = null;
		try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
			
					Object[] o = (Object[])os[0];
					pk_corp = o[0].toString();
			}

			
		}catch(DbException e){
			e.printStackTrace();
			throw new BusinessRuntimeException(e.getMessage());
		}finally{
			if(sessionManager!=null)
				sessionManager.release();
		}
		return pk_corp;
	}
    public String[] getPkglorgbookByPkcorp(String pk_corp)throws Exception{
    	String[] pk_glorgbook = null; 
    	String sql = "select glb.pk_glorgbook from bd_glorgbook glb ,bd_glorg glo"
    		+" where  glb.pk_glorg = glo.pk_glorg"
    		+" and glo.pk_entityorg ='"+pk_corp+"'";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{

			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			if(os!=null && os.length!=0){
				pk_glorgbook = new String[os.length];
				for(int i=0;i<os.length;i++){
					
					Object[] o = (Object[])os[i];

					pk_glorgbook[i]= o[0].toString().trim();

				}
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
    	return pk_glorgbook;
    }
    public void deleteSystemTypeVOByVO(SystemTypeVO vo)throws Exception{
    	if(vo==null){
    		return;
    	}
    	PersistenceManager sessionManager=null;
    	try{
		sessionManager = PersistenceManager.getInstance();
		JdbcSession session = sessionManager.getJdbcSession();
		String sql="delete from bank_system "
			+" where pk_system='"+vo.getPk_system()+"'";
		session.executeUpdate(sql);
		
    	} finally {
    		try {
    			if (sessionManager != null) {
    				sessionManager.release();
    			}
    		}catch (Exception e) {}
  		
    	}
    }
    public void deleteBankContrast() throws Exception{
    	PersistenceManager sessionManager=null;
    	try{
    		sessionManager = PersistenceManager.getInstance();
    		JdbcSession session = sessionManager.getJdbcSession();
    		String sql="delete from bank_bankcontrast";
    		session.executeUpdate(sql);
    		System.out.print("asdfdf");
        	} finally {
        		try {
        			if (sessionManager != null) {
        				sessionManager.release();
        			}
        		}catch (Exception e) {}
      		
        	}
    }
    public SystemTypeVO[] queryAllSystemTypeVOs()throws Exception{
    	SystemTypeVO[] vos = null;
    	String sql="select s.pk_system,s.systemcode,s.systemname,s.dateformat,"
    		+" s.vouchertype,s.transmether,s.IPaddress,s.port,"
    		+" s.username,s.password,s.ftppath," 
    		+" s.localpath,s.isfixlen,s.separator," 
    		+" s.datafilename,s.ratefilename,s.suffixname,"
    		+" s.isprealert,s.accountfilename,"
    		+" v.vouchtypename "
    		+" from bank_system s,bd_vouchertype v"
    		+" where s.vouchertype=v.pk_vouchertype"
    		+" order by s.systemcode";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{

			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				vos = new SystemTypeVO[os.length];
				for(int i=0;i<os.length;i++){
					SystemTypeVO vo = new SystemTypeVO();
					Object[] o = (Object[])os[i];
					vo.setPk_system(o[0].toString()==null?"":o[0].toString().trim());
					vo.setSystemcode(o[1].toString()==null?"":o[1].toString().trim());
					vo.setSystemname(o[2].toString()==null?"":o[2].toString().trim());
					vo.setDateformat(o[3].toString()==null?"":o[3].toString().trim());
					vo.setVouchertype(o[4].toString()==null?"":o[4].toString().trim());
					vo.setTransmether(o[5].toString()==null?"":o[5].toString().trim());
					vo.setIPaddress(o[6]==null?"":o[6].toString().trim());
					vo.setPort(o[7]==null?"":o[7].toString().trim());
					vo.setUsername(o[8]==null?"":o[8].toString().trim());
					vo.setPassword(o[9]==null?"":o[9].toString().trim());
					vo.setFtppath(o[10]==null?"":o[10].toString().trim());
					vo.setLocalpath(o[11]==null?"":o[11].toString().trim());
					vo.setIsfixlen(o[12]==null?"":o[12].toString().trim());
					vo.setSeparator(o[13]==null?"":o[13].toString().trim());
					vo.setDatafilename(o[14]==null?"":o[14].toString().trim());
					vo.setRatefilename(o[15]==null?"":o[15].toString().trim());
					vo.setSuffixname(o[16]==null?"":o[16].toString().trim());
					vo.setIsprealert(o[17]==null?"":o[17].toString().trim());
					vo.setAccountfilename(o[18]==null?"":o[18].toString().trim());
					vo.vouchertypename = o[19]==null?"":o[19].toString().trim();
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
    public void updateSystemTypeVOByVO(SystemTypeVO vo)throws Exception{
    	
    	if(vo==null){
    		return;
    	}
    	PersistenceManager sessionManager=null;
    	try{
		sessionManager = PersistenceManager.getInstance();
		JdbcSession session = sessionManager.getJdbcSession();
		String sql="update bank_system set "
			+" Systemcode='"+vo.getSystemcode()+"',"
			+" systemname='"+vo.getSystemname()+"',"
			+" dateformat='"+vo.getDateformat()+"',"
    		+" vouchertype='"+ vo.getVouchertype()+"',"
    		+" transmether='"+ vo.getTransmether()+"',"
    		+" IPaddress='"+vo.getIPaddress()+"',"
    		+" port='"+vo.getPort()+"',"
    		+" username='" +vo.getUsername()+"',"
    		+" password='" +vo.getPassword()+"',"
    		+" ftppath='"+vo.getFtppath()+"',"
    		+" localpath='"+vo.getLocalpath()+"',"
    		+" isfixlen='"+vo.getIsfixlen()+"',"
    		+" separator='"+vo.getSeparator()+"',"
    		+" datafilename='"+vo.getDatafilename()+"',"
    		+" ratefilename='"+vo.getRatefilename()+"',"
    		+" suffixname='"+vo.getSuffixname()+"',"
    		+" isprealert='"+vo.getIsprealert()+"',"
    		+" accountfilename='"+vo.getAccountfilename()+"'"
			+" where pk_system='"+vo.getPk_system()+"'";
		session.executeUpdate(sql);
		
    	} finally {
  		try {
  			if (sessionManager != null) {
  				sessionManager.release();
  			}
  		}catch (Exception e) {}
  		
    }
    }
    public void deleteFileDefineVOByVO(FileDefineVO vo)throws Exception{
    	if(vo==null){
    		return;
    	}
    	PersistenceManager sessionManager=null;
    	try{
		sessionManager = PersistenceManager.getInstance();
		JdbcSession session = sessionManager.getJdbcSession();
		String sql="delete from bank_filedefine "
			+" where pk_filedefine='"+vo.getPk_filedefine()+"'";
		session.executeUpdate(sql);
		
    	} finally {
    		try {
    			if (sessionManager != null) {
    				sessionManager.release();
    			}
    		}catch (Exception e) {}
  		
    	}
    }
    public FileDefineVO[] queryAllFileDefineVOs()throws Exception{
    	FileDefineVO[] vos = null;
    	String sql="select s.pk_filedefine,s.xh,s.columnname,s.datatype,"
    		+"s.length,s.pk_system "
    		+" from bank_filedefine s order by s.xh";
    		
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{

			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				vos = new FileDefineVO[os.length];
				for(int i=0;i<os.length;i++){
					FileDefineVO vo = new FileDefineVO();
					Object[] o = (Object[])os[i];
					vo.setPk_filedefine(o[0]==null?"":o[0].toString().trim());
					vo.setXh(o[1]==null?new Integer("0"):Integer.valueOf(o[1].toString().trim()));
					vo.setColumnname(o[2]==null?"":o[2].toString().trim());
					vo.setDatatype(o[3]==null?"":o[3].toString().trim());
					vo.setLength(o[4]==null?new Integer("0"):Integer.valueOf(o[4].toString().trim()));
					vo.setPk_system(o[5]==null?"":o[5].toString().trim());
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
    public void updateFileDefineVOByVO(FileDefineVO vo)throws Exception{
    	
    	if(vo==null){
    		return;
    	}
    	PersistenceManager sessionManager=null;
    	try{
    		sessionManager = PersistenceManager.getInstance();
    		JdbcSession session = sessionManager.getJdbcSession();
    		String sql="update bank_filedefine set "
    			+" xh="+vo.getXh()+","
    			+" columnname='"+vo.getColumnname()+"',"
				+" datatype='"+vo.getDatatype()+"',"
	    		+" length="+ vo.getLength()+","
	    		+" pk_system='"+vo.getPk_system()+"'"
				+" where pk_filedefine='"+vo.getPk_filedefine()+"'";
    		session.executeUpdate(sql);
		
	    	} finally {
	  		try {
	  			if (sessionManager != null) {
	  				sessionManager.release();
	  			}
	  		}catch (Exception e) {}
  		
    	}
    }
    public BankSubjContrastVO[] queryAllBankSubjContrastVO()throws Exception{
    	BankSubjContrastVO[] vos = null;
    	String sql="select pk_banksubjcontrast,pk_system,pk_glorgbook,pk_accsubj,"
    		+"def1,def2,def3,def4,def5  "
    		+" from bank_banksubjcontrast "
    		+" order by def1";
		
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				vos = new BankSubjContrastVO[os.length];
				for(int i=0;i<os.length;i++){
					BankSubjContrastVO vo = new BankSubjContrastVO();
					Object[] o = (Object[])os[i];
					vo.setPk_banksubjcontrast(o[0]==null?"":o[0].toString().trim());
					vo.setPk_system(o[1]==null?"":o[1].toString().trim());
					vo.setPk_glorgbook(o[2]==null?"":o[2].toString().trim());
					vo.setPk_accsubj(o[3]==null?"":o[3].toString().trim());
					vo.setDef1(o[4]==null?"":o[4].toString().trim());
					vo.setDef2(o[5]==null?"":o[5].toString().trim());
					vo.setDef3(o[6]==null?"":o[6].toString().trim());
					vo.setDef4(o[7]==null?"":o[7].toString().trim());
					vo.setDef5(o[8]==null?"":o[8].toString().trim());
					v.addElement(vo);
				}
				if(v.size()>0){
					v.copyInto(vos);
				}
			}
    	}finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
    	return vos ;
    }
    public void updateBankSubjContrastVO(BankSubjContrastVO vo)throws Exception{
    	if(vo == null){
    		return;
    	}
    	PersistenceManager sessionManager=null;
    	try{
    		sessionManager = PersistenceManager.getInstance();
    		JdbcSession session = sessionManager.getJdbcSession();
    		StringBuffer sb = new StringBuffer();
    		sb.append("update bank_banksubjcontrast set "
    			+" pk_system='"+vo.getPk_system()+"',"
    			+" pk_glorgbook='"+vo.getPk_glorgbook()+"',"
				+" pk_accsubj='"+vo.getPk_accsubj()+"'");
    		if(vo.getDef1()!=null && !vo.getDef1().equals("")){
    			sb.append(", def1='"+ vo.getDef1()+"'");
    		}
    		if(vo.getDef2()!=null && !vo.getDef2().equals("")){
    			sb.append(", def2='"+ vo.getDef2()+"'");
    		}
    		if(vo.getDef3()!=null && !vo.getDef3().equals("")){
    			sb.append(", def3='"+vo.getDef3()+"'");
    		}
    		if(vo.getDef4()!=null && !vo.getDef4().equals("")){
    			sb.append(", def4='"+vo.getDef4()+"'");
    		}
    		if(vo.getDef5()!=null && !vo.getDef5().equals("")){
    			sb.append(", def5='"+vo.getDef5()+"'");
    		}
    		sb.append(" where pk_banksubjcontrast='"+vo.getPk_banksubjcontrast()+"'");
//    		String sql="update bank_banksubjcontrast set "
//    			+" pk_system='"+vo.getPk_system()+"',"
//    			+" pk_glorgbook='"+vo.getPk_glorgbook()+"',"
//				+" pk_accsubj='"+vo.getPk_accsubj()+"',"
//	    		+" def1='"+ vo.getDef1()+"',"
//	    		+" def2='"+ vo.getDef2()+"',"
//	    		+" def3='"+vo.getDef3()+"',"
//	    		+" def4='"+vo.getDef4()+"',"
//	    		+" def5='"+vo.getDef5()+"'"
//				+" where pk_banksubjcontrast='"+vo.getPk_banksubjcontrast()+"'";
    		String sql = sb.toString();
    		session.executeUpdate(sql);
		
	    	} finally {
	  		try {
	  			if (sessionManager != null) {
	  				sessionManager.release();
	  			}
	  		}catch (Exception e) {}
  		
    	}
    }
	public void deleteBankSubjContrastVO(BankSubjContrastVO vo)throws Exception{
		if(vo == null){
			return;
		}
    	PersistenceManager sessionManager=null;
    	try{
		sessionManager = PersistenceManager.getInstance();
		JdbcSession session = sessionManager.getJdbcSession();
		String sql="delete from bank_banksubjcontrast "
			+" where pk_banksubjcontrast='"+vo.getPk_banksubjcontrast()+"'";
		session.executeUpdate(sql);
		
    	} finally {
    		try {
    			if (sessionManager != null) {
    				sessionManager.release();
    			}
    		}catch (Exception e) {}
  		
    	}
		
		
	}
	public SubjColShowVO[] queryAllSubjColShowVO()throws Exception{
		SubjColShowVO[] vos = null;
    	String sql="select pk_subjcolshow,pk_system,"
    		+"def1,def2,def3,def4,def5 "
    		+" from bank_subjcolshow ";
		
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				vos = new SubjColShowVO[os.length];
				for(int i=0;i<os.length;i++){
					SubjColShowVO vo = new SubjColShowVO();
					Object[] o = (Object[])os[i];
					vo.setPk_subjcolshow(o[0]==null?"":o[0].toString().trim());
					vo.setPk_system(o[1]==null?"":o[1].toString().trim());
					vo.setDef1(o[2]==null?"":o[2].toString().trim());
					vo.setDef2(o[3]==null?"":o[3].toString().trim());
					vo.setDef3(o[4]==null?"":o[4].toString().trim());
					vo.setDef4(o[5]==null?"":o[5].toString().trim());
					vo.setDef5(o[6]==null?"":o[6].toString().trim());
					v.addElement(vo);
				}
				if(v.size()>0){
					v.copyInto(vos);
				}
			}
    	}finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
		return vos ;
	}
	public void updateSubjColShowVO(SubjColShowVO vo)throws Exception{
		if(vo == null){
			return;
		}
    	PersistenceManager sessionManager=null;
    	try{
    		sessionManager = PersistenceManager.getInstance();
    		JdbcSession session = sessionManager.getJdbcSession();
    		String sql="update bank_subjcolshow set "
    			+" pk_system='"+vo.getPk_system()+"',"
	    		+" def1='"+ vo.getDef1()+"',"
	    		+" def2='"+ vo.getDef2()+"',"
	    		+" def3='"+vo.getDef3()+"',"
	    		+" def4='"+vo.getDef4()+"',"
	    		+" def5='"+vo.getDef5()+"'"
				+" where pk_subjcolshow='"+vo.getPk_subjcolshow()+"'";
    		session.executeUpdate(sql);
		
	    	} finally {
	  		try {
	  			if (sessionManager != null) {
	  				sessionManager.release();
	  			}
	  		}catch (Exception e) {}
  		
    	}
	}
	public void deleteVoucherElementVOByVO(VoucherElementVO vo)throws Exception{
		if(vo == null){
			return;
		}
    	PersistenceManager sessionManager=null;
    	try{
		sessionManager = PersistenceManager.getInstance();
		JdbcSession session = sessionManager.getJdbcSession();
		String sql="delete from bank_voucherelement "
			+" where pk_voucherelement='"+vo.getPk_voucherelement()+"'";
		session.executeUpdate(sql);
		
    	} finally {
    		try {
    			if (sessionManager != null) {
    				sessionManager.release();
    			}
    		}catch (Exception e) {}
  		
    	}
		
	}
	public VoucherElementVO[] queryAllVoucherElementVOs()throws Exception{
		VoucherElementVO[] vos = null;
    	String sql="select pk_voucherelement,pk_system,"
    		+"pk_corpxh,pk_currtypexh,money,dcorient,subjcode,datecol,deptass,inoutflag"
    		+" from bank_voucherelement v";
		
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				vos = new VoucherElementVO[os.length];
				for(int i=0;i<os.length;i++){
					VoucherElementVO vo = new VoucherElementVO();
					Object[] o = (Object[])os[i];
					vo.setPk_voucherelement(o[0]==null?"":o[0].toString().trim());
					vo.setPk_system(o[1]==null?"":o[1].toString().trim());
					vo.setPk_corpxh(o[2]==null?"":o[2].toString().trim());
					vo.setPk_currtypexh(o[3]==null?"":o[3].toString().trim());
					vo.setMoney(o[4]==null?"":o[4].toString().trim());
					vo.setDcorient(o[5]==null?"":o[5].toString().trim());
					vo.setSubjcode(o[6]==null?"":o[6].toString().trim());
					vo.setDatecol(o[7]==null?"":o[7].toString().trim());
					vo.setDeptass(o[8]==null?"":o[8].toString().trim());
					vo.setInoutflag(o[9]==null?"":o[9].toString().trim());
					v.addElement(vo);
				}
				if(v.size()>0){
					v.copyInto(vos);
				}
			}
    	}finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
		return vos;
	}
	public void updateVoucherElementVOByVO(VoucherElementVO vo)throws Exception{
		if(vo == null){
			return;
		}
    	PersistenceManager sessionManager=null;
    	try{
    		sessionManager = PersistenceManager.getInstance();
    		JdbcSession session = sessionManager.getJdbcSession();
    		String sql="update bank_voucherelement set "
    			+" pk_system='"+vo.getPk_system()+"',"
	    		+" pk_corpxh='"+ vo.getPk_corpxh()+"',"
	    		+" pk_currtypexh='"+ vo.getPk_currtypexh()+"',"
	    		+" money='"+vo.getMoney()+"',"
	    		+" dcorient='"+vo.getDcorient()+"',"
	    		+" subjcode='"+vo.getSubjcode()+"',"
	    		+" datecol ='"+vo.getDatecol()+"',"
	    		+" deptass='"+vo.getDeptass()+"',"
	    		+" inoutflag='"+vo.getInoutflag()+"'"
				+" where pk_voucherelement='"+vo.getPk_voucherelement()+"'";
    		session.executeUpdate(sql);
		
	    	} finally {
	  		try {
	  			if (sessionManager != null) {
	  				sessionManager.release();
	  			}
	  		}catch (Exception e) {}
  		
    	}
	}
    public CurrrateVO[] queryCurrrateVOs(UFDate date)throws Exception {
    	if(date== null){
    		return null;
    	}
    	CurrrateVO[] vos = null;
    	String sql="select r.PK_CURRTYPE,r.RATE,r.RATEDATE ,t.currtypename "
    		+" from bd_currrate r ,bd_currtype t "
    		+" where r.PK_CURRTYPE=t.pk_currtype and  r.RATEDATE='"+date+"'";
		
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				vos = new CurrrateVO[os.length];
				for(int i=0;i<os.length;i++){
					CurrrateVO vo = new CurrrateVO();
					Object[] o = (Object[])os[i];
					vo.setPK_CURRTYPE(o[0]==null?"":o[0].toString().trim());
					vo.setRATE(new UFDouble(o[1]==null?"0":o[1].toString().trim()));
					vo.setRATEDATE(new UFDate(o[2].toString().trim()));
					vo.currtypename  = o[3]==null?"":o[3].toString().trim();
					v.addElement(vo);
				}
				if(v.size()>0){
					v.copyInto(vos);
				}
			}
    	}finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
		return vos;
    }
    public BankSubjContrastVO[] queryBankSubjContrastVOByPkglorgbook(String pk_glorgbook)throws Exception{
    	
    	if(pk_glorgbook==null){
    		return null;
    	}
    	BankSubjContrastVO[] vos = null;
    	
    	String sql="select pk_banksubjcontrast,pk_system,pk_glorgbook,pk_accsubj,"
    		+"def1,def2,def3,def4,def5  "
    		+" from bank_banksubjcontrast "
    		+" where pk_glorgbook='"+pk_glorgbook+"'"
    		+" order by def1";

		
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
				vos = new BankSubjContrastVO[os.length];
				for(int i=0;i<os.length;i++){
					BankSubjContrastVO vo = new BankSubjContrastVO();
					Object[] o = (Object[])os[i];
					vo.setPk_banksubjcontrast(o[0]==null?"":o[0].toString().trim());
					vo.setPk_system(o[1]==null?"":o[1].toString().trim());
					vo.setPk_glorgbook(o[2]==null?"":o[2].toString().trim());
					vo.setPk_accsubj(o[3]==null?"":o[3].toString().trim());
					vo.setDef1(o[4]==null?"":o[4].toString().trim());
					vo.setDef2(o[5]==null?"":o[5].toString().trim());
					vo.setDef3(o[6]==null?"":o[6].toString().trim());
					vo.setDef4(o[7]==null?"":o[7].toString().trim());
					vo.setDef5(o[8]==null?"":o[8].toString().trim());
					v.addElement(vo);
				}
				if(v.size()>0){
					v.copyInto(vos);
				}
			}
    	}finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
    	return vos;
    }
	public double getRateByTypeDate(String pk_currinfo,UFDate date) throws Exception{

		double rate = 1.0f;
		String sql = "select rate from bd_currrate where "
			+" pk_currinfo='"+pk_currinfo+"'"
			+" and ratedate='"+date.toString()+"'";
		PersistenceManager ps=null;
		String strrate = null;
		try{
            ps=PersistenceManager.getInstance();
            JdbcSession sesison =ps.getJdbcSession();
            ArrayProcessor a=new ArrayProcessor();
            Object [] res=(Object [])sesison.executeQuery(sql, a);
            if(res!=null && res.length!=0){
			    strrate =res[0].toString().trim();
            }

		} finally {
      		try {
      			if (ps != null) {
      				ps.release();
      			}
      		}catch (Exception e) {}
      		
        }
		if(strrate!=null){
			UFDouble ufrate = new UFDouble(strrate);
			rate = ufrate.doubleValue();
		}
		return rate;
	}
	public String getPksubjByCorpAndCode(String pk_glorgbook,String subjcode)throws Exception{
		
		if(pk_glorgbook==null || subjcode==null){
			return null;
		}
		String pk_accsubj = null;
		String sql="select pk_accsubj from bd_accsubj where pk_glorgbook='"+pk_glorgbook+"'"
		+" and subjcode = '"+subjcode+"'"; 
		PersistenceManager ps=null;
		try{
            ps=PersistenceManager.getInstance();
            JdbcSession sesison =ps.getJdbcSession();
            ArrayProcessor a=new ArrayProcessor();
            Object [] res=(Object [])sesison.executeQuery(sql, a);
            if(res!=null && res.length!=0){
            	pk_accsubj =res[0].toString().trim();
            }

		} finally {
      		try {
      			if (ps != null) {
      				ps.release();
      			}
      		}catch (Exception e) {}
      		
        }
		return pk_accsubj;
	}
	public String getpkaccsubjByPkglorgbookandBalkmbm(String pk_glorgbook,String balkmbm) throws Exception{
		String pk_accsubj = null;
		String sql="select pk_accsubj from bank_ncsubjcontrast where pk_glorgbook='"+pk_glorgbook+"' and  balbank_kmbm='"+balkmbm+"'";
		PersistenceManager ps=null;
		try{
            ps=PersistenceManager.getInstance();
            JdbcSession sesison =ps.getJdbcSession();
            ArrayProcessor a=new ArrayProcessor();
            Object [] res=(Object [])sesison.executeQuery(sql, a);
            if(res==null || res.length==0){
              	Logger.info(sql);
                	throw new Exception("账簿:"+pk_glorgbook+",余额对账科目:"+balkmbm+",查找NC科目主键有误！");
            }
            if(res!=null && res.length!=0){
            	
            	pk_accsubj =res[0].toString().trim();
            }
		}finally {
      		try {
      			if (ps != null) {
      				ps.release();
      			}
      		}catch (Exception e) {}
      		
        }
		return pk_accsubj;
	}
	public String queryBankSubjContrastVOByVO(BankSubjContrastVO vo)throws Exception{
		if(vo == null){
			return null;
		}
		BankSubjContrastVO revo = null;
		String pk_accsubj = null;
		String sql  = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select pk_accsubj from bank_banksubjcontrast "
				+ " where pk_system='"+ vo.getPk_system()+"' "
				+ " and pk_glorgbook='"+vo.getPk_glorgbook()+"'");
		if(vo.getDef1()!=null && !vo.getDef1().equals("")){
			sb.append(" and def1='"+vo.getDef1().trim()+"'");
		}
		if(vo.getDef2()!=null && !vo.getDef2().equals("")){
			sb.append(" and def2='"+vo.getDef2().trim()+"'");
		}
		if(vo.getDef3()!=null && !vo.getDef3().equals("")){
			sb.append(" and def3='"+vo.getDef3().trim()+"'");
		}
		if(vo.getDef4()!=null && !vo.getDef4().equals("")){
			sb.append(" and def4='"+vo.getDef4().trim()+"'");
		}
		if(vo.getDef5()!=null && !vo.getDef5().equals("")){
			sb.append(" and def5='"+vo.getDef5().trim()+"'");
		}
		sql = sb.toString();
		PersistenceManager ps=null;
		try{
            ps=PersistenceManager.getInstance();
            JdbcSession sesison =ps.getJdbcSession();
            ArrayProcessor a=new ArrayProcessor();
            Object [] res=(Object [])sesison.executeQuery(sql, a);
            if(res!=null && res.length!=0){
            	pk_accsubj =res[0].toString().trim();
            }

		} finally {
      		try {
      			if (ps != null) {
      				ps.release();
      			}
      		}catch (Exception e) {}
      		
        }
		return pk_accsubj;
	}

	public BankSubjContrastVO[] queryBankSubjContrastVOsByVO(BankSubjContrastVO vo) throws Exception{
		BankSubjContrastVO[] vos = null;
		if(vo == null){
			return null;
		}
		String sql  = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select pk_accsubj,def1,def2,def3,def4,def5 from bank_banksubjcontrast "
				+ " where pk_system='"+ vo.getPk_system()+"' "
				+ " and pk_glorgbook='"+vo.getPk_glorgbook()+"'");
		if(vo.getDef1()!=null && !vo.getDef1().equals("")){
			sb.append(" and def1='"+vo.getDef1().trim()+"'");
		}
		if(vo.getDef2()!=null && !vo.getDef2().equals("")){
			sb.append(" and def2='"+vo.getDef2().trim()+"'");
		}
		if(vo.getDef3()!=null && !vo.getDef3().equals("")){
			sb.append(" and def3='"+vo.getDef3().trim()+"'");
		}
		if(vo.getDef4()!=null && !vo.getDef4().equals("")){
			sb.append(" and def4='"+vo.getDef4().trim()+"'");
		}
		if(vo.getDef5()!=null && !vo.getDef5().equals("")){
			sb.append(" and def5='"+vo.getDef5().trim()+"'");
		}
		sql = sb.toString();
		Object[] res = null; 
//		PersistenceManager ps=null;
    	PersistenceManager sessionManager=null;
		try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			res = list.toArray();
//            ps=PersistenceManager.getInstance();
//            JdbcSession sesison =ps.getJdbcSession();
//            ArrayProcessor a=new ArrayProcessor();
//            Object [] res=(Object [])sesison.executeQuery(sql, a);
//
//			res = list.toArray();
            Vector v = new Vector();
            if(res!=null && res.length!=0){
            	for(int i=0;i<res.length;i++){
            		Object[] o = (Object[])res[i];
                	BankSubjContrastVO tmpvo = new BankSubjContrastVO();
                	tmpvo.setPk_system(vo.getPk_system());
                	tmpvo.setPk_glorgbook(vo.getPk_glorgbook());
                	tmpvo.setPk_accsubj(o[0]==null?"":o[0].toString());
                	tmpvo.setDef1(o[1]==null?"":o[1].toString());
                	tmpvo.setDef2(o[2]==null?"":o[2].toString());
                	tmpvo.setDef3(o[3]==null?"":o[3].toString());
                	tmpvo.setDef4(o[4]==null?"":o[4].toString());
                	tmpvo.setDef5(o[5]==null?"":o[5].toString());
                	v.addElement(tmpvo);
            	}

            }
            if(v.size()>0){
            	vos = new BankSubjContrastVO[v.size()];
            	v.copyInto(vos);
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
	public void deleteBankSubjByPksystempkglorgbook(String pk_system, String[] pk_glorgbooks) throws Exception{
		if(pk_system==null || pk_glorgbooks==null || pk_glorgbooks.length==0){
			return ;
		}
		String sql = null;
    	PersistenceManager sessionManager=null;
    	try{
		sessionManager = PersistenceManager.getInstance();
		JdbcSession session = sessionManager.getJdbcSession();
		for(int i=0;i<pk_glorgbooks.length;i++){
			sql="delete from bank_banksubjcontrast "
				+" where pk_system='"+pk_system+"'"
				+" and pk_glorgbook='"+pk_glorgbooks[i]+"'";
			session.executeUpdate(sql);
		}
		
    	} finally {
    		try {
    			if (sessionManager != null) {
    				sessionManager.release();
    			}
    		}catch (Exception e) {}
  		
    	}
	}

	public String querySubjOrient(String pk_accsubj)throws Exception{
    	if(pk_accsubj==null){
    		return null;
    	}
    	String orient = null;
    	String sql="select balanorient from bd_accsubj where pk_accsubj ='"+pk_accsubj+"'";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			if(os!=null && os.length!=0){
			    Object[] o = (Object[])os[0];
			    orient =o[0].toString().trim();
			}
			
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
    	return orient ;
    	
    }
    public String[] getBdinfosByPkaccsubj(String pk_accsubj) throws Exception{
    	if(pk_accsubj==null){
    		return null;
    	}
    	String[] bdinfos= null;
    	String sql="select pk_bdinfo from bd_subjass where pk_accsubj ='"+pk_accsubj+"'";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			Vector v = new Vector();
			if(os!=null && os.length!=0){
			    
			    for(int i=0;i<os.length;i++){
			    	Object[] o = (Object[])os[i];
			    	v.addElement(o[0].toString().trim());
			    }
			}
			if(v.size()>0){
				bdinfos = new String[v.size()];
				v.copyInto(bdinfos);
			}
			
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
    	return bdinfos;
    }
    public boolean getIsChkBal(String pk_glorgbook,UFDate date )throws Exception{
    	
		if(pk_glorgbook==null || date==null){
			throw new BusinessException("查询昨日是否余额对账时，查询条件‘账簿’和‘日期’为空！");
		}
//		String glorgbookname = getStrByPk("bd_glorgbook","glorgbookname","pk_glorgbook",pk_glorgbook);
		boolean re = false;
    	String sql="select * from bank_glchk where pk_corp='"+pk_glorgbook+"' and chkdate='"+date+"'";
  	    PersistenceManager ps=null;
        try {
            ps=PersistenceManager.getInstance();
            JdbcSession sesison =ps.getJdbcSession();
            ArrayProcessor a=new ArrayProcessor();
            Object [] res=(Object [])sesison.executeQuery(sql, a);
            if(res==null || res.length==0){
            	//针对新增机构加校验
            	/*
            	Object o = new Object();
            	sql=" select count(*) from bank_glchk where pk_corp='"+pk_glorgbook+"' ";
            	res=(Object [])sesison.executeQuery(sql, a);     			
     			if(res!=null && res.length!=0 && res[0]!=null){
     				o = res[0];
     			}     		
     			String str = o.toString();
            	if(str.equals("0")){
            		re = true;
            	}else{*/
            		re = false;
            	//}
//              	throw new Exception(glorgbookname+"昨日未进行余额对账");
              }else{
            	  sql="select * from bank_glchk g,bank_glchkresult gl "
            		  +" where g.pk_glchk=gl.pk_glchk and gl.chkstate='0' and "
            		  +" g.pk_corp='"+pk_glorgbook+"' and g.chkdate='"+date+"'";
            	  res=(Object [])sesison.executeQuery(sql, a);
            	  if(res==null || res.length==0){
            		  re = true;
            		  
            	  }else{
            		  re = false;
            	  }
              }
            
        } finally {
    		try {
    			if (ps != null) {
    				ps.release();
    			}
    		}catch (Exception e) {}
    		
        }
		
		return re;
    	
    }
    
 public boolean getIsChkBal1(String pk_glorgbook,UFDate date )throws Exception{
    	
		if(pk_glorgbook==null || date==null){
			throw new BusinessException("查询昨日是否余额对账时，查询条件‘账簿’和‘日期’为空！");
		}
//		String glorgbookname = getStrByPk("bd_glorgbook","glorgbookname","pk_glorgbook",pk_glorgbook);
		boolean re = false;
    	String sql="select * from bank_glchk where pk_corp='"+pk_glorgbook+"' and chkdate='"+date+"'";
  	    PersistenceManager ps=null;
        try {
            ps=PersistenceManager.getInstance();
            JdbcSession sesison =ps.getJdbcSession();
            ArrayProcessor a=new ArrayProcessor();
            Object [] res=(Object [])sesison.executeQuery(sql, a);
            if(res==null || res.length==0){
            	//针对新增机构加校验
            	/*
            	Object o = new Object();
            	sql=" select count(*) from bank_glchk where pk_corp='"+pk_glorgbook+"' ";
            	res=(Object [])sesison.executeQuery(sql, a);     			
     			if(res!=null && res.length!=0 && res[0]!=null){
     				o = res[0];
     			}     		
     			String str = o.toString();
            	if(str.equals("0")){
            		re = true;
            	}else{*/
            		re = false;
            	//}
//              	throw new Exception(glorgbookname+"昨日未进行余额对账");
              }else{
            	  sql="select * from bank_glchk g,bank_glchkresult gl "
            		  +" where g.pk_glchk=gl.pk_glchk and gl.chkstate='0' and "
            		  +" g.pk_corp='"+pk_glorgbook+"' and g.chkdate='"+date+"'";
            	  res=(Object [])sesison.executeQuery(sql, a);
            	  if(res==null || res.length==0){
            		  re = true;
            		  
            	  }else{
            		  re = false;
            	  }
              }
            
        } finally {
    		try {
    			if (ps != null) {
    				ps.release();
    			}
    		}catch (Exception e) {}
    		
        }
		
		return re;
    	
    }
    
    public boolean IsImport(String pk_glorgbook,UFDate date ) throws Exception{    	
		if(pk_glorgbook==null || date==null){
			throw new BusinessException("查询昨日是否余额对账时，查询条件‘账簿’和‘日期’为空！");
		}
		boolean re = false;
    	String sql="select * from bank_glchk where pk_corp='"+pk_glorgbook+"' and chkdate='"+date+"'";
    	sql +=" and chkstate ='1'";
  	    PersistenceManager ps=null;
        try {
			ps = PersistenceManager.getInstance();
			JdbcSession sesison = ps.getJdbcSession();
			ArrayProcessor a = new ArrayProcessor();
			Object[] res = (Object[]) sesison.executeQuery(sql, a);
			if (res == null || res.length == 0) {
				re = false;
			}else{
				re = true;
			}

		} finally {
    		try {
    			if (ps != null) {
    				ps.release();
    			}
    		}catch (Exception e) {}
    		
        }		
		return re;    	
    }
    
    
    public String importLastDay(String pk_glorgbook,UFDate date ) throws Exception{    	
		if(pk_glorgbook==null || date==null){
			throw new BusinessException("查询昨日是否余额对账时，查询条件‘账簿’和‘日期’为空！");
		}
		String srtDate="";
		//String sql="select max(chkdate) from bank_glchk where pk_corp='"+pk_glorgbook+"' and chkdate='"+date+"'";
    	String sql="select max(chkdate) from bank_glchk where pk_corp='"+pk_glorgbook+"' ";
    	sql +=" and chkstate ='1'";
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
        try {
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			os = list.toArray();
			if(os!=null && os.length!=0){
			    Object[] o = (Object[])os[0];
			    srtDate =o[0].toString().trim();
			}
			
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }	
		return srtDate;    	
    }
    
    public HashMap getHmByPkglorgbook(String pk_glorgbook) throws Exception{
    	HashMap hm = new HashMap();
		String sql="select balbank_kmbm,pk_accsubj from bank_ncsubjcontrast where pk_glorgbook='"+pk_glorgbook+"' ";
		Object[] res = null;
		PersistenceManager sessionManager = null;
		try {
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List) session.executeQuery(sql,
					new ArrayListProcessor());
			res = list.toArray();
			if (res != null && res.length != 0) {
				for (int i = 0; i < res.length; i++) {
					Object[] o = (Object[]) res[i];
					hm.put(o[0].toString().trim(), o[1].toString().trim());
				}
			}else{
				throw new Exception("账簿:"+pk_glorgbook+",余额对账科目没有进行设置！");
			}

		} finally {
			try {
				if (sessionManager != null) {
					sessionManager.release();
				}
			} catch (Exception e) {
			}

		}
		return hm;
	}
    
    public HashMap querySubjOrientHm(String pk_glorgbook)throws Exception{    	
    	HashMap hm = new HashMap();
    	String sql="select pk_accsubj ,balanorient from bd_accsubj where pk_glorgbook ='"+pk_glorgbook+"'";
    	Object[] res = null;
		PersistenceManager sessionManager = null;
		try {
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			List list = (List) session.executeQuery(sql,
					new ArrayListProcessor());
			res = list.toArray();
			if (res != null && res.length != 0) {
				for (int i = 0; i < res.length; i++) {
					Object[] o = (Object[]) res[i];
					hm.put(o[0].toString().trim(), o[1].toString().trim());
				}
			}

		} finally {
			try {
				if (sessionManager != null) {
					sessionManager.release();
				}
			} catch (Exception e) {
			}

		}
    	return hm ;    	
    }
    
    public AccsubjVO[] getNcAccsujVosByCondition(String condition)throws Exception{      	
    	AccsubjVO[] result = null;
    	StringBuffer sb = new StringBuffer();
    	sb.append(" select pk_accsubj,outflag,subjcode,balanorient  ");
    	sb.append(" from bd_accsubj ");
    	sb.append(" where 1=1 ");
    	sb.append( condition);
    	PersistenceManager sessionManager=null;
    	Object[] os = null;
    	try{
			sessionManager = PersistenceManager.getInstance();	JdbcSession session= sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sb.toString(), new ArrayListProcessor());
			os = list.toArray();
			if(os!=null && os.length!=0){
				result = new AccsubjVO[os.length];
				for(int i=0;i<os.length;i++){					
					Object[] o = (Object[])os[i];
					result[i] = new AccsubjVO();
					result[i].setPk_accsubj(o[0].toString().trim());
					result[i].setOutflag(new UFBoolean(o[1].toString().trim()));
					result[i].setSubjcode(o[2].toString().trim());
					result[i].setBalanorient(Integer.parseInt(o[3].toString().trim()));
				}
			}
    		
    	} finally {
      		try {
      			if (sessionManager != null) {
      				sessionManager.release();
      			}
      		}catch (Exception e) {}
      		
        }
    	return result;    
    }   
}
