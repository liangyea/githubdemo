package nc.ui.bankpub.ncsubject;

import nc.ui.bd.ref.DefaultRefModel;

 

public class GlOrgBookRefModel extends DefaultRefModel {

    private String paraStr = null;

 

    public GlOrgBookRefModel() {

       super();

       init();

       // TODO Auto-generated constructor stub

    }

 

    public GlOrgBookRefModel(String refNodeName) {

       super(refNodeName);

       init();

       // TODO Auto-generated constructor stub

    }

    public void init()

    {


        setRefNodeName("会计主体账簿");

       setRefTitle("会计主体账簿");

    setFieldCode(new String[]{"glorgbookcode",
    		                 "glorgbookname"
    		});

       setFieldName(new String[]{"账簿编码","账簿名称"});
       setShownColumns(new int[]{0,1,2});
       setPkFieldCode("pk_glorgbook");

       setTableName("bd_glorgbook");

    }

 

 

}

