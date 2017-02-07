package nc.impl.bankpub.pub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.NamingException;

import nc.bs.pub.DataManageObject;
import nc.vo.bankpub.eliminaterule.EliminateRuleVO;
import nc.vo.bankpub.eliminaterule.RuleSetVO;
import nc.vo.bankpub.filedefine.FileDefineVO;

public class RuleSetDMO extends DataManageObject {

	public RuleSetDMO() throws NamingException {
		super();
	}
	
	/*
	 * 根据系统外查询规则*/
	public RuleSetVO[] queryBySystemPK(String fk_system) throws Exception
	{
		RuleSetVO[] result = null;
    	String sql = "select pk_ruleset,fk_system,rulecode,rulename from bank_ruleset where fk_system='"+fk_system+"'";
    	Connection con=null;
    	PreparedStatement stmt = null;
    	try{
			con = getConnection();	
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Vector retV = new Vector();
			while(rs.next())
			{
					RuleSetVO tmpVO = new RuleSetVO();
					tmpVO.setPk_ruleset(rs.getString("pk_ruleset"));
					tmpVO.setFk_system(rs.getString("fk_system"));
					tmpVO.setRulecode(rs.getString("rulecode"));
					tmpVO.setRulename(rs.getString("rulename"));
					FileDefineVO[] defines = queryCols(tmpVO.getPk_ruleset());
					tmpVO.setFileDefines(defines);					
					EliminateRuleVO[] colValues = queryValues(tmpVO.getPk_ruleset());
					tmpVO.setColValues(colValues);
					retV.add(tmpVO);
				}
   		if(retV.size()>0)
   		{
   			result = new RuleSetVO[retV.size()];
   			retV.copyInto(result);
   		}
   			
    	} finally {
      		try {
      			if(con!=null)
      			{
          			stmt.close();
          			con.close();
      			}
      		}catch (Exception e) {}
      		
        }
    	return result;
	}
	
	/*
	 * 根据系统外键删除*/
	public void delBySystemPK(String fk_system) throws Exception
	{
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "delete from bank_ruleset where fk_system='"+fk_system+"'";
		String sql2 = "delete from bank_ruleset_col where fk_ruleset in (select pk_ruleset from bank_ruleset where fk_system='"+fk_system+"')";
		String sql3 = "delete from bank_eliminaterule where fk_ruleset in (select pk_ruleset from bank_ruleset where fk_system='"+fk_system+"')";
    	try{
    		con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
			stmt = con.prepareStatement(sql2);
			stmt.executeUpdate();
			stmt = con.prepareStatement(sql3);
			stmt.executeUpdate();
    	} finally {
      		try {
      			if(con!=null)
      			{
          			stmt.close();
          			con.close();
      			}
      		}catch (Exception e) {}
        }
	}
	
	/*
	 * 根据规则主键删除*/
	public void delByPK(String pk_ruleset) throws Exception
	{
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "delete from bank_ruleset where pk_ruleset='"+pk_ruleset+"'";
		String sql2 = "delete from bank_ruleset_col where fk_ruleset = '"+pk_ruleset+"'";
		String sql3 = "delete from bank_eliminaterule where fk_ruleset = '"+pk_ruleset+"'";
    	try{
    		con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
			stmt = con.prepareStatement(sql2);
			stmt.executeUpdate();
			stmt = con.prepareStatement(sql3);
			stmt.executeUpdate();
    	} finally {
      		try {
      			if(con!=null)
      			{
          			stmt.close();
          			con.close();
      			}
      		}catch (Exception e) {}
        }
	}
	
