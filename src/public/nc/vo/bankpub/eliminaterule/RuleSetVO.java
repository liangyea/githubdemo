package nc.vo.bankpub.eliminaterule;

import nc.vo.bankpub.filedefine.FileDefineVO;
import nc.vo.pub.SuperVO;

public class RuleSetVO extends SuperVO {
	
	private String pk_ruleset = null;
	private String rulename   = null;
	private String rulecode   = null;
	private String fk_system  = null;
	private FileDefineVO[] fileDefines = null;
	private EliminateRuleVO[] colValues = null;

	public EliminateRuleVO[] getColValues() {
		return colValues;
	}

	public void setColValues(EliminateRuleVO[] colValues) {
		this.colValues = colValues;
	}

	public FileDefineVO[] getFileDefines() {
		return fileDefines;
	}

	public void setFileDefines(FileDefineVO[] fileDefines) {
		this.fileDefines = fileDefines;
	}

	public String getPKFieldName() {
		return "pk_ruleset";
	}

	public String getFk_system() {
		return fk_system;
	}

	public void setFk_system(String fk_system) {
		this.fk_system = fk_system;
	}

	public String getPk_ruleset() {
		return pk_ruleset;
	}

	public void setPk_ruleset(String pk_ruleset) {
		this.pk_ruleset = pk_ruleset;
	}

	public String getRulecode() {
		return rulecode;
	}

	public void setRulecode(String rulecode) {
		this.rulecode = rulecode;
	}

	public String getRulename() {
		return rulename;
	}

	public void setRulename(String rulename) {
		this.rulename = rulename;
	}

	public String getParentPKFieldName() {
		return null;
	}

	public String getTableName() {
		return "bank_ruleset";
	}

	public String toString()
	{
		return rulename;
	}

}
