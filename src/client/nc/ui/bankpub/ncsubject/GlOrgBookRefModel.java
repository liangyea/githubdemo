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


        setRefNodeName("��������˲�");

       setRefTitle("��������˲�");

    setFieldCode(new String[]{"glorgbookcode",
    		                 "glorgbookname"
    		});

       setFieldName(new String[]{"�˲�����","�˲�����"});
       setShownColumns(new int[]{0,1,2});
       setPkFieldCode("pk_glorgbook");

       setTableName("bd_glorgbook");

    }

 

 

}