	/*
	 * 插入新规则*/
	public void insert(RuleSetVO vo) throws Exception
	{
		if(vo==null)
			return;
		String pk_ruleSet = vo.getPk_ruleset()==null?getOID():vo.getPk_ruleset();
		String sql = "insert into bank_ruleset(pk_ruleset,fk_system,rulecode,rulename) values('"+pk_ruleSet+"','"+vo.getFk_system()+"','"+vo.getRulecode()+"','"+vo.getRulename()+"')";
		Connection con = null;
		PreparedStatement stmt = null;
    	try{
    		con = getConnection();
    		stmt = con.prepareStatement(sql);
    		stmt.executeUpdate();
			FileDefineVO[] fileDefines = vo.getFileDefines();
			if(fileDefines!=null && fileDefines.length>0)
			for(int i=0;i<fileDefines.length;i++)
			{
				FileDefineVO tempVO = fileDefines[i];
				String sql2 = "insert into bank_ruleset_col(fk_ruleset,fk_filedefine) values('"+pk_ruleSet+"','"+tempVO.getPk_filedefine()+"')";
				stmt = con.prepareStatement(sql2);
				stmt.executeUpdate();
			}
			
			EliminateRuleVO[] colValues = vo.getColValues();
			if(colValues!=null && colValues.length>0)
			for(int i=0;i<colValues.length;i++)
			{
				EliminateRuleVO tempVO = colValues[i];
				String sql2 = "insert into bank_eliminaterule(pk_eliminaterule, fk_ruleset, col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16) " +
						" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				stmt = con.prepareStatement(sql2);
				stmt.setString(1, getOID());
				stmt.setString(2, pk_ruleSet);
				if(tempVO.getCol1()!=null) stmt.setString(3, tempVO.getCol1()); else stmt.setNull(3, java.sql.Types.VARCHAR);
				if(tempVO.getCol2()!=null) stmt.setString(4, tempVO.getCol2()); else stmt.setNull(4, java.sql.Types.VARCHAR);
				if(tempVO.getCol3()!=null) stmt.setString(5, tempVO.getCol3()); else stmt.setNull(5, java.sql.Types.VARCHAR);
				if(tempVO.getCol4()!=null) stmt.setString(6, tempVO.getCol4()); else stmt.setNull(6, java.sql.Types.VARCHAR);
				if(tempVO.getCol5()!=null) stmt.setString(7, tempVO.getCol5()); else stmt.setNull(7, java.sql.Types.VARCHAR);
				if(tempVO.getCol6()!=null) stmt.setString(8, tempVO.getCol6()); else stmt.setNull(8, java.sql.Types.VARCHAR);
				if(tempVO.getCol7()!=null) stmt.setString(9, tempVO.getCol7()); else stmt.setNull(9, java.sql.Types.VARCHAR);
				if(tempVO.getCol8()!=null) stmt.setString(10, tempVO.getCol8()); else stmt.setNull(10, java.sql.Types.VARCHAR);
				if(tempVO.getCol9()!=null) stmt.setString(11, tempVO.getCol9()); else stmt.setNull(11, java.sql.Types.VARCHAR);
				if(tempVO.getCol10()!=null) stmt.setString(12, tempVO.getCol10()); else stmt.setNull(12, java.sql.Types.VARCHAR);				
				if(tempVO.getCol11()!=null) stmt.setString(13, tempVO.getCol11()); else stmt.setNull(13, java.sql.Types.VARCHAR);
				if(tempVO.getCol12()!=null) stmt.setString(14, tempVO.getCol12()); else stmt.setNull(14, java.sql.Types.VARCHAR);
				if(tempVO.getCol13()!=null) stmt.setString(15, tempVO.getCol13()); else stmt.setNull(15, java.sql.Types.VARCHAR);
				if(tempVO.getCol14()!=null) stmt.setString(16, tempVO.getCol14()); else stmt.setNull(16, java.sql.Types.VARCHAR);
				if(tempVO.getCol15()!=null) stmt.setString(17, tempVO.getCol15()); else stmt.setNull(17, java.sql.Types.VARCHAR);
				if(tempVO.getCol16()!=null) stmt.setString(18, tempVO.getCol16()); else stmt.setNull(18, java.sql.Types.VARCHAR);
				stmt.executeUpdate();
			}
    	} finally {
      		try {
      			if(con!=null)
      			{
          			stmt.close();
          			con.close();
      			}
      		}catch (Exception e) {}
        }
	}
	
	/*
	 * 更新规则*/
	public void update(RuleSetVO vo) throws Exception
	{
		delByPK(vo.getPk_ruleset());
		insert(vo);
	}
	
	/*
	 * 删除已设置过滤值*/
	public void delValue(String pk_eliminaterule) throws Exception
	{
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "delete from bank_eliminaterule where pk_eliminaterule='"+pk_eliminaterule+"'";
    	try{
    		con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
    	} finally {
      		try {
      			if(con!=null)
      			{
          			stmt.close();
          			con.close();
      			}
      		}catch (Exception e) {}
        }
	}
	
