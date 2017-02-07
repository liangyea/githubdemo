package nc.impl.bankpub.pub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.IPubTools;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.vo.bankpub.currrate.CurrrateVO;
import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.bd.b02.AccsubjVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;

public class ImplPubTools implements IPubTools {

	public String getPubInitSys(String initcode) throws BusinessException {
		// TODO �Զ����ɷ������
    	String value=null;
		try{
			DataItfDAO dao = new DataItfDAO();
			value = dao.getPubInitSys(initcode);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		 }
        return value;
	}

	public String getStrByPk(String tablename, String selfield,
			String wherefield, String value) throws BusinessException {
		// TODO �Զ����ɷ������
		String str = null;
		if(tablename==null || selfield==null ||  wherefield==null || value==null){
			return null;
		}
		try{
			DataItfDAO dao = new DataItfDAO();
			str = dao.getStrByPk(tablename, selfield, wherefield, value);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		 }
		return str;
	}
	//for �����޸Ļ�ƿ�Ŀ
	public String getStrByPk1(String tablename, String selfield,
			String wherefield, String value) throws BusinessException {
		// TODO �Զ����ɷ������
		String str = null;
		if(tablename==null || selfield==null ||  wherefield==null || value==null){
			return null;
		}
		try{
			DataItfDAO dao = new DataItfDAO();
			str = dao.getStrByPk1(tablename, selfield, wherefield, value);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		 }
		return str;
	}
	public String[] split(String str, String sp) throws BusinessException {
		// TODO �Զ����ɷ������
        Vector v = new Vector();
        String [] strs = null;
        if(str==null) return null;
        if(sp==null) return new String [] {str};
//        str=str.trim().substring(1); //ȥ����һ��"/"
        StringBuffer sb = new StringBuffer();
        String chr = null;
        for (int i = 0; i < str.length(); i++) {
            chr = str.substring(i,i+1);
            if(chr.equals(sp)){
                v.add(sb.toString());
                sb = new StringBuffer();
            } else {
                sb.append(chr);
                if(i==(str.length()-1)){
                    v.add(sb.toString());
                }
            }
        }
        if(v.size()>0){
            strs = new String [v.size()];
            v.copyInto(strs);
        }
        return strs;
	}

