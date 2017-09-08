MEETMANAGE = function(){
  var pagesize = 10;
  var type_data = [['0', '��Ч'], ['1', '��Ч']];
	/**
	 * �÷���������ȡѡ��Ԫ�ص�IDֵ
	 */
  function resultantNum(selects,id) {

	var ids = "";
	var len = selects.length;
	if(len > 0 && id!=''){
		for(var i=0 ; i<len; i++){
			ids = ids + selects[i].get(id) + ",";
		}
		ids = ids.substr(0,ids.length-1);
	}
	return ids;
  }

  //���
  var ID = new Ext.form.TextField({
    fieldLabel: '���',
    anchor: '97%',
    name: 'id',
    id: 'id',
    allowBlank: false,
    maxLength: 5
  });
  //��ʼʱ��
  var STIME = new Ext.form.TextField({
    fieldLabel: '����ʼʱ��11',
    anchor: '97%',
    name: 'startTime',
    id: 'startTime',
    allowBlank: false,
    maxLength: 50
  });
  //����ʱ��
  var ETIME = new Ext.form.TextField({
    fieldLabel: '�������ʱ��',
    anchor: '97%',
    name: 'endTime',
    id:'endTime',
    allowBlank: false,
    maxLength: 50
  });
  //��Ч��־λ
  var FLAG = new Ext.form.TextField({
    fieldLabel: '��Ч��־',
    anchor: '97%',
    name: 'flag',
    id: 'flag',
    allowBlank: false,
    maxLength: 2
  });
  //��ϯ����
  var VIPCODE = new Ext.form.TextField({
    fieldLabel: '��ϯ����',
    anchor: '97%',
    name: 'vipcode',
    id: 'vipcode',
    allowBlank: false,
    maxLength: 20,
    maxLengthText: '������󳤶�20'
  });
  //�޸���ϯ����
  var VIPNAME = new Ext.form.TextField({
    fieldLabel: '��ϯ����',
    anchor: '97%',
    name: 'vipname',
    id: 'vipname',
    allowBlank: false,
    maxLength: 50,
    maxLengthText: '������󳤶�50'
  });
  //����ʱ��ϵͳʱ��
  var UPDATETIME = new Ext.form.DateField({
    fieldLabel: '����ʱ��',
    anchor: '97%',
    name: 'updateTime',
    id: 'updateTime',
    format: 'Y-m-d H:i:s'
    
  });

  //��ѯģ��
  var dataset = {
    layout: 'column',
    xtype: 'fieldset',
    autoWidth: true,
    buttonAlign: 'center',
    labelAlign: 'right',
    title: 'VIP��ϯ����ʱ�����',
    checkboxToggle: true,
    items:[{
      layout: 'form',
      columnWidth: .18
    },{
      columnWidth: .40,
      layout: 'form',
      items: [{
        xtype: 'textfield',
        fieldLabel:	'���',
        allowBlank: true,
        mode: 'local',
        width: 185,
        name: 'VIP_NAME',
        id:	'VIP_ID',
        maxLength:	10,
        maxLengthText: '������󳤶�10'
      }]
    },{
     columnWidth: .2,
     layout: 'form',
     items: [{
    	  xtype: 'button',
    	  text: '��ѯ',
    	  handler: loadData,
    	  anchor: '60%'
      }]
    }]
  };

  //����
  var store = new Ext.data.Store({
    url: '../../VIPTimeSettingAction.do?type=selectFault',
    reader: new Ext.data.JsonReader({
      root:'rows',  //��Ӧ�а�����¼���ݵĽڵ��Ӧ�ĸ��������ƣ�����������
      totalProperty:'total' //�����е����м�¼��������,����
    },[
      {name:'ID'},
      {name:'BENTIME'},
      {name:'ENDTIME'},
      {name:'LOGINCODE'},
      {name:'AGENTNAME'},
      {name:'UPDATETIME'},
      {name:'FLAG'}
    ]),
    baseParams:{
      start: 0,
      limit: pagesize
    }
  });

  store.on('beforeload', function(store){
    var vip_id = "";
    try{
      vip_id = Ext.getCmp('VIP_ID').getValue().replace(/\"/g,"").replace(/\'/g,"");
    }catch(e){

    }
    var param = {ID:vip_id};
    Ext.apply(store.baseParams, param);
  });

  function loadData(){
    store.load();
  };

  function resetForm(){
    form.getForm().reset();
  };

  function addMeetManage(){
	  var meetform = new Ext.FormPanel({
		  border: false,
		  frame: true,
		  layout: 'column',
		  labelAlign: 'right',
		  items: [{
			  layout: 'form',
			  border: false,
			  style: 'padding 5xp',
			  items: [{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '���',
					  name:	'id',
					  id: 'id',
					  anchor: '48%',
					  allowBlank: false,
					  maxLength: 10,
					  maxLengthText: '������󳤶�10'
				  }]
			  },{
				  columnWidth : .9,
				  layout : 'form',
				  items : [{
					  xtype: 'textfield',
					  fieldLabel : '����ʼʱ��',
					  editable : true,
					  anchor: '48%',
					  name: 'startTime',
					  id: 'startTime',
					  maxLength:20
				  }]
			  },{
				  columnWidth : .9,
				  layout : 'form',
				  items : [{
					  xtype: 'textfield',
					  fieldLabel: '�������ʱ��',
					  editable: true,
					  anchor: '48%',
					  name: 'endTime',
					  id: 'endTime',
					  maxLength:20
				  }]
			  },{
//				  columnWidth : .9,
//				  layout : 'form',
//				  items : [{
//					  xtype: 'textfield',
//					  fieldLabel: '��Ч��־',
//					  editable: true,
//					  anchor: '48%',
//					  name: 'flag',
//					  id: 'flag',
//					  maxLength:20
//				  }]
				  columnWidth : .9,
				  layout : 'form',
				  items : [{
					  xtype	: 'combo',
					  fieldLabel: '��Ч��־',
					  name: 'flag',
					  id: 'flag',
					  width: 150,
					  store: type_data,
					  editable: false,
					  emptyText: '��ѡ��',
					  triggerAction : 'all',
					  anchor: '48%',
					  mode: 'local',
					  valueField: 'value', //?
					  displayField: 'name'	//?
				  }]
			  },{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '��ϯ����',
					  name:	'vipcode',
					  id: 'vipcode',
					  anchor: '48%',
					  value: AGENTID,
					  allowBlank: false,
					  maxLength: 20,
					  maxLengthText: '������󳤶�20'
				  }]

			  },{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '��ϯ����',
					  name:	'vipname',
					  id: 'vipname',
					  anchor: '48%',
					  value: AGENTNAME,
					  allowBlank: false,
					  maxLength: 50,
					  maxLengthText: '������󳤶�50'
				  }]
			  },{
          columnWidth: .9,
          layout: 'form',
          items: [{
            xtype: 'hidden',
            fieldLabel: '����ʱ��',
            editable: false,
            readOnly: true,
            anchor: '48%',
            name: 'updateTime',
            id: 'updateTime',
            value: new Date(),
            format : 'Y-m-d H:i:s'
          }]
        }]
		  }]
	  });
	  var meetwin = null;
	  if(meetwin == null){
		  meetwin = new Ext.Window({
			  title: 'VIP��ϯ����ʱ������',
			  closeable	:	true,
			  width	: 700,
			  height 	:	260,
			  plain	:	true,
			  resizable	:	false,
			  modal : true,
			  iconCls	:	'win',
			  border 	: 	false,
			  buttonAlign :	'center',
			  bodyStyle	:	'padding:5px',
			  items 	:	meetform,
			  buttons: [{
				  text: '����',
				  handler: function(){
					  var ID = Ext.getCmp('id').getValue();
				      var STIME = Ext.getCmp('startTime').getValue();
				      var ETIME = Ext.getCmp('endTime').getValue();
				      var VIPCODE = Ext.getCmp('vipcode').getValue();
				      var VIPNAME = Ext.getCmp('vipname').getValue();
				      var UPDATETIME = Ext.getCmp('updateTime').getValue();
				      var FLAG = Ext.getCmp('flag').getValue();
//					  STIME=Ext.util.Format.date(STIME, 'H:i:s');
//					  ETIME=Ext.util.Format.date(ETIME, 'H:i:s');
                      UPDATETIME=Ext.util.Format.date(UPDATETIME, 'Y-m-d H:i:s');
					  if(ID == "" || ID == null){
						  Ext.Msg.alert('��Ϣ��ʾ',"���Ϊ������!");
						  return;
					  }
					  if(VIPCODE == "" || VIPCODE == null){
						  Ext.Msg.alert('��Ϣ��ʾ',"��ϯ���Ϊ������!");
						  return;
					  }
					  if(VIPNAME == "" || VIPNAME == null){
						  Ext.Msg.alert('��Ϣ��ʾ',"��ϯ����Ϊ������!");
						  return;
					  }
					  var form = meetform.getForm();
					  form.submit({
						  url : '../../VIPTimeSettingAction.do?type=insertFault',
						  //�������Ҫ����Ĳ�����ǰ��������ݿ��Ӧ���ֶ�
						  params: {ID:ID, BENTIME:STIME, ENDTIME:ETIME, LOGINCODE:VIPCODE, AGENTNAME:VIPNAME, FLAG:FLAG, UPDATETIME:UPDATETIME},
						  scope: this,
						  success: function(form, action){
							  store.reload();
							  meetwin.destroy();
							  Ext.Msg.alert('��Ϣ��ʾ', action.result.msg);
						  },
						  failure: function(form, action){
							  store.reload();
							  meetwin.destroy();
							  Ext.Msg.alert('��Ϣ��ʾ', action.result.msg);
						  }
					  });
				  }
			  },{
				  text:'�ر�',
				  handler: function(){
					  meetwin.destroy();
				  }
			  }]

		  });
	  }
	  meetwin.show();
  };




  //ά�����޸���Ϣ
  function meetManage(id, startTime, endTime, vipcode, vipname, updateTime, flag){
	  var meetform = new Ext.FormPanel({
		  border: false,
		  frame: true,
		  layout: 'column',
		  labelAlign: 'right',
		  items: [{
			  layout: 'form',
			  border: false,
			  style: 'padding 5xp',
			  items: [{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '���',
					  name:	'id',
					  id: 'id',
					  anchor: '48%',
					  value: id,
					  allowBlank: false,
					  maxLength: 10,
					  maxLengthText: '������󳤶�10'
				  }]
			  },{
				  columnWidth : .9,
				  layout : 'form',
				  items : [{
					  xtype: 'textfield',
					  fieldLabel : '����ʼʱ��',
					  editable : true,
					  anchor: '48%',
					  name: 'startTime',
					  id: 'startTime',
					  maxLength:20,
					  value: startTime
				  }]
			  },{
				  columnWidth : .9,
				  layout : 'form',
				  items : [{
					  xtype: 'textfield',
					  fieldLabel: '�������ʱ��',
					  editable: true,
					  anchor: '48%',
					  name: 'endTime',
					  id: 'endTime',
					  maxLength:20,
					  value: endTime
				  }]
			  },{
//				  columnWidth : .9,
//				  layout : 'form',
//				  items : [{
//					  xtype: 'textfield',
//					  fieldLabel: '��Ч��־',
//					  editable: true,
//					  anchor: '48%',
//					  name: 'flag',
//					  id: 'flag',
//					  maxLength:20,
//					  value: flag
//				  }]
			  columnWidth : .9,
			  layout : 'form',
			  items : [{
				  xtype	: 'combo',
				  fieldLabel: '��Ч��־',
				  name: 'flag',
				  id: 'flag',
				  width: 150,
				  value: flag,
				  store: type_data,
				  editable: false,
				  emptyText: '��ѡ��',
				  triggerAction : 'all',
				  anchor: '48%',
				  mode: 'local',
				  valueField: 'value', //?
				  displayField: 'name'	//?
			  }]
			  },{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '��ϯ����',
					  name:	'vipcode',
					  id: 'vipcode',
					  anchor: '48%',
					  allowBlank: false,
					  value: vipcode,
					  maxLength: 20,
					  maxLengthText: '������󳤶�20'
				  }]

			  },{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '��ϯ����',
					  name:	'vipname',
					  id: 'vipname',
					  anchor: '48%',
					  allowBlank: false,
					  value: vipname,
					  maxLength: 50,
					  maxLengthText: '������󳤶�50'
				  }]
			  },{
                  columnWidth: .9,
                  layout: 'form',
                  items: [{
                    xtype: 'hidden',
                    fieldLabel: '����ʱ��',
                    editable: false,
                    readOnly: true,
                    anchor: '48%',
                    name: 'updateTime',
                    id: 'updateTime',
                    value: new Date(),
                    format : 'Y-m-d H:i:s'
                  }]
				}]
		  }]

	  });
	  var meetwin = null;
	  if(meetwin == null){
		  meetwin = new Ext.Window({
			  title: 'VIP��ϯ����ʱ���޸�',
			  closeable: true,
			  width: 700,
			  height: 260,
			  plain: true,
			  resizable: false,
			  modal: true,
			  iconCls: 'win',
			  border: false,
			  buttonAlign: 'center',
			  bodyStyle: 'padding:5px',
			  items: meetform,
			  buttons: [{
				  text: '�޸�',
				  handler: function(){
					  var ID = Ext.getCmp('id').getValue();
				      var STIME = Ext.getCmp('startTime').getValue();
				      var ETIME = Ext.getCmp('endTime').getValue();
				      var VIPCODE = Ext.getCmp('vipcode').getValue();
				      var VIPNAME = Ext.getCmp('vipname').getValue();
				      var UPDATETIME = Ext.getCmp('updateTime').getValue();
				      var FLAG = Ext.getCmp('flag').getValue();
				      UPDATETIME=Ext.util.Format.date(UPDATETIME, 'Y-m-d H:i:s');
					  if(ID == "" || ID == null){
						  Ext.Msg.alert('��Ϣ��ʾ',"���Ϊ������!");
						  return;
					  }
					  if(VIPCODE == "" || VIPCODE == null){
						  Ext.Msg.alert('��Ϣ��ʾ',"��ϯ���Ϊ������!");
						  return;
					  }
					  if(VIPNAME == "" || VIPNAME == null){
						  Ext.Msg.alert('��Ϣ��ʾ',"��ϯ����Ϊ������!");
						  return;
					  }
					  var form = meetform.getForm();
					  form.submit({
						  url : '../../VIPTimeSettingAction.do?type=updateFault',
						  params: {ID:ID, BENTIME:STIME, ENDTIME:ETIME, LOGINCODE:VIPCODE, AGENTNAME:VIPNAME, FLAG:FLAG, UPDATETIME:UPDATETIME}, 
						  scope: this,
						  success: function(form, action){
							  store.reload();
							  meetwin.destroy();
							  Ext.Msg.alert('��Ϣ��ʾ', action.result.msg);
						  },
						  failure: function(form, action){
							  store.reload();
							  meetwin.destroy();
							  Ext.Msg.alert('��Ϣ��ʾ', action.result.msg);
						  }
					  });
				  }
			  },{
				  text:'�ر�',
				  handler: function(){
					  meetwin.destroy();
				  }
			  }]

		  });
	  }
	  meetwin.show();
  };
  smcheck = new Ext.grid.CheckboxSelectionModel({checkOnly:true});
  var taskGrid = new Ext.grid.GridPanel({
	  	id: 'grid_grid',
		sm: smcheck,
		store: store,
		height: Ext.get("form").getHeight()-202,
		loadMask: {msg:'���ڼ������ݣ����Ժ�......'},
		colModel: new Ext.grid.ColumnModel({
      columns: [
        smcheck,
        new Ext.grid.RowNumberer(),
        {header: '���', dataIndex:'ID', width:50, sortable:true, align: 'center'},
        {header: '����ʼʱ��', dataIndex:'BENTIME', width:100, sortable:true, align: 'center'},
        {header: '�������ʱ��', dataIndex:'ENDTIME', width:100, sortable:true, align: 'center'},
        {header: '��ϯ����', dataIndex:'LOGINCODE', width:100, sortable:true, align: 'center'},
        {header: '��ϯ����', dataIndex:'AGENTNAME', width:100, sortable:true, align: 'center'},
        {header: '����ʱ��', dataIndex:'UPDATETIME', width:100, sortable:true, align: 'center'},
        {header: '��Ч��־', dataIndex:'FLAG', width:100, sortable:true, align: 'center'}
      ]
    }),
    listeners: {
      celldblclick: function(grid, rowIndex, columnIndex, e){
        var record = grid.getStore().getAt(rowIndex);
        var ID = record.get('ID');
        var STIME = record.get('BENTIME');
        var ETIME = record.get('ENDTIME');
        var VIPCODE = record.get('LOGINCODE');
        var VIPNAME = record.get('AGENTNAME');
        var UPDATETIME = record.get('UPDATETIME');
        var FLAG = record.get('FLAG');

        meetManage(ID, STIME, ETIME, VIPCODE, VIPNAME, UPDATETIME, FLAG);
      },
      afterrender: loadData
    },
    viewConfig:{
      forceFit: true,
      scrollOffset: 0
    },
    stripeRows: true,
    tbar: [{
      /*iconCls : 'icon-refresh',*/
			icon:'../../images/common/refresh.gif',
			text: 'ˢ��',
			handler : function() {
				store.reload();
			}
    },'-',{
      text: '����',
			/*iconCls : 	'icon-add',*/
			icon: '../../images/common/add.png',
			handler : function(){
				addMeetManage();
			}
    },'-',{
      text: 'ά��',
      iconCls: 'icon-edit',
      handler : function(){
        if (smcheck.getCount() == 0){
          Ext.Msg.alert('��Ϣ��ʾ',	'��ѡ��Ҫ�޸ĵķ���ʱ��');
        }else if (smcheck.getCount() > 1){
          Ext.Msg.alert('��Ϣ��ʾ',	'ֻ�ܲ���һ����¼,������ѡ��!');
        }else {
          var selects = smcheck.getSelections();
          var ID = resultantNum(selects, 'ID');
          var STIME = resultantNum(selects, 'BENTIME');
          var ETIME = resultantNum(selects, 'ENDTIME');
          var VIPCODE = resultantNum(selects, 'LOGINCODE');
          var VIPNAME = resultantNum(selects, 'AGENTNAME');
          var UPDATETIME = resultantNum(selects, 'UPDATETIME');
          var FLAG = resultantNum(selects, 'FLAG');
          meetManage(ID, STIME, ETIME, VIPCODE, VIPNAME, UPDATETIME, FLAG);
        }
      }
    },'-',{
      text: 'ɾ��',
      icon: '../../images/common/delete.png',
      handler : function(){
        if (smcheck.getCount() == 0){
          Ext.Msg.alert('��Ϣ��ʾ',	'��ѡ��Ҫɾ���ķ���ʱ��');
        }else{
          var selects = smcheck.getSelections();
          var ID = resultantNum(selects, 'ID');
          Ext.MessageBox.confirm('VIP��ϯ����ʱ��ɾ��','��ȷ���Ƿ�ɾ��', function(btn){
            if (btn == 'yes'){
              Ext.Ajax.request({
                url: '../../VIPTimeSettingAction.do?type=deleteFault',
                params: {ID:ID},
                success: function(response){
                  var obj = Ext.decode(response.responseText);
                  var msg = obj.msg;
                  Ext.Msg.alert('��ʾ', msg);
                  store.reload();
                },
                failure: function(response){
                  var obj = Ext.decode(response.responseText);
                  var msg = obj.msg;
                  Ext.Msg.alert('��ʾ', msg);
                  store.reload();
                }
              });
            }
          });
        }
      }
    }],
    bbar: new Ext.PagingToolbar({
      pageSize: pagesize,
			store: store,
			displayInfo: true,
			displayMsg: '��ǰ��ʾ {0}   �� ��   {1}����¼ ,�ܼ�¼Ϊ {2} ',
			emptyMsg: 'û�м�¼��'
    })
  });

  var form = new Ext.FormPanel({
    id: 'form_form',
    title:	'��ǰλ�ã��ۺϹ��� >> ��������>> VIP��ϯ����ʱ������',
    bodyStyle:	'padding:10px',
    frame:	true,
    autoScroll:	true,
    labelWidth: 60,
    width: ymwidth,
    autoHeight:	true,
    items:	[dataset,taskGrid],
    html: 	'<div id="showdata"></div>',
    renderTo:	'form'
  });
  return form;

};