	public void insValue(EliminateRuleVO vo) throws Exception
	{
		delValue(vo.getPk_eliminaterule());
		String sql = "insert into bank_eliminaterule(pk_eliminaterule, fk_ruleset, col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16) " +
		" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement stmt = null;
    	try{
    		con = getConnection();
    		stmt = con.prepareStatement(sql);
    		EliminateRuleVO tempVO = vo;
			stmt.setString(1, vo.getPk_eliminaterule()==null?getOID():vo.getPk_eliminaterule());
			stmt.setString(2, vo.getFk_ruleset());
			if(tempVO.getCol1()!=null) stmt.setString(3, tempVO.getCol1()); else stmt.setNull(3, java.sql.Types.VARCHAR);
			if(tempVO.getCol2()!=null) stmt.setString(4, tempVO.getCol2()); else stmt.setNull(4, java.sql.Types.VARCHAR);
			if(tempVO.getCol3()!=null) stmt.setString(5, tempVO.getCol3()); else stmt.setNull(5, java.sql.Types.VARCHAR);
			if(tempVO.getCol4()!=null) stmt.setString(6, tempVO.getCol4()); else stmt.setNull(6, java.sql.Types.VARCHAR);
			if(tempVO.getCol5()!=null) stmt.setString(7, tempVO.getCol5()); else stmt.setNull(7, java.sql.Types.VARCHAR);
			if(tempVO.getCol6()!=null) stmt.setString(8, tempVO.getCol6()); else stmt.setNull(8, java.sql.Types.VARCHAR);
			if(tempVO.getCol7()!=null) stmt.setString(9, tempVO.getCol7()); else stmt.setNull(9, java.sql.Types.VARCHAR);
			if(tempVO.getCol8()!=null) stmt.setString(10, tempVO.getCol8()); else stmt.setNull(10, java.sql.Types.VARCHAR);
			if(tempVO.getCol9()!=null) stmt.setString(11, tempVO.getCol9()); else stmt.setNull(11, java.sql.Types.VARCHAR);
			if(tempVO.getCol10()!=null) stmt.setString(12, tempVO.getCol10()); else stmt.setNull(12, java.sql.Types.VARCHAR);				
			if(tempVO.getCol11()!=null) stmt.setString(13, tempVO.getCol11()); else stmt.setNull(13, java.sql.Types.VARCHAR);
			if(tempVO.getCol12()!=null) stmt.setString(14, tempVO.getCol12()); else stmt.setNull(14, java.sql.Types.VARCHAR);
			if(tempVO.getCol13()!=null) stmt.setString(15, tempVO.getCol13()); else stmt.setNull(15, java.sql.Types.VARCHAR);
			if(tempVO.getCol14()!=null) stmt.setString(16, tempVO.getCol14()); else stmt.setNull(16, java.sql.Types.VARCHAR);
			if(tempVO.getCol15()!=null) stmt.setString(17, tempVO.getCol15()); else stmt.setNull(17, java.sql.Types.VARCHAR);
			if(tempVO.getCol16()!=null) stmt.setString(18, tempVO.getCol16()); else stmt.setNull(18, java.sql.Types.VARCHAR);
			stmt.executeUpdate();
    	} finally {
      		try {
      			if(con!=null)
      			{
          			stmt.close();
          			con.close();
      			}
      		}catch (Exception e) {}
        }
	}
	
	public EliminateRuleVO[] queryValues(String fk_ruleset) throws Exception
	{
		EliminateRuleVO[] result = null;
		String sql = "select pk_eliminaterule, fk_ruleset, col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16" +
		" from bank_eliminaterule where fk_ruleset='"+fk_ruleset+"' order by col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16";
    	Connection con=null;
    	PreparedStatement stmt = null;
    	try{
			con = getConnection();	
			stmt = con.prepareStatement(sql);
			Vector retV = new Vector();
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
						EliminateRuleVO colVO = new EliminateRuleVO();
						colVO.setPk_eliminaterule(rs.getString("pk_eliminaterule"));
						colVO.setFk_ruleset(rs.getString("fk_ruleset"));
						colVO.setCol1(rs.getString("col1"));
						colVO.setCol2(rs.getString("col2"));
						colVO.setCol3(rs.getString("col3"));
						colVO.setCol4(rs.getString("col4"));
						colVO.setCol5(rs.getString("col5"));
						colVO.setCol6(rs.getString("col6"));
						colVO.setCol7(rs.getString("col7"));
						colVO.setCol8(rs.getString("col8"));
						colVO.setCol9(rs.getString("col9"));
						colVO.setCol10(rs.getString("col10"));
						colVO.setCol11(rs.getString("col11"));
						colVO.setCol12(rs.getString("col12"));
						colVO.setCol13(rs.getString("col13"));
						colVO.setCol14(rs.getString("col14"));
						colVO.setCol15(rs.getString("col15"));
						colVO.setCol16(rs.getString("col16"));
						retV.add(colVO);
					}
					if(retV.size()>0)
					{
						result = new EliminateRuleVO[retV.size()];
						retV.copyInto(result);
					}
    	} finally {
      		try {
      			if(con!=null)
      			{
          			stmt.close();
          			con.close();
      			}
      		}catch (Exception e) {}
        }
    	return result;
	}
	
	public FileDefineVO[] queryCols(String fk_ruleset) throws Exception
	{
		FileDefineVO[] result = null;
		String sql = "select t2.pk_filedefine, t2.pk_system, t2.xh, t2.columnname, t2.datatype, t2.length  from bank_ruleset_col t1,bank_filedefine t2 where t1.fk_ruleset='"+fk_ruleset+"' and t1.fk_filedefine=t2.pk_filedefine order by t2.xh";
    	Connection con=null;
    	PreparedStatement stmt = null;
    	try{
			con = getConnection();	
			stmt = con.prepareStatement(sql);
			Vector retV = new Vector();
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				FileDefineVO defineVO = new FileDefineVO();
				defineVO.setPk_filedefine(rs.getString("pk_filedefine"));
				defineVO.setPk_system(rs.getString("pk_system"));
				defineVO.setXh(new Integer(rs.getInt("xh")));
				defineVO.setColumnname(rs.getString("columnname"));
				defineVO.setDatatype(rs.getString("datatype"));
				defineVO.setLength(new Integer(rs.getInt("length")));
				retV.add(defineVO);
			}
			if(retV.size()>0)
			{
				result = new FileDefineVO[retV.size()];
				retV.copyInto(result);
			}
    	}finally {
      		try {
      			if(con!=null)
      			{
          			stmt.close();
          			con.close();
      			}
      		}catch (Exception e) {}
        }
    	return result;
	}
}