	public String[] getBdinfosByPkaccsubj(String pk_accsubj) throws BusinessException{
		if(pk_accsubj==null){
			return null;
		}
		String[] bdinfos = null;
		try{
			DataItfDAO dao = new DataItfDAO();
			bdinfos = dao.getBdinfosByPkaccsubj(pk_accsubj);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		return bdinfos;
	}
	
	public String getPkcorpByPkglorgbook(String pk_glorgbook) throws BusinessException {
		// TODO �Զ����ɷ������
		String pk_corp = null;
		try{
			DataItfDAO dao = new DataItfDAO();
			pk_corp = dao.getPkcorpByPkglorgbook(pk_glorgbook);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		 }
		return pk_corp;
	}

	public String getpkaccsubjByPkglorgbookandBalkmbm(String pk_glorgbook,String balkmbm) throws BusinessException {
		String pk_accsubj = null;
		if(pk_glorgbook==null || balkmbm==null){
			
			return null;
		}
		try{
			DataItfDAO dao = new DataItfDAO();
			pk_accsubj = dao.getpkaccsubjByPkglorgbookandBalkmbm(pk_glorgbook, balkmbm);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
			
		}
		return pk_accsubj;
	}
	public String[] getPkglorgbookByPkcorp(String pk_corp) throws BusinessException {
		// TODO �Զ����ɷ������
    	String[] pk_glorgbook=null;
		try{
			DataItfDAO dao = new DataItfDAO();
			pk_glorgbook = dao.getPkglorgbookByPkcorp(pk_corp);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		 }
        return pk_glorgbook;
	}


	public CurrrateVO[] readCurrRateFile(SystemTypeVO vo ,UFDate date) throws BusinessException {
		// TODO �Զ����ɷ������
		if(vo == null){
			return null;
		}
		CurrrateVO[] cvos = null;
		try{
			String strdate = date.toString();
			String filename = vo.getRatefilename()
			+strdate.substring(0, 4)+strdate.substring(5, 7)+strdate.substring(8, 10)
			+vo.getSuffixname();
			if(vo.getTransmether().trim().equals("Y")) // FTP
			{
				
		    	 String ftpHost=vo.getIPaddress().trim();  //ftp ��ַ
		    	 int ftpPort=Integer.parseInt(vo.getPort().trim()); //ftp�˿�
		    	 Integer port = new Integer(ftpPort);
		    	 String username=vo.getUsername().trim(); 
		    	 String password=vo.getPassword().trim();
		    	 String ftpPath=vo.getFtppath().trim();
		    	 Logger.info("ftp��ַ:"+ftpHost);
		    	 Logger.info("ftp�˿�:"+port.toString());
		    	 Logger.info("�û���:"+username);
		    	 Logger.info("����:"+password);
		    	 Logger.info("·��:"+ftpPath);
		    	 FtpClient ftpClient = new FtpClient();
		         ftpClient.openServer(ftpHost,ftpPort);
		         ftpClient.login(username,password);
		         ftpClient.binary();
		         ftpClient.cd(ftpPath);
		         TelnetInputStream tis = null;
		         tis = ftpClient.get(filename);
		         InputStreamReader isr = new InputStreamReader(tis);
		         BufferedReader br = new BufferedReader(isr);
		         String strLine = null;
		         Vector v = new Vector();
		         Logger.info("=======��ʼ���ļ�========");
		         while((strLine=br.readLine())!=null){
		        	 CurrrateVO cvo = new CurrrateVO();
		        	 String items []=split(strLine,vo.getSeparator().trim());
		        	 if(items[1].equals("1")){
		        		 Logger.info("++++++++���ǵ��ջ���++++++++");
		        	 }
		        	 String sdate = null;
		        	 if(vo.getDateformat().trim().equals("yyyymmdd")){
		        		 sdate = items[0].substring(0,4)+"-"+items[0].substring(4,6)+"-"+items[0].substring(6,8);
		        	 }else if(vo.getDateformat().trim().equals("yyyy/mm/dd")){
		        		 sdate = items[0].substring(0,4)+"-"+items[0].substring(5,7)+"-"+items[0].substring(8,10);
		        	 }else{
		        		 sdate = items[0].trim(); 
		        	 }
		        	 String pk_currtype = getStrByPk("bank_currcontrast", "pk_currtype", "bank_currcode",items[2]);
		        	 cvo.setPK_CORP("0001");
		        	 cvo.setRATEDATE(new UFDate(sdate)); 
		        	 cvo.setPK_CURRTYPE(pk_currtype);
		        	 cvo.setRATE(new UFDouble(items[4]));
		        	 v.addElement(cvo);
		         }
		         if(v.size()>0){
		        	 cvos = new CurrrateVO[v.size()];
		        	 v.copyInto(cvos);
		         }
		         br.close();
		         ftpClient.closeServer();
				
			}
			else // ֱ�Ӷ������ļ�
			{
				Logger.info("=======��ʼ�������ļ�========");
				File dir = new File(vo.getLocalpath().trim());
				BufferedReader reader = new BufferedReader(new FileReader(dir));
			    String strLine = null;
			    Vector v = new Vector();
			    while((strLine=reader.readLine())!=null){
		        	 CurrrateVO cvo = new CurrrateVO();
		        	 String items []=split(strLine,vo.getSeparator().trim());
		        	 if(items[1].equals("1")){
		        		 Logger.info("++++++++���ǵ��ջ���++++++++");
		        	 }
		        	 String sdate = null;
		        	 if(vo.getDateformat().trim().equals("yyyymmdd")){
		        		 sdate = items[0].substring(0,4)+"-"+items[1].substring(4,6)+"-"+items[1].substring(5,8);
		        	 }else if(vo.getDateformat().trim().equals("yyyy/mm/dd")){
		        		 sdate = items[0].substring(0,4)+"-"+items[1].substring(5,7)+"-"+items[1].substring(8,10);
		        	 }else{
		        		 sdate = items[0].trim(); 
		        	 }
		        	 String pk_currtype = getStrByPk("bank_currcontrast", "pk_currtype", "bank_currcode",items[2]);
		        	 cvo.setPK_CORP("0001");
		        	 cvo.setRATEDATE(new UFDate(sdate)); 
		        	 cvo.setPK_CURRTYPE(pk_currtype);
		        	 cvo.setRATE(new UFDouble(items[4]));
		        	 v.addElement(cvo);
			    }
			    if(v.size()>0){
		        	 cvos = new CurrrateVO[v.size()];
		        	 v.copyInto(cvos);
			    }
			}
		    
		}catch(Exception e){
			e.printStackTrace();
			Logger.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		 }
		return cvos;
	}
    public void ExcuteUpdateSQL(String sql)throws Exception {
    	PersistenceManager ps=null;
        try {
            ps=PersistenceManager.getInstance();
            JdbcSession sesison =ps.getJdbcSession();
            sesison.executeUpdate(sql);
        } finally {
    		try {
    			if (ps != null) {
    				ps.release();
    			}
    		}catch (Exception e) {}
    		
        }
    }
	public String getPksubjByCorpAndCode(String pk_glorgbook,String subjcode)throws BusinessException {
		
		if(pk_glorgbook==null || subjcode==null){
			return null;
		}
		String pk_accsubj = null;
		try{
			DataItfDAO dao = new DataItfDAO();
			pk_accsubj = dao.getPksubjByCorpAndCode(pk_glorgbook, subjcode);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		 }
		
		return pk_accsubj;
	}

	public UFDate getServerDate() throws BusinessException {
		// TODO �Զ����ɷ������
		return new UFDate(new Date());
	}
	
	public HashMap getHmByPkglorgbook(String pk_glorgbook) throws BusinessException {
		HashMap hm = new HashMap();		
		try{
			DataItfDAO dao = new DataItfDAO();
			hm = dao.getHmByPkglorgbook(pk_glorgbook);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
			
		}
		return hm;
	}
	
	public HashMap getSubjOrientByGlorgbook(String pk_glorgbook) throws BusinessException {
		HashMap hm = new HashMap();		
		try{
			DataItfDAO dao = new DataItfDAO();
			hm = dao.querySubjOrientHm(pk_glorgbook);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
			
		}
		return hm;
	}
	
	public AccsubjVO[] getNcAccsujVosByCondition(String condition) throws BusinessException {
		AccsubjVO[] vos = null;
		DataItfDAO dao = new DataItfDAO();
		try {
			vos = dao.getNcAccsujVosByCondition(condition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vos;
	}
}
